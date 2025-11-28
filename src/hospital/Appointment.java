package hospital;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.*;

/**
 * Appointment Page
 * -----------------------
 * Displays all appointments for a specific doctor.
 * Shows doctor info, patient info, date/time, and disease description.
 * Uses JTable + DefaultTableModel for clean tabular display.
 */
public class Appointment extends JFrame {

    JTable table;              // Table to display appointments
    DefaultTableModel model;   // Table model to store row data

    /**
     * Constructor
     * @param doctorId The specific doctor's ID whose appointments must be displayed.
     */
    public Appointment(int doctorId) {

        setTitle("Doctor Appointments");
        setSize(900, 500);
        setLocation(300, 100);
        setLayout(new BorderLayout());
        setResizable(false);

        // ---------------------------
        // TABLE COLUMN HEADERS
        // ---------------------------
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

        // Creating model + table
        model = new DefaultTableModel(columnNames, 0);
        table = new JTable(model);

        JScrollPane sp = new JScrollPane(table);
        add(sp, BorderLayout.CENTER);

        // Load all appointment data for this doctor
        loadAppointmentData(doctorId);

        // BACK BUTTON
        JButton back = new JButton("Back");
        back.addActionListener(e -> {
            setVisible(false);
            new DoctorLogin();     // Go back to doctor login page
        });

        add(back, BorderLayout.SOUTH);

        setVisible(true);
    }


    /**
     * Load all appointments related to the given doctor ID.
     * Fetches data by joining appointment, doctor, and patient tables.
     */
    private void loadAppointmentData(int doctorId) {

        try {
            ConnectionClass obj = new ConnectionClass();

            // SQL JOIN to fetch a complete appointment record
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

            // Add each row to the table model
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

    /**
     * Main Method (for testing)
     */
    public static void main(String[] args) {
        new Appointment(1); // Run by passing doctorId manually
    }
}
