package com.example.service.coupon;

import com.example.model.shoppingCart.ShoppingCart;
import org.springframework.stereotype.Component;

/**
 * User: Erman PAYASLI
 * Date: 1.03.2019
 * Time: 08:23
 */
@Component
public interface CouponService
{
    public void applyCouponToCart(ShoppingCart cart);
}
