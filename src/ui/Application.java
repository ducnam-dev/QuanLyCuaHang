package ui;


import connectDB.ConnectDB;
import ui.forms.FormDangNhap;
import ui.gui.GUI_NhanVienCuaHang; 
import ui.gui.GUI_QuanLyCuaHang; 

import javax.swing.JOptionPane;

public class Application {
    public static String currentLoggedInUser = "";

    public static void main(String[] args) throws Exception {
        // Kết nối cơ sở dữ liệu
        try {
            ConnectDB.getInstance().connect();
            System.out.println("Kết nối cơ sở dữ liệu thành công!");
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Lỗi kết nối cơ sở dữ liệu!");
            return;
        }
        // Hiển thị form đăng nhập
        FormDangNhap formDangNhap = new FormDangNhap();
        formDangNhap.setVisible(true);

        // Lấy mã nhân viên đăng nhập
        currentLoggedInUser = formDangNhap.getMaNVDangNhap();

        // Chuyển hướng giao diện dựa trên vai trò người dùng
        if (currentLoggedInUser != null && currentLoggedInUser.equals("admin")) {
            GUI_QuanLyCuaHang guiQuanLy = new GUI_QuanLyCuaHang();
            guiQuanLy.setVisible(true);
        } else if (currentLoggedInUser != null && currentLoggedInUser.matches("NV\\d{3}")) {
            GUI_NhanVienCuaHang guiNhanVien = new GUI_NhanVienCuaHang(currentLoggedInUser);
            guiNhanVien.setVisible(true);
        } else {
            JOptionPane.showMessageDialog(null, "Chưa đăng nhập hoặc đăng nhập thất bại!");
        }
    }
}