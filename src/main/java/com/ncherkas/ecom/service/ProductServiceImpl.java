package com.ncherkas.ecom.service;

import com.ncherkas.ecom.dao.ProductDao;
import com.ncherkas.ecom.domain.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by nazariycherkas on 11/30/14.
 */
@Service
@Transactional
public class ProductServiceImpl implements ProductService {

    private final ProductDao productDao;

    @Autowired
    public ProductServiceImpl(ProductDao productDao) {
        this.productDao = productDao;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Product> getAllProducts() {
        return productDao.getAllProducts();
    }

    @Override
    public int insertProduct(Product product) {
        return productDao.insertProduct(product);
    }
}
