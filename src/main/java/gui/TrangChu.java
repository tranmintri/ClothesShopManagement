/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package gui;

import ConnectDB.KetNoiSQL;
import entity.NhanVien;
import java.awt.Color;
import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author 20086
 */
public class TrangChu extends javax.swing.JFrame {
    private static boolean qlkh_active = false;
    private static boolean qlbh_active = false;
    private static boolean qlnv_active = false;
    private static boolean qlsp_active = false;

    private static boolean qlhd_active = false;
    private static boolean ncc_active = false;
    private static boolean qltk_active = false;
    private static boolean doiMatKhau_active =false;
    private static boolean dangXuat_active = false;
    private static boolean  thongKe_active = false;
    private static boolean about_active = false;
    private static boolean  help_active = false;
    
     private final NhanVien nhanVien = FormDangNhap.nhanVien;
    

    /**
     * Creates new form TrangChu
     */
    public TrangChu() {
        KetNoiSQL.getInstance().connect();
        initComponents();
        addPanel(new FormWelcome());
        setRole();
    }
    
    private boolean isThanhToanHoaDon(){
        if(QuanLyBanHang.isThanhToan){
            JOptionPane.showMessageDialog(null, "Vui lòng hoàn tất việc thanh toán hóa đơn.");
            return true;
        }
        return false;
    }
    
    private static void clearEffect(){
        hoverMouseExited(nhanVien_container, lbl_NhanVien);
        hoverMouseExited(khachHang_container, lbl_KhachHang);
        hoverMouseExited(sanPham_container, lbl_sanPham);
        
        hoverMouseExited(banHang_container, lbl_BanHang);
        hoverMouseExited(quanLyTaiKhoan_container, lbl_QuanLyTaiKhoan);
        hoverMouseExited(hoaDon_container, lbl_hoaDon);
        hoverMouseExited(nhaCungCap_container, lbl_nhaCungCap);
        
        hoverMouseExited(doiMatKhau_container, lbl_DoiMatKhau);
        hoverMouseExited(DangXuat_container, lbl_DangXuat);
        hoverMouseExited(thongKe_container, lbl_ThongKe);
        
        hoverMouseExited(about_container, lbl_about);
        hoverMouseExited(help_container, lbl_help);

        qlbh_active = false;
        qlkh_active = false;
        qlnv_active = false;
        qlsp_active = false;
        qlhd_active = false;
        ncc_active = false;
        qltk_active = false;
        doiMatKhau_active = false;
        dangXuat_active = false;
        thongKe_active = false;
        about_active = false;
        help_active = false;
    }
    
     private static void addPanel(JPanel pnForm){
        right_container.removeAll();
        right_container.add(pnForm);
        right_container.updateUI();
    }
     
    public static void activeQLKHForm(){
        System.out.println("111running");
        
        clearEffect();
        System.out.println("1");
        qlkh_active = true;
        System.out.println("2");
        hoverMouseEntered(khachHang_container, lbl_KhachHang);
        System.out.println("3");
        addPanel(new QuanLyKhachHang());
        System.out.println("gui.TrangChu.activeQLKHForm()");
        
//        clearEffect();
//        hoverMouseEntered(khachHang_container, lbl_KhachHang);
//        addPanel(new QuanLyKhachHang());
    }
      /*
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////// HOVER /////////////////////////////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    */
    
    private static void hoverMouseEntered(javax.swing.JPanel pn_Container, javax.swing.JLabel lbl_Title){
        pn_Container.setBackground(new Color(255, 255, 255));
        lbl_Title.setForeground(new Color(102, 102, 255));
    }
    
    private static void hoverMouseExited(javax.swing.JPanel pn_Container, javax.swing.JLabel lbl_Title){
            pn_Container.setBackground(new Color(102, 102, 255));
        lbl_Title.setForeground(new Color(255, 255, 255));
    }
    
    public void setRole(){
        if(nhanVien.getChucVu().equalsIgnoreCase("Nhân viên"))
        {
            nhanVien_container.setVisible(false);
            hoaDon_container.setVisible(false);
            nhaCungCap_container.setVisible(false);
            quanLyTaiKhoan_container.setVisible(false);
            thongKe_container.setVisible(false);
        }
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        container = new javax.swing.JPanel();
        left_container = new javax.swing.JPanel();
        menu_container = new javax.swing.JPanel();
        lbl_Menu = new javax.swing.JLabel();
        header_container = new javax.swing.JPanel();
        txt_Header = new javax.swing.JLabel();
        sanPham_container = new javax.swing.JPanel();
        lbl_sanPham = new javax.swing.JLabel();
        banHang_container = new javax.swing.JPanel();
        lbl_BanHang = new javax.swing.JLabel();
        khachHang_container = new javax.swing.JPanel();
        lbl_KhachHang = new javax.swing.JLabel();
        quanLyTaiKhoan_container = new javax.swing.JPanel();
        lbl_QuanLyTaiKhoan = new javax.swing.JLabel();
        hoaDon_container = new javax.swing.JPanel();
        lbl_hoaDon = new javax.swing.JLabel();
        nhaCungCap_container = new javax.swing.JPanel();
        lbl_nhaCungCap = new javax.swing.JLabel();
        doiMatKhau_container = new javax.swing.JPanel();
        lbl_DoiMatKhau = new javax.swing.JLabel();
        DangXuat_container = new javax.swing.JPanel();
        lbl_DangXuat = new javax.swing.JLabel();
        thongKe_container = new javax.swing.JPanel();
        lbl_ThongKe = new javax.swing.JLabel();
        nhanVien_container = new javax.swing.JPanel();
        lbl_NhanVien = new javax.swing.JLabel();
        about_container = new javax.swing.JPanel();
        lbl_about = new javax.swing.JLabel();
        help_container = new javax.swing.JPanel();
        lbl_help = new javax.swing.JLabel();
        right_container = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(1383, 780));
        setUndecorated(true);

        container.setBackground(new java.awt.Color(255, 255, 255));
        container.setMaximumSize(new java.awt.Dimension(1383, 780));
        container.setMinimumSize(new java.awt.Dimension(1383, 780));
        container.setName(""); // NOI18N
        container.setPreferredSize(new java.awt.Dimension(1383, 780));

        left_container.setBackground(new java.awt.Color(102, 102, 255));
        left_container.setBorder(javax.swing.BorderFactory.createMatteBorder(3, 3, 3, 0, new java.awt.Color(0, 0, 0)));
        left_container.setMaximumSize(new java.awt.Dimension(230, 768));
        left_container.setMinimumSize(new java.awt.Dimension(230, 768));

        menu_container.setBackground(new java.awt.Color(102, 102, 255));
        menu_container.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(255, 255, 255)));
        menu_container.setForeground(new java.awt.Color(255, 255, 255));
        menu_container.setAlignmentX(0.0F);
        menu_container.setMaximumSize(new java.awt.Dimension(204, 60));
        menu_container.setMinimumSize(new java.awt.Dimension(204, 60));

        lbl_Menu.setFont(new java.awt.Font("Calibri", 1, 24)); // NOI18N
        lbl_Menu.setForeground(new java.awt.Color(255, 255, 255));
        lbl_Menu.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl_Menu.setText("Menu");
        lbl_Menu.setAlignmentY(0.0F);
        lbl_Menu.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        lbl_Menu.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        lbl_Menu.setMaximumSize(new java.awt.Dimension(236, 60));
        lbl_Menu.setMinimumSize(new java.awt.Dimension(236, 60));
        lbl_Menu.setPreferredSize(new java.awt.Dimension(236, 60));
        menu_container.add(lbl_Menu);

        header_container.setBackground(new java.awt.Color(102, 102, 255));
        header_container.setForeground(new java.awt.Color(255, 255, 255));
        header_container.setAlignmentX(0.0F);
        header_container.setMaximumSize(new java.awt.Dimension(204, 60));
        header_container.setMinimumSize(new java.awt.Dimension(204, 60));

        txt_Header.setFont(new java.awt.Font("Edwardian Script ITC", 1, 48)); // NOI18N
        txt_Header.setForeground(new java.awt.Color(255, 255, 255));
        txt_Header.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txt_Header.setText("ForClothes");
        txt_Header.setAlignmentY(0.0F);
        txt_Header.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        txt_Header.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        txt_Header.setMaximumSize(new java.awt.Dimension(204, 60));
        txt_Header.setMinimumSize(new java.awt.Dimension(204, 60));
        txt_Header.setPreferredSize(new java.awt.Dimension(204, 60));
        header_container.add(txt_Header);

        sanPham_container.setBackground(new java.awt.Color(102, 102, 255));
        sanPham_container.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(255, 255, 255)));
        sanPham_container.setForeground(new java.awt.Color(255, 255, 255));
        sanPham_container.setAlignmentX(0.0F);
        sanPham_container.setMaximumSize(new java.awt.Dimension(204, 60));
        sanPham_container.setMinimumSize(new java.awt.Dimension(204, 60));

        lbl_sanPham.setFont(new java.awt.Font("Calibri", 0, 24)); // NOI18N
        lbl_sanPham.setForeground(new java.awt.Color(255, 255, 255));
        lbl_sanPham.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl_sanPham.setText("Quản lý sản phẩm");
        lbl_sanPham.setAlignmentY(0.0F);
        lbl_sanPham.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lbl_sanPham.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        lbl_sanPham.setMaximumSize(new java.awt.Dimension(236, 60));
        lbl_sanPham.setMinimumSize(new java.awt.Dimension(236, 60));
        lbl_sanPham.setPreferredSize(new java.awt.Dimension(236, 60));
        lbl_sanPham.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbl_sanPhamMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lbl_sanPhamMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lbl_sanPhamMouseExited(evt);
            }
        });
        sanPham_container.add(lbl_sanPham);

        banHang_container.setBackground(new java.awt.Color(102, 102, 255));
        banHang_container.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(255, 255, 255)));
        banHang_container.setForeground(new java.awt.Color(255, 255, 255));
        banHang_container.setAlignmentX(0.0F);
        banHang_container.setMaximumSize(new java.awt.Dimension(204, 60));
        banHang_container.setMinimumSize(new java.awt.Dimension(204, 60));

        lbl_BanHang.setFont(new java.awt.Font("Calibri", 0, 24)); // NOI18N
        lbl_BanHang.setForeground(new java.awt.Color(255, 255, 255));
        lbl_BanHang.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl_BanHang.setText("Quản lý bán hàng");
        lbl_BanHang.setAlignmentY(0.0F);
        lbl_BanHang.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lbl_BanHang.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        lbl_BanHang.setMaximumSize(new java.awt.Dimension(236, 60));
        lbl_BanHang.setMinimumSize(new java.awt.Dimension(236, 60));
        lbl_BanHang.setPreferredSize(new java.awt.Dimension(236, 60));
        lbl_BanHang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbl_BanHangMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lbl_BanHangMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lbl_BanHangMouseExited(evt);
            }
        });
        banHang_container.add(lbl_BanHang);

        khachHang_container.setBackground(new java.awt.Color(102, 102, 255));
        khachHang_container.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(255, 255, 255)));
        khachHang_container.setForeground(new java.awt.Color(255, 255, 255));
        khachHang_container.setAlignmentX(0.0F);
        khachHang_container.setMaximumSize(new java.awt.Dimension(204, 60));
        khachHang_container.setMinimumSize(new java.awt.Dimension(204, 60));

        lbl_KhachHang.setFont(new java.awt.Font("Calibri", 0, 24)); // NOI18N
        lbl_KhachHang.setForeground(new java.awt.Color(255, 255, 255));
        lbl_KhachHang.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl_KhachHang.setText("Quản lý khách hàng");
        lbl_KhachHang.setAlignmentY(0.0F);
        lbl_KhachHang.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lbl_KhachHang.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        lbl_KhachHang.setMaximumSize(new java.awt.Dimension(236, 60));
        lbl_KhachHang.setMinimumSize(new java.awt.Dimension(236, 60));
        lbl_KhachHang.setPreferredSize(new java.awt.Dimension(236, 60));
        lbl_KhachHang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbl_KhachHangMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lbl_KhachHangMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lbl_KhachHangMouseExited(evt);
            }
        });
        khachHang_container.add(lbl_KhachHang);

        quanLyTaiKhoan_container.setBackground(new java.awt.Color(102, 102, 255));
        quanLyTaiKhoan_container.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(255, 255, 255)));
        quanLyTaiKhoan_container.setForeground(new java.awt.Color(255, 255, 255));
        quanLyTaiKhoan_container.setAlignmentX(0.0F);
        quanLyTaiKhoan_container.setMaximumSize(new java.awt.Dimension(204, 60));
        quanLyTaiKhoan_container.setMinimumSize(new java.awt.Dimension(204, 60));

        lbl_QuanLyTaiKhoan.setFont(new java.awt.Font("Calibri", 0, 24)); // NOI18N
        lbl_QuanLyTaiKhoan.setForeground(new java.awt.Color(255, 255, 255));
        lbl_QuanLyTaiKhoan.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl_QuanLyTaiKhoan.setText("Quản lý tài khoản");
        lbl_QuanLyTaiKhoan.setAlignmentY(0.0F);
        lbl_QuanLyTaiKhoan.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lbl_QuanLyTaiKhoan.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        lbl_QuanLyTaiKhoan.setMaximumSize(new java.awt.Dimension(236, 60));
        lbl_QuanLyTaiKhoan.setMinimumSize(new java.awt.Dimension(236, 60));
        lbl_QuanLyTaiKhoan.setPreferredSize(new java.awt.Dimension(236, 60));
        lbl_QuanLyTaiKhoan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbl_QuanLyTaiKhoanMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lbl_QuanLyTaiKhoanMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lbl_QuanLyTaiKhoanMouseExited(evt);
            }
        });
        quanLyTaiKhoan_container.add(lbl_QuanLyTaiKhoan);

        hoaDon_container.setBackground(new java.awt.Color(102, 102, 255));
        hoaDon_container.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(255, 255, 255)));
        hoaDon_container.setForeground(new java.awt.Color(255, 255, 255));
        hoaDon_container.setAlignmentX(0.0F);
        hoaDon_container.setMaximumSize(new java.awt.Dimension(204, 60));
        hoaDon_container.setMinimumSize(new java.awt.Dimension(204, 60));

        lbl_hoaDon.setFont(new java.awt.Font("Calibri", 0, 24)); // NOI18N
        lbl_hoaDon.setForeground(new java.awt.Color(255, 255, 255));
        lbl_hoaDon.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl_hoaDon.setText("Quản lý hoá đơn");
        lbl_hoaDon.setAlignmentY(0.0F);
        lbl_hoaDon.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lbl_hoaDon.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        lbl_hoaDon.setMaximumSize(new java.awt.Dimension(236, 60));
        lbl_hoaDon.setMinimumSize(new java.awt.Dimension(236, 60));
        lbl_hoaDon.setPreferredSize(new java.awt.Dimension(236, 60));
        lbl_hoaDon.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbl_hoaDonMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lbl_hoaDonMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lbl_hoaDonMouseExited(evt);
            }
        });
        hoaDon_container.add(lbl_hoaDon);

        nhaCungCap_container.setBackground(new java.awt.Color(102, 102, 255));
        nhaCungCap_container.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(255, 255, 255)));
        nhaCungCap_container.setForeground(new java.awt.Color(255, 255, 255));
        nhaCungCap_container.setAlignmentX(0.0F);
        nhaCungCap_container.setMaximumSize(new java.awt.Dimension(204, 60));
        nhaCungCap_container.setMinimumSize(new java.awt.Dimension(204, 60));

        lbl_nhaCungCap.setFont(new java.awt.Font("Calibri", 0, 24)); // NOI18N
        lbl_nhaCungCap.setForeground(new java.awt.Color(255, 255, 255));
        lbl_nhaCungCap.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl_nhaCungCap.setText("Quản lý nhà cung cấp");
        lbl_nhaCungCap.setAlignmentY(0.0F);
        lbl_nhaCungCap.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lbl_nhaCungCap.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        lbl_nhaCungCap.setMaximumSize(new java.awt.Dimension(236, 60));
        lbl_nhaCungCap.setMinimumSize(new java.awt.Dimension(236, 60));
        lbl_nhaCungCap.setPreferredSize(new java.awt.Dimension(236, 60));
        lbl_nhaCungCap.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbl_nhaCungCapMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lbl_nhaCungCapMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lbl_nhaCungCapMouseExited(evt);
            }
        });
        nhaCungCap_container.add(lbl_nhaCungCap);

        doiMatKhau_container.setBackground(new java.awt.Color(102, 102, 255));
        doiMatKhau_container.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 0, 0, 0, new java.awt.Color(255, 255, 255)));
        doiMatKhau_container.setForeground(new java.awt.Color(255, 255, 255));
        doiMatKhau_container.setAlignmentX(0.0F);
        doiMatKhau_container.setMaximumSize(new java.awt.Dimension(204, 60));
        doiMatKhau_container.setMinimumSize(new java.awt.Dimension(204, 60));

        lbl_DoiMatKhau.setFont(new java.awt.Font("Calibri", 0, 24)); // NOI18N
        lbl_DoiMatKhau.setForeground(new java.awt.Color(255, 255, 255));
        lbl_DoiMatKhau.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl_DoiMatKhau.setText("Đổi mật khẩu");
        lbl_DoiMatKhau.setAlignmentY(0.0F);
        lbl_DoiMatKhau.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lbl_DoiMatKhau.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        lbl_DoiMatKhau.setMaximumSize(new java.awt.Dimension(236, 60));
        lbl_DoiMatKhau.setMinimumSize(new java.awt.Dimension(236, 60));
        lbl_DoiMatKhau.setPreferredSize(new java.awt.Dimension(236, 45));
        lbl_DoiMatKhau.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbl_DoiMatKhauMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lbl_DoiMatKhauMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lbl_DoiMatKhauMouseExited(evt);
            }
        });
        doiMatKhau_container.add(lbl_DoiMatKhau);

        DangXuat_container.setBackground(new java.awt.Color(102, 102, 255));
        DangXuat_container.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 0, 0, 0, new java.awt.Color(255, 255, 255)));
        DangXuat_container.setForeground(new java.awt.Color(255, 255, 255));
        DangXuat_container.setAlignmentX(0.0F);
        DangXuat_container.setMaximumSize(new java.awt.Dimension(204, 60));
        DangXuat_container.setMinimumSize(new java.awt.Dimension(204, 60));

        lbl_DangXuat.setFont(new java.awt.Font("Calibri", 0, 24)); // NOI18N
        lbl_DangXuat.setForeground(new java.awt.Color(255, 255, 255));
        lbl_DangXuat.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl_DangXuat.setText("Đăng xuất");
        lbl_DangXuat.setAlignmentY(0.0F);
        lbl_DangXuat.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lbl_DangXuat.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        lbl_DangXuat.setMaximumSize(new java.awt.Dimension(236, 60));
        lbl_DangXuat.setMinimumSize(new java.awt.Dimension(236, 60));
        lbl_DangXuat.setPreferredSize(new java.awt.Dimension(236, 45));
        lbl_DangXuat.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbl_DangXuatMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lbl_DangXuatMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lbl_DangXuatMouseExited(evt);
            }
        });
        DangXuat_container.add(lbl_DangXuat);

        thongKe_container.setBackground(new java.awt.Color(102, 102, 255));
        thongKe_container.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(255, 255, 255)));
        thongKe_container.setForeground(new java.awt.Color(255, 255, 255));
        thongKe_container.setAlignmentX(0.0F);
        thongKe_container.setMaximumSize(new java.awt.Dimension(204, 60));
        thongKe_container.setMinimumSize(new java.awt.Dimension(204, 60));

        lbl_ThongKe.setFont(new java.awt.Font("Calibri", 0, 24)); // NOI18N
        lbl_ThongKe.setForeground(new java.awt.Color(255, 255, 255));
        lbl_ThongKe.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl_ThongKe.setText("Quản lý thống kê");
        lbl_ThongKe.setAlignmentY(0.0F);
        lbl_ThongKe.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lbl_ThongKe.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        lbl_ThongKe.setMaximumSize(new java.awt.Dimension(236, 60));
        lbl_ThongKe.setMinimumSize(new java.awt.Dimension(236, 60));
        lbl_ThongKe.setPreferredSize(new java.awt.Dimension(236, 60));
        lbl_ThongKe.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbl_ThongKeMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lbl_ThongKeMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lbl_ThongKeMouseExited(evt);
            }
        });
        thongKe_container.add(lbl_ThongKe);

        nhanVien_container.setBackground(new java.awt.Color(102, 102, 255));
        nhanVien_container.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(255, 255, 255)));
        nhanVien_container.setForeground(new java.awt.Color(255, 255, 255));
        nhanVien_container.setAlignmentX(0.0F);
        nhanVien_container.setMaximumSize(new java.awt.Dimension(204, 60));
        nhanVien_container.setMinimumSize(new java.awt.Dimension(204, 60));

        lbl_NhanVien.setFont(new java.awt.Font("Calibri", 0, 24)); // NOI18N
        lbl_NhanVien.setForeground(new java.awt.Color(255, 255, 255));
        lbl_NhanVien.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl_NhanVien.setText("Quản lý nhân viên");
        lbl_NhanVien.setAlignmentY(0.0F);
        lbl_NhanVien.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lbl_NhanVien.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        lbl_NhanVien.setMaximumSize(new java.awt.Dimension(236, 60));
        lbl_NhanVien.setMinimumSize(new java.awt.Dimension(236, 60));
        lbl_NhanVien.setPreferredSize(new java.awt.Dimension(236, 60));
        lbl_NhanVien.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbl_NhanVienMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lbl_NhanVienMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lbl_NhanVienMouseExited(evt);
            }
        });
        nhanVien_container.add(lbl_NhanVien);

        about_container.setBackground(new java.awt.Color(102, 102, 255));
        about_container.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                about_containerMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                about_containerMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                about_containerMouseExited(evt);
            }
        });

        lbl_about.setFont(new java.awt.Font("Calibri", 1, 16)); // NOI18N
        lbl_about.setForeground(new java.awt.Color(255, 255, 255));
        lbl_about.setText("About");

        javax.swing.GroupLayout about_containerLayout = new javax.swing.GroupLayout(about_container);
        about_container.setLayout(about_containerLayout);
        about_containerLayout.setHorizontalGroup(
            about_containerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(about_containerLayout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(lbl_about)
                .addContainerGap(26, Short.MAX_VALUE))
        );
        about_containerLayout.setVerticalGroup(
            about_containerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, about_containerLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbl_about, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        help_container.setBackground(new java.awt.Color(102, 102, 255));
        help_container.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                help_containerMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                help_containerMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                help_containerMouseExited(evt);
            }
        });

        lbl_help.setFont(new java.awt.Font("Calibri", 1, 16)); // NOI18N
        lbl_help.setForeground(new java.awt.Color(255, 255, 255));
        lbl_help.setText("Help");

        javax.swing.GroupLayout help_containerLayout = new javax.swing.GroupLayout(help_container);
        help_container.setLayout(help_containerLayout);
        help_containerLayout.setHorizontalGroup(
            help_containerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(help_containerLayout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(lbl_help, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(23, Short.MAX_VALUE))
        );
        help_containerLayout.setVerticalGroup(
            help_containerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(help_containerLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lbl_help))
        );

        javax.swing.GroupLayout left_containerLayout = new javax.swing.GroupLayout(left_container);
        left_container.setLayout(left_containerLayout);
        left_containerLayout.setHorizontalGroup(
            left_containerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(menu_container, javax.swing.GroupLayout.DEFAULT_SIZE, 258, Short.MAX_VALUE)
            .addComponent(sanPham_container, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(khachHang_container, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(banHang_container, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(header_container, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(quanLyTaiKhoan_container, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(hoaDon_container, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(nhaCungCap_container, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(thongKe_container, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(nhanVien_container, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(doiMatKhau_container, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(DangXuat_container, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(left_containerLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(left_containerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(about_container, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(help_container, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        left_containerLayout.setVerticalGroup(
            left_containerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(left_containerLayout.createSequentialGroup()
                .addComponent(header_container, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(menu_container, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(banHang_container, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(sanPham_container, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(khachHang_container, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(nhanVien_container, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(hoaDon_container, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(nhaCungCap_container, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(quanLyTaiKhoan_container, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(thongKe_container, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 12, Short.MAX_VALUE)
                .addComponent(about_container, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(help_container, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addComponent(doiMatKhau_container, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(DangXuat_container, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        right_container.setBackground(new java.awt.Color(255, 255, 255));
        right_container.setBorder(javax.swing.BorderFactory.createMatteBorder(3, 3, 3, 3, new java.awt.Color(0, 0, 0)));
        right_container.setAlignmentY(0.0F);
        right_container.setMaximumSize(new java.awt.Dimension(1130, 768));
        right_container.setMinimumSize(new java.awt.Dimension(1130, 768));
        right_container.setPreferredSize(new java.awt.Dimension(1130, 768));
        right_container.setLayout(new javax.swing.BoxLayout(right_container, javax.swing.BoxLayout.PAGE_AXIS));

        javax.swing.GroupLayout containerLayout = new javax.swing.GroupLayout(container);
        container.setLayout(containerLayout);
        containerLayout.setHorizontalGroup(
            containerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(containerLayout.createSequentialGroup()
                .addComponent(left_container, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(right_container, javax.swing.GroupLayout.PREFERRED_SIZE, 1124, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        containerLayout.setVerticalGroup(
            containerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(left_container, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(right_container, javax.swing.GroupLayout.DEFAULT_SIZE, 780, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(container, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(container, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void lbl_NhanVienMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbl_NhanVienMouseClicked
        // TODO add your handling code here:
        if(isThanhToanHoaDon()) return;
        clearEffect();
        qlnv_active = true;
        hoverMouseEntered(nhanVien_container, lbl_NhanVien);
        addPanel(new QuanLyNhanVien());
    }//GEN-LAST:event_lbl_NhanVienMouseClicked

    private void lbl_NhanVienMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbl_NhanVienMouseEntered
        // TODO add your handling code here:
        hoverMouseEntered(nhanVien_container, lbl_NhanVien);
    }//GEN-LAST:event_lbl_NhanVienMouseEntered

    private void lbl_NhanVienMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbl_NhanVienMouseExited
        // TODO add your handling code here:
        if(qlnv_active) return;
        hoverMouseExited(nhanVien_container, lbl_NhanVien);
    }//GEN-LAST:event_lbl_NhanVienMouseExited

    private void lbl_sanPhamMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbl_sanPhamMouseClicked
        // TODO add your handling code here:
        if(isThanhToanHoaDon()) return;
        clearEffect();
        qlsp_active = true;
        hoverMouseEntered(sanPham_container, lbl_sanPham);
        addPanel(new QuanLySanPham());
    }//GEN-LAST:event_lbl_sanPhamMouseClicked

    private void lbl_sanPhamMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbl_sanPhamMouseEntered
        // TODO add your handling code here:
        hoverMouseEntered(sanPham_container, lbl_sanPham);
    }//GEN-LAST:event_lbl_sanPhamMouseEntered

    private void lbl_sanPhamMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbl_sanPhamMouseExited
        // TODO add your handling code here:
        if(qlsp_active) return;
        hoverMouseExited(sanPham_container, lbl_sanPham);
    }//GEN-LAST:event_lbl_sanPhamMouseExited

    private void lbl_BanHangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbl_BanHangMouseClicked
        // TODO add your handling code here:
        if(isThanhToanHoaDon()) return;
        clearEffect();
        qlbh_active = true;
        hoverMouseEntered(banHang_container, lbl_BanHang);
        addPanel(new QLBH());
        QuanLyBanHang.resetTblDanhSachSP();
    }//GEN-LAST:event_lbl_BanHangMouseClicked

    private void lbl_BanHangMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbl_BanHangMouseEntered
        // TODO add your handling code here:
        hoverMouseEntered(banHang_container, lbl_BanHang);
    }//GEN-LAST:event_lbl_BanHangMouseEntered

    private void lbl_BanHangMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbl_BanHangMouseExited
        // TODO add your handling code here:
        if(qlbh_active) return;
        hoverMouseExited(banHang_container, lbl_BanHang);
    }//GEN-LAST:event_lbl_BanHangMouseExited

    private void lbl_KhachHangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbl_KhachHangMouseClicked
        // TODO add your handling code here:
        if(isThanhToanHoaDon()) return;
        clearEffect();
        qlkh_active = true;
        hoverMouseEntered(khachHang_container, lbl_KhachHang);
        addPanel(new QuanLyKhachHang());
    }//GEN-LAST:event_lbl_KhachHangMouseClicked

    private void lbl_KhachHangMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbl_KhachHangMouseEntered
        // TODO add your handling code here:
        hoverMouseEntered(khachHang_container, lbl_KhachHang);
    }//GEN-LAST:event_lbl_KhachHangMouseEntered

    private void lbl_KhachHangMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbl_KhachHangMouseExited
        // TODO add your handling code here:
        if(qlkh_active) return;
        hoverMouseExited(khachHang_container, lbl_KhachHang);
    }//GEN-LAST:event_lbl_KhachHangMouseExited

    private void lbl_QuanLyTaiKhoanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbl_QuanLyTaiKhoanMouseClicked
        // TODO add your handling code here:
        if(isThanhToanHoaDon()) return;
        clearEffect();
        qltk_active = true;
        hoverMouseEntered(quanLyTaiKhoan_container, lbl_QuanLyTaiKhoan);
        addPanel(new QuanLyTaiKhoan());
    }//GEN-LAST:event_lbl_QuanLyTaiKhoanMouseClicked

    private void lbl_QuanLyTaiKhoanMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbl_QuanLyTaiKhoanMouseEntered
        // TODO add your handling code here:
        hoverMouseEntered(quanLyTaiKhoan_container, lbl_QuanLyTaiKhoan);
    }//GEN-LAST:event_lbl_QuanLyTaiKhoanMouseEntered

    private void lbl_QuanLyTaiKhoanMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbl_QuanLyTaiKhoanMouseExited
        // TODO add your handling code here:
        if(qltk_active) return;
        hoverMouseExited(quanLyTaiKhoan_container, lbl_QuanLyTaiKhoan);
    }//GEN-LAST:event_lbl_QuanLyTaiKhoanMouseExited

    private void lbl_hoaDonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbl_hoaDonMouseClicked
        // TODO add your handling code here:
        if(isThanhToanHoaDon()) return;
       clearEffect();
        qlhd_active = true;
        hoverMouseEntered(hoaDon_container, lbl_hoaDon);
        addPanel(new QuanLyHoaDon());
    }//GEN-LAST:event_lbl_hoaDonMouseClicked

    private void lbl_hoaDonMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbl_hoaDonMouseEntered
        // TODO add your handling code here:
        hoverMouseEntered(hoaDon_container, lbl_hoaDon);
    }//GEN-LAST:event_lbl_hoaDonMouseEntered

    private void lbl_hoaDonMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbl_hoaDonMouseExited
        // TODO add your handling code here:
        if(qlhd_active) return;
        hoverMouseExited(hoaDon_container, lbl_hoaDon);
    }//GEN-LAST:event_lbl_hoaDonMouseExited

    private void lbl_nhaCungCapMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbl_nhaCungCapMouseClicked
        // TODO add your handling code here:
        if(isThanhToanHoaDon()) return;
        clearEffect();
        ncc_active = true;
        hoverMouseEntered(nhaCungCap_container, lbl_nhaCungCap);
        addPanel(new FormNhaCungCap());
    }//GEN-LAST:event_lbl_nhaCungCapMouseClicked

    private void lbl_nhaCungCapMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbl_nhaCungCapMouseEntered
        // TODO add your handling code here:
        hoverMouseEntered(nhaCungCap_container, lbl_nhaCungCap);
    }//GEN-LAST:event_lbl_nhaCungCapMouseEntered

    private void lbl_nhaCungCapMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbl_nhaCungCapMouseExited
        // TODO add your handling code here:
        if(ncc_active) return;
        hoverMouseExited(nhaCungCap_container, lbl_nhaCungCap);
    }//GEN-LAST:event_lbl_nhaCungCapMouseExited

    private void lbl_DoiMatKhauMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbl_DoiMatKhauMouseClicked
        // TODO add your handling code here:
         if(isThanhToanHoaDon()) return;
       clearEffect();
        doiMatKhau_active = true;
        hoverMouseEntered(doiMatKhau_container, lbl_DoiMatKhau);
        addPanel(new FormDoiMatKhau());
    }//GEN-LAST:event_lbl_DoiMatKhauMouseClicked

    private void lbl_DoiMatKhauMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbl_DoiMatKhauMouseEntered
        // TODO add your handling code here:
           hoverMouseEntered(doiMatKhau_container, lbl_DoiMatKhau);
    }//GEN-LAST:event_lbl_DoiMatKhauMouseEntered

    private void lbl_DoiMatKhauMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbl_DoiMatKhauMouseExited
        // TODO add your handling code here:
               if(doiMatKhau_active) return;
        hoverMouseExited(doiMatKhau_container, lbl_DoiMatKhau);
    }//GEN-LAST:event_lbl_DoiMatKhauMouseExited

    private void lbl_DangXuatMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbl_DangXuatMouseClicked
        // TODO add your handling code here:
         if(isThanhToanHoaDon()) return;
        clearEffect();
        dangXuat_active = true;
        hoverMouseEntered(DangXuat_container, lbl_DangXuat);
        new FormDangNhap().setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_lbl_DangXuatMouseClicked

    private void lbl_DangXuatMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbl_DangXuatMouseEntered
        // TODO add your handling code here:
           hoverMouseEntered(DangXuat_container, lbl_DangXuat);
    }//GEN-LAST:event_lbl_DangXuatMouseEntered

    private void lbl_DangXuatMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbl_DangXuatMouseExited
        // TODO add your handling code here:
                if(dangXuat_active) return;
        hoverMouseExited(DangXuat_container, lbl_DangXuat);
    }//GEN-LAST:event_lbl_DangXuatMouseExited

    private void lbl_ThongKeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbl_ThongKeMouseClicked
        // TODO add your handling code here:
        if(isThanhToanHoaDon()) return;
        clearEffect();
        thongKe_active = true;
        hoverMouseEntered(thongKe_container, lbl_ThongKe);
        addPanel(new FormThongKe());
        this.setVisible(true);
    }//GEN-LAST:event_lbl_ThongKeMouseClicked

    private void lbl_ThongKeMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbl_ThongKeMouseEntered
        // TODO add your handling code here:
        hoverMouseEntered(thongKe_container, lbl_ThongKe);
    }//GEN-LAST:event_lbl_ThongKeMouseEntered

    private void lbl_ThongKeMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbl_ThongKeMouseExited
        // TODO add your handling code here:
           if(thongKe_active) return;
        hoverMouseExited(thongKe_container, lbl_ThongKe);
    }//GEN-LAST:event_lbl_ThongKeMouseExited

    private void about_containerMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_about_containerMouseClicked
        // TODO add your handling code here:
                  
       clearEffect();
        about_active = true;
        hoverMouseEntered(about_container, lbl_about);
        new FormAbout().setVisible(true);
        clearEffect();
    }//GEN-LAST:event_about_containerMouseClicked

    private void about_containerMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_about_containerMouseEntered
        // TODO add your handling code here:
        hoverMouseEntered(about_container, lbl_about);
    }//GEN-LAST:event_about_containerMouseEntered

    private void about_containerMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_about_containerMouseExited
        // TODO add your handling code here:
               if(about_active) return;
        hoverMouseExited(about_container, lbl_about);
    }//GEN-LAST:event_about_containerMouseExited

    private void help_containerMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_help_containerMouseClicked
        // TODO add your handling code here:
                   
       clearEffect();
        help_active = true;
        hoverMouseEntered(help_container, lbl_help);
        
        File file = new File("");
        String path= file.getAbsolutePath();
        File URL = new File(path + "\\src\\main\\java\\helpWeb\\index.html");
        System.out.println("file: " +path);
        try {
            Desktop.getDesktop().open(URL);
        } catch (Exception e) {
        }
        clearEffect();
        
    }//GEN-LAST:event_help_containerMouseClicked

    private void help_containerMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_help_containerMouseEntered
        // TODO add your handling code here:
        hoverMouseEntered(help_container, lbl_help);
    }//GEN-LAST:event_help_containerMouseEntered

    private void help_containerMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_help_containerMouseExited
        // TODO add your handling code here:
               if(help_active) return;
        hoverMouseExited(help_container, lbl_help);
    }//GEN-LAST:event_help_containerMouseExited


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private static javax.swing.JPanel DangXuat_container;
    private static javax.swing.JPanel about_container;
    private static javax.swing.JPanel banHang_container;
    private javax.swing.JPanel container;
    private static javax.swing.JPanel doiMatKhau_container;
    private javax.swing.JPanel header_container;
    private static javax.swing.JPanel help_container;
    private static javax.swing.JPanel hoaDon_container;
    private static javax.swing.JPanel khachHang_container;
    private static javax.swing.JLabel lbl_BanHang;
    private static javax.swing.JLabel lbl_DangXuat;
    private static javax.swing.JLabel lbl_DoiMatKhau;
    private static javax.swing.JLabel lbl_KhachHang;
    private javax.swing.JLabel lbl_Menu;
    private static javax.swing.JLabel lbl_NhanVien;
    private static javax.swing.JLabel lbl_QuanLyTaiKhoan;
    private static javax.swing.JLabel lbl_ThongKe;
    private static javax.swing.JLabel lbl_about;
    private static javax.swing.JLabel lbl_help;
    private static javax.swing.JLabel lbl_hoaDon;
    private static javax.swing.JLabel lbl_nhaCungCap;
    private static javax.swing.JLabel lbl_sanPham;
    private javax.swing.JPanel left_container;
    private javax.swing.JPanel menu_container;
    private static javax.swing.JPanel nhaCungCap_container;
    private static javax.swing.JPanel nhanVien_container;
    private static javax.swing.JPanel quanLyTaiKhoan_container;
    private static javax.swing.JPanel right_container;
    private static javax.swing.JPanel sanPham_container;
    private static javax.swing.JPanel thongKe_container;
    private javax.swing.JLabel txt_Header;
    // End of variables declaration//GEN-END:variables
}
