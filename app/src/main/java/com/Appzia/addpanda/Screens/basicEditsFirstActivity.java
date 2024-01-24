package com.Appzia.addpanda.Screens;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.Appzia.addpanda.Adapter.categoryParentAdapter;
import com.Appzia.addpanda.Model.categoryChildModel;
import com.Appzia.addpanda.Model.categoryParentModel;
import com.Appzia.addpanda.Model.get_category_listChild1Model;
import com.Appzia.addpanda.R;
import com.Appzia.addpanda.Util.Constant.Constant;
import com.Appzia.addpanda.Webservice.WebserviceRetrofits.WebserviceRetrofit;
import com.Appzia.addpanda.databinding.ActivityBasicEditsFirstBinding;
import com.facebook.shimmer.ShimmerFrameLayout;
import com.google.android.material.bottomappbar.BottomAppBar;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.shape.CornerFamily;
import com.google.android.material.shape.MaterialShapeDrawable;

import java.util.ArrayList;
import java.util.Objects;

import pl.droidsonroids.gif.GifImageView;

public class basicEditsFirstActivity extends AppCompatActivity {

    ActivityBasicEditsFirstBinding binding;
    public static TextView trendingtextbasic;
    Context mContext;
    String token;
    String flag = "1";
    public static ImageView mainImageview;
    public static GifImageView progressBar;
    public static CoordinatorLayout CoLayout;
    public static ShimmerFrameLayout shimmerFrameLayout;


    public static com.Appzia.addpanda.Adapter.categoryParentAdapter categoryParentAdapter;
    //  public static trendingAdapter adapter2;

    public static TextView username;


    //declare array lists for categories

    public static ArrayList<categoryParentModel> parentModelArrayList;
    public static ArrayList<categoryChildModel> list1;
    public static ArrayList<get_category_listChild1Model> get_category_listChild1ModelList = new ArrayList<>();
    public static ArrayList<categoryChildModel> list2;
    public static ArrayList<categoryChildModel> list3;
    public static ArrayList<categoryChildModel> list4;
    public static ArrayList<categoryChildModel> list5;
    public static ArrayList<categoryChildModel> list6;
    public static ArrayList<categoryChildModel> list7;
    public static ArrayList<categoryChildModel> list8;
    public static ArrayList<categoryChildModel> list9;
    com.Appzia.addpanda.Adapter.mainCatBtnChildAdapter mainCatBtnChildAdapter;
    public static ArrayList<categoryChildModel> list10;
    public static ArrayList<categoryChildModel> list11;
    public static ArrayList<categoryChildModel> list12;
    public static ArrayList<categoryChildModel> list13;
    public static ArrayList<categoryChildModel> list14;
    public static ArrayList<categoryChildModel> list15;
    public static ArrayList<categoryChildModel> list16;
    public static ArrayList<categoryChildModel> list17;
    public static ArrayList<categoryChildModel> list18;
    public static ArrayList<categoryChildModel> list19;
    public static ArrayList<categoryChildModel> list20;
    public static ArrayList<categoryChildModel> list21;
    public static ArrayList<categoryChildModel> list22;
    public static ArrayList<categoryChildModel> list23;
    public static ArrayList<categoryChildModel> list24;
    public static ArrayList<categoryChildModel> list25;
    public static ArrayList<categoryChildModel> list26;
    public static ArrayList<categoryChildModel> list27;
    public static ArrayList<categoryChildModel> list28;
    public static ArrayList<categoryChildModel> list29;
    public static ArrayList<categoryChildModel> list30;
    public static ArrayList<categoryChildModel> list31;
    public static ArrayList<categoryChildModel> list32;
    public static ArrayList<categoryChildModel> list33;
    public static ArrayList<categoryChildModel> list34;
    public static ArrayList<categoryChildModel> list35;
    public static ArrayList<categoryChildModel> list36;
    public static ArrayList<categoryChildModel> list37;
    public static ArrayList<categoryChildModel> list38;
    public static ArrayList<categoryChildModel> list39;
    public static ArrayList<categoryChildModel> list40;
    public static ArrayList<categoryChildModel> list41;
    public static ArrayList<categoryChildModel> list42;
    public static ArrayList<categoryChildModel> list43;
    public static ArrayList<categoryChildModel> list44;
    public static ArrayList<categoryChildModel> list45;
    public static ArrayList<categoryChildModel> list46;
    public static ArrayList<categoryChildModel> list47;
    public static ArrayList<categoryChildModel> list48;
    public static ArrayList<categoryChildModel> list49;
    public static ArrayList<categoryChildModel> list50;

    @Override
    public void onStart() {
        super.onStart();
        parentModelArrayList.clear();
        list1.clear();


        username = (TextView) findViewById(R.id.username);
        progressBar = (GifImageView) findViewById(R.id.progressbar);

        CoLayout = (CoordinatorLayout) findViewById(R.id.CoLayout);

        shimmerFrameLayout = (ShimmerFrameLayout) findViewById(R.id.shimmerFrameLayout);


        SharedPreferences sh = getSharedPreferences("MySharedPref", Context.MODE_PRIVATE);

        token = sh.getString("TOKEN_SF", "");

        Constant.NetworkCheck(mContext);
        if ((Constant.wifiInfo != null && Constant.wifiInfo.isConnected()) || (Constant.mobileInfo != null && Constant.mobileInfo.isConnected())) {

            WebserviceRetrofit.get_category_listBasicEdit(mContext, token, basicEditsFirstActivity.this, flag);

        } else {
            Constant.NetworkCheckDialogue(mContext);
            Constant.dialogForNetwork.show();

            AppCompatButton btn = Constant.dialogForNetwork.findViewById(R.id.retry);

            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Constant.dialogForNetwork.dismiss();

                }
            });


        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityBasicEditsFirstBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        mContext = basicEditsFirstActivity.this;


        //By default on each activity android studio
        Objects.requireNonNull(getSupportActionBar()).hide();
        Window window = this.getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.setStatusBarColor(this.getResources().getColor(R.color.appThemeColor));

        mContext = binding.getRoot().getContext();

        parentModelArrayList = new ArrayList<>();
        list1 = new ArrayList<>();
        list2 = new ArrayList<>();
        list3 = new ArrayList<>();
        list4 = new ArrayList<>();
        list5 = new ArrayList<>();
        list6 = new ArrayList<>();
        list7 = new ArrayList<>();
        list8 = new ArrayList<>();
        list9 = new ArrayList<>();
        list10 = new ArrayList<>();
        list11 = new ArrayList<>();
        list12 = new ArrayList<>();
        list13 = new ArrayList<>();
        list14 = new ArrayList<>();
        list15 = new ArrayList<>();
        list16 = new ArrayList<>();
        list17 = new ArrayList<>();
        list18 = new ArrayList<>();
        list19 = new ArrayList<>();
        list20 = new ArrayList<>();
        list21 = new ArrayList<>();
        list22 = new ArrayList<>();
        list23 = new ArrayList<>();
        list24 = new ArrayList<>();
        list25 = new ArrayList<>();
        list26 = new ArrayList<>();
        list27 = new ArrayList<>();
        list28 = new ArrayList<>();
        list29 = new ArrayList<>();
        list30 = new ArrayList<>();
        list31 = new ArrayList<>();
        list32 = new ArrayList<>();
        list33 = new ArrayList<>();
        list34 = new ArrayList<>();
        list35 = new ArrayList<>();
        list36 = new ArrayList<>();
        list37 = new ArrayList<>();
        list38 = new ArrayList<>();
        list39 = new ArrayList<>();
        list40 = new ArrayList<>();
        list41 = new ArrayList<>();
        list42 = new ArrayList<>();
        list43 = new ArrayList<>();
        list44 = new ArrayList<>();
        list45 = new ArrayList<>();
        list46 = new ArrayList<>();
        list47 = new ArrayList<>();
        list48 = new ArrayList<>();
        list49 = new ArrayList<>();
        list50 = new ArrayList<>();

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
    binding.video.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext, "Coming soon....", Toast.LENGTH_SHORT).show();
            }
        });

        binding.noti.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), NotificationActivity.class));
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

        binding.swipeContainer.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                RefreshAndFetchData();
            }
        });

    }

    public void setAapterCategory(ArrayList<get_category_listChild1Model> get_category_listChild1ModelList) {
        this.get_category_listChild1ModelList = get_category_listChild1ModelList;
        categoryParentAdapter = new categoryParentAdapter(mContext, this.get_category_listChild1ModelList, Constant.basicKey, binding.relativelayout);
        binding.trendingRecyclerview.setAdapter(categoryParentAdapter);
        binding.trendingRecyclerview.setHasFixedSize(true);
        binding.trendingRecyclerview.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false));
        categoryParentAdapter.notifyDataSetChanged();



    }

    public void RefreshAndFetchData() {

        Intent intent = new Intent(mContext, basicEditsFirstActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        mContext.startActivity(intent);
        Constant.vibrator(mContext);
        binding.swipeContainer.setRefreshing(false);
    }
}



