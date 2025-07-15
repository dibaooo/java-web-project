/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package baovd.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;




/**
 *
 * @author Asus
 */
public class DBHelper {
    public static Connection makeConnection() 
        throws ClassNotFoundException, SQLException { // ClassNotFoundException 1.chua add driver vao trong project
                                        //  2. chuoi sai  //SQLException 1.URL sai 2. 3.sai password
                                        
    
        //1. Load Driver - com - SQLServerDriver
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        //2. Create connection String to connect DB Container
        //syntax: protocol://ip:port;databseName=...   (_protocol phan cach / _con lai ;)
        //ex: jdpc:sqlserver://localhost:1433;databaseName=PRJSE1824
        String url = "jdbc:sqlserver://"
                + "localhost:1433;"
                + "databaseName=PRJSE1824";
        //3. Open connection using DriverManager
        Connection con = DriverManager.getConnection(url, "sa","12345");
                
        return con;        
    }
    
    
}
