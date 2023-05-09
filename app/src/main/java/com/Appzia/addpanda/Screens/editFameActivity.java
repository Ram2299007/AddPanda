package com.Appzia.addpanda.Screens;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.content.res.ColorStateList;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.PointF;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.Appzia.addpanda.Adapter.font_adapter_new;
import com.Appzia.addpanda.Adapter.frameAdapter;
import com.Appzia.addpanda.Adapter.shapeAdapter;
import com.Appzia.addpanda.Adapter.textColorAdapter;
import com.Appzia.addpanda.MainActivity;
import com.Appzia.addpanda.Model.fontModelNew;
import com.Appzia.addpanda.Model.shapeModel;
import com.Appzia.addpanda.Model.textColorModel;
import com.Appzia.addpanda.R;
import com.Appzia.addpanda.Util.Constant.Constant;
import com.Appzia.addpanda.Webservice.Webservice;
import com.Appzia.addpanda.databinding.ActivityEditFameBinding;
import com.Appzia.addpanda.sharedPreference.PreferenceManager;
import com.google.android.material.bottomappbar.BottomAppBar;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.shape.CornerFamily;
import com.google.android.material.shape.MaterialShapeDrawable;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class editFameActivity extends AppCompatActivity {

    ActivityEditFameBinding binding;
    Context mContext;
    String token;


    public static final int REQUEST_ID_MULTIPLE_PERMISSIONS = 1;
    private static final int CAMERA_REQUEST = 1888;
    private static final int MY_CAMERA_PERMISSION_CODE = 100;
    SharedPreferences sharedPreferences, sharedPreferencesForShapeNull, sharedPreferencesForTextNull, sharedPreferenceStorage, sharedPreferenceStorageNull;
    String sTExt;
    LinearLayout cameraLT, galleryLT;
    TextView cameratxt, gallerytxt;
    ImageView cameraimg, galleryimg;
    LinearLayout cancelColor;
    RelativeLayout cancelIMg;

    RecyclerView recyclerView, colorRecyclerView, fontrecyclerview;
    Animation animation, animation2;
    SharedPreferences sh;
    SharedPreferences shFont;
    SharedPreferences shText;
    SharedPreferences shShape;
    SharedPreferences shshapeSelect;
    String shStorageFetch;
    String sshape, shShapeSelectText;
    Button editButton;

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

    private shapeModel[] model;
    private shapeAdapter adapter;

    private textColorModel[] colorModel;
    private textColorAdapter colorAdapter;


    private fontModelNew[] newFontModel;
    private font_adapter_new newFontAdapter;

    BottomSheetDialog dialogShape;

    EditText enterText;
    Button enterButton;
    LinearLayout cancelShapes;
    PreferenceManager preferenceManager;
    File file_camera, file_camera_final1;
    String previouslyEncodedGalley, previouslyEncodedCamera;
    public Dialog dialogLayout;

    TextView titleText;
    String getImg, getImg2, getImg3;
    byte[] byteArray;

    SharedPreferences sharedPreferenceFontText, shGettextFont;
    public String getCurrentTextForfont = "Ad Panda";

    public static frameAdapter frameadapter;
    String template_idKey;

    public static RelativeLayout mainRelativeLayout, mainLayout;
    String categoryid;
    String sub_cat_id;
    String template_id;

    String widthKey, heightKey;
    Animation goneAnime;



    @Override
    protected void onStart() {
        super.onStart();


        try {
            widthKey = getIntent().getStringExtra("widthKey");
            heightKey = getIntent().getStringExtra("heightKey");
            // here  navigation bar for  gone animation
            Constant.viewGoneAnimator(binding.coord);
            if (!widthKey.equals("")) {
                ViewGroup.LayoutParams params = binding.mainLayout.getLayoutParams();
                params.height = Integer.parseInt(heightKey);
                params.width = Integer.parseInt(widthKey);
                binding.mainLayout.setLayoutParams(params);
                binding.mainLayout.setBackgroundResource(R.drawable.bg_round_10);
                ColorStateList myColorStateList = ColorStateList.valueOf(getResources().getColor(R.color.white));
                binding.mainLayout.setBackgroundTintList(myColorStateList);





            } else {
            }
        } catch (Exception ignored) {
        }


        SharedPreferences sh = getSharedPreferences("MySharedPref", Context.MODE_PRIVATE);

        token = sh.getString("TOKEN_SF", "");
        template_idKey = sh.getString("template_idKey", "");

        mainRelativeLayout = (RelativeLayout) findViewById(R.id.mainRelativeLayout);
        mainLayout = (RelativeLayout) findViewById(R.id.mainLayout);

        categoryid = getIntent().getStringExtra("categoryidKey");

        sub_cat_id = getIntent().getStringExtra("sub_cat_idKey");
        template_id = getIntent().getStringExtra("template_idKeyKey");
//        Constant.frameList.clear();
//        Constant.frameListTwo.clear();

        Constant.NetworkCheck(mContext);
        if ((Constant.wifiInfo != null && Constant.wifiInfo.isConnected()) || (Constant.mobileInfo != null && Constant.mobileInfo.isConnected())) {
            Webservice.get_frame_list_two(mContext, token, editFameActivity.this, "", "", "");

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

        Constant.getSfFuncion(getApplicationContext());
        String imgDta = Constant.getSF.getString("originalImageKey", "empty");
        Log.d("##imgDta", imgDta);

        clearSharedPref();

    }




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityEditFameBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        mContext = this;


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


                        //   getSupportFragmentManager().beginTransaction().replace(R.id.editFrameActivityFrame, new mainFragment()).commit();


                        new AlertDialog.Builder(editFameActivity.this).setTitle("Warning").setMessage("If you leave this page , you loss all your work.\nAre you sure want to exit?")


                                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int which) {

                                        Intent i = new Intent(getApplicationContext(), MainActivity.class);
                                        i.putExtra("editKey", "editHome");
                                        startActivity(i);
                                    }

                                })

                                .setNegativeButton(android.R.string.no, null).setIcon(null).show();


                        break;

                    case R.id.profile:

                        //     getSupportFragmentManager().beginTransaction().replace(R.id.editFrameActivityFrame, new profileFragment()).commit();


                        new AlertDialog.Builder(editFameActivity.this).setTitle("Warning").setMessage("If you leave this page , you loss all your work.\nAre you sure want to exit?")


                                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int which) {

                                        Intent i2 = new Intent(getApplicationContext(), MainActivity.class);
                                        i2.putExtra("editKey", "editProfile");
                                        startActivity(i2);

                                    }

                                })

                                .setNegativeButton(android.R.string.no, null).setIcon(null).show();


                        break;
                    case R.id.category:

                        //getSupportFragmentManager().beginTransaction().replace(R.id.editFrameActivityFrame, new categoryTabFragment()).commit();


                        new AlertDialog.Builder(editFameActivity.this).setTitle("Warning").setMessage("If you leave this page , you loss all your work.\nAre you sure want to exit?")


                                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int which) {

                                        Intent i3 = new Intent(getApplicationContext(), MainActivity.class);
                                        i3.putExtra("editKey", "editCategory");
                                        startActivity(i3);

                                    }

                                })

                                .setNegativeButton(android.R.string.no, null).setIcon(null).show();


                        break;
                    case R.id.download:

                        //  getSupportFragmentManager().beginTransaction().replace(R.id.editFrameActivityFrame, new DownloadsFragment()).commit();


                        new AlertDialog.Builder(editFameActivity.this).setTitle("Warning").setMessage("If you leave this page , you loss all your work.\nAre you sure want to exit?")


                                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int which) {


                                        Intent i4 = new Intent(getApplicationContext(), MainActivity.class);
                                        i4.putExtra("editKey", "editDownload");
                                        startActivity(i4);

                                    }

                                })

                                .setNegativeButton(android.R.string.no, null).setIcon(null).show();


                        break;
                }
                return true;


            }
        });
        binding.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // getSupportFragmentManager().beginTransaction().replace(R.id.mainActivityFrame, new createFragment()).commit();
                new AlertDialog.Builder(editFameActivity.this).setTitle("Warning").setMessage("If you leave this page , you loss all your work.\nAre you sure want to exit?")


                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {

                                Intent i = new Intent(getApplicationContext(), MainActivity.class);
                                i.putExtra("editKey", "editFab");
                                startActivity(i);

                            }

                        })

                        .setNegativeButton(android.R.string.no, null).setIcon(null).show();


            }
        });
        try {

            try {
                SharedPreferences sh = getSharedPreferences("MySharedPref", MODE_PRIVATE);
                String s1 = sh.getString("imgData", "");
                byteArray = Base64.decode(s1, Base64.DEFAULT);


            } catch (Exception ex) {
                Toast.makeText(this, ex.getMessage(), Toast.LENGTH_SHORT).show();
            }

            Bitmap bmp = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);
            Drawable dr = new BitmapDrawable(bmp);
            binding.mainLayout.setBackgroundDrawable(dr);
        } catch (Exception ex) {
            Toast.makeText(this, "BITMAP ERROR :" + ex.getMessage(), Toast.LENGTH_SHORT).show();
        }
        //bottom for editfram from fragment activity


        preferenceManager = PreferenceManager.getInstance(getApplicationContext());

        binding.backArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //  Constant.collapse(binding.coord);

                onBackPressed();

            }
        });
        // Inflate the layout for this fragment


        dialogLayout = new Dialog(editFameActivity.this);
        dialogLayout.setContentView(R.layout.edit_frame);
        dialogLayout.setCanceledOnTouchOutside(true);
        dialogLayout.setCancelable(true);
        dialogLayout.getWindow().getAttributes().windowAnimations = R.style.PauseDialogAnimation;


        cameraLT = dialogLayout.findViewById(R.id.cameraLT);
        galleryLT = dialogLayout.findViewById(R.id.galleryLT);
        gallerytxt = dialogLayout.findViewById(R.id.gallerytxt);
        cameratxt = dialogLayout.findViewById(R.id.cameratxt);
        galleryimg = dialogLayout.findViewById(R.id.galleryimg);
        cameraimg = dialogLayout.findViewById(R.id.cameraimg);


        binding.addlogo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if (binding.addlogo.isPressed()) {
                    binding.text1.performClick();
                }
//                scrollListenerTouchFALSE();
                //      binding.editText.setVisibility(View.GONE);


                binding.treding.setText("Create Frame");

                animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fade_in_center2);

                binding.treding.setText("Create Frame");
                binding.selectframeid.setVisibility(View.VISIBLE);
                binding.selectframeid.setAnimation(animation);
                binding.selectframeid2.setVisibility(View.GONE);
                binding.right.setVisibility(View.VISIBLE);
                binding.wrong.setVisibility(View.VISIBLE);


                cameraLT.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (cameraLT.isPressed()) {
                            cameraimg.performClick();
                            cameratxt.performClick();
                        }

//                        assert getFragmentManager() != null;
//                        getFragmentManager().beginTransaction().replace(R.id.mainActivityFrame, new addlogoFragment()).commit();

                        //Using intent for opening a camera
                        checkAndRequestPermissions();
                        // requestPermissions(new String[]{Manifest.permission.CAMERA}, MY_CAMERA_PERMISSION_CODE);
                        Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                        //    cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT,CAMERA_REQUEST);
                        dialogLayout.dismiss();

                        try {

                            startActivityForResult(cameraIntent, CAMERA_REQUEST);
                        } catch (Exception ex) {
                            Log.d("camera_Exception", ex.getMessage());

                        }


                    }
                });

                galleryLT.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (galleryLT.isPressed()) {
                            galleryimg.performClick();
                            gallerytxt.performClick();
                        }

                        checkAndRequestPermissions();
                        try {
                            Intent galleryIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                            startActivityForResult(galleryIntent, 0);

                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });


                dialogLayout.show();

                Window window = dialogLayout.getWindow();
                window.setLayout(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            }
        });


        binding.download.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //  assert getFragmentManager() != null;
                new AlertDialog.Builder(editFameActivity.this).setTitle("Warning").setMessage("Are you sure for download your frame ?")


                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {

                                binding.right.performClick();
                                try {

                                    Bitmap bitmap = Bitmap.createBitmap(binding.mainLayout.getWidth(), binding.mainLayout.getHeight(), Bitmap.Config.ARGB_8888);
                                    Canvas canvas = new Canvas(bitmap);
                                    binding.mainLayout.draw(canvas);


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


                                    // getSupportFragmentManager().beginTransaction().replace(R.id.mainActivityFrame, new downloadimageFragment()).commit();
                                    Intent i = new Intent(getApplicationContext(), downloadImageActivity.class);
                                    i.putExtra("sub_cat_idkey", sub_cat_id);
                                    i.putExtra("template_idkey", template_id);
                                    i.putExtra("cat_idkey", categoryid);
                                    i.putExtra("heightKey", String.valueOf(heightKey));
                                    i.putExtra("widthKey", String.valueOf(widthKey));
                                    startActivity(i);

                                } catch (Exception ex) {
                                    Log.d("Bitmap Error", ex.getMessage());
                                }
                            }

                        })

                        .setNegativeButton(android.R.string.no, null).setIcon(null).show();


            }
        });


        final Dialog dlg2 = new Dialog(editFameActivity.this);
        dlg2.setContentView(R.layout.add_text_diialogue);
        dlg2.setCanceledOnTouchOutside(true);
        dlg2.setCancelable(true);
        dlg2.getWindow().getAttributes().windowAnimations = R.style.PauseDialogAnimation;

        enterText = (EditText) dlg2.findViewById(R.id.enterText);
        enterButton = (Button) dlg2.findViewById(R.id.addButton);
        editButton = (Button) dlg2.findViewById(R.id.editButton);
        titleText = (TextView) dlg2.findViewById(R.id.titleText);


        binding.editTextOnly.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //for text only

                editButton.setVisibility(View.VISIBLE);
                enterButton.setVisibility(View.GONE);
                titleText.setText("Edit Text");

                try {

                    SharedPreferences shText2 = getSharedPreferences("TEXTPref", Context.MODE_PRIVATE);

                    String sTExt2 = shText2.getString("text", "");


                    if (sTExt2.equals("SELECTED1")) {

                        try {

                            enterText.setText(binding.editTextFrag.getText().toString());
                            enterText.setSelection(enterText.getText().length());


                        } catch (Exception e) {
                        }
                    } else if (sTExt2.equals("SELECTED2")) {

                        try {

                            enterText.setText(binding.editTextFrag2.getText().toString());
                            enterText.setSelection(enterText.getText().length());


                        } catch (Exception e) {
                        }
                    } else if (sTExt2.equals("SELECTED3")) {

                        try {

                            enterText.setText(binding.editTextFrag3.getText().toString());
                            enterText.setSelection(enterText.getText().length());


                        } catch (Exception e) {
                        }
                    } else if (sTExt2.equals("SELECTED4")) {

                        try {


                            enterText.setText(binding.editTextFrag4.getText().toString());
                            enterText.setSelection(enterText.getText().length());

                        } catch (Exception e) {
                        }
                    } else if (sTExt2.equals("SELECTED5")) {

                        try {


                            enterText.setText(binding.editTextFrag5.getText().toString());
                            enterText.setSelection(enterText.getText().length());


                        } catch (Exception e) {
                        }
                    } else if (sTExt2.equals("SELECTED6")) {

                        try {

                            binding.editTextFrag6.setTextSize(22);
                            enterText.setText(binding.editTextFrag6.getText().toString());
                            enterText.setSelection(enterText.getText().length());


                        } catch (Exception e) {
                        }
                    } else if (sTExt2.equals("SELECTED7")) {

                        try {

                            binding.editTextFrag7.setTextSize(22);
                            enterText.setText(binding.editTextFrag7.getText().toString());
                            enterText.setSelection(enterText.getText().length());


                        } catch (Exception e) {
                        }
                    } else if (sTExt2.equals("SELECTED8")) {

                        try {

                            binding.editTextFrag8.setTextSize(22);
                            enterText.setText(binding.editTextFrag8.getText().toString());
                            enterText.setSelection(enterText.getText().length());


                        } catch (Exception e) {
                        }
                    } else if (sTExt2.equals("SELECTED9")) {

                        try {

                            binding.editTextFrag9.setTextSize(22);
                            enterText.setText(binding.editTextFrag9.getText().toString());
                            enterText.setSelection(enterText.getText().length());


                        } catch (Exception e) {
                        }
                    } else if (sTExt2.equals("SELECTED10")) {

                        try {

                            binding.editTextFrag10.setTextSize(22);
                            enterText.setText(binding.editTextFrag10.getText().toString());
                            enterText.setSelection(enterText.getText().length());


                        } catch (Exception e) {
                        }
                    } else {
                        Toast.makeText(editFameActivity.this, "No any one text selected !", Toast.LENGTH_SHORT).show();
                    }


                } catch (Exception ex) {
                }


                editButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        try {

                            shText = getSharedPreferences("TEXTPref", Context.MODE_PRIVATE);

                            sTExt = shText.getString("text", "");


                            if (sTExt.equals("SELECTED1")) {

                                try {

                                    if (enterText.length() <= 3) {
                                        String text = enterText.getText().toString();
                                        binding.editTextFrag.setText(text);
                                        binding.editTextFrag.setTextSize(30);
                                        enterText.setText("");
                                        dlg2.dismiss();
                                    } else {

                                        String text = enterText.getText().toString();
                                        binding.editTextFrag.setText(text);
                                        binding.editTextFrag.setTextSize(22);
                                        enterText.setText("");
                                        dlg2.dismiss();

                                    }


                                } catch (Exception e) {
                                }
                            } else if (sTExt.equals("SELECTED2")) {

                                try {

                                    if (enterText.length() <= 3) {
                                        String text = enterText.getText().toString();
                                        binding.editTextFrag2.setText(text);
                                        binding.editTextFrag2.setTextSize(30);
                                        enterText.setText("");
                                        dlg2.dismiss();
                                    } else {

                                        String text = enterText.getText().toString();
                                        binding.editTextFrag2.setText(text);
                                        binding.editTextFrag2.setTextSize(22);
                                        enterText.setText("");
                                        dlg2.dismiss();

                                    }

                                } catch (Exception e) {
                                }
                            } else if (sTExt.equals("SELECTED3")) {

                                try {

                                    if (enterText.length() <= 3) {
                                        String text = enterText.getText().toString();
                                        binding.editTextFrag3.setText(text);
                                        binding.editTextFrag3.setTextSize(30);
                                        enterText.setText("");
                                        dlg2.dismiss();
                                    } else {

                                        String text = enterText.getText().toString();
                                        binding.editTextFrag3.setText(text);
                                        binding.editTextFrag3.setTextSize(22);
                                        enterText.setText("");
                                        dlg2.dismiss();

                                    }

                                } catch (Exception e) {
                                }
                            } else if (sTExt.equals("SELECTED4")) {

                                try {

                                    if (enterText.length() <= 3) {
                                        String text = enterText.getText().toString();
                                        binding.editTextFrag4.setText(text);
                                        binding.editTextFrag4.setTextSize(30);
                                        enterText.setText("");
                                        dlg2.dismiss();
                                    } else {

                                        String text = enterText.getText().toString();
                                        binding.editTextFrag4.setText(text);
                                        binding.editTextFrag4.setTextSize(22);
                                        enterText.setText("");
                                        dlg2.dismiss();

                                    }
                                } catch (Exception e) {
                                }
                            } else if (sTExt.equals("SELECTED5")) {

                                try {

                                    if (enterText.length() <= 3) {
                                        String text = enterText.getText().toString();
                                        binding.editTextFrag5.setText(text);
                                        binding.editTextFrag5.setTextSize(30);
                                        enterText.setText("");
                                        dlg2.dismiss();
                                    } else {

                                        String text = enterText.getText().toString();
                                        binding.editTextFrag5.setText(text);
                                        binding.editTextFrag5.setTextSize(22);
                                        enterText.setText("");
                                        dlg2.dismiss();

                                    }

                                } catch (Exception e) {
                                }
                            } else if (sTExt.equals("SELECTED6")) {

                                try {

                                    if (enterText.length() <= 3) {
                                        String text = enterText.getText().toString();
                                        binding.editTextFrag6.setText(text);
                                        binding.editTextFrag6.setTextSize(30);
                                        enterText.setText("");
                                        dlg2.dismiss();
                                    } else {

                                        String text = enterText.getText().toString();
                                        binding.editTextFrag6.setText(text);
                                        binding.editTextFrag6.setTextSize(22);
                                        enterText.setText("");
                                        dlg2.dismiss();

                                    }

                                } catch (Exception e) {
                                }
                            } else if (sTExt.equals("SELECTED7")) {

                                try {

                                    if (enterText.length() <= 3) {
                                        String text = enterText.getText().toString();
                                        binding.editTextFrag7.setText(text);
                                        binding.editTextFrag7.setTextSize(30);
                                        enterText.setText("");
                                        dlg2.dismiss();
                                    } else {

                                        String text = enterText.getText().toString();
                                        binding.editTextFrag7.setText(text);
                                        binding.editTextFrag7.setTextSize(22);
                                        enterText.setText("");
                                        dlg2.dismiss();

                                    }

                                } catch (Exception e) {
                                }
                            } else if (sTExt.equals("SELECTED8")) {

                                try {

                                    if (enterText.length() <= 3) {
                                        String text = enterText.getText().toString();
                                        binding.editTextFrag8.setText(text);
                                        binding.editTextFrag8.setTextSize(30);
                                        enterText.setText("");
                                        dlg2.dismiss();
                                    } else {

                                        String text = enterText.getText().toString();
                                        binding.editTextFrag8.setText(text);
                                        binding.editTextFrag8.setTextSize(22);
                                        enterText.setText("");
                                        dlg2.dismiss();

                                    }

                                } catch (Exception e) {
                                }
                            } else if (sTExt.equals("SELECTED9")) {

                                try {

                                    if (enterText.length() <= 3) {
                                        String text = enterText.getText().toString();
                                        binding.editTextFrag9.setText(text);
                                        binding.editTextFrag9.setTextSize(30);
                                        enterText.setText("");
                                        dlg2.dismiss();
                                    } else {

                                        String text = enterText.getText().toString();
                                        binding.editTextFrag9.setText(text);
                                        binding.editTextFrag9.setTextSize(22);
                                        enterText.setText("");
                                        dlg2.dismiss();

                                    }

                                } catch (Exception e) {
                                }
                            } else if (sTExt.equals("SELECTED10")) {

                                try {

                                    if (enterText.length() <= 3) {
                                        String text = enterText.getText().toString();
                                        binding.editTextFrag10.setText(text);
                                        binding.editTextFrag10.setTextSize(30);
                                        enterText.setText("");
                                        dlg2.dismiss();
                                    } else {

                                        String text = enterText.getText().toString();
                                        binding.editTextFrag10.setText(text);
                                        binding.editTextFrag10.setTextSize(22);
                                        enterText.setText("");
                                        dlg2.dismiss();

                                    }

                                } catch (Exception e) {
                                }
                            } else {
                                Toast.makeText(editFameActivity.this, "No any one text selected !", Toast.LENGTH_SHORT).show();
                            }


                        } catch (Exception ex) {
                        }
                    }
                });


                dlg2.show();

                Window window = dlg2.getWindow();
                window.setLayout(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);


            }
        });


        binding.addtext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (binding.addtext.isPressed()) {
                    binding.text2.performClick();
                }


                animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_in_bottom2);
                animation2 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_in_top_2);
                binding.treding.setText("Create Frame");
                binding.editFrame.setAnimation(animation);
                binding.selectframeid.setVisibility(View.GONE);
                binding.selectframeid2.setAnimation(animation2);
                binding.selectframeid2.setVisibility(View.VISIBLE);
                binding.addLayout.setAnimation(animation);
                binding.right.setVisibility(View.VISIBLE);
                binding.wrong.setVisibility(View.VISIBLE);


                editButton.setVisibility(View.GONE);
                enterButton.setVisibility(View.VISIBLE);
                titleText.setText("Add Text");


                enterButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String text = enterText.getText().toString();
                        if (enterText.getText().toString().equals("")) {

                            enterText.setError("Missing text ?");
                            // Toast.makeText(editFameActivity.this, "Missing Text ?", Toast.LENGTH_SHORT).show();
                        } else {


                            if (enterText.length() <= 3) {

                                if (binding.editTextFrag.getText().toString().equals("Enter your 1 text here")) {

                                    binding.editTextFrag.setText(text);

                                    binding.editTextFrag.setTextSize(30);
                                    binding.editTextFrag.setVisibility(View.VISIBLE);

                                    binding.editTextFrag.requestFocus();
                                    enterText.setText("");
                                    highlightText1();
                                    dlg2.dismiss();

                                } else if (binding.editTextFrag2.getText().toString().equals("Enter your 2 text here")) {

                                    binding.editTextFrag2.setText(text);

                                    binding.editTextFrag2.setTextSize(30);
                                    binding.editTextFrag2.setVisibility(View.VISIBLE);

                                    binding.editTextFrag2.requestFocus();
                                    enterText.setText("");
                                    highlightText1();
                                    dlg2.dismiss();

                                } else if (binding.editTextFrag3.getText().toString().equals("Enter your 3 text here")) {
                                    binding.editTextFrag3.setText(text);

                                    binding.editTextFrag3.setTextSize(30);
                                    binding.editTextFrag3.setVisibility(View.VISIBLE);

                                    binding.editTextFrag3.requestFocus();
                                    enterText.setText("");
                                    highlightText1();
                                    dlg2.dismiss();

                                } else if (binding.editTextFrag4.getText().toString().equals("Enter your 4 text here")) {
                                    binding.editTextFrag4.setText(text);

                                    binding.editTextFrag4.setTextSize(30);
                                    binding.editTextFrag4.setVisibility(View.VISIBLE);

                                    binding.editTextFrag4.requestFocus();
                                    enterText.setText("");
                                    highlightText1();
                                    dlg2.dismiss();

                                } else if (binding.editTextFrag5.getText().toString().equals("Enter your 5 text here")) {
                                    binding.editTextFrag5.setText(text);

                                    binding.editTextFrag5.setTextSize(30);
                                    binding.editTextFrag5.setVisibility(View.VISIBLE);

                                    binding.editTextFrag5.requestFocus();
                                    enterText.setText("");
                                    highlightText1();
                                    dlg2.dismiss();

                                } else if (binding.editTextFrag6.getText().toString().equals("Enter your 6 text here")) {
                                    binding.editTextFrag6.setText(text);

                                    binding.editTextFrag6.setTextSize(30);
                                    binding.editTextFrag6.setVisibility(View.VISIBLE);

                                    binding.editTextFrag6.requestFocus();
                                    enterText.setText("");
                                    highlightText1();
                                    dlg2.dismiss();

                                } else if (binding.editTextFrag7.getText().toString().equals("Enter your 7 text here")) {
                                    binding.editTextFrag7.setText(text);

                                    binding.editTextFrag7.setTextSize(30);
                                    binding.editTextFrag7.setVisibility(View.VISIBLE);

                                    binding.editTextFrag7.requestFocus();
                                    enterText.setText("");
                                    highlightText1();
                                    dlg2.dismiss();

                                } else if (binding.editTextFrag8.getText().toString().equals("Enter your 8 text here")) {
                                    binding.editTextFrag8.setText(text);

                                    binding.editTextFrag8.setTextSize(30);
                                    binding.editTextFrag8.setVisibility(View.VISIBLE);

                                    binding.editTextFrag8.requestFocus();
                                    enterText.setText("");
                                    highlightText1();
                                    dlg2.dismiss();

                                } else if (binding.editTextFrag9.getText().toString().equals("Enter your 9 text here")) {
                                    binding.editTextFrag9.setText(text);

                                    binding.editTextFrag9.setTextSize(30);
                                    binding.editTextFrag9.setVisibility(View.VISIBLE);

                                    binding.editTextFrag9.requestFocus();
                                    enterText.setText("");
                                    highlightText1();
                                    dlg2.dismiss();

                                } else if (binding.editTextFrag10.getText().toString().equals("Enter your 10 text here")) {
                                    binding.editTextFrag10.setText(text);

                                    binding.editTextFrag10.setTextSize(30);
                                    binding.editTextFrag10.setVisibility(View.VISIBLE);

                                    binding.editTextFrag10.requestFocus();
                                    enterText.setText("");
                                    highlightText1();
                                    dlg2.dismiss();

                                } else {
                                    Toast.makeText(editFameActivity.this, "You can add maximum 10 text !", Toast.LENGTH_SHORT).show();
                                    enterText.setText("");
                                    dlg2.dismiss();
                                }

                            } else {


                                if (binding.editTextFrag.getText().toString().equals("Enter your 1 text here")) {

                                    binding.editTextFrag.setText(text);
                                    binding.editTextFrag.setVisibility(View.VISIBLE);

                                    binding.editTextFrag.requestFocus();
                                    enterText.setText("");
                                    highlightText1();
                                    dlg2.dismiss();

                                } else if (binding.editTextFrag2.getText().toString().equals("Enter your 2 text here")) {


                                    binding.editTextFrag2.setText(text);
                                    binding.editTextFrag2.setVisibility(View.VISIBLE);

                                    binding.editTextFrag2.requestFocus();
                                    enterText.setText("");
                                    highlightText2();
                                    dlg2.dismiss();

                                } else if (binding.editTextFrag3.getText().toString().equals("Enter your 3 text here")) {
                                    binding.editTextFrag3.setText(text);
                                    binding.editTextFrag3.setVisibility(View.VISIBLE);

                                    binding.editTextFrag3.requestFocus();
                                    enterText.setText("");
                                    highlightText3();
                                    dlg2.dismiss();

                                } else if (binding.editTextFrag4.getText().toString().equals("Enter your 4 text here")) {

                                    binding.editTextFrag4.setText(text);
                                    binding.editTextFrag4.setVisibility(View.VISIBLE);

                                    binding.editTextFrag4.requestFocus();
                                    enterText.setText("");
                                    highlightText4();
                                    dlg2.dismiss();

                                } else if (binding.editTextFrag5.getText().toString().equals("Enter your 5 text here")) {

                                    binding.editTextFrag5.setText(text);
                                    binding.editTextFrag5.setVisibility(View.VISIBLE);

                                    binding.editTextFrag5.requestFocus();
                                    enterText.setText("");
                                    highlightText5();
                                    dlg2.dismiss();

                                } else if (binding.editTextFrag6.getText().toString().equals("Enter your 6 text here")) {

                                    binding.editTextFrag6.setText(text);
                                    binding.editTextFrag6.setVisibility(View.VISIBLE);

                                    binding.editTextFrag6.requestFocus();
                                    enterText.setText("");
                                    hightlightText6();
                                    dlg2.dismiss();

                                } else if (binding.editTextFrag7.getText().toString().equals("Enter your 7 text here")) {

                                    binding.editTextFrag7.setText(text);
                                    binding.editTextFrag7.setVisibility(View.VISIBLE);

                                    binding.editTextFrag7.requestFocus();
                                    enterText.setText("");
                                    highlightText7();
                                    dlg2.dismiss();


                                } else if (binding.editTextFrag8.getText().toString().equals("Enter your 8 text here")) {

                                    binding.editTextFrag8.setText(text);
                                    binding.editTextFrag8.setVisibility(View.VISIBLE);

                                    binding.editTextFrag8.requestFocus();
                                    enterText.setText("");
                                    highlightText8();
                                    dlg2.dismiss();

                                } else if (binding.editTextFrag9.getText().toString().equals("Enter your 9 text here")) {

                                    binding.editTextFrag9.setText(text);
                                    binding.editTextFrag9.setVisibility(View.VISIBLE);

                                    binding.editTextFrag9.requestFocus();
                                    enterText.setText("");
                                    highlightText9();
                                    dlg2.dismiss();

                                } else if (binding.editTextFrag10.getText().toString().equals("Enter your 10 text here")) {

                                    binding.editTextFrag10.setText(text);
                                    binding.editTextFrag10.setVisibility(View.VISIBLE);

                                    binding.editTextFrag10.requestFocus();
                                    enterText.setText("");
                                    highlightText10();
                                    dlg2.dismiss();
                                } else {
                                    Toast.makeText(editFameActivity.this, "You can add maximum 10 text !", Toast.LENGTH_SHORT).show();
                                    enterText.setText("");
                                    dlg2.dismiss();
                                }

                            }
                        }


                    }
                });


                dlg2.show();

                Window window = dlg2.getWindow();
                window.setLayout(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);


            }
        });


        final Dialog dialogLayoutColor = new Dialog(editFameActivity.this);
        dialogLayoutColor.setContentView(R.layout.color_dialogue_box);
        dialogLayoutColor.setCanceledOnTouchOutside(true);
        dialogLayoutColor.setCancelable(true);
        dialogLayoutColor.getWindow().getAttributes().windowAnimations = R.style.PauseDialogAnimation;
//
//
        //for margine to dialogue
        dialogLayoutColor.getWindow().setGravity(Gravity.TOP | Gravity.END);
        WindowManager.LayoutParams layoutParams = dialogLayoutColor.getWindow().getAttributes();

        layoutParams.y = 170; // top margin
        dialogLayoutColor.getWindow().setAttributes(layoutParams);


        colorRecyclerView = dialogLayoutColor.findViewById(R.id.colorRecyclerView);
        cancelColor = dialogLayoutColor.findViewById(R.id.cancelColor);


        binding.textcolor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if (binding.textcolor.isPressed()) {
                    binding.text4.performClick();


                    //   binding.editTextFrag.setTextColor(Color.parseColor("#00FF00"));
                }
                //       binding.editText.setVisibility(View.GONE);

                //    animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fadein);

                binding.treding.setText("Create Frame");
                binding.selectframeid.setVisibility(View.GONE);
                //  binding.editFrame.setAnimation(animation);
                binding.selectframeid2.setVisibility(View.VISIBLE);
                binding.right.setVisibility(View.VISIBLE);
                binding.wrong.setVisibility(View.VISIBLE);


                cancelColor.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        sh = getSharedPreferences("colorPref", Context.MODE_PRIVATE);

                        String s1 = sh.getString("color", "");

                        shText = getSharedPreferences("TEXTPref", Context.MODE_PRIVATE);

                        sTExt = shText.getString("text", "");


                        shshapeSelect = getSharedPreferences("SHAPEPref", Context.MODE_PRIVATE);

                        shShapeSelectText = shshapeSelect.getString("shape", "");


                        if (sTExt.equals("SELECTED1")) {

                            try {
                                binding.editTextFrag.setTextColor(Color.parseColor(s1));


                            } catch (Exception e) {
                            }
                        } else if (sTExt.equals("SELECTED2")) {

                            try {
                                binding.editTextFrag2.setTextColor(Color.parseColor(s1));


                            } catch (Exception e) {
                            }
                        } else if (sTExt.equals("SELECTED3")) {

                            try {
                                binding.editTextFrag3.setTextColor(Color.parseColor(s1));


                            } catch (Exception e) {
                            }
                        } else if (sTExt.equals("SELECTED4")) {

                            try {
                                binding.editTextFrag4.setTextColor(Color.parseColor(s1));


                            } catch (Exception e) {
                            }
                        } else if (sTExt.equals("SELECTED5")) {

                            try {
                                binding.editTextFrag5.setTextColor(Color.parseColor(s1));


                            } catch (Exception e) {
                            }
                        } else if (sTExt.equals("SELECTED6")) {

                            try {
                                binding.editTextFrag6.setTextColor(Color.parseColor(s1));


                            } catch (Exception e) {
                            }
                        } else if (sTExt.equals("SELECTED7")) {

                            try {
                                binding.editTextFrag7.setTextColor(Color.parseColor(s1));


                            } catch (Exception e) {
                            }
                        } else if (sTExt.equals("SELECTED8")) {

                            try {
                                binding.editTextFrag8.setTextColor(Color.parseColor(s1));


                            } catch (Exception e) {
                            }
                        } else if (sTExt.equals("SELECTED9")) {

                            try {
                                binding.editTextFrag9.setTextColor(Color.parseColor(s1));


                            } catch (Exception e) {
                            }
                        } else if (sTExt.equals("SELECTED10")) {

                            try {
                                binding.editTextFrag10.setTextColor(Color.parseColor(s1));


                            } catch (Exception e) {
                            }
                        }


                        //new part

                        try {
                            if (shShapeSelectText.equals("SELECTED_SHAPE1")) {

                                try {

                                    binding.shape1.setColorFilter(Color.parseColor(s1));

                                    //for put empty string or data


                                } catch (Exception e) {
                                }
                            } else if (shShapeSelectText.equals("SELECTED_SHAPE2")) {

                                try {
                                    binding.shape2.setColorFilter(Color.parseColor(s1));
                                    //for put empty string or data


                                } catch (Exception e) {
                                }
                            } else if (shShapeSelectText.equals("SELECTED_SHAPE3")) {

                                try {
                                    binding.shape3.setColorFilter(Color.parseColor(s1));
                                    //for put empty string or data


                                } catch (Exception e) {
                                }
                            } else if (shShapeSelectText.equals("SELECTED_SHAPE4")) {

                                try {
                                    binding.shape4.setColorFilter(Color.parseColor(s1));
                                    //for put empty string or data


                                } catch (Exception e) {
                                }
                            } else if (shShapeSelectText.equals("SELECTED_SHAPE5")) {

                                try {
                                    binding.shape5.setColorFilter(Color.parseColor(s1));
                                    //for put empty string or data


                                } catch (Exception e) {
                                }
                            } else if (shShapeSelectText.equals("SELECTED_SHAPE6")) {

                                try {
                                    binding.shape6.setColorFilter(Color.parseColor(s1));
                                    //for put empty string or data


                                } catch (Exception e) {
                                }
                            } else if (shShapeSelectText.equals("SELECTED_SHAPE7")) {

                                try {
                                    binding.shape7.setColorFilter(Color.parseColor(s1));
                                    //for put empty string or data


                                } catch (Exception e) {
                                }
                            } else if (shShapeSelectText.equals("SELECTED_SHAPE8")) {

                                try {
                                    binding.shape8.setColorFilter(Color.parseColor(s1));
                                    //for put empty string or data


                                } catch (Exception e) {
                                }
                            } else if (shShapeSelectText.equals("SELECTED_SHAPE9")) {

                                try {
                                    binding.shape9.setColorFilter(Color.parseColor(s1));
                                    //for put empty string or data


                                } catch (Exception e) {
                                }
                            } else if (shShapeSelectText.equals("SELECTED_SHAPE10")) {

                                try {
                                    binding.shape10.setColorFilter(Color.parseColor(s1));
                                    //for put empty string or data


                                } catch (Exception e) {
                                }
                            }
                        } catch (Exception ex) {
                        }


                        dialogLayoutColor.dismiss();
                    }
                });


                dialogLayoutColor.show();

                Window window = dialogLayoutColor.getWindow();
                window.setLayout(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);


            }
        });


        String colorData = String.valueOf(Color.parseColor("#b6370d"));


        // color model //

        colorModel = new textColorModel[]{
                // new textColorModel(colorData,colorData,colorData, 1             colorData,colorData,  2       Data,colorData,colorData,colorDa  3                                    4                                       5                                        6                                     7                                         8                                    9      t1),
                new textColorModel(Color.parseColor("#b7360d"), Color.parseColor("#bd4b27"), Color.parseColor("#c55e3f"), Color.parseColor("#cd7356"), Color.parseColor("#d3866e"), Color.parseColor("#da9a87"), Color.parseColor("#e2af9f"), Color.parseColor("#e8c2b6"), Color.parseColor("#f0d7cf"), "#b7360d", "#bd4b27", "#c55e3f", "#cd7356", "#d3866e", "#da9a87", "#e2af9f", "#e8c2b6", "#f0d7cf"),

                new textColorModel(Color.parseColor("#27005c"), Color.parseColor("#3d1b6d"), Color.parseColor("#53327c"), Color.parseColor("#53327c"), Color.parseColor("#7c669c"), Color.parseColor("#9281ae"), Color.parseColor("#a79abe"), Color.parseColor("#bfb3ce"), Color.parseColor("#d4cbde"), "#27005c", "#3d1b6d", "#53327c", "#53327c", "#7c669c", "#9281ae", "#a79abe", "#bfb3ce", "#d4cbde"),

                new textColorModel(Color.parseColor("#9e0235"), Color.parseColor("#aa1a49"), Color.parseColor("#b3345c"), Color.parseColor("#bd4f71"), Color.parseColor("#c66984"), Color.parseColor("#ce809b"), Color.parseColor("#d89bad"), Color.parseColor("#e4b2c3"), Color.parseColor("#eccdd6"), "#9e0235", "#aa1a49", "#b3345c", "#bd4f71", "#c66984", "#ce809b", "#d89bad", "#e4b2c3", "#eccdd6"),

                new textColorModel(Color.parseColor("#015c0a"), Color.parseColor("#1b6c24"), Color.parseColor("#337c3c"), Color.parseColor("#4f8c54"), Color.parseColor("#659c6c"), Color.parseColor("#80af82"), Color.parseColor("#99be9c"), Color.parseColor("#b2cfb7"), Color.parseColor("#cbddce"), "#015c0a", "#1b6c24", "#337c3c", "#4f8c54", "#659c6c", "#80af82", "#99be9c", "#b2cfb7", "#cbddce"),

                new textColorModel(Color.parseColor("#013e7a"), Color.parseColor("#1b5088"), Color.parseColor("#326596"), Color.parseColor("#4e79a3"), Color.parseColor("#668bb0"), Color.parseColor("#819ebc"), Color.parseColor("#98b3ca"), Color.parseColor("#b3c4d7"), Color.parseColor("#cbd8e5"), "#013e7a", "#1b5088", "#326596", "#4e79a3", "#668bb0", "##819ebc", "#98b3ca", "#b3c4d7", "#cbd8e5"),


                new textColorModel(Color.parseColor("#7c2d00"), Color.parseColor("#88431a"), Color.parseColor("#965733"), Color.parseColor("#a56c4c"), Color.parseColor("#b08167"), Color.parseColor("#bf9681"), Color.parseColor("#cbab99"), Color.parseColor("#d8c0b2"), Color.parseColor("#e4d5cc"), "#7c2d00", "#88431a", "#965733", "#a56c4c", "#b08167", "#bf9681", "#cbab99", "#d8c0b2", "#e4d5cc"),

                new textColorModel(Color.parseColor("#fe3d01"), Color.parseColor("#fe5019"), Color.parseColor("#ff6433"), Color.parseColor("#fe764d"), Color.parseColor("#fe764d"), Color.parseColor("#ff9f81"), Color.parseColor("#ffb098"), Color.parseColor("#fec4b3"), Color.parseColor("#ffd8cc"), "#fe3d01", "fe5019", "#ff6433", "#fe764d", "#fe764d", "#ff9f81", "#ffb098", "#fec4b3", "#ffd8cc"),


                //   1)
                // new textColorModel(colorData,colorData,colorData, 1             colorData,colorData,  2       Data,colorData,colorData,colorDa  3                                    4                                       5                                        6                                     7                                         8                                    9      t1),
                new textColorModel(Color.parseColor("#5d00cb"), Color.parseColor("#6d1ad0"), Color.parseColor("#7d32d5"), Color.parseColor("#8e4ddc"), Color.parseColor("#ae80e5"), Color.parseColor("#be98ea"), Color.parseColor("#c6a6ed"), Color.parseColor("#cdb2ef"), Color.parseColor("#dfccf5"),

                        "#5d00cb", "#6d1ad0", "#7d32d5", "#8e4ddc", "#ae80e5", "#be98ea", "#c6a6ed", "#cdb2ef", "#dfccf5"),
                //2)
                // new textColorModel(colorData,colorData,colorData, 1             colorData,colorData,  2       Data,colorData,colorData,colorDa  3                                    4                                       5                                        6                                     7                                         8                                    9      t1),
                new textColorModel(Color.parseColor("#e402ae"), Color.parseColor("#e71bb7"), Color.parseColor("#e833bf"), Color.parseColor("#eb4dc7"), Color.parseColor("#ee65cf"), Color.parseColor("#f180d7"), Color.parseColor("#f499df"), Color.parseColor("#f7b2e7"), Color.parseColor("#faccef"), "#e402ae", "#e71bb7", "#e833bf", "#eb4dc7", "#ee65cf", "#f180d7", "#f499df", "#f7b2e7", "#faccef"),

                //3)
                // new textColorModel(colorData,colorData,colorData, 1             colorData,colorData,  2       Data,colorData,colorData,colorDa  3                                    4                                       5                                        6                                     7                                         8                                    9      t1),
                new textColorModel(Color.parseColor("#ffa413"), Color.parseColor("#ffad2a"), Color.parseColor("#ffb642"), Color.parseColor("#ffbf5a"), Color.parseColor("#ffc871"), Color.parseColor("#ffd188"), Color.parseColor("#ffdb9f"), Color.parseColor("#ffe4b8"), Color.parseColor("#ffedd0"), "#ffa413", "#ffad2a", "#ffb642", "#ffbf5a", "#ffc871", "#ffd188", "#ffdb9f", "#ffe4b8", "#ffedd0"),

                //4)
                // new textColorModel(colorData,colorData,colorData, 1             colorData,colorData,  2       Data,colorData,colorData,colorDa  3                                    4                                       5                                        6                                     7                                         8                                    9      t1),
                new textColorModel(Color.parseColor("#027f6a"), Color.parseColor("#198c79"), Color.parseColor("#339987"), Color.parseColor("#4da597"), Color.parseColor("#66b2a6"), Color.parseColor("#80beb4"), Color.parseColor("#99ccc3"), Color.parseColor("#b2d9d2"), Color.parseColor("#cce5e1"), "#027f6a", "#198c79", "#339987", "#4da597", "#66b2a6", "#80beb4", "#99ccc3", "#b2d9d2", "#cce5e1"),

                //5
                // new textColorModel(colorData,colorData,colorData, 1             colorData,colorData,  2       Data,colorData,colorData,colorDa  3                                    4                                       5                                        6                                     7                                         8                                    9      t1),
                new textColorModel(Color.parseColor("#009900"), Color.parseColor("#1ba319"), Color.parseColor("#33ad33"), Color.parseColor("#4db84d"), Color.parseColor("#66c266"), Color.parseColor("#80cc7f"), Color.parseColor("#99d69a"), Color.parseColor("#b1e0b2"), Color.parseColor("#ccebcb"), "#009900", "#1ba319", "#33ad33", "#4db84d", "#66c266", "#80cc7f", "#99d69a", "#b1e0b2", "#ccebcb"),

                //6
                // new textColorModel(colorData,colorData,colorData, 1             colorData,colorData,  2       Data,colorData,colorData,colorDa  3                                    4                                       5                                        6                                     7                                         8                                    9      t1),
                new textColorModel(Color.parseColor("#fad306"), Color.parseColor("#fad71c"), Color.parseColor("#fbdb37"), Color.parseColor("#fbe04f"), Color.parseColor("#fce468"), Color.parseColor("#fce981"), Color.parseColor("#fdec9b"), Color.parseColor("#fdf1b4"), Color.parseColor("#fef6cd"), "#fad306", "#fad71c", "#fbdb37", "#fbe04f", "#fce468", "#fce981", "#fdec9b", "#fdf1b4", "#fef6cd"),

                //7
                // new textColorModel(colorData,colorData,colorData, 1             colorData,colorData,  2       Data,colorData,colorData,colorDa  3                                    4                                       5                                        6                                     7                                         8                                    9      t1),
                new textColorModel(Color.parseColor("#ff0443"), Color.parseColor("#ff1b56"), Color.parseColor("#ff3669"), Color.parseColor("#ff4e7b"), Color.parseColor("#ff678e"), Color.parseColor("#ff80a1"), Color.parseColor("#ff9ab4"), Color.parseColor("#ffb3c7"), Color.parseColor("#ffccd9"), "#ff0443", "#ff1b56", "#ff3669", "#ff4e7b", "#ff678e", "#ff80a1", "#ff9ab4", "#ffb3c7", "#ffccd9"),

                //8
                // new textColorModel(colorData,colorData,colorData, 1             colorData,colorData,  2       Data,colorData,colorData,colorDa  3                                    4                                       5                                        6                                     7                                         8                                    9      t1),
                new textColorModel(Color.parseColor("#49b109"), Color.parseColor("#5ab922"), Color.parseColor("#6ec13a"), Color.parseColor("#81c953"), Color.parseColor("#92cf6c"), Color.parseColor("#a4d984"), Color.parseColor("#b5e09d"), Color.parseColor("#c7e8b5"), Color.parseColor("#dbeece"), "#49b109", "#5ab922", "#6ec13a", "#81c953", "#92cf6c", "#a4d984", "#b5e09d", "#c7e8b5", "#dbeece"),


                //9
                // new textColorModel(colorData,colorData,colorData, 1             colorData,colorData,  2       Data,colorData,colorData,colorDa  3                                    4                                       5                                        6                                     7                                         8                                    9      t1),
                new textColorModel(Color.parseColor("#9a0af1"), Color.parseColor("#a423f3"), Color.parseColor("#ae3cf3"), Color.parseColor("#b854f5"), Color.parseColor("#c16cf6"), Color.parseColor("#cc85f8"), Color.parseColor("#d59df9"), Color.parseColor("#e0b6fb"), Color.parseColor("#ebcefc"), "#9a0af1", "#a423f3", "#ae3cf3", "#b854f5", "#c16cf6", "#cc85f8", "#d59df9", "#e0b6fb", "#ebcefc"),

                //10
                // new textColorModel(colorData,colorData,colorData, 1             colorData,colorData,  2       Data,colorData,colorData,colorDa  3                                    4                                       5                                        6                                     7                                         8                                    9      t1),
                new textColorModel(Color.parseColor("#09d0eb"), Color.parseColor("#1fd5ed"), Color.parseColor("#37daf0"), Color.parseColor("#4fdef2"), Color.parseColor("#6ae3f4"), Color.parseColor("#82e7f5"), Color.parseColor("#9aecf7"), Color.parseColor("#b4f0fa"), Color.parseColor("#cdf6fb"), "#09d0eb", "#1fd5ed", "#37daf0", "#4fdef2", "#6ae3f4", "#82e7f5", "#9aecf7", "#b4f0fa", "#cdf6fb"),

                //10
                // new textColorModel(colorData,colorData,colorData, 1             colorData,colorData,  2       Data,colorData,colorData,colorDa  3                                    4                                       5                                        6                                     7                                         8                                    9      t1),
                new textColorModel(Color.parseColor("#000000"), Color.parseColor("#1a1a1a"), Color.parseColor("#333333"), Color.parseColor("#4d4d4d"), Color.parseColor("#808080"), Color.parseColor("#999999"), Color.parseColor("#e5e5e5"), Color.parseColor("#ffffff"), Color.parseColor("#ffffff"), "#000000", "#1a1a1a", "#333333", "#4d4d4d", "#808080", "#999999", "#e5e5e5", "#ffffff", "#ffffff"),


        };


        colorAdapter = new textColorAdapter(colorModel, cancelColor);
        colorRecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

        //for  adding scroll bar in recyclerview
        //   new FastScrollerBuilder(colorRecyclerView).build();
        colorRecyclerView.setHasFixedSize(true);
        colorRecyclerView.setAdapter(colorAdapter);


        final Dialog dialogLayoutFont = new Dialog(editFameActivity.this);
        dialogLayoutFont.setContentView(R.layout.add_font_dialogue);
        //   dialogLayoutFont.setCanceledOnTouchOutside(true);
        //  dialogLayoutFont.setCancelable(true);
        dialogLayoutFont.getWindow().getAttributes().windowAnimations = R.style.PauseDialogAnimation;
//
//
        //for margine to dialogue
        dialogLayoutFont.getWindow().setGravity(Gravity.TOP | Gravity.END);
        WindowManager.LayoutParams layoutParamsFont = dialogLayoutFont.getWindow().getAttributes();

        layoutParamsFont.y = 110; // top margin
        dialogLayoutFont.getWindow().setAttributes(layoutParamsFont);


        cancelIMg = dialogLayoutFont.findViewById(R.id.cancelIMg);
        fontrecyclerview = dialogLayoutFont.findViewById(R.id.fontrecyclerview);


        binding.addfont.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                scrollListenerTouchFALSE();

                if (binding.addfont.isPressed()) {
                    binding.text5.performClick();
                }


                // binding.editTextFrag.se
                cancelIMg.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        shText = getSharedPreferences("TEXTPref", Context.MODE_PRIVATE);

                        sTExt = shText.getString("text", "");


                        shFont = getSharedPreferences("fontPref", Context.MODE_PRIVATE);

                        String s1 = shFont.getString("font", "");


                        try {

                            if (sTExt.equals("SELECTED1")) {

                                try {
                                    Typeface typeface = Typeface.createFromAsset(getAssets(), s1);
                                    binding.editTextFrag.setTypeface(typeface);
                                } catch (Exception e) {
                                }
                            } else if (sTExt.equals("SELECTED2")) {

                                try {
                                    Typeface typeface = Typeface.createFromAsset(getAssets(), s1);
                                    binding.editTextFrag2.setTypeface(typeface);
                                } catch (Exception e) {
                                }
                            } else if (sTExt.equals("SELECTED3")) {

                                try {
                                    Typeface typeface = Typeface.createFromAsset(getAssets(), s1);
                                    binding.editTextFrag3.setTypeface(typeface);
                                } catch (Exception e) {
                                }
                            } else if (sTExt.equals("SELECTED4")) {

                                try {
                                    Typeface typeface = Typeface.createFromAsset(getAssets(), s1);
                                    binding.editTextFrag4.setTypeface(typeface);
                                } catch (Exception e) {
                                }
                            } else if (sTExt.equals("SELECTED5")) {

                                try {
                                    Typeface typeface = Typeface.createFromAsset(getAssets(), s1);
                                    binding.editTextFrag5.setTypeface(typeface);
                                } catch (Exception e) {
                                }
                            } else if (sTExt.equals("SELECTED6")) {

                                try {
                                    Typeface typeface = Typeface.createFromAsset(getAssets(), s1);
                                    binding.editTextFrag6.setTypeface(typeface);
                                } catch (Exception e) {
                                }
                            } else if (sTExt.equals("SELECTED7")) {

                                try {
                                    Typeface typeface = Typeface.createFromAsset(getAssets(), s1);
                                    binding.editTextFrag7.setTypeface(typeface);
                                } catch (Exception e) {
                                }
                            } else if (sTExt.equals("SELECTED8")) {

                                try {
                                    Typeface typeface = Typeface.createFromAsset(getAssets(), s1);
                                    binding.editTextFrag8.setTypeface(typeface);
                                } catch (Exception e) {
                                }
                            } else if (sTExt.equals("SELECTED9")) {

                                try {
                                    Typeface typeface = Typeface.createFromAsset(getAssets(), s1);
                                    binding.editTextFrag9.setTypeface(typeface);
                                } catch (Exception e) {
                                }
                            } else if (sTExt.equals("SELECTED10")) {

                                try {
                                    Typeface typeface = Typeface.createFromAsset(getAssets(), s1);
                                    binding.editTextFrag10.setTypeface(typeface);
                                } catch (Exception e) {
                                }
                            }


                        } catch (Exception ex) {
                        }

                        dialogLayoutFont.dismiss();
                    }
                });
                //   binding.editText.setVisibility(View.GONE);

                // animation = AnimationUtils.loadAnimation(getApplicationContext(),  R.anim.fadein);

                binding.treding.setText("Create Frame");
                binding.selectframeid.setVisibility(View.GONE);
                //   binding.editFrame.setAnimation(animation);
                binding.selectframeid2.setVisibility(View.VISIBLE);
                binding.right.setVisibility(View.VISIBLE);
                binding.wrong.setVisibility(View.VISIBLE);

                dialogLayoutFont.show();

                Window window = dialogLayoutFont.getWindow();
                window.setLayout(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);

            }
        });


//        fontModel = new add_font_model[]{
//
//                new add_font_model(getCurrentTextForfont, "Add Panda", "Add Panda", "Add Panda", "Add Panda", "Add Panda", "Add Panda", "Add Panda", "Add Panda", "Add Panda", "Add Panda", "Add Panda", "Add Panda", "Add Panda", "Add Panda", "Add Panda", "fonts/poppins_medium.ttf", "fonts/poor_richard.ttf", "fonts/port_lligat_sans.ttf", "fonts/playfair_display.ttf", "fonts/pirata_one.ttf", "fonts/pattaya.ttf", "fonts/port_lligat_sans.ttf", "fonts/porter_sans_block.ttf", "fonts/postnobillscolombo_medium.ttf", "fonts/potta_one.ttf", "fonts/praiseregular.ttf", "fonts/quintessential_regular.ttf", "fonts/qahiri_regular.ttf", "fonts/rancho.ttf", "fonts/redressed.ttf", "fonts/rufina.ttf"),
//
//        };
//


        // font model

        fontModelData();

        newFontAdapter = new font_adapter_new(newFontModel, cancelIMg);
        fontrecyclerview.setLayoutManager(new LinearLayoutManager(editFameActivity.this));
        fontrecyclerview.setHasFixedSize(true);
        //      new FastScrollerBuilder(fontrecyclerview).build();
        fontrecyclerview.setAdapter(newFontAdapter);


        binding.dummyButonForAddFont.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                try {

                    SharedPreferences shText2 = getSharedPreferences("TEXTPref", Context.MODE_PRIVATE);

                    String sTExt2 = shText2.getString("text", "");


                    if (sTExt2.equals("SELECTED1")) {

                        try {
                            getCurrentTextForfont = binding.editTextFrag.getText().toString();


                            fontModelData();

                            newFontAdapter = new font_adapter_new(newFontModel, cancelIMg);
                            fontrecyclerview.setLayoutManager(new LinearLayoutManager(editFameActivity.this));
                            fontrecyclerview.setHasFixedSize(true);
                            fontrecyclerview.setAdapter(newFontAdapter);

                        } catch (Exception e) {
                        }
                    } else if (sTExt2.equals("SELECTED2")) {

                        try {
                            getCurrentTextForfont = binding.editTextFrag2.getText().toString();


                            fontModelData();

                            newFontAdapter = new font_adapter_new(newFontModel, cancelIMg);
                            fontrecyclerview.setLayoutManager(new LinearLayoutManager(editFameActivity.this));
                            fontrecyclerview.setHasFixedSize(true);
                            fontrecyclerview.setAdapter(newFontAdapter);
                        } catch (Exception e) {
                        }
                    } else if (sTExt2.equals("SELECTED3")) {

                        try {
                            getCurrentTextForfont = binding.editTextFrag3.getText().toString();


                            fontModelData();

                            newFontAdapter = new font_adapter_new(newFontModel, cancelIMg);
                            fontrecyclerview.setLayoutManager(new LinearLayoutManager(editFameActivity.this));
                            fontrecyclerview.setHasFixedSize(true);
                            fontrecyclerview.setAdapter(newFontAdapter);
                        } catch (Exception e) {
                        }
                    } else if (sTExt2.equals("SELECTED4")) {

                        try {
                            getCurrentTextForfont = binding.editTextFrag4.getText().toString();


                            fontModelData();

                            newFontAdapter = new font_adapter_new(newFontModel, cancelIMg);
                            fontrecyclerview.setLayoutManager(new LinearLayoutManager(editFameActivity.this));
                            fontrecyclerview.setHasFixedSize(true);
                            fontrecyclerview.setAdapter(newFontAdapter);
                        } catch (Exception e) {
                        }
                    } else if (sTExt2.equals("SELECTED5")) {

                        try {
                            getCurrentTextForfont = binding.editTextFrag5.getText().toString();


                            fontModelData();

                            newFontAdapter = new font_adapter_new(newFontModel, cancelIMg);
                            fontrecyclerview.setLayoutManager(new LinearLayoutManager(editFameActivity.this));
                            fontrecyclerview.setHasFixedSize(true);
                            fontrecyclerview.setAdapter(newFontAdapter);

                        } catch (Exception e) {
                        }
                    } else if (sTExt2.equals("SELECTED6")) {

                        try {
                            getCurrentTextForfont = binding.editTextFrag6.getText().toString();


                            fontModelData();

                            newFontAdapter = new font_adapter_new(newFontModel, cancelIMg);
                            fontrecyclerview.setLayoutManager(new LinearLayoutManager(editFameActivity.this));
                            fontrecyclerview.setHasFixedSize(true);
                            fontrecyclerview.setAdapter(newFontAdapter);

                        } catch (Exception e) {
                        }
                    } else if (sTExt2.equals("SELECTED7")) {

                        try {
                            getCurrentTextForfont = binding.editTextFrag7.getText().toString();


                            fontModelData();

                            newFontAdapter = new font_adapter_new(newFontModel, cancelIMg);
                            fontrecyclerview.setLayoutManager(new LinearLayoutManager(editFameActivity.this));
                            fontrecyclerview.setHasFixedSize(true);
                            fontrecyclerview.setAdapter(newFontAdapter);

                        } catch (Exception e) {
                        }
                    } else if (sTExt2.equals("SELECTED8")) {

                        try {
                            getCurrentTextForfont = binding.editTextFrag8.getText().toString();


                            newFontModel = new fontModelNew[]{


                            };

                            newFontAdapter = new font_adapter_new(newFontModel, cancelIMg);
                            fontrecyclerview.setLayoutManager(new LinearLayoutManager(editFameActivity.this));
                            fontrecyclerview.setHasFixedSize(true);
                            fontrecyclerview.setAdapter(newFontAdapter);

                        } catch (Exception e) {
                        }
                    } else if (sTExt2.equals("SELECTED9")) {

                        try {
                            getCurrentTextForfont = binding.editTextFrag9.getText().toString();


                            fontModelData();

                            newFontAdapter = new font_adapter_new(newFontModel, cancelIMg);
                            fontrecyclerview.setLayoutManager(new LinearLayoutManager(editFameActivity.this));
                            fontrecyclerview.setHasFixedSize(true);
                            fontrecyclerview.setAdapter(newFontAdapter);

                        } catch (Exception e) {
                        }
                    } else if (sTExt2.equals("SELECTED10")) {

                        try {
                            getCurrentTextForfont = binding.editTextFrag10.getText().toString();


                            fontModelData();

                            newFontAdapter = new font_adapter_new(newFontModel, cancelIMg);
                            fontrecyclerview.setLayoutManager(new LinearLayoutManager(editFameActivity.this));
                            fontrecyclerview.setHasFixedSize(true);
                            fontrecyclerview.setAdapter(newFontAdapter);

                        } catch (Exception e) {
                        }
                    } else {
                        Toast.makeText(editFameActivity.this, "No any one text selected !", Toast.LENGTH_SHORT).show();
                    }


                } catch (Exception ex) {
                }
            }
        });


        //for bottomsheet dialogue layout

        dialogShape = new BottomSheetDialog(editFameActivity.this, R.style.BottomSheetDialog);
        View viewShape = getLayoutInflater().inflate(R.layout.addshape_dialogue, null, false);
        dialogShape.setContentView(viewShape);
        recyclerView = viewShape.findViewById(R.id.shaperecyclerview);
        cancelShapes = viewShape.findViewById(R.id.cancelShape);


        binding.addshapes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                scrollListenerTouchFALSE();

                if (binding.addshapes.isPressed()) {
                    binding.text3.performClick();
                }

                animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fade_in_center2);

                binding.treding.setText("Create Frame");
                binding.selectframeid.setVisibility(View.GONE);
                binding.editFrame.setAnimation(animation);
                binding.selectframeid2.setVisibility(View.VISIBLE);
                binding.right.setVisibility(View.VISIBLE);
                binding.wrong.setVisibility(View.VISIBLE);

                cancelShapes.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {


                        shShape = getSharedPreferences("shapePref", Context.MODE_PRIVATE);

                        sshape = shShape.getString("shape", "");


                        if (binding.txtS1.getText().toString().equals("SHAPE_1")) {

                            binding.shape1.setImageResource(getResources().getIdentifier(sshape, "drawable", editFameActivity.this.getPackageName()));
                            binding.shape1.setVisibility(View.VISIBLE);
                            binding.txtS1.setText("");
                            highlightShape1();

                            dialogShape.dismiss();

                        } else if (binding.txtS2.getText().toString().equals("SHAPE_2")) {

                            binding.shape2.setImageResource(getResources().getIdentifier(sshape, "drawable", editFameActivity.this.getPackageName()));
                            binding.shape2.setVisibility(View.VISIBLE);
                            binding.txtS2.setText("");
                            highlightShape2();
                            dialogShape.dismiss();
                        } else if (binding.txtS3.getText().toString().equals("SHAPE_3")) {

                            binding.shape3.setImageResource(getResources().getIdentifier(sshape, "drawable", editFameActivity.this.getPackageName()));
                            binding.shape3.setVisibility(View.VISIBLE);
                            binding.txtS3.setText("");
                            highlightShape3();
                            dialogShape.dismiss();
                        } else if (binding.txtS4.getText().toString().equals("SHAPE_4")) {

                            binding.shape4.setImageResource(getResources().getIdentifier(sshape, "drawable", editFameActivity.this.getPackageName()));
                            binding.shape4.setVisibility(View.VISIBLE);
                            binding.txtS4.setText("");
                            highlightShape4();
                            dialogShape.dismiss();
                        } else if (binding.txtS5.getText().toString().equals("SHAPE_5")) {

                            binding.shape5.setImageResource(getResources().getIdentifier(sshape, "drawable", editFameActivity.this.getPackageName()));
                            binding.shape5.setVisibility(View.VISIBLE);
                            binding.txtS5.setText("");
                            highlightShape5();
                            dialogShape.dismiss();
                        } else if (binding.txtS6.getText().toString().equals("SHAPE_6")) {

                            binding.shape6.setImageResource(getResources().getIdentifier(sshape, "drawable", editFameActivity.this.getPackageName()));
                            binding.shape6.setVisibility(View.VISIBLE);
                            binding.txtS6.setText("");
                            highlightShape6();
                            dialogShape.dismiss();
                        } else if (binding.txtS7.getText().toString().equals("SHAPE_7")) {

                            binding.shape7.setImageResource(getResources().getIdentifier(sshape, "drawable", editFameActivity.this.getPackageName()));
                            binding.shape7.setVisibility(View.VISIBLE);
                            binding.txtS7.setText("");
                            highlightShape7();
                            dialogShape.dismiss();
                        } else if (binding.txtS8.getText().toString().equals("SHAPE_8")) {

                            binding.shape8.setImageResource(getResources().getIdentifier(sshape, "drawable", editFameActivity.this.getPackageName()));
                            binding.shape8.setVisibility(View.VISIBLE);
                            binding.txtS8.setText("");
                            highlightShape8();
                            dialogShape.dismiss();
                        } else if (binding.txtS9.getText().toString().equals("SHAPE_9")) {

                            binding.shape9.setImageResource(getResources().getIdentifier(sshape, "drawable", editFameActivity.this.getPackageName()));
                            binding.shape9.setVisibility(View.VISIBLE);
                            binding.txtS9.setText("");
                            highlightShape9();
                            dialogShape.dismiss();
                        } else if (binding.txtS10.getText().toString().equals("SHAPE_10")) {

                            binding.shape10.setImageResource(getResources().getIdentifier(sshape, "drawable", editFameActivity.this.getPackageName()));
                            binding.shape10.setVisibility(View.VISIBLE);
                            binding.txtS10.setText("");
                            highlightShape10();
                            dialogShape.dismiss();
                        } else {

                            Toast.makeText(editFameActivity.this, "You can add maximum 10 Shapes !", Toast.LENGTH_SHORT).show();
                            dialogShape.dismiss();
                        }


                    }
                });

                dialogShape.show();


            }
        });


        //shape model
        model = new shapeModel[]{

                new shapeModel(R.drawable.oneshape, R.drawable.twoshape, R.drawable.threeshape, R.drawable.fourshape, "oneshape", "twoshape", "threeshape", "fourshape"),
                new shapeModel(R.drawable.shape7, R.drawable.shape8, R.drawable.shape10, R.drawable.shape12, "shape7", "shape8", "shape10", "shape12"),
                new shapeModel(R.drawable.shape9, R.drawable.shape8, R.drawable.shape10, R.drawable.shape12, "shape9", "shape8", "shape10", "shape12"),
                new shapeModel(R.drawable.shape13, R.drawable.shape14, R.drawable.shape15, R.drawable.shape16, "shape13", "shape14", "shape15", "shape16"),
                new shapeModel(R.drawable.eightkon, R.drawable.eightkon2, R.drawable.eighttrialngle, R.drawable.fivekon, "eightkon", "eightkon2", "eighttrialngle", "fivekon"),
                new shapeModel(R.drawable.flower, R.drawable.machine, R.drawable.multistar, R.drawable.obdhobd, "flower", "machine", "multistar", "obdhobd"),
                new shapeModel(R.drawable.panchami, R.drawable.ring, R.drawable.sevenangle, R.drawable.sixangle, "panchami", "ring", "sevenangle", "sixangle"),
                new shapeModel(R.drawable.sixkon, R.drawable.star, R.drawable.starcircle, R.drawable.starfill, "sixkon", "star", "starcircle", "starfill"),
                new shapeModel(R.drawable.sun, R.drawable.tenkon, R.drawable.trianlge, R.drawable.fourstar, "sun", "tenkon", "trianlge", "fourstar"),
                new shapeModel(R.drawable.new1, R.drawable.new2, R.drawable.new3, R.drawable.new4, "new1", "new2", "new3", "new4"),
                new shapeModel(R.drawable.new5, R.drawable.new6, R.drawable.new7, R.drawable.new8, "new5", "new6", "new7", "new8"),
                new shapeModel(R.drawable.new9, R.drawable.new10, R.drawable.new11, R.drawable.new12, "new9", "new10", "new11", "new12"),
                new shapeModel(R.drawable.new13, R.drawable.new14, R.drawable.new15, R.drawable.new16, "new13", "new14", "new15", "new16"),
                new shapeModel(R.drawable.new17, R.drawable.new18, R.drawable.new19, R.drawable.threeshape, "new17", "new18", "new19", "threeshape"),


        };


        adapter = new shapeAdapter(model, cancelShapes);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        //  new FastScrollerBuilder(recyclerView).build();
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);

        binding.right.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                binding.editTextOnly.setVisibility(View.INVISIBLE);

                binding.edttextFragRel1.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.edttextFragRel2.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.edttextFragRel3.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.edttextFragRel4.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.edttextFragRel5.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.edttextFragRel6.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.edttextFragRel7.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.edttextFragRel8.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.edttextFragRel9.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.edttextFragRel10.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.shapeRel1.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.shapeRel2.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.shapeRel3.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.shapeRel4.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.shapeRel5.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.shapeRel6.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.shapeRel7.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.shapeRel8.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.shapeRel9.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.shapeRel10.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.storageRel1.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.storageRel2.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.storageRel3.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.storageRel4.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.storageRel5.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.storageRel6.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.storageRel7.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.storageRel8.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.storageRel9.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.storageRel10.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);


                //for clear selection from shared preference
                clearSharedPref();

//
//                try {
//                    Bitmap bitmap = Bitmap.createBitmap(binding.mainLayout.getWidth(), binding.mainLayout.getHeight(), Bitmap.Config.ARGB_8888);
//                    Canvas canvas = new Canvas(bitmap);
//                    binding.mainLayout.draw(canvas);
//
//
//                    // for set background in relative layout
//
//                    Drawable dr = new BitmapDrawable(bitmap);
//
//                    binding.mainLayout.setBackgroundDrawable(dr);

//                } catch (Exception ex) {
//                }


                //for reset a position of current edittext

//                binding.editTextFrag.setGravity(Gravity.CENTER | Gravity.BOTTOM);
//                setMargins(binding.editTextFrag, 50, 50, 50, 50);
//
//                binding.editTextFrag.setTextColor(Color.parseColor("#c0c0c0"));
//                Typeface typeface = Typeface.createFromAsset(getAssets(), "fonts/poppins.ttf");
//                binding.editTextFrag.setTypeface(typeface);
//                binding.editTextFrag.setTextSize(17.0f);
//                binding.editTextFrag.setText("Enter your new text here");
//                enterText.setText("");

            }
        });
        binding.mainLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if (binding.wrong.getVisibility() == View.VISIBLE) {

                    binding.wrong.animate().alpha(0.0f);
                    binding.right.animate().alpha(0.0f);
                    binding.right.setVisibility(View.INVISIBLE);
                    binding.wrong.setVisibility(View.INVISIBLE);
                } else if (binding.wrong.getVisibility() == View.INVISIBLE) {

                    binding.wrong.animate().alpha(1.0f);
                    binding.right.animate().alpha(1.0f);
                    binding.wrong.setVisibility(View.VISIBLE);
                    binding.right.setVisibility(View.VISIBLE);

                }


            }
        });


        binding.wrong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                shText = getSharedPreferences("TEXTPref", Context.MODE_PRIVATE);

                sTExt = shText.getString("text", "");

                Log.d("sTExt", sTExt);

                shshapeSelect = getSharedPreferences("SHAPEPref", Context.MODE_PRIVATE);

                shShapeSelectText = shshapeSelect.getString("shape", "");

                Log.d("shShapeSelectText", shShapeSelectText);

                sharedPreferenceStorage = getSharedPreferences("STORAGEPref", Context.MODE_PRIVATE);

                shStorageFetch = sharedPreferenceStorage.getString("storage", "");

                Log.d("StoragePrefrence", shStorageFetch);


                //for text only
                try {

                    if (sTExt.equals("SELECTED1")) {

                        try {
                            binding.editTextFrag.setText("Enter your 1 text here");
                            binding.editTextFrag.setVisibility(View.GONE);

                            Typeface typeface = Typeface.createFromAsset(getAssets(), "fonts/poppins.ttf");
                            binding.editTextFrag.setTypeface(typeface);
                            binding.editTextOnly.setVisibility(View.INVISIBLE);

                        } catch (Exception e) {
                        }
                    } else if (sTExt.equals("SELECTED2")) {

                        try {
                            binding.editTextFrag2.setText("Enter your 2 text here");
                            binding.editTextFrag2.setVisibility(View.GONE);
                            Typeface typeface = Typeface.createFromAsset(getAssets(), "fonts/poppins.ttf");
                            binding.editTextFrag2.setTypeface(typeface);
                            binding.editTextOnly.setVisibility(View.INVISIBLE);

                        } catch (Exception e) {
                        }
                    } else if (sTExt.equals("SELECTED3")) {

                        try {

                            binding.editTextFrag3.setText("Enter your 3 text here");
                            binding.editTextFrag3.setVisibility(View.GONE);
                            Typeface typeface = Typeface.createFromAsset(getAssets(), "fonts/poppins.ttf");
                            binding.editTextFrag3.setTypeface(typeface);
                            binding.editTextOnly.setVisibility(View.INVISIBLE);

                        } catch (Exception e) {
                        }
                    } else if (sTExt.equals("SELECTED4")) {

                        try {
                            binding.editTextFrag4.setText("Enter your 4 text here");
                            binding.editTextFrag4.setVisibility(View.GONE);
                            Typeface typeface = Typeface.createFromAsset(getAssets(), "fonts/poppins.ttf");
                            binding.editTextFrag4.setTypeface(typeface);
                            binding.editTextOnly.setVisibility(View.INVISIBLE);

                        } catch (Exception e) {
                        }
                    } else if (sTExt.equals("SELECTED5")) {

                        try {

                            binding.editTextFrag5.setText("Enter your 5 text here");
                            binding.editTextFrag5.setVisibility(View.GONE);
                            Typeface typeface = Typeface.createFromAsset(getAssets(), "fonts/poppins.ttf");
                            binding.editTextFrag5.setTypeface(typeface);
                            binding.editTextOnly.setVisibility(View.INVISIBLE);
                        } catch (Exception e) {
                        }
                    } else if (sTExt.equals("SELECTED6")) {

                        try {

                            binding.editTextFrag6.setText("Enter your 6 text here");
                            binding.editTextFrag6.setVisibility(View.GONE);
                            Typeface typeface = Typeface.createFromAsset(getAssets(), "fonts/poppins.ttf");
                            binding.editTextFrag6.setTypeface(typeface);
                            binding.editTextOnly.setVisibility(View.INVISIBLE);
                        } catch (Exception e) {
                        }
                    } else if (sTExt.equals("SELECTED7")) {

                        try {
                            binding.editTextFrag7.setText("Enter your 7 text here");
                            binding.editTextFrag7.setVisibility(View.GONE);
                            Typeface typeface = Typeface.createFromAsset(getAssets(), "fonts/poppins.ttf");
                            binding.editTextFrag7.setTypeface(typeface);
                            binding.editTextOnly.setVisibility(View.INVISIBLE);

                        } catch (Exception e) {
                        }
                    } else if (sTExt.equals("SELECTED8")) {

                        try {
                            binding.editTextFrag8.setText("Enter your 8 text here");
                            binding.editTextFrag8.setVisibility(View.GONE);
                            Typeface typeface = Typeface.createFromAsset(getAssets(), "fonts/poppins.ttf");
                            binding.editTextFrag8.setTypeface(typeface);
                            binding.editTextOnly.setVisibility(View.INVISIBLE);

                        } catch (Exception e) {
                        }
                    } else if (sTExt.equals("SELECTED9")) {

                        try {

                            binding.editTextFrag9.setText("Enter your 9 text here");
                            binding.editTextFrag9.setVisibility(View.GONE);
                            Typeface typeface = Typeface.createFromAsset(getAssets(), "fonts/poppins.ttf");
                            binding.editTextFrag9.setTypeface(typeface);
                            binding.editTextOnly.setVisibility(View.INVISIBLE);
                        } catch (Exception e) {
                        }
                    } else if (sTExt.equals("SELECTED10")) {

                        try {
                            binding.editTextFrag10.setText("Enter your 10 text here");
                            binding.editTextFrag10.setVisibility(View.GONE);

                            Typeface typeface = Typeface.createFromAsset(getAssets(), "fonts/poppins.ttf");
                            binding.editTextFrag10.setTypeface(typeface);
                            binding.editTextOnly.setVisibility(View.INVISIBLE);

                        } catch (Exception e) {
                        }
                    }


                } catch (Exception ex) {
                }
                //for shape only
                try {
                    if (shShapeSelectText.equals("SELECTED_SHAPE1")) {

                        try {
                            binding.txtS1.setText("SHAPE_1");
                            binding.shape1.setVisibility(View.GONE);


                        } catch (Exception e) {
                        }
                    } else if (shShapeSelectText.equals("SELECTED_SHAPE2")) {

                        try {

                            //for put empty string or data
                            binding.txtS2.setText("SHAPE_2");
                            binding.shape2.setVisibility(View.GONE);


                        } catch (Exception e) {
                        }
                    } else if (shShapeSelectText.equals("SELECTED_SHAPE3")) {

                        try {

                            //for put empty string or data
                            binding.txtS3.setText("SHAPE_3");
                            binding.shape3.setVisibility(View.GONE);


                        } catch (Exception e) {
                        }
                    } else if (shShapeSelectText.equals("SELECTED_SHAPE4")) {

                        try {

                            //for put empty string or data
                            binding.txtS4.setText("SHAPE_4");
                            binding.shape4.setVisibility(View.GONE);


                        } catch (Exception e) {
                        }
                    } else if (shShapeSelectText.equals("SELECTED_SHAPE5")) {

                        try {

                            //for put empty string or data
                            binding.txtS5.setText("SHAPE_5");
                            binding.shape5.setVisibility(View.GONE);


                        } catch (Exception e) {
                        }
                    } else if (shShapeSelectText.equals("SELECTED_SHAPE6")) {

                        try {

                            //for put empty string or data
                            binding.txtS6.setText("SHAPE_6");
                            binding.shape6.setVisibility(View.GONE);


                        } catch (Exception e) {
                        }
                    } else if (shShapeSelectText.equals("SELECTED_SHAPE7")) {

                        try {

                            //for put empty string or data
                            binding.txtS7.setText("SHAPE_7");
                            binding.shape7.setVisibility(View.GONE);


                        } catch (Exception e) {
                        }
                    } else if (shShapeSelectText.equals("SELECTED_SHAPE8")) {

                        try {

                            //for put empty string or data
                            binding.txtS8.setText("SHAPE_8");
                            binding.shape8.setVisibility(View.GONE);


                        } catch (Exception e) {
                        }
                    } else if (shShapeSelectText.equals("SELECTED_SHAPE9")) {

                        try {

                            //for put empty string or data
                            binding.txtS9.setText("SHAPE_9");
                            binding.shape9.setVisibility(View.GONE);


                        } catch (Exception e) {
                        }
                    } else if (shShapeSelectText.equals("SELECTED_SHAPE10")) {

                        try {
                            ;
                            //for put empty string or data
                            binding.txtS10.setText("SHAPE_10");
                            binding.shape10.setVisibility(View.GONE);


                        } catch (Exception e) {
                        }
                    }
                } catch (Exception ex) {
                }
                //for storage only
                try {
                    if (shStorageFetch.equals("SELECTED_STORAGE1")) {

                        try {
                            binding.storageTxt1.setText("STORAGE_1");
                            binding.storageImg1.setVisibility(View.GONE);

                        } catch (Exception e) {
                        }
                    } else if (shStorageFetch.equals("SELECTED_STORAGE2")) {

                        try {

                            //for put empty string or data
                            binding.storageTxt2.setText("STORAGE_2");
                            binding.storageImg2.setVisibility(View.GONE);


                        } catch (Exception e) {
                        }
                    } else if (shStorageFetch.equals("SELECTED_STORAGE3")) {

                        try {

                            //for put empty string or data
                            binding.storageTxt3.setText("STORAGE_3");
                            binding.storageImg3.setVisibility(View.GONE);


                        } catch (Exception e) {
                        }
                    } else if (shStorageFetch.equals("SELECTED_STORAGE4")) {

                        try {

                            //for put empty string or data
                            binding.storageTxt4.setText("STORAGE_4");
                            binding.storageImg4.setVisibility(View.GONE);


                        } catch (Exception e) {
                        }
                    } else if (shStorageFetch.equals("SELECTED_STORAGE5")) {

                        try {

                            //for put empty string or data
                            binding.storageTxt5.setText("STORAGE_5");
                            binding.storageImg5.setVisibility(View.GONE);


                        } catch (Exception e) {
                        }
                    } else if (shStorageFetch.equals("SELECTED_STORAGE6")) {

                        try {

                            //for put empty string or data
                            binding.storageTxt6.setText("STORAGE_6");
                            binding.storageImg6.setVisibility(View.GONE);


                        } catch (Exception e) {
                        }
                    } else if (shStorageFetch.equals("SELECTED_STORAGE7")) {

                        try {

                            //for put empty string or data
                            binding.storageTxt7.setText("STORAGE_7");
                            binding.storageImg7.setVisibility(View.GONE);


                        } catch (Exception e) {
                        }
                    } else if (shStorageFetch.equals("SELECTED_STORAGE8")) {

                        try {

                            //for put empty string or data
                            binding.storageTxt8.setText("STORAGE_8");
                            binding.storageImg8.setVisibility(View.GONE);


                        } catch (Exception e) {
                        }
                    } else if (shStorageFetch.equals("SELECTED_STORAGE9")) {

                        try {

                            //for put empty string or data
                            binding.storageTxt9.setText("STORAGE_9");
                            binding.storageImg9.setVisibility(View.GONE);

                        } catch (Exception e) {
                        }
                    } else if (shStorageFetch.equals("SELECTED_STORAGE10")) {

                        try {
                            ;
                            //for put empty string or data
                            binding.storageTxt10.setText("STORAGE_10");
                            binding.storageImg10.setVisibility(View.GONE);


                        } catch (Exception e) {
                        }
                    }
                } catch (Exception ex) {
                }


                //for shape only


            }
        });


        ///This is code for moving textview from anywhere and zoom it

        binding.edttextFragRel1.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                RelativeLayout view = (RelativeLayout) v;
                view.bringToFront();
                viewTransformation(view, event);


                sharedPreferences = editFameActivity.this.getSharedPreferences("TEXTPref", MODE_PRIVATE);
                SharedPreferences.Editor myEdit = sharedPreferences.edit();
                myEdit.putString("text", "SELECTED1");
                myEdit.apply();


                sharedPreferencesForShapeNull = getSharedPreferences("SHAPEPref", MODE_PRIVATE);
                SharedPreferences.Editor myEdit2 = sharedPreferencesForShapeNull.edit();
                myEdit2.putString("shape", "");
                myEdit2.apply();

                sharedPreferenceStorageNull = editFameActivity.this.getSharedPreferences("STORAGEPref", MODE_PRIVATE);
                SharedPreferences.Editor myEdit3 = sharedPreferenceStorageNull.edit();
                myEdit3.putString("storage", "");
                myEdit3.apply();
                binding.dummyButonForAddFont.performClick();


                binding.edttextFragRel1.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork);
                binding.edttextFragRel2.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.edttextFragRel3.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.edttextFragRel4.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.edttextFragRel5.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.edttextFragRel6.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.edttextFragRel7.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.edttextFragRel8.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.edttextFragRel9.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.edttextFragRel10.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.shapeRel1.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.shapeRel2.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.shapeRel3.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.shapeRel4.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.shapeRel5.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.shapeRel6.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.shapeRel7.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.shapeRel8.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.shapeRel9.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.shapeRel10.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.storageRel1.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.storageRel2.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.storageRel3.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.storageRel4.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.storageRel5.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.storageRel6.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.storageRel7.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.storageRel8.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.storageRel9.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.storageRel10.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);


                //for visible edit text option

                binding.editTextOnly.animate().alpha(1.0f);
                binding.editTextOnly.setVisibility(View.VISIBLE);


                return true;
            }

        });    ///This is code for moving textview from anywhere and zoom it

        binding.edttextFragRel2.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                RelativeLayout view = (RelativeLayout) v;
                view.bringToFront();
                viewTransformation(view, event);


                sharedPreferences = editFameActivity.this.getSharedPreferences("TEXTPref", MODE_PRIVATE);
                SharedPreferences.Editor myEdit = sharedPreferences.edit();
                myEdit.putString("text", "SELECTED2");
                myEdit.apply();

                sharedPreferencesForShapeNull = getSharedPreferences("SHAPEPref", MODE_PRIVATE);
                SharedPreferences.Editor myEdit2 = sharedPreferencesForShapeNull.edit();
                myEdit2.putString("shape", "");
                myEdit2.apply();

                sharedPreferenceStorageNull = editFameActivity.this.getSharedPreferences("STORAGEPref", MODE_PRIVATE);
                SharedPreferences.Editor myEdit3 = sharedPreferenceStorageNull.edit();
                myEdit3.putString("storage", "");
                myEdit3.apply();

                binding.dummyButonForAddFont.performClick();


                binding.edttextFragRel1.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.edttextFragRel2.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork);
                binding.edttextFragRel3.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.edttextFragRel4.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.edttextFragRel5.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.edttextFragRel6.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.edttextFragRel7.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.edttextFragRel8.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.edttextFragRel9.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.edttextFragRel10.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.shapeRel1.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.shapeRel2.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.shapeRel3.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.shapeRel4.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.shapeRel5.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.shapeRel6.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.shapeRel7.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.shapeRel8.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.shapeRel9.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.shapeRel10.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.storageRel1.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.storageRel2.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.storageRel3.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.storageRel4.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.storageRel5.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.storageRel6.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.storageRel7.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.storageRel8.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.storageRel9.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.storageRel10.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);


                binding.editTextOnly.animate().alpha(1.0f);
                binding.editTextOnly.setVisibility(View.VISIBLE);

                return true;
            }

        });    ///This is code for moving textview from anywhere and zoom it

        binding.edttextFragRel3.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                RelativeLayout view = (RelativeLayout) v;
                view.bringToFront();
                viewTransformation(view, event);


                sharedPreferences = editFameActivity.this.getSharedPreferences("TEXTPref", MODE_PRIVATE);
                SharedPreferences.Editor myEdit = sharedPreferences.edit();
                myEdit.putString("text", "SELECTED3");
                myEdit.apply();

                sharedPreferencesForShapeNull = getSharedPreferences("SHAPEPref", MODE_PRIVATE);
                SharedPreferences.Editor myEdit2 = sharedPreferencesForShapeNull.edit();
                myEdit2.putString("shape", "");
                myEdit2.apply();

                sharedPreferenceStorageNull = editFameActivity.this.getSharedPreferences("STORAGEPref", MODE_PRIVATE);
                SharedPreferences.Editor myEdit3 = sharedPreferenceStorageNull.edit();
                myEdit3.putString("storage", "");
                myEdit3.apply();

                binding.dummyButonForAddFont.performClick();


                binding.edttextFragRel1.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.edttextFragRel2.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.edttextFragRel3.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork);
                binding.edttextFragRel4.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.edttextFragRel5.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.edttextFragRel6.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.edttextFragRel7.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.edttextFragRel8.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.edttextFragRel9.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.edttextFragRel10.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.shapeRel1.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.shapeRel2.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.shapeRel3.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.shapeRel4.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.shapeRel5.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.shapeRel6.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.shapeRel7.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.shapeRel8.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.shapeRel9.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.shapeRel10.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.storageRel1.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.storageRel2.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.storageRel3.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.storageRel4.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.storageRel5.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.storageRel6.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.storageRel7.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.storageRel8.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.storageRel9.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.storageRel10.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);


                binding.editTextOnly.animate().alpha(1.0f);
                binding.editTextOnly.setVisibility(View.VISIBLE);

                return true;
            }

        });    ///This is code for moving textview from anywhere and zoom it

        binding.edttextFragRel4.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                RelativeLayout view = (RelativeLayout) v;
                view.bringToFront();
                viewTransformation(view, event);

                sharedPreferences = editFameActivity.this.getSharedPreferences("TEXTPref", MODE_PRIVATE);
                SharedPreferences.Editor myEdit = sharedPreferences.edit();
                myEdit.putString("text", "SELECTED4");
                myEdit.apply();

                sharedPreferencesForShapeNull = getSharedPreferences("SHAPEPref", MODE_PRIVATE);
                SharedPreferences.Editor myEdit2 = sharedPreferencesForShapeNull.edit();
                myEdit2.putString("shape", "");
                myEdit2.apply();

                sharedPreferenceStorageNull = editFameActivity.this.getSharedPreferences("STORAGEPref", MODE_PRIVATE);
                SharedPreferences.Editor myEdit3 = sharedPreferenceStorageNull.edit();
                myEdit3.putString("storage", "");
                myEdit3.apply();
                binding.dummyButonForAddFont.performClick();


                //     Toast.makeText(getApplicationContext(), "Selected : "+binding.editTextFrag4.getText().toString(), Toast.LENGTH_LONG).show();

                binding.edttextFragRel1.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.edttextFragRel2.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.edttextFragRel3.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.edttextFragRel4.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork);
                binding.edttextFragRel5.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.edttextFragRel6.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.edttextFragRel7.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.edttextFragRel8.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.edttextFragRel9.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.edttextFragRel10.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.shapeRel1.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.shapeRel2.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.shapeRel3.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.shapeRel4.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.shapeRel5.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.shapeRel6.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.shapeRel7.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.shapeRel8.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.shapeRel9.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.shapeRel10.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.storageRel1.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.storageRel2.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.storageRel3.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.storageRel4.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.storageRel5.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.storageRel6.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.storageRel7.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.storageRel8.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.storageRel9.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.storageRel10.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);


                binding.editTextOnly.animate().alpha(1.0f);
                binding.editTextOnly.setVisibility(View.VISIBLE);

                return true;
            }

        });    ///This is code for moving textview from anywhere and zoom it

        binding.edttextFragRel5.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                RelativeLayout view = (RelativeLayout) v;
                view.bringToFront();
                viewTransformation(view, event);

                sharedPreferences = editFameActivity.this.getSharedPreferences("TEXTPref", MODE_PRIVATE);
                SharedPreferences.Editor myEdit = sharedPreferences.edit();
                myEdit.putString("text", "SELECTED5");
                myEdit.apply();

                sharedPreferencesForShapeNull = getSharedPreferences("SHAPEPref", MODE_PRIVATE);
                SharedPreferences.Editor myEdit2 = sharedPreferencesForShapeNull.edit();
                myEdit2.putString("shape", "");
                myEdit2.apply();

                sharedPreferenceStorageNull = editFameActivity.this.getSharedPreferences("STORAGEPref", MODE_PRIVATE);
                SharedPreferences.Editor myEdit3 = sharedPreferenceStorageNull.edit();
                myEdit3.putString("storage", "");
                myEdit3.apply();
                binding.dummyButonForAddFont.performClick();


                //Toast.makeText(getApplicationContext(), "Selected : "+binding.editTextFrag5.getText().toString(), Toast.LENGTH_LONG).show();

                binding.edttextFragRel1.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.edttextFragRel2.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.edttextFragRel3.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.edttextFragRel4.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.edttextFragRel5.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork);
                binding.edttextFragRel6.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.edttextFragRel7.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.edttextFragRel8.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.edttextFragRel9.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.edttextFragRel10.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.shapeRel1.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.shapeRel2.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.shapeRel3.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.shapeRel4.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.shapeRel5.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.shapeRel6.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.shapeRel7.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.shapeRel8.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.shapeRel9.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.shapeRel10.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.storageRel1.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.storageRel2.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.storageRel3.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.storageRel4.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.storageRel5.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.storageRel6.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.storageRel7.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.storageRel8.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.storageRel9.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.storageRel10.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);


                binding.editTextOnly.animate().alpha(1.0f);
                binding.editTextOnly.setVisibility(View.VISIBLE);
                return true;
            }

        });    ///This is code for moving textview from anywhere and zoom it

        binding.edttextFragRel6.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                RelativeLayout view = (RelativeLayout) v;
                view.bringToFront();
                viewTransformation(view, event);


                sharedPreferences = editFameActivity.this.getSharedPreferences("TEXTPref", MODE_PRIVATE);
                SharedPreferences.Editor myEdit = sharedPreferences.edit();
                myEdit.putString("text", "SELECTED6");
                myEdit.apply();

                sharedPreferencesForShapeNull = getSharedPreferences("SHAPEPref", MODE_PRIVATE);
                SharedPreferences.Editor myEdit2 = sharedPreferencesForShapeNull.edit();
                myEdit2.putString("shape", "");
                myEdit2.apply();

                sharedPreferenceStorageNull = editFameActivity.this.getSharedPreferences("STORAGEPref", MODE_PRIVATE);
                SharedPreferences.Editor myEdit3 = sharedPreferenceStorageNull.edit();
                myEdit3.putString("storage", "");
                myEdit3.apply();

                binding.dummyButonForAddFont.performClick();


                // Toast.makeText(getApplicationContext(), "Selected : "+binding.editTextFrag6.getText().toString(), Toast.LENGTH_LONG).show();

                binding.edttextFragRel1.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.edttextFragRel2.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.edttextFragRel3.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.edttextFragRel4.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.edttextFragRel5.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.edttextFragRel6.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork);
                binding.edttextFragRel7.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.edttextFragRel8.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.edttextFragRel9.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.edttextFragRel10.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.shapeRel1.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.shapeRel2.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.shapeRel3.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.shapeRel4.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.shapeRel5.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.shapeRel6.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.shapeRel7.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.shapeRel8.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.shapeRel9.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.shapeRel10.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.storageRel1.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.storageRel2.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.storageRel3.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.storageRel4.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.storageRel5.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.storageRel6.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.storageRel7.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.storageRel8.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.storageRel9.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.storageRel10.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);


                binding.editTextOnly.animate().alpha(1.0f);
                binding.editTextOnly.setVisibility(View.VISIBLE);
                return true;
            }

        });    ///This is code for moving textview from anywhere and zoom it

        binding.edttextFragRel7.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                RelativeLayout view = (RelativeLayout) v;
                view.bringToFront();
                viewTransformation(view, event);

                sharedPreferences = editFameActivity.this.getSharedPreferences("TEXTPref", MODE_PRIVATE);
                SharedPreferences.Editor myEdit = sharedPreferences.edit();
                myEdit.putString("text", "SELECTED7");
                myEdit.apply();

                sharedPreferencesForShapeNull = getSharedPreferences("SHAPEPref", MODE_PRIVATE);
                SharedPreferences.Editor myEdit2 = sharedPreferencesForShapeNull.edit();
                myEdit2.putString("shape", "");
                myEdit2.apply();

                sharedPreferenceStorageNull = editFameActivity.this.getSharedPreferences("STORAGEPref", MODE_PRIVATE);
                SharedPreferences.Editor myEdit3 = sharedPreferenceStorageNull.edit();
                myEdit3.putString("storage", "");
                myEdit3.apply();
                binding.dummyButonForAddFont.performClick();
//
//


                //  Toast.makeText(getApplicationContext(), "Selected : "+binding.editTextFrag7.getText().toString(), Toast.LENGTH_LONG).show();

                binding.edttextFragRel1.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.edttextFragRel2.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.edttextFragRel3.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.edttextFragRel4.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.edttextFragRel5.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.edttextFragRel6.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.edttextFragRel7.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork);
                binding.edttextFragRel8.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.edttextFragRel9.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.edttextFragRel10.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.shapeRel1.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.shapeRel2.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.shapeRel3.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.shapeRel4.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.shapeRel5.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.shapeRel6.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.shapeRel7.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.shapeRel8.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.shapeRel9.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.shapeRel10.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.storageRel1.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.storageRel2.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.storageRel3.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.storageRel4.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.storageRel5.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.storageRel6.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.storageRel7.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.storageRel8.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.storageRel9.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.storageRel10.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);


                binding.editTextOnly.animate().alpha(1.0f);
                binding.editTextOnly.setVisibility(View.VISIBLE);
                return true;
            }

        });    ///This is code for moving textview from anywhere and zoom it

        binding.edttextFragRel8.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                RelativeLayout view = (RelativeLayout) v;
                view.bringToFront();
                viewTransformation(view, event);

                sharedPreferences = editFameActivity.this.getSharedPreferences("TEXTPref", MODE_PRIVATE);
                SharedPreferences.Editor myEdit = sharedPreferences.edit();
                myEdit.putString("text", "SELECTED8");
                myEdit.apply();

                sharedPreferencesForShapeNull = getSharedPreferences("SHAPEPref", MODE_PRIVATE);
                SharedPreferences.Editor myEdit2 = sharedPreferencesForShapeNull.edit();
                myEdit2.putString("shape", "");
                myEdit2.apply();

                sharedPreferenceStorageNull = editFameActivity.this.getSharedPreferences("STORAGEPref", MODE_PRIVATE);
                SharedPreferences.Editor myEdit3 = sharedPreferenceStorageNull.edit();
                myEdit3.putString("storage", "");
                myEdit3.apply();

                binding.dummyButonForAddFont.performClick();


                // Toast.makeText(getApplicationContext(), "Selected : "+binding.editTextFrag8.getText().toString(), Toast.LENGTH_LONG).show();


                binding.edttextFragRel1.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.edttextFragRel2.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.edttextFragRel3.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.edttextFragRel4.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.edttextFragRel5.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.edttextFragRel6.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.edttextFragRel7.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.edttextFragRel8.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork);
                binding.edttextFragRel9.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.edttextFragRel10.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.shapeRel1.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.shapeRel2.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.shapeRel3.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.shapeRel4.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.shapeRel5.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.shapeRel6.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.shapeRel7.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.shapeRel8.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.shapeRel9.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.shapeRel10.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.storageRel1.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.storageRel2.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.storageRel3.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.storageRel4.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.storageRel5.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.storageRel6.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.storageRel7.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.storageRel8.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.storageRel9.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.storageRel10.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);


                binding.editTextOnly.animate().alpha(1.0f);
                binding.editTextOnly.setVisibility(View.VISIBLE);
                return true;
            }

        });    ///This is code for moving textview from anywhere and zoom it

        binding.edttextFragRel9.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                RelativeLayout view = (RelativeLayout) v;
                view.bringToFront();
                viewTransformation(view, event);

                sharedPreferences = editFameActivity.this.getSharedPreferences("TEXTPref", MODE_PRIVATE);
                SharedPreferences.Editor myEdit = sharedPreferences.edit();
                myEdit.putString("text", "SELECTED9");
                myEdit.apply();

                sharedPreferencesForShapeNull = getSharedPreferences("SHAPEPref", MODE_PRIVATE);
                SharedPreferences.Editor myEdit2 = sharedPreferencesForShapeNull.edit();
                myEdit2.putString("shape", "");
                myEdit2.apply();

                sharedPreferenceStorageNull = editFameActivity.this.getSharedPreferences("STORAGEPref", MODE_PRIVATE);
                SharedPreferences.Editor myEdit3 = sharedPreferenceStorageNull.edit();
                myEdit3.putString("storage", "");
                myEdit3.apply();

                binding.dummyButonForAddFont.performClick();


                // Toast.makeText(getApplicationContext(), "Selected : "+binding.editTextFrag9.getText().toString(), Toast.LENGTH_LONG).show();


                binding.edttextFragRel1.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.edttextFragRel2.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.edttextFragRel3.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.edttextFragRel4.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.edttextFragRel5.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.edttextFragRel6.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.edttextFragRel7.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.edttextFragRel8.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.edttextFragRel9.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork);
                binding.edttextFragRel10.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.shapeRel1.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.shapeRel2.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.shapeRel3.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.shapeRel4.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.shapeRel5.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.shapeRel6.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.shapeRel7.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.shapeRel8.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.shapeRel9.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.shapeRel10.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.storageRel1.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.storageRel2.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.storageRel3.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.storageRel4.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.storageRel5.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.storageRel6.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.storageRel7.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.storageRel8.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.storageRel9.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.storageRel10.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);


                binding.editTextOnly.animate().alpha(1.0f);
                binding.editTextOnly.setVisibility(View.VISIBLE);
                return true;
            }

        });    ///This is code for moving textview from anywhere and zoom it

        binding.edttextFragRel10.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                RelativeLayout view = (RelativeLayout) v;
                view.bringToFront();
                viewTransformation(view, event);

                sharedPreferences = editFameActivity.this.getSharedPreferences("TEXTPref", MODE_PRIVATE);
                SharedPreferences.Editor myEdit = sharedPreferences.edit();
                myEdit.putString("text", "SELECTED10");
                myEdit.apply();

                sharedPreferencesForShapeNull = getSharedPreferences("SHAPEPref", MODE_PRIVATE);
                SharedPreferences.Editor myEdit2 = sharedPreferencesForShapeNull.edit();
                myEdit2.putString("shape", "");
                myEdit2.apply();

                sharedPreferenceStorageNull = editFameActivity.this.getSharedPreferences("STORAGEPref", MODE_PRIVATE);
                SharedPreferences.Editor myEdit3 = sharedPreferenceStorageNull.edit();
                myEdit3.putString("storage", "");
                myEdit3.apply();

                binding.dummyButonForAddFont.performClick();


                //Toast.makeText(getApplicationContext(), "Selected : "+binding.editTextFrag10.getText().toString(), Toast.LENGTH_LONG).show();

                binding.edttextFragRel1.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.edttextFragRel2.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.edttextFragRel3.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.edttextFragRel4.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.edttextFragRel5.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.edttextFragRel6.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.edttextFragRel7.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.edttextFragRel8.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.edttextFragRel9.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.edttextFragRel10.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork);
                binding.shapeRel1.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.shapeRel2.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.shapeRel3.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.shapeRel4.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.shapeRel5.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.shapeRel6.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.shapeRel7.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.shapeRel8.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.shapeRel9.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.shapeRel10.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.storageRel1.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.storageRel2.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.storageRel3.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.storageRel4.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.storageRel5.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.storageRel6.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.storageRel7.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.storageRel8.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.storageRel9.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.storageRel10.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);


                binding.editTextOnly.animate().alpha(1.0f);
                binding.editTextOnly.setVisibility(View.VISIBLE);
                return true;
            }

        });


        ///This is code for moving Shape from anywhere and zoom it


        binding.shapeRel1.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                RelativeLayout view = (RelativeLayout) v;
                view.bringToFront();
                viewTransformation(view, event);


                sharedPreferences = editFameActivity.this.getSharedPreferences("SHAPEPref", MODE_PRIVATE);
                SharedPreferences.Editor myEdit = sharedPreferences.edit();
                myEdit.putString("shape", "SELECTED_SHAPE1");
                myEdit.apply();

                sharedPreferencesForTextNull = getSharedPreferences("TEXTPref", MODE_PRIVATE);
                SharedPreferences.Editor myEdit2 = sharedPreferencesForTextNull.edit();
                myEdit2.putString("text", "");
                myEdit2.apply();

                sharedPreferenceStorageNull = editFameActivity.this.getSharedPreferences("STORAGEPref", MODE_PRIVATE);
                SharedPreferences.Editor myEdit3 = sharedPreferenceStorageNull.edit();
                myEdit3.putString("storage", "");
                myEdit3.apply();

                binding.edttextFragRel1.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.edttextFragRel2.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.edttextFragRel3.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.edttextFragRel4.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.edttextFragRel5.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.edttextFragRel6.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.edttextFragRel7.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.edttextFragRel8.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.edttextFragRel9.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.edttextFragRel10.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.shapeRel1.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork);
                binding.shapeRel2.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.shapeRel3.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.shapeRel4.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.shapeRel5.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.shapeRel6.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.shapeRel7.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.shapeRel8.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.shapeRel9.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.shapeRel10.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.storageRel1.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.storageRel2.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.storageRel3.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.storageRel4.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.storageRel5.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.storageRel6.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.storageRel7.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.storageRel8.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.storageRel9.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.storageRel10.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);


                binding.editTextOnly.animate().alpha(0.0f);
                binding.editTextOnly.setVisibility(View.INVISIBLE);

                return true;
            }

        });


        binding.shapeRel2.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                RelativeLayout view = (RelativeLayout) v;
                view.bringToFront();
                viewTransformation(view, event);

                sharedPreferences = editFameActivity.this.getSharedPreferences("SHAPEPref", MODE_PRIVATE);
                SharedPreferences.Editor myEdit = sharedPreferences.edit();
                myEdit.putString("shape", "SELECTED_SHAPE2");
                myEdit.apply();

                sharedPreferencesForTextNull = getSharedPreferences("TEXTPref", MODE_PRIVATE);
                SharedPreferences.Editor myEdit2 = sharedPreferencesForTextNull.edit();
                myEdit2.putString("text", "");
                myEdit2.apply();

                sharedPreferenceStorageNull = editFameActivity.this.getSharedPreferences("STORAGEPref", MODE_PRIVATE);
                SharedPreferences.Editor myEdit3 = sharedPreferenceStorageNull.edit();
                myEdit3.putString("storage", "");
                myEdit3.apply();


                binding.edttextFragRel1.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.edttextFragRel2.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.edttextFragRel3.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.edttextFragRel4.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.edttextFragRel5.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.edttextFragRel6.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.edttextFragRel7.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.edttextFragRel8.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.edttextFragRel9.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.edttextFragRel10.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.shapeRel1.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.shapeRel2.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork);
                binding.shapeRel3.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.shapeRel4.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.shapeRel5.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.shapeRel6.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.shapeRel7.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.shapeRel8.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.shapeRel9.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.shapeRel10.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.storageRel1.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.storageRel2.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.storageRel3.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.storageRel4.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.storageRel5.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.storageRel6.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.storageRel7.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.storageRel8.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.storageRel9.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.storageRel10.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);


                binding.editTextOnly.animate().alpha(0.0f);
                binding.editTextOnly.setVisibility(View.INVISIBLE);
                return true;
            }

        });


        binding.shapeRel3.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                RelativeLayout view = (RelativeLayout) v;
                view.bringToFront();
                viewTransformation(view, event);

                sharedPreferences = editFameActivity.this.getSharedPreferences("SHAPEPref", MODE_PRIVATE);
                SharedPreferences.Editor myEdit = sharedPreferences.edit();
                myEdit.putString("shape", "SELECTED_SHAPE3");
                myEdit.apply();

                sharedPreferencesForTextNull = getSharedPreferences("TEXTPref", MODE_PRIVATE);
                SharedPreferences.Editor myEdit2 = sharedPreferencesForTextNull.edit();
                myEdit2.putString("text", "");
                myEdit2.apply();

                sharedPreferenceStorageNull = editFameActivity.this.getSharedPreferences("STORAGEPref", MODE_PRIVATE);
                SharedPreferences.Editor myEdit3 = sharedPreferenceStorageNull.edit();
                myEdit3.putString("storage", "");
                myEdit3.apply();

                binding.edttextFragRel1.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.edttextFragRel2.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.edttextFragRel3.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.edttextFragRel4.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.edttextFragRel5.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.edttextFragRel6.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.edttextFragRel7.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.edttextFragRel8.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.edttextFragRel9.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.edttextFragRel10.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.shapeRel1.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.shapeRel2.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.shapeRel3.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork);
                binding.shapeRel4.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.shapeRel5.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.shapeRel6.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.shapeRel7.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.shapeRel8.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.shapeRel9.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.shapeRel10.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.storageRel1.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.storageRel2.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.storageRel3.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.storageRel4.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.storageRel5.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.storageRel6.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.storageRel7.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.storageRel8.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.storageRel9.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.storageRel10.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);


                binding.editTextOnly.animate().alpha(0.0f);
                binding.editTextOnly.setVisibility(View.INVISIBLE);
                return true;
            }

        });


        binding.shapeRel4.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                RelativeLayout view = (RelativeLayout) v;
                view.bringToFront();
                viewTransformation(view, event);

                sharedPreferences = editFameActivity.this.getSharedPreferences("SHAPEPref", MODE_PRIVATE);
                SharedPreferences.Editor myEdit = sharedPreferences.edit();
                myEdit.putString("shape", "SELECTED_SHAPE4");
                myEdit.apply();

                sharedPreferencesForTextNull = getSharedPreferences("TEXTPref", MODE_PRIVATE);
                SharedPreferences.Editor myEdit2 = sharedPreferencesForTextNull.edit();
                myEdit2.putString("text", "");
                myEdit2.apply();

                sharedPreferenceStorageNull = editFameActivity.this.getSharedPreferences("STORAGEPref", MODE_PRIVATE);
                SharedPreferences.Editor myEdit3 = sharedPreferenceStorageNull.edit();
                myEdit3.putString("storage", "");
                myEdit3.apply();


                binding.edttextFragRel1.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.edttextFragRel2.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.edttextFragRel3.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.edttextFragRel4.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.edttextFragRel5.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.edttextFragRel6.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.edttextFragRel7.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.edttextFragRel8.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.edttextFragRel9.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.edttextFragRel10.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.shapeRel1.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.shapeRel2.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.shapeRel3.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.shapeRel4.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork);
                binding.shapeRel5.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.shapeRel6.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.shapeRel7.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.shapeRel8.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.shapeRel9.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.shapeRel10.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.storageRel1.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.storageRel2.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.storageRel3.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.storageRel4.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.storageRel5.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.storageRel6.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.storageRel7.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.storageRel8.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.storageRel9.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.storageRel10.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);


                binding.editTextOnly.animate().alpha(0.0f);
                binding.editTextOnly.setVisibility(View.INVISIBLE);
                return true;
            }

        });


        binding.shapeRel5.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                RelativeLayout view = (RelativeLayout) v;
                view.bringToFront();
                viewTransformation(view, event);

                sharedPreferences = editFameActivity.this.getSharedPreferences("SHAPEPref", MODE_PRIVATE);
                SharedPreferences.Editor myEdit = sharedPreferences.edit();
                myEdit.putString("shape", "SELECTED_SHAPE5");
                myEdit.apply();

                sharedPreferencesForTextNull = getSharedPreferences("TEXTPref", MODE_PRIVATE);
                SharedPreferences.Editor myEdit2 = sharedPreferencesForTextNull.edit();
                myEdit2.putString("text", "");
                myEdit2.apply();

                sharedPreferenceStorageNull = editFameActivity.this.getSharedPreferences("STORAGEPref", MODE_PRIVATE);
                SharedPreferences.Editor myEdit3 = sharedPreferenceStorageNull.edit();
                myEdit3.putString("storage", "");
                myEdit3.apply();
                binding.edttextFragRel1.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.edttextFragRel2.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.edttextFragRel3.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.edttextFragRel4.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.edttextFragRel5.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.edttextFragRel6.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.edttextFragRel7.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.edttextFragRel8.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.edttextFragRel9.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.edttextFragRel10.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.shapeRel1.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.shapeRel2.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.shapeRel3.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.shapeRel4.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.shapeRel5.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork);
                binding.shapeRel6.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.shapeRel7.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.shapeRel8.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.shapeRel9.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.shapeRel10.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.storageRel1.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.storageRel2.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.storageRel3.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.storageRel4.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.storageRel5.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.storageRel6.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.storageRel7.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.storageRel8.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.storageRel9.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.storageRel10.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);


                binding.editTextOnly.animate().alpha(0.0f);
                binding.editTextOnly.setVisibility(View.INVISIBLE);
                return true;


            }

        });


        binding.shapeRel6.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                RelativeLayout view = (RelativeLayout) v;
                view.bringToFront();
                viewTransformation(view, event);

                sharedPreferences = editFameActivity.this.getSharedPreferences("SHAPEPref", MODE_PRIVATE);
                SharedPreferences.Editor myEdit = sharedPreferences.edit();
                myEdit.putString("shape", "SELECTED_SHAPE6");
                myEdit.apply();

                sharedPreferencesForTextNull = getSharedPreferences("TEXTPref", MODE_PRIVATE);
                SharedPreferences.Editor myEdit2 = sharedPreferencesForTextNull.edit();
                myEdit2.putString("text", "");
                myEdit2.apply();

                sharedPreferenceStorageNull = editFameActivity.this.getSharedPreferences("STORAGEPref", MODE_PRIVATE);
                SharedPreferences.Editor myEdit3 = sharedPreferenceStorageNull.edit();
                myEdit3.putString("storage", "");
                myEdit3.apply();

                binding.edttextFragRel1.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.edttextFragRel2.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.edttextFragRel3.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.edttextFragRel4.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.edttextFragRel5.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.edttextFragRel6.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.edttextFragRel7.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.edttextFragRel8.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.edttextFragRel9.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.edttextFragRel10.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.shapeRel1.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.shapeRel2.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.shapeRel3.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.shapeRel4.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.shapeRel5.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.shapeRel6.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork);
                binding.shapeRel7.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.shapeRel8.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.shapeRel9.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.shapeRel10.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.storageRel1.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.storageRel2.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.storageRel3.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.storageRel4.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.storageRel5.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.storageRel6.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.storageRel7.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.storageRel8.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.storageRel9.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.storageRel10.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);

                binding.editTextOnly.animate().alpha(0.0f);
                binding.editTextOnly.setVisibility(View.INVISIBLE);
                return true;


            }

        });


        binding.shapeRel7.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                RelativeLayout view = (RelativeLayout) v;
                view.bringToFront();
                viewTransformation(view, event);

                sharedPreferences = editFameActivity.this.getSharedPreferences("SHAPEPref", MODE_PRIVATE);
                SharedPreferences.Editor myEdit = sharedPreferences.edit();
                myEdit.putString("shape", "SELECTED_SHAPE7");
                myEdit.apply();

                sharedPreferencesForTextNull = getSharedPreferences("TEXTPref", MODE_PRIVATE);
                SharedPreferences.Editor myEdit2 = sharedPreferencesForTextNull.edit();
                myEdit2.putString("text", "");
                myEdit2.apply();

                sharedPreferenceStorageNull = editFameActivity.this.getSharedPreferences("STORAGEPref", MODE_PRIVATE);
                SharedPreferences.Editor myEdit3 = sharedPreferenceStorageNull.edit();
                myEdit3.putString("storage", "");
                myEdit3.apply();


                binding.edttextFragRel1.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.edttextFragRel2.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.edttextFragRel3.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.edttextFragRel4.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.edttextFragRel5.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.edttextFragRel6.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.edttextFragRel7.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.edttextFragRel8.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.edttextFragRel9.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.edttextFragRel10.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.shapeRel1.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.shapeRel2.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.shapeRel3.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.shapeRel4.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.shapeRel5.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.shapeRel6.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.shapeRel7.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork);
                binding.shapeRel8.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.shapeRel9.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.shapeRel10.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.storageRel1.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.storageRel2.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.storageRel3.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.storageRel4.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.storageRel5.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.storageRel6.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.storageRel7.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.storageRel8.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.storageRel9.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.storageRel10.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);


                binding.editTextOnly.animate().alpha(0.0f);
                binding.editTextOnly.setVisibility(View.INVISIBLE);

                return true;
            }

        });


        binding.shapeRel8.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                RelativeLayout view = (RelativeLayout) v;
                view.bringToFront();
                viewTransformation(view, event);

                sharedPreferences = editFameActivity.this.getSharedPreferences("SHAPEPref", MODE_PRIVATE);
                SharedPreferences.Editor myEdit = sharedPreferences.edit();
                myEdit.putString("shape", "SELECTED_SHAPE8");
                myEdit.apply();

                sharedPreferencesForTextNull = getSharedPreferences("TEXTPref", MODE_PRIVATE);
                SharedPreferences.Editor myEdit2 = sharedPreferencesForTextNull.edit();
                myEdit2.putString("text", "");
                myEdit2.apply();

                sharedPreferenceStorageNull = editFameActivity.this.getSharedPreferences("STORAGEPref", MODE_PRIVATE);
                SharedPreferences.Editor myEdit3 = sharedPreferenceStorageNull.edit();
                myEdit3.putString("storage", "");
                myEdit3.apply();

                binding.edttextFragRel1.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.edttextFragRel2.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.edttextFragRel3.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.edttextFragRel4.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.edttextFragRel5.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.edttextFragRel6.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.edttextFragRel7.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.edttextFragRel8.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.edttextFragRel9.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.edttextFragRel10.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.shapeRel1.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.shapeRel2.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.shapeRel3.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.shapeRel4.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.shapeRel5.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.shapeRel6.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.shapeRel7.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.shapeRel8.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork);
                binding.shapeRel9.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.shapeRel10.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.storageRel1.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.storageRel2.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.storageRel3.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.storageRel4.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.storageRel5.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.storageRel6.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.storageRel7.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.storageRel8.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.storageRel9.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.storageRel10.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);


                binding.editTextOnly.animate().alpha(0.0f);
                binding.editTextOnly.setVisibility(View.INVISIBLE);

                return true;
            }

        });


        binding.shapeRel9.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                RelativeLayout view = (RelativeLayout) v;
                view.bringToFront();
                viewTransformation(view, event);

                sharedPreferences = editFameActivity.this.getSharedPreferences("SHAPEPref", MODE_PRIVATE);
                SharedPreferences.Editor myEdit = sharedPreferences.edit();
                myEdit.putString("shape", "SELECTED_SHAPE9");
                myEdit.apply();

                sharedPreferencesForTextNull = getSharedPreferences("TEXTPref", MODE_PRIVATE);
                SharedPreferences.Editor myEdit2 = sharedPreferencesForTextNull.edit();
                myEdit2.putString("text", "");
                myEdit2.apply();

                sharedPreferenceStorageNull = editFameActivity.this.getSharedPreferences("STORAGEPref", MODE_PRIVATE);
                SharedPreferences.Editor myEdit3 = sharedPreferenceStorageNull.edit();
                myEdit3.putString("storage", "");
                myEdit3.apply();

                binding.edttextFragRel1.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.edttextFragRel2.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.edttextFragRel3.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.edttextFragRel4.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.edttextFragRel5.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.edttextFragRel6.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.edttextFragRel7.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.edttextFragRel8.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.edttextFragRel9.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.edttextFragRel10.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.shapeRel1.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.shapeRel2.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.shapeRel3.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.shapeRel4.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.shapeRel5.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.shapeRel6.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.shapeRel7.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.shapeRel8.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.shapeRel9.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork);
                binding.shapeRel10.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.storageRel1.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.storageRel2.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.storageRel3.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.storageRel4.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.storageRel5.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.storageRel6.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.storageRel7.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.storageRel8.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.storageRel9.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.storageRel10.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);


                binding.editTextOnly.animate().alpha(0.0f);
                binding.editTextOnly.setVisibility(View.INVISIBLE);

                return true;
            }

        });


        binding.shapeRel10.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                RelativeLayout view = (RelativeLayout) v;
                view.bringToFront();
                viewTransformation(view, event);

                sharedPreferences = editFameActivity.this.getSharedPreferences("SHAPEPref", MODE_PRIVATE);
                SharedPreferences.Editor myEdit = sharedPreferences.edit();
                myEdit.putString("shape", "SELECTED_SHAPE10");
                myEdit.apply();

                sharedPreferencesForTextNull = getSharedPreferences("TEXTPref", MODE_PRIVATE);
                SharedPreferences.Editor myEdit2 = sharedPreferencesForTextNull.edit();
                myEdit2.putString("text", "");
                myEdit2.apply();

                sharedPreferenceStorageNull = editFameActivity.this.getSharedPreferences("STORAGEPref", MODE_PRIVATE);
                SharedPreferences.Editor myEdit3 = sharedPreferenceStorageNull.edit();
                myEdit3.putString("storage", "");
                myEdit3.apply();

                binding.edttextFragRel1.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.edttextFragRel2.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.edttextFragRel3.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.edttextFragRel4.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.edttextFragRel5.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.edttextFragRel6.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.edttextFragRel7.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.edttextFragRel8.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.edttextFragRel9.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.edttextFragRel10.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.shapeRel1.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.shapeRel2.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.shapeRel3.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.shapeRel4.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.shapeRel5.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.shapeRel6.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.shapeRel7.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.shapeRel8.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.shapeRel9.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.shapeRel10.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork);
                binding.storageRel1.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.storageRel2.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.storageRel3.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.storageRel4.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.storageRel5.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.storageRel6.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.storageRel7.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.storageRel8.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.storageRel9.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.storageRel10.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);


                binding.editTextOnly.animate().alpha(0.0f);
                binding.editTextOnly.setVisibility(View.INVISIBLE);

                return true;
            }

        });


        //here for draging storage images from local stoprage


        binding.storageRel1.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                RelativeLayout view = (RelativeLayout) v;
                view.bringToFront();
                viewTransformation(view, event);

                sharedPreferences = editFameActivity.this.getSharedPreferences("STORAGEPref", MODE_PRIVATE);
                SharedPreferences.Editor myEdit = sharedPreferences.edit();
                myEdit.putString("storage", "SELECTED_STORAGE1");
                myEdit.apply();

                sharedPreferences = editFameActivity.this.getSharedPreferences("SHAPEPref", MODE_PRIVATE);
                SharedPreferences.Editor myEdit2 = sharedPreferences.edit();
                myEdit2.putString("shape", "");
                myEdit2.apply();

                sharedPreferencesForTextNull = getSharedPreferences("TEXTPref", MODE_PRIVATE);
                SharedPreferences.Editor myEdit3 = sharedPreferencesForTextNull.edit();
                myEdit3.putString("text", "");
                myEdit3.apply();

                binding.edttextFragRel1.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.edttextFragRel2.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.edttextFragRel3.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.edttextFragRel4.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.edttextFragRel5.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.edttextFragRel6.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.edttextFragRel7.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.edttextFragRel8.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.edttextFragRel9.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.edttextFragRel10.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.shapeRel1.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.shapeRel2.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.shapeRel3.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.shapeRel4.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.shapeRel5.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.shapeRel6.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.shapeRel7.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.shapeRel8.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.shapeRel9.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.shapeRel10.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.storageRel1.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork);
                binding.storageRel2.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.storageRel3.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.storageRel4.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.storageRel5.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.storageRel6.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.storageRel7.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.storageRel8.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.storageRel9.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.storageRel10.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);


                binding.editTextOnly.animate().alpha(0.0f);
                binding.editTextOnly.setVisibility(View.INVISIBLE);

                return true;
            }

        });
        binding.storageRel2.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                RelativeLayout view = (RelativeLayout) v;
                view.bringToFront();
                viewTransformation(view, event);

                sharedPreferences = editFameActivity.this.getSharedPreferences("STORAGEPref", MODE_PRIVATE);
                SharedPreferences.Editor myEdit = sharedPreferences.edit();
                myEdit.putString("storage", "SELECTED_STORAGE2");
                myEdit.apply();

                sharedPreferences = editFameActivity.this.getSharedPreferences("SHAPEPref", MODE_PRIVATE);
                SharedPreferences.Editor myEdit2 = sharedPreferences.edit();
                myEdit2.putString("shape", "");
                myEdit2.apply();

                sharedPreferencesForTextNull = getSharedPreferences("TEXTPref", MODE_PRIVATE);
                SharedPreferences.Editor myEdit3 = sharedPreferencesForTextNull.edit();
                myEdit3.putString("text", "");
                myEdit3.apply();

                binding.edttextFragRel1.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.edttextFragRel2.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.edttextFragRel3.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.edttextFragRel4.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.edttextFragRel5.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.edttextFragRel6.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.edttextFragRel7.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.edttextFragRel8.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.edttextFragRel9.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.edttextFragRel10.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.shapeRel1.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.shapeRel2.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.shapeRel3.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.shapeRel4.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.shapeRel5.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.shapeRel6.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.shapeRel7.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.shapeRel8.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.shapeRel9.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.shapeRel10.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.storageRel1.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.storageRel2.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork);
                binding.storageRel3.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.storageRel4.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.storageRel5.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.storageRel6.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.storageRel7.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.storageRel8.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.storageRel9.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.storageRel10.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);


                binding.editTextOnly.animate().alpha(0.0f);
                binding.editTextOnly.setVisibility(View.INVISIBLE);

                return true;
            }

        });
        binding.storageRel3.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                RelativeLayout view = (RelativeLayout) v;
                view.bringToFront();
                viewTransformation(view, event);

                sharedPreferences = editFameActivity.this.getSharedPreferences("STORAGEPref", MODE_PRIVATE);
                SharedPreferences.Editor myEdit = sharedPreferences.edit();
                myEdit.putString("storage", "SELECTED_STORAGE3");
                myEdit.apply();

                sharedPreferences = editFameActivity.this.getSharedPreferences("SHAPEPref", MODE_PRIVATE);
                SharedPreferences.Editor myEdit2 = sharedPreferences.edit();
                myEdit2.putString("shape", "");
                myEdit2.apply();

                sharedPreferencesForTextNull = getSharedPreferences("TEXTPref", MODE_PRIVATE);
                SharedPreferences.Editor myEdit3 = sharedPreferencesForTextNull.edit();
                myEdit3.putString("text", "");
                myEdit3.apply();

                binding.edttextFragRel1.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.edttextFragRel2.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.edttextFragRel3.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.edttextFragRel4.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.edttextFragRel5.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.edttextFragRel6.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.edttextFragRel7.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.edttextFragRel8.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.edttextFragRel9.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.edttextFragRel10.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.shapeRel1.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.shapeRel2.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.shapeRel3.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.shapeRel4.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.shapeRel5.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.shapeRel6.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.shapeRel7.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.shapeRel8.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.shapeRel9.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.shapeRel10.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.storageRel1.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.storageRel2.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.storageRel3.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork);
                binding.storageRel4.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.storageRel5.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.storageRel6.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.storageRel7.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.storageRel8.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.storageRel9.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.storageRel10.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);


                binding.editTextOnly.animate().alpha(0.0f);
                binding.editTextOnly.setVisibility(View.INVISIBLE);

                return true;
            }

        });
        binding.storageRel4.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                RelativeLayout view = (RelativeLayout) v;
                view.bringToFront();
                viewTransformation(view, event);

                sharedPreferences = editFameActivity.this.getSharedPreferences("STORAGEPref", MODE_PRIVATE);
                SharedPreferences.Editor myEdit = sharedPreferences.edit();
                myEdit.putString("storage", "SELECTED_STORAGE4");
                myEdit.apply();

                sharedPreferences = editFameActivity.this.getSharedPreferences("SHAPEPref", MODE_PRIVATE);
                SharedPreferences.Editor myEdit2 = sharedPreferences.edit();
                myEdit2.putString("shape", "");
                myEdit2.apply();

                sharedPreferencesForTextNull = getSharedPreferences("TEXTPref", MODE_PRIVATE);
                SharedPreferences.Editor myEdit3 = sharedPreferencesForTextNull.edit();
                myEdit3.putString("text", "");
                myEdit3.apply();

                binding.edttextFragRel1.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.edttextFragRel2.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.edttextFragRel3.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.edttextFragRel4.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.edttextFragRel5.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.edttextFragRel6.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.edttextFragRel7.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.edttextFragRel8.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.edttextFragRel9.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.edttextFragRel10.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.shapeRel1.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.shapeRel2.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.shapeRel3.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.shapeRel4.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.shapeRel5.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.shapeRel6.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.shapeRel7.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.shapeRel8.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.shapeRel9.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.shapeRel10.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.storageRel1.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.storageRel2.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.storageRel3.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.storageRel4.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork);
                binding.storageRel5.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.storageRel6.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.storageRel7.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.storageRel8.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.storageRel9.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.storageRel10.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);


                binding.editTextOnly.animate().alpha(0.0f);
                binding.editTextOnly.setVisibility(View.INVISIBLE);

                return true;
            }

        });
        binding.storageRel5.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                RelativeLayout view = (RelativeLayout) v;
                view.bringToFront();
                viewTransformation(view, event);

                sharedPreferences = editFameActivity.this.getSharedPreferences("STORAGEPref", MODE_PRIVATE);
                SharedPreferences.Editor myEdit = sharedPreferences.edit();
                myEdit.putString("storage", "SELECTED_STORAGE5");
                myEdit.apply();

                sharedPreferences = editFameActivity.this.getSharedPreferences("SHAPEPref", MODE_PRIVATE);
                SharedPreferences.Editor myEdit2 = sharedPreferences.edit();
                myEdit2.putString("shape", "");
                myEdit2.apply();

                sharedPreferencesForTextNull = getSharedPreferences("TEXTPref", MODE_PRIVATE);
                SharedPreferences.Editor myEdit3 = sharedPreferencesForTextNull.edit();
                myEdit3.putString("text", "");
                myEdit3.apply();

                binding.edttextFragRel1.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.edttextFragRel2.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.edttextFragRel3.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.edttextFragRel4.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.edttextFragRel5.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.edttextFragRel6.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.edttextFragRel7.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.edttextFragRel8.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.edttextFragRel9.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.edttextFragRel10.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.shapeRel1.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.shapeRel2.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.shapeRel3.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.shapeRel4.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.shapeRel5.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.shapeRel6.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.shapeRel7.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.shapeRel8.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.shapeRel9.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.shapeRel10.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.storageRel1.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.storageRel2.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.storageRel3.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.storageRel4.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.storageRel5.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork);
                binding.storageRel6.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.storageRel7.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.storageRel8.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.storageRel9.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.storageRel10.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);


                binding.editTextOnly.animate().alpha(0.0f);
                binding.editTextOnly.setVisibility(View.INVISIBLE);

                return true;
            }

        });
        binding.storageRel6.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                RelativeLayout view = (RelativeLayout) v;
                view.bringToFront();
                viewTransformation(view, event);


                sharedPreferences = editFameActivity.this.getSharedPreferences("STORAGEPref", MODE_PRIVATE);
                SharedPreferences.Editor myEdit = sharedPreferences.edit();
                myEdit.putString("storage", "SELECTED_STORAGE6");
                myEdit.apply();

                sharedPreferences = editFameActivity.this.getSharedPreferences("SHAPEPref", MODE_PRIVATE);
                SharedPreferences.Editor myEdit2 = sharedPreferences.edit();
                myEdit2.putString("shape", "");
                myEdit2.apply();

                sharedPreferencesForTextNull = getSharedPreferences("TEXTPref", MODE_PRIVATE);
                SharedPreferences.Editor myEdit3 = sharedPreferencesForTextNull.edit();
                myEdit3.putString("text", "");
                myEdit3.apply();

                binding.edttextFragRel1.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.edttextFragRel2.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.edttextFragRel3.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.edttextFragRel4.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.edttextFragRel5.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.edttextFragRel6.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.edttextFragRel7.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.edttextFragRel8.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.edttextFragRel9.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.edttextFragRel10.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.shapeRel1.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.shapeRel2.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.shapeRel3.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.shapeRel4.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.shapeRel5.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.shapeRel6.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.shapeRel7.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.shapeRel8.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.shapeRel9.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.shapeRel10.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.storageRel1.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.storageRel2.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.storageRel3.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.storageRel4.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.storageRel5.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.storageRel6.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork);
                binding.storageRel7.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.storageRel8.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.storageRel9.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.storageRel10.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);


                binding.editTextOnly.animate().alpha(0.0f);
                binding.editTextOnly.setVisibility(View.INVISIBLE);

                return true;
            }

        });
        binding.storageRel7.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                RelativeLayout view = (RelativeLayout) v;
                view.bringToFront();
                viewTransformation(view, event);

                sharedPreferences = editFameActivity.this.getSharedPreferences("STORAGEPref", MODE_PRIVATE);
                SharedPreferences.Editor myEdit = sharedPreferences.edit();
                myEdit.putString("storage", "SELECTED_STORAGE7");
                myEdit.apply();

                sharedPreferences = editFameActivity.this.getSharedPreferences("SHAPEPref", MODE_PRIVATE);
                SharedPreferences.Editor myEdit2 = sharedPreferences.edit();
                myEdit2.putString("shape", "");
                myEdit2.apply();

                sharedPreferencesForTextNull = getSharedPreferences("TEXTPref", MODE_PRIVATE);
                SharedPreferences.Editor myEdit3 = sharedPreferencesForTextNull.edit();
                myEdit3.putString("text", "");
                myEdit3.apply();

                binding.edttextFragRel1.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.edttextFragRel2.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.edttextFragRel3.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.edttextFragRel4.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.edttextFragRel5.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.edttextFragRel6.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.edttextFragRel7.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.edttextFragRel8.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.edttextFragRel9.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.edttextFragRel10.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.shapeRel1.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.shapeRel2.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.shapeRel3.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.shapeRel4.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.shapeRel5.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.shapeRel6.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.shapeRel7.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.shapeRel8.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.shapeRel9.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.shapeRel10.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.storageRel1.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.storageRel2.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.storageRel3.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.storageRel4.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.storageRel5.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.storageRel6.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.storageRel7.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork);
                binding.storageRel8.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.storageRel9.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.storageRel10.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);


                binding.editTextOnly.animate().alpha(0.0f);
                binding.editTextOnly.setVisibility(View.INVISIBLE);

                return true;
            }

        });
        binding.storageRel8.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                RelativeLayout view = (RelativeLayout) v;
                view.bringToFront();
                viewTransformation(view, event);

                sharedPreferences = editFameActivity.this.getSharedPreferences("STORAGEPref", MODE_PRIVATE);
                SharedPreferences.Editor myEdit = sharedPreferences.edit();
                myEdit.putString("storage", "SELECTED_STORAGE8");
                myEdit.apply();

                sharedPreferences = editFameActivity.this.getSharedPreferences("SHAPEPref", MODE_PRIVATE);
                SharedPreferences.Editor myEdit2 = sharedPreferences.edit();
                myEdit2.putString("shape", "");
                myEdit2.apply();

                sharedPreferencesForTextNull = getSharedPreferences("TEXTPref", MODE_PRIVATE);
                SharedPreferences.Editor myEdit3 = sharedPreferencesForTextNull.edit();
                myEdit3.putString("text", "");
                myEdit3.apply();

                binding.edttextFragRel1.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.edttextFragRel2.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.edttextFragRel3.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.edttextFragRel4.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.edttextFragRel5.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.edttextFragRel6.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.edttextFragRel7.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.edttextFragRel8.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.edttextFragRel9.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.edttextFragRel10.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.shapeRel1.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.shapeRel2.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.shapeRel3.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.shapeRel4.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.shapeRel5.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.shapeRel6.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.shapeRel7.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.shapeRel8.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.shapeRel9.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.shapeRel10.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.storageRel1.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.storageRel2.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.storageRel3.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.storageRel4.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.storageRel5.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.storageRel6.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.storageRel7.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.storageRel8.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork);
                binding.storageRel9.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.storageRel10.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);


                binding.editTextOnly.animate().alpha(0.0f);
                binding.editTextOnly.setVisibility(View.INVISIBLE);

                return true;
            }

        });
        binding.storageRel9.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                RelativeLayout view = (RelativeLayout) v;
                view.bringToFront();
                viewTransformation(view, event);

                sharedPreferences = editFameActivity.this.getSharedPreferences("STORAGEPref", MODE_PRIVATE);
                SharedPreferences.Editor myEdit = sharedPreferences.edit();
                myEdit.putString("storage", "SELECTED_STORAGE9");
                myEdit.apply();

                sharedPreferences = editFameActivity.this.getSharedPreferences("SHAPEPref", MODE_PRIVATE);
                SharedPreferences.Editor myEdit2 = sharedPreferences.edit();
                myEdit2.putString("shape", "");
                myEdit2.apply();

                sharedPreferencesForTextNull = getSharedPreferences("TEXTPref", MODE_PRIVATE);
                SharedPreferences.Editor myEdit3 = sharedPreferencesForTextNull.edit();
                myEdit3.putString("text", "");
                myEdit3.apply();

                binding.edttextFragRel1.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.edttextFragRel2.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.edttextFragRel3.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.edttextFragRel4.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.edttextFragRel5.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.edttextFragRel6.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.edttextFragRel7.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.edttextFragRel8.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.edttextFragRel9.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.edttextFragRel10.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.shapeRel1.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.shapeRel2.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.shapeRel3.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.shapeRel4.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.shapeRel5.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.shapeRel6.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.shapeRel7.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.shapeRel8.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.shapeRel9.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.shapeRel10.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.storageRel1.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.storageRel2.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.storageRel3.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.storageRel4.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.storageRel5.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.storageRel6.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.storageRel7.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.storageRel8.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.storageRel9.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork);
                binding.storageRel10.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);


                binding.editTextOnly.animate().alpha(0.0f);
                binding.editTextOnly.setVisibility(View.INVISIBLE);
                return true;
            }

        });
        binding.storageRel10.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                RelativeLayout view = (RelativeLayout) v;
                view.bringToFront();
                viewTransformation(view, event);

                sharedPreferences = editFameActivity.this.getSharedPreferences("STORAGEPref", MODE_PRIVATE);
                SharedPreferences.Editor myEdit = sharedPreferences.edit();
                myEdit.putString("storage", "SELECTED_STORAGE10");
                myEdit.apply();

                sharedPreferences = editFameActivity.this.getSharedPreferences("SHAPEPref", MODE_PRIVATE);
                SharedPreferences.Editor myEdit2 = sharedPreferences.edit();
                myEdit2.putString("shape", "");
                myEdit2.apply();

                sharedPreferencesForTextNull = getSharedPreferences("TEXTPref", MODE_PRIVATE);
                SharedPreferences.Editor myEdit3 = sharedPreferencesForTextNull.edit();
                myEdit3.putString("text", "");
                myEdit3.apply();

                binding.edttextFragRel1.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.edttextFragRel2.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.edttextFragRel3.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.edttextFragRel4.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.edttextFragRel5.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.edttextFragRel6.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.edttextFragRel7.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.edttextFragRel8.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.edttextFragRel9.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.edttextFragRel10.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.shapeRel1.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.shapeRel2.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.shapeRel3.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.shapeRel4.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.shapeRel5.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.shapeRel6.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.shapeRel7.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.shapeRel8.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.shapeRel9.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.shapeRel10.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.storageRel1.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.storageRel2.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.storageRel3.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.storageRel4.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.storageRel5.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.storageRel6.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.storageRel7.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.storageRel8.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.storageRel9.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
                binding.storageRel10.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork);


                binding.editTextOnly.animate().alpha(0.0f);
                binding.editTextOnly.setVisibility(View.INVISIBLE);
                return true;
            }

        });


    }


    //add text highlight auto select
    public void highlightText1() {


        sharedPreferences = editFameActivity.this.getSharedPreferences("TEXTPref", MODE_PRIVATE);
        SharedPreferences.Editor myEdit = sharedPreferences.edit();
        myEdit.putString("text", "SELECTED1");
        myEdit.apply();


        sharedPreferencesForShapeNull = getSharedPreferences("SHAPEPref", MODE_PRIVATE);
        SharedPreferences.Editor myEdit2 = sharedPreferencesForShapeNull.edit();
        myEdit2.putString("shape", "");
        myEdit2.apply();

        sharedPreferenceStorageNull = editFameActivity.this.getSharedPreferences("STORAGEPref", MODE_PRIVATE);
        SharedPreferences.Editor myEdit3 = sharedPreferenceStorageNull.edit();
        myEdit3.putString("storage", "");
        myEdit3.apply();
        binding.dummyButonForAddFont.performClick();


        binding.edttextFragRel1.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork);
        binding.edttextFragRel2.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.edttextFragRel3.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.edttextFragRel4.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.edttextFragRel5.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.edttextFragRel6.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.edttextFragRel7.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.edttextFragRel8.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.edttextFragRel9.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.edttextFragRel10.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.shapeRel1.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.shapeRel2.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.shapeRel3.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.shapeRel4.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.shapeRel5.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.shapeRel6.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.shapeRel7.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.shapeRel8.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.shapeRel9.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.shapeRel10.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.storageRel1.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.storageRel2.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.storageRel3.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.storageRel4.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.storageRel5.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.storageRel6.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.storageRel7.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.storageRel8.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.storageRel9.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.storageRel10.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);

        binding.editTextOnly.animate().alpha(1.0f);
        binding.editTextOnly.setVisibility(View.VISIBLE);


    }

    public void highlightText2() {

        sharedPreferences = editFameActivity.this.getSharedPreferences("TEXTPref", MODE_PRIVATE);
        SharedPreferences.Editor myEdit = sharedPreferences.edit();
        myEdit.putString("text", "SELECTED2");
        myEdit.apply();

        sharedPreferencesForShapeNull = getSharedPreferences("SHAPEPref", MODE_PRIVATE);
        SharedPreferences.Editor myEdit2 = sharedPreferencesForShapeNull.edit();
        myEdit2.putString("shape", "");
        myEdit2.apply();

        sharedPreferenceStorageNull = editFameActivity.this.getSharedPreferences("STORAGEPref", MODE_PRIVATE);
        SharedPreferences.Editor myEdit3 = sharedPreferenceStorageNull.edit();
        myEdit3.putString("storage", "");
        myEdit3.apply();

        binding.dummyButonForAddFont.performClick();

        binding.edttextFragRel1.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.edttextFragRel2.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork);
        binding.edttextFragRel3.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.edttextFragRel4.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.edttextFragRel5.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.edttextFragRel6.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.edttextFragRel7.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.edttextFragRel8.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.edttextFragRel9.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.edttextFragRel10.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.shapeRel1.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.shapeRel2.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.shapeRel3.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.shapeRel4.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.shapeRel5.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.shapeRel6.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.shapeRel7.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.shapeRel8.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.shapeRel9.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.shapeRel10.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.storageRel1.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.storageRel2.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.storageRel3.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.storageRel4.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.storageRel5.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.storageRel6.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.storageRel7.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.storageRel8.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.storageRel9.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.storageRel10.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);

        binding.editTextOnly.animate().alpha(1.0f);
        binding.editTextOnly.setVisibility(View.VISIBLE);
    }

    public void highlightText3() {
        sharedPreferences = editFameActivity.this.getSharedPreferences("TEXTPref", MODE_PRIVATE);
        SharedPreferences.Editor myEdit = sharedPreferences.edit();
        myEdit.putString("text", "SELECTED3");
        myEdit.apply();

        sharedPreferencesForShapeNull = getSharedPreferences("SHAPEPref", MODE_PRIVATE);
        SharedPreferences.Editor myEdit2 = sharedPreferencesForShapeNull.edit();
        myEdit2.putString("shape", "");
        myEdit2.apply();

        sharedPreferenceStorageNull = editFameActivity.this.getSharedPreferences("STORAGEPref", MODE_PRIVATE);
        SharedPreferences.Editor myEdit3 = sharedPreferenceStorageNull.edit();
        myEdit3.putString("storage", "");
        myEdit3.apply();

        binding.dummyButonForAddFont.performClick();

        binding.edttextFragRel1.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.edttextFragRel2.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.edttextFragRel3.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork);
        binding.edttextFragRel4.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.edttextFragRel5.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.edttextFragRel6.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.edttextFragRel7.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.edttextFragRel8.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.edttextFragRel9.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.edttextFragRel10.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.shapeRel1.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.shapeRel2.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.shapeRel3.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.shapeRel4.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.shapeRel5.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.shapeRel6.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.shapeRel7.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.shapeRel8.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.shapeRel9.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.shapeRel10.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.storageRel1.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.storageRel2.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.storageRel3.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.storageRel4.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.storageRel5.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.storageRel6.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.storageRel7.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.storageRel8.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.storageRel9.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.storageRel10.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);


        binding.editTextOnly.animate().alpha(1.0f);
        binding.editTextOnly.setVisibility(View.VISIBLE);

    }

    public void highlightText4() {

        sharedPreferences = editFameActivity.this.getSharedPreferences("TEXTPref", MODE_PRIVATE);
        SharedPreferences.Editor myEdit = sharedPreferences.edit();
        myEdit.putString("text", "SELECTED4");
        myEdit.apply();

        sharedPreferencesForShapeNull = getSharedPreferences("SHAPEPref", MODE_PRIVATE);
        SharedPreferences.Editor myEdit2 = sharedPreferencesForShapeNull.edit();
        myEdit2.putString("shape", "");
        myEdit2.apply();

        sharedPreferenceStorageNull = editFameActivity.this.getSharedPreferences("STORAGEPref", MODE_PRIVATE);
        SharedPreferences.Editor myEdit3 = sharedPreferenceStorageNull.edit();
        myEdit3.putString("storage", "");
        myEdit3.apply();
        binding.dummyButonForAddFont.performClick();

        binding.edttextFragRel1.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.edttextFragRel2.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.edttextFragRel3.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.edttextFragRel4.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork);
        binding.edttextFragRel5.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.edttextFragRel6.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.edttextFragRel7.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.edttextFragRel8.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.edttextFragRel9.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.edttextFragRel10.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.shapeRel1.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.shapeRel2.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.shapeRel3.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.shapeRel4.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.shapeRel5.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.shapeRel6.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.shapeRel7.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.shapeRel8.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.shapeRel9.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.shapeRel10.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.storageRel1.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.storageRel2.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.storageRel3.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.storageRel4.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.storageRel5.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.storageRel6.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.storageRel7.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.storageRel8.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.storageRel9.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.storageRel10.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);


        binding.editTextOnly.animate().alpha(1.0f);
        binding.editTextOnly.setVisibility(View.VISIBLE);
    }

    public void highlightText5() {

        sharedPreferences = editFameActivity.this.getSharedPreferences("TEXTPref", MODE_PRIVATE);
        SharedPreferences.Editor myEdit = sharedPreferences.edit();
        myEdit.putString("text", "SELECTED5");
        myEdit.apply();

        sharedPreferencesForShapeNull = getSharedPreferences("SHAPEPref", MODE_PRIVATE);
        SharedPreferences.Editor myEdit2 = sharedPreferencesForShapeNull.edit();
        myEdit2.putString("shape", "");
        myEdit2.apply();

        sharedPreferenceStorageNull = editFameActivity.this.getSharedPreferences("STORAGEPref", MODE_PRIVATE);
        SharedPreferences.Editor myEdit3 = sharedPreferenceStorageNull.edit();
        myEdit3.putString("storage", "");
        myEdit3.apply();
        binding.dummyButonForAddFont.performClick();

        binding.edttextFragRel1.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.edttextFragRel2.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.edttextFragRel3.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.edttextFragRel4.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.edttextFragRel5.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork);
        binding.edttextFragRel6.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.edttextFragRel7.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.edttextFragRel8.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.edttextFragRel9.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.edttextFragRel10.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.shapeRel1.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.shapeRel2.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.shapeRel3.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.shapeRel4.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.shapeRel5.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.shapeRel6.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.shapeRel7.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.shapeRel8.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.shapeRel9.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.shapeRel10.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.storageRel1.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.storageRel2.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.storageRel3.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.storageRel4.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.storageRel5.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.storageRel6.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.storageRel7.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.storageRel8.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.storageRel9.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.storageRel10.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);

        binding.editTextOnly.animate().alpha(1.0f);
        binding.editTextOnly.setVisibility(View.VISIBLE);
    }

    public void hightlightText6() {

        sharedPreferences = editFameActivity.this.getSharedPreferences("TEXTPref", MODE_PRIVATE);
        SharedPreferences.Editor myEdit = sharedPreferences.edit();
        myEdit.putString("text", "SELECTED6");
        myEdit.apply();

        sharedPreferencesForShapeNull = getSharedPreferences("SHAPEPref", MODE_PRIVATE);
        SharedPreferences.Editor myEdit2 = sharedPreferencesForShapeNull.edit();
        myEdit2.putString("shape", "");
        myEdit2.apply();

        sharedPreferenceStorageNull = editFameActivity.this.getSharedPreferences("STORAGEPref", MODE_PRIVATE);
        SharedPreferences.Editor myEdit3 = sharedPreferenceStorageNull.edit();
        myEdit3.putString("storage", "");
        myEdit3.apply();

        binding.dummyButonForAddFont.performClick();


        binding.edttextFragRel1.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.edttextFragRel2.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.edttextFragRel3.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.edttextFragRel4.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.edttextFragRel5.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.edttextFragRel6.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork);
        binding.edttextFragRel7.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.edttextFragRel8.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.edttextFragRel9.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.edttextFragRel10.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.shapeRel1.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.shapeRel2.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.shapeRel3.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.shapeRel4.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.shapeRel5.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.shapeRel6.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.shapeRel7.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.shapeRel8.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.shapeRel9.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.shapeRel10.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.storageRel1.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.storageRel2.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.storageRel3.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.storageRel4.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.storageRel5.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.storageRel6.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.storageRel7.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.storageRel8.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.storageRel9.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.storageRel10.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);


        binding.editTextOnly.animate().alpha(1.0f);
        binding.editTextOnly.setVisibility(View.VISIBLE);
    }

    public void highlightText7() {

        sharedPreferences = editFameActivity.this.getSharedPreferences("TEXTPref", MODE_PRIVATE);
        SharedPreferences.Editor myEdit = sharedPreferences.edit();
        myEdit.putString("text", "SELECTED7");
        myEdit.apply();

        sharedPreferencesForShapeNull = getSharedPreferences("SHAPEPref", MODE_PRIVATE);
        SharedPreferences.Editor myEdit2 = sharedPreferencesForShapeNull.edit();
        myEdit2.putString("shape", "");
        myEdit2.apply();

        sharedPreferenceStorageNull = editFameActivity.this.getSharedPreferences("STORAGEPref", MODE_PRIVATE);
        SharedPreferences.Editor myEdit3 = sharedPreferenceStorageNull.edit();
        myEdit3.putString("storage", "");
        myEdit3.apply();
        binding.dummyButonForAddFont.performClick();

        binding.edttextFragRel1.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.edttextFragRel2.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.edttextFragRel3.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.edttextFragRel4.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.edttextFragRel5.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.edttextFragRel6.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.edttextFragRel7.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork);
        binding.edttextFragRel8.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.edttextFragRel9.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.edttextFragRel10.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.shapeRel1.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.shapeRel2.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.shapeRel3.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.shapeRel4.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.shapeRel5.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.shapeRel6.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.shapeRel7.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.shapeRel8.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.shapeRel9.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.shapeRel10.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.storageRel1.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.storageRel2.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.storageRel3.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.storageRel4.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.storageRel5.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.storageRel6.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.storageRel7.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.storageRel8.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.storageRel9.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.storageRel10.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);


        binding.editTextOnly.animate().alpha(1.0f);
        binding.editTextOnly.setVisibility(View.VISIBLE);
    }

    public void highlightText8() {

        sharedPreferences = editFameActivity.this.getSharedPreferences("TEXTPref", MODE_PRIVATE);
        SharedPreferences.Editor myEdit = sharedPreferences.edit();
        myEdit.putString("text", "SELECTED8");
        myEdit.apply();

        sharedPreferencesForShapeNull = getSharedPreferences("SHAPEPref", MODE_PRIVATE);
        SharedPreferences.Editor myEdit2 = sharedPreferencesForShapeNull.edit();
        myEdit2.putString("shape", "");
        myEdit2.apply();

        sharedPreferenceStorageNull = editFameActivity.this.getSharedPreferences("STORAGEPref", MODE_PRIVATE);
        SharedPreferences.Editor myEdit3 = sharedPreferenceStorageNull.edit();
        myEdit3.putString("storage", "");
        myEdit3.apply();

        binding.dummyButonForAddFont.performClick();


        binding.edttextFragRel1.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.edttextFragRel2.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.edttextFragRel3.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.edttextFragRel4.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.edttextFragRel5.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.edttextFragRel6.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.edttextFragRel7.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.edttextFragRel8.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork);
        binding.edttextFragRel9.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.edttextFragRel10.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.shapeRel1.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.shapeRel2.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.shapeRel3.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.shapeRel4.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.shapeRel5.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.shapeRel6.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.shapeRel7.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.shapeRel8.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.shapeRel9.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.shapeRel10.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.storageRel1.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.storageRel2.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.storageRel3.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.storageRel4.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.storageRel5.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.storageRel6.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.storageRel7.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.storageRel8.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.storageRel9.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.storageRel10.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);

        binding.editTextOnly.animate().alpha(1.0f);
        binding.editTextOnly.setVisibility(View.VISIBLE);
    }

    public void highlightText9() {

        sharedPreferences = editFameActivity.this.getSharedPreferences("TEXTPref", MODE_PRIVATE);
        SharedPreferences.Editor myEdit = sharedPreferences.edit();
        myEdit.putString("text", "SELECTED9");
        myEdit.apply();

        sharedPreferencesForShapeNull = getSharedPreferences("SHAPEPref", MODE_PRIVATE);
        SharedPreferences.Editor myEdit2 = sharedPreferencesForShapeNull.edit();
        myEdit2.putString("shape", "");
        myEdit2.apply();

        sharedPreferenceStorageNull = editFameActivity.this.getSharedPreferences("STORAGEPref", MODE_PRIVATE);
        SharedPreferences.Editor myEdit3 = sharedPreferenceStorageNull.edit();
        myEdit3.putString("storage", "");
        myEdit3.apply();

        binding.dummyButonForAddFont.performClick();

        binding.edttextFragRel1.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.edttextFragRel2.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.edttextFragRel3.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.edttextFragRel4.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.edttextFragRel5.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.edttextFragRel6.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.edttextFragRel7.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.edttextFragRel8.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.edttextFragRel9.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork);
        binding.edttextFragRel10.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.shapeRel1.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.shapeRel2.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.shapeRel3.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.shapeRel4.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.shapeRel5.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.shapeRel6.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.shapeRel7.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.shapeRel8.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.shapeRel9.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.shapeRel10.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.storageRel1.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.storageRel2.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.storageRel3.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.storageRel4.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.storageRel5.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.storageRel6.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.storageRel7.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.storageRel8.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.storageRel9.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.storageRel10.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);

        binding.editTextOnly.animate().alpha(1.0f);
        binding.editTextOnly.setVisibility(View.VISIBLE);

    }

    public void highlightText10() {

        sharedPreferences = editFameActivity.this.getSharedPreferences("TEXTPref", MODE_PRIVATE);
        SharedPreferences.Editor myEdit = sharedPreferences.edit();
        myEdit.putString("text", "SELECTED10");
        myEdit.apply();

        sharedPreferencesForShapeNull = getSharedPreferences("SHAPEPref", MODE_PRIVATE);
        SharedPreferences.Editor myEdit2 = sharedPreferencesForShapeNull.edit();
        myEdit2.putString("shape", "");
        myEdit2.apply();

        sharedPreferenceStorageNull = editFameActivity.this.getSharedPreferences("STORAGEPref", MODE_PRIVATE);
        SharedPreferences.Editor myEdit3 = sharedPreferenceStorageNull.edit();
        myEdit3.putString("storage", "");
        myEdit3.apply();

        binding.dummyButonForAddFont.performClick();

        binding.edttextFragRel1.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.edttextFragRel2.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.edttextFragRel3.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.edttextFragRel4.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.edttextFragRel5.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.edttextFragRel6.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.edttextFragRel7.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.edttextFragRel8.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.edttextFragRel9.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.edttextFragRel10.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork);
        binding.shapeRel1.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.shapeRel2.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.shapeRel3.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.shapeRel4.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.shapeRel5.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.shapeRel6.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.shapeRel7.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.shapeRel8.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.shapeRel9.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.shapeRel10.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.storageRel1.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.storageRel2.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.storageRel3.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.storageRel4.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.storageRel5.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.storageRel6.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.storageRel7.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.storageRel8.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.storageRel9.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.storageRel10.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);

        binding.editTextOnly.animate().alpha(1.0f);
        binding.editTextOnly.setVisibility(View.VISIBLE);
    }


    //add shape auto select

    public void highlightShape1() {
        sharedPreferences = editFameActivity.this.getSharedPreferences("SHAPEPref", MODE_PRIVATE);
        SharedPreferences.Editor myEdit = sharedPreferences.edit();
        myEdit.putString("shape", "SELECTED_SHAPE1");
        myEdit.apply();

        sharedPreferencesForTextNull = getSharedPreferences("TEXTPref", MODE_PRIVATE);
        SharedPreferences.Editor myEdit2 = sharedPreferencesForTextNull.edit();
        myEdit2.putString("text", "");
        myEdit2.apply();

        sharedPreferenceStorageNull = editFameActivity.this.getSharedPreferences("STORAGEPref", MODE_PRIVATE);
        SharedPreferences.Editor myEdit3 = sharedPreferenceStorageNull.edit();
        myEdit3.putString("storage", "");
        myEdit3.apply();

        binding.edttextFragRel1.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.edttextFragRel2.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.edttextFragRel3.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.edttextFragRel4.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.edttextFragRel5.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.edttextFragRel6.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.edttextFragRel7.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.edttextFragRel8.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.edttextFragRel9.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.edttextFragRel10.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.shapeRel1.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork);
        binding.shapeRel2.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.shapeRel3.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.shapeRel4.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.shapeRel5.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.shapeRel6.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.shapeRel7.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.shapeRel8.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.shapeRel9.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.shapeRel10.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.storageRel1.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.storageRel2.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.storageRel3.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.storageRel4.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.storageRel5.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.storageRel6.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.storageRel7.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.storageRel8.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.storageRel9.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.storageRel10.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);


        binding.editTextOnly.animate().alpha(0.0f);
        binding.editTextOnly.setVisibility(View.INVISIBLE);
    }

    public void highlightShape2() {
        sharedPreferences = editFameActivity.this.getSharedPreferences("SHAPEPref", MODE_PRIVATE);
        SharedPreferences.Editor myEdit = sharedPreferences.edit();
        myEdit.putString("shape", "SELECTED_SHAPE2");
        myEdit.apply();

        sharedPreferencesForTextNull = getSharedPreferences("TEXTPref", MODE_PRIVATE);
        SharedPreferences.Editor myEdit2 = sharedPreferencesForTextNull.edit();
        myEdit2.putString("text", "");
        myEdit2.apply();

        sharedPreferenceStorageNull = editFameActivity.this.getSharedPreferences("STORAGEPref", MODE_PRIVATE);
        SharedPreferences.Editor myEdit3 = sharedPreferenceStorageNull.edit();
        myEdit3.putString("storage", "");
        myEdit3.apply();


        binding.edttextFragRel1.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.edttextFragRel2.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.edttextFragRel3.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.edttextFragRel4.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.edttextFragRel5.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.edttextFragRel6.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.edttextFragRel7.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.edttextFragRel8.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.edttextFragRel9.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.edttextFragRel10.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.shapeRel1.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.shapeRel2.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork);
        binding.shapeRel3.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.shapeRel4.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.shapeRel5.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.shapeRel6.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.shapeRel7.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.shapeRel8.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.shapeRel9.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.shapeRel10.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.storageRel1.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.storageRel2.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.storageRel3.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.storageRel4.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.storageRel5.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.storageRel6.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.storageRel7.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.storageRel8.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.storageRel9.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.storageRel10.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);


        binding.editTextOnly.animate().alpha(0.0f);
        binding.editTextOnly.setVisibility(View.INVISIBLE);
    }

    public void highlightShape3() {
        sharedPreferences = editFameActivity.this.getSharedPreferences("SHAPEPref", MODE_PRIVATE);
        SharedPreferences.Editor myEdit = sharedPreferences.edit();
        myEdit.putString("shape", "SELECTED_SHAPE3");
        myEdit.apply();

        sharedPreferencesForTextNull = getSharedPreferences("TEXTPref", MODE_PRIVATE);
        SharedPreferences.Editor myEdit2 = sharedPreferencesForTextNull.edit();
        myEdit2.putString("text", "");
        myEdit2.apply();

        sharedPreferenceStorageNull = editFameActivity.this.getSharedPreferences("STORAGEPref", MODE_PRIVATE);
        SharedPreferences.Editor myEdit3 = sharedPreferenceStorageNull.edit();
        myEdit3.putString("storage", "");
        myEdit3.apply();

        binding.edttextFragRel1.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.edttextFragRel2.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.edttextFragRel3.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.edttextFragRel4.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.edttextFragRel5.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.edttextFragRel6.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.edttextFragRel7.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.edttextFragRel8.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.edttextFragRel9.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.edttextFragRel10.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.shapeRel1.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.shapeRel2.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.shapeRel3.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork);
        binding.shapeRel4.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.shapeRel5.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.shapeRel6.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.shapeRel7.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.shapeRel8.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.shapeRel9.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.shapeRel10.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.storageRel1.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.storageRel2.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.storageRel3.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.storageRel4.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.storageRel5.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.storageRel6.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.storageRel7.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.storageRel8.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.storageRel9.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.storageRel10.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);


        binding.editTextOnly.animate().alpha(0.0f);
        binding.editTextOnly.setVisibility(View.INVISIBLE);
    }

    public void highlightShape4() {
        sharedPreferences = editFameActivity.this.getSharedPreferences("SHAPEPref", MODE_PRIVATE);
        SharedPreferences.Editor myEdit = sharedPreferences.edit();
        myEdit.putString("shape", "SELECTED_SHAPE4");
        myEdit.apply();

        sharedPreferencesForTextNull = getSharedPreferences("TEXTPref", MODE_PRIVATE);
        SharedPreferences.Editor myEdit2 = sharedPreferencesForTextNull.edit();
        myEdit2.putString("text", "");
        myEdit2.apply();

        sharedPreferenceStorageNull = editFameActivity.this.getSharedPreferences("STORAGEPref", MODE_PRIVATE);
        SharedPreferences.Editor myEdit3 = sharedPreferenceStorageNull.edit();
        myEdit3.putString("storage", "");
        myEdit3.apply();


        binding.edttextFragRel1.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.edttextFragRel2.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.edttextFragRel3.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.edttextFragRel4.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.edttextFragRel5.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.edttextFragRel6.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.edttextFragRel7.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.edttextFragRel8.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.edttextFragRel9.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.edttextFragRel10.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.shapeRel1.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.shapeRel2.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.shapeRel3.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.shapeRel4.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork);
        binding.shapeRel5.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.shapeRel6.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.shapeRel7.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.shapeRel8.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.shapeRel9.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.shapeRel10.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.storageRel1.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.storageRel2.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.storageRel3.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.storageRel4.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.storageRel5.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.storageRel6.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.storageRel7.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.storageRel8.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.storageRel9.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.storageRel10.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);


        binding.editTextOnly.animate().alpha(0.0f);
        binding.editTextOnly.setVisibility(View.INVISIBLE);
    }

    public void highlightShape5() {
        sharedPreferences = editFameActivity.this.getSharedPreferences("SHAPEPref", MODE_PRIVATE);
        SharedPreferences.Editor myEdit = sharedPreferences.edit();
        myEdit.putString("shape", "SELECTED_SHAPE5");
        myEdit.apply();

        sharedPreferencesForTextNull = getSharedPreferences("TEXTPref", MODE_PRIVATE);
        SharedPreferences.Editor myEdit2 = sharedPreferencesForTextNull.edit();
        myEdit2.putString("text", "");
        myEdit2.apply();

        sharedPreferenceStorageNull = editFameActivity.this.getSharedPreferences("STORAGEPref", MODE_PRIVATE);
        SharedPreferences.Editor myEdit3 = sharedPreferenceStorageNull.edit();
        myEdit3.putString("storage", "");
        myEdit3.apply();
        binding.edttextFragRel1.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.edttextFragRel2.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.edttextFragRel3.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.edttextFragRel4.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.edttextFragRel5.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.edttextFragRel6.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.edttextFragRel7.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.edttextFragRel8.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.edttextFragRel9.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.edttextFragRel10.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.shapeRel1.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.shapeRel2.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.shapeRel3.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.shapeRel4.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.shapeRel5.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork);
        binding.shapeRel6.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.shapeRel7.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.shapeRel8.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.shapeRel9.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.shapeRel10.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.storageRel1.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.storageRel2.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.storageRel3.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.storageRel4.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.storageRel5.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.storageRel6.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.storageRel7.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.storageRel8.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.storageRel9.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.storageRel10.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);


        binding.editTextOnly.animate().alpha(0.0f);
        binding.editTextOnly.setVisibility(View.INVISIBLE);
    }

    public void highlightShape6() {
        sharedPreferences = editFameActivity.this.getSharedPreferences("SHAPEPref", MODE_PRIVATE);
        SharedPreferences.Editor myEdit = sharedPreferences.edit();
        myEdit.putString("shape", "SELECTED_SHAPE6");
        myEdit.apply();

        sharedPreferencesForTextNull = getSharedPreferences("TEXTPref", MODE_PRIVATE);
        SharedPreferences.Editor myEdit2 = sharedPreferencesForTextNull.edit();
        myEdit2.putString("text", "");
        myEdit2.apply();

        sharedPreferenceStorageNull = editFameActivity.this.getSharedPreferences("STORAGEPref", MODE_PRIVATE);
        SharedPreferences.Editor myEdit3 = sharedPreferenceStorageNull.edit();
        myEdit3.putString("storage", "");
        myEdit3.apply();

        binding.edttextFragRel1.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.edttextFragRel2.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.edttextFragRel3.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.edttextFragRel4.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.edttextFragRel5.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.edttextFragRel6.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.edttextFragRel7.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.edttextFragRel8.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.edttextFragRel9.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.edttextFragRel10.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.shapeRel1.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.shapeRel2.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.shapeRel3.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.shapeRel4.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.shapeRel5.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.shapeRel6.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork);
        binding.shapeRel7.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.shapeRel8.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.shapeRel9.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.shapeRel10.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.storageRel1.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.storageRel2.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.storageRel3.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.storageRel4.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.storageRel5.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.storageRel6.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.storageRel7.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.storageRel8.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.storageRel9.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.storageRel10.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);

        binding.editTextOnly.animate().alpha(0.0f);
        binding.editTextOnly.setVisibility(View.INVISIBLE);
    }

    public void highlightShape7() {
        sharedPreferences = editFameActivity.this.getSharedPreferences("SHAPEPref", MODE_PRIVATE);
        SharedPreferences.Editor myEdit = sharedPreferences.edit();
        myEdit.putString("shape", "SELECTED_SHAPE7");
        myEdit.apply();

        sharedPreferencesForTextNull = getSharedPreferences("TEXTPref", MODE_PRIVATE);
        SharedPreferences.Editor myEdit2 = sharedPreferencesForTextNull.edit();
        myEdit2.putString("text", "");
        myEdit2.apply();

        sharedPreferenceStorageNull = editFameActivity.this.getSharedPreferences("STORAGEPref", MODE_PRIVATE);
        SharedPreferences.Editor myEdit3 = sharedPreferenceStorageNull.edit();
        myEdit3.putString("storage", "");
        myEdit3.apply();


        binding.edttextFragRel1.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.edttextFragRel2.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.edttextFragRel3.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.edttextFragRel4.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.edttextFragRel5.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.edttextFragRel6.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.edttextFragRel7.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.edttextFragRel8.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.edttextFragRel9.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.edttextFragRel10.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.shapeRel1.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.shapeRel2.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.shapeRel3.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.shapeRel4.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.shapeRel5.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.shapeRel6.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.shapeRel7.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork);
        binding.shapeRel8.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.shapeRel9.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.shapeRel10.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.storageRel1.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.storageRel2.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.storageRel3.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.storageRel4.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.storageRel5.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.storageRel6.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.storageRel7.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.storageRel8.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.storageRel9.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.storageRel10.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);


        binding.editTextOnly.animate().alpha(0.0f);
        binding.editTextOnly.setVisibility(View.INVISIBLE);
    }

    public void highlightShape8() {
        sharedPreferences = editFameActivity.this.getSharedPreferences("SHAPEPref", MODE_PRIVATE);
        SharedPreferences.Editor myEdit = sharedPreferences.edit();
        myEdit.putString("shape", "SELECTED_SHAPE8");
        myEdit.apply();

        sharedPreferencesForTextNull = getSharedPreferences("TEXTPref", MODE_PRIVATE);
        SharedPreferences.Editor myEdit2 = sharedPreferencesForTextNull.edit();
        myEdit2.putString("text", "");
        myEdit2.apply();

        sharedPreferenceStorageNull = editFameActivity.this.getSharedPreferences("STORAGEPref", MODE_PRIVATE);
        SharedPreferences.Editor myEdit3 = sharedPreferenceStorageNull.edit();
        myEdit3.putString("storage", "");
        myEdit3.apply();

        binding.edttextFragRel1.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.edttextFragRel2.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.edttextFragRel3.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.edttextFragRel4.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.edttextFragRel5.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.edttextFragRel6.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.edttextFragRel7.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.edttextFragRel8.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.edttextFragRel9.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.edttextFragRel10.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.shapeRel1.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.shapeRel2.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.shapeRel3.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.shapeRel4.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.shapeRel5.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.shapeRel6.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.shapeRel7.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.shapeRel8.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork);
        binding.shapeRel9.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.shapeRel10.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.storageRel1.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.storageRel2.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.storageRel3.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.storageRel4.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.storageRel5.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.storageRel6.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.storageRel7.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.storageRel8.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.storageRel9.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.storageRel10.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);


        binding.editTextOnly.animate().alpha(0.0f);
        binding.editTextOnly.setVisibility(View.INVISIBLE);
    }

    public void highlightShape9() {
        sharedPreferences = editFameActivity.this.getSharedPreferences("SHAPEPref", MODE_PRIVATE);
        SharedPreferences.Editor myEdit = sharedPreferences.edit();
        myEdit.putString("shape", "SELECTED_SHAPE9");
        myEdit.apply();

        sharedPreferencesForTextNull = getSharedPreferences("TEXTPref", MODE_PRIVATE);
        SharedPreferences.Editor myEdit2 = sharedPreferencesForTextNull.edit();
        myEdit2.putString("text", "");
        myEdit2.apply();

        sharedPreferenceStorageNull = editFameActivity.this.getSharedPreferences("STORAGEPref", MODE_PRIVATE);
        SharedPreferences.Editor myEdit3 = sharedPreferenceStorageNull.edit();
        myEdit3.putString("storage", "");
        myEdit3.apply();

        binding.edttextFragRel1.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.edttextFragRel2.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.edttextFragRel3.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.edttextFragRel4.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.edttextFragRel5.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.edttextFragRel6.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.edttextFragRel7.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.edttextFragRel8.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.edttextFragRel9.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.edttextFragRel10.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.shapeRel1.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.shapeRel2.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.shapeRel3.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.shapeRel4.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.shapeRel5.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.shapeRel6.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.shapeRel7.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.shapeRel8.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.shapeRel9.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork);
        binding.shapeRel10.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.storageRel1.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.storageRel2.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.storageRel3.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.storageRel4.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.storageRel5.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.storageRel6.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.storageRel7.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.storageRel8.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.storageRel9.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.storageRel10.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);


        binding.editTextOnly.animate().alpha(0.0f);
        binding.editTextOnly.setVisibility(View.INVISIBLE);
    }

    public void highlightShape10() {
        sharedPreferences = editFameActivity.this.getSharedPreferences("SHAPEPref", MODE_PRIVATE);
        SharedPreferences.Editor myEdit = sharedPreferences.edit();
        myEdit.putString("shape", "SELECTED_SHAPE10");
        myEdit.apply();

        sharedPreferencesForTextNull = getSharedPreferences("TEXTPref", MODE_PRIVATE);
        SharedPreferences.Editor myEdit2 = sharedPreferencesForTextNull.edit();
        myEdit2.putString("text", "");
        myEdit2.apply();

        sharedPreferenceStorageNull = editFameActivity.this.getSharedPreferences("STORAGEPref", MODE_PRIVATE);
        SharedPreferences.Editor myEdit3 = sharedPreferenceStorageNull.edit();
        myEdit3.putString("storage", "");
        myEdit3.apply();

        binding.edttextFragRel1.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.edttextFragRel2.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.edttextFragRel3.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.edttextFragRel4.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.edttextFragRel5.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.edttextFragRel6.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.edttextFragRel7.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.edttextFragRel8.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.edttextFragRel9.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.edttextFragRel10.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.shapeRel1.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.shapeRel2.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.shapeRel3.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.shapeRel4.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.shapeRel5.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.shapeRel6.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.shapeRel7.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.shapeRel8.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.shapeRel9.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.shapeRel10.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork);
        binding.storageRel1.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.storageRel2.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.storageRel3.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.storageRel4.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.storageRel5.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.storageRel6.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.storageRel7.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.storageRel8.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.storageRel9.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.storageRel10.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);


        binding.editTextOnly.animate().alpha(0.0f);
        binding.editTextOnly.setVisibility(View.INVISIBLE);
    }


    public void highlightLogo1() {
        sharedPreferences = editFameActivity.this.getSharedPreferences("STORAGEPref", MODE_PRIVATE);
        SharedPreferences.Editor myEdit = sharedPreferences.edit();
        myEdit.putString("storage", "SELECTED_STORAGE1");
        myEdit.apply();

        sharedPreferences = editFameActivity.this.getSharedPreferences("SHAPEPref", MODE_PRIVATE);
        SharedPreferences.Editor myEdit2 = sharedPreferences.edit();
        myEdit2.putString("shape", "");
        myEdit2.apply();

        sharedPreferencesForTextNull = getSharedPreferences("TEXTPref", MODE_PRIVATE);
        SharedPreferences.Editor myEdit3 = sharedPreferencesForTextNull.edit();
        myEdit3.putString("text", "");
        myEdit3.apply();

        binding.edttextFragRel1.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.edttextFragRel2.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.edttextFragRel3.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.edttextFragRel4.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.edttextFragRel5.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.edttextFragRel6.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.edttextFragRel7.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.edttextFragRel8.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.edttextFragRel9.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.edttextFragRel10.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.shapeRel1.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.shapeRel2.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.shapeRel3.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.shapeRel4.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.shapeRel5.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.shapeRel6.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.shapeRel7.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.shapeRel8.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.shapeRel9.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.shapeRel10.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.storageRel1.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork);
        binding.storageRel2.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.storageRel3.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.storageRel4.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.storageRel5.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.storageRel6.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.storageRel7.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.storageRel8.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.storageRel9.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.storageRel10.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);


        binding.editTextOnly.animate().alpha(0.0f);
        binding.editTextOnly.setVisibility(View.INVISIBLE);
    }

    public void highlightLogo2() {
        sharedPreferences = editFameActivity.this.getSharedPreferences("STORAGEPref", MODE_PRIVATE);
        SharedPreferences.Editor myEdit = sharedPreferences.edit();
        myEdit.putString("storage", "SELECTED_STORAGE2");
        myEdit.apply();

        sharedPreferences = editFameActivity.this.getSharedPreferences("SHAPEPref", MODE_PRIVATE);
        SharedPreferences.Editor myEdit2 = sharedPreferences.edit();
        myEdit2.putString("shape", "");
        myEdit2.apply();

        sharedPreferencesForTextNull = getSharedPreferences("TEXTPref", MODE_PRIVATE);
        SharedPreferences.Editor myEdit3 = sharedPreferencesForTextNull.edit();
        myEdit3.putString("text", "");
        myEdit3.apply();

        binding.edttextFragRel1.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.edttextFragRel2.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.edttextFragRel3.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.edttextFragRel4.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.edttextFragRel5.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.edttextFragRel6.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.edttextFragRel7.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.edttextFragRel8.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.edttextFragRel9.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.edttextFragRel10.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.shapeRel1.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.shapeRel2.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.shapeRel3.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.shapeRel4.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.shapeRel5.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.shapeRel6.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.shapeRel7.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.shapeRel8.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.shapeRel9.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.shapeRel10.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.storageRel1.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.storageRel2.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork);
        binding.storageRel3.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.storageRel4.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.storageRel5.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.storageRel6.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.storageRel7.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.storageRel8.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.storageRel9.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.storageRel10.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);


        binding.editTextOnly.animate().alpha(0.0f);
        binding.editTextOnly.setVisibility(View.INVISIBLE);
    }

    public void highlightLogo3() {
        sharedPreferences = editFameActivity.this.getSharedPreferences("STORAGEPref", MODE_PRIVATE);
        SharedPreferences.Editor myEdit = sharedPreferences.edit();
        myEdit.putString("storage", "SELECTED_STORAGE3");
        myEdit.apply();

        sharedPreferences = editFameActivity.this.getSharedPreferences("SHAPEPref", MODE_PRIVATE);
        SharedPreferences.Editor myEdit2 = sharedPreferences.edit();
        myEdit2.putString("shape", "");
        myEdit2.apply();

        sharedPreferencesForTextNull = getSharedPreferences("TEXTPref", MODE_PRIVATE);
        SharedPreferences.Editor myEdit3 = sharedPreferencesForTextNull.edit();
        myEdit3.putString("text", "");
        myEdit3.apply();

        binding.edttextFragRel1.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.edttextFragRel2.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.edttextFragRel3.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.edttextFragRel4.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.edttextFragRel5.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.edttextFragRel6.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.edttextFragRel7.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.edttextFragRel8.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.edttextFragRel9.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.edttextFragRel10.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.shapeRel1.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.shapeRel2.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.shapeRel3.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.shapeRel4.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.shapeRel5.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.shapeRel6.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.shapeRel7.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.shapeRel8.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.shapeRel9.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.shapeRel10.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.storageRel1.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.storageRel2.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.storageRel3.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork);
        binding.storageRel4.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.storageRel5.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.storageRel6.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.storageRel7.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.storageRel8.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.storageRel9.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.storageRel10.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);


        binding.editTextOnly.animate().alpha(0.0f);
        binding.editTextOnly.setVisibility(View.INVISIBLE);
    }

    public void highlightLogo4() {
        sharedPreferences = editFameActivity.this.getSharedPreferences("STORAGEPref", MODE_PRIVATE);
        SharedPreferences.Editor myEdit = sharedPreferences.edit();
        myEdit.putString("storage", "SELECTED_STORAGE4");
        myEdit.apply();

        sharedPreferences = editFameActivity.this.getSharedPreferences("SHAPEPref", MODE_PRIVATE);
        SharedPreferences.Editor myEdit2 = sharedPreferences.edit();
        myEdit2.putString("shape", "");
        myEdit2.apply();

        sharedPreferencesForTextNull = getSharedPreferences("TEXTPref", MODE_PRIVATE);
        SharedPreferences.Editor myEdit3 = sharedPreferencesForTextNull.edit();
        myEdit3.putString("text", "");
        myEdit3.apply();

        binding.edttextFragRel1.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.edttextFragRel2.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.edttextFragRel3.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.edttextFragRel4.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.edttextFragRel5.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.edttextFragRel6.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.edttextFragRel7.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.edttextFragRel8.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.edttextFragRel9.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.edttextFragRel10.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.shapeRel1.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.shapeRel2.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.shapeRel3.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.shapeRel4.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.shapeRel5.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.shapeRel6.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.shapeRel7.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.shapeRel8.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.shapeRel9.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.shapeRel10.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.storageRel1.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.storageRel2.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.storageRel3.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.storageRel4.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork);
        binding.storageRel5.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.storageRel6.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.storageRel7.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.storageRel8.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.storageRel9.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.storageRel10.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);


        binding.editTextOnly.animate().alpha(0.0f);
        binding.editTextOnly.setVisibility(View.INVISIBLE);
    }

    public void highlightLogo5() {
        sharedPreferences = editFameActivity.this.getSharedPreferences("STORAGEPref", MODE_PRIVATE);
        SharedPreferences.Editor myEdit = sharedPreferences.edit();
        myEdit.putString("storage", "SELECTED_STORAGE5");
        myEdit.apply();

        sharedPreferences = editFameActivity.this.getSharedPreferences("SHAPEPref", MODE_PRIVATE);
        SharedPreferences.Editor myEdit2 = sharedPreferences.edit();
        myEdit2.putString("shape", "");
        myEdit2.apply();

        sharedPreferencesForTextNull = getSharedPreferences("TEXTPref", MODE_PRIVATE);
        SharedPreferences.Editor myEdit3 = sharedPreferencesForTextNull.edit();
        myEdit3.putString("text", "");
        myEdit3.apply();

        binding.edttextFragRel1.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.edttextFragRel2.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.edttextFragRel3.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.edttextFragRel4.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.edttextFragRel5.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.edttextFragRel6.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.edttextFragRel7.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.edttextFragRel8.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.edttextFragRel9.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.edttextFragRel10.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.shapeRel1.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.shapeRel2.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.shapeRel3.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.shapeRel4.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.shapeRel5.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.shapeRel6.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.shapeRel7.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.shapeRel8.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.shapeRel9.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.shapeRel10.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.storageRel1.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.storageRel2.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.storageRel3.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.storageRel4.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.storageRel5.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork);
        binding.storageRel6.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.storageRel7.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.storageRel8.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.storageRel9.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.storageRel10.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);


        binding.editTextOnly.animate().alpha(0.0f);
        binding.editTextOnly.setVisibility(View.INVISIBLE);
    }

    public void highlightLogo6() {
        sharedPreferences = editFameActivity.this.getSharedPreferences("STORAGEPref", MODE_PRIVATE);
        SharedPreferences.Editor myEdit = sharedPreferences.edit();
        myEdit.putString("storage", "SELECTED_STORAGE6");
        myEdit.apply();

        sharedPreferences = editFameActivity.this.getSharedPreferences("SHAPEPref", MODE_PRIVATE);
        SharedPreferences.Editor myEdit2 = sharedPreferences.edit();
        myEdit2.putString("shape", "");
        myEdit2.apply();

        sharedPreferencesForTextNull = getSharedPreferences("TEXTPref", MODE_PRIVATE);
        SharedPreferences.Editor myEdit3 = sharedPreferencesForTextNull.edit();
        myEdit3.putString("text", "");
        myEdit3.apply();

        binding.edttextFragRel1.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.edttextFragRel2.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.edttextFragRel3.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.edttextFragRel4.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.edttextFragRel5.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.edttextFragRel6.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.edttextFragRel7.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.edttextFragRel8.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.edttextFragRel9.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.edttextFragRel10.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.shapeRel1.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.shapeRel2.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.shapeRel3.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.shapeRel4.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.shapeRel5.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.shapeRel6.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.shapeRel7.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.shapeRel8.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.shapeRel9.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.shapeRel10.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.storageRel1.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.storageRel2.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.storageRel3.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.storageRel4.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.storageRel5.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.storageRel6.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork);
        binding.storageRel7.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.storageRel8.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.storageRel9.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.storageRel10.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);


        binding.editTextOnly.animate().alpha(0.0f);
        binding.editTextOnly.setVisibility(View.INVISIBLE);
    }

    public void highlightLogo7() {
        sharedPreferences = editFameActivity.this.getSharedPreferences("STORAGEPref", MODE_PRIVATE);
        SharedPreferences.Editor myEdit = sharedPreferences.edit();
        myEdit.putString("storage", "SELECTED_STORAGE7");
        myEdit.apply();

        sharedPreferences = editFameActivity.this.getSharedPreferences("SHAPEPref", MODE_PRIVATE);
        SharedPreferences.Editor myEdit2 = sharedPreferences.edit();
        myEdit2.putString("shape", "");
        myEdit2.apply();

        sharedPreferencesForTextNull = getSharedPreferences("TEXTPref", MODE_PRIVATE);
        SharedPreferences.Editor myEdit3 = sharedPreferencesForTextNull.edit();
        myEdit3.putString("text", "");
        myEdit3.apply();

        binding.edttextFragRel1.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.edttextFragRel2.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.edttextFragRel3.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.edttextFragRel4.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.edttextFragRel5.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.edttextFragRel6.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.edttextFragRel7.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.edttextFragRel8.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.edttextFragRel9.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.edttextFragRel10.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.shapeRel1.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.shapeRel2.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.shapeRel3.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.shapeRel4.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.shapeRel5.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.shapeRel6.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.shapeRel7.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.shapeRel8.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.shapeRel9.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.shapeRel10.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.storageRel1.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.storageRel2.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.storageRel3.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.storageRel4.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.storageRel5.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.storageRel6.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.storageRel7.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork);
        binding.storageRel8.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.storageRel9.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.storageRel10.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);


        binding.editTextOnly.animate().alpha(0.0f);
        binding.editTextOnly.setVisibility(View.INVISIBLE);
    }

    public void highlightLogo8() {
        sharedPreferences = editFameActivity.this.getSharedPreferences("STORAGEPref", MODE_PRIVATE);
        SharedPreferences.Editor myEdit = sharedPreferences.edit();
        myEdit.putString("storage", "SELECTED_STORAGE8");
        myEdit.apply();

        sharedPreferences = editFameActivity.this.getSharedPreferences("SHAPEPref", MODE_PRIVATE);
        SharedPreferences.Editor myEdit2 = sharedPreferences.edit();
        myEdit2.putString("shape", "");
        myEdit2.apply();

        sharedPreferencesForTextNull = getSharedPreferences("TEXTPref", MODE_PRIVATE);
        SharedPreferences.Editor myEdit3 = sharedPreferencesForTextNull.edit();
        myEdit3.putString("text", "");
        myEdit3.apply();

        binding.edttextFragRel1.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.edttextFragRel2.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.edttextFragRel3.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.edttextFragRel4.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.edttextFragRel5.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.edttextFragRel6.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.edttextFragRel7.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.edttextFragRel8.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.edttextFragRel9.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.edttextFragRel10.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.shapeRel1.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.shapeRel2.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.shapeRel3.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.shapeRel4.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.shapeRel5.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.shapeRel6.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.shapeRel7.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.shapeRel8.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.shapeRel9.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.shapeRel10.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.storageRel1.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.storageRel2.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.storageRel3.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.storageRel4.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.storageRel5.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.storageRel6.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.storageRel7.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.storageRel8.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork);
        binding.storageRel9.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.storageRel10.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);


        binding.editTextOnly.animate().alpha(0.0f);
        binding.editTextOnly.setVisibility(View.INVISIBLE);
    }

    public void highlightLogo9() {
        sharedPreferences = editFameActivity.this.getSharedPreferences("STORAGEPref", MODE_PRIVATE);
        SharedPreferences.Editor myEdit = sharedPreferences.edit();
        myEdit.putString("storage", "SELECTED_STORAGE9");
        myEdit.apply();

        sharedPreferences = editFameActivity.this.getSharedPreferences("SHAPEPref", MODE_PRIVATE);
        SharedPreferences.Editor myEdit2 = sharedPreferences.edit();
        myEdit2.putString("shape", "");
        myEdit2.apply();

        sharedPreferencesForTextNull = getSharedPreferences("TEXTPref", MODE_PRIVATE);
        SharedPreferences.Editor myEdit3 = sharedPreferencesForTextNull.edit();
        myEdit3.putString("text", "");
        myEdit3.apply();

        binding.edttextFragRel1.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.edttextFragRel2.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.edttextFragRel3.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.edttextFragRel4.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.edttextFragRel5.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.edttextFragRel6.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.edttextFragRel7.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.edttextFragRel8.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.edttextFragRel9.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.edttextFragRel10.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.shapeRel1.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.shapeRel2.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.shapeRel3.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.shapeRel4.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.shapeRel5.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.shapeRel6.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.shapeRel7.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.shapeRel8.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.shapeRel9.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.shapeRel10.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.storageRel1.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.storageRel2.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.storageRel3.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.storageRel4.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.storageRel5.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.storageRel6.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.storageRel7.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.storageRel8.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.storageRel9.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork);
        binding.storageRel10.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);


        binding.editTextOnly.animate().alpha(0.0f);
        binding.editTextOnly.setVisibility(View.INVISIBLE);
    }

    public void highlightLogo10() {
        sharedPreferences = editFameActivity.this.getSharedPreferences("STORAGEPref", MODE_PRIVATE);
        SharedPreferences.Editor myEdit = sharedPreferences.edit();
        myEdit.putString("storage", "SELECTED_STORAGE10");
        myEdit.apply();

        sharedPreferences = editFameActivity.this.getSharedPreferences("SHAPEPref", MODE_PRIVATE);
        SharedPreferences.Editor myEdit2 = sharedPreferences.edit();
        myEdit2.putString("shape", "");
        myEdit2.apply();

        sharedPreferencesForTextNull = getSharedPreferences("TEXTPref", MODE_PRIVATE);
        SharedPreferences.Editor myEdit3 = sharedPreferencesForTextNull.edit();
        myEdit3.putString("text", "");
        myEdit3.apply();

        binding.edttextFragRel1.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.edttextFragRel2.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.edttextFragRel3.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.edttextFragRel4.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.edttextFragRel5.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.edttextFragRel6.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.edttextFragRel7.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.edttextFragRel8.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.edttextFragRel9.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.edttextFragRel10.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.shapeRel1.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.shapeRel2.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.shapeRel3.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.shapeRel4.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.shapeRel5.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.shapeRel6.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.shapeRel7.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.shapeRel8.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.shapeRel9.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.shapeRel10.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.storageRel1.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.storageRel2.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.storageRel3.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.storageRel4.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.storageRel5.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.storageRel6.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.storageRel7.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.storageRel8.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.storageRel9.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork_transaprent);
        binding.storageRel10.setBackgroundResource(R.drawable.highlight_dotted_for_edit_framwork);


        binding.editTextOnly.animate().alpha(0.0f);
        binding.editTextOnly.setVisibility(View.INVISIBLE);
    }

    public void fontModelData() {


        newFontModel = new fontModelNew[]{
                new fontModelNew(getCurrentTextForfont, "fonts/poppins_medium.ttf"),
                new fontModelNew(getCurrentTextForfont, "fonts/poor_richard.ttf"),
                new fontModelNew(getCurrentTextForfont, "fonts/instagram.ttf"),
                new fontModelNew(getCurrentTextForfont, "fonts/port_lligat_sans.ttf"),
                new fontModelNew(getCurrentTextForfont, "fonts/playfail_display.ttf"),
                new fontModelNew(getCurrentTextForfont, "fonts/pirata_one.ttf"),
                new fontModelNew(getCurrentTextForfont, "fonts/pattaya.ttf"),
                new fontModelNew(getCurrentTextForfont, "fonts/porter_sans_block.ttf"),
                new fontModelNew(getCurrentTextForfont, "fonts/postnobillscolombo_medium.ttf"),
                new fontModelNew(getCurrentTextForfont, "fonts/potta_one.ttf"),
                new fontModelNew(getCurrentTextForfont, "fonts/praiseregular.ttf"),
                new fontModelNew(getCurrentTextForfont, "fonts/quintessential_regular.ttf"),
                new fontModelNew(getCurrentTextForfont, "fonts/qahiri_regular.ttf"),
                new fontModelNew(getCurrentTextForfont, "fonts/rancho.ttf"),
                new fontModelNew(getCurrentTextForfont, "fonts/redressed.ttf"),
                new fontModelNew(getCurrentTextForfont, "fonts/rufina.ttf"),
                new fontModelNew(getCurrentTextForfont, "fonts/alegreya.ttf"),
                new fontModelNew(getCurrentTextForfont, "fonts/balloo.ttf"),
                new fontModelNew(getCurrentTextForfont, "fonts/bebas.ttf"),
                new fontModelNew(getCurrentTextForfont, "fonts/coda.ttf"),
                new fontModelNew(getCurrentTextForfont, "fonts/dancing.ttf"),
                new fontModelNew(getCurrentTextForfont, "fonts/east.ttf"),
                new fontModelNew(getCurrentTextForfont, "fonts/julius.ttf"),
                new fontModelNew(getCurrentTextForfont, "fonts/libre.ttf"),
                new fontModelNew(getCurrentTextForfont, "fonts/limelight.ttf"),
                new fontModelNew(getCurrentTextForfont, "fonts/lobster.ttf"),
                new fontModelNew(getCurrentTextForfont, "fonts/monoton.ttf"),
                new fontModelNew(getCurrentTextForfont, "fonts/moondance.ttf"),
                new fontModelNew(getCurrentTextForfont, "fonts/niccone.ttf"),
                new fontModelNew(getCurrentTextForfont, "fonts/nobla.ttf"),
                new fontModelNew(getCurrentTextForfont, "fonts/nothing.ttf"),
                new fontModelNew(getCurrentTextForfont, "fonts/pasiion.ttf"),
                new fontModelNew(getCurrentTextForfont, "fonts/raleway.ttf"),
                new fontModelNew(getCurrentTextForfont, "fonts/rowdies.ttf"),
                new fontModelNew(getCurrentTextForfont, "fonts/rubic.ttf"),
                new fontModelNew(getCurrentTextForfont, "fonts/rubicregular.ttf"),
                new fontModelNew(getCurrentTextForfont, "fonts/rubies.ttf"),
                new fontModelNew(getCurrentTextForfont, "fonts/rye.ttf"),
                new fontModelNew(getCurrentTextForfont, "fonts/sevillana.ttf"),
                new fontModelNew(getCurrentTextForfont, "fonts/sey.ttf"),
                new fontModelNew(getCurrentTextForfont, "fonts/sixcaps.ttf"),
                new fontModelNew(getCurrentTextForfont, "fonts/tekko.ttf"),
                new fontModelNew(getCurrentTextForfont, "fonts/abril.ttf"),
                new fontModelNew(getCurrentTextForfont, "fonts/alpha.ttf"),
                new fontModelNew(getCurrentTextForfont, "fonts/bebas.ttf"),
                new fontModelNew(getCurrentTextForfont, "fonts/comfortaa.ttf"),
                new fontModelNew(getCurrentTextForfont, "fonts/dosis.ttf"),
                new fontModelNew(getCurrentTextForfont, "fonts/indieflower.ttf"),
                new fontModelNew(getCurrentTextForfont, "fonts/latoregular.ttf"),
                new fontModelNew(getCurrentTextForfont, "fonts/lora.ttf"),
                new fontModelNew(getCurrentTextForfont, "fonts/merriwheather.ttf"),
                new fontModelNew(getCurrentTextForfont, "fonts/miodakregular.ttf"),
                new fontModelNew(getCurrentTextForfont, "fonts/opensans.ttf"),
                new fontModelNew(getCurrentTextForfont, "fonts/oswald.ttf"),
                new fontModelNew(getCurrentTextForfont, "fonts/pacifio.ttf"),
                new fontModelNew(getCurrentTextForfont, "fonts/ptserief.ttf"),
                new fontModelNew(getCurrentTextForfont, "fonts/quicksand.ttf"),
                new fontModelNew(getCurrentTextForfont, "fonts/shadowlight.ttf"),
                new fontModelNew(getCurrentTextForfont, "fonts/spacemono.ttf"),
        };
    }

    private void edittextTouchFALSE() {
        binding.editTextFrag.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return false;
            }
        });
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


    private boolean checkAndRequestPermissions() {

        try {
            int camera = ContextCompat.checkSelfPermission(getApplicationContext(), android.Manifest.permission.CAMERA);
            int storage = ContextCompat.checkSelfPermission(getApplicationContext(), android.Manifest.permission.WRITE_EXTERNAL_STORAGE);
            int loc = ContextCompat.checkSelfPermission(getApplicationContext(), android.Manifest.permission.ACCESS_COARSE_LOCATION);
            int loc2 = ContextCompat.checkSelfPermission(getApplicationContext(), android.Manifest.permission.ACCESS_FINE_LOCATION);
            List<String> listPermissionsNeeded = new ArrayList<>();

            if (camera != PackageManager.PERMISSION_GRANTED) {
                listPermissionsNeeded.add(android.Manifest.permission.CAMERA);
            }
            if (storage != PackageManager.PERMISSION_GRANTED) {
                listPermissionsNeeded.add(android.Manifest.permission.WRITE_EXTERNAL_STORAGE);
            }
            if (!listPermissionsNeeded.isEmpty()) {
                ActivityCompat.requestPermissions(editFameActivity.this, listPermissionsNeeded.toArray(new String[listPermissionsNeeded.size()]), REQUEST_ID_MULTIPLE_PERMISSIONS);
                return false;
            }
        } catch (Exception ex) {
            Log.d("permission", ex.getMessage());
        }
        return true;
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == MY_CAMERA_PERMISSION_CODE) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(getApplicationContext(), "camera permission granted", Toast.LENGTH_LONG).show();
                Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(cameraIntent, CAMERA_REQUEST);
            } else {
                Toast.makeText(getApplicationContext(), "camera permission denied", Toast.LENGTH_LONG).show();
            }
        }
    }

    public String getPathFromUri(Uri uri, Activity activity) throws Exception {
        String[] projection = {MediaStore.MediaColumns.DATA};
        Cursor cursor = activity.managedQuery(uri, projection, null, null, null);
        int column_index = cursor.getColumnIndexOrThrow(MediaStore.MediaColumns.DATA);
        cursor.moveToFirst();
        return cursor.getString(column_index);
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 0) {


            try {
                Uri _uri;
                if (data != null) {
                    Uri imageUri = data.getData();
                    _uri = imageUri;
                    file_camera_final1 = new File(getPathFromUri(_uri, editFameActivity.this));
                    //  iv_profile.setImageURI(_uri);
                    dialogLayout.dismiss();

                    Bitmap photo2 = MediaStore.Images.Media.getBitmap(editFameActivity.this.getContentResolver(), imageUri);

//                    Bitmap photo2 = (Bitmap) data.getExtras().get("data");g
                    ByteArrayOutputStream baos = new ByteArrayOutputStream();
                    //Bitmap scaledImage = scaleBitmap(imageBitmap , 460, 230);
                    photo2.compress(Bitmap.CompressFormat.PNG, 100, baos);
                    byte[] b = baos.toByteArray();
                    String encodedImage = Base64.encodeToString(b, Base64.DEFAULT);
                    preferenceManager.setString("gallery_data", encodedImage);
                    preferenceManager.setString("camera_data", "passed_empty_gallery");
                    getImageFromGallery();

                    //  Toast.makeText(getApplicationContext(), String.valueOf(file_camera_final1.getAbsolutePath()), Toast.LENGTH_SHORT).show();

                    // Constant.URI_IMAGE=_uri;
                    //  Constants.ProfilePhoto=imageUri;
                    //Do whatever that you desire here. or leave this blank
                }

            } catch (Exception e) {
                e.printStackTrace();
            }


        }
        if (requestCode == CAMERA_REQUEST) {
            try {

                dialogLayout.dismiss();
                Bitmap photo2 = (Bitmap) data.getExtras().get("data");
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                //     Bitmap scaledImage = scaleBitmap(photo2 , 460, 230);
                photo2.compress(Bitmap.CompressFormat.PNG, 100, baos);
                byte[] b = baos.toByteArray();
                String encodedImage = Base64.encodeToString(b, Base64.DEFAULT);
                preferenceManager.setString("camera_data", encodedImage);
                preferenceManager.setString("gallery_data", "passed_empty_camera");
                getImageFromCamera();


            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }


    private void clearSharedPref() {

        try {


            sharedPreferencesForShapeNull = getSharedPreferences("SHAPEPref", MODE_PRIVATE);
            SharedPreferences.Editor myEdit2 = sharedPreferencesForShapeNull.edit();
            myEdit2.putString("shape", "");
            myEdit2.apply();

            sharedPreferenceStorageNull = getSharedPreferences("STORAGEPref", MODE_PRIVATE);
            SharedPreferences.Editor myEdit3 = sharedPreferenceStorageNull.edit();
            myEdit3.putString("storage", "");
            myEdit3.apply();

            sharedPreferencesForTextNull = getSharedPreferences("TEXTPref", MODE_PRIVATE);
            SharedPreferences.Editor myEdit = sharedPreferencesForTextNull.edit();
            myEdit.putString("text", "");
            myEdit.apply();
        } catch (Exception ex) {
        }
    }

    public void getImageFromCamera() {

        //for gallery data to image view

        try {
            preferenceManager = PreferenceManager.getInstance(editFameActivity.this);
            previouslyEncodedCamera = preferenceManager.getString("camera_data");

            if (!previouslyEncodedCamera.equalsIgnoreCase("")) {
                byte[] b = Base64.decode(previouslyEncodedCamera, Base64.DEFAULT);
                Bitmap bitmap = BitmapFactory.decodeByteArray(b, 0, b.length);


                //real coding from  here
                try {

                    if (binding.storageTxt1.getText().toString().equals("STORAGE_1")) {

                        binding.storageImg1.setImageBitmap(bitmap);
                        binding.storageImg1.setVisibility(View.VISIBLE);
                        binding.storageTxt1.setText("");
                        highlightLogo1();

                    } else if (binding.storageTxt2.getText().toString().equals("STORAGE_2")) {

                        binding.storageImg2.setImageBitmap(bitmap);
                        binding.storageImg2.setVisibility(View.VISIBLE);
                        binding.storageTxt2.setText("");
                        highlightLogo2();

                    } else if (binding.storageTxt3.getText().toString().equals("STORAGE_3")) {

                        binding.storageImg3.setImageBitmap(bitmap);
                        binding.storageImg3.setVisibility(View.VISIBLE);
                        binding.storageTxt3.setText("");
                        highlightLogo3();

                    } else if (binding.storageTxt4.getText().toString().equals("STORAGE_4")) {

                        binding.storageImg4.setImageBitmap(bitmap);
                        binding.storageImg4.setVisibility(View.VISIBLE);
                        binding.storageTxt4.setText("");
                        highlightLogo4();

                    } else if (binding.storageTxt5.getText().toString().equals("STORAGE_5")) {

                        binding.storageImg5.setImageBitmap(bitmap);
                        binding.storageImg5.setVisibility(View.VISIBLE);
                        binding.storageTxt5.setText("");
                        highlightLogo5();

                    } else if (binding.storageTxt6.getText().toString().equals("STORAGE_6")) {

                        binding.storageImg6.setImageBitmap(bitmap);
                        binding.storageImg6.setVisibility(View.VISIBLE);
                        binding.storageTxt6.setText("");
                        highlightLogo6();

                    } else if (binding.storageTxt7.getText().toString().equals("STORAGE_7")) {

                        binding.storageImg7.setImageBitmap(bitmap);
                        binding.storageImg7.setVisibility(View.VISIBLE);
                        binding.storageTxt7.setText("");
                        highlightLogo7();

                    } else if (binding.storageTxt8.getText().toString().equals("STORAGE_8")) {

                        binding.storageImg8.setImageBitmap(bitmap);
                        binding.storageImg8.setVisibility(View.VISIBLE);
                        binding.storageTxt8.setText("");
                        highlightLogo8();

                    } else if (binding.storageTxt9.getText().toString().equals("STORAGE_9")) {

                        binding.storageImg9.setImageBitmap(bitmap);
                        binding.storageImg9.setVisibility(View.VISIBLE);
                        binding.storageTxt9.setText("");
                        highlightLogo9();

                    } else if (binding.storageTxt10.getText().toString().equals("STORAGE_10")) {

                        binding.storageImg10.setImageBitmap(bitmap);
                        binding.storageImg10.setVisibility(View.VISIBLE);
                        binding.storageTxt10.setText("");
                        highlightLogo10();

                    } else {

                        Toast.makeText(editFameActivity.this, "You can add maximum 10 Photos !", Toast.LENGTH_SHORT).show();
                    }


                } catch (Exception ex) {
                }

            }

        } catch (Exception ex) {
        }


    }

    public void getImageFromGallery() {


        try {

            //for gallery data to image view
            preferenceManager = PreferenceManager.getInstance(editFameActivity.this);
            previouslyEncodedGalley = preferenceManager.getString("gallery_data");

            if (!previouslyEncodedGalley.equalsIgnoreCase("")) {
                byte[] b = Base64.decode(previouslyEncodedGalley, Base64.DEFAULT);
                Bitmap bitmap = BitmapFactory.decodeByteArray(b, 0, b.length);

                try {

                    if (binding.storageTxt1.getText().toString().equals("STORAGE_1")) {

                        binding.storageImg1.setImageBitmap(bitmap);
                        binding.storageImg1.setVisibility(View.VISIBLE);
                        binding.storageTxt1.setText("");
                        highlightLogo1();

                    } else if (binding.storageTxt2.getText().toString().equals("STORAGE_2")) {

                        binding.storageImg2.setImageBitmap(bitmap);
                        binding.storageImg2.setVisibility(View.VISIBLE);
                        binding.storageTxt2.setText("");
                        highlightLogo2();

                    } else if (binding.storageTxt3.getText().toString().equals("STORAGE_3")) {

                        binding.storageImg3.setImageBitmap(bitmap);
                        binding.storageImg3.setVisibility(View.VISIBLE);
                        binding.storageTxt3.setText("");
                        highlightLogo3();

                    } else if (binding.storageTxt4.getText().toString().equals("STORAGE_4")) {

                        binding.storageImg4.setImageBitmap(bitmap);
                        binding.storageImg4.setVisibility(View.VISIBLE);
                        binding.storageTxt4.setText("");
                        highlightLogo4();

                    } else if (binding.storageTxt5.getText().toString().equals("STORAGE_5")) {

                        binding.storageImg5.setImageBitmap(bitmap);
                        binding.storageImg5.setVisibility(View.VISIBLE);
                        binding.storageTxt5.setText("");
                        highlightLogo5();

                    } else if (binding.storageTxt6.getText().toString().equals("STORAGE_6")) {

                        binding.storageImg6.setImageBitmap(bitmap);
                        binding.storageImg6.setVisibility(View.VISIBLE);
                        binding.storageTxt6.setText("");
                        highlightLogo6();

                    } else if (binding.storageTxt7.getText().toString().equals("STORAGE_7")) {

                        binding.storageImg7.setImageBitmap(bitmap);
                        binding.storageImg7.setVisibility(View.VISIBLE);
                        binding.storageTxt7.setText("");
                        highlightLogo7();

                    } else if (binding.storageTxt8.getText().toString().equals("STORAGE_8")) {

                        binding.storageImg8.setImageBitmap(bitmap);
                        binding.storageImg8.setVisibility(View.VISIBLE);
                        binding.storageTxt8.setText("");
                        highlightLogo8();

                    } else if (binding.storageTxt9.getText().toString().equals("STORAGE_9")) {

                        binding.storageImg9.setImageBitmap(bitmap);
                        binding.storageImg9.setVisibility(View.VISIBLE);
                        binding.storageTxt9.setText("");
                        highlightLogo9();

                    } else if (binding.storageTxt10.getText().toString().equals("STORAGE_10")) {

                        binding.storageImg10.setImageBitmap(bitmap);
                        binding.storageImg10.setVisibility(View.VISIBLE);
                        binding.storageTxt10.setText("");
                        highlightLogo10();

                    } else {

                        Toast.makeText(editFameActivity.this, "You can add maximum 10 Photos !", Toast.LENGTH_SHORT).show();
                    }


                } catch (Exception ex) {
                }
            }

        } catch (Exception ex) {
        }


    }

    private Bitmap scaleBitmap(Bitmap bm, int maxWidth, int maxHeight) {
        int width = bm.getWidth();
        int height = bm.getHeight();

        if (width > height) {
            // landscape
            int ratio = width / maxWidth;
            width = maxWidth;
            height = height / ratio;
        } else if (height > width) {
            // portrait
            int ratio = height / maxHeight;
            height = maxHeight;
            width = width / ratio;
        } else {
            // square
            height = maxHeight;
            width = maxWidth;
        }

        bm = Bitmap.createScaledBitmap(bm, width, height, true);
        return bm;
    }

    @Override
    public void onBackPressed() {


            Constant.viewVisibleAnimator(binding.coord);



        new CountDownTimer(2500, 1000) {

            public void onTick(long millisUntilFinished) {


            }

            public void onFinish() {
                Constant.viewGoneAnimator(binding.coord);
            }
        }.start();

        if (binding.coord.getVisibility() == View.VISIBLE) {
            new AlertDialog.Builder(editFameActivity.this).setTitle("Warning").setMessage("If you leave this page , you loss all your work.\nAre you sure want to exit?")


                    .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {

                            onBackPressed2();

                        }

                    })

                    .setNegativeButton(android.R.string.no, null).setIcon(null).show();

        }


    }

    public void setAdapter() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false);
        binding.editRecyclerViewFrame.setLayoutManager(linearLayoutManager);
        frameadapter = new frameAdapter(mContext);
        binding.editRecyclerViewFrame.setHasFixedSize(true);
        binding.editRecyclerViewFrame.setAdapter(frameadapter);
        LinearLayoutManager linearLayoutManager2 = new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false);
        binding.editRecyclerViewFrame2.setLayoutManager(linearLayoutManager2);
        frameadapter = new frameAdapter(mContext);
        binding.editRecyclerViewFrame2.setHasFixedSize(true);
        binding.editRecyclerViewFrame2.setAdapter(frameadapter);
    }

    public void onBackPressed2() {

        super.onBackPressed();

    }
}
