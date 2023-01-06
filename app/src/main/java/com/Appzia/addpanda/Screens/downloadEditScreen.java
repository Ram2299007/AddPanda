package com.Appzia.addpanda.Screens;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.Appzia.addpanda.R;
import com.Appzia.addpanda.databinding.ActivityDownloadEditScreenBinding;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class downloadEditScreen extends AppCompatActivity {

    ActivityDownloadEditScreenBinding binding;

    String s1,s2;
    byte[]  byteArray;
    public static String path;
    public static File imageFile;
    Context mContext;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding  = ActivityDownloadEditScreenBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());



        mContext = binding.getRoot().getContext();
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


        Bitmap bmp = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);
        Drawable dr = new BitmapDrawable(bmp);
        binding.downloadImg.setBackgroundDrawable(dr);
        binding.backArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(s1.equals("basicKey")){
                    startActivity(new Intent(getApplicationContext(), Basic_editFrameActivity.class));

                    SharedPreferences sharedPreferences = getSharedPreferences("MySharedPref", MODE_PRIVATE);
                    SharedPreferences.Editor myEdit = sharedPreferences.edit();
                    myEdit.putString("editKey", "");
                    myEdit.putString("basicKey", "");
                    myEdit.apply();
                }else if(s2.equals("editKey")){
                    startActivity(new Intent(getApplicationContext(), editFameActivity.class));

                    SharedPreferences sharedPreferences = getSharedPreferences("MySharedPref", MODE_PRIVATE);
                    SharedPreferences.Editor myEdit = sharedPreferences.edit();
                    myEdit.putString("editKey", "");
                    myEdit.putString("basicKey", "");
                    myEdit.apply();
                }


            }
        });

        binding.download.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bitmap bitmap = Bitmap.createBitmap(binding.downloadImg.getWidth(), binding.downloadImg.getHeight(), Bitmap.Config.ARGB_8888);
                Canvas canvas = new Canvas(bitmap);
                binding.downloadImg.draw(canvas);

                //   Drawable dr = new BitmapDrawable(bitmap);


                FileOutputStream outStream = null;

                // Write to SD Card
                try {
                    File sdCard = Environment.getExternalStorageDirectory();
                    File dir = new File(sdCard.getAbsolutePath() + "/DCIM");
                    dir.mkdirs();

                    String fileName = String.format("%d.png", System.currentTimeMillis());
                    File outFile = new File(dir, fileName);

                    outStream = new FileOutputStream(outFile);
                    bitmap.compress(Bitmap.CompressFormat.PNG, 100, outStream);
                    outStream.flush();
                    outStream.close();

                    Log.d("downloadImgLOg", "onPictureTaken - wrote to " + outFile.getAbsolutePath());
                    Toast.makeText(getApplicationContext(), "Download Successful : \n" + fileName, Toast.LENGTH_SHORT).show();

                } catch (FileNotFoundException e) {
//                    print("FNF");
                    Log.d("FileNotFoundException", e.getMessage());
                    e.printStackTrace();
                } catch (IOException e) {
                    Log.d("IOException", e.getMessage());
                } finally {

                }


                // assert getFragmentManager() != null;
                // getFragmentManager().beginTransaction().replace(R.id.mainActivityFrame,new payment_without_coupan_Fragment()).commit();
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
        path = MediaStore.Images.Media.insertImage(inContext.getContentResolver(), inImage, "Title", null);
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