/**
 * Created by Behrooz on 10/06/2018.
 */
public class PromotionalOffer {
    private String promotedItemBarcode;
    private DeductionType deductionType;
    private double discountRate;
    private String substitutedItemBarcode;
    private int substitutedItemCount;



    public PromotionalOffer (

            String promotedItemBarcodeIn,
            DeductionType deductionTypeIn,
            double discountedRateIn,
            String substitutedItemBarcodeIn,
            int substitutedItemCountIn
    ) {
        promotedItemBarcode = promotedItemBarcodeIn;
        deductionType = deductionTypeIn;
        discountRate = discountedRateIn;
        substitutedItemBarcode = substitutedItemBarcodeIn;
        substitutedItemCount =substitutedItemCountIn;

    }

    public PromotionalOffer () {

    }


    public String getPromotedItemBarcode() {
        return promotedItemBarcode;
    }

    public void setPromotedItemBarcode(String promotedItemBarcode) {
        this.promotedItemBarcode = promotedItemBarcode;
    }


    public DeductionType getDeductionType() {
        return deductionType;
    }

    public void setDeductionType(DeductionType deductionType) {
        this.deductionType = deductionType;
    }

    public double getDiscountRate() {
        return discountRate;
    }

    public void setDiscountRate(double discountRate) {
        this.discountRate = discountRate;
    }

    public String getSubstitutedItemBarcode() {
        return substitutedItemBarcode;
    }

    public void setSubstitutedItemBarcode(String substitutedItemBarcode) {
        this.substitutedItemBarcode = substitutedItemBarcode;
    }

    public int getSubstitutedItemCount() {
        return substitutedItemCount;
    }

    public void setSubstitutedItemCount(int substitutedItemCount) {
        this.substitutedItemCount = substitutedItemCount;
    }
}
