import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Conn {

    private Connection c;
    protected Statement stmt;

    public Conn() throws SQLException {
            // Class.forName("com.mysql.cj.jdbc.Driver");
            c = DriverManager.getConnection("jdbc:mysql:///universityDatabase", "root", "hetu2344");
            stmt = c.createStatement();
    }
    
    public static void main(String[] args) {
        try{
            Conn c1 = new Conn();
            System.out.println("Connection established!");
        } catch(SQLException ex){
            System.out.println("Username and Password not provided for database connection");
        }
    }
}
