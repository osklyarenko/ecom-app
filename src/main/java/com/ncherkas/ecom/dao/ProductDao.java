package com.ncherkas.ecom.dao;

import com.ncherkas.ecom.domain.Product;

import java.util.List;

/**
 * Created by nazariycherkas on 11/26/14.
 */
public interface ProductDao {
    List<Product> getAllProducts();
}
