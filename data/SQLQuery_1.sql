-- Tạo cơ sở dữ liệu
CREATE DATABASE QuanLyCuaHangTienLoi;
GO

-- Sử dụng cơ sở dữ liệu
USE QuanLyCuaHangTienLoi;
GO

-- Bảng Nhân viên
CREATE TABLE NhanVien (
    maNV VARCHAR(10) PRIMARY KEY,
    matKhau VARCHAR(255) NOT NULL,
    tenNV NVARCHAR(100) NOT NULL,
    ngaySinh DATE NOT NULL,
    SDT VARCHAR(10) NOT NULL,
    cccd VARCHAR(12)
);

-- Bảng Khách hàng
CREATE TABLE KhachHang (
    maKH VARCHAR(10) PRIMARY KEY,
    tenKH NVARCHAR(100) NOT NULL,
    SDT VARCHAR(10) NOT NULL,
    diemTichLuy INT DEFAULT 0 CHECK (diemTichLuy >= 0)
);

-- Bảng Sản phẩm
CREATE TABLE SanPham (
    maSP VARCHAR(10) PRIMARY KEY,
    tenSP NVARCHAR(100) NOT NULL,
    giaBan FLOAT NOT NULL CHECK (giaBan >= 0),
    loaiSP NVARCHAR(50) NOT NULL CHECK (loaiSP IN (N'Thực phẩm', N'Đồ uống', N'Hàng gia dụng'))
);

-- Bảng Hóa đơn
CREATE TABLE HoaDon (
    maHD VARCHAR(10) PRIMARY KEY,
    maNV VARCHAR(10) NOT NULL,
    maKH VARCHAR(10) NOT NULL,
    ngayLapHD DATE NOT NULL,
    FOREIGN KEY (maNV) REFERENCES NhanVien(maNV),
    FOREIGN KEY (maKH) REFERENCES KhachHang(maKH)
);

-- Bảng Chi tiết hóa đơn
CREATE TABLE ChiTietHoaDon (
    maHD VARCHAR(10),
    maSP VARCHAR(10),
    soLuong INT NOT NULL CHECK (soLuong > 0),
    PRIMARY KEY (maHD, maSP),
    FOREIGN KEY (maHD) REFERENCES HoaDon(maHD),
    FOREIGN KEY (maSP) REFERENCES SanPham(maSP)
);

-- Bảng Tình trạng kho
CREATE TABLE TinhTrangKho (
    maSP VARCHAR(10) PRIMARY KEY,
    soLuong INT NOT NULL CHECK (soLuong >= 0),
    ngayNhap DATE NOT NULL CHECK (ngayNhap <= GETDATE()),
    FOREIGN KEY (maSP) REFERENCES SanPham(maSP)
);
GO


--Add 5 nvien mau
INSERT INTO NhanVien (maNV, matKhau, tenNV, ngaySinh, SDT, cccd)
VALUES
('NV001', 'nvpass1', N'Nguyễn Thị Lan', '1990-05-15', '0911111111', '012345678901'),
('NV002', 'nvpass2', N'Trần Minh Hoàng', '1988-08-22', '0922222222', '012345678902'),
('NV003', 'nvpass3', N'Lê Quang Hiếu', '1992-03-10', '0933333333', '012345678903'),
('NV004', 'nvpass4', N'Phạm Thu Trang', '1995-07-01', '0944444444', '012345678904'),
('NV005', 'nvpass5', N'Vũ Thành Mai', '1991-11-05', '0955555555', '012345678905');


--Add 5 KH mau
INSERT INTO KhachHang (maKH, tenKH, SDT, diemTichLuy)
VALUES
('KH001', N'Nguyễn Văn A', '0981111111', 120),
('KH002', N'Trần Thị B', '0982222222', 90),
('KH003', N'Lê Văn C', '0983333333', 150),
('KH004', N'Phạm Thị D', '0984444444', 60),
('KH005', N'Hoàng Văn E', '0985555555', 30);


--Add 10 san pham mau
INSERT INTO SanPham (maSP, tenSP, giaBan, loaiSP)
VALUES
('SP001', N'Mì tôm Hảo Hảo', 3500, N'Thực phẩm'),
('SP002', N'Coca Cola lon 330ml', 9000, N'Đồ uống'),
('SP003', N'Bánh Oreo', 12000, N'Thực phẩm'),
('SP004', N'Nước suối Aquafina 500ml', 6000, N'Đồ uống'),
('SP005', N'Bột giặt Omo 400g', 18000, N'Hàng gia dụng'),
('SP006', N'Kem đánh răng P/S 100g', 15000, N'Hàng gia dụng'),
('SP007', N'Cháo ăn liền Vifon', 8000, N'Thực phẩm'),
('SP008', N'Trà xanh Không độ 455ml', 10000, N'Đồ uống'),
('SP009', N'Nước rửa chén Sunlight 250ml', 14000, N'Hàng gia dụng'),
('SP010', N'Sữa tươi TH True Milk 1L', 28000, N'Đồ uống');
