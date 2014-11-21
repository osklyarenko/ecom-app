package com.ncherkas.ecom.dao;

import com.ncherkas.ecom.domain.Domain;
import com.ncherkas.ecom.domain.Product;
import com.ncherkas.ecom.domain.ProductType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by nazariycherkas on 11/20/14.
 */
@Repository
public class ProductJdbcDao {

    private static final DateTimeFormatter TIMEPOINT_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSSSS");

    private static final String SELECT_ALL_QUERY = "SELECT * FROM product";

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public ProductJdbcDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = checkNotNull(jdbcTemplate, "Constructor arg 'jdbcTemplate' mustn't be null");
    }

    @Transactional(readOnly = true)
    public List<Product> getAllProducts() {
        return jdbcTemplate.query(SELECT_ALL_QUERY, (rs, index) -> {
            String typeValue = rs.getString("type");

            return Domain.newProduct()
                    .setId(rs.getInt("product_id"))
                    .setType(ProductType.fromValue(typeValue).orElseThrow(() -> new IllegalStateException("Failed to " +
                            "resolve product type '" + typeValue + "'")))
                    .setName(rs.getString("name"))
                    .setDescription(rs.getString("description"))
                    .setPrice(rs.getBigDecimal("price"))
                    .setCreatedTimepoint(LocalDateTime.parse(rs.getString("created_timepoint"), TIMEPOINT_FORMATTER));
        });
    }
}
