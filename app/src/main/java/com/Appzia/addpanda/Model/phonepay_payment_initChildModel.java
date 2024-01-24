package com.Appzia.addpanda.Model;

public class phonepay_payment_initChildModel {
    String merchantId;
    String  merchantTransactionId;

    phonepay_payment_initChild2Model instrumentResponse;


    public phonepay_payment_initChildModel(String merchantId, String merchantTransactionId, phonepay_payment_initChild2Model instrumentResponse) {
        this.merchantId = merchantId;
        this.merchantTransactionId = merchantTransactionId;
        this.instrumentResponse = instrumentResponse;
    }

    public String getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(String merchantId) {
        this.merchantId = merchantId;
    }

    public String getMerchantTransactionId() {
        return merchantTransactionId;
    }

    public void setMerchantTransactionId(String merchantTransactionId) {
        this.merchantTransactionId = merchantTransactionId;
    }

    public phonepay_payment_initChild2Model getInstrumentResponse() {
        return instrumentResponse;
    }

    public void setInstrumentResponse(phonepay_payment_initChild2Model instrumentResponse) {
        this.instrumentResponse = instrumentResponse;
    }
}
