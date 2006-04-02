package org.seasar.continuations.extension;

import org.seasar.continuations.testcase.S2ContinuationsTestBase;
import org.seasar.framework.aop.interceptors.MockInterceptor;

/**
 * @author t-wada
 */
public class ContinuableConstructorAssemblerProxyTest extends S2ContinuationsTestBase {
    
    public void testCheckContoniationCacheIfMarkedAsContinuable() throws Exception {
        getContainer().getComponent("continuableSample");
        MockInterceptor mock = (MockInterceptor) getContainer().getComponent("mock");
        assertTrue(mock.isInvoked("resumeOrCreateNew"));
    }
    
    public void testDoesNotCheckCacheIfNotMarkedAsContinuable() throws Exception {
        getContainer().getComponent("nonContinuableSample");
        MockInterceptor mock = (MockInterceptor) getContainer().getComponent("mock");
        assertFalse(mock.isInvoked("resumeOrCreateNew"));
    }
}
