package hospital;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.ArrayList;

/**
 * AppointmentBooking
 * -------------------
 * Allows a patient to book an appointment with a doctor.
 * Patient name is passed from ProfilePatient.
 */
public class AppointmentBooking extends JFrame implements ActionListener {

    JFrame f;

    // Labels
    JLabel titleLabel, deptLabel, doctorLabel, patientLabel, dateLabel, timeLabel;

    // Dropdowns
    JComboBox<String> deptBox, doctorBox, dateBox, timeBox;

    // Buttons
    JButton book, back;

    // Holds the name of the patient using this window
    String patientName;



    // =====================================================
    //  CONSTRUCTOR — SETS UP THE APPOINTMENT BOOKING WINDOW
    // =====================================================
    public AppointmentBooking(String patientName) {

        this.patientName = patientName;

        f = new JFrame("Book Appointment");
        f.setSize(700, 500);
        f.setLocation(350, 150);
        f.setLayout(null);
        f.setResizable(false);

        // Window Title
        titleLabel = new JLabel("Appointment Booking");
        titleLabel.setBounds(180, 20, 400, 40);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 30));
        f.add(titleLabel);



        // =======================
        //  DEPARTMENT SELECTION
        // =======================
        deptLabel = new JLabel("Department:");
        deptLabel.setBounds(100, 80, 150, 30);
        f.add(deptLabel);

        deptBox = new JComboBox<>();
        deptBox.setBounds(250, 80, 300, 30);

        // When department changes → update doctor list
        deptBox.addActionListener(e -> loadDoctors());
        f.add(deptBox);



        // =======================
        //  DOCTOR SELECTION
        // =======================
        doctorLabel = new JLabel("Doctor:");
        doctorLabel.setBounds(100, 130, 150, 30);
        f.add(doctorLabel);

        doctorBox = new JComboBox<>();
        doctorBox.setBounds(250, 130, 300, 30);
        f.add(doctorBox);



        // ===========================
        //  PATIENT NAME (AUTO-FILLED)
        // ===========================
        patientLabel = new JLabel("Patient: " + patientName);
        patientLabel.setBounds(100, 180, 400, 30);
        f.add(patientLabel);



        // =======================
        //  DATE SELECTION
        // =======================
        dateLabel = new JLabel("Date:");
        dateLabel.setBounds(100, 230, 150, 30);
        f.add(dateLabel);

        dateBox = new JComboBox<>();
        dateBox.setBounds(250, 230, 300, 30);

        // Add next 7 days automatically
        java.time.LocalDate today = java.time.LocalDate.now();
        for (int i = 0; i < 7; i++) {
            dateBox.addItem(today.plusDays(i).toString());
        }

        f.add(dateBox);



        // =======================
        //  TIME SLOT SELECTION
        // =======================
        timeLabel = new JLabel("Time Slot:");
        timeLabel.setBounds(100, 280, 150, 30);
        f.add(timeLabel);

        timeBox = new JComboBox<>();
        timeBox.setBounds(250, 280, 300, 30);

        populateTimeSlots();
        f.add(timeBox);



        // =======================
        //  BUTTONS
        // =======================
        book = new JButton("Book");
        book.setBounds(180, 350, 140, 40);
        book.addActionListener(this);
        f.add(book);

        back = new JButton("Back");
        back.setBounds(400, 350, 140, 40);
        back.addActionListener(this);
        f.add(back);



        // Load departments from DB
        populateDepartments();

        f.setVisible(true);
    }



    // =====================================================
    //  LOAD UNIQUE DEPARTMENTS FROM DOCTOR TABLE
    // =====================================================
    private void populateDepartments() {
        try {
            ConnectionClass obj = new ConnectionClass();

            String query = "SELECT DISTINCT doctorDepartment FROM hospital_management_system.doctor";
            ResultSet rs = obj.stm.executeQuery(query);

            while (rs.next()) {
                deptBox.addItem(rs.getString("doctorDepartment"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    // =====================================================
    //  LOAD DOCTORS BASED ON SELECTED DEPARTMENT
    // =====================================================
    private void loadDoctors() {

        doctorBox.removeAllItems();

        try {
            ConnectionClass obj = new ConnectionClass();

            String selectedDept = (String) deptBox.getSelectedItem();

            if (selectedDept != null) {

                String query =
                        "SELECT doctorName FROM hospital_management_system.doctor " +
                                "WHERE doctorDepartment='" + selectedDept + "'";

                ResultSet rs = obj.stm.executeQuery(query);

                while (rs.next()) {
                    doctorBox.addItem(rs.getString("doctorName"));
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    // =====================================================
    //  GENERATE DOCTOR TIME SLOTS (9AM–5PM, SKIPPING LUNCH)
    // =====================================================
    private void populateTimeSlots() {

        ArrayList<String> slots = new ArrayList<>();

        for (int h = 9; h < 17; h++) {

            if (h == 12 || h == 13) continue; // Skip 12–2 PM lunch break

            slots.add(String.format("%02d:00", h));
            slots.add(String.format("%02d:40", h));
        }

        for (String s : slots) {
            timeBox.addItem(s);
        }
    }



    // =====================================================
    //  BUTTON HANDLER
    // =====================================================
    @Override
    public void actionPerformed(ActionEvent ae) {

        // --------------------
        //  BOOK APPOINTMENT
        // --------------------
        if (ae.getSource() == book) {

            String dept = (String) deptBox.getSelectedItem();
            String doctor = (String) doctorBox.getSelectedItem();
            String date = (String) dateBox.getSelectedItem();
            String time = (String) timeBox.getSelectedItem();

            // Basic validation
            if (dept == null || doctor == null || date == null || time == null) {
                JOptionPane.showMessageDialog(f, "Please select all fields!");
                return;
            }

            try {
                ConnectionClass obj = new ConnectionClass();


                // --------------------
                //  Get doctorId
                // --------------------
                String qDoctor =
                        "SELECT doctorId FROM hospital_management_system.doctor " +
                                "WHERE doctorName='" + doctor + "'";

                ResultSet rsDoctor = obj.stm.executeQuery(qDoctor);

                int doctorId = 0;
                if (rsDoctor.next()) doctorId = rsDoctor.getInt("doctorId");



                // --------------------
                //  Get patientId
                // --------------------
                String qPatient =
                        "SELECT patientId FROM hospital_management_system.patient " +
                                "WHERE patientName='" + patientName + "'";

                ResultSet rsPatient = obj.stm.executeQuery(qPatient);

                int patientId = 0;
                if (rsPatient.next()) patientId = rsPatient.getInt("patientId");



                // --------------------
                //  Insert appointment
                // --------------------
                String insert =
                        "INSERT INTO hospital_management_system.appointment " +
                                "(doctorId, doctorName, patientId, patientName, doctorDepartment, docAppointmentDate, docAppointmentTime, docAppointmentStatus) " +
                                "VALUES (" + doctorId + ", '" + doctor + "', " + patientId + ", '" + patientName + "', '" + dept + "', '" + date + "', '" + time + "', 'Pending')";

                int result = obj.stm.executeUpdate(insert);

                if (result > 0)
                    JOptionPane.showMessageDialog(f, "Appointment booked successfully!");
                else
                    JOptionPane.showMessageDialog(f, "Failed to book appointment.");

            } catch (Exception e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(f, "Database error occurred!");
            }
        }



        // --------------------
        //  BACK BUTTON
        // --------------------
        if (ae.getSource() == back) {
            f.setVisible(false);
            new ProfilePatient(patientName);
        }
    }



    public static void main(String[] args) {
        new AppointmentBooking("TestPatient");
    }
}
