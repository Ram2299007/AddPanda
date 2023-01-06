package com.Appzia.addpanda.Model;

public class trendingModel {

    String image,name,category_id,category_image,subcatid;

    public trendingModel(String image, String name, String category_id, String category_image, String subcatid) {
        this.image = image;
        this.name = name;
        this.category_id = category_id;
        this.category_image = category_image;
        this.subcatid = subcatid;
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

    public String getSubcatid() {
        return subcatid;
    }

    public void setSubcatid(String subcatid) {
        this.subcatid = subcatid;
    }
}
