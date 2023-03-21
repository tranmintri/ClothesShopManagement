/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import dao.NhanVienDAO;
import java.util.Date;
import java.sql.Timestamp;
import java.util.Calendar;

/**
 *
 * @author 20086
 */
public class NhanVien {
    private String maNhanVien;
    private String hoVaTen;
    private Date ngaySinh;
    private String diaChi;
    private String sdt;
    private boolean gioiTinh;
    private int luong;
    private String email;
    private String chucVu;
    private String OTP;
    private Timestamp expriedAt;
    
    public NhanVien() {
        this.OTP = null;
        expriedAt = null;
    }

    public NhanVien(String maNhanVien, String hoVaTen, Date ngaySinh, String diaChi, String sdt, boolean gioiTinh, int luong, String email, String chucVu,String OTP, Timestamp expriedAt) {
        this.maNhanVien = maNhanVien;
        this.hoVaTen = hoVaTen;
        this.ngaySinh = ngaySinh;
        this.diaChi = diaChi;
        this.sdt = sdt;
        this.gioiTinh = gioiTinh;
        this.luong = luong;
        this.email = email;
        this.chucVu = chucVu;
         this.OTP = OTP;
        this.expriedAt = expriedAt;
    }
    
       public String auto_ID(){
    	NhanVienDAO nhanVien_DAO = new NhanVienDAO();
    	String idPrefix = "NV";
    	int length = nhanVien_DAO.getAllNhanVien().size();
    	String finalId = idPrefix + String.format("%04d", length + 1);
    	return finalId;   	  	
    }
       
       public String auto_ID1(){
    	Date date = new Date();
        String ngay = String.format("%02d",date.getDate());
        String thang = String.format("%02d",date.getMonth() + 1);
        String nam = String.format("%02d", date.getYear() % 100);
        String gio = String.format("%02d", date.getHours());
        String phut = String.format("%02d", date.getMinutes());
           
    	String finalId = ngay+thang+nam+gio+phut;
    	
    	return finalId;
    }

    public NhanVien(String hoVaTen, Date ngaySinh, String diaChi, String sdt, boolean gioiTinh, int luong, String email, String chucVu, String OTP, Timestamp expriedAt) {
        this.maNhanVien = auto_ID();
        this.hoVaTen = hoVaTen;
        this.ngaySinh = ngaySinh;
        this.diaChi = diaChi;
        this.sdt = sdt;
        this.gioiTinh = gioiTinh;
        this.luong = luong;
        this.email = email;
        this.chucVu = chucVu;
        this.OTP = OTP;
        this.expriedAt = expriedAt;
    }

    
    public String getOTP() {
        return OTP;
    }

    public void setOTP(String OTP) {
        this.OTP = OTP;
    }

    public Timestamp getExpriedAt() {
        return expriedAt;
    }

    public void setExpriedAt(Timestamp expriedAt) {
        this.expriedAt = expriedAt;
    }

    
    public String getMaNhanVien() {
        return maNhanVien;
    }

    public void setMaNhanVien(String maNhanVien) {
        this.maNhanVien = maNhanVien;
    }

    public String getHoVaTen() {
        return hoVaTen;
    }

    public void setHoVaTen(String hoVaTen) {
        this.hoVaTen = hoVaTen;
    }

    public Date getNgaySinh() {
        return ngaySinh;
    }

    public void setNgaySinh(Date ngaySinh) {
        this.ngaySinh = ngaySinh;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    public boolean getGioiTinh() {
        return gioiTinh;
    }

    public void setGioiTinh(boolean gioiTinh) {
        this.gioiTinh = gioiTinh;
    }

    public int getLuong() {
        return luong;
    }

    public void setLuong(int luong) {
        this.luong = luong;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getChucVu() {
        return chucVu;
    }

    public void setChucVu(String chucVu) {
        this.chucVu = chucVu;
    }
    
    
}
