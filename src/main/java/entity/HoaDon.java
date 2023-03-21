/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import dao.ChiTietHoaDonDAO;
import dao.HoaDonDAO;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Random;

/**
 *
 * @author 20086
 */
public class HoaDon {
    private String maHoaDon;
    private KhachHang khachHang;
    private NhanVien nhanVien;
    private Date ngayLap;

    private String auto_IDHoaDon(){
        HoaDonDAO hoaDon_DAO = new HoaDonDAO();
        String idPrefix = "HD";
        Date currentDate = new Date(Calendar.getInstance().getTime().getTime());
        String ngay = currentDate.getDate()+"";
        String thang = String.format("%02d", currentDate.getMonth() + 1);
        String nam = currentDate.getYear() + 1900 + "";
        
        String format = ngay+thang+nam;
        System.err.println(ngay +"   "+ thang +"   "+nam);
        int length = hoaDon_DAO.getAllHoaDon(format).size();
        String finalId = idPrefix +format+ String.format("%04d", length + 1);
        return finalId;
    }
    
    private String auto_IDHoaDonCho(){
        HoaDonDAO hoaDon_DAO = new HoaDonDAO();
        String idPrefix = "HDC";
        String length = String.format("%04d", new Random().nextInt(10000));
        String finalId = idPrefix + length;
        
        while(hoaDon_DAO.getHoaDonById(finalId) != null){
            finalId = "HDC" + String.format("%04d", new Random().nextInt(10000));
        }
        
        return finalId;
    }
    
    public HoaDon() {
        this.maHoaDon = auto_IDHoaDon();
    }
    
    public HoaDon(HoaDon hd){
        this.khachHang = hd.getKhachHang();
        this.nhanVien = hd.getNhanVien();
        this.ngayLap = hd.getNgayLap();
    }
    
    public HoaDon(KhachHang khachHang, NhanVien nhanVien, Date ngayLap) {
        this.maHoaDon = auto_IDHoaDon();
        this.khachHang = khachHang;
        this.nhanVien = nhanVien;
        this.ngayLap = ngayLap;
    }
    
    public HoaDon(String maHoaDon, KhachHang khachHang, NhanVien nhanVien, Date ngayLap) {
        this.maHoaDon = maHoaDon;
        this.khachHang = khachHang;
        this.nhanVien = nhanVien;
        this.ngayLap = ngayLap;
    }
    
    public void setThanhHoaDonCho(){
        this.maHoaDon = auto_IDHoaDonCho();
    }
    
    public void setThanhHoaDon(){
        this.maHoaDon = auto_IDHoaDon();
    }
    
    public String getMaHoaDon() {
        return maHoaDon;
    }

    public void setMaHoaDon(String maHoaDon) {
        this.maHoaDon = maHoaDon;
    }

    public KhachHang getKhachHang() {
        return khachHang;
    }

    public void setKhachHang(KhachHang khachHang) {
        this.khachHang = khachHang;
    }

    public NhanVien getNhanVien() {
        return nhanVien;
    }

    public void setNhanVien(NhanVien nhanVien) {
        this.nhanVien = nhanVien;
    }

    public Date getNgayLap() {
        return ngayLap;
    }

    public void setNgayLap(Date ngayLap) {
        this.ngayLap = ngayLap;
    }
    
    public long tongTien(){
        long tongTien = 0;
        ChiTietHoaDonDAO cthd_DAO = new ChiTietHoaDonDAO();
        ArrayList<ChiTietHoaDon>listChiTietHoaDon = cthd_DAO.getAllCTHDByHoaDon(this);
        
        for(ChiTietHoaDon cthd : listChiTietHoaDon){
            tongTien += cthd.getSoLuong();
        }
        
        return tongTien;
    }
}
