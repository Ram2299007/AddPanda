package com.Appzia.addpanda.Screens;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.Appzia.addpanda.databinding.ActivityTransactionHistoryBinding;

public class transactionHistoryActivity extends AppCompatActivity {

    ActivityTransactionHistoryBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityTransactionHistoryBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }
}