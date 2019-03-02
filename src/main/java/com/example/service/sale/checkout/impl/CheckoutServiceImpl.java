package com.example.service.sale.checkout.impl;

import com.example.model.shoppingCart.ShoppingCart;
import com.example.service.delivery.DeliveryService;
import com.example.service.sale.checkout.CheckoutService;
import com.example.service.shoppingCart.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * User: Erman PAYASLI
 * Date: 2.03.2019
 * Time: 19:39
 */
@Service("checkoutService")
public class CheckoutServiceImpl implements CheckoutService
{

    @Autowired
    ShoppingCartService shoppingCartService;

    @Autowired
    DeliveryService deliveryService;


    @Override
    public ShoppingCart checkoutCart(ShoppingCart cart)
    {
        shoppingCartService.getTotalAmountAfterDiscounts(cart);

        deliveryService.calculateDeliveryCostOfCart(cart);

        return cart;
    }
}
