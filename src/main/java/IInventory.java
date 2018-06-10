
/**
 * Created by Behrooz on 10/06/2018..
 */
import java.util.*;

public interface IInventory {
    public Map<String,GroceryItem> readItemsFromFile();

    public List getAllBarCodes();

    public List<PromotionalOffer> getPromotionalOffersList();

    public List<PromotionalOffer> getIndividualPromotionalOffersList();

    public List<PromotionalOffer> getCombinedPromotionalOffersList();
}
