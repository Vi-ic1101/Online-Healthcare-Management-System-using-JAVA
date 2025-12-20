package hospital;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class PatientLogin extends JFrame implements ActionListener {

    JLabel titleLabel, userLabel, emailLabel, passLabel, warnLabel, bgLabel;
    JTextField userField, emailField;
    JPasswordField passField;
    JButton loginBtn, backBtn;

    public PatientLogin() {

        setTitle("Patient Login Page");
        setSize(800, 570);
        setLayout(null);
        setLocation(300, 100);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        bgLabel = new JLabel();
        bgLabel.setBounds(0, 0, getWidth(), getHeight());
        bgLabel.setLayout(null);

        ImageIcon img = new ImageIcon(ClassLoader.getSystemResource("hospital/icons/login.jpg"));
        Image scaledImg = img.getImage().getScaledInstance(getWidth(), getHeight(), Image.SCALE_SMOOTH);
        bgLabel.setIcon(new ImageIcon(scaledImg));
        add(bgLabel);

        titleLabel = new JLabel("Patient Login Page");
        titleLabel.setBounds(220, 30, 500, 50);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 30));
        bgLabel.add(titleLabel);

        userLabel = new JLabel("Username:");
        userLabel.setBounds(225, 180, 200, 40);
        userLabel.setFont(new Font("Arial", Font.BOLD, 25));
        bgLabel.add(userLabel);

        userField = new JTextField();
        userField.setBounds(userLabel.getX() + 150, userLabel.getY(), 200, 30);
        bgLabel.add(userField);

        emailLabel = new JLabel("Email ID:");
        emailLabel.setBounds(userLabel.getX(), userLabel.getY() + 50, 200, 40);
        emailLabel.setFont(new Font("Arial", Font.BOLD, 25));
        bgLabel.add(emailLabel);

        emailField = new JTextField();
        emailField.setBounds(userField.getX(), userField.getY() + 50, 200, 30);
        bgLabel.add(emailField);

        passLabel = new JLabel("Password:");
        passLabel.setBounds(emailLabel.getX(), emailLabel.getY() + 50, 200, 40);
        passLabel.setFont(new Font("Arial", Font.BOLD, 20));
        bgLabel.add(passLabel);

        passField = new JPasswordField();
        passField.setBounds(emailField.getX(), emailField.getY() + 50, 200, 30);
        bgLabel.add(passField);

        warnLabel = new JLabel();
        warnLabel.setBounds(150, 260, 500, 25);
        warnLabel.setForeground(Color.RED);
        bgLabel.add(warnLabel);

        loginBtn = new JButton("Login");
        loginBtn.setBounds(250, 350, 120, 35);
        loginBtn.addActionListener(this);
        bgLabel.add(loginBtn);

        backBtn = new JButton("Back");
        backBtn.setBounds(loginBtn.getX() + 150, loginBtn.getY(), 120, 35);
        backBtn.addActionListener(this);
        bgLabel.add(backBtn);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {

        if (ae.getSource() == backBtn) {
            dispose();
            new Index();
            return;
        }

        if (ae.getSource() == loginBtn) {

            String username = userField.getText().trim();
            String email = emailField.getText().trim();
            String password = String.valueOf(passField.getPassword()).trim();

            if (username.isEmpty() || email.isEmpty() || password.isEmpty()) {
                warnLabel.setText("Please enter all fields!");
                return;
            }

            try {
                ConnectionClass obj = new ConnectionClass();

                String sql =
                        "SELECT emailId FROM hospital_management_system.patient " +
                                "WHERE patientName=? AND emailId=? AND patientPassword=?";

                PreparedStatement ps = obj.con.prepareStatement(sql);
                ps.setString(1, username);
                ps.setString(2, email);
                ps.setString(3, password);

                ResultSet rs = ps.executeQuery();

                if (rs.next()) {
                    dispose();
                    new ProfilePatient(email);
                } else {
                    warnLabel.setText("Invalid login credentials!");
                }

            } catch (Exception e) {
                e.printStackTrace();
                warnLabel.setText("Database error!");
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(PatientLogin::new);
    }
}
