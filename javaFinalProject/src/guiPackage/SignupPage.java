package guiPackage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.lang.Integer;

import dataBaseConnection.DBConnector;

public class SignupPage extends JFrame implements ActionListener {

    private JLabel name,id,pass,imgLabel;
    private JTextField txtname,txtid;
    private JPasswordField password;
    private JButton save,reset;
    private ImageIcon imgSignup;
    private Connection c=null;
    private String username=null,userid=null,userpass=null;
    private static SignupPage signupPageRef=null;

    public SignupPage(){
        super("Sign Up");
        initiateSignup();
        String url = "jdbc:mysql://localhost/carshowroom";
        c = DBConnector.getDataBaseConnection(url);

        this.setBounds(300,200,350,300);
        this.setLayout(null);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

    }

    private void initiateSignup() {

        Image icon = Toolkit.getDefaultToolkit().getImage("icosignup.png");
        this.setIconImage(icon);

        this.name = new JLabel("Name");
        this.name.setBounds(40,15,50,30);
        this.name.setForeground(Color.white);
        this.add(this.name);

        int x = name.getX();
        int y = name.getY();

        this.id = new JLabel("ID");
        this.id.setBounds(x,y+50,50,30);
        this.id.setForeground(Color.white);
        this.add(this.id);

        y = id.getY();
        this.pass = new JLabel("Password");
        this.pass.setBounds(x-20,y+50,80,30);
        this.pass.setForeground(Color.white);
        this.add(this.pass);

        x = this.name.getX();
        y = this.name.getY();
        this.txtname = new JTextField();
        this.txtname.setBounds(x+45,y,150,25);
        this.add(this.txtname);

        x = this.id.getX();
        y = this.id.getY();
        this.txtid = new JTextField();
        this.txtid.setBounds(x+45,y,150,25);
        this.add(this.txtid);

        x = this.pass.getX();
        y = this.pass.getY();
        this.password = new JPasswordField();
        this.password.setBounds(x+45+20,y,150,25);
        this.add(this.password);

        x = this.password.getX();
        y = this.password.getY();

        this.save = new JButton("Save");
        this.save.setBounds(x+120,y+60,70,30);
        this.add(this.save);

        this.reset = new JButton("Reset");
        this.reset.setBounds(x-70,y+60,70,30);
        this.add(this.reset);

        this.imgSignup=new ImageIcon("imgsignup.jpg");
        this.imgLabel=new JLabel(imgSignup);
        this.imgLabel.setBounds(0,0,350,350);
        this.add(this.imgLabel);

        this.save.addActionListener(this);
        this.reset.addActionListener(this);

    }

    public static SignupPage startSignupPage(){
        if(signupPageRef==null){
            signupPageRef = new SignupPage();
        }
        signupPageRef.txtname.setText("");
        signupPageRef.txtid.setText("");
        signupPageRef.password.setText("");
        return signupPageRef;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==save){
            System.out.println("save");
            username = this.txtname.getText();
            userid = this.txtid.getText();
            userpass = String.valueOf(this.password.getPassword());
            //System.out.println(username.length()+" "+ userpass);
            if(username.length()>0 && userid.length()>0 && userpass.length()>0) {
                //System.out.println("done");
                saveDate(username, userid, userpass);
                signupPageRef.txtname.setText("");
                signupPageRef.txtid.setText("");
                signupPageRef.password.setText("");
                signupPageRef.dispose();
            }
            else{
                this.txtname.setBackground(Color.getHSBColor(13,40,90));
                this.txtid.setBackground(Color.getHSBColor(13,40,90));
                this.password.setBackground(Color.getHSBColor(13,40,90));
                this.txtname.setText("");
                this.txtid.setText("");
                this.password.setText("");
                JOptionPane.showMessageDialog(null,"Invalid!");
            }
        }
        if(e.getSource()==reset){
            this.txtname.setBackground(Color.white);
            this.txtid.setBackground(Color.white);
            this.password.setBackground(Color.white);
            this.txtname.setText("");
            this.txtid.setText("");
            this.password.setText("");
            userpass=null;
            userid=null;
            username=null;
           // System.out.println(username+" "+ userpass);
        }
    }
    public void saveDate(String name,String id,String pass){

        try{
            String query = "INSERT INTO login_details (name,id,password) VALUES (?, ?, ?)";
            PreparedStatement pr = c.prepareStatement(query);
            //Statement stmt = c.createStatement();
            //stmt.executeQuery(query);

            pr.setString(1,name);
            pr.setString(2,id);
            pr.setString(3,pass);
            pr.execute();
            pr.close();
            //System.out.println("done");
            JOptionPane.showMessageDialog(null,"Insert Successfully");

        }catch (Exception e){
            //System.out.println("error");
           // e.printStackTrace();
            JOptionPane.showMessageDialog(null,"Something wrong!");
        }
    }
}
