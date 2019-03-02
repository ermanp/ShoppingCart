package com.example.service.campaign.impl;

import com.example.model.shoppingCart.ShoppingCart;
import com.example.model.product.Category;
import com.example.model.product.Product;
import com.example.service.campaign.CampaignService;
import org.springframework.stereotype.Service;

/**
 * User: Erman PAYASLI
 * Date: 1.03.2019
 * Time: 08:21
 */
@Service("moreThanTwo")
public class MoreThanTwoCampaignImpl implements CampaignService
{
    @Override
    public void applyCampaignToCart(ShoppingCart cart, Category category)
    {
        for(Product product : category.getProducts()){
            product.setCampaignedPrice(product.getCampaignedPrice() - 5);
        }
    }
}
