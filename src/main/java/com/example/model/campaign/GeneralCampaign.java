package com.example.model.campaign;

import com.example.model.product.Category;
import com.example.model.product.Product;

import java.util.List;

/**
 * User: Erman PAYASLI
 * Date: 28.02.2019
 * Time: 19:36
 */
public class GeneralCampaign extends Campaign
{

    public GeneralCampaign(List<Product> products)
    {
        this.products = products;
    }

    @Override
    public double discount()
    {
        double totalCost = 0;
        double campaignedPrice = 0;
        double discountAmount = 0;

        for(Product product : products){
            totalCost += product.getPrice();
            campaignedPrice += (product.getPrice() - 5);

            discountAmount += ((totalCost - campaignedPrice)*product.getQuantity());
            /*product.setCampaignedPrice(product.getPrice() - 5);
            product.setDiscountedCost(product.getPrice() - 5);*/
        }
        return discountAmount;
    }
}
