package com.evalonlabs.booking.engine.datasource;

import com.evalonlabs.booking.App;
import com.evalonlabs.booking.engine.model.Book;

/**
 * Created by Evangelos
 */
public final class DB {
    public final static Store<String, Book> BOOKINGS =
            new Store<String, Book>(
                    Integer.valueOf(App.properties.getProperty("db.bookings.max", "50"))
            );
}
