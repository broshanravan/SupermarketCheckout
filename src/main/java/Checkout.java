
/**
 * Created by Behrooz on 10/06/2018..
 */
import java.text.DecimalFormat;
import java.util.Scanner;

public class Checkout {

    public static void main(String [] args) {
        PriceCalculator priceCalculatur = new PriceCalculator();
        System.out.println("Start Shopping");
        System.out.println("Please enter the barcode");
        System.out.println("Enter 'end' to complete the shopping");

        Scanner scanner = new Scanner(System.in);
        String code = scanner.nextLine();
        priceCalculatur.addItemsToBasket(code);

        //System.out.println("totalPrice is: \u00a3"  + totalPrice);

    }
}
