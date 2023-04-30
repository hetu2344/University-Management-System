import net.proteanit.sql.DbUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

public class ViewInstructorInfo extends JFrame implements ActionListener {
    Choice cEMPID;
    JTable table;
    JButton search, print, update, add, cancel, showAll;

    public ViewInstructorInfo() {
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

        update = new JButton("Update");
        update.setBounds(430, 70, 80, 30);
        update.addActionListener(this);
        add(update);

        add = new JButton("Add");
        add.setBounds(580, 70, 80, 30);
        add.addActionListener(this);
        add(add);

        cancel = new JButton("Cancel");
        cancel.setBounds(730, 70, 80, 30);
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
        } else if(e.getSource() == update) {
            setVisible(false);
            new UpdateInstructor();
        } else if(e.getSource() == search) {
            String query = "SELECT * FROM instructor WHERE empID = '" + cEMPID.getSelectedItem() + "';";
            try {
                Conn c = new Conn();
                ResultSet rs = c.stmt.executeQuery(query);
                table.setModel(DbUtils.resultSetToTableModel(rs));
            } catch(Exception ex) {
                ex.printStackTrace();
            }
        } else if(e.getSource() == add) {
            setVisible(false);
            new AddInstructor();
        } else if(e.getSource() == cancel) {
            setVisible(false);
        } else if(e.getSource() == showAll) {
            try {
                Conn c = new Conn();
                ResultSet rs = c.stmt.executeQuery("SELECT * FROM instructor");
                table.setModel(DbUtils.resultSetToTableModel(rs));
            } catch(Exception ex) {
                ex.printStackTrace();
            }
        }
    }
}
