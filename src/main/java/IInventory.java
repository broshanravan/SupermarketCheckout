
/**
 * Created by Behrooz on 31/10/2017.
 */
import java.util.*;

public interface IInventory {
    public Map<String,GroceryItem> readItemsFromFile();

    public List getAllBarCodes();

    public List<PromotionalOffer> getPromotionalOffersList();
}
