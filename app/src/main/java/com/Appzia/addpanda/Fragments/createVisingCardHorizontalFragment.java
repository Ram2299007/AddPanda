package com.Appzia.addpanda.Fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.Appzia.addpanda.R;

import com.Appzia.addpanda.databinding.FragmentCreateVisingCardHoriBinding;


public class createVisingCardHorizontalFragment extends Fragment {

    FragmentCreateVisingCardHoriBinding binding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentCreateVisingCardHoriBinding.inflate(inflater, container, false);
        // Inflate the layout for this fragment

        binding.backArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                assert getFragmentManager() != null;
                getFragmentManager().beginTransaction().replace(R.id.mainActivityFrame, new profileFragment()).commit();
            }
        });
        binding.horizonatlView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                assert getFragmentManager() != null;
                getFragmentManager().beginTransaction().replace(R.id.mainActivityFrame, new createVisingCardHorizontalFragment()).commit();
            }
        });

        binding.verticalView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                assert getFragmentManager() != null;
                getFragmentManager().beginTransaction().replace(R.id.mainActivityFrame, new createVisingCardVerticalFragment()).commit();
            }
        });
        binding.horirow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                assert getFragmentManager() != null;
                getFragmentManager().beginTransaction().replace(R.id.mainActivityFrame, new editVisitingCardFragment()).commit();
            }
        });
        return binding.getRoot();
    }
}