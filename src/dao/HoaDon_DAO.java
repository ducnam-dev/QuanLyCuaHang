package dao;

import java.sql.*;
import java.sql.Date;
import java.time.LocalDate;
import java.util.*;

import connectDB.ConnectDB;
import entity.*;

public class HoaDon_DAO {

    public HoaDon_DAO() {}

    public List<HoaDon> getAllHoaDon() throws Exception {
        List<HoaDon> dsHoaDon = new ArrayList<>();
        Connection conn = ConnectDB.getConnection();

        String sql = "SELECT * FROM HoaDon";
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(sql);

        while (rs.next()) {
            String maHD = rs.getString("maHD");
            String maNV = rs.getString("maNV");
            String maKH = rs.getString("maKH");
            LocalDate ngayLapHD = rs.getDate("ngayLapHD").toLocalDate();

            // Giả định có DAO hoặc constructor khởi tạo NhanVien và KhachHang từ maNV, maKH
            NhanVien nv = new NhanVien(maNV); // bạn nên dùng NhanVien_DAO nếu cần thông tin đầy đủ
            KhachHang kh = new KhachHang(maKH);

            HoaDon hd = new HoaDon(maHD, nv, kh, ngayLapHD);

            // Lấy chi tiết hóa đơn
            hd.getDanhSachChiTiet().addAll(getChiTietHoaDonByMaHD(maHD));

            dsHoaDon.add(hd);
        }
        return dsHoaDon;
    }

    public List<ChiTietHoaDon> getChiTietHoaDonByMaHD(String maHD) throws Exception {
        List<ChiTietHoaDon> ds = new ArrayList<>();
        Connection conn = ConnectDB.getConnection();

        String sql = "SELECT * FROM ChiTietHoaDon WHERE maHD = ?";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1, maHD);
        ResultSet rs = stmt.executeQuery();

        while (rs.next()) {
            String maSP = rs.getString("maSP");
            int soLuong = rs.getInt("soLuong");

            // giả sử đã có SanPham_DAO
            SanPham sp = new SanPham_DAO().getSanPhamByID(maSP);

            ds.add(new ChiTietHoaDon(sp, soLuong));
        }
        return ds;
    }

    public void addHoaDon(HoaDon hd) throws Exception {
        Connection conn = ConnectDB.getConnection();

        String sqlHD = "INSERT INTO HoaDon (maHD, maNV, maKH, ngayLapHD) VALUES (?, ?, ?, ?)";
        PreparedStatement stmtHD = conn.prepareStatement(sqlHD);
        stmtHD.setString(1, hd.getMaHD());
        stmtHD.setString(2, hd.getNhanVien().getMaNV());
        stmtHD.setString(3, hd.getKhachHang().getMaKH());
        stmtHD.setDate(4, Date.valueOf(hd.getNgayLapHD()));
        stmtHD.executeUpdate();

        // Insert từng dòng chi tiết hóa đơn
        String sqlCT = "INSERT INTO ChiTietHoaDon (maHD, maSP, soLuong) VALUES (?, ?, ?)";
        PreparedStatement stmtCT = conn.prepareStatement(sqlCT);

        for (ChiTietHoaDon ct : hd.getDanhSachChiTiet()) {
            stmtCT.setString(1, hd.getMaHD());
            stmtCT.setString(2, ct.getSanPham().getMaSP());
            stmtCT.setInt(3, ct.getSoLuong());
            stmtCT.addBatch();
        }
        stmtCT.executeBatch();
    }

    public HoaDon getHoaDonByID(String maHD) throws Exception {
        Connection conn = ConnectDB.getConnection();
        String sql = "SELECT * FROM HoaDon WHERE maHD = ?";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1, maHD);
        ResultSet rs = stmt.executeQuery();

        if (rs.next()) {
            String maNV = rs.getString("maNV");
            String maKH = rs.getString("maKH");
            LocalDate ngayLapHD = rs.getDate("ngayLapHD").toLocalDate();

            NhanVien nv = new NhanVien(maNV);
            KhachHang kh = new KhachHang(maKH);

            HoaDon hd = new HoaDon(maHD, nv, kh, ngayLapHD);
            hd.getDanhSachChiTiet().addAll(getChiTietHoaDonByMaHD(maHD));

            return hd;
        }
        return null;
    }

    public int countHoaDon() throws Exception {
        Connection conn = ConnectDB.getConnection();
        String sql = "SELECT COUNT(*) FROM HoaDon";
        PreparedStatement stmt = conn.prepareStatement(sql);
        ResultSet rs = stmt.executeQuery();

        if (rs.next()) {
            return rs.getInt(1);
        }
        return 0;
    }

	public List<HoaDon> getHoaDonChuaThanhToan() {
		// TODO Auto-generated method stub
		return null;
	}

	public void updateTrangThaiThanhToan(String maHD, boolean b) {
		// TODO Auto-generated method stub
		
	}
}
