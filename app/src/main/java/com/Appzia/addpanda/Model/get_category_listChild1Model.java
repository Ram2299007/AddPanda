package com.Appzia.addpanda.Model;


import java.util.ArrayList;

public class get_category_listChild1Model {


    String category_id;
    String category_type_flag;
    String category_type;
    String category_name;
    String category_image;
    String expiry;
    String is_lock;
    ArrayList<get_category_listChild2Model> sub_category_list = new ArrayList<>();


    public String getCategory_id() {
        return category_id;
    }

    public void setCategory_id(String category_id) {
        this.category_id = category_id;
    }

    public String getCategory_type_flag() {
        return category_type_flag;
    }

    public void setCategory_type_flag(String category_type_flag) {
        this.category_type_flag = category_type_flag;
    }

    public String getCategory_type() {
        return category_type;
    }

    public void setCategory_type(String category_type) {
        this.category_type = category_type;
    }

    public String getCategory_name() {
        return category_name;
    }

    public void setCategory_name(String category_name) {
        this.category_name = category_name;
    }

    public String getCategory_image() {
        return category_image;
    }

    public void setCategory_image(String category_image) {
        this.category_image = category_image;
    }

    public String getIs_lock() {
        return is_lock;
    }

    public void setIs_lock(String is_lock) {
        this.is_lock = is_lock;
    }

    public ArrayList<get_category_listChild2Model> getSub_category_list() {
        return sub_category_list;
    }

    public void setSub_category_list(ArrayList<get_category_listChild2Model> sub_category_list) {
        this.sub_category_list = sub_category_list;
    }


    public String getExpiry() {
        return expiry;
    }

    public void setExpiry(String expiry) {
        this.expiry = expiry;
    }
}
