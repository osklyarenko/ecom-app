package com.ncherkas.ecom.service;

import com.ncherkas.ecom.domain.Product;

import java.util.List;

/**
 * Created by nazariycherkas on 11/30/14.
 */
public interface ProductService {

    List<Product> getAllProducts();

    int insertProduct(Product product);
}
