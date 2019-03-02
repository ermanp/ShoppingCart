package com.example.service.coupon.impl;

import com.example.model.shoppingCart.ShoppingCart;
import com.example.service.coupon.CouponService;
import org.springframework.stereotype.Service;

/**
 * User: Erman PAYASLI
 * Date: 1.03.2019
 * Time: 08:24
 */
@Service("generalCouponService")
public class GeneralCouponDiscountImpl implements CouponService
{
    public static final double MIN_PURCHASE = 100.00;

    @Override
    public void applyCouponToCart(ShoppingCart cart)
    {
        if(cart.getDiscountedAmount() > MIN_PURCHASE){
            double couponedAmount = cart.getDiscountedAmount() - (cart.getDiscountedAmount()*0.1);

            cart.setDiscountedAmount(couponedAmount);
        }
    }
}
