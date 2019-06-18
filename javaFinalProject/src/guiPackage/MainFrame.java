package guiPackage;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class MainFrame extends JFrame{

    private static MainFrame MainFrameRef;

    public MainFrame() {
        MainFrameRef = this;

        Image icon = Toolkit.getDefaultToolkit().getImage("iclogin.png");
        this.setIconImage(icon);

       // JFrame frame = new JFrame();
        this.setLayout(new BorderLayout());
        this.setBounds(200,100,640,480);
        //this.remove(LoginPage.getLoginPageRef());
        this.add(LoginPage.getLoginPageRef(),BorderLayout.CENTER);
        this.setTitle("Login Page");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        //  new LoginPage();
    }

    public static MainFrame getMainFrameRef(){
        if(MainFrameRef == null){
            MainFrameRef = new MainFrame();
        }
        return MainFrameRef;
    }
}
