/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import dao.XuatXuDAO;

/**
 *
 * @author 20086
 */
public class XuatXu {
    private String maXuatXu;
    private String noiXuatXu;

    private String auto_ID(){
        XuatXuDAO xuatXu_DAO = new XuatXuDAO();
         String idPrefix = "XX";
        int length = xuatXu_DAO.getAllXuatXu().size();
        String finalId = idPrefix + String.format("%04d", length + 1);
        return finalId;
    }
    
    public XuatXu() {
    }

    
    public XuatXu(String noiXuatXu) {
        this.maXuatXu = auto_ID();
        this.noiXuatXu = noiXuatXu;
    }

    public String getMaXuatXu() {
        return maXuatXu;
    }

    public void setMaXuatXu(String maXuatXu) {
        this.maXuatXu = maXuatXu;
    }

    public String getNoiXuatXu() {
        return noiXuatXu;
    }

    public void setNoiXuatXu(String noiXuatXu) {
        this.noiXuatXu = noiXuatXu;
    }
    
    
}
