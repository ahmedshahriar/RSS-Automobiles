package guiPackage;

import javax.swing.*;

public class Main {
    public static void main(String[] args){
       SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new MainFrame();
           }
        });
        //OrderForm.getorderformRef();
       // SelectionPage.getSelectionPageRef();
         //Car.getCarRef();
        //Order.getOrderRef();
        //Customer.getCustomerRef();
        //TotalRevenue.getTotalRevenueRef();
       // SelectionPage.getSelectionPageRef();

    }
}
