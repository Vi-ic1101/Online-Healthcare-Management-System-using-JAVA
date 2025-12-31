package com.hospitalmanagement.project.dao;

import java.sql.*;
import com.hospitalmanagement.project.util.DBconnection;

public class PharmacistDAO {

    public ResultSet getPharmacistById(int pharmacistId) throws Exception {
        Connection con = DBconnection.getConnection();
        String sql = "SELECT * FROM pharmacist WHERE pharmacistId=?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setInt(1, pharmacistId);
        return ps.executeQuery();
    }
}
