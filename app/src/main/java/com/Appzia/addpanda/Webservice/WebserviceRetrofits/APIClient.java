package com.Appzia.addpanda.Webservice.WebserviceRetrofits;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class APIClient {

    private static Retrofit retrofit = null;

    public static Retrofit getClient() {

        OkHttpClient.Builder clientBuilder = new OkHttpClient.Builder();
        clientBuilder.connectTimeout(5, TimeUnit.MINUTES); // Connection timeout
        clientBuilder.readTimeout(5, TimeUnit.MINUTES);    // Read timeout
        OkHttpClient client = clientBuilder.build();
        retrofit = new Retrofit.Builder()
                .baseUrl("https://api.adpanda.in/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();
        return retrofit;
    }

}