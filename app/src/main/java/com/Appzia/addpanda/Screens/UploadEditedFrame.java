package com.Appzia.addpanda.Screens;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.icu.text.SimpleDateFormat;
import android.net.Uri;
import android.os.Build;
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
import android.webkit.MimeTypeMap;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.Appzia.addpanda.R;
import com.Appzia.addpanda.Webservice.Webservice;
import com.Appzia.addpanda.databinding.ActivityUploadEditedFrameBinding;
import com.google.android.material.bottomappbar.BottomAppBar;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.shape.CornerFamily;
import com.google.android.material.shape.MaterialShapeDrawable;

import java.io.ByteArrayOutputStream;
import java.io.File;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.Objects;

public class UploadEditedFrame extends AppCompatActivity {

    ActivityUploadEditedFrameBinding binding;
    String s1, s2;
    byte[] byteArray;
    public static String path;
    public static File imageFile;
    public static Context mContext;
    public static File pngFile;

    String categoryid, sub_cat_id, template_id;
    String token;
    public static String widthKey, heightKey;


    @Override
    protected void onStart() {
        super.onStart();


        SharedPreferences sh = getSharedPreferences("MySharedPref", Context.MODE_PRIVATE);

        token = sh.getString("TOKEN_SF", "");

        categoryid = getIntent().getStringExtra("cat_idkey");

        sub_cat_id = getIntent().getStringExtra("sub_cat_idkey");
        template_id = getIntent().getStringExtra("template_idkey");


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityUploadEditedFrameBinding.inflate(getLayoutInflater());
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
        } catch (Exception ignored) {
        }
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

//        binding.download.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                Bitmap bitmap = Bitmap.createBitmap(binding.downloadImg.getWidth(), binding.downloadImg.getHeight(), Bitmap.Config.ARGB_8888);
//                Canvas canvas = new Canvas(bitmap);
//                binding.downloadImg.draw(canvas);
//
//                getImageUri(mContext, bitmap);
//                Uri selectedImageUri = Uri.parse(path);
//
//                String selectedImagePath = getRealPathFromURIForGallery(selectedImageUri);
//                imageFile = new File(selectedImagePath);
//
//                Log.d("#imagefile", String.valueOf(imageFile));
//
//                Constant.NetworkCheck(mContext);
//                if ((Constant.wifiInfo != null && Constant.wifiInfo.isConnected()) || (Constant.mobileInfo != null && Constant.mobileInfo.isConnected())) {
//
//
//                    WebserviceRetrofit.create_content(mContext, token, categoryid, sub_cat_id, template_id, imageFile);
//
//                } else {
//                    Constant.NetworkCheckDialogue(mContext);
//                    Constant.dialogForNetwork.show();
//
//                    AppCompatButton btn = Constant.dialogForNetwork.findViewById(R.id.retry);
//
//                    btn.setOnClickListener(new View.OnClickListener() {
//                        @Override
//                        public void onClick(View v) {
//                            Constant.dialogForNetwork.dismiss();
//
//                        }
//                    });
//
//
//                }
//
//
//            }
//        });

//        binding.or.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Bitmap bitmap = Bitmap.createBitmap(binding.downloadImg.getWidth(), binding.downloadImg.getHeight(), Bitmap.Config.ARGB_8888);
//                Canvas canvas = new Canvas(bitmap);
//                binding.downloadImg.draw(canvas);
//                Bitmap mutableBitmap = bitmap.copy(Bitmap.Config.ARGB_8888, true);
//
//                int colorToReplace = Color.TRANSPARENT;
//
//                int newColor = Color.YELLOW;
//
//                for (int x = 0; x < mutableBitmap.getWidth(); x++) {
//                    for (int y = 0; y < mutableBitmap.getHeight(); y++) {
//                        int pixel = mutableBitmap.getPixel(x, y);
//
//                        // Check if the pixel color matches the color to replace
//                        if (pixel == colorToReplace) {
//                            // Replace the color with the new color
//                            mutableBitmap.setPixel(x, y, newColor);
//                        }
//                    }
//                }
//
//
//                getImageUri(mContext, mutableBitmap);
//                Uri uri = Uri.parse(path);
//
//                String extension;
//                File f ;
//                if(uri.getScheme().equals(ContentResolver.SCHEME_CONTENT)){
//                    final MimeTypeMap mimeTypeMap = MimeTypeMap.getSingleton();
//                    extension = mimeTypeMap.getExtensionFromMimeType(getContentResolver().getType(uri));
//
//                }else{
//                    extension = MimeTypeMap.getFileExtensionFromUrl(String.valueOf(Uri.fromFile(new File(uri.getPath()))));
//
//                }
//                Log.d("extension", extension);
//                f = new File(getCacheDir()+"/image."+extension);
//                try{
//                    InputStream is = getContentResolver().openInputStream(uri);
//                    FileOutputStream fs = new FileOutputStream(f);
//                    int read = 0;
//                    int bufferSize = 1024;
//                    final byte[] buffers = new byte[bufferSize];
//                    while ((read=is.read(buffers))!=-1){
//                        fs.write(buffers,0,read);
//                    }
//                    is.close();
//                    fs.close();
//
//                    Log.d("imageFile111", f.getPath());
//                    imageFile=f;
//                    //binding.upload.setText(uri.getPath());
//                }catch (Exception e){
//                    Toast.makeText(mContext, e.getMessage(), Toast.LENGTH_SHORT).show();
//
//                }
//
//            }
//        });

        binding.share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bitmap bitmap = Bitmap.createBitmap(binding.downloadImg.getWidth(), binding.downloadImg.getHeight(), Bitmap.Config.ARGB_8888);

                Canvas canvas = new Canvas(bitmap);
                binding.downloadImg.draw(canvas);

                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
                byte[] byteArray = stream.toByteArray();

//                Uri uri = Uri.fromFile(file);

                try {
                    // Create a temporary file to store the image
                    File tempFile = File.createTempFile("temp_image", ".png", getCacheDir());

                    // Write the byte array to the file
                    FileOutputStream fos = new FileOutputStream(tempFile);
                    fos.write(byteArray);
                    fos.flush();
                    fos.close();
                    Log.d("TAG", "getPath: "+tempFile);
                    Webservice.add_frame_photo(mContext, token, tempFile);





                } catch (IOException e) {
                    e.printStackTrace();
                    Toast.makeText(UploadEditedFrame.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                }


//                                Bitmap mutableBitmap = bitmap.copy(Bitmap.Config.ARGB_8888, true);
//                int colorToReplace = Color.TRANSPARENT;
//
//                int newColor = Color.parseColor("#f2f5f4");
//
//                for (int x = 0; x < mutableBitmap.getWidth(); x++) {
//                    for (int y = 0; y < mutableBitmap.getHeight(); y++) {
//                        int pixel = mutableBitmap.getPixel(x, y);
//
//                        // Check if the pixel color matches the color to replace
//                        if (pixel == colorToReplace) {
//                            mutableBitmap.setPixel(x, y, newColor);
//
//                        }
//                    }
//                }
//
//                Uri uri;
//                if (Build.VERSION.SDK_INT > Build.VERSION_CODES.Q) {
//                    getImageUri(mContext, mutableBitmap);
//                    Log.d("path%%", path);
//                    uri = Uri.parse(path);
//                } else {
//
//                    uri = bitmapToUri(mContext, mutableBitmap);
//                }


            }
        });
    }

    public Uri getImageUri(Context inContext, Bitmap inImage) {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        inImage.compress(Bitmap.CompressFormat.PNG, 100, bytes);

        String title = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            title = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        }
        path = MediaStore.Images.Media.insertImage(inContext.getContentResolver(), inImage, title, null);

        return Uri.parse(path);
    }

    public static Uri bitmapToUri(Context context, Bitmap bitmap) {
        // Get the context's external files directory
        File externalFilesDir = context.getExternalFilesDir(Environment.DIRECTORY_PICTURES);

        // Create a temporary file to save the bitmap
        File tempFile;
        try {
            tempFile = File.createTempFile("temp_image", ".png", externalFilesDir);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

        // Save the bitmap to the temporary file
        try (FileOutputStream out = new FileOutputStream(tempFile)) {
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, out);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

        // Return the URI of the saved file
        return Uri.fromFile(tempFile);
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

    public void saveRelativeLayoutAsImage(RelativeLayout relativeLayout, String fileName) {
        // Create a Bitmap object with the dimensions of the RelativeLayout
        Bitmap bitmap = Bitmap.createBitmap(relativeLayout.getWidth(), relativeLayout.getHeight(), Bitmap.Config.ARGB_8888);

        // Create a Canvas object with the Bitmap
        Canvas canvas = new Canvas(bitmap);

        // Draw the RelativeLayout onto the Canvas
        relativeLayout.draw(canvas);

        // Create a file to store the image
        File file = new File(getExternalFilesDir(null), fileName); // Replace with your desired file name and extension

        // Save the Bitmap as an image file
        try {
            FileOutputStream outputStream = new FileOutputStream(file);
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, outputStream);
            outputStream.flush();
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static File convertJpgToPng(Context context, String jpgPath) {
        // Load the JPG image
        Bitmap jpgBitmap = BitmapFactory.decodeFile(jpgPath);

        // Create a blank bitmap with the same dimensions as the JPG image
        Bitmap pngBitmap = Bitmap.createBitmap(jpgBitmap.getWidth(), jpgBitmap.getHeight(), Bitmap.Config.ARGB_8888);

        // Draw the JPG image onto the blank bitmap
        Canvas canvas = new Canvas(pngBitmap);
        canvas.drawBitmap(jpgBitmap, 0, 0, null);

        // Create a new file in the cache directory
        File cacheDir = context.getCacheDir();
        pngFile = new File(cacheDir, "image.png");

        // Save the PNG image to the cache file
        try {
            FileOutputStream outputStream = new FileOutputStream(pngFile);
            pngBitmap.compress(Bitmap.CompressFormat.PNG, 100, outputStream);
            outputStream.flush();
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Cleanup
        jpgBitmap.recycle();
        pngBitmap.recycle();

        return pngFile;
    }

}