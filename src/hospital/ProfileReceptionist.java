package hospital;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class ProfileReceptionist extends JFrame implements ActionListener {

    JFrame f;

    JLabel titleLabel, idLabel, nameLabel, emailLabel, addressLabel, phoneLabel;
    JLabel idValue, nameValue, emailValue, addressValue, phoneValue;

    JButton update, logout;

    String receptionistEmail;

    public ProfileReceptionist(String receptionistEmail) {
        this.receptionistEmail = receptionistEmail;

        f = new JFrame("Receptionist Profile");
        f.setSize(700, 600);
        f.setLocation(350, 150);
        f.setLayout(null);
        f.setResizable(false);

        titleLabel = new JLabel("Receptionist Profile");
        titleLabel.setBounds(180, 20, 400, 40);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 30));
        f.add(titleLabel);

        idLabel = new JLabel("Receptionist ID:");
        idLabel.setBounds(100, 100, 160, 30);
        f.add(idLabel);

        idValue = new JLabel();
        idValue.setBounds(280, 100, 300, 30);
        f.add(idValue);

        nameLabel = new JLabel("Name:");
        nameLabel.setBounds(100, 150, 150, 30);
        f.add(nameLabel);

        nameValue = new JLabel();
        nameValue.setBounds(280, 150, 300, 30);
        f.add(nameValue);

        emailLabel = new JLabel("Email:");
        emailLabel.setBounds(100, 200, 150, 30);
        f.add(emailLabel);

        emailValue = new JLabel();
        emailValue.setBounds(280, 200, 300, 30);
        f.add(emailValue);

        addressLabel = new JLabel("Address:");
        addressLabel.setBounds(100, 250, 150, 30);
        f.add(addressLabel);

        addressValue = new JLabel();
        addressValue.setBounds(280, 250, 300, 30);
        f.add(addressValue);

        phoneLabel = new JLabel("Phone:");
        phoneLabel.setBounds(100, 300, 150, 30);
        f.add(phoneLabel);

        phoneValue = new JLabel();
        phoneValue.setBounds(280, 300, 300, 30);
        f.add(phoneValue);

        fetchProfileFromDB();

        update = new JButton("Update Details");
        update.setBounds(150, 400, 180, 40);
        update.addActionListener(this);
        f.add(update);

        logout = new JButton("Logout");
        logout.setBounds(370, 400, 180, 40);
        logout.addActionListener(this);
        f.add(logout);

        f.setVisible(true);
    }

    private void fetchProfileFromDB() {
        try {
            ConnectionClass obj = new ConnectionClass();
            Connection conn = obj.con;

            String sql =
                    "SELECT receptionistId, receptionistName, emailID, address, phone " +
                            "FROM receptionist WHERE emailID = ?";

            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, receptionistEmail);

            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                idValue.setText(String.valueOf(rs.getInt("receptionistId")));
                nameValue.setText(rs.getString("receptionistName"));
                emailValue.setText(rs.getString("emailID"));
                addressValue.setText(rs.getString("address"));
                phoneValue.setText(rs.getString("phone"));
            } else {
                JOptionPane.showMessageDialog(f, "Receptionist not found!");
            }

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(f, "Error loading profile!");
        }
    }

    @Override
    public void actionPerformed(ActionEvent ae) {

        if (ae.getSource() == logout) {
            f.setVisible(false);
            new ReceptionistLogin();
        }

        if (ae.getSource() == update) {
            JOptionPane.showMessageDialog(f, "Update feature coming soon!");
        }
    }

    public static void main(String[] args) {
        new ProfileReceptionist("anita@gmail.com");
    }
}
