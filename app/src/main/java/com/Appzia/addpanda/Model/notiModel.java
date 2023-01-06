package com.Appzia.addpanda.Model;

public class notiModel {

    String notiText,notiTime,notiImg;

    public notiModel(String notiText, String notiTime, String notiImg) {
        this.notiText = notiText;
        this.notiTime = notiTime;
        this.notiImg = notiImg;
    }

    public String getNotiText() {
        return notiText;
    }

    public void setNotiText(String notiText) {
        this.notiText = notiText;
    }

    public String getNotiTime() {
        return notiTime;
    }

    public void setNotiTime(String notiTime) {
        this.notiTime = notiTime;
    }

    public String getNotiImg() {
        return notiImg;
    }

    public void setNotiImg(String notiImg) {
        this.notiImg = notiImg;
    }
}
