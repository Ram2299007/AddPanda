package com.Appzia.addpanda.Webservice.WebserviceRetrofits;

import android.app.DownloadManager;

import com.Appzia.addpanda.Model.get_category_listParentModel;
import com.Appzia.addpanda.Model.get_subscription_listModel;
import com.Appzia.addpanda.Model.globalModel;
import com.Appzia.addpanda.Model.payemntModel;
import com.Appzia.addpanda.Model.phonepay_payment_initParentModel;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface API {


    @POST("purchase_subscription")
    Call<phonepay_payment_initParentModel> purchase_subscription(@Body RequestBody body,@Header("token") String token);


    @POST("get_subscription_list")
    Call<get_subscription_listModel> get_subscription_list(@Header("token") String token);

    @POST("get_category_list")
    Call<get_category_listParentModel> get_category_list(@Header("token") String token);

    @POST("get_category_list_using_filter")
    Call<get_category_listParentModel> get_category_list_using_filter(@Body RequestBody body,@Header("token") String token);


    @POST("phonepay_check_status")
    Call<payemntModel> phonepay_check_status(@Body RequestBody body, @Header("token") String token);


    @POST("upload_user_contact_list")
    Call<globalModel> upload_user_contact_list(@Body RequestBody requestBody, @Header("token") String token);
}