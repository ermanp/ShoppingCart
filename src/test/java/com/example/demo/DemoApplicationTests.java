package com.example.demo;

import com.example.model.delivery.Delivery;
import com.example.model.product.Category;
import com.example.model.product.Product;
import com.example.model.shoppingCart.ShoppingCart;
import com.example.service.campaign.CampaignService;
import com.example.service.campaign.impl.MoreThanFiveCampaignImpl;
import com.example.service.campaign.impl.MoreThanThreeCampaignImpl;
import com.example.service.campaign.impl.MoreThanTwoCampaignImpl;
import com.example.service.delivery.DeliveryService;
import com.example.service.delivery.impl.DeliveryServiceImpl;
import com.example.service.sale.checkout.CheckoutService;
import com.example.service.sale.checkout.impl.CheckoutServiceImpl;
import com.example.service.shoppingCart.ShoppingCartService;
import com.example.service.shoppingCart.impl.ShoppingCartServiceImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(loader = AnnotationConfigContextLoader.class)
public class DemoApplicationTests
{

    @TestConfiguration
    static class CartServiceImplTestContextConf
    {
        @Bean
        public ShoppingCartService shoppingCartService()
        {
            return new ShoppingCartServiceImpl();
        }

        @Bean
        public CampaignService campaignService()
        {
            return new MoreThanFiveCampaignImpl();
        }

        @Bean
        public CampaignService campaignService3()
        {
            return new MoreThanThreeCampaignImpl();
        }

        @Bean
        public CampaignService campaignService2()
        {
            return new MoreThanTwoCampaignImpl();
        }

        @Bean
        public DeliveryService deliveryService()
        {
            return new DeliveryServiceImpl();
        }

        @Bean
        public CheckoutService checkoutService()
        {
            return new CheckoutServiceImpl();
        }
    }

    @Autowired
    private ShoppingCartService shoppingCartService;

    @Autowired
    @Qualifier("moreThanFive")
    private CampaignService moreThanFive;

    @Autowired
    @Qualifier("moreThanTwo")
    private CampaignService moreThanTwo;

    @Autowired
    @Qualifier("moreThanThree")
    private CampaignService moreThanThree;

    @Autowired
    CheckoutService checkoutService;

    ShoppingCart shoppingCart;

    @Before
    public void Requirements()
    {
        Category category1 = new Category();
        category1.setCategoryType("FOOD");

        Category category2 = new Category();
        category2.setCategoryType("WEAR");

        Category category3 = new Category();
        category3.setCategoryType("ELECTRONIC");

        Product product1 = new Product("1", "Apple", 6.00, category1, 1, 6.00);
        Product product2 = new Product("2", "Orange", 8.00, category1, 2, 8.00);
        Product product5 = new Product("3", "Banana", 9.00, category1, 1, 9.00);


        Product product3 = new Product("3", "Glasses", 40.00, category2, 6, 40.00);
        Product product4 = new Product("3", "Gloves", 0.00, category2, 6, 105.00);

        Product product6 = new Product("4", "KeyBoard", 0.00, category3, 1, 120.00);


        List<Product> cartProducts = new ArrayList<>();

        cartProducts.add(product1);
        cartProducts.add(product2);
        cartProducts.add(product3);
        cartProducts.add(product4);
        cartProducts.add(product5);
        cartProducts.add(product6);


        Delivery delivery1 = new Delivery("1", 2.00);
        Delivery delivery2 = new Delivery("2", 3.00);

        List<Delivery> deliveries = new ArrayList<>();

        deliveries.add(delivery1);
        deliveries.add(delivery2);

        ShoppingCart shoppingCart = new ShoppingCart(cartProducts);

        shoppingCart.setDeliveries(deliveries);

        this.shoppingCart = shoppingCart;
    }


    @Test
    public void ConfigTest()
    {
        System.out.println(shoppingCartService);
    }

    @Test
    public void ProductTests()
    {


        System.out.println(shoppingCart);
    }

    @Ignore
    @Test
    public void campaignTests()
    {

        /*System.out.println(shoppingCart.getTotalAmount());
        shoppingCartService.getCampaignDiscount(shoppingCart,null);*/

        shoppingCartService.getTotalAmountAfterDiscounts(shoppingCart);

    }


    @Ignore
    @Test
    public void moreThanTwoTest()
    {
        shoppingCartService.getTotalAmountAfterDiscounts(shoppingCart);
        Assert.assertEquals(String.valueOf(3.0), String.valueOf(shoppingCart.getDiscountedAmount()));
    }

    @Ignore
    @Test
    public void moreThanFive()
    {
        shoppingCartService.getTotalAmountAfterDiscounts(shoppingCart);
        Assert.assertEquals(String.valueOf(270.0), String.valueOf(shoppingCart.getDiscountedAmount()));

    }

    @Ignore
    @Test
    public void grouping()
    {
        Map<Category, List<Product>> groupMap = ShoppingCart.groupProductsByCategory(shoppingCart);
        System.out.println(groupMap);
    }

    @Test
    public void coTests()
    {
        ShoppingCart cart = checkoutService.checkoutCart(shoppingCart);
        Map<Category, List<Product>> groupMap = ShoppingCart.groupProductsByCategory(cart);
        groupMap.forEach((category, products) -> {
            System.out.println(category.getCategoryType());
            products.forEach(product -> System.out.printf("%14s, %15s%n", product.getTitle(), String.valueOf(product.getCampaignedPrice())));
        });

    }

    @Test
    public void checkoutTests()
    {

        ShoppingCart cart = checkoutService.checkoutCart(shoppingCart);

        Map<Category, List<Product>> groupMap = ShoppingCart.groupProductsByCategory(cart);
        groupMap.forEach((category, products) -> {
            System.out.println(category.getCategoryType());
            System.out.printf("%14s %15s %16s%n", "Product Name", "Quantity", "Unit Price");
            products.forEach(product -> System.out.printf("%14s %15s %16s%n", product.getTitle(), String.valueOf(product.getQuantity()), String.valueOf(product.getCampaignedPrice())));
        });

        DecimalFormat df = new DecimalFormat("#.##");
        System.out.printf("%14s %15s%n", "TOTAL PRICE:", df.format(shoppingCart.getTotalAmount()));
        System.out.printf("%14s %15s%n", "DISCOUNTED PRICE:", df.format(shoppingCart.getDiscountedAmount()));
        System.out.printf("%14s %15s%n", "DELIVERY COST:", df.format(shoppingCart.getDeliveryCost()));


    }


}
