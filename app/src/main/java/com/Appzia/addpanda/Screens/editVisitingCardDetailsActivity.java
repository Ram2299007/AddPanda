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

import com.Appzia.addpanda.MainActivity;
import com.Appzia.addpanda.R;
import com.Appzia.addpanda.Util.Constant.Constant;
import com.Appzia.addpanda.databinding.ActivityEditVisitingCardDetailsBinding;
import com.google.android.material.bottomappbar.BottomAppBar;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.shape.CornerFamily;
import com.google.android.material.shape.MaterialShapeDrawable;
import com.google.android.material.snackbar.Snackbar;

import java.util.Objects;

public class editVisitingCardDetailsActivity extends AppCompatActivity {

    ActivityEditVisitingCardDetailsBinding binding;
    Context mContext;

    String buainessname, yourname, email, web, address, mobile, whatsappl;

    @Override
    public void onStart() {
        super.onStart();

        try {



            Constant.getSfFuncion(mContext);
            buainessname = Constant.getSF.getString("BusnameKey", "");
            yourname = Constant.getSF.getString("yournameKey", "");
            email = Constant.getSF.getString("emailKey", "");
            web = Constant.getSF.getString("webKey", "");
            address = Constant.getSF.getString("addressKey", "");
            mobile = Constant.getSF.getString("mobileKey", "");
            whatsappl = Constant.getSF.getString("whatsappKey", "");

            binding.Busname.setText(buainessname);
            binding.yourname.setText(yourname);
            binding.email.setText(email);
            binding.web.setText(web);
            binding.address.setText(address);
            binding.mobile.setText(mobile);
            //   binding.whatsapp.setText(whatsappl);


        } catch (Exception ignored) {
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= ActivityEditVisitingCardDetailsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        mContext = binding.getRoot().getContext();
        // Inflate the layout for this fragment

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



        binding.caption.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (binding.Busname.getText().toString().isEmpty() || binding.yourname.getText().toString().isEmpty() || binding.email.getText().toString().isEmpty() || binding.web.getText().toString().isEmpty() || binding.address.getText().toString().isEmpty() || binding.mobile.getText().toString().isEmpty() ||  binding.whatsapp.getText().toString().isEmpty()) {
                    Snackbar.make(binding.getRoot(), "Missing Information ?", Snackbar.LENGTH_LONG)
                            .setAction("CLOSE", new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {

                                }
                            })
                            .setActionTextColor(getResources().getColor(android.R.color.holo_red_light))
                            .show();
                } else {

                    Constant.setSfFunction(mContext);
                    Constant.setSF.putString("BusnameKey", binding.Busname.getText().toString());
                    Constant.setSF.putString("yournameKey", binding.yourname.getText().toString());
                    Constant.setSF.putString("emailKey", binding.email.getText().toString());
                    Constant.setSF.putString("webKey", binding.web.getText().toString());
                    Constant.setSF.putString("addressKey", binding.address.getText().toString());
                    Constant.setSF.putString("mobileKey", binding.mobile.getText().toString());
                    Constant.setSF.putString("whatsappKey", binding.whatsapp.getText().toString());
                    Constant.setSF.apply();

                    startActivity(new Intent(getApplicationContext(), addCaptionScreen.class));
                }
            }
        });


        binding.backArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });


        binding.checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (binding.checkBox.isChecked()) {
                    binding.whatsapp.setText(binding.mobile.getText().toString());
                } else if (!binding.checkBox.isChecked()) {
                    binding.whatsapp.setText("");
                }
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}