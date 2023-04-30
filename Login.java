import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class Login extends JFrame implements ActionListener {
    JButton cancel, login;
    JPasswordField tfpassword;
    JTextField tfusername;

    public Login() {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        final int WIDTH = screenSize.width, LENGTH = screenSize.height;

        //Adding the background image
        ImageIcon background = new ImageIcon(ClassLoader.getSystemResource("icons/second.jpg"));
        //Scaling the Image
        Image i2 = background.getImage().getScaledInstance(50, 50, Image.SCALE_DEFAULT);
        background = new ImageIcon(i2);
        JLabel image = new JLabel(background);
        image.setBounds(160, 150, 50, 50);
        add(image);

        //Username text
        JLabel username = new JLabel("Username:");
        username.setBounds(30, 20, 100, 20);
        add(username);
        //Text area for entering username
        tfusername = new JTextField();
        tfusername.setBounds(150, 20, 200, 20);
        add(tfusername);

        //Username text
        JLabel password = new JLabel("Password:");
        password.setBounds(30, 50, 100, 20);
        add(password);
        //Text area for entering username
        tfpassword = new JPasswordField();
        tfpassword.setBounds(150, 50, 200, 20);
        add(tfpassword);

        //Adding buttons for login and cancel
        login = new JButton("Login");
        login.setBounds(90, 90, 70, 35);

        cancel = new JButton("Cancel");
        cancel.setBounds(210, 90, 70, 35);

        login.addActionListener(this);
        cancel.addActionListener(this);
        add(login);
        add(cancel);
        //Setting some default functions for Frame
        setLayout(null);
        setResizable(false);
        //        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Login");

        //Showing the Frame

        //To change the default frame open location from top left to desired one
        setLocation(WIDTH / 3 + WIDTH / 20, LENGTH / 4);
        setSize(400, 250);
        setVisible(true);
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

            String querry = "SELECT * FROM login WHERE username = '" + username + "' and password = '" + password + "';";

            try {
                Conn c = new Conn();
                ResultSet result = c.stmt.executeQuery(querry);
                if(result.next()) {
                    setVisible(false);
                    new MainScreen();
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(null, "Invalid Username or Password");
                    tfusername.setText("");
                    tfpassword.setText("");
                }
            } catch(Exception ex) {
                ex.printStackTrace();
            }

        }
    }
}
