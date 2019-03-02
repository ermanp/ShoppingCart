package com.example.model.campaign;

import com.example.model.product.Product;

import java.util.List;

/**
 * User: Erman PAYASLI
 * Date: 28.02.2019
 * Time: 19:38
 */
public class MoreThanFiveCampaign extends Campaign
{


    public MoreThanFiveCampaign(List<Product> products)
    {
        this.products = products;
    }

    @Override
    public double discount()
    {
        double totalCost = 0;
        double campaignedPrice = 0;
        double discountAmount = 0;

        for (Product product : products)
        {
            totalCost += product.getPrice();
            campaignedPrice += (product.getPrice() - (product.getPrice() * 0.5));

            discountAmount += ((totalCost - campaignedPrice) * product.getQuantity());
        }
        return discountAmount;
    }
}
