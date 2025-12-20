package hospital;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class ReceptionistLogin extends JFrame implements ActionListener {

    private JFrame f;
    private JLabel l1, l2, l4, l5;
    private JTextField emailField;
    private JPasswordField passwordField;
    private JButton login, back;

    public ReceptionistLogin() {

        f = new JFrame("Receptionist Login Page");
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

        l2 = new JLabel("Receptionist Login");
        l2.setBounds(220, 30, 400, 50);
        l2.setFont(new Font("Arial", Font.BOLD, 30));
        l1.add(l2);

        l5 = new JLabel("Email:");
        l5.setBounds(200, 200, 150, 40);
        l5.setFont(new Font("Arial", Font.BOLD, 22));
        l1.add(l5);

        emailField = new JTextField();
        emailField.setBounds(350, 210, 200, 30);
        l1.add(emailField);

        l4 = new JLabel("Password:");
        l4.setBounds(200, 260, 150, 40);
        l4.setFont(new Font("Arial", Font.BOLD, 22));
        l1.add(l4);

        passwordField = new JPasswordField();
        passwordField.setBounds(350, 270, 200, 30);
        l1.add(passwordField);

        login = new JButton("Login");
        login.setBounds(200, 340, 150, 35);
        login.addActionListener(this);
        l1.add(login);

        back = new JButton("Back");
        back.setBounds(400, 340, 150, 35);
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

            String email = emailField.getText().trim();
            String password = new String(passwordField.getPassword());

            if (email.isEmpty() || password.isEmpty()) {
                JOptionPane.showMessageDialog(f, "Please enter email and password!");
                return;
            }

            try {
                ReceptionistDAO dao = new ReceptionistDAO();
                Receptionist receptionist = dao.authenticate(email, password);

                if (receptionist != null) {
                    JOptionPane.showMessageDialog(f, "Login successful!");
                    f.setVisible(false);
                    new ReceptionistDashboard(email);

                } else {
                    JOptionPane.showMessageDialog(f, "Invalid email or password!");
                }

            } catch (Exception e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(f, "Database error!");
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(ReceptionistLogin::new);
    }

    // ========================
    // Receptionist POJO
    // ========================
    public static class Receptionist {
        private final int id;
        private final String name;
        private final String email;

        public Receptionist(int id, String name, String email) {
            this.id = id;
            this.name = name;
            this.email = email;
        }

        public int getId() { return id; }
        public String getName() { return name; }
        public String getEmail() { return email; }
    }

    // ========================
    // Receptionist DAO
    // ========================
    public static class ReceptionistDAO {

        public Receptionist authenticate(String email, String password) throws SQLException {

            ConnectionClass obj = new ConnectionClass();
            Connection con = obj.con;

            String sql =
                    "SELECT receptionistId, receptionistName, emailID " +
                            "FROM receptionist " +
                            "WHERE emailID = ? AND receptionistPassword = ?";

            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, email);
            ps.setString(2, password);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return new Receptionist(
                        rs.getInt("receptionistId"),
                        rs.getString("receptionistName"),
                        rs.getString("emailID")
                );
            }
            return null;
        }
    }
}
