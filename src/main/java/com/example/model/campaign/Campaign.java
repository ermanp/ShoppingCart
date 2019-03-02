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
    /*
     * Kampanyaları ayrı classlar olarak ele aldım, belirli offerlarla, ürünlerle ilişkisi veye kendilerine özel şartları olan gibi..
     * Bu yüzden Farklı sayı ile aynı işlemi yapan MoreThanFiveCampaign ve MoreThanThreeCampaign classlarını unique özellikleri olan
     * kampanyalarmış gibi varsayarak yarattım.
     *
     * */
    private String id;

    private String name;

    public abstract double discount();

    List<Product> products;

}
