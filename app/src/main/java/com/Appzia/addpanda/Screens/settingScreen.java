package com.Appzia.addpanda.Screens;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.core.content.FileProvider;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.Appzia.addpanda.R;
import com.Appzia.addpanda.Util.Constant.Constant;
import com.Appzia.addpanda.Util.InAppReview;
import com.Appzia.addpanda.Webservice.Webservice;
import com.Appzia.addpanda.databinding.ActivitySettingScreenBinding;
import com.google.android.material.bottomappbar.BottomAppBar;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.shape.CornerFamily;
import com.google.android.material.shape.MaterialShapeDrawable;

import java.util.Objects;

public class settingScreen extends AppCompatActivity {

    ActivitySettingScreenBinding binding;

    String name, profile;
    String token;
    Context mContext;
    public static TextView userNameId;
    public static TextView subId;

    public static ImageView profileId;
    AppCompatActivity activity;
    private InAppReview inAppReview;
    @Override
    public void onStart() {
        super.onStart();
        SharedPreferences sh = getSharedPreferences("MySharedPref", Context.MODE_PRIVATE);
        token = sh.getString("TOKEN_SF", "");

        try {
            userNameId = findViewById(R.id.userNameIdSetting);
            subId = findViewById(R.id.subidsetting);
            profileId = findViewById(R.id.profileId);

            Constant.NetworkCheck(mContext);
            if ((Constant.wifiInfo != null && Constant.wifiInfo.isConnected()) || (Constant.mobileInfo != null && Constant.mobileInfo.isConnected())) {
                Webservice.fetch_user_profile_SETDATA(mContext, token);

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

        } catch (Exception ignored) {
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySettingScreenBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        mContext = binding.getRoot().getContext();
        activity = settingScreen.this;


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


        binding.preferedlang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext, "Coming soon....", Toast.LENGTH_SHORT).show();
            }
        });
        binding.shareapp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext, "Coming soon....", Toast.LENGTH_SHORT).show();
            }
        });
        binding.rateus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inAppReview = new InAppReview();
                inAppReview.askUserForReview(mContext);

            }
        });
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

        binding.notification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), NotificationActivity.class));
            }
        });
        binding.hs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), helpAndSupportScreen.class));
            }
        });
        binding.backArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                intent.putExtra("editKey", "editProfile");
                startActivity(intent);
            }
        });


        binding.aboutus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                assert getFragmentManager() != null;
                startActivity(new Intent(getApplicationContext(), aboutUsScreen.class));
            }
        });

        binding.faq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), faqsScreen.class));
            }
        });

        binding.privacypolicy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), privacyPolicyScreen.class));
            }
        });
        binding.tc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), termAndConditionScreen.class));
            }
        });

        binding.shareapp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent shareIntent = new Intent(Intent.ACTION_SEND);
                shareIntent.setType("text/plain");
                shareIntent.putExtra(Intent.EXTRA_TEXT, "Hey Guys, I'd like to Refer you to Adpanda.in the perfect way to promote your brand and business to Impress your targeted audience by creating an Engaging and Creative Promotional Content.\n" +
                        "\n" +
                        "Click here https://play.google.com/store/apps/details?id=com.Appzia.addpanda&pcampaignid=web_share");

                // Use Chooser to show a list of available sharing apps
                Intent chooserIntent = Intent.createChooser(shareIntent, "Share URL with");
                if (shareIntent.resolveActivity(getPackageManager()) != null) {
                    startActivity(chooserIntent);
                }

            }
        });
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        intent.putExtra("editKey", "editProfile");
        startActivity(intent);
    }


//    private void getReviewInfo() {
//        reviewManager = ReviewManagerFactory.create(getApplicationContext());
//        Task<ReviewInfo> manager = reviewManager.requestReviewFlow();
//        manager.addOnCompleteListener(task -> {
//            if (task.isSuccessful()) {
//                reviewInfo = task.getResult();
//                startReviewFlow();
//            } else {
//                Toast.makeText(getApplicationContext(), "In App ReviewFlow failed to start", Toast.LENGTH_LONG).show();
//            }
//        });
//    }
//
//    public void startReviewFlow() {
//        if (reviewInfo != null) {
//            Task<Void> flow = reviewManager.launchReviewFlow(this, reviewInfo);
//            flow.addOnCompleteListener(new OnCompleteListener<Void>() {
//                @Override
//                public void onComplete(Task<Void> task) {
//                    Toast.makeText(getApplicationContext(), "In App Rating completed", Toast.LENGTH_LONG).show();
//                }
//            });
//        }
//        else {
//            Toast.makeText(getApplicationContext(), "In App Rating failed", Toast.LENGTH_LONG).show();
//        }
//    }
}