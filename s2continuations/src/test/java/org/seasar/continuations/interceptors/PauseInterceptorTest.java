package org.seasar.continuations.interceptors;

import org.seasar.continuations.sample.PlainInterface;
import org.seasar.continuations.testcase.S2ContinuationsTestBase;
import org.seasar.framework.aop.interceptors.MockInterceptor;

/**
 * @author t-wada
 */
public class PauseInterceptorTest extends S2ContinuationsTestBase {
    
    public void testRegisterOnPause() throws Exception {
        PlainInterface sample = (PlainInterface) getContainer().getComponent("sample");
        String result = sample.execute();
        assertEquals("paused1", result);
        
        MockInterceptor mock = (MockInterceptor) getContainer().getComponent("mock");
        assertTrue(mock.isInvoked("register"));
        
        Object[] args = mock.getArgs("register");
        assertEquals("sample", args[0]);
    }
}
