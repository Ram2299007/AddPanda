package com.Appzia.addpanda.Model;

public class downloadModel {


    //typoe 1 for main editframe download
    //type 2 for visitinng card download
    String image,type;

    public downloadModel(String image, String type) {
        this.image = image;
        this.type = type;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
