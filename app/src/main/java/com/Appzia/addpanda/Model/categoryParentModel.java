package com.Appzia.addpanda.Model;

import java.util.ArrayList;
import java.util.List;

public class categoryParentModel {

    String catname,catIdKey;
    List<categoryChildModel> categoryChildModel;
    List<mainCatBtnChildModel> mainCatBtnChildModel;

    public categoryParentModel(String catname, String catIdKey, List<com.Appzia.addpanda.Model.categoryChildModel> categoryChildModel) {
        this.catname = catname;
        this.catIdKey = catIdKey;
        this.categoryChildModel = categoryChildModel;

    }

    public String getCatname() {
        return catname;
    }

    public void setCatname(String catname) {
        this.catname = catname;
    }

    public String getCatIdKey() {
        return catIdKey;
    }

    public void setCatIdKey(String catIdKey) {
        this.catIdKey = catIdKey;
    }

    public List<com.Appzia.addpanda.Model.categoryChildModel> getCategoryChildModel() {
        return categoryChildModel;
    }

    public void setCategoryChildModel(List<com.Appzia.addpanda.Model.categoryChildModel> categoryChildModel) {
        this.categoryChildModel = categoryChildModel;
    }

    public List<com.Appzia.addpanda.Model.mainCatBtnChildModel> getMainCatBtnChildModel() {
        return mainCatBtnChildModel;
    }

    public void setMainCatBtnChildModel(List<com.Appzia.addpanda.Model.mainCatBtnChildModel> mainCatBtnChildModel) {
        this.mainCatBtnChildModel = mainCatBtnChildModel;
    }
}
