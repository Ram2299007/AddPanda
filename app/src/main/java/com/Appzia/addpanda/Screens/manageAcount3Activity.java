package com.Appzia.addpanda.Screens;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.style.UnderlineSpan;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.Appzia.addpanda.R;
import com.Appzia.addpanda.Util.Constant.Constant;
import com.Appzia.addpanda.Webservice.Webservice;
import com.Appzia.addpanda.databinding.ActivityManageAcount3Binding;
import com.google.android.material.bottomappbar.BottomAppBar;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.shape.CornerFamily;
import com.google.android.material.shape.MaterialShapeDrawable;

import java.util.Objects;

public class manageAcount3Activity extends AppCompatActivity {

    ActivityManageAcount3Binding binding;
    Context mContext;


    @Override
    protected void onStart() {
        super.onStart();


        Webservice.get_bank_details(mContext, binding.name, binding.accNumber, binding.ifsc, Constant.getSF.getString(Constant.TOKEN_SF, ""));

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityManageAcount3Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        SpannableString content = new SpannableString("View all Transactions >");
        content.setSpan(new UnderlineSpan(), 0, content.length(), 0);
        binding.view.setText(content);
        mContext = binding.getRoot().getContext();
        Constant.getSfFuncion(mContext);

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
        binding.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(getApplicationContext(), createScreen.class);
                startActivity(i);


            }
        });

        binding.pencil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Constant.setSfFunction(mContext);
                Constant.setSF.putString("ACC_DONE", "ACC_UPDATE");
                Constant.setSF.apply();
                Intent intent = new Intent(mContext, addBankScreen.class);
                intent.putExtra("account_holder_name", binding.name.getText().toString());
                intent.putExtra("account_number", binding.accNumber.getText().toString());
                intent.putExtra("ifsc_code", binding.ifsc.getText().toString());
                startActivity(intent);

            }
        });
        binding.backArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(getApplicationContext(), addBankScreen.class));
            }
        });

        binding.completekyc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), kycVerificationScreen.class));
            }
        });
    }
}