package com.Appzia.addpanda.Model;

public class horiVisitingModel {

    String image,visiting_card_template_id;

    public horiVisitingModel(String image, String visiting_card_template_id) {
        this.image = image;
        this.visiting_card_template_id = visiting_card_template_id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getFile() {
        return visiting_card_template_id;
    }

    public void setFile(String visiting_card_template_id) {
        this.visiting_card_template_id = visiting_card_template_id;
    }
}
