package com.evalonlabs.booking.engine;

/**
 * Created by Evangelos
 */
public interface Transaction<R> {

    public String getId();

    public R commit();

    public boolean rollback();

    public <T> Transaction<R> set(String key, T val);

    public <T> T get(String key);
}
