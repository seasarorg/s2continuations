package org.seasar.continuations.sample.impl;

import org.seasar.continuations.sample.PlainInterface;

/**
 * @author t-wada
 */
public class PlainInterfaceImpl implements PlainInterface {
    public String execute() {
        pause("paused1");
        pause("paused2");
        return "finished";
    }
    public void pause(String param) {
    }
}
