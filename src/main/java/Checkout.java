
/**
 * Created by Behrooz on 31/10/2017.
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
        String totalPrice = priceCalculatur.calculateTotalPayment(code);

        System.out.println("totalPrice is: \u00a3"  + totalPrice);


    }
}
