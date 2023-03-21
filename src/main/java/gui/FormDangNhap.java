/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package gui;

import ConnectDB.KetNoiSQL;
import dao.NhanVienDAO;
import entity.NhanVien;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;

/**
 *
 * @author Admin
 */
public class FormDangNhap extends javax.swing.JFrame {

    public static NhanVien nhanVien = null;
    private NhanVienDAO nv_DAO = new NhanVienDAO();
    /**
     * Creates new form FormDangNhap
     */
    public FormDangNhap() {
        KetNoiSQL.getInstance().connect();
        initComponents();
        
        txt_TaiKhoan.setBackground(new java.awt.Color(0,0,0,1));
        txt_TaiKhoan.setOpaque(false);
        pwd_MatKhau.setBackground(new java.awt.Color(0,0,0,1));
        pwd_MatKhau.setOpaque(false);
        lbl_showicon.setVisible(false);
        lbl_hiddenicon.setVisible(true);
    }
    private void doiMatKhauHandler(){
        KetNoiSQL.getInstance().connect();
        new FormQuenMatKhau().setVisible(true);
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollBar1 = new javax.swing.JScrollBar();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        lbl_showicon = new javax.swing.JLabel();
        lbl_usericon = new javax.swing.JLabel();
        lbl_hiddenicon = new javax.swing.JLabel();
        lbl_QuenMatKhau = new javax.swing.JLabel();
        btn_DangNhap = new javax.swing.JButton();
        lbl_Thoat = new javax.swing.JLabel();
        txt_TaiKhoan = new javax.swing.JTextField();
        pwd_MatKhau = new javax.swing.JPasswordField();
        jPanel3 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createMatteBorder(3, 3, 3, 3, new java.awt.Color(0, 0, 0)));

        jPanel2.setBackground(new java.awt.Color(102, 102, 255));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setBackground(new java.awt.Color(255, 255, 255));
        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("ĐĂNG NHẬP");
        jPanel2.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 30, 230, 50));
        jPanel2.add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 90, 320, 10));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Mật khẩu:");
        jPanel2.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 210, 100, 30));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Tài khoản:");
        jPanel2.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 120, 100, 30));

        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("_________________________________________________________________");
        jLabel4.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        jPanel2.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 280, 460, 10));

        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("_________________________________________________________________");
        jLabel5.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        jPanel2.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 190, 470, 10));

        lbl_showicon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/show-password.png"))); // NOI18N
        lbl_showicon.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbl_showiconMouseClicked(evt);
            }
        });
        jPanel2.add(lbl_showicon, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 240, 60, 50));

        lbl_usericon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/userLogin.png"))); // NOI18N
        jPanel2.add(lbl_usericon, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 150, 50, 50));

        lbl_hiddenicon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/hidden-password.png"))); // NOI18N
        lbl_hiddenicon.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbl_hiddeniconMouseClicked(evt);
            }
        });
        jPanel2.add(lbl_hiddenicon, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 240, 60, 50));

        lbl_QuenMatKhau.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lbl_QuenMatKhau.setForeground(new java.awt.Color(255, 255, 255));
        lbl_QuenMatKhau.setText("Quên mật khẩu?");
        lbl_QuenMatKhau.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbl_QuenMatKhauMouseClicked(evt);
            }
        });
        jPanel2.add(lbl_QuenMatKhau, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 300, 120, 30));

        btn_DangNhap.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btn_DangNhap.setText("Đăng nhập");
        btn_DangNhap.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, java.awt.Color.white, java.awt.Color.yellow, java.awt.Color.orange, java.awt.Color.black));
        btn_DangNhap.setBorderPainted(false);
        btn_DangNhap.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_DangNhapMouseClicked(evt);
            }
        });
        jPanel2.add(btn_DangNhap, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 350, 320, 40));

        lbl_Thoat.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/exit.png"))); // NOI18N
        lbl_Thoat.setText(" ");
        lbl_Thoat.setAlignmentX(0.5F);
        lbl_Thoat.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbl_ThoatMouseClicked(evt);
            }
        });
        jPanel2.add(lbl_Thoat, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 0, 40, 40));

        txt_TaiKhoan.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        txt_TaiKhoan.setForeground(new java.awt.Color(255, 255, 255));
        txt_TaiKhoan.setText("admin");
        txt_TaiKhoan.setBorder(null);
        jPanel2.add(txt_TaiKhoan, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 160, 470, 40));

        pwd_MatKhau.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        pwd_MatKhau.setForeground(new java.awt.Color(255, 255, 255));
        pwd_MatKhau.setText("admin");
        pwd_MatKhau.setBorder(null);
        jPanel2.add(pwd_MatKhau, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 250, 470, 40));

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));

        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/shopping-cart.png"))); // NOI18N

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(47, 47, 47)
                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 311, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(55, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(41, 41, 41)
                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 317, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 581, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, 455, Short.MAX_VALUE)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void lbl_ThoatMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbl_ThoatMouseClicked
        // TODO add your handling code here:
        System.exit(0);
    }//GEN-LAST:event_lbl_ThoatMouseClicked

    private void lbl_showiconMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbl_showiconMouseClicked
        // TODO add your handling code here:
        pwd_MatKhau.setEchoChar((char)8226);
        lbl_showicon.setVisible(false);
        lbl_showicon.setEnabled(false);
        lbl_hiddenicon.setEnabled(true);
        lbl_hiddenicon.setVisible(true);
    }//GEN-LAST:event_lbl_showiconMouseClicked

    private void lbl_hiddeniconMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbl_hiddeniconMouseClicked
        // TODO add your handling code here:
          pwd_MatKhau.setEchoChar((char)0);
        lbl_hiddenicon.setVisible(false);
        lbl_hiddenicon.setEnabled(false);
        lbl_showicon.setEnabled(true);
        lbl_showicon.setVisible(true);
    }//GEN-LAST:event_lbl_hiddeniconMouseClicked

    private void btn_DangNhapMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_DangNhapMouseClicked
        // TODO add your handling code here:
         Connection conn = KetNoiSQL.getConnection();
        try{
        String sql="SELECT * FROM TaiKhoan WHERE tenTaiKhoan=? AND matKhau=? and (isDeleted is null or isDeleted = 0)";
        PreparedStatement stmt= conn.prepareCall(sql);
        stmt.setString(1, txt_TaiKhoan.getText());
        stmt.setString(2, String.valueOf(pwd_MatKhau.getPassword()));
        ResultSet rs= stmt.executeQuery();
        if(rs.next()){
            JOptionPane.showMessageDialog(null,"Đăng nhập thành công");
            nhanVien = nv_DAO.getNhanVienByID(rs.getString(4));
            this.setVisible(false);
            new FormLoading().setVisible(true);
           //new TrangChu().setVisible(true);
        }else{
            JOptionPane.showMessageDialog(null, "Tài khoản hoặc mật khẩu không đúng");
            pwd_MatKhau.setText("");
        }
        }catch(Exception ex){
        JOptionPane.showMessageDialog(null, ex);
        }
    }//GEN-LAST:event_btn_DangNhapMouseClicked

    private void lbl_QuenMatKhauMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbl_QuenMatKhauMouseClicked
        // TODO add your handling code here:
         doiMatKhauHandler();
    }//GEN-LAST:event_lbl_QuenMatKhauMouseClicked

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(FormDangNhap.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FormDangNhap.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FormDangNhap.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FormDangNhap.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FormDangNhap().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_DangNhap;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollBar jScrollBar1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JLabel lbl_QuenMatKhau;
    private javax.swing.JLabel lbl_Thoat;
    private javax.swing.JLabel lbl_hiddenicon;
    private javax.swing.JLabel lbl_showicon;
    private javax.swing.JLabel lbl_usericon;
    private javax.swing.JPasswordField pwd_MatKhau;
    private javax.swing.JTextField txt_TaiKhoan;
    // End of variables declaration//GEN-END:variables
}