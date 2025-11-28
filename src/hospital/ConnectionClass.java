package hospital;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * ConnectionClass
 * -----------------------------
 * Handles the database connection for the application.
 * Follows proper OOP structure, adds exception handling,
 * and provides a getter for reusable DB connection.
 */
public class ConnectionClass {

    public Connection con;
    public Statement stm;

    // Constructor establishes DB connection
    public ConnectionClass() {
        try {
            // Load MySQL JDBC Driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Establish the connection
            con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/hospital_management_system",
                    "root",
                    "12345"
            );

            // Create a reusable statement object
            stm = con.createStatement();

            System.out.println("Database Connected Successfully.");

        } catch (ClassNotFoundException e) {
            System.out.println("JDBC Driver not found.");
            e.printStackTrace();

        } catch (SQLException e) {
            System.out.println("Error while connecting to the database.");
            e.printStackTrace();
        }
    }

    /**
     * Returns the active database connection.
     */
    public Connection getConnection() {
        return con;
    }

    /**
     * Returns a statement object.
     */
    public Statement getStatement() {
        return stm;
    }

    /**
     * Safely closes the connection.
     */
    public void closeConnection() {
        try {
            if (stm != null) stm.close();
            if (con != null) con.close();

            System.out.println("Connection closed.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Optional: test the class
    public static void main(String[] args) {
        ConnectionClass cc = new ConnectionClass();
        cc.closeConnection();
    }
}
