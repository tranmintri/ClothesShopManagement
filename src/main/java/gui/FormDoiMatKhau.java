/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package gui;

import dao.TaiKhoanDAO;
import entity.NhanVien;
import entity.TaiKhoan;
import javax.swing.JOptionPane;

/**
 *
 * @author Admin
 */
public class FormDoiMatKhau extends javax.swing.JPanel {
    private NhanVien nhanVien = FormDangNhap.nhanVien;
    private TaiKhoanDAO taiKhoan_DAO = new TaiKhoanDAO();
    private TaiKhoan taiKhoan = taiKhoan_DAO.getTaiKhoanByMNV(nhanVien.getMaNhanVien());
    /**
     * Creates new form FormDoiMatKhau1
     */
    public FormDoiMatKhau() {
        initComponents();
    }

    private void kiemTraHandler(){
        if(String.valueOf(txt_matKhau.getPassword()).trim().equals("")){
            JOptionPane.showMessageDialog(null, "Vui lòng nhập mật khẩu");
            return;
        }
        
        if(!String.valueOf(txt_matKhau.getPassword()).trim().equals(taiKhoan.getMatKhau())){
            JOptionPane.showMessageDialog(null, "Mật khẩu không đúng.");
            return;
        }
        
        enableFormMatKhau();
    }
    
    private void doiMatKhauHandler(){
        if(String.valueOf(pwd_matKhauMoi.getPassword()).trim().equals("") || String.valueOf(pwd_nhapLaiMatKhauMoi.getPassword()).trim().equals("")){
            JOptionPane.showMessageDialog(null, "Vui lòng điền đầy đủ thông tin");
            return;
        }
        
        if(!String.valueOf(pwd_matKhauMoi.getPassword()).trim().equals(String.valueOf(pwd_nhapLaiMatKhauMoi.getPassword()).trim())){
            JOptionPane.showMessageDialog(null, "Nhập lại mật khẩu phải trùng với mật khẩu mới");
            return;
        }
        
        capNhatMatKhau();
        JOptionPane.showMessageDialog(null, "Cập nhật thành công");
        clearInput();
                
    }
    
    private void capNhatMatKhau(){
        taiKhoan.setMatKhau(String.valueOf(pwd_matKhauMoi.getPassword()).trim());
        taiKhoan_DAO.updateMatKhau(taiKhoan);
    }
    
    private void clearInput(){
        txt_matKhau.setText("");
        pwd_matKhauMoi.setText("");
        pwd_nhapLaiMatKhauMoi.setText("");
        disableFormMatKhau();
    }
    
    private void enableFormMatKhau(){
        pwd_matKhauMoi.setEnabled(true);
        pwd_nhapLaiMatKhauMoi.setEnabled(true);
        btn_doiMatKhau1.setEnabled(true);
        btn_kiemTra1.setEnabled(false);
        txt_matKhau.setEnabled(false);
    }
    
    private void disableFormMatKhau(){
        pwd_matKhauMoi.setEnabled(false);
        pwd_nhapLaiMatKhauMoi.setEnabled(false);
        btn_doiMatKhau1.setEnabled(false);
        btn_kiemTra1.setEnabled(true);
        txt_matKhau.setEnabled(true);
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        lbl_title1 = new javax.swing.JLabel();
        lbl_matKhau1 = new javax.swing.JLabel();
        btn_kiemTra1 = new javax.swing.JButton();
        lbl_nhapMatKhauMoi1 = new javax.swing.JLabel();
        lbl_nhapLaiMKM1 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        btn_doiMatKhau1 = new javax.swing.JButton();
        txt_matKhau = new javax.swing.JPasswordField();
        pwd_matKhauMoi = new javax.swing.JPasswordField();
        pwd_nhapLaiMatKhauMoi = new javax.swing.JPasswordField();

        setBackground(new java.awt.Color(255, 255, 255));

        jPanel2.setBackground(new java.awt.Color(102, 102, 255));

        lbl_title1.setFont(new java.awt.Font("Calibri", 1, 55)); // NOI18N
        lbl_title1.setForeground(new java.awt.Color(255, 255, 255));
        lbl_title1.setText("Đổi mật khẩu");
        lbl_title1.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);

        lbl_matKhau1.setFont(new java.awt.Font("Calibri", 1, 24)); // NOI18N
        lbl_matKhau1.setForeground(new java.awt.Color(255, 255, 255));
        lbl_matKhau1.setText("Nhập mật khẩu hiện tại: ");

        btn_kiemTra1.setFont(new java.awt.Font("Calibri", 0, 18)); // NOI18N
        btn_kiemTra1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/symbol_check.png"))); // NOI18N
        btn_kiemTra1.setText("Kiểm tra");
        btn_kiemTra1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_kiemTra1MouseClicked(evt);
            }
        });
        btn_kiemTra1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_kiemTra1ActionPerformed(evt);
            }
        });

        lbl_nhapMatKhauMoi1.setFont(new java.awt.Font("Calibri", 1, 24)); // NOI18N
        lbl_nhapMatKhauMoi1.setForeground(new java.awt.Color(255, 255, 255));
        lbl_nhapMatKhauMoi1.setText("Nhập mật khẩu mới:");

        lbl_nhapLaiMKM1.setFont(new java.awt.Font("Calibri", 1, 24)); // NOI18N
        lbl_nhapLaiMKM1.setForeground(new java.awt.Color(255, 255, 255));
        lbl_nhapLaiMKM1.setText("Nhập lại mật khẩu:");

        jButton2.setFont(new java.awt.Font("Calibri", 0, 18)); // NOI18N
        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/refresh-button.png"))); // NOI18N
        jButton2.setText("Reset");
        jButton2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton2MouseClicked(evt);
            }
        });
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        btn_doiMatKhau1.setFont(new java.awt.Font("Calibri", 0, 18)); // NOI18N
        btn_doiMatKhau1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icons-update.png"))); // NOI18N
        btn_doiMatKhau1.setText("Đổi mật khẩu");
        btn_doiMatKhau1.setEnabled(false);
        btn_doiMatKhau1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_doiMatKhau1MouseClicked(evt);
            }
        });
        btn_doiMatKhau1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_doiMatKhau1ActionPerformed(evt);
            }
        });

        txt_matKhau.setFont(new java.awt.Font("Calibri", 0, 20)); // NOI18N

        pwd_nhapLaiMatKhauMoi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pwd_nhapLaiMatKhauMoiActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(lbl_matKhau1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_matKhau, javax.swing.GroupLayout.PREFERRED_SIZE, 399, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(24, 24, 24)
                        .addComponent(btn_kiemTra1))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbl_nhapMatKhauMoi1)
                            .addComponent(lbl_nhapLaiMKM1))
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(93, 93, 93)
                                .addComponent(btn_doiMatKhau1, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(67, 67, 67)
                                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                                .addGap(45, 45, 45)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(pwd_matKhauMoi)
                                    .addComponent(pwd_nhapLaiMatKhauMoi, javax.swing.GroupLayout.DEFAULT_SIZE, 399, Short.MAX_VALUE))))))
                .addGap(0, 73, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lbl_title1, javax.swing.GroupLayout.PREFERRED_SIZE, 344, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(269, 269, 269))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addComponent(lbl_title1, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_kiemTra1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbl_matKhau1)
                    .addComponent(txt_matKhau, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(52, 52, 52)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbl_nhapMatKhauMoi1)
                    .addComponent(pwd_matKhauMoi, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(57, 57, 57)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lbl_nhapLaiMKM1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pwd_nhapLaiMatKhauMoi))
                .addGap(83, 83, 83)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btn_doiMatKhau1, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(107, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(114, Short.MAX_VALUE)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(111, 111, 111))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(82, Short.MAX_VALUE)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(83, 83, 83))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btn_kiemTra1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_kiemTra1MouseClicked
        // TODO add your handling code here:
        kiemTraHandler();
    }//GEN-LAST:event_btn_kiemTra1MouseClicked

    private void btn_kiemTra1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_kiemTra1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_kiemTra1ActionPerformed

    private void jButton2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton2MouseClicked
        // TODO add your handling code here:
        clearInput();
    }//GEN-LAST:event_jButton2MouseClicked

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton2ActionPerformed

    private void btn_doiMatKhau1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_doiMatKhau1MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_doiMatKhau1MouseClicked

    private void btn_doiMatKhau1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_doiMatKhau1ActionPerformed
        // TODO add your handling code here:
        doiMatKhauHandler();
    }//GEN-LAST:event_btn_doiMatKhau1ActionPerformed

    private void pwd_nhapLaiMatKhauMoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pwd_nhapLaiMatKhauMoiActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_pwd_nhapLaiMatKhauMoiActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_doiMatKhau1;
    private javax.swing.JButton btn_kiemTra1;
    private javax.swing.JButton jButton2;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JLabel lbl_matKhau1;
    private javax.swing.JLabel lbl_nhapLaiMKM1;
    private javax.swing.JLabel lbl_nhapMatKhauMoi1;
    private javax.swing.JLabel lbl_title1;
    private javax.swing.JPasswordField pwd_matKhauMoi;
    private javax.swing.JPasswordField pwd_nhapLaiMatKhauMoi;
    private javax.swing.JPasswordField txt_matKhau;
    // End of variables declaration//GEN-END:variables
}