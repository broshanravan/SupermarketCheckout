import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by Behrooz on 10/06/2018..
 */
public class TestGroceryItem {

    @Test
    public void testParametrizedConstructor() {
        GroceryItem groceryItem = new GroceryItem("apple", 1, "APL",  MeasurementMethod.WEIGHT,10);

        assertEquals("apple",groceryItem.getItemName());
        assert(1 == groceryItem.getPrice());
        assertEquals("APL",groceryItem.getItemCode());
        assertEquals(MeasurementMethod.WEIGHT,groceryItem.getMeasurementMethod());
        assert(10 == groceryItem.getWeight());
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
        groceryItem.setMeasurementMethod(MeasurementMethod.WEIGHT);
        assertEquals(MeasurementMethod.WEIGHT,groceryItem.getMeasurementMethod());
    }

}
