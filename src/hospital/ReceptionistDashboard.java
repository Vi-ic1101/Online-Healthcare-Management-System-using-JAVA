package hospital;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ReceptionistDashboard extends JFrame implements ActionListener {

    JFrame f;
    JLabel titleLabel, welcomeLabel;

    JButton viewAppointments;
    JButton addPatient;
    JButton searchPatient;
    JButton logout;

    String receptionistEmail;

    public ReceptionistDashboard(String receptionistEmail) {
        this.receptionistEmail = receptionistEmail;

        f = new JFrame("Receptionist Dashboard");
        f.setSize(700, 500);
        f.setLocation(350, 150);
        f.setLayout(null);
        f.setResizable(false);

        titleLabel = new JLabel("Receptionist Dashboard");
        titleLabel.setBounds(180, 30, 400, 40);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 30));
        f.add(titleLabel);

        welcomeLabel = new JLabel("Logged in as: " + receptionistEmail);
        welcomeLabel.setBounds(200, 80, 350, 25);
        f.add(welcomeLabel);

        viewAppointments = new JButton("View Appointments");
        viewAppointments.setBounds(250, 140, 200, 40);
        viewAppointments.addActionListener(this);
        f.add(viewAppointments);

        addPatient = new JButton("Register New Patient");
        addPatient.setBounds(250, 200, 200, 40);
        addPatient.addActionListener(this);
        f.add(addPatient);

        searchPatient = new JButton("Search Patient");
        searchPatient.setBounds(250, 260, 200, 40);
        searchPatient.addActionListener(this);
        f.add(searchPatient);

        logout = new JButton("Logout");
        logout.setBounds(250, 320, 200, 40);
        logout.addActionListener(this);
        f.add(logout);

        f.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {

        if (ae.getSource() == viewAppointments) {
            new ViewAppointments(); // create this next
        }

        if (ae.getSource() == addPatient) {
            new AddPatient(); // receptionist can register patients
        }

        if (ae.getSource() == searchPatient) {
            new SearchPatient(); // receptionist can search patients
        }

        if (ae.getSource() == logout) {
            f.dispose();
            new ReceptionistLogin();
        }
    }

    public static void main(String[] args) {
        new ReceptionistDashboard("anita@gmail.com");
    }
}
