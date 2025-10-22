package com.lulobank.insurance.model;

public record ClientInformation(
        String name,
        String lastName,
        String businessName,
        Document document,
        String email,
        Phone phoneInfo,
        String address
) {
}
