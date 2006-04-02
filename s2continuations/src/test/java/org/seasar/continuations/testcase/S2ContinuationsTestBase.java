package org.seasar.continuations.testcase;

import junit.framework.TestCase;

import org.seasar.framework.container.S2Container;
import org.seasar.framework.container.factory.S2ContainerFactory;
import org.seasar.framework.container.factory.SingletonS2ContainerFactory;
import org.seasar.framework.util.ClassUtil;

/**
 * @author t-wada
 */
public class S2ContinuationsTestBase extends TestCase {
    private S2Container container;
    
    protected String diconName() {
        return ClassUtil.getShortClassName(this.getClass()) + ".dicon";
    }
    public String bootStrapDiconName() {
        return ClassUtil.getShortClassName(this.getClass()) + "_s2container.dicon";
    }
    protected void setUp() throws Exception {
        String packagePath = this.getClass().getPackage().getName().replace('.', '/');
        System.setProperty(S2ContainerFactory.FACTORY_CONFIG_KEY, packagePath + '/' + bootStrapDiconName());
        SingletonS2ContainerFactory.setConfigPath(packagePath + '/' + diconName());
        SingletonS2ContainerFactory.init();
        this.container = SingletonS2ContainerFactory.getContainer();
    }
    protected void tearDown() throws Exception {
        SingletonS2ContainerFactory.destroy();
        System.setProperty(S2ContainerFactory.FACTORY_CONFIG_KEY, S2ContainerFactory.FACTORY_CONFIG_PATH);
    }
    protected S2Container getContainer() {
        return this.container;
    }
}
