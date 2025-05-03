
package entity;

import java.time.LocalDate;
import java.util.Objects;

public class NhanVien {
    private String maNV;
    private String matKhau;
    private String tenNV;
    private LocalDate ngaySinh;
    private String sdt;
    private String cccd;

  
    public NhanVien(String maNV, String matKhau, String tenNV, LocalDate ngaySinh, String sDT, String cccd) {
		super();
		this.maNV = maNV;
		this.matKhau = matKhau;
		this.tenNV = tenNV;
		this.ngaySinh = ngaySinh;
		this.sdt = sDT;
		this.cccd = cccd;
	}

	public NhanVien(String maNV) {
		super();
		this.maNV = maNV;
	}

	public String getMaNV() {
		return maNV;
	}

	public void setMaNV(String maNV) {
		this.maNV = maNV;
	}

	public String getMatKhau() {
		return matKhau;
	}

	public void setMatKhau(String matKhau) {
		this.matKhau = matKhau;
	}

	public String getTenNV() {
		return tenNV;
	}

	public void setTenNV(String tenNV) {
		this.tenNV = tenNV;
	}

	public LocalDate getNgaySinh() {
		return ngaySinh;
	}

	public void setNgaySinh(LocalDate ngaySinh) {
		this.ngaySinh = ngaySinh;
	}

	public String getSDT() {
		return sdt;
	}

	public void setSDT(String sDT) {
		sdt = sDT;
	}

	public String getCccd() {
		return cccd;
	}

	public void setCccd(String cccd) {
		this.cccd = cccd;
	}

	@Override
	public int hashCode() {
		return Objects.hash(maNV);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		NhanVien other = (NhanVien) obj;
		return Objects.equals(maNV, other.maNV);
	}

	@Override
	public String toString() {
		return "NhanVien [maNV=" + maNV + ", matKhau=" + matKhau + ", tenNV=" + tenNV + ", ngaySinh=" + ngaySinh
				+ ", SDT=" + sdt + ", cccd=" + cccd + "]";
	}
	
	
	
	

	
    

    
	
}
