package com.Appzia.addpanda.Fragments;

import android.app.ActivityManager;
import android.app.Application;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.Appzia.addpanda.R;
import com.Appzia.addpanda.Screens.SignInActivity;
import com.Appzia.addpanda.databinding.FragmentProfileBinding;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.squareup.picasso.Picasso;

import java.util.Objects;


public class profileFragment extends Fragment {

    FragmentProfileBinding binding;
    private FirebaseAuth mfirebaseAuth;

    String name, profile;
    GoogleSignInClient mGoogleApiClient;


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
        // Inflate the layout for this fragment
        binding = FragmentProfileBinding.inflate(inflater, container, false);
        mfirebaseAuth = FirebaseAuth.getInstance();


        binding.backArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                assert getFragmentManager() != null;
                getFragmentManager().beginTransaction().replace(R.id.mainActivityFrame, new mainFragment()).commit();
            }
        });

        binding.setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                assert getFragmentManager() != null;
                getFragmentManager().beginTransaction().replace(R.id.mainActivityFrame, new settingFragment()).commit();

            }
        });
        binding.partnerwithus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                assert getFragmentManager() != null;
                getFragmentManager().beginTransaction().replace(R.id.mainActivityFrame, new partner_with_usFragment()).commit();

            }
        });

        binding.manageBankAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                assert getFragmentManager() != null;
                getFragmentManager().beginTransaction().replace(R.id.mainActivityFrame, new manageAccountFragment()).commit();
            }
        });

        binding.subscribe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                assert getFragmentManager() != null;
                getFragmentManager().beginTransaction().replace(R.id.mainActivityFrame, new subscriptionFragment()).commit();
            }
        });
        binding.referearn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                assert getFragmentManager() != null;
                getFragmentManager().beginTransaction().replace(R.id.mainActivityFrame, new referAndEarnFragment()).commit();
            }
        });
        binding.visitingCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                assert getFragmentManager() != null;
                getFragmentManager().beginTransaction().replace(R.id.mainActivityFrame, new createVisingCardHorizontalFragment()).commit();
            }
        });


        binding.logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mfirebaseAuth.signOut();
                startActivity(new Intent(getActivity(), SignInActivity.class));
            }
        });

        return binding.getRoot();
    }
}