package com.Appzia.addpanda.Screens;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.Appzia.addpanda.Fragments.profileFragment;
import com.Appzia.addpanda.MainActivity;
import com.Appzia.addpanda.R;
import com.Appzia.addpanda.Webservice.Webservice;
import com.Appzia.addpanda.databinding.ActivityPersonalProfileBinding;
import com.google.android.material.bottomappbar.BottomAppBar;
import com.google.android.material.shape.CornerFamily;
import com.google.android.material.shape.MaterialShapeDrawable;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class PersonalProfile extends AppCompatActivity {

    ActivityPersonalProfileBinding binding;
    String name, email_id, mobile_number, dob, political_interest, account_type, token;
    public static ImageView imageView;


    private File imageFile;
    Bitmap bitmap2 = null;

    public static final int PICK_IMAGE_RESULT = 1;
    private static final int PICK_IMAGE = 5;
    private Uri currImageURI;
    Context mContext;
    private Bitmap bitmap;
    File file, file_data_gallary;
    private String imagepath;

    String finalUrl;

    //for upload image to api
    Uri selectedUri;

    String url = "";
    String encodedImage;

    String checkOnActivityResukt="";
    public static ProgressBar progressBar;
    public static final int REQUEST_ID_MULTIPLE_PERMISSIONS = 1;

    public static final String FILE_NAME_NEW = "imgNew.txt";

    @Override
    protected void onStart() {
        super.onStart();

        try {
            //progress bar declaration
            progressBar = (ProgressBar) findViewById(R.id.progressbar);
            name = getIntent().getStringExtra("name");
            email_id = getIntent().getStringExtra("email_id");
            mobile_number = getIntent().getStringExtra("mobile_number");
            dob = getIntent().getStringExtra("dob");
            political_interest = getIntent().getStringExtra("political_interest");
            account_type = getIntent().getStringExtra("account_type");

            token = getIntent().getStringExtra("token");

            binding.name.setText(name);
            binding.email.setText(email_id);
            binding.mobilePersonal.setText(mobile_number);
            binding.dob.setText(dob);
            binding.political.setText(political_interest);
        } catch (Exception ex) {
            Toast.makeText(this, ex.getMessage(), Toast.LENGTH_SHORT).show();

        }

        if(checkOnActivityResukt.equals("")){
            binding.imageView.setImageBitmap(bitmap2);
            Webservice.fetchDataProfile(mContext, token);
        }else if(checkOnActivityResukt.equals("ok")){
            binding.imageView.setImageBitmap(bitmap2);
        }



    }

    @Override
    public void onBackPressed() {

        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        intent.putExtra("editKey", "personalProfileKey");
        startActivity(intent);
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityPersonalProfileBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        //By default on each activity android studio
        Objects.requireNonNull(getSupportActionBar()).hide();
        Window window = this.getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.setStatusBarColor(this.getResources().getColor(R.color.appThemeColor));

        mContext = this;

        imageView = findViewById(R.id.imageView);


        binding.pic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK,
                        android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(Intent.createChooser(intent, "select image"),
                        PICK_IMAGE);

            }
        });

        binding.updateId.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //  file = new File(getRealPathFromURI(currImageURI));
                Log.d("currImageURI", String.valueOf(currImageURI));

                Webservice.update_user_profile_data_personal_main(mContext, account_type, binding.name.getText().toString(), binding.email.getText().toString(), binding.mobilePersonal.getText().toString(), "a", binding.dob.getText().toString(), binding.political.getText().toString(), token, imageFile);
            }
        });
    }


    public void save() {
        String text = "Sample Data";
        FileOutputStream fos = null;
        try {
            fos = openFileOutput(FILE_NAME_NEW, MODE_PRIVATE);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            assert fos != null;
            fos.write(finalUrl.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        Toast.makeText(getApplicationContext(), "Saved to " + getFilesDir() + "/" + FILE_NAME_NEW, Toast.LENGTH_SHORT).show();

        Log.d("pathSaved", "Saved to " + getFilesDir() + "/" + FILE_NAME_NEW);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE && resultCode == RESULT_OK) {
            assert data != null;
            Uri selectedImageUri = data.getData();
            binding.imageView.setImageURI(selectedImageUri);

            String selectedImagePath = getRealPathFromURIForGallery(selectedImageUri);
            imageFile = new File(selectedImagePath);

            checkOnActivityResukt = "ok";


            try {
                bitmap2 = MediaStore.Images.Media.getBitmap(this.getContentResolver(), selectedImageUri);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            binding.imageView.setImageBitmap(bitmap2);


            Log.d("imageFile", String.valueOf(imageFile));
        }
    }
    public String getRealPathFromURIForGallery(Uri uri) {
        if (uri == null) {
            return null;
        }
        String[] projection = {MediaStore.Images.Media.DATA};
        Cursor cursor = this.getContentResolver().query(uri, projection, null,
                null, null);
        if (cursor != null) {
            int column_index =
                    cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            cursor.moveToFirst();
            return cursor.getString(column_index);
        }
        assert false;
        cursor.close();
        return uri.getPath();
    }


}