/**
 * Created by Behrooz on 19/09/2017.
 */
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by Behrooz on 10/06/2018..
 */
public class TestPromotionalOffer {

    @Test
    public void testParametrazedConstructor() {
        PromotionalOffer promotionalOffer = new PromotionalOffer("COK", 2, DeductionType.PRICE, 0,1, null,0);

        assert(2 == promotionalOffer.getPromotedItemCount());
        assertEquals("COK",promotionalOffer.getPromotedItemBarcode());
        assert(2 == promotionalOffer.getPromotedItemCount());
        assert(1 == promotionalOffer.getDiscountRate());
    }

    @Test
    public void testSetPromotedItemBarcode(){
        PromotionalOffer promotionalOffer = new PromotionalOffer();
        promotionalOffer.setPromotedItemBarcode("SUP");
        assertEquals("SUP", promotionalOffer.getPromotedItemBarcode());
    }

    @Test
    public void testSetDiscountedItemBarcode() {
        PromotionalOffer promotionalOffer = new PromotionalOffer();
        promotionalOffer.setPromotedItemBarcode("BRD");
        assertEquals("BRD",promotionalOffer.getPromotedItemBarcode());
    }


    @Test
    public void testSetDiscountPercentRate(){
        PromotionalOffer promotionalOffer = new PromotionalOffer();
        promotionalOffer.setPromotedItemCount(5);
        assert(5 == promotionalOffer.getPromotedItemCount());
    }

    @Test
    public void testSetPromotedItemCount() {
        PromotionalOffer promotionalOffer = new PromotionalOffer();
        promotionalOffer.setPromotedItemCount(2);
        assert(2 == promotionalOffer.getPromotedItemCount());
    }

}
