package guiPackage;

import dataBaseConnection.DBConnector;
import eventHandling.OrderEvent;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class Order extends JFrame implements ActionListener {
    public JButton searchButton,resetButton;
    public JTextField osearch;
    private JLabel searchOrder,i,imgLabel;
    public ImageIcon imgOrder;
    public JTable table;
    private static Order orderRef=null;
    public DefaultTableModel tableModel;
    private boolean isDataAvailable = false;

    public Order(){
        super("Order Records");
        //orderRef = this;
        initiateOrder();
        this.setLayout(null);
        this.setBounds(100,100,1000,500);
        this.setVisible(true);
        this.tableModel.getDataVector().removeAllElements();
        this.showData();
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        if(!isDataAvailable){
            JOptionPane.showMessageDialog(null,"No Records Available");
        }

    }
    public void initiateOrder(){
        this.table = new JTable();
        Object[] column = {"Order Id","Customer ID","Car ID","Car Model","Car Price","Purchase Date"};
        this.tableModel = new DefaultTableModel();
        tableModel.setColumnIdentifiers(column);
       // tableModel.addColumn(column);
        this.table.setModel(tableModel);
        table.setBackground(Color.WHITE);
        table.setFont(new Font("Serif", Font.PLAIN, 18));
        table.setRowHeight(30);


        Image icon = Toolkit.getDefaultToolkit().getImage("icoorder.png");
        this.setIconImage(icon);


        JScrollPane pane = new JScrollPane(table);
        pane.setBounds(50,0,700,400);
        this.add(pane);
        this.showData();
        this.table.setAutoCreateRowSorter(true);

        /*this.searchOrder= new JLabel();
        this.searchOrder.setBounds(35,20,50,30);
        this.add(this.searchOrder);*/

       // int y = this.searchOrder.getY();

        this.osearch = new JTextField();
        this.osearch.setBounds(800,30,100,25);
        this.add(this.osearch);

        this.i = new JLabel("By-ID");
        this.i.setBounds(osearch.getX()+100,30,90,25);
        this.i.setForeground(Color.RED);
        this.add(this.i);

        //y = osearch.getY();
        this.searchButton = new JButton("Search");
        this.searchButton.setBounds(760,60,85,25);
        this.add(this.searchButton);

        this.resetButton = new JButton("Reset");
        this.resetButton.setBounds(760+85+15,60,85,25);
        this.add(this.resetButton);



        this.imgOrder=new ImageIcon("imgorder.jpg");
        this.imgLabel=new JLabel(imgOrder);
        this.imgLabel.setBounds(0,0,1000,500);
        this.add(this.imgLabel);

        OrderEvent orderEventRef = new OrderEvent();
        this.searchButton.addActionListener(orderEventRef);
        this.resetButton.addActionListener(orderEventRef);
        SelectionPage.getSelectionPageRef().logout.addActionListener(this);
    }
    public static Order getOrderRef(){
        if(orderRef==null){
            orderRef = new Order();
        }
        orderRef.tableModel.getDataVector().removeAllElements();
        return orderRef;
    }

    public void showData(){
        try{
            String url = "jdbc:mysql://localhost/carshowroom";
            Connection c = DBConnector.getDataBaseConnection(url);
            String query = "Select * from orderdetails";
            Statement s = c.createStatement();
            ResultSet result  = s.executeQuery(query);
            while(result.next()) {
                    isDataAvailable = true;
                    Object[] rows = {result.getString(1),
                                    result.getString(2),
                                    result.getString(3),
                                    result.getString(4),
                                    result.getString(5),
                                    result.getString(6)};
                    tableModel.addRow(rows);
                    //System.out.println(result.getString(1));
                }
                table.setModel(tableModel);
        }catch (Exception e){
            //e.printStackTrace();
            isDataAvailable = false;
            //JOptionPane.showMessageDialog(null,"Query execution failed");
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==SelectionPage.getSelectionPageRef().logout){
            this.dispose();
        }
    }
}
