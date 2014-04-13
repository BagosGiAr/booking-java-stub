package com.evalonlabs.booking.engine.transaction;

import com.evalonlabs.booking.engine.Transaction;
import com.evalonlabs.booking.engine.datasource.DB;
import com.evalonlabs.booking.engine.model.Status;

/**
 * Created by Evangelos
 */
public class StatusTransaction implements Transaction<Status> {
    private final String id;

    public StatusTransaction(String id) {
        this.id = id;
    }


    @Override
    public String getId() {
        return this.id;
    }

    @Override
    public Status commit() {
        return new Status(DB.BOOKINGS.getMax() - DB.BOOKINGS.size());
    }

    @Override
    public boolean rollback() {
        return false;
    }

    @Override
    public <T> StatusTransaction set(String key, T val) {
        return this;
    }

    @Override
    public <T> T get(String key) {
        return null;
    }
}
