package java;

import javax.swing.*;
import java.Database.Conn;
import java.Database.MakeUniDB;
import java.Interface.MainScreen;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class Login extends JFrame implements ActionListener {

    Conn c;

    JPanel loginPanel, DBPanel, MySQL_Connect_Panel, startPanel;

    JRadioButton MySQL, Derby_DB;

    private JButton cancel, login, connect, connectToDB, makeUniDB;
    private JPasswordField tfpassword;
    private JTextField tfusername;
    private Choice DB;
    private JLabel error;

    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    final int WIDTH = screenSize.width, LENGTH = screenSize.height;

    public Login() {

        //Test code

        intializeStartPanel();
        add(startPanel);

        //Test code ends

        initializeDBPanel();
//
        add(DBPanel);
//
        initializeMySQLPanel();
//
        add(MySQL_Connect_Panel);

        //Setting some default functions for Frame
        setLayout(null);
        setResizable(false);
        //        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("java.Login");

        //Showing the Frame

        //To change the default frame open location from top left to desired one
        setLocation((WIDTH - 400) / 2, (LENGTH - 350) / 2);
        setSize(400, 350);
        setVisible(true);
    }

    private void initializeDBPanel() {
        DBPanel = new JPanel(null);


        JLabel info = new JLabel("Please select your database");
        info.setFont(new Font("LucidaGrande", Font.BOLD, 20));
        info.setBounds(60, 30, 290, 40);
        DBPanel.add(info);

        MySQL = new JRadioButton("MySQL");
        MySQL.addActionListener(this);
        MySQL.setBounds(56, 90, 100, 30);


        Derby_DB = new JRadioButton("Derby DB");
        Derby_DB.addActionListener(this);
        Derby_DB.setBounds(247, 90, 100, 30);


        DBPanel.add(MySQL);
        DBPanel.add(Derby_DB);

        DBPanel.setVisible(false);
        DBPanel.setSize(400, 120);
    }

    private void initializeLoginPanel() {
        loginPanel = new JPanel(null);
        //Adding the background image
        ImageIcon background = new ImageIcon(ClassLoader.getSystemResource("icons/second.jpg"));
        //Scaling the Image
        Image i2 = background.getImage().getScaledInstance(50, 50, Image.SCALE_DEFAULT);
        background = new ImageIcon(i2);
        JLabel image = new JLabel(background);
        image.setBounds(175, 250, 50, 50);
        loginPanel.add(image);

        //Username text
        JLabel username = new JLabel("Username:");
        username.setBounds(30, 60, 100, 30);
        loginPanel.add(username);
        //Text area for entering username
        tfusername = new JTextField();
        tfusername.setBounds(150, 60, 200, 30);
        loginPanel.add(tfusername);

        //Username text
        JLabel password = new JLabel("Password:");
        password.setBounds(30, 110, 100, 30);
        loginPanel.add(password);
        //Text area for entering username
        tfpassword = new JPasswordField();
        tfpassword.setBounds(150, 110, 200, 30);
        loginPanel.add(tfpassword);

        error = new JLabel("Invalid username or password");
        error.setBounds(100, 150, 200, 30);
        error.setForeground(Color.RED);
        error.setVisible(false);
        loginPanel.add(error);

        //Adding buttons for login and cancel
        login = new JButton("java.Login");
        login.setBounds(105, 190, 70, 35);

        cancel = new JButton("Cancel");
        cancel.setBounds(225, 190, 70, 35);

        login.addActionListener(this);
        cancel.addActionListener(this);
        loginPanel.add(login);
        loginPanel.add(cancel);

        loginPanel.setVisible(false);
        loginPanel.setSize(400, 350);
    }

    private void initializeMySQLPanel() {

        MySQL_Connect_Panel = new JPanel(null);
        MySQL_Connect_Panel.setSize(400, 350);

        JLabel username = new JLabel("Username:");
        username.setBounds(60, 150, 100, 30);
        MySQL_Connect_Panel.add(username);
        //Text area for entering username
        tfusername = new JTextField();
        tfusername.setBounds(150, 150, 200, 30);
        MySQL_Connect_Panel.add(tfusername);

        //Username text
        JLabel password = new JLabel("Password:");
        password.setBounds(60, 180, 100, 30);
        MySQL_Connect_Panel.add(password);
        //Text area for entering username
        tfpassword = new JPasswordField();
        tfpassword.setBounds(150, 180, 200, 30);
        MySQL_Connect_Panel.add(tfpassword);
        MySQL_Connect_Panel.setVisible(false);

        error = new JLabel("Invalid username or password");
        error.setBounds(100, 220, 200, 30);
        error.setForeground(Color.RED);
        error.setVisible(false);
        MySQL_Connect_Panel.add(error);

        connect = new JButton("Connect");
        connect.setBounds(150, 260, 100, 30);
        connect.addActionListener(this);
        MySQL_Connect_Panel.add(connect);
    }

    private void intializeStartPanel(){
        startPanel = new JPanel(null);

        JLabel heading1 = new JLabel("Welcome to");
        heading1.setFont(new Font("LucidaGrande", Font.BOLD, 20));
        heading1.setBounds(140, 20, 120, 20);
        startPanel.add(heading1);

        JLabel heading2 = new JLabel("University Management System");
        heading2.setFont(new Font("LucidaGrande", Font.BOLD, 20));
        heading2.setBounds(30, 40, 340, 25);
        startPanel.add(heading2);

        JLabel info1 = new JLabel("-> If you don't have database setup");
        info1.setFont(new Font("LucidaGrande", Font.PLAIN, 17));
        info1.setBounds(30, 100, 340, 20);
        startPanel.add(info1);

        JLabel info2 = new JLabel("locally click ");
        info2.setFont(new Font("LucidaGrande", Font.PLAIN, 17));
        info2.setBounds(60, 120, 100, 20);
        startPanel.add(info2);

        JLabel info3 = new JLabel("Make UniDB");
        info3.setFont(new Font("LucidaGrande", Font.ITALIC, 17));
        info3.setForeground(Color.RED);
        info3.setBounds(160, 120, 100, 20);
        startPanel.add(info3);

        JLabel info4 = new JLabel(", else");
        info4.setBounds(260, 120, 100, 20);
        info4.setFont(new Font("LucidaGrande", Font.PLAIN, 17));
        startPanel.add(info4);

        JLabel info5 = new JLabel("select ");
        info5.setFont(new Font("LucidaGrande", Font.PLAIN, 17));
        info5.setBounds(60, 140, 60, 20);
        startPanel.add(info5);

        JLabel info6 = new JLabel("connect to DB.");
        info6.setFont(new Font("LucidaGrande", Font.ITALIC, 17));
        info6.setForeground(Color.RED);
        info6.setBounds(115, 140, 120, 20);
        startPanel.add(info6);

        makeUniDB = new JButton("java.Database.MakeUniDB");
        makeUniDB.setBounds(50, 220, 100, 30);
        makeUniDB.addActionListener(this);
        startPanel.add(makeUniDB);

        connectToDB = new JButton("Connect");
        connectToDB.setBounds(250, 220, 100, 30);
        connectToDB.addActionListener(this);
        startPanel.add(connectToDB);

        startPanel.setSize(400, 350);
    }

    public static void main(String[] args) {
        new Login();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == cancel) {
            setVisible(false);
        } else if(e.getSource() == login) {
            String username = tfusername.getText();
            String password = tfpassword.getText();

            String query = "SELECT * FROM login WHERE username = '" + username + "' and password = '" + password + "'";

            try {
                ResultSet result = c.stmt.executeQuery(query);
                if(result.next()) {
                    setVisible(false);
                    new MainScreen(c);
                    dispose();
                } else {
                    tfusername.setText("");
                    tfpassword.setText("");
                    error.setVisible(true);
                }
            } catch(Exception ex) {
                ex.printStackTrace();
            }
        } else if(e.getSource() == MySQL) {

            String username = tfusername.getText();
            String password = tfpassword.getText();

            MySQL_Connect_Panel.setVisible(! MySQL_Connect_Panel.isVisible());
        } else if(e.getSource() == Derby_DB) {
            try {
                c = new Conn();
                JOptionPane.showMessageDialog(null, "Database connected SUCCESSFULLY :)");
                DBPanel.setVisible(false);
                initializeLoginPanel();
                this.add(loginPanel);
                loginPanel.setVisible(true);
            } catch(Exception ec) {
                if(ec.getMessage().contains("not found")){
                    JOptionPane.showMessageDialog(null, "Database not created!!");
                } else{
                    JOptionPane.showMessageDialog(null, "Derby DB library not found");
                }
            }
        } else if(e.getSource() == connect) {
            String username = tfusername.getText();
            String password = tfpassword.getText();

            try {
                c = new Conn(username, password);
                JOptionPane.showMessageDialog(null, "Database connected SUCCESSFULLY :)");
                initializeLoginPanel();
                this.add(loginPanel);
                DBPanel.setVisible(false);
                MySQL_Connect_Panel.setVisible(false);
                loginPanel.setVisible(true);
            } catch(SQLException ex) {
                if(ex.getMessage().contains("Unknown database")){
                    JOptionPane.showMessageDialog(null, "Database not created!!");
                } else{
                    error.setVisible(true);
                }
                tfpassword.setText("");
                tfusername.setText("");
            } catch(ClassNotFoundException ec) {
                JOptionPane.showMessageDialog(null, "MySQL library not found");
            }
        } else if(e.getSource() == makeUniDB){
            startPanel.setVisible(false);
            new MakeUniDB();
            DBPanel.setVisible(true);
        } else if(e.getSource() == connectToDB){
            startPanel.setVisible(false);
            DBPanel.setVisible(true);
        }
    }
}
