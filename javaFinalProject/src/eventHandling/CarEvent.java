package eventHandling;

import com.sun.org.apache.regexp.internal.RE;
import dataBaseConnection.DBConnector;
import guiPackage.Car;
import jdk.nashorn.internal.scripts.JO;

import javax.swing.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class CarEvent implements ActionListener {
    public Car carRef;
    private static int count=0;
    private boolean isQueryExecute = false;

    public void actionPerformed(ActionEvent e) {
        carRef = Car.getCarRef();
        if (e.getSource() == carRef.save) {
            //System.out.println("save");
            saveToCardetails();
            clearAll();
            carRef.showData();
        }
        if (e.getSource() == carRef.update) {
            int row=-1,col=-1;
            String value=null;
            row = carRef.table.getSelectedRow();
            col = carRef.table.getSelectedColumn();
            if(row>=0 && col >=0){
                 value = carRef.table.getValueAt(row,col).toString();
                //System.out.println(value);
                 updateCarDetailsTable(row,col,value);
                 clearAll();
                 carRef.showData();
            }
            else{
               // System.out.println("No row or column selected");
                JOptionPane.showMessageDialog(null,"No row or column selected");
                row=col=-1;
            }
        }
        if (e.getSource() == carRef.delete) {
            int row =-1;
            row = carRef.table.getSelectedRow();
            if(row>=0) {
                Object s = carRef.table.getValueAt(row, 0);
                String carid = s.toString();
                //System.out.println(carid);
                if (row >= 0) {
                    carRef.tableModel.removeRow(row);
                    deleteDataFromCarDetails(carid);
                }
            }
            else{
                JOptionPane.showMessageDialog(null,"No Row Selected");
                row = -1;
            }
        }
        if (e.getSource() == carRef.searchById) {
            String carid = carRef.txtsearch.getText();
           // System.out.println(carid+" bal");
            searchData(carid);
            if(!isQueryExecute){
                JOptionPane.showMessageDialog(null,"Invalid car id");
                isQueryExecute =false;
            }

        }
        if(e.getSource()==carRef.reset){
            isQueryExecute = false;
            clearAll();
            carRef.showData();
        }

    }

    public void saveToCardetails() {
        String carid = carRef.txtcarid.getText();
        String carmodel = carRef.txtmodel.getText();
        String carprice = carRef.txtprice.getText();
        String manufacturer = carRef.txtmanf.getText();
        String warranty = carRef.txtwarranty.getText();
        if (carid.length() > 0 && carmodel.length() > 0 && carprice.length() > 0 && manufacturer.length() > 0 && warranty.length() > 0) {
            try {
                String url = "jdbc:mysql://localhost/carshowroom";
                Connection c = DBConnector.getDataBaseConnection(url);
                String query = "INSERT INTO cardetails(CarID,CarModel,CarPrice,Manufacturer,Warranty) VALUES (?,?,?,?,?)";
                PreparedStatement pr = c.prepareStatement(query);
                pr.setString(1, carid);
                pr.setString(2, carmodel);
                pr.setString(3, carprice);
                pr.setString(4, manufacturer);
                pr.setString(5, warranty);
                pr.execute();
                pr.close();
                JOptionPane.showMessageDialog(null, "Data Inserted..");
               // System.out.println("car table updated");
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("void");
            JOptionPane.showMessageDialog(null, "Invalid!");
        }
    }

    public void deleteDataFromCarDetails(String carid) {
        //String carid = carRef.txtcarid.getText();
        try {
            String url = "jdbc:mysql://localhost/carshowroom";
            Connection c = DBConnector.getDataBaseConnection(url);
            String query = "DELETE FROM cardetails WHERE CarID = ?";
            PreparedStatement pr = c.prepareStatement(query);
            pr.setString(1, carid);
            pr.execute();
            pr.close();
          //  System.out.println("deleted "+ carid);

        } catch (Exception e) {
           // e.printStackTrace();
            JOptionPane.showMessageDialog(null,"failed");
        }
    }

    private void clearAll() {
        carRef.tableModel.getDataVector().removeAllElements();
        carRef.txtcarid.setText("");
        carRef.txtprice.setText("");
        carRef.txtmodel.setText("");
        carRef.txtmanf.setText("");
        carRef.txtwarranty.setText("");
        carRef.txtsearch.setText("");
    }

    public void updateCarDetailsTable(int row,int col,String value){
        String carid  = carRef.table.getValueAt(row,0).toString();
       // System.out.println(carid);
        String query=null;
        if(col==1){
            query = "UPDATE cardetails SET CarModel= ? WHERE CarID = ?";
        }
        else if(col==2){
            query = "UPDATE cardetails SET CarPrice= ? WHERE CarID = ?";
        }
        else if(col==3){
            query = "UPDATE cardetails SET CarManufacturer= ? WHERE CarID = ?";
        }
        else if(col==4){
            query = "UPDATE cardetails SET Warranty= ? WHERE CarID = ?";
        }
       // System.out.println(query);
        try {
            String url = "jdbc:mysql://localhost/carshowroom";
            Connection c = DBConnector.getDataBaseConnection(url);
            //String query = "DELETE FROM cardetails WHERE CarID = ?";
            PreparedStatement pr = c.prepareStatement(query);
            pr.setString(1, value);
            pr.setString(2, carid);
            pr.execute();
            pr.close();
            System.out.println("update done");

        } catch (Exception e) {
            //e.printStackTrace();
            JOptionPane.showMessageDialog(null,"Update failed");
        }
    }

    public void searchData(String id){
        try{
            String query = "Select * from cardetails where CarID = ? ";
            String url = "jdbc:mysql://localhost/carshowroom";
            Connection c = DBConnector.getDataBaseConnection(url);
            PreparedStatement p = c.prepareStatement(query);
            p.setString(1,id);
            ResultSet r = p.executeQuery();
            if(r.next()){
                isQueryExecute = true;
                clearAll();
                Object []obj = {r.getString(1),
                                r.getString(2),
                                r.getString(3),
                                r.getString(4),
                                r.getString(5)};
                carRef.tableModel.addRow(obj);
            }
            carRef.table.setModel(carRef.tableModel);
        }catch (Exception e){
            //e.printStackTrace();
            JOptionPane.showMessageDialog(null,"Query execution failed!");
    }
    }
}

