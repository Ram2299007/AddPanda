package com.Appzia.addpanda.Webservice.WebserviceRetrofits;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class APIClientForPayment {

    private static Retrofit retrofit = null;

    public static Retrofit getClient() {
        retrofit = new Retrofit.Builder()
                .baseUrl("https://api.adpanda.in/")
          //      .baseUrl("http://192.168.0.238:5000/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit;
    }

}