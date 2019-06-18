package dataBaseConnection;

import java.sql.Connection;
import java.sql.DriverManager;


public class DBConnector {
    private static Connection c;
    public static Connection getDataBaseConnection(String url){
        if(c==null) {
            try {
                String username = "root";
                String pass = "";
                Class.forName("com.mysql.jdbc.Driver");
                c = DriverManager.getConnection(url, username, pass);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return c;
        }
        else{
            return c;
        }
    }

}