package com.hospitalmanagement.project.dao;

import java.sql.*;
import com.hospitalmanagement.project.util.DBconnection;

public class DoctorDAO {

    public ResultSet getDoctorById(int doctorId) throws Exception {
        Connection con = DBconnection.getConnection();
        String sql = "SELECT * FROM doctor WHERE doctorId=?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setInt(1, doctorId);
        return ps.executeQuery();
    }

    public ResultSet getAllDoctors() throws Exception {
        Connection con = DBconnection.getConnection();
        Statement st = con.createStatement();
        return st.executeQuery("SELECT * FROM doctor");
    }
}
