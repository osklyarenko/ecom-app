package com.ncherkas.ecom;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by nazariycherkas on 11/18/14.
 */

@EnableAutoConfiguration
@RestController
public class App {

    @RequestMapping("/")
    public String test() {
        return "Hello, World!";
    }

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }
}
