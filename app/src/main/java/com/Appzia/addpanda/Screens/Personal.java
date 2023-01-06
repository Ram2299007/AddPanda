package com.Appzia.addpanda.Screens;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.SeekBar;
import android.widget.Toast;


import com.Appzia.addpanda.R;
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


import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Personal extends AppCompatActivity {
    ActivityPersonalBinding binding;

    String authId;
    String personal = "PERSONAL";

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

        authId = FirebaseAuth.getInstance().getUid();


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

                    if (binding.mobile.getText().toString().isEmpty() || binding.email.getText().toString().isEmpty() || binding.mobile.getText().toString().isEmpty() || binding.address.getText().toString().isEmpty() || binding.dob.getText().toString().isEmpty()) {

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
                            startActivity(new Intent(getApplicationContext(), ReferActivity.class));
                       // binding.sb.setProgress(0);
                    }
                }


            }
        });
    }


}