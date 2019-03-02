package com.example.model.product;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

/**
 * User: Erman PAYASLI
 * Date: 28.02.2019
 * Time: 19:22
 */
public class Category
{
    private String id;

    @Getter
    @Setter
    private String categoryType;

    @Getter
    @Setter
    private List<Product> products;

    @Getter
    @Setter
    private String title;


    public Category()
    {
        this.products = new ArrayList<>();
    }


    public static int calculateProductNumberUnderCategory(Category category){

        int total = 0;

        for(Product product : category.getProducts()){
            total += product.getQuantity();
        }

        return total;
    }

}
