package com.ncherkas.ecom.domain;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * Created by nazariycherkas on 11/20/14.
 */
public class Product {

    public final Integer id;
    public final ProductType productType;
    public final String name;
    public final String description;
    public final BigDecimal price;
    public final LocalDateTime createdTimepoint;

    public Product(Integer id, ProductType productType, String name, String description, BigDecimal price,
                   LocalDateTime createdTimepoint) {
        this.id = id;
        this.productType = productType;
        this.name = name;
        this.description = description;
        this.price = price;
        this.createdTimepoint = createdTimepoint;
    }
}
