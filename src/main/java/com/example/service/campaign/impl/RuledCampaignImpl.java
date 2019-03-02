package com.example.service.campaign.impl;

import com.example.model.shoppingCart.ShoppingCart;
import com.example.model.product.Category;
import com.example.service.campaign.CampaignService;
import org.springframework.stereotype.Service;

/**
 * User: Erman PAYASLI
 * Date: 1.03.2019
 * Time: 08:15
 */
// Created for a specified campaign rule
@Service("campaignService")
public class RuledCampaignImpl implements CampaignService
{

    @Override
    public void applyCampaignToCart(ShoppingCart cart, Category category)
    {
    }
}
