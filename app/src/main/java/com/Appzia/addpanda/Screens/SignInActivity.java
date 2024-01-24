package com.Appzia.addpanda.Screens;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.Toast;

import com.Appzia.addpanda.R;
import com.Appzia.addpanda.Util.Constant.Constant;
import com.Appzia.addpanda.Webservice.Webservice;
import com.Appzia.addpanda.databinding.ActivitySignInBinding;
import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FacebookAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.GoogleAuthProvider;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class SignInActivity extends AppCompatActivity {
    ActivitySignInBinding binding;
    private FirebaseAuth auth, mAuth;
    GoogleSignInClient mGoogleSignInClient;
    ProgressDialog progressDialog, progressDialog2;
    CallbackManager mCallbackManager;
    LoginButton loginButton;
    public static final int PERMISSIONS_REQUEST_READ_CONTACTS = 1;
    ImageView img;
    public static final int REQUEST_ID_MULTIPLE_PERMISSIONS = 1;
    public static String type = "2";    //for mobile type
    Context mContext;
    String social_media_type;
    String token, VERIFIED_KEY, VERIFIED_SOCIAL_MEDIA_KEY;
    public static String social_media_typeKey;


    @Override
    protected void onStart() {
        super.onStart();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            checkAndRequestPermissions();
        }

        SharedPreferences sh = getSharedPreferences("MySharedPref", Context.MODE_PRIVATE);
        String user_id = sh.getString("user_id", "");
        String otp = sh.getString("otp", "");
        token = sh.getString("TOKEN_SF", "");
        VERIFIED_KEY = sh.getString("VERIFIED_KEY", "");
        VERIFIED_SOCIAL_MEDIA_KEY = sh.getString("VERIFIED_SOCIAL_MEDIA_KEY", "");
        //   Toast.makeText(mContext, token, Toast.LENGTH_SHORT).show();
        social_media_typeKey = sh.getString("social_media_type", "");


        if (VERIFIED_KEY.equals("VERIFIED_KEY")) {

            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(intent);

        } else if (VERIFIED_SOCIAL_MEDIA_KEY.equals("VERIFIED_SOCIAL_MEDIA_KEY")) {
            Intent intent = new Intent(mContext, MainActivity.class);

            mContext.startActivity(intent);
        } else {
            try {

                Constant.NetworkCheck(mContext);
                if ((Constant.wifiInfo != null && Constant.wifiInfo.isConnected()) || (Constant.mobileInfo != null && Constant.mobileInfo.isConnected())) {
                    //  WebserviceRetrofit.verify_otp(mContext, type, otp, token, binding.mobileNo.getText().toString());
                    Webservice.social_media_login_FOR_CHECK(mContext, "1", FirebaseAuth.getInstance().getUid(), token);
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
        }

//
        SharedPreferences sharedPreferences = getSharedPreferences("MySharedPref", MODE_PRIVATE);

        SharedPreferences.Editor myEdit = sharedPreferences.edit();

        myEdit.putString("nameKey", "");
        myEdit.apply();


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivitySignInBinding.inflate(getLayoutInflater());

        setContentView(binding.getRoot());


        Objects.requireNonNull(getSupportActionBar()).hide();
        Window window = this.getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.setStatusBarColor(this.getResources().getColor(R.color.appThemeColor));


        mContext = this;
        progressDialog = new ProgressDialog(this);
        //for facebook authentication
        FacebookSdk.sdkInitialize(getApplicationContext());

        mAuth = FirebaseAuth.getInstance();

        // Initialize Facebook Login button
        mCallbackManager = CallbackManager.Factory.create();
        loginButton = findViewById(R.id.login_button);


        binding.fb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SharedPreferences sharedPreferences = getSharedPreferences("MySharedPref", MODE_PRIVATE);

                SharedPreferences.Editor myEdit = sharedPreferences.edit();

                myEdit.putString("nameKey", "facebook");
                myEdit.apply();


                if (binding.refer.getText().toString().equals("")) {
                    binding.refer.requestFocus();
                    binding.skip.setVisibility(View.VISIBLE);
                    binding.refer.setBackgroundResource(R.drawable.missing_edittext_radius);
                    binding.refer.setHint("Reference code ?");

                } else {
                    binding.loginButton.performClick();

                }


            }
        });

        loginButton.setReadPermissions("email", "public_profile");
        loginButton.registerCallback(mCallbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                handleFacebookAccessToken(loginResult.getAccessToken());
            }

            @Override
            public void onCancel() {
                Log.d("TAG", "facebook:onCancel");
            }

            @Override
            public void onError(FacebookException error) {
                Log.d("TAG", "facebook:onError", error);
            }
        });
        // Other Data starts from here

        auth = FirebaseAuth.getInstance();

        progressDialog = new ProgressDialog(SignInActivity.this);
        progressDialog.setTitle("Login");
        progressDialog.setMessage("Login to your account");
        //  Configure Google Sign In  error in future occure in default web config client id
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestIdToken(getString(R.string.default_web_client_id)).requestEmail().build();

        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);

        binding.googleId.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                signIn();
            }
        });


        binding.mobileNo.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {


                if (binding.mobileNo.length() == 10) {

                    SharedPreferences sharedPreferences = getSharedPreferences("MySharedPref", MODE_PRIVATE);

                    SharedPreferences.Editor myEdit = sharedPreferences.edit();

                    myEdit.putString("nameKey", "phone");
                    myEdit.apply();


                    if (binding.refer.getText().toString().equals("")) {
                        binding.refer.requestFocus();
                        binding.skip.setVisibility(View.VISIBLE);
                        binding.refer.setBackgroundResource(R.drawable.missing_edittext_radius);
                        binding.refer.setHint("Reference code ?");

                    } else {
                        //  binding.skip.setVisibility(View.GONE);
                        //binding.skip.setText("Continue with phone");
                        startActivity(new Intent(getApplicationContext(), MobileVerification.class));

                    }


                }

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        binding.refer.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                if (binding.refer.getText().toString().equals("")) {
                    binding.skip.setText("Skip & continue");
                    binding.skip.setVisibility(View.VISIBLE);
                    binding.refer.setBackgroundResource(R.drawable.missing_edittext_radius);
                    binding.refer.setHint("Reference code ?");

                } else {

                    SharedPreferences sh = getSharedPreferences("MySharedPref", Context.MODE_PRIVATE);

                    String s1 = sh.getString("nameKey", "");

                    if (s1.equals("phone")) {

                        binding.skip.setText("Continue with phone");
                        binding.refer.setBackgroundResource(R.drawable.button_color_hover_for_all);
                        binding.refer.setHint("Reference code");

                    } else if (s1.equals("google")) {
                        binding.skip.setText("Continue with google");
                        binding.refer.setBackgroundResource(R.drawable.button_color_hover_for_all);
                        binding.refer.setHint("Reference code");

                        social_media_type = "1";
                        // Storing data into SharedPreferences
                        SharedPreferences sharedPreferences = getSharedPreferences("MySharedPref", MODE_PRIVATE);
                        SharedPreferences.Editor myEdit = sharedPreferences.edit();
                        myEdit.putString("social_media_type", social_media_type);
                        myEdit.apply();


                    } else if (s1.equals("facebook")) {
                        binding.skip.setText("Continue with facebook");
                        binding.refer.setBackgroundResource(R.drawable.button_color_hover_for_all);
                        binding.refer.setHint("Reference code");


                    } else {


                    }

                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        binding.skip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SharedPreferences sh = getSharedPreferences("MySharedPref", Context.MODE_PRIVATE);

                String s1 = sh.getString("nameKey", "");

                if (binding.skip.getText().toString().equals("Skip & continue") && s1.equals("phone")) {

                    //withput referal key
                    try {
                        progressDialog2.setTitle("Please wait");
                        progressDialog2.setMessage("Log in to your account");
                        progressDialog2.show();
                    } catch (Exception ignored) {
                    }

                    Constant.NetworkCheck(mContext);
                    if ((Constant.wifiInfo != null && Constant.wifiInfo.isConnected()) || (Constant.mobileInfo != null && Constant.mobileInfo.isConnected())) {
                        Webservice.send_otp(mContext, type, binding.mobileNo.getText().toString().trim(), binding.refer.getText().toString());

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


                } else if (binding.skip.getText().toString().equals("Skip & continue") && s1.equals("google")) {


                    //withput referal key
                    progressDialog.show();
                    Intent signInIntent = mGoogleSignInClient.getSignInIntent();
                    startActivityForResult(signInIntent, RC_SIGN_IN);
                    social_media_type = "1";
                    // Storing data into SharedPreferences
                    SharedPreferences sharedPreferences = getSharedPreferences("MySharedPref", MODE_PRIVATE);
                    SharedPreferences.Editor myEdit = sharedPreferences.edit();
                    myEdit.putString("social_media_type", social_media_type);
                    myEdit.apply();
                } else if (binding.skip.getText().toString().equals("Skip & continue") && s1.equals("facebook")) {

                    binding.loginButton.performClick();

                } else {
                    Toast.makeText(mContext, "Authentication not selected", Toast.LENGTH_SHORT).show();
                }


                if (binding.skip.getText().toString().equals("Continue with phone")) {

                    Constant.NetworkCheck(mContext);
                    if ((Constant.wifiInfo != null && Constant.wifiInfo.isConnected()) || (Constant.mobileInfo != null && Constant.mobileInfo.isConnected())) {
                        Webservice.send_otp(mContext, type, binding.mobileNo.getText().toString().trim(), binding.refer.getText().toString());

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
                } else if (binding.skip.getText().toString().equals("Continue with google")) {

                    //with referal key
                    progressDialog.show();
                    Intent signInIntent = mGoogleSignInClient.getSignInIntent();
                    startActivityForResult(signInIntent, RC_SIGN_IN);
                } else if (binding.skip.getText().toString().equals("Continue with facebook")) {

                    //with referal key
                    binding.loginButton.performClick();
                }

            }
        });


    }

    int RC_SIGN_IN = 65;

    private void signIn() {


        SharedPreferences sharedPreferences = getSharedPreferences("MySharedPref", MODE_PRIVATE);

        SharedPreferences.Editor myEdit = sharedPreferences.edit();

        myEdit.putString("nameKey", "google");
        myEdit.apply();


        if (binding.refer.getText().toString().equals("")) {
            binding.refer.requestFocus();
            binding.skip.setVisibility(View.VISIBLE);
            binding.refer.setBackgroundResource(R.drawable.missing_edittext_radius);
            binding.refer.setHint("Reference code ?");

        } else {
            //  binding.skip.setVisibility(View.GONE);
            // binding.skip.setText("Continue with google");
            progressDialog.show();
            Intent signInIntent = mGoogleSignInClient.getSignInIntent();
            startActivityForResult(signInIntent, RC_SIGN_IN);

        }


    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                // Google Sign In was successful, authenticate with Firebase
                GoogleSignInAccount account = task.getResult(ApiException.class);
                Log.d("TAG", "firebaseAuthWithGoogle:" + account.getId());
                firebaseAuthWithGoogle(account.getIdToken());
            } catch (ApiException e) {
                // Google Sign In failed, update UI appropriately
                Log.w("TAG", "Google sign in failed", e);
            }
        }


        // Pass the activity result back to the Facebook SDK
        mCallbackManager.onActivityResult(requestCode, resultCode, data);
    }


    private void firebaseAuthWithGoogle(String idToken) {
        AuthCredential credential = GoogleAuthProvider.getCredential(idToken, null);
        auth.signInWithCredential(credential).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    // Sign in success, update UI with the signed-in user's information

                    progressDialog.dismiss();
                    Log.d("signInWithCredential", "signInWithCredential:success" + task);


                    social_media_type = "1";
                    // Storing data into SharedPreferences
                    SharedPreferences sharedPreferences = getSharedPreferences("MySharedPref", MODE_PRIVATE);
                    SharedPreferences.Editor myEdit = sharedPreferences.edit();
                    myEdit.putString("social_media_type", social_media_type);
                    myEdit.apply();


                    SharedPreferences sh = getSharedPreferences("MySharedPref", Context.MODE_PRIVATE);
                    social_media_typeKey = sh.getString("social_media_type", "");

                    try {
                        Constant.NetworkCheck(mContext);
                        if ((Constant.wifiInfo != null && Constant.wifiInfo.isConnected()) || (Constant.mobileInfo != null && Constant.mobileInfo.isConnected())) {
                            Webservice.social_media_login_FOR_CHECK(mContext, social_media_typeKey, FirebaseAuth.getInstance().getUid(), token);

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
                        Toast.makeText(mContext, ex.getMessage(), Toast.LENGTH_SHORT).show();
                    }

                    //  Toast.makeText(SignInActivity.this, "Success", Toast.LENGTH_SHORT).show();

                    String key = FirebaseAuth.getInstance().getUid();
                    String name = Objects.requireNonNull(auth.getCurrentUser()).getDisplayName();
                    String image = String.valueOf(Objects.requireNonNull(auth.getCurrentUser()).getPhotoUrl());
                    String provider_data = String.valueOf(Objects.requireNonNull(auth.getCurrentUser()).getProviderData());
                    String email = String.valueOf(Objects.requireNonNull(auth.getCurrentUser()).getEmail());
                    String TenantId = String.valueOf(Objects.requireNonNull(auth.getCurrentUser()).getTenantId());


//                            Toast.makeText(SignInActivity.this, "User Id :\n" + key, Toast.LENGTH_SHORT).show();
//                            Toast.makeText(SignInActivity.this, "Name :\n" + name, Toast.LENGTH_SHORT).show();
//                            Toast.makeText(SignInActivity.this, "Name :\n" + image, Toast.LENGTH_SHORT).show();
                    Log.d("url", image);

                    //  Log.d("googleResponse", "_name_"+name+"_image_"+image+"_key_"+key+"___All Response ___"+allResponse);


                    Log.d("googleResponse", "_name : " + name + "_image :" + image + "_-Auth_Id :" + key + "_provider_data" + provider_data + "_email" + email + "_TenantId" + TenantId);

                } else {
                    // If sign in fails, display a message to the user.
                    Log.w("TAG", "signInWithCredential:failure", task.getException());
                    //    updateUI(null);
                }
            }
        });
    }


    private void handleFacebookAccessToken(AccessToken token2) {
        Log.d("TAG", "handleFacebookAccessToken:" + token2);

        AuthCredential credential = FacebookAuthProvider.getCredential(token2.getToken());
        mAuth.signInWithCredential(credential).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
//                    // Sign in success, update UI with the signed-in user's information
//                    Log.d("TAG", "signInWithCredential:success");
//                    FirebaseUser user = mAuth.getCurrentUser();
//
//                    //after complete activity
//
//                    String key = FirebaseAuth.getInstance().getUid();
//                    String name = Objects.requireNonNull(auth.getCurrentUser()).getDisplayName();
//                    String image = String.valueOf(Objects.requireNonNull(auth.getCurrentUser()).getPhotoUrl());
//
//                    startActivity(new Intent(getApplicationContext(), choosePersonalBusiness.class));


                    progressDialog.dismiss();


                    social_media_type = "2";
                    // Storing data into SharedPreferences
                    SharedPreferences sharedPreferences = getSharedPreferences("MySharedPref", MODE_PRIVATE);
                    SharedPreferences.Editor myEdit = sharedPreferences.edit();
                    myEdit.putString("social_media_type", social_media_type);
                    myEdit.apply();


                    SharedPreferences sh = getSharedPreferences("MySharedPref", Context.MODE_PRIVATE);
                    social_media_typeKey = sh.getString("social_media_type", "");

                    try {
                        Constant.NetworkCheck(mContext);
                        if ((Constant.wifiInfo != null && Constant.wifiInfo.isConnected()) || (Constant.mobileInfo != null && Constant.mobileInfo.isConnected())) {
                            Webservice.social_media_login_FOR_CHECK(mContext, social_media_typeKey, FirebaseAuth.getInstance().getUid(), token);

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
                        Toast.makeText(mContext, ex.getMessage(), Toast.LENGTH_SHORT).show();
                    }


                } else {
                    // If sign in fails, display a message to the user.
                    Log.w("TAG", "signInWithCredential:failure", task.getException());
                    Toast.makeText(getApplicationContext(), "Authentication failed.", Toast.LENGTH_SHORT).show();


                }
            }
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.TIRAMISU)
    private boolean checkAndRequestPermissions() {

        try {
            int camera = ContextCompat.checkSelfPermission(getApplicationContext(), android.Manifest.permission.CAMERA);
            int storage = ContextCompat.checkSelfPermission(getApplicationContext(), android.Manifest.permission.WRITE_EXTERNAL_STORAGE);
            int contact = ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.READ_CONTACTS);
            int loc = ContextCompat.checkSelfPermission(getApplicationContext(), android.Manifest.permission.ACCESS_COARSE_LOCATION);
            int loc2 = ContextCompat.checkSelfPermission(getApplicationContext(), android.Manifest.permission.ACCESS_FINE_LOCATION);
            int calling = ContextCompat.checkSelfPermission(getApplicationContext(), android.Manifest.permission.CALL_PHONE);
            int receiveSms = ContextCompat.checkSelfPermission(getApplicationContext(), android.Manifest.permission.RECEIVE_SMS);
            int phonestate = ContextCompat.checkSelfPermission(getApplicationContext(), android.Manifest.permission.READ_PHONE_STATE);
            int readSms = ContextCompat.checkSelfPermission(getApplicationContext(), android.Manifest.permission.READ_SMS);
            int readPhonenumber = ContextCompat.checkSelfPermission(getApplicationContext(), android.Manifest.permission.READ_PHONE_NUMBERS);
            int POST_NOTIFICATIONS = ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.POST_NOTIFICATIONS);


            List<String> listPermissionsNeeded = new ArrayList<>();

            if (camera != PackageManager.PERMISSION_GRANTED) {
                listPermissionsNeeded.add(android.Manifest.permission.CAMERA);
            }
            if (storage != PackageManager.PERMISSION_GRANTED) {
                listPermissionsNeeded.add(android.Manifest.permission.WRITE_EXTERNAL_STORAGE);

            }
            if (contact != PackageManager.PERMISSION_GRANTED) {
                listPermissionsNeeded.add(android.Manifest.permission.READ_CONTACTS);

            }
            if (calling != PackageManager.PERMISSION_GRANTED) {
                listPermissionsNeeded.add(android.Manifest.permission.CALL_PHONE);

            }
            if (receiveSms != PackageManager.PERMISSION_GRANTED) {
                listPermissionsNeeded.add(Manifest.permission.RECEIVE_SMS);

            }
            if (phonestate != PackageManager.PERMISSION_GRANTED) {
                listPermissionsNeeded.add(Manifest.permission.READ_PHONE_STATE);

            }
            if (readSms != PackageManager.PERMISSION_GRANTED) {
                listPermissionsNeeded.add(Manifest.permission.READ_SMS);

            }
            if (readPhonenumber != PackageManager.PERMISSION_GRANTED) {
                listPermissionsNeeded.add(Manifest.permission.READ_PHONE_NUMBERS);

            }
            if (POST_NOTIFICATIONS != PackageManager.PERMISSION_GRANTED) {
                listPermissionsNeeded.add(Manifest.permission.POST_NOTIFICATIONS);

            }
            if (!listPermissionsNeeded.isEmpty()) {
                ActivityCompat.requestPermissions(SignInActivity.this, listPermissionsNeeded.toArray(new String[listPermissionsNeeded.size()]), REQUEST_ID_MULTIPLE_PERMISSIONS);

                return false;
            }
        } catch (Exception ex) {
            Log.d("permission", ex.getMessage());
        }
        return true;
    }

    @Override
    public void onBackPressed() {
        finish();
        finishAffinity();
    }
}