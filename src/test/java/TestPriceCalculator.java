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

        apple = new GroceryItem("apple",1.0,"APL",MeasurementMethod.COUNT,10);
        milk = new GroceryItem("milk",1.3, "MLK",MeasurementMethod.COUNT,10);
        soup = new GroceryItem("soup",0.65, "SUP",MeasurementMethod.COUNT,10);
        bread = new GroceryItem("bread",0.8, "BRD",MeasurementMethod.COUNT,10);
        coke = new GroceryItem("Coke",0.7, "COK",MeasurementMethod.COUNT,10);
        bean = new GroceryItem("Beans",0.5, "BNS",MeasurementMethod.COUNT,10);
        bannanas = new GroceryItem("Bannanas",1.5, "BNN",MeasurementMethod.WEIGHT,10);
        orange = new GroceryItem("Orange",1.99, "ORG",MeasurementMethod.WEIGHT,10);

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
    public void testIndividualPlusCombinationDiscount() {
        PriceCalculator priceCalculator = new PriceCalculator();
        List<GroceryItem> groceryItemList = new ArrayList<GroceryItem>();
        List<PromotionalOffer> individualPromotionalOfferList = new LinkedList<PromotionalOffer>();
        List<PromotionalOffer> CombinationpromotionalOfferList = new LinkedList<PromotionalOffer>();
        PromotionalOffer combinationpromotionalOffer = new PromotionalOffer("COK",  DeductionType.COMBINATION, 20, "SUP",1);
        PromotionalOffer individualPromotionalAplOffer = new PromotionalOffer("APL",  DeductionType.INDIVIDUAL, 10, null,0);

        PromotionalOffer individualPromotionalCokOffer = new PromotionalOffer("COK",  DeductionType.INDIVIDUAL, 5, null,0);

        CombinationpromotionalOfferList.add(combinationpromotionalOffer);
        individualPromotionalOfferList.add(individualPromotionalCokOffer);
        individualPromotionalOfferList.add(individualPromotionalAplOffer);

        Inventory inventory = Mockito.mock(Inventory.class);
        when (inventory.getIndividualPromotionalOffersList()).thenReturn(individualPromotionalOfferList);
        when (inventory.getCombinedPromotionalOffersList()).thenReturn(CombinationpromotionalOfferList);
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
        assertEquals(0.24, discount );

    }

    @Test
    public void testPriceIncludingIndividualPlusCombinationDiscount() {
        PriceCalculator priceCalculator = new PriceCalculator();
        List<GroceryItem> groceryItemList = new ArrayList<GroceryItem>();
        List<PromotionalOffer> individualPromotionalOfferList = new LinkedList<PromotionalOffer>();
        List<PromotionalOffer> CombinationpromotionalOfferList = new LinkedList<PromotionalOffer>();
        PromotionalOffer combinationpromotionalOffer = new PromotionalOffer("COK",  DeductionType.COMBINATION, 20, "SUP",1);
        PromotionalOffer individualPromotionalOffer = new PromotionalOffer("APL",  DeductionType.INDIVIDUAL, 10, null,0);

        CombinationpromotionalOfferList.add(combinationpromotionalOffer);
        individualPromotionalOfferList.add(individualPromotionalOffer);

        Inventory inventory = Mockito.mock(Inventory.class);
        when (inventory.getIndividualPromotionalOffersList()).thenReturn(individualPromotionalOfferList);
        when (inventory.getCombinedPromotionalOffersList()).thenReturn(CombinationpromotionalOfferList);
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

        String finalPrice = priceCalculator.calculatePayment(groceryItemList);
        assertEquals("5.81", finalPrice );

    }

    /**
     * in case number of promoted discount goods are
     * more than the number of discounted
     * only as many discounts as
     * the number of discounted will apply
     */

    @Test
    public void testPriceCombinationDiscountOnly() {
        PriceCalculator priceCalculator = new PriceCalculator();
        List<GroceryItem> groceryItemList = new ArrayList<GroceryItem>();
        List<PromotionalOffer> individualPromotionalOfferList = new LinkedList<PromotionalOffer>();
        List<PromotionalOffer> CombinationpromotionalOfferList = new LinkedList<PromotionalOffer>();
        PromotionalOffer combinationpromotionalOffer = new PromotionalOffer("COK",  DeductionType.COMBINATION, 20, "SUP",1);


        CombinationpromotionalOfferList.add(combinationpromotionalOffer);

        Inventory inventory = Mockito.mock(Inventory.class);
        when (inventory.getIndividualPromotionalOffersList()).thenReturn(individualPromotionalOfferList);
        when (inventory.getCombinedPromotionalOffersList()).thenReturn(CombinationpromotionalOfferList);
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
        assertEquals(0.14, discount );

    }

    /**
     * This method is supposed to test the price
     * calculation for only the groceryItems
     * that are being measured by Their weight.
     */

    @Test
    public void testIndividualDeductionWeightDiscountOnly() {
        PriceCalculator priceCalculator = new PriceCalculator();
        List<GroceryItem> groceryItemList = new ArrayList<GroceryItem>();
        List<PromotionalOffer> individualPromotionalOfferList = new LinkedList<PromotionalOffer>();
        PromotionalOffer promotionalOffer = new PromotionalOffer("BNN",  DeductionType.INDIVIDUAL, 20, null,0);

        individualPromotionalOfferList.add(promotionalOffer);

        Inventory inventory = Mockito.mock(Inventory.class);
        when (inventory.getIndividualPromotionalOffersList()).thenReturn(individualPromotionalOfferList);
        priceCalculator.setInventory(inventory);


        groceryItemList.add(apple);
        groceryItemList.add(bread);
        groceryItemList.add(soup);
        groceryItemList.add(milk);
        groceryItemList.add(bannanas);
        groceryItemList.add(bannanas);
        groceryItemList.add(bannanas);
        groceryItemList.add(soup);

        double discount = priceCalculator.getTotalDiscount(groceryItemList);
        assertEquals(9.0, discount );

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
        PromotionalOffer promotionalOffer = new PromotionalOffer("BNS",  DeductionType.INDIVIDUAL, 20,null,0);

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
     * This method is s to test the discount
     * calculation for only the groceryItems
     * that are being measured by Their count.
     */

    @Test
    public void testIndividualCountOnlyDiscount() {
        PriceCalculator priceCalculator = new PriceCalculator();
        List<GroceryItem> basket = new ArrayList<GroceryItem>();
        List<PromotionalOffer> promotionalOfferList = new LinkedList<PromotionalOffer>();
        PromotionalOffer promotionalOffer = new PromotionalOffer("BNS",  DeductionType.INDIVIDUAL, 20, null,0);

        promotionalOfferList.add(promotionalOffer);
        Inventory inventory = Mockito.mock(Inventory.class);
        when (inventory.getIndividualPromotionalOffersList()).thenReturn(promotionalOfferList);
        priceCalculator.setInventory(inventory);


        basket.add(bean);
        basket.add(bean);
        basket.add(bannanas);
        basket.add(bannanas);
        basket.add(bread);
        double discount = priceCalculator.getTotalDiscount(basket);
        assert(discount  == 0.2);
    }

    /**
     * This function tests  totalthe discount
     * amount for the basket which
     * contains goods with weight and
     * cont discount
     */

    @Test
    public void testIndividualDiscountOnCountAndWeight() {

        PriceCalculator priceCalculator = new PriceCalculator();
        List<GroceryItem> basket = new ArrayList<GroceryItem>();
        List<PromotionalOffer> promotionalOfferList = new LinkedList<PromotionalOffer>();
        PromotionalOffer promotionalOffer = new PromotionalOffer("BNS",  DeductionType.INDIVIDUAL, 20, null,0);

        PromotionalOffer promotionalOffer_1 = new PromotionalOffer("BNN",  DeductionType.INDIVIDUAL, 20, null,0);

        promotionalOfferList.add(promotionalOffer);
        promotionalOfferList.add(promotionalOffer_1);
        Inventory inventory = Mockito.mock(Inventory.class);
        when (inventory.getIndividualPromotionalOffersList()).thenReturn(promotionalOfferList);
        priceCalculator.setInventory(inventory);


        basket.add(bean);
        basket.add(bean);
        basket.add(bannanas);
        basket.add(bannanas);
        basket.add(bread);
        double discount = priceCalculator.getTotalDiscount(basket);
        assert(discount == 6.2);
    }

}
