package com.Appzia.addpanda.Model;

import java.util.ArrayList;

public class get_subscription_listChildModel {

    String subscription_id;
    String plan_name;
    String mrp;
    String price;
    String plan_valiity;
    String description;
    int is_active;
    String expiry;
    int sr_no;

    ArrayList<get_subscription_listChild2Model> list = new ArrayList<>();

    public String getSubscription_id() {
        return subscription_id;
    }

    public void setSubscription_id(String subscription_id) {
        this.subscription_id = subscription_id;
    }

    public String getPlan_name() {
        return plan_name;
    }

    public void setPlan_name(String plan_name) {
        this.plan_name = plan_name;
    }

    public String getMrp() {
        return mrp;
    }

    public void setMrp(String mrp) {
        this.mrp = mrp;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getPlan_valiity() {
        return plan_valiity;
    }

    public void setPlan_valiity(String plan_valiity) {
        this.plan_valiity = plan_valiity;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getIs_active() {
        return is_active;
    }

    public void setIs_active(int is_active) {
        this.is_active = is_active;
    }

    public String getExpiry() {
        return expiry;
    }

    public void setExpiry(String expiry) {
        this.expiry = expiry;
    }

    public int getSr_no() {
        return sr_no;
    }

    public void setSr_no(int sr_no) {
        this.sr_no = sr_no;
    }

    public ArrayList<get_subscription_listChild2Model> getList() {
        return list;
    }

    public void setList(ArrayList<get_subscription_listChild2Model> list) {
        this.list = list;
    }
}
