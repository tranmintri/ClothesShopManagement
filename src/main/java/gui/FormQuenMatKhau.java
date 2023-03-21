/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package gui;

import dao.NhanVienDAO;
import dao.TaiKhoanDAO;
import entity.NhanVien;
import entity.TaiKhoan;
import java.io.UnsupportedEncodingException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Properties;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.swing.JOptionPane;

/**
 *
 * @author 20086
 */
public class FormQuenMatKhau extends javax.swing.JFrame {
    private NhanVienDAO nhanVien_DAO = new NhanVienDAO();
    private TaiKhoanDAO taiKhoan_DAO = new TaiKhoanDAO();
    /**
     * Creates new form FormDoiMatKhau
     */
    public FormQuenMatKhau() {
        initComponents();
    }

    private boolean tonTaiNhanVien(){
        if(nhanVien_DAO.getNhanVienByGmail(txt_gmail.getText()) == null){
            JOptionPane.showMessageDialog(null, "Không tìm thấy nhân viên sở hữu email này");
            return false;
        }
        
        return true;
    }
       private void guiMaHandler(){
        try {
            String fromEmail = "ddryh21102002@gmail.com";
            String password = "pvluosuwbagbfoqf";
            String toEmail = txt_gmail.getText();
            
            String subject = "Send email test";
            String OTP = String.format("%06d",new Random().nextInt(999999));
            
            Timestamp expiredAt = new Timestamp(System.currentTimeMillis() + 10 * 60 * 1000);
            
            if(nhanVien_DAO.updateOTP(toEmail, OTP, expiredAt) == -1) return;
            
            String body = "Mã OTP: " + OTP +"\n\n\n\n OTP hết hạn sau 10 phút";
            Properties props = new Properties();
            props.put("mail.smtp.host", "smtp.gmail.com"); //SMTP Host
            props.put("mail.smtp.port", "587"); //TLS Port
            props.put("mail.smtp.auth", "true"); //enable authentication
            props.put("mail.smtp.starttls.enable", "true"); //enable STARTTLS
            
            javax.mail.Authenticator auth = new javax.mail.Authenticator() {
                protected javax.mail.PasswordAuthentication getPasswordAuthentication() {
                    return new javax.mail.PasswordAuthentication(fromEmail, password);
                }
            };
            
            Session session = Session.getInstance(props, auth);
            
            MimeMessage msg = new MimeMessage(session);
            //set message headers
            msg.addHeader("Content-type", "text/HTML; charset=UTF-8");
            msg.addHeader("format", "flowed");
            msg.addHeader("Content-Transfer-Encoding", "8bit");
            msg.setFrom(new InternetAddress(fromEmail, "NoReply-JD"));
            msg.setReplyTo(InternetAddress.parse(fromEmail, false));
            msg.setSubject(subject, "UTF-8");
            msg.setText(body, "UTF-8");
            msg.setSentDate(new Date());
            msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail, false));
            javax.mail.Transport.send(msg);
            System.out.println("Gui mail thanh cong");
            JOptionPane.showMessageDialog(null, "Gửi OTP thành công. Vui lòng kiểm tra gmail");
            txt_gmail.setEnabled(false);
        } catch (MessagingException ex) {
            Logger.getLogger(FormSanPham.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(FormSanPham.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    
    private void enableMatKhauMoi(){
        txt_matKhauMoi.setEnabled(true);
        txt_nhapLaiMK.setEnabled(true);
        btn_doiMatKhau.setEnabled(true);
    }
    
    private void enableGmailOTP(){
        txt_gmail.setEnabled(true);
        txt_maOTP.setEnabled(true);
    }
    
    private void kiemTraOTPHandler(){
        NhanVien nhanVien = nhanVien_DAO.getNhanVienByGmail(txt_gmail.getText());
        Timestamp currentTime = new Timestamp(System.currentTimeMillis());
        
        if(nhanVien.getOTP().equals(txt_maOTP.getText().trim())){
            if(nhanVien.getExpriedAt().getTime() - currentTime.getTime() < 0){
                JOptionPane.showMessageDialog(null, "Mã OTP đã hết hạn");
                return;
            }
            
            JOptionPane.showMessageDialog(null, "OTP hợp lệ. Vui lòng nhập mật khẩu mới");
            enableMatKhauMoi();
            txt_maOTP.setEnabled(false);
        }
        else{
            JOptionPane.showMessageDialog(null, "OTP không trùng khớp");
            return;
        }
    }
    
    private void resetForm(){
        txt_gmail.setEnabled(true);
        txt_maOTP.setEnabled(true);
        txt_matKhauMoi.setEnabled(false);
        txt_nhapLaiMK.setEnabled(false);
        btn_doiMatKhau.setEnabled(false);
        clearInput();
    }
    
    private void clearInput(){
        txt_gmail.setText("");
        txt_maOTP.setText("");
        txt_matKhauMoi.setText("");
        txt_nhapLaiMK.setText("");
    }
    private void doiMatKhauHandler(){
        if(String.valueOf(txt_matKhauMoi.getPassword()).trim().equals("")){
            JOptionPane.showMessageDialog(null, "Vui lòng nhập mật khẩu mới");
            return; 
        }
        
        if(String.valueOf(txt_nhapLaiMK.getPassword()).trim().equals(String.valueOf(txt_matKhauMoi.getPassword()).trim())){
            NhanVien nv = nhanVien_DAO.getNhanVienByGmail(txt_gmail.getText().trim());
            if(nv == null) return;
            
            TaiKhoan tk = taiKhoan_DAO.getTaiKhoanByMNV(nv.getMaNhanVien());
            if(tk == null) return;
            
            tk.setMatKhau(String.valueOf(txt_matKhauMoi.getPassword()).trim());
            if(taiKhoan_DAO.updateMatKhau(tk) == -1) return;
            
            JOptionPane.showMessageDialog(null, "Đổi mật khẩu mới thành công");
            resetForm();
            return;
        }
        
        JOptionPane.showMessageDialog(null, "Mã OTP không hợp lệ.");
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
        lbl_title = new javax.swing.JLabel();
        btn_guiMa = new javax.swing.JButton();
        lbl_nhapGmail = new javax.swing.JLabel();
        txt_gmail = new javax.swing.JTextField();
        lbl_maOTP = new javax.swing.JLabel();
        txt_maOTP = new javax.swing.JTextField();
        btn_kiemTra = new javax.swing.JButton();
        lbl_nhapMatKhauMoi = new javax.swing.JLabel();
        lbl_nhapLaiMKM = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        btn_doiMatKhau = new javax.swing.JButton();
        txt_matKhauMoi = new javax.swing.JPasswordField();
        txt_nhapLaiMK = new javax.swing.JPasswordField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        jPanel1.setBackground(new java.awt.Color(102, 102, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createMatteBorder(3, 3, 3, 3, new java.awt.Color(0, 0, 0)));

        lbl_title.setFont(new java.awt.Font("Calibri", 1, 48)); // NOI18N
        lbl_title.setForeground(new java.awt.Color(255, 255, 255));
        lbl_title.setText("QUÊN MẬT KHẨU");
        lbl_title.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);

        btn_guiMa.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        btn_guiMa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/key.png"))); // NOI18N
        btn_guiMa.setText("Gửi mã");
        btn_guiMa.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_guiMaMouseClicked(evt);
            }
        });
        btn_guiMa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_guiMaActionPerformed(evt);
            }
        });

        lbl_nhapGmail.setFont(new java.awt.Font("Calibri", 1, 18)); // NOI18N
        lbl_nhapGmail.setForeground(new java.awt.Color(255, 255, 255));
        lbl_nhapGmail.setText("Nhập gmail: ");

        txt_gmail.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        txt_gmail.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_gmailActionPerformed(evt);
            }
        });

        lbl_maOTP.setFont(new java.awt.Font("Calibri", 1, 18)); // NOI18N
        lbl_maOTP.setForeground(new java.awt.Color(255, 255, 255));
        lbl_maOTP.setText("Nhập mã OTP:");

        txt_maOTP.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        txt_maOTP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_maOTPActionPerformed(evt);
            }
        });

        btn_kiemTra.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        btn_kiemTra.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/symbol_check.png"))); // NOI18N
        btn_kiemTra.setText("Kiểm tra");
        btn_kiemTra.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_kiemTraMouseClicked(evt);
            }
        });

        lbl_nhapMatKhauMoi.setFont(new java.awt.Font("Calibri", 1, 18)); // NOI18N
        lbl_nhapMatKhauMoi.setForeground(new java.awt.Color(255, 255, 255));
        lbl_nhapMatKhauMoi.setText("Nhập mật khẩu mới:");

        lbl_nhapLaiMKM.setFont(new java.awt.Font("Calibri", 1, 18)); // NOI18N
        lbl_nhapLaiMKM.setForeground(new java.awt.Color(255, 255, 255));
        lbl_nhapLaiMKM.setText("Nhập lại mật khẩu:");

        jButton1.setFont(new java.awt.Font("sansserif", 0, 14)); // NOI18N
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/sign-error-icon.png"))); // NOI18N
        jButton1.setText("Hủy");
        jButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton1MouseClicked(evt);
            }
        });
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        btn_doiMatKhau.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        btn_doiMatKhau.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icons-update.png"))); // NOI18N
        btn_doiMatKhau.setText("Đổi mật khẩu");
        btn_doiMatKhau.setEnabled(false);
        btn_doiMatKhau.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_doiMatKhauMouseClicked(evt);
            }
        });
        btn_doiMatKhau.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_doiMatKhauActionPerformed(evt);
            }
        });

        txt_matKhauMoi.setEnabled(false);

        txt_nhapLaiMK.setEnabled(false);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbl_maOTP)
                            .addComponent(lbl_nhapGmail))
                        .addGap(76, 76, 76))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                            .addComponent(lbl_nhapMatKhauMoi)
                            .addGap(29, 29, 29))
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addComponent(lbl_nhapLaiMKM)
                            .addGap(42, 42, 42))))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addComponent(lbl_title, javax.swing.GroupLayout.PREFERRED_SIZE, 372, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txt_matKhauMoi, javax.swing.GroupLayout.PREFERRED_SIZE, 443, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(txt_gmail, javax.swing.GroupLayout.DEFAULT_SIZE, 443, Short.MAX_VALUE)
                                .addComponent(txt_maOTP))
                            .addComponent(txt_nhapLaiMK, javax.swing.GroupLayout.PREFERRED_SIZE, 443, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 27, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btn_guiMa, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btn_kiemTra, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addGap(32, 32, 32))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btn_doiMatKhau, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(77, 77, 77)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(236, 236, 236))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(lbl_title, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(44, 44, 44)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txt_gmail, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btn_guiMa, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(lbl_nhapGmail))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_maOTP, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbl_maOTP)
                    .addComponent(btn_kiemTra, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbl_nhapMatKhauMoi)
                    .addComponent(txt_matKhauMoi, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbl_nhapLaiMKM)
                    .addComponent(txt_nhapLaiMK, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(49, 49, 49)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btn_doiMatKhau, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(33, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void txt_maOTPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_maOTPActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_maOTPActionPerformed

    private void btn_guiMaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_guiMaMouseClicked
        // TODO add your handling code here:
        if(!tonTaiNhanVien()) return;
        guiMaHandler();
    }//GEN-LAST:event_btn_guiMaMouseClicked

    private void btn_kiemTraMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_kiemTraMouseClicked
        // TODO add your handling code here:
        kiemTraOTPHandler();
    }//GEN-LAST:event_btn_kiemTraMouseClicked

    private void btn_doiMatKhauMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_doiMatKhauMouseClicked
        // TODO add your handling code here:
        doiMatKhauHandler();
    }//GEN-LAST:event_btn_doiMatKhauMouseClicked

    private void jButton1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton1MouseClicked
            // TODO add your handling code here:
       
        this.setVisible(false);
    }//GEN-LAST:event_jButton1MouseClicked

    private void btn_guiMaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_guiMaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_guiMaActionPerformed

    private void txt_gmailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_gmailActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_gmailActionPerformed

    private void btn_doiMatKhauActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_doiMatKhauActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_doiMatKhauActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed

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
            java.util.logging.Logger.getLogger(FormQuenMatKhau.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FormQuenMatKhau.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FormQuenMatKhau.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FormQuenMatKhau.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FormQuenMatKhau().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_doiMatKhau;
    private javax.swing.JButton btn_guiMa;
    private javax.swing.JButton btn_kiemTra;
    private javax.swing.JButton jButton1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel lbl_maOTP;
    private javax.swing.JLabel lbl_nhapGmail;
    private javax.swing.JLabel lbl_nhapLaiMKM;
    private javax.swing.JLabel lbl_nhapMatKhauMoi;
    private javax.swing.JLabel lbl_title;
    private javax.swing.JTextField txt_gmail;
    private javax.swing.JTextField txt_maOTP;
    private javax.swing.JPasswordField txt_matKhauMoi;
    private javax.swing.JPasswordField txt_nhapLaiMK;
    // End of variables declaration//GEN-END:variables
}
