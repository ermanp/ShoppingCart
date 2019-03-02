package com.example.model.shoppingCart;

import com.example.model.delivery.Delivery;
import com.example.model.product.Category;
import com.example.model.product.Product;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * User: Erman PAYASLI
 * Date: 28.02.2019
 * Time: 18:10
 */
public class ShoppingCart
{
    @Getter
    @Setter
    private double totalAmount;

    @Getter
    @Setter
    private double discountedAmount;

    @Getter
    @Setter
    private double campaignedAmount;

    @Getter
    @Setter
    private List<Product> products;

    @Getter
    @Setter
    private List<Delivery> deliveries;

    @Getter
    @Setter
    private double deliveryCost;

    @Getter
    @Setter
    private int appliedCampaignNumber = 0;

    @Getter
    @Setter
    private Set<Category> categories;

    public ShoppingCart(List<Product> products)
    {
        Set<Category> categorySet = new HashSet<>();
        this.products = products;
        for (Product product : products)
        {
            this.totalAmount += (product.getPrice() * product.getQuantity());
            categorySet.add(product.getCategory());
        }

        this.categories = categorySet;
        this.discountedAmount = totalAmount;
        this.campaignedAmount = totalAmount;
    }

    public static Map<Category,List<Product>> groupProductsByCategory(ShoppingCart cart){
        Map<Category,List<Product>> groupByCategoryMap =
                cart.getProducts().stream().collect(Collectors.groupingBy(Product::getCategory));

        return groupByCategoryMap;

    }

    public void increaseAppliedCampaignNumber(){
        this.appliedCampaignNumber ++;
    }



}
