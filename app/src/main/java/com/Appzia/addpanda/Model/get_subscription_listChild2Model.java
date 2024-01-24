package com.Appzia.addpanda.Model;

import java.util.ArrayList;

public class get_subscription_listChild2Model {
    String name;
    String type;
    ArrayList<get_subscription_listchild3Model> type_data = new ArrayList<>();


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public ArrayList<get_subscription_listchild3Model> getType_data() {
        return type_data;
    }

    public void setType_data(ArrayList<get_subscription_listchild3Model> type_data) {
        this.type_data = type_data;
    }
}
