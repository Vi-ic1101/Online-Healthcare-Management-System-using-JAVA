package hospital;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

/**
 * Refactored AdminLogin:
 * - Uses AdminDAO for DB access (PreparedStatement + try-with-resources)
 * - Uses SwingWorker to perform login off the EDT
 * - Basic Admin model included
 *
 * Note: AdminDAO is implemented as an inner static class for convenience.
 * You can move it to its own file (recommended for larger projects).
 */
public class AdminLogin extends JFrame implements ActionListener {
    private JFrame f;
    private JLabel l1, l2, l3, l4, l5;
    private JTextField user1, iD;
    private JPasswordField password1;
    private JButton login, back;

    public AdminLogin() {
        f = new JFrame("Admin Login Page");
        f.setBackground(Color.WHITE);
        f.setLayout(null);
        f.setSize(800, 570);
        f.setLocation(300, 100);
        f.setResizable(false);

        l1 = new JLabel();
        l1.setBounds(0, 0, f.getWidth(), f.getHeight());
        l1.setLayout(null);

        ImageIcon img = new ImageIcon(ClassLoader.getSystemResource("hospital/icons/login.jpg"));
        if (img != null) {
            Image i = img.getImage().getScaledInstance(f.getWidth(), f.getHeight(), Image.SCALE_SMOOTH);
            l1.setIcon(new ImageIcon(i));
        }

        l2 = new JLabel("Admin Login Page");
        l2.setBounds(200, 30, 500, 50);
        l2.setFont(new Font("Arial", Font.BOLD, 30));
        l2.setForeground(Color.black);
        l1.add(l2);

        l3 = new JLabel("User Name: ");
        l3.setBounds((f.getWidth() / 2) - 200, (f.getHeight() / 2) - 100, 500, 50);
        l3.setFont(new Font("Arial", Font.BOLD, 30));
        l3.setForeground(Color.black);
        l1.add(l3);

        user1 = new JTextField();
        user1.setBounds(l3.getX() + 200, l3.getY() + 10, 200, 30);
        l1.add(user1);

        l5 = new JLabel("Email id: ");
        l5.setBounds(l3.getX(), l3.getY() + 75, 500, 50);
        l5.setFont(new Font("Arial", Font.BOLD, 30));
        l5.setForeground(Color.black);
        l1.add(l5);

        iD = new JTextField();
        iD.setBounds(l5.getX() + 200, l5.getY() + 10, 200, 30);
        l1.add(iD);

        l4 = new JLabel("Password: ");
        l4.setBounds(l5.getX(), l5.getY() + 50, 500, 50);
        l4.setFont(new Font("Arial", Font.BOLD, 30));
        l4.setForeground(Color.black);
        l1.add(l4);

        password1 = new JPasswordField();
        password1.setBounds(user1.getX(), l4.getY() + 10, 200, 30);
        l1.add(password1);

        login = new JButton("Login");
        login.setBounds(l4.getX(), l4.getY() + 100, 190, 35);
        login.setBackground(new Color(255, 255, 255, 180));
        login.setForeground(Color.BLACK);
        login.addActionListener(this);
        l1.add(login);

        back = new JButton("Previous");
        back.setBounds(password1.getX(), login.getY(), 190, 35);
        back.setBackground(new Color(255, 255, 255, 180));
        back.setForeground(Color.BLACK);
        back.addActionListener(this);
        l1.add(back);

        f.add(l1);
        f.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == back) {
            f.dispose();
            new Index();
            return;
        }

        if (ae.getSource() == login) {
            // Basic validation
            final String username = user1.getText().trim();
            final String email = iD.getText().trim();
            final String password = new String(password1.getPassword());

            if (username.isEmpty() || email.isEmpty() || password.isEmpty()) {
                JOptionPane.showMessageDialog(f, "Please enter username, email and password.");
                return;
            }

            // Use SwingWorker to avoid blocking the EDT
            login.setEnabled(false);
            back.setEnabled(false);
            new SwingWorker<Admin, Void>() {
                private Exception error = null;

                @Override
                protected Admin doInBackground() {
                    try {
                        AdminDAO dao = new AdminDAO();
                        return dao.authenticate(email, password, username);
                    } catch (Exception ex) {
                        error = ex;
                        return null;
                    }
                }

                @Override
                protected void done() {
                    login.setEnabled(true);
                    back.setEnabled(true);
                    if (error != null) {
                        error.printStackTrace();
                        JOptionPane.showMessageDialog(f, "Login error: " + error.getMessage());
                        return;
                    }
                    try {
                        Admin admin = get();
                        if (admin != null) {
                            JOptionPane.showMessageDialog(f, "Login successful!");
                            f.setVisible(false);
                            // Redirect to ProfileAdmin with admin email
                            new ProfileAdmin(admin.getEmail());
                        } else {
                            JOptionPane.showMessageDialog(f, "Wrong email, username, or password!");
                        }
                    } catch (Exception ex) {
                        ex.printStackTrace();
                        JOptionPane.showMessageDialog(f, "Unexpected error during login.");
                    }
                }
            }.execute();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(AdminLogin::new);
    }

    // -------------------------
    // Admin model (simple POJO)
    // -------------------------
    public static class Admin {
        private final int id;
        private final String name;
        private final String email;

        public Admin(int id, String name, String email) {
            this.id = id;
            this.name = name;
            this.email = email;
        }

        public int getId() { return id; }
        public String getName() { return name; }
        public String getEmail() { return email; }
    }

    // -------------------------
    // AdminDAO (small, safe, uses PreparedStatement)
    // -------------------------
    public static class AdminDAO {

        /**
         * Authenticate admin by email, password and username.
         * Returns Admin object if credentials match; otherwise null.
         */
        public Admin authenticate(String email, String password, String username) throws SQLException {
            // Using your ConnectionClass which exposes `con` field.
            ConnectionClass connObj = new ConnectionClass();
            Connection conn = connObj.con;
            if (conn == null) throw new SQLException("Unable to obtain DB connection.");

            String sql = "SELECT adminId, adminName, emailID FROM admin WHERE emailID = ? AND adminPassword = ? AND adminName = ?";

            try (PreparedStatement pst = conn.prepareStatement(sql)) {
                pst.setString(1, email);
                pst.setString(2, password);
                pst.setString(3, username);

                try (ResultSet rs = pst.executeQuery()) {
                    if (rs.next()) {
                        int id = rs.getInt("adminId");
                        String name = rs.getString("adminName");
                        String mail = rs.getString("emailID");
                        return new Admin(id, name, mail);
                    } else {
                        return null;
                    }
                }
            } finally {
                // Do not close connObj.con here because ConnectionClass currently manages it.
                // If you refactor ConnectionClass to provide/close connections per-call, close here.
            }
        }
    }


}
