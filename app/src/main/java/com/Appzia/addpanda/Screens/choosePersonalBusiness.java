package com.Appzia.addpanda.Screens;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.Appzia.addpanda.R;
import com.Appzia.addpanda.databinding.ActivityChoosePersonalBusinessBinding;

import java.util.Objects;

public class choosePersonalBusiness extends AppCompatActivity {

    ActivityChoosePersonalBusinessBinding binding;
    String token;
    String social_media_type,input_parameter,reffral_code;
    String personal_business_key;

    String mobileKey;

    @Override
    protected void onStart() {
        super.onStart();

        try{
            mobileKey = getIntent().getStringExtra("mobileKey");

        }catch (Exception ignored){}


//        SharedPreferences sh = getSharedPreferences("MySharedPref", Context.MODE_PRIVATE);
//        String user_id = sh.getString("user_id", "");
//
//
//        if (!user_id.isEmpty()) {
//
//            startActivity(new Intent(choosePersonalBusiness.this, MainActivity.class));
//        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityChoosePersonalBusinessBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Objects.requireNonNull(getSupportActionBar()).hide();
        Window window = this.getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.setStatusBarColor(this.getResources().getColor(R.color.appThemeColor));

        try {
            token = getIntent().getStringExtra("tokenKey");
            social_media_type = getIntent().getStringExtra("social_media_typeKey");
            input_parameter = getIntent().getStringExtra("input_parameterKey");
            reffral_code = getIntent().getStringExtra("reffral_codeKey");
        }catch (Exception ignored){}

//        try {
//
//            if(personal_business_key.equals("personal_business_key")) {
//                SharedPreferences sharedPreferences = getSharedPreferences("MySharedPref", MODE_PRIVATE);
//                SharedPreferences.Editor myEdit = sharedPreferences.edit();
//                myEdit.putString("tokenSF", token);
//                myEdit.putString("input_parameterSF", input_parameter);
//                myEdit.apply();
//            }
//        } catch (Exception ignored) {
//        }

        binding.backArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), MobileVerification.class));
            }
        });


        binding.personal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),Personal.class);
                intent.putExtra("tokenKey",token);
                intent.putExtra("social_media_typeKey",social_media_type);
                intent.putExtra("input_parameterKey",input_parameter);
                intent.putExtra("reffral_codeKey",reffral_code);
                intent.putExtra("mobileKey",mobileKey);
                startActivity(intent);
            }
        });
        binding.business.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),Business.class);
                intent.putExtra("tokenKey",token);
                intent.putExtra("social_media_typeKey",social_media_type);
                intent.putExtra("input_parameterKey",input_parameter);
                intent.putExtra("reffral_codeKey",reffral_code);
                intent.putExtra("mobileKey",mobileKey);
                startActivity(intent);
            }
        });

    }
}