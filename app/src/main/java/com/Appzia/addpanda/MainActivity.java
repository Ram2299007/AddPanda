package com.Appzia.addpanda;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.Appzia.addpanda.Fragments.downloadimageFragment;
import com.Appzia.addpanda.Fragments.trendingFragment;
import com.google.android.material.bottomappbar.BottomAppBar;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.shape.CornerFamily;
import com.google.android.material.shape.MaterialShapeDrawable;

import com.Appzia.addpanda.Fragments.DownloadsFragment;
import com.Appzia.addpanda.Fragments.categoryTabFragment;
import com.Appzia.addpanda.Fragments.createFragment;
import com.Appzia.addpanda.Fragments.mainFragment;
import com.Appzia.addpanda.Fragments.profileFragment;
import com.Appzia.addpanda.databinding.ActivityMainBinding;

import java.util.Arrays;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;

    String editFrameData;


    @Override
    protected void onStart() {
        super.onStart();


        try {
            editFrameData = getIntent().getStringExtra("editKey");

            if (editFrameData.equals("editHome")) {
                getSupportFragmentManager().beginTransaction().replace(R.id.mainActivityFrame, new mainFragment()).commit();
            } else if (editFrameData.equals("editProfile")) {

                getSupportFragmentManager().beginTransaction().replace(R.id.mainActivityFrame, new profileFragment()).commit();
            } else if (editFrameData.equals("editCategory")) {

                getSupportFragmentManager().beginTransaction().replace(R.id.mainActivityFrame, new categoryTabFragment()).commit();
            } else if (editFrameData.equals("editDownload")) {

                getSupportFragmentManager().beginTransaction().replace(R.id.mainActivityFrame, new DownloadsFragment()).commit();
            } else if (editFrameData.equals("editDownloadImage")) {

                SharedPreferences sharedPreferences = getSharedPreferences("MySharedPref", MODE_PRIVATE);
                SharedPreferences.Editor myEdit = sharedPreferences.edit();
                myEdit.putString("editKey", "editKey");
                myEdit.apply();


                byte[] byteArray = getIntent().getByteArrayExtra("imgData");
                Bundle bundle = new Bundle();
                bundle.putString("imgData", Arrays.toString(byteArray));
                downloadimageFragment fragobj = new downloadimageFragment();
                fragobj.setArguments(bundle);
                getSupportFragmentManager().beginTransaction().replace(R.id.mainActivityFrame, new downloadimageFragment()).commit();


            } else if (editFrameData.equals("editDownloadImage2")) {

                SharedPreferences sharedPreferences = getSharedPreferences("MySharedPref", MODE_PRIVATE);
                SharedPreferences.Editor myEdit = sharedPreferences.edit();
                myEdit.putString("basicKey", "basicKey");
                myEdit.apply();

                byte[] byteArray = getIntent().getByteArrayExtra("imgData");
                Bundle bundle = new Bundle();
                bundle.putString("imgData", Arrays.toString(byteArray));

                downloadimageFragment fragobj = new downloadimageFragment();
                fragobj.setArguments(bundle);
                getSupportFragmentManager().beginTransaction().replace(R.id.mainActivityFrame, new downloadimageFragment()).commit();


            } else if (editFrameData.equals("editFab")) {

                getSupportFragmentManager().beginTransaction().replace(R.id.mainActivityFrame, new createFragment()).commit();
            } else if (editFrameData.equals("editBack")) {

                getSupportFragmentManager().beginTransaction().replace(R.id.mainActivityFrame, new trendingFragment()).commit();
            } else {

                getSupportFragmentManager().beginTransaction().replace(R.id.mainActivityFrame, new mainFragment()).commit();
            }
        } catch (Exception e) {

            getSupportFragmentManager().beginTransaction().replace(R.id.mainActivityFrame, new mainFragment()).commit();
        }


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
                getSupportFragmentManager().beginTransaction().replace(R.id.mainActivityFrame, new createFragment()).commit();
            }
        });

    }
}