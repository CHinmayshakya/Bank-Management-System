import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Conn {
    public Statement st;
    public Connection con;
    Conn(){
        String url = "jdbc:mysql://localhost:3306/bankingSystem";
        String userName = "root";
        String password = "Chinmay2212";

        try{
            con = DriverManager.getConnection(url,userName,password);
            st = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
        }
        catch (Exception e){
            System.out.println(e);
        }
    }
}
