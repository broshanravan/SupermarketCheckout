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
        assert (6 == itemsMap.size());

    }


    @Test
    public void testGetPromotionalOffersList() {
        Inventory inventory = new Inventory();

        inventory.setInventoryFileName("testInventories/testGroceryItems.json");
        inventory.setPrpmotionalFileName("testInventories/testPromotionalOffers.json");

        List<PromotionalOffer> promotionalOfferList = inventory.getPromotionalOffersList();
        assert 2 == promotionalOfferList.size();
    }

    @Test
    public void testGetAllBarCodes() {
        Inventory inventory = new Inventory();
        inventory.setInventoryFileName("testInventories/testGroceryItems.json");
        inventory.setPrpmotionalFileName("testInventories/testPromotionalOffers.json");
        List<String> barCodesList = inventory.getAllBarCodes();
        assert(barCodesList.size() == 6);
        assert(barCodesList.contains("BRD"));
        assert(barCodesList.contains("APL"));
        assert(barCodesList.contains("COK"));
        assert(barCodesList.contains("BNN"));
    }

}
