/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package gui;

import dao.ChiTietHoaDonDAO;
import dao.HoaDonDAO;
import dao.KichThuocDAO;
import dao.MauSacDAO;
import dao.PhanLoaiDAO;
import dao.SanPhamDAO;
import entity.KichThuoc;
import entity.MauSac;
import entity.PhanLoai;
import entity.SanPham;
import java.awt.Color;
import java.awt.event.ItemEvent;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Admin
 */
public class TabThongKeSanPham extends javax.swing.JPanel {
    private SanPhamDAO sanPham_DAO = new SanPhamDAO();
    private ChiTietHoaDonDAO cthd_DAO = new ChiTietHoaDonDAO();
    private HoaDonDAO hoaDon_DAO = new HoaDonDAO();
    private KichThuocDAO kichThuoc_DAO = new KichThuocDAO();
    private MauSacDAO mauSac_DAO = new MauSacDAO();
    private PhanLoaiDAO phanLoai_DAO = new PhanLoaiDAO();
    /**
     * Creates new form TabThongKeSanPham
     */
    public TabThongKeSanPham() {
        initComponents();
        designTable();
        tblDanhSachSanPham();
        khoiTaoGiaTri();
    }
    
    private void khoiTaoGiaTri(){
        
        ArrayList<KichThuoc>listKichThuoc = kichThuoc_DAO.getAllKichThuoc();
        for(KichThuoc kt : listKichThuoc){
            cb_KichThuoc.addItem(kt.getKichThuoc());
        }
        
        ArrayList<MauSac>listMauSac = mauSac_DAO.getAllMauSac();
        for(MauSac ms : listMauSac){
            cb_MauSac.addItem(ms.getMauSac());
        }
        
        ArrayList<PhanLoai>listPhanLoai = phanLoai_DAO.getAllPhanLoai();
        for(PhanLoai pl : listPhanLoai){
            cb_PhanLoai.addItem(pl.getLoaiSanPham());
        }
        

    }
    
    private void designTable() {
        tbl_DanhSachSanPham.getTableHeader().setFont(new java.awt.Font("Calibri", 1, 14));
        tbl_DanhSachSanPham.getTableHeader().setOpaque(false);
        tbl_DanhSachSanPham.getTableHeader().setBackground(new Color(102, 102, 255));
        tbl_DanhSachSanPham.getTableHeader().setForeground(Color.WHITE);
        tbl_DanhSachSanPham.setDefaultEditor(Object.class, null);
        tbl_DanhSachSanPham.setRowHeight(30);
   
    }
    
    private void clearTable(){
        DefaultTableModel dtm = (DefaultTableModel) tbl_DanhSachSanPham.getModel();
        dtm.setRowCount(0);
    }
    
    private void tblDanhSachSanPham(){
        clearTable();
        
        String mauSac = cb_MauSac.getSelectedItem().toString();
        if(cb_MauSac.getSelectedItem().toString().equals("Tất cả")) mauSac = "";
        
        String phanLoai = cb_PhanLoai.getSelectedItem().toString();
        if(cb_PhanLoai.getSelectedItem().toString().equals("Tất cả")) phanLoai = "";
        
        String kichThuoc = cb_KichThuoc.getSelectedItem().toString();
        if(cb_KichThuoc.getSelectedItem().toString().equals("Tất cả")) kichThuoc = "";
        
//        ArrayList<SanPham> listSanPham = cthd_DAO.thongKeDanhSachSanPhamVoiSoLuongBanDuoc(mauSac, phanLoai, kichThuoc);
        ArrayList<SanPham> listSanPham = sanPham_DAO.getAllSanPham("", "", phanLoai, mauSac, kichThuoc);
        DefaultTableModel dtm = (DefaultTableModel) tbl_DanhSachSanPham.getModel();

        for(SanPham sp : listSanPham){

            Object[] rowData = {sp.getMaSP(), sp.getTenSP(), sp.getPhanLoai().getLoaiSanPham(), sp.getKichThuoc().getKichThuoc(),
                                sp.getMauSac().getMauSac(), sp.getSoLuong(), NumberFormat.getInstance().format( sp.getGiaNhap()) };
            dtm.addRow(rowData);
        }
        
   
    }
    
    private void tblDanhSachSanPhamVuotDinhMuc(int soLuongDinhMuc){
        clearTable();
        
        String mauSac = cb_MauSac.getSelectedItem().toString();
        if(cb_MauSac.getSelectedItem().toString().equals("Tất cả")) mauSac = "";
        
        String phanLoai = cb_PhanLoai.getSelectedItem().toString();
        if(cb_PhanLoai.getSelectedItem().toString().equals("Tất cả")) phanLoai = "";
        
        String kichThuoc = cb_KichThuoc.getSelectedItem().toString();
        if(cb_KichThuoc.getSelectedItem().toString().equals("Tất cả")) kichThuoc = "";

//        ArrayList<SanPham> listSanPham = cthd_DAO.thongKeDanhSachSanPhamVoiSoLuongBanDuoc(mauSac, phanLoai, kichThuoc, thang, nam);
        ArrayList<SanPham> listSanPham = sanPham_DAO.getAllSanPhamVuotDinhMuc("", "", phanLoai, mauSac, kichThuoc, soLuongDinhMuc);
        DefaultTableModel dtm = (DefaultTableModel) tbl_DanhSachSanPham.getModel();
        
        for(SanPham sp : listSanPham){
     
            Object[] rowData = {sp.getMaSP(), sp.getTenSP(), sp.getPhanLoai().getLoaiSanPham(), sp.getKichThuoc().getKichThuoc(),
                                sp.getMauSac().getMauSac(), sp.getSoLuong(), NumberFormat.getInstance().format(sp.getGiaNhap()) };
            dtm.addRow(rowData);
        }
        
    }
    
    private void tblDanhSachSanPhamDuoiDinhMuc(int soLuongDinhMuc){
        clearTable();
        
        String mauSac = cb_MauSac.getSelectedItem().toString();
        if(cb_MauSac.getSelectedItem().toString().equals("Tất cả")) mauSac = "";
        
        String phanLoai = cb_PhanLoai.getSelectedItem().toString();
        if(cb_PhanLoai.getSelectedItem().toString().equals("Tất cả")) phanLoai = "";
        
        String kichThuoc = cb_KichThuoc.getSelectedItem().toString();
        if(cb_KichThuoc.getSelectedItem().toString().equals("Tất cả")) kichThuoc = "";

//        ArrayList<SanPham> listSanPham = cthd_DAO.thongKeDanhSachSanPhamVoiSoLuongBanDuoc(mauSac, phanLoai, kichThuoc, thang, nam);
        ArrayList<SanPham> listSanPham = sanPham_DAO.getAllSanPhamDuoiDinhMuc("", "", phanLoai, mauSac, kichThuoc, soLuongDinhMuc);
        DefaultTableModel dtm = (DefaultTableModel) tbl_DanhSachSanPham.getModel();

        for(SanPham sp : listSanPham){
           
            Object[] rowData = {sp.getMaSP(), sp.getTenSP(), sp.getPhanLoai().getLoaiSanPham(), sp.getKichThuoc().getKichThuoc(),
                                sp.getMauSac().getMauSac(), sp.getSoLuong(), NumberFormat.getInstance().format(sp.getGiaNhap()) };
            dtm.addRow(rowData);
        }
        
    }
    
    private void tblDanhSachSanPhamTon(){
        clearTable();
        
        String mauSac = cb_MauSac.getSelectedItem().toString();
        if(cb_MauSac.getSelectedItem().toString().equals("Tất cả")) mauSac = "";
        
        String phanLoai = cb_PhanLoai.getSelectedItem().toString();
        if(cb_PhanLoai.getSelectedItem().toString().equals("Tất cả")) phanLoai = "";
        
        String kichThuoc = cb_KichThuoc.getSelectedItem().toString();
        if(cb_KichThuoc.getSelectedItem().toString().equals("Tất cả")) kichThuoc = "";

//        ArrayList<SanPham> listSanPham = cthd_DAO.thongKeDanhSachSanPhamVoiSoLuongBanDuoc(mauSac, phanLoai, kichThuoc, thang, nam);
        ArrayList<SanPham> listSanPham = sanPham_DAO.getAllSanPhamTon("", "", phanLoai, mauSac, kichThuoc);
        DefaultTableModel dtm = (DefaultTableModel) tbl_DanhSachSanPham.getModel();

        for(SanPham sp : listSanPham){
           
            Object[] rowData = {sp.getMaSP(), sp.getTenSP(), sp.getPhanLoai().getLoaiSanPham(), sp.getKichThuoc().getKichThuoc(),
                                sp.getMauSac().getMauSac(), sp.getSoLuong(), NumberFormat.getInstance().format(sp.getGiaNhap()) };
            dtm.addRow(rowData);
        }
        
    }
    
    private void tblDanhSachSanPhamHetHang(){
        clearTable();
        
        String mauSac = cb_MauSac.getSelectedItem().toString();
        if(cb_MauSac.getSelectedItem().toString().equals("Tất cả")) mauSac = "";
        
        String phanLoai = cb_PhanLoai.getSelectedItem().toString();
        if(cb_PhanLoai.getSelectedItem().toString().equals("Tất cả")) phanLoai = "";
        
        String kichThuoc = cb_KichThuoc.getSelectedItem().toString();
        if(cb_KichThuoc.getSelectedItem().toString().equals("Tất cả")) kichThuoc = "";

//        ArrayList<SanPham> listSanPham = cthd_DAO.thongKeDanhSachSanPhamVoiSoLuongBanDuoc(mauSac, phanLoai, kichThuoc, thang, nam);
        ArrayList<SanPham> listSanPham = sanPham_DAO.getAllSanPhamHetHang("", "", phanLoai, mauSac, kichThuoc);
        DefaultTableModel dtm = (DefaultTableModel) tbl_DanhSachSanPham.getModel();

        for(SanPham sp : listSanPham){
           
            Object[] rowData = {sp.getMaSP(), sp.getTenSP(), sp.getPhanLoai().getLoaiSanPham(), sp.getKichThuoc().getKichThuoc(),
                                sp.getMauSac().getMauSac(), sp.getSoLuong(), NumberFormat.getInstance().format(sp.getGiaNhap()) };
            dtm.addRow(rowData);
        }
        
    }
    
    private int duLieuDinhMuc(){
        int soLuong = 0;
        try {
            soLuong = Integer.parseInt(txt_DinhMuc.getText());
            if(soLuong < 0){
                JOptionPane.showMessageDialog(null, "Định mức phải là số lớn hơn 0");
                return -1;
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Định mức phải là 1 con số");
            return -1;
        }
        return soLuong;
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        btng = new javax.swing.ButtonGroup();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbl_DanhSachSanPham = new javax.swing.JTable();
        lbl_MauSac = new javax.swing.JLabel();
        cb_MauSac = new javax.swing.JComboBox<>();
        lbl_PhanLoai = new javax.swing.JLabel();
        cb_PhanLoai = new javax.swing.JComboBox<>();
        lbl_KichThuoc = new javax.swing.JLabel();
        cb_KichThuoc = new javax.swing.JComboBox<>();
        jPanel4 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txt_DinhMuc = new javax.swing.JTextField();
        rbtn_duoiDinhMuc = new javax.swing.JRadioButton();
        rbtn_vuocDinhMuc = new javax.swing.JRadioButton();
        rbtn_conHangTrongKho = new javax.swing.JRadioButton();
        rbtn_hetHangTrongKho = new javax.swing.JRadioButton();
        rbtn_tatCa = new javax.swing.JRadioButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();

        jLabel1.setText("jLabel1");

        setBackground(new java.awt.Color(255, 255, 255));

        tbl_DanhSachSanPham.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã sản phẩm", "Tên sản phẩm", "Phân loại", "Kích cỡ", "Màu sắc", "Số lượng tồn kho", "Giá bán"
            }
        ));
        tbl_DanhSachSanPham.setRowHeight(30);
        jScrollPane1.setViewportView(tbl_DanhSachSanPham);

        lbl_MauSac.setFont(new java.awt.Font("Calibri", 1, 18)); // NOI18N
        lbl_MauSac.setText("Màu sắc:");

        cb_MauSac.setFont(new java.awt.Font("Calibri", 0, 18)); // NOI18N
        cb_MauSac.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Tất cả" }));
        cb_MauSac.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cb_MauSacItemStateChanged(evt);
            }
        });

        lbl_PhanLoai.setFont(new java.awt.Font("Calibri", 1, 18)); // NOI18N
        lbl_PhanLoai.setText("Phân loại:");

        cb_PhanLoai.setFont(new java.awt.Font("Calibri", 0, 18)); // NOI18N
        cb_PhanLoai.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Tất cả" }));
        cb_PhanLoai.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cb_PhanLoaiItemStateChanged(evt);
            }
        });

        lbl_KichThuoc.setFont(new java.awt.Font("Calibri", 1, 18)); // NOI18N
        lbl_KichThuoc.setText("Kích thước:");

        cb_KichThuoc.setFont(new java.awt.Font("Calibri", 0, 18)); // NOI18N
        cb_KichThuoc.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Tất cả" }));
        cb_KichThuoc.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cb_KichThuocItemStateChanged(evt);
            }
        });
        cb_KichThuoc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cb_KichThuocActionPerformed(evt);
            }
        });

        jPanel4.setBackground(new java.awt.Color(102, 102, 255));

        jLabel2.setFont(new java.awt.Font("Calibri", 1, 20)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Định mức tồn:");

        jLabel3.setFont(new java.awt.Font("Calibri", 1, 28)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Tồn kho");

        txt_DinhMuc.setFont(new java.awt.Font("Calibri", 1, 20)); // NOI18N
        txt_DinhMuc.addInputMethodListener(new java.awt.event.InputMethodListener() {
            public void caretPositionChanged(java.awt.event.InputMethodEvent evt) {
            }
            public void inputMethodTextChanged(java.awt.event.InputMethodEvent evt) {
                txt_DinhMucInputMethodTextChanged(evt);
            }
        });
        txt_DinhMuc.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_DinhMucKeyReleased(evt);
            }
        });

        rbtn_duoiDinhMuc.setBackground(new java.awt.Color(102, 102, 255));
        btng.add(rbtn_duoiDinhMuc);
        rbtn_duoiDinhMuc.setFont(new java.awt.Font("Calibri", 1, 20)); // NOI18N
        rbtn_duoiDinhMuc.setForeground(new java.awt.Color(255, 255, 255));
        rbtn_duoiDinhMuc.setText("Dưới định mức tồn");
        rbtn_duoiDinhMuc.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                rbtn_duoiDinhMucItemStateChanged(evt);
            }
        });

        rbtn_vuocDinhMuc.setBackground(new java.awt.Color(102, 102, 255));
        btng.add(rbtn_vuocDinhMuc);
        rbtn_vuocDinhMuc.setFont(new java.awt.Font("Calibri", 1, 20)); // NOI18N
        rbtn_vuocDinhMuc.setForeground(new java.awt.Color(255, 255, 255));
        rbtn_vuocDinhMuc.setText("Vược định mức tồn");
        rbtn_vuocDinhMuc.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                rbtn_vuocDinhMucItemStateChanged(evt);
            }
        });

        rbtn_conHangTrongKho.setBackground(new java.awt.Color(102, 102, 255));
        btng.add(rbtn_conHangTrongKho);
        rbtn_conHangTrongKho.setFont(new java.awt.Font("Calibri", 1, 20)); // NOI18N
        rbtn_conHangTrongKho.setForeground(new java.awt.Color(255, 255, 255));
        rbtn_conHangTrongKho.setText("Còn hàng trong kho");
        rbtn_conHangTrongKho.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                rbtn_conHangTrongKhoItemStateChanged(evt);
            }
        });

        rbtn_hetHangTrongKho.setBackground(new java.awt.Color(102, 102, 255));
        btng.add(rbtn_hetHangTrongKho);
        rbtn_hetHangTrongKho.setFont(new java.awt.Font("Calibri", 1, 20)); // NOI18N
        rbtn_hetHangTrongKho.setForeground(new java.awt.Color(255, 255, 255));
        rbtn_hetHangTrongKho.setText("Hết hàng trong kho");
        rbtn_hetHangTrongKho.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                rbtn_hetHangTrongKhoItemStateChanged(evt);
            }
        });

        rbtn_tatCa.setBackground(new java.awt.Color(102, 102, 255));
        btng.add(rbtn_tatCa);
        rbtn_tatCa.setFont(new java.awt.Font("Calibri", 1, 20)); // NOI18N
        rbtn_tatCa.setForeground(new java.awt.Color(255, 255, 255));
        rbtn_tatCa.setSelected(true);
        rbtn_tatCa.setText("Tất cả");
        rbtn_tatCa.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                rbtn_tatCaItemStateChanged(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_DinhMuc, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(35, 35, 35)
                        .addComponent(rbtn_tatCa, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(12, 12, 12))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(rbtn_vuocDinhMuc)
                            .addComponent(rbtn_duoiDinhMuc))
                        .addGap(47, 47, 47)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(rbtn_hetHangTrongKho)
                            .addComponent(rbtn_conHangTrongKho))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addGap(60, 60, 60))
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(160, 160, 160)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_DinhMuc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(rbtn_tatCa))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(rbtn_conHangTrongKho, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(rbtn_hetHangTrongKho))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(rbtn_duoiDinhMuc)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(rbtn_vuocDinhMuc)))
                .addContainerGap(21, Short.MAX_VALUE))
        );

        jButton3.setFont(new java.awt.Font("Calibri", 1, 18)); // NOI18N
        jButton3.setText("Top 10 sản phẩm bán chậm");
        jButton3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton3MouseClicked(evt);
            }
        });

        jButton4.setFont(new java.awt.Font("Calibri", 1, 18)); // NOI18N
        jButton4.setText("Top 10 sản phẩm bán chạy");
        jButton4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton4MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 468, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(lbl_MauSac, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cb_MauSac, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lbl_KichThuoc, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lbl_PhanLoai))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(cb_PhanLoai, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(cb_KichThuoc, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 34, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jButton4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, 305, Short.MAX_VALUE)))
                    .addComponent(jScrollPane1))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(17, 17, 17)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(lbl_MauSac, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(cb_MauSac, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
                                .addComponent(jButton4, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cb_PhanLoai, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbl_PhanLoai)
                            .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lbl_KichThuoc, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cb_KichThuoc, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(42, 42, 42))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 520, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void cb_KichThuocActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cb_KichThuocActionPerformed
        // TODO add your handling code here:
        tblDanhSachSanPham();
    }//GEN-LAST:event_cb_KichThuocActionPerformed

    private void cb_MauSacItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cb_MauSacItemStateChanged
        // TODO add your handling code here:
       tblDanhSachSanPham();
    }//GEN-LAST:event_cb_MauSacItemStateChanged

    private void cb_PhanLoaiItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cb_PhanLoaiItemStateChanged
        // TODO add your handling code here:
       tblDanhSachSanPham();
    }//GEN-LAST:event_cb_PhanLoaiItemStateChanged

    private void cb_KichThuocItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cb_KichThuocItemStateChanged
        // TODO add your handling code here:
        tblDanhSachSanPham();
    }//GEN-LAST:event_cb_KichThuocItemStateChanged

    private void txt_DinhMucKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_DinhMucKeyReleased
        // TODO add your handling code here:
        if(txt_DinhMuc.getText().equals("")) txt_DinhMuc.setText("1");
        
         int soLuong = duLieuDinhMuc();
        if(soLuong == -1) return;
        
        if(rbtn_tatCa.isSelected()){
            tblDanhSachSanPham();
        }
        else if(rbtn_vuocDinhMuc.isSelected()){
            tblDanhSachSanPhamVuotDinhMuc(soLuong);
        }
        else if(rbtn_duoiDinhMuc.isSelected()){
            tblDanhSachSanPhamDuoiDinhMuc(soLuong);
        }
    }//GEN-LAST:event_txt_DinhMucKeyReleased

    private void txt_DinhMucInputMethodTextChanged(java.awt.event.InputMethodEvent evt) {//GEN-FIRST:event_txt_DinhMucInputMethodTextChanged
        // TODO add your handling code here:
         int soLuong = duLieuDinhMuc();
        if(soLuong == -1) return;
        
        if(rbtn_tatCa.isSelected()){
            tblDanhSachSanPham();
        }
        else if(rbtn_vuocDinhMuc.isSelected()){
            tblDanhSachSanPhamVuotDinhMuc(soLuong);
        }
        else if(rbtn_duoiDinhMuc.isSelected()){
            tblDanhSachSanPhamDuoiDinhMuc(soLuong);
        }
    }//GEN-LAST:event_txt_DinhMucInputMethodTextChanged

    private void rbtn_duoiDinhMucItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_rbtn_duoiDinhMucItemStateChanged
        // TODO add your handling code here:
         int soLuong = duLieuDinhMuc();
        if(soLuong == -1) return;
        
        if(evt.getStateChange() == ItemEvent.SELECTED){
            tblDanhSachSanPhamDuoiDinhMuc(soLuong);
        }
    }//GEN-LAST:event_rbtn_duoiDinhMucItemStateChanged

    private void rbtn_conHangTrongKhoItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_rbtn_conHangTrongKhoItemStateChanged
        // TODO add your handling code here:
        
        if(evt.getStateChange() == ItemEvent.SELECTED){
            tblDanhSachSanPhamTon();
        }
    }//GEN-LAST:event_rbtn_conHangTrongKhoItemStateChanged

    private void rbtn_hetHangTrongKhoItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_rbtn_hetHangTrongKhoItemStateChanged
        // TODO add your handling code here:     
        if(evt.getStateChange() == ItemEvent.SELECTED){
            tblDanhSachSanPhamHetHang();
        }
    }//GEN-LAST:event_rbtn_hetHangTrongKhoItemStateChanged

    private void rbtn_vuocDinhMucItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_rbtn_vuocDinhMucItemStateChanged
        // TODO add your handling code here:
         int soLuong = duLieuDinhMuc();
        if(soLuong == -1) return;
        
        if(evt.getStateChange() == ItemEvent.SELECTED){
            tblDanhSachSanPhamVuotDinhMuc(soLuong);
        }
    }//GEN-LAST:event_rbtn_vuocDinhMucItemStateChanged

    private void rbtn_tatCaItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_rbtn_tatCaItemStateChanged
        // TODO add your handling code here:
        if(evt.getStateChange() == ItemEvent.SELECTED){
            tblDanhSachSanPham();
        }
    }//GEN-LAST:event_rbtn_tatCaItemStateChanged

    private void jButton4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton4MouseClicked
        // TODO add your handling code here:
        ArrayList<SanPham>listSanPham = sanPham_DAO.topNSanPham();
        new FormDanhSachSanPhamBanChay(listSanPham).setVisible(true);
    }//GEN-LAST:event_jButton4MouseClicked

    private void jButton3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton3MouseClicked
        // TODO add your handling code here:
         ArrayList<SanPham>listSanPham = sanPham_DAO.topNSanPhamBanCham();
        new FormDanhSachSanPhamBanCham(listSanPham).setVisible(true);
    }//GEN-LAST:event_jButton3MouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup btng;
    private javax.swing.JComboBox<String> cb_KichThuoc;
    private javax.swing.JComboBox<String> cb_MauSac;
    private javax.swing.JComboBox<String> cb_PhanLoai;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lbl_KichThuoc;
    private javax.swing.JLabel lbl_MauSac;
    private javax.swing.JLabel lbl_PhanLoai;
    private javax.swing.JRadioButton rbtn_conHangTrongKho;
    private javax.swing.JRadioButton rbtn_duoiDinhMuc;
    private javax.swing.JRadioButton rbtn_hetHangTrongKho;
    private javax.swing.JRadioButton rbtn_tatCa;
    private javax.swing.JRadioButton rbtn_vuocDinhMuc;
    private javax.swing.JTable tbl_DanhSachSanPham;
    private javax.swing.JTextField txt_DinhMuc;
    // End of variables declaration//GEN-END:variables
}
