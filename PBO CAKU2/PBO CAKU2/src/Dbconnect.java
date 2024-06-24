import java.sql.*;

public class Dbconnect {
    private static final String HOST = "127.0.0.1";
private static final int PORT = 3306;
private static final String DB_NAME = "login";
private static final String USERNAME = "root";
private static final String PASSWORD = ""; // Isi dengan password MySQL Anda


    public static Connection getConnect() {
        Connection connection = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver"); // Load the MySQL JDBC driver
            connection = DriverManager.getConnection(
                    String.format("jdbc:mysql://%s:%d/%s", HOST, PORT, DB_NAME),
                    USERNAME, PASSWORD);
            System.out.println("Connected to database successfully!");
        } catch (ClassNotFoundException e) {
            System.err.println("MySQL JDBC driver not found!");
            e.printStackTrace();
        } catch (SQLException e) {
            System.err.println("Error connecting to the database: " + e.getMessage());
            e.printStackTrace();
        }
        
        return connection;
    }
    
    }
