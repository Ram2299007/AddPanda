package com.Appzia.addpanda.Fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.Appzia.addpanda.R;
import com.Appzia.addpanda.databinding.FragmentEditVisitingCardDetailsBinding;
import com.google.android.material.snackbar.Snackbar;


public class editVisitingCardDetailsFragment extends Fragment {


    FragmentEditVisitingCardDetailsBinding binding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentEditVisitingCardDetailsBinding.inflate(inflater, container, false);

        // Inflate the layout for this fragment


        binding.caption.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(binding.Busname.getText().toString().isEmpty() ||binding.yourname.getText().toString().isEmpty() || binding.email.getText().toString().isEmpty() ||binding.web.getText().toString().isEmpty() ||binding.address.getText().toString().isEmpty() ||binding.mobile.getText().toString().isEmpty() || !binding.checkBox.isChecked() || binding.whatsapp.getText().toString().isEmpty()){
                    Snackbar.make(binding.getRoot(), "Missing Information ?", Snackbar.LENGTH_LONG)
                            .setAction("CLOSE", new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {

                                }
                            })
                            .setActionTextColor(getResources().getColor(android.R.color.holo_red_light))
                            .show();
                }else {
                    assert getFragmentManager() != null;
                    getFragmentManager().beginTransaction().replace(R.id.mainActivityFrame, new addCaptionFragment()).commit();
                }
            }
        });
        return binding.getRoot();
    }
}