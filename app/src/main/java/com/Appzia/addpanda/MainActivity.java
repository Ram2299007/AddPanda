package com.Appzia.addpanda;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;


import com.Appzia.addpanda.Screens.createScreen;
import com.Appzia.addpanda.Screens.downloadImageActivity;

import com.Appzia.addpanda.Screens.editFameActivity;
import com.Appzia.addpanda.Screens.trendingActivity;
import com.google.android.material.bottomappbar.BottomAppBar;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.shape.CornerFamily;
import com.google.android.material.shape.MaterialShapeDrawable;

import com.Appzia.addpanda.Fragments.DownloadsFragment;
import com.Appzia.addpanda.Fragments.categoryTabFragment;
import com.Appzia.addpanda.Fragments.mainFragment;
import com.Appzia.addpanda.Fragments.profileFragment;
import com.Appzia.addpanda.databinding.ActivityMainBinding;

import java.util.Arrays;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    public static String account_typeKey;
    public static String mobileKey;
    public static String emailKey;
    public static String nameKey;
    public static String BusinessCatKey;
    public static String social_media_typeKey;
    public static String tokenKey;
    public static String TOKEN_SF;
    ActivityMainBinding binding;

    public static String editFrameData = null;


    @Override
    protected void onStart() {
        super.onStart();
        editFrameData = null;

        try {
            account_typeKey = getIntent().getStringExtra("account_typeKey");
            mobileKey = getIntent().getStringExtra("mobileKey");
            emailKey = getIntent().getStringExtra("emailKey");
            nameKey = getIntent().getStringExtra("nameKey");  // from all 4 webservice
            BusinessCatKey = getIntent().getStringExtra("BusinessCatKey");
            social_media_typeKey = getIntent().getStringExtra("social_media_typeKey");
            tokenKey = getIntent().getStringExtra("tokenKey");
            SharedPreferences sh = getSharedPreferences("MySharedPref", Context.MODE_PRIVATE);
            TOKEN_SF = sh.getString("TOKEN_SF", "");


        } catch (Exception ignored) {
        }
        try {
            editFrameData = getIntent().getStringExtra("editKey");

            if (editFrameData.equals("editHome")) {
                getSupportFragmentManager().beginTransaction().replace(R.id.mainActivityFrame, new mainFragment()).commit();
                editFrameData = null;
            } else if (editFrameData.equals("editProfile")) {

                getSupportFragmentManager().beginTransaction().replace(R.id.mainActivityFrame, new profileFragment()).commit();
                editFrameData = null;
            } else if (editFrameData.equals("editCategory")) {

                getSupportFragmentManager().beginTransaction().replace(R.id.mainActivityFrame, new categoryTabFragment()).commit();
                editFrameData = null;
            } else if (editFrameData.equals("editDownload")) {

                getSupportFragmentManager().beginTransaction().replace(R.id.mainActivityFrame, new DownloadsFragment()).commit();
                editFrameData = null;
            } else if (editFrameData.equals("editDownloadImage")) {

                SharedPreferences sharedPreferences = getSharedPreferences("MySharedPref", MODE_PRIVATE);
                SharedPreferences.Editor myEdit = sharedPreferences.edit();
                myEdit.putString("editKey", "editKey");
                myEdit.apply();


                byte[] byteArray = getIntent().getByteArrayExtra("imgData");


                Intent intent = new Intent(getApplicationContext(), downloadImageActivity.class);
                intent.putExtra("imgData", Arrays.toString(byteArray));
                startActivity(intent);
                editFrameData = null;

            } else if (editFrameData.equals("editDownloadImage2")) {

                SharedPreferences sharedPreferences = getSharedPreferences("MySharedPref", MODE_PRIVATE);
                SharedPreferences.Editor myEdit = sharedPreferences.edit();
                myEdit.putString("basicKey", "basicKey");
                myEdit.apply();

                byte[] byteArray = getIntent().getByteArrayExtra("imgData");
                Intent intent = new Intent(getApplicationContext(), downloadImageActivity.class);
                intent.putExtra("imgData", Arrays.toString(byteArray));
                startActivity(intent);
                editFrameData = null;

            } else if (editFrameData.equals("editFab")) {

                startActivity(new Intent(getApplicationContext(), createScreen.class));
                editFrameData = null;
            } else if (editFrameData.equals("editBack")) {

                startActivity(new Intent(getApplicationContext(), trendingActivity.class));
                editFrameData = null;
            } else if (editFrameData.equals("personalProfileKey")) {

                getSupportFragmentManager().beginTransaction().replace(R.id.mainActivityFrame, new profileFragment()).commit();
                editFrameData = null;
            } else if (editFrameData.equals("businessProfileKey")) {

                getSupportFragmentManager().beginTransaction().replace(R.id.mainActivityFrame, new profileFragment()).commit();
                editFrameData = null;
            } else {

                getSupportFragmentManager().beginTransaction().replace(R.id.mainActivityFrame, new mainFragment()).commit();
                editFrameData = null;
            }
        } catch (Exception e) {

            getSupportFragmentManager().beginTransaction().replace(R.id.mainActivityFrame, new mainFragment()).commit();
            editFrameData = null;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
         editFrameData=null;
    }

    @Override
    protected void onRestart() {
        super.onRestart();
         editFrameData=null;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.Theme_AddPanda);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


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


                        getSupportFragmentManager().beginTransaction().replace(R.id.mainActivityFrame, new mainFragment()).commit();

                        break;

                    case R.id.profile:

                        getSupportFragmentManager().beginTransaction().replace(R.id.mainActivityFrame, new profileFragment()).commit();


                        break;
                    case R.id.category:

                        getSupportFragmentManager().beginTransaction().replace(R.id.mainActivityFrame, new categoryTabFragment()).commit();


                        break;
                    case R.id.download:

                        getSupportFragmentManager().beginTransaction().replace(R.id.mainActivityFrame, new DownloadsFragment()).commit();


                        break;
                }
                return true;


            }
        });

        binding.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), createScreen.class));
            }
        });

    }

    @Override
    public void onBackPressed() {

        new AlertDialog.Builder(MainActivity.this).setTitle("Exit").setMessage("Are you sure want to exit ?")


                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        finishAffinity();
                        finish();


                    }

                })

                .setNegativeButton(android.R.string.no, null).setIcon(null).show();

    }

}