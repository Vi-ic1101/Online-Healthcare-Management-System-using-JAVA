package hospital;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.*;

public class Appointment extends JFrame {

    JTable table;
    DefaultTableModel model;

    public Appointment(int doctorId) {

        setTitle("Doctor Appointments");
        setSize(900, 500);
        setLocation(300, 100);
        setLayout(new BorderLayout());
        setResizable(false);

        // Table Columns
        String[] columnNames = {
                "Appointment ID",
                "Doctor ID",
                "Doctor Name",
                "Patient ID",
                "Patient Name",
                "Date",
                "Time",
                "Disease / Description"
        };

        model = new DefaultTableModel(columnNames, 0);
        table = new JTable(model);
        JScrollPane sp = new JScrollPane(table);

        add(sp, BorderLayout.CENTER);

        loadAppointmentData(doctorId);

        JButton back = new JButton("Back");
        back.addActionListener(e -> {
            setVisible(false);

            // Get emailId for going back (optional)
            new DoctorLogin();
        });

        add(back, BorderLayout.SOUTH);

        setVisible(true);
    }


    // ============================================
    // ✔ LOAD APPOINTMENTS FOR THIS DOCTOR
    // ============================================
    private void loadAppointmentData(int doctorId) {

        try {
            ConnectionClass obj = new ConnectionClass();

            String q =
                    "SELECT a.appointmentId, " +
                            "d.doctorId, d.doctorName, " +
                            "p.patientId, p.patientName, " +
                            "a.appointmentDate, a.appointmentTime, a.disease " +
                            "FROM appointment a " +
                            "JOIN doctor d ON a.doctorId = d.doctorId " +
                            "JOIN patient p ON a.patientId = p.patientId " +
                            "WHERE a.doctorId = " + doctorId;

            ResultSet rs = obj.stm.executeQuery(q);

            while (rs.next()) {
                Object[] row = {
                        rs.getInt("appointmentId"),
                        rs.getInt("doctorId"),
                        rs.getString("doctorName"),

                        rs.getInt("patientId"),
                        rs.getString("patientName"),

                        rs.getString("appointmentDate"),
                        rs.getString("appointmentTime"),
                        rs.getString("disease")
                };
                model.addRow(row);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) {
        new Appointment(1); // Test manually by passing doctorId
    }
}
