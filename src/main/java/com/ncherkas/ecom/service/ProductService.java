package com.ncherkas.ecom.service;

import com.ncherkas.ecom.domain.Product;
import com.ncherkas.ecom.exception.EntityNotFoundException;

import java.util.List;

/**
 * Created by nazariycherkas on 11/30/14.
 */
public interface ProductService {

    List<Product> getAllProducts();

    Product getProductById(int productId) throws EntityNotFoundException;

    int insertProduct(Product product);

    void deleteProductById(int productId) throws EntityNotFoundException;
}
