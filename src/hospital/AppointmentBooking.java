package hospital;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.ArrayList;

public class AppointmentBooking extends JFrame implements ActionListener {

    JFrame f;
    JComboBox<String> deptBox, doctorBox, dateBox, timeBox;
    JButton book, back;

    String patientEmail, patientName;

    public AppointmentBooking(String patientEmail, String patientName) {
        this.patientEmail = patientEmail;
        this.patientName = patientName;

        f = new JFrame("Book Appointment");
        f.setSize(700, 500);
        f.setLocation(350, 150);
        f.setLayout(null);

        f.add(label("Appointment Booking", 200, 20, 30, true));
        f.add(label("Department:", 100, 80));
        f.add(label("Doctor:", 100, 130));
        f.add(label("Patient: " + patientName, 100, 180));
        f.add(label("Date:", 100, 230));
        f.add(label("Time Slot:", 100, 280));

        deptBox = box(250, 80);
        doctorBox = box(250, 130);
        dateBox = box(250, 230);
        timeBox = box(250, 280);

        deptBox.addActionListener(e -> loadDoctors());

        book = button("Book", 180, 350);
        back = button("Back", 400, 350);

        populateDepartments();
        populateDates();
        populateTimeSlots();

        f.setVisible(true);
    }

    private JLabel label(String text, int x, int y) {
        return label(text, x, y, 14, false);
    }

    private JLabel label(String text, int x, int y, int size, boolean bold) {
        JLabel l = new JLabel(text);
        l.setBounds(x, y, 400, 30);
        l.setFont(new Font("Arial", bold ? Font.BOLD : Font.PLAIN, size));
        return l;
    }

    private JComboBox<String> box(int x, int y) {
        JComboBox<String> b = new JComboBox<>();
        b.setBounds(x, y, 300, 30);
        f.add(b);
        return b;
    }

    private JButton button(String text, int x, int y) {
        JButton b = new JButton(text);
        b.setBounds(x, y, 140, 40);
        b.addActionListener(this);
        f.add(b);
        return b;
    }

    private void populateDepartments() {
        try {
            ConnectionClass obj = new ConnectionClass();
            ResultSet rs = obj.stm.executeQuery(
                    "SELECT DISTINCT doctorDepartment FROM hospital_management_system.doctor"
            );
            while (rs.next()) deptBox.addItem(rs.getString(1));
        } catch (Exception ignored) {}
    }

    private void loadDoctors() {
        doctorBox.removeAllItems();
        try {
            ConnectionClass obj = new ConnectionClass();
            PreparedStatement ps = obj.con.prepareStatement(
                    "SELECT doctorName FROM hospital_management_system.doctor WHERE doctorDepartment=?"
            );
            ps.setString(1, (String) deptBox.getSelectedItem());
            ResultSet rs = ps.executeQuery();
            while (rs.next()) doctorBox.addItem(rs.getString(1));
        } catch (Exception ignored) {}
    }

    private void populateDates() {
        java.time.LocalDate d = java.time.LocalDate.now();
        for (int i = 0; i < 7; i++) dateBox.addItem(d.plusDays(i).toString());
    }

    private void populateTimeSlots() {
        for (int h = 9; h < 17; h++) {
            if (h == 12 || h == 13) continue;
            timeBox.addItem(String.format("%02d:00", h));
            timeBox.addItem(String.format("%02d:40", h));
        }
    }

    @Override
    public void actionPerformed(ActionEvent ae) {

        if (ae.getSource() == back) {
            f.dispose();
            new ProfilePatient(patientEmail);
            return;
        }

        if (ae.getSource() == book) {

            try {
                ConnectionClass obj = new ConnectionClass();

                PreparedStatement psPatient =
                        obj.con.prepareStatement("SELECT patientId FROM patient WHERE emailId=?");
                psPatient.setString(1, patientEmail);
                ResultSet rp = psPatient.executeQuery();
                if (!rp.next()) return;
                int patientId = rp.getInt(1);

                PreparedStatement psDoctor =
                        obj.con.prepareStatement("SELECT doctorId FROM doctor WHERE doctorName=?");
                psDoctor.setString(1, (String) doctorBox.getSelectedItem());
                ResultSet rd = psDoctor.executeQuery();
                if (!rd.next()) return;
                int doctorId = rd.getInt(1);

                PreparedStatement ins = obj.con.prepareStatement(
                        "INSERT INTO appointment VALUES (NULL,?,?,?,?,?,?,?, 'Pending')"
                );
                ins.setInt(1, doctorId);
                ins.setString(2, (String) doctorBox.getSelectedItem());
                ins.setInt(3, patientId);
                ins.setString(4, patientName);
                ins.setString(5, (String) deptBox.getSelectedItem());
                ins.setString(6, (String) dateBox.getSelectedItem());
                ins.setString(7, (String) timeBox.getSelectedItem());

                ins.executeUpdate();
                JOptionPane.showMessageDialog(f, "Appointment booked!");

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    public static void main(String[] args) {

        // ⚠ Must exist in DB
        String testEmail = "testpatient@gmail.com";
        String testName  = "Test Patient";

        SwingUtilities.invokeLater(() ->
                new AppointmentBooking(testEmail, testName)
        );
    }

}
