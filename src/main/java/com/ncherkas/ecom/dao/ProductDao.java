package com.ncherkas.ecom.dao;

import com.ncherkas.ecom.domain.Product;

import java.util.List;
import java.util.Optional;

/**
 * Created by nazariycherkas on 11/26/14.
 */
public interface ProductDao {

    List<Product> getAllProducts();

    Optional<Product> getProductById(int productId);

    int insertProduct(Product product);

    boolean deleteProductById(int productId);
}
