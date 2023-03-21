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
import entity.HoaDon;
import entity.KichThuoc;
import entity.MauSac;
import entity.PhanLoai;
import entity.SanPham;
import java.awt.Color;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Admin
 */


public class TabThongKeDoanhThu extends javax.swing.JPanel {
    private boolean initial = true; 
    private SanPhamDAO sanPham_DAO = new SanPhamDAO();
    private ChiTietHoaDonDAO cthd_DAO = new ChiTietHoaDonDAO();
    private HoaDonDAO hoaDon_DAO = new HoaDonDAO();
    private KichThuocDAO kichThuoc_DAO = new KichThuocDAO();
    private MauSacDAO mauSac_DAO = new MauSacDAO();
    private PhanLoaiDAO phanLoai_DAO = new PhanLoaiDAO();
    /**
     * Creates new form TabThongKeSanPham
     */
    public TabThongKeDoanhThu() {
        initComponents();
        designTable();
        khoiTaoGiaTri();
        tblDanhSachSanPham();
        myEvent();
//        tiLeSoVoiDoanhThu();
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
        
        cb_TatCa.setSelected(true);
        
        // ngay hom nay
        dc_TuNgay.setDate(Calendar.getInstance().getTime());
        dc_DenNgay.setDate(Calendar.getInstance().getTime());
        initial = false;
    }
    
    private void designTable() {
        tbl_DanhSachSanPham.getTableHeader().setFont(new java.awt.Font("Calibri", 1, 14));
        tbl_DanhSachSanPham.getTableHeader().setOpaque(false);
        tbl_DanhSachSanPham.getTableHeader().setBackground(new Color(102, 102, 255));
        tbl_DanhSachSanPham.getTableHeader().setForeground(Color.WHITE);
        tbl_DanhSachSanPham.setDefaultEditor(Object.class, null);
    }
    
    private void clearTable(){
        DefaultTableModel dtm = (DefaultTableModel) tbl_DanhSachSanPham.getModel();
        dtm.setRowCount(0);
    }
    
    private double tongDoanhThu(String mauSac, String phanLoai, String kichThuoc){
        double tongDoanhThu = 0;
        ArrayList<SanPham> listSanPham = cthd_DAO.thongKeDanhSachSanPhamVoiSoLuongBanDuoc(mauSac, phanLoai, kichThuoc);
         for(SanPham sp : listSanPham){
             tongDoanhThu += sp.getGiaNhap();
         }
         return tongDoanhThu;
    }
    
    private double tongDoanhThuFilter(String mauSac, String phanLoai, String kichThuoc, String tuNgay,String denNgay){
        double tongDoanhThu = 0;
         ArrayList<SanPham> listSanPham = cthd_DAO.thongKeDanhSachSanPhamVoiSoLuongBanDuoc(mauSac, phanLoai, kichThuoc, tuNgay, denNgay);
         for(SanPham sp : listSanPham){
             tongDoanhThu += sp.getGiaNhap();
         }
         return tongDoanhThu;
    }
    
    private void tblDanhSachSanPham(){
        clearTable();
        
        String mauSac = cb_MauSac.getSelectedItem().toString();
        if(cb_MauSac.getSelectedItem().toString().equals("Tất cả")) mauSac = "";
        
        String phanLoai = cb_PhanLoai.getSelectedItem().toString();
        if(cb_PhanLoai.getSelectedItem().toString().equals("Tất cả")) phanLoai = "";
        
        String kichThuoc = cb_KichThuoc.getSelectedItem().toString();
        if(cb_KichThuoc.getSelectedItem().toString().equals("Tất cả")) kichThuoc = "";
        
        ArrayList<SanPham> listSanPham = cthd_DAO.thongKeDanhSachSanPhamVoiSoLuongBanDuoc(mauSac, phanLoai, kichThuoc);
        DefaultTableModel dtm = (DefaultTableModel) tbl_DanhSachSanPham.getModel();
        int tongSoSanPhamBanDuoc = 0;
        double tongDoanhThu = tongDoanhThu(mauSac, phanLoai, kichThuoc);
        for(SanPham sp : listSanPham){
            tongSoSanPhamBanDuoc += sp.getSoLuong();
            double tiLeDoanhThu = (sp.getGiaNhap() / tongDoanhThu) * 100;
            Object[] rowData = {sp.getMaSP(), sp.getTenSP(), sp.getPhanLoai().getLoaiSanPham(), sp.getKichThuoc().getKichThuoc(),
                                sp.getMauSac().getMauSac(), sp.getSoLuong(), NumberFormat.getInstance().format( sp.getGiaNhap()), 
                                String.format("%.2f", tiLeDoanhThu) };
            dtm.addRow(rowData);
        }
        
        lbl_SoTongSanPhamDaBan.setText(tongSoSanPhamBanDuoc+"");
        lbl_SoTongSanPhamConLai.setText(NumberFormat.getInstance().format(tongDoanhThu));
    }
    
   
    private void tblDanhSachSanPhamTheoTime(){
        clearTable();
        
        String mauSac = cb_MauSac.getSelectedItem().toString();
        if(cb_MauSac.getSelectedItem().toString().equals("Tất cả")) mauSac = "";
        
        String phanLoai = cb_PhanLoai.getSelectedItem().toString();
        if(cb_PhanLoai.getSelectedItem().toString().equals("Tất cả")) phanLoai = "";
        
        String kichThuoc = cb_KichThuoc.getSelectedItem().toString();
        if(cb_KichThuoc.getSelectedItem().toString().equals("Tất cả")) kichThuoc = "";
      

        String tuNgay = new SimpleDateFormat("yyyy-MM-dd").format( dc_TuNgay.getDate());
        String denNgay = new SimpleDateFormat("yyyy-MM-dd").format( dc_DenNgay.getDate());
        ArrayList<SanPham> listSanPham = cthd_DAO.thongKeDanhSachSanPhamVoiSoLuongBanDuoc(mauSac, phanLoai, kichThuoc, tuNgay, denNgay);
        
        DefaultTableModel dtm = (DefaultTableModel) tbl_DanhSachSanPham.getModel();
        int tongSoSanPhamBanDuoc = 0;
        double tongDoanhThu = tongDoanhThuFilter(mauSac, phanLoai, kichThuoc, tuNgay, denNgay);
        for(SanPham sp : listSanPham){
            tongSoSanPhamBanDuoc += sp.getSoLuong();
            double tiLeDoanhThu = (sp.getGiaNhap() / tongDoanhThu) * 100;
            Object[] rowData = {sp.getMaSP(), sp.getTenSP(), sp.getPhanLoai().getLoaiSanPham(), sp.getKichThuoc().getKichThuoc(),
                                sp.getMauSac().getMauSac(), sp.getSoLuong(), NumberFormat.getInstance().format(sp.getGiaNhap()),
                                String.format("%.2f", tiLeDoanhThu)};
            dtm.addRow(rowData);
        }
        
        lbl_SoTongSanPhamDaBan.setText(tongSoSanPhamBanDuoc+"");
        lbl_SoTongSanPhamConLai.setText(NumberFormat.getInstance().format(tongDoanhThu));
    }
    
     
    private boolean dateValid(){
        Date currentDate = Calendar.getInstance().getTime();
         if(dc_TuNgay.getDate().getTime() - currentDate.getTime() > 0){
             JOptionPane.showMessageDialog(null, "Từ ngày phải bé hơn hoặc bằng ngày hiện tại");
             dc_TuNgay.setDate(dc_DenNgay.getDate());
             return false;
         }
         
         if(dc_TuNgay.getDate().getTime() - dc_DenNgay.getDate().getTime() > 0){
             JOptionPane.showMessageDialog(null, "Từ ngày phải bé hơn hoặc bằng đến ngày");
             dc_TuNgay.setDate(dc_DenNgay.getDate());
             return false;
         }
         
         if(dc_DenNgay.getDate().getTime() - currentDate.getTime() > 0){
             JOptionPane.showMessageDialog(null, "Đến ngày phải bé hơn hoặc bằng ngày hiện tại");
             dc_DenNgay.setDate(currentDate);
             return false;
         }
         
         
//         if(dc_DenNgay.getDate().getTime() < dc_TuNgay.getDate().getTime()){
//             JOptionPane.showMessageDialog(null, "Ngày dến phải lớn hơn hoặc bằng từ ngày");
//             return false;
//         }
         
         return true;
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
        jScrollPane1 = new javax.swing.JScrollPane();
        tbl_DanhSachSanPham = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        lbl_TongSanPhamDaBan = new javax.swing.JLabel();
        lbl_SoTongSanPhamDaBan = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        lbl_TongSanPhamConLai = new javax.swing.JLabel();
        lbl_SoTongSanPhamConLai = new javax.swing.JLabel();
        lbl_MauSac = new javax.swing.JLabel();
        cb_MauSac = new javax.swing.JComboBox<>();
        lbl_Thang = new javax.swing.JLabel();
        lbl_Nam = new javax.swing.JLabel();
        cb_TatCa = new javax.swing.JCheckBox();
        cb_PhanLoai = new javax.swing.JComboBox<>();
        lbl_PhanLoai = new javax.swing.JLabel();
        lbl_KichThuoc = new javax.swing.JLabel();
        cb_KichThuoc = new javax.swing.JComboBox<>();
        dc_TuNgay = new com.toedter.calendar.JDateChooser();
        dc_DenNgay = new com.toedter.calendar.JDateChooser();

        jLabel1.setText("jLabel1");

        setBackground(new java.awt.Color(255, 255, 255));

        tbl_DanhSachSanPham.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã sản phẩm", "Tên sản phẩm", "Phân loại", "Kích cỡ", "Màu sắc", "Số lượng bán được", "Doanh thu", "Tỉ lệ doanh thu (%)"
            }
        ));
        tbl_DanhSachSanPham.setRowHeight(30);
        jScrollPane1.setViewportView(tbl_DanhSachSanPham);

        jPanel1.setBackground(new java.awt.Color(102, 102, 255));

        lbl_TongSanPhamDaBan.setFont(new java.awt.Font("Calibri", 1, 20)); // NOI18N
        lbl_TongSanPhamDaBan.setForeground(new java.awt.Color(255, 255, 255));
        lbl_TongSanPhamDaBan.setText("Sản phẩm đã bán được");

        lbl_SoTongSanPhamDaBan.setFont(new java.awt.Font("Calibri", 1, 48)); // NOI18N
        lbl_SoTongSanPhamDaBan.setForeground(new java.awt.Color(255, 255, 255));
        lbl_SoTongSanPhamDaBan.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl_SoTongSanPhamDaBan.setText("0");
        lbl_SoTongSanPhamDaBan.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbl_SoTongSanPhamDaBan, javax.swing.GroupLayout.PREFERRED_SIZE, 229, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addComponent(lbl_TongSanPhamDaBan)))
                .addGap(48, 48, 48))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbl_TongSanPhamDaBan, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(8, 8, 8)
                .addComponent(lbl_SoTongSanPhamDaBan, javax.swing.GroupLayout.DEFAULT_SIZE, 132, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel2.setBackground(new java.awt.Color(102, 102, 255));

        lbl_TongSanPhamConLai.setFont(new java.awt.Font("Calibri", 1, 20)); // NOI18N
        lbl_TongSanPhamConLai.setForeground(new java.awt.Color(255, 255, 255));
        lbl_TongSanPhamConLai.setText("Tổng doanh thu");

        lbl_SoTongSanPhamConLai.setFont(new java.awt.Font("Calibri", 1, 48)); // NOI18N
        lbl_SoTongSanPhamConLai.setForeground(new java.awt.Color(255, 255, 255));
        lbl_SoTongSanPhamConLai.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl_SoTongSanPhamConLai.setText("0");
        lbl_SoTongSanPhamConLai.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbl_SoTongSanPhamConLai, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(97, Short.MAX_VALUE)
                .addComponent(lbl_TongSanPhamConLai)
                .addGap(89, 89, 89))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbl_TongSanPhamConLai, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbl_SoTongSanPhamConLai, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        lbl_MauSac.setFont(new java.awt.Font("Calibri", 1, 18)); // NOI18N
        lbl_MauSac.setText("Màu sắc:");

        cb_MauSac.setFont(new java.awt.Font("Calibri", 0, 18)); // NOI18N
        cb_MauSac.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Tất cả" }));
        cb_MauSac.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cb_MauSacItemStateChanged(evt);
            }
        });

        lbl_Thang.setFont(new java.awt.Font("Calibri", 1, 18)); // NOI18N
        lbl_Thang.setText("Từ ngày:");

        lbl_Nam.setFont(new java.awt.Font("Calibri", 1, 18)); // NOI18N
        lbl_Nam.setText("Đến ngày:");

        cb_TatCa.setBackground(new java.awt.Color(255, 255, 255));
        cb_TatCa.setFont(new java.awt.Font("Calibri", 1, 18)); // NOI18N
        cb_TatCa.setSelected(true);
        cb_TatCa.setText("Tất cả");
        cb_TatCa.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cb_TatCaItemStateChanged(evt);
            }
        });
        cb_TatCa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cb_TatCaActionPerformed(evt);
            }
        });

        cb_PhanLoai.setFont(new java.awt.Font("Calibri", 0, 18)); // NOI18N
        cb_PhanLoai.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Tất cả" }));
        cb_PhanLoai.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cb_PhanLoaiItemStateChanged(evt);
            }
        });

        lbl_PhanLoai.setFont(new java.awt.Font("Calibri", 1, 18)); // NOI18N
        lbl_PhanLoai.setText("Phân loại:");

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

        dc_TuNgay.setDateFormatString("dd/MM/yyyy");
        dc_TuNgay.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                dc_TuNgayPropertyChange(evt);
            }
        });

        dc_DenNgay.setDateFormatString("dd/MM/yyyy");
        dc_DenNgay.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                dc_DenNgayPropertyChange(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 234, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lbl_KichThuoc, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lbl_PhanLoai))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(cb_KichThuoc, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(cb_PhanLoai, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lbl_MauSac, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cb_MauSac, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(cb_TatCa, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(28, 28, 28))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(21, 21, 21)
                                        .addComponent(lbl_Nam))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(18, 18, 18)
                                        .addComponent(lbl_Thang)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(dc_TuNgay, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(dc_DenNgay, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 4, Short.MAX_VALUE)))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(dc_TuNgay, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lbl_MauSac, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(cb_MauSac, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(lbl_Thang, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(lbl_Nam)
                                .addComponent(cb_PhanLoai, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(lbl_PhanLoai))
                            .addComponent(dc_DenNgay, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(lbl_KichThuoc, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(cb_KichThuoc, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(cb_TatCa)))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 533, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void cb_MauSacItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cb_MauSacItemStateChanged
        // TODO add your handling code here:
        if(cb_TatCa.isSelected()){
            tblDanhSachSanPham();
        }
        else if(!cb_TatCa.isSelected()){
            tblDanhSachSanPhamTheoTime();
        }
    }//GEN-LAST:event_cb_MauSacItemStateChanged

    private void cb_TatCaItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cb_TatCaItemStateChanged
        // TODO add your handling code here:
        if(cb_TatCa.isSelected()){
            tblDanhSachSanPham();
        }
        else if(!cb_TatCa.isSelected()){
            tblDanhSachSanPhamTheoTime();
        }
    }//GEN-LAST:event_cb_TatCaItemStateChanged

    private void cb_TatCaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cb_TatCaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cb_TatCaActionPerformed

    private void cb_PhanLoaiItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cb_PhanLoaiItemStateChanged
        // TODO add your handling code here:
        if(cb_TatCa.isSelected()){
            tblDanhSachSanPham();
        }
        else if(!cb_TatCa.isSelected()){
            tblDanhSachSanPhamTheoTime();
        }
    }//GEN-LAST:event_cb_PhanLoaiItemStateChanged

    private void cb_KichThuocItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cb_KichThuocItemStateChanged
        // TODO add your handling code here:

        if(cb_TatCa.isSelected()){
            tblDanhSachSanPham();
        }
        else if(!cb_TatCa.isSelected()){
            tblDanhSachSanPhamTheoTime();
        }
    }//GEN-LAST:event_cb_KichThuocItemStateChanged

    private void cb_KichThuocActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cb_KichThuocActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cb_KichThuocActionPerformed

    private void dc_TuNgayPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_dc_TuNgayPropertyChange
        // TODO add your handling code here:
        if(initial) return;
        if(!dateValid())return;
        
        if(cb_TatCa.isSelected()){
            tblDanhSachSanPham();
        }
        else if(!cb_TatCa.isSelected()){
            tblDanhSachSanPhamTheoTime();
        }
            
        
    
    }//GEN-LAST:event_dc_TuNgayPropertyChange

    private void dc_DenNgayPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_dc_DenNgayPropertyChange
        // TODO add your handling code here:
        if(initial) return;
        if(!dateValid())return;
        
       if(cb_TatCa.isSelected()){
            tblDanhSachSanPham();
        }
        else if(!cb_TatCa.isSelected()){
            tblDanhSachSanPhamTheoTime();
        }
   
    }//GEN-LAST:event_dc_DenNgayPropertyChange


    private void myEvent(){
        tbl_DanhSachSanPham.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e){
                JTable table = (JTable)e.getSource();
                Point point = e.getPoint();
                int row = table.rowAtPoint(point);
                
                
                if(e.getClickCount() == 2 && table.getSelectedRow() != -1){
                    
                    SanPham sp = sanPham_DAO.getSanPhamById(table.getValueAt(row, 0).toString());
                    ArrayList<HoaDon> listHoaDon;
                    String maSP = sp.getMaSP();
                    String tuNgay = new SimpleDateFormat("yyyy-MM-dd").format( dc_TuNgay.getDate());
                    String denNgay = new SimpleDateFormat("yyyy-MM-dd").format( dc_DenNgay.getDate());
                    if(!cb_TatCa.isSelected()){
                       
                        listHoaDon = cthd_DAO.danhSachHoaDonTheoSanPham(maSP, tuNgay, denNgay);
                        new FormChiTietHoaDon(sp, listHoaDon).setVisible(true);
                    }
                    else{
                        listHoaDon = cthd_DAO.danhSachHoaDonTheoSanPham(maSP);
                        new FormChiTietHoaDon(sp, listHoaDon).setVisible(true);
                    }       
                }
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> cb_KichThuoc;
    private javax.swing.JComboBox<String> cb_MauSac;
    private javax.swing.JComboBox<String> cb_PhanLoai;
    private javax.swing.JCheckBox cb_TatCa;
    private com.toedter.calendar.JDateChooser dc_DenNgay;
    private com.toedter.calendar.JDateChooser dc_TuNgay;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lbl_KichThuoc;
    private javax.swing.JLabel lbl_MauSac;
    private javax.swing.JLabel lbl_Nam;
    private javax.swing.JLabel lbl_PhanLoai;
    private javax.swing.JLabel lbl_SoTongSanPhamConLai;
    private javax.swing.JLabel lbl_SoTongSanPhamDaBan;
    private javax.swing.JLabel lbl_Thang;
    private javax.swing.JLabel lbl_TongSanPhamConLai;
    private javax.swing.JLabel lbl_TongSanPhamDaBan;
    private javax.swing.JTable tbl_DanhSachSanPham;
    // End of variables declaration//GEN-END:variables
}
