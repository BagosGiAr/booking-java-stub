package com.evalonlabs.booking.engine.model;

/**
 * Created by Evangelos
 */
public class Status {
    private int total;

    public Status(int total) {

        this.total = total;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    @Override
    public String toString() {
        return "{\"total\":" + total +"}";
    }
}
