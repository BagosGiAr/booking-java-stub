package com.evalonlabs.booking.engine.transaction;

import com.evalonlabs.booking.engine.Transaction;
import com.evalonlabs.booking.engine.datasource.DB;
import com.evalonlabs.booking.engine.model.Book;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Evangelos
 */
public class BookTransaction implements Transaction<Book> {
    private final String id;
    private final Map<String, Object> map;

    public BookTransaction(String id) {
        this.id = id;
        this.map = new HashMap<String, Object>();
    }

    @Override
    public String getId() {
        return this.id;
    }

    @Override
    public Book commit() {
        if(DB.BOOKINGS.isFull() || DB.BOOKINGS.containsKey((String) get("id"))) {
            return null;
        }
        Book book = new Book((String) get("id"), (String) get("name"));
        DB.BOOKINGS.put((String) get("id"), book);
        return book;
    }

    @Override
    public boolean rollback() {
        return false;
    }

    @Override
    public <T> BookTransaction set(String key, T val) {
        map.put(key, val);
        return this;
    }

    @Override
    public <T> T get(String key) {
        return (T) map.get(key);
    }
}
