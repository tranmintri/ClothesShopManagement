/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package gui;

import dao.NhaCungCapDAO;
import entity.NhaCungCap;
import java.awt.Color;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Admin
 */
public class FormNhaCungCap extends javax.swing.JPanel {
    private NhaCungCapDAO nhaCC_DAO = new NhaCungCapDAO();
    
    private boolean isThemActive = false;
    private boolean isSuaActive = false;
    
    /**
     * Creates new form FormNhaCungCap
     */
    public FormNhaCungCap() {
        initComponents();
        designTable();
        tblDanhSachNhaCungCap();

    }
    
    private void designTable(){
        tbl_danhSachNhaCungCap.getTableHeader().setFont(new java.awt.Font("Calibri", 1, 14));
        tbl_danhSachNhaCungCap.getTableHeader().setOpaque(false);
        tbl_danhSachNhaCungCap.getTableHeader().setBackground(new Color(102, 102, 255));
        tbl_danhSachNhaCungCap.getTableHeader().setForeground(Color.WHITE);
        tbl_danhSachNhaCungCap.setDefaultEditor(Object.class, null); // Không cho phép edit
        
      
//        tbl_danhSachNhanVien.getTableHeader()
    }
    
    private void isThemKhachHangClicked(boolean check){
        isThemActive = check;
        isSuaActive = !check;
        
        if(isThemActive){
            btn_Them.setText("Huỷ");
            btn_sua.setEnabled(false);
        }
        else if(isSuaActive){
            btn_sua.setText("Huỷ");
            btn_Them.setEnabled(false);
        }
        
        btn_luu.setEnabled(true);
        isInputActive(true);
    }
    
    public void huyThaoTac(){
        isSuaActive = false;
        isThemActive = false;
        btn_Them.setText("Thêm");
        btn_sua.setText("Cập nhật");
        btn_Them.setEnabled(true);
        btn_sua.setEnabled(true);
        btn_luu.setEnabled(false);
        tbl_danhSachNhaCungCap.clearSelection();    
        isInputActive(false);
    }

    private void isInputActive(boolean check){
        txt_diaChi.setEnabled(check);
        txt_email.setEnabled(check);
        txt_nhaCungCap.setEnabled(check);
        txt_soDienThoai.setEnabled(check);
    }
    
    private void tblDanhSachNhaCungCap(){
        clearTable();
        DefaultTableModel dtm = (DefaultTableModel) tbl_danhSachNhaCungCap.getModel();
        ArrayList<NhaCungCap> listNhaCungCap = nhaCC_DAO.getAllNhaCungCap();
        
        for(NhaCungCap ncc : listNhaCungCap){
            Object[] rowData = {ncc.getMaNhaCungCap(), ncc.getTenNhaCungCap(), ncc.getSdt(), ncc.getEmail(), ncc.getDiaChi()};
            dtm.addRow(rowData);
        }
    }
    
    private void clearTable(){
        DefaultTableModel dtm = (DefaultTableModel) tbl_danhSachNhaCungCap.getModel();
        dtm.setRowCount(0);
    }
    
    private void clearInput(){
        txt_diaChi.setText("");
        txt_email.setText("");
        txt_nhaCungCap.setText("");
        txt_soDienThoai.setText("");
    }
    
    private boolean isValidInput(){
        if(txt_diaChi.getText().equals("") || txt_email.getText().equals("") || txt_nhaCungCap.getText().equals("") || txt_soDienThoai.getText().equals("")){
            JOptionPane.showMessageDialog(null, "Vui lòng điền đầy đủ thông tin");
            return false;
        }
        
        return true;
    }
    
    private void themNhaCungCapHandler(){
        if(!isValidInput()) return;
        
        String tenNhaCungCap = txt_nhaCungCap.getText();
        String sdt = txt_soDienThoai.getText();
        String diaChi = txt_diaChi.getText();
        String email = txt_email.getText();
        
        NhaCungCap ncc = new NhaCungCap(tenNhaCungCap, diaChi, sdt, email);
        
        if(nhaCC_DAO.addNhaCungCap(ncc) != -1){
            JOptionPane.showMessageDialog(null, "Thêm thành công");
            tblDanhSachNhaCungCap();
            clearInput();
            huyThaoTac();
            return;
        }
        JOptionPane.showMessageDialog(null, "Thêm thất bại");
        huyThaoTac();
    }
    
    private void capNhatNhaCungCapHandler(){
        
        if (!isValidInput()) {
            return;
        }
        String id = (String) tbl_danhSachNhaCungCap.getValueAt(tbl_danhSachNhaCungCap.getSelectedRow(), 0);
        NhaCungCap ncc = nhaCC_DAO.getNhaCungCap(id);

        ncc.setDiaChi(txt_diaChi.getText());
        ncc.setEmail(txt_email.getText());
        ncc.setSdt(txt_soDienThoai.getText().trim());
        ncc.setTenNhaCungCap(txt_nhaCungCap.getText());
        

        if (nhaCC_DAO.updateNhaCungCap(ncc) != -1) {
            JOptionPane.showMessageDialog(null, "Cập nhật thành công");
           tblDanhSachNhaCungCap();
            clearInput();    
            huyThaoTac();
            return;
        }
        JOptionPane.showMessageDialog(null, "Cập nhật thất bại");
        huyThaoTac();
    }
    
    private void timNhaCungCapHandler(){
        String tenNhaCungCap = txt_timNhaCungCap.getText();
        if(tenNhaCungCap.equals("")){
            
            return;
        }
        
        ArrayList<NhaCungCap> listNhaCungCap = nhaCC_DAO.getNhaCungCapByTen(tenNhaCungCap);
        
        clearTable();
        DefaultTableModel dtm = (DefaultTableModel) tbl_danhSachNhaCungCap.getModel();
        for(NhaCungCap ncc : listNhaCungCap){
            Object[] rowData = {ncc.getMaNhaCungCap(), ncc.getTenNhaCungCap(), ncc.getSdt(), ncc.getEmail(), ncc.getDiaChi()};
            dtm.addRow(rowData);
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

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbl_danhSachNhaCungCap = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        lbl_tenNhaCungCap = new javax.swing.JLabel();
        txt_nhaCungCap = new javax.swing.JTextField();
        txt_diaChi = new javax.swing.JTextField();
        lbl_diaChi = new javax.swing.JLabel();
        lbl_soDienThoai = new javax.swing.JLabel();
        txt_soDienThoai = new javax.swing.JTextField();
        txt_email = new javax.swing.JTextField();
        lbl_email = new javax.swing.JLabel();
        btn_Them = new javax.swing.JButton();
        btn_sua = new javax.swing.JButton();
        btn_luu = new javax.swing.JButton();
        lbl_timNhaCungCap = new javax.swing.JLabel();
        txt_timNhaCungCap = new javax.swing.JTextField();
        btn_Tim = new javax.swing.JButton();

        setBackground(new java.awt.Color(255, 255, 255));
        setBorder(javax.swing.BorderFactory.createEmptyBorder(20, 20, 20, 20));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Danh sách nhà cung cấp"));

        tbl_danhSachNhaCungCap.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Mã nhà cung cấp", "Tên nhà cung cấp", "Số điện thoại", "Email", "Địa chỉ"
            }
        ));
        tbl_danhSachNhaCungCap.setRowHeight(30);
        tbl_danhSachNhaCungCap.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tbl_danhSachNhaCungCapMousePressed(evt);
            }
        });
        jScrollPane1.setViewportView(tbl_danhSachNhaCungCap);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1078, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 485, Short.MAX_VALUE)
                .addContainerGap())
        );

        add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 210, 1100, 520));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Chi tiết nhà cung cấp", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Calibri", 0, 14))); // NOI18N

        lbl_tenNhaCungCap.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        lbl_tenNhaCungCap.setText("Tên nhà cung cấp:");

        txt_nhaCungCap.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        txt_nhaCungCap.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txt_nhaCungCap.setEnabled(false);

        txt_diaChi.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        txt_diaChi.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txt_diaChi.setEnabled(false);

        lbl_diaChi.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        lbl_diaChi.setText("Địa chỉ:");

        lbl_soDienThoai.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        lbl_soDienThoai.setText("Số điện thoại:");

        txt_soDienThoai.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        txt_soDienThoai.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txt_soDienThoai.setEnabled(false);

        txt_email.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        txt_email.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txt_email.setEnabled(false);

        lbl_email.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        lbl_email.setText("Email:");

        btn_Them.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icons-add.png"))); // NOI18N
        btn_Them.setText("Thêm");
        btn_Them.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_ThemActionPerformed(evt);
            }
        });

        btn_sua.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/service_manager.png"))); // NOI18N
        btn_sua.setText("Cập nhật");
        btn_sua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_suaActionPerformed(evt);
            }
        });

        btn_luu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/save.png"))); // NOI18N
        btn_luu.setText("Lưu");
        btn_luu.setEnabled(false);
        btn_luu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_luuActionPerformed(evt);
            }
        });

        lbl_timNhaCungCap.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        lbl_timNhaCungCap.setText("Tìm kiếm nhà cung cấp:");

        txt_timNhaCungCap.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_timNhaCungCapKeyReleased(evt);
            }
        });

        btn_Tim.setText("Tìm");
        btn_Tim.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_TimActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(lbl_tenNhaCungCap)
                            .addComponent(lbl_diaChi)
                            .addComponent(txt_nhaCungCap, javax.swing.GroupLayout.DEFAULT_SIZE, 350, Short.MAX_VALUE)
                            .addComponent(txt_diaChi, javax.swing.GroupLayout.DEFAULT_SIZE, 350, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(txt_email)
                            .addComponent(txt_soDienThoai, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbl_soDienThoai, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbl_email, javax.swing.GroupLayout.Alignment.LEADING)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(lbl_timNhaCungCap)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txt_timNhaCungCap, javax.swing.GroupLayout.PREFERRED_SIZE, 287, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btn_Tim, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 266, Short.MAX_VALUE)))
                .addGap(98, 98, 98)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btn_sua, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_Them, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_luu, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(49, 49, 49))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btn_Them, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btn_sua, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btn_luu, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lbl_timNhaCungCap)
                            .addComponent(txt_timNhaCungCap, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btn_Tim, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 22, Short.MAX_VALUE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(lbl_tenNhaCungCap)
                                .addGap(2, 2, 2)
                                .addComponent(txt_nhaCungCap, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lbl_diaChi)
                                .addGap(2, 2, 2)
                                .addComponent(txt_diaChi, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(lbl_soDienThoai)
                                .addGap(2, 2, 2)
                                .addComponent(txt_soDienThoai, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(6, 6, 6)
                                .addComponent(lbl_email)
                                .addGap(2, 2, 2)
                                .addComponent(txt_email, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap())
        );

        add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 1100, 200));
    }// </editor-fold>//GEN-END:initComponents
    

  
    
   
    
    private void txt_timNhaCungCapKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_timNhaCungCapKeyReleased
        // TODO add your handling code here:
        //if(txt_timNhaCungCap.getText().equals("")) tblDanhSachNhaCungCap();
        //timNhaCungCapHandler();
    }//GEN-LAST:event_txt_timNhaCungCapKeyReleased

    private void tbl_danhSachNhaCungCapMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_danhSachNhaCungCapMousePressed
        // TODO add your handling code here:
        int row = tbl_danhSachNhaCungCap.getSelectedRow();
        
        DefaultTableModel dtm = (DefaultTableModel) tbl_danhSachNhaCungCap.getModel();
        
             txt_diaChi.setText(dtm.getValueAt(row, 4).toString());
        txt_email.setText(dtm.getValueAt(row, 3).toString());
        txt_nhaCungCap.setText(dtm.getValueAt(row, 1).toString());
        txt_soDienThoai.setText(dtm.getValueAt(row, 2).toString());
    }//GEN-LAST:event_tbl_danhSachNhaCungCapMousePressed

    private void btn_ThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_ThemActionPerformed
        // TODO add your handling code here:
        if(btn_Them.getText().equalsIgnoreCase("Thêm")){
            isThemKhachHangClicked(true);
            clearInput();
        }
        else if(btn_Them.getText().equalsIgnoreCase("Huỷ")){
            huyThaoTac();
            clearInput();
        }
    }//GEN-LAST:event_btn_ThemActionPerformed

    private void btn_suaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_suaActionPerformed
        // TODO add your handling code here:
         if(tbl_danhSachNhaCungCap.getSelectedRow() == -1){
            JOptionPane.showMessageDialog(null, "Vui lòng chọn dữ liệu để sửa");
            return;
        }
        
        if(btn_sua.getText().equals("Cập nhật")){
            isThemKhachHangClicked(false);
        }
        else if(btn_sua.getText().equals("Huỷ")){
            clearInput();
            huyThaoTac();
        }
    }//GEN-LAST:event_btn_suaActionPerformed

    private void btn_luuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_luuActionPerformed
        // TODO add your handling code here:
         if(isThemActive){
            themNhaCungCapHandler();
        }else if(isSuaActive){
            capNhatNhaCungCapHandler();
        }
    }//GEN-LAST:event_btn_luuActionPerformed

    private void btn_TimActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_TimActionPerformed
        // TODO add your handling code here:
        clearTable();
        String keyWord = txt_timNhaCungCap.getText().trim().toLowerCase();
        NhaCungCapDAO nccDAO = new NhaCungCapDAO();
        ArrayList<NhaCungCap> listNhaCungCaps = nccDAO.getAllNhaCungCap();
        DefaultTableModel dtm = (DefaultTableModel) tbl_danhSachNhaCungCap.getModel();
        for(NhaCungCap ncc : listNhaCungCaps){
            if(ncc.getMaNhaCungCap().toLowerCase().contains(keyWord) || ncc.getTenNhaCungCap().toLowerCase().contains(keyWord) || ncc.getSdt().toLowerCase().contains(keyWord) || ncc.getEmail().toLowerCase().contains(keyWord))
            {
                Object[] rowdata={ncc.getMaNhaCungCap(), ncc.getTenNhaCungCap(), ncc.getSdt(), ncc.getEmail(), ncc.getDiaChi()};
                dtm.addRow(rowdata);
            }
        }
    }//GEN-LAST:event_btn_TimActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_Them;
    private javax.swing.JButton btn_Tim;
    private javax.swing.JButton btn_luu;
    private javax.swing.JButton btn_sua;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lbl_diaChi;
    private javax.swing.JLabel lbl_email;
    private javax.swing.JLabel lbl_soDienThoai;
    private javax.swing.JLabel lbl_tenNhaCungCap;
    private javax.swing.JLabel lbl_timNhaCungCap;
    private javax.swing.JTable tbl_danhSachNhaCungCap;
    private javax.swing.JTextField txt_diaChi;
    private javax.swing.JTextField txt_email;
    private javax.swing.JTextField txt_nhaCungCap;
    private javax.swing.JTextField txt_soDienThoai;
    private javax.swing.JTextField txt_timNhaCungCap;
    // End of variables declaration//GEN-END:variables
}
