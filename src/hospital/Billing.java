package hospital;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class Billing extends JFrame implements ActionListener {

    JLabel titleLabel, patientNameLabel, patientEmailLabel,
            doctorChargeLabel, roomChargeLabel, medicineChargeLabel,
            otherChargeLabel, totalLabel;

    JTextField patientNameField, patientEmailField,
            doctorChargeField, roomChargeField, medicineChargeField,
            otherChargeField, totalField;

    JButton fetchBtn, calculateBtn, saveBtn, clearBtn, backBtn;

    String pharmacistEmail;

    public Billing(String pharmacistEmail) {
        this.pharmacistEmail = pharmacistEmail;

        setTitle("Patient Billing");
        setSize(720, 600);
        setLocation(350, 150);
        setLayout(null);
        setResizable(false);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        titleLabel = new JLabel("Patient Billing");
        titleLabel.setBounds(240, 20, 300, 40);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 30));
        add(titleLabel);

        // Patient Name
        patientNameLabel = new JLabel("Patient Name:");
        patientNameLabel.setBounds(100, 100, 160, 30);
        patientNameLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        add(patientNameLabel);

        patientNameField = new JTextField();
        patientNameField.setBounds(280, 100, 250, 30);
        add(patientNameField);

        // Patient Email
        patientEmailLabel = new JLabel("Patient Email:");
        patientEmailLabel.setBounds(100, 140, 160, 30);
        patientEmailLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        add(patientEmailLabel);

        patientEmailField = new JTextField();
        patientEmailField.setBounds(280, 140, 250, 30);
        add(patientEmailField);

        // Fetch button
        fetchBtn = new JButton("Fetch Patient");
        fetchBtn.setBounds(550, 120, 130, 30);
        styleButton(fetchBtn);
        fetchBtn.addActionListener(this);
        add(fetchBtn);

        // Charges
        doctorChargeLabel = new JLabel("Doctor Charge:");
        doctorChargeLabel.setBounds(100, 200, 160, 30);
        doctorChargeLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        add(doctorChargeLabel);

        doctorChargeField = new JTextField("0");
        doctorChargeField.setBounds(280, 200, 250, 30);
        add(doctorChargeField);

        roomChargeLabel = new JLabel("Room Charge:");
        roomChargeLabel.setBounds(100, 240, 160, 30);
        roomChargeLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        add(roomChargeLabel);

        roomChargeField = new JTextField("0");
        roomChargeField.setBounds(280, 240, 250, 30);
        add(roomChargeField);

        medicineChargeLabel = new JLabel("Medicine Charge:");
        medicineChargeLabel.setBounds(100, 280, 180, 30);
        medicineChargeLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        add(medicineChargeLabel);

        medicineChargeField = new JTextField("0");
        medicineChargeField.setBounds(280, 280, 250, 30);
        add(medicineChargeField);

        otherChargeLabel = new JLabel("Other Charge:");
        otherChargeLabel.setBounds(100, 320, 160, 30);
        otherChargeLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        add(otherChargeLabel);

        otherChargeField = new JTextField("0");
        otherChargeField.setBounds(280, 320, 250, 30);
        add(otherChargeField);

        // Total
        totalLabel = new JLabel("Total Amount:");
        totalLabel.setBounds(100, 370, 160, 30);
        totalLabel.setFont(new Font("Arial", Font.BOLD, 20));
        add(totalLabel);

        totalField = new JTextField();
        totalField.setBounds(280, 370, 250, 30);
        totalField.setEditable(false);
        totalField.setFont(new Font("Arial", Font.BOLD, 18));
        totalField.setForeground(Color.BLUE);
        add(totalField);

        // Buttons
        calculateBtn = new JButton("Calculate");
        calculateBtn.setBounds(70, 440, 130, 40);
        styleButton(calculateBtn);
        calculateBtn.addActionListener(this);
        add(calculateBtn);

        saveBtn = new JButton("Save");
        saveBtn.setBounds(210, 440, 130, 40);
        styleButton(saveBtn);
        saveBtn.addActionListener(this);
        add(saveBtn);

        clearBtn = new JButton("Clear");
        clearBtn.setBounds(350, 440, 130, 40);
        styleButton(clearBtn);
        clearBtn.addActionListener(this);
        add(clearBtn);

        backBtn = new JButton("Back");
        backBtn.setBounds(490, 440, 130, 40);
        styleButton(backBtn);
        backBtn.addActionListener(this);
        add(backBtn);

        setVisible(true);
    }

    private void styleButton(JButton btn) {
        btn.setBackground(Color.DARK_GRAY);
        btn.setForeground(Color.ORANGE);
        btn.setFocusPainted(false);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == fetchBtn) {
            fetchPatient();
        }
        else if (e.getSource() == calculateBtn) {
            calculateTotal();
        }
        else if (e.getSource() == clearBtn) {
            clearFields();
        }
        else if (e.getSource() == backBtn) {
            dispose();
            new ProfilePharmacist(pharmacistEmail);
        }
        else if (e.getSource() == saveBtn) {
            JOptionPane.showMessageDialog(this, "Billing save coming soon!");
        }
    }

    // 🔥 FETCH PATIENT USING EMAIL + NAME
    private void fetchPatient() {
        String name = patientNameField.getText().trim();
        String email = patientEmailField.getText().trim();

        if (name.isEmpty() || email.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Enter patient name and email");
            return;
        }

        try (Connection conn = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/hospital_management_system",
                "root",
                "12345")) {

            String sql = "SELECT patientName FROM patient WHERE emailId = ? AND patientName = ?";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, email);
            pst.setString(2, name);

            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                JOptionPane.showMessageDialog(this, "Patient found ✔");
            } else {
                JOptionPane.showMessageDialog(this, "Patient not found ❌");
            }

        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Database error while fetching patient");
        }
    }

    private void calculateTotal() {
        try {
            double doctor = Double.parseDouble(doctorChargeField.getText());
            double room = Double.parseDouble(roomChargeField.getText());
            double medicine = Double.parseDouble(medicineChargeField.getText());
            double other = Double.parseDouble(otherChargeField.getText());

            totalField.setText(String.valueOf(doctor + room + medicine + other));
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Invalid charge values");
        }
    }

    private void clearFields() {
        patientNameField.setText("");
        patientEmailField.setText("");
        doctorChargeField.setText("0");
        roomChargeField.setText("0");
        medicineChargeField.setText("0");
        otherChargeField.setText("0");
        totalField.setText("");
    }

    public static void main(String[] args) {
        new Billing("ap");
    }
}
