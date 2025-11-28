package hospital;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class ProfilePatient extends JFrame implements ActionListener {

    JFrame f;
    JLabel titleLabel, idLabel, nameLabel, emailLabel, phoneLabel, addressLabel;
    JLabel idValue, nameValue, emailValue, phoneValue, addressValue;
    JButton bookAppointment, logout;
    String emailId;  // safer unique value

    public ProfilePatient(String emailId) {
        this.emailId = emailId;

        f = new JFrame("Patient Profile");
        f.setSize(700, 500);
        f.setLocation(350, 150);
        f.setLayout(null);
        f.setResizable(false);

        titleLabel = new JLabel("Patient Profile");
        titleLabel.setBounds(220, 20, 300, 40);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 28));
        f.add(titleLabel);

        // Labels
        idLabel = new JLabel("Patient ID:");
        idLabel.setBounds(100, 80, 150, 30);
        f.add(idLabel);

        idValue = new JLabel();
        idValue.setBounds(250, 80, 300, 30);
        f.add(idValue);

        nameLabel = new JLabel("Name:");
        nameLabel.setBounds(100, 120, 150, 30);
        f.add(nameLabel);

        nameValue = new JLabel();
        nameValue.setBounds(250, 120, 300, 30);
        f.add(nameValue);

        emailLabel = new JLabel("Email:");
        emailLabel.setBounds(100, 160, 150, 30);
        f.add(emailLabel);

        emailValue = new JLabel();
        emailValue.setBounds(250, 160, 300, 30);
        f.add(emailValue);

        phoneLabel = new JLabel("Phone:");
        phoneLabel.setBounds(100, 200, 150, 30);
        f.add(phoneLabel);

        phoneValue = new JLabel();
        phoneValue.setBounds(250, 200, 300, 30);
        f.add(phoneValue);

        addressLabel = new JLabel("Address:");
        addressLabel.setBounds(100, 240, 150, 30);
        f.add(addressLabel);

        addressValue = new JLabel();
        addressValue.setBounds(250, 240, 300, 30);
        f.add(addressValue);

        // Buttons
        bookAppointment = new JButton("Book Appointment");
        bookAppointment.setBounds(150, 350, 180, 40);
        bookAppointment.addActionListener(this);
        f.add(bookAppointment);

        logout = new JButton("Logout");
        logout.setBounds(370, 350, 180, 40);
        logout.addActionListener(this);
        f.add(logout);

        fetchPatientData();

        f.setVisible(true);
    }

    // =============================
    // Fetch Patient Data (SAFE)
    // =============================
    private void fetchPatientData() {
        try {
            ConnectionClass obj = new ConnectionClass();

            String query = "SELECT * FROM hospital_management_system.patient WHERE emailId = ?";
            PreparedStatement pstmt = obj.con.prepareStatement(query);
            pstmt.setString(1, emailId);

            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                idValue.setText(String.valueOf(rs.getInt("patientId")));
                nameValue.setText(rs.getString("patientName"));
                emailValue.setText(rs.getString("emailId"));
                phoneValue.setText(rs.getString("phone"));
                addressValue.setText(rs.getString("address"));
            } else {
                JOptionPane.showMessageDialog(f, "Patient data not found!");
            }

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(f, "Database error!");
        }
    }

    @Override
    public void actionPerformed(ActionEvent ae) {

        if (ae.getSource() == logout) {
            f.setVisible(false);
            new PatientLogin();
        }

        if (ae.getSource() == bookAppointment) {
            f.setVisible(false);
            new AppointmentBooking(emailId);  // pass email as unique identifier
        }
    }

    public static void main(String[] args) {
        new ProfilePatient("test@mail.com");
    }
}
