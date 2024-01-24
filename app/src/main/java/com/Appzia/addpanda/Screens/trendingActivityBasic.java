package com.Appzia.addpanda.Screens;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.Appzia.addpanda.Adapter.ViewPagerAdapter;
import com.Appzia.addpanda.Adapter.trenddingImageAdapter;
import com.Appzia.addpanda.R;
import com.Appzia.addpanda.Util.Constant.Constant;
import com.Appzia.addpanda.Webservice.Webservice;
import com.Appzia.addpanda.databinding.ActivityTrendingBasicBinding;
import com.google.android.material.bottomappbar.BottomAppBar;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.shape.CornerFamily;
import com.google.android.material.shape.MaterialShapeDrawable;
import com.squareup.picasso.Picasso;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.util.Objects;

public class trendingActivityBasic extends AppCompatActivity {

    ActivityTrendingBasicBinding binding;

    public static ProgressBar progressBar;

    public static trenddingImageAdapter adapter;
    public static RelativeLayout relativelayout;
    SharedPreferences shNew;
    String s1;
    public static TextView isactivedummy;
    public static Context mContext;
    public static String template_idKey;
    public static AppCompatButton autoClick;
    String token;
    String category_id = "1";
    public static RecyclerView.LayoutManager layoutManager;
    public static ViewPagerAdapter viewPagerAdapter;

    public static ImageView viewPagerBackImage;

    //progress bar declaration


    public static RecyclerView recyclerView;
    String categoryidKey, sub_cat_id;

    String path;
    public static File imageFile;
    String clickedKey;


    @Override
    public void onStart() {
        super.onStart();

        SharedPreferences sh = getSharedPreferences("MySharedPref", Context.MODE_PRIVATE);

        token = sh.getString("TOKEN_SF", "");


        //progress bar declaration
        progressBar = (ProgressBar) findViewById(R.id.progressbar);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        autoClick = (AppCompatButton) findViewById(R.id.autoClick);
        relativelayout = (RelativeLayout) findViewById(R.id.relativelayout);

        viewPagerBackImage = (ImageView) findViewById(R.id.viewPagerBackImage);

        try {

            String nameKey = getIntent().getStringExtra("nameKey");
            clickedKey = getIntent().getStringExtra("clickedKey");
            categoryidKey = getIntent().getStringExtra("categoryidKey");
            sub_cat_id = getIntent().getStringExtra("sub_cat_idKey");

            Constant.NetworkCheck(mContext);
            if ((Constant.wifiInfo != null && Constant.wifiInfo.isConnected()) || (Constant.mobileInfo != null && Constant.mobileInfo.isConnected())) {
                Webservice.get_Template_list_Basic(mContext, token, categoryidKey, sub_cat_id, trendingActivityBasic.this);
                try {
                    Webservice.get_frame_list_Basic(mContext, token, trendingActivityBasic.this, categoryidKey, sub_cat_id, template_idKey);

                } catch (Exception ignored) {
                }
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




        } catch (Exception ex) {
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityTrendingBasicBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        mContext = binding.getRoot().getContext();


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
        isactivedummy = findViewById(R.id.isactivedummy);
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


                    // for set background in relative layout

                    //  Drawable dr = new BitmapDrawable(bitmap);

                    ByteArrayOutputStream stream = new ByteArrayOutputStream();
                    bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
                    byte[] byteArray = stream.toByteArray();

                    String saveThis = Base64.encodeToString(byteArray, Base64.DEFAULT);
                    Log.d("byteArray", String.valueOf(saveThis));

                    SharedPreferences sharedPreferences = getSharedPreferences("MySharedPref", MODE_PRIVATE);
                    SharedPreferences.Editor myEdit = sharedPreferences.edit();
                    myEdit.putString("imgData", saveThis);
                    myEdit.apply();

                    Intent i = new Intent(getApplicationContext(), Basic_editFrameActivity.class);
                    i.putExtra("categoryidKey", categoryidKey);
                    i.putExtra("sub_cat_idKey", sub_cat_id);
                    i.putExtra("template_idKeyKey", template_idKey);
                    startActivity(i);


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

                getImageUri(getApplicationContext(), bitmap);
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


                //  WebserviceRetrofit.get_frame_list(mContext, token, trendingActivity.this, category_idKey, sub_cat_idKey, template_idKey);


            }
        });

    }

    public static void setAdapter() {
        // set a LinearLayoutManager with default horizontal orientation and false value for reverseLayout to show the items from start to end
        layoutManager = new GridLayoutManager(mContext, 3);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new trenddingImageAdapter(mContext, autoClick, relativelayout, isactivedummy);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();


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
        path = MediaStore.Images.Media.insertImage(inContext.getContentResolver(), inImage, "Title", null);
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
        super.onBackPressed();
    }
}