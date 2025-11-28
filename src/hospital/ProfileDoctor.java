package hospital;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.*;

public class ProfileDoctor extends JFrame {

    JLabel lId, lName, lAddress, lDept, lPhone, lEmail;
    JLabel vId, vName, vAddress, vDept, vPhone, vEmail;
    int doctorId;

    public ProfileDoctor(String emailId) {
        setTitle("Doctor Profile");
        setSize(700, 600);
        setLocation(350, 120);
        setLayout(null);
        setResizable(false);

        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBounds(0, 0, 700, 600);
        panel.setBackground(new Color(230, 240, 255));

        JLabel title = new JLabel("Doctor Profile");
        title.setBounds(180, 20, 400, 40);
        title.setFont(new Font("Arial", Font.BOLD, 28));
        panel.add(title);

        lId = new JLabel("Doctor ID:");
        lId.setBounds(100, 80, 200, 25);
        panel.add(lId);

        lName = new JLabel("Name:");
        lName.setBounds(100, 120, 200, 25);
        panel.add(lName);

        lAddress = new JLabel("Address:");
        lAddress.setBounds(100, 160, 200, 25);
        panel.add(lAddress);

        lDept = new JLabel("Department:");
        lDept.setBounds(100, 200, 200, 25);
        panel.add(lDept);

        lPhone = new JLabel("Phone:");
        lPhone.setBounds(100, 240, 200, 25);
        panel.add(lPhone);

        lEmail = new JLabel("Email ID:");
        lEmail.setBounds(100, 280, 200, 25);
        panel.add(lEmail);

        // Value Labels
        vId = new JLabel();
        vId.setBounds(300, 80, 200, 25);
        panel.add(vId);

        vName = new JLabel();
        vName.setBounds(300, 120, 200, 25);
        panel.add(vName);

        vAddress = new JLabel();
        vAddress.setBounds(300, 160, 300, 25);
        panel.add(vAddress);

        vDept = new JLabel();
        vDept.setBounds(300, 200, 200, 25);
        panel.add(vDept);

        vPhone = new JLabel();
        vPhone.setBounds(300, 240, 200, 25);
        panel.add(vPhone);

        vEmail = new JLabel();
        vEmail.setBounds(300, 280, 250, 25);
        panel.add(vEmail);

        // Buttons
        JButton back = new JButton("Back");
        back.setBounds(150, 350, 120, 30);
        back.addActionListener(e -> {
            setVisible(false);
            new DoctorLogin();
        });
        panel.add(back);

        JButton appointmentBtn = new JButton("Appointments");
        appointmentBtn.setBounds(back.getX() + 150, 350, 140, 30);
        appointmentBtn.addActionListener(e -> showAppointments());
        panel.add(appointmentBtn);

        add(panel);
        setVisible(true);

        loadDoctorData(emailId);
    }

    // =======================================
    // Load doctor data via secure query
    // =======================================
    private void loadDoctorData(String emailId) {
        try {
            ConnectionClass obj = new ConnectionClass();

            String q = "SELECT * FROM doctor WHERE emailId = ?";
            PreparedStatement ps = obj.con.prepareStatement(q);
            ps.setString(1, emailId);

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {

                doctorId = rs.getInt("doctorId");

                vId.setText(String.valueOf(doctorId));
                vName.setText(rs.getString("doctorName"));
                vAddress.setText(rs.getString("address"));
                vDept.setText(rs.getString("doctorDepartment"));
                vPhone.setText(rs.getString("phone"));
                vEmail.setText(rs.getString("emailId"));

            } else {
                JOptionPane.showMessageDialog(this, "Doctor not found!");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    // =======================================
    // Show appointments in JTable
    // =======================================
    private void showAppointments() {

        JFrame frame = new JFrame("Appointments for Dr. " + vName.getText());
        frame.setSize(800, 400);
        frame.setLocationRelativeTo(null);

        String[] cols = {"Appointment ID", "Patient Name", "Department", "Date", "Time", "Status"};
        DefaultTableModel model = new DefaultTableModel(cols, 0);

        JTable table = new JTable(model);
        JScrollPane scroll = new JScrollPane(table);
        frame.add(scroll);

        try {
            ConnectionClass obj = new ConnectionClass();

            String query = "SELECT appointmentId, patientName, doctorDepartment, docAppointmentDate, docAppointmentTime, docAppointmentStatus FROM appointment WHERE doctorId = ?";
            PreparedStatement ps = obj.con.prepareStatement(query);
            ps.setInt(1, doctorId);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                model.addRow(new Object[]{
                        rs.getInt("appointmentId"),
                        rs.getString("patientName"),
                        rs.getString("doctorDepartment"),
                        rs.getString("docAppointmentDate"),
                        rs.getString("docAppointmentTime"),
                        rs.getString("docAppointmentStatus")
                });
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        frame.setVisible(true);
    }

    public static void main(String[] args) {
        new ProfileDoctor("test@mail.com");
    }
}
