/**
 * Created by Behrooz on 31/10/2017.
 */
import java.text.DecimalFormat;
import java.util.*;

public class PriceCalculator  implements IPriceCalculator{

    Inventory inventory = new Inventory();
    DecimalFormat decimalFormatter = new DecimalFormat("0.00");

    Map<String, GroceryItem> groceryItemsMap = inventory.readItemsFromFile();


    /**
     * Calculating the total price.
     * This function runs continuously until the
     * user completes the shopping
     * @param itemCode
     * @return finalPayment
     */

    public String calculateTotalPayment(String itemCode) {
        double subTotal = 0;
        List<GroceryItem> basket = new ArrayList<GroceryItem>();
        while (!"end".equalsIgnoreCase(itemCode)) {
            GroceryItem groceryItem = groceryItemsMap.get(itemCode);
            if(groceryItem != null){
                basket.add(groceryItem);
                subTotal += groceryItem.getPrice();

                System.out.println("Decribtion : " +  groceryItem.getItemName());
                System.out.println("Price : \u00a3" +  decimalFormatter.format(groceryItem.getPrice()));
                System.out.println("Sub-total : \u00a3" + decimalFormatter.format(subTotal));

                System.out.println("please enter next Item code; " );
            }  else {
                System.out.println("Invalid barCode, Please try again");
            }
            Scanner scanner = new Scanner(System.in);
            itemCode = scanner.nextLine();
        }
        subTotal = Double.valueOf(decimalFormatter.format(subTotal));

        System.out.println("Sub-total is: \u00a3" + subTotal);
        double discount = this.getPromotionalDiscount(basket);

        if (discount == 0) {
            System.out.println("(No offers available)");
        } else {
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
     * This unction calculates the combined
     * discount for promoted goods
     * @param shoppingBasket
     * @return promotionalDiscount
     */
    public double getPromotionalDiscount(List<GroceryItem> shoppingBasket){
        double promotionalDiscount = 0;
        List<PromotionalOffer> promotionalOfferList = inventory.getPromotionalOffersList();

        for( PromotionalOffer promotionalOffer :promotionalOfferList) {
            double discountedGoodPurchasedCount = 0;
            double itemPrice =0;

            for (GroceryItem groceryItem : shoppingBasket ) {
                String itemCode = groceryItem.getItemCode();


                if (itemCode.equalsIgnoreCase(promotionalOffer.getPromotedItemBarcode())){
                    discountedGoodPurchasedCount ++;
                    itemPrice = groceryItem.getPrice();
                }
            }
            if(discountedGoodPurchasedCount !=0) {
                if (promotionalOffer.getDiscountType() == DeductionType.NUMBER) {
                    promotionalDiscount = +promotionalOffer.getPromotedItemCount() / discountedGoodPurchasedCount * itemPrice;
                } else {
                    double originalPrice = discountedGoodPurchasedCount * itemPrice;
                    double discountedPrice = +(discountedGoodPurchasedCount / promotionalOffer.getPromotedItemCount()) * (promotionalOffer.getDiscountedPrice()) ;
                    promotionalDiscount = originalPrice - discountedPrice;
                }
            }

        }


        return promotionalDiscount;
    }


    public Map<String, GroceryItem> getGroceryItemsMap() {
        return groceryItemsMap;
    }

    public void setGroceryItemsMap(Map<String, GroceryItem> groceryItemsMap) {
        this.groceryItemsMap = groceryItemsMap;
    }

    public Inventory getInventory() {
        return inventory;
    }

    public void setInventory(Inventory inventory) {
        this.inventory = inventory;
    }
}
