/**
 * Created by Behrooz on 10/06/2018.
 */
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;


public class TestPriceCalculator {

    PriceCalculator priceCalculatur = new PriceCalculator();

    GroceryItem apple = null;
    GroceryItem milk = null;
    GroceryItem soup = null;
    GroceryItem bread = null;
    GroceryItem coke = null;
    GroceryItem bean = null;
    GroceryItem bannanas = null;
    GroceryItem orange = null;

    @Before
    public void initiateGroceryItems(){

        apple = new GroceryItem("apple",1.0,"APL",MeasurementMethod.COUNT,0.200);
        milk = new GroceryItem("milk",1.3, "MLK",MeasurementMethod.COUNT,0.200);
        soup = new GroceryItem("soup",0.65, "SUP",MeasurementMethod.COUNT,0.200);
        bread = new GroceryItem("bread",0.8, "BRD",MeasurementMethod.COUNT,0.200);
        coke = new GroceryItem("Coke",0.7, "COK",MeasurementMethod.COUNT,0.200);
        bean = new GroceryItem("Beans",0.5, "BNS",MeasurementMethod.COUNT,0.200);
        bannanas = new GroceryItem("Bannanas",1.5, "BNN",MeasurementMethod.WEIGHT,2.00);
        orange = new GroceryItem("Orange",1.99, "ORG",MeasurementMethod.WEIGHT,3);

    }


    /**
     * This is in case more than one
     * type of discount
     * applies to the goods in the basket
     */
   @Test
   public void testCombinationDiscount() {

   }



    @Test
    public void testCodeIsValid(){
        assertTrue(priceCalculatur.isBarCodeValid("APL"));
        assertFalse(priceCalculatur.isBarCodeValid("INVALID"));
    }

    /**
     * in case number of soup cans discount units are
     * less that the number of breads
     * Then all the soup can discounts will apply
     */
    @Test
    public void testPriceCombinationDiscount() {
        PriceCalculator priceCalculator = new PriceCalculator();
        List<GroceryItem> groceryItemList = new ArrayList<GroceryItem>();
        List<PromotionalOffer> promotionalOfferList = new LinkedList<PromotionalOffer>();
        PromotionalOffer promotionalOffer = new PromotionalOffer("COK", 2, DeductionType.PRICE, 0,1);

        promotionalOfferList.add(promotionalOffer);

        Inventory inventory = Mockito.mock(Inventory.class);
        when (inventory.getPromotionalOffersList()).thenReturn(promotionalOfferList);
        priceCalculator.setInventory(inventory);

        groceryItemList.add(apple);
        groceryItemList.add(soup);
        groceryItemList.add(bean);
        groceryItemList.add(bean);
        groceryItemList.add(bean);
        groceryItemList.add(bread);
        groceryItemList.add(coke);
        groceryItemList.add(coke);
        groceryItemList.add(coke);

        double discount = priceCalculator.getTotalDiscount(groceryItemList);
        assertEquals(0.4, discount );

    }

    /**
     * in case number of promoted discount goods are
     * more than the number of discounted
     * only as many discounts as
     * the number of discounted will apply
     */
    @Test
    public void testCountDeductionDiscount() {
        PriceCalculator priceCalculator = new PriceCalculator();
        List<GroceryItem> groceryItemList = new ArrayList<GroceryItem>();
        List<PromotionalOffer> promotionalOfferList = new LinkedList<PromotionalOffer>();
        PromotionalOffer promotionalOffer = new PromotionalOffer("BNS", 3, DeductionType.NUMBER, 2,0, null,0);

        promotionalOfferList.add(promotionalOffer);

        Inventory inventory = Mockito.mock(Inventory.class);
        when (inventory.getPromotionalOffersList()).thenReturn(promotionalOfferList);
        priceCalculator.setInventory(inventory);


        groceryItemList.add(apple);
        groceryItemList.add(soup);
        groceryItemList.add(milk);
        groceryItemList.add(bean);
        groceryItemList.add(bean);
        groceryItemList.add(bean);
        groceryItemList.add(soup);

        double discount = priceCalculator.getTotalDiscount(groceryItemList);
        assertEquals(0.5, discount );

    }

    /**
     * in case number the items in
     * The basket do not match amy os
     * The discound offers
     */
    @Test
    public void testNoDiscountsApply() {
        PriceCalculator priceCalculator = new PriceCalculator();
        List<GroceryItem> groceryItemList = new ArrayList<GroceryItem>();

        List<PromotionalOffer> promotionalOfferList = new LinkedList<PromotionalOffer>();
        PromotionalOffer promotionalOffer = new PromotionalOffer("BNS", 3, DeductionType.NUMBER, 2,0,null,0);

        promotionalOfferList.add(promotionalOffer);

        Inventory inventory = Mockito.mock(Inventory.class);
        when (inventory.getPromotionalOffersList()).thenReturn(promotionalOfferList);
        priceCalculator.setInventory(inventory);

        groceryItemList.add(soup);
        groceryItemList.add(milk);
        groceryItemList.add(bread);
        groceryItemList.add(bread);

        double discount = priceCalculator.getTotalDiscount(groceryItemList);
        assertEquals(0.0, discount );


    }

    /**
     * This methos is supposed to test the price
     * calculation for the groceryItems that are being measured by
     * Their weight rather than count
     */

    @Test
    public void testBasketWithFruits() {
        PriceCalculator priceCalculator = new PriceCalculator();
        List<GroceryItem> basket = new ArrayList<GroceryItem>();
        basket.add(bannanas);
        double price = Double.parseDouble(priceCalculator.calculatePayment(basket));
        assert(price  == 3);
    }


    @Test
    public void testCombinationOfCountWeightOffer() {
        PriceCalculator priceCalculator = new PriceCalculator();
        List<GroceryItem> basket = new ArrayList<GroceryItem>();
        PromotionalOffer promotionalOffer = new PromotionalOffer("BNS", 3, DeductionType.NUMBER, 2,0, null, 0);
         basket.add(bean);
        basket.add(bean);
        basket.add(bean);
        basket.add(bannanas);
        double price = Double.parseDouble(priceCalculator.calculatePayment(basket));
        assert(price  == 4);
    }

    @Test
    public void testOfferOnFruits() {
        PriceCalculator priceCalculator = new PriceCalculator();
        List<GroceryItem> basket = new ArrayList<GroceryItem>();
        List<PromotionalOffer> promotionalOfferList = new LinkedList<PromotionalOffer>();
        PromotionalOffer promotionalOfferBannanas = new PromotionalOffer("BNN", 3, DeductionType.NUMBER, 2,2, null, 0);
        PromotionalOffer promotionalOfferOrange = new PromotionalOffer("ORG", 3, DeductionType.NUMBER, 3,3,null,0);
        promotionalOfferList.add(promotionalOfferBannanas);
        promotionalOfferList.add(promotionalOfferOrange);
        basket.add(bannanas);
        basket.add(orange);


        Inventory inventory = Mockito.mock(Inventory.class);
        when (inventory.getPromotionalOffersList()).thenReturn(promotionalOfferList);
        priceCalculator.setInventory(inventory);
        double price = Double.parseDouble(priceCalculator.calculatePayment(basket));
        assert(price  == 3);

    }

}
