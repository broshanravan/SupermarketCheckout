
/**
 * Created by Behrooz on 31/10/2017.
 */

import java.util.List;

public interface IPriceCalculator {

    public String calculateTotalPayment(String itemCode) ;


    public boolean isBarCodeValid(String barCodeIn);
}
