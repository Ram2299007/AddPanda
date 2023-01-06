package com.Appzia.addpanda.Screens;

import androidx.annotation.LongDef;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.loader.content.CursorLoader;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.ParcelFileDescriptor;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.provider.OpenableColumns;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.MimeTypeMap;
import android.widget.EditText;
import android.widget.Toast;

import com.Appzia.addpanda.MainActivity;
import com.Appzia.addpanda.R;
import com.Appzia.addpanda.Util.Constant.Constant;
import com.Appzia.addpanda.Util.FileUtils;
import com.Appzia.addpanda.Webservice.Webservice;
import com.Appzia.addpanda.databinding.ActivityContentcreatorPersonalJoinScreenBinding;
import com.google.android.material.bottomappbar.BottomAppBar;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.shape.CornerFamily;
import com.google.android.material.shape.MaterialShapeDrawable;
import com.google.android.material.snackbar.Snackbar;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileDescriptor;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Objects;

import cz.msebera.android.httpclient.entity.mime.MIME;

public class contentcreatorPersonalJoinScreen extends AppCompatActivity {

    ActivityContentcreatorPersonalJoinScreenBinding binding;
    private static final int FILE_SELECT_CODE = 0;
    InputStream InputFile;


    String partner_is = "1";
    public static File imageFile;

    public static int PICKFILE_RESULT_CODE = 10;

    public static EditText name2, mobie2, email2, totalexp, portfolio;
    public static AppCompatButton specail;

    String token;
    Context mContext;

    @Override
    public void onStart() {
        super.onStart();
        name2 = findViewById(R.id.name);
        mobie2 = findViewById(R.id.mobile);
        email2 = findViewById(R.id.email);
        totalexp = findViewById(R.id.exp);
        portfolio = findViewById(R.id.special);
        specail = findViewById(R.id.upload);

        SharedPreferences sh = getSharedPreferences("MySharedPref", Context.MODE_PRIVATE);

        token = sh.getString("TOKEN_SF", "");

        //checkl internert connectin
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
        binding = ActivityContentcreatorPersonalJoinScreenBinding.inflate(getLayoutInflater());
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

                if (binding.name.getText().toString().isEmpty() || binding.mobile.getText().toString().isEmpty() || binding.email.getText().toString().isEmpty() || binding.exp.getText().toString().isEmpty() || binding.special.getText().toString().isEmpty()) {
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
                        Webservice.add_partner_with_us_data(mContext, token, binding.name.getText().toString(), binding.mobile.getText().toString(), binding.email.getText().toString(), binding.exp.getText().toString(), imageFile, partner_is, binding.special.getText().toString());

                      //  Webservice.add_partner_with_us_data_IS(mContext, token, binding.name.getText().toString(), binding.mobile.getText().toString(), binding.email.getText().toString(), binding.exp.getText().toString(), InputFile, partner_is, binding.special.getText().toString());

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
                    }catch (Exception e){
                        Toast.makeText(mContext, e.getMessage(), Toast.LENGTH_SHORT).show();

                    }




                }
                break;
        }
        super.onActivityResult(requestCode, resultCode, data);
    }




}