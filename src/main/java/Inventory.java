
/**
 * Created by Behrooz on 31/10/2017.
 */
import java.io.*;
import java.util.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import org.apache.log4j.Logger;
import com.google.gson.reflect.TypeToken;

public class Inventory implements IInventory{


    final static Logger logger = Logger.getLogger(Inventory.class);

    /**
     * reading  a list of grocery items from a flatfile
     * @return groceryItemsMap
     */

    List<String> barcodeList = null;
    public  Map<String,GroceryItem> readItemsFromFile() {
        Map<String,GroceryItem> groceryItemsMap = new HashMap<String,GroceryItem>();
        File jsonFile = new File("groceryItem.json");
        try {
            BufferedReader bufferReader = new BufferedReader(new FileReader(jsonFile));
            Gson gson = new Gson();

            List<GroceryItem> groceryItemList = gson.fromJson(bufferReader,new TypeToken<List<GroceryItem>>(){}.getType());
            System.out.println("The length of Items list Is: " + groceryItemList.size());
             barcodeList = new ArrayList<String>();

            for (GroceryItem  groceryItem : groceryItemList) {
                groceryItemsMap.put(groceryItem.getItemCode(),groceryItem);
                barcodeList.add(groceryItem.getItemCode());
                System.out.println(groceryItem.getItemCode());
            }


        } catch(FileNotFoundException nfe) {
            logger.error("The Inventory file is missing");

        } catch (IOException ioe){
            logger.error("The Inventory file is corrupted or not present");
        }
        return groceryItemsMap;
    }

    /**
     * Reading the collection of all available Bar codes
     * from inventory file
     * @return barcodeList
     */
    public List getAllBarCodes(){
        if(barcodeList ==null) {
            readItemsFromFile();
        }
        return barcodeList;
    }

    /**
     * Reading a collection of all Promotional offers
     * from the relevant file
     * @return promotionalOffersList
     */

    public List<PromotionalOffer> getPromotionalOffersList(){

        List<PromotionalOffer> promotionalOfferList = new ArrayList<PromotionalOffer>();

        //String promotionFileName = getFileName("promotionalOffer.json");

        File file = new File("promotionalOffer.json");
        try {
            BufferedReader bufferReader = new BufferedReader(new FileReader(file));
            Gson gson = new Gson();
             promotionalOfferList = gson.fromJson(bufferReader,new TypeToken<List<PromotionalOffer>>(){}.getType());

             System.out.println(promotionalOfferList);

        } catch(FileNotFoundException nfe) {
            logger.error("error, promotional offers file does not exist");

        } catch (IOException ioe){
            logger.error("error while reading the promotional offers from the file");
        }
        return promotionalOfferList;

    }

    /**
     * retvieving the individual item
     * price
     * @param itemBarCode
     * @return fileName
     */
    public double getItemPrice(String itemBarCode) {
        double itemPrice = 0 ;
        Map<String,GroceryItem> itemsMap = readItemsFromFile();
        GroceryItem groceryItem = itemsMap.get(itemBarCode.trim());


        itemPrice = groceryItem.getPrice();
        return itemPrice;
    }

    public String getFileName(String fileKey) {
        Properties properties = new Properties();
        String fileName = "";
        try {
            File file = new File("shopping.properties");
            FileInputStream fileInput = new FileInputStream(file);

            properties.load(fileInput);
            fileName =properties.getProperty(fileKey);
            fileInput.close();

        } catch (FileNotFoundException e) {
            logger.error("error, the file shopping.properties does not exist");
        } catch (IOException e) {
            logger.error("error,  reading the file shopping.properties");
        }
        return fileName;
    }


}
