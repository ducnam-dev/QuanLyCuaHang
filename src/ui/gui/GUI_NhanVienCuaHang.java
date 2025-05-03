package ui.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import java.awt.Image;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import dao.NhanVien_DAO;
import dao.SanPham_DAO;
import entity.*;
import ui.forms.FormDangNhap;
import ui.forms.FormKhachHang;

public class GUI_NhanVienCuaHang extends JFrame implements ActionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel panelSanPham = new JPanel();
	private final JTextField txtMaSP = new JTextField();
	private final JTextField txtTenSP = new JTextField();
	private final JTextField txtGiaBan = new JTextField();
	private final JComboBox<String> cboLoaiSP = new JComboBox<String>();
	private final JLabel lblMaSP = new JLabel("Mã sản phẩm:  ");
	private final JLabel lblTenSP = new JLabel("Tên sản phẩm: ");
	private final JLabel lblGiaBan = new JLabel("Giá bán:            ");
	private final JLabel lblLoaiSP = new JLabel("Loại sản phẩm: ");
	private final JButton btnThem = new JButton("Thêm");
	private final JButton btnXoa = new JButton("Xóa");
	private final JButton btnLuu = new JButton("Lưu");
	private final JButton btnHuy = new JButton("Xóa trắng");
	private final JButton btnTimKiem = new JButton("Tìm kiếm");
	private final JButton btnXacNhan = new JButton("Xác nhận");
	private final JButton btnShow = new JButton("Hiện");
	private boolean isPasswordShown = false;
	private String currentMaNV = "";
	private JTable tbl;
	private DefaultTableModel model;
	private JPanel menuPanelDangXuat;

	private final JTextField txtMaNV_2;
	private final JTextField txtSDT_2;
	private final JTextField txtMaNV_1;
	private final JPasswordField txtMatKhau;
	private final JPasswordField txtMatKhauConfirm;
	private final JTextField txtCCCD;
	private final JPasswordField txtMatKhau_2;
	private final JTextField txtNgaySinh_2;
	private final JTextField txtHoTen_2;
	private final JPasswordField txtMatKhauOld;

	public GUI_NhanVienCuaHang(String maNV) throws Exception {
		setTitle("Phần mềm quản lý cửa hàng tiện lợi - Nhân viên");
		setSize(1000, 700);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setIconImage(new ImageIcon(GUI_NhanVienCuaHang.class.getResource("/images/store-icon.png")).getImage());

		this.currentMaNV = maNV;

		getContentPane().setLayout(null);

		// Left Panel
		JPanel leftPanel = new JPanel();
		leftPanel.setBorder(new LineBorder(new Color(0, 0, 0)));
		leftPanel.setBackground(new Color(204, 204, 255));
		leftPanel.setLocation(0, 0);
		leftPanel.setSize(271, 669);
		getContentPane().add(leftPanel, BorderLayout.WEST);
		leftPanel.setLayout(null);

		JPanel logoPanel = new JPanel();
		logoPanel.setBackground(new Color(0, 153, 255));
		logoPanel.setBounds(0, 0, 271, 92);
		leftPanel.add(logoPanel);
		logoPanel.setLayout(null);

		JLabel logoLabel = new JLabel("Quản lý cửa hàng");
		ImageIcon icon = new ImageIcon(GUI_NhanVienCuaHang.class.getResource("/images/store-icon.png"));
		Image scaledImage = icon.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
		logoLabel.setIcon(new ImageIcon(scaledImage));
		logoLabel.setHorizontalTextPosition(SwingConstants.RIGHT);
		logoLabel.setIconTextGap(10);
		logoLabel.setHorizontalAlignment(SwingConstants.LEFT);
		logoLabel.setFont(new Font("Dialog", Font.BOLD, 20));
		logoLabel.setForeground(Color.WHITE);
		logoLabel.setBounds(5, 5, 266, 87);
		logoPanel.add(logoLabel);

		JPanel menuPanel = new JPanel();
		menuPanel.setBackground(new Color(0, 0, 0, 0));
		menuPanel.setBounds(0, 90, 271, 579);
		leftPanel.add(menuPanel);
		menuPanel.setLayout(null);

		JPanel menuPanelInfo = new JPanel();
		menuPanelInfo.setBackground(new Color(36, 31, 49));
		menuPanelInfo.setBounds(0, 30, 271, 80);
		menuPanel.add(menuPanelInfo);
		menuPanelInfo.setLayout(new BorderLayout(0, 0));

		JLabel lblThongTinPhanMem = new JLabel("Thông Tin Phần Mềm");
		ImageIcon infoIcon = new ImageIcon(GUI_NhanVienCuaHang.class.getResource("/images/info.png"));
		Image scaledInfoImage = infoIcon.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
		lblThongTinPhanMem.setIcon(new ImageIcon(scaledInfoImage));
		lblThongTinPhanMem.setHorizontalTextPosition(SwingConstants.RIGHT);
		lblThongTinPhanMem.setIconTextGap(10);
		lblThongTinPhanMem.setHorizontalAlignment(SwingConstants.LEFT);
		lblThongTinPhanMem.setForeground(Color.WHITE);
		lblThongTinPhanMem.setHorizontalAlignment(SwingConstants.CENTER);
		lblThongTinPhanMem.setFont(new Font("Dialog", Font.BOLD, 20));
		menuPanelInfo.add(lblThongTinPhanMem, BorderLayout.CENTER);

		JPanel menuPanelSanPham = new JPanel();
		menuPanelSanPham.setBackground(new Color(36, 31, 49));
		menuPanelSanPham.setBounds(0, 130, 271, 80);
		menuPanel.add(menuPanelSanPham);
		menuPanelSanPham.setLayout(new BorderLayout(0, 0));

		JLabel lblQuanLySanPham = new JLabel("Quản Lý Sản Phẩm  ");
		ImageIcon qlspIcon = new ImageIcon(GUI_NhanVienCuaHang.class.getResource("/images/qlsp.png"));
		Image scaledQlspImage = qlspIcon.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
		lblQuanLySanPham.setIcon(new ImageIcon(scaledQlspImage));
		lblQuanLySanPham.setHorizontalTextPosition(SwingConstants.RIGHT);
		lblQuanLySanPham.setIconTextGap(10);
		lblQuanLySanPham.setHorizontalAlignment(SwingConstants.LEFT);
		lblQuanLySanPham.setForeground(Color.WHITE);
		lblQuanLySanPham.setHorizontalAlignment(SwingConstants.CENTER);
		lblQuanLySanPham.setFont(new Font("Dialog", Font.BOLD, 20));
		menuPanelSanPham.add(lblQuanLySanPham, BorderLayout.CENTER);

		JPanel menuPanelThanhToan = new JPanel();
		menuPanelThanhToan.setBackground(new Color(36, 31, 49));
		menuPanelThanhToan.setBounds(0, 230, 271, 80);
		menuPanel.add(menuPanelThanhToan);
		menuPanelThanhToan.setLayout(new BorderLayout(0, 0));

		JLabel lblThanhToan = new JLabel("Tạo Hóa Đơn             ");
		ImageIcon thanhToanIcon = new ImageIcon(GUI_NhanVienCuaHang.class.getResource("/images/thanhtoan.png"));
		Image scaledThanhToanImage = thanhToanIcon.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
		lblThanhToan.setIcon(new ImageIcon(scaledThanhToanImage));
		lblThanhToan.setHorizontalTextPosition(SwingConstants.RIGHT);
		lblThanhToan.setIconTextGap(10);
		lblThanhToan.setHorizontalAlignment(SwingConstants.LEFT);
		lblThanhToan.setForeground(Color.WHITE);
		lblThanhToan.setHorizontalAlignment(SwingConstants.CENTER);
		lblThanhToan.setFont(new Font("Dialog", Font.BOLD, 20));
		menuPanelThanhToan.add(lblThanhToan);

		JPanel menuPanelAdjustInfo = new JPanel();
		menuPanelAdjustInfo.setBackground(new Color(36, 31, 49));
		menuPanelAdjustInfo.setBounds(0, 330, 271, 80);
		menuPanel.add(menuPanelAdjustInfo);
		menuPanelAdjustInfo.setLayout(new BorderLayout(0, 0));

		JLabel lblChinhSuaThongTin = new JLabel("Thông Tin Nhân Viên");
		ImageIcon editIcon = new ImageIcon(GUI_NhanVienCuaHang.class.getResource("/images/edit.png"));
		Image scaledEditImage = editIcon.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
		lblChinhSuaThongTin.setIcon(new ImageIcon(scaledEditImage));
		lblChinhSuaThongTin.setHorizontalTextPosition(SwingConstants.RIGHT);
		lblChinhSuaThongTin.setIconTextGap(10);
		lblChinhSuaThongTin.setHorizontalAlignment(SwingConstants.LEFT);
		lblChinhSuaThongTin.setForeground(Color.WHITE);
		lblChinhSuaThongTin.setHorizontalAlignment(SwingConstants.CENTER);
		lblChinhSuaThongTin.setFont(new Font("Dialog", Font.BOLD, 20));
		menuPanelAdjustInfo.add(lblChinhSuaThongTin);

		JLabel lblWelcome = new JLabel("Welcome, " + currentMaNV);
		lblWelcome.setHorizontalAlignment(SwingConstants.CENTER);
		lblWelcome.setBounds(0, 620, 271, 28);
		lblWelcome.setFont(new Font("Dialog", Font.BOLD, 20));
		leftPanel.add(lblWelcome);

		// Content Panel
		JPanel contentPanel = new JPanel();
		contentPanel.setBounds(271, 0, 725, 669);
		getContentPane().add(contentPanel);
		contentPanel.setLayout(null);
		
		// Tạo panel đăng xuất
		menuPanelDangXuat = new JPanel();
		menuPanelDangXuat.setBackground(new Color(36, 31, 49));
		menuPanelDangXuat.setBounds(0, 430, 271, 60); // Đặt ở dưới cùng của menu
		menuPanel.add(menuPanelDangXuat);
		menuPanelDangXuat.setLayout(new BorderLayout(0, 0));

		JLabel lblDangXuat = new JLabel("Đăng xuất");
		lblDangXuat.setForeground(Color.WHITE);
		lblDangXuat.setHorizontalAlignment(SwingConstants.CENTER);
		lblDangXuat.setFont(new Font("Dialog", Font.BOLD, 18));
		menuPanelDangXuat.add(lblDangXuat, BorderLayout.CENTER);

		// Thêm biểu tượng đăng xuất (tùy chọn)
		JLabel lblIconDangXuat = new JLabel(new ImageIcon("images/logout_icon.png")); // Thay đổi đường dẫn nếu cần
		lblIconDangXuat.setHorizontalAlignment(SwingConstants.RIGHT);
		lblDangXuat.setIconTextGap(10);
		menuPanelDangXuat.add(lblIconDangXuat, BorderLayout.WEST);

		// Create table
		createTable();

		// Panel Thông tin phần mềm
		JPanel panelInfo = new JPanel();
		panelInfo.setBounds(0, 0, 725, 669);
		panelInfo.setLayout(null);

		JPanel panelTitle = new JPanel();
		panelTitle.setBounds(0, 12, 725, 68);
		panelInfo.add(panelTitle);
		panelTitle.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		JLabel lblPhanMem = new JLabel("Phần mềm quản lý cửa hàng tiện lợi");
		lblPhanMem.setForeground(new Color(0, 153, 255));
		lblPhanMem.setFont(new Font("Dialog", Font.BOLD, 30));
		panelTitle.add(lblPhanMem);

		JPanel panelNhom = new JPanel();
		panelNhom.setBounds(0, 72, 725, 45);
		panelInfo.add(panelNhom);
		panelNhom.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		JLabel lblNhom = new JLabel("Nhóm 7:");
		lblNhom.setFont(new Font("Dialog", Font.BOLD, 25));
		panelNhom.add(lblNhom);

		String[] thanhVien = { "Nguyễn Thanh Tuấn", "Nguyễn Văn Quốc", "Trần Đức Nam", "Nguyễn Trần Trọng Huy" };
		int y = 135;
		for (String tv : thanhVien) {
			JPanel panelTV = new JPanel();
			panelTV.setBounds(0, y, 725, 45);
			panelInfo.add(panelTV);
			panelTV.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
			JLabel lblTV = new JLabel(tv);
			lblTV.setFont(new Font("Dialog", Font.BOLD, 25));
			panelTV.add(lblTV);
			y += 70;
		}

		contentPanel.add(panelInfo);

		// Panel Quản lý sản phẩm
		panelSanPham.setBounds(0, 0, 725, 669);
		panelSanPham.setBackground(UIManager.getColor("Button.background"));
		contentPanel.add(panelSanPham);
		panelSanPham.setLayout(null);

		JPanel inputPanel = new JPanel();
		inputPanel.setBorder(
				new TitledBorder(null, "Thông tin sản phẩm", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		inputPanel.setBounds(0, 0, 715, 284);
		panelSanPham.add(inputPanel);
		inputPanel.setLayout(new BoxLayout(inputPanel, BoxLayout.X_AXIS));

		Box b = Box.createVerticalBox();
		Box b1 = Box.createHorizontalBox();
		Box b2 = Box.createHorizontalBox();
		Box b3 = Box.createHorizontalBox();
		Box b4 = Box.createHorizontalBox();

		lblMaSP.setFont(new Font("Arial", Font.PLAIN, 15));
		b1.add(lblMaSP);
		txtMaSP.setFont(new Font("Arial", Font.PLAIN, 15));
		b1.add(txtMaSP);

		lblTenSP.setFont(new Font("Arial", Font.PLAIN, 15));
		b2.add(lblTenSP);
		txtTenSP.setFont(new Font("Arial", Font.PLAIN, 15));
		b2.add(txtTenSP);

		lblGiaBan.setFont(new Font("Arial", Font.PLAIN, 15));
		b3.add(lblGiaBan);
		txtGiaBan.setFont(new Font("Arial", Font.PLAIN, 15));
		b3.add(txtGiaBan);

		lblLoaiSP.setFont(new Font("Arial", Font.PLAIN, 15));
		b4.add(lblLoaiSP);
		cboLoaiSP.addItem("Đồ uống");
		cboLoaiSP.addItem("Thực phẩm");
		cboLoaiSP.addItem("Hàng gia dụng");
		cboLoaiSP.setFont(new Font("Arial", Font.PLAIN, 15));
		btnThem.setFont(new Font("Arial", Font.BOLD, 15));
	    btnThem.setBackground(new Color(0, 153, 255)); // Xanh biển
	    btnThem.setForeground(Color.WHITE);
	    btnThem.setPreferredSize(new Dimension(100, 30));
	    btnThem.setToolTipText("Thêm sản phẩm mới");
	    b4.add(btnThem);
	    b4.add(Box.createHorizontalStrut(10));

	    btnXoa.setFont(new Font("Arial", Font.BOLD, 15));
	    btnXoa.setBackground(new Color(255, 51, 51)); // Đỏ
	    btnXoa.setForeground(Color.WHITE);
	    btnXoa.setPreferredSize(new Dimension(100, 30));
	    btnXoa.setToolTipText("Xóa sản phẩm đã chọn");
	    b4.add(btnXoa);
	    b4.add(Box.createHorizontalStrut(10));

	    btnLuu.setFont(new Font("Arial", Font.BOLD, 15));
	    btnLuu.setBackground(new Color(0, 204, 102)); // Xanh lá cây
	    btnLuu.setForeground(Color.WHITE);
	    btnLuu.setPreferredSize(new Dimension(100, 30));
	    btnLuu.setToolTipText("Lưu chỉnh sửa sản phẩm");
	    b4.add(btnLuu);
	    b4.add(Box.createHorizontalStrut(10));

	    btnHuy.setFont(new Font("Arial", Font.BOLD, 15));
	    btnHuy.setBackground(new Color(255, 153, 0)); // Cam
	    btnHuy.setForeground(Color.WHITE);
	    btnHuy.setPreferredSize(new Dimension(100, 30));
	    btnHuy.setToolTipText("Xóa trắng các trường nhập");
	    b4.add(btnHuy);
	    b4.add(Box.createHorizontalStrut(10));

	    btnTimKiem.setFont(new Font("Arial", Font.BOLD, 15));
	    btnTimKiem.setBackground(new Color(153, 153, 153)); // Xám
	    btnTimKiem.setForeground(Color.WHITE);
	    btnTimKiem.setPreferredSize(new Dimension(100, 30));
	    btnTimKiem.setToolTipText("Tìm kiếm sản phẩm theo mã");
	    b4.add(btnTimKiem);

	    b.add(b1);
	    b.add(Box.createVerticalStrut(10));
	    b.add(b2);
	    b.add(Box.createVerticalStrut(10));
	    b.add(b3);
	    b.add(Box.createVerticalStrut(10));
	    b.add(b4);
	    b.add(Box.createVerticalStrut(20));

	    inputPanel.add(b);

		JScrollPane tblPane = new JScrollPane(tbl);
		tblPane.setBorder(BorderFactory.createTitledBorder("Danh sách sản phẩm"));
		tblPane.setBounds(0, 285, 715, 384);
		panelSanPham.add(tblPane);
		
		// Panel Chỉnh sửa thông tin
		JPanel panelAdjustInfo = new JPanel();
		panelAdjustInfo.setBounds(0, 0, 725, 669);
		contentPanel.add(panelAdjustInfo);
		panelAdjustInfo.setLayout(new BoxLayout(panelAdjustInfo, BoxLayout.Y_AXIS));

		Box b10_1 = Box.createVerticalBox();
		b10_1.setBorder(BorderFactory.createTitledBorder("Đổi mật khẩu"));
		panelAdjustInfo.add(b10_1);

		JPanel p1 = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JLabel lblMaNV_1 = new JLabel("Mã nhân viên: ");
		lblMaNV_1.setFont(new Font("Arial", Font.BOLD, 20));
		p1.add(lblMaNV_1);
		txtMaNV_1 = new JTextField(25);
		txtMaNV_1.setText(currentMaNV);
		txtMaNV_1.setEditable(false);
		txtMaNV_1.setFont(new Font("Arial", Font.PLAIN, 20));
		p1.add(txtMaNV_1);
		b10_1.add(p1);
		b10_1.add(Box.createVerticalStrut(20));

		JPanel p2 = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JLabel lblMatKhauCu = new JLabel("Mật khẩu cũ: ");
		lblMatKhauCu.setFont(new Font("Arial", Font.BOLD, 20));
		p2.add(lblMatKhauCu);
		txtMatKhauOld = new JPasswordField(20);
		txtMatKhauOld.setFont(new Font("Arial", Font.PLAIN, 20));
		p2.add(txtMatKhauOld);
		btnShow.setPreferredSize(new Dimension(80, 30));
		p2.add(btnShow);
		b10_1.add(p2);
		b10_1.add(Box.createVerticalStrut(20));

		JPanel p3 = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JLabel lblMatKhauMoi = new JLabel("Mật khẩu mới: ");
		lblMatKhauMoi.setFont(new Font("Dialog", Font.BOLD, 20));
		p3.add(lblMatKhauMoi);
		txtMatKhau = new JPasswordField(25);
		txtMatKhau.setFont(new Font("Dialog", Font.PLAIN, 20));
		p3.add(txtMatKhau);
		b10_1.add(p3);
		b10_1.add(Box.createVerticalStrut(20));

		JPanel p4 = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JLabel lblXacNhanMatKhau = new JLabel("Xác nhận mật khẩu: ");
		lblXacNhanMatKhau.setFont(new Font("Dialog", Font.BOLD, 20));
		p4.add(lblXacNhanMatKhau);
		txtMatKhauConfirm = new JPasswordField(25);
		txtMatKhauConfirm.setFont(new Font("Dialog", Font.PLAIN, 20));
		p4.add(txtMatKhauConfirm);
		b10_1.add(p4);
		b10_1.add(Box.createVerticalStrut(15));

		JPanel p5 = new JPanel();
		btnXacNhan.setFont(new Font("Arial", Font.BOLD, 16));
		p5.add(btnXacNhan);
		b10_1.add(p5);

		Dimension labelSize = new Dimension(200, 30);
		lblMaNV_1.setPreferredSize(labelSize);
		lblMatKhauCu.setPreferredSize(labelSize);
		lblMatKhauMoi.setPreferredSize(labelSize);
		lblXacNhanMatKhau.setPreferredSize(labelSize);

		Box b10_2 = Box.createVerticalBox();
		panelAdjustInfo.add(b10_2);
		b10_2.setBorder(BorderFactory.createTitledBorder("Thông tin nhân viên"));
		b10_2.add(Box.createVerticalStrut(15));

		Dimension labelSize2 = new Dimension(200, 30);

		Box b11_2 = Box.createHorizontalBox();
		JLabel lblMaNV = new JLabel("Mã nhân viên: ");
		lblMaNV.setFont(new Font("Arial", Font.BOLD, 20));
		lblMaNV.setPreferredSize(labelSize2);
		b11_2.add(lblMaNV);
		txtMaNV_2 = new JTextField(20);
		txtMaNV_2.setEditable(false);
		txtMaNV_2.setFont(new Font("Arial", Font.PLAIN, 20));
		b11_2.add(txtMaNV_2);
		b10_2.add(b11_2);

		Box b12_2 = Box.createHorizontalBox();
		JLabel lblMatKhau = new JLabel("Mật khẩu: ");
		lblMatKhau.setFont(new Font("Arial", Font.BOLD, 20));
		lblMatKhau.setPreferredSize(labelSize2);
		b12_2.add(lblMatKhau);
		txtMatKhau_2 = new JPasswordField(20);
		txtMatKhau_2.setEditable(false);
		txtMatKhau_2.setFont(new Font("Arial", Font.PLAIN, 20));
		b12_2.add(txtMatKhau_2);
		b10_2.add(b12_2);

		Box b13_2 = Box.createHorizontalBox();
		JLabel lblHoTen = new JLabel("Họ tên: ");
		lblHoTen.setFont(new Font("Dialog", Font.BOLD, 20));
		lblHoTen.setPreferredSize(labelSize2);
		b13_2.add(lblHoTen);
		txtHoTen_2 = new JTextField(20);
		txtHoTen_2.setEditable(false);
		txtHoTen_2.setFont(new Font("Dialog", Font.PLAIN, 20));
		b13_2.add(txtHoTen_2);
		b10_2.add(b13_2);

		Box b14_2 = Box.createHorizontalBox();
		JLabel lblNgaySinh = new JLabel("Ngày sinh: ");
		lblNgaySinh.setFont(new Font("Dialog", Font.BOLD, 20));
		lblNgaySinh.setPreferredSize(labelSize2);
		b14_2.add(lblNgaySinh);
		txtNgaySinh_2 = new JTextField(20);
		txtNgaySinh_2.setEditable(false);
		txtNgaySinh_2.setFont(new Font("Dialog", Font.PLAIN, 20));
		b14_2.add(txtNgaySinh_2);
		b10_2.add(b14_2);

		Box b15_2 = Box.createHorizontalBox();
		JLabel lblSDT = new JLabel("Số điện thoại: ");
		lblSDT.setFont(new Font("Dialog", Font.BOLD, 20));
		lblSDT.setPreferredSize(labelSize2);
		b15_2.add(lblSDT);
		txtSDT_2 = new JTextField(20);
		txtSDT_2.setEditable(false);
		txtSDT_2.setFont(new Font("Dialog", Font.PLAIN, 20));
		b15_2.add(txtSDT_2);
		b10_2.add(b15_2);

		Box b16_2 = Box.createHorizontalBox();
		JLabel lblCCCD = new JLabel("CCCD: ");
		lblCCCD.setFont(new Font("Dialog", Font.BOLD, 20));
		lblCCCD.setPreferredSize(labelSize2);
		b16_2.add(lblCCCD);
		txtCCCD = new JTextField(20);
		txtCCCD.setEditable(false);
		txtCCCD.setFont(new Font("Dialog", Font.PLAIN, 20));
		b16_2.add(txtCCCD);
		b10_2.add(b16_2);

		// Ban đầu chỉ hiển thị panel thông tin
		panelSanPham.setVisible(false);
		panelAdjustInfo.setVisible(false);
		
		menuPanelDangXuat.addMouseListener(new MouseAdapter() {
		    @Override
		    public void mouseClicked(MouseEvent e) {
		        int confirm = JOptionPane.showConfirmDialog(
		                GUI_NhanVienCuaHang.this,
		                "Bạn có chắc chắn muốn đăng xuất không?",
		                "Xác nhận đăng xuất",
		                JOptionPane.YES_NO_OPTION);
		                
		        if (confirm == JOptionPane.YES_OPTION) {
//		            // Xóa dữ liệu giỏ hàng khi đăng xuất
//		            GioHang.getInstance().xoaTatCa();
		            
		            // Đóng cửa sổ hiện tại
		            dispose();
		            
		            // Mở form đăng nhập
		            FormDangNhap loginForm = new FormDangNhap();
		            loginForm.setVisible(true);
		        }
		    }

		    @Override
		    public void mouseEntered(MouseEvent e) {
		        menuPanelDangXuat.setBackground(new Color(232, 17, 35)); // Màu đỏ khi hover
		        setCursor(new Cursor(Cursor.HAND_CURSOR));
		    }

		    @Override
		    public void mouseExited(MouseEvent e) {
		        menuPanelDangXuat.setBackground(new Color(36, 31, 49));
		        setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
		    }
		});


		// Menu handlers
		menuPanelInfo.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				panelInfo.setVisible(true);
				panelSanPham.setVisible(false);
				panelAdjustInfo.setVisible(false);
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				menuPanelInfo.setBackground(new Color(0, 204, 255));
				setCursor(new Cursor(Cursor.HAND_CURSOR));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				menuPanelInfo.setBackground(new Color(36, 31, 49));
				setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}
		});

		menuPanelSanPham.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				panelInfo.setVisible(false);
				panelSanPham.setVisible(true);
				panelAdjustInfo.setVisible(false);
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				menuPanelSanPham.setBackground(new Color(0, 204, 255));
				setCursor(new Cursor(Cursor.HAND_CURSOR));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				menuPanelSanPham.setBackground(new Color(36, 31, 49));
				setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}
		});

		menuPanelThanhToan.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				panelInfo.setVisible(false);
				panelSanPham.setVisible(false);
				panelAdjustInfo.setVisible(false);
				FormKhachHang form = new FormKhachHang(currentMaNV);
				form.setVisible(true);
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				menuPanelThanhToan.setBackground(new Color(0, 204, 255));
				setCursor(new Cursor(Cursor.HAND_CURSOR));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				menuPanelThanhToan.setBackground(new Color(36, 31, 49));
				setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}
		});

		menuPanelAdjustInfo.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				panelInfo.setVisible(false);
				panelSanPham.setVisible(false);
				panelAdjustInfo.setVisible(true);
				try {
					loadDataToPanelAdjustInfo();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				menuPanelAdjustInfo.setBackground(new Color(0, 204, 255));
				setCursor(new Cursor(Cursor.HAND_CURSOR));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				menuPanelAdjustInfo.setBackground(new Color(36, 31, 49));
				setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}
		});

		// Action listeners
		btnThem.addActionListener(this);
		btnXoa.addActionListener(this);
		btnLuu.addActionListener(this);
		btnHuy.addActionListener(this);
		btnTimKiem.addActionListener(this);
		btnXacNhan.addActionListener(this);
		btnShow.addActionListener(this);

		tbl.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int row = tbl.getSelectedRow();
				txtMaSP.setText(tbl.getValueAt(row, 0).toString());
				txtTenSP.setText(tbl.getValueAt(row, 1).toString());
				txtGiaBan.setText(tbl.getValueAt(row, 2).toString());
				cboLoaiSP.setSelectedItem(tbl.getValueAt(row, 3).toString());
			}
		});

		loadDataToPanelAdjustInfo();
	}

	private void createTable() {
		String[] tblCols = { "Mã sản phẩm", "Tên sản phẩm", "Giá bán", "Loại sản phẩm" };
		model = new DefaultTableModel(tblCols, 0) {
			private static final long serialVersionUID = 1L;

			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		tbl = new JTable(model);
		tbl.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tbl.getColumnModel().getColumn(0).setPreferredWidth(50);
		tbl.getColumnModel().getColumn(1).setPreferredWidth(150);
		tbl.getColumnModel().getColumn(2).setPreferredWidth(80);
		tbl.getColumnModel().getColumn(3).setPreferredWidth(100);
		tbl.setRowHeight(30);
		tbl.setFont(new Font("Arial", Font.PLAIN, 20));

		refreshTable();
	}

	private void refreshTable() {
		model.setRowCount(0);
		SanPham_DAO spDAO = new SanPham_DAO();
		List<SanPham> list = null;
		try {
			list = spDAO.getAllSanPham();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for (SanPham sp : list) {
			model.addRow(new Object[] { sp.getMaSP(), sp.getTenSP(), sp.getGiaBan(), sp.getLoaiSP() });
		}
	}

	private void loadDataToPanelAdjustInfo() throws Exception {
		NhanVien_DAO nvDAO = new NhanVien_DAO();
		NhanVien nv = nvDAO.getNhanVienByID(currentMaNV);
		if (nv != null) {
			txtMaNV_2.setText(nv.getMaNV());
			txtMatKhau_2.setText(nv.getMatKhau());
			txtHoTen_2.setText(nv.getTenNV());
			txtNgaySinh_2.setText(nv.getNgaySinh().format(java.time.format.DateTimeFormatter.ofPattern("dd/MM/yyyy")));
			txtSDT_2.setText(nv.getSDT());
			txtCCCD.setText(nv.getCccd());
		}
	}

	private void clearInputs() {
		txtMaSP.setText("");
		txtTenSP.setText("");
		txtGiaBan.setText("");
		cboLoaiSP.setSelectedIndex(0);
	}

	@SuppressWarnings("deprecation")
	@Override
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();

		if (o.equals(btnThem)) {
			try {
				SanPham_DAO spDAO = new SanPham_DAO();
				String maSP = txtMaSP.getText().trim();
				if (spDAO.getSanPhamByID(maSP) != null) {
					throw new Exception("Mã sản phẩm đã tồn tại");
				}
				if (!txtGiaBan.getText().matches("\\d+(\\.\\d+)?")) {
					throw new Exception("Giá bán phải là số");
				}
				SanPham sp = new SanPham(maSP, txtTenSP.getText(), Double.parseDouble(txtGiaBan.getText()),
						cboLoaiSP.getSelectedItem().toString());
				spDAO.addSanPham(sp);
				clearInputs();
				JOptionPane.showMessageDialog(null, "Thêm thành công");
				refreshTable();
			} catch (Exception e2) {
				JOptionPane.showMessageDialog(null, "Lỗi: " + e2.getMessage());
			}
		} else if (o.equals(btnXoa)) {
			int row = tbl.getSelectedRow();
			if (row == -1) {
				JOptionPane.showMessageDialog(this, "Phải chọn một dòng để xóa");
			} else if (JOptionPane.showConfirmDialog(this,
					"Bạn có chắc chắn xóa sản phẩm " + tbl.getValueAt(row, 0) + " không?", "Cảnh báo",
					JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
				SanPham_DAO spDAO = new SanPham_DAO();
				try {
					spDAO.deleteSanPham(tbl.getValueAt(row, 0).toString());
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				clearInputs();
				refreshTable();
				JOptionPane.showMessageDialog(this, "Đã xóa thành công");
			}
		} else if (o.equals(btnLuu)) {
			int row = tbl.getSelectedRow();
			if (row != -1) {
				try {
					if (!txtGiaBan.getText().matches("\\d+(\\.\\d+)?")) {
						throw new Exception("Giá bán phải là số");
					}
					SanPham_DAO spDAO = new SanPham_DAO();
					SanPham sp = new SanPham(txtMaSP.getText(), txtTenSP.getText(),
							Double.parseDouble(txtGiaBan.getText()), cboLoaiSP.getSelectedItem().toString());
					spDAO.editSanPhamByID(tbl.getValueAt(row, 0).toString(), sp);
					clearInputs();
					JOptionPane.showMessageDialog(null, "Cập nhật thành công");
					refreshTable();
				} catch (Exception e2) {
					JOptionPane.showMessageDialog(null, "Lỗi: " + e2.getMessage());
				}
			} else {
				JOptionPane.showMessageDialog(null, "Chọn một sản phẩm để cập nhật");
			}
		} else if (o.equals(btnHuy)) {
			clearInputs();
		} else if (o.equals(btnTimKiem)) {
			String input = JOptionPane.showInputDialog(null, "Nhập mã sản phẩm cần tìm: ");
			if (input != null && !input.trim().isEmpty()) {
				SanPham_DAO spDAO = new SanPham_DAO();
				SanPham sp = null;
				try {
					sp = spDAO.getSanPhamByID(input);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				if (sp != null) {
					for (int i = 0; i < tbl.getRowCount(); i++) {
						if (tbl.getValueAt(i, 0).toString().equalsIgnoreCase(input)) {
							tbl.setRowSelectionInterval(i, i);
							break;
						}
					}
				} else {
					JOptionPane.showMessageDialog(null, "Không tìm thấy sản phẩm");
				}
			} else {
				JOptionPane.showMessageDialog(null, "Mã sản phẩm không được để trống");
			}
		} else if (o.equals(btnShow)) {
			isPasswordShown = !isPasswordShown;
			if (isPasswordShown) {
				txtMatKhauOld.setEchoChar((char) 0);
			} else {
				txtMatKhauOld.setEchoChar('*');
			}
		} else if (o.equals(btnXacNhan)) {
			if (txtMatKhau.getText().equals(txtMatKhauConfirm.getText())) {
				NhanVien_DAO nvDAO = new NhanVien_DAO();
				try {
					if (nvDAO.checkLogin(currentMaNV, txtMatKhauOld.getText())) {
						try {
							NhanVien nv = nvDAO.getNhanVienByID(currentMaNV);
							nv.setMatKhau(txtMatKhau.getText());
							nvDAO.editNhanVienByID(currentMaNV, nv);
							JOptionPane.showMessageDialog(null, "Đổi mật khẩu thành công!");
							txtMatKhauOld.setText("");
							txtMatKhau.setText("");
							txtMatKhauConfirm.setText("");
							loadDataToPanelAdjustInfo();
						} catch (Exception e1) {
							JOptionPane.showMessageDialog(null, e1.getMessage());
						}
					} else {
						JOptionPane.showMessageDialog(null, "Mật khẩu cũ sai!");
					}
				} catch (HeadlessException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			} else {
				JOptionPane.showMessageDialog(null, "Mật khẩu xác nhận không khớp!");
			}
		}
	}
}