package com.ncherkas.ecom.domain;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.ncherkas.ecom.jackson.LocalDateTimeSerializer;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * Created by nazariycherkas on 11/20/14.
 */
public class Product {

    private Integer id;
    private ProductType type;
    private String name;
    private String description;
    private BigDecimal price;

    @JsonSerialize(using = LocalDateTimeSerializer.class)
    private LocalDateTime createdTimepoint;

    protected Product() {
    }

    public Product setId(Integer id) {
        this.id = id;
        return this;
    }

    public Product setType(ProductType type) {
        this.type = type;
        return this;
    }

    public Product setName(String name) {
        this.name = name;
        return this;
    }

    public Product setDescription(String description) {
        this.description = description;
        return this;
    }

    public Product setPrice(BigDecimal price) {
        this.price = price;
        return this;
    }

    public Product setCreatedTimepoint(LocalDateTime createdTimepoint) {
        this.createdTimepoint = createdTimepoint;
        return this;
    }

    public Integer getId() {
        return id;
    }

    public ProductType getType() {
        return type;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public LocalDateTime getCreatedTimepoint() {
        return createdTimepoint;
    }
}
