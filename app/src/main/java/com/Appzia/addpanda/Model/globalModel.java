package com.Appzia.addpanda.Model;

public class globalModel {
    int error_code;
    String message;

    public globalModel(int error_code, String message) {
        this.error_code = error_code;
        this.message = message;
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
}
