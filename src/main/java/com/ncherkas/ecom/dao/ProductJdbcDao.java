package com.ncherkas.ecom.dao;

import com.ncherkas.ecom.domain.DomainEntities;
import com.ncherkas.ecom.domain.Product;
import com.ncherkas.ecom.domain.ProductType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.sql.ResultSet;
import java.util.List;
import java.util.Optional;

import static com.google.common.base.Preconditions.*;

/**
 * Created by nazariycherkas on 11/20/14.
 */
@Repository
@Transactional(propagation = Propagation.MANDATORY)
public class ProductJdbcDao implements ProductDao {

    private static final String SELECT_ALL_QUERY = "SELECT * FROM product";
    private static final String SELECT_BY_ID_QUERY = "SELECT * FROM product WHERE product_id = ?";
    private static final String INSERT_QUERY = "INSERT INTO product(" +
            "product_id, " +
            "type," +
            "name," +
            "description," +
            "price) VALUES(nextval('product_id_seq'), ?, ?, ?, ?) RETURNING product_id";
    private static final String DELETE_QUERY = "DELETE FROM product WHERE product_id = ?";

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public ProductJdbcDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = checkNotNull(jdbcTemplate);
    }

    @Transactional(readOnly = true)
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

    @Transactional(readOnly = true)
    @Override
    public Optional<Product> getProductById(int productId) {
        return jdbcTemplate.query(SELECT_BY_ID_QUERY, new Object[]{ productId }, (ResultSet rs) ->
            rs.next() ? Optional.of(DomainEntities.newProduct()
                    .setId(rs.getInt("product_id"))
                    .setType(ProductType.fromValue(rs.getString("type"))
                            .orElseThrow(IllegalStateException::new))
                    .setName(rs.getString("name"))
                    .setDescription(rs.getString("description"))
                    .setPrice(rs.getBigDecimal("price"))
                    .setCreatedTimepoint(rs.getTimestamp("created_timepoint")
                            .toLocalDateTime())) : Optional.<Product>empty());
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
        return jdbcTemplate.queryForObject(INSERT_QUERY, queryArgs, Integer.class);
    }

    @Override
    public boolean deleteProductById(int productId) {
        return jdbcTemplate.update(DELETE_QUERY, productId) > 0;
    }
}
