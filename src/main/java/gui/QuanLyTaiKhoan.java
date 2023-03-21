/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package gui;

import entity.TaiKhoan;
import entity.NhanVien;
import dao.TaiKhoanDAO;
import dao.NhanVienDAO;
import java.awt.Color;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Admin
 */
public class QuanLyTaiKhoan extends javax.swing.JPanel {

    private TaiKhoanDAO taiKhoanDao;
    private NhanVienDAO nhanVienDao;

    /**
     * Creates new form FormDanhSachTaiKhoan
     */
    public QuanLyTaiKhoan() {
        initComponents();
        tblDanhSachTaiKhoan();
        designTable();
    }
        private void designTable() {
        tbl_DanhSachTaiKhoan.getTableHeader().setFont(new java.awt.Font("Calibri", 1, 14));
        tbl_DanhSachTaiKhoan.getTableHeader().setOpaque(false);
        tbl_DanhSachTaiKhoan.getTableHeader().setBackground(new Color(102, 102, 255));
        tbl_DanhSachTaiKhoan.getTableHeader().setForeground(Color.WHITE);
        tbl_DanhSachTaiKhoan.setDefaultEditor(Object.class, null); // Không cho phép edit

//        tbl_danhSachNhanVien.getTableHeader()
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbl_DanhSachTaiKhoan = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        lbl_TentaiKhoan = new javax.swing.JLabel();
        txt_TenTaiKhoan = new javax.swing.JTextField();
        lbl_MatKhau = new javax.swing.JLabel();
        txt_MatKhau = new javax.swing.JTextField();
        lbl_LoaitaiKhoan = new javax.swing.JLabel();
        txt_LoaiTaiKhoan = new javax.swing.JTextField();
        lbl_MaNhanVien = new javax.swing.JLabel();
        txt_MaNhanVien = new javax.swing.JTextField();
        lbl_TenNhanVien = new javax.swing.JLabel();
        txt_TenNhanVien = new javax.swing.JTextField();
        lbl_ChucVu = new javax.swing.JLabel();
        txt_ChucVu = new javax.swing.JTextField();
        btn_DatMatKhauMacDinh = new javax.swing.JButton();
        lbl_TimKiem = new javax.swing.JLabel();
        txt_TimKiem = new javax.swing.JTextField();
        btn_TimKiem = new javax.swing.JButton();

        setBackground(new java.awt.Color(255, 255, 255));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Danh sách tài khoản", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Calibri", 0, 14))); // NOI18N

        tbl_DanhSachTaiKhoan.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        tbl_DanhSachTaiKhoan.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Tên Tài Khoản", "Tên Nhân Viên", "Loại tài khoản"
            }
        ));
        tbl_DanhSachTaiKhoan.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        tbl_DanhSachTaiKhoan.setRowHeight(30);
        tbl_DanhSachTaiKhoan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tbl_DanhSachTaiKhoanMousePressed(evt);
            }
        });
        jScrollPane1.setViewportView(tbl_DanhSachTaiKhoan);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 473, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Thông tin chi tiết", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Calibri", 0, 14))); // NOI18N

        lbl_TentaiKhoan.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        lbl_TentaiKhoan.setText("Tên tài khoản:");

        txt_TenTaiKhoan.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        txt_TenTaiKhoan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_TenTaiKhoanActionPerformed(evt);
            }
        });

        lbl_MatKhau.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        lbl_MatKhau.setText("Mật khẩu:");

        txt_MatKhau.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N

        lbl_LoaitaiKhoan.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        lbl_LoaitaiKhoan.setText("Loại tài khoản:");

        txt_LoaiTaiKhoan.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N

        lbl_MaNhanVien.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        lbl_MaNhanVien.setText("Mã nhân viên:");

        txt_MaNhanVien.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N

        lbl_TenNhanVien.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        lbl_TenNhanVien.setText("Tên nhân viên:");

        txt_TenNhanVien.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N

        lbl_ChucVu.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        lbl_ChucVu.setText("Chức vụ:");

        txt_ChucVu.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N

        btn_DatMatKhauMacDinh.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        btn_DatMatKhauMacDinh.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/lock.png"))); // NOI18N
        btn_DatMatKhauMacDinh.setText("Đặt mật khẩu mặc định");
        btn_DatMatKhauMacDinh.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_DatMatKhauMacDinhMouseClicked(evt);
            }
        });

        lbl_TimKiem.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        lbl_TimKiem.setText("Tìm kiếm tài khoản:");

        txt_TimKiem.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N

        btn_TimKiem.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        btn_TimKiem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/loupe.png"))); // NOI18N
        btn_TimKiem.setText("Tìm");
        btn_TimKiem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_TimKiemActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(lbl_TimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(txt_TimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 362, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btn_TimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btn_DatMatKhauMacDinh, javax.swing.GroupLayout.PREFERRED_SIZE, 226, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(77, 77, 77))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txt_TenNhanVien)
                            .addComponent(txt_MaNhanVien, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbl_MaNhanVien, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbl_TenNhanVien, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 58, Short.MAX_VALUE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(txt_MatKhau, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbl_TentaiKhoan, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbl_MatKhau, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_TenTaiKhoan, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(65, 65, 65)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txt_ChucVu, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbl_ChucVu, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_LoaiTaiKhoan, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbl_LoaitaiKhoan, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(35, 35, 35))))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lbl_TimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txt_TimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btn_TimKiem)
                    .addComponent(btn_DatMatKhauMacDinh, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, 0)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addComponent(lbl_MaNhanVien, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, 0)
                                .addComponent(txt_MaNhanVien, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lbl_TenNhanVien, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, 0)
                                .addComponent(txt_TenNhanVien, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addComponent(lbl_TentaiKhoan, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, 0)
                                .addComponent(txt_TenTaiKhoan, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lbl_MatKhau, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, 0)
                                .addComponent(txt_MatKhau, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(71, 71, 71))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(lbl_ChucVu, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(txt_ChucVu, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lbl_LoaitaiKhoan, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(txt_LoaiTaiKhoan, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 209, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel1.getAccessibleContext().setAccessibleDescription("");
    }// </editor-fold>//GEN-END:initComponents

    private void txt_TenTaiKhoanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_TenTaiKhoanActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_TenTaiKhoanActionPerformed

    private void tbl_DanhSachTaiKhoanMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_DanhSachTaiKhoanMousePressed
        // TODO add your handling code here:
        int row = tbl_DanhSachTaiKhoan.getSelectedRow();
        DefaultTableModel dtm = (DefaultTableModel) tbl_DanhSachTaiKhoan.getModel();

        String tenTaiKhoan = dtm.getValueAt(row, 0).toString();
        taiKhoanDao = new TaiKhoanDAO();
        TaiKhoan taiKhoan = taiKhoanDao.getTaiKhoanByName(tenTaiKhoan);

        txt_TenTaiKhoan.setText(taiKhoan.getTenTaiKhoan());
        txt_MatKhau.setText(taiKhoan.getMatKhau());
        txt_LoaiTaiKhoan.setText(taiKhoan.getLoaiTaiKhoan() == true ? "Quản lý" : "Nhân viên");
        txt_MaNhanVien.setText(taiKhoan.getNhanVien().getMaNhanVien());
        txt_TenNhanVien.setText(taiKhoan.getNhanVien().getHoVaTen());
        txt_ChucVu.setText(taiKhoan.getNhanVien().getChucVu());
    }//GEN-LAST:event_tbl_DanhSachTaiKhoanMousePressed

    private void btn_TimKiemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_TimKiemActionPerformed
        // TODO add your handling code here:
        clearTable();
        String keyWord = txt_TimKiem.getText().trim().toLowerCase();
        taiKhoanDao = new TaiKhoanDAO();
        ArrayList<TaiKhoan> listTaiKhoan = taiKhoanDao.getAllTaiKhoanConHoatDong();
        DefaultTableModel dtm = (DefaultTableModel) tbl_DanhSachTaiKhoan.getModel();
        for (TaiKhoan taiKhoan : listTaiKhoan) {
            if (taiKhoan.getTenTaiKhoan().toLowerCase().contains(keyWord) || taiKhoan.getNhanVien().getHoVaTen().toLowerCase().contains(keyWord) || (taiKhoan.getLoaiTaiKhoan() == true ? "Quản lý" : "Nhân viên").toLowerCase().contains(keyWord)) {
                Object[] rowData = {taiKhoan.getTenTaiKhoan(), taiKhoan.getNhanVien().getHoVaTen(), taiKhoan.getLoaiTaiKhoan() == true ? "Quản lý" : "Nhân viên"};
                dtm.addRow(rowData);
            }
        }

    }//GEN-LAST:event_btn_TimKiemActionPerformed

    private void btn_DatMatKhauMacDinhMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_DatMatKhauMacDinhMouseClicked
        // TODO add your handling code here:
        buttonHandler();
    }//GEN-LAST:event_btn_DatMatKhauMacDinhMouseClicked

    private void buttonHandler(){
        if(tbl_DanhSachTaiKhoan.getSelectedRow() == -1){
            JOptionPane.showMessageDialog(null, "Vui lòng chọn dữ liệu để cập nhật");
            return;
        }
        
        TaiKhoan taiKhoan = taiKhoanDao.getTaiKhoanByTK((String) tbl_DanhSachTaiKhoan.getValueAt(tbl_DanhSachTaiKhoan.getSelectedRow(), 0));
        if(taiKhoan == null){
            JOptionPane.showMessageDialog(null, "Không tìm thấy tài khoản");
            return;
        }
        taiKhoan.setMatKhau("1111");
        if(taiKhoanDao.updateMatKhau(taiKhoan) == -1){
            JOptionPane.showMessageDialog(null, "Cập nhật thất bại");
            return;
        }
        JOptionPane.showMessageDialog(null, "Cập nhật thành công");
        tblDanhSachTaiKhoan();
        clearInformation();
    }
    private void clearTable() {
        DefaultTableModel dtm = (DefaultTableModel) tbl_DanhSachTaiKhoan.getModel();
        dtm.setRowCount(0);
    }
    
    private void clearInformation(){
        txt_ChucVu.setText("");
        txt_LoaiTaiKhoan.setText("");
        txt_MaNhanVien.setText("");
        txt_MatKhau.setText("");
        txt_TenNhanVien.setText("");
        txt_TenTaiKhoan.setText("");
    }

    private void tblDanhSachTaiKhoan() {
        taiKhoanDao = new TaiKhoanDAO();
        nhanVienDao = new NhanVienDAO();
        clearTable();
        DefaultTableModel dtm = (DefaultTableModel) tbl_DanhSachTaiKhoan.getModel();
        ArrayList<TaiKhoan> listTaiKhoan = taiKhoanDao.getAllTaiKhoanConHoatDong();
        for (TaiKhoan taiKhoan : listTaiKhoan) {
            Object[] rowData = {taiKhoan.getTenTaiKhoan(), taiKhoan.getNhanVien().getHoVaTen(), taiKhoan.getLoaiTaiKhoan() == true ? "Quản lý" : "Nhân viên"};
            dtm.addRow(rowData);
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_DatMatKhauMacDinh;
    private javax.swing.JButton btn_TimKiem;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lbl_ChucVu;
    private javax.swing.JLabel lbl_LoaitaiKhoan;
    private javax.swing.JLabel lbl_MaNhanVien;
    private javax.swing.JLabel lbl_MatKhau;
    private javax.swing.JLabel lbl_TenNhanVien;
    private javax.swing.JLabel lbl_TentaiKhoan;
    private javax.swing.JLabel lbl_TimKiem;
    private javax.swing.JTable tbl_DanhSachTaiKhoan;
    private javax.swing.JTextField txt_ChucVu;
    private javax.swing.JTextField txt_LoaiTaiKhoan;
    private javax.swing.JTextField txt_MaNhanVien;
    private javax.swing.JTextField txt_MatKhau;
    private javax.swing.JTextField txt_TenNhanVien;
    private javax.swing.JTextField txt_TenTaiKhoan;
    private javax.swing.JTextField txt_TimKiem;
    // End of variables declaration//GEN-END:variables
}
