package hospital;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.ResultSet;

public class RegistrationPage extends JFrame implements ActionListener {
    JFrame f;
    JLabel lTitle, lName, lEmail, lPhone, lAddress, lRole, lPassword, lConfirmPassword,lDepartment;
    JTextField tName, tEmail, tPhone, tAddress;
    JPasswordField pPassword, pConfirmPassword;
    JButton btnRegister, btnBack;
    JComboBox<String> tRole;

    RegistrationPage() {
        f = new JFrame("User Registration Page");
        f.setBackground(Color.WHITE);
        f.setLayout(null);

        f.setSize(800, 650);
        f.setLocation(300, 60);
        f.setVisible(true);
        f.setResizable(false);

        JLabel background = new JLabel();
        background.setBounds(0, 0, f.getWidth(), f.getHeight());
        background.setLayout(null);

        // Title
        lTitle = new JLabel("User Registration");
        lTitle.setBounds(220, 30, 400, 50);
        lTitle.setFont(new Font("Arial", Font.BOLD, 30));
        background.add(lTitle);

        // Name
        lName = new JLabel("User Name:");
        lName.setBounds(140, 100, 200, 35);
        lName.setFont(new Font("Arial", Font.PLAIN, 20));
        background.add(lName);

        tName = new JTextField();
        tName.setBounds(330, 100, 250, 30);
        background.add(tName);

        // Email
        lEmail = new JLabel("Email:");
        lEmail.setBounds(140, 150, 200, 35);
        lEmail.setFont(new Font("Arial", Font.PLAIN, 20));
        background.add(lEmail);

        tEmail = new JTextField();
        tEmail.setBounds(330, 150, 250, 30);
        background.add(tEmail);

        // Phone
        lPhone = new JLabel("Phone:");
        lPhone.setBounds(140, 200, 200, 35);
        lPhone.setFont(new Font("Arial", Font.PLAIN, 20));
        background.add(lPhone);

        tPhone = new JTextField();
        tPhone.setBounds(330, 200, 250, 30);
        background.add(tPhone);

        // Address
        lAddress = new JLabel("Address:");
        lAddress.setBounds(140, 250, 200, 35);
        lAddress.setFont(new Font("Arial", Font.PLAIN, 20));
        background.add(lAddress);

        tAddress = new JTextField();
        tAddress.setBounds(330, 250, 250, 30);
        background.add(tAddress);

        // Role
        lRole = new JLabel("Role:");
        lRole.setBounds(140, 300, 200, 35);
        lRole.setFont(new Font("Arial", Font.PLAIN, 20));
        background.add(lRole);

        String roles[] = {"select","Doctor", "Admin","patient"};
        tRole = new JComboBox<>(roles);
        tRole.setBounds(330, 300, 250, 30);
        background.add(tRole);

        lDepartment = new JLabel("Role:");
        lDepartment.setBounds(140, 300, 200, 35);
        lDepartment.setFont(new Font("Arial", Font.PLAIN, 20));
        background.add(lDepartment);

        // Password
        lPassword = new JLabel("Password:");
        lPassword.setBounds(140, 350, 200, 35);
        lPassword.setFont(new Font("Arial", Font.PLAIN, 20));
        background.add(lPassword);

        pPassword = new JPasswordField();
        pPassword.setBounds(330, 350, 250, 30);
        background.add(pPassword);

        // Confirm Password
        lConfirmPassword = new JLabel("Confirm Password:");
        lConfirmPassword.setBounds(140, 400, 200, 35);
        lConfirmPassword.setFont(new Font("Arial", Font.PLAIN, 20));
        background.add(lConfirmPassword);

        pConfirmPassword = new JPasswordField();
        pConfirmPassword.setBounds(330, 400, 250, 30);
        background.add(pConfirmPassword);

        // Register Button
        btnRegister = new JButton("Register Doctor");
        btnRegister.setBounds(220, 480, 150, 40);
        btnRegister.setBackground(new Color(0, 153, 51));
        btnRegister.setForeground(Color.WHITE);
        btnRegister.addActionListener(this);
        background.add(btnRegister);

        // Back Button
        btnBack = new JButton("Back");
        btnBack.setBounds(400, 480, 150, 40);
        btnBack.setBackground(new Color(204, 204, 204));
        btnBack.setForeground(Color.BLACK);
        btnBack.addActionListener(this);
        background.add(btnBack);

        f.add(background);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == btnBack) {
            f.setVisible(false);
            new Index();
        }

        if (ae.getSource() == btnRegister) {
            String username = (String) tRole.getSelectedItem();

            if (username.equalsIgnoreCase("Doctor")) {

                String name = tName.getText();
                String email = tEmail.getText();
                String phone = tPhone.getText();
                String address = tAddress.getText();

                String password = new String(pPassword.getPassword());
                String confirmPassword = new String(pConfirmPassword.getPassword());

                if (name.isEmpty() || email.isEmpty() || phone.isEmpty() || address.isEmpty() ||
                        username.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {

                    JOptionPane.showMessageDialog(f, "All fields are required!");

                } else if (!password.equals(confirmPassword)) {

                    JOptionPane.showMessageDialog(f, "Passwords do not match!");

                } else {

                    String qB = "INSERT INTO hospital_management_system.doctor " +
                            "(doctorName, address, doctorDepartment, phone, emailId, doctorPassword, doctorConfirmPassword) " +
                            "VALUES (" +
                            "'Dr. " + name + "', " +
                            "'" + address + "', " +
                            "NULL, " +
                            "'" + phone + "', " +
                            "'" + email + "', " +
                            "'" + password + "', " +
                            "'" + confirmPassword + "'" +
                            ");";

                    try {
                        ConnectionClass obj = new ConnectionClass();
                        obj.stm.executeUpdate(qB);
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }

                    JOptionPane.showMessageDialog(f, "Registration Successful!");
                    f.setVisible(false);
                    new LoginPage();
                }
            }
            if (username.equalsIgnoreCase("patient")) {

                String name = tName.getText();
                String email = tEmail.getText();
                String phone = tPhone.getText();
                String address = tAddress.getText();

                String password = new String(pPassword.getPassword());
                String confirmPassword = new String(pConfirmPassword.getPassword());

                if (name.isEmpty() || email.isEmpty() || phone.isEmpty() || address.isEmpty() ||
                        username.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {

                    JOptionPane.showMessageDialog(f, "All fields are required!");

                } else if (!password.equals(confirmPassword)) {

                    JOptionPane.showMessageDialog(f, "Passwords do not match!");

                } else {

                    String qB = "INSERT INTO hospital_management_system.patient " +
                            "(patientName, address, phone, emailId, patientPassword, patientConfirmPassword )" +
                            "VALUES (" +
                            "' " + name + "', " +
                            "'" + address + "', " +
//                            "NULL, " +
                            "'" + phone + "', " +
                            "'" + email + "', " +
                            "'" + password + "', " +
                            "'" + confirmPassword + "'" +
                            ");";

                    try {
                        ConnectionClass obj = new ConnectionClass();
                        obj.stm.executeUpdate(qB);
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }

                    JOptionPane.showMessageDialog(f, "Registration Successful!");
                    f.setVisible(false);
                    new Index();
                }
            }
            if (username.equalsIgnoreCase("Admin")) {

                String name = tName.getText();
                String email = tEmail.getText();
                String phone = tPhone.getText();
                String address = tAddress.getText();

                String password = new String(pPassword.getPassword());
                String confirmPassword = new String(pConfirmPassword.getPassword());

                if (name.isEmpty() || email.isEmpty() || phone.isEmpty() || address.isEmpty() ||
                        username.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {

                    JOptionPane.showMessageDialog(f, "All fields are required!");

                } else if (!password.equals(confirmPassword)) {

                    JOptionPane.showMessageDialog(f, "Passwords do not match!");

                } else {

                    String qB = "INSERT INTO hospital_management_system.admin " +
                            "(adminName, address, phone, emailID, adminPassword, adminConfirmPassword) " +
                            "VALUES (" +
                            "'Mod.  " + name + "', " +
                            "'" + address + "', " +
                            "'" + phone + "', " +
                            "'" + email + "', " +
                            "'" + password + "', " +
                            "'" + confirmPassword + "'" +
                            ");";

                    try {
                        ConnectionClass obj = new ConnectionClass();
                        obj.stm.executeUpdate(qB);
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }

                    JOptionPane.showMessageDialog(f, "Registration Successful!");
                    f.setVisible(false);
                    new AdminLogin();
                }
            }
        }
    }
}
