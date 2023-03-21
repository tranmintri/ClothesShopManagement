/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package gui;

import com.itextpdf.io.font.FontConstants;
import com.itextpdf.io.font.FontProgram;
import com.itextpdf.io.font.FontProgramFactory;
import com.itextpdf.io.font.PdfEncodings;
import com.itextpdf.io.source.ByteArrayOutputStream;
import com.itextpdf.kernel.color.DeviceRgb;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfName;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.border.Border;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.element.Text;
import com.itextpdf.layout.property.TextAlignment;
import com.itextpdf.pdfa.PdfADocument;
import dao.ChiTietHoaDonDAO;
import dao.HoaDonDAO;
import dao.KhachHangDAO;
import dao.KhuyenMaiDAO;
import dao.KichThuocDAO;
import dao.MauSacDAO;
import dao.NhaCungCapDAO;
import dao.NhanVienDAO;
import dao.PhanLoaiDAO;
import dao.SanPhamDAO;
import entity.ChiTietHoaDon;
import entity.HoaDon;
import entity.KhachHang;
import entity.KhuyenMai;
import entity.KichThuoc;
import entity.MauSac;
import entity.NhaCungCap;
import entity.NhanVien;
import entity.PhanLoai;
import entity.SanPham;
import java.awt.Color;
import java.awt.Desktop;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.print.PageFormat;
import java.awt.print.PrinterJob;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.sql.Date;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import javax.print.Doc;
import javax.print.DocPrintJob;
import javax.print.PrintService;
import javax.print.SimpleDoc;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.html.HTML;

import org.apache.commons.lang.SerializationUtils;

/**
 *
 * @author 20086
 */
public class QuanLyBanHang extends javax.swing.JPanel {

    private static KhachHang khachHang = null;
    private static SanPham sanPham = null;
    public static ArrayList<ChiTietHoaDon> listChiTietHoaDon = new ArrayList<>();
    private static boolean isThemKhachHangActive = false;
    public static boolean isThanhToan = false;

    private static final SanPhamDAO sanPham_DAO = new SanPhamDAO();
    private final PhanLoaiDAO phanLoai_DAO = new PhanLoaiDAO();
    private final MauSacDAO mauSac_DAO = new MauSacDAO();
    private final KichThuocDAO kichThuoc_DAO = new KichThuocDAO();
    private final KhachHangDAO khachHang_DAO = new KhachHangDAO();
    private static final HoaDonDAO hoaDon_DAO = new HoaDonDAO();
    private static final ChiTietHoaDonDAO cthd_DAO = new ChiTietHoaDonDAO();
    private final NhanVienDAO nv_DAO = new NhanVienDAO();
    private KhuyenMaiDAO khuyenMai_DAO;
    private final NhanVien nhanVien = FormDangNhap.nhanVien;
    private static boolean isKhachHang = false;

    /**
     * Creates new form QuanLyBanHang
     */
    public QuanLyBanHang() {
        initComponents();
        initialValue();
        designTable();
        tblCTHD_changeHandler();
        setEvent();
//        setEvent();
    }

    private void designTable() {
        tbl_danhSachSanPham.getTableHeader().setFont(new java.awt.Font("Calibri", 1, 14));
        tbl_danhSachSanPham.getTableHeader().setOpaque(false);
        tbl_danhSachSanPham.getTableHeader().setBackground(new Color(102, 102, 255));
        tbl_danhSachSanPham.getTableHeader().setForeground(Color.WHITE);
        tbl_danhSachSanPham.setDefaultEditor(Object.class, null); // Không cho phép edit       

        tbl_chiTietHoaDon.getTableHeader().setFont(new java.awt.Font("Calibri", 1, 14));
        tbl_chiTietHoaDon.getTableHeader().setOpaque(false);
        tbl_chiTietHoaDon.getTableHeader().setBackground(new Color(102, 102, 255));
        tbl_chiTietHoaDon.getTableHeader().setForeground(Color.WHITE);
        tbl_chiTietHoaDon.setDefaultEditor(Object.class, null); // Không cho phép edit  
    }

    private void initialValue() {
        ArrayList<MauSac> listMauSac = mauSac_DAO.getAllMauSac();
//         cb_mauSac.addItem("Tất cả");
        for (MauSac ms : listMauSac) {
            cb_mauSac.addItem(ms.getMauSac());
        }

        ArrayList<PhanLoai> listPhanLoai = phanLoai_DAO.getAllPhanLoai();
//         cb_loaiSanPham.addItem("Tất cả");
        for (PhanLoai pl : listPhanLoai) {
            cb_loaiSanPham.addItem(pl.getLoaiSanPham());
        }

        ArrayList<KichThuoc> listKichThuoc = kichThuoc_DAO.getAllKichThuoc();
//         cb_kichCo.addItem("Tất cả");
        for (KichThuoc kt : listKichThuoc) {
            cb_kichCo.addItem(kt.getKichThuoc());
        }

        tblDanhSachSanPhamWithFilter();
    }

    public static void resetTblDanhSachSP() {
        ArrayList<SanPham> listSP = sanPham_DAO.getAllSanPham();

        removeSelectedDSSP();
        clearTableDSSP();
        DefaultTableModel dtm = (DefaultTableModel) tbl_danhSachSanPham.getModel();
        for (SanPham sp : listSP) {
            String giaBan = "";
            if (sp.getKhuyenMai().getSoLuong() > 0) {
                giaBan = NumberFormat.getInstance().format(sp.tinhGiaBanLucSau());
            } else {
                giaBan = NumberFormat.getInstance().format(sp.tinhGiaBanLucDau());
            }
            Object[] rowData = {sp.getMaSP(), sp.getTenSP(), sp.getPhanLoai().getLoaiSanPham(), giaBan, sp.getSoLuong(), sp.getMauSac().getMauSac(), sp.getChatLieu().getChatLieu(), sp.getKichThuoc().getKichThuoc()};
            dtm.addRow(rowData);
        }
    }

    private static void tblDanhSachSanPham() {
        removeSelectedDSSP();
        clearTableDSSP();
        DefaultTableModel dtm = (DefaultTableModel) tbl_danhSachSanPham.getModel();

        ArrayList<SanPham> listSanPham = sanPham_DAO.getAllSanPham();
        for (SanPham sp : listSanPham) {
            String giaBan = "";
            if (sp.getKhuyenMai().getSoLuong() > 0) {
                giaBan = NumberFormat.getInstance().format(sp.tinhGiaBanLucSau());
            } else {
                giaBan = NumberFormat.getInstance().format(sp.tinhGiaBanLucDau());
            }
            Object[] rowData = {sp.getMaSP(), sp.getTenSP(), sp.getPhanLoai().getLoaiSanPham(), giaBan, sp.getSoLuong(), sp.getMauSac().getMauSac(), sp.getChatLieu().getChatLieu(), sp.getKichThuoc().getKichThuoc()};
            dtm.addRow(rowData);
        }
    }

    private static void tblDanhSachSanPhamWithFilter() {
        removeSelectedDSSP();
        clearTableDSSP();
        DefaultTableModel dtm = (DefaultTableModel) tbl_danhSachSanPham.getModel();

        String maSanPham = txt_maSanPham.getText();
        String tenSanPham = txt_tenSanPham.getText();
        String loaiSanPham = (String) cb_loaiSanPham.getSelectedItem();
        if (cb_loaiSanPham.getSelectedItem().equals("Tất cả")) {
            loaiSanPham = "";
        }

        String mauSac = (String) cb_mauSac.getSelectedItem();
        if (cb_mauSac.getSelectedItem().equals("Tất cả")) {
            mauSac = "";
        }

        String kichCo = (String) cb_kichCo.getSelectedItem();
        if (cb_kichCo.getSelectedItem().equals("Tất cả")) {
            kichCo = "";
        }

        ArrayList<SanPham> listSanPham = sanPham_DAO.getAllSanPham(maSanPham, tenSanPham, loaiSanPham, mauSac, kichCo);
//           ArrayList<SanPham> listSanPham = sanPham_DAO.getAllSanPham();
        for (SanPham sp : listSanPham) {
            String giaBan = "";
            if (sp.getKhuyenMai().getSoLuong() > 0) {
                giaBan = NumberFormat.getInstance().format(sp.tinhGiaBanLucSau());
            } else {
                giaBan = NumberFormat.getInstance().format(sp.tinhGiaBanLucDau());
            }
            Object[] rowData = {sp.getMaSP(), sp.getTenSP(), sp.getPhanLoai().getLoaiSanPham(), giaBan, sp.getSoLuong(), sp.getMauSac().getMauSac(), sp.getChatLieu().getChatLieu(), sp.getKichThuoc().getKichThuoc()};
            dtm.addRow(rowData);
        }
    }

    private static void clearTableDSSP() {
        DefaultTableModel dtm1 = (DefaultTableModel) tbl_danhSachSanPham.getModel();
        dtm1.setRowCount(0);
    }

    private static void clearTable() {
        DefaultTableModel dtm = (DefaultTableModel) tbl_chiTietHoaDon.getModel();
        dtm.setRowCount(0);
    }

    private static void removeSelectedDSSP() {
        tbl_danhSachSanPham.clearSelection();
    }

    private static void tblChiTietHoaDon() {
        clearTable();
        DefaultTableModel dtm = (DefaultTableModel) tbl_chiTietHoaDon.getModel();

        for (ChiTietHoaDon cthd : listChiTietHoaDon) {
            long giaBan = 0;
            if (cthd.getSanPham().getKhuyenMai().getSoLuong() > 0) {
                giaBan = cthd.getSanPham().tinhGiaBanLucSau();
            } else {
                giaBan = cthd.getSanPham().tinhGiaBanLucDau();
            }
            Object[] dataRow = {cthd.getSanPham().getMaSP(), cthd.getSanPham().getTenSP(), giaBan, cthd.getSoLuong()};
            dtm.addRow(dataRow);
        }
    }

    private boolean isThemValid() {
        if (!isKhachHang) {
            JOptionPane.showMessageDialog(null, "Vui lòng lấy thông tin khách hàng");
            return false;
        }
        if (sanPham == null) {
            JOptionPane.showMessageDialog(null, "Vui lòng chọn sản phẩm để thêm vào hoá đơn");
            return false;
        }

        if (txt_soLuong.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Vui lòng nhập số lượng");
            return false;
        }

        try {
            int soLuong = Integer.parseInt(txt_soLuong.getText());

            if (soLuong < 1) {
                JOptionPane.showMessageDialog(null, "Số lượng phải từ 1 trở lên");
                return false;
            }

            if (soLuong > sanPham.getSoLuong()) {
                JOptionPane.showMessageDialog(null, "Số lượng phải bé hơn số hàng tồn kho");
                return false;
            }

        } catch (Exception e) {

            JOptionPane.showMessageDialog(null, "Số lượng phải là 1 con số lớn hơn 0");
            return false;
        }
        return true;
    }

    private static void tongTienHandler() {
        long tongTien = 0;

        for (int i = 0; i < tbl_chiTietHoaDon.getRowCount(); i++) {
            
            long thanhTien = Long.parseLong(tbl_chiTietHoaDon.getValueAt(i, 3).toString()) * Long.parseLong(tbl_chiTietHoaDon.getValueAt(i, 2).toString());
            tongTien += thanhTien;
        }

        lbl_tongTienValue.setText(tongTien + "");
    }

    private static boolean tonTaiSanPhamTrongCTHD(SanPham sp) {

        if (tbl_chiTietHoaDon.getRowCount() < 1) {
            return false;
        }

        for (int i = 0; i < tbl_chiTietHoaDon.getRowCount(); i++) {
            if (tbl_chiTietHoaDon.getValueAt(i, 0).equals(sp.getMaSP())) {
                System.out.println("RKDO");
                int soLuong = Integer.parseInt(txt_soLuong.getText());

                int tongSP = Integer.parseInt(tbl_chiTietHoaDon.getValueAt(i, 3).toString()) + soLuong;

                tbl_chiTietHoaDon.setValueAt(tongSP, i, 3);
                return true;
            }
        }
        return false;
    }

    private static void capNhatSLSPKhiThemCTHD(SanPham sp, int soLuong) {
        sp.setSoLuong(sp.getSoLuong() - soLuong);
        if (sanPham_DAO.capNhatSoLuong(sp) == -1) {
            return;
        }
    }

    private static void themCTHDHandler() {
        int soLuong = Integer.parseInt(txt_soLuong.getText());

        String maSP = (String) tbl_danhSachSanPham.getValueAt(tbl_danhSachSanPham.getSelectedRow(), 0);
        SanPham sp = sanPham_DAO.getSanPhamById(maSP);

        //Kiểm tra số lượng khuyến mãi
        int soLuongKhuyenMai = sp.getKhuyenMai().getSoLuong();
        Date ngayBD = new java.sql.Date(sp.getKhuyenMai().getNgayBatDau().getTime());
        Date ngayHT = new java.sql.Date(System.currentTimeMillis()); 
        
        if (soLuong > soLuongKhuyenMai && soLuongKhuyenMai > 0 && (ngayHT.toString().equals(ngayBD.toString()) || ngayHT.after(ngayBD))) {
            if (JOptionPane.showConfirmDialog(null, "Sản phẩm bạn chọn chỉ còn " + soLuongKhuyenMai + " sản phẩm khuyến mãi. Bạn có muốn tiếp tục mua không", "Confirm", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                capNhatSLSPKhiThemCTHD(sp, soLuong);

                DefaultTableModel dtm = (DefaultTableModel) tbl_chiTietHoaDon.getModel();

                if (!tonTaiSanPhamTrongCTHD(sp)) {
                    long giaBan = 0;
                    if (sp.getKhuyenMai().getSoLuong() > 0) {
                        giaBan = sp.tinhGiaBanLucSau();
                    } else {
                        giaBan = sp.tinhGiaBanLucDau();
                    }
                    Object[] rowData = {sp.getMaSP(), sp.getTenSP(), (giaBan *soLuongKhuyenMai + sp.tinhGiaBanLucDau()* (soLuong-soLuongKhuyenMai))/soLuong, soLuong, sp.getPhanLoai().getLoaiSanPham(), sp.getMauSac().getMauSac(), sp.getChatLieu().getChatLieu(), sp.getKichThuoc().getKichThuoc()};
    
                    dtm.addRow(rowData);
                    
                }

                txt_soLuong.setText("");
                tongTienHandler();
                tblDanhSachSanPhamWithFilter();
                sanPham = null;
            }
        }
        else{
              capNhatSLSPKhiThemCTHD(sp, soLuong);

                DefaultTableModel dtm = (DefaultTableModel) tbl_chiTietHoaDon.getModel();

                if (!tonTaiSanPhamTrongCTHD(sp)) {
                    long giaBan = 0;
                    if (sp.getKhuyenMai().getSoLuong() > 0) {
                        giaBan = sp.tinhGiaBanLucSau();
                    } else {
                        giaBan = sp.tinhGiaBanLucDau();
                    }
                    Object[] rowData = {sp.getMaSP(), sp.getTenSP(), giaBan, soLuong, sp.getPhanLoai().getLoaiSanPham(), sp.getMauSac().getMauSac(), sp.getChatLieu().getChatLieu(), sp.getKichThuoc().getKichThuoc()};
                    dtm.addRow(rowData);
                }

                txt_soLuong.setText("");
                tongTienHandler();
                tblDanhSachSanPhamWithFilter();
                sanPham = null;
        }

//        isThanhToan = true;
    }

    private boolean isThemHangChoValid() {
        if (tbl_chiTietHoaDon.getRowCount() < 1) {
            JOptionPane.showMessageDialog(null, "Vui lòng thêm sản phẩm.");
            return false;
        }

        if (txt_soDienThoai.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Vui lòng điền đầy đủ thông tin khách hàng.");
            return false;
        }

        if (txt_tenKhachHang.getText().equals("") || txt_gmail.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Vui lòng nhập thông tin khách hàng");
            return false;
        }

        return true;
    }

    private boolean isValidInput() {
        if (txt_soDienThoai.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Vui lòng nhập số điện thoại để tìm kiếm");
            return false;
        }

        return true;
    }

    private void clearInput() {
        txt_soDienThoai.setText("");
        txt_tenKhachHang.setText("");
        txt_tienKhachDua.setText("");
        txt_gmail.setText("");
        lbl_tongTienValue.setText("0");
        lbl_tienThuaValue.setText("0");
    }

    private static void capNhatSLSPKhiXoaSP(SanPham sp, int soLuong) {
        sp.setSoLuong(sp.getSoLuong() + soLuong);
        if (sanPham_DAO.capNhatSoLuong(sp) == -1) {
            return;
        }
    }

    private static void xoaSanPhamHandler() {
        if (tbl_chiTietHoaDon.getSelectedRow() == -1) {
            JOptionPane.showMessageDialog(null, "Vui lòng chọn sản phẩm muốn xoá");
            return;
        }

        SanPham sp = sanPham_DAO.getSanPhamById((String) tbl_chiTietHoaDon.getValueAt(tbl_chiTietHoaDon.getSelectedRow(), 0));

        int soLuong = (int) tbl_chiTietHoaDon.getValueAt(tbl_chiTietHoaDon.getSelectedRow(), 3);

        capNhatSLSPKhiXoaSP(sp, soLuong);

        DefaultTableModel dtm = (DefaultTableModel) tbl_chiTietHoaDon.getModel();
        dtm.removeRow(tbl_chiTietHoaDon.getSelectedRow());

        //JOptionPane.showMessageDialog(null, "Xoá thành công");
        tblDanhSachSanPham();
        tongTienHandler();
    }

    private void enableFormKhachHang() {
        txt_gmail.setEnabled(true);
        txt_tenKhachHang.setEnabled(true);
        cb_gioiTinh.setEnabled(true);
    }

    private static void disableFormKhachHang() {
        txt_gmail.setEnabled(false);
        txt_tenKhachHang.setEnabled(false);
        cb_gioiTinh.setEnabled(false);
    }

    private static void thongTinKhachHang(KhachHang khachHang) {
        txt_soDienThoai.setText(khachHang.getSdt());
        txt_gmail.setText(khachHang.getEmail());
        txt_tenKhachHang.setText(khachHang.getHoVaTen());
        String gioiTinh = "";
        if (khachHang.getGioiTinh() == false) {
            cb_gioiTinh.setSelectedItem("Nam");
        } else {
            cb_gioiTinh.setSelectedItem("Nữ");
        }

    }

    private void btnTimHandler() {
        if (!isSoDienThoaiValid()) {
            return;
        }

        khachHang = khachHang_DAO.getKhachHangBySdt(txt_soDienThoai.getText().trim());
        if (khachHang == null) {
            if (JOptionPane.showConfirmDialog(null, "Không tìm thấy khách hàng. Bạn có muốn thêm thông tin khách hàng không?", "Thêm khách hàng", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                TrangChu.activeQLKHForm();
            }
//            JOptionPane.showMessageDialog(null, "Không tìm thấy số điện thoại vui lòng nhập thông tin khách hàng mới cho số điện thoại này");
//            enableFormKhachHang();
//            txt_tenKhachHang.setText("");
//            cb_gioiTinh.setSelectedIndex(0);
//            txt_gmail.setText("");
//            isThemKhachHangActive = true;
        } else {
            thongTinKhachHang(khachHang);
            isThemKhachHangActive = false;
            isKhachHang = true;
        }
    }

    private boolean isThanhToanHopLe() {
        if (tbl_chiTietHoaDon.getRowCount() < 1) {
            JOptionPane.showMessageDialog(null, "Vui lòng thêm sản phẩm để thanh toán");
            return false;
        }

        if (txt_tienKhachDua.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Vui lòng nhập tiền khách hàng đưa");
            return false;
        }

        try {
            long tienKhachDua = Long.parseLong(txt_tienKhachDua.getText());
            long tongTien = Long.parseLong(lbl_tongTienValue.getText());

            if (tienKhachDua - tongTien < 0) {
                JOptionPane.showMessageDialog(null, "Không đủ tiền thanh toán.");
                return false;
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Tiền khách hàng đưa phải là chữ số");
            return false;
        }

        return true;
    }

    private KhachHang layThongTinKhachHang() {
        String tenKhachHang = txt_tenKhachHang.getText();
        String gmail = txt_gmail.getText();
        String sdt = txt_soDienThoai.getText();
        boolean gioiTinh = false;
        if (cb_gioiTinh.getSelectedItem().equals("Nữ")) {
            gioiTinh = true;
        }

        return new KhachHang(tenKhachHang, gmail, sdt, gioiTinh);
    }

    private static void khoiTaoGiaTri() {
        listChiTietHoaDon.clear();

        khachHang = null;
        isThemKhachHangActive = false;
        lblHinhAnh.setIcon(null);
        tblDanhSachSanPhamWithFilter();
        tblChiTietHoaDon();
        isKhachHang = false;
    }

    private void thanhToanHandler() {
        if (isThemKhachHangActive) {
            if (!isThongTinKhachHangValid()) {
                return;
            }

            khachHang = layThongTinKhachHang();
            if (khachHang_DAO.addKhachHang(khachHang) == -1) {
                return;
            }
            isThemKhachHangActive = false;
        } else {
            isThemKhachHangActive = false;
        }

        HoaDon hd = new HoaDon();

        KhachHang kh = khachHang_DAO.getKhachHangBySdt(txt_soDienThoai.getText());
        hd.setKhachHang(kh);
        hd.setNhanVien(nhanVien);
        Date currentDate = new Date(Calendar.getInstance().getTime().getTime());
        hd.setNgayLap(currentDate);

        if (hoaDon_DAO.addHoaDon(hd) == -1) {
            return;
        }

        ArrayList<ChiTietHoaDon> listChiTietHoaDon = new ArrayList<>();

        for (int i = 0; i < tbl_chiTietHoaDon.getRowCount(); i++) {
            int soLuong = Integer.parseInt(tbl_chiTietHoaDon.getValueAt(i, 3).toString());
            long donGia = Long.parseLong(tbl_chiTietHoaDon.getValueAt(i, 2).toString());
            SanPham sp = sanPham_DAO.getSanPhamById(tbl_chiTietHoaDon.getValueAt(i, 0).toString());
//            long giaBan = 0;
//            if (sp.getKhuyenMai().getSoLuong() > 0) {
//                giaBan = sp.tinhGiaBanLucSau();
//            } else {
//                giaBan = sp.tinhGiaBanLucDau();
//            }
            long thanhTien = donGia * soLuong;
            ChiTietHoaDon cthd = new ChiTietHoaDon(sp, hd, soLuong, thanhTien);

            listChiTietHoaDon.add(cthd);
            if (cthd_DAO.addChiTietHoaDon(cthd) == -1) {
                return;
            }
        }

        capNhatSoLuongKhuyenMai();

        DefaultTableModel dtm = (DefaultTableModel) tbl_chiTietHoaDon.getModel();
        dtm.setRowCount(0);

        xuatHoaDon(hd, listChiTietHoaDon, kh, nhanVien);
        clearInput();
        JOptionPane.showMessageDialog(null, "Thanh toán thành công");
        khoiTaoGiaTri();
        disableFormKhachHang();
        openHoaDon(hd.getMaHoaDon());
    }

    private void xoaTatCaHandler() {
        if (tbl_chiTietHoaDon.getRowCount() < 1) {
            return;
        }

        for (int i = 0; i < tbl_chiTietHoaDon.getRowCount(); i++) {
            SanPham sp = sanPham_DAO.getSanPhamById((String) tbl_chiTietHoaDon.getValueAt(i, 0));
            int soLuong = Integer.parseInt(tbl_chiTietHoaDon.getValueAt(i, 3).toString());
            capNhatSLSPKhiXoaSP(sp, soLuong);
        }

        DefaultTableModel dtm = (DefaultTableModel) tbl_chiTietHoaDon.getModel();
        dtm.setRowCount(0);

        tblDanhSachSanPham();
        tongTienHandler();
//         isThanhToan = false;
    }

    public void capNhatSoLuongKhuyenMai() {
        khuyenMai_DAO = new KhuyenMaiDAO();
        DefaultTableModel dtm = (DefaultTableModel) tbl_chiTietHoaDon.getModel();
        ArrayList<SanPham> listSanPham = sanPham_DAO.getAllSanPham();
        for (int i = 0; i < tbl_chiTietHoaDon.getRowCount(); i++) {
            for (SanPham sanPham : listSanPham) {
                if (String.valueOf(dtm.getValueAt(i, 0)).equalsIgnoreCase(sanPham.getMaSP())) {
                    if (sanPham.getKhuyenMai().getPhanTramKhuyenMai() > 0) {
                        sanPham.getKhuyenMai().setSoLuong(sanPham.getKhuyenMai().getSoLuong() - Integer.parseInt(dtm.getValueAt(i, 3).toString()));
                        if (sanPham.getKhuyenMai().getSoLuong() >= 0) {
                            khuyenMai_DAO.updateKhuyenMai(sanPham.getKhuyenMai());
                        } else {
                            sanPham.getKhuyenMai().setSoLuong(Integer.parseInt(0 + ""));
                            khuyenMai_DAO.updateKhuyenMai(sanPham.getKhuyenMai());
                            sanPham.getKhuyenMai().setMaKhuyenMai("KM0001");
                            sanPham_DAO.updateSanPham(sanPham);
                        }

                    }
                }
            }
        }

    }

    private void themVaoHangChoHandler() {
        if (isThemKhachHangActive) {
            khachHang = layThongTinKhachHang();
            if (khachHang_DAO.addKhachHang(khachHang) == -1) {
                return;
            }
            isThemKhachHangActive = false;
        } else {
            khachHang = khachHang_DAO.getKhachHangBySdt(txt_soDienThoai.getText());
            isThemKhachHangActive = false;
        }

        HoaDon hd = new HoaDon();
        hd.setThanhHoaDonCho();
        hd.setKhachHang(khachHang);
        hd.setNhanVien(nhanVien);
        Date currentDate = new Date(Calendar.getInstance().getTime().getTime());
        hd.setNgayLap(currentDate);

        if (hoaDon_DAO.addHoaDon(hd) == -1) {
            return;
        }

        for (int i = 0; i < tbl_chiTietHoaDon.getRowCount(); i++) {
            SanPham sp = sanPham_DAO.getSanPhamById(tbl_chiTietHoaDon.getValueAt(i, 0).toString());
            if (sp == null) {
                return;
            }
            int soLuong = Integer.parseInt(tbl_chiTietHoaDon.getValueAt(i, 3).toString());
            long giaBan = 0;
            if (sp.getKhuyenMai().getSoLuong() > 0) {
                giaBan = sp.tinhGiaBanLucSau();
            } else {
                giaBan = sp.tinhGiaBanLucDau();
            }
            long thanhTien = giaBan * soLuong;
            ChiTietHoaDon cthd = new ChiTietHoaDon(sp, hd, soLuong, thanhTien);
            if (cthd_DAO.addChiTietHoaDon(cthd) == -1) {
                return;
            }
        }

        JOptionPane.showMessageDialog(null, "Thêm vào hoá đơn chờ thành công");
        FormChoHoaDon.tblDanhSachCho();

        khoiTaoGiaTri();
        clearInput();
    }

    public static void thanhToanHangCho(HoaDon hd) {

        ArrayList<ChiTietHoaDon> listCTHD = cthd_DAO.getAllCTHDByHoaDon(hd);

        if (cthd_DAO.deleteCTHD(hd) == -1) {
            return;
        }

        clearTable();
        DefaultTableModel dtm = (DefaultTableModel) tbl_chiTietHoaDon.getModel();

        for (int i = 0; i < listCTHD.size(); i++) {
            SanPham sp = listCTHD.get(i).getSanPham();
            long giaBan = 0;
            if (sp.getKhuyenMai().getSoLuong() > 0) {
                giaBan = sp.tinhGiaBanLucSau();
            } else {
                giaBan = sp.tinhGiaBanLucDau();
            }
            Object[] rowData = {sp.getMaSP(), sp.getTenSP(), giaBan, listCTHD.get(i).getSoLuong(), sp.getPhanLoai().getLoaiSanPham(),
                sp.getMauSac().getMauSac(), sp.getChatLieu().getChatLieu(), sp.getKichThuoc().getKichThuoc()};
            dtm.addRow(rowData);
        }

        KhachHang kh = hd.getKhachHang();

        if (hoaDon_DAO.deleteHoaDon(hd) == -1) {
            return;
        }

        thongTinKhachHang(kh);
        disableFormKhachHang();
        tongTienHandler();
        FormChoHoaDon.tblDanhSachCho();
        isKhachHang = true;
    }

    public static void xuatHoaDon(HoaDon hd, ArrayList<ChiTietHoaDon> listCTHD, KhachHang kh, NhanVien nv) {
        try {

            String pathFull = "D:\\report\\" + hd.getMaHoaDon() + ".pdf";
            PdfWriter pdfWriter = new PdfWriter(pathFull);

//            FontProgram fontProgram = FontProgramFactory.createFont("D:/font_/vuArial.ttf");
            PdfDocument pdfDocument = new PdfDocument(pdfWriter);
            pdfDocument.addNewPage();
            pdfDocument.setDefaultPageSize(PageSize.A4);
            Document document = new Document(pdfDocument);

            PdfFont font = PdfFontFactory.createFont("src/fonts/vuArial.ttf", PdfEncodings.IDENTITY_H);
            PdfFont bold = PdfFontFactory.createFont("src/fonts/vuArial.ttf", PdfEncodings.IDENTITY_H);
            float col = 560f;
            float columnWidth[] = {col};

            Table tblTitle = new Table(columnWidth);
            tblTitle.addCell(new Cell().add(new Paragraph(new Text("HOÁ ĐƠN BÁN HÀNG").setFont(bold))).setBorder(Border.NO_BORDER)); // 
            tblTitle.setTextAlignment(TextAlignment.CENTER)
                    .setMarginTop(5f).setMarginBottom(60f).setFontSize(30f);

            float hdWidth[] = {460, 100};
            Table tblMaHD = new Table(hdWidth);
            tblMaHD.addCell(new Cell().add("Mã hoá đơn").setBold().setBorder(Border.NO_BORDER).setTextAlignment(TextAlignment.RIGHT).setMarginRight(20f)
                    .setMarginBottom(20f).setFont(bold));
            tblMaHD.addCell(new Cell().add(hd.getMaHoaDon()).setBorder(Border.NO_BORDER).setMarginBottom(20f).setFont(font));

            float colWidth[] = {90, 290, 100, 100};
            Table empInfoTable = new Table(colWidth);
            empInfoTable.addCell(new Cell(0, 4).add("Thông tin nhân viên:").setBold().setBorder(Border.NO_BORDER).setMarginBottom(10f).setFont(bold));

            empInfoTable.addCell(new Cell().add("Mã nhân viên:").setBorder(Border.NO_BORDER).setFont(font));
            empInfoTable.addCell(new Cell().add(nv.getMaNhanVien()).setBorder(Border.NO_BORDER).setFont(font));
            empInfoTable.addCell(new Cell().add("Họ tên:").setBorder(Border.NO_BORDER).setFont(font));
            empInfoTable.addCell(new Cell().add(nv.getHoVaTen()).setBorder(Border.NO_BORDER).setFont(font)); // tên khách hàng

            Table customerInfoTable = new Table(colWidth);

            customerInfoTable.addCell(new Cell(0, 4).add("Thông tin khách hàng:").setBold().setBorder(Border.NO_BORDER).setMarginBottom(10f)
                    .setMarginTop(15f).setFont(bold));

            customerInfoTable.addCell(new Cell().add("Họ tên:").setBorder(Border.NO_BORDER).setFont(font));
            customerInfoTable.addCell(new Cell().add(kh.getHoVaTen()).setBorder(Border.NO_BORDER).setFont(font)); // tên khách hàng
            customerInfoTable.addCell(new Cell().add("Ngày mua:").setBorder(Border.NO_BORDER).setFont(font));
            customerInfoTable.addCell(new Cell().add(new SimpleDateFormat("dd/MM/yyyy").format(hd.getNgayLap())).setBorder(Border.NO_BORDER).setFont(font));

            float productWidth[] = {40, 350, 60, 100};

            Table listProductTbl = new Table(productWidth);
            listProductTbl.addCell(new Cell(0, 4).add("Danh sách sản phẩm").setBold().setMarginTop(20f).setBorder(Border.NO_BORDER).setFont(bold));

            listProductTbl.addCell(new Cell().add("STT").setBold().setFont(bold));
            listProductTbl.addCell(new Cell().add("Tên sản phẩm").setBold().setFont(bold));
            listProductTbl.addCell(new Cell().add("Số lượng").setBold().setFont(bold));
            listProductTbl.addCell(new Cell().add("Thành tiền").setBold().setFont(bold));

            // for để load sản phẩm
            int tongTien = 0;
            int tienKhachTra = 0;

            for (int i = 0; i < listCTHD.size(); i++) {
                int stt = i + 1;
                ChiTietHoaDon cthd = listCTHD.get(i);
                String tenSP = cthd.getSanPham().getTenSP() + " " + cthd.getSanPham().getKichThuoc().getKichThuoc();
                listProductTbl.addCell(new Cell().add(stt + "").setFont(font));
                listProductTbl.addCell(new Cell().add(tenSP).setFont(font));
                listProductTbl.addCell(new Cell().add(cthd.getSoLuong() + "").setFont(font));
                long giaBan = 0;
                if (cthd.getSanPham().getKhuyenMai().getSoLuong() > 0) {
                    giaBan = cthd.getSanPham().tinhGiaBanLucSau();
                } else {
                    giaBan = cthd.getSanPham().tinhGiaBanLucDau();
                }
                listProductTbl.addCell(new Cell().add(NumberFormat.getInstance().format(giaBan * cthd.getSoLuong())).setFont(font));
                tongTien += giaBan * cthd.getSoLuong();
            }

            if (txt_tienKhachDua == null) {
                tienKhachTra = tongTien;
            } else {
                tienKhachTra = Integer.parseInt(txt_tienKhachDua.getText().trim());
            }

            int tienThua = tienKhachTra - tongTien;

            float ttttWidth[] = {480, 100};
            Table tblThongTinThanhToan = new Table(ttttWidth);
            tblThongTinThanhToan.addCell(new Cell(0, 2).setMarginTop(20f).setBorder(Border.NO_BORDER).setFont(font));
            tblThongTinThanhToan.addCell(new Cell().add("Tổng tiền thanh toán:").setBorder(Border.NO_BORDER).setFont(font));
            tblThongTinThanhToan.addCell(new Cell().add(NumberFormat.getInstance().format(tongTien)).setBorder(Border.NO_BORDER).setFont(font));
            tblThongTinThanhToan.addCell(new Cell().add("Tiền khách trả:").setBorder(Border.NO_BORDER).setFont(font));
            tblThongTinThanhToan.addCell(new Cell().add(NumberFormat.getInstance().format(tienKhachTra)).setBorder(Border.NO_BORDER).setFont(font));
            tblThongTinThanhToan.addCell(new Cell().add("Tiền thừa:").setBorder(Border.NO_BORDER).setFont(font));
            tblThongTinThanhToan.addCell(new Cell().add(NumberFormat.getInstance().format(tienThua)).setBorder(Border.NO_BORDER).setFont(font));

            document.add(tblTitle);
            document.add(tblMaHD);
            document.add(empInfoTable);
            document.add(customerInfoTable);
            document.add(listProductTbl);
            document.add(tblThongTinThanhToan);

            pdfDocument.close();
            pdfWriter.close();

//            File file = new File("");
//            String path = file.getAbsolutePath();
//
//            String pathFull1 = path + "\\src\\main\\java\\report\\" + hd.getMaHoaDon() + ".pdf";
//            PdfWriter pdfWriter1 = new PdfWriter(pathFull1);
//            
//            PdfDocument pdfDocument1 = new PdfDocument(pdfWriter1);
//            pdfDocument1.addNewPage();
//            pdfDocument1.setDefaultPageSize(PageSize.A4);
//            Document document1 = new Document(pdfDocument1);
//            
//            document1.add(tblTitle);
//            document1.add(tblMaHD);
//            document1.add(empInfoTable);
//            document1.add(customerInfoTable);
//            document1.add(listProductTbl);
//            document1.add(tblThongTinThanhToan);
            System.err.println("In thanh cong");
//            inHoaDon(path);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(QuanLyBanHang.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(QuanLyBanHang.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void tienThuaHandler() {
        try {
            long tongTien = Long.parseLong(lbl_tongTienValue.getText());
            long tienKh = Long.parseLong(txt_tienKhachDua.getText());

            long tienThua = tienKh - tongTien;
            lbl_tienThuaValue.setText(NumberFormat.getInstance().format(tienThua));
        } catch (Exception e) {
        }

    }

    private boolean isSoDienThoaiValid() {
        if (!txt_soDienThoai.getText().matches("^0\\d{9}$")) {
            JOptionPane.showMessageDialog(null, "Số điện thoại không hợp lệ");

            return false;
        }

        return true;
    }

    private boolean isThongTinKhachHangValid() {
        if (txt_gmail.getText().trim().equals("") || txt_tenKhachHang.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Vui lòng nhập đầy đủ thông tin khách hàng");
            return false;
        }

        if (!txt_gmail.getText().trim().matches("^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$")) {
            JOptionPane.showMessageDialog(null, "Gmail khách hàng không hợp lệ");
            return false;

        }

        return true;
    }

    private void giamSoLuongHandler() {
        try {
            int soLuongCoSan = Integer.parseInt(tbl_chiTietHoaDon.getValueAt(tbl_chiTietHoaDon.getSelectedRow(), 3).toString());
            int soLuong = Integer.parseInt(txt_soLuong.getText());
            if (soLuong < 1) {
                JOptionPane.showMessageDialog(null, "Số lượng phải lớn hơn 1");
                return;
            }
            if (soLuong > soLuongCoSan) {
                JOptionPane.showMessageDialog(null, "Số lượng giảm phải bé hơn số lượng trong CTHD");
                return;
            }
            SanPham spp = sanPham_DAO.getSanPhamById(tbl_chiTietHoaDon.getValueAt(tbl_chiTietHoaDon.getSelectedRow(), 0).toString());
            if (spp == null) {
                return;
            }
            System.out.println("So luong truoc khi cap nhat: " + spp.getSoLuong());
            spp.setSoLuong(spp.getSoLuong() + soLuong);
            System.out.println("So luong sau khi cap nhat: " + spp.getSoLuong());
            if (sanPham_DAO.capNhatSoLuong(spp) != -1) {
                JOptionPane.showMessageDialog(null, "Giảm số lượng thành công");

                tbl_chiTietHoaDon.setValueAt(soLuongCoSan - soLuong, tbl_chiTietHoaDon.getSelectedRow(), 3);
                tblDanhSachSanPhamWithFilter();
                tongTienHandler();
                txt_soLuong.setText("");
            }
            if (Integer.parseInt(tbl_chiTietHoaDon.getValueAt(tbl_chiTietHoaDon.getSelectedRow(), 3).toString()) == 0) {
                DefaultTableModel model_CTHD = (DefaultTableModel) tbl_chiTietHoaDon.getModel();
                model_CTHD.removeRow(tbl_chiTietHoaDon.getSelectedRow());
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Số lượng phải là 1 chữ số");
        }
    }

    private void openHoaDon(String tenHoaDon) {

        File file = new File("");
        // String path= file.getAbsolutePath();
        //File URL = new File(path + "\\src\\main\\java\\report\\"+idLatest+".pdf");
        //System.out.println("file: " +path + idLatest);
        File URL = new File("D:\\report\\" + tenHoaDon + ".pdf");
        try {
            Desktop.getDesktop().open(URL);
        } catch (Exception e) {

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
        lbl_maSanPham = new javax.swing.JLabel();
        txt_maSanPham = new javax.swing.JTextField();
        lbl_loaiSanPham = new javax.swing.JLabel();
        cb_loaiSanPham = new javax.swing.JComboBox<>();
        lbl_mauSac = new javax.swing.JLabel();
        cb_mauSac = new javax.swing.JComboBox<>();
        lbl_kichCo = new javax.swing.JLabel();
        cb_kichCo = new javax.swing.JComboBox<>();
        lbl_tenSanPham = new javax.swing.JLabel();
        txt_tenSanPham = new javax.swing.JTextField();
        pn_hinhAnh = new javax.swing.JPanel();
        lblHinhAnh = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbl_danhSachSanPham = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbl_chiTietHoaDon = new javax.swing.JTable();
        lbl_soLuong = new javax.swing.JLabel();
        txt_soLuong = new javax.swing.JTextField();
        btn_them = new javax.swing.JButton();
        btn_xoaSanPham = new javax.swing.JButton();
        btn_xoaTatCa = new javax.swing.JButton();
        btn_giam = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        lbl_soDienThoai = new javax.swing.JLabel();
        txt_soDienThoai = new javax.swing.JTextField();
        btn_tim = new javax.swing.JButton();
        lbl_tenKhachHang = new javax.swing.JLabel();
        txt_tenKhachHang = new javax.swing.JTextField();
        lbl_gmail = new javax.swing.JLabel();
        txt_gmail = new javax.swing.JTextField();
        lbl_tenKhachHang1 = new javax.swing.JLabel();
        cb_gioiTinh = new javax.swing.JComboBox<>();
        lbl_tongTienTitle = new javax.swing.JLabel();
        lbl_tongTienValue = new javax.swing.JLabel();
        lbl_tienKhachDua = new javax.swing.JLabel();
        txt_tienKhachDua = new javax.swing.JTextField();
        lbl_tienThua = new javax.swing.JLabel();
        lbl_tienThuaValue = new javax.swing.JLabel();
        btn_thanhToan = new javax.swing.JButton();
        btn_themVaoHangCho = new javax.swing.JButton();
        btn_themKhachHang = new javax.swing.JButton();

        setBackground(new java.awt.Color(255, 255, 255));
        setMaximumSize(new java.awt.Dimension(1130, 773));
        setMinimumSize(new java.awt.Dimension(1130, 773));
        setPreferredSize(new java.awt.Dimension(1115, 735));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Tìm sản phẩm", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Calibri", 0, 14))); // NOI18N

        lbl_maSanPham.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        lbl_maSanPham.setText("Mã sản phẩm:");

        txt_maSanPham.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        txt_maSanPham.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_maSanPhamKeyReleased(evt);
            }
        });

        lbl_loaiSanPham.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        lbl_loaiSanPham.setText("Loại sản phẩm:");

        cb_loaiSanPham.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Tất cả" }));
        cb_loaiSanPham.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cb_loaiSanPhamItemStateChanged(evt);
            }
        });

        lbl_mauSac.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        lbl_mauSac.setText("Màu sắc:");

        cb_mauSac.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Tất cả" }));
        cb_mauSac.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cb_mauSacItemStateChanged(evt);
            }
        });

        lbl_kichCo.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        lbl_kichCo.setText("Kích cỡ:");

        cb_kichCo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Tất cả" }));
        cb_kichCo.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cb_kichCoItemStateChanged(evt);
            }
        });

        lbl_tenSanPham.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        lbl_tenSanPham.setText("Tên sản phẩm:");

        txt_tenSanPham.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        txt_tenSanPham.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_tenSanPhamKeyReleased(evt);
            }
        });

        pn_hinhAnh.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(119, 119, 119), 3, true));

        lblHinhAnh.setMaximumSize(new java.awt.Dimension(159, 143));
        lblHinhAnh.setMinimumSize(new java.awt.Dimension(159, 143));
        lblHinhAnh.setPreferredSize(new java.awt.Dimension(159, 143));

        javax.swing.GroupLayout pn_hinhAnhLayout = new javax.swing.GroupLayout(pn_hinhAnh);
        pn_hinhAnh.setLayout(pn_hinhAnhLayout);
        pn_hinhAnhLayout.setHorizontalGroup(
            pn_hinhAnhLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblHinhAnh, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        pn_hinhAnhLayout.setVerticalGroup(
            pn_hinhAnhLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblHinhAnh, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jLabel1.setText("Hình ảnh");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbl_tenSanPham)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addComponent(lbl_maSanPham)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addComponent(txt_maSanPham)
                                    .addGap(28, 28, 28)))
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(lbl_loaiSanPham)
                                .addComponent(cb_loaiSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addComponent(txt_tenSanPham, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 308, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(lbl_mauSac)
                    .addComponent(cb_mauSac, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(168, 168, 168)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbl_kichCo)
                            .addComponent(cb_kichCo, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 12, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(58, 58, 58))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(pn_hinhAnh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(pn_hinhAnh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(lbl_tenSanPham)
                        .addGap(2, 2, 2)
                        .addComponent(txt_tenSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(14, 14, 14)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(lbl_loaiSanPham)
                                .addGap(0, 0, 0)
                                .addComponent(cb_loaiSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(lbl_maSanPham)
                                .addGap(2, 2, 2)
                                .addComponent(txt_maSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(lbl_mauSac)
                                .addGap(0, 0, 0)
                                .addComponent(cb_mauSac, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(lbl_kichCo, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, 0)
                                .addComponent(cb_kichCo, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Danh sách sản phẩm"));

        tbl_danhSachSanPham.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã sản phẩm", "Tên sản phẩm", "Loại sản phẩm", "Đơn giá", "Số lượng", "Màu sắc", "Chất liệu", "Kích thước"
            }
        ));
        tbl_danhSachSanPham.setRowHeight(30);
        tbl_danhSachSanPham.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tbl_danhSachSanPhamMousePressed(evt);
            }
        });
        jScrollPane1.setViewportView(tbl_danhSachSanPham);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 232, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Hóa đơn", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Calibri", 0, 14))); // NOI18N

        tbl_chiTietHoaDon.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã sản phẩm", "Tên sản phẩm", "Đơn giá", "Số lượng", "Loại sản phẩm", "Màu sắc", "Chất liệu", "Kích thước"
            }
        ));
        tbl_chiTietHoaDon.setRowHeight(30);
        jScrollPane2.setViewportView(tbl_chiTietHoaDon);

        lbl_soLuong.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        lbl_soLuong.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl_soLuong.setText("Số lượng:");
        lbl_soLuong.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        txt_soLuong.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N

        btn_them.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        btn_them.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icons-add.png"))); // NOI18N
        btn_them.setText("Thêm");
        btn_them.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_themMouseClicked(evt);
            }
        });

        btn_xoaSanPham.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        btn_xoaSanPham.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icons-delete.png"))); // NOI18N
        btn_xoaSanPham.setText("Xoá sản phẩm");
        btn_xoaSanPham.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_xoaSanPhamMouseClicked(evt);
            }
        });

        btn_xoaTatCa.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        btn_xoaTatCa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/delete-button.png"))); // NOI18N
        btn_xoaTatCa.setText("Xoá tất cả");
        btn_xoaTatCa.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_xoaTatCaMouseClicked(evt);
            }
        });

        btn_giam.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        btn_giam.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icons8-minus-sign-24.png"))); // NOI18N
        btn_giam.setText("Giảm");
        btn_giam.setEnabled(false);
        btn_giam.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_giamMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(lbl_soLuong)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txt_soLuong, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btn_them, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btn_giam, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btn_xoaSanPham)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btn_xoaTatCa)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btn_them, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addComponent(btn_giam, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                    .addComponent(txt_soLuong)
                    .addComponent(btn_xoaTatCa, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lbl_soLuong, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btn_xoaSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Thanh toán", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Calibri", 0, 14))); // NOI18N

        lbl_soDienThoai.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        lbl_soDienThoai.setText("Số điện thoại:");

        txt_soDienThoai.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N

        btn_tim.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        btn_tim.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/loupe.png"))); // NOI18N
        btn_tim.setText("Tìm");
        btn_tim.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_timMouseClicked(evt);
            }
        });

        lbl_tenKhachHang.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        lbl_tenKhachHang.setText("Tên khách hàng:");

        txt_tenKhachHang.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        txt_tenKhachHang.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txt_tenKhachHang.setEnabled(false);

        lbl_gmail.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        lbl_gmail.setText("Gmail:");

        txt_gmail.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        txt_gmail.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txt_gmail.setEnabled(false);

        lbl_tenKhachHang1.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        lbl_tenKhachHang1.setText("Giới tính:");

        cb_gioiTinh.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "", "Nam", "Nữ" }));

        lbl_tongTienTitle.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        lbl_tongTienTitle.setText("Tổng tiền:");

        lbl_tongTienValue.setFont(new java.awt.Font("Calibri", 1, 18)); // NOI18N
        lbl_tongTienValue.setText("0");

        lbl_tienKhachDua.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        lbl_tienKhachDua.setText("Tiền khách đưa:");

        txt_tienKhachDua.setFont(new java.awt.Font("Calibri", 0, 18)); // NOI18N
        txt_tienKhachDua.setMaximumSize(new java.awt.Dimension(64, 30));
        txt_tienKhachDua.setMinimumSize(new java.awt.Dimension(64, 30));
        txt_tienKhachDua.setPreferredSize(new java.awt.Dimension(64, 30));
        txt_tienKhachDua.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_tienKhachDuaKeyReleased(evt);
            }
        });

        lbl_tienThua.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        lbl_tienThua.setText("Tiền thừa:");

        lbl_tienThuaValue.setFont(new java.awt.Font("Calibri", 1, 18)); // NOI18N
        lbl_tienThuaValue.setText("0");

        btn_thanhToan.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        btn_thanhToan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/cash_register.png"))); // NOI18N
        btn_thanhToan.setText("Thanh toán");
        btn_thanhToan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_thanhToanMouseClicked(evt);
            }
        });

        btn_themVaoHangCho.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        btn_themVaoHangCho.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icons-add.png"))); // NOI18N
        btn_themVaoHangCho.setText("Thêm vào hàng chờ");
        btn_themVaoHangCho.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_themVaoHangChoMouseClicked(evt);
            }
        });

        btn_themKhachHang.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        btn_themKhachHang.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/user.png"))); // NOI18N
        btn_themKhachHang.setText("Thêm khách hàng");
        btn_themKhachHang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_themKhachHangMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(lbl_tenKhachHang, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txt_soDienThoai, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 195, Short.MAX_VALUE)
                                    .addComponent(txt_tenKhachHang, javax.swing.GroupLayout.Alignment.LEADING))
                                .addGap(13, 13, 13)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(jPanel4Layout.createSequentialGroup()
                                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(lbl_tenKhachHang1)
                                            .addComponent(cb_gioiTinh, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                            .addComponent(txt_gmail, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 229, Short.MAX_VALUE)
                                            .addComponent(lbl_gmail, javax.swing.GroupLayout.Alignment.LEADING)))
                                    .addGroup(jPanel4Layout.createSequentialGroup()
                                        .addComponent(btn_tim, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 35, Short.MAX_VALUE)
                                        .addComponent(btn_themKhachHang, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addComponent(lbl_soDienThoai))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(lbl_tienThua)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lbl_tienThuaValue, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(lbl_tienKhachDua)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txt_tienKhachDua, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(lbl_tongTienTitle)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lbl_tongTienValue, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 47, Short.MAX_VALUE)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(btn_themVaoHangCho)
                                .addGap(40, 40, 40))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                                .addComponent(btn_thanhToan, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(51, 51, 51))))))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(lbl_soDienThoai)
                .addGap(1, 1, 1)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txt_soDienThoai, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btn_tim)
                            .addComponent(btn_themKhachHang))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(lbl_tenKhachHang)
                                .addGap(0, 0, 0)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(cb_gioiTinh, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txt_tenKhachHang, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(lbl_gmail)
                                .addGap(0, 0, 0)
                                .addComponent(txt_gmail, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(lbl_tenKhachHang1, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(33, 33, 33)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lbl_tongTienTitle)
                            .addComponent(lbl_tongTienValue))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lbl_tienKhachDua)
                            .addComponent(txt_tienKhachDua, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lbl_tienThua)
                            .addComponent(lbl_tienThuaValue)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addComponent(btn_themVaoHangCho, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btn_thanhToan, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(8, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(51, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(24, 24, 24))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btn_timMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_timMouseClicked
        // TODO add your handling code here:
        if (!isValidInput()) {
            return;
        }

        btnTimHandler();
    }//GEN-LAST:event_btn_timMouseClicked

    private void btn_themMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_themMouseClicked
        // TODO add your handling code here:
        if (!isThemValid()) {
            return;
        }

        themCTHDHandler();
    }//GEN-LAST:event_btn_themMouseClicked

    private void btn_xoaSanPhamMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_xoaSanPhamMouseClicked
        // TODO add your handling code here:
        xoaSanPhamHandler();
    }//GEN-LAST:event_btn_xoaSanPhamMouseClicked

    private void btn_thanhToanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_thanhToanMouseClicked
        // TODO add your handling code here:
        if (!isThanhToanHopLe()) {
            return;
        }

        thanhToanHandler();
    }//GEN-LAST:event_btn_thanhToanMouseClicked

    private void btn_xoaTatCaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_xoaTatCaMouseClicked
        // TODO add your handling code here:
        xoaTatCaHandler();
    }//GEN-LAST:event_btn_xoaTatCaMouseClicked

    private void cb_mauSacItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cb_mauSacItemStateChanged
        // TODO add your handling code here:
        tblDanhSachSanPhamWithFilter();
    }//GEN-LAST:event_cb_mauSacItemStateChanged

    private void cb_loaiSanPhamItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cb_loaiSanPhamItemStateChanged
        // TODO add your handling code here:
        tblDanhSachSanPhamWithFilter();
        System.out.println("loaiSanPham");
    }//GEN-LAST:event_cb_loaiSanPhamItemStateChanged

    private void cb_kichCoItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cb_kichCoItemStateChanged
        // TODO add your handling code here:
        tblDanhSachSanPhamWithFilter();
        System.out.println("kichCo");
    }//GEN-LAST:event_cb_kichCoItemStateChanged

    private void txt_maSanPhamKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_maSanPhamKeyReleased
        // TODO add your handling code here:
        tblDanhSachSanPhamWithFilter();
        System.out.println("maSP");
    }//GEN-LAST:event_txt_maSanPhamKeyReleased

    private void txt_tenSanPhamKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_tenSanPhamKeyReleased
        // TODO add your handling code here:
        tblDanhSachSanPhamWithFilter();
    }//GEN-LAST:event_txt_tenSanPhamKeyReleased

    private void btn_themVaoHangChoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_themVaoHangChoMouseClicked
        // TODO add your handling code here:
        if (!isThemHangChoValid()) {
            return;
        }

        themVaoHangChoHandler();
    }//GEN-LAST:event_btn_themVaoHangChoMouseClicked

    private void txt_tienKhachDuaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_tienKhachDuaKeyReleased
        // TODO add your handling code here:
        tienThuaHandler();
    }//GEN-LAST:event_txt_tienKhachDuaKeyReleased

    private void tblCTHD_changeHandler() {
        tbl_chiTietHoaDon.getModel().addTableModelListener(new TableModelListener() {
            @Override
            public void tableChanged(TableModelEvent e) {
                if (tbl_chiTietHoaDon.getRowCount() < 1) {
                    isThanhToan = false;
                } else {
                    isThanhToan = true;
                }
            }
        });
    }

    public ImageIcon ResizeImage(String imgPath) {
        ImageIcon myImage = new ImageIcon(imgPath);
        Image img = myImage.getImage();
        Image newImg = img.getScaledInstance(pn_hinhAnh.getWidth(), pn_hinhAnh.getHeight(), Image.SCALE_SMOOTH);
        ImageIcon image = new ImageIcon(newImg);

        return image;
    }

    private void tbl_danhSachSanPhamMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_danhSachSanPhamMousePressed
        // TODO add your handling code here:
        String id = (String) tbl_danhSachSanPham.getValueAt(tbl_danhSachSanPham.getSelectedRow(), 0);
        sanPham = sanPham_DAO.getSanPhamById(id);
        int soLuong = Integer.parseInt(tbl_danhSachSanPham.getValueAt(tbl_danhSachSanPham.getSelectedRow(), 4).toString());
        sanPham.setSoLuong(soLuong);
        lblHinhAnh.setText("");
        File file = new File("");
        String path = file.getAbsolutePath();

        lblHinhAnh.setIcon(ResizeImage(path + "\\src\\main\\java\\hinhAnh\\" + sanPham.getHinhAnh()));
    }//GEN-LAST:event_tbl_danhSachSanPhamMousePressed

    private void btn_giamMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_giamMouseClicked
        // TODO add your handling code here:
        giamSoLuongHandler();
    }//GEN-LAST:event_btn_giamMouseClicked

    private void btn_themKhachHangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_themKhachHangMouseClicked
        // TODO add your handling code here:
//        System.out.println("running");
        TrangChu.activeQLKHForm();
    }//GEN-LAST:event_btn_themKhachHangMouseClicked

    public void setEvent() {
        tbl_danhSachSanPham.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tbl_danhSachSanPhamMousePressed(evt);
            }
        });

        tbl_chiTietHoaDon.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (tbl_chiTietHoaDon.getSelectedRow() == -1) {
                    btn_giam.setEnabled(false);
                } else {
                    btn_giam.setEnabled(true);
                }
            }
        });

//        ListSelectionModel cellSelection
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_giam;
    private javax.swing.JButton btn_thanhToan;
    private javax.swing.JButton btn_them;
    private javax.swing.JButton btn_themKhachHang;
    private javax.swing.JButton btn_themVaoHangCho;
    private javax.swing.JButton btn_tim;
    private javax.swing.JButton btn_xoaSanPham;
    private javax.swing.JButton btn_xoaTatCa;
    private static javax.swing.JComboBox<String> cb_gioiTinh;
    private static javax.swing.JComboBox<String> cb_kichCo;
    private static javax.swing.JComboBox<String> cb_loaiSanPham;
    private static javax.swing.JComboBox<String> cb_mauSac;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private static javax.swing.JLabel lblHinhAnh;
    private javax.swing.JLabel lbl_gmail;
    private javax.swing.JLabel lbl_kichCo;
    private javax.swing.JLabel lbl_loaiSanPham;
    private javax.swing.JLabel lbl_maSanPham;
    private javax.swing.JLabel lbl_mauSac;
    private javax.swing.JLabel lbl_soDienThoai;
    private javax.swing.JLabel lbl_soLuong;
    private javax.swing.JLabel lbl_tenKhachHang;
    private javax.swing.JLabel lbl_tenKhachHang1;
    private javax.swing.JLabel lbl_tenSanPham;
    private javax.swing.JLabel lbl_tienKhachDua;
    private javax.swing.JLabel lbl_tienThua;
    private javax.swing.JLabel lbl_tienThuaValue;
    private javax.swing.JLabel lbl_tongTienTitle;
    private static javax.swing.JLabel lbl_tongTienValue;
    private javax.swing.JPanel pn_hinhAnh;
    private static javax.swing.JTable tbl_chiTietHoaDon;
    private static javax.swing.JTable tbl_danhSachSanPham;
    private static javax.swing.JTextField txt_gmail;
    private static javax.swing.JTextField txt_maSanPham;
    private static javax.swing.JTextField txt_soDienThoai;
    private static javax.swing.JTextField txt_soLuong;
    private static javax.swing.JTextField txt_tenKhachHang;
    private static javax.swing.JTextField txt_tenSanPham;
    private static javax.swing.JTextField txt_tienKhachDua;
    // End of variables declaration//GEN-END:variables
}
