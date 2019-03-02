package com.example.demo;

import com.example.service.shoppingCart.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"com.example"})
public class DemoApplication
{

    @Autowired
    ShoppingCartService shoppingCartService;

    public static void main(String[] args)
    {
        SpringApplication.run(DemoApplication.class, args);


    }






}
