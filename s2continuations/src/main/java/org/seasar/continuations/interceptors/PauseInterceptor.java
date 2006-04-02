package org.seasar.continuations.interceptors;

import org.aopalliance.intercept.MethodInvocation;
import org.seasar.continuations.registry.ContinuationContextRegistry;
import org.seasar.framework.aop.interceptors.AbstractInterceptor;
import org.seasar.framework.container.ComponentDef;
import org.seasar.framework.log.Logger;

import com.uwyn.rife.continuations.exceptions.PauseException;

/**
 * @author t-wada
 */
public class PauseInterceptor extends AbstractInterceptor {
    private static final long serialVersionUID = 1L;
    private static Logger logger_ = Logger.getLogger(PauseInterceptor.class);
    private ContinuationContextRegistry registry;
    
    
    public Object invoke(MethodInvocation invocation) throws Throwable {
        ComponentDef cd = getComponentDef(invocation);
        String componentName = cd.getComponentName();
        Class componentClass = cd.getComponentClass();
        Object componentKey = null;
        if(componentName != null) {
            componentKey = componentName;
        } else {
            componentKey = componentClass;
        }
        
        try {
            Object proceededResult = invocation.proceed();
            logger_.info("## invocation succeeded normally. name=[" + componentName + "] class=[" + componentClass + "]");
            this.registry.deregisterIfExists(componentKey);
            return proceededResult;
        } catch (PauseException pe) {
            logger_.info("## invocation paused. storing context. name=[" + componentName + "] class=[" + componentClass + "]");
            this.registry.register(componentKey, pe.getContext());
            Object pausedResult = pe.getParameters();
            logger_.info("## returning paused result. result=[" + pausedResult + "] name=[" + componentName + "] class=[" + componentClass + "]");
            return pausedResult;
        } catch(Throwable t) {
            this.registry.deregisterIfExists(componentKey);
            throw t;
        }
    }
    
    
    public void setContinuationContextRegistry(ContinuationContextRegistry continuationContextRegistry) {
        this.registry = continuationContextRegistry;
    }
}
