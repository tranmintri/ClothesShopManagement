/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ConnectDB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author 20086
 */
public class KetNoiSQL {
    private static Connection conn = null;
    private static KetNoiSQL instance = new KetNoiSQL();
    
    public static KetNoiSQL getInstance(){
        return instance;
    }
    
     public void connect(){
        String url = "jdbc:sqlserver://localhost:1433;database=QLBHTT;encrypt=false;trustServerCertificate=true;";
        String username = "sa";
        String password = "sapassword";
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            conn = DriverManager.getConnection(url, username, password);
            System.out.println("Connect database sucessfully");
        } catch (SQLException ex) {
            Logger.getLogger(KetNoiSQL.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Connect failed");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(KetNoiSQL.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static Connection getConnection(){
        return conn;
    }
}
