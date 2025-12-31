package com.hospitalmanagement.project.dao;

import java.sql.*;
import com.hospitalmanagement.project.util.DBconnection;

public class BillingDAO {

    public ResultSet getBillsByPatient(int patientId) throws Exception {
        Connection con = DBconnection.getConnection();
        String sql = "SELECT * FROM billing WHERE patientId=?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setInt(1, patientId);
        return ps.executeQuery();
    }

    public void generateBill(int patientId, double amount) throws Exception {
        Connection con = DBconnection.getConnection();
        String sql = "INSERT INTO billing(patientId, amount) VALUES (?,?)";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setInt(1, patientId);
        ps.setDouble(2, amount);
        ps.executeUpdate();
    }
}
