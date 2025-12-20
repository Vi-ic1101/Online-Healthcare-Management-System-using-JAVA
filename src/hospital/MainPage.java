package hospital;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainPage extends JFrame implements ActionListener {

    JLabel bgLabel, title1, title2;
    JButton bLogin, bRegister;

    public MainPage() {
        setTitle("Main Page");
        setSize(800, 570);
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        // Background Image (same path style as Index)
        ImageIcon bg = new ImageIcon(ClassLoader.getSystemResource("hospital/icons/main_image.jpg"));
        Image scaled = bg.getImage().getScaledInstance(800, 570, Image.SCALE_SMOOTH);
        bgLabel = new JLabel(new ImageIcon(scaled));
        bgLabel.setBounds(0, 0, 800, 570);
        bgLabel.setLayout(null);
        add(bgLabel);

        // Headings
        title1 = new JLabel("HealthCare Management System");
        title1.setBounds(50, 200, 700, 50);
        title1.setFont(new Font("Arial", Font.BOLD, 30));
        title1.setForeground(Color.WHITE);
        bgLabel.add(title1);

        title2 = new JLabel("Welcome - Choose an option");
        title2.setBounds(70, 250, 700, 50);
        title2.setFont(new Font("Arial", Font.BOLD, 25));
        title2.setForeground(Color.WHITE);
        bgLabel.add(title2);

        // Buttons
        bLogin = new JButton("Login");
        bLogin.setBounds(50, 350, 150, 40);
        styleButton(bLogin);
        bLogin.addActionListener(this);
        bgLabel.add(bLogin);

        bRegister = new JButton("Register");
        bRegister.setBounds(250, 350, 150, 40);
        styleButton(bRegister);
        bRegister.addActionListener(this);
        bgLabel.add(bRegister);

        // Center window
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void styleButton(JButton btn) {
        btn.setBackground(Color.DARK_GRAY);
        btn.setForeground(Color.ORANGE);
        btn.setFocusPainted(false);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == bLogin) {
            dispose();
            // Open index (doctor/patient/admin/pharmacist/receptionist menu)
            new Index();
        } else if (e.getSource() == bRegister) {
            dispose();
            // Open registration page; RegistrationPage already sends user to Index after success
            new RegistrationPage();
        }
    }

    public static void main(String[] args) {
        new MainPage();
    }
}
