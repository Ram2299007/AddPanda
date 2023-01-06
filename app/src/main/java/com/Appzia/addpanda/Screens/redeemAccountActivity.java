package com.Appzia.addpanda.Screens;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.Appzia.addpanda.databinding.ActivityRedeemAccountBinding;

public class redeemAccountActivity extends AppCompatActivity {

    ActivityRedeemAccountBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRedeemAccountBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }
}