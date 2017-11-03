# SupermarketCheckout
The application is designed to function as a checkout point where the customer will scan or enter the barcode of the goods and the price will be shown in the screen. The good also will be added to a basket(Java list) and the price is added to the subtotal of shopping.
There are two types of cods involved in the process.
The first group are the goods that could be counted in whole number like bottle of milk, can of bins, packet of apples, and can of coke.
The second group are the goods that are being sold by weight like bananas and loos oranges. 
According to the above logic every time a good of second category is entered to the basket the system will ask the user for the weight of the good.
In order for the inventory of goods on sale to be independently manageable, the list is being maintained in a JSON file which can be maintained by the manager without the need to alter the code.
Frome time to time there are some promotional offers on the goods where the combination shopping will get the customer a discount
For example, if cans of coke are being sole as 70P each buying two can will get the customer 40P discount which brings the total cost to £100 instead of £1.40
The promotional offers are also being kept in separate JSON file o make them more maintainable independent of the code.
To make sure that the application runs correctly an all the functions are behaving expected. For each function a set of unite tests has been added to the application to cover all the different scenarios of shopping list contents and the combination of offers available
The application is also created in a way that it will create an executable JAR file which could be run from command line.
To create such file run the following command s
gradle clean
gradle   fatjar
The JAR file created should then be put in a different folder together with all the JSON files involved.
To run the application the user needs to navigate to that folder and run the command
Java -jar ‘jar_file_name’




