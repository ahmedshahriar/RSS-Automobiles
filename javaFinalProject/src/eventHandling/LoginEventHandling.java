package eventHandling;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;

import dataBaseConnection.DBConnector;
import guiPackage.LoginPage;
import guiPackage.MainFrame;
import guiPackage.SelectionPage;
import guiPackage.SignupPage;



public class LoginEventHandling implements ActionListener {
    MainFrame frame = MainFrame.getMainFrameRef();
    private String pass=null,id=null;
    private LoginPage logref=null;
    public boolean islogin = false;
    private String url = "jdbc:mysql://localhost/carshowroom";
    Connection c = DBConnector.getDataBaseConnection(url);
    @Override
    public void actionPerformed(ActionEvent e) {
        logref = LoginPage.getLoginPageRef();
       // System.out.println(e.getActionCommand()+"\n"+(e.getSource()==logref.login));
        if(e.getSource()==logref.login){
             //System.out.println("Done");
             pass = String.valueOf(logref.password.getPassword());
             id = logref.idtxt.getText();
             checkUser(id,pass);

             if(islogin==true){
                 frame.add(SelectionPage.getSelectionPageRef(), BorderLayout.CENTER);
                 frame.remove(LoginPage.getLoginPageRef());
                 frame.setTitle("Main Frame");
                 frame.paintAll(frame.getGraphics());
                 frame.setSize(700,500);
             }

        }
        if(e.getSource()==logref.reset){
            logref.idtxt.setText("");
            logref.password.setText("");
        }
        if(e.getSource()==logref.signup){
            SignupPage signupref = SignupPage.startSignupPage();
        }

    }

    public void checkUser(String id,String pass){
        try{
            String query = "SELECT * FROM login_details where id= ? and password= ?";
            PreparedStatement pr = c.prepareStatement(query);
            pr.setString(1,id);
            pr.setString(2,pass);
            ResultSet result = pr.executeQuery();

            if(result.next()){
              //  System.out.println("log in done");
                islogin = true;
            }
            else{
                System.out.println(result.isLast());
                logref.idtxt.setText("");
                logref.password.setText("");
                JOptionPane.showMessageDialog(null,"Invalid Password or User id!");
            }
        }catch (Exception e){
            e.printStackTrace();

        }
    }
}
