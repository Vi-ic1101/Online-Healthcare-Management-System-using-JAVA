package hospital;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class RegistrationPage extends JFrame implements ActionListener {

    JFrame f;
    JLabel lTitle, lName, lEmail, lPhone, lAddress, lRole, lPassword, lConfirmPassword, lDepartment;
    JTextField tName, tEmail, tPhone, tAddress;
    JPasswordField pPassword, pConfirmPassword;
    JComboBox<String> tRole, tDepartment;
    JButton btnRegister, btnBack;

    public RegistrationPage() {
        f = new JFrame("User Registration Page");
        f.setBackground(Color.WHITE);
        f.setLayout(null);

        f.setSize(800, 650);
        f.setLocation(300, 60);
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
        lName = new JLabel("Name:");
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
        String roles[] = {"Select", "Doctor", "Admin", "Patient","Pharmacist","Receptionist"};
        tRole = new JComboBox<>(roles);
        tRole.setBounds(330, 300, 250, 30);
        background.add(tRole);

        // Department (only for doctors)
        lDepartment = new JLabel("Department:");
        lDepartment.setBounds(140, 350, 200, 35);
        lDepartment.setFont(new Font("Arial", Font.PLAIN, 20));
        background.add(lDepartment);

        String departments[] = {"Cardiology", "Neurology", "Orthopedics", "Pediatrics", "General"};
        tDepartment = new JComboBox<>(departments);
        tDepartment.setBounds(330, 350, 250, 30);
        background.add(tDepartment);

        JLabel lWarn = new JLabel("*select only if you are a doctor or an admin:");
        lWarn.setBounds(tDepartment.getX(), tDepartment.getY()+20, 250, 35);
        lWarn.setForeground(Color.red);
        lWarn.setFont(new Font("Arial", Font.PLAIN, 10));
        background.add(lWarn);

        // Password
        lPassword = new JLabel("Password:");
        lPassword.setBounds(140, 400, 200, 35);
        lPassword.setFont(new Font("Arial", Font.PLAIN, 20));
        background.add(lPassword);
        pPassword = new JPasswordField();
        pPassword.setBounds(330, 400, 250, 30);
        background.add(pPassword);

        // Confirm Password
        lConfirmPassword = new JLabel("Confirm Password:");
        lConfirmPassword.setBounds(140, 450, 200, 35);
        lConfirmPassword.setFont(new Font("Arial", Font.PLAIN, 20));
        background.add(lConfirmPassword);
        pConfirmPassword = new JPasswordField();
        pConfirmPassword.setBounds(330, 450, 250, 30);
        background.add(pConfirmPassword);

        // Buttons
        btnRegister = new JButton("Register");
        btnRegister.setBounds(220, 520, 150, 40);
        btnRegister.setBackground(new Color(0, 153, 51));
        btnRegister.setForeground(Color.WHITE);
        btnRegister.addActionListener(this);
        background.add(btnRegister);

        btnBack = new JButton("Back");
        btnBack.setBounds(400, 520, 150, 40);
        btnBack.setBackground(new Color(204, 204, 204));
        btnBack.setForeground(Color.BLACK);
        btnBack.addActionListener(this);
        background.add(btnBack);

        f.add(background);
        f.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == btnBack) {
            f.dispose();
            new MainPage();
        }

        if (ae.getSource() == btnRegister) {
            String role = (String) tRole.getSelectedItem();
            String name = tName.getText();
            String email = tEmail.getText();
            String phone = tPhone.getText();
            String address = tAddress.getText();
            String password = new String(pPassword.getPassword());
            String confirmPassword = new String(pConfirmPassword.getPassword());

            if (name.isEmpty() || email.isEmpty() || phone.isEmpty() || address.isEmpty() ||
                    password.isEmpty() || confirmPassword.isEmpty() || role.equals("Select")) {
                JOptionPane.showMessageDialog(f, "All fields are required!");
                return;
            }

            if (!password.equals(confirmPassword)) {
                JOptionPane.showMessageDialog(f, "Passwords do not match!");
                return;
            }

            try {
                ConnectionClass obj = new ConnectionClass();
                String query = "";

                switch (role) {
                    case "Doctor":
                        query = "INSERT INTO doctor (doctorName, address, doctorDepartment, phone, emailId, doctorPassword, doctorConfirmPassword) VALUES (?, ?, ?, ?, ?, ?, ?)";
                        PreparedStatement psDoctor = obj.con.prepareStatement(query);
                        psDoctor.setString(1, name);
                        psDoctor.setString(2, address);
                        psDoctor.setString(3, (String) tDepartment.getSelectedItem());
                        psDoctor.setString(4, phone);
                        psDoctor.setString(5, email);
                        psDoctor.setString(6, password);
                        psDoctor.setString(7, confirmPassword);
                        psDoctor.executeUpdate();
                        break;

                    case "Patient":
                        query = "INSERT INTO patient (patientName, address, phone, emailId, patientPassword, patientConfirmPassword) VALUES (?, ?, ?, ?, ?, ?)";
                        PreparedStatement psPatient = obj.con.prepareStatement(query);
                        psPatient.setString(1, name);
                        psPatient.setString(2, address);
                        psPatient.setString(3, phone);
                        psPatient.setString(4, email);
                        psPatient.setString(5, password);
                        psPatient.setString(6, confirmPassword);
                        psPatient.executeUpdate();
                        break;

                    case "Admin":
                        query = "INSERT INTO admin (adminName, address, phone, emailID, adminPassword, adminConfirmPassword) VALUES (?, ?, ?, ?, ?, ?)";
                        PreparedStatement psAdmin = obj.con.prepareStatement(query);
                        psAdmin.setString(1, name);
                        psAdmin.setString(2, address);
                        psAdmin.setString(3, phone);
                        psAdmin.setString(4, email);
                        psAdmin.setString(5, password);
                        psAdmin.setString(6, confirmPassword);
                        psAdmin.executeUpdate();
                        break;
                    case "Pharmacist":
//                      phoneNumber, address, licenseNumber, qualification, experienceYears, shift, status, createdAt
                        query = "INSERT INTO pharmacist (pharmacistName, address, phoneNumber, emailId, password, ConfirmPassword) VALUES (?, ?, ?, ?, ?, ?)";
                        PreparedStatement psPharmacist = obj.con.prepareStatement(query);
                        psPharmacist.setString(1, name);
                        psPharmacist.setString(2, address);
                        psPharmacist.setString(3, phone);
                        psPharmacist.setString(4, email);
                        psPharmacist.setString(5, password);
                        psPharmacist.setString(6, confirmPassword);
                        psPharmacist.executeUpdate();
                        break;
                    case "Receptionist":
//                     receptionistId, receptionistName, emailID, receptionistPassword, phone, address, confirmPassword
                        query = "INSERT INTO Receptionist (receptionistName, address, phone, emailID, receptionistPassword, ConfirmPassword) VALUES (?, ?, ?, ?, ?, ?)";
                        PreparedStatement psReceptionist = obj.con.prepareStatement(query);
                        psReceptionist.setString(1, name);
                        psReceptionist.setString(2, address);
                        psReceptionist.setString(3, phone);
                        psReceptionist.setString(4, email);
                        psReceptionist.setString(5, password);
                        psReceptionist.setString(6, confirmPassword);
                        psReceptionist.executeUpdate();
                        break;
                }

                JOptionPane.showMessageDialog(f, "Registration Successful!");
                f.setVisible(false);

                // Redirect based on role
                if (role.equals("Doctor")) new DoctorLogin();
                else if (role.equals("Patient")) new Index();
                else if (role.equals("Admin")) new AdminLogin();
                else if (role.equals("Pharmacist")) new PharmacistLogin();
                else if (role.equals("Receptionist")) new PharmacistLogin();

            } catch (Exception e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(f, "Error during registration!");
            }
        }
    }

    public static void main(String[] args) {
        new RegistrationPage();
    }
}


