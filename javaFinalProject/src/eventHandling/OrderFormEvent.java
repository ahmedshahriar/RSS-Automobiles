package eventHandling;

import dataBaseConnection.CarDataProvider;
import dataBaseConnection.DBConnector;
import guiPackage.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class OrderFormEvent implements ActionListener {
    private static int customerId = getPrevCustomerId();
    public static int orderId = getPrevOrerId();
    private boolean status = false,iscusDataSaved = false,blankField_order = false,isorderDataSaved=false,iscarDataSaved=false;
    private  boolean blankField_car=false,blankField_cus = false;
    private boolean dataSaved = false;
    private OrderForm orderFormRef=null;
    private ExportPdf pdf=null;
    private static OrderFormEvent orderFormEventRef=null;
    String url = "jdbc:mysql://localhost/carshowroom";
    private int n;
    @Override
    public void actionPerformed(ActionEvent e) {
        orderFormRef = OrderForm.getorderformRef();
        if (e.getSource() == orderFormRef.save) {
            n = orderFormRef.selectCarID.getSelectedIndex();
            if(checkCustomerDetails() && checkOrderDeatials() && calculateTotalPrice()>0){
               // customerId++;
                //orderId++;
                saveDataToRevenue();
                saveDataToOrder();
                saveDataToCustomer();
                updateCarDetails();
                dataSaved = true;
                orderFormRef.displayPrice.setText(Long.toString(calculateTotalPrice()));
               /* orderFormRef.selectCarID.setSelectedIndex(0);
                orderFormRef.selectCarID.remove(n);
                this.orderFormRef.txtmodel.setText("");
                this.orderFormRef.txtman.setText("");
                this.orderFormRef.txtprice.setText("");
                this.orderFormRef.txtwarranty.setText("");*/

                setAllData();
                JOptionPane.showMessageDialog(null,"Bill Saved");
            }
            else{
                System.out.println(checkCustomerDetails());
                System.out.println(checkOrderDeatials());
                System.out.println(calculateTotalPrice());
                JOptionPane.showMessageDialog(null,"Invalid name phone or main");
            }
        }
        if(e.getSource()==orderFormRef.creatbill){
            if(dataSaved){
                JOptionPane.showMessageDialog(null,"Bill Created");
                dataSaved = false;
            }
            else{
                if(checkOrderDeatials() && checkCustomerDetails() && calculateTotalPrice()>=0){
                   // customerId++;
                    //orderId++;
                    orderFormRef.displayPrice.setText(Long.toString(calculateTotalPrice()));
                    saveDataToRevenue();
                    saveDataToCustomer();
                    saveDataToOrder();
                    updateCarDetails();
                   // orderFormRef.selectCarID.setSelectedIndex(n);
                   // orderFormRef.selectCarID.remove(n);
                     new ExportPdf();

                   /* this.orderFormRef.txtmodel.setText("");
                    this.orderFormRef.txtman.setText("");
                    this.orderFormRef.txtprice.setText("");
                    this.orderFormRef.txtwarranty.setText("");*/

                    setAllData();
                    JOptionPane.showMessageDialog(null, "Data saved & Bill Created");
                    //ExportPdf.getExportPdfRef().doc.close();

                }
                else{
                    JOptionPane.showMessageDialog(null,"Invalid name phone or main");
                }
            }
        }
        if(e.getSource()==orderFormRef.reset){
            clearAll();
            orderFormRef.setCombobox();
            //orderFormRef.selectCarID.setSelectedIndex(0);
        }
        if(e.getSource()==orderFormRef.selectCarID){
            int index = orderFormRef.selectCarID.getSelectedIndex();
            if(index>=0){
                if(index==0){
                    orderFormRef.selectCarID.setSelectedIndex(0);
                }
                else if(index>0){
                    try {
                        System.out.println("index " + index);
                        index--;
                        String carModel = CarDataProvider.getCarModel(index);
                        String carPrice = CarDataProvider.getPrice(index);
                        String carManufacturer = CarDataProvider.getCarManufacturer(index);
                        String carWarranty = CarDataProvider.getWarranty(index);
                        orderFormRef.txtmodel.setText(carModel);
                        orderFormRef.txtprice.setText(carPrice);
                        orderFormRef.txtman.setText(carManufacturer);
                        orderFormRef.txtwarranty.setText(carWarranty);

                    }catch (Exception nu){
                        System.out.println(nu.getMessage());
                        //nu.printStackTrace();
                    }
                }
            }
        }
        if(e.getSource()==orderFormRef.newForm){
            if(e.getSource()==orderFormRef.newForm){
                OrderForm.getorderformRef().carIDList.clear();
                OrderForm.getorderformRef().selectCarID.removeAllItems();
                OrderForm.getorderformRef().selectCarID.addItem("Select car id..");
                OrderForm.getorderformRef().setCombobox();
                dataSaved = false;
                orderFormRef.dispose();
                orderFormRef.setVisible(true);
//                pdf.doc.open();

                setAllData();
                clearAll();
                orderId = getPrevOrerId();
                ++orderId;
                OrderForm.getorderformRef().setOrderID.setText(""+OrderFormEvent.orderId);
                // System.out.println("new form");
                orderFormRef.setDate();
            }
        }
    }
   /* public static OrderFormEvent getOrderFormEvnetRef(){
        if(orderFormEventRef==null){
            orderFormEventRef = new OrderFormEvent();
        }
        return orderFormEventRef;
    }*/
    private void setAllData() {

        Car.getCarRef().setVisible(false);
        Order.getOrderRef().setVisible(false);
        Customer.getCustomerRef().setVisible(false);
        TotalRevenue.getTotalRevenueRef().setVisible(false);

       // Car.getCarRef().tableModel.getDataVector().removeAllElements();
        Car.getCarRef().showData();

        //Order.getOrderRef().tableModel.getDataVector().removeAllElements();
        Order.getOrderRef().showData();

       // Customer.getCustomerRef().tableModel.getDataVector().removeAllElements();
        Customer.getCustomerRef().showData();

        //TotalRevenue.getTotalRevenueRef().tableModel.getDataVector().removeAllElements();
        TotalRevenue.getTotalRevenueRef().showTotalRevenue();
    }

    public void clearAll() {
        // this.orderFormRef.selectCarID.setPrototypeDisplayValue("Select Car ID");
        this.orderFormRef.setOrderID.setText("");
        this.orderFormRef.txtname.setText("");
        this.orderFormRef.txtaddress.setText("");
        this.orderFormRef.txtphone.setText("");
        this.orderFormRef.txtmail.setText("");

        this.orderFormRef.txtmodel.setText("");
        this.orderFormRef.txtman.setText("");
        this.orderFormRef.txtprice.setText("");
        this.orderFormRef.txtwarranty.setText("");
        this.orderFormRef.txtvat.setText("");
        this.orderFormRef.displayPrice.setText("");
        this.orderFormRef.txtdis.setText("");
//        this.orderFormRef.setCombobox();
    }
    public  boolean checkCustomerDetails(){
        String name = orderFormRef.txtname.getText();
        String mail = orderFormRef.txtmail.getText();
        String phone  = orderFormRef.txtphone.getText();
        String address = orderFormRef.txtaddress.getText();

        if(checkName(name) && checkMail(mail) && checkPhone(phone) && address.length()>0){
            //System.out.println("true..");
            return true;
        }
        else{
            //System.out.println("false..");
            return false;
        }
    }

    public boolean checkOrderDeatials(){
        String vat = orderFormRef.txtvat.getText();
        String dis = orderFormRef.txtdis.getText();
        if(vat.length()>0 && dis.length()>0){
            return true;
        }
        else{
            return false;
        }
    }

    public boolean checkPhone(String tphone){

        String number = tphone;
        //System.out.println("len "+number.length());
        if(number.length()==11&& number.charAt(0)=='0' && number.charAt(1)=='1'){
            return true;
        }
        return false;
    }

    public boolean checkName(String  tname){
        String name = tname;
        if(name.length()!=0 || name.matches("[0-9]+")==false){
           // System.out.println("name "+name);
            return true;
        }
        return false;
    }
    public boolean checkMail(String tmail){
        String mail = tmail;
        if(mail.length()!=0&& mail.matches("(.*)@(.*).com(.*)")==true){
            //System.out.println("main "+mail);
            return true;
        }
        return false;
    }

    private int convertVat(String v){
        String vat = v;
        int n = vat.length();
        for(int i=0;i<n;i++){
            if((vat.charAt(i)>=48 && vat.charAt(i)<=58) || vat.charAt(i)=='.'){
                continue;
            }
            else{
                return 0;
            }
        }
        if(n==0){
            return 0;
        }
        else{
            for(int i=1;i<n;i++){
                if(vat.charAt(i)=='.'){
                    return 1;
                }
            }
        }
       return 2;

    }
    private int convertDiscount(String d){
        int n = d.length();
        for(int i=0;i<n;i++){
            if((d.charAt(i)>=48 && d.charAt(i)<=58) || d.charAt(i)=='.'){
                continue;
            }
            else{
                return 0;
            }
        }
        if(n==0){
            return 0;
        }
        else{
            for(int i=1;i<n;i++){
                if(d.charAt(i)=='.'){
                    return 1;
                }
            }
        }
        return 2;
    }
    public long calculateTotalPrice(){
        String v = orderFormRef.txtvat.getText();
        String dis = orderFormRef.txtdis.getText();
        String p = orderFormRef.txtprice.getText();
        double vat=0,d=0;
        if(convertVat(v)==0){
            return -999999;
        }
        else if(convertVat(v)==1){
            vat =Double.parseDouble(v);
            vat = (long)vat;
        }
        else if(convertVat(v)==2){
            vat = Long.parseLong(v);
        }

        if(convertDiscount(dis)==0){
            return -99999;
        }
        else if(convertDiscount(dis)==1){
            d = Double.parseDouble(dis);
            d = (long)d;
        }
        else if(convertDiscount(dis)==2){
            d = Long.parseLong(dis);
        }
        long price = Long.parseLong(p);
        long totalPrice = (long) ((price+((vat*price)/100)) -((d*price)/100));

        return totalPrice;
    }
    public static int getPrevCustomerId(){
        int row=0;
        try{
            String url = "jdbc:mysql://localhost/carshowroom";
            Connection c = DBConnector.getDataBaseConnection(url);
            String query = "SELECT MAX(ID) FROM customer";
            Statement s = c.createStatement();
            ResultSet result  = s.executeQuery(query);
            if(result.next()){
                row = result.getInt(1);
            }
            else {
                row=0;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return row;
    }

    public static int getPrevOrerId(){
        int row=0;
        try{
            String url = "jdbc:mysql://localhost/carshowroom";
            Connection c = DBConnector.getDataBaseConnection(url);
            String query = "SELECT MAX(ID) FROM customer";
            Statement s = c.createStatement();
            ResultSet result  = s.executeQuery(query);
            if(result.next()){
                row = result.getInt(1);
            }
            else {
                row=0;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return row;
    }

    public long reveneue(){
        long total=0;
        boolean b = false;
        try{
            String url = "jdbc:mysql://localhost/carshowroom";
            Connection c = DBConnector.getDataBaseConnection(url);
            String query = "SELECT Amount FROM total_revenue";
            Statement s = c.createStatement();
            ResultSet result  = s.executeQuery(query);
            while (result.next()){
                b = true;
                total+=Long.parseLong(result.getString(1));
                //System.out.println(result.getString("Amount"));
            }
        }catch (Exception e){
            b = false;
           // e.printStackTrace();
        }
        if(b) {
            return total;
        }
        return -9999;
    }
    public void saveDataToRevenue(){
        String orderid = orderFormRef.setOrderID.getText();
        String date = getDate();
        if(checkOrderDeatials() && checkCustomerDetails() && calculateTotalPrice()>0){
            try {
                String price = Long.toString(calculateTotalPrice());
                Connection c = DBConnector.getDataBaseConnection(url);
                String query = "INSERT INTO total_revenue (OrderId,Amount,PurchaseDate) VALUES (?, ?, ?)";
                PreparedStatement pr = c.prepareStatement(query);

                pr.setString(1, orderid);
                pr.setString(2, price);
                pr.setString(3, date);
                pr.execute();
                pr.close();
                System.out.println("rev done");
                status = true;

            } catch (Exception e) {
                status = false;
            }
        }
        else{
            status = false;
        }

    }

    public void updateCarDetails(){
        String carid = orderFormRef.selectCarID.getSelectedItem().toString();
        if(checkCustomerDetails() && checkOrderDeatials() && calculateTotalPrice()>0){
            if(carid!=null){
                try {
                    //String id = Integer.toString(carid);
                    Connection c = DBConnector.getDataBaseConnection(url);
                    String query = "DELETE FROM cardetails WHERE CarID = ?";
                    PreparedStatement pr = c.prepareStatement(query);

                    pr.setString(1,carid);
                    pr.execute();
                    pr.close();
                    status = true;
                  //  iscarDataSaved = true;
                    System.out.println("car deleted");

                } catch (Exception e) {
                    // e.printStackTrace();
                    status = false;
                   // iscarDataSaved = false;
                }
            }
        }
        else{
            JOptionPane.showMessageDialog(null,"failed");
        }
    }
    public void saveDataToOrder(){
        if(checkOrderDeatials() && checkCustomerDetails() && calculateTotalPrice()>0){
            String carid = orderFormRef.selectCarID.getSelectedItem().toString();
            customerId = getPrevCustomerId();
            ++customerId;
            int orderid = Integer.parseInt(orderFormRef.setOrderID.getText());
            String carmodel = orderFormRef.txtmodel.getText();
            String carprice  = orderFormRef.txtprice.getText();
            String date = getDate();
            try {
                Connection c = DBConnector.getDataBaseConnection(url);
                String query = "INSERT INTO orderdetails (OrderId,CustomerID,CarID,CarModel,CarPrice,PurchaseDate) VALUES (?, ?, ?, ?,?,?)";
                PreparedStatement pr = c.prepareStatement(query);
                pr.setInt(1,  orderid);
                pr.setInt(2, customerId);
                pr.setString(3, carid);
                pr.setString(4, carmodel);
                pr.setString(5, carprice);
                pr.setString(6, date);
                pr.execute();
                pr.close();
                 System.out.println("order done");
                // JOptionPane.showMessageDialog(null,"Insert Successfully");
                status = true;
                isorderDataSaved = true;

            } catch (Exception e) {
                status = false;
                isorderDataSaved = false;
                //System.out.println("error");
                //e.printStackTrace();
                JOptionPane.showMessageDialog(null,"Something wrong!");
            }

        }
        else{
            System.out.println("failed ordertable");
        }
    }
    public void saveDataToCustomer(){
        if(checkCustomerDetails() && checkOrderDeatials() && calculateTotalPrice()>0){
            String name = orderFormRef.txtname.getText();
            String address = orderFormRef.txtaddress.getText();
            String phone =  orderFormRef.txtphone.getText();
            String mail = orderFormRef.txtmail.getText();
            String date = getDate();
            customerId = getPrevCustomerId();
            ++customerId;
            //String cusid  = Integer.toString(customerId);
            try {
                Connection c = DBConnector.getDataBaseConnection(url);
                String query = "INSERT INTO customer (ID,name,phone,mail,address,PurchaseDate) VALUES (?, ?, ?, ?, ?, ?)";
                PreparedStatement pr = c.prepareStatement(query);

                pr.setInt(1, customerId);
                pr.setString(2, name);
                pr.setString(3, phone);
                pr.setString(4, mail);
                pr.setString(5, address);
                pr.setString(6, date);
                pr.execute();
                pr.close();
                System.out.println("Customer done");
                // JOptionPane.showMessageDialog(null,"Insert Successfully");
                status = true;
                iscusDataSaved = true;

            } catch (Exception e) {
                status = false;
                iscusDataSaved = false;
            }
        }
        else{
            System.out.println("failed customer table");
        }
    }
    public  String getDate(){
        SimpleDateFormat formatdate = new SimpleDateFormat("YYYY/MM/dd");
        Date date = new Date();
        String d = formatdate.format(date);
        // System.out.println(d);
        return d;
    }

}
