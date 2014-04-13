package com.evalonlabs.booking.engine;

/**
 * Created by Evangelos
 */
public interface Transactionable<R> {

    public Transaction<R> begin();
}
