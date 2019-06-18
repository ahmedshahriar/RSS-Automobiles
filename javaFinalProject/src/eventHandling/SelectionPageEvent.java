package eventHandling;

import com.sun.org.apache.xpath.internal.operations.Or;
import guiPackage.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SelectionPageEvent implements ActionListener {
    private SelectionPage selectionPageRef;
    private MainFrame mainFrameRef;
    private Customer customerRef;
   // private static int count = 0;
    @Override
    public void actionPerformed(ActionEvent e) {
        selectionPageRef = SelectionPage.getSelectionPageRef();
        mainFrameRef = MainFrame.getMainFrameRef();
       // customerRef = Customer.getCustomerRef();
        if(e.getSource()==selectionPageRef.customer){
            System.out.println("customer Button");
            //Customer c= new Customer("Customer Details");
            if(Customer.getCustomerRef().getDefaultCloseOperation()== WindowConstants.DISPOSE_ON_CLOSE){
                Customer.getCustomerRef().setVisible(true);
                Customer.getCustomerRef().csearch.setText("");
            }
            Customer.getCustomerRef();
            Customer.getCustomerRef().showData();
        }
        if(e.getSource()==selectionPageRef.bill){
            System.out.println("bill Button");
            if(OrderForm.getorderformRef().getDefaultCloseOperation()==WindowConstants.DISPOSE_ON_CLOSE){
                OrderForm.getorderformRef().setVisible(true);
//                OrderFormEvent.getOrderFormEvnetRef().clearAll();
                OrderForm.getorderformRef().carIDList.clear();
                OrderForm.getorderformRef().selectCarID.removeAllItems();
                OrderForm.getorderformRef().selectCarID.addItem("Select car id..");
                OrderForm.getorderformRef().setCombobox();
                OrderFormEvent.orderId = OrderFormEvent.getPrevOrerId();
                ++OrderFormEvent.orderId;
                OrderForm.getorderformRef().setOrderID.setText(""+OrderFormEvent.orderId);
                OrderForm.getorderformRef().setDate();
                Car.getCarRef().showData();
                Car.getCarRef().dispose();
            }

        }
        if(e.getSource()==selectionPageRef.car){
            System.out.println("car Button");
            if(Car.getCarRef().getDefaultCloseOperation()==WindowConstants.DISPOSE_ON_CLOSE){
                Car.getCarRef().setVisible(true);
                Car.getCarRef().txtsearch.setText("");
            }
            Car.getCarRef();
            Car.getCarRef().showData();
        }
        if(e.getSource()==selectionPageRef.order){
            System.out.println("Order button");
            if(Order.getOrderRef().getDefaultCloseOperation()==WindowConstants.DISPOSE_ON_CLOSE){
                Order.getOrderRef().setVisible(true);
                Order.getOrderRef().osearch.setText("");
            }
            Order.getOrderRef();
            Order.getOrderRef().showData();

        }
        if(e.getSource()==selectionPageRef.logout){
            /*Order.getOrderRef().dispose();
            Customer.getCustomerRef().dispose();
            Car.getCarRef().dispose();
            OrderForm.getorderformRef().dispose();
            TotalRevenue.getTotalRevenueRef().dispose();*/

            LoginPage.getLoginPageRef().resetTxtField();
            mainFrameRef.add(LoginPage.getLoginPageRef(), BorderLayout.CENTER);
            mainFrameRef.remove(SelectionPage.getSelectionPageRef());
            mainFrameRef.setTitle("Login Page");
            mainFrameRef.setSize(640,480);
        }
        if(e.getSource()==selectionPageRef.rev){
            new TotalRevenue();
        }
    }
}
