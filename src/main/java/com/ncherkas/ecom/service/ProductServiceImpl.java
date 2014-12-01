package com.ncherkas.ecom.service;

import com.ncherkas.ecom.dao.ProductDao;
import com.ncherkas.ecom.domain.Product;
import com.ncherkas.ecom.exception.EntityNotFoundException;
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
    public Product getProductById(int productId) throws EntityNotFoundException {
        return productDao.getProductById(productId).orElseThrow(() ->
                new EntityNotFoundException("Product with id '%s' not found", productId));
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

    @Override
    public void deleteProductById(int productId) throws EntityNotFoundException {
        boolean deleted = productDao.deleteProductById(productId);
        if (!deleted) {
            throw new EntityNotFoundException("Product with id '%s' not found", productId);
        }
    }
}
