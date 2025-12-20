
        package hospital;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Index extends JFrame implements ActionListener {

    JLabel bgLabel, title1, title2, title3;
    JButton bDoctor, bPatient, bAdmin, bPharmacist, bReceptionist, bBack;

    public Index() {

        setTitle("Index Page");
        setSize(800, 570);
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        // Background Image
        ImageIcon bg = new ImageIcon(ClassLoader.getSystemResource("hospital/icons/main_image.jpg"));
        Image scaled = bg.getImage().getScaledInstance(800, 570, Image.SCALE_SMOOTH);
        bgLabel = new JLabel(new ImageIcon(scaled));
        bgLabel.setBounds(0, 0, 800, 570);
        bgLabel.setLayout(null);
        add(bgLabel);

        // Text Headings
        title1 = new JLabel("HealthCare Management System");
        title1.setBounds(50, 200, 700, 50);
        title1.setFont(new Font("Arial", Font.BOLD, 30));
        title1.setForeground(Color.WHITE);
        bgLabel.add(title1);

        title3 = new JLabel("Administrator Access Page");
        title3.setBounds(20, 50, 500, 50);
        title3.setFont(new Font("Arial", Font.BOLD, 25));
        title3.setForeground(Color.WHITE);
        bgLabel.add(title3);

        title2 = new JLabel("Project using JAVA");
        title2.setBounds(70, 250, 700, 50);
        title2.setFont(new Font("Arial", Font.BOLD, 25));
        title2.setForeground(Color.WHITE);
        bgLabel.add(title2);

        // Buttons
        bDoctor = new JButton("Doctor");
        bDoctor.setBounds(50, 330, 150, 40);
        styleButton(bDoctor);
        bDoctor.addActionListener(this);
        bgLabel.add(bDoctor);

        bPatient = new JButton("Patient");
        bPatient.setBounds(250, 330, 150, 40);
        styleButton(bPatient);
        bPatient.addActionListener(this);
        bgLabel.add(bPatient);

        bAdmin = new JButton("Admin");
        bAdmin.setBounds(50, 390, 150, 40);
        styleButton(bAdmin);
        bAdmin.addActionListener(this);
        bgLabel.add(bAdmin);

        bPharmacist = new JButton("Pharmacist");
        bPharmacist.setBounds(250, 390, 150, 40);
        styleButton(bPharmacist);
        bPharmacist.addActionListener(this);
        bgLabel.add(bPharmacist);

        bReceptionist = new JButton("Receptionist");
        bReceptionist.setBounds(150, 450, 150, 40);
        styleButton(bReceptionist);
        bReceptionist.addActionListener(this);
        bgLabel.add(bReceptionist);

        // Back button to MainPage
        bBack = new JButton("Back");
        bBack.setBounds(650, 20, 100, 30);
        styleButton(bBack);
        bBack.addActionListener(this);
        bgLabel.add(bBack);

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

        if (e.getSource() == bDoctor) {
            dispose();
            new DoctorLogin();
        }
        else if (e.getSource() == bPatient) {
            dispose();
            new PatientLogin();
        }
        else if (e.getSource() == bAdmin) {
            dispose();
            new AdminLogin();
        }
        else if (e.getSource() == bPharmacist) {
            dispose();
            new PharmacistLogin();
        }
        else if (e.getSource() == bReceptionist) {
            dispose();
            new ReceptionistLogin();
        }
        else if (e.getSource() == bBack) {
            dispose();
            new MainPage();   // go back to main page
        }
    }

    public static void main(String[] args) {
        new Index();
    }
}