package entity;

public class ChiTietHoaDon {
    private SanPham sanPham;
    private int soLuong;

    public ChiTietHoaDon(SanPham sanPham, int soLuong) {
        this.sanPham = sanPham;
        this.soLuong = soLuong;
    }

    public SanPham getSanPham() {
        return sanPham;
    }

    public void setSanPham(SanPham sanPham) {
        this.sanPham = sanPham;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        if (soLuong > 0)
            this.soLuong = soLuong;
        else
            throw new IllegalArgumentException("Số lượng phải lớn hơn 0");
    }

    public double tinhTien() {
        return sanPham.getGiaBan() * soLuong;
    }

    @Override
    public String toString() {
        return "ChiTietHoaDon [sanPham=" + sanPham + ", soLuong=" + soLuong + ", thanhTien=" + tinhTien() + "]";
    }
}
