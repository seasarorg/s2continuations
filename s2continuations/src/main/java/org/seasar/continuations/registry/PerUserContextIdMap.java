package org.seasar.continuations.registry;

/**
 * @author t-wada
 */
public interface PerUserContextIdMap {
    void put(Object key, String contextId);
    String remove(Object key);
    String get(Object key);
}
