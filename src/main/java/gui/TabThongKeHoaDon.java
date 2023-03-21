/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package gui;

import dao.ChiTietHoaDonDAO;
import dao.HoaDonDAO;
import dao.KhachHangDAO;
import dao.NhanVienDAO;
import entity.HoaDon;
import entity.KhachHang;
import entity.NhanVien;
import java.awt.Color;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Admin
 */
public class TabThongKeHoaDon extends javax.swing.JPanel {
    private KhachHangDAO khachHang_DAO = new KhachHangDAO();
    private NhanVienDAO nhanVien_DAO = new NhanVienDAO();
    private ChiTietHoaDonDAO cthd_DAO = new ChiTietHoaDonDAO();
    private HoaDonDAO hoaDon_DAO = new HoaDonDAO();
    
    /**
     * Creates new form TabThongKeHoaDon
     */
    public TabThongKeHoaDon() {
        initComponents();
        designTable();
        tableHoaDon();
        khoiTaoGiaTri();
    }
    
     private void designTable() {
        tbl_DanhSachHoaDon.getTableHeader().setFont(new java.awt.Font("Calibri", 1, 14));
        tbl_DanhSachHoaDon.getTableHeader().setOpaque(false);
        tbl_DanhSachHoaDon.getTableHeader().setBackground(new Color(102, 102, 255));
        tbl_DanhSachHoaDon.getTableHeader().setForeground(Color.WHITE);
        tbl_DanhSachHoaDon.setDefaultEditor(Object.class, null); // Không cho phép edit

//        tbl_danhSachNhanVien.getTableHeader()
    }
     
     private void khoiTaoGiaTri(){
         ArrayList<NhanVien> listNhanVien = nhanVien_DAO.getAllNhanVien();
         
         for(NhanVien nv : listNhanVien){
             cb_TenNhanVien.addItem(nv.getHoVaTen());
         }
         
         ArrayList<KhachHang> listKhachHang = khachHang_DAO.getAllKhachHang();
         for(KhachHang kh : listKhachHang){
             cb_TenKhachHang.addItem(kh.getHoVaTen());
         }
         
         cb_TatCa.setSelected(true);
         dc_TuNgay.setDate(Calendar.getInstance().getTime());
         dc_DenNgay.setDate(Calendar.getInstance().getTime());
     }
     
     private void clearTable(){
        DefaultTableModel dtm = (DefaultTableModel) tbl_DanhSachHoaDon.getModel();
        dtm.setRowCount(0);
     }
     
     private void tableHoaDon(){
         clearTable();
         
         String tenNhanVien = cb_TenNhanVien.getSelectedItem().toString();
         if(tenNhanVien.equals("Tất cả")) tenNhanVien = "";
         
         String tenKhachHang = cb_TenKhachHang.getSelectedItem().toString();
         if(tenKhachHang.equals("Tất cả")) tenKhachHang = "";
         
         ArrayList<HoaDon> listHoaDon = hoaDon_DAO.getAllHoaDon(tenKhachHang, tenNhanVien);
         
         double tongHoaDon = listHoaDon.size();
         double tongThanhTien = 0;
         DefaultTableModel dtm = (DefaultTableModel) tbl_DanhSachHoaDon.getModel();
         for(HoaDon hd : listHoaDon){
            double tongTien = cthd_DAO.tongTienHoaDon(hd.getMaHoaDon());
            tongThanhTien += tongTien;
            Object[] rowData = {hd.getMaHoaDon(), hd.getNhanVien().getHoVaTen(), hd.getKhachHang().getHoVaTen(), 
                new SimpleDateFormat("dd/MM/yyyy").format(hd.getNgayLap()), NumberFormat.getInstance().format(tongTien)};
            dtm.addRow(rowData);
         }
         
         lbl_KQTongDoanhThu.setText(NumberFormat.getInstance().format(tongThanhTien));
         lbl_KQTongHD.setText(NumberFormat.getInstance().format(tongHoaDon));
     }
     
     private void tableHoaDonTheoTime(){
         clearTable();
         
         String tenNhanVien = cb_TenNhanVien.getSelectedItem().toString();
         if(tenNhanVien.equals("Tất cả")) tenNhanVien = "";
         
         String tenKhachHang = cb_TenKhachHang.getSelectedItem().toString();
         if(tenKhachHang.equals("Tất cả")) tenKhachHang = "";
         
        
         
        
         
         String tuNgay = new SimpleDateFormat("yyyy-MM-dd").format( dc_TuNgay.getDate());
         String denNgay = new SimpleDateFormat("yyyy-MM-dd").format( dc_DenNgay.getDate());

          ArrayList<HoaDon> listHoaDon = hoaDon_DAO.getAllHoaDon(tenKhachHang, tenNhanVien, tuNgay, denNgay);
          
         double tongHoaDon = listHoaDon.size();
         double tongThanhTien = 0;
         
         DefaultTableModel dtm = (DefaultTableModel) tbl_DanhSachHoaDon.getModel();
         for(HoaDon hd : listHoaDon){
            double tongTien = cthd_DAO.tongTienHoaDon(hd.getMaHoaDon());
            tongThanhTien += tongTien;
            Object[] rowData = {hd.getMaHoaDon(), hd.getNhanVien().getHoVaTen(), hd.getKhachHang().getHoVaTen(), 
                new SimpleDateFormat("dd/MM/yyyy").format(hd.getNgayLap()), NumberFormat.getInstance().format(tongTien)};
            dtm.addRow(rowData);
         }
         
         lbl_KQTongDoanhThu.setText(NumberFormat.getInstance().format(tongThanhTien));
         lbl_KQTongHD.setText(NumberFormat.getInstance().format(tongHoaDon));
     }
     
     private boolean isTuNgayValid(){
         Date currentDate = Calendar.getInstance().getTime();
         if(dc_TuNgay.getDate().getTime() - currentDate.getTime() > 0){
             JOptionPane.showMessageDialog(null, "Ngày phải bé hơn hoặc bằng ngày hiện tại");
             return false;
         }
         
         return true;
     }
     
     private boolean isDenNgayValid(){
         Date currentDate = Calendar.getInstance().getTime();
         Date tuNgay = dc_TuNgay.getDate();
         Date denNgay = dc_DenNgay.getDate();
         
         if(denNgay.getTime() - currentDate.getTime() > 0){
             JOptionPane.showMessageDialog(null, "Ngày phải bé hơn hoặc bằng ngày hiện tại");
             return false;
         }
         
         if(tuNgay.getTime() - denNgay.getTime() > 0){
             JOptionPane.showMessageDialog(null, "Đến ngày phải có giá trị nhỏ hơn từ ngày");
                     
             return false;
         }
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

        jScrollPane1 = new javax.swing.JScrollPane();
        tbl_DanhSachHoaDon = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        lbl_TongSoHoaDon = new javax.swing.JLabel();
        lbl_KQTongHD = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        lbl_TongDoanhThu = new javax.swing.JLabel();
        lbl_KQTongDoanhThu = new javax.swing.JLabel();
        dc_TuNgay = new com.toedter.calendar.JDateChooser();
        dc_DenNgay = new com.toedter.calendar.JDateChooser();
        cb_TatCa = new javax.swing.JCheckBox();
        lbl_TenNhanVien = new javax.swing.JLabel();
        cb_TenNhanVien = new javax.swing.JComboBox<>();
        lbl_TenKhachHang = new javax.swing.JLabel();
        cb_TenKhachHang = new javax.swing.JComboBox<>();

        setBackground(new java.awt.Color(255, 255, 255));

        tbl_DanhSachHoaDon.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Mã hóa đơn", "Tên nhân viên", "Tên khách hàng", "Ngày lập", "Thành tiền"
            }
        ));
        tbl_DanhSachHoaDon.setRowHeight(30);
        jScrollPane1.setViewportView(tbl_DanhSachHoaDon);

        jLabel1.setFont(new java.awt.Font("Calibri", 1, 18)); // NOI18N
        jLabel1.setText("Từ ngày:");

        jLabel2.setFont(new java.awt.Font("Calibri", 1, 18)); // NOI18N
        jLabel2.setText("Đến ngày:");

        jPanel1.setBackground(new java.awt.Color(102, 102, 255));

        lbl_TongSoHoaDon.setFont(new java.awt.Font("Calibri", 1, 24)); // NOI18N
        lbl_TongSoHoaDon.setForeground(new java.awt.Color(255, 255, 255));
        lbl_TongSoHoaDon.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl_TongSoHoaDon.setText("Tổng số hóa đơn");

        lbl_KQTongHD.setFont(new java.awt.Font("Calibri", 1, 48)); // NOI18N
        lbl_KQTongHD.setForeground(new java.awt.Color(255, 255, 255));
        lbl_KQTongHD.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl_KQTongHD.setText("0");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbl_KQTongHD, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbl_TongSoHoaDon, javax.swing.GroupLayout.DEFAULT_SIZE, 222, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(lbl_TongSoHoaDon, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbl_KQTongHD, javax.swing.GroupLayout.DEFAULT_SIZE, 131, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel2.setBackground(new java.awt.Color(102, 102, 255));
        jPanel2.setPreferredSize(new java.awt.Dimension(215, 177));

        lbl_TongDoanhThu.setFont(new java.awt.Font("Calibri", 1, 24)); // NOI18N
        lbl_TongDoanhThu.setForeground(new java.awt.Color(255, 255, 255));
        lbl_TongDoanhThu.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl_TongDoanhThu.setText("Tổng doanh thu");

        lbl_KQTongDoanhThu.setFont(new java.awt.Font("Calibri", 1, 48)); // NOI18N
        lbl_KQTongDoanhThu.setForeground(new java.awt.Color(255, 255, 255));
        lbl_KQTongDoanhThu.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl_KQTongDoanhThu.setText("0");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbl_KQTongDoanhThu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbl_TongDoanhThu, javax.swing.GroupLayout.DEFAULT_SIZE, 297, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(lbl_TongDoanhThu, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbl_KQTongDoanhThu, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        dc_TuNgay.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                dc_TuNgayPropertyChange(evt);
            }
        });

        dc_DenNgay.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                dc_DenNgayPropertyChange(evt);
            }
        });

        cb_TatCa.setBackground(new java.awt.Color(255, 255, 255));
        cb_TatCa.setFont(new java.awt.Font("Calibri", 1, 18)); // NOI18N
        cb_TatCa.setText("Tất cả");
        cb_TatCa.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cb_TatCaItemStateChanged(evt);
            }
        });

        lbl_TenNhanVien.setFont(new java.awt.Font("Calibri", 1, 18)); // NOI18N
        lbl_TenNhanVien.setText("Tên nhân viên:");

        cb_TenNhanVien.setFont(new java.awt.Font("Calibri", 0, 18)); // NOI18N
        cb_TenNhanVien.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Tất cả" }));
        cb_TenNhanVien.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cb_TenNhanVienItemStateChanged(evt);
            }
        });

        lbl_TenKhachHang.setFont(new java.awt.Font("Calibri", 1, 18)); // NOI18N
        lbl_TenKhachHang.setText("Tên khách hàng:");

        cb_TenKhachHang.setFont(new java.awt.Font("Calibri", 0, 18)); // NOI18N
        cb_TenKhachHang.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Tất cả" }));
        cb_TenKhachHang.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cb_TenKhachHangItemStateChanged(evt);
            }
        });
        cb_TenKhachHang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cb_TenKhachHangActionPerformed(evt);
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
                        .addComponent(jScrollPane1)
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, 309, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(lbl_TenNhanVien)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(cb_TenNhanVien, javax.swing.GroupLayout.PREFERRED_SIZE, 333, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(dc_TuNgay, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(jLabel2)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(dc_DenNgay, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(cb_TatCa, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(32, 32, 32))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lbl_TenKhachHang)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(cb_TenKhachHang, javax.swing.GroupLayout.PREFERRED_SIZE, 334, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(31, 31, 31))))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(dc_TuNgay, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(dc_DenNgay, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cb_TatCa, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lbl_TenNhanVien, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cb_TenNhanVien, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lbl_TenKhachHang, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cb_TenKhachHang, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, 198, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 520, Short.MAX_VALUE)
                .addGap(34, 34, 34))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void cb_TenNhanVienItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cb_TenNhanVienItemStateChanged
        if(cb_TatCa.isSelected()){
            tableHoaDon();
        }else if(!cb_TatCa.isSelected()){
            tableHoaDonTheoTime();
        }
    }//GEN-LAST:event_cb_TenNhanVienItemStateChanged

    private void cb_TenKhachHangItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cb_TenKhachHangItemStateChanged
        // TODO add your handling code here:
        if(cb_TatCa.isSelected()){
            tableHoaDon();
        }else if(!cb_TatCa.isSelected()){
            tableHoaDonTheoTime();
        }
    }//GEN-LAST:event_cb_TenKhachHangItemStateChanged

    private void cb_TatCaItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cb_TatCaItemStateChanged
        // TODO add your handling code here:
        if(cb_TatCa.isSelected()){
            tableHoaDon();
        }else if(!cb_TatCa.isSelected()){
            tableHoaDonTheoTime();
        }
    }//GEN-LAST:event_cb_TatCaItemStateChanged

    private void dc_TuNgayPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_dc_TuNgayPropertyChange
        // TODO add your handling code here:
        if(!isTuNgayValid()) return; 
        
         if(cb_TatCa.isSelected()){
            tableHoaDon();
        }else if(!cb_TatCa.isSelected()){
            tableHoaDonTheoTime();
        }
    }//GEN-LAST:event_dc_TuNgayPropertyChange

    private void dc_DenNgayPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_dc_DenNgayPropertyChange
        // TODO add your handling code here:
        if(!isDenNgayValid()) return;
        
         if(cb_TatCa.isSelected()){
            tableHoaDon();
        }else if(!cb_TatCa.isSelected()){
            tableHoaDonTheoTime();
        }
    }//GEN-LAST:event_dc_DenNgayPropertyChange

    private void cb_TenKhachHangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cb_TenKhachHangActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cb_TenKhachHangActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JCheckBox cb_TatCa;
    private javax.swing.JComboBox<String> cb_TenKhachHang;
    private javax.swing.JComboBox<String> cb_TenNhanVien;
    private com.toedter.calendar.JDateChooser dc_DenNgay;
    private com.toedter.calendar.JDateChooser dc_TuNgay;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lbl_KQTongDoanhThu;
    private javax.swing.JLabel lbl_KQTongHD;
    private javax.swing.JLabel lbl_TenKhachHang;
    private javax.swing.JLabel lbl_TenNhanVien;
    private javax.swing.JLabel lbl_TongDoanhThu;
    private javax.swing.JLabel lbl_TongSoHoaDon;
    private javax.swing.JTable tbl_DanhSachHoaDon;
    // End of variables declaration//GEN-END:variables
}
