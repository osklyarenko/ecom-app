package com.ncherkas.ecom.domain.searchPattern;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.ncherkas.ecom.domain.ProductType;
import com.ncherkas.ecom.jackson.OptionalStringDeserializer;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;

/**
 * Created by nazariycherkas on 12/2/14.
 */
@JsonIgnoreProperties({ "priceGreaterThan", "priceLessThan", "createDateFromOpt", "createDateToOpt" })
public class ProductSearchPattern {

    private ProductType type;
    private String name;
    @JsonProperty("description")
    @JsonDeserialize(using = OptionalStringDeserializer.class)
    private Optional<String> descriptionOpt;
    @JsonProperty("priceGreaterThan")
    private Optional<BigDecimal> priceGreaterThanOpt;
    @JsonProperty("priceLessThan")
    private Optional<BigDecimal> priceLessThanOpt;
    @JsonProperty("createDateFrom")
    private Optional<LocalDateTime> createDateFromOpt;
    @JsonProperty("createDateTo")
    private Optional<LocalDateTime> createDateToOpt;

    public ProductType getType() {
        return type;
    }

    public String getName() {
        return name;
    }

    public Optional<String> getDescriptionOpt() {
        return descriptionOpt;
    }

    public Optional<BigDecimal> getPriceGreaterThanOpt() {
        return priceGreaterThanOpt;
    }

    public Optional<BigDecimal> getPriceLessThanOpt() {
        return priceLessThanOpt;
    }

    public Optional<LocalDateTime> getCreateDateFromOpt() {
        return createDateFromOpt;
    }

    public Optional<LocalDateTime> getCreateDateToOpt() {
        return createDateToOpt;
    }
}
