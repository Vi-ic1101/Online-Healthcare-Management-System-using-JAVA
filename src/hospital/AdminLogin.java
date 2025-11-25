package hospital;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.ResultSet;

public class AdminLogin extends JFrame implements ActionListener {
    JFrame f;
    JLabel l1, l2, l3, l4, l5;
    JTextField user1, iD;
    JPasswordField password1;
    JButton login, back;

    public AdminLogin() {
        f = new JFrame("Admin Login Page");
        f.setBackground(Color.WHITE);
        f.setLayout(null);
        f.setSize(800, 570);
        f.setLocation(300, 100);
        f.setVisible(true);
        f.setResizable(false);

        l1 = new JLabel();
        l1.setBounds(0, 0, f.getWidth(), f.getHeight());
        l1.setLayout(null);

        ImageIcon img = new ImageIcon(ClassLoader.getSystemResource("hospital/icons/login.jpg"));
        Image i = img.getImage().getScaledInstance(f.getWidth(), f.getHeight(), Image.SCALE_SMOOTH);
        l1.setIcon(new ImageIcon(i));

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
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == back) {
            f.dispose();
            new Index();
        }

        if (ae.getSource() == login) {
            try {
                ConnectionClass obj = new ConnectionClass();
                String email = iD.getText();
                String password = password1.getText();

                String qB = "SELECT * FROM admin WHERE emailID='" + email + "' AND adminPassword='" + password + "'";
                ResultSet rs = obj.stm.executeQuery(qB);

                if (rs.next()) {
                    JOptionPane.showMessageDialog(f, "Login successful!");
                    f.setVisible(false);
                    // Redirect to Admin Dashboard (you can create a separate class)
                    new ProfileAdmin(email);
                } else {
                    JOptionPane.showMessageDialog(f, "Wrong email or password!");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        new AdminLogin();
    }
}
