package com.Appzia.addpanda.Webservice;

import static android.content.Context.MODE_PRIVATE;

import android.app.Activity;

import android.app.ProgressDialog;
import android.content.Context;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.util.Log;

import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatSeekBar;

import com.Appzia.addpanda.Fragments.DownloadsFragment;
import com.Appzia.addpanda.Fragments.categoryTabFragment;
import com.Appzia.addpanda.Model.categoryChildModel;
import com.Appzia.addpanda.Model.categoryModel;
import com.Appzia.addpanda.Model.categoryParentModel;
import com.Appzia.addpanda.Model.downloadModel;
import com.Appzia.addpanda.Model.faqModel;
import com.Appzia.addpanda.Model.globalModel;
import com.Appzia.addpanda.Model.horiVisitingModel;
import com.Appzia.addpanda.Model.mainCatBtnChildModel;
import com.Appzia.addpanda.Model.notiModel;
import com.Appzia.addpanda.Model.viewPagerModel;
import com.Appzia.addpanda.Screens.Basic_editFrameActivity;
import com.Appzia.addpanda.Screens.EditFrameOwner;
import com.Appzia.addpanda.Screens.NotificationActivity;
import com.Appzia.addpanda.Screens.aboutUsScreen;
import com.Appzia.addpanda.Screens.basicEditsFirstActivity;
import com.Appzia.addpanda.Screens.contentcreatorBusinessJoinScreen;
import com.Appzia.addpanda.Screens.contentcreatorPersonalJoinScreen;
import com.Appzia.addpanda.Screens.createVisingCardHorizontalScreens;
import com.Appzia.addpanda.Screens.createVisingCardVerticalScreens;
import com.Appzia.addpanda.Screens.downloadImageActivity;
import com.Appzia.addpanda.Screens.editFameActivity;
import com.Appzia.addpanda.Screens.faqsScreen;
import com.Appzia.addpanda.Screens.helpAndSupportScreen;
import com.Appzia.addpanda.Screens.kycVerificationScreen;
import com.Appzia.addpanda.Screens.manageAcount3Activity;
import com.Appzia.addpanda.Screens.privacyPolicyScreen;
import com.Appzia.addpanda.Screens.settingScreen;
import com.Appzia.addpanda.Screens.termAndConditionScreen;
import com.Appzia.addpanda.Screens.trendingActivityBasic;
import com.Appzia.addpanda.Screens.viewAll;
import com.Appzia.addpanda.Screens.viewAllBasic;
import com.Appzia.addpanda.Util.Constant.Constant;
import com.Appzia.addpanda.Fragments.mainFragment;
import com.Appzia.addpanda.Fragments.profileFragment;
import com.Appzia.addpanda.Screens.MainActivity;
import com.Appzia.addpanda.Model.frameModel;
import com.Appzia.addpanda.Model.trendingSubModel;
import com.Appzia.addpanda.R;
import com.Appzia.addpanda.Screens.Business;
import com.Appzia.addpanda.Screens.BusinessProfile;
import com.Appzia.addpanda.Screens.MobileVerification;
import com.Appzia.addpanda.Screens.Personal;
import com.Appzia.addpanda.Screens.PersonalProfile;
import com.Appzia.addpanda.Screens.ReferActivity;
import com.Appzia.addpanda.Screens.choosePersonalBusiness;
import com.Appzia.addpanda.Screens.trendingActivity;
import com.Appzia.addpanda.Webservice.WebserviceRetrofits.API;
import com.Appzia.addpanda.Webservice.WebserviceRetrofits.APIClient;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.MySSLSocketFactory;
import com.loopj.android.http.RequestParams;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.InputStream;

import cz.msebera.android.httpclient.Header;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;


public class Webservice {
    public static final String TAG = "WebserviceRetrofit";
    // public static final String BASE_URL = "http://192.168.0.145/add_panda/";
    //  public static final String BASE_URL = "https://www.adpanda.in/api/";
    public static final String BASE_URL = "https://api.adpanda.in/";
    public static final String UPDATE_USER_PROFILE_DATA = BASE_URL + "update_user_profile_data";
    public static final String SEND_OTP = BASE_URL + "send_otp";
    public static final String VERIFY_OTP = BASE_URL + "verify_otp";
    public static final String SOCIAL_MEDIA_LOGIN_PERSONAL = BASE_URL + "social_media_login";
    public static final String SOCIAL_MEDIA_LOGIN_BUSINESS = BASE_URL + "social_media_login";
    public static final String UPLOAD_USER_CONTACT_LIST = BASE_URL + "upload_user_contact_list";
    public static final String CHECK_EMAIL_ID = BASE_URL + "check_email_id";
    public static final String CHECK_MOBILE = BASE_URL + "check_mobile_number";
    public static final String FETCH_USER_PROFILE = BASE_URL + "fetch_user_profile";
    public static final String GET_ABOUT_US = BASE_URL + "get_about_us";
    public static final String GET_HELP_AND_SUPPORT = BASE_URL + "get_help_and_support";
    public static final String GET_SUBCAT_ID = BASE_URL + "get_sub_category_list";
    public static final String get_category_list_All = BASE_URL + "get_category_list";
    public static final String get_category_list_All_BASIC_EDITS = BASE_URL + "get_category_list_using_filter";
    public static final String GET_FRAME_LIST = BASE_URL + "get_frame_list";
    public static final String ADD_FRAME_PHOTO = BASE_URL + "add_frame_photo";
    public static final String FETCH_ALL_FRAME_PHOTO = BASE_URL + "fetch_all_frames_photo";
    public static final String UPDATE_FCM_TOKEN = BASE_URL + "update_fcm_token";
    public static final String SAVE_BANK_DETAILS = BASE_URL + "save_bank_details";
    public static final String GET_BANK_DETAILS = BASE_URL + "get_bank_details";
    public static final String GET_IDENTITY_VERIFICATION = BASE_URL + "get_identity_verification";
    public static final String IDENTITY_VERIFICATION = BASE_URL + "identity_verification";

    public static final String GET_TEMPLATE = BASE_URL + "get_template_list";
    public static final String CREATE_CONTENT = BASE_URL + "create_content";
    public static final String GET_MY_CONTENT_CREATOR_LIST = BASE_URL + "get_my_content_creator_list";
    public static final String GET_MY_CREATING_VISITING_CARD_LIST = BASE_URL + "get_my_creating_visiting_card_list";
    public static final String GET_MY_NOTIFICATION = BASE_URL + "get_my_notifications";
    public static final String ADD_PARTNER_WITH_US = BASE_URL + "add_partner_with_us_data";
    public static final String GET_FAQ = BASE_URL + "get_faq";
    public static final String GET_VISITING_CARD_LIST = BASE_URL + "get_visiting_card_list";
    public static final String CREATING_VISITING_CARD = BASE_URL + "creating_visiting_card";
    public static final String GET_PRIVACY_POLICY = BASE_URL + "get_privacy_policy";
    public static final String GET_TERM_AND_CONDITION = BASE_URL + "get_terms_conditions";
    public static final String GET_BANNER = BASE_URL + "get_banner";


    public static void update_user_profile_data_personal(final Context mContext, String account_type, String name, String email, String mobile, String address, String dob, String poilitical_interest, String token, File imageFile) {
        try {

            final AsyncHttpClient client = new AsyncHttpClient();
            client.setSSLSocketFactory(MySSLSocketFactory.getFixedSocketFactory());
            final RequestParams request_param = new RequestParams();

            request_param.put("account_type", account_type);
            request_param.put("name", name);
            request_param.put("email", email);
            request_param.put("mobile", mobile);
            request_param.put("address", address);
            request_param.put("dob", dob);
            request_param.put("poilitical_interest", poilitical_interest);

            if (imageFile != null) {
                request_param.put("image", imageFile);
            }


            Log.d(TAG, "@@@update_user_profile_data_personal :" + UPDATE_USER_PROFILE_DATA + "?" + request_param.toString());
            ((Activity) mContext).runOnUiThread(new Runnable() {
                public void run() {

                    client.addHeader("token", token);
                    client.post(UPDATE_USER_PROFILE_DATA, request_param, new JsonHttpResponseHandler() {
                        @Override
                        public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                            Log.d(TAG, "@@@update_user_profile_data_personal_response :" + response.toString());

                            try {
                                int status = response.getInt("error_code");
                                if (status == 200) {
                                    String message = response.getString("message");
                                    Toast.makeText(mContext, message, Toast.LENGTH_SHORT).show();

                                    JSONObject obj = response.getJSONObject("data");
                                    String token = obj.getString("token");

                                    try {
                                        String user_id = obj.getString("user_id");

                                        SharedPreferences sharedPreferences = mContext.getSharedPreferences("MySharedPref", MODE_PRIVATE);
                                        SharedPreferences.Editor myEdit = sharedPreferences.edit();
                                        myEdit.putString("user_id", user_id);
                                        myEdit.putString("VERIFIED_KEY", "VERIFIED_KEY");
                                        myEdit.putString("TOKEN_SF", token);

                                        myEdit.apply();
                                    } catch (Exception ignored) {
                                    }
                                    if (mContext != null) {
                                        Intent i = new Intent(mContext, ReferActivity.class);
                                        // String token =obj.getString("token");
                                        i.putExtra("tokenKey", token);
                                        i.putExtra("mobileKey", mobile);
                                        i.putExtra("account_typeKey", account_type);
                                        i.putExtra("nameKey", name);
//                                        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
//                                        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                        mContext.startActivity(i);

                                        ((Activity) mContext).finish();
                                    }


                                } else {
                                    String message = response.getString("message");
                                    Toast.makeText(mContext, message, Toast.LENGTH_SHORT).show();
                                }
                            } catch (JSONException e) {

                                e.printStackTrace();

                                Log.d("@@@ notSuccess: ", e.getMessage());
                            }
                            super.onSuccess(statusCode, headers, response);
                        }

                        @Override
                        public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {


                            super.onFailure(statusCode, headers, responseString, throwable);
                        }

                        @Override
                        public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {


                            super.onFailure(statusCode, headers, throwable, errorResponse);
                        }

                        @Override
                        public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONArray errorResponse) {


                            super.onFailure(statusCode, headers, throwable, errorResponse);
                        }
                    });
                }
            });
        } catch (Exception e) {


            e.printStackTrace();
        }
    }

    public static void update_user_profile_data_business(final Context mContext, String account_type, String business_name, String email, String mobile, String address, String website, String business_category, String doi, String business_detaills, String token) {
        try {

            final AsyncHttpClient client = new AsyncHttpClient();
            client.setSSLSocketFactory(MySSLSocketFactory.getFixedSocketFactory());
            final RequestParams request_param = new RequestParams();
            request_param.put("account_type", account_type);
            request_param.put("business_name", business_name);
            request_param.put("email", email);
            request_param.put("mobile", mobile);
            request_param.put("address", address);
            request_param.put("website", website);
            request_param.put("business_category", business_category);
            request_param.put("doi", doi);
            request_param.put("business_detaills", business_detaills);

            Log.d(TAG, "@@@update_user_profile_data_business :" + UPDATE_USER_PROFILE_DATA + "?" + request_param.toString());
            ((Activity) mContext).runOnUiThread(new Runnable() {
                public void run() {

                    client.addHeader("token", token);
                    client.post(UPDATE_USER_PROFILE_DATA, request_param, new JsonHttpResponseHandler() {
                        @Override
                        public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                            Log.d(TAG, "@@@update_user_profile_data_business_response :" + response.toString());

                            try {
                                int status = response.getInt("error_code");
                                if (status == 200) {
                                    String message = response.getString("message");
                                    Toast.makeText(mContext, message, Toast.LENGTH_SHORT).show();


                                    JSONObject obj = response.getJSONObject("data");
                                    String token = obj.getString("token");
                                    try {
                                        String user_id = obj.getString("user_id");

                                        SharedPreferences sharedPreferences = mContext.getSharedPreferences("MySharedPref", MODE_PRIVATE);
                                        SharedPreferences.Editor myEdit = sharedPreferences.edit();
                                        myEdit.putString("user_id", user_id);
                                        myEdit.putString("TOKEN_SF", token);
                                        myEdit.putString("VERIFIED_KEY", "VERIFIED_KEY");
                                        myEdit.apply();
                                    } catch (Exception ignored) {
                                    }


                                    if (mContext != null) {
                                        Intent i = new Intent(mContext, ReferActivity.class);
                                        // String token =obj.getString("token");
                                        i.putExtra("tokenKey", token);
                                        i.putExtra("mobileKey", mobile);
                                        i.putExtra("account_typeKey", account_type);
                                        i.putExtra("nameKey", business_name);
                                        i.putExtra("BusinessCatKey", business_category);
//                                        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
//                                        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                        mContext.startActivity(i);

                                        ((Activity) mContext).finish();
                                    }


                                } else {
                                    String message = response.getString("message");
                                    Toast.makeText(mContext, message, Toast.LENGTH_SHORT).show();
                                }
                            } catch (JSONException e) {

                                e.printStackTrace();

                                Log.d("@@@ notSuccess: ", e.getMessage());
                            }
                            super.onSuccess(statusCode, headers, response);
                        }

                        @Override
                        public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {


                            super.onFailure(statusCode, headers, responseString, throwable);
                        }

                        @Override
                        public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {


                            super.onFailure(statusCode, headers, throwable, errorResponse);
                        }

                        @Override
                        public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONArray errorResponse) {


                            super.onFailure(statusCode, headers, throwable, errorResponse);
                        }
                    });
                }
            });
        } catch (Exception e) {


            e.printStackTrace();
        }
    }


    public static void send_otp(final Context mContext, String type, String input_parameter, String reffral_code) {
        try {

            final AsyncHttpClient client = new AsyncHttpClient();
            client.setSSLSocketFactory(MySSLSocketFactory.getFixedSocketFactory());
            final RequestParams request_param = new RequestParams();
            request_param.put("type", type);
            request_param.put("input_parameter", input_parameter);
            request_param.put("reffral_code", reffral_code);


            Log.d(TAG, "@@@send_otp :" + SEND_OTP + "?" + request_param.toString());
            ((Activity) mContext).runOnUiThread(new Runnable() {
                public void run() {

                    client.post(SEND_OTP, request_param, new JsonHttpResponseHandler() {
                        @Override
                        public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                            Log.d(TAG, "@@@send_otp_response :" + response.toString());

                            try {
                                int status = response.getInt("error_code");
                                if (status == 200) {


                                    String message = response.getString("message");
                                    // Toast.makeText(mContext, message, Toast.LENGTH_SHORT).show();

                                    Log.d("resopnse", String.valueOf(response.getJSONObject("data")));


                                    //for get array from database
//                                       JSONArray arr = response.getJSONArray("data");
//                                      JSONObject obj = arr.getJSONObject(0);
//                                     String otp = obj.getString("otp");

                                    // for getting only object from database
                                    JSONObject obj = response.getJSONObject("data");
                                    String otp = obj.getString("otp");
                                    //   Toast.makeText(mContext, otp, Toast.LENGTH_SHORT).show();
                                    String token = obj.getString("token");
                                    Log.d("otp_new", otp);

                                    try {


                                        SharedPreferences sharedPreferences = mContext.getSharedPreferences("MySharedPref", MODE_PRIVATE);
                                        SharedPreferences.Editor myEdit = sharedPreferences.edit();
                                        myEdit.putString("otp", otp);
                                        myEdit.putString("TOKEN_SF", token);
                                        myEdit.apply();
                                    } catch (Exception ignored) {
                                    }


                                    if (mContext != null) {
                                        Intent intent = new Intent(mContext, MobileVerification.class);
//                                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
//                                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                        intent.putExtra("mobileKey", input_parameter);
                                        intent.putExtra("otpKey", otp);
                                        intent.putExtra("tokenKey", token);
                                        mContext.startActivity(intent);

                                        ((Activity) mContext).finish();
                                        ProgressDialog progressDialog = new ProgressDialog(mContext);
                                        progressDialog.dismiss();
                                    }


                                } else {
                                    String message = response.getString("message");
                                    Toast.makeText(mContext, message, Toast.LENGTH_SHORT).show();
                                }
                            } catch (JSONException e) {

                                e.printStackTrace();

                                Log.d("@@@ notSuccess: ", e.getMessage());
                            }
                            super.onSuccess(statusCode, headers, response);
                        }

                        @Override
                        public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {


                            super.onFailure(statusCode, headers, responseString, throwable);
                        }

                        @Override
                        public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {


                            super.onFailure(statusCode, headers, throwable, errorResponse);
                        }

                        @Override
                        public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONArray errorResponse) {


                            super.onFailure(statusCode, headers, throwable, errorResponse);
                        }
                    });
                }
            });
        } catch (Exception e) {


            e.printStackTrace();
        }
    }

    public static void verify_otp(final Context mContext, String type, String otp, String token, String mobileKey, AppCompatSeekBar sb) {
        try {

            final AsyncHttpClient client = new AsyncHttpClient();
            client.setSSLSocketFactory(MySSLSocketFactory.getFixedSocketFactory());
            final RequestParams request_param = new RequestParams();
            request_param.put("type", type);
            request_param.put("otp", otp);


            Log.d(TAG, "@@@verify_otp :" + VERIFY_OTP + "?" + request_param.toString());
            ((Activity) mContext).runOnUiThread(new Runnable() {
                public void run() {

                    client.addHeader("token", token);
                    client.post(VERIFY_OTP, request_param, new JsonHttpResponseHandler() {
                        @Override
                        public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                            Log.d(TAG, "@@@verify_otp_response :" + response.toString());

                            try {
                                int status = response.getInt("error_code");
                                if (status == 200) {
                                    String message = response.getString("message");


                                    Log.d("resopnse", String.valueOf(response.getJSONObject("data")));

                                    JSONObject obj = response.getJSONObject("data");
                                    String token2 = obj.getString("token");
                                    String is_profile_complete = obj.getString("is_profile_complete");

                                    try {
                                        String user_id = obj.getString("user_id");

                                        SharedPreferences sharedPreferences = mContext.getSharedPreferences("MySharedPref", MODE_PRIVATE);
                                        SharedPreferences.Editor myEdit = sharedPreferences.edit();
                                        myEdit.putString("user_id", user_id);
                                        myEdit.putString("otp", otp);

                                        myEdit.putString("TOKEN_SF", token2);
                                        myEdit.putString("social_media_type", "");
                                        myEdit.apply();
                                        //  Toast.makeText(mContext, mobileKey, Toast.LENGTH_SHORT).show();
                                    } catch (Exception ignored) {
                                    }


                                    if (is_profile_complete.equals("1")) {


                                        try {
                                            SharedPreferences sharedPreferences = mContext.getSharedPreferences("MySharedPref", MODE_PRIVATE);
                                            SharedPreferences.Editor myEdit = sharedPreferences.edit();
                                            myEdit.putString("VERIFIED_KEY", "VERIFIED_KEY");
                                            myEdit.apply();
                                        } catch (Exception ignored) {
                                        }


                                        Intent intent = new Intent(mContext, MainActivity.class);
                                        mContext.startActivity(intent);
                                    } else if (is_profile_complete.equals("0")) {

                                        try {
                                            String user_id = obj.getString("user_id");

                                            SharedPreferences sharedPreferences = mContext.getSharedPreferences("MySharedPref", MODE_PRIVATE);
                                            SharedPreferences.Editor myEdit = sharedPreferences.edit();
                                            myEdit.putString("user_id", user_id);
                                            myEdit.putString("otp", otp);

                                            myEdit.putString("TOKEN_SF", token2);
                                            myEdit.putString("social_media_type", "");
                                            myEdit.apply();
                                            //     Toast.makeText(mContext, mobileKey, Toast.LENGTH_SHORT).show();
                                        } catch (Exception ignored) {
                                        }


                                        if (mContext != null) {
                                            //Toast.makeText(mContext, message, Toast.LENGTH_SHORT).show();
                                            Intent intent = new Intent(mContext, choosePersonalBusiness.class);
                                            intent.putExtra("otpKey", otp);
                                            intent.putExtra("mobileKey", mobileKey);
                                            intent.putExtra("TOKEN_SF", token2);
                                            mContext.startActivity(intent);

                                            ((Activity) mContext).finish();
                                            ProgressDialog progressDialog = new ProgressDialog(mContext);
                                            progressDialog.dismiss();
                                        }
                                    }


                                } else {
                                    String message = response.getString("message");
                                    Toast.makeText(mContext, message, Toast.LENGTH_SHORT).show();
                                    sb.setProgress(0);

                                }
                            } catch (JSONException e) {

                                e.printStackTrace();

                                Log.d("@@@ notSuccess: ", e.getMessage());
                            }
                            super.onSuccess(statusCode, headers, response);
                        }

                        @Override
                        public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {


                            super.onFailure(statusCode, headers, responseString, throwable);
                        }

                        @Override
                        public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {


                            super.onFailure(statusCode, headers, throwable, errorResponse);
                        }

                        @Override
                        public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONArray errorResponse) {


                            super.onFailure(statusCode, headers, throwable, errorResponse);
                        }
                    });
                }
            });
        } catch (Exception e) {


            e.printStackTrace();
        }
    }

    public static void check_mobile_number(final Context mContext, String mobile) {
        try {

            final AsyncHttpClient client = new AsyncHttpClient();
            client.setSSLSocketFactory(MySSLSocketFactory.getFixedSocketFactory());
            final RequestParams request_param = new RequestParams();
            request_param.put("mobile", mobile);


            Log.d(TAG, "@@@check_mobile_number :" + CHECK_MOBILE + "?" + request_param.toString());
            ((Activity) mContext).runOnUiThread(new Runnable() {
                public void run() {


                    client.post(CHECK_MOBILE, request_param, new JsonHttpResponseHandler() {
                        @Override
                        public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                            Log.d(TAG, "@@@check_mobile_number_response :" + response.toString());

                            try {
                                int status = response.getInt("error_code");
                                if (status == 200) {
                                    String message = response.getString("message");

                                    Drawable customErrorDrawable = mContext.getResources().getDrawable(R.drawable.done);
                                    customErrorDrawable.setBounds(0, 0, customErrorDrawable.getIntrinsicWidth(), customErrorDrawable.getIntrinsicHeight());


                                    try {
                                        Personal.mobilePersonal.setError(message, customErrorDrawable);

                                    } catch (Exception ignored) {
                                    }

                                    try {
                                        Business.mobilePersonal.setError(message, customErrorDrawable);

                                    } catch (Exception ex) {
                                    }

                                    Log.d("check_email_id_resopnse", String.valueOf(response.getJSONObject("data")));


                                } else {
                                    String message = response.getString("message");
                                    Drawable customErrorDrawable = mContext.getResources().getDrawable(R.drawable.cancel);
                                    customErrorDrawable.setBounds(0, 0, customErrorDrawable.getIntrinsicWidth(), customErrorDrawable.getIntrinsicHeight());
                                    //here set error in edittext

                                    try {
                                        Personal.mobilePersonal.setError(message, customErrorDrawable);

                                    } catch (Exception ex) {
                                    }


                                    try {
                                        Business.mobilePersonal.setError(message, customErrorDrawable);
                                    } catch (Exception ignored) {
                                    }
                                    //  Toast.makeText(mContext, message, Toast.LENGTH_SHORT).show();
                                }
                            } catch (JSONException e) {

                                e.printStackTrace();

                                Log.d("@@@ notSuccess: ", e.getMessage());
                            }
                            super.onSuccess(statusCode, headers, response);
                        }

                        @Override
                        public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {


                            super.onFailure(statusCode, headers, responseString, throwable);
                        }

                        @Override
                        public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {


                            super.onFailure(statusCode, headers, throwable, errorResponse);
                        }

                        @Override
                        public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONArray errorResponse) {


                            super.onFailure(statusCode, headers, throwable, errorResponse);
                        }
                    });
                }
            });
        } catch (Exception e) {


            e.printStackTrace();
        }
    }

    public static void check_email_id(final Context mContext, String email) {

        try {

            final AsyncHttpClient client = new AsyncHttpClient();
            client.setSSLSocketFactory(MySSLSocketFactory.getFixedSocketFactory());
            final RequestParams request_param = new RequestParams();
            request_param.put("email", email);


            Log.d(TAG, "@@@check_email_id :" + CHECK_EMAIL_ID + "?" + request_param.toString());
            ((Activity) mContext).runOnUiThread(new Runnable() {
                public void run() {


                    client.post(CHECK_EMAIL_ID, request_param, new JsonHttpResponseHandler() {
                        @Override
                        public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                            Log.d(TAG, "@@@check_email_id_response :" + response.toString());

                            try {
                                int status = response.getInt("error_code");
                                if (status == 200) {
                                    String message = response.getString("message");

                                    Drawable customErrorDrawable = mContext.getResources().getDrawable(R.drawable.done);
                                    customErrorDrawable.setBounds(0, 0, customErrorDrawable.getIntrinsicWidth(), customErrorDrawable.getIntrinsicHeight());

                                    try {
                                        Personal.email.setError(message, customErrorDrawable);

                                    } catch (Exception ignored) {
                                    }

                                    try {
                                        Business.email.setError(message, customErrorDrawable);
                                    } catch (Exception ignored) {
                                    }

                                    Log.d("check_email_id_resopnse", String.valueOf(response.getJSONObject("data")));


                                } else {
                                    String message = response.getString("message");
                                    Drawable customErrorDrawable = mContext.getResources().getDrawable(R.drawable.cancel);
                                    customErrorDrawable.setBounds(0, 0, customErrorDrawable.getIntrinsicWidth(), customErrorDrawable.getIntrinsicHeight());
                                    //here set error in edittext

                                    try {
                                        Personal.email.setError(message, customErrorDrawable);

                                    } catch (Exception ignored) {
                                    }

                                    try {

                                        Business.email.setError(message, customErrorDrawable);
                                    } catch (Exception ignored) {
                                    }
                                    //  Toast.makeText(mContext, message, Toast.LENGTH_SHORT).show();
                                }
                            } catch (JSONException e) {

                                e.printStackTrace();

                                Log.d("@@@ notSuccess: ", e.getMessage());
                            }
                            super.onSuccess(statusCode, headers, response);
                        }

                        @Override
                        public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {


                            super.onFailure(statusCode, headers, responseString, throwable);
                        }

                        @Override
                        public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {


                            super.onFailure(statusCode, headers, throwable, errorResponse);
                        }

                        @Override
                        public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONArray errorResponse) {


                            super.onFailure(statusCode, headers, throwable, errorResponse);
                        }
                    });
                }
            });
        } catch (Exception e) {


            e.printStackTrace();
        }
    }

    public static void social_media_login_personal(final Context mContext, String social_media_type, String input_parameter, String reffral_code, String account_type, String name, String email, String mobile, String address, String dob, String poilitical_interest) {
        try {

            final AsyncHttpClient client = new AsyncHttpClient();
            client.setSSLSocketFactory(MySSLSocketFactory.getFixedSocketFactory());
            final RequestParams request_param = new RequestParams();
            //  request_param.put("type", type);
            request_param.put("social_media_type", social_media_type);
            request_param.put("input_parameter", input_parameter);
            request_param.put("reffral_code", reffral_code);
            request_param.put("account_type", account_type);
            request_param.put("name", name);
            request_param.put("email", email);
            request_param.put("mobile", mobile);
            request_param.put("address", address);
            request_param.put("dob", dob);
            request_param.put("poilitical_interest", poilitical_interest);


            Log.d(TAG, "@@@social_media_login_personal :" + SOCIAL_MEDIA_LOGIN_PERSONAL + "?" + request_param.toString());
            ((Activity) mContext).runOnUiThread(new Runnable() {
                public void run() {

                    client.post(SOCIAL_MEDIA_LOGIN_PERSONAL, request_param, new JsonHttpResponseHandler() {
                        @Override
                        public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                            Log.d(TAG, "@@@social_media_login_personal_response :" + response.toString());

                            try {
                                int status = response.getInt("error_code");
                                if (status == 200) {


                                    String message = response.getString("message");
                                    // Toast.makeText(mContext, message, Toast.LENGTH_SHORT).show();

                                    Log.d("resopnse", String.valueOf(response.getJSONObject("data")));


                                    // for getting only object from database
//                                    JSONObject obj = response.getJSONObject("data");
//                                    String otp = obj.getString("otp");
//                                    String token = obj.getString("token");
//                                    Log.d("array", otp);
                                    JSONObject obj = response.getJSONObject("data");
                                    String token = obj.getString("token");
                                    try {
                                        String user_id = obj.getString("user_id");

                                        SharedPreferences sharedPreferences = mContext.getSharedPreferences("MySharedPref", MODE_PRIVATE);
                                        SharedPreferences.Editor myEdit = sharedPreferences.edit();
                                        myEdit.putString("user_id", user_id);
                                        myEdit.putString("TOKEN_SF", token);
                                        myEdit.putString("VERIFIED_SOCIAL_MEDIA_KEY", "VERIFIED_SOCIAL_MEDIA_KEY");

                                        myEdit.apply();
                                    } catch (Exception ignored) {
                                    }

                                    if (mContext != null) {
                                        Intent intent = new Intent(mContext, ReferActivity.class);
                                        //  String token =obj.getString("token");
                                        intent.putExtra("tokenKey", token);
                                        intent.putExtra("social_media_typeKey", social_media_type);
                                        intent.putExtra("account_typeKey", account_type);
                                        intent.putExtra("emailKey", email);
                                        intent.putExtra("nameKey", name);


//                                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
//                                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
//                                        intent.putExtra("otpKey", otp);
//                                        intent.putExtra("tokenKey", token);
                                        mContext.startActivity(intent);


                                        ((Activity) mContext).finish();
                                        ProgressDialog progressDialog = new ProgressDialog(mContext);
                                        progressDialog.dismiss();
                                    }


                                } else {
                                    String message = response.getString("message");
                                    Toast.makeText(mContext, message, Toast.LENGTH_SHORT).show();
                                }
                            } catch (JSONException e) {

                                e.printStackTrace();

                                Log.d("@@@ notSuccess: ", e.getMessage());
                            }
                            super.onSuccess(statusCode, headers, response);
                        }

                        @Override
                        public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {


                            super.onFailure(statusCode, headers, responseString, throwable);
                        }

                        @Override
                        public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {


                            super.onFailure(statusCode, headers, throwable, errorResponse);
                        }

                        @Override
                        public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONArray errorResponse) {


                            super.onFailure(statusCode, headers, throwable, errorResponse);
                        }
                    });
                }
            });
        } catch (Exception e) {


            e.printStackTrace();
        }
    }


    public static void social_media_login_business(final Context mContext, String social_media_type, String input_parameter, String reffral_code, String account_type, String business_name, String email, String mobile, String address, String website, String business_category, String doi, String business_detaills, File imageFile) {
        try {

            final AsyncHttpClient client = new AsyncHttpClient();
            client.setSSLSocketFactory(MySSLSocketFactory.getFixedSocketFactory());
            final RequestParams request_param = new RequestParams();
            //  request_param.put("type", type);
            request_param.put("social_media_type", social_media_type);
            request_param.put("input_parameter", input_parameter);
            request_param.put("reffral_code", reffral_code);
            request_param.put("account_type", account_type);
            request_param.put("business_name", business_name);
            request_param.put("email", email);
            request_param.put("mobile", mobile);
            request_param.put("address", address);
            request_param.put("website", website);
            request_param.put("business_category", business_category);
            request_param.put("doi", doi);
            request_param.put("business_detaills", business_detaills);

            if (imageFile != null) {
                request_param.put("image", imageFile);
            }


            Log.d(TAG, "@@@social_media_login_business :" + SOCIAL_MEDIA_LOGIN_BUSINESS + "?" + request_param.toString());
            ((Activity) mContext).runOnUiThread(new Runnable() {
                public void run() {

                    client.post(SOCIAL_MEDIA_LOGIN_BUSINESS, request_param, new JsonHttpResponseHandler() {
                        @Override
                        public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                            Log.d(TAG, "@@@social_media_login_business_response :" + response.toString());

                            try {
                                int status = response.getInt("error_code");
                                if (status == 200) {


                                    String message = response.getString("message");
                                    //   Toast.makeText(mContext, message, Toast.LENGTH_SHORT).show();

                                    Log.d("resopnse", String.valueOf(response.getJSONObject("data")));
                                    JSONObject obj = response.getJSONObject("data");
                                    String token = obj.getString("token");

                                    // for getting only object from database
//                                    JSONObject obj = response.getJSONObject("data");
//                                    String otp = obj.getString("otp");
//                                    String token = obj.getString("token");
//                                    Log.d("array", otp);
                                    try {

                                        String user_id = obj.getString("user_id");

                                        SharedPreferences sharedPreferences = mContext.getSharedPreferences("MySharedPref", MODE_PRIVATE);
                                        SharedPreferences.Editor myEdit = sharedPreferences.edit();
                                        myEdit.putString("user_id", user_id);
                                        myEdit.putString("VERIFIED_SOCIAL_MEDIA_KEY", "VERIFIED_SOCIAL_MEDIA_KEY");
                                        myEdit.putString("TOKEN_SF", token);
                                        myEdit.apply();
                                    } catch (Exception ignored) {
                                    }

                                    if (mContext != null) {
                                        Intent intent = new Intent(mContext, ReferActivity.class);
                                        //  String token =obj.getString("token");
                                        intent.putExtra("tokenKey", token);
                                        intent.putExtra("account_typeKey", account_type);
                                        intent.putExtra("emailKey", email);
                                        intent.putExtra("nameKey", business_name);
                                        intent.putExtra("BusinessCatKey", business_category);
//                                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
//                                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
//                                        intent.putExtra("otpKey", otp);
//                                        intent.putExtra("tokenKey", token);
                                        mContext.startActivity(intent);

                                        ((Activity) mContext).finish();
                                        ProgressDialog progressDialog = new ProgressDialog(mContext);
                                        progressDialog.dismiss();
                                    }


                                } else {
                                    String message = response.getString("message");
                                    Toast.makeText(mContext, message, Toast.LENGTH_SHORT).show();
                                }
                            } catch (JSONException e) {

                                e.printStackTrace();

                                Log.d("@@@ notSuccess: ", e.getMessage());
                            }
                            super.onSuccess(statusCode, headers, response);
                        }

                        @Override
                        public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {


                            super.onFailure(statusCode, headers, responseString, throwable);
                        }

                        @Override
                        public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {


                            super.onFailure(statusCode, headers, throwable, errorResponse);
                        }

                        @Override
                        public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONArray errorResponse) {


                            super.onFailure(statusCode, headers, throwable, errorResponse);
                        }
                    });
                }
            });
        } catch (Exception e) {


            e.printStackTrace();
        }
    }

    public static void upload_user_contact_list(final Context mContext, String token, String user_contacts, String account_typeKey, String mobile, String emailKey, String name, String BusinessCatKey, String social_media_typeKey) {
        try {
            final AsyncHttpClient client = new AsyncHttpClient();
            client.setConnectTimeout(30000); // 30 seconds
            client.setResponseTimeout(30000);
            client.setSSLSocketFactory(MySSLSocketFactory.getFixedSocketFactory());
            final RequestParams request_param = new RequestParams();
            request_param.put("user_contacts", user_contacts);
            Log.d("test&&", user_contacts);

            Log.d(TAG, "@@@upload_user_contact_list :" + UPLOAD_USER_CONTACT_LIST + "?" + request_param.toString());
            ((Activity) mContext).runOnUiThread(new Runnable() {
                public void run() {

                    client.addHeader("token", token);
                    client.post(UPLOAD_USER_CONTACT_LIST, request_param, new JsonHttpResponseHandler() {
                        @Override
                        public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                            Log.d(TAG, "@@@upload_user_contact_list_response :" + response.toString());

                            try {
                                int status = response.getInt("error_code");
                                if (status == 200) {


                                    String message = response.getString("message");
                                    Toast.makeText(mContext, message, Toast.LENGTH_SHORT).show();


                                    if (mContext != null) {


                                        Intent intent = new Intent(mContext, MainActivity.class);
                                        intent.putExtra("account_typeKey", account_typeKey);
                                        intent.putExtra("mobileKey", mobile);
                                        intent.putExtra("emailKey", emailKey);
                                        intent.putExtra("nameKey", name);
                                        intent.putExtra("BusinessCatKey", BusinessCatKey);
                                        intent.putExtra("social_media_typeKey", social_media_typeKey);
                                        intent.putExtra("tokenKey", token);


                                        mContext.startActivity(intent);

                                        //((Activity) mContext).finish();
                                        ReferActivity.dialog.dismiss();

                                    }


                                } else {
                                    String message = response.getString("message");
                                    Toast.makeText(mContext, message, Toast.LENGTH_SHORT).show();
                                }
                            } catch (JSONException e) {

                                e.printStackTrace();

                                Log.d("@@@ notSuccess: ", e.getMessage());
                            }
                            super.onSuccess(statusCode, headers, response);
                        }

                        @Override
                        public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {

                            //
                            // Log.w("responseString", responseString);
                            super.onFailure(statusCode, headers, responseString, throwable);
                        }

                        @Override
                        public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {

                            try {
                                //   Toast.makeText(mContext, errorResponse.toString(), Toast.LENGTH_SHORT).show();
                                // Log.w("responseString", errorResponse.toString());
                            } catch (Exception ex) {
                                // Log.d("JSONObject.toString()'", errorResponse.toString());
                            }

                            super.onFailure(statusCode, headers, throwable, errorResponse);
                        }

                        @Override
                        public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONArray errorResponse) {


                            //   Log.w("responseString", errorResponse.toString());

                            super.onFailure(statusCode, headers, throwable, errorResponse);
                        }
                    });
                }
            });
        } catch (Exception e) {

            //


            e.printStackTrace();
        }
    }

    public static void fetch_user_profile(final Context mContext, String token) {

        try {

            final AsyncHttpClient client = new AsyncHttpClient();
            client.setSSLSocketFactory(MySSLSocketFactory.getFixedSocketFactory());
            final RequestParams request_param = new RequestParams();


            Log.d(TAG, "@@@fetch_user_profile :" + FETCH_USER_PROFILE + "?" + request_param.toString());
            ((Activity) mContext).runOnUiThread(new Runnable() {
                public void run() {


                    client.addHeader("token", token);
                    client.post(FETCH_USER_PROFILE, request_param, new JsonHttpResponseHandler() {
                        @Override
                        public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                            Log.d(TAG, "@@@fetch_user_profile_response :" + response.toString());

                            try {
                                int status = response.getInt("error_code");
                                if (status == 200) {
                                    String message = response.getString("message");
                                    //      Toast.makeText(mContext, message, Toast.LENGTH_SHORT).show();

                                    //main code type here

                                    JSONObject obj = response.getJSONObject("data");
                                    String account_type = obj.getString("account_type");

                                    //for business
                                    String name = obj.getString("name");

                                    String email_id = obj.getString("email_id");
                                    String mobile_number = obj.getString("mobile_number");
                                    String dob = obj.getString("dob");
                                    String political_interest = obj.getString("political_interest");

                                    //tbis is for business account details
                                    String bussiness_name = obj.getString("bussiness_name");
                                    String website = obj.getString("website");
                                    String business_category = obj.getString("business_category");
                                    String date_of_incorporation = obj.getString("date_of_incorporation");
                                    String business_details = obj.getString("business_details");


                                    if (account_type.equals("1")) {
                                        //this is a personal
                                        //    Toast.makeText(mContext, account_type, Toast.LENGTH_SHORT).show();
                                        profileFragment.userNameId.setText(name);
                                        profileFragment.subId.setText(mobile_number);

                                        if (mContext != null) {
                                            Intent intentPersonal = new Intent(mContext, PersonalProfile.class);
                                            intentPersonal.putExtra("name", name);
                                            intentPersonal.putExtra("token", token);
                                            intentPersonal.putExtra("account_type", account_type);
                                            intentPersonal.putExtra("email_id", email_id);
                                            intentPersonal.putExtra("mobile_number", mobile_number);
                                            intentPersonal.putExtra("dob", dob);
                                            intentPersonal.putExtra("political_interest", political_interest);
                                            mContext.startActivity(intentPersonal);
                                            ((Activity) mContext).finish();
                                        }

                                    } else if (account_type.equals("2")) {

                                        // This is a business
                                        //   Toast.makeText(mContext, account_type, Toast.LENGTH_SHORT).show();
                                        profileFragment.userNameId.setText(bussiness_name);
                                        profileFragment.subId.setText(business_category);
                                        if (mContext != null) {
                                            Intent intentBusiness = new Intent(mContext, BusinessProfile.class);
                                            intentBusiness.putExtra("bussiness_name", bussiness_name);
                                            intentBusiness.putExtra("token", token);
                                            intentBusiness.putExtra("email_id", email_id);
                                            intentBusiness.putExtra("mobile_number", mobile_number);
                                            intentBusiness.putExtra("account_type", account_type);
                                            intentBusiness.putExtra("website", website);
                                            intentBusiness.putExtra("business_category", business_category);
                                            intentBusiness.putExtra("date_of_incorporation", date_of_incorporation);
                                            intentBusiness.putExtra("business_details", business_details);
                                            mContext.startActivity(intentBusiness);
                                            ((Activity) mContext).finish();
                                        }

                                    }

                                } else {
                                    String message = response.getString("message");
                                    Toast.makeText(mContext, message, Toast.LENGTH_SHORT).show();
                                }
                            } catch (JSONException e) {

                                e.printStackTrace();

                                Log.d("@@@ notSuccess: ", e.getMessage());
                            }
                            super.onSuccess(statusCode, headers, response);
                        }

                        @Override
                        public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {


                            super.onFailure(statusCode, headers, responseString, throwable);
                        }

                        @Override
                        public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {


                            super.onFailure(statusCode, headers, throwable, errorResponse);
                        }

                        @Override
                        public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONArray errorResponse) {


                            super.onFailure(statusCode, headers, throwable, errorResponse);
                        }
                    });
                }
            });
        } catch (Exception e) {


            e.printStackTrace();
        }
    }


    public static void social_media_login_FOR_CHECK(final Context mContext, String social_media_type, String input_parameter, String token) {
        try {

            final AsyncHttpClient client = new AsyncHttpClient();
            client.setSSLSocketFactory(MySSLSocketFactory.getFixedSocketFactory());
            final RequestParams request_param = new RequestParams();
            //  request_param.put("type", type);
            request_param.put("social_media_type", social_media_type);
            request_param.put("input_parameter", input_parameter);


            Log.d(TAG, "@@@social_media_login_FOR_CHECK :" + SOCIAL_MEDIA_LOGIN_PERSONAL + "?" + request_param.toString());
            ((Activity) mContext).runOnUiThread(new Runnable() {
                public void run() {

                    client.post(SOCIAL_MEDIA_LOGIN_PERSONAL, request_param, new JsonHttpResponseHandler() {
                        @Override
                        public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                            Log.d(TAG, "@@@social_media_login_FOR_CHECK_response :" + response.toString());

                            try {
                                int status = response.getInt("error_code");

                                if (status == 202) {

                                    try {

                                        JSONObject obj = response.getJSONObject("data");
                                        String token2 = obj.getString("token");

                                        SharedPreferences sharedPreferences = mContext.getSharedPreferences("MySharedPref", MODE_PRIVATE);
                                        SharedPreferences.Editor myEdit = sharedPreferences.edit();
                                        myEdit.putString("otp", "");
                                        myEdit.putString("TOKEN_SF", token2);
                                        myEdit.apply();
                                    } catch (Exception ex) {
                                    }


                                    if (mContext != null) {
                                        Intent intent = new Intent(mContext, choosePersonalBusiness.class);
                                        intent.putExtra("social_media_typeKey", social_media_type);
                                        intent.putExtra("input_parameterKey", input_parameter);
                                        mContext.startActivity(intent);
                                        ((Activity) mContext).finish();
                                        ProgressDialog progressDialog = new ProgressDialog(mContext);
                                        progressDialog.dismiss();
                                    }


                                } else if (status == 200) {

                                    try {

                                        JSONObject obj = response.getJSONObject("data");
                                        String token2 = obj.getString("token");


                                        // Storing data into SharedPreferences
                                        SharedPreferences sharedPreferences = mContext.getSharedPreferences("MySharedPref", MODE_PRIVATE);
                                        SharedPreferences.Editor myEdit = sharedPreferences.edit();
                                        myEdit.putString("otp", "");
                                        myEdit.putString("TOKEN_SF", token2);
                                        myEdit.putString("VERIFIED_SOCIAL_MEDIA_KEY", "VERIFIED_SOCIAL_MEDIA_KEY");
                                        myEdit.putString("VERIFIED_SOCIAL_MEDIA_TYPE", social_media_type);
                                        myEdit.putString("VERIFIED_INPUT_PARAMETER", input_parameter);
                                        myEdit.apply();
                                    } catch (Exception ex) {
                                    }


                                    // chooseActivity to mainactivity

                                    if (mContext != null) {
                                        Intent intent = new Intent(mContext, MainActivity.class);
                                        mContext.startActivity(intent);
                                        ((Activity) mContext).finish();
                                        intent.putExtra("social_media_typeKey", social_media_type);
                                        intent.putExtra("input_parameterKey", input_parameter);
                                        ProgressDialog progressDialog = new ProgressDialog(mContext);
                                        progressDialog.dismiss();
                                    }


                                }
                            } catch (JSONException e) {

                                e.printStackTrace();

                                Log.d("@@@ notSuccess: ", e.getMessage());
                            }
                            super.onSuccess(statusCode, headers, response);
                        }

                        @Override
                        public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {


                            super.onFailure(statusCode, headers, responseString, throwable);
                        }

                        @Override
                        public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {


                            super.onFailure(statusCode, headers, throwable, errorResponse);
                        }

                        @Override
                        public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONArray errorResponse) {


                            super.onFailure(statusCode, headers, throwable, errorResponse);
                        }
                    });
                }
            });
        } catch (Exception e) {


            e.printStackTrace();
        }
    }

    public static void fetch_user_profile_SETDATA(final Context mContext, String token) {

        try {

            final AsyncHttpClient client = new AsyncHttpClient();
            client.setSSLSocketFactory(MySSLSocketFactory.getFixedSocketFactory());
            final RequestParams request_param = new RequestParams();


            profileFragment.shimmerFrameLayout.startShimmer();
            Log.d(TAG, "@@@fetch_user_profile :" + FETCH_USER_PROFILE + "?" + request_param.toString());
            ((Activity) mContext).runOnUiThread(new Runnable() {
                public void run() {


                    client.addHeader("token", token);
                    client.post(FETCH_USER_PROFILE, request_param, new JsonHttpResponseHandler() {
                        @Override
                        public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                            Log.d(TAG, "@@@fetch_user_profile_response :" + response.toString());

                            try {
                                int status = response.getInt("error_code");
                                if (status == 200) {
                                    String message = response.getString("message");
                                    //    Toast.makeText(mContext, message, Toast.LENGTH_SHORT).show();

                                    //main code type here

                                    JSONObject obj = response.getJSONObject("data");
                                    String account_type = obj.getString("account_type");

                                    //for business
                                    String name = obj.getString("name");
                                    String address = obj.getString("address");
                                    String profile_pic = obj.getString("profile_pic");
                                    Log.d(TAG, "onSuccess: " + " " + profile_pic);

                                    String email_id = obj.getString("email_id");
                                    String mobile_number = obj.getString("mobile_number");
                                    String dob = obj.getString("dob");
                                    String political_interest = obj.getString("political_interest");


                                    if (account_type.equals("1")) {
                                        //this is a personal

                                        try {
                                            profileFragment.userNameId.setText(name);
                                            profileFragment.subId.setText(mobile_number);
                                            Picasso.get().load(profile_pic).placeholder(R.drawable.eclips).into(profileFragment.imageViewProfile);

                                        } catch (Exception ignored) {
                                        }


                                        try {
                                            settingScreen.userNameId.setText(name);
                                            settingScreen.subId.setText(mobile_number);
                                            Picasso.get().load(profile_pic).placeholder(R.drawable.eclips).into(settingScreen.profileId);
                                        } catch (Exception ignored) {
                                        }

                                        try {
                                            EditFrameOwner.profileBasic.setVisibility(View.VISIBLE);
                                            EditFrameOwner.emailBasic.setVisibility(View.VISIBLE);
                                            EditFrameOwner.addressBasic.setVisibility(View.VISIBLE);
                                            EditFrameOwner.nameBasic.setVisibility(View.VISIBLE);
                                            EditFrameOwner.phoneBasic.setVisibility(View.VISIBLE);

                                            EditFrameOwner.nameText.setText(name);
                                            EditFrameOwner.PhoneText.setText(mobile_number);
                                            EditFrameOwner.addressText.setText(address);
                                            EditFrameOwner.emailText.setText(email_id);
                                            //    EditFrameOwner.webText.setText(website);
                                            EditFrameOwner.webLyt.setVisibility(View.GONE);
                                            Picasso.get().load(profile_pic).placeholder(R.drawable.eclips).into(EditFrameOwner.profileImg);

                                        } catch (Exception ignored) {
                                        }


                                    } else if (account_type.equals("2")) {

                                        // This is a business
                                        //tbis is for business account details
                                        String bussiness_name = obj.getString("bussiness_name");
                                        String website = obj.getString("website");
                                        String business_category = obj.getString("business_category");
                                        String date_of_incorporation = obj.getString("date_of_incorporation");
                                        String business_details = obj.getString("business_details");


                                        try {
                                            profileFragment.userNameId.setText(bussiness_name);
                                            profileFragment.subId.setText(business_category);

                                            Picasso.get().load(profile_pic).placeholder(R.drawable.eclips).into(profileFragment.imageViewProfile);

                                        } catch (Exception ignored) {
                                        }


                                        try {
                                            settingScreen.userNameId.setText(bussiness_name);
                                            settingScreen.subId.setText(business_category);
                                            Picasso.get().load(profile_pic).placeholder(R.drawable.eclips).into(settingScreen.profileId);
                                        } catch (Exception ignored) {
                                        }

                                        try {
                                            EditFrameOwner.webLyt.setVisibility(View.VISIBLE);
                                            EditFrameOwner.profileBasic.setVisibility(View.VISIBLE);
                                            EditFrameOwner.emailBasic.setVisibility(View.VISIBLE);
                                            EditFrameOwner.addressBasic.setVisibility(View.VISIBLE);
                                            EditFrameOwner.nameBasic.setVisibility(View.VISIBLE);
                                            EditFrameOwner.phoneBasic.setVisibility(View.VISIBLE);

                                            EditFrameOwner.nameText.setText(bussiness_name);
                                            EditFrameOwner.PhoneText.setText(mobile_number);
                                            EditFrameOwner.addressText.setText(address);
                                            EditFrameOwner.emailText.setText(email_id);
                                            EditFrameOwner.webText.setText(website);
                                            Picasso.get().load(profile_pic).placeholder(R.drawable.eclips).into(EditFrameOwner.profileImg);
                                        } catch (Exception ignored) {
                                        }


                                    }

                                    profileFragment.shimmerFrameLayout.stopShimmer();
                                    profileFragment.shimmerFrameLayout.setVisibility(View.GONE);
                                    profileFragment.profileLinearId.setVisibility(View.VISIBLE);

                                } else {
                                    String message = response.getString("message");
                                    // Toast.makeText(mContext, message, Toast.LENGTH_SHORT).show();
                                }
                            } catch (JSONException e) {

                                e.printStackTrace();

                                Log.d("@@@ notSuccess: ", e.getMessage());
                            }
                            super.onSuccess(statusCode, headers, response);
                        }

                        @Override
                        public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {


                            super.onFailure(statusCode, headers, responseString, throwable);
                        }

                        @Override
                        public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {


                            super.onFailure(statusCode, headers, throwable, errorResponse);
                        }

                        @Override
                        public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONArray errorResponse) {


                            super.onFailure(statusCode, headers, throwable, errorResponse);
                        }
                    });
                }
            });
        } catch (Exception e) {

            //


            e.printStackTrace();
        }
    }


    public static void update_user_profile_data_personal_main(final Context mContext, String account_type, String name, String email, String mobile, String address, String dob, String poilitical_interest, String token, File img) {
        try {

            final AsyncHttpClient client = new AsyncHttpClient();
            client.setSSLSocketFactory(MySSLSocketFactory.getFixedSocketFactory());
            final RequestParams request_param = new RequestParams();

            PersonalProfile.progressBar.setVisibility(View.VISIBLE);
            request_param.put("account_type", account_type);
            request_param.put("name", name);
            request_param.put("email", email);
            request_param.put("mobile", mobile);
            request_param.put("address", address);
            request_param.put("dob", dob);
            request_param.put("poilitical_interest", poilitical_interest);

            if (img != null) {
                request_param.put("image", img);
            }


            Log.d(TAG, "@@@update_user_profile_data_personal :" + UPDATE_USER_PROFILE_DATA + "?" + request_param.toString());
            ((Activity) mContext).runOnUiThread(new Runnable() {
                public void run() {

                    client.addHeader("token", token);
                    client.post(UPDATE_USER_PROFILE_DATA, request_param, new JsonHttpResponseHandler() {
                        @Override
                        public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                            Log.d(TAG, "@@@update_user_profile_data_personal_response :" + response.toString());

                            try {
                                int status = response.getInt("error_code");
                                if (status == 200) {

                                    String message = response.getString("message");
                                    Toast.makeText(mContext, message, Toast.LENGTH_SHORT).show();

                                    JSONObject obj = response.getJSONObject("data");
                                    String token = obj.getString("token");

                                    try {
                                        String user_id = obj.getString("user_id");

                                        SharedPreferences sharedPreferences = mContext.getSharedPreferences("MySharedPref", MODE_PRIVATE);
                                        SharedPreferences.Editor myEdit = sharedPreferences.edit();
                                        myEdit.putString("user_id", user_id);
                                        myEdit.putString("VERIFIED_KEY", "VERIFIED_KEY");
                                        myEdit.putString("TOKEN_SF", token);

                                        myEdit.apply();

                                    } catch (Exception ignored) {
                                    }

                                    PersonalProfile.progressBar.setVisibility(View.INVISIBLE);

                                } else {
                                    String message = response.getString("message");
                                    Toast.makeText(mContext, message, Toast.LENGTH_SHORT).show();
                                }
                            } catch (JSONException e) {

                                e.printStackTrace();

                                Log.d("@@@ notSuccess: ", e.getMessage());
                            }
                            super.onSuccess(statusCode, headers, response);
                        }

                        @Override
                        public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {


                            super.onFailure(statusCode, headers, responseString, throwable);
                        }

                        @Override
                        public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {


                            super.onFailure(statusCode, headers, throwable, errorResponse);
                        }

                        @Override
                        public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONArray errorResponse) {


                            super.onFailure(statusCode, headers, throwable, errorResponse);
                        }
                    });
                }
            });
        } catch (Exception e) {


            e.printStackTrace();
        }
    }


    public static void update_user_profile_data_business_Main(final Context mContext, String account_type, String business_name, String email, String mobile, String address, String website, String business_category, String doi, String business_detaills, String token, File image) {
        try {

            final AsyncHttpClient client = new AsyncHttpClient();
            client.setSSLSocketFactory(MySSLSocketFactory.getFixedSocketFactory());
            final RequestParams request_param = new RequestParams();
            request_param.put("account_type", account_type);
            request_param.put("business_name", business_name);
            request_param.put("email", email);
            request_param.put("mobile", mobile);
            request_param.put("address", address);
            request_param.put("website", website);
            request_param.put("business_category", business_category);
            request_param.put("doi", doi);
            request_param.put("business_detaills", business_detaills);

            if (image != null) {
                request_param.put("image", image);
            }


            Log.d(TAG, "@@@update_user_profile_data_business :" + UPDATE_USER_PROFILE_DATA + "?" + request_param.toString());
            ((Activity) mContext).runOnUiThread(new Runnable() {
                public void run() {

                    client.addHeader("token", token);
                    client.post(UPDATE_USER_PROFILE_DATA, request_param, new JsonHttpResponseHandler() {
                        @Override
                        public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                            Log.d(TAG, "@@@update_user_profile_data_business_response :" + response.toString());

                            try {
                                int status = response.getInt("error_code");
                                if (status == 200) {
                                    String message = response.getString("message");
                                    Toast.makeText(mContext, message, Toast.LENGTH_SHORT).show();


                                    JSONObject obj = response.getJSONObject("data");
                                    String token = obj.getString("token");
                                    try {
                                        String user_id = obj.getString("user_id");

                                        SharedPreferences sharedPreferences = mContext.getSharedPreferences("MySharedPref", MODE_PRIVATE);
                                        SharedPreferences.Editor myEdit = sharedPreferences.edit();
                                        myEdit.putString("user_id", user_id);
                                        myEdit.putString("TOKEN_SF", token);
                                        myEdit.putString("VERIFIED_KEY", "VERIFIED_KEY");
                                        myEdit.apply();
                                    } catch (Exception ignored) {
                                    }


                                } else {
                                    String message = response.getString("message");
                                    Toast.makeText(mContext, message, Toast.LENGTH_SHORT).show();
                                }
                            } catch (JSONException e) {

                                e.printStackTrace();

                                Log.d("@@@ notSuccess: ", e.getMessage());
                            }
                            super.onSuccess(statusCode, headers, response);
                        }

                        @Override
                        public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {


                            super.onFailure(statusCode, headers, responseString, throwable);
                        }

                        @Override
                        public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {


                            super.onFailure(statusCode, headers, throwable, errorResponse);
                        }

                        @Override
                        public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONArray errorResponse) {


                            super.onFailure(statusCode, headers, throwable, errorResponse);
                        }
                    });
                }
            });
        } catch (Exception e) {


            e.printStackTrace();
        }
    }


    public static void fetchDataProfile(final Context mContext, String token) {

        try {

            final AsyncHttpClient client = new AsyncHttpClient();
            client.setSSLSocketFactory(MySSLSocketFactory.getFixedSocketFactory());
            final RequestParams request_param = new RequestParams();


            Log.d(TAG, "@@@fetch_user_profile :" + FETCH_USER_PROFILE + "?" + request_param.toString());
            ((Activity) mContext).runOnUiThread(new Runnable() {
                public void run() {


                    client.addHeader("token", token);
                    client.post(FETCH_USER_PROFILE, request_param, new JsonHttpResponseHandler() {
                        @Override
                        public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                            Log.d(TAG, "@@@fetch_user_profile_response :" + response.toString());

                            try {
                                int status = response.getInt("error_code");
                                if (status == 200) {
                                    String message = response.getString("message");
                                    //    Toast.makeText(mContext, message, Toast.LENGTH_SHORT).show();

                                    //main code type here

                                    JSONObject obj = response.getJSONObject("data");
                                    String account_type = obj.getString("account_type");

                                    Constant.setSfFunction(mContext);
                                    Constant.setSF.putString(Constant.ACC_TYPE, account_type);
                                    Constant.setSF.apply();


                                    //for business
                                    String name = obj.getString("name");
                                    String profile_pic = obj.getString("profile_pic");

                                    try {
                                        mainFragment.getImgUrl(profile_pic);
                                    } catch (Exception ex) {
                                    }


                                    Log.d("profile_pic_new", "https://www.adpanda.in/api/" + profile_pic);

                                    String email_id = obj.getString("email_id");
                                    String mobile_number = obj.getString("mobile_number");
                                    String dob = obj.getString("dob");
                                    String political_interest = obj.getString("political_interest");

                                    //tbis is for business account details
                                    String bussiness_name = obj.getString("bussiness_name");
                                    String website = obj.getString("website");
                                    String business_category = obj.getString("business_category");
                                    String date_of_incorporation = obj.getString("date_of_incorporation");
                                    String business_details = obj.getString("business_details");


                                    if (account_type.equals("1")) {


                                        try {
                                            mainFragment.username.setText(name);
                                        } catch (Exception ex) {
                                        }


                                        //   Picasso.get().load(profile_pic).placeholder(R.drawable.group2).into(PersonalProfile.imageView);
                                        try {
                                            Picasso.get().load(profile_pic).placeholder(R.drawable.profilelogo).into(mainFragment.mainImageview);

                                        } catch (Exception ex) {
                                        }
                                        try {
                                            Picasso.get().load(profile_pic).placeholder(R.drawable.group2).into(PersonalProfile.imageView);
                                            //   mainFragment.loadImgUrlProf.setText(profile_pic);
                                        } catch (Exception ex) {
                                        }


                                        Log.d("@name1", name);


                                    } else if (account_type.equals("2")) {

                                        try {
                                            mainFragment.username.setText(bussiness_name);
                                        } catch (Exception ex) {
                                        }


                                        try {
                                            Picasso.get().load(profile_pic).placeholder(R.drawable.profilelogo).into(mainFragment.mainImageview);
                                        } catch (Exception ex) {
                                        }
                                        try {
                                            Picasso.get().load(profile_pic).placeholder(R.drawable.groupbisines).into(BusinessProfile.imageViewBusiness);
                                        } catch (Exception ex) {
                                        }


                                        Log.d("@name2", bussiness_name);


                                    }

                                } else {
                                    String message = response.getString("message");
                                    // Toast.makeText(mContext, message, Toast.LENGTH_SHORT).show();
                                }
                            } catch (JSONException e) {

                                e.printStackTrace();

                                Log.d("@@@ notSuccess: ", e.getMessage());
                            }
                            super.onSuccess(statusCode, headers, response);
                        }

                        @Override
                        public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {


                            super.onFailure(statusCode, headers, responseString, throwable);
                        }

                        @Override
                        public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {


                            super.onFailure(statusCode, headers, throwable, errorResponse);
                        }

                        @Override
                        public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONArray errorResponse) {


                            super.onFailure(statusCode, headers, throwable, errorResponse);
                        }
                    });
                }
            });
        } catch (Exception e) {


            e.printStackTrace();
        }
    }


    public static void get_category_list_All(final Context mContext, String token, mainFragment mainFragment) {

        try {


            final AsyncHttpClient client = new AsyncHttpClient();
            client.setSSLSocketFactory(MySSLSocketFactory.getFixedSocketFactory());
            final RequestParams request_param = new RequestParams();


            Log.d(TAG, "@@@GET_CATEGORY_LIST :" + get_category_list_All + "?" + request_param.toString());
            ((Activity) mContext).runOnUiThread(new Runnable() {
                public void run() {

                    String fcm_token;
                    com.Appzia.addpanda.Fragments.mainFragment.parentModelArrayList.clear();
                    com.Appzia.addpanda.Fragments.mainFragment.list1.clear();
                    Constant.mainCatBtnModelList.clear();
                    Constant.categoryNames.clear();
                    //  com.Appzia.addpanda.Fragments.mainFragment.progressBar.setVisibility(View.VISIBLE);
                    com.Appzia.addpanda.Fragments.mainFragment.shimmerFrameLayout.startShimmer();
                    client.addHeader("token", token);


                    client.post(get_category_list_All, request_param, new JsonHttpResponseHandler() {
                        @Override
                        public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                            Log.d(TAG, "@@@GET_CATEGORY_LIST_response :" + response.toString());


                            // internet is available
                            try {
                                int status = response.getInt("error_code");


                                if (status == 200) {


                                    JSONArray arr = response.getJSONArray("data");


                                    for (int i = 0; i < arr.length(); i++) {
                                        JSONObject obj2 = arr.getJSONObject(i);
                                        String category_name = obj2.getString("category_name");

                                        String category_image = obj2.getString("category_image");
                                        String category_id = obj2.getString("category_id");
                                        String category_type_flag = obj2.getString("category_type_flag");
                                        Log.d("@category_type_flag", category_type_flag);


                                        JSONArray sub_category_list = obj2.getJSONArray("sub_category_list");
                                        Log.d("@@@category_nameZZ", String.valueOf(category_name));


                                        String sub_cat_name = null;
                                        String sub_cat_id = null;
                                        String image = null;

                                        JSONObject obj3;
                                        Log.d("@@catid", String.valueOf(category_id));


                                        //this is for filter account types 1 or 2

                                        Constant.getSfFuncion(mContext);
                                        String accountType = Constant.getSF.getString(Constant.ACC_TYPE, "");


                                        //parent
                                        Constant.categoryNames.add(category_name);


                                        if (accountType.equals("1") && category_type_flag.equals("1")) {
                                            Constant.mainCatBtnModelList.add(new mainCatBtnChildModel(category_name, category_id));

                                            for (int j = 0; j < sub_category_list.length(); j++) {
                                                obj3 = sub_category_list.getJSONObject(j);

                                                sub_cat_name = obj3.getString("sub_cat_name");
                                                sub_cat_id = obj3.getString("sub_cat_id");
                                                image = obj3.getString("image");


                                                if (category_id.equals("1")) {

                                                    com.Appzia.addpanda.Fragments.mainFragment.list1.add(new categoryChildModel(image, sub_cat_name, category_id, category_image, sub_cat_id, category_name));
                                                    //     com.Appzia.addpanda.Fragments.mainFragment.parentModelArrayList.add(new categoryParentModel(category_name, com.Appzia.addpanda.Fragments.mainFragment.list1));
                                                    Log.d("@@addTotalList1", String.valueOf(sub_cat_name));
                                                }

                                                if (category_id.equals("2")) {

                                                    com.Appzia.addpanda.Fragments.mainFragment.list2.add(new categoryChildModel(image, sub_cat_name, category_id, category_image, sub_cat_id, category_name));
                                                    // com.Appzia.addpanda.Fragments.mainFragment.parentModelArrayList.add(new categoryParentModel(category_name, com.Appzia.addpanda.Fragments.mainFragment.list2));
                                                    Log.d("@@addTotalList2", String.valueOf(sub_cat_name));
                                                }

                                                if (category_id.equals("3")) {

                                                    com.Appzia.addpanda.Fragments.mainFragment.list3.add(new categoryChildModel(image, sub_cat_name, category_id, category_image, sub_cat_id, category_name));
                                                    //   com.Appzia.addpanda.Fragments.mainFragment.parentModelArrayList.add(new categoryParentModel(category_name, com.Appzia.addpanda.Fragments.mainFragment.list3));
                                                    Log.d("@@addTotalList3", String.valueOf(sub_cat_name));
                                                }

                                                if (category_id.equals("4")) {

                                                    com.Appzia.addpanda.Fragments.mainFragment.list4.add(new categoryChildModel(image, sub_cat_name, category_id, category_image, sub_cat_id, category_name));
                                                    Log.d("@@addTotalList5", String.valueOf(sub_cat_name));

                                                }
                                                if (category_id.equals("5")) {

                                                    com.Appzia.addpanda.Fragments.mainFragment.list5.add(new categoryChildModel(image, sub_cat_name, category_id, category_image, sub_cat_id, category_name));
                                                    Log.d("@@addTotalList5", String.valueOf(sub_cat_name));

                                                }
                                                if (category_id.equals("6")) {

                                                    com.Appzia.addpanda.Fragments.mainFragment.list6.add(new categoryChildModel(image, sub_cat_name, category_id, category_image, sub_cat_id, category_name));
                                                    Log.d("@@addTotalList5", String.valueOf(sub_cat_name));

                                                }
                                                if (category_id.equals("7")) {

                                                    com.Appzia.addpanda.Fragments.mainFragment.list7.add(new categoryChildModel(image, sub_cat_name, category_id, category_image, sub_cat_id, category_name));
                                                    Log.d("@@addTotalList5", String.valueOf(sub_cat_name));

                                                }
                                                if (category_id.equals("8")) {

                                                    com.Appzia.addpanda.Fragments.mainFragment.list8.add(new categoryChildModel(image, sub_cat_name, category_id, category_image, sub_cat_id, category_name));
                                                    Log.d("@@addTotalList5", String.valueOf(sub_cat_name));

                                                }
                                                if (category_id.equals("9")) {

                                                    com.Appzia.addpanda.Fragments.mainFragment.list9.add(new categoryChildModel(image, sub_cat_name, category_id, category_image, sub_cat_id, category_name));
                                                    Log.d("@@addTotalList5", String.valueOf(sub_cat_name));

                                                }
                                                if (category_id.equals("10")) {

                                                    com.Appzia.addpanda.Fragments.mainFragment.list10.add(new categoryChildModel(image, sub_cat_name, category_id, category_image, sub_cat_id, category_name));
                                                    Log.d("@@addTotalList5", String.valueOf(sub_cat_name));

                                                }
                                                if (category_id.equals("11")) {

                                                    com.Appzia.addpanda.Fragments.mainFragment.list11.add(new categoryChildModel(image, sub_cat_name, category_id, category_image, sub_cat_id, category_name));
                                                    Log.d("@@addTotalList5", String.valueOf(sub_cat_name));

                                                }
                                                if (category_id.equals("12")) {

                                                    com.Appzia.addpanda.Fragments.mainFragment.list12.add(new categoryChildModel(image, sub_cat_name, category_id, category_image, sub_cat_id, category_name));
                                                    Log.d("@@addTotalList5", String.valueOf(sub_cat_name));

                                                }
                                                if (category_id.equals("13")) {

                                                    com.Appzia.addpanda.Fragments.mainFragment.list13.add(new categoryChildModel(image, sub_cat_name, category_id, category_image, sub_cat_id, category_name));
                                                    Log.d("@@addTotalList5", String.valueOf(sub_cat_name));

                                                }
                                                if (category_id.equals("14")) {

                                                    com.Appzia.addpanda.Fragments.mainFragment.list14.add(new categoryChildModel(image, sub_cat_name, category_id, category_image, sub_cat_id, category_name));
                                                    Log.d("@@addTotalList5", String.valueOf(sub_cat_name));

                                                }
                                                if (category_id.equals("15")) {

                                                    com.Appzia.addpanda.Fragments.mainFragment.list15.add(new categoryChildModel(image, sub_cat_name, category_id, category_image, sub_cat_id, category_name));
                                                    Log.d("@@addTotalList5", String.valueOf(sub_cat_name));

                                                }
                                                if (category_id.equals("16")) {

                                                    com.Appzia.addpanda.Fragments.mainFragment.list16.add(new categoryChildModel(image, sub_cat_name, category_id, category_image, sub_cat_id, category_name));
                                                    Log.d("@@addTotalList5", String.valueOf(sub_cat_name));

                                                }
                                                if (category_id.equals("17")) {

                                                    com.Appzia.addpanda.Fragments.mainFragment.list17.add(new categoryChildModel(image, sub_cat_name, category_id, category_image, sub_cat_id, category_name));
                                                    Log.d("@@addTotalList5", String.valueOf(sub_cat_name));

                                                }
                                                if (category_id.equals("18")) {

                                                    com.Appzia.addpanda.Fragments.mainFragment.list18.add(new categoryChildModel(image, sub_cat_name, category_id, category_image, sub_cat_id, category_name));
                                                    Log.d("@@addTotalList5", String.valueOf(sub_cat_name));

                                                }
                                                if (category_id.equals("19")) {

                                                    com.Appzia.addpanda.Fragments.mainFragment.list19.add(new categoryChildModel(image, sub_cat_name, category_id, category_image, sub_cat_id, category_name));
                                                    Log.d("@@addTotalList5", String.valueOf(sub_cat_name));

                                                }
                                                if (category_id.equals("20")) {

                                                    com.Appzia.addpanda.Fragments.mainFragment.list20.add(new categoryChildModel(image, sub_cat_name, category_id, category_image, sub_cat_id, category_name));
                                                    Log.d("@@addTotalList5", String.valueOf(sub_cat_name));

                                                }
                                                if (category_id.equals("21")) {

                                                    com.Appzia.addpanda.Fragments.mainFragment.list21.add(new categoryChildModel(image, sub_cat_name, category_id, category_image, sub_cat_id, category_name));
                                                    Log.d("@@addTotalList5", String.valueOf(sub_cat_name));

                                                }
                                                if (category_id.equals("22")) {

                                                    com.Appzia.addpanda.Fragments.mainFragment.list22.add(new categoryChildModel(image, sub_cat_name, category_id, category_image, sub_cat_id, category_name));
                                                    Log.d("@@addTotalList5", String.valueOf(sub_cat_name));

                                                }
                                                if (category_id.equals("23")) {

                                                    com.Appzia.addpanda.Fragments.mainFragment.list23.add(new categoryChildModel(image, sub_cat_name, category_id, category_image, sub_cat_id, category_name));
                                                    Log.d("@@addTotalList5", String.valueOf(sub_cat_name));

                                                }
                                                if (category_id.equals("24")) {

                                                    com.Appzia.addpanda.Fragments.mainFragment.list24.add(new categoryChildModel(image, sub_cat_name, category_id, category_image, sub_cat_id, category_name));
                                                    Log.d("@@addTotalList5", String.valueOf(sub_cat_name));

                                                }
                                                if (category_id.equals("25")) {

                                                    com.Appzia.addpanda.Fragments.mainFragment.list25.add(new categoryChildModel(image, sub_cat_name, category_id, category_image, sub_cat_id, category_name));
                                                    Log.d("@@addTotalList5", String.valueOf(sub_cat_name));

                                                }
                                                if (category_id.equals("26")) {

                                                    com.Appzia.addpanda.Fragments.mainFragment.list26.add(new categoryChildModel(image, sub_cat_name, category_id, category_image, sub_cat_id, category_name));
                                                    Log.d("@@addTotalList5", String.valueOf(sub_cat_name));

                                                }
                                                if (category_id.equals("27")) {

                                                    com.Appzia.addpanda.Fragments.mainFragment.list27.add(new categoryChildModel(image, sub_cat_name, category_id, category_image, sub_cat_id, category_name));
                                                    Log.d("@@addTotalList5", String.valueOf(sub_cat_name));

                                                }
                                                if (category_id.equals("28")) {

                                                    com.Appzia.addpanda.Fragments.mainFragment.list28.add(new categoryChildModel(image, sub_cat_name, category_id, category_image, sub_cat_id, category_name));
                                                    Log.d("@@addTotalList5", String.valueOf(sub_cat_name));

                                                }
                                                if (category_id.equals("29")) {

                                                    com.Appzia.addpanda.Fragments.mainFragment.list29.add(new categoryChildModel(image, sub_cat_name, category_id, category_image, sub_cat_id, category_name));
                                                    Log.d("@@addTotalList5", String.valueOf(sub_cat_name));

                                                }
                                                if (category_id.equals("30")) {

                                                    com.Appzia.addpanda.Fragments.mainFragment.list30.add(new categoryChildModel(image, sub_cat_name, category_id, category_image, sub_cat_id, category_name));
                                                    Log.d("@@addTotalList5", String.valueOf(sub_cat_name));

                                                }
                                                if (category_id.equals("31")) {

                                                    com.Appzia.addpanda.Fragments.mainFragment.list31.add(new categoryChildModel(image, sub_cat_name, category_id, category_image, sub_cat_id, category_name));
                                                    Log.d("@@addTotalList5", String.valueOf(sub_cat_name));

                                                }
                                                if (category_id.equals("32")) {

                                                    com.Appzia.addpanda.Fragments.mainFragment.list32.add(new categoryChildModel(image, sub_cat_name, category_id, category_image, sub_cat_id, category_name));
                                                    Log.d("@@addTotalList5", String.valueOf(sub_cat_name));

                                                }
                                                if (category_id.equals("33")) {

                                                    com.Appzia.addpanda.Fragments.mainFragment.list33.add(new categoryChildModel(image, sub_cat_name, category_id, category_image, sub_cat_id, category_name));
                                                    Log.d("@@addTotalList5", String.valueOf(sub_cat_name));

                                                }
                                                if (category_id.equals("34")) {

                                                    com.Appzia.addpanda.Fragments.mainFragment.list34.add(new categoryChildModel(image, sub_cat_name, category_id, category_image, sub_cat_id, category_name));
                                                    Log.d("@@addTotalList5", String.valueOf(sub_cat_name));

                                                }
                                                if (category_id.equals("35")) {

                                                    com.Appzia.addpanda.Fragments.mainFragment.list35.add(new categoryChildModel(image, sub_cat_name, category_id, category_image, sub_cat_id, category_name));
                                                    Log.d("@@addTotalList5", String.valueOf(sub_cat_name));

                                                }
                                                if (category_id.equals("36")) {

                                                    com.Appzia.addpanda.Fragments.mainFragment.list36.add(new categoryChildModel(image, sub_cat_name, category_id, category_image, sub_cat_id, category_name));
                                                    Log.d("@@addTotalList5", String.valueOf(sub_cat_name));

                                                }
                                                if (category_id.equals("37")) {

                                                    com.Appzia.addpanda.Fragments.mainFragment.list37.add(new categoryChildModel(image, sub_cat_name, category_id, category_image, sub_cat_id, category_name));
                                                    Log.d("@@addTotalList5", String.valueOf(sub_cat_name));

                                                }
                                                if (category_id.equals("38")) {

                                                    com.Appzia.addpanda.Fragments.mainFragment.list38.add(new categoryChildModel(image, sub_cat_name, category_id, category_image, sub_cat_id, category_name));
                                                    Log.d("@@addTotalList5", String.valueOf(sub_cat_name));

                                                }
                                                if (category_id.equals("39")) {

                                                    com.Appzia.addpanda.Fragments.mainFragment.list39.add(new categoryChildModel(image, sub_cat_name, category_id, category_image, sub_cat_id, category_name));
                                                    Log.d("@@addTotalList5", String.valueOf(sub_cat_name));

                                                }
                                                if (category_id.equals("40")) {

                                                    com.Appzia.addpanda.Fragments.mainFragment.list40.add(new categoryChildModel(image, sub_cat_name, category_id, category_image, sub_cat_id, category_name));
                                                    Log.d("@@addTotalList5", String.valueOf(sub_cat_name));

                                                }
                                                if (category_id.equals("41")) {

                                                    com.Appzia.addpanda.Fragments.mainFragment.list41.add(new categoryChildModel(image, sub_cat_name, category_id, category_image, sub_cat_id, category_name));
                                                    Log.d("@@addTotalList5", String.valueOf(sub_cat_name));

                                                }
                                                if (category_id.equals("42")) {

                                                    com.Appzia.addpanda.Fragments.mainFragment.list42.add(new categoryChildModel(image, sub_cat_name, category_id, category_image, sub_cat_id, category_name));
                                                    Log.d("@@addTotalList5", String.valueOf(sub_cat_name));

                                                }
                                                if (category_id.equals("43")) {

                                                    com.Appzia.addpanda.Fragments.mainFragment.list43.add(new categoryChildModel(image, sub_cat_name, category_id, category_image, sub_cat_id, category_name));
                                                    Log.d("@@addTotalList5", String.valueOf(sub_cat_name));

                                                }
                                                if (category_id.equals("44")) {

                                                    com.Appzia.addpanda.Fragments.mainFragment.list44.add(new categoryChildModel(image, sub_cat_name, category_id, category_image, sub_cat_id, category_name));
                                                    Log.d("@@addTotalList5", String.valueOf(sub_cat_name));

                                                }
                                                if (category_id.equals("45")) {

                                                    com.Appzia.addpanda.Fragments.mainFragment.list45.add(new categoryChildModel(image, sub_cat_name, category_id, category_image, sub_cat_id, category_name));
                                                    Log.d("@@addTotalList5", String.valueOf(sub_cat_name));

                                                }
                                                if (category_id.equals("46")) {

                                                    com.Appzia.addpanda.Fragments.mainFragment.list46.add(new categoryChildModel(image, sub_cat_name, category_id, category_image, sub_cat_id, category_name));
                                                    Log.d("@@addTotalList5", String.valueOf(sub_cat_name));

                                                }
                                                if (category_id.equals("47")) {

                                                    com.Appzia.addpanda.Fragments.mainFragment.list47.add(new categoryChildModel(image, sub_cat_name, category_id, category_image, sub_cat_id, category_name));
                                                    Log.d("@@addTotalList5", String.valueOf(sub_cat_name));

                                                }
                                                if (category_id.equals("48")) {

                                                    com.Appzia.addpanda.Fragments.mainFragment.list48.add(new categoryChildModel(image, sub_cat_name, category_id, category_image, sub_cat_id, category_name));
                                                    Log.d("@@addTotalList5", String.valueOf(sub_cat_name));

                                                }
                                                if (category_id.equals("49")) {

                                                    com.Appzia.addpanda.Fragments.mainFragment.list49.add(new categoryChildModel(image, sub_cat_name, category_id, category_image, sub_cat_id, category_name));
                                                    Log.d("@@addTotalList5", String.valueOf(sub_cat_name));

                                                }
                                                if (category_id.equals("50")) {

                                                    com.Appzia.addpanda.Fragments.mainFragment.list50.add(new categoryChildModel(image, sub_cat_name, category_id, category_image, sub_cat_id, category_name));
                                                    Log.d("@@addTotalList5", String.valueOf(sub_cat_name));

                                                }


                                            }
                                            //data are stored in list below

                                            if (category_id.equals("1")) {

                                                //com.Appzia.addpanda.Fragments.mainFragment.list1.add(new categoryChildModel(image, sub_cat_name, category_id, category_image, sub_cat_id,category_name));
                                                com.Appzia.addpanda.Fragments.mainFragment.parentModelArrayList.add(new categoryParentModel(category_name, category_id, com.Appzia.addpanda.Fragments.mainFragment.list1));
                                                Log.d("@@addTotalList1", String.valueOf(sub_cat_name));
                                            }

                                            if (category_id.equals("2")) {

                                                //  com.Appzia.addpanda.Fragments.mainFragment.list2.add(new categoryChildModel(image, sub_cat_name, category_id, category_image, sub_cat_id,category_name));
                                                com.Appzia.addpanda.Fragments.mainFragment.parentModelArrayList.add(new categoryParentModel(category_name, category_id, com.Appzia.addpanda.Fragments.mainFragment.list2));
                                                Log.d("@@addTotalList2", String.valueOf(sub_cat_name));
                                            }

                                            if (category_id.equals("3")) {

                                                //com.Appzia.addpanda.Fragments.mainFragment.list3.add(new categoryChildModel(image, sub_cat_name,category_id, category_id, category_image, sub_cat_id));
                                                com.Appzia.addpanda.Fragments.mainFragment.parentModelArrayList.add(new categoryParentModel(category_name, category_id, com.Appzia.addpanda.Fragments.mainFragment.list3));
                                                Log.d("@@addTotalList3", String.valueOf(sub_cat_name));
                                            }

                                            if (category_id.equals("4")) {

                                                //com.Appzia.addpanda.Fragments.mainFragment.list3.add(new categoryChildModel(image, sub_cat_name,category_id, category_id, category_image, sub_cat_id));
                                                com.Appzia.addpanda.Fragments.mainFragment.parentModelArrayList.add(new categoryParentModel(category_name, category_id, com.Appzia.addpanda.Fragments.mainFragment.list4));
                                                Log.d("@@addTotalList3", String.valueOf(sub_cat_name));
                                            }

                                            if (category_id.equals("5")) {

                                                //   com.Appzia.addpanda.Fragments.mainFragment.list4.add(new categoryChildModel(image, sub_cat_name,category_id, category_id, category_image, sub_cat_id));
                                                com.Appzia.addpanda.Fragments.mainFragment.parentModelArrayList.add(new categoryParentModel(category_name, category_id, com.Appzia.addpanda.Fragments.mainFragment.list5));
                                                Log.d("@@addTotalList5", String.valueOf(sub_cat_name));

                                            }

                                            if (category_id.equals("6")) {

//                                            com.Appzia.addpanda.Fragments.mainFragment.list6.add(new categoryChildModel(image, sub_cat_name,category_id, category_id, category_image, sub_cat_id));
                                                com.Appzia.addpanda.Fragments.mainFragment.parentModelArrayList.add(new categoryParentModel(category_name, category_id, com.Appzia.addpanda.Fragments.mainFragment.list6));

                                                Log.d("@@addTotalList5", String.valueOf(sub_cat_name));

                                            }
                                            if (category_id.equals("7")) {

//                                            com.Appzia.addpanda.Fragments.mainFragment.list7.add(new categoryChildModel(image, sub_cat_name,category_id, category_id, category_image, sub_cat_id));
                                                com.Appzia.addpanda.Fragments.mainFragment.parentModelArrayList.add(new categoryParentModel(category_name, category_id, com.Appzia.addpanda.Fragments.mainFragment.list7));
                                                Log.d("@@addTotalList5", String.valueOf(sub_cat_name));

                                            }
                                            if (category_id.equals("8")) {

//                                            com.Appzia.addpanda.Fragments.mainFragment.list8.add(new categoryChildModel(image, sub_cat_name,category_id, category_id, category_image, sub_cat_id));
                                                com.Appzia.addpanda.Fragments.mainFragment.parentModelArrayList.add(new categoryParentModel(category_name, category_id, com.Appzia.addpanda.Fragments.mainFragment.list8));
                                                Log.d("@@addTotalList5", String.valueOf(sub_cat_name));

                                            }
                                            if (category_id.equals("9")) {

//                                            com.Appzia.addpanda.Fragments.mainFragment.list9.add(new categoryChildModel(image, sub_cat_name,category_id, category_id, category_image, sub_cat_id));
                                                com.Appzia.addpanda.Fragments.mainFragment.parentModelArrayList.add(new categoryParentModel(category_name, category_id, com.Appzia.addpanda.Fragments.mainFragment.list9));
                                                Log.d("@@addTotalList5", String.valueOf(sub_cat_name));

                                            }
                                            if (category_id.equals("10")) {

//                                            com.Appzia.addpanda.Fragments.mainFragment.list10.add(new categoryChildModel(image, sub_cat_name,category_id, category_id, category_image, sub_cat_id));
                                                com.Appzia.addpanda.Fragments.mainFragment.parentModelArrayList.add(new categoryParentModel(category_name, category_id, com.Appzia.addpanda.Fragments.mainFragment.list10));
                                                Log.d("@@addTotalList5", String.valueOf(sub_cat_name));

                                            }
                                            if (category_id.equals("11")) {

//                                            com.Appzia.addpanda.Fragments.mainFragment.list11.add(new categoryChildModel(image, sub_cat_name,category_id, category_id, category_image, sub_cat_id));
                                                com.Appzia.addpanda.Fragments.mainFragment.parentModelArrayList.add(new categoryParentModel(category_name, category_id, com.Appzia.addpanda.Fragments.mainFragment.list11));
                                                Log.d("@@addTotalList5", String.valueOf(sub_cat_name));

                                            }
                                            if (category_id.equals("12")) {

//                                            com.Appzia.addpanda.Fragments.mainFragment.list12.add(new categoryChildModel(image, sub_cat_name,category_id, category_id, category_image, sub_cat_id));
                                                com.Appzia.addpanda.Fragments.mainFragment.parentModelArrayList.add(new categoryParentModel(category_name, category_id, com.Appzia.addpanda.Fragments.mainFragment.list12));
                                                Log.d("@@addTotalList5", String.valueOf(sub_cat_name));

                                            }
                                            if (category_id.equals("13")) {

//                                            com.Appzia.addpanda.Fragments.mainFragment.list13.add(new categoryChildModel(image, sub_cat_name,category_id, category_id, category_image, sub_cat_id));
                                                com.Appzia.addpanda.Fragments.mainFragment.parentModelArrayList.add(new categoryParentModel(category_name, category_id, com.Appzia.addpanda.Fragments.mainFragment.list13));
                                                Log.d("@@addTotalList5", String.valueOf(sub_cat_name));

                                            }
                                            if (category_id.equals("14")) {

//                                            com.Appzia.addpanda.Fragments.mainFragment.list14.add(new categoryChildModel(image, sub_cat_name,category_id, category_id, category_image, sub_cat_id));
                                                com.Appzia.addpanda.Fragments.mainFragment.parentModelArrayList.add(new categoryParentModel(category_name, category_id, com.Appzia.addpanda.Fragments.mainFragment.list14));
                                                Log.d("@@addTotalList5", String.valueOf(sub_cat_name));

                                            }
                                            if (category_id.equals("15")) {

//                                            com.Appzia.addpanda.Fragments.mainFragment.list15.add(new categoryChildModel(image, sub_cat_name,category_id, category_id, category_image, sub_cat_id));
                                                com.Appzia.addpanda.Fragments.mainFragment.parentModelArrayList.add(new categoryParentModel(category_name, category_id, com.Appzia.addpanda.Fragments.mainFragment.list15));
                                                Log.d("@@addTotalList5", String.valueOf(sub_cat_name));

                                            }
                                            if (category_id.equals("16")) {

//                                            com.Appzia.addpanda.Fragments.mainFragment.list16.add(new categoryChildModel(image, sub_cat_name,category_id, category_id, category_image, sub_cat_id));
                                                com.Appzia.addpanda.Fragments.mainFragment.parentModelArrayList.add(new categoryParentModel(category_name, category_id, com.Appzia.addpanda.Fragments.mainFragment.list16));
                                                Log.d("@@addTotalList5", String.valueOf(sub_cat_name));

                                            }
                                            if (category_id.equals("17")) {

//                                            com.Appzia.addpanda.Fragments.mainFragment.list17.add(new categoryChildModel(image, sub_cat_name,category_id, category_id, category_image, sub_cat_id));
                                                com.Appzia.addpanda.Fragments.mainFragment.parentModelArrayList.add(new categoryParentModel(category_name, category_id, com.Appzia.addpanda.Fragments.mainFragment.list17));
                                                Log.d("@@addTotalList5", String.valueOf(sub_cat_name));

                                            }
                                            if (category_id.equals("18")) {

//                                            com.Appzia.addpanda.Fragments.mainFragment.list18.add(new categoryChildModel(image, sub_cat_name,category_id, category_id, category_image, sub_cat_id));
                                                com.Appzia.addpanda.Fragments.mainFragment.parentModelArrayList.add(new categoryParentModel(category_name, category_id, com.Appzia.addpanda.Fragments.mainFragment.list18));
                                                Log.d("@@addTotalList5", String.valueOf(sub_cat_name));

                                            }
                                            if (category_id.equals("19")) {

//                                            com.Appzia.addpanda.Fragments.mainFragment.list19.add(new categoryChildModel(image, sub_cat_name,category_id, category_id, category_image, sub_cat_id));
                                                com.Appzia.addpanda.Fragments.mainFragment.parentModelArrayList.add(new categoryParentModel(category_name, category_id, com.Appzia.addpanda.Fragments.mainFragment.list19));
                                                Log.d("@@addTotalList5", String.valueOf(sub_cat_name));

                                            }
                                            if (category_id.equals("20")) {

//                                            com.Appzia.addpanda.Fragments.mainFragment.list20.add(new categoryChildModel(image, sub_cat_name,category_id, category_id, category_image, sub_cat_id));
                                                com.Appzia.addpanda.Fragments.mainFragment.parentModelArrayList.add(new categoryParentModel(category_name, category_id, com.Appzia.addpanda.Fragments.mainFragment.list20));
                                                Log.d("@@addTotalList5", String.valueOf(sub_cat_name));

                                            }
                                            if (category_id.equals("21")) {

//                                            com.Appzia.addpanda.Fragments.mainFragment.list21.add(new categoryChildModel(image, sub_cat_name,category_id, category_id, category_image, sub_cat_id));
                                                com.Appzia.addpanda.Fragments.mainFragment.parentModelArrayList.add(new categoryParentModel(category_name, category_id, com.Appzia.addpanda.Fragments.mainFragment.list21));
                                                Log.d("@@addTotalList5", String.valueOf(sub_cat_name));

                                            }
                                            if (category_id.equals("22")) {

//                                            com.Appzia.addpanda.Fragments.mainFragment.list5.add(new categoryChildModel(image, sub_cat_name,category_id, category_id, category_image, sub_cat_id));
                                                com.Appzia.addpanda.Fragments.mainFragment.parentModelArrayList.add(new categoryParentModel(category_name, category_id, com.Appzia.addpanda.Fragments.mainFragment.list22));
                                                Log.d("@@addTotalList5", String.valueOf(sub_cat_name));

                                            }
                                            if (category_id.equals("23")) {

//                                            com.Appzia.addpanda.Fragments.mainFragment.list22.add(new categoryChildModel(image, sub_cat_name,category_id, category_id, category_image, sub_cat_id));
                                                com.Appzia.addpanda.Fragments.mainFragment.parentModelArrayList.add(new categoryParentModel(category_name, category_id, com.Appzia.addpanda.Fragments.mainFragment.list23));
                                                Log.d("@@addTotalList5", String.valueOf(sub_cat_name));

                                            }
                                            if (category_id.equals("24")) {

//                                            com.Appzia.addpanda.Fragments.mainFragment.list23.add(new categoryChildModel(image, sub_cat_name,category_id, category_id, category_image, sub_cat_id));
                                                com.Appzia.addpanda.Fragments.mainFragment.parentModelArrayList.add(new categoryParentModel(category_name, category_id, com.Appzia.addpanda.Fragments.mainFragment.list24));
                                                Log.d("@@addTotalList5", String.valueOf(sub_cat_name));

                                            }
                                            if (category_id.equals("25")) {

//                                            com.Appzia.addpanda.Fragments.mainFragment.list24.add(new categoryChildModel(image, sub_cat_name,category_id, category_id, category_image, sub_cat_id));
                                                com.Appzia.addpanda.Fragments.mainFragment.parentModelArrayList.add(new categoryParentModel(category_name, category_id, com.Appzia.addpanda.Fragments.mainFragment.list25));
                                                Log.d("@@addTotalList5", String.valueOf(sub_cat_name));

                                            }
                                            if (category_id.equals("26")) {

//                                            com.Appzia.addpanda.Fragments.mainFragment.list25.add(new categoryChildModel(image, sub_cat_name,category_id, category_id, category_image, sub_cat_id));
                                                com.Appzia.addpanda.Fragments.mainFragment.parentModelArrayList.add(new categoryParentModel(category_name, category_id, com.Appzia.addpanda.Fragments.mainFragment.list26));
                                                Log.d("@@addTotalList5", String.valueOf(sub_cat_name));

                                            }
                                            if (category_id.equals("27")) {

//                                            com.Appzia.addpanda.Fragments.mainFragment.list26.add(new categoryChildModel(image, sub_cat_name,category_id, category_id, category_image, sub_cat_id));
                                                com.Appzia.addpanda.Fragments.mainFragment.parentModelArrayList.add(new categoryParentModel(category_name, category_id, com.Appzia.addpanda.Fragments.mainFragment.list27));
                                                Log.d("@@addTotalList5", String.valueOf(sub_cat_name));

                                            }
                                            if (category_id.equals("28")) {

//                                            com.Appzia.addpanda.Fragments.mainFragment.list28.add(new categoryChildModel(image, sub_cat_name,category_id, category_id, category_image, sub_cat_id));
                                                com.Appzia.addpanda.Fragments.mainFragment.parentModelArrayList.add(new categoryParentModel(category_name, category_id, com.Appzia.addpanda.Fragments.mainFragment.list28));
                                                Log.d("@@addTotalList5", String.valueOf(sub_cat_name));

                                            }
                                            if (category_id.equals("29")) {

//                                            com.Appzia.addpanda.Fragments.mainFragment.list29.add(new categoryChildModel(image, sub_cat_name,category_id, category_id, category_image, sub_cat_id));
                                                com.Appzia.addpanda.Fragments.mainFragment.parentModelArrayList.add(new categoryParentModel(category_name, category_id, com.Appzia.addpanda.Fragments.mainFragment.list29));
                                                Log.d("@@addTotalList5", String.valueOf(sub_cat_name));

                                            }
                                            if (category_id.equals("30")) {

//                                            com.Appzia.addpanda.Fragments.mainFragment.list30.add(new categoryChildModel(image, sub_cat_name,category_id, category_id, category_image, sub_cat_id));
                                                com.Appzia.addpanda.Fragments.mainFragment.parentModelArrayList.add(new categoryParentModel(category_name, category_id, com.Appzia.addpanda.Fragments.mainFragment.list30));
                                                Log.d("@@addTotalList5", String.valueOf(sub_cat_name));

                                            }
                                            if (category_id.equals("31")) {

//                                            com.Appzia.addpanda.Fragments.mainFragment.list31.add(new categoryChildModel(image, sub_cat_name,category_id, category_id, category_image, sub_cat_id));
                                                com.Appzia.addpanda.Fragments.mainFragment.parentModelArrayList.add(new categoryParentModel(category_name, category_id, com.Appzia.addpanda.Fragments.mainFragment.list31));
                                                Log.d("@@addTotalList5", String.valueOf(sub_cat_name));

                                            }
                                            if (category_id.equals("32")) {

//                                            com.Appzia.addpanda.Fragments.mainFragment.list32.add(new categoryChildModel(image, sub_cat_name,category_id, category_id, category_image, sub_cat_id));
                                                com.Appzia.addpanda.Fragments.mainFragment.parentModelArrayList.add(new categoryParentModel(category_name, category_id, com.Appzia.addpanda.Fragments.mainFragment.list32));
                                                Log.d("@@addTotalList5", String.valueOf(sub_cat_name));

                                            }
                                            if (category_id.equals("33")) {

//                                            com.Appzia.addpanda.Fragments.mainFragment.list33.add(new categoryChildModel(image, sub_cat_name,category_id, category_id, category_image, sub_cat_id));
                                                com.Appzia.addpanda.Fragments.mainFragment.parentModelArrayList.add(new categoryParentModel(category_name, category_id, com.Appzia.addpanda.Fragments.mainFragment.list33));
                                                Log.d("@@addTotalList5", String.valueOf(sub_cat_name));

                                            }
                                            if (category_id.equals("34")) {

//                                            com.Appzia.addpanda.Fragments.mainFragment.list34.add(new categoryChildModel(image, sub_cat_name,category_id, category_id, category_image, sub_cat_id));
                                                com.Appzia.addpanda.Fragments.mainFragment.parentModelArrayList.add(new categoryParentModel(category_name, category_id, com.Appzia.addpanda.Fragments.mainFragment.list34));
                                                Log.d("@@addTotalList5", String.valueOf(sub_cat_name));

                                            }
                                            if (category_id.equals("35")) {

//                                            com.Appzia.addpanda.Fragments.mainFragment.list35.add(new categoryChildModel(image, sub_cat_name,category_id, category_id, category_image, sub_cat_id));
                                                com.Appzia.addpanda.Fragments.mainFragment.parentModelArrayList.add(new categoryParentModel(category_name, category_id, com.Appzia.addpanda.Fragments.mainFragment.list35));
                                                Log.d("@@addTotalList5", String.valueOf(sub_cat_name));

                                            }
                                            if (category_id.equals("36")) {

//                                            com.Appzia.addpanda.Fragments.mainFragment.list36.add(new categoryChildModel(image, sub_cat_name,category_id, category_id, category_image, sub_cat_id));
                                                com.Appzia.addpanda.Fragments.mainFragment.parentModelArrayList.add(new categoryParentModel(category_name, category_id, com.Appzia.addpanda.Fragments.mainFragment.list36));
                                                Log.d("@@addTotalList5", String.valueOf(sub_cat_name));

                                            }
                                            if (category_id.equals("37")) {

//                                            com.Appzia.addpanda.Fragments.mainFragment.list37.add(new categoryChildModel(image, sub_cat_name,category_id, category_id, category_image, sub_cat_id));
                                                com.Appzia.addpanda.Fragments.mainFragment.parentModelArrayList.add(new categoryParentModel(category_name, category_id, com.Appzia.addpanda.Fragments.mainFragment.list37));
                                                Log.d("@@addTotalList5", String.valueOf(sub_cat_name));

                                            }
                                            if (category_id.equals("38")) {

//                                            com.Appzia.addpanda.Fragments.mainFragment.list38.add(new categoryChildModel(image, sub_cat_name,category_id, category_id, category_image, sub_cat_id));
                                                com.Appzia.addpanda.Fragments.mainFragment.parentModelArrayList.add(new categoryParentModel(category_name, category_id, com.Appzia.addpanda.Fragments.mainFragment.list38));
                                                Log.d("@@addTotalList5", String.valueOf(sub_cat_name));

                                            }
                                            if (category_id.equals("39")) {

//                                            com.Appzia.addpanda.Fragments.mainFragment.list39.add(new categoryChildModel(image, sub_cat_name,category_id, category_id, category_image, sub_cat_id));
                                                com.Appzia.addpanda.Fragments.mainFragment.parentModelArrayList.add(new categoryParentModel(category_name, category_id, com.Appzia.addpanda.Fragments.mainFragment.list39));
                                                Log.d("@@addTotalList5", String.valueOf(sub_cat_name));

                                            }
                                            if (category_id.equals("40")) {

//                                            com.Appzia.addpanda.Fragments.mainFragment.list40.add(new categoryChildModel(image, sub_cat_name,category_id, category_id, category_image, sub_cat_id));
                                                com.Appzia.addpanda.Fragments.mainFragment.parentModelArrayList.add(new categoryParentModel(category_name, category_id, com.Appzia.addpanda.Fragments.mainFragment.list40));
                                                Log.d("@@addTotalList5", String.valueOf(sub_cat_name));

                                            }
                                            if (category_id.equals("41")) {

//                                            com.Appzia.addpanda.Fragments.mainFragment.list41.add(new categoryChildModel(image, sub_cat_name,category_id, category_id, category_image, sub_cat_id));
                                                com.Appzia.addpanda.Fragments.mainFragment.parentModelArrayList.add(new categoryParentModel(category_name, category_id, com.Appzia.addpanda.Fragments.mainFragment.list41));
                                                Log.d("@@addTotalList5", String.valueOf(sub_cat_name));

                                            }
                                            if (category_id.equals("42")) {

//                                            com.Appzia.addpanda.Fragments.mainFragment.list42.add(new categoryChildModel(image, sub_cat_name,category_id, category_id, category_image, sub_cat_id));
                                                com.Appzia.addpanda.Fragments.mainFragment.parentModelArrayList.add(new categoryParentModel(category_name, category_id, com.Appzia.addpanda.Fragments.mainFragment.list42));
                                                Log.d("@@addTotalList5", String.valueOf(sub_cat_name));

                                            }
                                            if (category_id.equals("43")) {

//                                            com.Appzia.addpanda.Fragments.mainFragment.list43.add(new categoryChildModel(image, sub_cat_name,category_id, category_id, category_image, sub_cat_id));
                                                com.Appzia.addpanda.Fragments.mainFragment.parentModelArrayList.add(new categoryParentModel(category_name, category_id, com.Appzia.addpanda.Fragments.mainFragment.list43));
                                                Log.d("@@addTotalList5", String.valueOf(sub_cat_name));

                                            }
                                            if (category_id.equals("44")) {

//                                            com.Appzia.addpanda.Fragments.mainFragment.list44.add(new categoryChildModel(image, sub_cat_name,category_id, category_id, category_image, sub_cat_id));
                                                com.Appzia.addpanda.Fragments.mainFragment.parentModelArrayList.add(new categoryParentModel(category_name, category_id, com.Appzia.addpanda.Fragments.mainFragment.list44));
                                                Log.d("@@addTotalList5", String.valueOf(sub_cat_name));

                                            }
                                            if (category_id.equals("45")) {

//                                            com.Appzia.addpanda.Fragments.mainFragment.list45.add(new categoryChildModel(image, sub_cat_name,category_id, category_id, category_image, sub_cat_id));
                                                com.Appzia.addpanda.Fragments.mainFragment.parentModelArrayList.add(new categoryParentModel(category_name, category_id, com.Appzia.addpanda.Fragments.mainFragment.list45));
                                                Log.d("@@addTotalList5", String.valueOf(sub_cat_name));

                                            }
                                            if (category_id.equals("46")) {

//                                            com.Appzia.addpanda.Fragments.mainFragment.list46.add(new categoryChildModel(image, sub_cat_name,category_id, category_id, category_image, sub_cat_id));
                                                com.Appzia.addpanda.Fragments.mainFragment.parentModelArrayList.add(new categoryParentModel(category_name, category_id, com.Appzia.addpanda.Fragments.mainFragment.list46));
                                                Log.d("@@addTotalList5", String.valueOf(sub_cat_name));

                                            }
                                            if (category_id.equals("47")) {

//                                            com.Appzia.addpanda.Fragments.mainFragment.list47.add(new categoryChildModel(image, sub_cat_name,category_id, category_id, category_image, sub_cat_id));
                                                com.Appzia.addpanda.Fragments.mainFragment.parentModelArrayList.add(new categoryParentModel(category_name, category_id, com.Appzia.addpanda.Fragments.mainFragment.list47));
                                                Log.d("@@addTotalList5", String.valueOf(sub_cat_name));

                                            }
                                            if (category_id.equals("48")) {

//                                            com.Appzia.addpanda.Fragments.mainFragment.list48.add(new categoryChildModel(image, sub_cat_name,category_id, category_id, category_image, sub_cat_id));
                                                com.Appzia.addpanda.Fragments.mainFragment.parentModelArrayList.add(new categoryParentModel(category_name, category_id, com.Appzia.addpanda.Fragments.mainFragment.list48));
                                                Log.d("@@addTotalList5", String.valueOf(sub_cat_name));

                                            }
                                            if (category_id.equals("49")) {

//                                            com.Appzia.addpanda.Fragments.mainFragment.list49.add(new categoryChildModel(image, sub_cat_name,category_id, category_id, category_image, sub_cat_id));
                                                com.Appzia.addpanda.Fragments.mainFragment.parentModelArrayList.add(new categoryParentModel(category_name, category_id, com.Appzia.addpanda.Fragments.mainFragment.list49));
                                                Log.d("@@addTotalList5", String.valueOf(sub_cat_name));

                                            }
                                            if (category_id.equals("50")) {

//                                            com.Appzia.addpanda.Fragments.mainFragment.list50.add(new categoryChildModel(image, sub_cat_name,category_id, category_id, category_image, sub_cat_id));
                                                com.Appzia.addpanda.Fragments.mainFragment.parentModelArrayList.add(new categoryParentModel(category_name, category_id, com.Appzia.addpanda.Fragments.mainFragment.list50));
                                                Log.d("@@addTotalList5", String.valueOf(sub_cat_name));

                                            }

                                            com.Appzia.addpanda.Fragments.mainFragment.shimmerFrameLayout.stopShimmer();
                                            com.Appzia.addpanda.Fragments.mainFragment.whatsapp.setVisibility(View.VISIBLE);
                                            com.Appzia.addpanda.Fragments.mainFragment.CoLayout.setVisibility(View.VISIBLE);
                                            com.Appzia.addpanda.Fragments.mainFragment.shimmerFrameLayout.setVisibility(View.GONE);

                                        } else if (accountType.equals("2") && category_type_flag.equals("2")) {
                                            Constant.mainCatBtnModelList.add(new mainCatBtnChildModel(category_name, category_id));

                                            for (int j = 0; j < sub_category_list.length(); j++) {
                                                obj3 = sub_category_list.getJSONObject(j);

                                                sub_cat_name = obj3.getString("sub_cat_name");
                                                sub_cat_id = obj3.getString("sub_cat_id");
                                                image = obj3.getString("image");


                                                if (category_id.equals("1")) {

                                                    com.Appzia.addpanda.Fragments.mainFragment.list1.add(new categoryChildModel(image, sub_cat_name, category_id, category_image, sub_cat_id, category_name));
                                                    //     com.Appzia.addpanda.Fragments.mainFragment.parentModelArrayList.add(new categoryParentModel(category_name, com.Appzia.addpanda.Fragments.mainFragment.list1));
                                                    Log.d("@@addTotalList1", String.valueOf(sub_cat_name));
                                                }

                                                if (category_id.equals("2")) {

                                                    com.Appzia.addpanda.Fragments.mainFragment.list2.add(new categoryChildModel(image, sub_cat_name, category_id, category_image, sub_cat_id, category_name));
                                                    // com.Appzia.addpanda.Fragments.mainFragment.parentModelArrayList.add(new categoryParentModel(category_name, com.Appzia.addpanda.Fragments.mainFragment.list2));
                                                    Log.d("@@addTotalList2", String.valueOf(sub_cat_name));
                                                }

                                                if (category_id.equals("3")) {

                                                    com.Appzia.addpanda.Fragments.mainFragment.list3.add(new categoryChildModel(image, sub_cat_name, category_id, category_image, sub_cat_id, category_name));
                                                    //   com.Appzia.addpanda.Fragments.mainFragment.parentModelArrayList.add(new categoryParentModel(category_name, com.Appzia.addpanda.Fragments.mainFragment.list3));
                                                    Log.d("@@addTotalList3", String.valueOf(sub_cat_name));
                                                }

                                                if (category_id.equals("4")) {

                                                    com.Appzia.addpanda.Fragments.mainFragment.list4.add(new categoryChildModel(image, sub_cat_name, category_id, category_image, sub_cat_id, category_name));
                                                    Log.d("@@addTotalList5", String.valueOf(sub_cat_name));

                                                }
                                                if (category_id.equals("5")) {

                                                    com.Appzia.addpanda.Fragments.mainFragment.list5.add(new categoryChildModel(image, sub_cat_name, category_id, category_image, sub_cat_id, category_name));
                                                    Log.d("@@addTotalList5", String.valueOf(sub_cat_name));

                                                }
                                                if (category_id.equals("6")) {

                                                    com.Appzia.addpanda.Fragments.mainFragment.list6.add(new categoryChildModel(image, sub_cat_name, category_id, category_image, sub_cat_id, category_name));
                                                    Log.d("@@addTotalList5", String.valueOf(sub_cat_name));

                                                }
                                                if (category_id.equals("7")) {

                                                    com.Appzia.addpanda.Fragments.mainFragment.list7.add(new categoryChildModel(image, sub_cat_name, category_id, category_image, sub_cat_id, category_name));
                                                    Log.d("@@addTotalList5", String.valueOf(sub_cat_name));

                                                }
                                                if (category_id.equals("8")) {

                                                    com.Appzia.addpanda.Fragments.mainFragment.list8.add(new categoryChildModel(image, sub_cat_name, category_id, category_image, sub_cat_id, category_name));
                                                    Log.d("@@addTotalList5", String.valueOf(sub_cat_name));

                                                }
                                                if (category_id.equals("9")) {

                                                    com.Appzia.addpanda.Fragments.mainFragment.list9.add(new categoryChildModel(image, sub_cat_name, category_id, category_image, sub_cat_id, category_name));
                                                    Log.d("@@addTotalList5", String.valueOf(sub_cat_name));

                                                }
                                                if (category_id.equals("10")) {

                                                    com.Appzia.addpanda.Fragments.mainFragment.list10.add(new categoryChildModel(image, sub_cat_name, category_id, category_image, sub_cat_id, category_name));
                                                    Log.d("@@addTotalList5", String.valueOf(sub_cat_name));

                                                }
                                                if (category_id.equals("11")) {

                                                    com.Appzia.addpanda.Fragments.mainFragment.list11.add(new categoryChildModel(image, sub_cat_name, category_id, category_image, sub_cat_id, category_name));
                                                    Log.d("@@addTotalList5", String.valueOf(sub_cat_name));

                                                }
                                                if (category_id.equals("12")) {

                                                    com.Appzia.addpanda.Fragments.mainFragment.list12.add(new categoryChildModel(image, sub_cat_name, category_id, category_image, sub_cat_id, category_name));
                                                    Log.d("@@addTotalList5", String.valueOf(sub_cat_name));

                                                }
                                                if (category_id.equals("13")) {

                                                    com.Appzia.addpanda.Fragments.mainFragment.list13.add(new categoryChildModel(image, sub_cat_name, category_id, category_image, sub_cat_id, category_name));
                                                    Log.d("@@addTotalList5", String.valueOf(sub_cat_name));

                                                }
                                                if (category_id.equals("14")) {

                                                    com.Appzia.addpanda.Fragments.mainFragment.list14.add(new categoryChildModel(image, sub_cat_name, category_id, category_image, sub_cat_id, category_name));
                                                    Log.d("@@addTotalList5", String.valueOf(sub_cat_name));

                                                }
                                                if (category_id.equals("15")) {

                                                    com.Appzia.addpanda.Fragments.mainFragment.list15.add(new categoryChildModel(image, sub_cat_name, category_id, category_image, sub_cat_id, category_name));
                                                    Log.d("@@addTotalList5", String.valueOf(sub_cat_name));

                                                }
                                                if (category_id.equals("16")) {

                                                    com.Appzia.addpanda.Fragments.mainFragment.list16.add(new categoryChildModel(image, sub_cat_name, category_id, category_image, sub_cat_id, category_name));
                                                    Log.d("@@addTotalList5", String.valueOf(sub_cat_name));

                                                }
                                                if (category_id.equals("17")) {

                                                    com.Appzia.addpanda.Fragments.mainFragment.list17.add(new categoryChildModel(image, sub_cat_name, category_id, category_image, sub_cat_id, category_name));
                                                    Log.d("@@addTotalList5", String.valueOf(sub_cat_name));

                                                }
                                                if (category_id.equals("18")) {

                                                    com.Appzia.addpanda.Fragments.mainFragment.list18.add(new categoryChildModel(image, sub_cat_name, category_id, category_image, sub_cat_id, category_name));
                                                    Log.d("@@addTotalList5", String.valueOf(sub_cat_name));

                                                }
                                                if (category_id.equals("19")) {

                                                    com.Appzia.addpanda.Fragments.mainFragment.list19.add(new categoryChildModel(image, sub_cat_name, category_id, category_image, sub_cat_id, category_name));
                                                    Log.d("@@addTotalList5", String.valueOf(sub_cat_name));

                                                }
                                                if (category_id.equals("20")) {

                                                    com.Appzia.addpanda.Fragments.mainFragment.list20.add(new categoryChildModel(image, sub_cat_name, category_id, category_image, sub_cat_id, category_name));
                                                    Log.d("@@addTotalList5", String.valueOf(sub_cat_name));

                                                }
                                                if (category_id.equals("21")) {

                                                    com.Appzia.addpanda.Fragments.mainFragment.list21.add(new categoryChildModel(image, sub_cat_name, category_id, category_image, sub_cat_id, category_name));
                                                    Log.d("@@addTotalList5", String.valueOf(sub_cat_name));

                                                }
                                                if (category_id.equals("22")) {

                                                    com.Appzia.addpanda.Fragments.mainFragment.list22.add(new categoryChildModel(image, sub_cat_name, category_id, category_image, sub_cat_id, category_name));
                                                    Log.d("@@addTotalList5", String.valueOf(sub_cat_name));

                                                }
                                                if (category_id.equals("23")) {

                                                    com.Appzia.addpanda.Fragments.mainFragment.list23.add(new categoryChildModel(image, sub_cat_name, category_id, category_image, sub_cat_id, category_name));
                                                    Log.d("@@addTotalList5", String.valueOf(sub_cat_name));

                                                }
                                                if (category_id.equals("24")) {

                                                    com.Appzia.addpanda.Fragments.mainFragment.list24.add(new categoryChildModel(image, sub_cat_name, category_id, category_image, sub_cat_id, category_name));
                                                    Log.d("@@addTotalList5", String.valueOf(sub_cat_name));

                                                }
                                                if (category_id.equals("25")) {

                                                    com.Appzia.addpanda.Fragments.mainFragment.list25.add(new categoryChildModel(image, sub_cat_name, category_id, category_image, sub_cat_id, category_name));
                                                    Log.d("@@addTotalList5", String.valueOf(sub_cat_name));

                                                }
                                                if (category_id.equals("26")) {

                                                    com.Appzia.addpanda.Fragments.mainFragment.list26.add(new categoryChildModel(image, sub_cat_name, category_id, category_image, sub_cat_id, category_name));
                                                    Log.d("@@addTotalList5", String.valueOf(sub_cat_name));

                                                }
                                                if (category_id.equals("27")) {

                                                    com.Appzia.addpanda.Fragments.mainFragment.list27.add(new categoryChildModel(image, sub_cat_name, category_id, category_image, sub_cat_id, category_name));
                                                    Log.d("@@addTotalList5", String.valueOf(sub_cat_name));

                                                }
                                                if (category_id.equals("28")) {

                                                    com.Appzia.addpanda.Fragments.mainFragment.list28.add(new categoryChildModel(image, sub_cat_name, category_id, category_image, sub_cat_id, category_name));
                                                    Log.d("@@addTotalList5", String.valueOf(sub_cat_name));

                                                }
                                                if (category_id.equals("29")) {

                                                    com.Appzia.addpanda.Fragments.mainFragment.list29.add(new categoryChildModel(image, sub_cat_name, category_id, category_image, sub_cat_id, category_name));
                                                    Log.d("@@addTotalList5", String.valueOf(sub_cat_name));

                                                }
                                                if (category_id.equals("30")) {

                                                    com.Appzia.addpanda.Fragments.mainFragment.list30.add(new categoryChildModel(image, sub_cat_name, category_id, category_image, sub_cat_id, category_name));
                                                    Log.d("@@addTotalList5", String.valueOf(sub_cat_name));

                                                }
                                                if (category_id.equals("31")) {

                                                    com.Appzia.addpanda.Fragments.mainFragment.list31.add(new categoryChildModel(image, sub_cat_name, category_id, category_image, sub_cat_id, category_name));
                                                    Log.d("@@addTotalList5", String.valueOf(sub_cat_name));

                                                }
                                                if (category_id.equals("32")) {

                                                    com.Appzia.addpanda.Fragments.mainFragment.list32.add(new categoryChildModel(image, sub_cat_name, category_id, category_image, sub_cat_id, category_name));
                                                    Log.d("@@addTotalList5", String.valueOf(sub_cat_name));

                                                }
                                                if (category_id.equals("33")) {

                                                    com.Appzia.addpanda.Fragments.mainFragment.list33.add(new categoryChildModel(image, sub_cat_name, category_id, category_image, sub_cat_id, category_name));
                                                    Log.d("@@addTotalList5", String.valueOf(sub_cat_name));

                                                }
                                                if (category_id.equals("34")) {

                                                    com.Appzia.addpanda.Fragments.mainFragment.list34.add(new categoryChildModel(image, sub_cat_name, category_id, category_image, sub_cat_id, category_name));
                                                    Log.d("@@addTotalList5", String.valueOf(sub_cat_name));

                                                }
                                                if (category_id.equals("35")) {

                                                    com.Appzia.addpanda.Fragments.mainFragment.list35.add(new categoryChildModel(image, sub_cat_name, category_id, category_image, sub_cat_id, category_name));
                                                    Log.d("@@addTotalList5", String.valueOf(sub_cat_name));

                                                }
                                                if (category_id.equals("36")) {

                                                    com.Appzia.addpanda.Fragments.mainFragment.list36.add(new categoryChildModel(image, sub_cat_name, category_id, category_image, sub_cat_id, category_name));
                                                    Log.d("@@addTotalList5", String.valueOf(sub_cat_name));

                                                }
                                                if (category_id.equals("37")) {

                                                    com.Appzia.addpanda.Fragments.mainFragment.list37.add(new categoryChildModel(image, sub_cat_name, category_id, category_image, sub_cat_id, category_name));
                                                    Log.d("@@addTotalList5", String.valueOf(sub_cat_name));

                                                }
                                                if (category_id.equals("38")) {

                                                    com.Appzia.addpanda.Fragments.mainFragment.list38.add(new categoryChildModel(image, sub_cat_name, category_id, category_image, sub_cat_id, category_name));
                                                    Log.d("@@addTotalList5", String.valueOf(sub_cat_name));

                                                }
                                                if (category_id.equals("39")) {

                                                    com.Appzia.addpanda.Fragments.mainFragment.list39.add(new categoryChildModel(image, sub_cat_name, category_id, category_image, sub_cat_id, category_name));
                                                    Log.d("@@addTotalList5", String.valueOf(sub_cat_name));

                                                }
                                                if (category_id.equals("40")) {

                                                    com.Appzia.addpanda.Fragments.mainFragment.list40.add(new categoryChildModel(image, sub_cat_name, category_id, category_image, sub_cat_id, category_name));
                                                    Log.d("@@addTotalList5", String.valueOf(sub_cat_name));

                                                }
                                                if (category_id.equals("41")) {

                                                    com.Appzia.addpanda.Fragments.mainFragment.list41.add(new categoryChildModel(image, sub_cat_name, category_id, category_image, sub_cat_id, category_name));
                                                    Log.d("@@addTotalList5", String.valueOf(sub_cat_name));

                                                }
                                                if (category_id.equals("42")) {

                                                    com.Appzia.addpanda.Fragments.mainFragment.list42.add(new categoryChildModel(image, sub_cat_name, category_id, category_image, sub_cat_id, category_name));
                                                    Log.d("@@addTotalList5", String.valueOf(sub_cat_name));

                                                }
                                                if (category_id.equals("43")) {

                                                    com.Appzia.addpanda.Fragments.mainFragment.list43.add(new categoryChildModel(image, sub_cat_name, category_id, category_image, sub_cat_id, category_name));
                                                    Log.d("@@addTotalList5", String.valueOf(sub_cat_name));

                                                }
                                                if (category_id.equals("44")) {

                                                    com.Appzia.addpanda.Fragments.mainFragment.list44.add(new categoryChildModel(image, sub_cat_name, category_id, category_image, sub_cat_id, category_name));
                                                    Log.d("@@addTotalList5", String.valueOf(sub_cat_name));

                                                }
                                                if (category_id.equals("45")) {

                                                    com.Appzia.addpanda.Fragments.mainFragment.list45.add(new categoryChildModel(image, sub_cat_name, category_id, category_image, sub_cat_id, category_name));
                                                    Log.d("@@addTotalList5", String.valueOf(sub_cat_name));

                                                }
                                                if (category_id.equals("46")) {

                                                    com.Appzia.addpanda.Fragments.mainFragment.list46.add(new categoryChildModel(image, sub_cat_name, category_id, category_image, sub_cat_id, category_name));
                                                    Log.d("@@addTotalList5", String.valueOf(sub_cat_name));

                                                }
                                                if (category_id.equals("47")) {

                                                    com.Appzia.addpanda.Fragments.mainFragment.list47.add(new categoryChildModel(image, sub_cat_name, category_id, category_image, sub_cat_id, category_name));
                                                    Log.d("@@addTotalList5", String.valueOf(sub_cat_name));

                                                }
                                                if (category_id.equals("48")) {

                                                    com.Appzia.addpanda.Fragments.mainFragment.list48.add(new categoryChildModel(image, sub_cat_name, category_id, category_image, sub_cat_id, category_name));
                                                    Log.d("@@addTotalList5", String.valueOf(sub_cat_name));

                                                }
                                                if (category_id.equals("49")) {

                                                    com.Appzia.addpanda.Fragments.mainFragment.list49.add(new categoryChildModel(image, sub_cat_name, category_id, category_image, sub_cat_id, category_name));
                                                    Log.d("@@addTotalList5", String.valueOf(sub_cat_name));

                                                }
                                                if (category_id.equals("50")) {

                                                    com.Appzia.addpanda.Fragments.mainFragment.list50.add(new categoryChildModel(image, sub_cat_name, category_id, category_image, sub_cat_id, category_name));
                                                    Log.d("@@addTotalList5", String.valueOf(sub_cat_name));

                                                }


                                            }
                                            //data are stored in list below

                                            if (category_id.equals("1")) {

                                                //com.Appzia.addpanda.Fragments.mainFragment.list1.add(new categoryChildModel(image, sub_cat_name, category_id, category_image, sub_cat_id,category_name));
                                                com.Appzia.addpanda.Fragments.mainFragment.parentModelArrayList.add(new categoryParentModel(category_name, category_id, com.Appzia.addpanda.Fragments.mainFragment.list1));
                                                Log.d("@@addTotalList1", String.valueOf(sub_cat_name));
                                            }

                                            if (category_id.equals("2")) {

                                                //  com.Appzia.addpanda.Fragments.mainFragment.list2.add(new categoryChildModel(image, sub_cat_name, category_id, category_image, sub_cat_id,category_name));
                                                com.Appzia.addpanda.Fragments.mainFragment.parentModelArrayList.add(new categoryParentModel(category_name, category_id, com.Appzia.addpanda.Fragments.mainFragment.list2));
                                                Log.d("@@addTotalList2", String.valueOf(sub_cat_name));
                                            }

                                            if (category_id.equals("3")) {

                                                //com.Appzia.addpanda.Fragments.mainFragment.list3.add(new categoryChildModel(image, sub_cat_name,category_id, category_id, category_image, sub_cat_id));
                                                com.Appzia.addpanda.Fragments.mainFragment.parentModelArrayList.add(new categoryParentModel(category_name, category_id, com.Appzia.addpanda.Fragments.mainFragment.list3));
                                                Log.d("@@addTotalList3", String.valueOf(sub_cat_name));
                                            }

                                            if (category_id.equals("4")) {

                                                //com.Appzia.addpanda.Fragments.mainFragment.list3.add(new categoryChildModel(image, sub_cat_name,category_id, category_id, category_image, sub_cat_id));
                                                com.Appzia.addpanda.Fragments.mainFragment.parentModelArrayList.add(new categoryParentModel(category_name, category_id, com.Appzia.addpanda.Fragments.mainFragment.list4));
                                                Log.d("@@addTotalList3", String.valueOf(sub_cat_name));
                                            }

                                            if (category_id.equals("5")) {

                                                //   com.Appzia.addpanda.Fragments.mainFragment.list4.add(new categoryChildModel(image, sub_cat_name,category_id, category_id, category_image, sub_cat_id));
                                                com.Appzia.addpanda.Fragments.mainFragment.parentModelArrayList.add(new categoryParentModel(category_name, category_id, com.Appzia.addpanda.Fragments.mainFragment.list5));
                                                Log.d("@@addTotalList5", String.valueOf(sub_cat_name));

                                            }

                                            if (category_id.equals("6")) {

//                                            com.Appzia.addpanda.Fragments.mainFragment.list6.add(new categoryChildModel(image, sub_cat_name,category_id, category_id, category_image, sub_cat_id));
                                                com.Appzia.addpanda.Fragments.mainFragment.parentModelArrayList.add(new categoryParentModel(category_name, category_id, com.Appzia.addpanda.Fragments.mainFragment.list6));

                                                Log.d("@@addTotalList5", String.valueOf(sub_cat_name));

                                            }
                                            if (category_id.equals("7")) {

//                                            com.Appzia.addpanda.Fragments.mainFragment.list7.add(new categoryChildModel(image, sub_cat_name,category_id, category_id, category_image, sub_cat_id));
                                                com.Appzia.addpanda.Fragments.mainFragment.parentModelArrayList.add(new categoryParentModel(category_name, category_id, com.Appzia.addpanda.Fragments.mainFragment.list7));
                                                Log.d("@@addTotalList5", String.valueOf(sub_cat_name));

                                            }
                                            if (category_id.equals("8")) {

//                                            com.Appzia.addpanda.Fragments.mainFragment.list8.add(new categoryChildModel(image, sub_cat_name,category_id, category_id, category_image, sub_cat_id));
                                                com.Appzia.addpanda.Fragments.mainFragment.parentModelArrayList.add(new categoryParentModel(category_name, category_id, com.Appzia.addpanda.Fragments.mainFragment.list8));
                                                Log.d("@@addTotalList5", String.valueOf(sub_cat_name));

                                            }
                                            if (category_id.equals("9")) {

//                                            com.Appzia.addpanda.Fragments.mainFragment.list9.add(new categoryChildModel(image, sub_cat_name,category_id, category_id, category_image, sub_cat_id));
                                                com.Appzia.addpanda.Fragments.mainFragment.parentModelArrayList.add(new categoryParentModel(category_name, category_id, com.Appzia.addpanda.Fragments.mainFragment.list9));
                                                Log.d("@@addTotalList5", String.valueOf(sub_cat_name));

                                            }
                                            if (category_id.equals("10")) {

//                                            com.Appzia.addpanda.Fragments.mainFragment.list10.add(new categoryChildModel(image, sub_cat_name,category_id, category_id, category_image, sub_cat_id));
                                                com.Appzia.addpanda.Fragments.mainFragment.parentModelArrayList.add(new categoryParentModel(category_name, category_id, com.Appzia.addpanda.Fragments.mainFragment.list10));
                                                Log.d("@@addTotalList5", String.valueOf(sub_cat_name));

                                            }
                                            if (category_id.equals("11")) {

//                                            com.Appzia.addpanda.Fragments.mainFragment.list11.add(new categoryChildModel(image, sub_cat_name,category_id, category_id, category_image, sub_cat_id));
                                                com.Appzia.addpanda.Fragments.mainFragment.parentModelArrayList.add(new categoryParentModel(category_name, category_id, com.Appzia.addpanda.Fragments.mainFragment.list11));
                                                Log.d("@@addTotalList5", String.valueOf(sub_cat_name));

                                            }
                                            if (category_id.equals("12")) {

//                                            com.Appzia.addpanda.Fragments.mainFragment.list12.add(new categoryChildModel(image, sub_cat_name,category_id, category_id, category_image, sub_cat_id));
                                                com.Appzia.addpanda.Fragments.mainFragment.parentModelArrayList.add(new categoryParentModel(category_name, category_id, com.Appzia.addpanda.Fragments.mainFragment.list12));
                                                Log.d("@@addTotalList5", String.valueOf(sub_cat_name));

                                            }
                                            if (category_id.equals("13")) {

//                                            com.Appzia.addpanda.Fragments.mainFragment.list13.add(new categoryChildModel(image, sub_cat_name,category_id, category_id, category_image, sub_cat_id));
                                                com.Appzia.addpanda.Fragments.mainFragment.parentModelArrayList.add(new categoryParentModel(category_name, category_id, com.Appzia.addpanda.Fragments.mainFragment.list13));
                                                Log.d("@@addTotalList5", String.valueOf(sub_cat_name));

                                            }
                                            if (category_id.equals("14")) {

//                                            com.Appzia.addpanda.Fragments.mainFragment.list14.add(new categoryChildModel(image, sub_cat_name,category_id, category_id, category_image, sub_cat_id));
                                                com.Appzia.addpanda.Fragments.mainFragment.parentModelArrayList.add(new categoryParentModel(category_name, category_id, com.Appzia.addpanda.Fragments.mainFragment.list14));
                                                Log.d("@@addTotalList5", String.valueOf(sub_cat_name));

                                            }
                                            if (category_id.equals("15")) {

//                                            com.Appzia.addpanda.Fragments.mainFragment.list15.add(new categoryChildModel(image, sub_cat_name,category_id, category_id, category_image, sub_cat_id));
                                                com.Appzia.addpanda.Fragments.mainFragment.parentModelArrayList.add(new categoryParentModel(category_name, category_id, com.Appzia.addpanda.Fragments.mainFragment.list15));
                                                Log.d("@@addTotalList5", String.valueOf(sub_cat_name));

                                            }
                                            if (category_id.equals("16")) {

//                                            com.Appzia.addpanda.Fragments.mainFragment.list16.add(new categoryChildModel(image, sub_cat_name,category_id, category_id, category_image, sub_cat_id));
                                                com.Appzia.addpanda.Fragments.mainFragment.parentModelArrayList.add(new categoryParentModel(category_name, category_id, com.Appzia.addpanda.Fragments.mainFragment.list16));
                                                Log.d("@@addTotalList5", String.valueOf(sub_cat_name));

                                            }
                                            if (category_id.equals("17")) {

//                                            com.Appzia.addpanda.Fragments.mainFragment.list17.add(new categoryChildModel(image, sub_cat_name,category_id, category_id, category_image, sub_cat_id));
                                                com.Appzia.addpanda.Fragments.mainFragment.parentModelArrayList.add(new categoryParentModel(category_name, category_id, com.Appzia.addpanda.Fragments.mainFragment.list17));
                                                Log.d("@@addTotalList5", String.valueOf(sub_cat_name));

                                            }
                                            if (category_id.equals("18")) {

//                                            com.Appzia.addpanda.Fragments.mainFragment.list18.add(new categoryChildModel(image, sub_cat_name,category_id, category_id, category_image, sub_cat_id));
                                                com.Appzia.addpanda.Fragments.mainFragment.parentModelArrayList.add(new categoryParentModel(category_name, category_id, com.Appzia.addpanda.Fragments.mainFragment.list18));
                                                Log.d("@@addTotalList5", String.valueOf(sub_cat_name));

                                            }
                                            if (category_id.equals("19")) {

//                                            com.Appzia.addpanda.Fragments.mainFragment.list19.add(new categoryChildModel(image, sub_cat_name,category_id, category_id, category_image, sub_cat_id));
                                                com.Appzia.addpanda.Fragments.mainFragment.parentModelArrayList.add(new categoryParentModel(category_name, category_id, com.Appzia.addpanda.Fragments.mainFragment.list19));
                                                Log.d("@@addTotalList5", String.valueOf(sub_cat_name));

                                            }
                                            if (category_id.equals("20")) {

//                                            com.Appzia.addpanda.Fragments.mainFragment.list20.add(new categoryChildModel(image, sub_cat_name,category_id, category_id, category_image, sub_cat_id));
                                                com.Appzia.addpanda.Fragments.mainFragment.parentModelArrayList.add(new categoryParentModel(category_name, category_id, com.Appzia.addpanda.Fragments.mainFragment.list20));
                                                Log.d("@@addTotalList5", String.valueOf(sub_cat_name));

                                            }
                                            if (category_id.equals("21")) {

//                                            com.Appzia.addpanda.Fragments.mainFragment.list21.add(new categoryChildModel(image, sub_cat_name,category_id, category_id, category_image, sub_cat_id));
                                                com.Appzia.addpanda.Fragments.mainFragment.parentModelArrayList.add(new categoryParentModel(category_name, category_id, com.Appzia.addpanda.Fragments.mainFragment.list21));
                                                Log.d("@@addTotalList5", String.valueOf(sub_cat_name));

                                            }
                                            if (category_id.equals("22")) {

//                                            com.Appzia.addpanda.Fragments.mainFragment.list5.add(new categoryChildModel(image, sub_cat_name,category_id, category_id, category_image, sub_cat_id));
                                                com.Appzia.addpanda.Fragments.mainFragment.parentModelArrayList.add(new categoryParentModel(category_name, category_id, com.Appzia.addpanda.Fragments.mainFragment.list22));
                                                Log.d("@@addTotalList5", String.valueOf(sub_cat_name));

                                            }
                                            if (category_id.equals("23")) {

//                                            com.Appzia.addpanda.Fragments.mainFragment.list22.add(new categoryChildModel(image, sub_cat_name,category_id, category_id, category_image, sub_cat_id));
                                                com.Appzia.addpanda.Fragments.mainFragment.parentModelArrayList.add(new categoryParentModel(category_name, category_id, com.Appzia.addpanda.Fragments.mainFragment.list23));
                                                Log.d("@@addTotalList5", String.valueOf(sub_cat_name));

                                            }
                                            if (category_id.equals("24")) {

//                                            com.Appzia.addpanda.Fragments.mainFragment.list23.add(new categoryChildModel(image, sub_cat_name,category_id, category_id, category_image, sub_cat_id));
                                                com.Appzia.addpanda.Fragments.mainFragment.parentModelArrayList.add(new categoryParentModel(category_name, category_id, com.Appzia.addpanda.Fragments.mainFragment.list24));
                                                Log.d("@@addTotalList5", String.valueOf(sub_cat_name));

                                            }
                                            if (category_id.equals("25")) {

//                                            com.Appzia.addpanda.Fragments.mainFragment.list24.add(new categoryChildModel(image, sub_cat_name,category_id, category_id, category_image, sub_cat_id));
                                                com.Appzia.addpanda.Fragments.mainFragment.parentModelArrayList.add(new categoryParentModel(category_name, category_id, com.Appzia.addpanda.Fragments.mainFragment.list25));
                                                Log.d("@@addTotalList5", String.valueOf(sub_cat_name));

                                            }
                                            if (category_id.equals("26")) {

//                                            com.Appzia.addpanda.Fragments.mainFragment.list25.add(new categoryChildModel(image, sub_cat_name,category_id, category_id, category_image, sub_cat_id));
                                                com.Appzia.addpanda.Fragments.mainFragment.parentModelArrayList.add(new categoryParentModel(category_name, category_id, com.Appzia.addpanda.Fragments.mainFragment.list26));
                                                Log.d("@@addTotalList5", String.valueOf(sub_cat_name));

                                            }
                                            if (category_id.equals("27")) {

//                                            com.Appzia.addpanda.Fragments.mainFragment.list26.add(new categoryChildModel(image, sub_cat_name,category_id, category_id, category_image, sub_cat_id));
                                                com.Appzia.addpanda.Fragments.mainFragment.parentModelArrayList.add(new categoryParentModel(category_name, category_id, com.Appzia.addpanda.Fragments.mainFragment.list27));
                                                Log.d("@@addTotalList5", String.valueOf(sub_cat_name));

                                            }
                                            if (category_id.equals("28")) {

//                                            com.Appzia.addpanda.Fragments.mainFragment.list28.add(new categoryChildModel(image, sub_cat_name,category_id, category_id, category_image, sub_cat_id));
                                                com.Appzia.addpanda.Fragments.mainFragment.parentModelArrayList.add(new categoryParentModel(category_name, category_id, com.Appzia.addpanda.Fragments.mainFragment.list28));
                                                Log.d("@@addTotalList5", String.valueOf(sub_cat_name));

                                            }
                                            if (category_id.equals("29")) {

//                                            com.Appzia.addpanda.Fragments.mainFragment.list29.add(new categoryChildModel(image, sub_cat_name,category_id, category_id, category_image, sub_cat_id));
                                                com.Appzia.addpanda.Fragments.mainFragment.parentModelArrayList.add(new categoryParentModel(category_name, category_id, com.Appzia.addpanda.Fragments.mainFragment.list29));
                                                Log.d("@@addTotalList5", String.valueOf(sub_cat_name));

                                            }
                                            if (category_id.equals("30")) {

//                                            com.Appzia.addpanda.Fragments.mainFragment.list30.add(new categoryChildModel(image, sub_cat_name,category_id, category_id, category_image, sub_cat_id));
                                                com.Appzia.addpanda.Fragments.mainFragment.parentModelArrayList.add(new categoryParentModel(category_name, category_id, com.Appzia.addpanda.Fragments.mainFragment.list30));
                                                Log.d("@@addTotalList5", String.valueOf(sub_cat_name));

                                            }
                                            if (category_id.equals("31")) {

//                                            com.Appzia.addpanda.Fragments.mainFragment.list31.add(new categoryChildModel(image, sub_cat_name,category_id, category_id, category_image, sub_cat_id));
                                                com.Appzia.addpanda.Fragments.mainFragment.parentModelArrayList.add(new categoryParentModel(category_name, category_id, com.Appzia.addpanda.Fragments.mainFragment.list31));
                                                Log.d("@@addTotalList5", String.valueOf(sub_cat_name));

                                            }
                                            if (category_id.equals("32")) {

//                                            com.Appzia.addpanda.Fragments.mainFragment.list32.add(new categoryChildModel(image, sub_cat_name,category_id, category_id, category_image, sub_cat_id));
                                                com.Appzia.addpanda.Fragments.mainFragment.parentModelArrayList.add(new categoryParentModel(category_name, category_id, com.Appzia.addpanda.Fragments.mainFragment.list32));
                                                Log.d("@@addTotalList5", String.valueOf(sub_cat_name));

                                            }
                                            if (category_id.equals("33")) {

//                                            com.Appzia.addpanda.Fragments.mainFragment.list33.add(new categoryChildModel(image, sub_cat_name,category_id, category_id, category_image, sub_cat_id));
                                                com.Appzia.addpanda.Fragments.mainFragment.parentModelArrayList.add(new categoryParentModel(category_name, category_id, com.Appzia.addpanda.Fragments.mainFragment.list33));
                                                Log.d("@@addTotalList5", String.valueOf(sub_cat_name));

                                            }
                                            if (category_id.equals("34")) {

//                                            com.Appzia.addpanda.Fragments.mainFragment.list34.add(new categoryChildModel(image, sub_cat_name,category_id, category_id, category_image, sub_cat_id));
                                                com.Appzia.addpanda.Fragments.mainFragment.parentModelArrayList.add(new categoryParentModel(category_name, category_id, com.Appzia.addpanda.Fragments.mainFragment.list34));
                                                Log.d("@@addTotalList5", String.valueOf(sub_cat_name));

                                            }
                                            if (category_id.equals("35")) {

//                                            com.Appzia.addpanda.Fragments.mainFragment.list35.add(new categoryChildModel(image, sub_cat_name,category_id, category_id, category_image, sub_cat_id));
                                                com.Appzia.addpanda.Fragments.mainFragment.parentModelArrayList.add(new categoryParentModel(category_name, category_id, com.Appzia.addpanda.Fragments.mainFragment.list35));
                                                Log.d("@@addTotalList5", String.valueOf(sub_cat_name));

                                            }
                                            if (category_id.equals("36")) {

//                                            com.Appzia.addpanda.Fragments.mainFragment.list36.add(new categoryChildModel(image, sub_cat_name,category_id, category_id, category_image, sub_cat_id));
                                                com.Appzia.addpanda.Fragments.mainFragment.parentModelArrayList.add(new categoryParentModel(category_name, category_id, com.Appzia.addpanda.Fragments.mainFragment.list36));
                                                Log.d("@@addTotalList5", String.valueOf(sub_cat_name));

                                            }
                                            if (category_id.equals("37")) {

//                                            com.Appzia.addpanda.Fragments.mainFragment.list37.add(new categoryChildModel(image, sub_cat_name,category_id, category_id, category_image, sub_cat_id));
                                                com.Appzia.addpanda.Fragments.mainFragment.parentModelArrayList.add(new categoryParentModel(category_name, category_id, com.Appzia.addpanda.Fragments.mainFragment.list37));
                                                Log.d("@@addTotalList5", String.valueOf(sub_cat_name));

                                            }
                                            if (category_id.equals("38")) {

//                                            com.Appzia.addpanda.Fragments.mainFragment.list38.add(new categoryChildModel(image, sub_cat_name,category_id, category_id, category_image, sub_cat_id));
                                                com.Appzia.addpanda.Fragments.mainFragment.parentModelArrayList.add(new categoryParentModel(category_name, category_id, com.Appzia.addpanda.Fragments.mainFragment.list38));
                                                Log.d("@@addTotalList5", String.valueOf(sub_cat_name));

                                            }
                                            if (category_id.equals("39")) {

//                                            com.Appzia.addpanda.Fragments.mainFragment.list39.add(new categoryChildModel(image, sub_cat_name,category_id, category_id, category_image, sub_cat_id));
                                                com.Appzia.addpanda.Fragments.mainFragment.parentModelArrayList.add(new categoryParentModel(category_name, category_id, com.Appzia.addpanda.Fragments.mainFragment.list39));
                                                Log.d("@@addTotalList5", String.valueOf(sub_cat_name));

                                            }
                                            if (category_id.equals("40")) {

//                                            com.Appzia.addpanda.Fragments.mainFragment.list40.add(new categoryChildModel(image, sub_cat_name,category_id, category_id, category_image, sub_cat_id));
                                                com.Appzia.addpanda.Fragments.mainFragment.parentModelArrayList.add(new categoryParentModel(category_name, category_id, com.Appzia.addpanda.Fragments.mainFragment.list40));
                                                Log.d("@@addTotalList5", String.valueOf(sub_cat_name));

                                            }
                                            if (category_id.equals("41")) {

//                                            com.Appzia.addpanda.Fragments.mainFragment.list41.add(new categoryChildModel(image, sub_cat_name,category_id, category_id, category_image, sub_cat_id));
                                                com.Appzia.addpanda.Fragments.mainFragment.parentModelArrayList.add(new categoryParentModel(category_name, category_id, com.Appzia.addpanda.Fragments.mainFragment.list41));
                                                Log.d("@@addTotalList5", String.valueOf(sub_cat_name));

                                            }
                                            if (category_id.equals("42")) {

//                                            com.Appzia.addpanda.Fragments.mainFragment.list42.add(new categoryChildModel(image, sub_cat_name,category_id, category_id, category_image, sub_cat_id));
                                                com.Appzia.addpanda.Fragments.mainFragment.parentModelArrayList.add(new categoryParentModel(category_name, category_id, com.Appzia.addpanda.Fragments.mainFragment.list42));
                                                Log.d("@@addTotalList5", String.valueOf(sub_cat_name));

                                            }
                                            if (category_id.equals("43")) {

//                                            com.Appzia.addpanda.Fragments.mainFragment.list43.add(new categoryChildModel(image, sub_cat_name,category_id, category_id, category_image, sub_cat_id));
                                                com.Appzia.addpanda.Fragments.mainFragment.parentModelArrayList.add(new categoryParentModel(category_name, category_id, com.Appzia.addpanda.Fragments.mainFragment.list43));
                                                Log.d("@@addTotalList5", String.valueOf(sub_cat_name));

                                            }
                                            if (category_id.equals("44")) {

//                                            com.Appzia.addpanda.Fragments.mainFragment.list44.add(new categoryChildModel(image, sub_cat_name,category_id, category_id, category_image, sub_cat_id));
                                                com.Appzia.addpanda.Fragments.mainFragment.parentModelArrayList.add(new categoryParentModel(category_name, category_id, com.Appzia.addpanda.Fragments.mainFragment.list44));
                                                Log.d("@@addTotalList5", String.valueOf(sub_cat_name));

                                            }
                                            if (category_id.equals("45")) {

//                                            com.Appzia.addpanda.Fragments.mainFragment.list45.add(new categoryChildModel(image, sub_cat_name,category_id, category_id, category_image, sub_cat_id));
                                                com.Appzia.addpanda.Fragments.mainFragment.parentModelArrayList.add(new categoryParentModel(category_name, category_id, com.Appzia.addpanda.Fragments.mainFragment.list45));
                                                Log.d("@@addTotalList5", String.valueOf(sub_cat_name));

                                            }
                                            if (category_id.equals("46")) {

//                                            com.Appzia.addpanda.Fragments.mainFragment.list46.add(new categoryChildModel(image, sub_cat_name,category_id, category_id, category_image, sub_cat_id));
                                                com.Appzia.addpanda.Fragments.mainFragment.parentModelArrayList.add(new categoryParentModel(category_name, category_id, com.Appzia.addpanda.Fragments.mainFragment.list46));
                                                Log.d("@@addTotalList5", String.valueOf(sub_cat_name));

                                            }
                                            if (category_id.equals("47")) {

//                                            com.Appzia.addpanda.Fragments.mainFragment.list47.add(new categoryChildModel(image, sub_cat_name,category_id, category_id, category_image, sub_cat_id));
                                                com.Appzia.addpanda.Fragments.mainFragment.parentModelArrayList.add(new categoryParentModel(category_name, category_id, com.Appzia.addpanda.Fragments.mainFragment.list47));
                                                Log.d("@@addTotalList5", String.valueOf(sub_cat_name));

                                            }
                                            if (category_id.equals("48")) {

//                                            com.Appzia.addpanda.Fragments.mainFragment.list48.add(new categoryChildModel(image, sub_cat_name,category_id, category_id, category_image, sub_cat_id));
                                                com.Appzia.addpanda.Fragments.mainFragment.parentModelArrayList.add(new categoryParentModel(category_name, category_id, com.Appzia.addpanda.Fragments.mainFragment.list48));
                                                Log.d("@@addTotalList5", String.valueOf(sub_cat_name));

                                            }
                                            if (category_id.equals("49")) {

//                                            com.Appzia.addpanda.Fragments.mainFragment.list49.add(new categoryChildModel(image, sub_cat_name,category_id, category_id, category_image, sub_cat_id));
                                                com.Appzia.addpanda.Fragments.mainFragment.parentModelArrayList.add(new categoryParentModel(category_name, category_id, com.Appzia.addpanda.Fragments.mainFragment.list49));
                                                Log.d("@@addTotalList5", String.valueOf(sub_cat_name));

                                            }
                                            if (category_id.equals("50")) {

//                                            com.Appzia.addpanda.Fragments.mainFragment.list50.add(new categoryChildModel(image, sub_cat_name,category_id, category_id, category_image, sub_cat_id));
                                                com.Appzia.addpanda.Fragments.mainFragment.parentModelArrayList.add(new categoryParentModel(category_name, category_id, com.Appzia.addpanda.Fragments.mainFragment.list50));
                                                Log.d("@@addTotalList5", String.valueOf(sub_cat_name));

                                            }
                                            com.Appzia.addpanda.Fragments.mainFragment.shimmerFrameLayout.stopShimmer();
                                            com.Appzia.addpanda.Fragments.mainFragment.whatsapp.setVisibility(View.VISIBLE);
                                            com.Appzia.addpanda.Fragments.mainFragment.CoLayout.setVisibility(View.VISIBLE);
                                            com.Appzia.addpanda.Fragments.mainFragment.shimmerFrameLayout.setVisibility(View.GONE);
                                        }


                                    }


                                    mainFragment.setAdapter();


                                } else {
                                    String message = response.getString("message");
                                    // Toast.makeText(mContext, message, Toast.LENGTH_SHORT).show();
                                }
                            } catch (JSONException e) {

                                e.printStackTrace();

                                Log.d("@@@ notSuccess: ", e.getMessage());
                            }


                            super.onSuccess(statusCode, headers, response);
                        }

                        @Override
                        public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {


                            super.onFailure(statusCode, headers, responseString, throwable);
                        }

                        @Override
                        public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {


                            super.onFailure(statusCode, headers, throwable, errorResponse);
                        }

                        @Override
                        public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONArray errorResponse) {


                            super.onFailure(statusCode, headers, throwable, errorResponse);
                        }
                    });

                }
            });
        } catch (Exception e) {


            e.printStackTrace();
        }
    }

    public static void get_Template_list(final Context mContext, String token, String category_id, String sub_cat_id, trendingActivity trendingActivity, String tempLang) {

        try {
            Constant.trendingSubList.clear();
            final AsyncHttpClient client = new AsyncHttpClient();
            client.setSSLSocketFactory(MySSLSocketFactory.getFixedSocketFactory());
            final RequestParams request_param = new RequestParams();
            request_param.put("category_id", category_id);
            request_param.put("sub_cat_id", sub_cat_id);


            Log.d(TAG, "@@@GET_TEMPLATEss :" + GET_TEMPLATE + "?" + request_param.toString());
            ((Activity) mContext).runOnUiThread(new Runnable() {
                public void run() {
                    com.Appzia.addpanda.Screens.trendingActivity.shimmerFrameLayout.startShimmer();

                    client.addHeader("token", token);
                    client.post(GET_TEMPLATE, request_param, new JsonHttpResponseHandler() {
                        @Override
                        public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                            Log.d(TAG, "@@@GET_TEMPLATE_response :" + response.toString());

                            try {
                                int status = response.getInt("error_code");


                                if (status == 200) {


                                    JSONArray arr = response.getJSONArray("data");
                                    JSONObject obj1 = arr.getJSONObject(0);
                                    JSONObject obj2 = null;
                                    for (int i = 0; i < arr.length(); i++) {
                                        obj2 = arr.getJSONObject(i);
                                        //    String image = obj2.getString("image");
                                        String template_id = obj2.getString("template_id");
                                        String template_lang = obj2.getString("template_lang");
                                        String template_edit_type_flag = obj2.getString("template_edit_type_flag");
                                        int is_active = obj2.getInt("is_active");
                                        //    Toast.makeText(mContext, template_edit_type_flag, Toast.LENGTH_SHORT).show();


                                        if (template_lang.equals(tempLang)) {


//                                            if (template_edit_type_flag.equals("1")) {

                                            JSONArray imgArray = obj2.getJSONArray("image");
                                            for (int k = 0; k < imgArray.length(); k++) {

                                                JSONObject object = imgArray.getJSONObject(k);
                                                String image = object.getString("t_img");
                                                //     Toast.makeText(mContext, image, Toast.LENGTH_SHORT).show();
                                                Constant.trendingSubList.add(new trendingSubModel(image, category_id, sub_cat_id, template_id, "", is_active));
                                            }
//


                                        }


                                    }


                                    assert obj2 != null;
                                    String template_id2 = obj1.getString("template_id");
                                    Log.d("##template_id", template_id2);


                                    com.Appzia.addpanda.Screens.trendingActivity.template_idKey = template_id2;

                                    com.Appzia.addpanda.Screens.trendingActivity.shimmerFrameLayout.stopShimmer();
                                    com.Appzia.addpanda.Screens.trendingActivity.shimmerFrameLayout.setVisibility(View.GONE);
                                    com.Appzia.addpanda.Screens.trendingActivity.CoLayout.setVisibility(View.VISIBLE);


                                    com.Appzia.addpanda.Screens.trendingActivity.setAdapter();


                                } else {
                                    String message = response.getString("message");
                                    Toast.makeText(mContext, message, Toast.LENGTH_SHORT).show();
                                }
                            } catch (JSONException e) {

                                e.printStackTrace();

                                Log.d("@@@ notSuccess: ", e.getMessage());
                            }
                            super.onSuccess(statusCode, headers, response);
                        }

                        @Override
                        public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {


                            super.onFailure(statusCode, headers, responseString, throwable);
                        }

                        @Override
                        public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {


                            super.onFailure(statusCode, headers, throwable, errorResponse);
                        }

                        @Override
                        public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONArray errorResponse) {


                            super.onFailure(statusCode, headers, throwable, errorResponse);
                        }
                    });
                }
            });
        } catch (Exception e) {


            e.printStackTrace();
        }
    }

    public static void get_Template_list_Basic(final Context mContext, String token, String category_id, String sub_cat_id, trendingActivityBasic trendingActivity) {

        try {
            Constant.trendingSubList.clear();
            final AsyncHttpClient client = new AsyncHttpClient();
            client.setSSLSocketFactory(MySSLSocketFactory.getFixedSocketFactory());
            final RequestParams request_param = new RequestParams();
            request_param.put("category_id", category_id);
            request_param.put("sub_cat_id", sub_cat_id);


            Log.d(TAG, "@@@GET_TEMPLATE :" + GET_TEMPLATE + "?" + request_param.toString());
            ((Activity) mContext).runOnUiThread(new Runnable() {
                public void run() {
                    com.Appzia.addpanda.Screens.trendingActivityBasic.progressBar.setVisibility(View.VISIBLE);

                    client.addHeader("token", token);
                    client.post(GET_TEMPLATE, request_param, new JsonHttpResponseHandler() {
                        @Override
                        public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                            Log.d(TAG, "@@@GET_TEMPLATE_response :" + response.toString());

                            try {
                                int status = response.getInt("error_code");


                                if (status == 200) {


                                    JSONArray arr = response.getJSONArray("data");
                                    JSONObject obj1 = arr.getJSONObject(0);

                                    for (int i = 0; i < arr.length(); i++) {
                                        JSONObject obj2 = arr.getJSONObject(i);
                                        String image = obj2.getString("image");
                                        String template_id = obj2.getString("template_id");
                                        int is_active = obj2.getInt("is_active");

                                        Log.d("@@@image", image);

                                        Constant.trendingSubList.add(new trendingSubModel(image, category_id, sub_cat_id, template_id, is_active));
//                                        Constant.pd.d();

                                    }

                                    String imgSingleFirst = obj1.getString("image");
                                    Log.d("imgSingleFirst", imgSingleFirst);

                                    Picasso.get().load(imgSingleFirst).placeholder(R.color.white).into(com.Appzia.addpanda.Screens.trendingActivityBasic.viewPagerBackImage);
                                    com.Appzia.addpanda.Screens.trendingActivityBasic.progressBar.setVisibility(View.INVISIBLE);


                                    com.Appzia.addpanda.Screens.trendingActivityBasic.setAdapter();


                                } else {
                                    String message = response.getString("message");
                                    // Toast.makeText(mContext, message, Toast.LENGTH_SHORT).show();
                                }
                            } catch (JSONException e) {

                                e.printStackTrace();

                                Log.d("@@@ notSuccess: ", e.getMessage());
                            }
                            super.onSuccess(statusCode, headers, response);
                        }

                        @Override
                        public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {


                            super.onFailure(statusCode, headers, responseString, throwable);
                        }

                        @Override
                        public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {


                            super.onFailure(statusCode, headers, throwable, errorResponse);
                        }

                        @Override
                        public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONArray errorResponse) {


                            super.onFailure(statusCode, headers, throwable, errorResponse);
                        }
                    });
                }
            });
        } catch (Exception e) {


            e.printStackTrace();
        }
    }

    public static void get_category_list_All_BasicEdit(final Context mContext, String token, basicEditsFirstActivity fragment, String flag) {

        try {

            final AsyncHttpClient client = new AsyncHttpClient();
            client.setSSLSocketFactory(MySSLSocketFactory.getFixedSocketFactory());
            final RequestParams request_param = new RequestParams();
            request_param.put("flag", flag);

            Constant.mainCatBtnModelList.clear();
            basicEditsFirstActivity.parentModelArrayList.clear();
            basicEditsFirstActivity.list1.clear();

            basicEditsFirstActivity.shimmerFrameLayout.startShimmer();
            Log.d(TAG, "@@@basiceditTrendingModelSubList :" + get_category_list_All_BASIC_EDITS + "?" + request_param.toString());
            ((Activity) mContext).runOnUiThread(new Runnable() {
                public void run() {


                    client.addHeader("token", token);
                    client.post(get_category_list_All_BASIC_EDITS, request_param, new JsonHttpResponseHandler() {
                        @Override
                        public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                            Log.d(TAG, "@@@basiceditTrendingModelSubList_response :" + response.toString());

                            try {
                                int status = response.getInt("error_code");


                                if (status == 200) {


                                    JSONArray arr = response.getJSONArray("data");


                                    for (int i = 0; i < arr.length(); i++) {
                                        JSONObject obj2 = arr.getJSONObject(i);
                                        String category_name = obj2.getString("category_name");

                                        String category_image = obj2.getString("category_image");
                                        String category_id = obj2.getString("category_id");


                                        JSONArray sub_category_list = obj2.getJSONArray("subcategory");
                                        Log.d("@@@category_nameZZnew", String.valueOf(category_name));
                                        String category_type_flag = obj2.getString("category_type_flag");


                                        String sub_cat_name = null;
                                        String sub_cat_id = null;
                                        String image = null;

                                        JSONObject obj3;
                                        Log.d("@@catid", String.valueOf(category_id));

                                        Constant.getSfFuncion(mContext);
                                        String accountType = Constant.getSF.getString(Constant.ACC_TYPE, "");


                                        if (accountType.equals("1") && category_type_flag.equals("1")) {
                                            Constant.mainCatBtnModelList.add(new mainCatBtnChildModel(category_name, category_id));
                                            for (int j = 0; j < sub_category_list.length(); j++) {
                                                obj3 = sub_category_list.getJSONObject(j);

                                                sub_cat_name = obj3.getString("sub_cat_name");
                                                sub_cat_id = obj3.getString("sub_cat_id");
                                                image = obj3.getString("image");


                                                if (category_id.equals("1")) {

                                                    com.Appzia.addpanda.Screens.basicEditsFirstActivity.list1.add(new categoryChildModel(image, sub_cat_name, category_id, category_image, sub_cat_id, category_name));
                                                    //       com.Appzia.addpanda.Screens.basicEditsFirstActivity.parentModelArrayList.add(new categoryParentModel(category_name, com.Appzia.addpanda.Fragments.mainFragment.list1));
                                                    Log.d("@@addTotalList1", String.valueOf(sub_cat_name));
                                                }

                                                if (category_id.equals("2")) {

                                                    com.Appzia.addpanda.Screens.basicEditsFirstActivity.list2.add(new categoryChildModel(image, sub_cat_name, category_id, category_image, sub_cat_id, category_name));
                                                    // com.Appzia.addpanda.Fragments.mainFragment.parentModelArrayList.add(new categoryParentModel(category_name, com.Appzia.addpanda.Fragments.mainFragment.list2));
                                                    Log.d("@@addTotalList2", String.valueOf(sub_cat_name));
                                                }

                                                if (category_id.equals("3")) {

                                                    com.Appzia.addpanda.Screens.basicEditsFirstActivity.list3.add(new categoryChildModel(image, sub_cat_name, category_id, category_image, sub_cat_id, category_name));
                                                    //     com.Appzia.addpanda.Screens.basicEditsFirstActivity.parentModelArrayList.add(new categoryParentModel(category_name, com.Appzia.addpanda.Fragments.mainFragment.list3));
                                                    Log.d("@@addTotalList3", String.valueOf(sub_cat_name));
                                                }

                                                if (category_id.equals("4")) {

                                                    com.Appzia.addpanda.Screens.basicEditsFirstActivity.list4.add(new categoryChildModel(image, sub_cat_name, category_id, category_image, sub_cat_id, category_name));
                                                    Log.d("@@addTotalList5", String.valueOf(sub_cat_name));

                                                }
                                                if (category_id.equals("5")) {

                                                    com.Appzia.addpanda.Screens.basicEditsFirstActivity.list5.add(new categoryChildModel(image, sub_cat_name, category_id, category_image, sub_cat_id, category_name));
                                                    Log.d("@@addTotalList5", String.valueOf(sub_cat_name));

                                                }
                                                if (category_id.equals("6")) {

                                                    com.Appzia.addpanda.Screens.basicEditsFirstActivity.list6.add(new categoryChildModel(image, sub_cat_name, category_id, category_image, sub_cat_id, category_name));
                                                    Log.d("@@addTotalList5", String.valueOf(sub_cat_name));

                                                }
                                                if (category_id.equals("7")) {

                                                    com.Appzia.addpanda.Screens.basicEditsFirstActivity.list7.add(new categoryChildModel(image, sub_cat_name, category_id, category_image, sub_cat_id, category_name));
                                                    Log.d("@@addTotalList5", String.valueOf(sub_cat_name));

                                                }
                                                if (category_id.equals("8")) {

                                                    com.Appzia.addpanda.Screens.basicEditsFirstActivity.list8.add(new categoryChildModel(image, sub_cat_name, category_id, category_image, sub_cat_id, category_name));
                                                    Log.d("@@addTotalList5", String.valueOf(sub_cat_name));

                                                }
                                                if (category_id.equals("9")) {

                                                    com.Appzia.addpanda.Screens.basicEditsFirstActivity.list9.add(new categoryChildModel(image, sub_cat_name, category_id, category_image, sub_cat_id, category_name));
                                                    Log.d("@@addTotalList5", String.valueOf(sub_cat_name));

                                                }
                                                if (category_id.equals("10")) {

                                                    com.Appzia.addpanda.Screens.basicEditsFirstActivity.list10.add(new categoryChildModel(image, sub_cat_name, category_id, category_image, sub_cat_id, category_name));
                                                    Log.d("@@addTotalList5", String.valueOf(sub_cat_name));

                                                }
                                                if (category_id.equals("11")) {

                                                    com.Appzia.addpanda.Screens.basicEditsFirstActivity.list11.add(new categoryChildModel(image, sub_cat_name, category_id, category_image, sub_cat_id, category_name));
                                                    Log.d("@@addTotalList5", String.valueOf(sub_cat_name));

                                                }
                                                if (category_id.equals("12")) {

                                                    com.Appzia.addpanda.Screens.basicEditsFirstActivity.list12.add(new categoryChildModel(image, sub_cat_name, category_id, category_image, sub_cat_id, category_name));
                                                    Log.d("@@addTotalList5", String.valueOf(sub_cat_name));

                                                }
                                                if (category_id.equals("13")) {

                                                    com.Appzia.addpanda.Screens.basicEditsFirstActivity.list13.add(new categoryChildModel(image, sub_cat_name, category_id, category_image, sub_cat_id, category_name));
                                                    Log.d("@@addTotalList5", String.valueOf(sub_cat_name));

                                                }
                                                if (category_id.equals("14")) {

                                                    com.Appzia.addpanda.Screens.basicEditsFirstActivity.list14.add(new categoryChildModel(image, sub_cat_name, category_id, category_image, sub_cat_id, category_name));
                                                    Log.d("@@addTotalList5", String.valueOf(sub_cat_name));

                                                }
                                                if (category_id.equals("15")) {

                                                    com.Appzia.addpanda.Screens.basicEditsFirstActivity.list15.add(new categoryChildModel(image, sub_cat_name, category_id, category_image, sub_cat_id, category_name));
                                                    Log.d("@@addTotalList5", String.valueOf(sub_cat_name));

                                                }
                                                if (category_id.equals("16")) {

                                                    com.Appzia.addpanda.Screens.basicEditsFirstActivity.list16.add(new categoryChildModel(image, sub_cat_name, category_id, category_image, sub_cat_id, category_name));
                                                    Log.d("@@addTotalList5", String.valueOf(sub_cat_name));

                                                }
                                                if (category_id.equals("17")) {

                                                    com.Appzia.addpanda.Screens.basicEditsFirstActivity.list17.add(new categoryChildModel(image, sub_cat_name, category_id, category_image, sub_cat_id, category_name));
                                                    Log.d("@@addTotalList5", String.valueOf(sub_cat_name));

                                                }
                                                if (category_id.equals("18")) {

                                                    com.Appzia.addpanda.Screens.basicEditsFirstActivity.list18.add(new categoryChildModel(image, sub_cat_name, category_id, category_image, sub_cat_id, category_name));
                                                    Log.d("@@addTotalList5", String.valueOf(sub_cat_name));

                                                }
                                                if (category_id.equals("19")) {

                                                    com.Appzia.addpanda.Screens.basicEditsFirstActivity.list19.add(new categoryChildModel(image, sub_cat_name, category_id, category_image, sub_cat_id, category_name));
                                                    Log.d("@@addTotalList5", String.valueOf(sub_cat_name));

                                                }
                                                if (category_id.equals("20")) {

                                                    com.Appzia.addpanda.Screens.basicEditsFirstActivity.list20.add(new categoryChildModel(image, sub_cat_name, category_id, category_image, sub_cat_id, category_name));
                                                    Log.d("@@addTotalList5", String.valueOf(sub_cat_name));

                                                }
                                                if (category_id.equals("21")) {

                                                    com.Appzia.addpanda.Screens.basicEditsFirstActivity.list21.add(new categoryChildModel(image, sub_cat_name, category_id, category_image, sub_cat_id, category_name));
                                                    Log.d("@@addTotalList5", String.valueOf(sub_cat_name));

                                                }
                                                if (category_id.equals("22")) {

                                                    com.Appzia.addpanda.Screens.basicEditsFirstActivity.list22.add(new categoryChildModel(image, sub_cat_name, category_id, category_image, sub_cat_id, category_name));
                                                    Log.d("@@addTotalList5", String.valueOf(sub_cat_name));

                                                }
                                                if (category_id.equals("23")) {

                                                    com.Appzia.addpanda.Screens.basicEditsFirstActivity.list23.add(new categoryChildModel(image, sub_cat_name, category_id, category_image, sub_cat_id, category_name));
                                                    Log.d("@@addTotalList5", String.valueOf(sub_cat_name));

                                                }
                                                if (category_id.equals("24")) {

                                                    com.Appzia.addpanda.Screens.basicEditsFirstActivity.list24.add(new categoryChildModel(image, sub_cat_name, category_id, category_image, sub_cat_id, category_name));
                                                    Log.d("@@addTotalList5", String.valueOf(sub_cat_name));

                                                }
                                                if (category_id.equals("25")) {

                                                    com.Appzia.addpanda.Screens.basicEditsFirstActivity.list25.add(new categoryChildModel(image, sub_cat_name, category_id, category_image, sub_cat_id, category_name));
                                                    Log.d("@@addTotalList5", String.valueOf(sub_cat_name));

                                                }
                                                if (category_id.equals("26")) {

                                                    com.Appzia.addpanda.Screens.basicEditsFirstActivity.list26.add(new categoryChildModel(image, sub_cat_name, category_id, category_image, sub_cat_id, category_name));
                                                    Log.d("@@addTotalList5", String.valueOf(sub_cat_name));

                                                }
                                                if (category_id.equals("27")) {

                                                    com.Appzia.addpanda.Screens.basicEditsFirstActivity.list27.add(new categoryChildModel(image, sub_cat_name, category_id, category_image, sub_cat_id, category_name));
                                                    Log.d("@@addTotalList5", String.valueOf(sub_cat_name));

                                                }
                                                if (category_id.equals("28")) {

                                                    com.Appzia.addpanda.Screens.basicEditsFirstActivity.list28.add(new categoryChildModel(image, sub_cat_name, category_id, category_image, sub_cat_id, category_name));
                                                    Log.d("@@addTotalList5", String.valueOf(sub_cat_name));

                                                }
                                                if (category_id.equals("29")) {

                                                    com.Appzia.addpanda.Screens.basicEditsFirstActivity.list29.add(new categoryChildModel(image, sub_cat_name, category_id, category_image, sub_cat_id, category_name));
                                                    Log.d("@@addTotalList5", String.valueOf(sub_cat_name));

                                                }
                                                if (category_id.equals("30")) {

                                                    com.Appzia.addpanda.Screens.basicEditsFirstActivity.list30.add(new categoryChildModel(image, sub_cat_name, category_id, category_image, sub_cat_id, category_name));
                                                    Log.d("@@addTotalList5", String.valueOf(sub_cat_name));

                                                }
                                                if (category_id.equals("31")) {

                                                    com.Appzia.addpanda.Screens.basicEditsFirstActivity.list31.add(new categoryChildModel(image, sub_cat_name, category_id, category_image, sub_cat_id, category_name));
                                                    Log.d("@@addTotalList5", String.valueOf(sub_cat_name));

                                                }
                                                if (category_id.equals("32")) {

                                                    com.Appzia.addpanda.Screens.basicEditsFirstActivity.list32.add(new categoryChildModel(image, sub_cat_name, category_id, category_image, sub_cat_id, category_name));
                                                    Log.d("@@addTotalList5", String.valueOf(sub_cat_name));

                                                }
                                                if (category_id.equals("33")) {

                                                    com.Appzia.addpanda.Screens.basicEditsFirstActivity.list33.add(new categoryChildModel(image, sub_cat_name, category_id, category_image, sub_cat_id, category_name));
                                                    Log.d("@@addTotalList5", String.valueOf(sub_cat_name));

                                                }
                                                if (category_id.equals("34")) {

                                                    com.Appzia.addpanda.Screens.basicEditsFirstActivity.list34.add(new categoryChildModel(image, sub_cat_name, category_id, category_image, sub_cat_id, category_name));
                                                    Log.d("@@addTotalList5", String.valueOf(sub_cat_name));

                                                }
                                                if (category_id.equals("35")) {

                                                    com.Appzia.addpanda.Screens.basicEditsFirstActivity.list35.add(new categoryChildModel(image, sub_cat_name, category_id, category_image, sub_cat_id, category_name));
                                                    Log.d("@@addTotalList5", String.valueOf(sub_cat_name));

                                                }
                                                if (category_id.equals("36")) {

                                                    com.Appzia.addpanda.Screens.basicEditsFirstActivity.list36.add(new categoryChildModel(image, sub_cat_name, category_id, category_image, sub_cat_id, category_name));
                                                    Log.d("@@addTotalList5", String.valueOf(sub_cat_name));

                                                }
                                                if (category_id.equals("37")) {

                                                    com.Appzia.addpanda.Screens.basicEditsFirstActivity.list37.add(new categoryChildModel(image, sub_cat_name, category_id, category_image, sub_cat_id, category_name));
                                                    Log.d("@@addTotalList5", String.valueOf(sub_cat_name));

                                                }
                                                if (category_id.equals("38")) {

                                                    com.Appzia.addpanda.Screens.basicEditsFirstActivity.list38.add(new categoryChildModel(image, sub_cat_name, category_id, category_image, sub_cat_id, category_name));
                                                    Log.d("@@addTotalList5", String.valueOf(sub_cat_name));

                                                }
                                                if (category_id.equals("39")) {

                                                    com.Appzia.addpanda.Screens.basicEditsFirstActivity.list39.add(new categoryChildModel(image, sub_cat_name, category_id, category_image, sub_cat_id, category_name));
                                                    Log.d("@@addTotalList5", String.valueOf(sub_cat_name));

                                                }
                                                if (category_id.equals("40")) {

                                                    com.Appzia.addpanda.Screens.basicEditsFirstActivity.list40.add(new categoryChildModel(image, sub_cat_name, category_id, category_image, sub_cat_id, category_name));
                                                    Log.d("@@addTotalList5", String.valueOf(sub_cat_name));

                                                }
                                                if (category_id.equals("41")) {

                                                    com.Appzia.addpanda.Screens.basicEditsFirstActivity.list41.add(new categoryChildModel(image, sub_cat_name, category_id, category_image, sub_cat_id, category_name));
                                                    Log.d("@@addTotalList5", String.valueOf(sub_cat_name));

                                                }
                                                if (category_id.equals("42")) {

                                                    com.Appzia.addpanda.Screens.basicEditsFirstActivity.list42.add(new categoryChildModel(image, sub_cat_name, category_id, category_image, sub_cat_id, category_name));
                                                    Log.d("@@addTotalList5", String.valueOf(sub_cat_name));

                                                }
                                                if (category_id.equals("43")) {

                                                    com.Appzia.addpanda.Screens.basicEditsFirstActivity.list43.add(new categoryChildModel(image, sub_cat_name, category_id, category_image, sub_cat_id, category_name));
                                                    Log.d("@@addTotalList5", String.valueOf(sub_cat_name));

                                                }
                                                if (category_id.equals("44")) {

                                                    com.Appzia.addpanda.Screens.basicEditsFirstActivity.list44.add(new categoryChildModel(image, sub_cat_name, category_id, category_image, sub_cat_id, category_name));
                                                    Log.d("@@addTotalList5", String.valueOf(sub_cat_name));

                                                }
                                                if (category_id.equals("45")) {

                                                    com.Appzia.addpanda.Screens.basicEditsFirstActivity.list45.add(new categoryChildModel(image, sub_cat_name, category_id, category_image, sub_cat_id, category_name));
                                                    Log.d("@@addTotalList5", String.valueOf(sub_cat_name));

                                                }
                                                if (category_id.equals("46")) {

                                                    com.Appzia.addpanda.Screens.basicEditsFirstActivity.list46.add(new categoryChildModel(image, sub_cat_name, category_id, category_image, sub_cat_id, category_name));
                                                    Log.d("@@addTotalList5", String.valueOf(sub_cat_name));

                                                }
                                                if (category_id.equals("47")) {

                                                    com.Appzia.addpanda.Screens.basicEditsFirstActivity.list47.add(new categoryChildModel(image, sub_cat_name, category_id, category_image, sub_cat_id, category_name));
                                                    Log.d("@@addTotalList5", String.valueOf(sub_cat_name));

                                                }
                                                if (category_id.equals("48")) {

                                                    com.Appzia.addpanda.Screens.basicEditsFirstActivity.list48.add(new categoryChildModel(image, sub_cat_name, category_id, category_image, sub_cat_id, category_name));
                                                    Log.d("@@addTotalList5", String.valueOf(sub_cat_name));

                                                }
                                                if (category_id.equals("49")) {

                                                    com.Appzia.addpanda.Screens.basicEditsFirstActivity.list49.add(new categoryChildModel(image, sub_cat_name, category_id, category_image, sub_cat_id, category_name));
                                                    Log.d("@@addTotalList5", String.valueOf(sub_cat_name));

                                                }
                                                if (category_id.equals("50")) {

                                                    com.Appzia.addpanda.Screens.basicEditsFirstActivity.list50.add(new categoryChildModel(image, sub_cat_name, category_id, category_image, sub_cat_id, category_name));
                                                    Log.d("@@addTotalList5", String.valueOf(sub_cat_name));

                                                }
                                                basicEditsFirstActivity.shimmerFrameLayout.stopShimmer();
                                                basicEditsFirstActivity.shimmerFrameLayout.setVisibility(View.GONE);
                                                basicEditsFirstActivity.CoLayout.setVisibility(View.VISIBLE);

                                            }
                                            //data are stored in list below

                                            if (category_id.equals("1")) {

                                                //com.Appzia.addpanda.Fragments.mainFragment.list1.add(new categoryChildModel(image, sub_cat_name, category_id, category_image, sub_cat_id,category_name));
                                                com.Appzia.addpanda.Screens.basicEditsFirstActivity.parentModelArrayList.add(new categoryParentModel(category_name, category_id, com.Appzia.addpanda.Fragments.mainFragment.list1));
                                                Log.d("@@addTotalList1", String.valueOf(sub_cat_name));
                                            }

                                            if (category_id.equals("2")) {

                                                //    com.Appzia.addpanda.Screens.basicEditsFirstActivity.list2.add(new categoryChildModel(image, sub_cat_name, category_id, category_image, sub_cat_id,category_name));
                                                com.Appzia.addpanda.Screens.basicEditsFirstActivity.parentModelArrayList.add(new categoryParentModel(category_name, category_id, com.Appzia.addpanda.Fragments.mainFragment.list2));
                                                Log.d("@@addTotalList2", String.valueOf(sub_cat_name));
                                            }

                                            if (category_id.equals("3")) {

                                                //com.Appzia.addpanda.Fragments.mainFragment.list3.add(new categoryChildModel(image, sub_cat_name,category_id, category_id, category_image, sub_cat_id));
                                                com.Appzia.addpanda.Screens.basicEditsFirstActivity.parentModelArrayList.add(new categoryParentModel(category_name, category_id, com.Appzia.addpanda.Fragments.mainFragment.list3));
                                                Log.d("@@addTotalList3", String.valueOf(sub_cat_name));
                                            }

                                            if (category_id.equals("4")) {

                                                //com.Appzia.addpanda.Fragments.mainFragment.list3.add(new categoryChildModel(image, sub_cat_name,category_id, category_id, category_image, sub_cat_id));
                                                com.Appzia.addpanda.Screens.basicEditsFirstActivity.parentModelArrayList.add(new categoryParentModel(category_name, category_id, com.Appzia.addpanda.Fragments.mainFragment.list4));
                                                Log.d("@@addTotalList3", String.valueOf(sub_cat_name));
                                            }

                                            if (category_id.equals("5")) {

                                                //     com.Appzia.addpanda.Screens.basicEditsFirstActivity.list4.add(new categoryChildModel(image, sub_cat_name,category_id, category_id, category_image, sub_cat_id));
                                                com.Appzia.addpanda.Screens.basicEditsFirstActivity.parentModelArrayList.add(new categoryParentModel(category_name, category_id, com.Appzia.addpanda.Fragments.mainFragment.list5));
                                                Log.d("@@addTotalList5", String.valueOf(sub_cat_name));

                                            }

                                            if (category_id.equals("6")) {

//                                              com.Appzia.addpanda.Screens.basicEditsFirstActivity.list6.add(new categoryChildModel(image, sub_cat_name,category_id, category_id, category_image, sub_cat_id));
                                                com.Appzia.addpanda.Screens.basicEditsFirstActivity.parentModelArrayList.add(new categoryParentModel(category_name, category_id, com.Appzia.addpanda.Fragments.mainFragment.list6));

                                                Log.d("@@addTotalList5", String.valueOf(sub_cat_name));

                                            }
                                            if (category_id.equals("7")) {

//                                              com.Appzia.addpanda.Screens.basicEditsFirstActivity.list7.add(new categoryChildModel(image, sub_cat_name,category_id, category_id, category_image, sub_cat_id));
                                                com.Appzia.addpanda.Screens.basicEditsFirstActivity.parentModelArrayList.add(new categoryParentModel(category_name, category_id, com.Appzia.addpanda.Fragments.mainFragment.list7));
                                                Log.d("@@addTotalList5", String.valueOf(sub_cat_name));

                                            }
                                            if (category_id.equals("8")) {

//                                              com.Appzia.addpanda.Screens.basicEditsFirstActivity.list8.add(new categoryChildModel(image, sub_cat_name,category_id, category_id, category_image, sub_cat_id));
                                                com.Appzia.addpanda.Screens.basicEditsFirstActivity.parentModelArrayList.add(new categoryParentModel(category_name, category_id, com.Appzia.addpanda.Fragments.mainFragment.list8));
                                                Log.d("@@addTotalList5", String.valueOf(sub_cat_name));

                                            }
                                            if (category_id.equals("9")) {

//                                              com.Appzia.addpanda.Screens.basicEditsFirstActivity.list9.add(new categoryChildModel(image, sub_cat_name,category_id, category_id, category_image, sub_cat_id));
                                                com.Appzia.addpanda.Screens.basicEditsFirstActivity.parentModelArrayList.add(new categoryParentModel(category_name, category_id, com.Appzia.addpanda.Fragments.mainFragment.list9));
                                                Log.d("@@addTotalList5", String.valueOf(sub_cat_name));

                                            }
                                            if (category_id.equals("10")) {

//                                              com.Appzia.addpanda.Screens.basicEditsFirstActivity.list10.add(new categoryChildModel(image, sub_cat_name,category_id, category_id, category_image, sub_cat_id));
                                                com.Appzia.addpanda.Screens.basicEditsFirstActivity.parentModelArrayList.add(new categoryParentModel(category_name, category_id, com.Appzia.addpanda.Fragments.mainFragment.list10));
                                                Log.d("@@addTotalList5", String.valueOf(sub_cat_name));

                                            }
                                            if (category_id.equals("11")) {

//                                              com.Appzia.addpanda.Screens.basicEditsFirstActivity.list11.add(new categoryChildModel(image, sub_cat_name,category_id, category_id, category_image, sub_cat_id));
                                                com.Appzia.addpanda.Screens.basicEditsFirstActivity.parentModelArrayList.add(new categoryParentModel(category_name, category_id, com.Appzia.addpanda.Fragments.mainFragment.list11));
                                                Log.d("@@addTotalList5", String.valueOf(sub_cat_name));

                                            }
                                            if (category_id.equals("12")) {

//                                              com.Appzia.addpanda.Screens.basicEditsFirstActivity.list12.add(new categoryChildModel(image, sub_cat_name,category_id, category_id, category_image, sub_cat_id));
                                                com.Appzia.addpanda.Screens.basicEditsFirstActivity.parentModelArrayList.add(new categoryParentModel(category_name, category_id, com.Appzia.addpanda.Fragments.mainFragment.list12));
                                                Log.d("@@addTotalList5", String.valueOf(sub_cat_name));

                                            }
                                            if (category_id.equals("13")) {

//                                              com.Appzia.addpanda.Screens.basicEditsFirstActivity.list13.add(new categoryChildModel(image, sub_cat_name,category_id, category_id, category_image, sub_cat_id));
                                                com.Appzia.addpanda.Screens.basicEditsFirstActivity.parentModelArrayList.add(new categoryParentModel(category_name, category_id, com.Appzia.addpanda.Fragments.mainFragment.list13));
                                                Log.d("@@addTotalList5", String.valueOf(sub_cat_name));

                                            }
                                            if (category_id.equals("14")) {

//                                              com.Appzia.addpanda.Screens.basicEditsFirstActivity.list14.add(new categoryChildModel(image, sub_cat_name,category_id, category_id, category_image, sub_cat_id));
                                                com.Appzia.addpanda.Screens.basicEditsFirstActivity.parentModelArrayList.add(new categoryParentModel(category_name, category_id, com.Appzia.addpanda.Fragments.mainFragment.list14));
                                                Log.d("@@addTotalList5", String.valueOf(sub_cat_name));

                                            }
                                            if (category_id.equals("15")) {

//                                              com.Appzia.addpanda.Screens.basicEditsFirstActivity.list15.add(new categoryChildModel(image, sub_cat_name,category_id, category_id, category_image, sub_cat_id));
                                                com.Appzia.addpanda.Screens.basicEditsFirstActivity.parentModelArrayList.add(new categoryParentModel(category_name, category_id, com.Appzia.addpanda.Fragments.mainFragment.list15));
                                                Log.d("@@addTotalList5", String.valueOf(sub_cat_name));

                                            }
                                            if (category_id.equals("16")) {

//                                              com.Appzia.addpanda.Screens.basicEditsFirstActivity.list16.add(new categoryChildModel(image, sub_cat_name,category_id, category_id, category_image, sub_cat_id));
                                                com.Appzia.addpanda.Screens.basicEditsFirstActivity.parentModelArrayList.add(new categoryParentModel(category_name, category_id, com.Appzia.addpanda.Fragments.mainFragment.list16));
                                                Log.d("@@addTotalList5", String.valueOf(sub_cat_name));

                                            }
                                            if (category_id.equals("17")) {

//                                              com.Appzia.addpanda.Screens.basicEditsFirstActivity.list17.add(new categoryChildModel(image, sub_cat_name,category_id, category_id, category_image, sub_cat_id));
                                                com.Appzia.addpanda.Screens.basicEditsFirstActivity.parentModelArrayList.add(new categoryParentModel(category_name, category_id, com.Appzia.addpanda.Fragments.mainFragment.list17));
                                                Log.d("@@addTotalList5", String.valueOf(sub_cat_name));

                                            }
                                            if (category_id.equals("18")) {

//                                              com.Appzia.addpanda.Screens.basicEditsFirstActivity.list18.add(new categoryChildModel(image, sub_cat_name,category_id, category_id, category_image, sub_cat_id));
                                                com.Appzia.addpanda.Screens.basicEditsFirstActivity.parentModelArrayList.add(new categoryParentModel(category_name, category_id, com.Appzia.addpanda.Fragments.mainFragment.list18));
                                                Log.d("@@addTotalList5", String.valueOf(sub_cat_name));

                                            }
                                            if (category_id.equals("19")) {

//                                              com.Appzia.addpanda.Screens.basicEditsFirstActivity.list19.add(new categoryChildModel(image, sub_cat_name,category_id, category_id, category_image, sub_cat_id));
                                                com.Appzia.addpanda.Screens.basicEditsFirstActivity.parentModelArrayList.add(new categoryParentModel(category_name, category_id, com.Appzia.addpanda.Fragments.mainFragment.list19));
                                                Log.d("@@addTotalList5", String.valueOf(sub_cat_name));

                                            }
                                            if (category_id.equals("20")) {

//                                              com.Appzia.addpanda.Screens.basicEditsFirstActivity.list20.add(new categoryChildModel(image, sub_cat_name,category_id, category_id, category_image, sub_cat_id));
                                                com.Appzia.addpanda.Screens.basicEditsFirstActivity.parentModelArrayList.add(new categoryParentModel(category_name, category_id, com.Appzia.addpanda.Fragments.mainFragment.list20));
                                                Log.d("@@addTotalList5", String.valueOf(sub_cat_name));

                                            }
                                            if (category_id.equals("21")) {

//                                              com.Appzia.addpanda.Screens.basicEditsFirstActivity.list21.add(new categoryChildModel(image, sub_cat_name,category_id, category_id, category_image, sub_cat_id));
                                                com.Appzia.addpanda.Screens.basicEditsFirstActivity.parentModelArrayList.add(new categoryParentModel(category_name, category_id, com.Appzia.addpanda.Fragments.mainFragment.list21));
                                                Log.d("@@addTotalList5", String.valueOf(sub_cat_name));

                                            }
                                            if (category_id.equals("22")) {

//                                              com.Appzia.addpanda.Screens.basicEditsFirstActivity.list5.add(new categoryChildModel(image, sub_cat_name,category_id, category_id, category_image, sub_cat_id));
                                                com.Appzia.addpanda.Screens.basicEditsFirstActivity.parentModelArrayList.add(new categoryParentModel(category_name, category_id, com.Appzia.addpanda.Fragments.mainFragment.list22));
                                                Log.d("@@addTotalList5", String.valueOf(sub_cat_name));

                                            }
                                            if (category_id.equals("23")) {

//                                              com.Appzia.addpanda.Screens.basicEditsFirstActivity.list22.add(new categoryChildModel(image, sub_cat_name,category_id, category_id, category_image, sub_cat_id));
                                                com.Appzia.addpanda.Screens.basicEditsFirstActivity.parentModelArrayList.add(new categoryParentModel(category_name, category_id, com.Appzia.addpanda.Fragments.mainFragment.list23));
                                                Log.d("@@addTotalList5", String.valueOf(sub_cat_name));

                                            }
                                            if (category_id.equals("24")) {

//                                              com.Appzia.addpanda.Screens.basicEditsFirstActivity.list23.add(new categoryChildModel(image, sub_cat_name,category_id, category_id, category_image, sub_cat_id));
                                                com.Appzia.addpanda.Screens.basicEditsFirstActivity.parentModelArrayList.add(new categoryParentModel(category_name, category_id, com.Appzia.addpanda.Fragments.mainFragment.list24));
                                                Log.d("@@addTotalList5", String.valueOf(sub_cat_name));

                                            }
                                            if (category_id.equals("25")) {

//                                              com.Appzia.addpanda.Screens.basicEditsFirstActivity.list24.add(new categoryChildModel(image, sub_cat_name,category_id, category_id, category_image, sub_cat_id));
                                                com.Appzia.addpanda.Screens.basicEditsFirstActivity.parentModelArrayList.add(new categoryParentModel(category_name, category_id, com.Appzia.addpanda.Fragments.mainFragment.list25));
                                                Log.d("@@addTotalList5", String.valueOf(sub_cat_name));

                                            }
                                            if (category_id.equals("26")) {

//                                              com.Appzia.addpanda.Screens.basicEditsFirstActivity.list25.add(new categoryChildModel(image, sub_cat_name,category_id, category_id, category_image, sub_cat_id));
                                                com.Appzia.addpanda.Screens.basicEditsFirstActivity.parentModelArrayList.add(new categoryParentModel(category_name, category_id, com.Appzia.addpanda.Fragments.mainFragment.list26));
                                                Log.d("@@addTotalList5", String.valueOf(sub_cat_name));

                                            }
                                            if (category_id.equals("27")) {

//                                              com.Appzia.addpanda.Screens.basicEditsFirstActivity.list26.add(new categoryChildModel(image, sub_cat_name,category_id, category_id, category_image, sub_cat_id));
                                                com.Appzia.addpanda.Screens.basicEditsFirstActivity.parentModelArrayList.add(new categoryParentModel(category_name, category_id, com.Appzia.addpanda.Fragments.mainFragment.list27));
                                                Log.d("@@addTotalList5", String.valueOf(sub_cat_name));

                                            }
                                            if (category_id.equals("28")) {

//                                              com.Appzia.addpanda.Screens.basicEditsFirstActivity.list28.add(new categoryChildModel(image, sub_cat_name,category_id, category_id, category_image, sub_cat_id));
                                                com.Appzia.addpanda.Screens.basicEditsFirstActivity.parentModelArrayList.add(new categoryParentModel(category_name, category_id, com.Appzia.addpanda.Fragments.mainFragment.list28));
                                                Log.d("@@addTotalList5", String.valueOf(sub_cat_name));

                                            }
                                            if (category_id.equals("29")) {

//                                              com.Appzia.addpanda.Screens.basicEditsFirstActivity.list29.add(new categoryChildModel(image, sub_cat_name,category_id, category_id, category_image, sub_cat_id));
                                                com.Appzia.addpanda.Screens.basicEditsFirstActivity.parentModelArrayList.add(new categoryParentModel(category_name, category_id, com.Appzia.addpanda.Fragments.mainFragment.list29));
                                                Log.d("@@addTotalList5", String.valueOf(sub_cat_name));

                                            }
                                            if (category_id.equals("30")) {

//                                              com.Appzia.addpanda.Screens.basicEditsFirstActivity.list30.add(new categoryChildModel(image, sub_cat_name,category_id, category_id, category_image, sub_cat_id));
                                                com.Appzia.addpanda.Screens.basicEditsFirstActivity.parentModelArrayList.add(new categoryParentModel(category_name, category_id, com.Appzia.addpanda.Fragments.mainFragment.list30));
                                                Log.d("@@addTotalList5", String.valueOf(sub_cat_name));

                                            }
                                            if (category_id.equals("31")) {

//                                              com.Appzia.addpanda.Screens.basicEditsFirstActivity.list31.add(new categoryChildModel(image, sub_cat_name,category_id, category_id, category_image, sub_cat_id));
                                                com.Appzia.addpanda.Screens.basicEditsFirstActivity.parentModelArrayList.add(new categoryParentModel(category_name, category_id, com.Appzia.addpanda.Fragments.mainFragment.list31));
                                                Log.d("@@addTotalList5", String.valueOf(sub_cat_name));

                                            }
                                            if (category_id.equals("32")) {

//                                              com.Appzia.addpanda.Screens.basicEditsFirstActivity.list32.add(new categoryChildModel(image, sub_cat_name,category_id, category_id, category_image, sub_cat_id));
                                                com.Appzia.addpanda.Screens.basicEditsFirstActivity.parentModelArrayList.add(new categoryParentModel(category_name, category_id, com.Appzia.addpanda.Fragments.mainFragment.list32));
                                                Log.d("@@addTotalList5", String.valueOf(sub_cat_name));

                                            }
                                            if (category_id.equals("33")) {

//                                              com.Appzia.addpanda.Screens.basicEditsFirstActivity.list33.add(new categoryChildModel(image, sub_cat_name,category_id, category_id, category_image, sub_cat_id));
                                                com.Appzia.addpanda.Screens.basicEditsFirstActivity.parentModelArrayList.add(new categoryParentModel(category_name, category_id, com.Appzia.addpanda.Fragments.mainFragment.list33));
                                                Log.d("@@addTotalList5", String.valueOf(sub_cat_name));

                                            }
                                            if (category_id.equals("34")) {

//                                              com.Appzia.addpanda.Screens.basicEditsFirstActivity.list34.add(new categoryChildModel(image, sub_cat_name,category_id, category_id, category_image, sub_cat_id));
                                                com.Appzia.addpanda.Screens.basicEditsFirstActivity.parentModelArrayList.add(new categoryParentModel(category_name, category_id, com.Appzia.addpanda.Fragments.mainFragment.list34));
                                                Log.d("@@addTotalList5", String.valueOf(sub_cat_name));

                                            }
                                            if (category_id.equals("35")) {

//                                              com.Appzia.addpanda.Screens.basicEditsFirstActivity.list35.add(new categoryChildModel(image, sub_cat_name,category_id, category_id, category_image, sub_cat_id));
                                                com.Appzia.addpanda.Screens.basicEditsFirstActivity.parentModelArrayList.add(new categoryParentModel(category_name, category_id, com.Appzia.addpanda.Fragments.mainFragment.list35));
                                                Log.d("@@addTotalList5", String.valueOf(sub_cat_name));

                                            }
                                            if (category_id.equals("36")) {

//                                              com.Appzia.addpanda.Screens.basicEditsFirstActivity.list36.add(new categoryChildModel(image, sub_cat_name,category_id, category_id, category_image, sub_cat_id));
                                                com.Appzia.addpanda.Screens.basicEditsFirstActivity.parentModelArrayList.add(new categoryParentModel(category_name, category_id, com.Appzia.addpanda.Fragments.mainFragment.list36));
                                                Log.d("@@addTotalList5", String.valueOf(sub_cat_name));

                                            }
                                            if (category_id.equals("37")) {

//                                              com.Appzia.addpanda.Screens.basicEditsFirstActivity.list37.add(new categoryChildModel(image, sub_cat_name,category_id, category_id, category_image, sub_cat_id));
                                                com.Appzia.addpanda.Screens.basicEditsFirstActivity.parentModelArrayList.add(new categoryParentModel(category_name, category_id, com.Appzia.addpanda.Fragments.mainFragment.list37));
                                                Log.d("@@addTotalList5", String.valueOf(sub_cat_name));

                                            }
                                            if (category_id.equals("38")) {

//                                              com.Appzia.addpanda.Screens.basicEditsFirstActivity.list38.add(new categoryChildModel(image, sub_cat_name,category_id, category_id, category_image, sub_cat_id));
                                                com.Appzia.addpanda.Screens.basicEditsFirstActivity.parentModelArrayList.add(new categoryParentModel(category_name, category_id, com.Appzia.addpanda.Fragments.mainFragment.list38));
                                                Log.d("@@addTotalList5", String.valueOf(sub_cat_name));

                                            }
                                            if (category_id.equals("39")) {

//                                              com.Appzia.addpanda.Screens.basicEditsFirstActivity.list39.add(new categoryChildModel(image, sub_cat_name,category_id, category_id, category_image, sub_cat_id));
                                                com.Appzia.addpanda.Screens.basicEditsFirstActivity.parentModelArrayList.add(new categoryParentModel(category_name, category_id, com.Appzia.addpanda.Fragments.mainFragment.list39));
                                                Log.d("@@addTotalList5", String.valueOf(sub_cat_name));

                                            }
                                            if (category_id.equals("40")) {

//                                              com.Appzia.addpanda.Screens.basicEditsFirstActivity.list40.add(new categoryChildModel(image, sub_cat_name,category_id, category_id, category_image, sub_cat_id));
                                                com.Appzia.addpanda.Screens.basicEditsFirstActivity.parentModelArrayList.add(new categoryParentModel(category_name, category_id, com.Appzia.addpanda.Fragments.mainFragment.list40));
                                                Log.d("@@addTotalList5", String.valueOf(sub_cat_name));

                                            }
                                            if (category_id.equals("41")) {

//                                              com.Appzia.addpanda.Screens.basicEditsFirstActivity.list41.add(new categoryChildModel(image, sub_cat_name,category_id, category_id, category_image, sub_cat_id));
                                                com.Appzia.addpanda.Screens.basicEditsFirstActivity.parentModelArrayList.add(new categoryParentModel(category_name, category_id, com.Appzia.addpanda.Fragments.mainFragment.list41));
                                                Log.d("@@addTotalList5", String.valueOf(sub_cat_name));

                                            }
                                            if (category_id.equals("42")) {

//                                              com.Appzia.addpanda.Screens.basicEditsFirstActivity.list42.add(new categoryChildModel(image, sub_cat_name,category_id, category_id, category_image, sub_cat_id));
                                                com.Appzia.addpanda.Screens.basicEditsFirstActivity.parentModelArrayList.add(new categoryParentModel(category_name, category_id, com.Appzia.addpanda.Fragments.mainFragment.list42));
                                                Log.d("@@addTotalList5", String.valueOf(sub_cat_name));

                                            }
                                            if (category_id.equals("43")) {

//                                              com.Appzia.addpanda.Screens.basicEditsFirstActivity.list43.add(new categoryChildModel(image, sub_cat_name,category_id, category_id, category_image, sub_cat_id));
                                                com.Appzia.addpanda.Screens.basicEditsFirstActivity.parentModelArrayList.add(new categoryParentModel(category_name, category_id, com.Appzia.addpanda.Fragments.mainFragment.list43));
                                                Log.d("@@addTotalList5", String.valueOf(sub_cat_name));

                                            }
                                            if (category_id.equals("44")) {

//                                              com.Appzia.addpanda.Screens.basicEditsFirstActivity.list44.add(new categoryChildModel(image, sub_cat_name,category_id, category_id, category_image, sub_cat_id));
                                                com.Appzia.addpanda.Screens.basicEditsFirstActivity.parentModelArrayList.add(new categoryParentModel(category_name, category_id, com.Appzia.addpanda.Fragments.mainFragment.list44));
                                                Log.d("@@addTotalList5", String.valueOf(sub_cat_name));

                                            }
                                            if (category_id.equals("45")) {

//                                              com.Appzia.addpanda.Screens.basicEditsFirstActivity.list45.add(new categoryChildModel(image, sub_cat_name,category_id, category_id, category_image, sub_cat_id));
                                                com.Appzia.addpanda.Screens.basicEditsFirstActivity.parentModelArrayList.add(new categoryParentModel(category_name, category_id, com.Appzia.addpanda.Fragments.mainFragment.list45));
                                                Log.d("@@addTotalList5", String.valueOf(sub_cat_name));

                                            }
                                            if (category_id.equals("46")) {

//                                              com.Appzia.addpanda.Screens.basicEditsFirstActivity.list46.add(new categoryChildModel(image, sub_cat_name,category_id, category_id, category_image, sub_cat_id));
                                                com.Appzia.addpanda.Screens.basicEditsFirstActivity.parentModelArrayList.add(new categoryParentModel(category_name, category_id, com.Appzia.addpanda.Fragments.mainFragment.list46));
                                                Log.d("@@addTotalList5", String.valueOf(sub_cat_name));

                                            }
                                            if (category_id.equals("47")) {

//                                              com.Appzia.addpanda.Screens.basicEditsFirstActivity.list47.add(new categoryChildModel(image, sub_cat_name,category_id, category_id, category_image, sub_cat_id));
                                                com.Appzia.addpanda.Screens.basicEditsFirstActivity.parentModelArrayList.add(new categoryParentModel(category_name, category_id, com.Appzia.addpanda.Fragments.mainFragment.list47));
                                                Log.d("@@addTotalList5", String.valueOf(sub_cat_name));

                                            }
                                            if (category_id.equals("48")) {

//                                              com.Appzia.addpanda.Screens.basicEditsFirstActivity.list48.add(new categoryChildModel(image, sub_cat_name,category_id, category_id, category_image, sub_cat_id));
                                                com.Appzia.addpanda.Screens.basicEditsFirstActivity.parentModelArrayList.add(new categoryParentModel(category_name, category_id, com.Appzia.addpanda.Fragments.mainFragment.list48));
                                                Log.d("@@addTotalList5", String.valueOf(sub_cat_name));

                                            }
                                            if (category_id.equals("49")) {

//                                              com.Appzia.addpanda.Screens.basicEditsFirstActivity.list49.add(new categoryChildModel(image, sub_cat_name,category_id, category_id, category_image, sub_cat_id));
                                                com.Appzia.addpanda.Screens.basicEditsFirstActivity.parentModelArrayList.add(new categoryParentModel(category_name, category_id, com.Appzia.addpanda.Fragments.mainFragment.list49));
                                                Log.d("@@addTotalList5", String.valueOf(sub_cat_name));

                                            }
                                            if (category_id.equals("50")) {

//                                              com.Appzia.addpanda.Screens.basicEditsFirstActivity.list50.add(new categoryChildModel(image, sub_cat_name,category_id, category_id, category_image, sub_cat_id));
                                                com.Appzia.addpanda.Screens.basicEditsFirstActivity.parentModelArrayList.add(new categoryParentModel(category_name, category_id, com.Appzia.addpanda.Fragments.mainFragment.list50));
                                                Log.d("@@addTotalList5", String.valueOf(sub_cat_name));

                                            }
                                        } else if (accountType.equals("2") && category_type_flag.equals("2")) {
                                            Constant.mainCatBtnModelList.add(new mainCatBtnChildModel(category_name, category_id));
                                            for (int j = 0; j < sub_category_list.length(); j++) {
                                                obj3 = sub_category_list.getJSONObject(j);

                                                sub_cat_name = obj3.getString("sub_cat_name");
                                                sub_cat_id = obj3.getString("sub_cat_id");
                                                image = obj3.getString("image");


                                                if (category_id.equals("1")) {

                                                    com.Appzia.addpanda.Screens.basicEditsFirstActivity.list1.add(new categoryChildModel(image, sub_cat_name, category_id, category_image, sub_cat_id, category_name));
                                                    //       com.Appzia.addpanda.Screens.basicEditsFirstActivity.parentModelArrayList.add(new categoryParentModel(category_name, com.Appzia.addpanda.Fragments.mainFragment.list1));
                                                    Log.d("@@addTotalList1", String.valueOf(sub_cat_name));
                                                }

                                                if (category_id.equals("2")) {

                                                    com.Appzia.addpanda.Screens.basicEditsFirstActivity.list2.add(new categoryChildModel(image, sub_cat_name, category_id, category_image, sub_cat_id, category_name));
                                                    // com.Appzia.addpanda.Fragments.mainFragment.parentModelArrayList.add(new categoryParentModel(category_name, com.Appzia.addpanda.Fragments.mainFragment.list2));
                                                    Log.d("@@addTotalList2", String.valueOf(sub_cat_name));
                                                }

                                                if (category_id.equals("3")) {

                                                    com.Appzia.addpanda.Screens.basicEditsFirstActivity.list3.add(new categoryChildModel(image, sub_cat_name, category_id, category_image, sub_cat_id, category_name));
                                                    //     com.Appzia.addpanda.Screens.basicEditsFirstActivity.parentModelArrayList.add(new categoryParentModel(category_name, com.Appzia.addpanda.Fragments.mainFragment.list3));
                                                    Log.d("@@addTotalList3", String.valueOf(sub_cat_name));
                                                }

                                                if (category_id.equals("4")) {

                                                    com.Appzia.addpanda.Screens.basicEditsFirstActivity.list4.add(new categoryChildModel(image, sub_cat_name, category_id, category_image, sub_cat_id, category_name));
                                                    Log.d("@@addTotalList5", String.valueOf(sub_cat_name));

                                                }
                                                if (category_id.equals("5")) {

                                                    com.Appzia.addpanda.Screens.basicEditsFirstActivity.list5.add(new categoryChildModel(image, sub_cat_name, category_id, category_image, sub_cat_id, category_name));
                                                    Log.d("@@addTotalList5", String.valueOf(sub_cat_name));

                                                }
                                                if (category_id.equals("6")) {

                                                    com.Appzia.addpanda.Screens.basicEditsFirstActivity.list6.add(new categoryChildModel(image, sub_cat_name, category_id, category_image, sub_cat_id, category_name));
                                                    Log.d("@@addTotalList5", String.valueOf(sub_cat_name));

                                                }
                                                if (category_id.equals("7")) {

                                                    com.Appzia.addpanda.Screens.basicEditsFirstActivity.list7.add(new categoryChildModel(image, sub_cat_name, category_id, category_image, sub_cat_id, category_name));
                                                    Log.d("@@addTotalList5", String.valueOf(sub_cat_name));

                                                }
                                                if (category_id.equals("8")) {

                                                    com.Appzia.addpanda.Screens.basicEditsFirstActivity.list8.add(new categoryChildModel(image, sub_cat_name, category_id, category_image, sub_cat_id, category_name));
                                                    Log.d("@@addTotalList5", String.valueOf(sub_cat_name));

                                                }
                                                if (category_id.equals("9")) {

                                                    com.Appzia.addpanda.Screens.basicEditsFirstActivity.list9.add(new categoryChildModel(image, sub_cat_name, category_id, category_image, sub_cat_id, category_name));
                                                    Log.d("@@addTotalList5", String.valueOf(sub_cat_name));

                                                }
                                                if (category_id.equals("10")) {

                                                    com.Appzia.addpanda.Screens.basicEditsFirstActivity.list10.add(new categoryChildModel(image, sub_cat_name, category_id, category_image, sub_cat_id, category_name));
                                                    Log.d("@@addTotalList5", String.valueOf(sub_cat_name));

                                                }
                                                if (category_id.equals("11")) {

                                                    com.Appzia.addpanda.Screens.basicEditsFirstActivity.list11.add(new categoryChildModel(image, sub_cat_name, category_id, category_image, sub_cat_id, category_name));
                                                    Log.d("@@addTotalList5", String.valueOf(sub_cat_name));

                                                }
                                                if (category_id.equals("12")) {

                                                    com.Appzia.addpanda.Screens.basicEditsFirstActivity.list12.add(new categoryChildModel(image, sub_cat_name, category_id, category_image, sub_cat_id, category_name));
                                                    Log.d("@@addTotalList5", String.valueOf(sub_cat_name));

                                                }
                                                if (category_id.equals("13")) {

                                                    com.Appzia.addpanda.Screens.basicEditsFirstActivity.list13.add(new categoryChildModel(image, sub_cat_name, category_id, category_image, sub_cat_id, category_name));
                                                    Log.d("@@addTotalList5", String.valueOf(sub_cat_name));

                                                }
                                                if (category_id.equals("14")) {

                                                    com.Appzia.addpanda.Screens.basicEditsFirstActivity.list14.add(new categoryChildModel(image, sub_cat_name, category_id, category_image, sub_cat_id, category_name));
                                                    Log.d("@@addTotalList5", String.valueOf(sub_cat_name));

                                                }
                                                if (category_id.equals("15")) {

                                                    com.Appzia.addpanda.Screens.basicEditsFirstActivity.list15.add(new categoryChildModel(image, sub_cat_name, category_id, category_image, sub_cat_id, category_name));
                                                    Log.d("@@addTotalList5", String.valueOf(sub_cat_name));

                                                }
                                                if (category_id.equals("16")) {

                                                    com.Appzia.addpanda.Screens.basicEditsFirstActivity.list16.add(new categoryChildModel(image, sub_cat_name, category_id, category_image, sub_cat_id, category_name));
                                                    Log.d("@@addTotalList5", String.valueOf(sub_cat_name));

                                                }
                                                if (category_id.equals("17")) {

                                                    com.Appzia.addpanda.Screens.basicEditsFirstActivity.list17.add(new categoryChildModel(image, sub_cat_name, category_id, category_image, sub_cat_id, category_name));
                                                    Log.d("@@addTotalList5", String.valueOf(sub_cat_name));

                                                }
                                                if (category_id.equals("18")) {

                                                    com.Appzia.addpanda.Screens.basicEditsFirstActivity.list18.add(new categoryChildModel(image, sub_cat_name, category_id, category_image, sub_cat_id, category_name));
                                                    Log.d("@@addTotalList5", String.valueOf(sub_cat_name));

                                                }
                                                if (category_id.equals("19")) {

                                                    com.Appzia.addpanda.Screens.basicEditsFirstActivity.list19.add(new categoryChildModel(image, sub_cat_name, category_id, category_image, sub_cat_id, category_name));
                                                    Log.d("@@addTotalList5", String.valueOf(sub_cat_name));

                                                }
                                                if (category_id.equals("20")) {

                                                    com.Appzia.addpanda.Screens.basicEditsFirstActivity.list20.add(new categoryChildModel(image, sub_cat_name, category_id, category_image, sub_cat_id, category_name));
                                                    Log.d("@@addTotalList5", String.valueOf(sub_cat_name));

                                                }
                                                if (category_id.equals("21")) {

                                                    com.Appzia.addpanda.Screens.basicEditsFirstActivity.list21.add(new categoryChildModel(image, sub_cat_name, category_id, category_image, sub_cat_id, category_name));
                                                    Log.d("@@addTotalList5", String.valueOf(sub_cat_name));

                                                }
                                                if (category_id.equals("22")) {

                                                    com.Appzia.addpanda.Screens.basicEditsFirstActivity.list22.add(new categoryChildModel(image, sub_cat_name, category_id, category_image, sub_cat_id, category_name));
                                                    Log.d("@@addTotalList5", String.valueOf(sub_cat_name));

                                                }
                                                if (category_id.equals("23")) {

                                                    com.Appzia.addpanda.Screens.basicEditsFirstActivity.list23.add(new categoryChildModel(image, sub_cat_name, category_id, category_image, sub_cat_id, category_name));
                                                    Log.d("@@addTotalList5", String.valueOf(sub_cat_name));

                                                }
                                                if (category_id.equals("24")) {

                                                    com.Appzia.addpanda.Screens.basicEditsFirstActivity.list24.add(new categoryChildModel(image, sub_cat_name, category_id, category_image, sub_cat_id, category_name));
                                                    Log.d("@@addTotalList5", String.valueOf(sub_cat_name));

                                                }
                                                if (category_id.equals("25")) {

                                                    com.Appzia.addpanda.Screens.basicEditsFirstActivity.list25.add(new categoryChildModel(image, sub_cat_name, category_id, category_image, sub_cat_id, category_name));
                                                    Log.d("@@addTotalList5", String.valueOf(sub_cat_name));

                                                }
                                                if (category_id.equals("26")) {

                                                    com.Appzia.addpanda.Screens.basicEditsFirstActivity.list26.add(new categoryChildModel(image, sub_cat_name, category_id, category_image, sub_cat_id, category_name));
                                                    Log.d("@@addTotalList5", String.valueOf(sub_cat_name));

                                                }
                                                if (category_id.equals("27")) {

                                                    com.Appzia.addpanda.Screens.basicEditsFirstActivity.list27.add(new categoryChildModel(image, sub_cat_name, category_id, category_image, sub_cat_id, category_name));
                                                    Log.d("@@addTotalList5", String.valueOf(sub_cat_name));

                                                }
                                                if (category_id.equals("28")) {

                                                    com.Appzia.addpanda.Screens.basicEditsFirstActivity.list28.add(new categoryChildModel(image, sub_cat_name, category_id, category_image, sub_cat_id, category_name));
                                                    Log.d("@@addTotalList5", String.valueOf(sub_cat_name));

                                                }
                                                if (category_id.equals("29")) {

                                                    com.Appzia.addpanda.Screens.basicEditsFirstActivity.list29.add(new categoryChildModel(image, sub_cat_name, category_id, category_image, sub_cat_id, category_name));
                                                    Log.d("@@addTotalList5", String.valueOf(sub_cat_name));

                                                }
                                                if (category_id.equals("30")) {

                                                    com.Appzia.addpanda.Screens.basicEditsFirstActivity.list30.add(new categoryChildModel(image, sub_cat_name, category_id, category_image, sub_cat_id, category_name));
                                                    Log.d("@@addTotalList5", String.valueOf(sub_cat_name));

                                                }
                                                if (category_id.equals("31")) {

                                                    com.Appzia.addpanda.Screens.basicEditsFirstActivity.list31.add(new categoryChildModel(image, sub_cat_name, category_id, category_image, sub_cat_id, category_name));
                                                    Log.d("@@addTotalList5", String.valueOf(sub_cat_name));

                                                }
                                                if (category_id.equals("32")) {

                                                    com.Appzia.addpanda.Screens.basicEditsFirstActivity.list32.add(new categoryChildModel(image, sub_cat_name, category_id, category_image, sub_cat_id, category_name));
                                                    Log.d("@@addTotalList5", String.valueOf(sub_cat_name));

                                                }
                                                if (category_id.equals("33")) {

                                                    com.Appzia.addpanda.Screens.basicEditsFirstActivity.list33.add(new categoryChildModel(image, sub_cat_name, category_id, category_image, sub_cat_id, category_name));
                                                    Log.d("@@addTotalList5", String.valueOf(sub_cat_name));

                                                }
                                                if (category_id.equals("34")) {

                                                    com.Appzia.addpanda.Screens.basicEditsFirstActivity.list34.add(new categoryChildModel(image, sub_cat_name, category_id, category_image, sub_cat_id, category_name));
                                                    Log.d("@@addTotalList5", String.valueOf(sub_cat_name));

                                                }
                                                if (category_id.equals("35")) {

                                                    com.Appzia.addpanda.Screens.basicEditsFirstActivity.list35.add(new categoryChildModel(image, sub_cat_name, category_id, category_image, sub_cat_id, category_name));
                                                    Log.d("@@addTotalList5", String.valueOf(sub_cat_name));

                                                }
                                                if (category_id.equals("36")) {

                                                    com.Appzia.addpanda.Screens.basicEditsFirstActivity.list36.add(new categoryChildModel(image, sub_cat_name, category_id, category_image, sub_cat_id, category_name));
                                                    Log.d("@@addTotalList5", String.valueOf(sub_cat_name));

                                                }
                                                if (category_id.equals("37")) {

                                                    com.Appzia.addpanda.Screens.basicEditsFirstActivity.list37.add(new categoryChildModel(image, sub_cat_name, category_id, category_image, sub_cat_id, category_name));
                                                    Log.d("@@addTotalList5", String.valueOf(sub_cat_name));

                                                }
                                                if (category_id.equals("38")) {

                                                    com.Appzia.addpanda.Screens.basicEditsFirstActivity.list38.add(new categoryChildModel(image, sub_cat_name, category_id, category_image, sub_cat_id, category_name));
                                                    Log.d("@@addTotalList5", String.valueOf(sub_cat_name));

                                                }
                                                if (category_id.equals("39")) {

                                                    com.Appzia.addpanda.Screens.basicEditsFirstActivity.list39.add(new categoryChildModel(image, sub_cat_name, category_id, category_image, sub_cat_id, category_name));
                                                    Log.d("@@addTotalList5", String.valueOf(sub_cat_name));

                                                }
                                                if (category_id.equals("40")) {

                                                    com.Appzia.addpanda.Screens.basicEditsFirstActivity.list40.add(new categoryChildModel(image, sub_cat_name, category_id, category_image, sub_cat_id, category_name));
                                                    Log.d("@@addTotalList5", String.valueOf(sub_cat_name));

                                                }
                                                if (category_id.equals("41")) {

                                                    com.Appzia.addpanda.Screens.basicEditsFirstActivity.list41.add(new categoryChildModel(image, sub_cat_name, category_id, category_image, sub_cat_id, category_name));
                                                    Log.d("@@addTotalList5", String.valueOf(sub_cat_name));

                                                }
                                                if (category_id.equals("42")) {

                                                    com.Appzia.addpanda.Screens.basicEditsFirstActivity.list42.add(new categoryChildModel(image, sub_cat_name, category_id, category_image, sub_cat_id, category_name));
                                                    Log.d("@@addTotalList5", String.valueOf(sub_cat_name));

                                                }
                                                if (category_id.equals("43")) {

                                                    com.Appzia.addpanda.Screens.basicEditsFirstActivity.list43.add(new categoryChildModel(image, sub_cat_name, category_id, category_image, sub_cat_id, category_name));
                                                    Log.d("@@addTotalList5", String.valueOf(sub_cat_name));

                                                }
                                                if (category_id.equals("44")) {

                                                    com.Appzia.addpanda.Screens.basicEditsFirstActivity.list44.add(new categoryChildModel(image, sub_cat_name, category_id, category_image, sub_cat_id, category_name));
                                                    Log.d("@@addTotalList5", String.valueOf(sub_cat_name));

                                                }
                                                if (category_id.equals("45")) {

                                                    com.Appzia.addpanda.Screens.basicEditsFirstActivity.list45.add(new categoryChildModel(image, sub_cat_name, category_id, category_image, sub_cat_id, category_name));
                                                    Log.d("@@addTotalList5", String.valueOf(sub_cat_name));

                                                }
                                                if (category_id.equals("46")) {

                                                    com.Appzia.addpanda.Screens.basicEditsFirstActivity.list46.add(new categoryChildModel(image, sub_cat_name, category_id, category_image, sub_cat_id, category_name));
                                                    Log.d("@@addTotalList5", String.valueOf(sub_cat_name));

                                                }
                                                if (category_id.equals("47")) {

                                                    com.Appzia.addpanda.Screens.basicEditsFirstActivity.list47.add(new categoryChildModel(image, sub_cat_name, category_id, category_image, sub_cat_id, category_name));
                                                    Log.d("@@addTotalList5", String.valueOf(sub_cat_name));

                                                }
                                                if (category_id.equals("48")) {

                                                    com.Appzia.addpanda.Screens.basicEditsFirstActivity.list48.add(new categoryChildModel(image, sub_cat_name, category_id, category_image, sub_cat_id, category_name));
                                                    Log.d("@@addTotalList5", String.valueOf(sub_cat_name));

                                                }
                                                if (category_id.equals("49")) {

                                                    com.Appzia.addpanda.Screens.basicEditsFirstActivity.list49.add(new categoryChildModel(image, sub_cat_name, category_id, category_image, sub_cat_id, category_name));
                                                    Log.d("@@addTotalList5", String.valueOf(sub_cat_name));

                                                }
                                                if (category_id.equals("50")) {

                                                    com.Appzia.addpanda.Screens.basicEditsFirstActivity.list50.add(new categoryChildModel(image, sub_cat_name, category_id, category_image, sub_cat_id, category_name));
                                                    Log.d("@@addTotalList5", String.valueOf(sub_cat_name));

                                                }


                                            }
                                            //data are stored in list below

                                            if (category_id.equals("1")) {

                                                //com.Appzia.addpanda.Fragments.mainFragment.list1.add(new categoryChildModel(image, sub_cat_name, category_id, category_image, sub_cat_id,category_name));
                                                com.Appzia.addpanda.Screens.basicEditsFirstActivity.parentModelArrayList.add(new categoryParentModel(category_name, category_id, com.Appzia.addpanda.Fragments.mainFragment.list1));
                                                Log.d("@@addTotalList1", String.valueOf(sub_cat_name));
                                            }

                                            if (category_id.equals("2")) {

                                                //    com.Appzia.addpanda.Screens.basicEditsFirstActivity.list2.add(new categoryChildModel(image, sub_cat_name, category_id, category_image, sub_cat_id,category_name));
                                                com.Appzia.addpanda.Screens.basicEditsFirstActivity.parentModelArrayList.add(new categoryParentModel(category_name, category_id, com.Appzia.addpanda.Fragments.mainFragment.list2));
                                                Log.d("@@addTotalList2", String.valueOf(sub_cat_name));
                                            }

                                            if (category_id.equals("3")) {

                                                //com.Appzia.addpanda.Fragments.mainFragment.list3.add(new categoryChildModel(image, sub_cat_name,category_id, category_id, category_image, sub_cat_id));
                                                com.Appzia.addpanda.Screens.basicEditsFirstActivity.parentModelArrayList.add(new categoryParentModel(category_name, category_id, com.Appzia.addpanda.Fragments.mainFragment.list3));
                                                Log.d("@@addTotalList3", String.valueOf(sub_cat_name));
                                            }

                                            if (category_id.equals("4")) {

                                                //com.Appzia.addpanda.Fragments.mainFragment.list3.add(new categoryChildModel(image, sub_cat_name,category_id, category_id, category_image, sub_cat_id));
                                                com.Appzia.addpanda.Screens.basicEditsFirstActivity.parentModelArrayList.add(new categoryParentModel(category_name, category_id, com.Appzia.addpanda.Fragments.mainFragment.list4));
                                                Log.d("@@addTotalList3", String.valueOf(sub_cat_name));
                                            }

                                            if (category_id.equals("5")) {

                                                //     com.Appzia.addpanda.Screens.basicEditsFirstActivity.list4.add(new categoryChildModel(image, sub_cat_name,category_id, category_id, category_image, sub_cat_id));
                                                com.Appzia.addpanda.Screens.basicEditsFirstActivity.parentModelArrayList.add(new categoryParentModel(category_name, category_id, com.Appzia.addpanda.Fragments.mainFragment.list5));
                                                Log.d("@@addTotalList5", String.valueOf(sub_cat_name));

                                            }

                                            if (category_id.equals("6")) {

//                                              com.Appzia.addpanda.Screens.basicEditsFirstActivity.list6.add(new categoryChildModel(image, sub_cat_name,category_id, category_id, category_image, sub_cat_id));
                                                com.Appzia.addpanda.Screens.basicEditsFirstActivity.parentModelArrayList.add(new categoryParentModel(category_name, category_id, com.Appzia.addpanda.Fragments.mainFragment.list6));

                                                Log.d("@@addTotalList5", String.valueOf(sub_cat_name));

                                            }
                                            if (category_id.equals("7")) {

//                                              com.Appzia.addpanda.Screens.basicEditsFirstActivity.list7.add(new categoryChildModel(image, sub_cat_name,category_id, category_id, category_image, sub_cat_id));
                                                com.Appzia.addpanda.Screens.basicEditsFirstActivity.parentModelArrayList.add(new categoryParentModel(category_name, category_id, com.Appzia.addpanda.Fragments.mainFragment.list7));
                                                Log.d("@@addTotalList5", String.valueOf(sub_cat_name));

                                            }
                                            if (category_id.equals("8")) {

//                                              com.Appzia.addpanda.Screens.basicEditsFirstActivity.list8.add(new categoryChildModel(image, sub_cat_name,category_id, category_id, category_image, sub_cat_id));
                                                com.Appzia.addpanda.Screens.basicEditsFirstActivity.parentModelArrayList.add(new categoryParentModel(category_name, category_id, com.Appzia.addpanda.Fragments.mainFragment.list8));
                                                Log.d("@@addTotalList5", String.valueOf(sub_cat_name));

                                            }
                                            if (category_id.equals("9")) {

//                                              com.Appzia.addpanda.Screens.basicEditsFirstActivity.list9.add(new categoryChildModel(image, sub_cat_name,category_id, category_id, category_image, sub_cat_id));
                                                com.Appzia.addpanda.Screens.basicEditsFirstActivity.parentModelArrayList.add(new categoryParentModel(category_name, category_id, com.Appzia.addpanda.Fragments.mainFragment.list9));
                                                Log.d("@@addTotalList5", String.valueOf(sub_cat_name));

                                            }
                                            if (category_id.equals("10")) {

//                                              com.Appzia.addpanda.Screens.basicEditsFirstActivity.list10.add(new categoryChildModel(image, sub_cat_name,category_id, category_id, category_image, sub_cat_id));
                                                com.Appzia.addpanda.Screens.basicEditsFirstActivity.parentModelArrayList.add(new categoryParentModel(category_name, category_id, com.Appzia.addpanda.Fragments.mainFragment.list10));
                                                Log.d("@@addTotalList5", String.valueOf(sub_cat_name));

                                            }
                                            if (category_id.equals("11")) {

//                                              com.Appzia.addpanda.Screens.basicEditsFirstActivity.list11.add(new categoryChildModel(image, sub_cat_name,category_id, category_id, category_image, sub_cat_id));
                                                com.Appzia.addpanda.Screens.basicEditsFirstActivity.parentModelArrayList.add(new categoryParentModel(category_name, category_id, com.Appzia.addpanda.Fragments.mainFragment.list11));
                                                Log.d("@@addTotalList5", String.valueOf(sub_cat_name));

                                            }
                                            if (category_id.equals("12")) {

//                                              com.Appzia.addpanda.Screens.basicEditsFirstActivity.list12.add(new categoryChildModel(image, sub_cat_name,category_id, category_id, category_image, sub_cat_id));
                                                com.Appzia.addpanda.Screens.basicEditsFirstActivity.parentModelArrayList.add(new categoryParentModel(category_name, category_id, com.Appzia.addpanda.Fragments.mainFragment.list12));
                                                Log.d("@@addTotalList5", String.valueOf(sub_cat_name));

                                            }
                                            if (category_id.equals("13")) {

//                                              com.Appzia.addpanda.Screens.basicEditsFirstActivity.list13.add(new categoryChildModel(image, sub_cat_name,category_id, category_id, category_image, sub_cat_id));
                                                com.Appzia.addpanda.Screens.basicEditsFirstActivity.parentModelArrayList.add(new categoryParentModel(category_name, category_id, com.Appzia.addpanda.Fragments.mainFragment.list13));
                                                Log.d("@@addTotalList5", String.valueOf(sub_cat_name));

                                            }
                                            if (category_id.equals("14")) {

//                                              com.Appzia.addpanda.Screens.basicEditsFirstActivity.list14.add(new categoryChildModel(image, sub_cat_name,category_id, category_id, category_image, sub_cat_id));
                                                com.Appzia.addpanda.Screens.basicEditsFirstActivity.parentModelArrayList.add(new categoryParentModel(category_name, category_id, com.Appzia.addpanda.Fragments.mainFragment.list14));
                                                Log.d("@@addTotalList5", String.valueOf(sub_cat_name));

                                            }
                                            if (category_id.equals("15")) {

//                                              com.Appzia.addpanda.Screens.basicEditsFirstActivity.list15.add(new categoryChildModel(image, sub_cat_name,category_id, category_id, category_image, sub_cat_id));
                                                com.Appzia.addpanda.Screens.basicEditsFirstActivity.parentModelArrayList.add(new categoryParentModel(category_name, category_id, com.Appzia.addpanda.Fragments.mainFragment.list15));
                                                Log.d("@@addTotalList5", String.valueOf(sub_cat_name));

                                            }
                                            if (category_id.equals("16")) {

//                                              com.Appzia.addpanda.Screens.basicEditsFirstActivity.list16.add(new categoryChildModel(image, sub_cat_name,category_id, category_id, category_image, sub_cat_id));
                                                com.Appzia.addpanda.Screens.basicEditsFirstActivity.parentModelArrayList.add(new categoryParentModel(category_name, category_id, com.Appzia.addpanda.Fragments.mainFragment.list16));
                                                Log.d("@@addTotalList5", String.valueOf(sub_cat_name));

                                            }
                                            if (category_id.equals("17")) {

//                                              com.Appzia.addpanda.Screens.basicEditsFirstActivity.list17.add(new categoryChildModel(image, sub_cat_name,category_id, category_id, category_image, sub_cat_id));
                                                com.Appzia.addpanda.Screens.basicEditsFirstActivity.parentModelArrayList.add(new categoryParentModel(category_name, category_id, com.Appzia.addpanda.Fragments.mainFragment.list17));
                                                Log.d("@@addTotalList5", String.valueOf(sub_cat_name));

                                            }
                                            if (category_id.equals("18")) {

//                                              com.Appzia.addpanda.Screens.basicEditsFirstActivity.list18.add(new categoryChildModel(image, sub_cat_name,category_id, category_id, category_image, sub_cat_id));
                                                com.Appzia.addpanda.Screens.basicEditsFirstActivity.parentModelArrayList.add(new categoryParentModel(category_name, category_id, com.Appzia.addpanda.Fragments.mainFragment.list18));
                                                Log.d("@@addTotalList5", String.valueOf(sub_cat_name));

                                            }
                                            if (category_id.equals("19")) {

//                                              com.Appzia.addpanda.Screens.basicEditsFirstActivity.list19.add(new categoryChildModel(image, sub_cat_name,category_id, category_id, category_image, sub_cat_id));
                                                com.Appzia.addpanda.Screens.basicEditsFirstActivity.parentModelArrayList.add(new categoryParentModel(category_name, category_id, com.Appzia.addpanda.Fragments.mainFragment.list19));
                                                Log.d("@@addTotalList5", String.valueOf(sub_cat_name));

                                            }
                                            if (category_id.equals("20")) {

//                                              com.Appzia.addpanda.Screens.basicEditsFirstActivity.list20.add(new categoryChildModel(image, sub_cat_name,category_id, category_id, category_image, sub_cat_id));
                                                com.Appzia.addpanda.Screens.basicEditsFirstActivity.parentModelArrayList.add(new categoryParentModel(category_name, category_id, com.Appzia.addpanda.Fragments.mainFragment.list20));
                                                Log.d("@@addTotalList5", String.valueOf(sub_cat_name));

                                            }
                                            if (category_id.equals("21")) {

//                                              com.Appzia.addpanda.Screens.basicEditsFirstActivity.list21.add(new categoryChildModel(image, sub_cat_name,category_id, category_id, category_image, sub_cat_id));
                                                com.Appzia.addpanda.Screens.basicEditsFirstActivity.parentModelArrayList.add(new categoryParentModel(category_name, category_id, com.Appzia.addpanda.Fragments.mainFragment.list21));
                                                Log.d("@@addTotalList5", String.valueOf(sub_cat_name));

                                            }
                                            if (category_id.equals("22")) {

//                                              com.Appzia.addpanda.Screens.basicEditsFirstActivity.list5.add(new categoryChildModel(image, sub_cat_name,category_id, category_id, category_image, sub_cat_id));
                                                com.Appzia.addpanda.Screens.basicEditsFirstActivity.parentModelArrayList.add(new categoryParentModel(category_name, category_id, com.Appzia.addpanda.Fragments.mainFragment.list22));
                                                Log.d("@@addTotalList5", String.valueOf(sub_cat_name));

                                            }
                                            if (category_id.equals("23")) {

//                                              com.Appzia.addpanda.Screens.basicEditsFirstActivity.list22.add(new categoryChildModel(image, sub_cat_name,category_id, category_id, category_image, sub_cat_id));
                                                com.Appzia.addpanda.Screens.basicEditsFirstActivity.parentModelArrayList.add(new categoryParentModel(category_name, category_id, com.Appzia.addpanda.Fragments.mainFragment.list23));
                                                Log.d("@@addTotalList5", String.valueOf(sub_cat_name));

                                            }
                                            if (category_id.equals("24")) {

//                                              com.Appzia.addpanda.Screens.basicEditsFirstActivity.list23.add(new categoryChildModel(image, sub_cat_name,category_id, category_id, category_image, sub_cat_id));
                                                com.Appzia.addpanda.Screens.basicEditsFirstActivity.parentModelArrayList.add(new categoryParentModel(category_name, category_id, com.Appzia.addpanda.Fragments.mainFragment.list24));
                                                Log.d("@@addTotalList5", String.valueOf(sub_cat_name));

                                            }
                                            if (category_id.equals("25")) {

//                                              com.Appzia.addpanda.Screens.basicEditsFirstActivity.list24.add(new categoryChildModel(image, sub_cat_name,category_id, category_id, category_image, sub_cat_id));
                                                com.Appzia.addpanda.Screens.basicEditsFirstActivity.parentModelArrayList.add(new categoryParentModel(category_name, category_id, com.Appzia.addpanda.Fragments.mainFragment.list25));
                                                Log.d("@@addTotalList5", String.valueOf(sub_cat_name));

                                            }
                                            if (category_id.equals("26")) {

//                                              com.Appzia.addpanda.Screens.basicEditsFirstActivity.list25.add(new categoryChildModel(image, sub_cat_name,category_id, category_id, category_image, sub_cat_id));
                                                com.Appzia.addpanda.Screens.basicEditsFirstActivity.parentModelArrayList.add(new categoryParentModel(category_name, category_id, com.Appzia.addpanda.Fragments.mainFragment.list26));
                                                Log.d("@@addTotalList5", String.valueOf(sub_cat_name));

                                            }
                                            if (category_id.equals("27")) {

//                                              com.Appzia.addpanda.Screens.basicEditsFirstActivity.list26.add(new categoryChildModel(image, sub_cat_name,category_id, category_id, category_image, sub_cat_id));
                                                com.Appzia.addpanda.Screens.basicEditsFirstActivity.parentModelArrayList.add(new categoryParentModel(category_name, category_id, com.Appzia.addpanda.Fragments.mainFragment.list27));
                                                Log.d("@@addTotalList5", String.valueOf(sub_cat_name));

                                            }
                                            if (category_id.equals("28")) {

//                                              com.Appzia.addpanda.Screens.basicEditsFirstActivity.list28.add(new categoryChildModel(image, sub_cat_name,category_id, category_id, category_image, sub_cat_id));
                                                com.Appzia.addpanda.Screens.basicEditsFirstActivity.parentModelArrayList.add(new categoryParentModel(category_name, category_id, com.Appzia.addpanda.Fragments.mainFragment.list28));
                                                Log.d("@@addTotalList5", String.valueOf(sub_cat_name));

                                            }
                                            if (category_id.equals("29")) {

//                                              com.Appzia.addpanda.Screens.basicEditsFirstActivity.list29.add(new categoryChildModel(image, sub_cat_name,category_id, category_id, category_image, sub_cat_id));
                                                com.Appzia.addpanda.Screens.basicEditsFirstActivity.parentModelArrayList.add(new categoryParentModel(category_name, category_id, com.Appzia.addpanda.Fragments.mainFragment.list29));
                                                Log.d("@@addTotalList5", String.valueOf(sub_cat_name));

                                            }
                                            if (category_id.equals("30")) {

//                                              com.Appzia.addpanda.Screens.basicEditsFirstActivity.list30.add(new categoryChildModel(image, sub_cat_name,category_id, category_id, category_image, sub_cat_id));
                                                com.Appzia.addpanda.Screens.basicEditsFirstActivity.parentModelArrayList.add(new categoryParentModel(category_name, category_id, com.Appzia.addpanda.Fragments.mainFragment.list30));
                                                Log.d("@@addTotalList5", String.valueOf(sub_cat_name));

                                            }
                                            if (category_id.equals("31")) {

//                                              com.Appzia.addpanda.Screens.basicEditsFirstActivity.list31.add(new categoryChildModel(image, sub_cat_name,category_id, category_id, category_image, sub_cat_id));
                                                com.Appzia.addpanda.Screens.basicEditsFirstActivity.parentModelArrayList.add(new categoryParentModel(category_name, category_id, com.Appzia.addpanda.Fragments.mainFragment.list31));
                                                Log.d("@@addTotalList5", String.valueOf(sub_cat_name));

                                            }
                                            if (category_id.equals("32")) {

//                                              com.Appzia.addpanda.Screens.basicEditsFirstActivity.list32.add(new categoryChildModel(image, sub_cat_name,category_id, category_id, category_image, sub_cat_id));
                                                com.Appzia.addpanda.Screens.basicEditsFirstActivity.parentModelArrayList.add(new categoryParentModel(category_name, category_id, com.Appzia.addpanda.Fragments.mainFragment.list32));
                                                Log.d("@@addTotalList5", String.valueOf(sub_cat_name));

                                            }
                                            if (category_id.equals("33")) {

//                                              com.Appzia.addpanda.Screens.basicEditsFirstActivity.list33.add(new categoryChildModel(image, sub_cat_name,category_id, category_id, category_image, sub_cat_id));
                                                com.Appzia.addpanda.Screens.basicEditsFirstActivity.parentModelArrayList.add(new categoryParentModel(category_name, category_id, com.Appzia.addpanda.Fragments.mainFragment.list33));
                                                Log.d("@@addTotalList5", String.valueOf(sub_cat_name));

                                            }
                                            if (category_id.equals("34")) {

//                                              com.Appzia.addpanda.Screens.basicEditsFirstActivity.list34.add(new categoryChildModel(image, sub_cat_name,category_id, category_id, category_image, sub_cat_id));
                                                com.Appzia.addpanda.Screens.basicEditsFirstActivity.parentModelArrayList.add(new categoryParentModel(category_name, category_id, com.Appzia.addpanda.Fragments.mainFragment.list34));
                                                Log.d("@@addTotalList5", String.valueOf(sub_cat_name));

                                            }
                                            if (category_id.equals("35")) {

//                                              com.Appzia.addpanda.Screens.basicEditsFirstActivity.list35.add(new categoryChildModel(image, sub_cat_name,category_id, category_id, category_image, sub_cat_id));
                                                com.Appzia.addpanda.Screens.basicEditsFirstActivity.parentModelArrayList.add(new categoryParentModel(category_name, category_id, com.Appzia.addpanda.Fragments.mainFragment.list35));
                                                Log.d("@@addTotalList5", String.valueOf(sub_cat_name));

                                            }
                                            if (category_id.equals("36")) {

//                                              com.Appzia.addpanda.Screens.basicEditsFirstActivity.list36.add(new categoryChildModel(image, sub_cat_name,category_id, category_id, category_image, sub_cat_id));
                                                com.Appzia.addpanda.Screens.basicEditsFirstActivity.parentModelArrayList.add(new categoryParentModel(category_name, category_id, com.Appzia.addpanda.Fragments.mainFragment.list36));
                                                Log.d("@@addTotalList5", String.valueOf(sub_cat_name));

                                            }
                                            if (category_id.equals("37")) {

//                                              com.Appzia.addpanda.Screens.basicEditsFirstActivity.list37.add(new categoryChildModel(image, sub_cat_name,category_id, category_id, category_image, sub_cat_id));
                                                com.Appzia.addpanda.Screens.basicEditsFirstActivity.parentModelArrayList.add(new categoryParentModel(category_name, category_id, com.Appzia.addpanda.Fragments.mainFragment.list37));
                                                Log.d("@@addTotalList5", String.valueOf(sub_cat_name));

                                            }
                                            if (category_id.equals("38")) {

//                                              com.Appzia.addpanda.Screens.basicEditsFirstActivity.list38.add(new categoryChildModel(image, sub_cat_name,category_id, category_id, category_image, sub_cat_id));
                                                com.Appzia.addpanda.Screens.basicEditsFirstActivity.parentModelArrayList.add(new categoryParentModel(category_name, category_id, com.Appzia.addpanda.Fragments.mainFragment.list38));
                                                Log.d("@@addTotalList5", String.valueOf(sub_cat_name));

                                            }
                                            if (category_id.equals("39")) {

//                                              com.Appzia.addpanda.Screens.basicEditsFirstActivity.list39.add(new categoryChildModel(image, sub_cat_name,category_id, category_id, category_image, sub_cat_id));
                                                com.Appzia.addpanda.Screens.basicEditsFirstActivity.parentModelArrayList.add(new categoryParentModel(category_name, category_id, com.Appzia.addpanda.Fragments.mainFragment.list39));
                                                Log.d("@@addTotalList5", String.valueOf(sub_cat_name));

                                            }
                                            if (category_id.equals("40")) {

//                                              com.Appzia.addpanda.Screens.basicEditsFirstActivity.list40.add(new categoryChildModel(image, sub_cat_name,category_id, category_id, category_image, sub_cat_id));
                                                com.Appzia.addpanda.Screens.basicEditsFirstActivity.parentModelArrayList.add(new categoryParentModel(category_name, category_id, com.Appzia.addpanda.Fragments.mainFragment.list40));
                                                Log.d("@@addTotalList5", String.valueOf(sub_cat_name));

                                            }
                                            if (category_id.equals("41")) {

//                                              com.Appzia.addpanda.Screens.basicEditsFirstActivity.list41.add(new categoryChildModel(image, sub_cat_name,category_id, category_id, category_image, sub_cat_id));
                                                com.Appzia.addpanda.Screens.basicEditsFirstActivity.parentModelArrayList.add(new categoryParentModel(category_name, category_id, com.Appzia.addpanda.Fragments.mainFragment.list41));
                                                Log.d("@@addTotalList5", String.valueOf(sub_cat_name));

                                            }
                                            if (category_id.equals("42")) {

//                                              com.Appzia.addpanda.Screens.basicEditsFirstActivity.list42.add(new categoryChildModel(image, sub_cat_name,category_id, category_id, category_image, sub_cat_id));
                                                com.Appzia.addpanda.Screens.basicEditsFirstActivity.parentModelArrayList.add(new categoryParentModel(category_name, category_id, com.Appzia.addpanda.Fragments.mainFragment.list42));
                                                Log.d("@@addTotalList5", String.valueOf(sub_cat_name));

                                            }
                                            if (category_id.equals("43")) {

//                                              com.Appzia.addpanda.Screens.basicEditsFirstActivity.list43.add(new categoryChildModel(image, sub_cat_name,category_id, category_id, category_image, sub_cat_id));
                                                com.Appzia.addpanda.Screens.basicEditsFirstActivity.parentModelArrayList.add(new categoryParentModel(category_name, category_id, com.Appzia.addpanda.Fragments.mainFragment.list43));
                                                Log.d("@@addTotalList5", String.valueOf(sub_cat_name));

                                            }
                                            if (category_id.equals("44")) {

//                                              com.Appzia.addpanda.Screens.basicEditsFirstActivity.list44.add(new categoryChildModel(image, sub_cat_name,category_id, category_id, category_image, sub_cat_id));
                                                com.Appzia.addpanda.Screens.basicEditsFirstActivity.parentModelArrayList.add(new categoryParentModel(category_name, category_id, com.Appzia.addpanda.Fragments.mainFragment.list44));
                                                Log.d("@@addTotalList5", String.valueOf(sub_cat_name));

                                            }
                                            if (category_id.equals("45")) {

//                                              com.Appzia.addpanda.Screens.basicEditsFirstActivity.list45.add(new categoryChildModel(image, sub_cat_name,category_id, category_id, category_image, sub_cat_id));
                                                com.Appzia.addpanda.Screens.basicEditsFirstActivity.parentModelArrayList.add(new categoryParentModel(category_name, category_id, com.Appzia.addpanda.Fragments.mainFragment.list45));
                                                Log.d("@@addTotalList5", String.valueOf(sub_cat_name));

                                            }
                                            if (category_id.equals("46")) {

//                                              com.Appzia.addpanda.Screens.basicEditsFirstActivity.list46.add(new categoryChildModel(image, sub_cat_name,category_id, category_id, category_image, sub_cat_id));
                                                com.Appzia.addpanda.Screens.basicEditsFirstActivity.parentModelArrayList.add(new categoryParentModel(category_name, category_id, com.Appzia.addpanda.Fragments.mainFragment.list46));
                                                Log.d("@@addTotalList5", String.valueOf(sub_cat_name));

                                            }
                                            if (category_id.equals("47")) {

//                                              com.Appzia.addpanda.Screens.basicEditsFirstActivity.list47.add(new categoryChildModel(image, sub_cat_name,category_id, category_id, category_image, sub_cat_id));
                                                com.Appzia.addpanda.Screens.basicEditsFirstActivity.parentModelArrayList.add(new categoryParentModel(category_name, category_id, com.Appzia.addpanda.Fragments.mainFragment.list47));
                                                Log.d("@@addTotalList5", String.valueOf(sub_cat_name));

                                            }
                                            if (category_id.equals("48")) {

//                                              com.Appzia.addpanda.Screens.basicEditsFirstActivity.list48.add(new categoryChildModel(image, sub_cat_name,category_id, category_id, category_image, sub_cat_id));
                                                com.Appzia.addpanda.Screens.basicEditsFirstActivity.parentModelArrayList.add(new categoryParentModel(category_name, category_id, com.Appzia.addpanda.Fragments.mainFragment.list48));
                                                Log.d("@@addTotalList5", String.valueOf(sub_cat_name));

                                            }
                                            if (category_id.equals("49")) {

//                                              com.Appzia.addpanda.Screens.basicEditsFirstActivity.list49.add(new categoryChildModel(image, sub_cat_name,category_id, category_id, category_image, sub_cat_id));
                                                com.Appzia.addpanda.Screens.basicEditsFirstActivity.parentModelArrayList.add(new categoryParentModel(category_name, category_id, com.Appzia.addpanda.Fragments.mainFragment.list49));
                                                Log.d("@@addTotalList5", String.valueOf(sub_cat_name));

                                            }
                                            if (category_id.equals("50")) {

//                                              com.Appzia.addpanda.Screens.basicEditsFirstActivity.list50.add(new categoryChildModel(image, sub_cat_name,category_id, category_id, category_image, sub_cat_id));
                                                com.Appzia.addpanda.Screens.basicEditsFirstActivity.parentModelArrayList.add(new categoryParentModel(category_name, category_id, com.Appzia.addpanda.Fragments.mainFragment.list50));
                                                Log.d("@@addTotalList5", String.valueOf(sub_cat_name));

                                            }
                                            basicEditsFirstActivity.shimmerFrameLayout.stopShimmer();
                                            basicEditsFirstActivity.shimmerFrameLayout.setVisibility(View.GONE);
                                            basicEditsFirstActivity.CoLayout.setVisibility(View.VISIBLE);
                                        }


                                    }


                                    ///  fragment.setAdapter();


                                } else {
                                    String message = response.getString("message");
                                    // Toast.makeText(mContext, message, Toast.LENGTH_SHORT).show();
                                }
                            } catch (JSONException e) {

                                e.printStackTrace();

                                Log.d("@@@ notSuccess: ", e.getMessage());
                            }
                            super.onSuccess(statusCode, headers, response);
                        }

                        @Override
                        public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {


                            super.onFailure(statusCode, headers, responseString, throwable);
                        }

                        @Override
                        public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {


                            super.onFailure(statusCode, headers, throwable, errorResponse);
                        }

                        @Override
                        public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONArray errorResponse) {


                            super.onFailure(statusCode, headers, throwable, errorResponse);
                        }
                    });
                }
            });
        } catch (Exception e) {


            e.printStackTrace();
        }
    }

//    public static void get_category_list_All_ScratchEdit(final Context mContext, String token, scratchActivity fragment, String flag) {
//
//        try {
//
//            Constant.scratcheditTrendingModelSubList.clear();
//            Constant.scratcheditAniversaryModelSubList.clear();
//            final AsyncHttpClient client = new AsyncHttpClient();
//            client.setSSLSocketFactory(MySSLSocketFactory.getFixedSocketFactory());
//            final RequestParams request_param = new RequestParams();
//            request_param.put("flag", flag);
//
//
//            Log.d(TAG, "@@@scratcheditTrendingModelSubList :" + get_category_list_All_BASIC_EDITS + "?" + request_param.toString());
//            ((Activity) mContext).runOnUiThread(new Runnable() {
//                public void run() {
//
//
//                    client.addHeader("token", token);
//                    client.post(get_category_list_All_BASIC_EDITS, request_param, new JsonHttpResponseHandler() {
//                        @Override
//                        public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
//                            Log.d(TAG, "@@@scratcheditTrendingModelSubList_response :" + response.toString());
//
//                            try {
//                                int status = response.getInt("error_code");
//
//                                if (status == 200) {
//
//
//                                    JSONArray arr = response.getJSONArray("data");
//
//
//                                    for (int i = 0; i < arr.length(); i++) {
//                                        JSONObject obj2 = arr.getJSONObject(i);
//                                        String category_name = obj2.getString("category_name");
//
//                                        String category_image = obj2.getString("category_image");
//                                        String category_id = obj2.getString("category_id");
//
//
//                                        JSONArray sub_category_list = obj2.getJSONArray("subcategory");
//                                        Log.d("@@@scratcheditTrendingModelSubList", String.valueOf(sub_category_list));
//
//
//                                        if (category_id.equals("1")) {
//                                            Log.d("@@@category_id_basic", category_name + category_image);
//
//                                            scratchFragment.trendingtextscratch.setText(category_name);
//
//                                            for (int j = 0; j < sub_category_list.length(); j++) {
//                                                JSONObject obj3 = sub_category_list.getJSONObject(j);
//
//                                                String sub_cat_name = obj3.getString("sub_cat_name");
//                                                String image = obj3.getString("image");
////
//                                                Log.d("@@sub_cat_name", sub_cat_name);
//                                                Log.d("@@image", image);
//
//                                                Constant.scratcheditTrendingModelSubList.add(new scratcheditTrendingModel(image, sub_cat_name, category_id, category_image));
//
//                                            }
//
//
//                                        }
//
//
//                                        if (category_id.equals("2")) {
//                                            Log.d("@@@category_id_one", category_name + category_image);
//                                            scratchActivity.anitextscratch.setText(category_name);
//
//
//                                            for (int j = 0; j < sub_category_list.length(); j++) {
//                                                JSONObject obj3 = sub_category_list.getJSONObject(j);
//
//                                                String sub_cat_name = obj3.getString("sub_cat_name");
//                                                String image = obj3.getString("image");
//
//                                                Log.d("@@image2", image);
//
//                                                Constant.scratcheditAniversaryModelSubList.add(new scratcheditAniversaryModel(image, sub_cat_name, category_id, category_image));
//
//                                            }
//
//
//                                        }
//
//                                    }
//
//
//                                    fragment.setAdapter();
//
//
//                                } else {
//                                    String message = response.getString("message");
//                                    // Toast.makeText(mContext, message, Toast.LENGTH_SHORT).show();
//                                }
//                            } catch (JSONException e) {
//
//                                e.printStackTrace();
//
//                                Log.d("@@@ notSuccess: ", e.getMessage());
//                            }
//                            super.onSuccess(statusCode, headers, response);
//                        }
//
//                        @Override
//                        public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
//
//
//                            super.onFailure(statusCode, headers, responseString, throwable);
//                        }
//
//                        @Override
//                        public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
//
//
//
//
//                            super.onFailure(statusCode, headers, throwable, errorResponse);
//                        }
//
//                        @Override
//                        public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONArray errorResponse) {
//
//
//
//
//                            super.onFailure(statusCode, headers, throwable, errorResponse);
//                        }
//                    });
//                }
//            });
//        } catch (Exception e) {
//
//
//
//
//            e.printStackTrace();
//        }
//    }


    public static void get_frame_list(final Context mContext, String token, trendingActivity fragment, String category_idnew, String sub_cat_id, String template_id) {

        try {

            final AsyncHttpClient client = new AsyncHttpClient();
            client.setSSLSocketFactory(MySSLSocketFactory.getFixedSocketFactory());
            final RequestParams request_param = new RequestParams();
//            request_param.put("category_id", category_idnew);
//            request_param.put("sub_cat_id", sub_cat_id);
//            request_param.put("template_id", template_id);


            Log.d(TAG, "@@@get_frame_list :" + GET_FRAME_LIST + "?" + request_param.toString());
            ((Activity) mContext).runOnUiThread(new Runnable() {
                public void run() {

                    Constant.frameList.clear();
                    client.addHeader("token", token);
                    client.post(GET_FRAME_LIST, request_param, new JsonHttpResponseHandler() {
                        @Override
                        public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                            Log.d(TAG, "@@@get_frame_list_response :" + response.toString());

                            try {
                                int status = response.getInt("error_code");

                                if (status == 200) {


                                    JSONArray arr = response.getJSONArray("data");


                                    for (int i = 0; i < arr.length(); i++) {
                                        JSONObject obj2 = arr.getJSONObject(i);
                                        String category_name = obj2.getString("category_name");


                                        String category_id = obj2.getString("category_id");
                                        String image = obj2.getString("image");


                                        Constant.frameList.add(new frameModel(image));
//                                        if (category_id.equals(category_idnew)) {
//                                            //   Toast.makeText(mContext, "SUccess", Toast.LENGTH_SHORT).show();
//                                            Log.d("@@@category_get_frame_listone", category_name);
//
//
//
//
//
//                                        }


                                    }

                                    fragment.setFrameAdapter();


                                    fragment.setAdapter();


                                } else {
                                    String message = response.getString("message");
                                    // Toast.makeText(mContext, message, Toast.LENGTH_SHORT).show();
                                }
                            } catch (JSONException e) {

                                e.printStackTrace();

                                Log.d("@@@ notSuccess: ", e.getMessage());
                            }
                            super.onSuccess(statusCode, headers, response);
                        }

                        @Override
                        public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {


                            super.onFailure(statusCode, headers, responseString, throwable);
                        }

                        @Override
                        public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {


                            super.onFailure(statusCode, headers, throwable, errorResponse);
                        }

                        @Override
                        public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONArray errorResponse) {


                            super.onFailure(statusCode, headers, throwable, errorResponse);
                        }
                    });
                }
            });
        } catch (Exception e) {


            e.printStackTrace();
        }
    }

    public static void get_frame_list_Basic(final Context mContext, String token, trendingActivityBasic fragment, String category_idnew, String sub_cat_id, String template_id) {

        try {

            final AsyncHttpClient client = new AsyncHttpClient();
            client.setSSLSocketFactory(MySSLSocketFactory.getFixedSocketFactory());
            final RequestParams request_param = new RequestParams();
            request_param.put("category_id", category_idnew);
            request_param.put("sub_cat_id", sub_cat_id);
            request_param.put("template_id", template_id);


            Log.d(TAG, "@@@get_frame_list :" + GET_FRAME_LIST + "?" + request_param.toString());
            ((Activity) mContext).runOnUiThread(new Runnable() {
                public void run() {

                    Constant.frameList.clear();
                    client.addHeader("token", token);
                    client.post(GET_FRAME_LIST, request_param, new JsonHttpResponseHandler() {
                        @Override
                        public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                            Log.d(TAG, "@@@get_frame_list_response :" + response.toString());

                            try {
                                int status = response.getInt("error_code");

                                if (status == 200) {


                                    JSONArray arr = response.getJSONArray("data");


                                    for (int i = 0; i < arr.length(); i++) {
                                        JSONObject obj2 = arr.getJSONObject(i);
                                        String category_name = obj2.getString("category_name");


                                        String category_id = obj2.getString("category_id");
                                        String image = obj2.getString("image");


                                        if (category_id.equals(category_idnew)) {
                                            //   Toast.makeText(mContext, "SUccess", Toast.LENGTH_SHORT).show();


                                            Constant.frameList.add(new frameModel(image));


                                        }
//
//
//                                        if (category_id.equals("2")) {
//                                            //    Toast.makeText(mContext, "SUccess", Toast.LENGTH_SHORT).show();
//                                            Log.d("@@@category_get_frame_listtwo", category_name);
//                                            Constant.frameList.add(new frameModel(image));
//
//
//                                        }

                                    }

                                    fragment.setFrameAdapter();


                                    fragment.setAdapter();


                                } else {
                                    String message = response.getString("message");
                                    // Toast.makeText(mContext, message, Toast.LENGTH_SHORT).show();
                                }
                            } catch (JSONException e) {

                                e.printStackTrace();

                                Log.d("@@@ notSuccess: ", e.getMessage());
                            }
                            super.onSuccess(statusCode, headers, response);
                        }

                        @Override
                        public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {


                            super.onFailure(statusCode, headers, responseString, throwable);
                        }

                        @Override
                        public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {


                            super.onFailure(statusCode, headers, throwable, errorResponse);
                        }

                        @Override
                        public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONArray errorResponse) {


                            super.onFailure(statusCode, headers, throwable, errorResponse);
                        }
                    });
                }
            });
        } catch (Exception e) {


            e.printStackTrace();
        }
    }

    public static void get_frame_list_two(final Context mContext, String token, editFameActivity editFameActivity, String category_id, String sub_cat_id, String template_id) {

        try {

            final AsyncHttpClient client = new AsyncHttpClient();
            client.setSSLSocketFactory(MySSLSocketFactory.getFixedSocketFactory());
            final RequestParams request_param = new RequestParams();
//            request_param.put("category_id", category_id);
//            request_param.put("sub_cat_id", sub_cat_id);
//            request_param.put("template_id", template_id);


            Log.d(TAG, "@@@get_frame_list :" + GET_FRAME_LIST + "?" + request_param.toString());
            ((Activity) mContext).runOnUiThread(new Runnable() {
                public void run() {

                    Constant.frameList.clear();
                    Constant.frameListTwo.clear();
                    client.addHeader("token", token);
                    client.post(GET_FRAME_LIST, request_param, new JsonHttpResponseHandler() {
                        @Override
                        public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                            Log.d(TAG, "@@@get_frame_list_response :" + response.toString());

                            try {
                                int status = response.getInt("error_code");

                                if (status == 200) {


                                    JSONArray arr = response.getJSONArray("data");


                                    for (int i = 0; i < arr.length(); i++) {
                                        JSONObject obj2 = arr.getJSONObject(i);
                                        String category_name = obj2.getString("category_name");


                                        String category_id = obj2.getString("category_id");
                                        String image = obj2.getString("image");

                                        Constant.frameListTwo.add(new frameModel(image));


                                    }

                                    editFameActivity.setAdapter();


                                } else {
                                    String message = response.getString("message");
                                    // Toast.makeText(mContext, message, Toast.LENGTH_SHORT).show();
                                }
                            } catch (JSONException e) {

                                e.printStackTrace();

                                Log.d("@@@ notSuccess: ", e.getMessage());
                            }
                            super.onSuccess(statusCode, headers, response);
                        }

                        @Override
                        public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {


                            super.onFailure(statusCode, headers, responseString, throwable);
                        }

                        @Override
                        public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {


                            super.onFailure(statusCode, headers, throwable, errorResponse);
                        }

                        @Override
                        public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONArray errorResponse) {


                            super.onFailure(statusCode, headers, throwable, errorResponse);
                        }
                    });
                }
            });
        } catch (Exception e) {


            e.printStackTrace();
        }
    }

    public static void get_frame_list_twoBasic(final Context mContext, String token, Basic_editFrameActivity editFameActivity, String category_id, String sub_cat_id, String template_id) {

        try {

            final AsyncHttpClient client = new AsyncHttpClient();
            client.setSSLSocketFactory(MySSLSocketFactory.getFixedSocketFactory());
            final RequestParams request_param = new RequestParams();
//            request_param.put("category_id", category_id);
//            request_param.put("sub_cat_id", sub_cat_id);
//            request_param.put("template_id", template_id);


            Log.d(TAG, "@@@get_frame_list :" + GET_FRAME_LIST + "?" + request_param.toString());
            ((Activity) mContext).runOnUiThread(new Runnable() {
                public void run() {

                    Constant.frameList.clear();
                    Constant.frameListTwo.clear();
                    client.addHeader("token", token);
                    client.post(GET_FRAME_LIST, request_param, new JsonHttpResponseHandler() {
                        @Override
                        public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                            Log.d(TAG, "@@@get_frame_list_response :" + response.toString());

                            try {
                                int status = response.getInt("error_code");

                                if (status == 200) {


                                    JSONArray arr = response.getJSONArray("data");


                                    for (int i = 0; i < arr.length(); i++) {
                                        JSONObject obj2 = arr.getJSONObject(i);
                                        String category_name = obj2.getString("category_name");


                                        String category_id = obj2.getString("category_id");
                                        String image = obj2.getString("image");


                                        if (category_id.equals("1")) {
                                            //   Toast.makeText(mContext, "SUccess", Toast.LENGTH_SHORT).show();


                                            Constant.frameListTwo.add(new frameModel(image));


                                        }


                                        if (category_id.equals("2")) {
                                            //    Toast.makeText(mContext, "SUccess", Toast.LENGTH_SHORT).show();

                                            Constant.frameListTwo.add(new frameModel(image));


                                        }

                                    }

                                    editFameActivity.setAdapter();


                                } else {
                                    String message = response.getString("message");
                                    // Toast.makeText(mContext, message, Toast.LENGTH_SHORT).show();
                                }
                            } catch (JSONException e) {

                                e.printStackTrace();

                                Log.d("@@@ notSuccess: ", e.getMessage());
                            }
                            super.onSuccess(statusCode, headers, response);
                        }

                        @Override
                        public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {


                            super.onFailure(statusCode, headers, responseString, throwable);
                        }

                        @Override
                        public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {


                            super.onFailure(statusCode, headers, throwable, errorResponse);
                        }

                        @Override
                        public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONArray errorResponse) {


                            super.onFailure(statusCode, headers, throwable, errorResponse);
                        }
                    });
                }
            });
        } catch (Exception e) {


            e.printStackTrace();
        }
    }


    public static void get_sub_category_list(final Context mContext, String category_idnew, String token, viewAll viewall) {

        try {

            final AsyncHttpClient client = new AsyncHttpClient();
            client.setSSLSocketFactory(MySSLSocketFactory.getFixedSocketFactory());
            final RequestParams request_param = new RequestParams();
            request_param.put("category_id", category_idnew);


            Log.d(TAG, "@@@get_sub_category_list :" + GET_SUBCAT_ID + "?" + request_param.toString());
            ((Activity) mContext).runOnUiThread(new Runnable() {
                public void run() {
                    viewAll.progressBar.setVisibility(View.VISIBLE);

                    Constant.viewAllList.clear();
                    Constant.viewAllListAnnni.clear();
                    client.addHeader("token", token);
                    client.post(GET_SUBCAT_ID, request_param, new JsonHttpResponseHandler() {
                        @Override
                        public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                            Log.d(TAG, "@@@get_sub_category_list_response :" + response.toString());

                            try {
                                int status = response.getInt("error_code");
                                if (status == 200) {

                                    JSONArray arr = response.getJSONArray("data");


                                    for (int i = 0; i < arr.length(); i++) {
                                        JSONObject obj2 = arr.getJSONObject(i);
                                        String category_name = obj2.getString("category_name");
                                        String sub_cat_name = obj2.getString("sub_cat_name");


                                        String category_id = obj2.getString("category_id");
                                        String image = obj2.getString("image");
                                        String sub_cat_id = obj2.getString("sub_cat_id");
                                        int is_active = obj2.getInt("is_active");


                                        if (category_id.equals(category_idnew)) {
                                            //   Toast.makeText(mContext, "SUccess", Toast.LENGTH_SHORT).show();
                                            Log.d("@@@sub_cat_name", sub_cat_name);

                                            Constant.viewAllList.add(new trendingSubModel(image, category_id, sub_cat_id, sub_cat_name, "", is_active));
                                            viewall.setAdapter();

                                        }


                                    }

                                    viewAll.progressBar.setVisibility(View.INVISIBLE);


                                } else {
                                    String message = response.getString("message");
                                    // Toast.makeText(mContext, message, Toast.LENGTH_SHORT).show();
                                }
                            } catch (JSONException e) {

                                e.printStackTrace();

                                Log.d("@@@ notSuccess: ", e.getMessage());
                            }
                            super.onSuccess(statusCode, headers, response);
                        }

                        @Override
                        public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {


                            super.onFailure(statusCode, headers, responseString, throwable);
                        }

                        @Override
                        public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {


                            super.onFailure(statusCode, headers, throwable, errorResponse);
                        }

                        @Override
                        public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONArray errorResponse) {


                            super.onFailure(statusCode, headers, throwable, errorResponse);
                        }
                    });
                }
            });
        } catch (Exception e) {


            e.printStackTrace();
        }
    }

    public static void get_sub_category_list_Basic(final Context mContext, String category_idnew, String token, viewAllBasic viewall) {

        try {

            final AsyncHttpClient client = new AsyncHttpClient();
            client.setSSLSocketFactory(MySSLSocketFactory.getFixedSocketFactory());
            final RequestParams request_param = new RequestParams();
            request_param.put("category_id", category_idnew);


            Log.d(TAG, "@@@get_sub_category_list :" + GET_SUBCAT_ID + "?" + request_param.toString());
            ((Activity) mContext).runOnUiThread(new Runnable() {
                public void run() {
                    viewAllBasic.progressBar.setVisibility(View.VISIBLE);

                    Constant.viewAllList.clear();
                    Constant.viewAllListAnnni.clear();
                    client.addHeader("token", token);
                    client.post(GET_SUBCAT_ID, request_param, new JsonHttpResponseHandler() {
                        @Override
                        public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                            Log.d(TAG, "@@@get_sub_category_list_response :" + response.toString());

                            try {
                                int status = response.getInt("error_code");
                                if (status == 200) {

                                    JSONArray arr = response.getJSONArray("data");


                                    for (int i = 0; i < arr.length(); i++) {
                                        JSONObject obj2 = arr.getJSONObject(i);
                                        String category_name = obj2.getString("category_name");
                                        String sub_cat_name = obj2.getString("sub_cat_name");


                                        String category_id = obj2.getString("category_id");
                                        String image = obj2.getString("image");
                                        String sub_cat_id = obj2.getString("sub_cat_id");
                                        int is_active = obj2.getInt("is_active");


                                        if (category_id.equals(category_idnew)) {
                                            //   Toast.makeText(mContext, "SUccess", Toast.LENGTH_SHORT).show();
                                            Log.d("@@@sub_cat_name", sub_cat_name);

                                            Constant.viewAllList.add(new trendingSubModel(image, category_id, sub_cat_id, sub_cat_name, "", is_active));
                                            viewall.setAdapter();

                                        }


                                    }

                                    viewAllBasic.progressBar.setVisibility(View.INVISIBLE);


                                } else {
                                    String message = response.getString("message");
                                    // Toast.makeText(mContext, message, Toast.LENGTH_SHORT).show();
                                }
                            } catch (JSONException e) {

                                e.printStackTrace();

                                Log.d("@@@ notSuccess: ", e.getMessage());
                            }
                            super.onSuccess(statusCode, headers, response);
                        }

                        @Override
                        public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {


                            super.onFailure(statusCode, headers, responseString, throwable);
                        }

                        @Override
                        public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {


                            super.onFailure(statusCode, headers, throwable, errorResponse);
                        }

                        @Override
                        public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONArray errorResponse) {


                            super.onFailure(statusCode, headers, throwable, errorResponse);
                        }
                    });
                }
            });
        } catch (Exception e) {


            e.printStackTrace();
        }
    }


    public static void create_content(final Context mContext, String token, String category_id, String sub_cat_id, String template_id, File upload_template) {
        try {

            final AsyncHttpClient client = new AsyncHttpClient();
            client.setSSLSocketFactory(MySSLSocketFactory.getFixedSocketFactory());
            final RequestParams request_param = new RequestParams();

            try {
                if (!category_id.equals("")) {
                    request_param.put("category_id", category_id);
                    request_param.put("sub_cat_id", sub_cat_id);
                    request_param.put("template_id", template_id);
                } else {

                    request_param.put("category_id", "2");
                    request_param.put("sub_cat_id", "15");
                    request_param.put("template_id", "9");
                }
            } catch (Exception ex) {
                request_param.put("category_id", "2");
                request_param.put("sub_cat_id", "15");
                request_param.put("template_id", "9");
            }

            Log.d("dataNew", category_id + " : " + sub_cat_id + " : " + template_id);


            if (upload_template != null) {
                request_param.put("upload_template", upload_template);
            }


            Log.d(TAG, "@@@CREATE_CONTENT :" + CREATE_CONTENT + "?" + request_param.toString());
            ((Activity) mContext).runOnUiThread(new Runnable() {
                public void run() {

                    try {
                        downloadImageActivity.progressBar.setVisibility(View.VISIBLE);
                    } catch (Exception ignored) {
                    }

                    try {
                        com.Appzia.addpanda.Screens.trendingActivity.shimmerFrameLayout.startShimmer();

                    } catch (Exception ignored) {
                    }
                    client.addHeader("token", token);
                    client.post(CREATE_CONTENT, request_param, new JsonHttpResponseHandler() {
                        @Override
                        public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                            Log.d(TAG, "@@@CREATE_CONTENT_response :" + response.toString());

                            try {
                                int status = response.getInt("error_code");
                                if (status == 200) {

                                    Toast.makeText(mContext, "Image added to download list !", Toast.LENGTH_SHORT).show();
                                    Log.d("#CreateContent", response.toString());

                                    try {
                                        downloadImageActivity.progressBar.setVisibility(View.INVISIBLE);
                                    } catch (Exception ignored) {
                                    }

                                    try {
                                        com.Appzia.addpanda.Screens.trendingActivity.shimmerFrameLayout.stopShimmer();
                                        com.Appzia.addpanda.Screens.trendingActivity.shimmerFrameLayout.setVisibility(View.GONE);
                                        com.Appzia.addpanda.Screens.trendingActivity.CoLayout.setVisibility(View.VISIBLE);
                                    } catch (Exception ignored) {
                                    }

                                } else {
                                    String message = response.getString("message");
                                    Toast.makeText(mContext, message, Toast.LENGTH_SHORT).show();
                                }
                            } catch (JSONException e) {

                                e.printStackTrace();

                                Log.d("@@@ notSuccess: ", e.getMessage());
                            }
                            super.onSuccess(statusCode, headers, response);
                        }

                        @Override
                        public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {


                            super.onFailure(statusCode, headers, responseString, throwable);
                        }

                        @Override
                        public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {


                            super.onFailure(statusCode, headers, throwable, errorResponse);
                        }

                        @Override
                        public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONArray errorResponse) {


                            super.onFailure(statusCode, headers, throwable, errorResponse);
                        }
                    });
                }
            });
        } catch (Exception e) {


            e.printStackTrace();
        }
    }

    public static void get_my_content_creator_list(final Context mContext, String token) {
        try {

            final AsyncHttpClient client = new AsyncHttpClient();
            client.setSSLSocketFactory(MySSLSocketFactory.getFixedSocketFactory());
            final RequestParams request_param = new RequestParams();


            Log.d(TAG, "@@@CREATE_CONTENT :" + GET_MY_CONTENT_CREATOR_LIST + "?" + request_param.toString());
            ((Activity) mContext).runOnUiThread(new Runnable() {
                public void run() {


                    DownloadsFragment.shimmerFrameLayout.startShimmer();

                    client.addHeader("token", token);
                    Log.d(TAG, "Token :" + token);
                    client.post(GET_MY_CONTENT_CREATOR_LIST, request_param, new JsonHttpResponseHandler() {
                        @Override
                        public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                            Log.d(TAG, "@@@CREATE_CONTENT_response :" + response.toString());

                            try {
                                int status = response.getInt("error_code");


                                if (status == 404) {
                                    Toast.makeText(mContext, "Content data not found", Toast.LENGTH_SHORT).show();
                                } else if (status == 200) {


                                    JSONArray arr = response.getJSONArray("data");

                                    Constant.downloadList.clear();
                                    for (int i = 0; i < arr.length(); i++) {
                                        JSONObject obj2 = arr.getJSONObject(i);
                                        Log.d(TAG, "Token: " + String.valueOf(arr.length()));

                                        String created_file = obj2.getString("created_file");

                                        Constant.downloadList.add(new downloadModel(created_file, "1"));

                                    }
                                    DownloadsFragment.shimmerFrameLayout.stopShimmer();
                                    DownloadsFragment.CoLayout.setVisibility(View.VISIBLE);
                                    DownloadsFragment.shimmerFrameLayout.setVisibility(View.INVISIBLE);

                                    DownloadsFragment.setAdapter();


                                } else {
                                    String message = response.getString("message");
                                    // Toast.makeText(mContext, message, Toast.LENGTH_SHORT).show();
                                }
                            } catch (JSONException e) {

                                e.printStackTrace();

                                Log.d("@@@ notSuccess: ", e.getMessage());
                            }
                            super.onSuccess(statusCode, headers, response);
                        }

                        @Override
                        public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {


                            super.onFailure(statusCode, headers, responseString, throwable);
                        }

                        @Override
                        public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {


                            super.onFailure(statusCode, headers, throwable, errorResponse);
                        }

                        @Override
                        public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONArray errorResponse) {


                            super.onFailure(statusCode, headers, throwable, errorResponse);
                        }
                    });
                }
            });
        } catch (Exception e) {


            e.printStackTrace();
        }
    }

    public static void get_my_notifications(final Context mContext, String token) {
        try {

            final AsyncHttpClient client = new AsyncHttpClient();
            client.setSSLSocketFactory(MySSLSocketFactory.getFixedSocketFactory());
            final RequestParams request_param = new RequestParams();


            Log.d(TAG, "@@@CREATE_CONTENT :" + GET_MY_NOTIFICATION + "?" + request_param.toString());
            ((Activity) mContext).runOnUiThread(new Runnable() {
                public void run() {
                    Constant.notiList.clear();
                    try {
                        NotificationActivity.shimmerFrameLayout.startShimmer();
                    } catch (Exception ignored) {
                    }

                    client.addHeader("token", token);
                    client.post(GET_MY_NOTIFICATION, request_param, new JsonHttpResponseHandler() {
                        @Override
                        public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                            Log.d(TAG, "@@@CREATE_CONTENT_response :" + response.toString());

                            try {
                                int status = response.getInt("error_code");

                                if (status == 404) {
                                    String message = response.getString("message");
                                    Toast.makeText(mContext, "Notification not found.", Toast.LENGTH_SHORT).show();
                                } else if (status == 200) {


                                    JSONArray arr = response.getJSONArray("data");

                                    //  Toast.makeText(mContext, "success", Toast.LENGTH_SHORT).show();

                                    for (int i = 0; i < arr.length(); i++) {
                                        JSONObject obj2 = arr.getJSONObject(i);
                                        String notification_id = obj2.getString("notification_id");
                                        String subject = obj2.getString("subject");
                                        String description = obj2.getString("description");
                                        String file = obj2.getString("file");
                                        String date = obj2.getString("date");
                                        String time = obj2.getString("time");

                                        Constant.notiList.add(new notiModel(description, time, file));

                                        try {
                                            NotificationActivity.shimmerFrameLayout.stopShimmer();
                                            NotificationActivity.shimmerFrameLayout.setVisibility(View.GONE);
                                            NotificationActivity.notiRecyclerview.setVisibility(View.VISIBLE);
                                        } catch (Exception ignored) {
                                        }


                                    }

                                    NotificationActivity.setAdapter();


                                } else {
                                    String message = response.getString("message");
                                    // Toast.makeText(mContext, message, Toast.LENGTH_SHORT).show();
                                }
                            } catch (JSONException e) {

                                e.printStackTrace();

                                Log.d("@@@ notSuccess: ", e.getMessage());
                            }
                            super.onSuccess(statusCode, headers, response);
                        }

                        @Override
                        public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {


                            super.onFailure(statusCode, headers, responseString, throwable);
                        }

                        @Override
                        public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {


                            super.onFailure(statusCode, headers, throwable, errorResponse);
                        }

                        @Override
                        public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONArray errorResponse) {


                            super.onFailure(statusCode, headers, throwable, errorResponse);
                        }
                    });
                }
            });
        } catch (Exception e) {


            e.printStackTrace();
        }
    }


    public static void add_partner_with_us_data(final Context mContext, String token, String name, String mobile, String email, String experience, File portfolio, String partner, String specialization) {
        try {

            final AsyncHttpClient client = new AsyncHttpClient();
            client.setSSLSocketFactory(MySSLSocketFactory.getFixedSocketFactory());
            final RequestParams request_param = new RequestParams();

            request_param.put("name", name);
            request_param.put("mobile_no", mobile);
            request_param.put("email_id", email);
            request_param.put("total_experiense", experience);

            if (portfolio != null) {
                request_param.put("uploaded_portfolio_link", portfolio);
            }
            request_param.put("partner_is", partner);
            request_param.put("specialization", specialization);


            Log.d(TAG, "@@@ADD_PARTNER_WITH_US :" + ADD_PARTNER_WITH_US + "?" + request_param.toString());
            ((Activity) mContext).runOnUiThread(new Runnable() {
                public void run() {
                    Constant.notiList.clear();
                    try {
                        //   NotificationFragment.progressBar.setVisibility(View.VISIBLE);
                    } catch (Exception ignored) {
                    }

                    client.addHeader("token", token);
                    client.post(ADD_PARTNER_WITH_US, request_param, new JsonHttpResponseHandler() {
                        @Override
                        public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                            Log.d(TAG, "@@@ADD_PARTNER_WITH_US_response :" + response.toString());

                            try {
                                int status = response.getInt("error_code");

                                if (status == 200) {


                                    if (partner.equals("1")) {
                                        assert portfolio != null;
                                        portfolio.delete();
                                        contentcreatorPersonalJoinScreen.totalexp.setText("");
                                        mContext.startActivity(new Intent(mContext, MainActivity.class));
                                    } else if (partner.equals("2")) {
                                        assert portfolio != null;
                                        portfolio.delete();
                                        contentcreatorBusinessJoinScreen.totalexp.setText("");
                                        mContext.startActivity(new Intent(mContext, MainActivity.class));
                                    }
                                    Toast.makeText(mContext, "Upload successfully !", Toast.LENGTH_SHORT).show();

                                } else {
                                    String message = response.getString("message");
                                    // Toast.makeText(mContext, message, Toast.LENGTH_SHORT).show();
                                }


                                if (status == 201) {
                                    Toast.makeText(mContext, "Already You connect with us", Toast.LENGTH_SHORT).show();
                                }
                            } catch (JSONException e) {

                                e.printStackTrace();

                                Log.d("@@@ notSuccess: ", e.getMessage());
                            }
                            super.onSuccess(statusCode, headers, response);
                        }

                        @Override
                        public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {


                            super.onFailure(statusCode, headers, responseString, throwable);
                        }

                        @Override
                        public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {


                            super.onFailure(statusCode, headers, throwable, errorResponse);
                        }

                        @Override
                        public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONArray errorResponse) {


                            super.onFailure(statusCode, headers, throwable, errorResponse);
                        }
                    });
                }
            });
        } catch (Exception e) {

//


            e.printStackTrace();
        }
    }

    public static void add_partner_with_us_data_IS(final Context mContext, String token, String name, String mobile, String email, String experience, InputStream portfolio, String partner, String specialization) {
        try {

            final AsyncHttpClient client = new AsyncHttpClient();
            client.setSSLSocketFactory(MySSLSocketFactory.getFixedSocketFactory());
            final RequestParams request_param = new RequestParams();

            request_param.put("name", name);
            request_param.put("mobile_no", mobile);
            request_param.put("email_id", email);
            request_param.put("total_experiense", experience);

            if (portfolio != null) {
                request_param.put("uploaded_portfolio_link", portfolio);
            }
            request_param.put("partner_is", partner);
            request_param.put("specialization", specialization);


            Log.d(TAG, "@@@ADD_PARTNER_WITH_US :" + ADD_PARTNER_WITH_US + "?" + request_param.toString());
            ((Activity) mContext).runOnUiThread(new Runnable() {
                public void run() {
                    Constant.notiList.clear();
                    try {
                        //   NotificationFragment.progressBar.setVisibility(View.VISIBLE);
                    } catch (Exception ignored) {
                    }

                    client.addHeader("token", token);
                    client.post(ADD_PARTNER_WITH_US, request_param, new JsonHttpResponseHandler() {
                        @Override
                        public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                            Log.d(TAG, "@@@ADD_PARTNER_WITH_US_response :" + response.toString());

                            try {
                                int status = response.getInt("error_code");

                                if (status == 200) {
                                    Toast.makeText(mContext, "Upload successfully !", Toast.LENGTH_SHORT).show();

                                    //  JSONArray arr = response.getJSONArray("data");


//                                    for (int i = 0; i < arr.length(); i++) {
//                                        JSONObject obj2 = arr.getJSONObject(i);
//                                        String notification_id = obj2.getString("notification_id");
//                                        String subject = obj2.getString("subject");
//                                        String description = obj2.getString("description");
//                                        String file = obj2.getString("file");
//                                        String date = obj2.getString("date");
//                                        String time = obj2.getString("time");
//
//                                        Constant.notiList.add(new notiModel(description,time,file));
//
//                                        try {
//                                            NotificationFragment.progressBar.setVisibility(View.INVISIBLE);
//                                        }catch (Exception ignored){}
//
//
//                                    }

                                    //  NotificationFragment.setAdapter();


                                } else {
                                    String message = response.getString("message");
                                    // Toast.makeText(mContext, message, Toast.LENGTH_SHORT).show();
                                }


                                if (status == 201) {
                                    Toast.makeText(mContext, "Already You connect with us", Toast.LENGTH_SHORT).show();
                                }
                            } catch (JSONException e) {

                                e.printStackTrace();
                                //
                                Log.d("@@@ notSuccess: ", e.getMessage());
                            }
                            super.onSuccess(statusCode, headers, response);
                        }

                        @Override
                        public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {

                            //
                            Log.d(TAG, "onFailure: " + responseString);
                            super.onFailure(statusCode, headers, responseString, throwable);
                        }

                        @Override
                        public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {

                            //

                            Log.d(TAG, "onFailure: " + errorResponse);
                            super.onFailure(statusCode, headers, throwable, errorResponse);
                        }

                        @Override
                        public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONArray errorResponse) {


                            Log.d(TAG, "onFailure: " + errorResponse.toString());


                            super.onFailure(statusCode, headers, throwable, errorResponse);
                        }
                    });
                }
            });
        } catch (Exception e) {

//


            e.printStackTrace();
        }
    }


    public static void fetchDataProfileforpartnerwithus(final Context mContext, String token, String partner_is) {

        try {

            final AsyncHttpClient client = new AsyncHttpClient();
            client.setSSLSocketFactory(MySSLSocketFactory.getFixedSocketFactory());
            final RequestParams request_param = new RequestParams();


            Log.d(TAG, "@@@fetch_user_profile :" + FETCH_USER_PROFILE + "?" + request_param.toString());
            ((Activity) mContext).runOnUiThread(new Runnable() {
                public void run() {


                    client.addHeader("token", token);
                    client.post(FETCH_USER_PROFILE, request_param, new JsonHttpResponseHandler() {
                        @Override
                        public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                            Log.d(TAG, "@@@fetch_user_profile_response :" + response.toString());

                            try {
                                int status = response.getInt("error_code");
                                if (status == 200) {
                                    String message = response.getString("message");
                                    //    Toast.makeText(mContext, message, Toast.LENGTH_SHORT).show();

                                    //main code type here

                                    JSONObject obj = response.getJSONObject("data");
                                    String account_type = obj.getString("account_type");

                                    //for business
                                    String name = obj.getString("name");
                                    String profile_pic = obj.getString("profile_pic");


                                    Log.d("profile_pic_new", "https://www.adpanda.in/api/" + profile_pic);

                                    String email_id = obj.getString("email_id");
                                    String mobile_number = obj.getString("mobile_number");
                                    String dob = obj.getString("dob");
                                    String political_interest = obj.getString("political_interest");

                                    //tbis is for business account details
                                    String bussiness_name = obj.getString("bussiness_name");
                                    String website = obj.getString("website");
                                    String business_category = obj.getString("business_category");
                                    String date_of_incorporation = obj.getString("date_of_incorporation");
                                    String business_details = obj.getString("business_details");


                                    if (partner_is.equals("1")) {
                                        contentcreatorPersonalJoinScreen.name2.setText(name);
                                        contentcreatorPersonalJoinScreen.mobie2.setText(mobile_number);
                                        contentcreatorPersonalJoinScreen.email2.setText(email_id);

                                    }

                                    if (partner_is.equals("2")) {

                                        contentcreatorBusinessJoinScreen.name2.setText(name);
                                        contentcreatorBusinessJoinScreen.mobie2.setText(mobile_number);
                                        contentcreatorBusinessJoinScreen.email2.setText(email_id);
                                    }


                                } else {
                                    String message = response.getString("message");
                                    // Toast.makeText(mContext, message, Toast.LENGTH_SHORT).show();
                                }
                            } catch (JSONException e) {

                                e.printStackTrace();

                                Log.d("@@@ notSuccess: ", e.getMessage());
                            }
                            super.onSuccess(statusCode, headers, response);
                        }

                        @Override
                        public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {


                            super.onFailure(statusCode, headers, responseString, throwable);
                        }

                        @Override
                        public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {


                            super.onFailure(statusCode, headers, throwable, errorResponse);
                        }

                        @Override
                        public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONArray errorResponse) {


                            super.onFailure(statusCode, headers, throwable, errorResponse);
                        }
                    });
                }
            });
        } catch (Exception e) {


            e.printStackTrace();
        }
    }

    public static void get_about_us(final Context mContext, String token) {

        try {

            final AsyncHttpClient client = new AsyncHttpClient();
            client.setSSLSocketFactory(MySSLSocketFactory.getFixedSocketFactory());
            final RequestParams request_param = new RequestParams();


            Log.d(TAG, "@@@GET_ABOUT_US :" + GET_ABOUT_US + "?" + request_param.toString());
            ((Activity) mContext).runOnUiThread(new Runnable() {
                public void run() {


                    client.addHeader("token", token);
                    client.post(GET_ABOUT_US, request_param, new JsonHttpResponseHandler() {
                        @Override
                        public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                            Log.d(TAG, "@@@GET_ABOUT_US_response :" + response.toString());

                            try {
                                int status = response.getInt("error_code");
                                if (status == 200) {

                                    JSONArray array = response.getJSONArray("data");


                                    for (int i = 0; i < array.length(); i++) {
                                        JSONObject obj = array.getJSONObject(i);
                                        String content = obj.getString("content");
                                        aboutUsScreen.aboutUs.setText(content);
                                        Log.d("@@content", content);

                                    }


                                } else {
                                    String message = response.getString("message");
                                    // Toast.makeText(mContext, message, Toast.LENGTH_SHORT).show();
                                }
                            } catch (JSONException e) {

                                e.printStackTrace();

                                Log.d("@@@ notSuccess: ", e.getMessage());
                            }
                            super.onSuccess(statusCode, headers, response);
                        }

                        @Override
                        public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {


                            super.onFailure(statusCode, headers, responseString, throwable);
                        }

                        @Override
                        public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {


                            super.onFailure(statusCode, headers, throwable, errorResponse);
                        }

                        @Override
                        public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONArray errorResponse) {


                            super.onFailure(statusCode, headers, throwable, errorResponse);
                        }
                    });
                }
            });
        } catch (Exception e) {


            e.printStackTrace();
        }
    }

    public static void get_help_and_support(final Context mContext, String token) {

        try {

            final AsyncHttpClient client = new AsyncHttpClient();
            client.setSSLSocketFactory(MySSLSocketFactory.getFixedSocketFactory());
            final RequestParams request_param = new RequestParams();


            Log.d(TAG, "@@@GET_HELP_AND_SUPPORT :" + GET_HELP_AND_SUPPORT + "?" + request_param.toString());
            ((Activity) mContext).runOnUiThread(new Runnable() {
                public void run() {


                    client.addHeader("token", token);
                    client.post(GET_HELP_AND_SUPPORT, request_param, new JsonHttpResponseHandler() {
                        @Override
                        public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                            Log.d(TAG, "@@@GET_HELP_AND_SUPPORT_response :" + response.toString());

                            try {
                                int status = response.getInt("error_code");
                                if (status == 200) {

                                    JSONArray array = response.getJSONArray("data");


                                    for (int i = 0; i < array.length(); i++) {
                                        JSONObject obj = array.getJSONObject(i);
                                        String content = obj.getString("content");
                                        helpAndSupportScreen.hs.setText(content);
                                        Log.d("@@content", content);

                                    }


                                } else {
                                    String message = response.getString("message");
                                    // Toast.makeText(mContext, message, Toast.LENGTH_SHORT).show();
                                }
                            } catch (JSONException e) {

                                e.printStackTrace();

                                Log.d("@@@ notSuccess: ", e.getMessage());
                            }
                            super.onSuccess(statusCode, headers, response);
                        }

                        @Override
                        public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {


                            super.onFailure(statusCode, headers, responseString, throwable);
                        }

                        @Override
                        public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {


                            super.onFailure(statusCode, headers, throwable, errorResponse);
                        }

                        @Override
                        public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONArray errorResponse) {


                            super.onFailure(statusCode, headers, throwable, errorResponse);
                        }
                    });
                }
            });
        } catch (Exception e) {


            e.printStackTrace();
        }
    }

    public static void get_privacy_policy(final Context mContext, String token) {

        try {

            final AsyncHttpClient client = new AsyncHttpClient();
            client.setSSLSocketFactory(MySSLSocketFactory.getFixedSocketFactory());
            final RequestParams request_param = new RequestParams();


            Log.d(TAG, "@@@GET_PRIVACY_POLICY :" + GET_PRIVACY_POLICY + "?" + request_param.toString());
            ((Activity) mContext).runOnUiThread(new Runnable() {
                public void run() {


                    client.addHeader("token", token);
                    client.post(GET_PRIVACY_POLICY, request_param, new JsonHttpResponseHandler() {
                        @Override
                        public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                            Log.d(TAG, "@@@GET_PRIVACY_POLICY_response :" + response.toString());

                            try {
                                int status = response.getInt("error_code");
                                if (status == 200) {

                                    JSONArray array = response.getJSONArray("data");


                                    for (int i = 0; i < array.length(); i++) {
                                        JSONObject obj = array.getJSONObject(i);
                                        String content = obj.getString("content");
                                        privacyPolicyScreen.privacypolicy.setText(content);
                                        Log.d("@@content", content);

                                    }


                                } else {
                                    String message = response.getString("message");
                                    // Toast.makeText(mContext, message, Toast.LENGTH_SHORT).show();
                                }
                            } catch (JSONException e) {

                                e.printStackTrace();

                                Log.d("@@@ notSuccess: ", e.getMessage());
                            }
                            super.onSuccess(statusCode, headers, response);
                        }

                        @Override
                        public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {


                            super.onFailure(statusCode, headers, responseString, throwable);
                        }

                        @Override
                        public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {


                            super.onFailure(statusCode, headers, throwable, errorResponse);
                        }

                        @Override
                        public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONArray errorResponse) {


                            super.onFailure(statusCode, headers, throwable, errorResponse);
                        }
                    });
                }
            });
        } catch (Exception e) {


            e.printStackTrace();
        }
    }

    public static void get_term_and_condition(final Context mContext, String token) {

        try {

            final AsyncHttpClient client = new AsyncHttpClient();
            client.setSSLSocketFactory(MySSLSocketFactory.getFixedSocketFactory());
            final RequestParams request_param = new RequestParams();


            Log.d(TAG, "@@@GET_TERM_AND_CONDITION :" + GET_TERM_AND_CONDITION + "?" + request_param.toString());
            ((Activity) mContext).runOnUiThread(new Runnable() {
                public void run() {


                    client.addHeader("token", token);
                    client.post(GET_TERM_AND_CONDITION, request_param, new JsonHttpResponseHandler() {
                        @Override
                        public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                            Log.d(TAG, "@@@GET_PRIVACY_POLICY_response :" + response.toString());

                            try {
                                int status = response.getInt("error_code");
                                if (status == 200) {

                                    JSONArray array = response.getJSONArray("data");


                                    for (int i = 0; i < array.length(); i++) {
                                        JSONObject obj = array.getJSONObject(i);
                                        String content = obj.getString("content");
                                        termAndConditionScreen.tc.setText(content);
                                        Log.d("@@content", content);

                                    }


                                } else {
                                    String message = response.getString("message");
                                    // Toast.makeText(mContext, message, Toast.LENGTH_SHORT).show();
                                }
                            } catch (JSONException e) {

                                e.printStackTrace();

                                Log.d("@@@ notSuccess: ", e.getMessage());
                            }
                            super.onSuccess(statusCode, headers, response);
                        }

                        @Override
                        public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {


                            super.onFailure(statusCode, headers, responseString, throwable);
                        }

                        @Override
                        public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {


                            super.onFailure(statusCode, headers, throwable, errorResponse);
                        }

                        @Override
                        public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONArray errorResponse) {


                            super.onFailure(statusCode, headers, throwable, errorResponse);
                        }
                    });
                }
            });
        } catch (Exception e) {


            e.printStackTrace();
        }
    }

    public static void get_banner(final Context mContext, String token, mainFragment fragment) {

        try {

            Constant.viewpager.clear();
            final AsyncHttpClient client = new AsyncHttpClient();
            client.setSSLSocketFactory(MySSLSocketFactory.getFixedSocketFactory());
            final RequestParams request_param = new RequestParams();


            Log.d(TAG, "@@@GET_BANNER :" + GET_BANNER + "?" + request_param.toString());
            ((Activity) mContext).runOnUiThread(new Runnable() {
                public void run() {


                    client.addHeader("token", token);
                    client.post(GET_BANNER, request_param, new JsonHttpResponseHandler() {
                        @Override
                        public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                            Log.d(TAG, "@@@GET_BANNER_response :" + response.toString());

                            try {
                                int status = response.getInt("error_code");
                                if (status == 200) {

                                    JSONArray array = response.getJSONArray("data");


                                    for (int i = 0; i < array.length(); i++) {
                                        JSONObject obj = array.getJSONObject(i);
                                        String image = obj.getString("image");
                                        Log.d("@@imageDataa", image);

                                        Constant.viewpager.add(new viewPagerModel(image));

                                    }
                                    fragment.getBannerAdapter();


                                } else {
                                    String message = response.getString("message");
                                    // Toast.makeText(mContext, message, Toast.LENGTH_SHORT).show();
                                }
                            } catch (JSONException e) {

                                e.printStackTrace();

                                Log.d("@@@ notSuccess: ", e.getMessage());
                            }
                            super.onSuccess(statusCode, headers, response);
                        }

                        @Override
                        public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {


                            super.onFailure(statusCode, headers, responseString, throwable);
                        }

                        @Override
                        public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {


                            super.onFailure(statusCode, headers, throwable, errorResponse);
                        }

                        @Override
                        public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONArray errorResponse) {


                            super.onFailure(statusCode, headers, throwable, errorResponse);
                        }
                    });
                }
            });
        } catch (Exception e) {


            e.printStackTrace();
        }
    }


    public static void get_faq(final Context mContext, String token, faqsScreen fragment) {

        try {
            Constant.faqList.clear();

            final AsyncHttpClient client = new AsyncHttpClient();
            client.setSSLSocketFactory(MySSLSocketFactory.getFixedSocketFactory());
            final RequestParams request_param = new RequestParams();

            faqsScreen.shimmerFrameLayout.startShimmer();


            Log.d(TAG, "@@@GET_FAQ :" + GET_FAQ + "?" + request_param.toString());
            ((Activity) mContext).runOnUiThread(new Runnable() {
                public void run() {


                    client.addHeader("token", token);
                    client.post(GET_FAQ, request_param, new JsonHttpResponseHandler() {
                        @Override
                        public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                            Log.d(TAG, "@@@GET_FAQ_response :" + response.toString());

                            try {
                                int status = response.getInt("error_code");
                                if (status == 200) {

                                    JSONArray array = response.getJSONArray("data");


                                    for (int i = 0; i < array.length(); i++) {
                                        JSONObject obj = array.getJSONObject(i);
                                        String question = obj.getString("question");
                                        String answer = obj.getString("answer");
                                        Constant.faqList.add(new faqModel(question, answer));

                                    }
                                    faqsScreen.shimmerFrameLayout.stopShimmer();
                                    faqsScreen.shimmerFrameLayout.setVisibility(View.GONE);
                                    faqsScreen.faqRecyclerview.setVisibility(View.VISIBLE);

                                    fragment.setAdapter();


                                } else {
                                    String message = response.getString("message");
                                    // Toast.makeText(mContext, message, Toast.LENGTH_SHORT).show();
                                }
                            } catch (JSONException e) {

                                e.printStackTrace();

                                Log.d("@@@ notSuccess: ", e.getMessage());
                            }
                            super.onSuccess(statusCode, headers, response);
                        }

                        @Override
                        public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {


                            super.onFailure(statusCode, headers, responseString, throwable);
                        }

                        @Override
                        public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {


                            super.onFailure(statusCode, headers, throwable, errorResponse);
                        }

                        @Override
                        public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONArray errorResponse) {


                            super.onFailure(statusCode, headers, throwable, errorResponse);
                        }
                    });
                }
            });
        } catch (Exception e) {


            e.printStackTrace();
        }
    }


    public static void get_visiting_card_list(final Context mContext, String token, createVisingCardHorizontalScreens fragment) {

        Constant.horiVisitingList.clear();
        try {
            final AsyncHttpClient client = new AsyncHttpClient();
            client.setSSLSocketFactory(MySSLSocketFactory.getFixedSocketFactory());
            final RequestParams request_param = new RequestParams();


            Log.d(TAG, "@@@get_visiting_card_list :" + GET_VISITING_CARD_LIST + "?" + request_param.toString());
            ((Activity) mContext).runOnUiThread(new Runnable() {
                public void run() {

                    createVisingCardHorizontalScreens.progressBar.setVisibility(View.VISIBLE);
                    client.addHeader("token", token);
                    client.post(GET_VISITING_CARD_LIST, request_param, new JsonHttpResponseHandler() {
                        @Override
                        public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                            Log.d(TAG, "@@@get_visiting_card_list_response :" + response.toString());

                            try {
                                int status = response.getInt("error_code");
                                if (status == 200) {

                                    JSONArray array = response.getJSONArray("data");


                                    for (int i = 0; i < array.length(); i++) {
                                        JSONObject obj = array.getJSONObject(i);
                                        String visiting_card_template_id = obj.getString("visiting_card_template_id");
                                        String category_type_flag = obj.getString("category_type_flag");
                                        String category_type = obj.getString("category_type");
                                        String image = obj.getString("image");
                                        String position = obj.getString("position");

                                        if (position.equals("2")) {
                                            //2 for horizontal and 1 for vertical

                                            Constant.horiVisitingList.add(new horiVisitingModel(image, visiting_card_template_id));
                                            createVisingCardHorizontalScreens.progressBar.setVisibility(View.INVISIBLE);
                                        }
                                    }

                                    fragment.setAdapter();


                                } else {
                                    String message = response.getString("message");
                                    // Toast.makeText(mContext, message, Toast.LENGTH_SHORT).show();
                                }
                            } catch (JSONException e) {

                                e.printStackTrace();

                                Log.d("@@@ notSuccess: ", e.getMessage());
                            }
                            super.onSuccess(statusCode, headers, response);
                        }

                        @Override
                        public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {


                            super.onFailure(statusCode, headers, responseString, throwable);
                        }

                        @Override
                        public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {


                            super.onFailure(statusCode, headers, throwable, errorResponse);
                        }

                        @Override
                        public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONArray errorResponse) {


                            super.onFailure(statusCode, headers, throwable, errorResponse);
                        }
                    });
                }
            });
        } catch (Exception e) {


            e.printStackTrace();
        }
    }

    public static void get_visiting_card_list_vertical(final Context mContext, String token, createVisingCardVerticalScreens fragment) {

        Constant.horiVisitingList.clear();
        try {
            final AsyncHttpClient client = new AsyncHttpClient();
            client.setSSLSocketFactory(MySSLSocketFactory.getFixedSocketFactory());
            final RequestParams request_param = new RequestParams();


            Log.d(TAG, "@@@get_visiting_card_list :" + GET_VISITING_CARD_LIST + "?" + request_param.toString());
            ((Activity) mContext).runOnUiThread(new Runnable() {
                public void run() {

                    createVisingCardHorizontalScreens.progressBar.setVisibility(View.VISIBLE);
                    client.addHeader("token", token);
                    client.post(GET_VISITING_CARD_LIST, request_param, new JsonHttpResponseHandler() {
                        @Override
                        public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                            Log.d(TAG, "@@@get_visiting_card_list_response :" + response.toString());

                            try {
                                int status = response.getInt("error_code");
                                if (status == 200) {

                                    JSONArray array = response.getJSONArray("data");


                                    for (int i = 0; i < array.length(); i++) {
                                        JSONObject obj = array.getJSONObject(i);
                                        String visiting_card_template_id = obj.getString("visiting_card_template_id");
                                        String category_type_flag = obj.getString("category_type_flag");
                                        String category_type = obj.getString("category_type");
                                        String image = obj.getString("image");
                                        String position = obj.getString("position");

                                        if (position.equals("1")) {
                                            //2 for horizontal and 1 for vertical

                                            Constant.horiVisitingList.add(new horiVisitingModel(image, visiting_card_template_id));
                                            createVisingCardHorizontalScreens.progressBar.setVisibility(View.INVISIBLE);
                                        }
                                    }

                                    fragment.setAdapter();


                                } else {
                                    String message = response.getString("message");
                                    // Toast.makeText(mContext, message, Toast.LENGTH_SHORT).show();
                                }
                            } catch (JSONException e) {

                                e.printStackTrace();

                                Log.d("@@@ notSuccess: ", e.getMessage());
                            }
                            super.onSuccess(statusCode, headers, response);
                        }

                        @Override
                        public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {


                            super.onFailure(statusCode, headers, responseString, throwable);
                        }

                        @Override
                        public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {


                            super.onFailure(statusCode, headers, throwable, errorResponse);
                        }

                        @Override
                        public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONArray errorResponse) {


                            super.onFailure(statusCode, headers, throwable, errorResponse);
                        }
                    });
                }
            });
        } catch (Exception e) {


            e.printStackTrace();
        }
    }


    public static void creating_visiting_card(final Context mContext, String token, String visiting_card_template_id, File image) {

        try {

            final AsyncHttpClient client = new AsyncHttpClient();
            client.setSSLSocketFactory(MySSLSocketFactory.getFixedSocketFactory());
            final RequestParams request_param = new RequestParams();

            request_param.put("visiting_card_template_id", visiting_card_template_id);


            request_param.put("image", image);


            Log.d(TAG, "@@@creating_visiting_card :" + CREATING_VISITING_CARD + "?" + request_param.toString());
            ((Activity) mContext).runOnUiThread(new Runnable() {
                public void run() {


                    client.addHeader("token", token);
                    client.post(CREATING_VISITING_CARD, request_param, new JsonHttpResponseHandler() {
                        @Override
                        public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                            Log.d(TAG, "@@@creating_visiting_card_response :" + response.toString());

                            try {
                                int status = response.getInt("error_code");
                                String msg = response.getString("message");
                                if (status == 200) {

                                    Toast.makeText(mContext, msg, Toast.LENGTH_SHORT).show();

//                                    JSONArray array = response.getJSONArray("data");
//
//
//                                    for (int i = 0; i < array.length(); i++) {
//                                        JSONObject obj = array.getJSONObject(i);
//                                        String content = obj.getString("content");
//                                        aboutusFRagment.aboutUs.setText(content);
//                                        Log.d("@@content", content);
//
//                                    }


                                } else {
                                    String message = response.getString("message");
                                    // Toast.makeText(mContext, message, Toast.LENGTH_SHORT).show();
                                }
                            } catch (JSONException e) {

                                e.printStackTrace();

                                Log.d("@@@ notSuccess: ", e.getMessage());
                            }
                            super.onSuccess(statusCode, headers, response);
                        }

                        @Override
                        public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {


                            super.onFailure(statusCode, headers, responseString, throwable);
                        }

                        @Override
                        public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {


                            super.onFailure(statusCode, headers, throwable, errorResponse);
                        }

                        @Override
                        public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONArray errorResponse) {


                            super.onFailure(statusCode, headers, throwable, errorResponse);
                        }
                    });
                }
            });
        } catch (Exception e) {


            e.printStackTrace();
        }
    }

    public static void get_my_creating_visiting_card_list(final Context mContext, String token) {
        try {

            final AsyncHttpClient client = new AsyncHttpClient();
            client.setSSLSocketFactory(MySSLSocketFactory.getFixedSocketFactory());
            final RequestParams request_param = new RequestParams();


            Log.d(TAG, "@@@get_my_creating_visiting_card_list :" + GET_MY_CREATING_VISITING_CARD_LIST + "?" + request_param.toString());
            ((Activity) mContext).runOnUiThread(new Runnable() {
                public void run() {
                    Constant.visitingCardAdapterList.clear();
                    try {
                        //   DownloadsFragment.shimmerFrameLayout.startShimmer();
                    } catch (Exception ignored) {
                    }

                    client.addHeader("token", token);
                    client.post(GET_MY_CREATING_VISITING_CARD_LIST, request_param, new JsonHttpResponseHandler() {
                        @Override
                        public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                            Log.d(TAG, "@@@get_my_creating_visiting_card_list_response :" + response.toString());

                            try {
                                int status = response.getInt("error_code");

                                if (status == 404) {
                                    Toast.makeText(mContext, "Visiting card data not found", Toast.LENGTH_SHORT).show();
                                } else if (status == 200) {


                                    JSONArray arr = response.getJSONArray("data");


                                    for (int i = 0; i < arr.length(); i++) {
                                        JSONObject obj2 = arr.getJSONObject(i);
                                        String image = obj2.getString("image");

                                        Constant.visitingCardAdapterList.add(new downloadModel(image, "2"));

                                    }
                                    DownloadsFragment.shimmerFrameLayout.stopShimmer();
                                    DownloadsFragment.CoLayout.setVisibility(View.VISIBLE);
                                    DownloadsFragment.shimmerFrameLayout.setVisibility(View.INVISIBLE);

                                    DownloadsFragment.visitingCardAdapter();


                                } else {
                                    String message = response.getString("message");
                                    // Toast.makeText(mContext, message, Toast.LENGTH_SHORT).show();
                                }
                            } catch (JSONException e) {

                                e.printStackTrace();

                                Log.d("@@@ notSuccess: ", e.getMessage());
                            }
                            super.onSuccess(statusCode, headers, response);
                        }

                        @Override
                        public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {


                            super.onFailure(statusCode, headers, responseString, throwable);
                        }

                        @Override
                        public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {


                            super.onFailure(statusCode, headers, throwable, errorResponse);
                        }

                        @Override
                        public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONArray errorResponse) {


                            super.onFailure(statusCode, headers, throwable, errorResponse);
                        }
                    });
                }
            });
        } catch (Exception e) {


            e.printStackTrace();
        }
    }


    public static void get_category_list_display(final Context mContext, String token, categoryTabFragment categoryTabFragment, String type) {
        try {
            final AsyncHttpClient client = new AsyncHttpClient();
            client.setSSLSocketFactory(MySSLSocketFactory.getFixedSocketFactory());
            final RequestParams request_param = new RequestParams();


            Log.d(TAG, "@@@get_category_list_display :" + get_category_list_All + "?" + request_param.toString());
            ((Activity) mContext).runOnUiThread(new Runnable() {
                public void run() {

                    //it needs to clear  a list
                    Constant.categoryModelList.clear();
                    com.Appzia.addpanda.Fragments.categoryTabFragment.shimmerFrameLayout.startShimmer();
                    client.addHeader("token", token);
                    client.post(get_category_list_All, request_param, new JsonHttpResponseHandler() {
                        @Override
                        public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                            Log.d(TAG, "@@@get_category_list_display_response :" + response.toString());

                            try {
                                int status = response.getInt("error_code");
                                if (status == 200) {


                                    JSONArray arr = response.getJSONArray("data");


                                    for (int i = 0; i < arr.length(); i++) {
                                        JSONObject obj2 = arr.getJSONObject(i);
                                        String category_name = obj2.getString("category_name");
                                        String category_id = obj2.getString("category_id");
                                        String category_type_flag = obj2.getString("category_type_flag");

                                        Log.d("##category_name", category_name);
                                        String category_image = obj2.getString("category_image");
                                        Log.d("##category_image", category_image);

                                        Constant.getSfFuncion(mContext);
                                        String accountType = Constant.getSF.getString(Constant.ACC_TYPE, "");


                                        if (accountType.equals("1") && category_type_flag.equals("1") && type.equals("personal")) {

                                            Constant.categoryModelList.add(new categoryModel(category_image, category_name, category_id));
                                        } else if (accountType.equals("2") && category_type_flag.equals("2")) {
                                            Toast.makeText(mContext, "business", Toast.LENGTH_SHORT).show();
                                            Constant.categoryModelList.add(new categoryModel(category_image, category_name, category_id));
                                        }
                                    }

                                    com.Appzia.addpanda.Fragments.categoryTabFragment.shimmerFrameLayout.stopShimmer();
                                    com.Appzia.addpanda.Fragments.categoryTabFragment.CoLayout.setVisibility(View.VISIBLE);
                                    com.Appzia.addpanda.Fragments.categoryTabFragment.shimmerFrameLayout.setVisibility(View.INVISIBLE);
                                    categoryTabFragment.setAdapter();


                                } else {
                                    String message = response.getString("message");

                                }
                            } catch (JSONException e) {

                                e.printStackTrace();

                                Log.d("@@@ notSuccess: ", e.getMessage());
                            }
                            super.onSuccess(statusCode, headers, response);
                        }

                        @Override
                        public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {


                            super.onFailure(statusCode, headers, responseString, throwable);
                        }

                        @Override
                        public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {


                            super.onFailure(statusCode, headers, throwable, errorResponse);
                        }

                        @Override
                        public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONArray errorResponse) {


                            super.onFailure(statusCode, headers, throwable, errorResponse);
                        }
                    });
                }
            });
        } catch (Exception e) {


            e.printStackTrace();
        }
    }


    public static void get_sub_category_TrendingActivity(final Context mContext, String category_idnew, String token, trendingActivity activity) {

        try {

            final AsyncHttpClient client = new AsyncHttpClient();
            client.setSSLSocketFactory(MySSLSocketFactory.getFixedSocketFactory());
            final RequestParams request_param = new RequestParams();
            request_param.put("category_id", category_idnew);


            Log.d(TAG, "@@@get_sub_category_ViewAll :" + GET_SUBCAT_ID + "?" + request_param.toString());
            ((Activity) mContext).runOnUiThread(new Runnable() {
                public void run() {
                    com.Appzia.addpanda.Screens.trendingActivity.shimmerFrameLayout.startShimmer();


                    client.addHeader("token", token);
                    client.post(GET_SUBCAT_ID, request_param, new JsonHttpResponseHandler() {
                        @Override
                        public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                            Log.d(TAG, "@@@get_sub_category_ViewAll_response :" + response.toString());

                            try {
                                int status = response.getInt("error_code");
                                if (status == 200) {

                                    JSONArray arr = response.getJSONArray("data");

                                    Constant.trendingListForExtra.clear();
                                    for (int i = 0; i < arr.length(); i++) {
                                        JSONObject obj2 = arr.getJSONObject(i);
                                        String category_name = obj2.getString("category_name");
                                        String sub_cat_name = obj2.getString("sub_cat_name");
                                        Log.d(TAG, "category_idnew: " + sub_cat_name);
                                        String category_id = obj2.getString("category_id");
                                        String image = obj2.getString("image");
                                        String sub_cat_id = obj2.getString("sub_cat_id");
                                        int is_active = obj2.getInt("is_active");


                                        if (category_id.equals(category_idnew)) {
                                            //   Toast.makeText(mContext, "SUccess", Toast.LENGTH_SHORT).show();
                                            Log.d("@@@category_idnew", category_idnew);

                                            Constant.trendingListForExtra.add(new trendingSubModel(image, category_id, sub_cat_id, sub_cat_name, "", is_active));
                                            trendingActivity.setSubAdapter();

                                        }


                                    }

                                    com.Appzia.addpanda.Screens.trendingActivity.shimmerFrameLayout.stopShimmer();
                                    com.Appzia.addpanda.Screens.trendingActivity.shimmerFrameLayout.setVisibility(View.GONE);
                                    com.Appzia.addpanda.Screens.trendingActivity.CoLayout.setVisibility(View.VISIBLE);


                                } else {
                                    String message = response.getString("message");
                                    // Toast.makeText(mContext, message, Toast.LENGTH_SHORT).show();
                                }
                            } catch (JSONException e) {

                                e.printStackTrace();

                                Log.d("@@@ notSuccess: ", e.getMessage());
                            }
                            super.onSuccess(statusCode, headers, response);
                        }

                        @Override
                        public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {


                            super.onFailure(statusCode, headers, responseString, throwable);
                        }

                        @Override
                        public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {


                            super.onFailure(statusCode, headers, throwable, errorResponse);
                        }

                        @Override
                        public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONArray errorResponse) {


                            super.onFailure(statusCode, headers, throwable, errorResponse);
                        }
                    });
                }
            });
        } catch (Exception e) {


            e.printStackTrace();
        }
    }

    public static void get_frame_list_testing(final Context mContext, String token, EditFrameOwner EditFrameOwner, String category_id, String sub_cat_id, String template_id) {

        try {

            final AsyncHttpClient client = new AsyncHttpClient();
            client.setSSLSocketFactory(MySSLSocketFactory.getFixedSocketFactory());
            final RequestParams request_param = new RequestParams();


            Log.d(TAG, "@@@get_frame_list :" + GET_FRAME_LIST + "?" + request_param.toString());
            ((Activity) mContext).runOnUiThread(new Runnable() {
                public void run() {

                    Constant.frameList.clear();
                    Constant.frameListTwo.clear();
                    client.addHeader("token", token);
                    client.post(GET_FRAME_LIST, request_param, new JsonHttpResponseHandler() {
                        @Override
                        public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                            Log.d(TAG, "@@@get_frame_list_response :" + response.toString());

                            try {
                                int status = response.getInt("error_code");

                                if (status == 200) {


                                    JSONArray arr = response.getJSONArray("data");


                                    for (int i = 0; i < arr.length(); i++) {
                                        JSONObject obj2 = arr.getJSONObject(i);
                                        String category_name = obj2.getString("category_name");


                                        String category_id = obj2.getString("category_id");
                                        String image = obj2.getString("image");

                                        Constant.frameListTwo.add(new frameModel(image));


                                    }

                                    EditFrameOwner.setAdapter();


                                } else {
                                    String message = response.getString("message");
                                    // Toast.makeText(mContext, message, Toast.LENGTH_SHORT).show();
                                }
                            } catch (JSONException e) {

                                e.printStackTrace();

                                Log.d("@@@ notSuccess: ", e.getMessage());
                            }
                            super.onSuccess(statusCode, headers, response);
                        }

                        @Override
                        public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {


                            super.onFailure(statusCode, headers, responseString, throwable);
                        }

                        @Override
                        public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {


                            super.onFailure(statusCode, headers, throwable, errorResponse);
                        }

                        @Override
                        public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONArray errorResponse) {


                            super.onFailure(statusCode, headers, throwable, errorResponse);
                        }
                    });
                }
            });
        } catch (Exception e) {


            e.printStackTrace();
        }
    }

    public static void add_frame_photo(final Context mContext, String token, File imgFile) {

        try {

            final AsyncHttpClient client = new AsyncHttpClient();
            client.setSSLSocketFactory(MySSLSocketFactory.getFixedSocketFactory());
            final RequestParams request_param = new RequestParams();


            if (imgFile != null) {
                request_param.put("file", imgFile);
            }


            Log.d(TAG, "@@@add_frame_photo :" + ADD_FRAME_PHOTO + "?" + request_param.toString());
            ((Activity) mContext).runOnUiThread(new Runnable() {
                public void run() {
                    client.addHeader("token", token);
                    client.post(ADD_FRAME_PHOTO, request_param, new JsonHttpResponseHandler() {
                        @Override
                        public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                            Log.d(TAG, "@@@add_frame_photo_response :" + response.toString());

                            try {
                                int status = response.getInt("error_code");
                                String msg = response.getString("message");
                                if (status == 200) {
                                    Toast.makeText(mContext, msg, Toast.LENGTH_SHORT).show();
                                    imgFile.delete();
                                    mContext.startActivity(new Intent(mContext, MainActivity.class));

                                } else {
                                    String message = response.getString("message");
                                    // Toast.makeText(mContext, message, Toast.LENGTH_SHORT).show();
                                }
                            } catch (JSONException e) {

                                e.printStackTrace();

                                Log.d("@@@ notSuccess: ", e.getMessage());
                            }
                            super.onSuccess(statusCode, headers, response);
                        }

                        @Override
                        public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {


                            super.onFailure(statusCode, headers, responseString, throwable);
                        }

                        @Override
                        public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {


                            super.onFailure(statusCode, headers, throwable, errorResponse);
                        }

                        @Override
                        public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONArray errorResponse) {


                            super.onFailure(statusCode, headers, throwable, errorResponse);
                        }
                    });
                }
            });
        } catch (Exception e) {


            e.printStackTrace();
        }
    }


    public static void fetch_all_frames_photo(Context mContext, String token, trendingActivity trendingActivity) {

        try {

            final AsyncHttpClient client = new AsyncHttpClient();
            client.setSSLSocketFactory(MySSLSocketFactory.getFixedSocketFactory());
            final RequestParams request_param = new RequestParams();

            Log.d(TAG, "@@@fetch_all_frames_photo :" + FETCH_ALL_FRAME_PHOTO + "?" + request_param.toString());
            ((Activity) mContext).runOnUiThread(new Runnable() {
                public void run() {


                    client.addHeader("token", token);
                    client.post(FETCH_ALL_FRAME_PHOTO, request_param, new JsonHttpResponseHandler() {
                        @Override
                        public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                            Log.d(TAG, "@@@fetch_all_frames_photo_response :" + response.toString());

                            try {
                                int status = response.getInt("error_code");
                                String msg = response.getString("message");
                                if (status == 200) {


                                    String message = response.getString("message");
                                    Constant.frameList.clear();
                                    JSONArray arr = response.getJSONArray("data");
                                    for (int i = 0; i < arr.length(); i++) {
                                        JSONObject obj = arr.getJSONObject(i);
                                        String images = obj.getString("images");
                                        Constant.frameList.add(new frameModel(images));
                                    }

                                    trendingActivity.setFrameAdapter();
                                    //   Toast.makeText(mContext, msg, Toast.LENGTH_SHORT).show();
                                } else {
                                    //  Toast.makeText(mContext, msg, Toast.LENGTH_SHORT).show();
                                }
                            } catch (JSONException e) {

                                e.printStackTrace();

                                Log.d("@@@ notSuccess: ", e.getMessage());
                            }
                            super.onSuccess(statusCode, headers, response);
                        }

                        @Override
                        public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {


                            super.onFailure(statusCode, headers, responseString, throwable);
                        }

                        @Override
                        public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {


                            super.onFailure(statusCode, headers, throwable, errorResponse);
                        }

                        @Override
                        public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONArray errorResponse) {


                            super.onFailure(statusCode, headers, throwable, errorResponse);
                        }
                    });
                }
            });
        } catch (Exception e) {


            e.printStackTrace();
        }
    }

    public static void fetch_all_frames_photo_basic(Context mContext, String token, Basic_editFrameActivity trendingActivity) {

        try {
            Constant.frameListTwo.clear();
            final AsyncHttpClient client = new AsyncHttpClient();
            client.setSSLSocketFactory(MySSLSocketFactory.getFixedSocketFactory());
            final RequestParams request_param = new RequestParams();

            Log.d(TAG, "@@@fetch_all_frames_photo :" + FETCH_ALL_FRAME_PHOTO + "?" + request_param.toString());
            ((Activity) mContext).runOnUiThread(new Runnable() {
                public void run() {


                    client.addHeader("token", token);
                    client.post(FETCH_ALL_FRAME_PHOTO, request_param, new JsonHttpResponseHandler() {
                        @Override
                        public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                            Log.d(TAG, "@@@fetch_all_frames_photo_response :" + response.toString());

                            try {
                                int status = response.getInt("error_code");
                                String msg = response.getString("message");
                                if (status == 200) {


                                    String message = response.getString("message");

                                    JSONArray arr = response.getJSONArray("data");
                                    for (int i = 0; i < arr.length(); i++) {
                                        JSONObject obj = arr.getJSONObject(i);
                                        String images = obj.getString("images");
                                        Constant.frameListTwo.add(new frameModel(images));
                                    }

                                    trendingActivity.setFrameAdapter();
                                    //   Toast.makeText(mContext, msg, Toast.LENGTH_SHORT).show();
                                } else {
                                    //  Toast.makeText(mContext, msg, Toast.LENGTH_SHORT).show();
                                }
                            } catch (JSONException e) {

                                e.printStackTrace();

                                Log.d("@@@ notSuccess: ", e.getMessage());
                            }
                            super.onSuccess(statusCode, headers, response);
                        }

                        @Override
                        public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {


                            super.onFailure(statusCode, headers, responseString, throwable);
                        }

                        @Override
                        public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {


                            super.onFailure(statusCode, headers, throwable, errorResponse);
                        }

                        @Override
                        public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONArray errorResponse) {


                            super.onFailure(statusCode, headers, throwable, errorResponse);
                        }
                    });
                }
            });
        } catch (Exception e) {


            e.printStackTrace();
        }
    }

    public static void fetch_all_frames_photo_Scratch(Context mContext, String token, editFameActivity trendingActivity) {

        try {
            Constant.frameListTwo.clear();
            final AsyncHttpClient client = new AsyncHttpClient();
            client.setSSLSocketFactory(MySSLSocketFactory.getFixedSocketFactory());
            final RequestParams request_param = new RequestParams();

            Log.d(TAG, "@@@fetch_all_frames_photo :" + FETCH_ALL_FRAME_PHOTO + "?" + request_param.toString());
            ((Activity) mContext).runOnUiThread(new Runnable() {
                public void run() {
                    client.addHeader("token", token);
                    client.post(FETCH_ALL_FRAME_PHOTO, request_param, new JsonHttpResponseHandler() {
                        @Override
                        public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                            Log.d(TAG, "@@@fetch_all_frames_photo_response :" + response.toString());

                            try {
                                int status = response.getInt("error_code");
                                String msg = response.getString("message");
                                if (status == 200) {


                                    String message = response.getString("message");

                                    JSONArray arr = response.getJSONArray("data");
                                    for (int i = 0; i < arr.length(); i++) {
                                        JSONObject obj = arr.getJSONObject(i);
                                        String images = obj.getString("images");
                                        Constant.frameListTwo.add(new frameModel(images));
                                    }

                                    trendingActivity.setFrameAdapter();
                                    //   Toast.makeText(mContext, msg, Toast.LENGTH_SHORT).show();
                                } else {
                                    Toast.makeText(mContext, msg, Toast.LENGTH_SHORT).show();
                                }
                            } catch (JSONException e) {

                                e.printStackTrace();

                                Log.d("@@@ notSuccess: ", e.getMessage());
                            }
                            super.onSuccess(statusCode, headers, response);
                        }

                        @Override
                        public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {


                            super.onFailure(statusCode, headers, responseString, throwable);
                        }

                        @Override
                        public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {


                            super.onFailure(statusCode, headers, throwable, errorResponse);
                        }

                        @Override
                        public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONArray errorResponse) {


                            super.onFailure(statusCode, headers, throwable, errorResponse);
                        }
                    });
                }
            });
        } catch (Exception e) {

            //Toast.makeText(((Activity) mContext).getApplication(), e.getMessage(), Toast.LENGTH_SHORT).show();


            e.printStackTrace();
        }
    }

    public static void update_fcm_token(Context mContext, String token, String fcm_token) {

        try {

            final AsyncHttpClient client = new AsyncHttpClient();
            client.setSSLSocketFactory(MySSLSocketFactory.getFixedSocketFactory());
            final RequestParams request_param = new RequestParams();

            request_param.put("token", token);
            request_param.put("fcm_token", fcm_token);


            Log.d(TAG, "@@@update_fcm_token :" + UPDATE_FCM_TOKEN + "?" + request_param.toString());
            ((Activity) mContext).runOnUiThread(new Runnable() {
                public void run() {
                    client.addHeader("token", token);
                    client.post(UPDATE_FCM_TOKEN, request_param, new JsonHttpResponseHandler() {
                        @Override
                        public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                            Log.d(TAG, "@@@update_fcm_token_response :" + response.toString());

                            try {
                                int status = response.getInt("error_code");
                                String msg = response.getString("message");
                                if (status == 200) {
                                    //  Toast.makeText(mContext, " fcm uploaded", Toast.LENGTH_SHORT).show();
                                } else {
                                    Toast.makeText(mContext, msg, Toast.LENGTH_SHORT).show();
                                }
                            } catch (JSONException e) {

                                e.printStackTrace();

                                Log.d("@@@ notSuccess: ", e.getMessage());
                            }
                            super.onSuccess(statusCode, headers, response);
                        }

                        @Override
                        public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {


                            super.onFailure(statusCode, headers, responseString, throwable);
                        }

                        @Override
                        public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {


                            super.onFailure(statusCode, headers, throwable, errorResponse);
                        }

                        @Override
                        public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONArray errorResponse) {


                            super.onFailure(statusCode, headers, throwable, errorResponse);
                        }
                    });
                }
            });
        } catch (Exception e) {


            e.printStackTrace();
        }


    }

    public static void save_bank_details(Context mContext, String account_holder_name, String account_number, String ifsc_code, String token) {
        try {

            final AsyncHttpClient client = new AsyncHttpClient();
            client.setSSLSocketFactory(MySSLSocketFactory.getFixedSocketFactory());
            final RequestParams request_param = new RequestParams();

            request_param.put("account_holder_name", account_holder_name);
            request_param.put("account_number", account_number);
            request_param.put("ifsc_code", ifsc_code);

            client.addHeader("token", token);
            Log.d(TAG, "@@@save_bank_details :" + SAVE_BANK_DETAILS + "?" + request_param.toString());
            ((Activity) mContext).runOnUiThread(new Runnable() {
                public void run() {
                    client.addHeader("token", token);
                    client.post(SAVE_BANK_DETAILS, request_param, new JsonHttpResponseHandler() {
                        @Override
                        public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                            Log.d(TAG, "@@@save_bank_details_response :" + response.toString());

                            try {
                                int status = response.getInt("error_code");
                                String msg = response.getString("message");
                                if (status == 200) {
                                    // Toast.makeText(mContext, msg, Toast.LENGTH_SHORT).show();
                                    Constant.setSfFunction(mContext);
                                    Constant.setSF.putString("ACC_DONE", "ACC_DONE");
                                    Constant.setSF.apply();
                                    mContext.startActivity(new Intent(mContext, manageAcount3Activity.class));
                                } else {
                                    Toast.makeText(mContext, msg, Toast.LENGTH_SHORT).show();
                                }
                            } catch (JSONException e) {

                                e.printStackTrace();

                                Log.d("@@@ notSuccess: ", e.getMessage());
                            }
                            super.onSuccess(statusCode, headers, response);
                        }

                        @Override
                        public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {


                            super.onFailure(statusCode, headers, responseString, throwable);
                        }

                        @Override
                        public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {


                            super.onFailure(statusCode, headers, throwable, errorResponse);
                        }

                        @Override
                        public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONArray errorResponse) {


                            super.onFailure(statusCode, headers, throwable, errorResponse);
                        }
                    });
                }
            });
        } catch (Exception e) {


            e.printStackTrace();
        }
    }

    public static void get_bank_details(Context mContext, TextView name, TextView account, TextView ifsc, String token) {
        try {

            final AsyncHttpClient client = new AsyncHttpClient();
            client.setSSLSocketFactory(MySSLSocketFactory.getFixedSocketFactory());
            final RequestParams request_param = new RequestParams();

            client.addHeader("token", token);
            Log.d(TAG, "@@@get_bank_details :" + GET_BANK_DETAILS + "?" + request_param.toString());
            ((Activity) mContext).runOnUiThread(new Runnable() {
                public void run() {
                    client.addHeader("token", token);
                    client.post(GET_BANK_DETAILS, request_param, new JsonHttpResponseHandler() {
                        @Override
                        public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                            Log.d(TAG, "@@@get_bank_details_response :" + response.toString());

                            try {
                                int status = response.getInt("error_code");
                                String msg = response.getString("message");
                                if (status == 200) {

                                    JSONObject obj = response.getJSONObject("data");
                                    String account_holder_name = obj.getString("account_holder_name");
                                    String account_number = obj.getString("account_number");
                                    String ifsc_code = obj.getString("ifsc_code");

                                    name.setText(account_holder_name);
                                    account.setText(account_number);
                                    ifsc.setText(ifsc_code);

                                } else {
                                    Toast.makeText(mContext, msg, Toast.LENGTH_SHORT).show();
                                }
                            } catch (JSONException e) {

                                e.printStackTrace();

                                Log.d("@@@ notSuccess: ", e.getMessage());
                            }
                            super.onSuccess(statusCode, headers, response);
                        }

                        @Override
                        public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {


                            super.onFailure(statusCode, headers, responseString, throwable);
                        }

                        @Override
                        public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {


                            super.onFailure(statusCode, headers, throwable, errorResponse);
                        }

                        @Override
                        public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONArray errorResponse) {


                            super.onFailure(statusCode, headers, throwable, errorResponse);
                        }
                    });
                }
            });
        } catch (Exception e) {


            e.printStackTrace();
        }
    }

    public static void get_identity_verification(Context mContext, AppCompatButton adharBtn, AppCompatButton panBtn, AppCompatButton checkBtn, String token) {

        try {

            final AsyncHttpClient client = new AsyncHttpClient();
            client.setSSLSocketFactory(MySSLSocketFactory.getFixedSocketFactory());
            final RequestParams request_param = new RequestParams();

            client.addHeader("token", token);
            Log.d(TAG, "@@@get_identity_verification :" + GET_IDENTITY_VERIFICATION + "?" + request_param.toString());
            ((Activity) mContext).runOnUiThread(new Runnable() {
                public void run() {
                    client.addHeader("token", token);
                    client.post(GET_IDENTITY_VERIFICATION, request_param, new JsonHttpResponseHandler() {
                        @Override
                        public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                            Log.d(TAG, "@@@get_identity_verification_response :" + response.toString());

                            try {
                                int status = response.getInt("error_code");
                                String msg = response.getString("message");
                                if (status == 200) {

                                    //      Toast.makeText(mContext, msg, Toast.LENGTH_SHORT).show();
                                    JSONObject obj = response.getJSONObject("data");


                                    JSONObject adhaar_data = obj.getJSONObject("adhaar_data");
                                    String adhar_status = adhaar_data.getString("adhar_status");

                                    if (adhar_status.equals("0")) {
                                        //0 = not uploaded
                                        adharBtn.setError(null);
                                        adharBtn.setEnabled(true);
                                    } else if (adhar_status.equals("1")) {
                                        //1= pending

                                        Drawable customErrorDrawable = mContext.getResources().getDrawable(R.drawable.yellowwarning);
                                        customErrorDrawable.setBounds(0, 0, customErrorDrawable.getIntrinsicWidth(), customErrorDrawable.getIntrinsicHeight());
                                        adharBtn.setError("Pending", customErrorDrawable);
                                        adharBtn.setEnabled(true);

                                    } else if (adhar_status.equals("2")) {
                                        //2= verified

                                        Drawable customErrorDrawable = mContext.getResources().getDrawable(R.drawable.greenwarning);
                                        customErrorDrawable.setBounds(0, 0, customErrorDrawable.getIntrinsicWidth(), customErrorDrawable.getIntrinsicHeight());
                                        adharBtn.setError("Verified", customErrorDrawable);
                                        adharBtn.setEnabled(false);

                                    } else if (adhar_status.equals("3")) {
                                        //3= rejected

                                        Drawable customErrorDrawable = mContext.getResources().getDrawable(R.drawable.redwarning);
                                        customErrorDrawable.setBounds(0, 0, customErrorDrawable.getIntrinsicWidth(), customErrorDrawable.getIntrinsicHeight());
                                        adharBtn.setError("Rejected", customErrorDrawable);
                                        adharBtn.setEnabled(true);

                                    }


                                    JSONObject pan_data = obj.getJSONObject("pan_data");
                                    String pan_status = pan_data.getString("pan_status");

                                    if (pan_status.equals("0")) {
                                        //0 = not uploaded
                                        panBtn.setError(null);
                                        panBtn.setEnabled(true);
                                    } else if (pan_status.equals("1")) {
                                        //1= pending

                                        Drawable customErrorDrawable = mContext.getResources().getDrawable(R.drawable.yellowwarning);
                                        customErrorDrawable.setBounds(0, 0, customErrorDrawable.getIntrinsicWidth(), customErrorDrawable.getIntrinsicHeight());
                                        panBtn.setError("Pending", customErrorDrawable);
                                        panBtn.setEnabled(true);
                                    } else if (pan_status.equals("2")) {
                                        //2= verified

                                        Drawable customErrorDrawable = mContext.getResources().getDrawable(R.drawable.greenwarning);
                                        customErrorDrawable.setBounds(0, 0, customErrorDrawable.getIntrinsicWidth(), customErrorDrawable.getIntrinsicHeight());
                                        panBtn.setError("Verified", customErrorDrawable);
                                        panBtn.setEnabled(false);
                                    } else if (pan_status.equals("3")) {
                                        //3= rejected

                                        Drawable customErrorDrawable = mContext.getResources().getDrawable(R.drawable.redwarning);
                                        customErrorDrawable.setBounds(0, 0, customErrorDrawable.getIntrinsicWidth(), customErrorDrawable.getIntrinsicHeight());
                                        panBtn.setError("Rejected", customErrorDrawable);
                                        panBtn.setEnabled(true);
                                    }

                                    JSONObject cancel_check_data = obj.getJSONObject("cancel_check_data");
                                    String cancel_check_status = cancel_check_data.getString("cancel_check_status");

                                    if (cancel_check_status.equals("0")) {
                                        //0 = not uploaded
                                        checkBtn.setError(null);
                                        checkBtn.setEnabled(true);
                                    } else if (cancel_check_status.equals("1")) {
                                        //1= pending

                                        Drawable customErrorDrawable = mContext.getResources().getDrawable(R.drawable.yellowwarning);
                                        customErrorDrawable.setBounds(0, 0, customErrorDrawable.getIntrinsicWidth(), customErrorDrawable.getIntrinsicHeight());
                                        checkBtn.setError("Pending", customErrorDrawable);
                                        checkBtn.setEnabled(true);
                                    } else if (cancel_check_status.equals("2")) {
                                        //2= verified

                                        Drawable customErrorDrawable = mContext.getResources().getDrawable(R.drawable.greenwarning);
                                        customErrorDrawable.setBounds(0, 0, customErrorDrawable.getIntrinsicWidth(), customErrorDrawable.getIntrinsicHeight());
                                        checkBtn.setError("Verified", customErrorDrawable);
                                        checkBtn.setEnabled(false);
                                    } else if (cancel_check_status.equals("3")) {
                                        //3= rejected

                                        Drawable customErrorDrawable = mContext.getResources().getDrawable(R.drawable.redwarning);
                                        customErrorDrawable.setBounds(0, 0, customErrorDrawable.getIntrinsicWidth(), customErrorDrawable.getIntrinsicHeight());
                                        checkBtn.setError("Rejected", customErrorDrawable);
                                        checkBtn.setEnabled(true);
                                    }


                                } else {
                                    Toast.makeText(mContext, msg, Toast.LENGTH_SHORT).show();
                                }
                            } catch (
                                    JSONException e) {

                                e.printStackTrace();

                                Log.d("@@@ notSuccess: ", e.getMessage());
                            }
                            super.

                                    onSuccess(statusCode, headers, response);
                        }

                        @Override
                        public void onFailure(int statusCode, Header[] headers, String
                                responseString, Throwable throwable) {


                            super.onFailure(statusCode, headers, responseString, throwable);
                        }

                        @Override
                        public void onFailure(int statusCode, Header[] headers, Throwable
                                throwable, JSONObject errorResponse) {


                            super.onFailure(statusCode, headers, throwable, errorResponse);
                        }

                        @Override
                        public void onFailure(int statusCode, Header[] headers, Throwable
                                throwable, JSONArray errorResponse) {


                            super.onFailure(statusCode, headers, throwable, errorResponse);
                        }
                    });
                }
            });
        } catch (
                Exception e) {


            e.printStackTrace();
        }
    }

    public static void get_identity_verificationAdhar(Context mContext, String token, ImageView img1, ImageView img2) {

        try {

            final AsyncHttpClient client = new AsyncHttpClient();
            client.setSSLSocketFactory(MySSLSocketFactory.getFixedSocketFactory());
            final RequestParams request_param = new RequestParams();

            client.addHeader("token", token);
            Log.d("token", token);
            Log.d(TAG, "@@@get_identity_verification :" + GET_IDENTITY_VERIFICATION + "?" + request_param.toString());
            ((Activity) mContext).runOnUiThread(new Runnable() {
                public void run() {
                    client.addHeader("token", token);
                    client.post(GET_IDENTITY_VERIFICATION, request_param, new JsonHttpResponseHandler() {
                        @Override
                        public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                            Log.d(TAG, "@@@get_identity_verification_response :" + response.toString());

                            try {
                                int status = response.getInt("error_code");
                                String msg = response.getString("message");
                                if (status == 200) {
                                    JSONObject obj = response.getJSONObject("data");
                                    JSONObject adhaar_data = obj.getJSONObject("adhaar_data");
                                    String adhar_img_front = adhaar_data.getString("adhar_img_front");
                                    try {
                                        Picasso.get().load(adhar_img_front).into(img1);
                                    } catch (Exception ignored) {
                                    }

                                    String adhar_img_back = adhaar_data.getString("adhar_img_back");
                                    try {
                                        Picasso.get().load(adhar_img_back).into(img2);
                                    } catch (Exception ignored) {
                                    }


                                } else {
                                    Toast.makeText(mContext, msg, Toast.LENGTH_SHORT).show();
                                }
                            } catch (
                                    JSONException e) {

                                e.printStackTrace();

                                Log.d("@@@ notSuccess: ", e.getMessage());
                            }
                            super.

                                    onSuccess(statusCode, headers, response);
                        }

                        @Override
                        public void onFailure(int statusCode, Header[] headers, String
                                responseString, Throwable throwable) {


                            super.onFailure(statusCode, headers, responseString, throwable);
                        }

                        @Override
                        public void onFailure(int statusCode, Header[] headers, Throwable
                                throwable, JSONObject errorResponse) {


                            super.onFailure(statusCode, headers, throwable, errorResponse);
                        }

                        @Override
                        public void onFailure(int statusCode, Header[] headers, Throwable
                                throwable, JSONArray errorResponse) {


                            super.onFailure(statusCode, headers, throwable, errorResponse);
                        }
                    });
                }
            });
        } catch (
                Exception e) {


            e.printStackTrace();
        }
    }

    public static void get_identity_verificationPan(Context mContext, String token, ImageView img1) {

        try {

            final AsyncHttpClient client = new AsyncHttpClient();
            client.setSSLSocketFactory(MySSLSocketFactory.getFixedSocketFactory());
            final RequestParams request_param = new RequestParams();

            client.addHeader("token", token);
            Log.d(TAG, "@@@get_identity_verification :" + GET_IDENTITY_VERIFICATION + "?" + request_param.toString());
            ((Activity) mContext).runOnUiThread(new Runnable() {
                public void run() {
                    client.addHeader("token", token);
                    client.post(GET_IDENTITY_VERIFICATION, request_param, new JsonHttpResponseHandler() {
                        @Override
                        public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                            Log.d(TAG, "@@@get_identity_verification_response :" + response.toString());

                            try {
                                int status = response.getInt("error_code");
                                String msg = response.getString("message");
                                if (status == 200) {
                                    JSONObject obj = response.getJSONObject("data");
                                    JSONObject pan_data = obj.getJSONObject("pan_data");
                                    String pan_img = pan_data.getString("pan_img");
                                    try {
                                        Picasso.get().load(pan_img).into(img1);
                                    } catch (Exception ignored) {
                                    }


                                } else {
                                    Toast.makeText(mContext, msg, Toast.LENGTH_SHORT).show();
                                }
                            } catch (
                                    JSONException e) {

                                e.printStackTrace();

                                Log.d("@@@ notSuccess: ", e.getMessage());
                            }
                            super.

                                    onSuccess(statusCode, headers, response);
                        }

                        @Override
                        public void onFailure(int statusCode, Header[] headers, String
                                responseString, Throwable throwable) {


                            super.onFailure(statusCode, headers, responseString, throwable);
                        }

                        @Override
                        public void onFailure(int statusCode, Header[] headers, Throwable
                                throwable, JSONObject errorResponse) {


                            super.onFailure(statusCode, headers, throwable, errorResponse);
                        }

                        @Override
                        public void onFailure(int statusCode, Header[] headers, Throwable
                                throwable, JSONArray errorResponse) {


                            super.onFailure(statusCode, headers, throwable, errorResponse);
                        }
                    });
                }
            });
        } catch (
                Exception e) {


            e.printStackTrace();
        }
    }

    public static void get_identity_verificationCheck(Context mContext, String token, ImageView img1) {

        try {

            final AsyncHttpClient client = new AsyncHttpClient();
            client.setSSLSocketFactory(MySSLSocketFactory.getFixedSocketFactory());
            final RequestParams request_param = new RequestParams();

            client.addHeader("token", token);
            Log.d(TAG, "@@@get_identity_verification :" + GET_IDENTITY_VERIFICATION + "?" + request_param.toString());
            ((Activity) mContext).runOnUiThread(new Runnable() {
                public void run() {
                    client.addHeader("token", token);
                    client.post(GET_IDENTITY_VERIFICATION, request_param, new JsonHttpResponseHandler() {
                        @Override
                        public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                            Log.d(TAG, "@@@get_identity_verification_response :" + response.toString());

                            try {
                                int status = response.getInt("error_code");
                                String msg = response.getString("message");
                                if (status == 200) {
                                    JSONObject obj = response.getJSONObject("data");
                                    JSONObject cancel_check_data = obj.getJSONObject("cancel_check_data");
                                    String cancel_check_img = cancel_check_data.getString("cancel_check_img");
                                    try {
                                        Picasso.get().load(cancel_check_img).into(img1);
                                    } catch (Exception ignored) {
                                    }


                                } else {
                                    Toast.makeText(mContext, msg, Toast.LENGTH_SHORT).show();
                                }
                            } catch (
                                    JSONException e) {

                                e.printStackTrace();

                                Log.d("@@@ notSuccess: ", e.getMessage());
                            }
                            super.

                                    onSuccess(statusCode, headers, response);
                        }

                        @Override
                        public void onFailure(int statusCode, Header[] headers, String
                                responseString, Throwable throwable) {


                            super.onFailure(statusCode, headers, responseString, throwable);
                        }

                        @Override
                        public void onFailure(int statusCode, Header[] headers, Throwable
                                throwable, JSONObject errorResponse) {


                            super.onFailure(statusCode, headers, throwable, errorResponse);
                        }

                        @Override
                        public void onFailure(int statusCode, Header[] headers, Throwable
                                throwable, JSONArray errorResponse) {


                            super.onFailure(statusCode, headers, throwable, errorResponse);
                        }
                    });
                }
            });
        } catch (
                Exception e) {


            e.printStackTrace();
        }
    }

    public static void identity_verification(Context mContext, String identity_type, File file, File file2, String token) {

        try {

            final AsyncHttpClient client = new AsyncHttpClient();
            client.setSSLSocketFactory(MySSLSocketFactory.getFixedSocketFactory());
            final RequestParams request_param = new RequestParams();

            request_param.put("identity_type", identity_type);


            if (file != null) {
                request_param.put("file", file);
            }
            if (file2 != null) {
                request_param.put("file2", file2);
            }
            client.addHeader("token", token);
            Log.d(TAG, "@@@identity_verification :" + IDENTITY_VERIFICATION + "?" + request_param.toString());
            ((Activity) mContext).runOnUiThread(new Runnable() {
                public void run() {
                    client.addHeader("token", token);
                    client.post(IDENTITY_VERIFICATION, request_param, new JsonHttpResponseHandler() {
                        @Override
                        public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                            Log.d(TAG, "@@@identity_verification_response :" + response.toString());

                            try {
                                int status = response.getInt("error_code");
                                String msg = response.getString("message");
                                if (status == 200) {

                                    Toast.makeText(mContext, msg, Toast.LENGTH_SHORT).show();
                                    mContext.startActivity(new Intent(mContext, kycVerificationScreen.class));

                                } else {
                                    Toast.makeText(mContext, msg, Toast.LENGTH_SHORT).show();
                                }
                            } catch (
                                    JSONException e) {

                                e.printStackTrace();

                                Log.d("@@@ notSuccess: ", e.getMessage());
                            }
                            super.

                                    onSuccess(statusCode, headers, response);
                        }

                        @Override
                        public void onFailure(int statusCode, Header[] headers, String
                                responseString, Throwable throwable) {


                            super.onFailure(statusCode, headers, responseString, throwable);
                        }

                        @Override
                        public void onFailure(int statusCode, Header[] headers, Throwable
                                throwable, JSONObject errorResponse) {


                            super.onFailure(statusCode, headers, throwable, errorResponse);
                        }

                        @Override
                        public void onFailure(int statusCode, Header[] headers, Throwable
                                throwable, JSONArray errorResponse) {


                            super.onFailure(statusCode, headers, throwable, errorResponse);
                        }
                    });
                }
            });
        } catch (
                Exception e) {


            e.printStackTrace();
        }
    }

    public static void phonepay_payment_init(Context mContext, String uid, String amount) {
        try {

            final AsyncHttpClient client = new AsyncHttpClient();
            client.setSSLSocketFactory(MySSLSocketFactory.getFixedSocketFactory());
            final RequestParams request_param = new RequestParams();


            request_param.put("u_id", uid);
            request_param.put("amount", amount);


            Log.d(TAG, "@@@phonepay_payment_init :" + "http://192.168.0.200/influny-test-payment/PaymentController/phonepay_payment_init" + "?" + request_param.toString());
            ((Activity) mContext).runOnUiThread(new Runnable() {
                public void run() {

                    client.post("http://192.168.0.200/influny-test-payment/PaymentController/phonepay_payment_init", request_param, new JsonHttpResponseHandler() {
                        @Override
                        public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                            Log.d(TAG, "@@@phonepay_payment_init_response :" + response.toString());

                            try {
                                boolean success = response.getBoolean("success");
                                String msg = response.getString("message");
                                if (success == true) {

                                    Toast.makeText(mContext, msg, Toast.LENGTH_SHORT).show();

                                } else {
                                    Toast.makeText(mContext, msg, Toast.LENGTH_SHORT).show();
                                }
                            } catch (
                                    JSONException e) {

                                e.printStackTrace();

                                Log.d("@@@ notSuccess: ", e.getMessage());
                            }
                            super.

                                    onSuccess(statusCode, headers, response);
                        }

                        @Override
                        public void onFailure(int statusCode, Header[] headers, String
                                responseString, Throwable throwable) {


                            super.onFailure(statusCode, headers, responseString, throwable);
                        }

                        @Override
                        public void onFailure(int statusCode, Header[] headers, Throwable
                                throwable, JSONObject errorResponse) {


                            super.onFailure(statusCode, headers, throwable, errorResponse);
                        }

                        @Override
                        public void onFailure(int statusCode, Header[] headers, Throwable
                                throwable, JSONArray errorResponse) {


                            super.onFailure(statusCode, headers, throwable, errorResponse);
                        }
                    });
                }
            });
        } catch (
                Exception e) {


            e.printStackTrace();
        }
    }


    public static void upload_user_contact_listRetrofit(final Context mContext, String token, String user_contacts, String account_typeKey, String mobile, String emailKey, String name, String BusinessCatKey, String social_media_typeKey) {

        Retrofit retrofit = APIClient.getClient();
        API api = retrofit.create(API.class);
        MultipartBody.Builder builder = new MultipartBody.Builder();
        builder.setType(MultipartBody.FORM);
        builder.addFormDataPart("user_contacts", user_contacts);

        RequestBody requestBody = builder.build();

        api.upload_user_contact_list(requestBody, token).enqueue(new Callback<globalModel>() {
            @Override
            public void onResponse(@NonNull Call<globalModel> call, @NonNull Response<globalModel> response) {
                Log.d(TAG, "onResponseupload_user_contact_listRetrofit: " + response.code());

                if (response.body() != null) {

                    int errorcode = response.body().getError_code();
                    String message = response.body().getMessage();


                    if (errorcode == 200) {


                        Toast.makeText(mContext, message, Toast.LENGTH_SHORT).show();


                        if (mContext != null) {


                            Intent intent = new Intent(mContext, MainActivity.class);
                            intent.putExtra("account_typeKey", account_typeKey);
                            intent.putExtra("mobileKey", mobile);
                            intent.putExtra("emailKey", emailKey);
                            intent.putExtra("nameKey", name);
                            intent.putExtra("BusinessCatKey", BusinessCatKey);
                            intent.putExtra("social_media_typeKey", social_media_typeKey);
                            intent.putExtra("tokenKey", token);


                            mContext.startActivity(intent);

                            //((Activity) mContext).finish();
                            ReferActivity.dialog.dismiss();

                        }


                    } else {
                        Toast.makeText(mContext, message, Toast.LENGTH_SHORT).show();

                    }

                } else {
                    // Toast.makeText(mContext, response.code(), Toast.LENGTH_SHORT).show();
                    Log.d(TAG, "onResponse: " + response.toString());
                }

            }

            @Override
            public void onFailure(@NonNull Call<globalModel> call, @NonNull Throwable t) {
                //   Toast.makeText(mContext, "" + t.getMessage(), Toast.LENGTH_SHORT).show();
                Log.e("loginerror", t.getMessage());
            }
        });
    }


    public static void fetchDataProfileVisitingCard(final Context mContext, String token, EditText busname, EditText yourname, EditText email, EditText web, EditText addressEt, EditText mobile) {

        try {

            final AsyncHttpClient client = new AsyncHttpClient();
            client.setSSLSocketFactory(MySSLSocketFactory.getFixedSocketFactory());
            final RequestParams request_param = new RequestParams();


            Log.d(TAG, "@@@fetch_user_profile :" + FETCH_USER_PROFILE + "?" + request_param.toString());
            ((Activity) mContext).runOnUiThread(new Runnable() {
                public void run() {


                    client.addHeader("token", token);
                    client.post(FETCH_USER_PROFILE, request_param, new JsonHttpResponseHandler() {
                        @Override
                        public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                            Log.d(TAG, "@@@fetch_user_profile_response :" + response.toString());

                            try {
                                int status = response.getInt("error_code");
                                if (status == 200) {
                                    String message = response.getString("message");
                                    //    Toast.makeText(mContext, message, Toast.LENGTH_SHORT).show();

                                    //main code type here

                                    JSONObject obj = response.getJSONObject("data");
                                    String account_type = obj.getString("account_type");

                                    Constant.setSfFunction(mContext);
                                    Constant.setSF.putString(Constant.ACC_TYPE, account_type);
                                    Constant.setSF.apply();


                                    //for business
                                    String name = obj.getString("name");
                                    String profile_pic = obj.getString("profile_pic");

                                    try {
                                        mainFragment.getImgUrl(profile_pic);
                                    } catch (Exception ex) {
                                    }


                                    Log.d("profile_pic_new", "https://www.adpanda.in/api/" + profile_pic);

                                    String email_id = obj.getString("email_id");
                                    String mobile_number = obj.getString("mobile_number");
                                    String address = obj.getString("address");
                                    String dob = obj.getString("dob");
                                    String political_interest = obj.getString("political_interest");

                                    //tbis is for business account details
                                    String bussiness_name = obj.getString("bussiness_name");
                                    String website = obj.getString("website");
                                    String business_category = obj.getString("business_category");
                                    String date_of_incorporation = obj.getString("date_of_incorporation");
                                    String business_details = obj.getString("business_details");


                                    if (account_type.equals("1")) {

                                        // 2 for personal
                                        if (bussiness_name != null) {
                                            if(bussiness_name.equals("null")){
                                                busname.setText("");
                                            }else {
                                                busname.setText(bussiness_name);
                                            }
                                        }

                                        if (website != null) {
                                            if(bussiness_name.equals("null")){
                                                web.setText("");
                                            }else {
                                                web.setText(website);
                                            }
                                        }

                                        if (name != null) {
                                            if(name.equals("null")){
                                                yourname.setText("");
                                            }else {
                                                yourname.setText(name);
                                            }
                                        }

                                        if (email_id != null) {
                                            if(email_id.equals("null")) {
                                                email.setText("");
                                            }else{
                                                email.setText(email_id);
                                            }
                                        }

                                        if (address != null) {
                                            if(address.equals("null")) {
                                                addressEt.setText("");
                                            }else{
                                                addressEt.setText(address);
                                            }
                                        }
                                        if (mobile_number != null) {
                                            if(mobile_number.equals("null")) {
                                                mobile.setText("");
                                            }else{
                                                mobile.setText(mobile_number);
                                            }
                                        }
                                    } else if (account_type.equals("2")) {

                                        // 2 for personal
                                        if (bussiness_name != null) {
                                            if(bussiness_name.equals("null")){
                                                busname.setText("");
                                            }else {
                                                busname.setText(bussiness_name);
                                            }
                                        }

                                        if (website != null) {
                                            if(bussiness_name.equals("null")){
                                                web.setText("");
                                            }else {
                                                web.setText(website);
                                            }
                                        }

                                        if (name != null) {
                                            if(name.equals("null")){
                                                yourname.setText("");
                                            }else {
                                                yourname.setText(name);
                                            }
                                        }

                                        if (email_id != null) {
                                            if(email_id.equals("null")) {
                                                email.setText("");
                                            }else{
                                                email.setText(email_id);
                                            }
                                        }

                                        if (address != null) {
                                            if(address.equals("null")) {
                                                addressEt.setText("");
                                            }else{
                                                addressEt.setText(address);
                                            }
                                        }
                                        if (mobile_number != null) {
                                            if(mobile_number.equals("null")) {
                                                mobile.setText("");
                                            }else{
                                                mobile.setText(mobile_number);
                                            }
                                        }
                                    }

                                } else {
                                    String message = response.getString("message");
                                    // Toast.makeText(mContext, message, Toast.LENGTH_SHORT).show();
                                }
                            } catch (JSONException e) {

                                e.printStackTrace();

                                Log.d("@@@ notSuccess: ", e.getMessage());
                            }
                            super.onSuccess(statusCode, headers, response);
                        }

                        @Override
                        public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {


                            super.onFailure(statusCode, headers, responseString, throwable);
                        }

                        @Override
                        public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {


                            super.onFailure(statusCode, headers, throwable, errorResponse);
                        }

                        @Override
                        public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONArray errorResponse) {


                            super.onFailure(statusCode, headers, throwable, errorResponse);
                        }
                    });
                }
            });
        } catch (Exception e) {


            e.printStackTrace();
        }
    }


}


