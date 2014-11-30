package com.ncherkas.ecom.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import java.util.Arrays;
import java.util.Optional;

/**
* Created by nazariycherkas on 11/20/14.
*/
public enum ProductType {

    TYRES("tyres"),
    STEERING_WHEEL("steering_wheel"),
    SHOCK_ABSORBER("shock_absorber"),
    EXHAUST_SYSTEM("exhaust_system"),
    TRANSMISSION("transmission"),
    ENGINE("engine");

    private final String value;

    private ProductType(String value) {
        this.value = value;
    }

    @JsonValue
    public String getValue() {
        return value;
    }

    @JsonCreator
    public static Optional<ProductType> fromValue(String value) {
        return Arrays.stream(values())
                .filter(productType -> productType.getValue().equals(value))
                .findFirst();
    }
}
