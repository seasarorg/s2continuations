package org.seasar.continuations.sample.impl;

import org.seasar.continuations.sample.PlainInterface2;

/**
 * @author t-wada
 */
public class PlainInterface2Impl implements PlainInterface2 {
    public String execute() {
        pause("paused1");
        pause("paused2");
        return "finished";
    }
    public void pause(String param) {
    }
}
