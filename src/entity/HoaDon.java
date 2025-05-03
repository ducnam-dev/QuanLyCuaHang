package entity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class HoaDon {
    private String maHD;
    private NhanVien nhanVien;
    private KhachHang khachHang;
    private LocalDate ngayLapHD;
    private List<ChiTietHoaDon> danhSachChiTiet;

    public HoaDon() {
        danhSachChiTiet = new ArrayList<>();
    }

    public HoaDon(String maHD, NhanVien nhanVien, KhachHang khachHang, LocalDate ngayLapHD) {
        this.maHD = maHD;
        this.nhanVien = nhanVien;
        this.khachHang = khachHang;
        this.ngayLapHD = ngayLapHD;
        this.danhSachChiTiet = new ArrayList<>();
    }

    public void themChiTiet(ChiTietHoaDon ct) {
        danhSachChiTiet.add(ct);
    }

    public String getMaHD() {
        return maHD;
    }

    public void setMaHD(String maHD) {
        this.maHD = maHD;
    }

    public NhanVien getNhanVien() {
        return nhanVien;
    }

    public void setNhanVien(NhanVien nhanVien) {
        this.nhanVien = nhanVien;
    }

    public KhachHang getKhachHang() {
        return khachHang;
    }

    public void setKhachHang(KhachHang khachHang) {
        this.khachHang = khachHang;
    }

    public LocalDate getNgayLapHD() {
        return ngayLapHD;
    }

    public void setNgayLapHD(LocalDate ngayLapHD) {
        this.ngayLapHD = ngayLapHD;
    }

    public List<ChiTietHoaDon> getDanhSachChiTiet() {
        return danhSachChiTiet;
    }

    public double getTongTien() {
        return danhSachChiTiet.stream().mapToDouble(ChiTietHoaDon::tinhTien).sum();
    }

    @Override
    public int hashCode() {
        return Objects.hash(maHD);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        HoaDon other = (HoaDon) obj;
        return Objects.equals(maHD, other.maHD);
    }

    @Override
    public String toString() {
        return "HoaDon [maHD=" + maHD + ", nhanVien=" + nhanVien + ", khachHang=" + khachHang
                + ", ngayLapHD=" + ngayLapHD + ", tongTien=" + getTongTien()
                + ", chiTiet=" + danhSachChiTiet + "]";
    }
}
