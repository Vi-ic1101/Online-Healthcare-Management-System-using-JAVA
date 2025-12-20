package hospital;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class ProfilePharmacist extends JFrame implements ActionListener {

    JFrame f;
    JLabel titleLabel, idLabel, nameLabel, emailLabel, addressLabel, phoneLabel;
    JLabel idValue, nameValue, emailValue, addressValue, phoneValue;
    JButton update, logout, billingBtn;   // added billing button
    String pharmacistEmail;

    // Constructor takes logged-in pharmacist email
    public ProfilePharmacist(String pharmacistEmail) {

        this.pharmacistEmail = pharmacistEmail;

        f = new JFrame("Pharmacist Profile");
        f.setSize(700, 600);
        f.setLocation(350, 150);
        f.setLayout(null);
        f.setResizable(false);

        titleLabel = new JLabel("Pharmacist Profile");
        titleLabel.setBounds(180, 20, 400, 40);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 30));
        f.add(titleLabel);

        // Labels
        idLabel = new JLabel("Pharmacist ID:");
        idLabel.setBounds(100, 100, 160, 30);
        idLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        f.add(idLabel);

        idValue = new JLabel();
        idValue.setBounds(280, 100, 300, 30);
        idValue.setFont(new Font("Arial", Font.PLAIN, 20));
        f.add(idValue);

        nameLabel = new JLabel("Name:");
        nameLabel.setBounds(100, 150, 150, 30);
        nameLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        f.add(nameLabel);

        nameValue = new JLabel();
        nameValue.setBounds(280, 150, 300, 30);
        nameValue.setFont(new Font("Arial", Font.PLAIN, 20));
        f.add(nameValue);

        emailLabel = new JLabel("Email:");
        emailLabel.setBounds(100, 200, 150, 30);
        emailLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        f.add(emailLabel);

        emailValue = new JLabel();
        emailValue.setBounds(280, 200, 300, 30);
        emailValue.setFont(new Font("Arial", Font.PLAIN, 20));
        f.add(emailValue);

        addressLabel = new JLabel("Address:");
        addressLabel.setBounds(100, 250, 150, 30);
        addressLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        f.add(addressLabel);

        addressValue = new JLabel();
        addressValue.setBounds(280, 250, 300, 30);
        addressValue.setFont(new Font("Arial", Font.PLAIN, 20));
        f.add(addressValue);

        phoneLabel = new JLabel("Phone:");
        phoneLabel.setBounds(100, 300, 150, 30);
        phoneLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        f.add(phoneLabel);

        phoneValue = new JLabel();
        phoneValue.setBounds(280, 300, 300, 30);
        phoneValue.setFont(new Font("Arial", Font.PLAIN, 20));
        f.add(phoneValue);

        // Fetch profile from DB
        fetchProfileFromDB();

        // Buttons
        update = new JButton("Update Details");
        update.setBounds(80, 400, 160, 40);
        update.setFont(new Font("Arial", Font.PLAIN, 18));
        update.addActionListener(this);
        f.add(update);

        billingBtn = new JButton("Billing");
        billingBtn.setBounds(260, 400, 160, 40);
        billingBtn.setFont(new Font("Arial", Font.PLAIN, 18));
        billingBtn.addActionListener(this);
        f.add(billingBtn);

        logout = new JButton("Logout");
        logout.setBounds(440, 400, 160, 40);
        logout.setFont(new Font("Arial", Font.PLAIN, 18));
        logout.addActionListener(this);
        f.add(logout);

        f.setVisible(true);
    }

    private void fetchProfileFromDB() {
        Connection conn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;

        try {
            conn = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/hospital_management_system",
                    "root",
                    "12345"
            );

            String query = "SELECT pharmacistId, pharmacistName, emailId, address, phoneNumber " +
                    "FROM pharmacist WHERE emailId = ?";

            pst = conn.prepareStatement(query);
            pst.setString(1, pharmacistEmail);
            rs = pst.executeQuery();

            if (rs.next()) {
                idValue.setText(String.valueOf(rs.getInt("pharmacistId")));
                nameValue.setText(rs.getString("pharmacistName"));
                emailValue.setText(rs.getString("emailId"));
                addressValue.setText(rs.getString("address"));
                phoneValue.setText(rs.getString("phoneNumber"));
            } else {
                JOptionPane.showMessageDialog(f, "Pharmacist not found!");
            }

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(f, "Error fetching profile data.");
        } finally {
            try {
                if (rs != null) rs.close();
                if (pst != null) pst.close();
                if (conn != null) conn.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }


    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == logout) {
            f.setVisible(false);
            new PharmacistLogin();
        } else if (ae.getSource() == update) {
            JOptionPane.showMessageDialog(f, "Update functionality coming soon!");
        } else if (ae.getSource() == billingBtn) {
            // open Billing page and pass same pharmacist email so Back works
            new Billing(pharmacistEmail);
        }
    }

    public static void main(String[] args) {
        new ProfilePharmacist("pharmacist@example.com");
    }
}
