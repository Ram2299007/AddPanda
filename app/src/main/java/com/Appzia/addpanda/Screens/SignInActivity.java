package com.Appzia.addpanda.Screens;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.Toast;


import com.Appzia.addpanda.MainActivity;
import com.Appzia.addpanda.R;
import com.Appzia.addpanda.databinding.ActivitySignInBinding;
import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;
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
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class SignInActivity extends AppCompatActivity {
    ActivitySignInBinding binding;
    private FirebaseAuth auth, mAuth;
    GoogleSignInClient mGoogleSignInClient;
    ProgressDialog progressDialog;
    CallbackManager mCallbackManager;
    LoginButton loginButton;
    public static final int PERMISSIONS_REQUEST_READ_CONTACTS = 1;
    ImageView img;
    public static final int REQUEST_ID_MULTIPLE_PERMISSIONS = 1;
    @Override
    protected void onStart() {
        super.onStart();
//
        checkAndRequestPermissions();


        if (auth.getCurrentUser() != null) {

            startActivity(new Intent(SignInActivity.this, MainActivity.class));
        }
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



        //for facebook authentication
        FacebookSdk.sdkInitialize(getApplicationContext());

        mAuth = FirebaseAuth.getInstance();

        // Initialize Facebook Login button
        mCallbackManager = CallbackManager.Factory.create();
        loginButton = findViewById(R.id.login_button);


        binding.fb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                binding.loginButton.performClick();

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
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();

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

               if(binding.mobileNo.getText().toString().length() == 10){
                  startActivity(new Intent(getApplicationContext(),MobileVerification.class));
               }

           }

           @Override
           public void afterTextChanged(Editable s) {

           }
       });


    }

    int RC_SIGN_IN = 65;

    private void signIn() {
        progressDialog.show();
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
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
        auth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information

                            progressDialog.dismiss();
                            Log.d("TAG", "signInWithCredential:success");
                            FirebaseUser user = auth.getCurrentUser();
                            //updateUI(user);

//                            Users users=new Users();
//                            users.setUserId(user.getUid());
//                            users.setUserName(user.getDisplayName());
//                            users.setProfilepic(user.getPhotoUrl().toString());
//                            database.getReference().child("Users").child(user.getUid()).setValue(users);


                            //    very important intent all time
                            Intent intent = new Intent(SignInActivity.this, choosePersonalBusiness.class);
                            SignInActivity.this.overridePendingTransition(0, 0);

                            intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                            startActivity(intent);

                            Toast.makeText(SignInActivity.this, "Success", Toast.LENGTH_SHORT).show();

                            String key = FirebaseAuth.getInstance().getUid();
                            String name = Objects.requireNonNull(auth.getCurrentUser()).getDisplayName();
                            String image = String.valueOf(Objects.requireNonNull(auth.getCurrentUser()).getPhotoUrl());

//                            Toast.makeText(SignInActivity.this, "User Id :\n" + key, Toast.LENGTH_SHORT).show();
//                            Toast.makeText(SignInActivity.this, "Name :\n" + name, Toast.LENGTH_SHORT).show();
//                            Toast.makeText(SignInActivity.this, "Name :\n" + image, Toast.LENGTH_SHORT).show();
                            Log.d("url", image);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w("TAG", "signInWithCredential:failure", task.getException());
                            //    updateUI(null);
                        }
                    }
                });
    }


    private void handleFacebookAccessToken(AccessToken token) {
        Log.d("TAG", "handleFacebookAccessToken:" + token);

        AuthCredential credential = FacebookAuthProvider.getCredential(token.getToken());
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d("TAG", "signInWithCredential:success");
                            FirebaseUser user = mAuth.getCurrentUser();

                            //after complete activity

                            String key = FirebaseAuth.getInstance().getUid();
                            String name = Objects.requireNonNull(auth.getCurrentUser()).getDisplayName();
                            String image = String.valueOf(Objects.requireNonNull(auth.getCurrentUser()).getPhotoUrl());

//                            Toast.makeText(SignInActivity.this, "User Id :\n" + key, Toast.LENGTH_SHORT).show();
//                            Toast.makeText(SignInActivity.this, "Name :\n" + name, Toast.LENGTH_SHORT).show();
//                            Toast.makeText(SignInActivity.this, "Name :\n" + image, Toast.LENGTH_SHORT).show();
                          startActivity(new Intent(getApplicationContext(),choosePersonalBusiness.class));
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w("TAG", "signInWithCredential:failure", task.getException());
                            Toast.makeText(getApplicationContext(), "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();


                        }
                    }
                });
    }

    private boolean checkAndRequestPermissions() {

        try {
            int camera = ContextCompat.checkSelfPermission(getApplicationContext(), android.Manifest.permission.CAMERA);
            int storage = ContextCompat.checkSelfPermission(getApplicationContext(), android.Manifest.permission.WRITE_EXTERNAL_STORAGE);
            int contact = ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.READ_CONTACTS);
            int loc = ContextCompat.checkSelfPermission(getApplicationContext(), android.Manifest.permission.ACCESS_COARSE_LOCATION);
            int loc2 = ContextCompat.checkSelfPermission(getApplicationContext(), android.Manifest.permission.ACCESS_FINE_LOCATION);
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
            if (!listPermissionsNeeded.isEmpty()) {
                ActivityCompat.requestPermissions(SignInActivity.this, listPermissionsNeeded.toArray(new String[listPermissionsNeeded.size()]), REQUEST_ID_MULTIPLE_PERMISSIONS);

                return false;
            }
        }catch (Exception ex){
            Log.d("permission", ex.getMessage());
        }
        return true;
    }



}