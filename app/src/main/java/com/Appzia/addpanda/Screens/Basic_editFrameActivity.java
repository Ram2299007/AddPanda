package com.Appzia.addpanda.Screens;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.PointF;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.InputFilter;
import android.text.InputType;
import android.util.Base64;
import android.util.Log;
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
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.Appzia.addpanda.MainActivity;
import com.Appzia.addpanda.R;
import com.Appzia.addpanda.databinding.ActivityBasicEditFrameBinding;
import com.Appzia.addpanda.sharedPreference.PreferenceManager;
import com.google.android.material.bottomappbar.BottomAppBar;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.shape.CornerFamily;
import com.google.android.material.shape.MaterialShapeDrawable;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Basic_editFrameActivity extends AppCompatActivity {

    ActivityBasicEditFrameBinding binding;
    Animation animation, animation2;
    EditText enterText;
    Button enterButton;
    Button editButton;
    TextView titleText;
    public static final int REQUEST_ID_MULTIPLE_PERMISSIONS = 1;
    File file_camera, file_camera_final1;
    PreferenceManager preferenceManager;
    String previouslyEncodedGalley, previouslyEncodedCamera;


    // for moving edittext variable section
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
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityBasicEditFrameBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


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


                        new AlertDialog.Builder(Basic_editFrameActivity.this).setTitle("Warning").setMessage("If you leave this page , you loss all your work.\nAre you sure want to exit?")


                                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int which) {

                                        Intent i = new Intent(getApplicationContext(), MainActivity.class);
                                        i.putExtra("editKey", "editHome");
                                        startActivity(i);
                                    }

                                })

                                .setNegativeButton(android.R.string.no, null).setIcon(R.drawable.warning_svg).show();


                        break;

                    case R.id.profile:

                        //     getSupportFragmentManager().beginTransaction().replace(R.id.editFrameActivityFrame, new profileFragment()).commit();


                        new AlertDialog.Builder(Basic_editFrameActivity.this).setTitle("Warning").setMessage("If you leave this page , you loss all your work.\nAre you sure want to exit?")


                                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int which) {

                                        Intent i2 = new Intent(getApplicationContext(), MainActivity.class);
                                        i2.putExtra("editKey", "editProfile");
                                        startActivity(i2);

                                    }

                                })

                                .setNegativeButton(android.R.string.no, null).setIcon(R.drawable.warning_svg).show();


                        break;
                    case R.id.category:

                        //getSupportFragmentManager().beginTransaction().replace(R.id.editFrameActivityFrame, new categoryTabFragment()).commit();


                        new AlertDialog.Builder(Basic_editFrameActivity.this).setTitle("Warning").setMessage("If you leave this page , you loss all your work.\nAre you sure want to exit?")


                                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int which) {

                                        Intent i3 = new Intent(getApplicationContext(), MainActivity.class);
                                        i3.putExtra("editKey", "editCategory");
                                        startActivity(i3);

                                    }

                                })

                                .setNegativeButton(android.R.string.no, null).setIcon(R.drawable.warning_svg).show();


                        break;
                    case R.id.download:

                        //  getSupportFragmentManager().beginTransaction().replace(R.id.editFrameActivityFrame, new DownloadsFragment()).commit();


                        new AlertDialog.Builder(Basic_editFrameActivity.this).setTitle("Warning").setMessage("If you leave this page , you loss all your work.\nAre you sure want to exit?")


                                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int which) {


                                        Intent i4 = new Intent(getApplicationContext(), MainActivity.class);
                                        i4.putExtra("editKey", "editDownload");
                                        startActivity(i4);

                                    }

                                })

                                .setNegativeButton(android.R.string.no, null).setIcon(R.drawable.warning_svg).show();


                        break;
                }
                return true;


            }
        });
        preferenceManager = PreferenceManager.getInstance(getApplicationContext());

        //bottom for editfram from fragment activity
        binding.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // getSupportFragmentManager().beginTransaction().replace(R.id.mainActivityFrame, new createFragment()).commit();
                new AlertDialog.Builder(Basic_editFrameActivity.this).setTitle("Warning").setMessage("If you leave this page , you loss all your work.\nAre you sure want to exit?")


                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {

                                Intent i = new Intent(getApplicationContext(), MainActivity.class);
                                i.putExtra("editKey", "editFab");
                                startActivity(i);

                            }

                        })

                        .setNegativeButton(android.R.string.no, null).setIcon(R.drawable.warning_svg).show();


            }
        });


        binding.backArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //     getSupportFragmentManager().beginTransaction().replace(R.id.mainActivityFrame, new trendingFragment()).commit();

                new AlertDialog.Builder(Basic_editFrameActivity.this).setTitle("Warning").setMessage("If you leave this page , you loss all your work.\nAre you sure want to exit?")


                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {

                                Intent i = new Intent(getApplicationContext(), MainActivity.class);
                                i.putExtra("editKey", "editBack");
                                startActivity(i);

                            }
                        })

                        .setNegativeButton(android.R.string.no, null).setIcon(R.drawable.warning_svg).show();


            }
        });
        // Inflate the layout for this fragment

        binding.download.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //  assert getFragmentManager() != null;
                new AlertDialog.Builder(Basic_editFrameActivity.this).setTitle("Warning").setMessage("Are you sure for download your frame ?")


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


//                getSupportFragmentManager().beginTransaction().replace(R.id.mainActivityFrame, new downloadimageFragment()).commit();
                                    Intent i = new Intent(getApplicationContext(), MainActivity.class);
                                    i.putExtra("editKey", "editDownloadImage2");
                                    i.putExtra("imgData", byteArray);
                                    startActivity(i);

                                } catch (Exception ex) {
                                    Log.d("Bitmap Error", ex.getMessage());
                                }
                            }

                        })

                        .setNegativeButton(android.R.string.no, null).setIcon(R.drawable.warning_svg_2).show();


            }
        });


        final Dialog dlg2 = new Dialog(Basic_editFrameActivity.this);
        dlg2.setContentView(R.layout.add_text_diialogue);
        dlg2.setCanceledOnTouchOutside(true);
        dlg2.setCancelable(true);
        dlg2.getWindow().getAttributes().windowAnimations = R.style.PauseDialogAnimation;

        enterText = (EditText) dlg2.findViewById(R.id.enterText);
        enterButton = (Button) dlg2.findViewById(R.id.addButton);
        editButton = (Button) dlg2.findViewById(R.id.editButton);
        titleText = (TextView) dlg2.findViewById(R.id.titleText);


        binding.addtext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_in_bottom2);
                animation2 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_in_top_2);
                binding.treding.setText("Create Frame");
                binding.editFrame.setAnimation(animation);
                binding.selectframeid.setVisibility(View.GONE);
                binding.selectframeid2.setAnimation(animation2);
                binding.selectframeid2.setVisibility(View.VISIBLE);
                binding.addLayout.setAnimation(animation);


                editButton.setVisibility(View.GONE);
                enterButton.setVisibility(View.VISIBLE);
                titleText.setText("Add Text");

                enterText.setText(binding.editTextFrag.getText().toString());
                enterText.setSelection(enterText.length());
                enterText.setInputType(InputType.TYPE_CLASS_TEXT);
                InputFilter[] filterArray = new InputFilter[1];
                filterArray[0] = new InputFilter.LengthFilter(20);
                enterText.setFilters(filterArray);

                enterButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        if (enterText.getText().toString().equals("")) {

                            enterText.setError("Missing text ?");
                            // Toast.makeText(editFameActivity.this, "Missing Text ?", Toast.LENGTH_SHORT).show();
                        } else {


                            String text = enterText.getText().toString();

                            binding.editTextFrag.setText(text);
                            binding.editTextFrag.setVisibility(View.VISIBLE);


                            enterText.setText("");
                            dlg2.dismiss();

                        }
                    }
                });


                dlg2.show();

                Window window = dlg2.getWindow();
                window.setLayout(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);


            }
        });
        binding.locationButn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_in_bottom2);
                animation2 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_in_top_2);
                binding.treding.setText("Create Frame");
                binding.editFrame.setAnimation(animation);
                binding.selectframeid.setVisibility(View.GONE);
                binding.selectframeid2.setAnimation(animation2);
                binding.selectframeid2.setVisibility(View.VISIBLE);
                binding.addLayout.setAnimation(animation);


                editButton.setVisibility(View.GONE);
                enterButton.setVisibility(View.VISIBLE);
                titleText.setText("Add Location");

                enterText.setText(binding.locationText.getText().toString());
                enterText.setSelection(enterText.length());
                enterText.setInputType(InputType.TYPE_CLASS_TEXT);
                InputFilter[] filterArray = new InputFilter[1];
                filterArray[0] = new InputFilter.LengthFilter(25);
                enterText.setFilters(filterArray);

                enterButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        if (enterText.getText().toString().equals("")) {

                            enterText.setError("Missing location ?");
                            // Toast.makeText(editFameActivity.this, "Missing Text ?", Toast.LENGTH_SHORT).show();
                        } else {


                            String text = enterText.getText().toString();

                            binding.locationText.setText(text);
                            binding.locationText.setVisibility(View.VISIBLE);
                            binding.locationImg.setVisibility(View.VISIBLE);

                            enterText.setText("");
                            dlg2.dismiss();

                        }
                    }
                });


                dlg2.show();

                Window window = dlg2.getWindow();
                window.setLayout(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);

            }
        });
        binding.emailBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_in_bottom2);
                animation2 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_in_top_2);
                binding.treding.setText("Create Frame");
                binding.editFrame.setAnimation(animation);
                binding.selectframeid.setVisibility(View.GONE);
                binding.selectframeid2.setAnimation(animation2);
                binding.selectframeid2.setVisibility(View.VISIBLE);
                binding.addLayout.setAnimation(animation);


                editButton.setVisibility(View.GONE);
                enterButton.setVisibility(View.VISIBLE);
                titleText.setText("Add Email");

                enterText.setText(binding.emailText.getText().toString());
                enterText.setSelection(enterText.length());
                enterText.setInputType(InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS);
                InputFilter[] filterArray = new InputFilter[1];
                filterArray[0] = new InputFilter.LengthFilter(29);
                enterText.setFilters(filterArray);

                enterButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        if (enterText.getText().toString().equals("")) {

                            enterText.setError("Missing Email ?");
                            // Toast.makeText(editFameActivity.this, "Missing Text ?", Toast.LENGTH_SHORT).show();
                        } else {


                            String text = enterText.getText().toString();

                            binding.emailText.setText(text);
                            binding.emailText.setVisibility(View.VISIBLE);
                            binding.emailImg.setVisibility(View.VISIBLE);

                            enterText.setText("");
                            dlg2.dismiss();

                        }
                    }
                });


                dlg2.show();

                Window window = dlg2.getWindow();
                window.setLayout(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);

            }
        });
        binding.phoneBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_in_bottom2);
                animation2 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_in_top_2);
                binding.treding.setText("Create Frame");
                binding.editFrame.setAnimation(animation);
                binding.selectframeid.setVisibility(View.GONE);
                binding.selectframeid2.setAnimation(animation2);
                binding.selectframeid2.setVisibility(View.VISIBLE);
                binding.addLayout.setAnimation(animation);


                editButton.setVisibility(View.GONE);
                enterButton.setVisibility(View.VISIBLE);
                titleText.setText("Add Phone");

                enterText.setText(binding.phoneText.getText().toString());
                enterText.setSelection(enterText.length());
                enterText.setInputType(InputType.TYPE_CLASS_PHONE | InputType.TYPE_CLASS_TEXT);
                InputFilter[] filterArray = new InputFilter[1];
                filterArray[0] = new InputFilter.LengthFilter(10);
                enterText.setFilters(filterArray);




                enterButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        if (enterText.getText().toString().equals("")) {

                            enterText.setError("Missing Phone ?");
                            // Toast.makeText(editFameActivity.this, "Missing Text ?", Toast.LENGTH_SHORT).show();
                        } else {
                            String text = enterText.getText().toString();

                            binding.phoneText.setText("+91-"+text);
                            binding.phoneText.setVisibility(View.VISIBLE);
                            binding.phoneImg.setVisibility(View.VISIBLE);

                            enterText.setText("");
                            dlg2.dismiss();

                        }
                    }
                });


                dlg2.show();

                Window window = dlg2.getWindow();
                window.setLayout(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);


            }
        });


        binding.logoBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAndRequestPermissions();
                try {
                    Intent galleryIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    startActivityForResult(galleryIntent, 0);

                } catch (Exception e) {
                    e.printStackTrace();
                }

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


                }


            }
        });

        binding.wrong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        binding.socialMedia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                binding.whatsapp.setVisibility(View.VISIBLE);
                binding.facebook.setVisibility(View.VISIBLE);
                binding.twitter.setVisibility(View.VISIBLE);
                binding.instagram.setVisibility(View.VISIBLE);

            }
        });

        binding.whatsapp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.whatsapp.setVisibility(View.GONE);
            }
        });
        binding.facebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.facebook.setVisibility(View.GONE);
            }
        });
        binding.twitter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.twitter.setVisibility(View.GONE);
            }
        });
        binding.instagram.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                binding.instagram.setVisibility(View.GONE);

            }
        });
    }

    private boolean checkAndRequestPermissions() {
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
            ActivityCompat.requestPermissions(Basic_editFrameActivity.this, listPermissionsNeeded.toArray(new String[listPermissionsNeeded.size()]), REQUEST_ID_MULTIPLE_PERMISSIONS);
            return false;
        }
        return true;
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
                    file_camera_final1 = new File(getPathFromUri(_uri, Basic_editFrameActivity.this));


                    Bitmap photo2 = MediaStore.Images.Media.getBitmap(Basic_editFrameActivity.this.getContentResolver(), imageUri);
                    //Bitmap photo2 = (Bitmap) data.getExtras().get("data");
                    ByteArrayOutputStream baos = new ByteArrayOutputStream();
                    //Bitmap scaledImage = scaleBitmap(imageBitmap , 460, 230);
                    photo2.compress(Bitmap.CompressFormat.PNG, 100, baos);
                    byte[] b = baos.toByteArray();
                    String encodedImage = Base64.encodeToString(b, Base64.DEFAULT);
                    preferenceManager.setString("gallery_data2", encodedImage);
                    // preferenceManager.setString("camera_data", "passed_empty_gallery");
                    getImageFromGallery();

                }

            } catch (Exception e) {
                e.printStackTrace();
            }


        }


        binding.imageGallaryRel.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                RelativeLayout view = (RelativeLayout) v;
                view.bringToFront();
                viewTransformation(view, event);
                return true;
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

    private void setMargins(View view, int left, int top, int right, int bottom) {
        if (view.getLayoutParams() instanceof ViewGroup.MarginLayoutParams) {
            ViewGroup.MarginLayoutParams p = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
            p.setMargins(left, top, right, bottom);
            view.requestLayout();
        }
    }

    public void getImageFromGallery() {


        try {

            //for gallery data to image view
            preferenceManager = PreferenceManager.getInstance(Basic_editFrameActivity.this);
            previouslyEncodedGalley = preferenceManager.getString("gallery_data2");

            Log.d("previouslyEncodedGalley", previouslyEncodedGalley);

            if (!previouslyEncodedGalley.equalsIgnoreCase("")) {
                byte[] b = Base64.decode(previouslyEncodedGalley, Base64.DEFAULT);
                Bitmap bitmap = BitmapFactory.decodeByteArray(b, 0, b.length);

                try {


                    binding.img.setImageBitmap(bitmap);
                    binding.imageGallaryRel.setVisibility(View.VISIBLE);


                } catch (Exception ex) {

                    Toast.makeText(this, ex.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }

        } catch (Exception ex) {

            Toast.makeText(this, ex.getMessage(), Toast.LENGTH_SHORT).show();
        }


    }


}