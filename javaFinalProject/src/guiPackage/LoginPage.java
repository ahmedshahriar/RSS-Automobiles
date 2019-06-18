package guiPackage;
import eventHandling.LoginEventHandling;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class LoginPage extends JPanel {
    public JLabel background,imgLabel;
    public JPasswordField password;
    private ImageIcon i;
    public JLabel id,pass;
    public JButton login,reset,signup;
    public JTextField idtxt;
    public static LoginPage LoginPageRef=null;
   // JFrame frame = new JFrame();


   public static LoginPage getLoginPageRef(){
       if(LoginPageRef==null){
           LoginPageRef = new LoginPage();
          // return ref;
       }
       return LoginPageRef;
   }



    public void resetTxtField(){
       this.idtxt.setText("");
       this.password.setText("");
    }
    public LoginPage()  {
        initialLoginPage();
       /*// BufferedImage bf = ImageIO.read(new File("C:/Users/Supto/Desktop/h.jpg"));
        try {
            Image i = ImageIO.read(new File("C:\\Users\\Supto\\Desktop\\h.jpg"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.setContentPane(i);*/

        //  this.setContentPane(bf);
       // this.setBackground(Color.getHSBColor(13,40,90));
        this.setLayout(new BorderLayout());
        //this.setBounds(100,100,400,400);
        //ImageIcon image = new ImageIcon("")

    }

   /* private void setContentPane(Image i) {
        this.i=i;
    }*/

   /* @Override
    public void paintComponent(Graphics g)
    {
        g.drawImage(i,0,0,null);
    }*/

    public void initialLoginPage(){


        this.id = new JLabel("USER-ID");
        this.id.setBounds(80,90,50,25);
        this.id.setForeground(Color.white);
        this.add(this.id);
        setLayout(new FlowLayout());

        this.pass = new JLabel("PASSWORD");
        this.pass.setBounds(60,90+25+25,80,25);
        this.pass.setForeground(Color.white);
        this.add(this.pass);

        this.idtxt = new JTextField();
        this.idtxt.setBounds(80+50+15,90,120,25);
        this.add(this.idtxt);

        this.password = new JPasswordField();
        this.password.setBounds(80+50+15,90+50,120,25);
        this.add(password);

        //Rectangle r = this.password.getBounds();
        int x = this.password.getX();
        int y = this.password.getY();

        this.login = new JButton("Login");
        this.login.setBounds(x+90,y+60,70,30);
        this.add(this.login);

        this.reset = new JButton("Reset");
        this.reset.setBounds(x-90,y+60,70,30);
        this.add(this.reset);

        this.signup = new JButton("Register");
        this.signup.setBounds(this.reset.getX()+77,this.reset.getY(),100,28);
        this.add(this.signup);

        this.i=new ImageIcon("imlogin.jpg");
        this.imgLabel=new JLabel(i);
        this.imgLabel.setBounds(0,0,640,480);
        this.add(this.imgLabel);


        LoginEventHandling ref = new LoginEventHandling();
        this.reset.addActionListener(ref);
        this.login.addActionListener(ref);
        this.signup.addActionListener(ref);

    }

}
