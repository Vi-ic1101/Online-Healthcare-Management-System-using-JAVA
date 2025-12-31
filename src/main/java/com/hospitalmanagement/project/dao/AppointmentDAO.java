package com.hospitalmanagement.project.dao;

import java.sql.*;
import com.hospitalmanagement.project.util.DBconnection;

public class AppointmentDAO {

    public ResultSet getAppointmentsByDoctor(int doctorId) throws Exception {
        Connection con = DBconnection.getConnection();
        String sql =
                "SELECT a.*, p.patientName " +
                        "FROM appointment a JOIN patient p ON a.patientId=p.patientId " +
                        "WHERE a.doctorId=?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setInt(1, doctorId);
        return ps.executeQuery();
    }

    public ResultSet getAppointmentsByPatient(int patientId) throws Exception {
        Connection con = DBconnection.getConnection();
        String sql =
                "SELECT a.*, d.doctorName " +
                        "FROM appointment a JOIN doctor d ON a.doctorId=d.doctorId " +
                        "WHERE a.patientId=?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setInt(1, patientId);
        return ps.executeQuery();
    }
}
