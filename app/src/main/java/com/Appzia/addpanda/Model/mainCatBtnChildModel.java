package com.Appzia.addpanda.Model;

public class mainCatBtnChildModel {

    String textName,catId;

    public mainCatBtnChildModel(String textName, String catId) {
        this.textName = textName;
        this.catId = catId;
    }

    public String getTextName() {
        return textName;
    }

    public void setTextName(String textName) {
        this.textName = textName;
    }

    public String getCatId() {
        return catId;
    }

    public void setCatId(String catId) {
        this.catId = catId;
    }
}
