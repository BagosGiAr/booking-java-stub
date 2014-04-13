package com.evalonlabs.booking.engine;

import com.evalonlabs.booking.engine.model.Book;
import com.evalonlabs.booking.engine.model.Status;
import com.evalonlabs.booking.http.ContextSystem;

/**
 * Created by Evangelos
 */
public class BookSystem implements ContextSystem {

    public void book(final String id, final String name, final CallbackHandler<Book> handler) {
        Transaction transaction = Transactions.BOOK.begin().set("id", id).set("name", name);
        Book result = (Book) transaction.commit();
        if (result == null) {
            transaction.rollback();
        }
        handler.apply(result);
    }

    public void cancel(final String id, final CallbackHandler<Boolean> handler) {
        Transaction transaction = Transactions.CANCEL.begin().set("id", id);
        Boolean result = (Boolean) transaction.commit();
        if (result == null) {
            transaction.rollback();
        }
        handler.apply(result);
    }

    public void getStatus(final CallbackHandler<Status> handler) {
        Transaction transaction = Transactions.STATUS.begin();
        Status result = (Status) transaction.commit();
        if (result == null) {
            transaction.rollback();
        }
        handler.apply(result);
    }
}
