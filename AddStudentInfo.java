import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.ResultSet;
import java.util.*;

import com.toedter.calendar.JDateChooser;

public class AddStudentInfo extends JFrame implements ActionListener {

    Random ran = new Random();
    long last6 = Math.abs((ran.nextLong() % 900000L) + 100000L);

    JTextField tfFirstName, tfMiddleName, tfLastName, tdAddress, tfMobile, tfEmail;
    JLabel lblSNumber;
    JButton addStudent = new JButton("Add Student"), cancel = new JButton("Cancel"), clear = new JButton("Clear");
    static JDateChooser dtDob;
    JComboBox txtFaculty;

    private int getStudentNumber() {
        int studentNumber;
        try {
            Conn c = new Conn();
            ResultSet r = c.stmt.executeQuery("SELECT ");
        } catch(Exception e) {
            e.printStackTrace();
        }

        return 0;
    }

    public AddStudentInfo() {

        //Getting dimension of screen
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        final int WIDTH = screenSize.width, LENGTH = screenSize.height;

        setLayout(null);

        JLabel heading = new JLabel("New Student Details");
        heading.setBounds(275, 10, 250, 30);
        heading.setFont(new Font("serif", Font.BOLD, 25));
        add(heading);

        JLabel FirstName = new JLabel("First Name");
        FirstName.setBounds(50, 60, 100, 30);
        FirstName.setFont(new Font("serif", Font.BOLD, 20));
        add(FirstName);

        tfFirstName = new JTextField();
        tfFirstName.setBounds(175, 60, 100, 30);
        add(tfFirstName);

        JLabel middleName = new JLabel("Middle Name");
        middleName.setBounds(425, 60, 130, 30);
        middleName.setFont(new Font("serif", Font.BOLD, 20));
        add(middleName);

        tfMiddleName = new JTextField();
        tfMiddleName.setBounds(570, 60, 100, 30);
        add(tfMiddleName);

        JLabel lastName = new JLabel("Last Name");
        lastName.setBounds(50, 120, 130, 30);
        lastName.setFont(new Font("serif", Font.BOLD, 20));
        add(lastName);

        tfLastName = new JTextField();
        tfLastName.setBounds(175, 120, 100, 30);
        add(tfLastName);

        JLabel txtNumber = new JLabel("Student Number");
        txtNumber.setBounds(425, 120, 150, 30);
        txtNumber.setFont(new Font("serif", Font.BOLD, 20));
        add(txtNumber);

        lblSNumber = new JLabel("2344" + last6);
        lblSNumber.setBounds(590, 120, 100, 30);
        add(lblSNumber);


        JLabel dob = new JLabel("Date of Birth");
        dob.setBounds(50, 180, 130, 30);
        dob.setFont(new Font("serif", Font.BOLD, 20));
        add(dob);

        dtDob = new JDateChooser();
        dtDob.setBounds(175, 180, 150, 30);
        add(dtDob);

        JLabel address = new JLabel("Address");
        address.setBounds(425, 180, 100, 30);
        address.setFont(new Font("serif", Font.BOLD, 20));
        add(address);

        tdAddress = new JTextField();
        tdAddress.setBounds(510, 180, 200, 50);
        add(tdAddress);

        JLabel mobile = new JLabel("Phone Number");
        mobile.setBounds(50, 250, 130, 30);
        mobile.setFont(new Font("serif", Font.BOLD, 20));
        add(mobile);

        tfMobile = new JTextField();
        tfMobile.setBounds(175, 250, 150, 30);
        add(tfMobile);

        JLabel faculty = new JLabel("Faculty");
        faculty.setBounds(425, 250, 100, 30);
        faculty.setFont(new Font("serif", Font.BOLD, 20));
        add(faculty);

        String[] faculties = {"Science", "Engineering", "Humanities", "Social Work", "Arts", "Agriculture", "Architecture"};
        txtFaculty = new JComboBox(faculties);
        txtFaculty.setBounds(510, 240, 200, 50);
        add(txtFaculty);

        JLabel email = new JLabel("E-mail");
        email.setBounds(50, 310, 130, 30);
        email.setFont(new Font("serif", Font.BOLD, 20));
        add(email);

        tfEmail = new JTextField();
        tfEmail.setBounds(175, 310, 150, 30);
        add(tfEmail);

        addStudent.setBounds(100, 410, 120, 40);
        addStudent.addActionListener(this);
        add(addStudent);

        clear.setBounds(300, 410, 120, 40);
        clear.addActionListener(this);
        add(clear);

        cancel.setBounds(500, 410, 120, 40);
        cancel.addActionListener(this);
        add(cancel);
        //Setting location and size of window
        setResizable(false);
        setSize(775, 500);
        setLocation((WIDTH - 800) / 2, (LENGTH - 600) / 2);
        setVisible(true);
    }

    public static void main(String[] args) {
        new AddStudentInfo();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == addStudent) {
            String FName = tfFirstName.getText();
            String MName = tfMiddleName.getText();
            String LName = tfLastName.getText();
            String studentNumber = lblSNumber.getText();
            String dob = ((JTextField) (dtDob.getDateEditor().getUiComponent())).getText();
            String address = tdAddress.getText();
            String mobile = tfMobile.getText();
            String email = tfEmail.getText();
            String faculty = (String) (txtFaculty.getSelectedItem());
            try{
                String query = "INSERT INTO student values('" + FName + "', '" + MName + "', '" + LName + "', '" + studentNumber + "', '" + dob + "', '" + address + "', '" + mobile + "', '" + email + "', '" + faculty + "');";
                Conn c = new Conn();
                c.stmt.executeUpdate(query);

                JOptionPane.showMessageDialog(null, "Student details added successfully");

                tfFirstName.setText("");
                tfMiddleName.setText("");
                tfLastName.setText("");
                tdAddress.setText("");
                tfMobile.setText("");
                tfEmail.setText("");

            } catch(Exception ex){
                ex.printStackTrace();
            }

        } else if(e.getSource() == clear){
            tfFirstName.setText("");
            tfMiddleName.setText("");
            tfLastName.setText("");
            tdAddress.setText("");
            tfMobile.setText("");
            tfEmail.setText("");
        } else if (e.getSource() == cancel){
            setVisible(false);
        }
    }
}
