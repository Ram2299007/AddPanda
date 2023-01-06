package com.Appzia.addpanda.Fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.Appzia.addpanda.R;
import com.Appzia.addpanda.databinding.FragmentSettingBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.squareup.picasso.Picasso;

import java.util.Objects;


public class settingFragment extends Fragment {

    FragmentSettingBinding binding;

    String name, profile;


    @Override
    public void onStart() {
        super.onStart();


        try {
            //for accessing the name from firebase authentication
            name = String.valueOf(Objects.requireNonNull(FirebaseAuth.getInstance().getCurrentUser()).getDisplayName());
            binding.userNameId.setText(name);


            //for accessing profile image form firebas authetication
            profile = String.valueOf(Objects.requireNonNull(FirebaseAuth.getInstance().getCurrentUser()).getPhotoUrl());
            Picasso.get().load(profile).placeholder(R.drawable.eclips).into(binding.profileId);

        } catch (Exception e) {
        }

    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentSettingBinding.inflate(inflater, container, false);
        // Inflate the layout for this fragment


        binding.backArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                assert getFragmentManager() != null;
                getFragmentManager().beginTransaction().replace(R.id.mainActivityFrame, new profileFragment()).commit();
            }
        });


        binding.aboutus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                assert getFragmentManager() != null;
                getFragmentManager().beginTransaction().replace(R.id.mainActivityFrame, new aboutusFRagment()).commit();
            }
        });

        binding.faq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                assert getFragmentManager() != null;
                getFragmentManager().beginTransaction().replace(R.id.mainActivityFrame, new faksFragment()).commit();
            }
        });


        return binding.getRoot();
    }
}