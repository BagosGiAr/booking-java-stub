package com.evalonlabs.booking.engine;

import java.util.UUID;

/**
 * Created by Evangelos
 */
public class IDGenerator {

    public static String generate() {
        return UUID.randomUUID().toString();
    }
}
