package Instructor;

import net.proteanit.sql.DbUtils;

import javax.swing.*;
import Database.Conn;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

public class InstructorLeaveDetails extends JFrame implements ActionListener {
    private Choice cEMPID;
    private JTable table;
    private JButton search, print, cancel, showAll;

    private Conn conn;

    public InstructorLeaveDetails(Conn c) {

        this.conn = c;

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        final int WIDTH = screenSize.width, LENGTH = screenSize.height;
        setLayout(null);

        JLabel heading = new JLabel("Search by Employee Number");
        heading.setBounds(20, 20, 180, 30);
        add(heading);

        cEMPID = new Choice();
        cEMPID.setBounds(210, 20, 150, 30);
        add(cEMPID);

        try {
            ResultSet rs = c.stmt.executeQuery("SELECT empID FROM instLeave");
            while(rs.next()) {
                cEMPID.add(rs.getString("empID"));
            }
        } catch(Exception ex) {
            ex.printStackTrace();
        }

        table = new JTable();
        JScrollPane jsp = new JScrollPane(table);
        table.setBounds(0, 100, 900, 600);
        add(table);
        add(jsp);


        initialiseButtons();

        setSize(900, 700);
        setLocation((WIDTH - 900) / 2, (LENGTH - 700) / 2);
        setVisible(true);
    }

    private void initialiseButtons() {
        search = new JButton("Search");
        search.setBounds(20, 70, 80, 30);
        search.addActionListener(this);
        add(search);

        showAll = new JButton("Show All");
        showAll.setBounds(150, 70, 80, 30);
        showAll.addActionListener(this);
        add(showAll);

        print = new JButton("Print");
        print.setBounds(280, 70, 80, 30);
        print.addActionListener(this);
        add(print);

        cancel = new JButton("Cancel");
        cancel.setBounds(430, 70, 80, 30);
        cancel.addActionListener(this);
        add(cancel);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == print) {
            try {
                table.print();
            } catch(Exception ex) {
                ex.printStackTrace();
            }
        } else if(e.getSource() == search) {
            String query = "SELECT * FROM instLeave WHERE empID = '" + cEMPID.getSelectedItem() + "';";
            try {
                ResultSet rs = conn.stmt.executeQuery(query);
                table.setModel(DbUtils.resultSetToTableModel(rs));
            } catch(Exception ex) {
                ex.printStackTrace();
            }
        } else if(e.getSource() == cancel) {
            setVisible(false);
        } else if(e.getSource() == showAll) {
            try {
                ResultSet rs = conn.stmt.executeQuery("SELECT * FROM instLeave");
                table.setModel(DbUtils.resultSetToTableModel(rs));
            } catch(Exception ex) {
                ex.printStackTrace();
            }
        }
    }
}
