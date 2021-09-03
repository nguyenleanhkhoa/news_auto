/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Anhkhoa
 */
public class DbConnector {

    private static final String DATABASE_NAME = "news_db";
//    private static final String URL = "jdbc:mysql://113.161.80.157:13306/" + DATABASE_NAME + "?useSSL=false";
//    private static final String USER_NAME = "khoanguyen";
//    private static final String PASS_WORD = "Khoa@2019";
    
  private static final String URL = "jdbc:mysql://localhost:3306/" + DATABASE_NAME + "?useSSL=false";
  private static final String USER_NAME = "root";
  private static final String PASS_WORD = "Abc12345";

    /**
     * Connect database
     * @return result of connection
     */
    public static Connection connectDb() {
        Connection con = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(URL, USER_NAME, PASS_WORD);
            System.out.println("Database connected!");
            return con;
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
            Logger.getLogger(DbConnector.class.getName()).log(Level.SEVERE, null, ex);
        }

        return con;
    }
}
