package com.hospitalmanagement.project.dao;

import java.sql.*;
import com.hospitalmanagement.project.util.DBconnection;

public class PatientDAO {

    public ResultSet getPatientById(int patientId) throws Exception {
        Connection con = DBconnection.getConnection();
        String sql = "SELECT * FROM patient WHERE patientId=?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setInt(1, patientId);
        return ps.executeQuery();
    }

    public ResultSet searchPatient(String name) throws Exception {
        Connection con = DBconnection.getConnection();
        String sql = "SELECT * FROM patient WHERE patientName LIKE ?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, "%" + name + "%");
        return ps.executeQuery();
    }
}
