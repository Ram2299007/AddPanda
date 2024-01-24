package com.Appzia.addpanda.Model;

public class trendingSubModel {

    String image, category_id, sub_cat_id, template_id, sub_cat_name;
    int is_active;

    public trendingSubModel(String image, String category_id, String sub_cat_id, String template_id, int is_active) {
        this.image = image;
        this.category_id = category_id;
        this.sub_cat_id = sub_cat_id;
        this.template_id = template_id;
        this.is_active = is_active;
    }

    public trendingSubModel(String image, String category_id, String sub_cat_id, String sub_cat_name, String test, int is_active) {
        this.image = image;
        this.category_id = category_id;
        this.sub_cat_id = sub_cat_id;
        this.sub_cat_name = sub_cat_name;
        this.is_active = is_active;


    }

    public int getIs_active() {
        return is_active;
    }

    public void setIs_active(int is_active) {
        this.is_active = is_active;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getCategory_id() {
        return category_id;
    }

    public void setCategory_id(String category_id) {
        this.category_id = category_id;
    }

    public String getSub_cat_id() {
        return sub_cat_id;
    }

    public void setSub_cat_id(String sub_cat_id) {
        this.sub_cat_id = sub_cat_id;
    }

    public String getTemplate_id() {
        return template_id;
    }

    public void setTemplate_id(String template_id) {
        this.template_id = template_id;
    }

    public String getSub_cat_name() {
        return sub_cat_name;
    }

    public void setSub_cat_name(String sub_cat_name) {
        this.sub_cat_name = sub_cat_name;
    }
}
