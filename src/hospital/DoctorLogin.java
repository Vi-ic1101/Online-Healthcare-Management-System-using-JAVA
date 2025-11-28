package hospital;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

/**
 * DoctorLogin
 * -----------------------------------
 * Handles login UI and authentication for Doctors.
 * Uses PreparedStatement for security,
 * improved layout, OOP, and better readability.
 */
public class DoctorLogin extends JFrame implements ActionListener {

    private JFrame frame;
    private JLabel titleLabel, emailLabel, userLabel, passLabel, bgLabel;
    private JTextField emailField, userField;
    private JPasswordField passField;
    private JButton loginBtn, backBtn;

    public DoctorLogin() {

        // ---------- FRAME SETUP ----------
        frame = new JFrame("Doctor Login Page");
        frame.setSize(800, 570);
        frame.setLocation(300, 100);
        frame.setLayout(null);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // ---------- BACKGROUND IMAGE ----------
        bgLabel = new JLabel();
        bgLabel.setBounds(0, 0, frame.getWidth(), frame.getHeight());
        bgLabel.setLayout(null);

        ImageIcon img = new ImageIcon(ClassLoader.getSystemResource("hospital/icons/login.jpg"));
        Image scaledImg = img.getImage().getScaledInstance(frame.getWidth(), frame.getHeight(), Image.SCALE_SMOOTH);
        bgLabel.setIcon(new ImageIcon(scaledImg));

        // ---------- TITLE ----------
        titleLabel = new JLabel("Doctor Login Page");
        titleLabel.setBounds(200, 30, 500, 50);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 32));
        titleLabel.setForeground(Color.BLACK);
        bgLabel.add(titleLabel);

        // ---------- EMAIL ----------
        emailLabel = new JLabel("Email ID:");
        emailLabel.setBounds(180, 180, 300, 40);
        emailLabel.setFont(new Font("Arial", Font.BOLD, 26));
        bgLabel.add(emailLabel);

        emailField = new JTextField();
        emailField.setBounds(380, 185, 230, 30);
        bgLabel.add(emailField);

        // ---------- USERNAME ----------
        userLabel = new JLabel("User Name:");
        userLabel.setBounds(180, 240, 300, 40);
        userLabel.setFont(new Font("Arial", Font.BOLD, 26));
        bgLabel.add(userLabel);

        userField = new JTextField();
        userField.setBounds(380, 245, 230, 30);
        bgLabel.add(userField);

        // ---------- PASSWORD ----------
        passLabel = new JLabel("Password:");
        passLabel.setBounds(180, 295, 300, 40);
        passLabel.setFont(new Font("Arial", Font.BOLD, 26));
        bgLabel.add(passLabel);

        passField = new JPasswordField();
        passField.setBounds(380, 300, 230, 30);
        bgLabel.add(passField);

        // ---------- LOGIN BUTTON ----------
        loginBtn = new JButton("Login");
        loginBtn.setBounds(180, 380, 180, 40);
        loginBtn.addActionListener(this);
        bgLabel.add(loginBtn);

        // ---------- BACK BUTTON ----------
        backBtn = new JButton("Previous");
        backBtn.setBounds(430, 380, 180, 40);
        backBtn.addActionListener(this);
        bgLabel.add(backBtn);

        frame.add(bgLabel);
        frame.setVisible(true);
    }


    // ===========================================================
    // ACTION HANDLING
    // ===========================================================
    @Override
    public void actionPerformed(ActionEvent ae) {

        // -------- BACK BUTTON --------
        if (ae.getSource() == backBtn) {
            frame.setVisible(false);
            new Index();
            return;
        }

        // -------- LOGIN BUTTON --------
        if (ae.getSource() == loginBtn) {

            String email = emailField.getText().trim();
            String username = userField.getText().trim();
            String password = String.valueOf(passField.getPassword()).trim();

            if (email.isEmpty() || username.isEmpty() || password.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "All fields are required!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            try {
                ConnectionClass db = new ConnectionClass();
                Connection con = db.getConnection();

                // Secure SQL query (no injection)
                String sql = "SELECT * FROM doctor WHERE emailId=? AND doctorName=? AND doctorPassword=?";
                PreparedStatement stmt = con.prepareStatement(sql);

                stmt.setString(1, email);
                stmt.setString(2, username);
                stmt.setString(3, password);

                ResultSet rs = stmt.executeQuery();

                if (rs.next()) {
                    // Successful login
                    new ProfileDoctor(email);
                    frame.setVisible(false);

                } else {
                    JOptionPane.showMessageDialog(frame,
                            "Wrong Email, Username, or Password!",
                            "Login Failed",
                            JOptionPane.ERROR_MESSAGE);
                }

                stmt.close();
                db.closeConnection();

            } catch (Exception e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(frame, "Error connecting to database", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public static void main(String[] args) {
        new DoctorLogin();
    }
}
