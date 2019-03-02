package com.example.service.shoppingCart.impl;

import com.example.model.campaign.Campaign;
import com.example.model.campaign.GeneralCampaign;
import com.example.model.campaign.MoreThanFiveCampaign;
import com.example.model.campaign.MoreThanThreeCampaign;
import com.example.model.discount.coupon.Coupon;
import com.example.model.shoppingCart.ShoppingCart;
import com.example.model.product.Category;
import com.example.model.product.Product;
import com.example.service.campaign.CampaignService;
import com.example.service.coupon.CouponService;
import com.example.service.delivery.DeliveryService;
import com.example.service.shoppingCart.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * User: Erman PAYASLI
 * Date: 28.02.2019
 * Time: 20:59
 */
@Service("shoppingCartService")
public class ShoppingCartServiceImpl implements ShoppingCartService
{

    @Autowired
    @Qualifier("moreThanTwo")
    CampaignService moreThanTwoCampaignService;

    @Autowired
    @Qualifier("moreThanFive")
    CampaignService moreThanFiveCampaignService;

    @Autowired
    @Qualifier("moreThanThree")
    CampaignService moreThanThreeCampaignService;

    @Autowired
    @Qualifier("deliveryService")
    DeliveryService deliveryService;

    @Autowired
    @Qualifier("generalCouponService")
    CouponService couponService;

    @Override
    public double getTotalAmountAfterDiscounts(ShoppingCart cart)
    {
/*
         Bu metotta önce 76. linedaki commentli alanı yazmıştım, o kod parçasında Category'nin altındaki Productın quantitysini gözardı ediyordu

            * Örn: Cartta FOOD Categorysine ait 5 elma, 8 muz, 2 portakal varsa quantityi gözardı edip moreThanTwo metoduna sokuyordum çünkü sadece 3 çeşit var gibi..

            * Daha sonra bu yaklaşımı değiştirip aşağıdaki for döngüsünü yazdım, burada Category'nin altındaki Productların miktarını hesaba katıyor.
*/
        for(Category category : cart.getCategories()){
            if(Category.calculateProductNumberUnderCategory(category) > 2){
                moreThanTwoCampaignService.applyCampaignToCart(cart,category);
            }
            if(Category.calculateProductNumberUnderCategory(category) > 5){
                moreThanFiveCampaignService.applyCampaignToCart(cart,category);
                continue;
            }
            if(Category.calculateProductNumberUnderCategory(category) > 3){
                moreThanThreeCampaignService.applyCampaignToCart(cart,category);
                continue;
            }
        }

        /*
        Map<Category, List<Product>> productMap = ShoppingCart.groupProductsByCategory(cart);

              /*  productMap.forEach((category, products) -> {
            if(products.size() > 5){
                moreThanFiveCampaignService.applyCampaignToCart(cart,category);
            }

            if(products.size() > 3 && products.size() < 6){
                moreThanThreeCampaignService.applyCampaignToCart(cart,category);

            }

            if (products.size() > 1 && products.size() < 3){
                moreThanTwoCampaignService.applyCampaignToCart(cart,category);

            }
        });
*/


        double lastCampaignedAmount = 0.0;

        for (Product product : cart.getProducts()){
            lastCampaignedAmount += (product.getCampaignedPrice() * product.getQuantity());
        }

        cart.setCampaignedAmount(lastCampaignedAmount);
        cart.setDiscountedAmount(lastCampaignedAmount);

/*
        Coupon indirimini cartın ham fiyatı yerine kampanyalı fiyatı üzerinden işleme soktum case açıklamasında buna dair bir açıklama göremedim.
*/

        couponService.applyCouponToCart(cart);

        return cart.getDiscountedAmount();

    }

    @Override
    public double getDeliveryCost(ShoppingCart cart)
    {
        return deliveryService.calculateDeliveryCostOfCart(cart);
    }

    @Override
    public double getCouponDiscount(ShoppingCart cart, Coupon coupon)
    {
        if(cart.getTotalAmount() > coupon.getMinPurchase()){
            if(coupon.getType().equals("RATE")){
                return coupon.getAmount() * cart.getDiscountedAmount() / 100;
            }
        }
        return 0;
    }

    @Override
    public double getCampaignDiscount(ShoppingCart cart, Campaign campaign)
    {
        /* Metot, Carta kampanyalar dahilinde ne kadar fiyat indirimi olacağını gösterir

               * Category'nin altındaki product sayısı, product quantity de hesaba katılarak alındı

               * Örn: Cartta sadece 1 ürün ama 6 adet varsa Cart moreThanFive ve GeneralCampaign kampanyasına dahil ediliyor

               * GeneralCampaign aynı categorirnin 1den fazla ürünü eklenen cartlar için.

        */

        double totalDiscountAmountForCart = 0;

        for(Category category : cart.getCategories())
        {
            if (cart.getAppliedCampaignNumber() < 3)
            {
                if (Category.calculateProductNumberUnderCategory(category) > 2)
                {
                    double generalCampaignDiscout = new GeneralCampaign(category.getProducts()).discount();
                    totalDiscountAmountForCart += generalCampaignDiscout;
                    cart.increaseAppliedCampaignNumber();

                }

                if (Category.calculateProductNumberUnderCategory(category) > 5)
                {
                    double moreThanFiveDiscount = new MoreThanFiveCampaign(category.getProducts()).discount();
                    totalDiscountAmountForCart += moreThanFiveDiscount;
                    cart.increaseAppliedCampaignNumber();
                    continue;
                }

                if (Category.calculateProductNumberUnderCategory(category) > 3)
                {
                    double moreThanThreeDiscount = new MoreThanThreeCampaign(category.getProducts()).discount();
                    totalDiscountAmountForCart += moreThanThreeDiscount;
                    cart.increaseAppliedCampaignNumber();
                    continue;
                }
            }
        }

        return cart.getTotalAmount() - totalDiscountAmountForCart;
    }
}
