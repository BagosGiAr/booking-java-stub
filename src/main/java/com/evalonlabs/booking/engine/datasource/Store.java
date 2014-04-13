package com.evalonlabs.booking.engine.datasource;

import java.util.Collection;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by Evangelos
 */
public class Store<K, V> implements Map<K, V> {
    private final int max;
    private final AtomicInteger size;
    private final ConcurrentHashMap<K, V> map;

    public Store() {
        this(Integer.MAX_VALUE);
    }

    public Store(int max) {
        this.max = max;
        this.map = new ConcurrentHashMap<K, V>();
        this.size = new AtomicInteger(0);
    }

    public int getMax() {
        return max;
    }

    @Override
    public int size() {
        return size.get();
    }

    @Override
    public boolean isEmpty() {
        return size.get() == 0;
    }

    public boolean isFull() {
        return size.get() == max;
    }

    @Override
    public boolean containsKey(Object key) {
        return map.containsKey(key);
    }

    @Override
    public boolean containsValue(Object value) {
        return map.containsValue(value);
    }

    @Override
    public V get(Object key) {
        return map.get(key);
    }

    @Override
    public V put(K key, V value) {
        if (size.get() + 1 > max) {
            return null;
        }

        size.getAndIncrement();

        return map.put(key, value);
    }

    @Override
    public V remove(Object key) {
        if (size.get() - 1 < 0) {
            return null;
        }

        size.decrementAndGet();

        return map.remove(key);
    }

    @Override
    public void putAll(Map<? extends K, ? extends V> m) {
        assert this.size.get() + m.size() >= max;

        this.size.addAndGet(m.size());

        map.putAll(m);
    }

    @Override
    public void clear() {
        map.clear();
        size.lazySet(0);
    }

    @Override
    public Set<K> keySet() {
        return map.keySet();
    }

    @Override
    public Collection<V> values() {
        return map.values();
    }

    @Override
    public Set<Entry<K, V>> entrySet() {
        return map.entrySet();
    }
}
