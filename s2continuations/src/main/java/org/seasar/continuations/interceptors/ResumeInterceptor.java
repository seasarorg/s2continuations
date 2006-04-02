package org.seasar.continuations.interceptors;

import org.aopalliance.intercept.MethodInvocation;
import org.seasar.continuations.exception.SContinuableObjectCreationException;
import org.seasar.continuations.helper.ContinuableObjectFactory;
import org.seasar.continuations.helper.ContinuableObjectResumeHelper;
import org.seasar.framework.aop.interceptors.AbstractInterceptor;

/**
 * @author t-wada
 */
public class ResumeInterceptor extends AbstractInterceptor {
    private static final long serialVersionUID = 1L;
    private ContinuableObjectResumeHelper resumeHelper;
    private int componentKeyArgumentIndex;
    
    
    public Object invoke(final MethodInvocation invocation) throws Throwable {
        final Object componentKey = invocation.getArguments()[this.componentKeyArgumentIndex];
        
        return this.resumeHelper.resumeOrCreateNew(componentKey, new ContinuableObjectFactory() {
            public Object createNew() {
                try {
                    return invocation.proceed();
                } catch (Throwable e) {
                    throw new SContinuableObjectCreationException("ECNT0003", new Object[]{componentKey}, e);
                }
            }
        });
    }
    
    
    public void setContinuableObjectResumeHelper(ContinuableObjectResumeHelper continuableObjectResumeHelper) {
        this.resumeHelper = continuableObjectResumeHelper;
    }
    public void setComponentKeyArgumentIndex(int componentKeyArgumentIndex) {
        this.componentKeyArgumentIndex = componentKeyArgumentIndex;
    }
}
