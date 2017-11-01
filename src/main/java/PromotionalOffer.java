/**
 * Created by Behrooz on 31/10/2017.
 */
public class PromotionalOffer {
    private String promotedItemBarcode;
    private int promotedItemCount;
    private DeductionType discountType;
    private int numbersCharged;
    private double discountedPrice;



    public PromotionalOffer (

            String promotedItemBarcodeIn,
            int promotedItemCountIn,
            DeductionType discountTypeIn,
            int numbersChargedIn,
            double discountedPriceIn
    ) {
        promotedItemBarcode = promotedItemBarcodeIn;
        promotedItemCount = promotedItemCountIn;
        discountType = discountTypeIn;
        numbersCharged = numbersChargedIn;
        discountedPrice = discountedPriceIn;

    }

    public PromotionalOffer () {

    }






    public String getPromotedItemBarcode() {
        return promotedItemBarcode;
    }

    public void setPromotedItemBarcode(String promotedItemBarcode) {
        this.promotedItemBarcode = promotedItemBarcode;
    }

    public int getPromotedItemCount() {
        return promotedItemCount;
    }

    public void setPromotedItemCount(int promotedItemCount) {
        this.promotedItemCount = promotedItemCount;
    }

    public DeductionType getDiscountType() {
        return discountType;
    }

    public void setDiscountType(DeductionType discountType) {
        this.discountType = discountType;
    }

    public int getNumbersCharged() {
        return numbersCharged;
    }

    public void setNumbersCharged(int numbersCharged) {
        this.numbersCharged = numbersCharged;
    }

    public double getDiscountedPrice() {
        return discountedPrice;
    }

    public void setDiscountedPrice(double discountedPrice) {
        this.discountedPrice = discountedPrice;
    }
}
