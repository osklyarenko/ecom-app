package com.ncherkas.ecom;

import com.ncherkas.ecom.dao.ProductDao;
import com.ncherkas.ecom.domain.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by nazariycherkas on 11/18/14.
 */

@EnableAutoConfiguration
@ComponentScan
@RestController
public class App {

    private ProductDao productDao;

    @RequestMapping("/")
    public String test() {
        return "Hello, World!";
    }

    @RequestMapping("/products")
    public List<Product> getAllProducts() {
        return productDao.getAllProducts();
    }

    @Autowired
    public void setProductDao(ProductDao productDao) {
        this.productDao = productDao;
    }

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }
}
