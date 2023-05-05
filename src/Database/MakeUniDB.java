package Database;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.sql.*;
import java.util.*;

public class MakeUniDB extends JFrame implements ActionListener {

    private JTextField txtUser;
    private JPasswordField txtPass;
    private JButton start = new JButton("Start"), cancel = new JButton("Cancel"), derbyDB = new JButton("Derby DB");

    public MakeUniDB() {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        final int WIDTH = screenSize.width, LENGTH = screenSize.height;
        setLayout(null);

        JLabel info1 = new JLabel("-> Enter the username and password in which you want to install");
        info1.setBounds(10, 20, 480, 20);
        info1.setForeground(Color.RED);
        add(info1);

        JLabel info2 = new JLabel("university database in MySQL.");
        info2.setBounds(32, 40, 480, 20);
        info2.setForeground(Color.RED);
        add(info2);

        JLabel info3 = new JLabel("-> If you don't have MySQL installed then select Derby DB.");
        info3.setBounds(10, 60, 480, 20);
        info3.setForeground(Color.RED);
        add(info3);

        JLabel info4 = new JLabel("-> The username and password are taken only to create and host");
        info4.setBounds(10, 80, 480, 20);
        info4.setForeground(Color.RED);
        add(info4);

        JLabel info5 = new JLabel("University Database locally and nothing else, if you don't want");
        info5.setBounds(32, 100, 480, 20);
        info5.setForeground(Color.RED);
        add(info5);

        JLabel info6 = new JLabel("to give your password then go with Derby DB.");
        info6.setBounds(32, 120, 480, 20);
        info6.setForeground(Color.RED);
        add(info6);

        JLabel user = new JLabel("Username");
        user.setBounds(50, 150, 100, 30);
        user.setFont(new Font("LucidaGrande", Font.BOLD, 16));
        add(user);

        txtUser = new JTextField();
        txtUser.setBounds(150, 150, 150, 30);
        add(txtUser);


        JLabel password = new JLabel("Password");
        password.setBounds(50, 180, 100, 30);
        password.setFont(new Font("LucidaGrande", Font.BOLD, 16));
        add(password);

        txtPass = new JPasswordField();
        txtPass.setBounds(150, 180, 150, 30);
        add(txtPass);


        start.setBounds(72, 250, 70, 37);
        start.addActionListener(this);
        add(start);

        derbyDB.setBounds(214, 250, 70, 37);
        derbyDB.addActionListener(this);
        add(derbyDB);

        cancel.setBounds(356, 250, 70, 37);
        cancel.addActionListener(this);
        add(cancel);


        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);

        setTitle("Making University Database");
        setLocation((WIDTH - 500) / 2, (LENGTH - 350) / 2);
        setSize(500, 350);
        setVisible(true);
    }


    private boolean createDB_MySQL() {
        String userName = txtUser.getText();
        String password = txtPass.getText();
        Connection conn = null;
        try {

            Class.forName("com.mysql.cj.jdbc.Driver");

            conn = DriverManager.getConnection("jdbc:mysql:", userName, password);

            Statement s = conn.createStatement();

            s.execute("CREATE DATABASE UniversityDB");

            s.execute("USE UniversityDB");

            createTable(conn);


        } catch(SQLException ex) {
            try {
                conn = DriverManager.getConnection("jdbc:mysql:///UniversityDB", userName, password);

                createTable(conn);

                return true;

            } catch(SQLException e) {
                JOptionPane.showMessageDialog(null, "Error in connection to database, at " + e.getMessage());
                return false;
            } catch(FileNotFoundException ef){
                JOptionPane.showMessageDialog(null, "Student.csv or Instructor.csv file not found");
                return false;
            }
        } catch(FileNotFoundException ef) {
            JOptionPane.showMessageDialog(null, "Student.csv or Instructor.csv file not found");
            return false;
        } catch(ClassNotFoundException ec) {
            JOptionPane.showMessageDialog(null, "MySQL library not detected");
            return false;
        }
        return true;
    }

    private void dropTableIfExist(Connection co) {
        try {
            co.createStatement().execute("DROP TABLE student");
        } catch(Exception ignored) {

        }
        try {
            co.createStatement().execute("DROP TABLE instructor");
        } catch(Exception ignored) {

        }
        try {
            co.createStatement().execute("DROP TABLE instLeave");
        } catch(Exception ignored) {

        }
        try {
            co.createStatement().execute("DROP TABLE stuLeave");
        } catch(Exception ignored) {

        }
        try {
            co.createStatement().execute("DROP TABLE login");
        } catch(Exception ignored) {

        }
    }

    private void createTable(Connection con) throws SQLException, FileNotFoundException {

        dropTableIfExist(con);

        buildStudentTable(con);

        buildInstructorTable(con);

        buildStuLeaveTable(con);

        buildInstLeaveTable(con);

        buildLoginTable(con);

        try {
            con.close();
        } catch(SQLException e) {
            e.printStackTrace();
        }

    }

    private void buildLoginTable(Connection con) {
        try {
            Statement stmt = con.createStatement();
            stmt.execute("CREATE TABLE login (username varchar(20), password varchar(20))");

            stmt.execute("INSERT INTO login VALUES ('admin', '12345')");
        } catch(SQLException ex) {
            ex.printStackTrace();
        }

    }

    private void buildInstLeaveTable(Connection con) {
        try {
            con.createStatement().execute("CREATE TABLE instLeave (empID varchar(40), date varchar(40), duration varchar(40))");
        } catch(SQLException e) {
            e.printStackTrace();
        }
    }

    private void buildStuLeaveTable(Connection con) {
        try {
            con.createStatement().execute("CREATE TABLE stuLeave (stuNo varchar(40), date varchar(40), duration varchar(40))");
        } catch(SQLException e) {
            e.printStackTrace();
        }
    }

    private void buildInstructorTable(Connection con) throws SQLException, FileNotFoundException {
        Statement stmt = con.createStatement();
        stmt.execute("CREATE TABLE instructor (fname varchar(40), mname varchar(40), lname varchar(40), empID varchar(40), dob varchar(40), address varchar(400), mobile varchar(40), email varchar(40), faculty varchar(40))");

        Scanner s = new Scanner(new File("CSV files/Instructor.csv"));
        s.nextLine();

        String[] s1;
        while(s.hasNextLine()) {
            s1 = s.nextLine().split(",");
            String values = "('" + s1[0] + "', '" + s1[1] + "', '" + s1[2] + "', '" + s1[3] + "', '" + s1[4] + "', '" + s1[5].substring(1) + "," + s1[6] + "," + s1[7].substring(0, s1[7].length() - 1) + "', '" + s1[8] + "', '" + s1[9] + "', '" + s1[10] + "')";

            stmt.execute("INSERT INTO instructor VALUES " + values);
        }
    }

    private void buildStudentTable(Connection con) throws SQLException, FileNotFoundException {
        Statement stmt = con.createStatement();
        Scanner s = new Scanner(new File("CSV files/Student.csv"));

        stmt.execute("CREATE TABLE student(fname varchar(40), mname varchar(40), lname varchar(40), studentNo varchar(40), dob varchar(40), address varchar(400), mobile varchar(40), email varchar(40), faculty varchar(40))");

        s.nextLine();

        String[] s1;
        while(s.hasNextLine()) {
            s1 = s.nextLine().split(",");
            String values = "('" + s1[0] + "', '" + s1[1] + "', '" + s1[2] + "', '" + s1[3] + "', '" + s1[4] + "', '" + s1[5].substring(1) + "," + s1[6] + "," + s1[7].substring(0, s1[7].length() - 1) + "', '" + s1[8] + "', '" + s1[9] + "', '" + s1[10] + "')";

            stmt.execute("INSERT INTO student VALUES " + values);
        }
    }

    private boolean createDB_DerbyAB() {
        Connection conn;
        try {
            Class.forName("org.apache.derby.iapi.jdbc.Driver42");
            conn = DriverManager.getConnection("jdbc:derby:UniversityDB;create=true");

            createTable(conn);

            conn.close();

        } catch(SQLException e) {
            JOptionPane.showMessageDialog(null, "Error in connection to database, at " + e.getMessage());
            return false;
        } catch(FileNotFoundException ef) {
            JOptionPane.showMessageDialog(null, "Student.csv or Instructor.csv file not found");
            return false;
        } catch(ClassNotFoundException ec) {
            JOptionPane.showMessageDialog(null, "Derby DB library not found");
            return false;
        }
        return true;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == derbyDB) {
            //Enter code for derby DB library
            if(createDB_DerbyAB())
                JOptionPane.showMessageDialog(null, "UniversityDB build SUCCESSFULLY :)");
        } else if(e.getSource() == start) {
            if(createDB_MySQL())
                JOptionPane.showMessageDialog(null, "UniversityDB build SUCCESSFULLY :)");
        } else if(e.getSource() == cancel) {
            setVisible(false);
            dispose();
        }
    }
}
