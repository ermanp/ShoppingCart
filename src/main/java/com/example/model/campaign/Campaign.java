package com.example.model.campaign;

import com.example.model.product.Category;
import com.example.model.product.Product;

import java.util.List;

/**
 * User: Erman PAYASLI
 * Date: 2.03.2019
 * Time: 13:23
 */
public abstract class Campaign
{
    private String id;

    private String name;

    public abstract double discount();

    List<Product> products;

}
