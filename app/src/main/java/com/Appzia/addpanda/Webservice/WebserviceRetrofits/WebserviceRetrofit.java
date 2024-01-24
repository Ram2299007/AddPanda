package com.Appzia.addpanda.Webservice.WebserviceRetrofits;


import android.content.Context;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.ValueCallback;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ScrollView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.coordinatorlayout.widget.CoordinatorLayout;

import com.Appzia.addpanda.Fragments.mainFragment;
import com.Appzia.addpanda.Model.get_category_listParentModel;
import com.Appzia.addpanda.Model.get_subscription_listModel;
import com.Appzia.addpanda.Model.globalModel;
import com.Appzia.addpanda.Model.payemntModel;
import com.Appzia.addpanda.Model.phonepay_payment_initChild2Model;
import com.Appzia.addpanda.Model.phonepay_payment_initChild3Model;
import com.Appzia.addpanda.Model.phonepay_payment_initChildModel;
import com.Appzia.addpanda.Model.phonepay_payment_initParentModel;
import com.Appzia.addpanda.R;
import com.Appzia.addpanda.Screens.basicEditsFirstActivity;
import com.Appzia.addpanda.Screens.subscriptionActivity;
import com.Appzia.addpanda.Util.Constant.Constant;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import pl.droidsonroids.gif.GifImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class WebserviceRetrofit {

    static String TAG = "Adppanda";

    public static void purchase_subscription(Context mContext, String subscription_id, String amount, WebView webview, ScrollView lyt, CoordinatorLayout btlyt, GifImageView progressbar, AppCompatActivity activity, String token, subscriptionActivity subscriptionActivity) {

        progressbar.setVisibility(View.VISIBLE);
        Retrofit retrofit = APIClientForPayment.getClient();
        API api = retrofit.create(API.class);
        RequestBody requestBody = new MultipartBody.Builder().setType(MultipartBody.FORM)
                .addFormDataPart("subscription_id", subscription_id)
                .build();

        api.purchase_subscription(requestBody, token).enqueue(new Callback<phonepay_payment_initParentModel>() {
            @Override
            public void onResponse(@NonNull Call<phonepay_payment_initParentModel> call, @NonNull Response<phonepay_payment_initParentModel> response) {
                Log.d(TAG, "onResponse_: " + response.code());

                if (response.body() != null) {

                    String code = response.body().getCode();
                    String message = response.body().getMessage();

                    phonepay_payment_initChildModel model = response.body().getData();


                    phonepay_payment_initChild2Model model2 = model.getInstrumentResponse();
                    phonepay_payment_initChild3Model model3 = model2.getRedirectInfo();
                    if (code.equals("PAYMENT_INITIATED")) {
                        progressbar.setVisibility(View.GONE);


                        lyt.setVisibility(View.GONE);
                        webview.setVisibility(View.VISIBLE);
                        btlyt.setVisibility(View.VISIBLE);
                        // Enable JavaScript (if needed)
                        WebSettings webSettings = webview.getSettings();
                        webSettings.setJavaScriptEnabled(true);
                        webview.setWebViewClient(new WebViewClient());
                        String url = model3.getUrl();
                        webview.loadUrl(url);


                        Window window = activity.getWindow();
                        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
                        window.setStatusBarColor(activity.getResources().getColor(R.color.white));

                        String js = "(function() { " +
                                "return document.getElementById('my_element').innerHTML;" +
                                "})()";

                        webview.setWebViewClient(new WebViewClient() {
                            @Override
                            public void onPageFinished(WebView view, String url) {
                                super.onPageFinished(view, url);
                                view.evaluateJavascript(js, new ValueCallback<String>() {
                                    @Override
                                    public void onReceiveValue(String value) {
                                        // 'value' contains the result of the JavaScript code
                                        Log.d("WebViewdata", "Data: " + url);
                                        if (url.equals("https://api.adpanda.in/ApiController/fail")) {

                                            new CountDownTimer(2000, 1000) {

                                                public void onTick(long millisUntilFinished) {
                                                    progressbar.setVisibility(View.VISIBLE);
                                                }

                                                public void onFinish() {
                                                    progressbar.setVisibility(View.VISIBLE);
                                                    lyt.setVisibility(View.VISIBLE);
                                                    webview.setVisibility(View.GONE);
                                                    btlyt.setVisibility(View.VISIBLE);
                                                    progressbar.setVisibility(View.GONE);
                                                    Window window = activity.getWindow();
                                                    window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                                                    window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
                                                    window.setStatusBarColor(activity.getResources().getColor(R.color.appThemeColor));
                                                }

                                            }.start();


                                        } else if (url.equals("https://api.adpanda.in/ApiController/success")) {


                                            new CountDownTimer(2000, 1000) {

                                                public void onTick(long millisUntilFinished) {
                                                    progressbar.setVisibility(View.VISIBLE);
                                                }

                                                public void onFinish() {
                                                    progressbar.setVisibility(View.VISIBLE);
                                                    WebserviceRetrofit.phonepay_check_status(mContext,token,model.getMerchantTransactionId(),lyt,webview,btlyt,progressbar,activity,subscriptionActivity);
                                                }

                                            }.start();


                                        }
                                    }
                                });
                            }
                        });


                    } else {
                        Toast.makeText(mContext, message, Toast.LENGTH_SHORT).show();
                    }

                } else {
                    Log.d(TAG, "onResponse: " + response.toString());
                }

            }

            @Override
            public void onFailure(@NonNull Call<phonepay_payment_initParentModel> call, @NonNull Throwable t) {
                //   Toast.makeText(mContext, "" + t.getMessage(), Toast.LENGTH_SHORT).show();
                Log.e("loginerror", t.getMessage());
            }
        });
    }




    public static void get_subscription_list(Context mContext, subscriptionActivity subscriptionActivity, String token, GifImageView progressbar) {

        progressbar.setVisibility(View.VISIBLE);
        Retrofit retrofit = APIClient.getClient();
        API api = retrofit.create(API.class);


        api.get_subscription_list(token).enqueue(new Callback<get_subscription_listModel>() {
            @Override
            public void onResponse(@NonNull Call<get_subscription_listModel> call, @NonNull Response<get_subscription_listModel> response) {
                Log.d(TAG, "onResponse_: " + response);

                if (response.body() != null) {

                    int errorcode = response.body().getError_code();
                    String message = response.body().getMessage();
                    Constant.get_subscription_list.clear();
                    if (errorcode == 200) {
                        progressbar.setVisibility(View.GONE);
                        Constant.get_subscription_list = response.body().getData();
                        subscriptionActivity.setAdapter( Constant.get_subscription_list,response.body().getPostion() );

                    } else {
                        Toast.makeText(mContext, message, Toast.LENGTH_SHORT).show();
                    }

                } else {
                    Log.d(TAG, "onResponse: " + response.toString());
                }

            }

            @Override
            public void onFailure(@NonNull Call<get_subscription_listModel> call, @NonNull Throwable t) {
                //   Toast.makeText(mContext, "" + t.getMessage(), Toast.LENGTH_SHORT).show();
                Log.e("loginerror", t.getMessage());
            }
        });
    }

    public static void get_category_list(Context mContext, String token, mainFragment mainFragment) {
        com.Appzia.addpanda.Fragments.mainFragment.shimmerFrameLayout.startShimmer();
        Retrofit retrofit = APIClient.getClient();
        API api = retrofit.create(API.class);


        api.get_category_list(token).enqueue(new Callback<get_category_listParentModel>() {
            @Override
            public void onResponse(@NonNull Call<get_category_listParentModel> call, @NonNull Response<get_category_listParentModel> response) {
                Log.d(TAG, "onResponse_: " + response);

                if (response.body() != null) {

                    int errorcode = response.body().getError_code();
                    String message = response.body().getMessage();

                    Constant.get_category_listChild1ModelList.clear();
                    if (errorcode == 200) {
                        com.Appzia.addpanda.Fragments.mainFragment.shimmerFrameLayout.stopShimmer();
                        com.Appzia.addpanda.Fragments.mainFragment.whatsapp.setVisibility(View.VISIBLE);
                        com.Appzia.addpanda.Fragments.mainFragment.CoLayout.setVisibility(View.VISIBLE);
                        com.Appzia.addpanda.Fragments.mainFragment.shimmerFrameLayout.setVisibility(View.GONE);
                        Constant.get_category_listChild1ModelList = response.body().getData();
                        mainFragment.setAapterCategory(Constant.get_category_listChild1ModelList);


                        mainFragment.setAdapter();

                    } else {
                        Toast.makeText(mContext, message, Toast.LENGTH_SHORT).show();
                    }

                } else {
                    Log.d(TAG, "onResponse: " + response.toString());
                }

            }

            @Override
            public void onFailure(@NonNull Call<get_category_listParentModel> call, @NonNull Throwable t) {
                //   Toast.makeText(mContext, "" + t.getMessage(), Toast.LENGTH_SHORT).show();
                Log.e("loginerror", t.getMessage());
            }
        });
    }
    public static void get_category_listBasicEdit(Context mContext, String token, basicEditsFirstActivity basicEditsFirstActivity2, String flag) {
        com.Appzia.addpanda.Screens.basicEditsFirstActivity.shimmerFrameLayout.startShimmer();
        Retrofit retrofit = APIClient.getClient();
        API api = retrofit.create(API.class);
        RequestBody requestBody = new MultipartBody.Builder().setType(MultipartBody.FORM)
                .addFormDataPart("flag", flag)

                .build();

        api.get_category_list_using_filter(requestBody,token).enqueue(new Callback<get_category_listParentModel>() {
            @Override
            public void onResponse(@NonNull Call<get_category_listParentModel> call, @NonNull Response<get_category_listParentModel> response) {
                Log.d(TAG, "get_category_listBasicEditonResponse_: " + response);

                if (response.body() != null) {

                    int errorcode = response.body().getError_code();
                    String message = response.body().getMessage();

                    Constant.get_category_listChild1ModelList.clear();
                    if (errorcode == 200) {
                        com.Appzia.addpanda.Screens.basicEditsFirstActivity.shimmerFrameLayout.stopShimmer();

                       com.Appzia.addpanda.Screens.basicEditsFirstActivity.CoLayout.setVisibility(View.VISIBLE);
                       com.Appzia.addpanda.Screens.basicEditsFirstActivity.shimmerFrameLayout.setVisibility(View.GONE);
                        Constant.get_category_listChild1ModelList = response.body().getData();
                        basicEditsFirstActivity2.setAapterCategory(Constant.get_category_listChild1ModelList);

                    } else {
                        Toast.makeText(mContext, message, Toast.LENGTH_SHORT).show();
                    }

                } else {
                    Log.d(TAG, "onResponse: " + response.toString());
                }

            }

            @Override
            public void onFailure(@NonNull Call<get_category_listParentModel> call, @NonNull Throwable t) {
                //   Toast.makeText(mContext, "" + t.getMessage(), Toast.LENGTH_SHORT).show();
                Log.e("loginerror", t.getMessage());
            }
        });
    }



    public static void phonepay_check_status(Context mContext, String token, String merchantTransactionId, ScrollView lyt, WebView webview, CoordinatorLayout btlyt, GifImageView progressbar, AppCompatActivity activity, subscriptionActivity subscriptionActivity){

        Retrofit retrofit = APIClientForPayment.getClient();
        API api = retrofit.create(API.class);
        RequestBody requestBody = new MultipartBody.Builder().setType(MultipartBody.FORM)
                .addFormDataPart("merchantTransactionId", merchantTransactionId)
                .build();

        api.phonepay_check_status(requestBody,token).enqueue(new Callback<payemntModel>() {
            @Override
            public void onResponse(@NonNull Call<payemntModel> call, @NonNull Response<payemntModel> response) {
                Log.d(TAG, "phonepay_check_statusonResponse_: " + response.body().getStatus());

                if (response.body() != null) {

                    if(response.body().getStatus().equals("PAYMENT_SUCCESS")){
                        lyt.setVisibility(View.VISIBLE);
                        webview.setVisibility(View.GONE);
                        btlyt.setVisibility(View.VISIBLE);
                        progressbar.setVisibility(View.GONE);
                        Window window = activity.getWindow();
                        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
                        window.setStatusBarColor(activity.getResources().getColor(R.color.appThemeColor));
                        get_subscription_list(mContext,subscriptionActivity,token,progressbar);
                    }

                } else {
                    Log.d(TAG, "onResponse: " + response.toString());
                }

            }

            @Override
            public void onFailure(@NonNull Call<payemntModel> call, @NonNull Throwable t) {
                //   Toast.makeText(mContext, "" + t.getMessage(), Toast.LENGTH_SHORT).show();
                Log.e("loginerror", t.getMessage());
            }
        });

    }
}
