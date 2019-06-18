package guiPackage;
import dataBaseConnection.DBConnector;
import eventHandling.CustomerEvent;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class Customer extends JFrame implements ActionListener{
    public JButton reset,searchButton;
    public JLabel i,imgLabel;
    public ImageIcon imgCustomer;
    public JTextField cName,cPhone,cid,cAddress,csearch;
    private JLabel searchCus;
    public JTable table;
    private static Customer customerRef;
    private boolean isDataAvailable = false;
    public DefaultTableModel tableModel;

    public Customer(){
        super("Customer Details");
        initiateCustomer();
        this.setLayout(null);
        this.setBounds(100,200,1100,500);
        this.setVisible(true);
        this.tableModel.getDataVector().removeAllElements();
        this.showData();
        if(!isDataAvailable){
            JOptionPane.showMessageDialog(null,"No data to show");
        }
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    public void initiateCustomer(){
        this.table = new JTable();
        String[] column = {"ID","Name","Phone","Address","Purchase Date"};
        this.tableModel = new DefaultTableModel();
        tableModel.setColumnIdentifiers(column);
        this.table.setModel(tableModel);
        table.setBackground(Color.WHITE);
        table.setFont(new Font("Serif", Font.PLAIN, 18));
        table.setRowHeight(30);

        Image icon = Toolkit.getDefaultToolkit().getImage("icocustomer.png");
        this.setIconImage(icon);

        JScrollPane pane = new JScrollPane(table);
        pane.setBounds(50,0,800,400);
        this.add(pane);
        this.showData();

        /*this.searchCus = new JLabel("Search");
        this.searchCus.setBounds(35,20,50,30);
        this.add(this.searchCus);

        int y = this.searchCus.getY();*/

        this.csearch = new JTextField();
        this.csearch.setBounds(900,30,100,25);
        this.add(this.csearch);

        this.i = new JLabel("By-ID");
        this.i.setBounds(csearch.getX()+100,30,90,25);
        this.i.setForeground(Color.RED);
        this.add(this.i);

        this.searchButton = new JButton("Search");
        this.searchButton.setBounds(860,60,85,25);
        this.add(this.searchButton);

        this.reset = new JButton("Reset");
        this.reset.setBounds(860+85+15,60,85,25);
        this.add(this.reset);

        this.imgCustomer=new ImageIcon("imgcustomer.jpg");
        this.imgLabel=new JLabel(imgCustomer);
        this.imgLabel.setBounds(0,0,1100,500);
        this.add(this.imgLabel);

        CustomerEvent customerEventRef = new CustomerEvent();
        this.searchButton.addActionListener(customerEventRef);
        this.reset.addActionListener(customerEventRef);
        SelectionPage.getSelectionPageRef().logout.addActionListener(this);
    }
    public static Customer getCustomerRef(){
        if(customerRef==null){
            customerRef = new Customer();
        }
        customerRef.tableModel.getDataVector().removeAllElements();
        return customerRef;
    }
    public void showData(){
        try{
            String url = "jdbc:mysql://localhost/carshowroom";
            Connection c = DBConnector.getDataBaseConnection(url);
            String query = "Select * from customer";
            Statement s = c.createStatement();
            ResultSet result  = s.executeQuery(query);
            while(result.next()) {
                isDataAvailable = true;
                Object[] rows = {result.getString(1),
                        result.getString(2),
                        result.getString(3),
                        result.getString(4),
                        result.getString(5)};
                tableModel.addRow(rows);
                //System.out.println(result.getString(1));
            }
            table.setModel(tableModel);
        }catch (Exception e){
            //e.printStackTrace();
            JOptionPane.showMessageDialog(null,"Query execution failed");
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==SelectionPage.getSelectionPageRef().logout){
            this.dispose();
        }
    }
}
