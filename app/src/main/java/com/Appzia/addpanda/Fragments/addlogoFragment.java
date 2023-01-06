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
import com.Appzia.addpanda.databinding.FragmentAddlogoBinding;

public class addlogoFragment extends Fragment {

    FragmentAddlogoBinding binding;
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentAddlogoBinding.inflate(inflater,container,false);

        binding.cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                assert getFragmentManager() != null;
                startActivity(new Intent(getActivity(), editFameActivity.class));
            }
        });


        binding.done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                assert getFragmentManager() != null;
                startActivity(new Intent(getActivity(), editFameActivity.class));


            }
        });

        return binding.getRoot();
    }
}