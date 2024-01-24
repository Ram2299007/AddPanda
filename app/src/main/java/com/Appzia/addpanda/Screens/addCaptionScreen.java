package com.Appzia.addpanda.Screens;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.Appzia.addpanda.R;
import com.Appzia.addpanda.Util.Constant.Constant;
import com.Appzia.addpanda.databinding.ActivityAddCaptionScreenBinding;
import com.google.android.material.bottomappbar.BottomAppBar;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.shape.CornerFamily;
import com.google.android.material.shape.MaterialShapeDrawable;

import java.util.Objects;

public class addCaptionScreen extends AppCompatActivity {

    ActivityAddCaptionScreenBinding binding;
    Context mContext;

    String text, fbKey, instaKey, linkdnKey, addressKey, captionEdtKey, whatsappKey;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAddCaptionScreenBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        mContext = binding.getRoot().getContext();

//By default on each activity android studio
        Objects.requireNonNull(getSupportActionBar()).hide();
        Window window = this.getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.setStatusBarColor(this.getResources().getColor(R.color.appThemeColor));
        binding.bottomNavigationView.setBackground(null);
        //Corner radius
        BottomAppBar bottomAppBar = findViewById(R.id.bottomAppBar);
        MaterialShapeDrawable bottomBarBackground = (MaterialShapeDrawable) bottomAppBar.getBackground();
        bottomBarBackground.setShapeAppearanceModel(bottomBarBackground.getShapeAppearanceModel().toBuilder().setTopRightCorner(CornerFamily.ROUNDED, 10).setTopLeftCorner(CornerFamily.ROUNDED, 10).build());

        binding.bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.home:


                        Intent i = new Intent(getApplicationContext(), MainActivity.class);
                        i.putExtra("editKey", "editHome");
                        startActivity(i);


                        break;

                    case R.id.profile:

                        Intent i2 = new Intent(getApplicationContext(), MainActivity.class);
                        i2.putExtra("editKey", "editProfile");
                        startActivity(i2);


                        break;
                    case R.id.category:

                        Intent i3 = new Intent(getApplicationContext(), MainActivity.class);
                        i3.putExtra("editKey", "editCategory");
                        startActivity(i3);

                        break;
                    case R.id.download:

                        Intent i4 = new Intent(getApplicationContext(), MainActivity.class);
                        i4.putExtra("editKey", "editDownload");
                        startActivity(i4);


                        break;
                }
                return true;


            }
        });

        try {

            Constant.getSfFuncion(mContext);
            text = Constant.getSF.getString("textKey", "");
            fbKey = Constant.getSF.getString("fbKey", "");
            instaKey = Constant.getSF.getString("instaKey", "");
            linkdnKey = Constant.getSF.getString("linkdnKey", "");
            addressKey = Constant.getSF.getString("addressKey", "");
            captionEdtKey = Constant.getSF.getString("captionEdtKey", "");
            whatsappKey = Constant.getSF.getString("whatsappKey", "");


            binding.text.setText(text);
            binding.fb.setText(fbKey);
            binding.insta.setText(instaKey);
            binding.linkdn.setText(linkdnKey);
            binding.address.setText(addressKey);
            binding.captionEdt.setText(captionEdtKey);


        } catch (Exception ignored) {
        }
        binding.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(getApplicationContext(), createScreen.class);
                startActivity(i);


            }
        });
        binding.backArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        binding.caption.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (binding.text.getText().toString().equals("") || binding.caption.getText().toString().equals("")) {
                    Toast.makeText(mContext, "Text and caption can't be empty ?", Toast.LENGTH_SHORT).show();
                } else {

                    Constant.setSfFunction(mContext);
                    Constant.setSF.putString("textKey", binding.text.getText().toString());
                    Constant.setSF.putString("fbKey", binding.fb.getText().toString());
                    Constant.setSF.putString("instaKey", binding.insta.getText().toString());
                    Constant.setSF.putString("linkdnKey", binding.linkdn.getText().toString());
                    Constant.setSF.putString("addressKey", binding.address.getText().toString());
                    Constant.setSF.putString("captionEdtKey", binding.captionEdt.getText().toString());
                    Constant.setSF.apply();

                    Constant.getSfFuncion(mContext);
                    String data = Constant.getSF.getString("veriHoriKey", "");

                    if (data.equals("VERI")) {
                        startActivity(new Intent(getApplicationContext(), edit_vertical_visiting_card.class));
                    } else if (data.equals("HORI")) {
                        startActivity(new Intent(getApplicationContext(), edit_and_download_visitingcard.class));
                    }
                }

            }
        });
    }
}