package com.Appzia.addpanda.Screens;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.cardview.widget.CardView;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.PointF;
import android.icu.text.SimpleDateFormat;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.Appzia.addpanda.R;
import com.Appzia.addpanda.Util.Constant.Constant;
import com.Appzia.addpanda.Webservice.Webservice;
import com.Appzia.addpanda.databinding.ActivityEditVerticalVisitingCardBinding;
import com.google.android.material.bottomappbar.BottomAppBar;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.shape.CornerFamily;
import com.google.android.material.shape.MaterialShapeDrawable;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.Objects;

public class edit_vertical_visiting_card extends AppCompatActivity {

    ActivityEditVerticalVisitingCardBinding binding;
    Context mContext;

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
    String mobile;
    String nameKey;
    String template_idKey;
    String token;
    String web;
    String whatsappl;
    String yourname;
    String textValue, fbValue, instaValue, captionValue, addressValue, lnkdValue;

    float[] lastEvent = null;
    float d = 0f;
    float newRot = 0f;
    private boolean isZoomAndRotate;
    private boolean isOutSide;
    private static final int NONE = 0;
    private static final int DRAG = 1;
    private static final int ZOOM = 2;
    private int mode = NONE;
    private PointF start = new PointF();
    private PointF mid = new PointF();
    float oldDist = 1f;
    private float xCoOrdinate, yCoOrdinate;
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
            final ImageView img = new ImageView(getApplicationContext());


            textValue = Constant.getSF.getString("textKey", "");
            fbValue = Constant.getSF.getString("fbKey", "");
            instaValue = Constant.getSF.getString("instaKey", "");
            lnkdValue = Constant.getSF.getString("linkdnKey", "");
            addressValue = Constant.getSF.getString("addressKey", "");
            captionValue = Constant.getSF.getString("captionEdtKey", "");

            Picasso.get().load(this.ProfileKey).into(img, new Callback() {
                public void onSuccess() {
                    binding.viewCardImg.setBackgroundDrawable(img.getDrawable());
                }

                public void onError(Exception e) {
                }
            });
            this.binding.mainTitle.setText(this.buainessname);
            this.binding.subtitle.setText(this.yourname);
            this.binding.phone.setText(this.mobile);
            this.binding.mail.setText(this.email);
            this.binding.web.setText(this.web);
            this.binding.location.setText(this.address);
        } catch (Exception e) {
        }

        try {
            File f = new File(getCacheDir()+"/visiting_card_img."+"jpg");

            if (f.exists()) {
                // File exists, you can now read its contents
                BufferedReader reader = new BufferedReader(new FileReader(f));
                StringBuilder stringBuilder = new StringBuilder();
                String line;

                while ((line = reader.readLine()) != null) {
                    stringBuilder.append(line).append('\n');
                }

                // Now, stringBuilder contains the contents of the file
                String fileContents = stringBuilder.toString();
                Log.d("TAG", "data "+fileContents);

                binding.img.setImageURI(Uri.fromFile(f));
                reader.close();
            } else {
                Toast.makeText(mContext, "file not exist", Toast.LENGTH_SHORT).show();
                // File does not exist
                // Handle accordingly
            }
        } catch (IOException e) {

            // Handle exceptions, e.g., file not found, permission issues, etc.
            e.printStackTrace();
            Log.d("TAG", "errortt: " + e.getMessage());
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityEditVerticalVisitingCardBinding.inflate(getLayoutInflater());
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
        binding.share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Bitmap bitmap = Bitmap.createBitmap(binding.viewCardImg.getWidth(), binding.viewCardImg.getHeight(), Bitmap.Config.ARGB_8888);
                Canvas canvas = new Canvas(bitmap);
                binding.viewCardImg.draw(canvas);

                getImageUri(mContext, bitmap);
                Uri uri = getImageUri(mContext, bitmap);

                File f = new File(getCacheDir() + "/temp." + "jpeg");
                try {
                    InputStream is = getContentResolver().openInputStream(uri);
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




                Intent whatsappIntent = new Intent(Intent.ACTION_SEND);
                whatsappIntent.setType("text/plain");
                whatsappIntent.putExtra(Intent.EXTRA_TEXT, "*Greeting from " + buainessname + "*" + "\n" + textValue + "\n" + captionValue + "\n" + fbValue + "\n" + instaValue + "\n" + linkdnKey);

                whatsappIntent.putExtra(Intent.EXTRA_STREAM, uri);
                whatsappIntent.setType("image/*");
                whatsappIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);


                try {
                    startActivity(whatsappIntent);
                } catch (android.content.ActivityNotFoundException ex) {
                    Toast.makeText(getApplicationContext(), "Apps has not been installed.", Toast.LENGTH_SHORT).show();
                }
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
                Uri uri = getImageUri(mContext, bitmap);

                File f = new File(getCacheDir() + "/temp." + "jpg");
                try {
                    InputStream is = getContentResolver().openInputStream(uri);
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

        binding.getImgCrd.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                CardView view = (CardView) v;
                view.bringToFront();
                viewTransformation(view, event);


                return true;
            }

        });

        binding.mainTitle.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                TextView view = (TextView) v;
                view.bringToFront();
                viewTransformation(view, event);


                return true;
            }

        });
        binding.subtitle.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                TextView view = (TextView) v;
                view.bringToFront();
                viewTransformation(view, event);


                return true;
            }

        });
        binding.phone.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                TextView view = (TextView) v;
                view.bringToFront();
                viewTransformation(view, event);


                return true;
            }

        });
        binding.mail.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                TextView view = (TextView) v;
                view.bringToFront();
                viewTransformation(view, event);


                return true;
            }

        });
        binding.web.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                TextView view = (TextView) v;
                view.bringToFront();
                viewTransformation(view, event);


                return true;
            }

        });
        binding.location.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                TextView view = (TextView) v;
                view.bringToFront();
                viewTransformation(view, event);


                return true;
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


    private void viewTransformation(View view, MotionEvent event) {
        switch (event.getAction() & MotionEvent.ACTION_MASK) {
            case MotionEvent.ACTION_DOWN:
                xCoOrdinate = view.getX() - event.getRawX();
                yCoOrdinate = view.getY() - event.getRawY();

                start.set(event.getX(), event.getY());
                isOutSide = false;
                mode = DRAG;
                lastEvent = null;
                break;
            case MotionEvent.ACTION_POINTER_DOWN:
                oldDist = spacing(event);
                if (oldDist > 10f) {
                    midPoint(mid, event);
                    mode = ZOOM;
                }

                lastEvent = new float[4];
                lastEvent[0] = event.getX(0);
                lastEvent[1] = event.getX(1);
                lastEvent[2] = event.getY(0);
                lastEvent[3] = event.getY(1);
                d = rotation(event);
                break;
            case MotionEvent.ACTION_UP:
                isZoomAndRotate = false;
                if (mode == DRAG) {
                    float x = event.getX();
                    float y = event.getY();
                }
            case MotionEvent.ACTION_OUTSIDE:
                isOutSide = true;
                mode = NONE;
                lastEvent = null;
            case MotionEvent.ACTION_POINTER_UP:
                mode = NONE;
                lastEvent = null;
                break;
            case MotionEvent.ACTION_MOVE:
                if (!isOutSide) {
                    if (mode == DRAG) {
                        isZoomAndRotate = false;
                        view.animate().x(event.getRawX() + xCoOrdinate).y(event.getRawY() + yCoOrdinate).setDuration(0).start();
                    }
                    if (mode == ZOOM && event.getPointerCount() == 2) {
                        float newDist1 = spacing(event);
                        if (newDist1 > 10f) {
                            float scale = newDist1 / oldDist * view.getScaleX();
                            view.setScaleX(scale);
                            view.setScaleY(scale);
                        }
                        if (lastEvent != null) {
                            newRot = rotation(event);
                            view.setRotation((float) (view.getRotation() + (newRot - d)));
                        }
                    }
                }
                break;
        }
    }
    private float rotation(MotionEvent event) {
        double delta_x = (event.getX(0) - event.getX(1));
        double delta_y = (event.getY(0) - event.getY(1));
        double radians = Math.atan2(delta_y, delta_x);
        return (float) Math.toDegrees(radians);
    }

    private float spacing(MotionEvent event) {
        float x = event.getX(0) - event.getX(1);
        float y = event.getY(0) - event.getY(1);
        return (int) Math.sqrt(x * x + y * y);
    }

    private void midPoint(PointF point, MotionEvent event) {
        float x = event.getX(0) + event.getX(1);
        float y = event.getY(0) + event.getY(1);
        point.set(x / 2, y / 2);
    }
}