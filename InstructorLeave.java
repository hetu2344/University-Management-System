import com.toedter.calendar.JDateChooser;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.ResultSet;
import java.util.*;

public class InstructorLeave extends JFrame implements ActionListener {
    Choice cEMPID, cTime;
    JTable table;
    JDateChooser dtDate;
    JButton submit, cancel;

    public InstructorLeave() {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        final int WIDTH = screenSize.width, LENGTH = screenSize.height;
        setLayout(null);

        JLabel heading = new JLabel("Instructor leave Application");
        heading.setBounds(100, 10, 300, 30);
        heading.setFont(new Font("Tahoma", Font.BOLD, 20));
        add(heading);

        JLabel student = new JLabel("Search by Employee Number");
        student.setBounds(20, 50, 180, 30);
        add(student);

        cEMPID = new Choice();
        cEMPID.setBounds(300, 50, 150, 30);
        add(cEMPID);

        try {
            Conn c = new Conn();
            ResultSet rs = c.stmt.executeQuery("SELECT empID FROM instructor");
            while(rs.next()) {
                cEMPID.add(rs.getString("empID"));
            }
        } catch(Exception ex) {
            ex.printStackTrace();
        }

        table = new JTable();
        JScrollPane jsp = new JScrollPane(table);
        table.setBounds(0, 200, 500, 550);
        add(table);
        add(jsp);

        JLabel date = new JLabel("Date:");
        date.setBounds(20, 90, 50, 30);
        date.setFont(new Font("serif", Font.BOLD, 20));
        add(date);

        dtDate = new JDateChooser();
        dtDate.setBounds(60, 90, 150, 30);
        add(dtDate);

        JLabel leaveTime = new JLabel("Time Duration");
        leaveTime.setBounds(20, 130, 180, 30);
        add(leaveTime);

        cTime = new Choice();
        cTime.add("Half Day");
        cTime.add("Full Day");
        cTime.setBounds(300, 130, 150, 30);
        add(cTime);

        submit = new JButton("Submit");
        submit.setBounds(125, 170, 100, 30);
        submit.addActionListener(this);
        add(submit);

        cancel = new JButton("Cancel");
        cancel.setBounds(300, 170, 100, 30);
        cancel.addActionListener(this);
        add(cancel);

        setSize(500, 550);
        setLocation((WIDTH - 500) / 2, (LENGTH - 550) / 2);
        setVisible(true);
    }

    public static void main(String[] args) {
        new InstructorLeave();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == submit) {
            String empID = cEMPID.getSelectedItem();
            String date = ((JTextField) (dtDate.getDateEditor().getUiComponent())).getText();
            String duration = cTime.getSelectedItem();
            try {
                String query = "INSERT INTO instLeave values('" + empID + "', '" + date + "', '" + duration + "');";
                Conn c = new Conn();
                c.stmt.executeUpdate(query);

                JOptionPane.showMessageDialog(null, "Application Submitted");
            } catch(Exception ex) {
                ex.printStackTrace();
            }
        } else {
            setVisible(false);
        }
    }
}
