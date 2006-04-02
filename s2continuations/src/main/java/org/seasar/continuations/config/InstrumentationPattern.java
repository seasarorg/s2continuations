package org.seasar.continuations.config;

/**
 * @author t-wada
 */
public interface InstrumentationPattern {
    boolean matches(String name);
}
