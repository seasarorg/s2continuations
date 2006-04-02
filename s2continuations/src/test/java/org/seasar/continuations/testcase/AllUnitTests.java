package org.seasar.continuations.testcase;

import java.lang.reflect.Modifier;

import junit.framework.Test;

import com.gargoylesoftware.base.testing.RecursiveTestSuite;
import com.gargoylesoftware.base.testing.TestFilter;

/**
 * @author t-wada
 */
public class AllUnitTests {
    
    public static Test suite() throws Exception {
        return new RecursiveTestSuite("target/test-classes", new TestFilter() {
            public boolean accept(Class eachTestClass) {
                if(Modifier.isAbstract(eachTestClass.getModifiers())) {
                    return false;
                }
                return true;
            }
        });
    }
    
}
