package com.Appzia.addpanda.Screens;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.icu.text.SimpleDateFormat;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.Appzia.addpanda.MainActivity;
import com.Appzia.addpanda.R;
import com.Appzia.addpanda.Util.Constant.Constant;
import com.Appzia.addpanda.Webservice.Webservice;
import com.Appzia.addpanda.databinding.ActivityDownloadImage2Binding;
import com.google.android.material.bottomappbar.BottomAppBar;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.shape.CornerFamily;
import com.google.android.material.shape.MaterialShapeDrawable;

import java.io.ByteArrayOutputStream;
import java.io.File;

import java.util.Date;
import java.util.Objects;

public class downloadImageActivity extends AppCompatActivity {

    ActivityDownloadImage2Binding binding;
    String s1, s2;
    byte[] byteArray;
    public static String path;
    public static File imageFile;
   public static Context mContext;

    String categoryid, sub_cat_id, template_id;
    String token;
 public static   String widthKey, heightKey;

    public static ProgressBar progressBar;

    @Override
    protected void onStart() {
        super.onStart();



        SharedPreferences sh = getSharedPreferences("MySharedPref", Context.MODE_PRIVATE);

        token = sh.getString("TOKEN_SF", "");

        categoryid = getIntent().getStringExtra("cat_idkey");

        sub_cat_id = getIntent().getStringExtra("sub_cat_idkey");
        template_id = getIntent().getStringExtra("template_idkey");


        //progress bar declaration
        progressBar = (ProgressBar) findViewById(R.id.progressbar);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityDownloadImage2Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        Objects.requireNonNull(getSupportActionBar()).hide();
        Window window = this.getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.setStatusBarColor(this.getResources().getColor(R.color.appThemeColor));

        mContext = binding.getRoot().getContext();


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

        try {
            widthKey = getIntent().getStringExtra("widthKey");
            heightKey = getIntent().getStringExtra("heightKey");


            // Toast.makeText(mContext, widthKey, Toast.LENGTH_SHORT).show();
        } catch (Exception ignored) {
        }
        binding.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), MainActivity.class);
                i.putExtra("editKey", "editFab");
                startActivity(i);
            }
        });
        SharedPreferences sh = getSharedPreferences("MySharedPref", MODE_PRIVATE);
        s1 = sh.getString("basicKey", "");


        SharedPreferences sh2 = getSharedPreferences("MySharedPref", MODE_PRIVATE);
        s2 = sh2.getString("editKey", "");


        try {
            SharedPreferences sh3 = getSharedPreferences("MySharedPref", MODE_PRIVATE);
            String s1 = sh3.getString("imgData", "");
            byteArray = Base64.decode(s1, Base64.DEFAULT);


        } catch (Exception ex) {
            Toast.makeText(getApplicationContext(), ex.getMessage(), Toast.LENGTH_SHORT).show();
        }

//
//        ViewGroup.LayoutParams params = binding.downloadImg.getLayoutParams();
//        params.height = Integer.parseInt(heightKey);
//        params.width = Integer.parseInt(widthKey);
//        binding.downloadImg.setLayoutParams(params);
        //  binding.downloadImg.setBackgroundResource(R.drawable.bg_round_10);
        Bitmap bmp = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);
        Drawable dr = new BitmapDrawable(bmp);
        binding.downloadImg.setBackgroundDrawable(dr);


        try {
            ViewGroup.LayoutParams params = binding.downloadImg.getLayoutParams();
            params.height = Integer.parseInt(heightKey);
            params.width = Integer.parseInt(widthKey);
            binding.downloadImg.setLayoutParams(params);
        }catch (Exception ignored){}
        binding.backArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                if(s1.equals("basicKey")){
//                    startActivity(new Intent(getApplicationContext(), Basic_editFrameActivity.class));
//
//                    SharedPreferences sharedPreferences = getSharedPreferences("MySharedPref", MODE_PRIVATE);
//                    SharedPreferences.Editor myEdit = sharedPreferences.edit();
//                    myEdit.putString("editKey", "");
//                    myEdit.putString("basicKey", "");
//                    myEdit.apply();
//                }else if(s2.equals("editKey")){
//                    startActivity(new Intent(getApplicationContext(), editFameActivity.class));
//
//                    SharedPreferences sharedPreferences = getSharedPreferences("MySharedPref", MODE_PRIVATE);
//                    SharedPreferences.Editor myEdit = sharedPreferences.edit();
//                    myEdit.putString("editKey", "");
//                    myEdit.putString("basicKey", "");
//                    myEdit.apply();
//                }

                onBackPressed();

            }
        });

        binding.download.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

           //     Toast.makeText(mContext, String.valueOf(path), Toast.LENGTH_SHORT).show();
                Bitmap bitmap = Bitmap.createBitmap(binding.downloadImg.getWidth(), binding.downloadImg.getHeight(), Bitmap.Config.ARGB_8888);
                Canvas canvas = new Canvas(bitmap);
                binding.downloadImg.draw(canvas);

                getImageUri(mContext, bitmap);
                Uri selectedImageUri = Uri.parse(path);

                String selectedImagePath = getRealPathFromURIForGallery(selectedImageUri);
                imageFile = new File(selectedImagePath);

                Log.d("#imagefile", String.valueOf(imageFile));

                Constant.NetworkCheck(mContext);
                if ((Constant.wifiInfo != null && Constant.wifiInfo.isConnected()) || (Constant.mobileInfo != null && Constant.mobileInfo.isConnected())) {



                    Webservice.create_content(mContext, token, categoryid, sub_cat_id, template_id, imageFile);

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

        binding.share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bitmap bitmap = Bitmap.createBitmap(binding.downloadImg.getWidth(), binding.downloadImg.getHeight(), Bitmap.Config.ARGB_8888);
                Canvas canvas = new Canvas(bitmap);
                binding.downloadImg.draw(canvas);

                getImageUri(mContext, bitmap);
                Uri selectedImageUri = Uri.parse(path);

                String selectedImagePath = getRealPathFromURIForGallery(selectedImageUri);
                imageFile = new File(selectedImagePath);

                Log.d("#imagefile", String.valueOf(imageFile));

                Uri imgUri = Uri.parse(imageFile.getAbsolutePath());
                Intent whatsappIntent = new Intent(Intent.ACTION_SEND);
                whatsappIntent.setType("text/plain");
                whatsappIntent.putExtra(Intent.EXTRA_TEXT, "AdPanda :");
                whatsappIntent.putExtra(Intent.EXTRA_STREAM, imgUri);
                whatsappIntent.setType("image/jpeg");
                whatsappIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);

                try {
                    startActivity(whatsappIntent);
                } catch (android.content.ActivityNotFoundException ex) {
                    Toast.makeText(getApplicationContext(), "Apps has not been installed.", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

    public Uri getImageUri(Context inContext, Bitmap inImage) {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        inImage.compress(Bitmap.CompressFormat.JPEG, 100, bytes);

        String title = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        path = MediaStore.Images.Media.insertImage(inContext.getContentResolver(), inImage, title, null);
        return Uri.parse(path);
    }

    public String getRealPathFromURIForGallery(Uri uri) {
        if (uri == null) {
            return null;
        }
        String[] projection = {MediaStore.Images.Media.DATA};
        Cursor cursor = mContext.getContentResolver().query(uri, projection, null,
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