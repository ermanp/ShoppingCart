package com.example.model.discount.coupon;


import com.example.model.shoppingCart.ShoppingCart;
import lombok.Getter;
import lombok.Setter;

/**
 * User: Erman PAYASLI
 * Date: 1.03.2019
 * Time: 15:58
 */
public class Coupon
{
    @Getter
    @Setter
    private String id;

    @Getter
    @Setter
    private String name;

    @Getter
    @Setter
    private String code;

    @Getter
    @Setter
    private String type;

    @Getter
    @Setter
    private double minPurchase;

    @Getter
    @Setter
    private double amount;


    public Coupon(String id, String name, String type, double minPurchase, double amount)
    {
        this.id = id;
        this.name = name;
        this.type = type;
        this.minPurchase = minPurchase;
        this.amount = amount;
    }
}
