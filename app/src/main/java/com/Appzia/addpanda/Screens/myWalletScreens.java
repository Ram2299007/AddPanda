package com.Appzia.addpanda.Screens;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.Appzia.addpanda.databinding.ActivityMyWalletScreensBinding;

public class myWalletScreens extends AppCompatActivity {
    ActivityMyWalletScreensBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMyWalletScreensBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }
}