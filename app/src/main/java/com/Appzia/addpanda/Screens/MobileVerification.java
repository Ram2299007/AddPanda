package com.Appzia.addpanda.Screens;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.SeekBar;

import com.google.android.material.snackbar.Snackbar;
import com.Appzia.addpanda.R;
import com.Appzia.addpanda.databinding.ActivityMobileVerificationBinding;

import java.util.Objects;

public class MobileVerification extends AppCompatActivity {

    ActivityMobileVerificationBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMobileVerificationBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Objects.requireNonNull(getSupportActionBar()).hide();
        Window window = this.getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.setStatusBarColor(this.getResources().getColor(R.color.appThemeColor));

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
                if (progress > 95) {

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
                        startActivity(new Intent(getApplicationContext(), choosePersonalBusiness.class));
                        binding.sb.setProgress(0);
                    }
                }


            }
        });


    }
}