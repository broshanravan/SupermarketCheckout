/**
 * Created by Behrooz on 10/06/2018..
 */
import java.text.DecimalFormat;
import java.util.*;

public class PriceCalculator  implements IPriceCalculator{

    IInventory inventory = new Inventory();
    DecimalFormat decimalFormatter = new DecimalFormat("0.00");

    Map<String, GroceryItem> groceryItemsMap = inventory.readItemsFromFile();



    public void addItemsToBasket() {

        List<GroceryItem> basket = new ArrayList<GroceryItem>();
        Scanner scanner = new Scanner(System.in);


            String itemCode = scanner.nextLine();
            double subTotal = 0;
            while (!"end".equalsIgnoreCase(itemCode)) {

                GroceryItem groceryItem = groceryItemsMap.get(itemCode);
                 groceryItem = groceryItemsMap.get(itemCode);
                if (groceryItem != null) {
                    if (MeasurementMethod.WEIGHT.equals(groceryItem.getMeasurementMethod())) {
                        System.out.println("please enter weight; ");
                        boolean validwaight = false;
                        while (!validwaight){
                            try {
                                double weight = Double.parseDouble(scanner.nextLine());
                                groceryItem.setWeight(weight);
                                validwaight = true;
                            } catch (java.lang.NumberFormatException e) {
                                System.err.println("Wrong Value for Weight, Please try again");
                            }
                        }
                    }

                    basket.add(groceryItem);

                    System.out.println("Description : " + groceryItem.getItemName());
                    System.out.println("Price : \u00a3" + decimalFormatter.format(groceryItem.getPricePerMeasurementUnit()));
                    if( groceryItem != null && getItemlDiscount(groceryItem) != 0){
                        System.out.println("Discount for this item is: " + getItemlDiscount(groceryItem));
                    }

                    double price = groceryItem.getPricePerMeasurementUnit();
                    double discount = getItemlDiscount(groceryItem);
                    double priceAfterDiscount = price - discount;
                    if( groceryItem != null && getItemlDiscount(groceryItem) != 0){
                        System.out.println("Price after discount: : \u00a3" + decimalFormatter.format(priceAfterDiscount));
                    }

                    subTotal = subTotal + priceAfterDiscount;


                    System.out.println("Please enter next Item code; ");
                    itemCode = scanner.nextLine();
                } else {
                    System.err.println("Invalid BAR Code, Please try again");
                    itemCode = scanner.nextLine();
                }

            }

        //}
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
                     subTotal += groceryItem.getPricePerMeasurementUnit() * groceryItem.getWeight();
                } else {
                    subTotal += groceryItem.getPricePerMeasurementUnit();
                }
            }
        }
        subTotal = Double.valueOf(decimalFormatter.format(subTotal));

        double discount = this.getTotalDiscount(basket);
        System.out.println("Total  price is: \u00a3" + decimalFormatter.format(subTotal));
        if (discount == 0) {
           ;
            System.out.println("(No offers available)");
        } else {
            System.out.println("total discount amount is: \u00a3" + decimalFormatter.format(discount));
        }

        String finalPayment = decimalFormatter.format(subTotal - discount);
        System.out.println("Payable :" + finalPayment);
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

        double totalIndividualDiscount = Double.parseDouble(getTotalIndividualDiscount(shoppingBasket));
        double totalCombinedDiscount = Double.parseDouble(getTotalCombinationDiscount(shoppingBasket));
        totalalDiscount = totalIndividualDiscount + totalCombinedDiscount;
        String discountStr =new DecimalFormat("##.##").format(totalalDiscount);
        totalalDiscount = Double.parseDouble(discountStr);
        return totalalDiscount;
    }

    /**
     * This function in only to calculate
     * the discount of the goods which are
     * only eligible for discount when purchased
     * along with
     * other pubstituted ones
     * @param shoppingBasket
     * @return discount
     */

    public String getTotalCombinationDiscount(List<GroceryItem> shoppingBasket){
        double discount = 0;
        double basketPromotedItemCount = 0 ;
        double basketSubstitutedIteCount = 0;
        List<PromotionalOffer> combinationOfferList = inventory.getCombinedPromotionalOffersList();
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
                if (promotedItem != null && basketSubstitutedIteCount !=0) {
                   double promotedItemsPrice = promotedItem.getPricePerMeasurementUnit();
                   int numberOfDiscountableItems = new Double(basketSubstitutedIteCount/promotionalOffer.getSubstitutedItemCount()).intValue();
                    discount = promotedItemsPrice* numberOfDiscountableItems * promotionalOffer.getDiscountRate() / 100;

                }


        }

        return decimalFormatter.format(discount);
    }

    /**
     * CAlculates total discount for the
     * Shopping bag
     * @param shoppingBasket
     * @return discount
     */

    public String getTotalIndividualDiscount(List<GroceryItem> shoppingBasket){
        double discount = 0;
        List<PromotionalOffer> individualOfferList = inventory.getIndividualPromotionalOffersList();
        for( PromotionalOffer promotionalOffer :individualOfferList) {
                for (GroceryItem groceryItem : shoppingBasket) {
                    String itemCode = groceryItem.getItemCode();

                    if (itemCode.equalsIgnoreCase(promotionalOffer.getPromotedItemBarcode())) {

                        double itemPrice = groceryItem.getPricePerMeasurementUnit();
                        if (groceryItem.getMeasurementMethod() == MeasurementMethod.COUNT) {
                            discount = discount + itemPrice * promotionalOffer.getDiscountRate() / 100;

                        } else {
                            discount = discount + itemPrice* groceryItem.getWeight() * promotionalOffer.getDiscountRate() / 100;

                        }
                    }
                }
        }
        return decimalFormatter.format(discount);
    }
    public int getNumberOfIndividualDiscountAfterApplyingCombinedDiscount(List<GroceryItem> shoppingBasket,String ProcessingItemCode){
       int numberToBeDiscountedIndividualy = 0;
        List<PromotionalOffer> combinationOfferList = inventory.getCombinedPromotionalOffersList();
        int numberAlreadDoubleDiscounted = 0;
        int total = 0 ;
        for( PromotionalOffer promotionalOffer :combinationOfferList) {
            for (GroceryItem groceryItem : shoppingBasket) {
                total ++;
                String itemCode = groceryItem.getItemCode();
                if (ProcessingItemCode.equalsIgnoreCase(promotionalOffer.getPromotedItemBarcode())) {
                    numberAlreadDoubleDiscounted++;
                }


            }

        }

        numberToBeDiscountedIndividualy = total - numberAlreadDoubleDiscounted;
        return numberToBeDiscountedIndividualy;

    }

    /**
     * calculates discount for an indivitual
     * Item for sole purpose of display
     * @param groceryItem
     * @return discount
     */

    public double getItemlDiscount(GroceryItem groceryItem){
        double discount = 0;
        List<PromotionalOffer> individualOfferList = inventory.getIndividualPromotionalOffersList();
        for( PromotionalOffer promotionalOffer :individualOfferList) {

            String itemCode = groceryItem.getItemCode();

            if (itemCode.equalsIgnoreCase(promotionalOffer.getPromotedItemBarcode())) {

                double itemPrice = groceryItem.getPricePerMeasurementUnit();
                if (groceryItem.getMeasurementMethod() == MeasurementMethod.COUNT) {
                    discount = itemPrice * promotionalOffer.getDiscountRate() / 100;

                } else {
                    discount = discount + itemPrice * groceryItem.getWeight() * promotionalOffer.getDiscountRate() / 100;
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
