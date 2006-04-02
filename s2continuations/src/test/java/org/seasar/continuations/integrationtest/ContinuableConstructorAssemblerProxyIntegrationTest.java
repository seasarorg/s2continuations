package org.seasar.continuations.integrationtest;

import org.seasar.continuations.sample.PlainInterface;
import org.seasar.continuations.testcase.S2ContinuationsTestBase;

/**
 * @author t-wada
 */
public class ContinuableConstructorAssemblerProxyIntegrationTest extends S2ContinuationsTestBase {
    
    public void testInterceptsAssembler() throws Exception {
        assertEquals("paused1", getContinuable().execute());
        assertEquals("paused2", getContinuable().execute());
        assertEquals("finished", getContinuable().execute());
        assertEquals("paused1", getContinuable().execute());
        assertEquals("paused2", getContinuable().execute());
        assertEquals("finished", getContinuable().execute());
    }
    
    private PlainInterface getContinuable() {
        return ((PlainInterface) getContainer().getComponent(PlainInterface.class));
    }
}
