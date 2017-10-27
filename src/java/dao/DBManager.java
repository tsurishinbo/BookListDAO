package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBManager {

    private static String driverName = "org.postgresql.Driver";
    private static String url = "jdbc:postgresql://localhost:5432/bookdb";
    private static String user = "postgres";
    private static String pass = "postgres";
    
    public static Connection getConnection() {
        Connection con = null;
        try {
            Class.forName(driverName);
            con = DriverManager.getConnection(url, user, pass);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return con;
    }
}
