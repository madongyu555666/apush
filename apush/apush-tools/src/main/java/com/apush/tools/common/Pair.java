package com.apush.tools.common;

/**
 * 获取map格式第一二个值，也就是key，value
 * @author madongyu-ds
 *
 * @param <K>
 * @param <V>
 */
public final class Pair<K, V> {
    public final K key;
    public final V value;

    public Pair(K key, V value) {
        this.key = key;
        this.value = value;
    }

    public K first() {
        return key;
    }

    public V second() {
        return value;
    }

    public static <K, V> Pair<K, V> of(K k, V v) {
        return new Pair<>(k, v);
    }
}
