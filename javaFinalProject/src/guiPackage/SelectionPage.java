package guiPackage;

import eventHandling.SelectionPageEvent;

import javax.swing.*;
import java.awt.*;

public class SelectionPage extends JPanel {
    public JLabel label,imgLabel;
    ImageIcon imgSelection;
    public JButton car,order,customer,bill,logout,rev;
    private static SelectionPage selectionPageRef;

    public  SelectionPage(){
        initiateFrame();
        //this.setBounds(300,200,600,450);
        this.setLayout(null);
        this.setVisible(true);
        //this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void initiateFrame() {
        /*this.setBackground(Color.blue);
        this.label = new JLabel("");
        label.setIcon(new ImageIcon("C:\\Users\\Supto\\IdeaProjects\\javaFinalProject\\Show.png"));
        Rectangle r = this.getBounds();
        label.setBounds(r);
        label.setSize(300,300);
        this.getContentPane().add(label);*/
        this.car = new JButton("Car Records");
        this.car.setBounds(40,65,160,70);
        this.add(this.car);

        int x = this.car.getX();
        int y = this.car.getY();

        this.customer = new JButton("Customer Records");
        this.customer.setBounds(x+140+80,y,160,70);
        this.add(this.customer);

        x = this.customer.getX();
        this.order = new JButton("Order Records");
        this.order.setBounds(x+140+80,y,160,70);
        this.add(this.order);

       // x = this.order.getX();
        y = this.order.getY();

        this.bill = new JButton("Create Bill");
        this.bill.setBounds(x,y+70+80,160,70);
        this.add(this.bill);

        //this.logout = new JButton(new ImageIcon("C:\\Users\\Supto\\IdeaProjects\\javaFinalProject\\logout.png"));
        this.logout = new JButton("Logout");
        this.logout.setBounds(580,10,100,25);
        this.add(this.logout);

        this.rev = new JButton("Total Revenue");
        this.rev.setBounds(5,10,115,25);
        this.add(this.rev);
        SelectionPageEvent ref = new SelectionPageEvent();


        this.imgSelection=new ImageIcon("imgselection.jpg");
        this.imgLabel=new JLabel(imgSelection);
        this.imgLabel.setBounds(0,0,700,500);
        this.add(this.imgLabel);



        this.customer.addActionListener(ref);
        this.bill.addActionListener(ref);
        this.car.addActionListener(ref);
        this.order.addActionListener(ref);
        this.logout.addActionListener(ref);
        this.rev.addActionListener(ref);
    }

    public static SelectionPage getSelectionPageRef(){
        if(selectionPageRef==null){
            selectionPageRef=new SelectionPage();
        }
        return selectionPageRef;
    }
}
