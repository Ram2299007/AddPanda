package com.Appzia.addpanda.Screens;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.MimeTypeMap;
import android.widget.Toast;

import com.Appzia.addpanda.R;
import com.Appzia.addpanda.Util.Constant.Constant;
import com.Appzia.addpanda.Webservice.Webservice;
import com.Appzia.addpanda.databinding.ActivityCheckVerificationScreenBinding;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.Objects;

public class checkVerificationScreen extends AppCompatActivity {

    ActivityCheckVerificationScreenBinding binding;
    String identityType = "3";
    Context mContext;
    File file, file2;
    String checkOnActivityResukt = "";
    Uri selectedImageUri;
    String token;
    public static int FILE_SELECT_CODE1 = 1;

    @Override
    protected void onResume() {
        super.onResume();
        token = Constant.getSF.getString(Constant.TOKEN_SF, "");

        if (checkOnActivityResukt.equals("")) {
            binding.img1.setImageURI(selectedImageUri);
            Webservice.get_identity_verificationCheck(mContext, token, binding.img1);
        } else if (checkOnActivityResukt.equals("ok")) {
            binding.img1.setImageURI(selectedImageUri);
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCheckVerificationScreenBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        mContext = binding.getRoot().getContext();
        //By default on each activity android studio
        Objects.requireNonNull(getSupportActionBar()).hide();
        Window window = this.getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.setStatusBarColor(this.getResources().getColor(R.color.appThemeColor));
        binding.backArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        binding.submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Webservice.identity_verification(mContext, identityType, file, file2, token);
            }
        });


        binding.img1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
                intent.setType("image/*");
                intent.addCategory(Intent.CATEGORY_OPENABLE);

                try {
                    startActivityForResult(
                            Intent.createChooser(intent, "Select a File to Upload"),
                            FILE_SELECT_CODE1);
                } catch (android.content.ActivityNotFoundException ex) {
                    // Potentially direct the user to the Market with a Dialog
                    Toast.makeText(getApplicationContext(), "Please install a File Manager.", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {


        //very important for uplopading multiple data


        if (resultCode == RESULT_OK && requestCode == FILE_SELECT_CODE1) {
            // Get the Uri of the selected file
            selectedImageUri = data.getData();
            checkOnActivityResukt = "ok";
            file = null;
            Log.d("ImageFile000", selectedImageUri.getAuthority());
            Log.d("ImageFile000", selectedImageUri.getScheme());

            String extension;
            File f;
            if (selectedImageUri.getScheme().equals(ContentResolver.SCHEME_CONTENT)) {
                final MimeTypeMap mimeTypeMap = MimeTypeMap.getSingleton();
                extension = mimeTypeMap.getExtensionFromMimeType(getContentResolver().getType(selectedImageUri));

            } else {
                extension = MimeTypeMap.getFileExtensionFromUrl(String.valueOf(Uri.fromFile(new File(selectedImageUri.getPath()))));

            }
            Log.d("extension", extension);
            f = new File(getCacheDir() + "/temp." + extension);
            try {
                InputStream is = getContentResolver().openInputStream(selectedImageUri);
                FileOutputStream fs = new FileOutputStream(f);
                int read = 0;
                int bufferSize = 1024;
                final byte[] buffers = new byte[bufferSize];
                while ((read = is.read(buffers)) != -1) {
                    fs.write(buffers, 0, read);
                }
                is.close();
                fs.close();

                Log.d("imageFile111", f.getPath());
                file = f;
                binding.img1.setImageURI(selectedImageUri);
            } catch (Exception e) {
                Toast.makeText(mContext, e.getMessage(), Toast.LENGTH_SHORT).show();

            }
        }


        super.onActivityResult(requestCode, resultCode, data);
    }
}