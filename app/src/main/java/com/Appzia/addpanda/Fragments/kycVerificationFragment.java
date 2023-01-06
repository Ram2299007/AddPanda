package com.Appzia.addpanda.Fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.Appzia.addpanda.R;
import com.Appzia.addpanda.databinding.FragmentKycVerificationBinding;


public class kycVerificationFragment extends Fragment {
    FragmentKycVerificationBinding binding;



    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding=FragmentKycVerificationBinding.inflate(inflater,container,false);
        // Inflate the layout for this fragment

        binding.backArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                assert getFragmentManager() != null;
                getFragmentManager().beginTransaction().replace(R.id.mainActivityFrame,new manageAcount3Fragment()).commit();
            }
        });
        return binding.getRoot();
    }
}