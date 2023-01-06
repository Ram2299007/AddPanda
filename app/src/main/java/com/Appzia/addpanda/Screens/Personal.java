package com.Appzia.addpanda.Screens;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.Toast;


import com.Appzia.addpanda.R;
import com.Appzia.addpanda.Util.Constant.Constant;
import com.Appzia.addpanda.Webservice.Webservice;
import com.Appzia.addpanda.databinding.ActivityPersonalBinding;
import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;


import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Personal extends AppCompatActivity {
    ActivityPersonalBinding binding;

    String authId;
    String personal = "PERSONAL";
    DatePickerDialog.OnDateSetListener setListener;
    Context mContext;
    String account_type = "1";
    String token, social_media_type, input_parameter, reffral_code;
    FirebaseAuth auth;


    public static EditText email;
    public static EditText mobilePersonal;
    String MOBILE_VERIFIED_KEY;


    @Override
    protected void onStart() {
        super.onStart();


        try {
            SharedPreferences sh = getSharedPreferences("MySharedPref", Context.MODE_PRIVATE);
            token = sh.getString("TOKEN_SF", "");
            MOBILE_VERIFIED_KEY = sh.getString("MOBILE_VERIFIED_KEY", "");
            binding.mobilePersonal.setText(MOBILE_VERIFIED_KEY);
        } catch (Exception ignored) {
        }

        try {
            auth = FirebaseAuth.getInstance();
            String name = Objects.requireNonNull(auth.getCurrentUser()).getDisplayName();
            String email = Objects.requireNonNull(auth.getCurrentUser()).getEmail();
            binding.name.setText(name);
            binding.email.setText(email);
        } catch (Exception ignored) {
        }
    }


    //

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityPersonalBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Objects.requireNonNull(getSupportActionBar()).hide();
        Window window = this.getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.setStatusBarColor(this.getResources().getColor(R.color.appThemeColor));

        email = findViewById(R.id.email);
        mobilePersonal = findViewById(R.id.mobilePersonal);

        authId = FirebaseAuth.getInstance().getUid();

        mContext = this;

        try {

            social_media_type = getIntent().getStringExtra("social_media_typeKey");

            if (social_media_type == null) {
                social_media_type = "0";//Assign default string    0 for phone authentication
            }
            input_parameter = getIntent().getStringExtra("input_parameterKey");
            reffral_code = getIntent().getStringExtra("reffral_codeKey");
        } catch (Exception ignored) {
        }

        Calendar calendar = Calendar.getInstance();
        final int year = calendar.get(Calendar.YEAR);
        final int month = calendar.get(Calendar.MONTH);
        final int day = calendar.get(Calendar.DAY_OF_MONTH);
        //  DatePickerDialog.OnDateSetListener setListener;

        binding.dob.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.Q)
            @Override
            public void onClick(View view) {

                DatePickerDialog datePickerDialog = new DatePickerDialog(Personal.this, android.R.style.Theme_Holo_Dialog_MinWidth
                        , setListener, year, month, day);
                datePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                datePickerDialog.show();
            }
        });

        setListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int years, int months, int dayofMonths) {

                months = months + 1;


                if (String.valueOf(months).length() == 1 && String.valueOf(dayofMonths).length() == 1) {
                    String date = "0" + dayofMonths + "-" + "0" + months + "-" + years;
                    binding.dob.setText(date);
                } else if (String.valueOf(months).length() == 1 && String.valueOf(dayofMonths).length() == 2) {
                    String date = dayofMonths + "-" + "0" + months + "-" + years;
                    binding.dob.setText(date);
                } else if (String.valueOf(months).length() == 2 && String.valueOf(dayofMonths).length() == 1) {
                    String date = "0" + dayofMonths + "-" + months + "-" + years;
                    binding.dob.setText(date);
                } else if (String.valueOf(months).length() == 2 && String.valueOf(dayofMonths).length() == 2) {
                    String date = dayofMonths + "-" + months + "-" + years;
                    binding.dob.setText(date);
                }

                //work is done


            }
        };


        binding.sb.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

                if (seekBar.getProgress() > 95) {

                } else {

                    //Toast.makeText(MobileVerification.this, "Test1", Toast.LENGTH_SHORT).show();
                }

                if (seekBar.getProgress() <= 90 && seekBar.getProgress() >= 0) {
                    binding.sb.setProgress(0);
                }

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {


            }

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress,
                                          boolean fromUser) {


                if (binding.mobilePersonal.length() < 10) {
                    binding.mobilePersonal.setError(" Mobile Number must be 10 numbers !");
                    binding.mobilePersonal.requestFocus();
                    binding.sb.setProgress(0);
                } else if (!android.util.Patterns.EMAIL_ADDRESS.matcher(binding.email.getText().toString()).matches()) {
                    binding.email.setError("Wrong email address !");
                    binding.email.requestFocus();
                    binding.sb.setProgress(0);
                } else {
                    if (binding.email.getError().equals("This email id already used! Please try other email id.")) {

                        Drawable customErrorDrawable = mContext.getResources().getDrawable(R.drawable.cancel);
                        customErrorDrawable.setBounds(0, 0, customErrorDrawable.getIntrinsicWidth(), customErrorDrawable.getIntrinsicHeight());
                        Personal.email.setError("This email id already used! Please try other email id.", customErrorDrawable);
                        binding.email.requestFocus();
                        binding.sb.setProgress(0);
                    } else if (binding.mobilePersonal.getError().equals("This mobile_number id already used! Please try other mobile_number.")) {
                        Drawable customErrorDrawable = mContext.getResources().getDrawable(R.drawable.cancel);
                        customErrorDrawable.setBounds(0, 0, customErrorDrawable.getIntrinsicWidth(), customErrorDrawable.getIntrinsicHeight());
                        Personal.mobilePersonal.setError("This mobile_number id already used! Please try other mobile_number.", customErrorDrawable);
                        binding.mobilePersonal.requestFocus();
                        binding.sb.setProgress(0);
                    } else {

                        if (progress == 100) {

                            if (binding.mobilePersonal.getText().toString().isEmpty() || binding.email.getText().toString().isEmpty() || binding.mobilePersonal.getText().toString().isEmpty() || binding.address.getText().toString().isEmpty() || binding.dob.getText().toString().isEmpty()) {

                                Snackbar.make(binding.getRoot(), "Missing Information ?", Snackbar.LENGTH_LONG)
                                        .setAction("CLOSE", new View.OnClickListener() {
                                            @Override
                                            public void onClick(View view) {

                                            }
                                        })
                                        .setActionTextColor(getResources().getColor(android.R.color.holo_red_light))
                                        .show();

                                binding.sb.setProgress(0);


                            } else {


                                if (social_media_type.equals("1")) {    //for google

                                    Constant.NetworkCheck(mContext);
                                    if ((Constant.wifiInfo != null && Constant.wifiInfo.isConnected()) || (Constant.mobileInfo != null && Constant.mobileInfo.isConnected())) {
                                        Webservice.social_media_login_personal(mContext, social_media_type, input_parameter, reffral_code, account_type, binding.name.getText().toString(), binding.email.getText().toString(), binding.mobilePersonal.getText().toString(), binding.address.getText().toString(), binding.dob.getText().toString(), binding.political.getText().toString());

                                    }
                                    else {
                                        Constant.NetworkCheckDialogue(mContext);
                                        Constant.dialogForNetwork.show();

                                        AppCompatButton btn = Constant.dialogForNetwork.findViewById(R.id.retry);

                                        btn.setOnClickListener(new View.OnClickListener() {
                                            @Override
                                            public void onClick(View v) {
                                                Constant.dialogForNetwork.dismiss();

                                            }
                                        });


                                    }


                                } else if (social_media_type.equals("2")) {  // for facebook

                                    //this is for facebook
                                    Constant.NetworkCheck(mContext);
                                    if ((Constant.wifiInfo != null && Constant.wifiInfo.isConnected()) || (Constant.mobileInfo != null && Constant.mobileInfo.isConnected())) {
                                        Webservice.social_media_login_personal(mContext, social_media_type, input_parameter, reffral_code, account_type, binding.name.getText().toString(), binding.email.getText().toString(), binding.mobilePersonal.getText().toString(), binding.address.getText().toString(), binding.dob.getText().toString(), binding.political.getText().toString());

                                    }
                                    else {
                                        Constant.NetworkCheckDialogue(mContext);
                                        Constant.dialogForNetwork.show();

                                        AppCompatButton btn = Constant.dialogForNetwork.findViewById(R.id.retry);

                                        btn.setOnClickListener(new View.OnClickListener() {
                                            @Override
                                            public void onClick(View v) {
                                                Constant.dialogForNetwork.dismiss();

                                            }
                                        });


                                    }


                                } else if (social_media_type.equals("0")) {

                                    Constant.NetworkCheck(mContext);
                                    if ((Constant.wifiInfo != null && Constant.wifiInfo.isConnected()) || (Constant.mobileInfo != null && Constant.mobileInfo.isConnected())) {
                                        Webservice.update_user_profile_data_personal(mContext, account_type, binding.name.getText().toString(), binding.email.getText().toString(), binding.mobilePersonal.getText().toString(), binding.address.getText().toString(), binding.dob.getText().toString(), binding.political.getText().toString(), token);

                                    } else {
                                        Constant.NetworkCheckDialogue(mContext);
                                        Constant.dialogForNetwork.show();

                                        AppCompatButton btn = Constant.dialogForNetwork.findViewById(R.id.retry);

                                        btn.setOnClickListener(new View.OnClickListener() {
                                            @Override
                                            public void onClick(View v) {
                                                Constant.dialogForNetwork.dismiss();

                                            }
                                        });


                                    }
                                }


                            }
                        }
                    }
                }


            }
        });

        binding.email.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!android.util.Patterns.EMAIL_ADDRESS.matcher(binding.email.getText().toString()).matches()) {


                } else {
                    Webservice.check_email_id(mContext, binding.email.getText().toString());
                }


            }

            @Override
            public void afterTextChanged(Editable s) {


            }
        });
        binding.mobilePersonal.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {


                if (social_media_type.equals("1") || social_media_type.equals("2")) {
                    if (binding.mobilePersonal.length() < 10) {

                    } else {
                        Webservice.check_mobile_number(mContext, binding.mobilePersonal.getText().toString());
                        binding.mobilePersonal.setFocusable(true);
                    }

                } else {
                    binding.mobilePersonal.setFocusable(false);

                    Drawable customErrorDrawable = mContext.getResources().getDrawable(R.drawable.graynew);
                    customErrorDrawable.setBounds(0, 0, customErrorDrawable.getIntrinsicWidth(), customErrorDrawable.getIntrinsicHeight());
                    //here set error in edittext

                    binding.mobilePersonal.setError("n", customErrorDrawable);

                }


            }

            @Override
            public void afterTextChanged(Editable s) {


            }
        });


    }


}