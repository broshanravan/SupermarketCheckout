/**
 * Created by Behrooz on 10/06/2018..
 */
import java.text.DecimalFormat;
import java.util.*;

public class PriceCalculator  implements IPriceCalculator{

    IInventory inventory = new Inventory();
    DecimalFormat decimalFormatter = new DecimalFormat("0.00");

    Map<String, GroceryItem> groceryItemsMap = inventory.readItemsFromFile();



    public void addItemsToBasket(String itemCode) {

        List<GroceryItem> basket = new ArrayList<GroceryItem>();
        Scanner scanner = new Scanner(System.in);
        GroceryItem groceryItem = groceryItemsMap.get(itemCode);
        if(groceryItem == null){
            System.err.println("Invalid BAR Code, Please try again");
        }else {
            if (MeasurementMethod.WEIGHT.equals(groceryItem.getMeasurementMethod())) {
                System.out.println("please enter weight; ");
                double weight = Double.parseDouble(scanner.nextLine());
                groceryItem.setWeight(weight);
            }

            while (!"end".equalsIgnoreCase(itemCode)) {
                String code = scanner.nextLine();
                if (groceryItem != null) {
                    basket.add(groceryItem);
                }
                System.out.println("Description : " + groceryItem.getItemName());
                System.out.println("Price : \u00a3" + decimalFormatter.format(groceryItem.getPricePerMeasurementUnit()));

                System.out.println("please enter next Item code; ");
                groceryItem = groceryItemsMap.get(itemCode);
                if (MeasurementMethod.WEIGHT.equals(groceryItem.getMeasurementMethod())) {
                    System.out.println("please enter weight; ");
                    double weight = Double.parseDouble(scanner.nextLine());
                    groceryItem.setWeight(weight);
                }
            }

        }
        calculatePayment(basket);
    }


    /**
     * Calculating the total price.
     * This function runs continuously until the
     * user completes the shopping
     * @param basket
     * @return finalPayment
     */

    public String calculatePayment(List<GroceryItem> basket) {
        double subTotal = 0;
        //Scanner scanner = new Scanner(System.in);

        for(GroceryItem groceryItem :basket){
            if(groceryItem != null) {
                if (groceryItem.getMeasurementMethod().equals(MeasurementMethod.WEIGHT)) {
                    System.out.println("Please Enter Weight in kg: ");
                    boolean waightIsValid = false;
                    while (!waightIsValid) {
                        try {
                            double weight = groceryItem.getWeight();
                            subTotal += groceryItem.getPricePerMeasurementUnit() * weight;
                            waightIsValid = true;
                        } catch (Exception ex) {
                            System.out.println("Please Enter Weight in kg: ");
                        }
                    }
                } else {
                    subTotal += groceryItem.getPricePerMeasurementUnit();
                }
            }
        }
        subTotal = Double.valueOf(decimalFormatter.format(subTotal));

        System.out.println("Sub-total is: \u00a3" + subTotal);
        double discount = this.getTotalDiscount(basket);
        if (discount == 0) {
            System.out.println("(No offers available)");
        } else {
            System.out.println("Sub-total is: \u00a3" + subTotal);
            System.out.println("total discount amount is: \u00a3" + decimalFormatter.format(discount));
        }
        String finalPayment = decimalFormatter.format(subTotal - discount);
        return  finalPayment;

    }


    /**
     * will validate the barcode entered by
     * the user
     * @param barCodeIn
     * @return validation result
     */
    public boolean isBarCodeValid(String barCodeIn){

        List<String> barCodeList = inventory.getAllBarCodes();
        if(barCodeList.contains(barCodeIn)) {
            return true;
        }

        return false;
    }

    /**
     * This function calculates the combined
     * discount for promoted goods
     * @param shoppingBasket
     * @return promotionalDiscount
     */
    public double getTotalDiscount(List<GroceryItem> shoppingBasket){

        double totalalDiscount = 0;

        double totalIndividualDiscount = getTotalIndividualDiscount(shoppingBasket);
        double totalCombinedDistotnt = getTotalCombinationDiscount(shoppingBasket);
        totalalDiscount = totalIndividualDiscount + totalCombinedDistotnt;
        return totalalDiscount;
    }

    /**
     * This function in only to calculate
     * the discount of the goods which are
     * only eligible for discount when purchased
     * along with
     * other pubstituted ones
     * @param shoppingBasket
     * @return
     */

    public double getTotalCombinationDiscount(List<GroceryItem> shoppingBasket){
        double discount = 0;
        double basketPromotedItemCount = 0 ;
        double basketSubstitutedIteCount = 0;
        List<PromotionalOffer> combinationOfferList = inventory.getPromotionalOffersList();
        for( PromotionalOffer promotionalOffer :combinationOfferList) {
                GroceryItem promotedItem = null;
                for (GroceryItem groceryItem : shoppingBasket) {
                    String itemCode = groceryItem.getItemCode();

                    if (itemCode.equalsIgnoreCase(promotionalOffer.getPromotedItemBarcode())) {
                        promotedItem = groceryItem;
                        basketPromotedItemCount++;
                    } else if (itemCode.equalsIgnoreCase(promotionalOffer.getSubstitutedItemBarcode())) {
                        basketSubstitutedIteCount++;
                    }

                }
                if (promotedItem != null) {
                   needs rethink int discountEligibleCount = new Double(basketPromotedItemCount * promotedItem.getPricePerMeasurementUnit()).intValue();
                    discount = discountEligibleCount * promotionalOffer.getDiscountRate() / 100;
                }
                for (GroceryItem groceryItem : shoppingBasket) {
                    String itemCode = groceryItem.getItemCode();
                    if (itemCode.equalsIgnoreCase(promotionalOffer.getPromotedItemBarcode()))
                        shoppingBasket.remove(groceryItem);
                }

        }

        return discount;
    }

    /**
     *
     * @param shoppingBasket
     * @return
     */

    public double getTotalIndividualDiscount(List<GroceryItem> shoppingBasket){
        double discount = 0;
        List<PromotionalOffer> individualOfferList = inventory.getIndividualPromotionalOffersList());
        for( PromotionalOffer promotionalOffer :individualOfferList) {
                for (GroceryItem groceryItem : shoppingBasket) {
                    String itemCode = groceryItem.getItemCode();

                    if (itemCode.equalsIgnoreCase(promotionalOffer.getPromotedItemBarcode())) {

                        double itemPrice = groceryItem.getPricePerMeasurementUnit();
                        if (groceryItem.getMeasurementMethod() == MeasurementMethod.COUNT) {
                            discount = + itemPrice * promotionalOffer.getDiscountRate() / 100;

                        } else {
                            discount = + itemPrice* groceryItem.getWeight() * promotionalOffer.getDiscountRate() / 100;

                        }
                    }
                }
        }
        return discount;
    }



    public Map<String, GroceryItem> getGroceryItemsMap() {
        return groceryItemsMap;
    }

    public void setGroceryItemsMap(Map<String, GroceryItem> groceryItemsMap) {
        this.groceryItemsMap = groceryItemsMap;
    }

    public IInventory getInventory() {
        return inventory;
    }

    public void setInventory(Inventory inventory) {
        this.inventory = inventory;
    }
}
