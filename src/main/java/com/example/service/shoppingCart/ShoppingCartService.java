package com.example.service.shoppingCart;

import com.example.model.campaign.Campaign;
import com.example.model.discount.coupon.Coupon;
import com.example.model.shoppingCart.ShoppingCart;
import org.springframework.stereotype.Component;

/**
 * User: Erman PAYASLI
 * Date: 28.02.2019
 * Time: 20:58
 */
@Component
public interface ShoppingCartService
{
    public double getTotalAmountAfterDiscounts(ShoppingCart cart);

    public double getDeliveryCost(ShoppingCart cart);

    public double getCouponDiscount(ShoppingCart cart, Coupon coupon);

    public double getCampaignDiscount(ShoppingCart cart, Campaign campaign);

}
