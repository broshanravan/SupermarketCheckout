import org.junit.Test;

import java.util.List;
import java.util.Map;

/**
 * Created by Behrooz on 10/06/2018..
 */
public class TestInventory {

    @Test
    public void testReadItemsFromFile() {
        Inventory inventory = new Inventory();
        inventory.setInventoryFileName("testInventories/testGroceryItems.json");
        inventory.setPrpmotionalFileName("testInventories/testPromotionalOffers.json");
        Map<String, GroceryItem> itemsMap = inventory.readItemsFromFile();
        assert (7 == itemsMap.size());

    }


    @Test
    public void testGetPromotionalOffersList() {
        Inventory inventory = new Inventory();

        inventory.setInventoryFileName("testInventories/testGroceryItems.json");
        inventory.setPrpmotionalFileName("testInventories/testPromotionalOffers.json");

        List<PromotionalOffer> promotionalOfferList = inventory.getPromotionalOffersList();
        assert 4 == promotionalOfferList.size();
    }

    @Test
    public void testPromotionalOffersListContents() {
        Inventory inventory = new Inventory();

        inventory.setInventoryFileName("testInventories/testGroceryItems.json");
        inventory.setPrpmotionalFileName("testInventories/testPromotionalOffers.json");

        List<PromotionalOffer> promotionalOfferList = inventory.getPromotionalOffersList();

        PromotionalOffer promotionalOffer = promotionalOfferList.get(0);

        assert("SUP".equalsIgnoreCase(promotionalOffer.getPromotedItemBarcode()));
        assert(DeductionType.INDIVIDUAL == promotionalOffer.getDeductionType());
        assert(10 == promotionalOffer.getDiscountRate());
        assert(promotionalOffer.getSubstitutedItemBarcode() == null);
    }

    @Test
    public void testIndividualOffersListContents() {
        Inventory inventory = new Inventory();

        inventory.setInventoryFileName("testInventories/testGroceryItems.json");
        inventory.setPrpmotionalFileName("testInventories/testPromotionalOffers.json");

        List<PromotionalOffer> promotionalOfferList = inventory.getIndividualPromotionalOffersList();

        PromotionalOffer promotionalOffer = promotionalOfferList.get(0);

        assert("SUP".equalsIgnoreCase(promotionalOffer.getPromotedItemBarcode()));
        assert(DeductionType.INDIVIDUAL == promotionalOffer.getDeductionType());
        assert(10 == promotionalOffer.getDiscountRate());
        assert(promotionalOffer.getSubstitutedItemBarcode() == null);
    }
    @Test
    public void testCombinedOffersListContents() {
        Inventory inventory = new Inventory();

        inventory.setInventoryFileName("testInventories/testGroceryItems.json");
        inventory.setPrpmotionalFileName("testInventories/testPromotionalOffers.json");

        List<PromotionalOffer> promotionalOfferList = inventory.getCombinedPromotionalOffersList();

        PromotionalOffer promotionalOffer = promotionalOfferList.get(0);

        assert("BRD".equalsIgnoreCase(promotionalOffer.getPromotedItemBarcode()));
        assert(DeductionType.COMBINATION == promotionalOffer.getDeductionType());
        assert(50 == promotionalOffer.getDiscountRate());
        assert("BNS".equalsIgnoreCase(promotionalOffer.getSubstitutedItemBarcode()));
        assert(promotionalOffer.getSubstitutedItemCount() == 2);

    }

    @Test
    public void testGetAllBarCodes() {
        Inventory inventory = new Inventory();
        inventory.setInventoryFileName("testInventories/testGroceryItems.json");
        inventory.setPrpmotionalFileName("testInventories/testPromotionalOffers.json");
        List<String> barCodesList = inventory.getAllBarCodes();
        assert(barCodesList.size() == 7);
        assert(barCodesList.contains("BRD"));
        assert(barCodesList.contains("APL"));
        assert(barCodesList.contains("COK"));
        assert(barCodesList.contains("BNN"));
    }

}
