package eventHandling;

import dataBaseConnection.DBConnector;
import guiPackage.Order;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class OrderEvent implements ActionListener {
    private boolean isQueryExecute = false;
    private Order orderRef = null;
    @Override
    public void actionPerformed(ActionEvent e) {
        orderRef = Order.getOrderRef();
        if(e.getSource()== orderRef.searchButton){
            String oid = orderRef.osearch.getText();
            searchData(oid);
            //System.out.println(oid);
            if(!isQueryExecute){
                JOptionPane.showMessageDialog(null,"Invalid car id");
                isQueryExecute = false;
            }
        }
        if(e.getSource()==orderRef.resetButton){
            orderRef.osearch.setText("");
            isQueryExecute = false;
            clearAll();
            orderRef.showData();
        }
    }

    private void clearAll() {
        orderRef.tableModel.getDataVector().removeAllElements();
    }

    private void searchData(String id){
        try{
            String query = "Select * from orderdetails where OrderID = ? ";
            String url = "jdbc:mysql://localhost/carshowroom";
            Connection c = DBConnector.getDataBaseConnection(url);
            PreparedStatement p = c.prepareStatement(query);
            p.setString(1,id);
            ResultSet r = p.executeQuery();
            if(r.next()){
                isQueryExecute = true;
                clearAll();
                System.out.println("query done");
                Object []obj = {r.getString(1),
                                r.getString(2),
                                r.getString(3),
                                r.getString(4),
                                r.getString(5),
                                r.getString(6)};
                orderRef.tableModel.addRow(obj);
            }
            orderRef.table.setModel(orderRef.tableModel);
        }catch (Exception e){
           // e.printStackTrace();
            JOptionPane.showMessageDialog(null,"Query execution failed!");
        }
    }
}
