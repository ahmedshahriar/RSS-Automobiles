package eventHandling;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import dataBaseConnection.DBConnector;
import guiPackage.Customer;

import javax.swing.*;

public class CustomerEvent implements ActionListener {
    private Customer customerRef=null;
    private boolean isQueryExecute = false;
    @Override
    public void actionPerformed(ActionEvent e) {
        customerRef = Customer.getCustomerRef();
        if(e.getSource()== customerRef.searchButton){
            String cid = customerRef.csearch.getText();
            int id = Integer.parseInt(cid);
            searchData(id);
            //System.out.println(oid);
            if(!isQueryExecute){
                JOptionPane.showMessageDialog(null,"Invalid car id");
                isQueryExecute = false;
            }
        }
        if(e.getSource()==customerRef.reset){
            customerRef.csearch.setText("");
            isQueryExecute = false;
            clearAll();
            customerRef.showData();
        }

    }
    private void clearAll() {
        customerRef.tableModel.getDataVector().removeAllElements();
    }
    private void searchData(int id){
        try{
            String query = "Select * from customer where ID = ? ";
            String url = "jdbc:mysql://localhost/carshowroom";
            Connection c = DBConnector.getDataBaseConnection(url);
            PreparedStatement p = c.prepareStatement(query);
            p.setInt(1,id);
            ResultSet r = p.executeQuery();
            if(r.next()){
                isQueryExecute = true;
                clearAll();
                //System.out.println("query done");
               Object []obj = {r.getInt(1),
                        r.getString(2),
                        r.getString(3),
                        r.getString(4),
                        r.getString(5),
                        r.getString(6)};
                customerRef.tableModel.addRow(obj);
            }
            customerRef.table.setModel(customerRef.tableModel);
        }catch (Exception e){
            // e.printStackTrace();
            JOptionPane.showMessageDialog(null,"Query execution failed!");
        }
    }
}
