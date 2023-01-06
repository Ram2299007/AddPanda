package com.Appzia.addpanda.Screens;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.Bundle;
import android.os.Environment;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.Appzia.addpanda.MainActivity;
import com.Appzia.addpanda.Model.Contact;
import com.Appzia.addpanda.R;
import com.Appzia.addpanda.Util.Constant.Constant;
import com.Appzia.addpanda.Webservice.Webservice;
import com.Appzia.addpanda.databinding.ActivityReferBinding;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Objects;

public class ReferActivity extends AppCompatActivity {

    ActivityReferBinding binding;
    Context mContext;
    String token;
    String TOKEN_SF;
    String account_typeKey;
    String mobileKey, emailKey, nameKey, BusinessCatKey, social_media_typeKey;

    public static ProgressDialog dialog;

    public static final int PERMISSIONS_REQUEST_READ_CONTACTS = 1;


    public static  final String FILE_NAME="contactNew.json";
    public static String dataNew;






    ArrayList<Contact> contactList = new ArrayList<>();

    private static final String[] PROJECTION = new String[]{
            ContactsContract.CommonDataKinds.Phone.CONTACT_ID,
            ContactsContract.Contacts.DISPLAY_NAME,
            ContactsContract.CommonDataKinds.Phone.NUMBER
    };

    @Override
    protected void onStart() {
        super.onStart();
        try {
            account_typeKey = getIntent().getStringExtra("account_typeKey");

            Toast.makeText(mContext, account_typeKey, Toast.LENGTH_SHORT).show();



            mobileKey = getIntent().getStringExtra("mobileKey");
            emailKey = getIntent().getStringExtra("emailKey");
            nameKey = getIntent().getStringExtra("nameKey");
            BusinessCatKey = getIntent().getStringExtra("BusinessCatKey");
            social_media_typeKey = getIntent().getStringExtra("social_media_typeKey");

            SharedPreferences sh = getSharedPreferences("MySharedPref", Context.MODE_PRIVATE);
            TOKEN_SF = sh.getString("TOKEN_SF", "");

        } catch (Exception ignored) {
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityReferBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        Objects.requireNonNull(getSupportActionBar()).hide();
        Window window = this.getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.setStatusBarColor(this.getResources().getColor(R.color.appThemeColor));

        mContext = this;
        dialog = new ProgressDialog(this);




        try {
            token = getIntent().getStringExtra("tokenKey");

        } catch (Exception ignored) {
        }


        binding.skip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                intent.putExtra("account_typeKey", account_typeKey);
                intent.putExtra("mobileKey", mobileKey);
                intent.putExtra("emailKey", emailKey);
                intent.putExtra("nameKey", nameKey);
                intent.putExtra("BusinessCatKey", BusinessCatKey);
                intent.putExtra("social_media_typeKey", social_media_typeKey);
                intent.putExtra("tokenKey", token);
                startActivity(intent);
            }
        });


        binding.syncContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.setTitle("Please wait");
                dialog.setMessage("Your contact is synchronizing...");
                dialog.show();
                requestContactPermission();
            }
        });

    }




    public void requestContactPermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.READ_CONTACTS)) {
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle("Read Contacts permission");
                builder.setPositiveButton(android.R.string.ok, null);
                builder.setMessage("Please enable access to contacts.");
                builder.setOnDismissListener(new DialogInterface.OnDismissListener() {
                    @Override
                    public void onDismiss(DialogInterface dialog) {
                        requestPermissions(
                                new String[]
                                        {Manifest.permission.READ_CONTACTS}
                                , PERMISSIONS_REQUEST_READ_CONTACTS);
                    }
                });
                builder.show();
            } else {
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.READ_CONTACTS},
                        PERMISSIONS_REQUEST_READ_CONTACTS);
            }
        } else {
            getContactList();
        }
    }


    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == PERMISSIONS_REQUEST_READ_CONTACTS) {
            if (grantResults.length > 0
                    && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                getContactList();
            } else {
                Toast.makeText(this, "You have disabled a contacts permission", Toast.LENGTH_LONG).show();
            }
        }


    }


    private void getContactList() {
        ContentResolver cr = getContentResolver();

        Cursor cursor = cr.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, PROJECTION, null, null, ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME + " ASC");
        if (cursor != null) {
            HashSet<String> mobileNoSet = new HashSet<String>();
            try {
                final int nameIndex = cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME);
                final int numberIndex = cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER);

                String name, number;
                JSONArray arr = new JSONArray();
                while (cursor.moveToNext()) {
                    name = cursor.getString(nameIndex);
                    number = cursor.getString(numberIndex);
                    number = number.replace(" ", "");
                    if (!mobileNoSet.contains(number)) {
                        contactList.add(new Contact(name, number));
                        mobileNoSet.add(number);
//                        Log.d("hvy", "onCreaterrView  Phone Number: name = " + name
//                                + " No = " + number);

                        JSONObject obj2 = new JSONObject();
                        obj2.put("name", name.trim());
                        obj2.put("number", number.replaceAll("[()\\s-]+", "").trim());
                        arr.put(obj2);

                    }
                }

                dataNew  = arr.toString();

              //  save();
                Constant.NetworkCheck(mContext);
                if ((Constant.wifiInfo != null && Constant.wifiInfo.isConnected()) || (Constant.mobileInfo != null && Constant.mobileInfo.isConnected())) {
                    Webservice.upload_user_contact_list(mContext, token, "{ \"contact\":" + dataNew + "}", account_typeKey, mobileKey, emailKey, nameKey, BusinessCatKey, social_media_typeKey);

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



            } catch (JSONException e) {
                e.printStackTrace();
            } finally {
                cursor.close();
            }
        }
    }


   // public static  final String FILE_NAME="contactNew.json";
    public void save() {
        String text = "Sample Data";
        FileOutputStream fos = null;
        try {
            fos = openFileOutput(FILE_NAME,MODE_PRIVATE);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            assert fos != null;
            fos.write(dataNew.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(fos != null){
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        Toast.makeText(mContext, "Saved to "+ getFilesDir() + "/"+FILE_NAME, Toast.LENGTH_SHORT).show();

        Log.d("pathSaved", "Saved to "+ getFilesDir() + "/"+FILE_NAME);
    }


}