package ui.forms;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import dao.NhanVien_DAO;

public class FormDangNhap extends JDialog implements ActionListener {
    private static final long serialVersionUID = 1L;
    private JTextField txtMaNV;
    private JPasswordField txtMatKhau;
    private JCheckBox showPasswordCheckBox;
    private JButton btnDangNhap;
    private JButton btnThoat;
    private NhanVien_DAO nhanVienDAO = new NhanVien_DAO();
    public String maNVDangNhap;

    public String getMaNVDangNhap() {
        return maNVDangNhap;
    }

    public FormDangNhap() {
        setTitle("Đăng Nhập Hệ Thống");
        setSize(600, 400);
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        setModal(true);
        setIconImage(new ImageIcon(FormDangNhap.class.getResource("/images/store-icon.png")).getImage());

        // Panel chính với hình ảnh nền
        JPanel mainPanel = new JPanel(new GridBagLayout()) {
            private Image backgroundImage;

            {
                // Tải hình ảnh nền
                try {
                    String imagePath = "/images/anhnenn.jpg";
                    java.net.URL imageURL = FormDangNhap.class.getResource(imagePath);
                    if (imageURL == null) {
                        throw new Exception("Không tìm thấy hình ảnh tại: " + imagePath);
                    }
                    backgroundImage = new ImageIcon(imageURL).getImage();
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(FormDangNhap.this, "Lỗi tải hình ảnh nền: " + e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
                    e.printStackTrace();
                    backgroundImage = null;
                }
            }

            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                if (backgroundImage != null) {
                    // Vẽ hình ảnh nền, co giãn để vừa với kích thước panel
                    g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
                    // Thêm lớp phủ mờ để văn bản dễ đọc hơn
                    g.setColor(new Color(0, 0, 0, 150)); // Tăng độ mờ
                    g.fillRect(0, 0, getWidth(), getHeight());
                } else {
                    // Nếu không tải được hình ảnh, sử dụng màu nền mặc định
                    g.setColor(Color.BLACK);
                    g.fillRect(0, 0, getWidth(), getHeight());
                }
            }
        };
        mainPanel.setOpaque(false); // Đặt trong suốt để hình ảnh nền hiển thị
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Tiêu đề
        JLabel titleLabel = new JLabel("Đăng Nhập Hệ Thống");
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 24));
        titleLabel.setForeground(Color.WHITE); // Đổi màu chữ thành trắng
        titleLabel.setOpaque(false); // Đặt trong suốt
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        mainPanel.add(titleLabel, gbc);

        // Nhãn và trường nhập mã nhân viên
        JLabel lblMaNV = new JLabel("Mã đăng nhập:");
        lblMaNV.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        lblMaNV.setForeground(Color.WHITE); // Đổi màu chữ thành trắng
        lblMaNV.setOpaque(false); // Đặt trong suốt
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.WEST;
        mainPanel.add(lblMaNV, gbc);

        txtMaNV = new JTextField();
        txtMaNV.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        txtMaNV.setPreferredSize(new Dimension(200, 30));
        txtMaNV.setOpaque(true);
        txtMaNV.setBackground(Color.WHITE); // Giữ nền trắng để dễ đọc
        gbc.gridx = 1;
        gbc.gridy = 1;
        mainPanel.add(txtMaNV, gbc);

        // Nhãn và trường nhập mật khẩu
        JLabel lblMatKhau = new JLabel("Mật khẩu:");
        lblMatKhau.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        lblMatKhau.setForeground(Color.WHITE); // Đổi màu chữ thành trắng
        lblMatKhau.setOpaque(false); // Đặt trong suốt
        gbc.gridx = 0;
        gbc.gridy = 2;
        mainPanel.add(lblMatKhau, gbc);

        txtMatKhau = new JPasswordField();
        txtMatKhau.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        txtMatKhau.setPreferredSize(new Dimension(200, 30));
        txtMatKhau.setEchoChar('•');
        txtMatKhau.setOpaque(true);
        txtMatKhau.setBackground(Color.WHITE); // Giữ nền trắng để dễ đọc
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.gridheight = 1;
        mainPanel.add(txtMatKhau, gbc);

        // Checkbox hiển thị mật khẩu
        showPasswordCheckBox = new JCheckBox("Hiển thị mật khẩu");
        showPasswordCheckBox.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        showPasswordCheckBox.setForeground(Color.WHITE); // Đổi màu chữ thành trắng
        showPasswordCheckBox.setOpaque(false); // Đặt trong suốt
        showPasswordCheckBox.addActionListener(e -> {
            if (showPasswordCheckBox.isSelected()) {
                txtMatKhau.setEchoChar((char) 0);
            } else {
                txtMatKhau.setEchoChar('•');
            }
        });
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.WEST;
        mainPanel.add(showPasswordCheckBox, gbc);

        // Panel chứa nút Đăng nhập và Thoát
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 0));
        buttonPanel.setOpaque(false); // Đặt trong suốt

        // Nút Đăng nhập
        btnDangNhap = new JButton("Đăng Nhập");
        btnDangNhap.setOpaque(false); // Đặt trong suốt
        btnDangNhap.setContentAreaFilled(false); // Loại bỏ việc vẽ nền mặc định
        btnDangNhap.setForeground(Color.WHITE); // Đổi màu chữ thành trắng
        btnDangNhap.setFont(new Font("Segoe UI", Font.BOLD, 16));
        btnDangNhap.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2)); // Thêm viền trắng
        btnDangNhap.setFocusPainted(false);
        btnDangNhap.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                btnDangNhap.setOpaque(true);
                btnDangNhap.setContentAreaFilled(true);
                btnDangNhap.setBackground(new Color(50, 168, 255)); // Hiệu ứng hover
            }

            @Override
            public void mouseExited(MouseEvent e) {
                btnDangNhap.setOpaque(false);
                btnDangNhap.setContentAreaFilled(false);
                btnDangNhap.setBackground(null); // Trở về trong suốt
            }
        });
        buttonPanel.add(btnDangNhap);

        // Nút Thoát
        btnThoat = new JButton("Thoát");
        btnThoat.setOpaque(false); // Đặt trong suốt
        btnThoat.setContentAreaFilled(false); // Loại bỏ việc vẽ nền mặc định
        btnThoat.setForeground(Color.WHITE); // Đổi màu chữ thành trắng
        btnThoat.setFont(new Font("Segoe UI", Font.BOLD, 16));
        btnThoat.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2)); // Thêm viền trắng
        btnThoat.setFocusPainted(false);
        btnThoat.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                btnThoat.setOpaque(true);
                btnThoat.setContentAreaFilled(true);
                btnThoat.setBackground(new Color(255, 50, 50)); // Hiệu ứng hover
            }

            @Override
            public void mouseExited(MouseEvent e) {
                btnThoat.setOpaque(false);
                btnThoat.setContentAreaFilled(false);
                btnThoat.setBackground(null); // Trở về trong suốt
            }
        });
        buttonPanel.add(btnThoat);

        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        mainPanel.add(buttonPanel, gbc);

        // Sự kiện đăng nhập
        ActionListener loginAction = e -> {
            String maNV = txtMaNV.getText().trim();
            String password = new String(txtMatKhau.getPassword()).trim();

            if (maNV.isEmpty() || password.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Vui lòng nhập đầy đủ mã đăng nhập và mật khẩu!");
                return;
            }

            if (maNV.equals("admin") && password.equals("admin")) {
                maNVDangNhap = "admin";
                dispose();
            } else {
                try {
                    if (nhanVienDAO.checkLogin(maNV, password)) {
                        maNVDangNhap = maNV;
                        JOptionPane.showMessageDialog(this, "Đăng nhập thành công!");
                        dispose();
                    } else {
                        JOptionPane.showMessageDialog(this, "Sai mã đăng nhập hoặc mật khẩu!");
                    }
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(this, "Lỗi: " + ex.getMessage());
                }
            }
        };

        // Gán sự kiện cho nút đăng nhập
        btnDangNhap.addActionListener(loginAction);

        // Gán sự kiện nhấn Enter để đăng nhập
        txtMaNV.addActionListener(loginAction);
        txtMatKhau.addActionListener(loginAction);

        // Sự kiện nút thoát
        btnThoat.addActionListener(e -> dispose());

        add(mainPanel);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // Không cần phương thức này vì đã xử lý sự kiện trực tiếp trong constructor
    }
}