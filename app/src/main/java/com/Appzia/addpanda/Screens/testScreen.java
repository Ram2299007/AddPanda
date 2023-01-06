package com.Appzia.addpanda.Screens;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.text.HtmlCompat;

import android.graphics.Color;
import android.graphics.PointF;
import android.os.Bundle;
import android.text.Html;
import android.view.MotionEvent;
import android.view.View;
import android.webkit.WebView;
import android.widget.TextView;

import com.Appzia.addpanda.R;
import com.Appzia.addpanda.databinding.ActivityTestScreenBinding;

public class testScreen extends AppCompatActivity {

    ActivityTestScreenBinding binding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityTestScreenBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        WebView myWebView = (WebView) findViewById(R.id.my_webview);
        String htmlData = "<marquee><body> <p><i style=\"color: rgb(0, 0, 0); font-family: &quot;Josefin Sans&quot;, sans-serif; font-size: 18px; font-weight: 700; text-align: center; background-color: rgb(247, 173, 107);\">Kerala 555 is world’s no 1 Betting game where you can win cash prize for every right betting. Its 100% genuine.</i><br>   </p></body></marquee>";
        myWebView.loadData(htmlData, "text/html", "UTF-8");

    }

}