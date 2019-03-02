package com.example.service.campaign.impl;

import com.example.model.product.Product;
import com.example.model.shoppingCart.ShoppingCart;
import com.example.model.product.Category;
import com.example.service.campaign.CampaignService;
import org.springframework.stereotype.Service;

/**
 * User: Erman PAYASLI
 * Date: 1.03.2019
 * Time: 08:13
 */
@Service("moreThanThree")
public class MoreThanThreeCampaignImpl implements CampaignService
{

    @Override
    public void applyCampaignToCart(ShoppingCart cart, Category category)
    {
        for(Product product : category.getProducts()){
            product.setCampaignedPrice(product.getCampaignedPrice() - (product.getCampaignedPrice() * 0.2));
        }
    }
}
