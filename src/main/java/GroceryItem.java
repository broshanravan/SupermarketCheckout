
/**
 * Created by Behrooz on 10/06/2018.
 */
public class GroceryItem {
    private String itemName;
    private double pricePerMeasurementUnit;
    private String itemCode;
    private MeasurementMethod measurementMethod;
    private double weight;

    public GroceryItem(String itemNameIn,
                 double pricePerMesurmentUnitIn,
                 String itemCodeIn,
                 MeasurementMethod measurementUnitIn,
                 double weightIn) {
         itemName = itemNameIn;
        pricePerMeasurementUnit = pricePerMesurmentUnitIn;
         itemCode = itemCodeIn;
        measurementMethod = measurementUnitIn;
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

    public double getPricePerMeasurementUnit() {
        return pricePerMeasurementUnit;
    }

    public void setPricePerMeasurementUnit(double pricePerMeasurementUnit) {
        this.pricePerMeasurementUnit = pricePerMeasurementUnit;
    }

    public String getItemCode() {
        return itemCode;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    public MeasurementMethod getMeasurementMethod() {
        return measurementMethod;
    }

    public void setMeasurementMethod(MeasurementMethod measurementMethod) {
        this.measurementMethod = measurementMethod;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }
}
