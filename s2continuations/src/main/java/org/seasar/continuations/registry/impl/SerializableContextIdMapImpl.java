package org.seasar.continuations.registry.impl;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import org.seasar.continuations.registry.PerUserContextIdMap;

/**
 * @author t-wada
 */
public class SerializableContextIdMapImpl implements PerUserContextIdMap,Serializable {
    private static final long serialVersionUID = 1L;
    private Map map = new HashMap();
    
    public void put(Object key, String contextId) {
        this.map.put(key, contextId);
    }
    public String remove(Object key) {
        return (String) this.map.remove(key);
    }
    public String get(Object key) {
      return (String) this.map.get(key);
    }
}
