package hospital;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class AddPatient extends JFrame implements ActionListener {

    JFrame f;
    JTextField name, email, phone, address;
    JButton save, back;

    public AddPatient() {

        f = new JFrame("Register New Patient");
        f.setSize(500, 450);
        f.setLocation(400, 200);
        f.setLayout(null);

        JLabel title = new JLabel("New Patient Registration");
        title.setBounds(120, 20, 300, 30);
        title.setFont(new Font("Arial", Font.BOLD, 20));
        f.add(title);

        f.add(label("Name:", 80));
        name = field(80);

        f.add(label("Email:", 130));
        email = field(130);

        f.add(label("Phone:", 180));
        phone = field(180);

        f.add(label("Address:", 230));
        address = field(230);

        save = new JButton("Save");
        save.setBounds(100, 300, 120, 40);
        save.addActionListener(this);
        f.add(save);

        back = new JButton("Back");
        back.setBounds(250, 300, 120, 40);
        back.addActionListener(this);
        f.add(back);

        f.setVisible(true);
    }

    private JLabel label(String text, int y) {
        JLabel l = new JLabel(text);
        l.setBounds(50, y, 100, 30);
        return l;
    }

    private JTextField field(int y) {
        JTextField t = new JTextField();
        t.setBounds(160, y, 220, 30);
        f.add(t);
        return t;
    }

    @Override
    public void actionPerformed(ActionEvent ae) {

        if (ae.getSource() == back) {
            f.dispose();
        }

        if (ae.getSource() == save) {

            try {
                ConnectionClass obj = new ConnectionClass();
                String sql =
                        "INSERT INTO patient (patientName, emailId, phone, address) " +
                                "VALUES (?, ?, ?, ?)";

                PreparedStatement ps = obj.con.prepareStatement(sql);
                ps.setString(1, name.getText());
                ps.setString(2, email.getText());
                ps.setString(3, phone.getText());
                ps.setString(4, address.getText());

                ps.executeUpdate();

                JOptionPane.showMessageDialog(f, "Patient Registered!");
                f.dispose();

            } catch (Exception e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(f, "Error saving patient");
            }
        }
    }

    public static void main(String[] args) {
        new AddPatient();
    }
}
