package org.seasar.continuations.integrationtest;

import org.seasar.continuations.sample.PlainInterface;
import org.seasar.continuations.testcase.S2ContinuationsTestBase;

/**
 * @author t-wada
 */
public class ResumeInterceptorIntegrationTest extends S2ContinuationsTestBase {
    
    public void testInterceptsFactoryCall() throws Exception {
        assertEquals("paused1", getContinuable().execute());
        assertEquals("paused2", getContinuable().execute());
        assertEquals("finished", getContinuable().execute());
        assertEquals("paused1", getContinuable().execute());
        assertEquals("paused2", getContinuable().execute());
        assertEquals("finished", getContinuable().execute());
    }
    
    private PlainInterface getContinuable() {
        SampleObjectFactory factory = (SampleObjectFactory) getContainer().getComponent(SampleObjectFactory.class);
        return (PlainInterface) factory.create("sample");
    }
}
