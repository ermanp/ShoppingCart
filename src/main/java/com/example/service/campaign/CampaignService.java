package com.example.service.campaign;

import com.example.model.shoppingCart.ShoppingCart;
import com.example.model.product.Category;
import org.springframework.stereotype.Component;

/**
 * User: Erman PAYASLI
 * Date: 1.03.2019
 * Time: 07:43
 */
@Component
public interface CampaignService
{
    public void applyCampaignToCart(ShoppingCart cart, Category category);
}
