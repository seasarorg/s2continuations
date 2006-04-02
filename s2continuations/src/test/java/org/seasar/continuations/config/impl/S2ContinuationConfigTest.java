package org.seasar.continuations.config.impl;

import junit.framework.TestCase;

/**
 * @author t-wada
 */
public class S2ContinuationConfigTest extends TestCase {
    
    public void testMatchesToPackagePattern() throws Exception {
        S2ContinuationConfig config = new S2ContinuationConfig();
        config.addPattern("foo.bar.*");
        assertTrue(config.matches("foo.bar.Baz"));
        assertTrue(config.matches("foo.bar.hoge.Fuga"));
        assertFalse(config.matches("foo.hoge"));
    }
    
    public void testNothingWillBeInstrumentedUnlessPatternSpecified() throws Exception {
        S2ContinuationConfig config = new S2ContinuationConfig();
        assertFalse(config.matches(""));
        assertFalse(config.matches("foo.bar.Baz"));
    }
}
