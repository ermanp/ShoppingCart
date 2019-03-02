package com.example.model.product;

import lombok.Getter;
import lombok.Setter;

/**
 * User: Erman PAYASLI
 * Date: 28.02.2019
 * Time: 19:28
 */
public class Product
{

    @Getter
    @Setter
    private String id;

    @Getter
    @Setter
    private String title;

    @Getter
    @Setter
    private Category category;

    @Getter
    @Setter
    private int quantity;

    @Getter
    @Setter
    private double price;

    @Getter
    @Setter
    private double campaignedPrice;

    public Product(String id, String title, double cost, Category category, int quantity, double price)
    {
        this.id = id;
        this.title = title;
        this.category = category;
        this.campaignedPrice = price;
        this.quantity = quantity;
        this.price = price;

        category.getProducts().add(this);

    }



}
