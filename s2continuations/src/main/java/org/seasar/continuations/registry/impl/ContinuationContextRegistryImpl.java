package org.seasar.continuations.registry.impl;

import org.seasar.continuations.exception.SContinuableObjectConstraintException;
import org.seasar.continuations.registry.ContinuationContextRegistry;
import org.seasar.continuations.registry.PerUserContextIdMap;
import org.seasar.framework.container.S2Container;

import com.uwyn.rife.continuations.ContinuationContext;
import com.uwyn.rife.continuations.ContinuationManager;

/**
 * @author t-wada
 */
public class ContinuationContextRegistryImpl implements ContinuationContextRegistry {
    private ContinuationManager continuationManager;
    private S2Container container;
    
    
    public void register(Object key, ContinuationContext context) {
        getPerUserContextMap().put(key, context.getId());
        this.continuationManager.addContext(context);
    }
    
    
    public void deregisterIfExists(Object key) {
        String contextId = getPerUserContextMap().remove(key);
        if(contextId != null) {
            try {
                // TODO: needs clone?
                ContinuationContext ctx = this.continuationManager.getContext(contextId);
                this.continuationManager.removeContext(ctx);
            } catch (CloneNotSupportedException e) {
                throw new SContinuableObjectConstraintException("ECNT0004", new Object[]{key}, e);
            }
        }
    }
    
    
    public ContinuationContext getContextOrNull(Object key) {
        String id = getPerUserContextMap().get(key);
        if (id != null) {
            try {
                return this.continuationManager.getContext(id);
            } catch (CloneNotSupportedException e) {
                throw new SContinuableObjectConstraintException("ECNT0004", new Object[]{key}, e);
            }
        }
        return null;
    }
    
    
    private PerUserContextIdMap getPerUserContextMap() {
        return (PerUserContextIdMap) this.container.getComponent(PerUserContextIdMap.class);
    }
    public void setContainer(S2Container container) {
        this.container = container;
    }
    public void setContinuationManager(ContinuationManager continuationManager) {
        this.continuationManager = continuationManager;
    }
}
