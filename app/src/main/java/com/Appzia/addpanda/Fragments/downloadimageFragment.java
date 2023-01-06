package com.Appzia.addpanda.Fragments;


import static android.content.Context.MODE_APPEND;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.os.Environment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.Appzia.addpanda.Screens.Basic_editFrameActivity;
import com.Appzia.addpanda.Screens.editFameActivity;
import com.Appzia.addpanda.databinding.FragmentDownloadimageBinding;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Objects;


public class downloadimageFragment extends Fragment {
    FragmentDownloadimageBinding binding;
    String s1,s2;


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentDownloadimageBinding.inflate(inflater, container, false);

        SharedPreferences sh = requireActivity().getSharedPreferences("MySharedPref", Context.MODE_PRIVATE);
         s1 = sh.getString("basicKey", "");


        SharedPreferences sh2 = requireActivity().getSharedPreferences("MySharedPref", Context.MODE_PRIVATE);
        s2 = sh2.getString("editKey", "");





        byte[] byteArray = requireActivity().getIntent().getByteArrayExtra("imgData");
        Bitmap bmp = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);
        Drawable dr = new BitmapDrawable(bmp);
        binding.downloadImg.setBackgroundDrawable(dr);
        binding.backArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(s1.equals("basicKey")){
                    startActivity(new Intent(getActivity(), Basic_editFrameActivity.class));

                    SharedPreferences sharedPreferences = requireActivity().getSharedPreferences("MySharedPref", Context.MODE_PRIVATE);
                    SharedPreferences.Editor myEdit = sharedPreferences.edit();
                    myEdit.putString("editKey", "");
                    myEdit.putString("basicKey", "");
                    myEdit.apply();
                }else if(s2.equals("editKey")){
                    startActivity(new Intent(getActivity(), editFameActivity.class));

                    SharedPreferences sharedPreferences = requireActivity().getSharedPreferences("MySharedPref", Context.MODE_PRIVATE);
                    SharedPreferences.Editor myEdit = sharedPreferences.edit();
                    myEdit.putString("editKey", "");
                    myEdit.putString("basicKey", "");
                    myEdit.apply();
                }


            }
        });

        binding.download.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bitmap bitmap = Bitmap.createBitmap(binding.downloadImg.getWidth(), binding.downloadImg.getHeight(), Bitmap.Config.ARGB_8888);
                Canvas canvas = new Canvas(bitmap);
                binding.downloadImg.draw(canvas);

             //   Drawable dr = new BitmapDrawable(bitmap);


                FileOutputStream outStream = null;

                // Write to SD Card
                try {
                    File sdCard = Environment.getExternalStorageDirectory();
                    File dir = new File(sdCard.getAbsolutePath() + "/DCIM");
                    dir.mkdirs();

                    String fileName = String.format("%d.png", System.currentTimeMillis());
                    File outFile = new File(dir, fileName);

                    outStream = new FileOutputStream(outFile);
                    bitmap.compress(Bitmap.CompressFormat.PNG, 100, outStream);
                    outStream.flush();
                    outStream.close();

                    Log.d("downloadImgLOg", "onPictureTaken - wrote to " + outFile.getAbsolutePath());
                    Toast.makeText(getActivity(), "Download Successful : \n" + fileName, Toast.LENGTH_SHORT).show();

                } catch (FileNotFoundException e) {
//                    print("FNF");
                    Log.d("FileNotFoundException", e.getMessage());
                    e.printStackTrace();
                } catch (IOException e) {
                    Log.d("IOException", e.getMessage());
                } finally {

                }


                // assert getFragmentManager() != null;
                 // getFragmentManager().beginTransaction().replace(R.id.mainActivityFrame,new payment_without_coupan_Fragment()).commit();
            }
        });
        return binding.getRoot();
    }

}