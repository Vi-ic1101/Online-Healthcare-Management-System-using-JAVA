package hospital;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class PatientLogin extends JFrame implements ActionListener {
    JFrame f;
    JLabel lTitle, lUser, lEmail, lPassword, lWarn;
    JTextField tUser, tEmail;
    JPasswordField tPassword;
    JButton login, back;

    public PatientLogin() {
        f = new JFrame("Patient Login Page");
        f.setBackground(Color.WHITE);
        f.setLayout(null);

        f.setSize(800, 570);
        f.setLocation(300, 100);
        f.setResizable(false);

        lTitle = new JLabel("Patient Login Page");
        lTitle.setBounds(220, 30, 500, 50);
        lTitle.setFont(new Font("Arial", Font.BOLD, 30));
        lTitle.setForeground(Color.BLACK);
        f.add(lTitle);

        // Username
        lUser = new JLabel("Username:");
        lUser.setBounds(150, 120, 200, 30);
        lUser.setFont(new Font("Arial", Font.PLAIN, 20));
        f.add(lUser);

        tUser = new JTextField();
        tUser.setBounds(300, 120, 200, 30);
        f.add(tUser);

        // Email
        lEmail = new JLabel("Email ID:");
        lEmail.setBounds(150, 170, 200, 30);
        lEmail.setFont(new Font("Arial", Font.PLAIN, 20));
        f.add(lEmail);

        tEmail = new JTextField();
        tEmail.setBounds(300, 170, 200, 30);
        f.add(tEmail);

        // Password
        lPassword = new JLabel("Password:");
        lPassword.setBounds(150, 220, 200, 30);
        lPassword.setFont(new Font("Arial", Font.PLAIN, 20));
        f.add(lPassword);

        tPassword = new JPasswordField();
        tPassword.setBounds(300, 220, 200, 30);
        f.add(tPassword);

        // Warning label
        lWarn = new JLabel();
        lWarn.setBounds(150, 260, 400, 25);
        lWarn.setFont(new Font("Arial", Font.PLAIN, 12));
        lWarn.setForeground(Color.RED);
        f.add(lWarn);

        // Buttons
        login = new JButton("Login");
        login.setBounds(150, 300, 120, 35);
        login.addActionListener(this);
        f.add(login);

        back = new JButton("Back");
        back.setBounds(300, 300, 120, 35);
        back.addActionListener(this);
        f.add(back);

        f.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == back) {
            f.setVisible(false);
            new Index();
        }

        if (ae.getSource() == login) {
            String username = tUser.getText();
            String email = tEmail.getText();
            String password = new String(tPassword.getPassword());

            if (username.isEmpty() || email.isEmpty() || password.isEmpty()) {
                lWarn.setText("Please enter username, email, and password!");
                return;
            }

            try {
                ConnectionClass obj = new ConnectionClass();
                String query = "SELECT * FROM hospital_management_system.patient " +
                        "WHERE patientName='" + username + "' " +
                        "AND emailId='" + email + "' " +
                        "AND patientPassword='" + password + "'";
                ResultSet rs = obj.stm.executeQuery(query);

                if (rs.next()) {
                    f.setVisible(false);
                    new ProfilePatient(username);
                } else {
                    lWarn.setText("Incorrect username, email, or password!");
                }
            } catch (Exception e) {
                e.printStackTrace();
                lWarn.setText("Database error!");
            }
        }
    }

    public static void main(String[] args) {
        new PatientLogin();
    }
}
