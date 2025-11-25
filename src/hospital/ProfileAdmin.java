package hospital;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class ProfileAdmin extends JFrame implements ActionListener {
    JFrame f;
    JLabel titleLabel, idLabel, nameLabel, emailLabel, addressLabel, phoneLabel;
    JLabel idValue, nameValue, emailValue, addressValue, phoneValue;
    JButton update, logout;
    String adminEmail;

    // Constructor now takes email
    public ProfileAdmin(String adminEmail){
        this.adminEmail = adminEmail;

        f = new JFrame("Admin Profile");
        f.setSize(700, 600);
        f.setLocation(350, 150);
        f.setLayout(null);
        f.setResizable(false);

        titleLabel = new JLabel("Admin Profile");
        titleLabel.setBounds(220, 20, 300, 40);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 30));
        f.add(titleLabel);

        // Labels
        idLabel = new JLabel("Admin ID:");
        idLabel.setBounds(100, 100, 150, 30);
        idLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        f.add(idLabel);

        idValue = new JLabel();
        idValue.setBounds(270, 100, 300, 30);
        idValue.setFont(new Font("Arial", Font.PLAIN, 20));
        f.add(idValue);

        nameLabel = new JLabel("Name:");
        nameLabel.setBounds(100, 150, 150, 30);
        nameLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        f.add(nameLabel);

        nameValue = new JLabel();
        nameValue.setBounds(270, 150, 300, 30);
        nameValue.setFont(new Font("Arial", Font.PLAIN, 20));
        f.add(nameValue);

        emailLabel = new JLabel("Email:");
        emailLabel.setBounds(100, 200, 150, 30);
        emailLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        f.add(emailLabel);

        emailValue = new JLabel();
        emailValue.setBounds(270, 200, 300, 30);
        emailValue.setFont(new Font("Arial", Font.PLAIN, 20));
        f.add(emailValue);

        addressLabel = new JLabel("Address:");
        addressLabel.setBounds(100, 250, 150, 30);
        addressLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        f.add(addressLabel);

        addressValue = new JLabel();
        addressValue.setBounds(270, 250, 300, 30);
        addressValue.setFont(new Font("Arial", Font.PLAIN, 20));
        f.add(addressValue);

        phoneLabel = new JLabel("Phone:");
        phoneLabel.setBounds(100, 300, 150, 30);
        phoneLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        f.add(phoneLabel);

        phoneValue = new JLabel();
        phoneValue.setBounds(270, 300, 300, 30);
        phoneValue.setFont(new Font("Arial", Font.PLAIN, 20));
        f.add(phoneValue);

        fetchProfileFromDB();  // fetch details using email

        // Buttons
        update = new JButton("Update Details");
        update.setBounds(150, 400, 180, 40);
        update.setFont(new Font("Arial", Font.PLAIN, 18));
        update.addActionListener(this);
        f.add(update);

        logout = new JButton("Logout");
        logout.setBounds(370, 400, 180, 40);
        logout.setFont(new Font("Arial", Font.PLAIN, 18));
        logout.addActionListener(this);
        f.add(logout);

        f.setVisible(true);
    }

    private void fetchProfileFromDB() {
        try {
            Connection conn = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/hospital_management_system",
                    "root",
                    "12345"  // replace with your DB password
            );

            String query = "SELECT * FROM admin WHERE emailID=?";
            PreparedStatement pst = conn.prepareStatement(query);
            pst.setString(1, adminEmail);
            ResultSet rs = pst.executeQuery();

            if(rs.next()) {
                idValue.setText(String.valueOf(rs.getInt("adminId")));
                nameValue.setText(rs.getString("adminName"));
                emailValue.setText(rs.getString("emailID"));
                addressValue.setText(rs.getString("address"));
                phoneValue.setText(rs.getString("phone"));
            } else {
                JOptionPane.showMessageDialog(f, "Admin not found!");
            }

            rs.close();
            pst.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(f, "Error fetching profile data.");
        }
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if(ae.getSource() == logout){
            f.setVisible(false);
            new AdminLogin();  // redirect to login page
        }
        if(ae.getSource() == update){
            JOptionPane.showMessageDialog(f, "Update functionality coming soon!");
        }
    }

    public static void main(String[] args) {
        // Test with an existing admin email from DB
        new ProfileAdmin("admin@example.com");
    }
}
