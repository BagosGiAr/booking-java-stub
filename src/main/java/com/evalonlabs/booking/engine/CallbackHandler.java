package com.evalonlabs.booking.engine;

/**
 * Created by Evangelos
 */
public interface CallbackHandler<T> {
    void apply(T arg);
}
