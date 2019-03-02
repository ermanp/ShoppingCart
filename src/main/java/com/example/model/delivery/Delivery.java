package com.example.model.delivery;

import lombok.Getter;
import lombok.Setter;

/**
 * User: Erman PAYASLI
 * Date: 28.02.2019
 * Time: 18:08
 */
public class Delivery
{

    @Getter
    @Setter
    private String id;

    @Getter
    @Setter
    private double deliveryCost;


    public Delivery(String id, double deliveryCost)
    {
        this.id = id;
        this.deliveryCost = deliveryCost;
    }

    /*    public double getDeliveryAmount()
    {
        return (this.deliveryAmount*this.deliveryNumber) + fixedCost;
    }*/
}
