package com.Appzia.addpanda.Screens;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.icu.text.SimpleDateFormat;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.Appzia.addpanda.Adapter.ViewPagerAdapter;
import com.Appzia.addpanda.Adapter.trenddingImageAdapter;
import com.Appzia.addpanda.Adapter.trendingSubcateAdapter;
import com.Appzia.addpanda.MainActivity;
import com.Appzia.addpanda.R;
import com.Appzia.addpanda.Util.Constant.Constant;
import com.Appzia.addpanda.Webservice.Webservice;
import com.Appzia.addpanda.databinding.ActivityTrendingBinding;
import com.facebook.shimmer.ShimmerFrameLayout;
import com.google.android.material.bottomappbar.BottomAppBar;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.shape.CornerFamily;
import com.google.android.material.shape.MaterialShapeDrawable;
import com.squareup.picasso.Picasso;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.util.Date;
import java.util.Objects;

public class trendingActivity extends AppCompatActivity {

    ActivityTrendingBinding binding;


    public static trenddingImageAdapter adapter;
    SharedPreferences shNew;
    String s1;
    public static Context mContext;
    public static String template_idKey;
    public static trendingActivity trendingActivity2;
    public static AppCompatButton autoClick;
    String imageKey;
    public static trendingSubcateAdapter trendingSubcateAdapter;
    public static String token;
    String category_id = "1";
    public static RecyclerView.LayoutManager layoutManager;
    public static ViewPagerAdapter viewPagerAdapter;

    public static ImageView viewPagerBackImage;

    public static LinearLayout CoLayout;
    public static ShimmerFrameLayout shimmerFrameLayout;

    //progress bar declaration


    public static RecyclerView recyclerView, subCatRecview;
    public static String categoryidKey, sub_cat_id;

    String path;
    public static File imageFile;
    String clickedKey;

    String BasicKey;
    String categoryNameKey;
    public static String Lang = "English";

    @Override
    public void onResume() {
        super.onResume();

        try {
            BasicKey = getIntent().getStringExtra(Constant.BasicKeyMain);
         //   Toast.makeText(mContext, BasicKey, Toast.LENGTH_SHORT).show();
        } catch (Exception ex) {

        }

        SharedPreferences sh = getSharedPreferences("MySharedPref", Context.MODE_PRIVATE);
        token = sh.getString("TOKEN_SF", "");
        //progress bar declaration

        CoLayout = (LinearLayout) findViewById(R.id.CoLayout);
        shimmerFrameLayout = (ShimmerFrameLayout) findViewById(R.id.shimmerFrameLayout);

        subCatRecview = (RecyclerView) findViewById(R.id.subCatRecview);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        autoClick = (AppCompatButton) findViewById(R.id.autoClick);

        viewPagerBackImage = (ImageView) findViewById(R.id.viewPagerBackImage);


        try {

            categoryNameKey = getIntent().getStringExtra("categoryNameKey");
        } catch (Exception ignored) {
        }
        try {

            //  Toast.makeText(mContext, BasicKey, Toast.LENGTH_SHORT).show();
            String nameKey = getIntent().getStringExtra("nameKey");
            clickedKey = getIntent().getStringExtra("clickedKey");

            categoryidKey = getIntent().getStringExtra("categoryidKey");


           try {
                imageKey = getIntent().getStringExtra("imageKey");
               Picasso.get().load(imageKey).placeholder(R.color.white).into(binding.viewPagerBackImage);
           }catch (Exception ignored){}

            try {
                Constant.setSfFunction(mContext);
                Constant.setSF.putString("originalImageKey", imageKey);
                Constant.setSF.apply();
            } catch (Exception ignored) {
            }



            sub_cat_id = getIntent().getStringExtra("sub_cat_idKey");

            //   Picasso.get().load(imageKey).into(binding.viewPagerBackImage);

            // Toast.makeText(mContext, nameKey, Toast.LENGTH_SHORT).show();
            Constant.NetworkCheck(mContext);
            if ((Constant.wifiInfo != null && Constant.wifiInfo.isConnected()) || (Constant.mobileInfo != null && Constant.mobileInfo.isConnected())) {
                Webservice.get_Template_list(mContext, token, categoryidKey, sub_cat_id, trendingActivity.this,Lang);

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
            Constant.NetworkCheck(mContext);
            if ((Constant.wifiInfo != null && Constant.wifiInfo.isConnected()) || (Constant.mobileInfo != null && Constant.mobileInfo.isConnected())) {
                Webservice.get_frame_list(mContext, token, trendingActivity.this, "", "", "");

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


            try {
                Constant.NetworkCheck(mContext);
                if ((Constant.wifiInfo != null && Constant.wifiInfo.isConnected()) || (Constant.mobileInfo != null && Constant.mobileInfo.isConnected())) {
                    Webservice.get_sub_category_TrendingActivity(mContext, categoryidKey, token, trendingActivity.this);

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

            } catch (Exception ignored) {
            }


        } catch (Exception ex) {
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityTrendingBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        mContext = binding.getRoot().getContext();


        Objects.requireNonNull(getSupportActionBar()).hide();
        Window window = this.getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.setStatusBarColor(this.getResources().getColor(R.color.appThemeColor));
        trendingActivity2 = this;


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
                Intent i = new Intent(getApplicationContext(), MainActivity.class);
                i.putExtra("editKey", "editFab");
                startActivity(i);
            }
        });

        binding.img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        binding.backArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                onBackPressed();


            }
        });


        binding.edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Bitmap bitmap = Bitmap.createBitmap(binding.viepagerCroperId.getWidth(), binding.viepagerCroperId.getHeight(), Bitmap.Config.ARGB_8888);
                    Canvas canvas = new Canvas(bitmap);
                    binding.viepagerCroperId.draw(canvas);


                    ByteArrayOutputStream stream = new ByteArrayOutputStream();
                    bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
                    byte[] byteArray = stream.toByteArray();

                    String saveThis = Base64.encodeToString(byteArray, Base64.DEFAULT);
                    Log.d("byteArray", String.valueOf(saveThis));

                    SharedPreferences sharedPreferences = getSharedPreferences("MySharedPref", MODE_PRIVATE);
                    SharedPreferences.Editor myEdit = sharedPreferences.edit();
                    myEdit.putString("imgData", saveThis);
                    myEdit.apply();


                    if (BasicKey.equals(Constant.mainKey)) {
                        Intent i = new Intent(getApplicationContext(), editFameActivity.class);
                        i.putExtra("categoryidKey", categoryidKey);
                        i.putExtra("sub_cat_idKey", sub_cat_id);
                        i.putExtra("template_idKeyKey", template_idKey);
                        startActivity(i);
                    } else if (BasicKey.equals(Constant.basicKey)) {
                        Intent i = new Intent(getApplicationContext(), Basic_editFrameActivity.class);
                        i.putExtra("categoryidKey", categoryidKey);
                        i.putExtra("sub_cat_idKey", sub_cat_id);
                        i.putExtra("template_idKeyKey", template_idKey);
                        startActivity(i);
                    } else if (BasicKey.equals(Constant.scratchKey)) {
                        Intent i = new Intent(getApplicationContext(), Basic_editFrameActivity.class);
                        i.putExtra("categoryidKey", categoryidKey);
                        i.putExtra("sub_cat_idKey", sub_cat_id);
                        i.putExtra("template_idKeyKey", template_idKey);
                        startActivity(i);
                    }


                } catch (Exception ex) {
                    Log.d("Bitmap Error", ex.getMessage());
                }
            }
        });

        binding.gtranslate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //  startActivity(new Intent(getApplicationContext(),EditActivity.class));
            }
        });

        binding.download.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Bitmap bitmap = Bitmap.createBitmap(binding.viepagerCroperId.getWidth(), binding.viepagerCroperId.getHeight(), Bitmap.Config.ARGB_8888);
                Canvas canvas = new Canvas(bitmap);
                binding.viepagerCroperId.draw(canvas);

                getImageUri(mContext, bitmap);
                Uri selectedImageUri = Uri.parse(path);

                String selectedImagePath = getRealPathFromURIForGallery(selectedImageUri);
                imageFile = new File(selectedImagePath);

                Log.d("#imagefile", String.valueOf(imageFile));

                Constant.NetworkCheck(mContext);
                if ((Constant.wifiInfo != null && Constant.wifiInfo.isConnected()) || (Constant.mobileInfo != null && Constant.mobileInfo.isConnected())) {
                    Webservice.create_content(mContext, token, categoryidKey, sub_cat_id, template_idKey, imageFile);

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


//                FileOutputStream outStream = null;
//
//                // Write to SD Card
//                try {
//                    File sdCard = Environment.getExternalStorageDirectory();
//                    File dir = new File(sdCard.getAbsolutePath() + "/DCIM");
//                    dir.mkdirs();
//
//                    String fileName = String.format("%d.png", System.currentTimeMillis());
//                    File outFile = new File(dir, fileName);
//
//                    outStream = new FileOutputStream(outFile);
//                    bitmap.compress(Bitmap.CompressFormat.PNG, 100, outStream);
//                    outStream.flush();
//                    outStream.close();
//
//                    Log.d("downloadImgLOg", "onPictureTaken - wrote to " + outFile.getAbsolutePath());
//                    Toast.makeText(getApplicationContext(), "Download Successful : \n" + fileName, Toast.LENGTH_SHORT).show();
//
//                } catch (FileNotFoundException e) {
////                    print("FNF");
//                    Log.d("FileNotFoundException", e.getMessage());
//                    e.printStackTrace();
//                } catch (IOException e) {
//                    Log.d("IOException", e.getMessage());
//                } finally {
//
//                }
            }
        });

        binding.autoClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences shNew = getSharedPreferences("MySharedPref", Context.MODE_PRIVATE);
                String s1 = shNew.getString("imagekey", "");
                String sub_cat_idKey = shNew.getString("sub_cat_idKey", "");
                String category_idKey = shNew.getString("category_idKey", "");
                template_idKey = shNew.getString("template_idKey", "");
//                binding.viewPagerBackImage.setImageResource(Integer.parseInt(s1));

                Picasso.get().load(s1).into(binding.viewPagerBackImage);


                //  Webservice.get_frame_list(mContext, token, trendingActivity.this, category_idKey, sub_cat_idKey, template_idKey);


            }
        });

        binding.gtranslate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (binding.cardview.getVisibility() == View.VISIBLE) {

                    binding.cardview.setVisibility(View.GONE);
                } else if (binding.cardview.getVisibility() == View.GONE) {
                    binding.cardview.setVisibility(View.VISIBLE);
                }
            }
        });

        binding.english.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.cardview.setVisibility(View.GONE);

                Lang = "English";
                Constant.NetworkCheck(mContext);
                if ((Constant.wifiInfo != null && Constant.wifiInfo.isConnected()) || (Constant.mobileInfo != null && Constant.mobileInfo.isConnected())) {
                    Webservice.get_Template_list(mContext, token, categoryidKey, sub_cat_id, trendingActivity.this,Lang);

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

        binding.hindi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.cardview.setVisibility(View.GONE);
                Lang = "Hindi";

                Constant.NetworkCheck(mContext);
                if ((Constant.wifiInfo != null && Constant.wifiInfo.isConnected()) || (Constant.mobileInfo != null && Constant.mobileInfo.isConnected())) {
                    Webservice.get_Template_list(mContext, token, categoryidKey, sub_cat_id, trendingActivity.this,Lang);

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

        binding.marathi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.cardview.setVisibility(View.GONE);
                Lang = "marathi";
                Constant.NetworkCheck(mContext);
                if ((Constant.wifiInfo != null && Constant.wifiInfo.isConnected()) || (Constant.mobileInfo != null && Constant.mobileInfo.isConnected())) {
                    Webservice.get_Template_list(mContext, token, categoryidKey, sub_cat_id, trendingActivity.this,Lang);

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

    public static void setAdapter() {
        // set a LinearLayoutManager with default horizontal orientation and false value for reverseLayout to show the items from start to end
        layoutManager = new GridLayoutManager(mContext, 3);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new trenddingImageAdapter(mContext, autoClick);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        trendingSubcateAdapter = new trendingSubcateAdapter(mContext, token, categoryidKey, trendingActivity2);
        subCatRecview.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false));
        subCatRecview.setAdapter(trendingSubcateAdapter);
        trendingSubcateAdapter.notifyDataSetChanged();


    }


    public void setFrameAdapter() {

        try {
            viewPagerAdapter = new ViewPagerAdapter(mContext);
            binding.viewPagerId.setAdapter(viewPagerAdapter);

            binding.dotsIndicator.setViewPager(binding.viewPagerId);
            viewPagerAdapter.notifyDataSetChanged();


        } catch (Exception ignored) {
        }


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

    public void onBackPressed2() {
        if (clickedKey.equals("clickedKey")) {

            startActivity(new Intent(getApplicationContext(), MainActivity.class));
        }
    }


    @Override
    public void onBackPressed() {

        super.onBackPressed();
//        try {
//            if (clickedKey.equals("clickedKey")) {
//                onBackPressed2();
//            } else {
//                super.onBackPressed();
//            }
//        } catch (Exception ex) {
//            super.onBackPressed();
//        }

    }

}


