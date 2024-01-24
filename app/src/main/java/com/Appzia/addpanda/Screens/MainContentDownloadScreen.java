package com.Appzia.addpanda.Screens;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.app.DownloadManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.icu.text.SimpleDateFormat;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.Appzia.addpanda.R;
import com.Appzia.addpanda.databinding.ActivityVisitingCardDownloadScreenBinding;
import com.google.android.material.bottomappbar.BottomAppBar;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.shape.CornerFamily;
import com.google.android.material.shape.MaterialShapeDrawable;
import com.squareup.picasso.Picasso;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.Objects;

public class MainContentDownloadScreen extends AppCompatActivity {

    ActivityVisitingCardDownloadScreenBinding binding;
    String img_url_key;
    File outFile, imageFile;
    String path;
    Context mContext;
    LinearLayout webp, png, jpeg;

    public static long downloadId;

    @Override
    protected void onStart() {
        super.onStart();
        try {
            img_url_key = getIntent().getStringExtra("img_url_key");
            final ImageView img = new ImageView(getApplicationContext());

            Picasso.get().load(img_url_key).into(img, new com.squareup.picasso.Callback() {
                @Override
                public void onSuccess() {
                    binding.mainLayout.setBackgroundDrawable(img.getDrawable());
                }

                @Override
                public void onError(Exception e) {

                }

            });
        } catch (Exception ignored) {
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityVisitingCardDownloadScreenBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        //By default on each activity android studio
        mContext = MainContentDownloadScreen.this;
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

        final Dialog dialogLayoutColor = new Dialog(MainContentDownloadScreen.this);
        dialogLayoutColor.setContentView(R.layout.download_format_row);
        dialogLayoutColor.setCanceledOnTouchOutside(true);
        dialogLayoutColor.setCancelable(true);
        dialogLayoutColor.getWindow().getAttributes().windowAnimations = R.style.PauseDialogAnimation;
        dialogLayoutColor.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        dialogLayoutColor.getWindow().setGravity(Gravity.CENTER);
        WindowManager.LayoutParams layoutParams = dialogLayoutColor.getWindow().getAttributes();

        layoutParams.y = 170; // top margin
        dialogLayoutColor.getWindow().setAttributes(layoutParams);

        png = dialogLayoutColor.findViewById(R.id.png);
        jpeg = dialogLayoutColor.findViewById(R.id.jpeg);
        webp = dialogLayoutColor.findViewById(R.id.webp);

        mContext.registerReceiver(new DownloadReceiver(), new IntentFilter(DownloadManager.ACTION_DOWNLOAD_COMPLETE));

        binding.download.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                png.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        Bitmap bitmap = Bitmap.createBitmap(binding.mainLayout.getWidth(), binding.mainLayout.getHeight(), Bitmap.Config.ARGB_8888);
                        Canvas canvas = new Canvas(bitmap);
                        binding.mainLayout.draw(canvas);

                        img_url_key = getIntent().getStringExtra("img_url_key");
                        DownloadManager downloadManager = (DownloadManager) mContext.getSystemService(Context.DOWNLOAD_SERVICE);
                        DownloadManager.Request request = new DownloadManager.Request(Uri.parse(img_url_key));

                        request.setTitle("PNG Image");
                        request.setDescription("Downloading");


                        File customFolder;
                        //android 10
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                            customFolder = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS), "Adpanda/Media/Images");
                            String exactPath = customFolder.getAbsolutePath();
                            //    Log.d("TAG", "exactPath: " + exactPath + "/" + model.getFileName());
                        } else {
                            customFolder = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS), "Adpanda/Media/Images");

                        }

                        if (!customFolder.exists()) {
                            customFolder.mkdirs();
                        }
                        String fileName = String.format("%d.png", System.currentTimeMillis());
                        File destinationFile = new File(customFolder, fileName);
                        request.setDestinationUri(Uri.fromFile(destinationFile));
                        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
                        downloadId = downloadManager.enqueue(request);
                        dialogLayoutColor.dismiss();

                    }
                });
                jpeg.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Bitmap bitmap = Bitmap.createBitmap(binding.mainLayout.getWidth(), binding.mainLayout.getHeight(), Bitmap.Config.ARGB_8888);
                        Canvas canvas = new Canvas(bitmap);
                        binding.mainLayout.draw(canvas);

                        img_url_key = getIntent().getStringExtra("img_url_key");
                        DownloadManager downloadManager = (DownloadManager) mContext.getSystemService(Context.DOWNLOAD_SERVICE);
                        DownloadManager.Request request = new DownloadManager.Request(Uri.parse(img_url_key));

                        request.setTitle("JPEG Image");
                        request.setDescription("Downloading");


                        File customFolder;
                        //android 10
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                            customFolder = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS), "Adpanda/Media/Images");
                            String exactPath = customFolder.getAbsolutePath();
                            //    Log.d("TAG", "exactPath: " + exactPath + "/" + model.getFileName());
                        } else {
                            customFolder = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS), "Adpanda/Media/Images");

                        }

                        if (!customFolder.exists()) {
                            customFolder.mkdirs();
                        }
                        String fileName = String.format("%d.jpeg", System.currentTimeMillis());
                        File destinationFile = new File(customFolder, fileName);
                        request.setDestinationUri(Uri.fromFile(destinationFile));
                        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
                        downloadId = downloadManager.enqueue(request);
                        dialogLayoutColor.dismiss();


                    }
                });
                webp.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Bitmap bitmap = Bitmap.createBitmap(binding.mainLayout.getWidth(), binding.mainLayout.getHeight(), Bitmap.Config.ARGB_8888);
                        Canvas canvas = new Canvas(bitmap);
                        binding.mainLayout.draw(canvas);

                        img_url_key = getIntent().getStringExtra("img_url_key");
                        DownloadManager downloadManager = (DownloadManager) mContext.getSystemService(Context.DOWNLOAD_SERVICE);
                        DownloadManager.Request request = new DownloadManager.Request(Uri.parse(img_url_key));

                        request.setTitle("WEBP Image");
                        request.setDescription("Downloading");


                        File customFolder;
                        //android 10
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                            customFolder = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS), "Adpanda/Media/Images");
                            String exactPath = customFolder.getAbsolutePath();
                            //    Log.d("TAG", "exactPath: " + exactPath + "/" + model.getFileName());
                        } else {
                            customFolder = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS), "Adpanda/Media/Images");

                        }

                        if (!customFolder.exists()) {
                            customFolder.mkdirs();
                        }
                        String fileName = String.format("%d.webp", System.currentTimeMillis());
                        File destinationFile = new File(customFolder, fileName);
                        request.setDestinationUri(Uri.fromFile(destinationFile));
                        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
                        downloadId = downloadManager.enqueue(request);
                        dialogLayoutColor.dismiss();

                    }
                });


                dialogLayoutColor.show();

                Window window = dialogLayoutColor.getWindow();
                window.setLayout(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);


            }
        });

        binding.backArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                intent.putExtra("editKey", "editDownload");
                startActivity(intent);

            }
        });

        binding.notification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), NotificationActivity.class));
            }
        });

        binding.share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bitmap bitmap = Bitmap.createBitmap(binding.mainLayout.getWidth(), binding.mainLayout.getHeight(), Bitmap.Config.ARGB_8888);
                Canvas canvas = new Canvas(bitmap);
                binding.mainLayout.draw(canvas);

                getImageUri(getApplicationContext(), bitmap);
                Uri selectedImageUri = Uri.parse(path);

                String selectedImagePath = getRealPathFromURIForGallery(selectedImageUri);
                imageFile = new File(selectedImagePath);

                Log.d("#imagefile", String.valueOf(imageFile));

                Uri imgUri = Uri.parse(imageFile.getAbsolutePath());
                Intent whatsappIntent = new Intent(Intent.ACTION_SEND);
                whatsappIntent.setType("text/plain");
                whatsappIntent.putExtra(Intent.EXTRA_STREAM, imgUri);
                whatsappIntent.setType("image/jpeg");
                whatsappIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);

                try {
                    startActivity(whatsappIntent);
                } catch (android.content.ActivityNotFoundException ex) {
                    Toast.makeText(MainContentDownloadScreen.this, "Apps has not been installed.", Toast.LENGTH_SHORT).show();
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

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        intent.putExtra("editKey", "editDownload");
        startActivity(intent);
    }

    public static class DownloadReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (DownloadManager.ACTION_DOWNLOAD_COMPLETE.equals(intent.getAction())) {
                long id = intent.getLongExtra(DownloadManager.EXTRA_DOWNLOAD_ID, -1);
                if (id == downloadId) {

                }
            }
        }
    }
}

