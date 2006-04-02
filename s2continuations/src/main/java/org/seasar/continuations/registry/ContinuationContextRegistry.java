package org.seasar.continuations.registry;

import com.uwyn.rife.continuations.ContinuationContext;

/**
 * @author t-wada
 */
public interface ContinuationContextRegistry {
    void register(Object key, ContinuationContext context);
    void deregisterIfExists(Object key);
    ContinuationContext getContextOrNull(Object key);
}
