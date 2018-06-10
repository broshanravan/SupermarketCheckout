
/**
 * Created by Behrooz on 10/06/2018..
 */

import java.util.List;

public interface IPriceCalculator {

    public String calculatePayment(List<GroceryItem> basket) ;

    public void  addItemsToBasket(String itemCode);

    public boolean isBarCodeValid(String barCodeIn);
}
