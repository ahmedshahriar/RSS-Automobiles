package guiPackage;

import dataBaseConnection.DBConnector;
import eventHandling.OrderFormEvent;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class TotalRevenue extends JFrame implements ActionListener{
    public static TotalRevenue totalRevenueRef = null;
    public JLabel priceLabel,tag,imgLabel;
    public ImageIcon imgRev;
    public JTable table;
    public DefaultTableModel tableModel;
    private  boolean isdataAvailable = false;
     public TotalRevenue(){
         super("Total Revenue");
         initiategui();
         this.setLayout(null);
         this.setBounds(100,100,800,500);
         this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
         this.setVisible(true);
         this.tableModel.getDataVector().removeAllElements();
         this.showTotalRevenue();
         if(!isdataAvailable){
             JOptionPane.showMessageDialog(null,"No cars available!");
         }
     }

    public static TotalRevenue getTotalRevenueRef(){
        if(totalRevenueRef==null){
            totalRevenueRef = new TotalRevenue();
        }
        totalRevenueRef.tableModel.getDataVector().removeAllElements();
        return totalRevenueRef;
    }

    public void initiategui(){
        this.tag = new JLabel("Total Reveneue");
        this.tag.setBounds(610,30,150,80);
        this.tag.setFont(new Font("tahoma", Font.PLAIN, 20));
        this.tag.setForeground(Color.white);
        this.add(this.tag);

        Image icon = Toolkit.getDefaultToolkit().getImage("icorevenue.jpg");
        this.setIconImage(icon);


        long price  = new OrderFormEvent().reveneue();
        if(price<0){
            price = 0;
        }
        String p = Long.toString(price);
        this.priceLabel = new JLabel(p+" BDT");
        this.priceLabel.setBounds(this.tag.getX()+9,this.tag.getY()+40,200,80);
        this.priceLabel.setFont(new Font("tahoma", Font.BOLD, 18));
        this.priceLabel.setForeground(Color.BLUE);
        this.add(this.priceLabel);

        this.table = new JTable();
        Object[] column = {"Order ID","Amount","Purchase Date"};
        this.tableModel = new DefaultTableModel();
        tableModel.setColumnIdentifiers(column);
        this.table.setModel(tableModel);
        table.setBackground(Color.WHITE);
        table.setRowHeight(30);
        table.setFont(new Font("Serif", Font.PLAIN, 18));

        JScrollPane pane = new JScrollPane(table);
        pane.setBounds(30,0,550,300);
        this.add(pane);
        this.showTotalRevenue();

        this.imgRev=new ImageIcon("imgrevenue.jpg");
        this.imgLabel=new JLabel(imgRev);
        this.imgLabel.setBounds(0,0,800,500);
        this.add(this.imgLabel);
        SelectionPage.getSelectionPageRef().logout.addActionListener(this);
    }

    public void showTotalRevenue() {
        try {
            String url = "jdbc:mysql://localhost/carshowroom";
            Connection c = DBConnector.getDataBaseConnection(url);
            String query = "Select * from total_revenue";
            Statement s = c.createStatement();
            ResultSet result = s.executeQuery(query);
            while (result.next()) {
                isdataAvailable = true;
                Object[] rows = {result.getString(1),
                        result.getString(2),
                        result.getString(3)};
                tableModel.addRow(rows);
            }
            table.setModel(tableModel);
        } catch (Exception e) {
            //e.printStackTrace();
            //JOptionPane.showMessageDialog(null,"Query execution failed");
            System.out.println("Query execution failed");
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==SelectionPage.getSelectionPageRef().logout){
            this.dispose();
        }
    }
}
