package com.ncherkas.ecom;

import com.ncherkas.ecom.domain.Product;
import com.ncherkas.ecom.exception.EntityNotFoundException;
import com.ncherkas.ecom.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;
import static org.springframework.web.bind.annotation.RequestMethod.DELETE;

/**
 * Created by nazariycherkas on 11/18/14.
 */

@EnableAutoConfiguration
@ComponentScan
@RestController
public class App {

    private ProductService productService;

    @RequestMapping(value = "/", method = GET)
    public String test() {
        return "Hello, World!";
    }

    @RequestMapping(value = "/products", method = GET)
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    @RequestMapping(value = "/products/{productId}", method = GET)
    public Product getProductById(@PathVariable("productId") int productId) throws EntityNotFoundException {
        return productService.getProductById(productId);
    }

    @RequestMapping(value = "/products", method = POST)
    @ResponseStatus(HttpStatus.CREATED)
    public void insertProduct(@RequestBody Product product) {
        int productId = productService.insertProduct(product);
    }

    @RequestMapping(value = "/products/{productId}", method = DELETE)
    @ResponseStatus(HttpStatus.OK)
    public void deleteProduct(@PathVariable("productId") int productId) throws EntityNotFoundException {
        productService.deleteProductById(productId);
    }

    @Autowired
    public void setProductService(ProductService productService) {
        this.productService = productService;
    }

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }
}
