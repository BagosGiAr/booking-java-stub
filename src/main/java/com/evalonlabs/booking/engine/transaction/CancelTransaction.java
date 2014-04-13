package com.evalonlabs.booking.engine.transaction;

import com.evalonlabs.booking.engine.Transaction;
import com.evalonlabs.booking.engine.datasource.DB;
import com.evalonlabs.booking.engine.model.Book;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Evangelos
 */
public class CancelTransaction implements Transaction<Boolean> {
    private final String id;
    private final Map<String, Object> map;

    public CancelTransaction(String id) {
        this.id = id;
        this.map = new HashMap<String, Object>();
    }


    @Override
    public String getId() {
        return this.id;
    }

    @Override
    public Boolean commit() {
        if(DB.BOOKINGS.get((String) get("id")) == null) {
            return false;
        }
        DB.BOOKINGS.remove((String) get("id"));
        return true;
    }

    @Override
    public boolean rollback() {
        return false;
    }

    @Override
    public <T> CancelTransaction set(String key, T val) {
        map.put(key, val);
        return this;
    }

    @Override
    public <T> T get(String key) {
        return (T) map.get(key);
    }
}
