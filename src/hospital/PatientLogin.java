package hospital;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

/**
 * PatientLogin
 * -----------------------------------
 * Handles login UI and authentication for Patients.
 * Uses PreparedStatement for secure SQL, better GUI layout,
 * and proper OOP practices.
 */
public class PatientLogin extends JFrame implements ActionListener {

    private JFrame frame;
    private JLabel titleLabel, userLabel, emailLabel, passLabel, warnLabel, bgLabel;
    private JTextField userField, emailField;
    private JPasswordField passField;
    private JButton loginBtn, backBtn;

    public PatientLogin() {

        // ---------- FRAME SETUP ----------
        frame = new JFrame("Patient Login Page");
        frame.setSize(800, 570);
        frame.setLayout(null);
        frame.setLocation(300, 100);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // ---------- BACKGROUND ----------
        bgLabel = new JLabel();
        bgLabel.setBounds(0, 0, frame.getWidth(), frame.getHeight());
        bgLabel.setLayout(null);

        ImageIcon img = new ImageIcon(ClassLoader.getSystemResource("hospital/icons/login.jpg"));
        Image scaledImg = img.getImage().getScaledInstance(frame.getWidth(), frame.getHeight(), Image.SCALE_SMOOTH);
        bgLabel.setIcon(new ImageIcon(scaledImg));

        // ---------- TITLE ----------
        titleLabel = new JLabel("Patient Login Page");
        titleLabel.setBounds(220, 30, 500, 50);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 30));
        frame.add(titleLabel);

        // ---------- USERNAME ----------
        userLabel = new JLabel("Username:");
        userLabel.setBounds(150, 120, 200, 30);
        userLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        frame.add(userLabel);

        userField = new JTextField();
        userField.setBounds(300, 120, 200, 30);
        frame.add(userField);

        // ---------- EMAIL ----------
        emailLabel = new JLabel("Email ID:");
        emailLabel.setBounds(150, 170, 200, 30);
        emailLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        frame.add(emailLabel);

        emailField = new JTextField();
        emailField.setBounds(300, 170, 200, 30);
        frame.add(emailField);

        // ---------- PASSWORD ----------
        passLabel = new JLabel("Password:");
        passLabel.setBounds(150, 220, 200, 30);
        passLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        frame.add(passLabel);

        passField = new JPasswordField();
        passField.setBounds(300, 220, 200, 30);
        frame.add(passField);

        // ---------- WARNING LABEL ----------
        warnLabel = new JLabel();
        warnLabel.setBounds(150, 260, 500, 25);
        warnLabel.setFont(new Font("Arial", Font.PLAIN, 15));
        warnLabel.setForeground(Color.RED);
        frame.add(warnLabel);

        // ---------- LOGIN BUTTON ----------
        loginBtn = new JButton("Login");
        loginBtn.setBounds(150, 300, 120, 35);
        loginBtn.addActionListener(this);
        frame.add(loginBtn);

        // ---------- BACK BUTTON ----------
        backBtn = new JButton("Back");
        backBtn.setBounds(300, 300, 120, 35);
        backBtn.addActionListener(this);
        frame.add(backBtn);

        frame.setVisible(true);
    }

    // ===========================================================
    // ACTION HANDLING
    // ===========================================================
    @Override
    public void actionPerformed(ActionEvent ae) {

        // -------- BACK BUTTON --------
        if (ae.getSource() == backBtn) {
            frame.dispose();
            new Index();
            return;
        }

        // -------- LOGIN BUTTON --------
        if (ae.getSource() == loginBtn) {

            String username = userField.getText().trim();
            String email = emailField.getText().trim();
            String password = String.valueOf(passField.getPassword()).trim();

            // Validate fields
            if (username.isEmpty() || email.isEmpty() || password.isEmpty()) {
                warnLabel.setText("Please enter username, email, and password!");
                return;
            }

            try {
                ConnectionClass db = new ConnectionClass();
                Connection con = db.getConnection();

                // Use PreparedStatement for secure login
                String sql = "SELECT * FROM patient WHERE patientName=? AND emailId=? AND patientPassword=?";
                PreparedStatement pstmt = con.prepareStatement(sql);
                pstmt.setString(1, username);
                pstmt.setString(2, email);
                pstmt.setString(3, password);

                ResultSet rs = pstmt.executeQuery();

                if (rs.next()) {
                    // Successful login
                    frame.dispose();
                    new ProfilePatient(username);

                } else {
                    warnLabel.setText("Incorrect username, email, or password!");
                }

                pstmt.close();
                db.closeConnection();

            } catch (Exception e) {
                e.printStackTrace();
                warnLabel.setText("Database error!");
            }
        }
    }

    public static void main(String[] args) {
        new PatientLogin();
    }
}
