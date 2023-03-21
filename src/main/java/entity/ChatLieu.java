/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import dao.ChatLieuDAO;

/**
 *
 * @author 20086
 */
public class ChatLieu {
    private String maChatLieu;
    private String chatLieu;

    public ChatLieu() {
    }

    private String auto_ID(){
        ChatLieuDAO chatLieu_DAO = new ChatLieuDAO();
         String idPrefix = "CL";
        int length = chatLieu_DAO.getAllChatLieu().size();
        String finalId = idPrefix + String.format("%04d", length + 1);
        return finalId;
    }
    
    public ChatLieu(String chatLieu) {
        this.maChatLieu = auto_ID();
        this.chatLieu = chatLieu;
    }
     
    public ChatLieu(String maChatLieu, String chatLieu) {
        this.maChatLieu = maChatLieu;
        this.chatLieu = chatLieu;
    }

    
    public String getMaChatLieu() {
        return maChatLieu;
    }

    public void setMaChatLieu(String maChatLieu) {
        this.maChatLieu = maChatLieu;
    }

    public String getChatLieu() {
        return chatLieu;
    }

    public void setChatLieu(String chatLieu) {
        this.chatLieu = chatLieu;
    }
    
    
}
