package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import connectDB.ConnectDB;
import entity.*;

public class NhanVien_DAO {
    public NhanVien_DAO() {
    }

    public boolean checkLogin(String maNV, String matKhau) throws SQLException {
        Connection conn = ConnectDB.getConnection();
        try {
            String sql = "SELECT * FROM NhanVien WHERE maNV = ? AND matKhau = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, maNV);
            stmt.setString(2, matKhau);
            ResultSet rs = stmt.executeQuery();
            return rs.next();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<NhanVien> getAllNhanVien() throws Exception {
        List<NhanVien> dsNhanVien = new ArrayList<>();
        Connection conn = ConnectDB.getConnection();

        String sql = "SELECT * FROM NhanVien";
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(sql);

        while (rs.next()) {
            String maNV = rs.getString(1);
            String matKhau = rs.getString(2);
            String tenNV = rs.getString(3);
            LocalDate ngaySinh = rs.getDate(4).toLocalDate();
            String SDT = rs.getString(5);
            String cccd = rs.getString(6);
			NhanVien nv = new NhanVien(maNV, matKhau, tenNV, ngaySinh, SDT, cccd);
            dsNhanVien.add(nv);
        }
        return dsNhanVien;
    }

    public NhanVien getNhanVienByID(String maNV) throws Exception {
        Connection conn = ConnectDB.getConnection();
        String sql = "SELECT * FROM NhanVien WHERE maNV = ?";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1, maNV);
        ResultSet rs = stmt.executeQuery();

        if (rs.next()) {
            String matKhau = rs.getString("matKhau");
            String tenNV = rs.getString("tenNV");
            LocalDate ngaySinh = rs.getDate("ngaySinh").toLocalDate();
            String SDT = rs.getString("SDT");

            String cccd = null;
			return new NhanVien(maNV, matKhau, tenNV, ngaySinh, SDT, cccd);
        }
        return null;
    }

    public void addNhanVien(NhanVien nv) throws Exception {
        Connection conn = ConnectDB.getConnection();
        String sql = "INSERT INTO NhanVien (maNV, matKhau, tenNV, ngaySinh, SDT, CCCD) VALUES (?, ?, ?, ?, ?, ?)";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1, nv.getMaNV());
        stmt.setString(2, nv.getMatKhau());
        stmt.setString(3, nv.getTenNV());
        stmt.setDate(4, java.sql.Date.valueOf(nv.getNgaySinh()));
        stmt.setString(5, nv.getSDT());
        stmt.setString(6, nv.getCccd());
        stmt.executeUpdate();
    }

    public void updateNhanVien(NhanVien nv) throws Exception {
        try (Connection conn = ConnectDB.getConnection();
             PreparedStatement stmt = conn.prepareStatement(
                     "UPDATE NhanVien SET matKhau = ?, tenNV = ?, ngaySinh = ?, SDT = ?, CCCD = ? WHERE maNV = ?")) {
            stmt.setString(1, nv.getMatKhau());
            stmt.setString(2, nv.getTenNV());
            stmt.setDate(3, java.sql.Date.valueOf(nv.getNgaySinh()));
            stmt.setString(4, nv.getSDT());
            stmt.setString(5, nv.getCccd());
            stmt.setString(6, nv.getMaNV());
            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected == 0) {
                throw new Exception("Không tìm thấy nhân viên với mã: " + nv.getMaNV());
            }
        }
    }

    public boolean deleteNhanVien(String maNV) throws SQLException {
        Connection conn = ConnectDB.getConnection();
        try {
            String sql = "DELETE FROM NhanVien WHERE maNV = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, maNV);
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }


    public void editNhanVienByID(String maNV, NhanVien nv) throws Exception {
    	Connection conn = ConnectDB.getConnection();
    	String sql = "UPDATE NhanVien SET matKhau = ?, tenNV = ?, ngaySinh = ?, SDT = ?, CCCD = ? WHERE maNV = ?";
    	PreparedStatement stmt = conn.prepareStatement(sql);
    	stmt.setString(1, nv.getMatKhau());
    	stmt.setString(2, nv.getTenNV());
    	stmt.setDate(3, java.sql.Date.valueOf(nv.getNgaySinh()));
    	stmt.setString(4, nv.getSDT());
    	stmt.setString(5, nv.getCccd());
    	stmt.setString(6, maNV);

    	int rows = stmt.executeUpdate();
    	if (rows == 0) {
    		throw new Exception("Không tìm thấy nhân viên để cập nhật.");
    	}
    }


}