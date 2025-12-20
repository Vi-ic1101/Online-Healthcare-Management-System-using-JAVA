package hospital;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class ProfilePatient extends JFrame implements ActionListener {

    JLabel bgLabel;
    JLabel idValue, nameValue, emailValue, phoneValue, addressValue;
    JButton bookAppointment, logout;

    String emailId, patientName;

    public ProfilePatient(String emailId) {
        this.emailId = emailId;

        setTitle("Patient Profile");
        setSize(800, 550);
        setLayout(null);
        setLocation(350, 150);
        setResizable(false);

        // ---------- BACKGROUND (SAME AS LOGIN) ----------
        bgLabel = new JLabel();
        bgLabel.setBounds(0, 0, getWidth(), getHeight());
        bgLabel.setLayout(null);

        ImageIcon img = new ImageIcon(
                ClassLoader.getSystemResource("hospital/icons/profilePage.jpg")
        );
        Image scaledImg = img.getImage().getScaledInstance(
                getWidth(), getHeight(), Image.SCALE_SMOOTH
        );
        bgLabel.setIcon(new ImageIcon(scaledImg));
        add(bgLabel);

        JLabel idLabel = new JLabel("Patient ID:");
        idLabel.setBounds(150, 80, 110, 30);
        idLabel.setFont(new Font("Arial", Font.BOLD, 14));

        idLabel.setHorizontalAlignment(SwingConstants.CENTER); //  CENTER TEXT
        idLabel.setVerticalAlignment(SwingConstants.CENTER);
        idLabel.setOpaque(true);

        idLabel.setForeground(Color.black);
        idLabel.setBackground(Color.white);
        bgLabel.add(idLabel);

        JLabel nameLabel = new JLabel("Name:");
        nameLabel.setBounds(
                idLabel.getX(),
                idLabel.getY() + 40,
                idLabel.getWidth(),
                30
        );
        nameLabel.setHorizontalAlignment(SwingConstants.CENTER); //  CENTER TEXT
        nameLabel.setVerticalAlignment(SwingConstants.CENTER);
        nameLabel.setOpaque(true);

        nameLabel.setForeground(Color.black);
        nameLabel.setBackground(Color.white);
        nameLabel.setFont(new Font("Arial", Font.BOLD, 14));
        bgLabel.add(nameLabel);

        JLabel emailLabel = new JLabel("Email:");
        emailLabel.setBounds(
                idLabel.getX(),
                nameLabel.getY() + 40,
                nameLabel.getWidth(),
                30
        );
        emailLabel.setFont(new Font("Arial", Font.BOLD, 14));
        emailLabel.setHorizontalAlignment(SwingConstants.CENTER); //  CENTER TEXT
        emailLabel.setVerticalAlignment(SwingConstants.CENTER);
        emailLabel.setOpaque(true);

        emailLabel.setForeground(Color.black);
        emailLabel.setBackground(Color.white);
        bgLabel.add(emailLabel);

        JLabel phoneLabel = new JLabel("Phone:");
        phoneLabel.setBounds(
                idLabel.getX(),
                emailLabel.getY() + 40,
                emailLabel.getWidth(),
                30
        );

        phoneLabel.setFont(new Font("Arial", Font.BOLD, 14));
        phoneLabel.setHorizontalAlignment(SwingConstants.CENTER); //  CENTER TEXT
        phoneLabel.setVerticalAlignment(SwingConstants.CENTER);
        phoneLabel.setOpaque(true);
        phoneLabel.setForeground(Color.black);
        phoneLabel.setBackground(Color.white);
        bgLabel.add(phoneLabel);

        JLabel addressLabel = new JLabel("Address:");
        addressLabel.setBounds(
                idLabel.getX(),
                phoneLabel.getY() + 40,
                phoneLabel.getWidth(),
                30
        );
        addressLabel.setHorizontalAlignment(SwingConstants.CENTER); //  CENTER TEXT
        addressLabel.setVerticalAlignment(SwingConstants.CENTER);
        addressLabel.setOpaque(true);

        addressLabel.setForeground(Color.black);
        addressLabel.setBackground(Color.white);
        addressLabel.setFont(new Font("Arial", Font.BOLD, 14));
        bgLabel.add(addressLabel);

// ---------- VALUE LABELS (RELATIVE TO LABELS) ----------
        int valueX = idLabel.getX() + 150;

        idValue = new JLabel();
        idValue.setBounds(valueX, idLabel.getY(), 250, 30);
        idValue.setOpaque(true);
        idValue.setForeground(Color.black);
        idValue.setBackground(Color.white);

        bgLabel.add(idValue);

        nameValue = new JLabel();
        nameValue.setBounds(valueX, nameLabel.getY(), 250, 30);
        nameValue.setOpaque(true);
        nameValue.setForeground(Color.black);
        nameValue.setBackground(Color.white);
        bgLabel.add(nameValue);

        emailValue = new JLabel();
        emailValue.setBounds(valueX, emailLabel.getY(), 250, 30);
        emailValue.setOpaque(true);
        emailValue.setBackground(Color.white);
        emailValue.setForeground(Color.black);
        bgLabel.add(emailValue);

        phoneValue = new JLabel();
        phoneValue.setBounds(valueX, phoneLabel.getY(), 250, 30);
        phoneValue.setOpaque(true);
        phoneValue.setForeground(Color.black);
        phoneValue.setBackground(Color.white);
        bgLabel.add(phoneValue);

        addressValue = new JLabel();
        addressValue.setBounds(valueX, addressLabel.getY(), 250, 30);
        addressValue.setOpaque(true);
        addressValue.setForeground(Color.black);
        addressValue.setBackground(Color.white);
        bgLabel.add(addressValue);


        bookAppointment = new JButton("Book Appointment");
        bookAppointment.setBounds(150, 350, 180, 40);
        bookAppointment.addActionListener(this);
        bgLabel.add(bookAppointment);

        logout = new JButton("Logout");
        logout.setBounds(370, 350, 180, 40);
        logout.addActionListener(this);
        bgLabel.add(logout);

        fetchPatientData();
        setVisible(true);
    }

    private JLabel label(String t, int x, int y) {
        JLabel l = new JLabel(t);
        l.setBounds(x, y, 150, 30);
        l.setForeground(Color.BLACK);
        return l;
    }

    private JLabel value(int x, int y) {
        JLabel l = new JLabel();
        l.setBounds(x, y, 300, 30);
        l.setForeground(Color.BLACK);
        return l;
    }

    private void fetchPatientData() {
        try {
            ConnectionClass obj = new ConnectionClass();
            PreparedStatement ps = obj.con.prepareStatement(
                    "SELECT * FROM patient WHERE emailId=?"
            );
            ps.setString(1, emailId);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                patientName = rs.getString("patientName");
                idValue.setText(rs.getString("patientId"));
                nameValue.setText(patientName);
                emailValue.setText(rs.getString("emailId"));
                phoneValue.setText(rs.getString("phone"));
                addressValue.setText(rs.getString("address"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == logout) {
            dispose();
            new PatientLogin();
        }

        if (ae.getSource() == bookAppointment) {
            dispose();
            new AppointmentBooking(emailId, patientName);
        }
    }

    public static void main(String[] args) {
        new ProfilePatient("email.com");
    }
}
