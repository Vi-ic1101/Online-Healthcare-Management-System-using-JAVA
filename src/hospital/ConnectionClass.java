package hospital;
import java.sql.*;
import java.sql.Connection;
import java.sql.Statement;

public class ConnectionClass {

    Connection con;
    Statement stm;
    ConnectionClass(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con=DriverManager.getConnection("jdbc:mysql://localhost:3306/hospital_management_System","root","12345");
            stm= con.createStatement();
        }catch (Exception ex){
            ex.printStackTrace();

        }
    }
    public static void main(String []args){
        new ConnectionClass();
    }
}
