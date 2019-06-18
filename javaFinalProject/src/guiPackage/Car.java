package guiPackage;

import dataBaseConnection.DBConnector;
import eventHandling.CarEvent;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class Car extends JFrame implements ActionListener {
    private JLabel carid,model,price,manf,warranty,search,imgLabel;
    public JTextField txtcarid,txtmodel,txtprice,txtmanf,txtwarranty,txtsearch;
    public JButton update,save,delete,searchById,reset;
    public JTable table;
    ImageIcon imgCar;
    public DefaultTableModel tableModel;
    private static Car carRef=null;
    public boolean isCarAvailable=false;

    public  Car(){
        this.setTitle("Car Records");
        initiateCar();
        this.setLayout(null);
        this.setBounds(200,100,1000,500);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setVisible(true);
        this.tableModel.getDataVector().removeAllElements();
        this.showData();
        this.tableModel.getDataVector().removeAllElements();
        if(!isCarAvailable){
            JOptionPane.showMessageDialog(null,"No cars available!");
        }

    }

    private void initiateCar(){


        Image icon = Toolkit.getDefaultToolkit().getImage("icocar.png");
        this.setIconImage(icon);

        int labelh = 80;
        int labelw = 25;
        this.carid = new JLabel("Car Id");
        this.carid.setBounds(20+10,20,labelh,labelw);
        this.add(this.carid);

        int x = carid.getX();
        int y = carid.getY();

        this.model = new JLabel("Model");
        this.model.setBounds(x,y+40,labelh,labelw);
        this.add(this.model);

        x = model.getX();
        y = model.getY();

        this.price = new JLabel("Price");
        this.price.setBounds(x,y+40,labelh,labelw);
        this.add(this.price);

        x = price.getX();
        y = price.getY();

        this.manf = new JLabel("Manufacturer");
        this.manf.setBounds(x-20,y+40,labelh,labelw);
        this.add(this.manf);

        y = manf.getY();
        this.warranty = new JLabel("Warranty");
        this.warranty.setBounds(x-5,y+40,labelh,labelw);
        this.add(this.warranty);


        this.txtcarid = new JTextField();
        this.txtcarid.setBounds(10+80,20,120,25);
        this.add(this.txtcarid);

        x = txtcarid.getX();
        y = txtcarid.getY();

        this.txtmodel = new JTextField();
        this.txtmodel.setBounds(x,y+40,120,25);
        this.add(this.txtmodel);

        x =txtmodel.getX();
        y =txtmodel.getY();

        this.txtprice = new JTextField();
        this.txtprice.setBounds(x,y+40,120,25);
        this.add(this.txtprice);

        x =txtprice.getX();
        y =txtprice.getY();

        this.txtmanf = new JTextField();
        this.txtmanf.setBounds(x,y+40,120,25);
        this.add(this.txtmanf);

        x =txtmanf.getX();
        y =txtmanf.getY();

        this.txtwarranty = new JTextField();
        this.txtwarranty.setBounds(x,y+40,120,25);
        this.add(this.txtwarranty);

        x = warranty.getX();
        y = warranty.getY();

        this.save = new JButton("Save");
        this.save.setBounds(x+35,y+50,85,25);
        this.add(this.save);

        this.delete = new JButton("Delete");
        this.delete.setBounds(x-10,y+50+40,85,25);
        this.add(this.delete);

        x = delete.getX();
        y = delete.getY();
        this.update = new JButton("Update");
        this.update.setBounds(x+85+15,y,85,25);
        this.add(this.update);

        this.reset = new JButton("Reset");
        this.reset.setBounds(x+40,y+40,85,25);
        this.add(this.reset);

        this.table = new JTable();
        Object[] column = {"Car ID","Model","Price","Manufacturer","Warranty(Year)"};
        this.tableModel = new DefaultTableModel();
        tableModel.setColumnIdentifiers(column);
        this.table.setModel(tableModel);
        table.setBackground(Color.WHITE);
        table.setRowHeight(30);
        table.setFont(new Font("Serif", Font.PLAIN, 18));

        JScrollPane pane = new JScrollPane(table);
        pane.setBounds(250+10,0,550,300);
        this.add(pane);
        this.showData();
        this.table.setAutoCreateRowSorter(true);
        this.table.setSelectionBackground(Color.red);
        this.table.setSelectionForeground(Color.WHITE);
        this.txtsearch = new JTextField();
        this.txtsearch.setBounds(820,20,100,25);
        this.add(this.txtsearch);

        x = this.txtsearch.getX();
        y = this.txtsearch.getY();
        this.searchById = new JButton("Search");
        this.searchById.setBounds(x+10,y+35,80,25);
        this.add(this.searchById);

        this.search = new JLabel("(BY-ID)");
        this.search.setBounds(x+100+4,y,50,20);
        this.search.setForeground(Color.RED);
        this.add(this.search);

        this.imgCar=new ImageIcon("imacar.jpg");
        this.imgLabel=new JLabel(imgCar);
        this.imgLabel.setBounds(0,0,1000,500);
        this.add(this.imgLabel);


        // this.showData();
        CarEvent carEventRef = new CarEvent();

        this.update.addActionListener(carEventRef);
        this.delete.addActionListener(carEventRef);
        this.save.addActionListener(carEventRef);
        this.searchById.addActionListener(carEventRef);
        this.reset.addActionListener(carEventRef);

        SelectionPage.getSelectionPageRef().logout.addActionListener(this);
        //this.table.addMouseListener(carEventRef);
       // this.table.addKeyListener(this);
      //  System.out.println(this.tableModel.getRowCount());

    }
    public static Car getCarRef(){
        if(carRef==null){
            carRef = new Car();
        }
        carRef.tableModel.getDataVector().removeAllElements();
        return carRef;
    }
    public void showData(){
        try{
            String url = "jdbc:mysql://localhost/carshowroom";
            Connection c = DBConnector.getDataBaseConnection(url);
            String query = "Select * from cardetails";
            Statement s = c.createStatement();
            ResultSet result  = s.executeQuery(query);
            while (result.next()) {
                    isCarAvailable =true;
                   // System.out.println(result.getString(1));
                   Object[] rows = {result.getString(1),
                                    result.getString(2),
                                    result.getString(3),
                                    result.getString(4),
                                    result.getString(5)};
                    tableModel.addRow(rows);
                }
                table.setModel(tableModel);
        }catch (Exception e){
            //e.printStackTrace();
            isCarAvailable=false;
            //JOptionPane.showMessageDialog(null,"Query execution failed");
            //System.out.println("Query execution failed car");
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==SelectionPage.getSelectionPageRef().logout){
            this.dispose();
        }
    }
}
