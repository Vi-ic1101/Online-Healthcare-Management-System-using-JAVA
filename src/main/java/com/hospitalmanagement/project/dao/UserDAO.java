package com.hospitalmanagement.project.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.hospitalmanagement.project.util.DBconnection;

public class UserDAO {

    public static boolean registerUser(String role, String name, String email,
                                       String phone, String address, String dept,
                                       String password, String confirmPassword) {

        String sql;

        switch (role) {
            case "Doctor":
                sql = "INSERT INTO doctor (name, address, phone, email, password, confirm_password, department) VALUES (?,?,?,?,?,?,?)";
                break;

            case "Patient":
                sql = "INSERT INTO patient (name, address, phone, email, password, confirm_password) VALUES (?,?,?,?,?,?)";
                break;

            case "Admin":
                sql = "INSERT INTO admin (name, address, phone, email, password, confirm_password) VALUES (?,?,?,?,?,?)";
                break;

            case "Pharmacist":
                sql = "INSERT INTO pharmacist (name, address, phone, email, password, confirm_password) VALUES (?,?,?,?,?,?)";
                break;

            case "Receptionist":
                sql = "INSERT INTO receptionist (name, address, phone, email, password, confirm_password) VALUES (?,?,?,?,?,?)";
                break;

            default:
                return false;
        }

        try (Connection con = DBconnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, name);
            ps.setString(2, address);
            ps.setString(3, phone);
            ps.setString(4, email);
            ps.setString(5, password);
            ps.setString(6, confirmPassword);

            if (role.equals("Doctor")) {
                ps.setString(7, dept);
            }

            ps.executeUpdate();
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
