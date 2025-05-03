package entity;

public class KhachHang {
	private String maKH;
	private String tenKH;
	private String sdt;

	public KhachHang(String maKH, String tenKH, String sDT) {
		super();
		this.maKH = maKH;
		this.tenKH = tenKH;
		this.sdt = sDT;
	}

	public KhachHang(String maKH) {
		super();
		this.maKH = maKH;
	}

	public String getMaKH() {
		return maKH;
	}

	public void setMaKH(String maKH) {
		this.maKH = maKH;
	}

	public String getTenKH() {
		return tenKH;
	}

	public void setTenKH(String tenKH) {
		this.tenKH = tenKH;
	}

	public String getSDT() {
		return sdt;
	}

	public void setSDT(String sDT) {
		sdt = sDT;
	}

	@Override
	public String toString() {
		return "KhachHang [maKH=" + maKH + ", tenKH=" + tenKH + ", SDT=" + sdt + "]";
	}

	
}