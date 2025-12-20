package hospital;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class PharmacistLogin extends JFrame implements ActionListener {

    private JFrame f;
    private JLabel l1, l2, l3, l4, l5;
    private JTextField user1, iD;
    private JPasswordField password1;
    private JButton login, back;

    public PharmacistLogin() {

        f = new JFrame("Pharmacist Login Page");
        f.setLayout(null);
        f.setSize(800, 570);
        f.setLocation(300, 100);
        f.setResizable(false);

        // ---------- BACKGROUND ----------
        l1 = new JLabel();
        l1.setBounds(0, 0, f.getWidth(), f.getHeight());
        l1.setLayout(null);

        ImageIcon img = new ImageIcon(
                ClassLoader.getSystemResource("hospital/icons/login.jpg")
        );
        Image i = img.getImage().getScaledInstance(
                f.getWidth(), f.getHeight(), Image.SCALE_SMOOTH
        );
        l1.setIcon(new ImageIcon(i));

        // ---------- TITLE ----------
        l2 = new JLabel("Pharmacist Login Page");
        l2.setBounds(170, 30, 550, 50);
        l2.setFont(new Font("Arial", Font.BOLD, 30));
        l2.setForeground(Color.BLACK);
        l1.add(l2);

        // ---------- USERNAME ----------
        l3 = new JLabel("User Name:");
        l3.setBounds((f.getWidth() / 2) - 200, (f.getHeight() / 2) - 100, 300, 40);
        l3.setFont(new Font("Arial", Font.BOLD, 25));
        l1.add(l3);

        user1 = new JTextField();
        user1.setBounds(l3.getX() + 200, l3.getY(), 200, 30);
        l1.add(user1);

        // ---------- EMAIL ----------
        l5 = new JLabel("Email ID:");
        l5.setBounds(l3.getX(), l3.getY() + 60, 300, 40);
        l5.setFont(new Font("Arial", Font.BOLD, 25));
        l1.add(l5);

        iD = new JTextField();
        iD.setBounds(user1.getX(), l5.getY(), 200, 30);
        l1.add(iD);

        // ---------- PASSWORD ----------
        l4 = new JLabel("Password:");
        l4.setBounds(l5.getX(), l5.getY() + 60, 300, 40);
        l4.setFont(new Font("Arial", Font.BOLD, 25));
        l1.add(l4);

        password1 = new JPasswordField();
        password1.setBounds(user1.getX(), l4.getY(), 200, 30);
        l1.add(password1);

        // ---------- BUTTONS ----------
        login = new JButton("Login");
        login.setBounds(l4.getX(), l4.getY() + 80, 180, 35);
        login.addActionListener(this);
        l1.add(login);

        back = new JButton("Previous");
        back.setBounds(user1.getX(), login.getY(), 180, 35);
        back.addActionListener(this);
        l1.add(back);

        f.add(l1);
        f.setVisible(true);
    }

    // =====================================================
    // ACTION HANDLER
    // =====================================================
    @Override
    public void actionPerformed(ActionEvent ae) {

        if (ae.getSource() == back) {
            f.dispose();
            new Index();
            return;
        }

        if (ae.getSource() == login) {

            String username = user1.getText().trim();
            String email = iD.getText().trim();
            String password = String.valueOf(password1.getPassword());

            if (username.isEmpty() || email.isEmpty() || password.isEmpty()) {
                JOptionPane.showMessageDialog(f, "Please fill all fields!");
                return;
            }

            try {
                Pharmacist pharmacist = authenticate(email, password, username);

                if (pharmacist != null) {
                    JOptionPane.showMessageDialog(f, "Login Successful!");
                    f.dispose();
                    new ProfilePharmacist(pharmacist.getEmail());
                } else {
                    JOptionPane.showMessageDialog(f, "Invalid credentials!");
                }

            } catch (Exception e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(f, "Database error!");
            }
        }
    }

    // =====================================================
    // AUTHENTICATION METHOD (FIXED)
    // =====================================================
    private Pharmacist authenticate(String email, String password, String username)
            throws SQLException {

        ConnectionClass obj = new ConnectionClass();
        Connection con = obj.con;

        String sql =
                "SELECT pharmacistId, pharmacistName, emailId " +
                        "FROM pharmacist " +
                        "WHERE emailId = ? AND password = ? AND pharmacistName = ?";

        PreparedStatement pst = con.prepareStatement(sql);
        pst.setString(1, email);
        pst.setString(2, password);
        pst.setString(3, username);

        ResultSet rs = pst.executeQuery();

        if (rs.next()) {
            return new Pharmacist(
                    rs.getInt("pharmacistId"),
                    rs.getString("pharmacistName"),
                    rs.getString("emailId")
            );
        }
        return null;
    }

    // =====================================================
    // PHARMACIST MODEL
    // =====================================================
    static class Pharmacist {
        private final int id;
        private final String name;
        private final String email;

        Pharmacist(int id, String name, String email) {
            this.id = id;
            this.name = name;
            this.email = email;
        }

        public int getId() { return id; }
        public String getName() { return name; }
        public String getEmail() { return email; }
    }

    // =====================================================
    // MAIN
    // =====================================================
    public static void main(String[] args) {
        SwingUtilities.invokeLater(PharmacistLogin::new);
    }
}
