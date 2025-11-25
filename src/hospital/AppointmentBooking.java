package hospital;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.ArrayList;

public class AppointmentBooking extends JFrame implements ActionListener {
    JFrame f;
    JLabel titleLabel, deptLabel, doctorLabel, patientLabel, dateLabel, timeLabel;
    JComboBox<String> deptBox, doctorBox, dateBox, timeBox;
    JButton book, back;
    String patientName; // Passed from ProfilePatient

    public AppointmentBooking(String patientName) {
        this.patientName = patientName;

        f = new JFrame("Book Appointment");
        f.setSize(700, 500);
        f.setLocation(350, 150);
        f.setLayout(null);
        f.setResizable(false);

        titleLabel = new JLabel("Appointment Booking");
        titleLabel.setBounds(180, 20, 400, 40);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 30));
        f.add(titleLabel);

        // Department
        deptLabel = new JLabel("Department:");
        deptLabel.setBounds(100, 80, 150, 30);
        f.add(deptLabel);

        deptBox = new JComboBox<>();
        deptBox.setBounds(250, 80, 300, 30);
        deptBox.addActionListener(e -> loadDoctors());
        f.add(deptBox);

        // Doctor
        doctorLabel = new JLabel("Doctor:");
        doctorLabel.setBounds(100, 130, 150, 30);
        f.add(doctorLabel);

        doctorBox = new JComboBox<>();
        doctorBox.setBounds(250, 130, 300, 30);
        f.add(doctorBox);

        // Patient (auto-filled)
        patientLabel = new JLabel("Patient: " + patientName);
        patientLabel.setBounds(100, 180, 400, 30);
        f.add(patientLabel);

        // Date (next 7 days)
        dateLabel = new JLabel("Date:");
        dateLabel.setBounds(100, 230, 150, 30);
        f.add(dateLabel);

        dateBox = new JComboBox<>();
        dateBox.setBounds(250, 230, 300, 30);
        java.time.LocalDate today = java.time.LocalDate.now();
        for (int i = 0; i < 7; i++) {
            dateBox.addItem(today.plusDays(i).toString());
        }
        f.add(dateBox);

        // Time slot
        timeLabel = new JLabel("Time Slot:");
        timeLabel.setBounds(100, 280, 150, 30);
        f.add(timeLabel);

        timeBox = new JComboBox<>();
        timeBox.setBounds(250, 280, 300, 30);
        populateTimeSlots();
        f.add(timeBox);

        // Buttons
        book = new JButton("Book");
        book.setBounds(180, 350, 140, 40);
        book.addActionListener(this);
        f.add(book);

        back = new JButton("Back");
        back.setBounds(400, 350, 140, 40);
        back.addActionListener(this);
        f.add(back);

        // Populate departments from database
        populateDepartments();

        f.setVisible(true);
    }

    private void populateDepartments() {
        try {
            ConnectionClass obj = new ConnectionClass();
            String query = "SELECT DISTINCT doctorDepartment FROM hospital_management_system.doctor";
            ResultSet rs = obj.stm.executeQuery(query);
            while(rs.next()) {
                deptBox.addItem(rs.getString("doctorDepartment"));
            }
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    private void loadDoctors() {
        doctorBox.removeAllItems();
        try {
            ConnectionClass obj = new ConnectionClass();
            String selectedDept = (String) deptBox.getSelectedItem();
            if(selectedDept != null) {
                String query = "SELECT doctorName FROM hospital_management_system.doctor WHERE doctorDepartment='" + selectedDept + "'";
                ResultSet rs = obj.stm.executeQuery(query);
                while(rs.next()) {
                    doctorBox.addItem(rs.getString("doctorName"));
                }
            }
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    private void populateTimeSlots() {
        ArrayList<String> slots = new ArrayList<>();
        for(int h=9; h<17; h++) {
            if(h==12 || h==13) continue; // Skip break 12-2
            slots.add(String.format("%02d:00", h));
            slots.add(String.format("%02d:40", h));
        }
        for(String s : slots) {
            timeBox.addItem(s);
        }
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if(ae.getSource() == book) {
            String dept = (String) deptBox.getSelectedItem();
            String doctor = (String) doctorBox.getSelectedItem();
            String date = (String) dateBox.getSelectedItem();
            String time = (String) timeBox.getSelectedItem();

            if(dept==null || doctor==null || date==null || time==null) {
                JOptionPane.showMessageDialog(f, "Please select department, doctor, date, and time!");
                return;
            }

            try {
                ConnectionClass obj = new ConnectionClass();

                // Get doctorId
                String queryDoctor = "SELECT doctorId FROM hospital_management_system.doctor WHERE doctorName='" + doctor + "'";
                ResultSet rsDoctor = obj.stm.executeQuery(queryDoctor);
                int doctorId = 0;
                if(rsDoctor.next()) doctorId = rsDoctor.getInt("doctorId");

                // Get patientId
                String queryPatient = "SELECT patientId FROM hospital_management_system.patient WHERE patientName='" + patientName + "'";
                ResultSet rsPatient = obj.stm.executeQuery(queryPatient);
                int patientId = 0;
                if(rsPatient.next()) patientId = rsPatient.getInt("patientId");

                // Insert appointment
                String query = "INSERT INTO hospital_management_system.appointment " +
                        "(doctorId, doctorName, patientId, patientName, doctorDepartment, docAppointmentDate, docAppointmentTime, docAppointmentStatus) " +
                        "VALUES (" + doctorId + ", '" + doctor + "', " + patientId + ", '" + patientName + "', '" + dept + "', '" + date + "', '" + time + "', 'Pending')";
                int result = obj.stm.executeUpdate(query);
                if(result>0) {
                    JOptionPane.showMessageDialog(f, "Appointment booked successfully!");
                } else {
                    JOptionPane.showMessageDialog(f, "Booking failed.");
                }
            } catch(Exception e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(f, "Database error!");
            }
        }


        if(ae.getSource() == back) {
            f.setVisible(false);
            new ProfilePatient(patientName); // Go back to patient profile
        }
    }

    public static void main(String[] args) {
        new AppointmentBooking("TestPatient");
    }
}
