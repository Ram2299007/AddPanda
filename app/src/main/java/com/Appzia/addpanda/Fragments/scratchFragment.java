package com.Appzia.addpanda.Fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.Appzia.addpanda.R;
import com.Appzia.addpanda.Screens.editFameActivity;
import com.Appzia.addpanda.databinding.FragmentScratchBinding;


public class scratchFragment extends Fragment {
    FragmentScratchBinding binding;


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentScratchBinding.inflate(inflater, container, false);
        // Inflate the layout for this fragment

        binding.backArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                assert getFragmentManager() != null;
                getFragmentManager().beginTransaction().replace(R.id.mainActivityFrame, new createFragment()).commit();
            }
        });
        binding.birthday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

              startActivity(new Intent(getActivity(), editFameActivity.class));
            }
        });
        return binding.getRoot();
    }
}