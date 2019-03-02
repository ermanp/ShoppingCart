package com.example.service.delivery.impl;

import com.example.model.delivery.Delivery;
import com.example.model.product.Category;
import com.example.model.product.Product;
import com.example.model.shoppingCart.ShoppingCart;
import com.example.service.delivery.DeliveryService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * User: Erman PAYASLI
 * Date: 1.03.2019
 * Time: 16:19
 */
@Service("deliveryService")
public class DeliveryServiceImpl implements DeliveryService
{
    @Override
    public double calculateDeliveryCostOfCart(ShoppingCart cart)
    {
        /*Case açıklamasında yazan formuldeki PerDeliveryCost, PerProductCost ilişkisini anlayamadım,
        her product'ın delivery instanceı mı var? case açıklamasında buna dair bir açıklama bulamadım.
        Formule göre bir metot yazdım burada ama deliveryCost bir hayli yüksek çıkıyor. :)

        */


        double deliveryCost = 0;

        int numberOfDeliveries = 0;

        int numberOfProducts = 0;

        double fixedCost = 2.99;

        Map<Category, List<Product>> productsByCategoryMap = ShoppingCart.groupProductsByCategory(cart);

        numberOfDeliveries = productsByCategoryMap.size();

        numberOfProducts = cart.getProducts().size();

        for(Delivery delivery : cart.getDeliveries()){
            deliveryCost += (delivery.getDeliveryCost() * numberOfDeliveries);
        }

        for(Product product : cart.getProducts()){
            deliveryCost += (product.getPrice() + cart.getProducts().size());
        }

        deliveryCost += fixedCost;

        cart.setDeliveryCost(deliveryCost);

        return deliveryCost;
    }
}
