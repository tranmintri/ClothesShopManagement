/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package gui;

import dao.ChiTietHoaDonDAO;
import dao.HoaDonDAO;
import dao.SanPhamDAO;

import java.awt.Color;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.NumberFormat;
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
public class TabThongKeNhanVien extends javax.swing.JPanel {

    /**
     * Creates new form TabThongKeSanPham
     */
    public TabThongKeNhanVien() {
        initComponents();
        designTable();

    }
    
       private void designTable() {
        tbl_DanhSachSanPham.getTableHeader().setFont(new java.awt.Font("Calibri", 1, 14));
        tbl_DanhSachSanPham.getTableHeader().setOpaque(false);
        tbl_DanhSachSanPham.getTableHeader().setBackground(new Color(102, 102, 255));
        tbl_DanhSachSanPham.getTableHeader().setForeground(Color.WHITE);
        tbl_DanhSachSanPham.setDefaultEditor(Object.class, null);
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
        jLabel9 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbl_DanhSachSanPham = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        lbl_tongNhanVien = new javax.swing.JLabel();
        lbl_soTongNhanVien = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        lbl_tongDoanhThu = new javax.swing.JLabel();
        lbl_SoTongDoanhThu = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        cb_thang = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        cb_theoGiaTri = new javax.swing.JComboBox<>();
        lbl_maNhanVien = new javax.swing.JLabel();
        lbl_tenNhanVien = new javax.swing.JLabel();
        lbl_soDienThoai = new javax.swing.JLabel();
        lbl_email = new javax.swing.JLabel();
        lbl_sanPhamDaBan = new javax.swing.JLabel();
        lbl_doanhThu = new javax.swing.JLabel();
        lbl_luong = new javax.swing.JLabel();

        jLabel1.setText("jLabel1");

        jLabel9.setText("Doanh thu:");

        setBackground(new java.awt.Color(255, 255, 255));

        tbl_DanhSachSanPham.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã nhân viên", "Tên nhân viên", "Số điện thoại", "Email", "Lương", "Số sản phẩm bán được", "Doanh thu"
            }
        ));
        tbl_DanhSachSanPham.setRowHeight(30);
        jScrollPane1.setViewportView(tbl_DanhSachSanPham);

        jPanel1.setBackground(new java.awt.Color(102, 102, 255));

        lbl_tongNhanVien.setFont(new java.awt.Font("Calibri", 1, 20)); // NOI18N
        lbl_tongNhanVien.setForeground(new java.awt.Color(255, 255, 255));
        lbl_tongNhanVien.setText("Số lượng nhân viên");

        lbl_soTongNhanVien.setFont(new java.awt.Font("Calibri", 1, 48)); // NOI18N
        lbl_soTongNhanVien.setForeground(new java.awt.Color(255, 255, 255));
        lbl_soTongNhanVien.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl_soTongNhanVien.setText("0");
        lbl_soTongNhanVien.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbl_soTongNhanVien, javax.swing.GroupLayout.PREFERRED_SIZE, 229, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(33, 33, 33)
                        .addComponent(lbl_tongNhanVien)))
                .addGap(48, 48, 48))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbl_tongNhanVien, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(8, 8, 8)
                .addComponent(lbl_soTongNhanVien, javax.swing.GroupLayout.DEFAULT_SIZE, 132, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel2.setBackground(new java.awt.Color(102, 102, 255));

        lbl_tongDoanhThu.setFont(new java.awt.Font("Calibri", 1, 20)); // NOI18N
        lbl_tongDoanhThu.setForeground(new java.awt.Color(255, 255, 255));
        lbl_tongDoanhThu.setText("Tổng lương");

        lbl_SoTongDoanhThu.setFont(new java.awt.Font("Calibri", 1, 48)); // NOI18N
        lbl_SoTongDoanhThu.setForeground(new java.awt.Color(255, 255, 255));
        lbl_SoTongDoanhThu.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl_SoTongDoanhThu.setText("0");
        lbl_SoTongDoanhThu.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbl_SoTongDoanhThu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(108, 108, 108)
                .addComponent(lbl_tongDoanhThu)
                .addContainerGap(113, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbl_tongDoanhThu, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbl_SoTongDoanhThu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jLabel3.setFont(new java.awt.Font("Calibri", 1, 18)); // NOI18N
        jLabel3.setText("Nhân viên xuất sắc nhất tháng:");

        cb_thang.setFont(new java.awt.Font("Calibri", 1, 18)); // NOI18N
        cb_thang.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12" }));
        cb_thang.setToolTipText("");

        jLabel2.setFont(new java.awt.Font("Calibri", 1, 18)); // NOI18N
        jLabel2.setText("Tên nhân viên:");

        jLabel4.setFont(new java.awt.Font("Calibri", 1, 18)); // NOI18N
        jLabel4.setText("Mã nhân viên:");

        jLabel5.setFont(new java.awt.Font("Calibri", 1, 18)); // NOI18N
        jLabel5.setText("Số điện thoại:");

        jLabel6.setFont(new java.awt.Font("Calibri", 1, 18)); // NOI18N
        jLabel6.setText("Email:");

        jLabel7.setFont(new java.awt.Font("Calibri", 1, 18)); // NOI18N
        jLabel7.setText("Số sản phẩm đã bán:");

        jLabel8.setFont(new java.awt.Font("Calibri", 1, 18)); // NOI18N
        jLabel8.setText("Doanh thu:");

        jLabel10.setFont(new java.awt.Font("Calibri", 1, 18)); // NOI18N
        jLabel10.setText("Lương:");

        jLabel11.setFont(new java.awt.Font("Calibri", 1, 18)); // NOI18N
        jLabel11.setText("Theo:");

        cb_theoGiaTri.setFont(new java.awt.Font("Calibri", 1, 18)); // NOI18N
        cb_theoGiaTri.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Sản phẩm", "Doanh thu" }));
        cb_theoGiaTri.setToolTipText("");

        lbl_maNhanVien.setFont(new java.awt.Font("Calibri", 0, 18)); // NOI18N

        lbl_tenNhanVien.setFont(new java.awt.Font("Calibri", 0, 18)); // NOI18N

        lbl_soDienThoai.setFont(new java.awt.Font("Calibri", 0, 18)); // NOI18N

        lbl_email.setFont(new java.awt.Font("Calibri", 0, 18)); // NOI18N

        lbl_sanPhamDaBan.setFont(new java.awt.Font("Calibri", 0, 18)); // NOI18N

        lbl_doanhThu.setFont(new java.awt.Font("Calibri", 0, 18)); // NOI18N

        lbl_luong.setFont(new java.awt.Font("Calibri", 0, 18)); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 234, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel6)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lbl_email, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cb_thang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel11)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cb_theoGiaTri, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(lbl_tenNhanVien, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(lbl_soDienThoai, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(lbl_maNhanVien, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel8)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(lbl_doanhThu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel7)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(lbl_sanPhamDaBan, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel10)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(lbl_luong, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                        .addGap(12, 12, 12))
                    .addComponent(jScrollPane1))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(cb_thang)
                                .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(cb_theoGiaTri, javax.swing.GroupLayout.DEFAULT_SIZE, 33, Short.MAX_VALUE))
                            .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbl_tenNhanVien, javax.swing.GroupLayout.DEFAULT_SIZE, 33, Short.MAX_VALUE)
                            .addComponent(lbl_sanPhamDaBan, javax.swing.GroupLayout.DEFAULT_SIZE, 33, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbl_maNhanVien, javax.swing.GroupLayout.DEFAULT_SIZE, 33, Short.MAX_VALUE)
                            .addComponent(lbl_doanhThu, javax.swing.GroupLayout.DEFAULT_SIZE, 33, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbl_soDienThoai, javax.swing.GroupLayout.DEFAULT_SIZE, 33, Short.MAX_VALUE)
                            .addComponent(lbl_luong, javax.swing.GroupLayout.DEFAULT_SIZE, 33, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbl_email, javax.swing.GroupLayout.DEFAULT_SIZE, 36, Short.MAX_VALUE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 533, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> cb_thang;
    private javax.swing.JComboBox<String> cb_theoGiaTri;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lbl_SoTongDoanhThu;
    private javax.swing.JLabel lbl_doanhThu;
    private javax.swing.JLabel lbl_email;
    private javax.swing.JLabel lbl_luong;
    private javax.swing.JLabel lbl_maNhanVien;
    private javax.swing.JLabel lbl_sanPhamDaBan;
    private javax.swing.JLabel lbl_soDienThoai;
    private javax.swing.JLabel lbl_soTongNhanVien;
    private javax.swing.JLabel lbl_tenNhanVien;
    private javax.swing.JLabel lbl_tongDoanhThu;
    private javax.swing.JLabel lbl_tongNhanVien;
    private javax.swing.JTable tbl_DanhSachSanPham;
    // End of variables declaration//GEN-END:variables
}