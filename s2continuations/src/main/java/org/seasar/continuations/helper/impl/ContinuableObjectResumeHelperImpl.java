package org.seasar.continuations.helper.impl;

import org.seasar.continuations.exception.SContinuableObjectConstraintException;
import org.seasar.continuations.helper.ContinuableObjectFactory;
import org.seasar.continuations.helper.ContinuableObjectResumeHelper;
import org.seasar.continuations.registry.ContinuationContextRegistry;
import org.seasar.framework.container.ComponentDef;
import org.seasar.framework.container.S2Container;
import org.seasar.framework.log.Logger;

import com.uwyn.rife.continuations.ContinuableObject;
import com.uwyn.rife.continuations.ContinuationContext;

/**
 * @author t-wada
 */
public class ContinuableObjectResumeHelperImpl implements ContinuableObjectResumeHelper {
    private static Logger logger_ = Logger.getLogger(ContinuableObjectResumeHelperImpl.class);
    private S2Container container;
    private ContinuationContextRegistry registry;
    
    
    public Object resumeOrCreateNew(Object componentKey, ContinuableObjectFactory newObjectFactory) {
        ComponentDef cd = getContainer().getComponentDef(componentKey);
        return resumeOrCreateNew(cd, newObjectFactory);
    }
    
    
    public Object resumeOrCreateNew(ComponentDef componentDef, ContinuableObjectFactory newObjectFactory) {
        String name = componentDef.getComponentName();
        Class clazz = componentDef.getComponentClass();
        Object continuable = getContinuableIfExists(componentDef);
        if (continuable != null) {
            logger_.info("## continuable object found. returning it. name=[" + name + "] class=[" + clazz + "]");
            return continuable;
        }
        
        logger_.info("## continuable object not found. create new. name=[" + name + "] class=[" + clazz + "]");
        Object newlyCreated = newObjectFactory.createNew();
        if (newlyCreated instanceof ContinuableObject) {
            // FIXME: static acccess to ContinationContext
            ContinuationContext.createInstance((ContinuableObject) newlyCreated);
        } else {
            logger_.warn("## target component does not implement ContinuableObject. name=[" + name + "] class=[" + clazz + "]");
            throw new SContinuableObjectConstraintException("ECNT0005", clazz.getName());
        }
        return newlyCreated;
    }
    
    
    private Object getContinuableIfExists(ComponentDef componentDef) {
        String componentName = componentDef.getComponentName();
        Class componentClass = componentDef.getComponentClass();
        ContinuationContext context = null;
        if(componentName != null) {
            context = registry.getContextOrNull(componentName);
        } else {
            context = registry.getContextOrNull(componentClass);
        }
        if (context != null) {
            logger_.info("## context object found name=[" + componentName + "] class=[" + componentClass + "]");
            // FIXME: static acccess to ContinationContext
            ContinuationContext.setContext(context);
            return context.getContinuable();
        }
        return null;
    }
    
    
    public void setContinuationContextRegistry(ContinuationContextRegistry continuationContextRegistry) {
        this.registry = continuationContextRegistry;
    }
    public void setContainer(S2Container container) {
        this.container = container;
    }
    private S2Container getContainer() {
        // TODO: returning rootContainer for name-resolution purpose via ResumeInterceptor
        return container.getRoot();
    }
}
