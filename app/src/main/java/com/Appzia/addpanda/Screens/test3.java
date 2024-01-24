package com.Appzia.addpanda.Screens;

import androidx.appcompat.app.AppCompatActivity;
import androidx.browser.customtabs.CustomTabsIntent;
import androidx.core.content.ContextCompat;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import com.Appzia.addpanda.R;
import com.Appzia.addpanda.databinding.ActivityTest3Binding;

public class test3 extends AppCompatActivity {

    ActivityTest3Binding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= ActivityTest3Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        CustomTabsIntent.Builder builder = new CustomTabsIntent.Builder();
        builder.setToolbarColor(ContextCompat.getColor(test3.this, R.color.appThemeColor));
        builder.build()
                .launchUrl(test3.this, Uri.parse("https://www.google.com"));
    }
}