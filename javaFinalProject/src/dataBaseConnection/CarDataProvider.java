package dataBaseConnection;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class CarDataProvider {
    public static ArrayList<String> getCaridList(){
        ArrayList <String> carIDList = new ArrayList<String>();
        try{
            String url = "jdbc:mysql://localhost/carshowroom";
            Connection c = DBConnector.getDataBaseConnection(url);
            Statement stmt = c.createStatement();
            String query = "select CarID from cardetails";
            ResultSet result = stmt.executeQuery(query);
            while(result.next()){
                //System.out.println(result.getString("CarID"));
                carIDList.add(result.getString("CarID"));
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        if(carIDList.size()>=0){
            return carIDList;
        }
        return null;
    }

    public static String getPrice(int index){
        String price=null;
        ArrayList <String> PriceList = new ArrayList<String>();
        try{
            String url = "jdbc:mysql://localhost/carshowroom";
            Connection c = DBConnector.getDataBaseConnection(url);
            Statement stmt = c.createStatement();
            String query = "select CarPrice from cardetails";
            ResultSet result = stmt.executeQuery(query);
            while(result.next()){
               // System.out.println(result.getString("CarPrice"));
                PriceList.add(result.getString("CarPrice"));
            }
            price = PriceList.get(index);
          //  System.out.println("data "+ price);
        }catch (Exception e){
            e.printStackTrace();
        }
        return price;
    }

    public static String getCarModel(int index){
        String carmodel=null;
        ArrayList <String> CarModelList = new ArrayList<String>();
        try{
            String url = "jdbc:mysql://localhost/carshowroom";
            Connection c = DBConnector.getDataBaseConnection(url);
            Statement stmt = c.createStatement();
            String query = "select CarModel from cardetails";
            ResultSet result = stmt.executeQuery(query);
            while(result.next()){
                //System.out.println(result.getString("CarModel"));
                CarModelList.add(result.getString("CarModel"));
            }
            carmodel = CarModelList.get(index);
        }catch (Exception e){
            e.printStackTrace();
        }
        return carmodel;
    }

    public static String getCarManufacturer(int index){
        String carManufacturer=null;
        ArrayList <String> ManufacturerList = new ArrayList<String>();
        try{
            String url = "jdbc:mysql://localhost/carshowroom";
            Connection c = DBConnector.getDataBaseConnection(url);
            Statement stmt = c.createStatement();
            String query = "select Manufacturer from cardetails";
            ResultSet result = stmt.executeQuery(query);
            while(result.next()){
               // System.out.println(result.getString("Manufacturer"));
                ManufacturerList.add(result.getString("Manufacturer"));
            }
            carManufacturer = ManufacturerList.get(index);
        }catch (Exception e){
            e.printStackTrace();
        }
        return carManufacturer;
    }

    public static String getWarranty(int index){
        String warranty=null;
        ArrayList <String> WarrantyList = new ArrayList<String>();
        try{
            String url = "jdbc:mysql://localhost/carshowroom";
            Connection c = DBConnector.getDataBaseConnection(url);
            Statement stmt = c.createStatement();
            String query = "select Warranty from cardetails";
            ResultSet result = stmt.executeQuery(query);
            while(result.next()){
                //System.out.println(result.getString("Warranty"));
                WarrantyList.add(result.getString("Warranty"));
            }
            warranty = WarrantyList.get(index);
        }catch (Exception e){
            e.printStackTrace();
        }
        return warranty;
    }

}
