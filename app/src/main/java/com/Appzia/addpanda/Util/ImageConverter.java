package com.Appzia.addpanda.Util;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class ImageConverter {

    public static Uri bitmapToUri(Context context, Bitmap bitmap) {
        // Get the path for saving the image
        File imagesDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
        String fileName = "image.png";
        File imageFile = new File(imagesDir, fileName);

        // Convert Bitmap to PNG and save it to the file
        try {
            OutputStream outputStream = new FileOutputStream(imageFile);
            bitmap.compress(CompressFormat.PNG, 100, outputStream);
            outputStream.flush();
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Save the image to the MediaStore (to make it accessible in gallery apps)
        ContentResolver contentResolver = context.getContentResolver();
        ContentValues contentValues = new ContentValues();
        contentValues.put(MediaStore.Images.Media.DATA, imageFile.getAbsolutePath());
        contentValues.put(MediaStore.Images.Media.MIME_TYPE, "image/png");
        Uri uri = contentResolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues);

        return uri;
    }
}
