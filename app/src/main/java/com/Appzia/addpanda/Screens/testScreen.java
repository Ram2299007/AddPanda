package com.Appzia.addpanda.Screens;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.graphics.PointF;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

import com.Appzia.addpanda.databinding.ActivityTestScreenBinding;

public class testScreen extends AppCompatActivity {

    ActivityTestScreenBinding binding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityTestScreenBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());





    }

}