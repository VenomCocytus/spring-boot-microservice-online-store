package com.sehkmet.microservices.common.Utils;

import com.fasterxml.jackson.annotation.ObjectIdGenerators;

public class common {

    public static synchronized String generateTimeBasedId() {
        return String.valueOf(new ObjectIdGenerators.UUIDGenerator());
    }
}
