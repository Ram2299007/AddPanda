package com.Appzia.addpanda.Model;

public class phonepay_payment_initChild2Model {

    String type;

    phonepay_payment_initChild3Model redirectInfo;

    public phonepay_payment_initChild2Model(String type, phonepay_payment_initChild3Model redirectInfo) {
        this.type = type;
        this.redirectInfo = redirectInfo;
    }


    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public phonepay_payment_initChild3Model getRedirectInfo() {
        return redirectInfo;
    }

    public void setRedirectInfo(phonepay_payment_initChild3Model redirectInfo) {
        this.redirectInfo = redirectInfo;
    }
}
