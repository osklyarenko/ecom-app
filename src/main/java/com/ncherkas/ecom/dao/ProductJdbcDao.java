package com.ncherkas.ecom.dao;

import com.ncherkas.ecom.domain.DomainEntities;
import com.ncherkas.ecom.domain.Product;
import com.ncherkas.ecom.domain.ProductType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by nazariycherkas on 11/20/14.
 */
@Repository
@Transactional(propagation = Propagation.MANDATORY)
public class ProductJdbcDao implements ProductDao {

    private static final String SELECT_ALL_QUERY = "SELECT * FROM product";
    private static final String INSERT_PRODUCT_QUERY = "INSERT INTO product(" +
            "product_id, " +
            "type," +
            "name," +
            "description," +
            "price) VALUES(nextval('product_id_seq'), ?, ?, ?, ?) RETURNING product_id";

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public ProductJdbcDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = checkNotNull(jdbcTemplate);
    }

    @Transactional(propagation = Propagation.MANDATORY, readOnly = true)
    @Override
    public List<Product> getAllProducts() {
        return jdbcTemplate.query(SELECT_ALL_QUERY, (rs, index) ->
            DomainEntities.newProduct()
                    .setId(rs.getInt("product_id"))
                    .setType(ProductType.fromValue(rs.getString("type")).orElseThrow(IllegalStateException::new))
                    .setName(rs.getString("name"))
                    .setDescription(rs.getString("description"))
                    .setPrice(rs.getBigDecimal("price"))
                    .setCreatedTimepoint(rs.getTimestamp("created_timepoint").toLocalDateTime())
        );
    }

    @Override
    public int insertProduct(Product product) {
        checkNotNull(product);
        Object[] queryArgs = {
                product.getType().getValue(),
                product.getName(),
                product.getDescription(),
                product.getPrice()
        };
        return jdbcTemplate.queryForObject(INSERT_PRODUCT_QUERY, queryArgs, Integer.class);
    }
}
