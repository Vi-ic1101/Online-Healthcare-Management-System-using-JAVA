package hospital;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.*;
import java.sql.*;

public class SearchPatient extends JFrame implements ActionListener {

    JFrame f;
    JTextField search;
    JTable table;
    DefaultTableModel model;
    JButton find;

    public SearchPatient() {

        f = new JFrame("Search Patient");
        f.setSize(700, 400);
        f.setLocation(350, 200);
        f.setLayout(null);

        JLabel l = new JLabel("Search by Name / Email:");
        l.setBounds(50, 20, 200, 30);
        f.add(l);

        search = new JTextField();
        search.setBounds(230, 20, 200, 30);
        f.add(search);

        find = new JButton("Search");
        find.setBounds(450, 20, 100, 30);
        find.addActionListener(this);
        f.add(find);

        model = new DefaultTableModel(
                new String[]{"ID", "Name", "Email", "Phone", "Address"}, 0
        );

        table = new JTable(model);
        JScrollPane sp = new JScrollPane(table);
        sp.setBounds(20, 80, 650, 250);
        f.add(sp);

        f.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {

        model.setRowCount(0);

        try {
            ConnectionClass obj = new ConnectionClass();
            String key = "%" + search.getText() + "%";

            String sql =
                    "SELECT * FROM patient WHERE patientName LIKE ? OR emailId LIKE ?";

            PreparedStatement ps = obj.con.prepareStatement(sql);
            ps.setString(1, key);
            ps.setString(2, key);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                model.addRow(new Object[]{
                        rs.getInt("patientId"),
                        rs.getString("patientName"),
                        rs.getString("emailId"),
                        rs.getString("phone"),
                        rs.getString("address")
                });
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new SearchPatient();
    }
}
