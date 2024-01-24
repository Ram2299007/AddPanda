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
import com.Appzia.addpanda.Webservice.Webservice;
import com.Appzia.addpanda.databinding.ActivityAddBankScreenBinding;
import com.google.android.material.bottomappbar.BottomAppBar;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.shape.CornerFamily;
import com.google.android.material.shape.MaterialShapeDrawable;

import java.util.Objects;

public class addBankScreen extends AppCompatActivity {

    ActivityAddBankScreenBinding binding;
    Context mContext;
    String token;
    String account_holder_name;
    String account_number;
    String ifsc_code;

    @Override
    protected void onStart() {
        super.onStart();
       // Toast.makeText(mContext, Constant.getSF.getString("ACC_DONE", ""), Toast.LENGTH_SHORT).show();

        if (Constant.getSF.getString("ACC_DONE", "").equals("ACC_DONE")) {
            startActivity(new Intent(mContext, manageAcount3Activity.class));
        } else if (Constant.getSF.getString("ACC_DONE", "").equals("ACC_UPDATE")) {

            binding.submit.setText("Update");
            binding.submit.setBackgroundTintList(null);
            binding.submit.setEnabled(true);
            binding.completekyc.setVisibility(View.INVISIBLE);


        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAddBankScreenBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        mContext = binding.getRoot().getContext();

        //By default on each activity android studio
        Objects.requireNonNull(getSupportActionBar()).hide();
        Window window = this.getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.setStatusBarColor(this.getResources().getColor(R.color.appThemeColor));
        binding.bottomNavigationView.setBackground(null);


        Constant.getSfFuncion(mContext);
        token = Constant.getSF.getString(Constant.TOKEN_SF, "");

        account_holder_name = getIntent().getStringExtra("account_holder_name");
        account_number = getIntent().getStringExtra("account_number");
        ifsc_code = getIntent().getStringExtra("ifsc_code");

        if (account_holder_name != null) {

            binding.name.setText(account_holder_name);
        }
        if (account_number != null) {
            binding.number.setText(account_number);
        }
        if (ifsc_code != null) {
            binding.ifsc.setText(ifsc_code);

        }
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

        binding.backArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        binding.completekyc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (binding.name.getText().toString().isEmpty()) {
                    binding.name.setError("Missing name");
                } else if (binding.number.getText().toString().isEmpty()) {
                    binding.number.setError("Missing account number");
                } else if (binding.cnumber.getText().toString().isEmpty()) {
                    binding.cnumber.setError("Missing confirm account  number");
                } else if (binding.ifsc.getText().toString().isEmpty()) {
                    binding.ifsc.setError("Missing IFSC code");
                } else if (!binding.number.getText().toString().equals(binding.cnumber.getText().toString())) {
                    Toast.makeText(addBankScreen.this, "Account number doesn't match", Toast.LENGTH_SHORT).show();
                } else {
                    Webservice.save_bank_details(mContext, binding.name.getText().toString(), binding.number.getText().toString(), binding.ifsc.getText().toString(), token);
                }
            }
        });

        binding.submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (binding.name.getText().toString().isEmpty()) {
                    binding.name.setError("Missing name");
                } else if (binding.number.getText().toString().isEmpty()) {
                    binding.number.setError("Missing account number");
                } else if (binding.cnumber.getText().toString().isEmpty()) {
                    binding.cnumber.setError("Missing confirm account  number");
                } else if (binding.ifsc.getText().toString().isEmpty()) {
                    binding.ifsc.setError("Missing IFSC code");
                } else if (!binding.number.getText().toString().equals(binding.cnumber.getText().toString())) {
                    Toast.makeText(addBankScreen.this, "Account number doesn't match", Toast.LENGTH_SHORT).show();
                } else {
                    Webservice.save_bank_details(mContext, binding.name.getText().toString(), binding.number.getText().toString(), binding.ifsc.getText().toString(), token);
                }
            }
        });
    }
}