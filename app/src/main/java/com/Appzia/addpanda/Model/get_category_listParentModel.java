package com.Appzia.addpanda.Model;

import java.util.ArrayList;

public class get_category_listParentModel {
    int success;
    int error_code;
    String message;


    ArrayList<get_category_listChild1Model> data = new ArrayList<>();

    public int getSuccess() {
        return success;
    }

    public void setSuccess(int success) {
        this.success = success;
    }

    public int getError_code() {
        return error_code;
    }

    public void setError_code(int error_code) {
        this.error_code = error_code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ArrayList<get_category_listChild1Model> getData() {
        return data;
    }

    public void setData(ArrayList<get_category_listChild1Model> data) {
        this.data = data;
    }
}
