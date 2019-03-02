package com.example.service.delivery;

import com.example.model.shoppingCart.ShoppingCart;
import org.springframework.stereotype.Component;

/**
 * User: Erman PAYASLI
 * Date: 28.02.2019
 * Time: 18:30
 */
@Component
public interface DeliveryService
{
    public double calculateDeliveryCostOfCart(ShoppingCart cart);
}
