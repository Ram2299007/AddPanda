package com.Appzia.addpanda.Screens;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.Appzia.addpanda.databinding.ActivityPartnerWithUsBinding;
import com.Appzia.addpanda.databinding.ActivityPaymentWithoutCoupanBinding;

public class payment_without_coupan_Activity extends AppCompatActivity {

    ActivityPaymentWithoutCoupanBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityPaymentWithoutCoupanBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.notification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), NotificationActivity.class));
            }
        });
        binding.backArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });     binding.coupon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), coupanScreen.class));
            }
        });
    }
}