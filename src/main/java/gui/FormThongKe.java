/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package gui;

/**
 *
 * @author Admin
 */
public class FormThongKe extends javax.swing.JPanel {

    /**
     * Creates new form FormThongKe
     */
    public FormThongKe() {
        initComponents();
        addTabedpane();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    
    private void addTabedpane(){
        tabbedPane.add("Doanh thu", new TabThongKeDoanhThu());
        tabbedPane.add("Hóa đơn", new TabThongKeHoaDon());
        tabbedPane.add("Sản phẩm", new TabThongKeSanPham());
        //tabbedPane.add("Nhân viên", new TabThongKeNhanVien());
        
    }
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        tabbedPane = new javax.swing.JTabbedPane();

        setPreferredSize(new java.awt.Dimension(1130, 680));
        setLayout(new javax.swing.BoxLayout(this, javax.swing.BoxLayout.LINE_AXIS));
        add(tabbedPane);
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTabbedPane tabbedPane;
    // End of variables declaration//GEN-END:variables
}
