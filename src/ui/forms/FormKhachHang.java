package ui.forms;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;

import dao.HoaDon_DAO;
import dao.KhachHang_DAO;
import dao.SanPham_DAO;
import entity.*;

public class FormKhachHang extends JDialog implements ActionListener {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String maNV;
    private JTextField txtMaKH;
    private JTextField txtHoTenKH;
    private JTextField txtSDT;
    private JButton btnThanhToan;
    private JButton btnKiemTraKhachHang;
    private JButton btnThanhToanCu;
    private JTextField txtMaKH_2;
    private JTextField txtHoTen_2;
    private JTextField txtSDT_2;
    private String currentMaKH;

    public FormKhachHang(String maNV) {
        this.setTitle("Thanh toán hóa đơn");
        this.setModal(true);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setSize(600, 360);
        this.setLocationRelativeTo(null);

        this.maNV = maNV;
        getContentPane().setLayout(null);

        JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
        tabbedPane.setBounds(0, 0, 596, 327);
        getContentPane().add(tabbedPane);

        JPanel mainPanel = new JPanel();
        JPanel mainPanel2 = new JPanel();

        tabbedPane.addTab("Khách hàng mới", mainPanel);
        tabbedPane.addTab("Khách hàng cũ", mainPanel2);
        mainPanel2.setLayout(null);

        Box b_1 = Box.createVerticalBox();
        b_1.setBounds(0, 0, 591, 298);
        mainPanel2.add(b_1);

        Box b1_1 = Box.createHorizontalBox();
        b_1.add(b1_1);

        JLabel lblNhapSDT = new JLabel("Nhập số điện thoại khách hàng cũ:");
        lblNhapSDT.setFont(new Font("Dialog", Font.BOLD, 20));
        b1_1.add(lblNhapSDT);

        b_1.add(Box.createVerticalStrut(10));

        Box b4_1 = Box.createHorizontalBox();
        b_1.add(b4_1);

        JLabel lblSDT = new JLabel("Số điện thoại:");
        lblSDT.setFont(new Font("Dialog", Font.BOLD, 20));
        b4_1.add(lblSDT);

        b4_1.add(Box.createHorizontalStrut(50));

        txtSDT_2 = new JTextField();
        txtSDT_2.setFont(new Font("Dialog", Font.PLAIN, 20));
        txtSDT_2.setColumns(10);
        b4_1.add(txtSDT_2);

        b_1.add(Box.createVerticalStrut(30));

        Box b5_1 = Box.createHorizontalBox();
        b_1.add(b5_1);

        JLabel lblMaKH = new JLabel("Mã khách hàng:");
        lblMaKH.setFont(new Font("Dialog", Font.BOLD, 20));
        b5_1.add(lblMaKH);

        b5_1.add(Box.createHorizontalStrut(54));

        txtMaKH_2 = new JTextField();
        txtMaKH_2.setFont(new Font("Dialog", Font.PLAIN, 20));
        txtMaKH_2.setEditable(false);
        b5_1.add(txtMaKH_2);
        txtMaKH_2.setColumns(10);

        b_1.add(Box.createVerticalStrut(10));

        Box b5_1_1 = Box.createHorizontalBox();
        b_1.add(b5_1_1);

        JLabel lblHoTen = new JLabel("Họ tên khách hàng:");
        lblHoTen.setFont(new Font("Dialog", Font.BOLD, 20));
        b5_1_1.add(lblHoTen);

        b5_1_1.add(Box.createHorizontalStrut(20));

        txtHoTen_2 = new JTextField();
        txtHoTen_2.setFont(new Font("Dialog", Font.PLAIN, 20));
        txtHoTen_2.setEditable(false);
        txtHoTen_2.setColumns(10);
        b5_1_1.add(txtHoTen_2);

        b_1.add(Box.createVerticalStrut(20));

        Box b6_1 = Box.createHorizontalBox();
        b_1.add(b6_1);

        btnKiemTraKhachHang = new JButton("Kiểm tra");
        btnKiemTraKhachHang.setFont(new Font("Dialog", Font.BOLD, 20));
        b6_1.add(btnKiemTraKhachHang);

        b6_1.add(Box.createHorizontalStrut(20));

        btnThanhToanCu = new JButton("Thanh toán");
        btnThanhToanCu.setFont(new Font("Dialog", Font.BOLD, 20));
        b6_1.add(btnThanhToanCu);

        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        Box b = Box.createVerticalBox();
        Box b1 = Box.createHorizontalBox();
        Box b2 = Box.createHorizontalBox();
        Box b3 = Box.createHorizontalBox();
        Box b6 = Box.createHorizontalBox();

        JLabel lblMaKHNew = new JLabel("Mã khách hàng: ");
        lblMaKHNew.setFont(new Font("Dialog", Font.BOLD, 20));
        JLabel lblHoTenKH = new JLabel("Họ tên khách hàng: ");
        lblHoTenKH.setFont(new Font("Dialog", Font.BOLD, 20));
        JLabel lblSDTNew = new JLabel("Số điện thoại: ");
        lblSDTNew.setFont(new Font("Dialog", Font.BOLD, 20));

        b1.add(lblMaKHNew);
        b2.add(lblHoTenKH);
        b3.add(lblSDTNew);

        txtMaKH = new JTextField();
        txtMaKH.setFont(new Font("Dialog", Font.PLAIN, 20));
        b1.add(txtMaKH);
        txtMaKH.setColumns(10);

        txtHoTenKH = new JTextField();
        txtHoTenKH.setFont(new Font("Dialog", Font.PLAIN, 20));
        txtHoTenKH.setColumns(10);
        b2.add(txtHoTenKH);

        txtSDT = new JTextField();
        txtSDT.setFont(new Font("Dialog", Font.PLAIN, 20));
        txtSDT.setColumns(10);
        b3.add(txtSDT);

        btnThanhToan = new JButton("Thanh toán");
        btnThanhToan.setFont(new Font("Dialog", Font.BOLD, 20));
        b6.add(btnThanhToan);

        b.add(Box.createVerticalStrut(20));
        b.add(b1);
        b.add(Box.createVerticalStrut(10));
        b.add(b2);
        b.add(Box.createVerticalStrut(10));
        b.add(b3);
        b.add(Box.createVerticalStrut(20));
        b.add(b6);
        b.add(Box.createVerticalStrut(10));

        mainPanel.add(b);

        btnThanhToan.addActionListener(this);
        btnKiemTraKhachHang.addActionListener(this);
        btnThanhToanCu.addActionListener(this);
    }

    public void handleThanhToan(KhachHang kh, boolean isNewKhachHang) {
        try {
            // Nếu là khách hàng mới thì thêm vào CSDL
            if (isNewKhachHang) {
                KhachHang_DAO khDAO = new KhachHang_DAO();
                khDAO.addKhachHang(kh);
            }

            // Tạo mã hóa đơn tự động
            HoaDon_DAO hdDAO = new HoaDon_DAO();
            String maHD = "HD" + String.format("%03d", hdDAO.countHoaDon() + 1);

            // Tạo hóa đơn mới
            NhanVien nv = new NhanVien(maNV);
            HoaDon hd = new HoaDon(maHD, nv, kh, LocalDate.now());

            // Nhập sản phẩm vào hóa đơn
            int soSanPham = Integer.parseInt(JOptionPane.showInputDialog(null, "Nhập số sản phẩm trong hóa đơn: "));
            for (int i = 0; i < soSanPham; i++) {
                String maSP = JOptionPane.showInputDialog(null, "Nhập mã sản phẩm thứ " + (i + 1) + ": ");
                int soLuong = Integer.parseInt(JOptionPane.showInputDialog(null, "Nhập số lượng: "));
                
                // Giả sử có phương thức getSanPhamTheoMa trong DAO
                SanPham_DAO spDAO = new SanPham_DAO();
                SanPham sp = spDAO.getSanPhamByID(maSP);

                if (sp != null) {
                    ChiTietHoaDon ct = new ChiTietHoaDon(sp, soLuong);
                    hd.themChiTiet(ct);
                } else {
                    JOptionPane.showMessageDialog(null, "Không tìm thấy sản phẩm có mã: " + maSP);
                }
            }

            // Lưu hóa đơn và chi tiết vào CSDL
            hdDAO.addHoaDon(hd);

            JOptionPane.showMessageDialog(null, "Thanh toán thành công!\nMã hóa đơn: " + maHD + "\nTổng tiền: " + hd.getTongTien());
            dispose();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Lỗi: " + e.getMessage());
        }
    }



    @Override
    public void actionPerformed(ActionEvent e) {
        Object o = e.getSource();
        if (o == btnThanhToan) {
            String maKH = txtMaKH.getText().trim();
            String hoTenKH = txtHoTenKH.getText().trim();
            String sdt = txtSDT.getText().trim();

            try {
                KhachHang kh = new KhachHang(maKH, hoTenKH, sdt);
                handleThanhToan(kh, true);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage());
            }
        } else if (o == btnKiemTraKhachHang) {
            KhachHang_DAO khachHangDAO = new KhachHang_DAO();
            String sdt = txtSDT_2.getText().trim();
            try {
                for (KhachHang k : khachHangDAO.getAllKhachHang()) {
                    if (k.getSDT().equals(sdt)) {
                        txtMaKH_2.setText(k.getMaKH());
                        txtHoTen_2.setText(k.getTenKH());
                        currentMaKH = k.getMaKH();
                        return;
                    }
                }
                txtMaKH_2.setText("");
                txtHoTen_2.setText("");
                JOptionPane.showMessageDialog(null, "Không tìm thấy khách hàng với số điện thoại này!");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage());
            }
        } else if (o == btnThanhToanCu) {
            try {
                if (currentMaKH == null) {
                    throw new Exception("Chưa kiểm tra khách hàng!");
                }
                KhachHang kh = new KhachHang_DAO().getKhachHangByID(currentMaKH);
                handleThanhToan(kh, false);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage());
            }
        }
    }

	public void addSanPhamToHoaDon(String maSP) {
		// TODO Auto-generated method stub
		
	}

	
}