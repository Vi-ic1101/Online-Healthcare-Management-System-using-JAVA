package hospital;

import java.sql.Connection;
import java.sql.Statement;

public class ConnectionClass {

    Connection con;
    Statement stm;
    ConnectionClass(){
        try {

        }catch (Exception ex){
            ex.printStackTrace();

        }
    }
    public static void main(String []args){
        new ConnectionClass();
    }
}
