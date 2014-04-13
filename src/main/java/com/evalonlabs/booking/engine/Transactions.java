package com.evalonlabs.booking.engine;

import com.evalonlabs.booking.engine.transaction.BookTransaction;
import com.evalonlabs.booking.engine.transaction.CancelTransaction;
import com.evalonlabs.booking.engine.transaction.StatusTransaction;

/**
 * Created by Evangelos
 */
public enum Transactions implements Transactionable {
    BOOK {
        @Override
        public BookTransaction begin() {
            return new BookTransaction(IDGenerator.generate());
        }
    },
    CANCEL {
        @Override
        public CancelTransaction begin() {
            return new CancelTransaction(IDGenerator.generate());
        }
    },
    STATUS {
        @Override
        public StatusTransaction begin() {
            return new StatusTransaction(IDGenerator.generate());
        }
    }
}
