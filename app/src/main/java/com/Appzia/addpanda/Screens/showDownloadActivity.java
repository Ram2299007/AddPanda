package com.Appzia.addpanda.Screens;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.Appzia.addpanda.databinding.ActivityShowDownloadBinding;

public class showDownloadActivity extends AppCompatActivity {

    ActivityShowDownloadBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityShowDownloadBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }
}