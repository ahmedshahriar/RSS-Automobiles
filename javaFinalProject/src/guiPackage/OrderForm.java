package guiPackage;

import dataBaseConnection.CarDataProvider;
import eventHandling.ExportPdf;
import eventHandling.OrderFormEvent;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Date;


public class OrderForm extends JFrame implements ActionListener{
    private JLabel orderno,date,customer,name,address,phone,mail,setdate;
    private JLabel product,carid,model,manf,price,warranty;
    public JLabel vat,dis,totalprice,s1,s2,s3,displayPrice,setOrderID;
    public JButton creatbill,save,newForm,reset;
    public JTextField txtorder,txtdate,txtvat,txtdis;
    public JTextField txtname,txtaddress,txtphone,txtmail;
    public JTextField txtcarid,txtmodel,txtprice,txtman,txttotalprice,txtwarranty;
    public  JComboBox <String> selectCarID ;
    private static OrderForm orderformRef=null;
    public ArrayList <String> carIDList;

    public OrderForm(){
        initiateOrderform();
        this.setLayout(null);
        //this.setCombobox();
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setBounds(300,20,800,700);
        this.setForeground(Color.WHITE);
        this.setVisible(true);
    }
    public void initiateOrderform(){
        this.setTitle("Order Form");
        this.orderno = new JLabel("Order No");
        this.orderno.setBounds(30,10,80,25);
        this.add(this.orderno);
        int x = this.orderno.getX();
        int y = this.orderno.getY();



        Image icon = Toolkit.getDefaultToolkit().getImage("icoorderform.png");
        this.setIconImage(icon);


        this.setOrderID = new JLabel(""+OrderFormEvent.orderId);
        this.setOrderID.setBounds(x+60,y,100,25);
        this.setOrderID.setFont(new Font("tahoma", Font.BOLD, 15));
        this.add(this.setOrderID);

        this.date = new JLabel();
        this.date.setBounds(550,10,80,25);
        this.add(this.date);

        x = this.orderno.getX();
        y = this.orderno.getY();
        this.customer = new JLabel("Customer");
        this.customer.setBounds(20+30+20,y+50,80,25);
        this.customer.setForeground(Color.BLUE);
        this.add(this.customer);

        this.name = new JLabel("Name");
        this.name.setBounds(20,y+90,80,25);
        this.add(this.name);

        x = this.name.getX();
        y = this.name.getY();
        this.address = new JLabel("Address");
        this.address.setBounds(x-10,y+40,80,25);
        this.add(this.address);

        x = this.address.getX();
        y = this.address.getY();
        this.phone = new JLabel("Phone");
        this.phone.setBounds(x,y+40,80,25);
        this.add(this.phone);

        x = this.phone.getX();
        y = this.phone.getY();
        this.mail = new JLabel("Mail");
        this.mail.setBounds(x,y+40,80,25);
        this.add(this.mail);

        x = this.name.getX();
        y = this.name.getY();

        this.txtname = new JTextField();
        this.txtname.setBounds(x+50,y,120,25);
        this.add(this.txtname);

        x = this.address.getX();
        y = this.address.getY();

        this.txtaddress = new JTextField();
        this.txtaddress.setBounds(x+60,y,120,25);
        this.add(this.txtaddress);

        x = this.phone.getX();
        y = this.phone.getY();

        this.txtphone = new JTextField();
        this.txtphone.setBounds(x+60,y,120,25);
        this.add(this.txtphone);

        x = this.mail.getX();
        y = this.mail.getY();

        this.txtmail = new JTextField();
        this.txtmail.setBounds(x+60,y,120,25);
        this.add(this.txtmail);

        x = this.date.getX()-50;
        y = this.date.getY();

        this.setdate = new JLabel();
        this.setdate.setBounds(590,10,200,25);
        this.add(this.setdate);

        this.product = new JLabel("Product Description");
        this.product.setBounds(x+20,y+50,130,25);
        this.product.setForeground(Color.BLUE);
        this.add(this.product);

        x = product.getX();
        y = product.getY();

        this.carid = new JLabel("Select Car");
        this.carid.setBounds(x-5,y+40,80,25);
        this.add(this.carid);

        x = carid.getX();
        y = carid.getY();

        this.model = new JLabel("Model");
        this.model.setBounds(x,y+40,80,25);
        this.add(this.model);

        x = model.getX();
        y = model.getY();

        this.manf = new JLabel("Manufacturer");
        this.manf.setBounds(x-20,y+40,100,25);
        this.add(this.manf);

        x = manf.getX();
        y = manf.getY();

        this.price = new JLabel("Price");
        this.price.setBounds(x+10,y+40,80,25);
        this.add(this.price);

        x = price.getX();
        y = price.getY();

        this.warranty= new JLabel("Warranty");
        this.warranty.setBounds(x,y+40,80,25);
        this.add(this.warranty);

        x = this.carid.getX();
        y = this.carid.getY();

        this.selectCarID = new JComboBox<String>();
        this.selectCarID.setBounds(x+60,y,125,25);
        this.selectCarID.setBackground(Color.white);
        selectCarID.addItem("Select car id..");
        this.setCombobox();
       /* this.carIDList = CarDataProvider.getCaridList();

        for(String s: carIDList){
            selectCarID.addItem(s);
           // System.out.println(s);
        }
        this.add(this.selectCarID);*/

        x = this.model.getX();
        y = this.model.getY();

        this.txtmodel = new JTextField();
        this.txtmodel.setBounds(x+60,y,120,25);
        this.add(this.txtmodel);

        x = this.manf.getX();
        y = this.manf.getY();

        this.txtman = new JTextField();
        this.txtman.setBounds(x+60+20,y,120,25);
        this.add(this.txtman);

        x = this.price.getX();
        y = this.price.getY();

        this.txtprice = new JTextField();
        this.txtprice.setBounds(x+60+10,y,120,25);
        this.add(this.txtprice);

        x = this.warranty.getX();
        y = this.warranty.getY();

        this.txtwarranty = new JTextField();
        this.txtwarranty.setBounds(x+60+10,y,120,25);
        this.add(this.txtwarranty);

        this.vat = new JLabel("Vat");
        this.vat.setBounds(290,360,80,25);
        this.add(this.vat);

        x = this.vat.getX();
        y = this.vat.getY();

        this.txtvat = new JTextField();
        this.txtvat.setBounds(x+63,y,120,25);
        this.add(this.txtvat);

        this.dis = new JLabel("Discount");
        this.dis.setBounds(x,y+40,100,25);
        this.add(this.dis);

        x = dis.getX();
        y = dis.getY();

        this.totalprice = new JLabel("Total Price");
        this.totalprice.setBounds(x,y+40,100,25);
        this.add(this.totalprice);

        x = this.dis.getX();
        y = this.dis.getY();

        this.txtdis = new JTextField();
        this.txtdis.setBounds(x+63,y,120,25);
        this.add(this.txtdis);

        this.displayPrice = new JLabel();
        this.displayPrice.setBounds(x+63,y+40,120,25);
        this.displayPrice.setFont(new Font("Tahoma",Font.BOLD,15));
        this.add(this.displayPrice);
        this.displayPrice.setForeground(Color.BLUE);

        x = this.displayPrice.getX();
        y = this.displayPrice.getY();

        this.save = new JButton("Save");
        this.save.setBounds(x,y+50,120,25);
        this.add(this.save);

        x = this.vat.getX();
        y = this.vat.getY();
        this.s1 = new JLabel("(%)");
        this.s1.setBounds(x+80+120-5,y,20,20);
        this.add(this.s1);

        x = this.dis.getX();
        y = this.dis.getY();

        this.s2 = new JLabel("(%)");
        this.s2.setBounds(x+80+115,y,20,20);
        this.add(this.s2);

        x = this.totalprice.getX();
        y = this.totalprice.getY();

        this.s3 = new JLabel("BDT");
        this.s3.setBounds(x+80+120,y,25,20);
        this.add(this.s3);

        x = this.save.getX();
        y = this.save.getY();

        this.creatbill = new JButton("Create Bill");
        this.creatbill.setBounds(x+140,y,120,25);
        this.add(this.creatbill);
        setDate();

        this.newForm = new JButton("New");
        this.newForm.setBounds(x-115,y,100,25);
        this.add(this.newForm);

        x = this.save.getX();
        y = this.save.getY();

        this.reset = new JButton("Reset");
        this.reset.setBounds(x+9,y+50,100,25);
        this.add(this.reset);

        OrderFormEvent form = new OrderFormEvent();

        this.save.addActionListener(form);
        this.creatbill.addActionListener(form);
        this.selectCarID.addActionListener(form);
        this.newForm.addActionListener(form);
        this.reset.addActionListener(form);
        this.creatbill.addActionListener(ExportPdf.getExportPdfRef());
        SelectionPage.getSelectionPageRef().logout.addActionListener(this);
    }
    public static OrderForm getorderformRef(){
        if(orderformRef==null){
            orderformRef = new OrderForm();
        }
        return orderformRef;
    }
    public void setDate(){
        String date = ""+new Date();
        this.setdate.setText(date);
    }
    public void setCombobox(){
        /*int i = this.selectCarID.getSelectedIndex();
        if(i!=0){
            this.selectCarID.setSelectedIndex(0);
//            this.selectCarID.remove(i);
        }*/
        this.selectCarID.setSelectedIndex(0);
        this.carIDList = CarDataProvider.getCaridList();
        if(carIDList!=null){
           // selectCarID.addItem("Select car id..");
            for(String s: carIDList){
                selectCarID.addItem(s);
                // System.out.println(s);
            }
        }
        this.add(this.selectCarID);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==SelectionPage.getSelectionPageRef().logout){
            this.dispose();
        }
    }
}
