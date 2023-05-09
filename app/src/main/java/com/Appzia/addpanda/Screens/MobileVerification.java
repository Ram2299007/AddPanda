package com.Appzia.addpanda.Screens;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.SeekBar;
import android.widget.Toast;

import com.Appzia.addpanda.Webservice.Webservice;
import com.google.android.material.snackbar.Snackbar;
import com.Appzia.addpanda.R;
import com.Appzia.addpanda.databinding.ActivityMobileVerificationBinding;
import com.google.firebase.auth.FirebaseAuth;

import java.util.Objects;

public class MobileVerification extends AppCompatActivity {

    ActivityMobileVerificationBinding binding;
    String otp, token;
    Context mContext;
    private FirebaseAuth mfirebaseAuth;
    public static String type = "2";    //for mobile type

    String mobileKey;

    @Override
    protected void onStart() {
        super.onStart();

        try {
            mobileKey = getIntent().getStringExtra("mobileKey");
            binding.mobile.setText(mobileKey);

        } catch (Exception ignored) {

        }


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMobileVerificationBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        mfirebaseAuth = FirebaseAuth.getInstance();

        Objects.requireNonNull(getSupportActionBar()).hide();
        Window window = this.getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.setStatusBarColor(this.getResources().getColor(R.color.appThemeColor));

        mContext = this;
        otp = getIntent().getStringExtra("otpKey");
        token = getIntent().getStringExtra("tokenKey");


        try {
            Toast.makeText(this, otp, Toast.LENGTH_SHORT).show();


            int number = Integer.parseInt(otp);
            // Toast.makeText(this, String.valueOf(number), Toast.LENGTH_SHORT).show();

            int first = number / 1000; // Extract the first digit
            int second = (number / 100) % 10; // Extract the second digit
            int third = (number / 10) % 10; // Extract the third digit
            int four = (number) % 10; // Extract the fourth digit


            if(String.valueOf(number).length()==3){
                String data = "0"+String.valueOf(number);


                number = Integer.parseInt(data);
            }



            Log.d("Alldigitsnew", String.valueOf(number));

            Log.d("firstNumber", String.valueOf(first));
            binding.one.setText( String.valueOf(first));
            Log.d("secondNumber", String.valueOf(second));
            binding.two.setText( String.valueOf(second));
            Log.d("thirdNumber", String.valueOf(third));
            binding.three.setText( String.valueOf(third));

            Log.d("fourNumber", String.valueOf(four));
            binding.four.setText( String.valueOf(four));

        } catch (Exception ignored) {
        }

        binding.one.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                //validation for otp edittext
                if (binding.one.length() == 1) {
                    binding.two.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        binding.two.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                //validation for otp edittext
                if (binding.two.length() == 1) {
                    binding.three.requestFocus();
                }

                if (binding.two.getText().toString().isEmpty()) {
                    binding.one.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        binding.three.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                //validation for otp edittext
                if (binding.three.length() == 1) {
                    binding.four.requestFocus();
                }

                if (binding.three.getText().toString().isEmpty()) {
                    binding.two.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        binding.four.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {


            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                //validation for otp edittext
                if (binding.four.getText().toString().isEmpty()) {
                    binding.three.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


        binding.editPhoneID.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sharedPreferences = getSharedPreferences("MySharedPref", MODE_PRIVATE);
                SharedPreferences.Editor myEdit = sharedPreferences.edit();
                myEdit.putString("user_id", "");
                myEdit.putString("TOKEN_SF", "");
                myEdit.putString("social_media_type", "");
                myEdit.apply();
                mfirebaseAuth.signOut();
                startActivity(new Intent(getApplicationContext(), SignInActivity.class));
            }
        });

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
                if (progress == 100) {



                    if (binding.one.getText().toString().isEmpty() || binding.two.getText().toString().isEmpty() || binding.three.getText().toString().isEmpty() || binding.one.getText().toString().isEmpty()) {

                        Snackbar.make(binding.getRoot(), "INVALID OTP ?", Snackbar.LENGTH_LONG)
                                .setAction("CLOSE", new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {

                                    }
                                })
                                .setActionTextColor(getResources().getColor(android.R.color.holo_red_light))
                                .show();

                        binding.sb.setProgress(0);


                    } else {

                        try {

                            SharedPreferences sharedPreferences = mContext.getSharedPreferences("MySharedPref", MODE_PRIVATE);
                            SharedPreferences.Editor myEdit = sharedPreferences.edit();
                            myEdit.putString("MOBILE_VERIFIED_KEY",  binding.mobile.getText().toString());
                            myEdit.apply();

                            Webservice.verify_otp(mContext, type, otp, token, binding.mobile.getText().toString());
                        } catch (Exception ignored) {
                        }

                    }
                }


            }
        });


    }

    @Override
    public void onBackPressed() {
      Intent intent = new Intent(getApplicationContext(),SignInActivity.class);
      startActivity(intent);
    }
}