package org.seasar.continuations.integrationtest;

import org.seasar.framework.container.S2Container;

/**
 * @author t-wada
 */
public class SampleObjectFactory {
    private S2Container container;
    public void setContainer(S2Container container) {
        this.container = container;
    }
    public Object create(String name) {
        return this.container.getComponent(name);
    }
}
