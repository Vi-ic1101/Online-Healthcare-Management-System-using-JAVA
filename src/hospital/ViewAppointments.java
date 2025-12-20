package hospital;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.*;
import java.sql.*;

public class ViewAppointments extends JFrame implements ActionListener {

    JFrame f;
    JTable table;
    DefaultTableModel model;
    JButton approve, cancel, refresh;

    public ViewAppointments() {

        f = new JFrame("Manage Appointments");
        f.setSize(900, 500);
        f.setLocation(300, 150);
        f.setLayout(null);

        model = new DefaultTableModel(
                new String[]{
                        "Appointment ID", "Patient", "Doctor",
                        "Department", "Date", "Time", "Status"
                }, 0
        );

        table = new JTable(model);
        JScrollPane sp = new JScrollPane(table);
        sp.setBounds(20, 20, 850, 300);
        f.add(sp);

        approve = new JButton("Approve");
        approve.setBounds(200, 350, 150, 40);
        approve.addActionListener(this);
        f.add(approve);

        cancel = new JButton("Cancel");
        cancel.setBounds(380, 350, 150, 40);
        cancel.addActionListener(this);
        f.add(cancel);

        refresh = new JButton("Refresh");
        refresh.setBounds(560, 350, 150, 40);
        refresh.addActionListener(this);
        f.add(refresh);

        loadAppointments();

        f.setVisible(true);
    }

    private void loadAppointments() {
        model.setRowCount(0);

        try {
            ConnectionClass obj = new ConnectionClass();
            String sql = "SELECT * FROM appointment";

            ResultSet rs = obj.stm.executeQuery(sql);

            while (rs.next()) {
                model.addRow(new Object[]{
                        rs.getInt("appointmentId"),
                        rs.getString("patientName"),
                        rs.getString("doctorName"),
                        rs.getString("doctorDepartment"),
                        rs.getString("docAppointmentDate"),
                        rs.getString("docAppointmentTime"),
                        rs.getString("docAppointmentStatus")
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void updateStatus(String status) {

        int row = table.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(f, "Select an appointment first!");
            return;
        }

        int appointmentId = (int) model.getValueAt(row, 0);

        try {
            ConnectionClass obj = new ConnectionClass();
            String sql =
                    "UPDATE appointment SET docAppointmentStatus = ? WHERE appointmentId = ?";

            PreparedStatement ps = obj.con.prepareStatement(sql);
            ps.setString(1, status);
            ps.setInt(2, appointmentId);

            ps.executeUpdate();

            JOptionPane.showMessageDialog(f, "Appointment " + status);
            loadAppointments();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void actionPerformed(ActionEvent ae) {

        if (ae.getSource() == approve) {
            updateStatus("Approved");
        }

        if (ae.getSource() == cancel) {
            updateStatus("Cancelled");
        }

        if (ae.getSource() == refresh) {
            loadAppointments();
        }
    }

    public static void main(String[] args) {
        new ViewAppointments();
    }
}
