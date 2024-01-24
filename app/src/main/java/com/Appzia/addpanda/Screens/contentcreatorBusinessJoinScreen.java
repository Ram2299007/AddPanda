package com.Appzia.addpanda.Screens;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.MimeTypeMap;
import android.widget.EditText;
import android.widget.Toast;

import com.Appzia.addpanda.R;
import com.Appzia.addpanda.Util.Constant.Constant;
import com.Appzia.addpanda.Webservice.Webservice;
import com.Appzia.addpanda.databinding.ActivityContentcreatorBusinessJoinScreenBinding;
import com.google.android.material.bottomappbar.BottomAppBar;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.shape.CornerFamily;
import com.google.android.material.shape.MaterialShapeDrawable;
import com.google.android.material.snackbar.Snackbar;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.Objects;

public class contentcreatorBusinessJoinScreen extends AppCompatActivity {

    ActivityContentcreatorBusinessJoinScreenBinding binding;

    String token;
    public static File imageFile;
    private static final int FILE_SELECT_CODE = 0;
    String partner_is = "2";
    InputStream InputFile;
    Context mContext;
    public static EditText name2, mobie2, email2, totalexp, portfolio, specail;

    @Override
    public void onStart() {
        super.onStart();
        name2 = findViewById(R.id.name);
        mobie2 = findViewById(R.id.mobile);
        email2 = findViewById(R.id.email);
        totalexp = findViewById(R.id.exp);

        SharedPreferences sh = getSharedPreferences("MySharedPref", Context.MODE_PRIVATE);

        token = sh.getString("TOKEN_SF", "");


        Constant.NetworkCheck(mContext);
        if ((Constant.wifiInfo != null && Constant.wifiInfo.isConnected()) || (Constant.mobileInfo != null && Constant.mobileInfo.isConnected())) {

            Webservice.fetchDataProfileforpartnerwithus(mContext, token, partner_is);
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
        binding = ActivityContentcreatorBusinessJoinScreenBinding.inflate(getLayoutInflater());
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

        binding.upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
                intent.setType("*/*");
                intent.addCategory(Intent.CATEGORY_OPENABLE);

                try {
                    startActivityForResult(
                            Intent.createChooser(intent, "Select a File to Upload"),
                            FILE_SELECT_CODE);
                } catch (android.content.ActivityNotFoundException ex) {
                    // Potentially direct the user to the Market with a Dialog
                    Toast.makeText(getApplicationContext(), "Please install a File Manager.", Toast.LENGTH_SHORT).show();
                }
//
//                Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT,
//                        MediaStore.Files.getContentUri("external"));
//                startActivityForResult(Intent.createChooser(intent, "select File"),
//                        FILE_SELECT_CODE);
            }
        });
        binding.personal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (binding.name.getText().toString().isEmpty() || binding.mobile.getText().toString().isEmpty() || binding.email.getText().toString().isEmpty() || binding.exp.getText().toString().isEmpty() || binding.specialise.getText().toString().isEmpty() || binding.upload.getText().toString().isEmpty()) {
                    Snackbar.make(binding.getRoot(), "Missing Information ?", Snackbar.LENGTH_LONG)
                            .setAction("CLOSE", new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {

                                }
                            })
                            .setActionTextColor(getResources().getColor(android.R.color.holo_red_light))
                            .show();
                } else {

                    Constant.NetworkCheck(mContext);
                    if ((Constant.wifiInfo != null && Constant.wifiInfo.isConnected()) || (Constant.mobileInfo != null && Constant.mobileInfo.isConnected())) {
                        Webservice.add_partner_with_us_data(mContext, token, binding.name.getText().toString(), binding.mobile.getText().toString(), binding.email.getText().toString(), binding.exp.getText().toString(), imageFile, partner_is, binding.specialise.getText().toString());

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
            }
        });

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {

            //very important for uplopading multiple data

            case FILE_SELECT_CODE:
                if (resultCode == RESULT_OK) {
                    // Get the Uri of the selected file
                    Uri uri = data.getData();
                    InputFile = null;
                    Log.d("ImageFile000", uri.getAuthority());
                    Log.d("ImageFile000", uri.getScheme());

                    String extension;
                    File f ;
                    if(uri.getScheme().equals(ContentResolver.SCHEME_CONTENT)){
                        final MimeTypeMap mimeTypeMap = MimeTypeMap.getSingleton();
                        extension = mimeTypeMap.getExtensionFromMimeType(getContentResolver().getType(uri));

                    }else{
                        extension = MimeTypeMap.getFileExtensionFromUrl(String.valueOf(Uri.fromFile(new File(uri.getPath()))));

                    }
                    Log.d("extension", extension);
                    f = new File(getCacheDir()+"/temp."+extension);
                    try{
                        InputStream is = getContentResolver().openInputStream(uri);
                        FileOutputStream fs = new FileOutputStream(f);
                        int read = 0;
                        int bufferSize = 1024;
                        final byte[] buffers = new byte[bufferSize];
                        while ((read=is.read(buffers))!=-1){
                            fs.write(buffers,0,read);
                        }
                        is.close();
                        fs.close();

                        Log.d("imageFile111", f.getPath());
                        imageFile=f;
                        binding.upload.setText(uri.getPath());
                    }catch (Exception e){
                        Toast.makeText(mContext, e.getMessage(), Toast.LENGTH_SHORT).show();

                    }




                }
                break;
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

}