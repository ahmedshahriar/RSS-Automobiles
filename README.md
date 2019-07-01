# RSS-Automobiles

### Short description:

An automobile showroom management system where a sales personnel of a showroom (Ex-Manager) can do basic inventory CRUD operations on automobiles (Cars) and order with some extensible features such as billing form with proper authentication.
  
## Authors
* **RAFID SHAHRIAR**   - [rafid211](https://github.com/rafid211)
* **SAYMON,AKTHER HAMID**  - [AhSaymon](https://github.com/AhSaymon)
* **SAKIB,AHMED SHAHRIAR**  - [ahmedshahriar](https://github.com/ahmedshahriar)

### Prerequisites

```
java , mysql
```

### Target Users of this application:
  - Sales personnel of an automobile showroom (ex- Manager).

  
## Features :
* **User Access** :
  * User can sign in to the system .
  * User can register for access .
  * User can sign out from the system .

* **User Control**  :
  * Check inventory.
  * Check order history.
  * Check customer history.
  * Create an order form.
  * Generate a bill (which is saved in pdf format).
  * Check revenue.
  * User can sort multiple data from data table in ascending and     descending order.
  * User can search specific data(along with the row) from the table .

## Built With

* [Java 1.8,swing,awt](https://www.java.com/en/) - Core 
* [iTextpdf](https://itextpdf.com) - Billing Form Pdf generation
* [mysql](https://rometools.github.io/rome/) - Database management

## Project Structure:
 This project is divided into three packages :
* **guiPackage**  :
  * Car 
  * Customer 
  * LoginPage 
  * Main Frame 
  * Order
  * Selectionpage
  * Order Form
  * Sign Up Page 
  * Total Revenue 
* **Event Handling**  :
  * Car Event
  * Customer Event
  * Export Pdf
  * Login  Event 
  * Order Event
  * Order Form Event
  * Selection Page Event
* **Database connection**  :
  * CarDataProvider
  * DBConnector
  
### Database connection code snippet
```java
import java.sql.Connection;
import java.sql.DriverManager;


public class DBConnector {
    private static Connection c;
    public static Connection getDataBaseConnection(String url){
        if(c==null) {
            try {
                String username = "root";
                String pass = "";
                Class.forName("com.mysql.jdbc.Driver");
                c = DriverManager.getConnection(url, username, pass);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return c;
        }
        else{
            return c;
        }
    }

}
```

### Entity relationship Diagram :
![alt text](https://github.com/ahmedshahriar/RSS-Automobiles/blob/master/Database/Entity-relationship-diagram.png "ER Diagram")

### Relational Schema :
![alt text](https://github.com/ahmedshahriar/RSS-Automobiles/blob/master/Database/Relational-Schema.jpg "Relational Schema")

### GUI :
![alt text](https://github.com/ahmedshahriar/RSS-Automobiles/blob/master/GUI%20Screenshots/login.png "Login Page")
![alt text](https://github.com/ahmedshahriar/RSS-Automobiles/blob/master/GUI%20Screenshots/register.png "Registration Page")
![alt text](https://github.com/ahmedshahriar/RSS-Automobiles/blob/master/GUI%20Screenshots/selectionMenu.png "Menu Page")
![alt text](https://github.com/ahmedshahriar/RSS-Automobiles/blob/master/GUI%20Screenshots/car.png "Car Records Page")
![alt text](https://github.com/ahmedshahriar/RSS-Automobiles/blob/master/GUI%20Screenshots/customer.png "Customer Records Page")
![alt text](https://github.com/ahmedshahriar/RSS-Automobiles/blob/master/GUI%20Screenshots/order.png "Order Records Page")
![alt text](https://github.com/ahmedshahriar/RSS-Automobiles/blob/master/GUI%20Screenshots/orderForm.png "Order Form Page")
![alt text](https://github.com/ahmedshahriar/RSS-Automobiles/blob/master/GUI%20Screenshots/Revenue.png "Revenue Page")

#### Generated Bill as pdf
[Click here to download the sample bill as pdf](https://github.com/ahmedshahriar/RSS-Automobiles/blob/master/javaFinalProject/OrderForm0.pdf "Bill")

![alt text](https://github.com/ahmedshahriar/RSS-Automobiles/blob/master/GUI%20Screenshots/pdf.png "Generated Bill as Pdf")

