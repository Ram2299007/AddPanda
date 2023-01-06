package com.Appzia.addpanda.Model;

public class AnniversaryModel {

    String image,name,category_id,category_image,sub_cat_id;

    public AnniversaryModel(String image, String name, String category_id, String category_image, String sub_cat_id) {
        this.image = image;
        this.name = name;
        this.category_id = category_id;
        this.category_image = category_image;
        this.sub_cat_id = sub_cat_id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory_id() {
        return category_id;
    }

    public void setCategory_id(String category_id) {
        this.category_id = category_id;
    }

    public String getCategory_image() {
        return category_image;
    }

    public void setCategory_image(String category_image) {
        this.category_image = category_image;
    }

    public String getSub_cat_id() {
        return sub_cat_id;
    }

    public void setSub_cat_id(String sub_cat_id) {
        this.sub_cat_id = sub_cat_id;
    }
}
