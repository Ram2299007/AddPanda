package com.Appzia.addpanda.Screens;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.PagerSnapHelper;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.Appzia.addpanda.Adapter.subscriptionAapter;
import com.Appzia.addpanda.Model.get_subscription_listChildModel;
import com.Appzia.addpanda.R;
import com.Appzia.addpanda.Util.Constant.Constant;
import com.Appzia.addpanda.Webservice.WebserviceRetrofits.WebserviceRetrofit;
import com.Appzia.addpanda.databinding.ActivitySubscriptionBinding;
import com.google.android.material.bottomappbar.BottomAppBar;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.shape.CornerFamily;
import com.google.android.material.shape.MaterialShapeDrawable;

import java.util.ArrayList;
import java.util.Objects;

public class subscriptionActivity extends AppCompatActivity {

    ActivitySubscriptionBinding binding;
    static Context mContext;
    String TAG = "Adpanda";
    subscriptionAapter adapter;
    String token;
    LinearLayoutManager layoutManager;
    private int currentTopPosition = 0;
    private int nextPosition = 1;
    private int previousPosition = -1;
    AppCompatActivity activity;

    @Override
    protected void onResume() {
        super.onResume();
        binding.progressbar.setVisibility(View.GONE);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySubscriptionBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        activity = subscriptionActivity.this;
        mContext = binding.getRoot().getContext();
        Objects.requireNonNull(getSupportActionBar()).hide();
        Window window = this.getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.setStatusBarColor(this.getResources().getColor(R.color.appThemeColor));




        Constant.getSfFuncion(mContext);
        token = Constant.getSF.getString(Constant.TOKEN_SF, "");
        WebserviceRetrofit.get_subscription_list(mContext, subscriptionActivity.this,token,binding.progressbar);

        // for next row
        PagerSnapHelper snapHelper = new PagerSnapHelper();
        snapHelper.attachToRecyclerView(binding.recyclerview);







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

                Intent i = new Intent(getApplicationContext(), createScreen.class);
                startActivity(i);


            }
        });

        //    binding.strike.setPaintFlags(Paint.STRIKE_THRU_TEXT_FLAG);


        binding.backArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                intent.putExtra("editKey", "editProfile");
                startActivity(intent);

            }
        });

        binding.notification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), NotificationActivity.class));
            }
        });


//        binding.buyNow.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                WebserviceRetrofit.phonepay_payment_init(mContext, "4", binding.amount.getText().toString(), binding.webview, binding.lyt, binding.btlyt, binding.progressbar);
//            }
//        });


    }


    @Override
    public void onBackPressed() {

//        if (binding.webview.getVisibility() == View.VISIBLE) {
////
//
//        } else if (binding.webview.getVisibility() == View.GONE) {
//            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
//            intent.putExtra("editKey", "editProfile");
//            startActivity(intent);
//        }

        super.onBackPressed();
    }


    public void setAdapter(ArrayList<get_subscription_listChildModel> get_subscription_list, int postion) {
        adapter = new subscriptionAapter(mContext,binding.webview,binding.lyt,binding.btlyt,binding.progressbar,binding.recyclerview,activity,get_subscription_list,subscriptionActivity.this,postion);
        layoutManager = new LinearLayoutManager(mContext,LinearLayoutManager.HORIZONTAL,false);
        binding.recyclerview.setLayoutManager(layoutManager);
        binding.recyclerview.setAdapter(adapter);
        adapter.notifyDataSetChanged();


    }




}