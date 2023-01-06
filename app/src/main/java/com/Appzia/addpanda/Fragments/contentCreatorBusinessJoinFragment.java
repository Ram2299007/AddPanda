package com.Appzia.addpanda.Fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.Appzia.addpanda.R;
import com.Appzia.addpanda.databinding.FragmentContentCreatorBusinessJoinBinding;
import com.google.android.material.snackbar.Snackbar;


public class contentCreatorBusinessJoinFragment extends Fragment {

    FragmentContentCreatorBusinessJoinBinding binding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentContentCreatorBusinessJoinBinding.inflate(inflater, container, false);
        binding.backArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                assert getFragmentManager() != null;
                getFragmentManager().beginTransaction().replace(R.id.mainActivityFrame, new partner_with_usFragment()).commit();
            }
        });
        binding.personal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (binding.name.getText().toString().isEmpty() || binding.mobile.getText().toString().isEmpty() || binding.email.getText().toString().isEmpty() || binding.exp.getText().toString().isEmpty() || binding.specialise.getText().toString().isEmpty() || binding.upload.getText().toString().isEmpty()) {
                    Snackbar.make(binding.getRoot(), "Missing Information ?", Snackbar.LENGTH_LONG)
                            .setAction("CLOSE", new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {

                                }
                            })
                            .setActionTextColor(getResources().getColor(android.R.color.holo_red_light))
                            .show();
                } else {

                    assert getFragmentManager() != null;
                    getFragmentManager().beginTransaction().replace(R.id.mainActivityFrame, new partner_with_usFragment()).commit();
                }
            }
        });
        return binding.getRoot();
    }
}