import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by Behrooz on 31/10/2017.
 */
public class TestGroceryItem {

    @Test
    public void testParametrizedConstructor() {
        GroceryItem groceryItem = new GroceryItem("apple", 1, "APL",  "weight");

        assertEquals("apple",groceryItem.getItemName());
        assert(1 == groceryItem.getPrice());
        assertEquals("APL",groceryItem.getItemCode());
        assertEquals("weight",groceryItem.getMeasurementUnit());
    }

    @Test
    public void testSetItemName(){
        GroceryItem groceryItem = new GroceryItem();
        groceryItem.setItemName("apple");
        assertEquals("apple",groceryItem.getItemName());
    }

    @Test
    public void testSetPrice(){
        GroceryItem groceryItem = new GroceryItem();
        groceryItem.setPrice(1);
        assert(1 == groceryItem.getPrice());
    }

    @Test
    public void testSetItemCode() {
        GroceryItem groceryItem = new GroceryItem();
        groceryItem.setItemCode("APPL");
        assertEquals("APPL",groceryItem.getItemCode());
    }

    @Test
    public void testgetMeasurementUnit() {
        GroceryItem groceryItem = new GroceryItem();
        groceryItem.setMeasurementUnit("weight");
        assertEquals("weight",groceryItem.getMeasurementUnit());
    }

}
