package com.Appzia.addpanda.Screens;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.Toast;

import com.Appzia.addpanda.R;
import com.Appzia.addpanda.Util.Constant.Constant;
import com.Appzia.addpanda.Webservice.Webservice;
import com.Appzia.addpanda.databinding.ActivityBusinessProfileBinding;
import com.squareup.picasso.Picasso;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class BusinessProfile extends AppCompatActivity {

    ActivityBusinessProfileBinding binding;
    private static final int PICK_IMAGE = 5;
    File imageFile;
    String checkOnActivityResukt="";
    Bitmap bitmap2 = null;
    public static ImageView imageViewBusiness;

    String bussiness_name, website, business_category, date_of_incorporation, business_details, account_type, token, email_id, mobile_number;
    private Uri currImageURI;
    //for upload image to api
    Uri selectedUri;
    Bitmap bitmap;
    String url = "";
    String encodedImage;
    public static final int REQUEST_ID_MULTIPLE_PERMISSIONS = 1;
    public static final String FILE_NAME = "encodeImg.text";
    Context mContext;


    @Override
    protected void onStart() {
        super.onStart();

        try {
            bussiness_name = getIntent().getStringExtra("bussiness_name");
            website = getIntent().getStringExtra("website");
            business_category = getIntent().getStringExtra("business_category");
            date_of_incorporation = getIntent().getStringExtra("date_of_incorporation");
            business_details = getIntent().getStringExtra("business_details");
            account_type = getIntent().getStringExtra("account_type");
            token = getIntent().getStringExtra("token");
            email_id = getIntent().getStringExtra("email_id");
            mobile_number = getIntent().getStringExtra("mobile_number");


            binding.name.setText(bussiness_name);
            binding.web.setText(website);
            binding.category.setText(business_category);
            binding.dateofincorp.setText(date_of_incorporation);
            binding.businessd.setText(business_details);
        } catch (Exception ignored) {
        }



    }

    @Override
    public void onBackPressed() {

        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        intent.putExtra("editKey", "businessProfileKey");
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityBusinessProfileBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        //By default on each activity android studio
        Objects.requireNonNull(getSupportActionBar()).hide();
        Window window = this.getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.setStatusBarColor(this.getResources().getColor(R.color.appThemeColor));
        mContext = this;
        imageViewBusiness = findViewById(R.id.imageViewBusiness);



        if(checkOnActivityResukt.equals("")){

            Constant.NetworkCheck(mContext);
            if ((Constant.wifiInfo != null && Constant.wifiInfo.isConnected()) || (Constant.mobileInfo != null && Constant.mobileInfo.isConnected())) {


                Webservice.fetchDataProfile(mContext, token);
            }
            else {
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
        }else if(checkOnActivityResukt.equals("ok")){
            binding.imageViewBusiness.setImageBitmap(bitmap2);
        }

        binding.pic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
                intent.setType("image/*");
                intent.addCategory(Intent.CATEGORY_OPENABLE);

                try {
                    startActivityForResult(
                            Intent.createChooser(intent, "Select a File to Upload"),
                            PICK_IMAGE);
                } catch (android.content.ActivityNotFoundException ex) {
                    // Potentially direct the user to the Market with a Dialog
                    Toast.makeText(getApplicationContext(), "Please install a File Manager.", Toast.LENGTH_SHORT).show();
                }
            }
        });


        binding.updateId.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Constant.NetworkCheck(mContext);
                if ((Constant.wifiInfo != null && Constant.wifiInfo.isConnected()) || (Constant.mobileInfo != null && Constant.mobileInfo.isConnected())) {
                    Webservice.update_user_profile_data_business_Main(mContext, account_type, binding.name.getText().toString(), email_id, mobile_number, "a", binding.web.getText().toString(), binding.category.getText().toString(), binding.dateofincorp.getText().toString(), binding.businessd.getText().toString(), token, imageFile);

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
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode ==  PICK_IMAGE && resultCode == RESULT_OK) {
            Uri selectedImageUri = data.getData();
            File f = new File(getCacheDir() + "/temp." + "jpeg");
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
                imageFile = f;

            } catch (Exception e) {
                Toast.makeText(mContext, e.getMessage(), Toast.LENGTH_SHORT).show();

            }

            binding.imageViewBusiness.setImageURI(selectedImageUri);


            checkOnActivityResukt = "ok";


            try {
                bitmap2 = MediaStore.Images.Media.getBitmap(this.getContentResolver(), selectedImageUri);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            binding.imageViewBusiness.setImageBitmap(bitmap2);
        }
    }

    private void imageStore(Bitmap bitmap) {

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, outputStream);

        byte[] imageByte = outputStream.toByteArray();
        encodedImage = android.util.Base64.encodeToString(imageByte, Base64.DEFAULT);


        //this is a final variable for upload iin api
        Log.d("encodedImage", encodedImage);
        save();

    }

    private boolean checkAndRequestPermissions() {

        try {
            int storage = ContextCompat.checkSelfPermission(getApplicationContext(), android.Manifest.permission.WRITE_EXTERNAL_STORAGE);
            List<String> listPermissionsNeeded = new ArrayList<>();

            if (storage != PackageManager.PERMISSION_GRANTED) {
                listPermissionsNeeded.add(android.Manifest.permission.WRITE_EXTERNAL_STORAGE);

            }
            if (!listPermissionsNeeded.isEmpty()) {
                ActivityCompat.requestPermissions(BusinessProfile.this, listPermissionsNeeded.toArray(new String[listPermissionsNeeded.size()]), REQUEST_ID_MULTIPLE_PERMISSIONS);


                return false;
            }
        } catch (Exception ex) {
            Log.d("permission", ex.getMessage());
        }
        return true;
    }

    public void save() {
        String text = "Sample Data";
        FileOutputStream fos = null;
        try {
            fos = openFileOutput(FILE_NAME, MODE_PRIVATE);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            assert fos != null;
            fos.write(encodedImage.getBytes());
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

        Toast.makeText(getApplicationContext(), "Saved to " + getFilesDir() + "/" + FILE_NAME, Toast.LENGTH_SHORT).show();

        Log.d("pathSaved", "Saved to " + getFilesDir() + "/" + FILE_NAME);
    }


    public String getRealPathFromURI(Uri contentUri) {

        // can post image
        String[] proj = {MediaStore.Images.Media.DATA};
        Cursor cursor = managedQuery(contentUri,
                proj, // Which columns to return
                null,       // WHERE clause; which rows to return (all rows)
                null,       // WHERE clause selection arguments (none)
                null); // Order-by clause (ascending by name)
        int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        cursor.moveToFirst();

        return cursor.getString(column_index);
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