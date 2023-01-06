package com.Appzia.addpanda.Fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.Appzia.addpanda.R;
import com.Appzia.addpanda.databinding.FragmentMyWalletBinding;



public class myWalletFragment extends Fragment {

    FragmentMyWalletBinding binding;


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        binding = FragmentMyWalletBinding.inflate(inflater, container, false);

        binding.backArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                assert getFragmentManager() != null;
                getFragmentManager().beginTransaction().replace(R.id.mainActivityFrame,new profileFragment()).commit();
            }
        });


        return binding.getRoot();
    }
}