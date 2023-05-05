package Interface;

import Database.Conn;
import Instructor.*;
import Student.*;


import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MainScreen extends JFrame implements ActionListener {

    Conn conn;

    public MainScreen(Conn c) {

        this.conn = c;

        //Getting dimensions of screen
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        final int WIDTH = screenSize.width, LENGTH = screenSize.height;

        //Adding the background image
        ImageIcon background = new ImageIcon("icons/third.jpg");
        //Scaling the Image
        Image i2 = background.getImage().getScaledInstance(WIDTH, LENGTH - 100, Image.SCALE_DEFAULT);
        background = new ImageIcon(i2);
        JLabel image = new JLabel(background);
        add(image);

        //Menu bar for Main screen
        JMenuBar menu = new JMenuBar();

        //New Information menu Item
        JMenu newInfo = new JMenu("New Information");
        menu.add(newInfo);

        JMenuItem facultyInfo = new JMenuItem("Add new Faculty Information");
        facultyInfo.addActionListener(this);
        newInfo.add(facultyInfo);

        JMenuItem studInfo = new JMenuItem("Add new Student Information");
        studInfo.addActionListener(this);
        newInfo.add(studInfo);

        //View Details menu item
        JMenu viewDetails = new JMenu("Details");
        menu.add(viewDetails);

        JMenuItem facultyDetails = new JMenuItem("View Faculty Details");
        facultyDetails.addActionListener(this);
        viewDetails.add(facultyDetails);

        JMenuItem studDetails = new JMenuItem("View Student Details");
        studDetails.addActionListener(this);
        viewDetails.add(studDetails);

        //Leave menu item
        JMenu leave = new JMenu("Apply Leave");
        menu.add(leave);

        JMenuItem facultyLeaves = new JMenuItem("Faculty Leave");
        facultyLeaves.addActionListener(this);
        leave.add(facultyLeaves);

        JMenuItem studLeaves = new JMenuItem("Student Leave");
        studLeaves.addActionListener(this);
        leave.add(studLeaves);

        JMenu leaveDetails = new JMenu("Leave Details");
        menu.add(leaveDetails);

        JMenuItem facultyLeaveDetails = new JMenuItem("Faculty Leave Details");
        facultyLeaveDetails.addActionListener(this);
        leaveDetails.add(facultyLeaveDetails);

        JMenuItem studLeaveDetails = new JMenuItem("Student Leave Details");
        studLeaveDetails.addActionListener(this);
        leaveDetails.add(studLeaveDetails);

        //Update menu item
        JMenu update = new JMenu("Update");
        menu.add(update);

        JMenuItem facultyUpdate = new JMenuItem("Update Faculty Information");
        facultyUpdate.addActionListener(this);
        update.add(facultyUpdate);

        JMenuItem studentUpdate = new JMenuItem("Update Student Information");
        studentUpdate.addActionListener(this);
        update.add(studentUpdate);

        //Exit menu item
        JMenu close = new JMenu("Exit");
        menu.add(close);

        JMenuItem exit = new JMenuItem("Exit");
        exit.addActionListener(this);
        close.add(exit);


        setJMenuBar(menu);

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(screenSize);
        setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String msg = e.getActionCommand();
        switch(msg) {
            case "Exit" -> {
                setVisible(false);
                dispose();
            }
            case "Add new Student Information" -> new AddStudentInfo(conn);
            case "Add new Faculty Information" -> new AddInstructor(conn);
            case "View Faculty Details" -> new ViewInstructorInfo(conn);
            case "View Student Details" -> new ViewStudentInfo(conn);
            case "Faculty Leave" -> new InstructorLeave(conn);
            case "Student Leave" -> new StudentLeave(conn);
            case "Faculty Leave Details" -> new InstructorLeaveDetails(conn);
            case "Student Leave Details" -> new StudentLeaveDetails(conn);
            case "Update Faculty Information" -> new UpdateInstructor(conn);
            case "Update Student Information" -> new UpdateStudent(conn);
        }
    }
}
