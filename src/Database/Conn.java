package Database;

import java.sql.*;

public class Conn {

    private Connection c;
    public Statement stmt;

    public Conn() throws SQLException, ClassNotFoundException {
        Class.forName("org.apache.derby.iapi.jdbc.Driver42");

        c = DriverManager.getConnection("jdbc:derby:UniversityDB;");
        stmt = c.createStatement();
    }

    public Conn(String username, String password) throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");

        c = DriverManager.getConnection("jdbc:mysql:///UniversityDB", username, password);
        stmt = c.createStatement();
    }

    public void close(){
        try {
            c.close();
        } catch(SQLException ignored) {
        }
    }
}
