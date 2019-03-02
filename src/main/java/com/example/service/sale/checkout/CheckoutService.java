package com.example.service.sale.checkout;

import com.example.model.shoppingCart.ShoppingCart;
import org.springframework.stereotype.Component;

/**
 * User: Erman PAYASLI
 * Date: 2.03.2019
 * Time: 19:36
 */
@Component
public interface CheckoutService
{
    ShoppingCart checkoutCart(ShoppingCart cart);
}
