
/**
 * Created by Behrooz on 31/10/2017.
 */

import java.util.List;

public interface IPriceCalculator {

    public String calculatePayment(List<GroceryItem> basket) ;

    public void  addItemsToBasket(String itemCode);

    public boolean isBarCodeValid(String barCodeIn);
}
