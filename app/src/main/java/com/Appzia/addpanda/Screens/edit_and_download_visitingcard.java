package com.Appzia.addpanda.Screens;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.icu.text.SimpleDateFormat;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.Toast;

import com.Appzia.addpanda.MainActivity;
import com.Appzia.addpanda.R;
import com.Appzia.addpanda.Util.Constant.Constant;
import com.Appzia.addpanda.Webservice.Webservice;
import com.Appzia.addpanda.databinding.ActivityEditAndDownloadVisitingcardBinding;
import com.google.android.material.bottomappbar.BottomAppBar;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.shape.CornerFamily;
import com.google.android.material.shape.MaterialShapeDrawable;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.util.Date;
import java.util.Objects;


public class edit_and_download_visitingcard extends AppCompatActivity {

    ActivityEditAndDownloadVisitingcardBinding binding;

    public static String path;
    String ProfileKey;
    String address;
    String addressKey;

    String buainessname;
    String captionEdtKey;
    String email;
    String fbKey;
    File imageFile;
    String instaKey;
    String linkdnKey;
    Context mContext;
    String mobile;
    String nameKey;
    String template_idKey;
    String token;
    String web;
    String whatsappl;
    String yourname;

    String textValue,fbValue,instaValue,captionValue,addressValue,lnkdValue;

    @Override
    public void onStart() {
        super.onStart();
        this.token = getSharedPreferences("MySharedPref", 0).getString("TOKEN_SF", "");
        try {
            Constant.getSfFuncion(this.mContext);
            this.buainessname = Constant.getSF.getString("BusnameKey", "");
            this.yourname = Constant.getSF.getString("yournameKey", "");
            this.email = Constant.getSF.getString("emailKey", "");
            this.web = Constant.getSF.getString("webKey", "");
            this.address = Constant.getSF.getString("addressKey", "");
            this.mobile = Constant.getSF.getString("mobileKey", "");
            this.whatsappl = Constant.getSF.getString("whatsappKey", "");
            this.nameKey = Constant.getSF.getString("nameKey", "");
            this.fbKey = Constant.getSF.getString("fbKey", "");
            this.instaKey = Constant.getSF.getString("instaKey", "");
            this.linkdnKey = Constant.getSF.getString("linkdnKey", "");
            this.addressKey = Constant.getSF.getString("addressKey", "");
            this.captionEdtKey = Constant.getSF.getString("captionEdtKey", "");
            this.template_idKey = Constant.getSF.getString("template_idKey", "");
            this.ProfileKey = Constant.getSF.getString("ProfileKey", "");




            textValue = Constant.getSF.getString("textKey", "");
            fbValue = Constant.getSF.getString("fbKey", "");
            instaValue = Constant.getSF.getString("instaKey", "");
            lnkdValue = Constant.getSF.getString("linkdnKey", "");
            addressValue = Constant.getSF.getString("addressKey", "");
            captionValue = Constant.getSF.getString("captionEdtKey", "");


            final ImageView img = new ImageView(getApplicationContext());
            Picasso.get().load(this.ProfileKey).into(img, new Callback() {
                public void onSuccess() {
                    binding.viewCardImg.setBackgroundDrawable(img.getDrawable());
                }

                public void onError(Exception e) {
                }
            });
            this.binding.mainTitle.setText(this.yourname);
            this.binding.subtitle.setText(this.buainessname);
            this.binding.phone.setText(this.mobile);
            this.binding.email.setText(this.email);
            this.binding.website.setText(this.web);
            this.binding.location.setText(this.address);
        } catch (Exception e) {
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityEditAndDownloadVisitingcardBinding.inflate(getLayoutInflater());
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

        binding.notification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), NotificationActivity.class));
            }
        });

        binding.edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), editVisitingCardDetailsActivity.class));
            }
        });

        binding.download.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Bitmap bitmap = Bitmap.createBitmap(binding.viewCardImg.getWidth(), binding.viewCardImg.getHeight(), Bitmap.Config.ARGB_8888);
                binding.viewCardImg.draw(new Canvas(bitmap));
                getImageUri(mContext, bitmap);
                String selectedImagePath = getRealPathFromURIForGallery(Uri.parse(path));
                imageFile = new File(selectedImagePath);
                Log.d("#imagefile", String.valueOf(imageFile));
                Constant.NetworkCheck(mContext);
                if ((Constant.wifiInfo != null && Constant.wifiInfo.isConnected()) || (Constant.mobileInfo != null && Constant.mobileInfo.isConnected())) {
                    Webservice.creating_visiting_card(mContext, token, template_idKey, imageFile);

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
                Bitmap bitmap = Bitmap.createBitmap(binding.viewCardImg.getWidth(), binding.viewCardImg.getHeight(), Bitmap.Config.ARGB_8888);
                Canvas canvas = new Canvas(bitmap);
                binding.viewCardImg.draw(canvas);

                getImageUri(mContext, bitmap);
                Uri selectedImageUri = Uri.parse(path);

                String selectedImagePath = getRealPathFromURIForGallery(selectedImageUri);
                imageFile = new File(selectedImagePath);

                Log.d("#imagefile", String.valueOf(imageFile));

                Uri imgUri = Uri.parse(imageFile.getAbsolutePath());
                Intent whatsappIntent = new Intent(Intent.ACTION_SEND);
                whatsappIntent.setType("image/*");
                whatsappIntent.putExtra(Intent.EXTRA_TEXT, "*Greeting from "+buainessname+"*"+"\n"+"#"+textValue+"\n"+"#"+captionValue+"\n"+"#"+fbValue);
                whatsappIntent.putExtra(Intent.EXTRA_STREAM, imgUri);
                whatsappIntent.setPackage("com.whatsapp");
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
        inImage.compress(Bitmap.CompressFormat.JPEG, 100, new ByteArrayOutputStream());
        String title = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String insertImage = MediaStore.Images.Media.insertImage(inContext.getContentResolver(), inImage, title, (String) null);
        path = insertImage;
        return Uri.parse(insertImage);
    }

    public String getRealPathFromURIForGallery(Uri uri) {
        if (uri == null) {
            return null;
        }
        Cursor cursor = getContentResolver().query(uri, new String[]{"_data"}, (String) null, (String[]) null, (String) null);
        if (cursor != null) {
            int column_index = cursor.getColumnIndexOrThrow("_data");
            cursor.moveToFirst();
            return cursor.getString(column_index);
        }
        throw new AssertionError();
    }
}