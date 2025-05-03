package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import connectDB.ConnectDB;
import entity.*;

public class SanPham_DAO {
    public SanPham_DAO() {
    }

    public List<SanPham> getAllSanPham() throws Exception {
        List<SanPham> dsSanPham = new ArrayList<>();
        String sql = "SELECT * FROM SanPham";
        try (Connection conn = ConnectDB.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                String maSP = rs.getString("maSP");
                String tenSP = rs.getString("tenSP");
                double giaBan = rs.getDouble("giaBan");
                String loaiSP = rs.getString("loaiSP");
                SanPham sp = new SanPham(maSP, tenSP, giaBan, loaiSP);
                dsSanPham.add(sp);
            }
        } catch (SQLException e) {
            throw new Exception("Lỗi khi lấy danh sách sản phẩm: " + e.getMessage());
        }
        return dsSanPham;
    }

    public SanPham getSanPhamByID(String maSP) throws Exception {
        String sql = "SELECT * FROM SanPham WHERE maSP = ?";
        try (Connection conn = ConnectDB.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, maSP);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    String tenSP = rs.getString("tenSP");
                    double giaBan = rs.getDouble("giaBan");
                    String loaiSP = rs.getString("loaiSP");
                    return new SanPham(maSP, tenSP, giaBan, loaiSP);
                }
            }
        } catch (SQLException e) {
            throw new Exception("Lỗi khi lấy sản phẩm: " + e.getMessage());
        }
        return null;
    }

    public void addSanPham(SanPham sp) throws Exception {
        String sql = "INSERT INTO SanPham (maSP, tenSP, giaBan, loaiSP) VALUES (?, ?, ?, ?)";
        try (Connection conn = ConnectDB.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, sp.getMaSP());
            stmt.setString(2, sp.getTenSP());
            stmt.setDouble(3, sp.getGiaBan());
            stmt.setString(4, sp.getLoaiSP());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new Exception("Lỗi khi thêm sản phẩm: " + e.getMessage());
        }
    }

    public void updateSanPham(SanPham sp) throws Exception {
        String sql = "UPDATE SanPham SET tenSP = ?, giaBan = ?, loaiSP = ? WHERE maSP = ?";
        try (Connection conn = ConnectDB.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, sp.getTenSP());
            stmt.setDouble(2, sp.getGiaBan());
            stmt.setString(3, sp.getLoaiSP());
            stmt.setString(4, sp.getMaSP());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new Exception("Lỗi khi cập nhật sản phẩm: " + e.getMessage());
        }
    }

    public boolean deleteSanPham(String maSP) throws Exception {
        String sql = "DELETE FROM SanPham WHERE MaSP = ?";
        try (Connection conn = ConnectDB.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, maSP);
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            throw new Exception("Lỗi khi xóa sản phẩm: " + e.getMessage());
        }
    }

    public boolean deleteSanPhamByID(String maSP) throws Exception {
        return deleteSanPham(maSP); // Gọi deleteSanPham để tái sử dụng
    }

    public void editSanPhamByID(String oldMaSP, SanPham sp) throws Exception {
        String sql = "UPDATE SanPham SET maSP = ?, tenSP = ?, giaBan = ?, loaiSP = ? WHERE maSP = ?";
        try (Connection conn = ConnectDB.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, sp.getMaSP());
            stmt.setString(2, sp.getTenSP());
            stmt.setDouble(3, sp.getGiaBan());
            stmt.setString(4, sp.getLoaiSP());
            stmt.setString(5, oldMaSP);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new Exception("Lỗi khi chỉnh sửa sản phẩm: " + e.getMessage());
        }
    }
}