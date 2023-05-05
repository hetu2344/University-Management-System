package java.Instructor;

import javax.swing.*;
import java.Database.Conn;
import java.awt.*;
import java.awt.event.*;
import java.sql.ResultSet;

public class UpdateInstructor extends JFrame implements ActionListener {

    Conn conn;

    Choice cEMPID;

    String empID;

    JTextField tdAddress, tfMobile, tfEmail, txtFaculty;
    JLabel lblSNumber, tfFirstName, tfMiddleName, tfLastName, tfDOB;
    JButton updateInst = new JButton("Update Instructor"), cancel = new JButton("Cancel"), select = new JButton("Select");

    public UpdateInstructor(String empID, Conn c) {
        this.conn = c;

        setLayout(null);

        initializeLabels();

        initializeButtons();

        initializeFrame();

        changeLabels(empID);

    }

    public UpdateInstructor(Conn c) {
        this.conn = c;

        setLayout(null);

        initializeLabels();

        initializeButtons();

        initializeFrame();

    }

    private void initializeFrame() {

        //Getting dimension of screen
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        final int WIDTH = screenSize.width, LENGTH = screenSize.height;

        //Setting location and size of window
        setTitle("Update Instructor Details");
        setResizable(false);
        setSize(775, 500);
        setLocation((WIDTH - 800) / 2, (LENGTH - 600) / 2);
        setVisible(true);
    }

    private void initializeButtons() {
        updateInst.setBounds(100, 410, 120, 40);
        updateInst.addActionListener(this);
        add(updateInst);

        select.setBounds(500, 20, 120, 30);
        select.addActionListener(this);
        add(select);

        cancel.setBounds(500, 410, 120, 40);
        cancel.addActionListener(this);
        add(cancel);
    }

    private void initializeLabels() {
        JLabel selectNo = new JLabel("Select by Employee ID");
        selectNo.setBounds(20, 20, 180, 30);
        add(selectNo);

        cEMPID = new Choice();
        cEMPID.setBounds(210, 20, 150, 30);
        add(cEMPID);

        try {
            ResultSet rs = conn.stmt.executeQuery("SELECT empID FROM instructor");
            while(rs.next()) {
                cEMPID.add(rs.getString("empID"));
            }
        } catch(Exception ex) {
            ex.printStackTrace();
        }

        JLabel FirstName = new JLabel("First Name");
        FirstName.setBounds(50, 60, 100, 30);
        FirstName.setFont(new Font("serif", Font.BOLD, 20));
        add(FirstName);

        tfFirstName = new JLabel();
        tfFirstName.setFont(new Font("serif", Font.PLAIN, 20));
        tfFirstName.setBounds(175, 60, 100, 30);
        add(tfFirstName);

        JLabel middleName = new JLabel("Middle Name");
        middleName.setBounds(425, 60, 130, 30);
        middleName.setFont(new Font("serif", Font.BOLD, 20));
        add(middleName);

        tfMiddleName = new JLabel();
        tfMiddleName.setFont(new Font("serif", Font.PLAIN, 20));
        tfMiddleName.setBounds(570, 60, 100, 30);
        add(tfMiddleName);

        JLabel lastName = new JLabel("Last Name");
        lastName.setBounds(50, 120, 130, 30);
        lastName.setFont(new Font("serif", Font.BOLD, 20));
        add(lastName);

        tfLastName = new JLabel();
        tfLastName.setFont(new Font("serif", Font.PLAIN, 20));
        tfLastName.setBounds(175, 120, 100, 30);
        add(tfLastName);

        JLabel txtNumber = new JLabel("Employee ID");
        txtNumber.setBounds(425, 120, 150, 30);
        txtNumber.setFont(new Font("serif", Font.BOLD, 20));
        add(txtNumber);

        lblSNumber = new JLabel();
        lblSNumber.setBounds(600, 120, 100, 30);
        lblSNumber.setFont(new Font("serif", Font.PLAIN, 20));
        add(lblSNumber);


        JLabel dob = new JLabel("Date of Birth");
        dob.setBounds(50, 180, 130, 30);
        dob.setFont(new Font("serif", Font.BOLD, 20));
        add(dob);

        tfDOB = new JLabel();
        tfDOB.setBounds(175, 180, 100, 30);
        add(tfDOB);


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
        tfMobile.setBounds(200, 250, 170, 30);
        add(tfMobile);

        JLabel faculty = new JLabel("Faculty");
        faculty.setBounds(425, 250, 100, 30);
        faculty.setFont(new Font("serif", Font.BOLD, 20));
        add(faculty);

        txtFaculty = new JTextField();
        txtFaculty.setBounds(510, 250, 170, 30);
        add(txtFaculty);


        JLabel email = new JLabel("E-mail");
        email.setBounds(50, 310, 130, 30);
        email.setFont(new Font("serif", Font.BOLD, 20));
        add(email);

        tfEmail = new JTextField();
        tfEmail.setBounds(200, 310, 170, 30);
        add(tfEmail);
    }

    private void changeLabels(String empID) {
        try {
            cEMPID.select(empID);
            String query = "SELECT * FROM instructor WHERE empID = '" + empID + "'";
            ResultSet rs = conn.stmt.executeQuery(query);
            rs.next();
            tfFirstName.setText(rs.getString(1));
            tfMiddleName.setText(rs.getString(2));
            tfLastName.setText(rs.getString(3));
            lblSNumber.setText(rs.getString(4));
            tfDOB.setText(rs.getString(5));
            tdAddress.setText(rs.getString(6));
            tfMobile.setText(rs.getString(7));
            tfEmail.setText(rs.getString(8));
            txtFaculty.setText(rs.getString(9));

        } catch(Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == updateInst) {
            String empNumber = lblSNumber.getText();
            String address = tdAddress.getText();
            String mobile = tfMobile.getText();
            String email = tfEmail.getText();
            String faculty = txtFaculty.getText();
            try {

                String query = "UPDATE instructor set address = '" + address + "', mobile = '" + mobile + "', email = '" + email + "', faculty = '" + faculty + "' WHERE empID = '" + empNumber + "'";

                conn.stmt.executeUpdate(query);

                JOptionPane.showMessageDialog(null, "Instructor details updated successfully");

                tdAddress.setText("");
                tfMobile.setText("");
                txtFaculty.setText("");
                tfEmail.setText("");

            } catch(Exception ex) {
                ex.printStackTrace();
            }

        } else if(e.getSource() == select) {
            tdAddress.setText("");
            tfMobile.setText("");
            tfEmail.setText("");
            txtFaculty.setText("");

            this.empID = cEMPID.getSelectedItem();

            changeLabels(empID);
        } else if(e.getSource() == cancel) {
            setVisible(false);
        }
    }
}
