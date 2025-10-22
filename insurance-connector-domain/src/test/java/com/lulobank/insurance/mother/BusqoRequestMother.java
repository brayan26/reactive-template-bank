package com.lulobank.insurance.mother;

import com.lulobank.insurance.model.busqo.BusqoRequest;

import java.util.UUID;

public class BusqoRequestMother {

    public static BusqoRequest random() {
        return new BusqoRequest(
                UUID.randomUUID().toString(),
                UUID.randomUUID().toString(),
                UUID.randomUUID().toString(),
                UUID.randomUUID().toString(),
                UUID.randomUUID().toString(),
                UUID.randomUUID().toString()
        );
    }
}
