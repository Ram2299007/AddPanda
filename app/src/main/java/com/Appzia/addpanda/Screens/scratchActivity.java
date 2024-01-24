package com.Appzia.addpanda.Screens;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.coordinatorlayout.widget.CoordinatorLayout;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.Appzia.addpanda.R;
import com.Appzia.addpanda.databinding.ActivityScratchBinding;
import com.facebook.shimmer.ShimmerFrameLayout;
import com.google.android.material.bottomappbar.BottomAppBar;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.shape.CornerFamily;
import com.google.android.material.shape.MaterialShapeDrawable;

import java.util.Objects;

import pl.droidsonroids.gif.GifImageView;

public class scratchActivity extends AppCompatActivity {

    ActivityScratchBinding binding;
    public static TextView trendingtextbasic;
    Context mContext;
    String token;
    String flag = "2";
    public static ImageView mainImageview;
    public static GifImageView progressBar;
    public static CoordinatorLayout CoLayout;
    public static ShimmerFrameLayout shimmerFrameLayout;


    public static com.Appzia.addpanda.Adapter.categoryParentAdapter categoryParentAdapter;
    //  public static trendingAdapter adapter2;

    public static TextView username;

      int height=0;
      int width=0;


    //declare array lists for categories


    @Override
    public void onStart() {
        super.onStart();


        username = (TextView) findViewById(R.id.username);
        progressBar = (GifImageView) findViewById(R.id.progressbar);
        CoLayout = (CoordinatorLayout) findViewById(R.id.CoLayout);

        shimmerFrameLayout = (ShimmerFrameLayout) findViewById(R.id.shimmerFrameLayout);
        SharedPreferences sh = getSharedPreferences("MySharedPref", Context.MODE_PRIVATE);

        token = sh.getString("TOKEN_SF", "");


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityScratchBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        mContext = scratchActivity.this;


        //By default on each activity android studio
        Objects.requireNonNull(getSupportActionBar()).hide();
        Window window = this.getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.setStatusBarColor(this.getResources().getColor(R.color.appThemeColor));

        mContext = binding.getRoot().getContext();


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

        binding.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), MainActivity.class);
                i.putExtra("editKey", "editFab");
                startActivity(i);
            }
        });
        binding.backArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), MainActivity.class);
                i.putExtra("editKey", "editFab");
                startActivity(i);
            }
        });
        binding.noti.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), NotificationActivity.class));

            }
        });

        binding.spinnerid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.spinner.performClick();
            }
        });


        binding.spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // Toast.makeText(mContext, binding.spinner.getSelectedItem().toString(), Toast.LENGTH_SHORT).show();

                if (binding.spinner.getSelectedItem().equals("Default Size")) {
                    final float scale = getApplicationContext().getResources().getDisplayMetrics().density;

                    height = (int) (305 * scale + 0.5f);
                    width = 1080;
                    ViewGroup.LayoutParams params = binding.viewPagerBackImage.getLayoutParams();
                    params.height = height;
                    params.width = width;
                    binding.viewPagerBackImage.setLayoutParams(params);
                    binding.size.setText(binding.spinner.getSelectedItem().toString());
                } else if (binding.spinner.getSelectedItem().equals("1000 * 1000 (px)")) {

                    height = 1000;
                    width = 1000;
                    ViewGroup.LayoutParams params = binding.viewPagerBackImage.getLayoutParams();
                    params.height = height;
                    params.width = width;
                    binding.viewPagerBackImage.setLayoutParams(params);
                    binding.size.setText(binding.spinner.getSelectedItem().toString());


                } else if (binding.spinner.getSelectedItem().equals("905 * 1280 (px)")) {


                    height = 1280;
                    width = 905;
                    ViewGroup.LayoutParams params = binding.viewPagerBackImage.getLayoutParams();
                    params.height = height;
                    params.width = width;
                    binding.viewPagerBackImage.setLayoutParams(params);
                    binding.size.setText(binding.spinner.getSelectedItem().toString());
                } else if (binding.spinner.getSelectedItem().equals("700 * 1236 (px)")) {


                    height = 1236;
                    width = 700;
                    ViewGroup.LayoutParams params = binding.viewPagerBackImage.getLayoutParams();
                    params.height = height;
                    params.width = width;
                    binding.viewPagerBackImage.setLayoutParams(params);
                    binding.size.setText(binding.spinner.getSelectedItem().toString());
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        binding.done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),editFameActivity.class);
                intent.putExtra("widthKey",String.valueOf(width));
                intent.putExtra("heightKey",String.valueOf(height));
                startActivity(intent);
            }
        });

    }

}