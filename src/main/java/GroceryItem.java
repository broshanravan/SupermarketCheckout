
/**
 * Created by Behrooz on 31/10/2017.
 */
public class GroceryItem {
    private String itemName;
    private double price;
    private String itemCode;
    private MeasurementMethod measurementUnit;
    private double weight;

    public GroceryItem(String itemNameIn,
                 double priceIn,
                 String itemCodeIn,
                 MeasurementMethod measurementUnitIn,
                 double weightIn) {
         itemName = itemNameIn;
         price = priceIn;
         itemCode = itemCodeIn;
        measurementUnit = measurementUnitIn;
        weight= weightIn;

    }

    public GroceryItem() {

    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getItemCode() {
        return itemCode;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    public MeasurementMethod getMeasurementUnit() {
        return measurementUnit;
    }

    public void setMeasurementUnit(MeasurementMethod measurementUnit) {
        this.measurementUnit = measurementUnit;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }
}
