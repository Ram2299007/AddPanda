package com.Appzia.addpanda.Model;

public class phonepay_payment_initParentModel {

    boolean success;
    String code;
    String message;

    phonepay_payment_initChildModel data;

    public phonepay_payment_initParentModel(boolean success, String code, String message, phonepay_payment_initChildModel data) {
        this.success = success;
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public phonepay_payment_initChildModel getData() {
        return data;
    }

    public void setData(phonepay_payment_initChildModel data) {
        this.data = data;
    }
}
