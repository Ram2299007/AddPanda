package com.Appzia.addpanda.Model;

public class categoryModel {

    String image,text;
    String category_id;

    public categoryModel(String image, String text) {
        this.image = image;
        this.text = text;
    }

    public categoryModel(String image, String text,String category_id) {
        this.image = image;
        this.text = text;
        this.category_id = category_id;


    }

    public String getCategory_id() {
        return category_id;
    }

    public void setCategory_id(String category_id) {
        this.category_id = category_id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
