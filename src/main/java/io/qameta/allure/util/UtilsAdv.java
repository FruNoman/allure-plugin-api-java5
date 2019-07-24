package io.qameta.allure.util;

import java.util.Map;

public class UtilsAdv<K, V> {

    public V getOrDefault(Map<K, V> map, Object key, V defaultValue) {
        V v;
        return (((v = map.get(key)) != null) || map.containsKey(key))
                ? v
                : defaultValue;
    }

}
