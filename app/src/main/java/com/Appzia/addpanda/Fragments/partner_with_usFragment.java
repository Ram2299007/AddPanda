package com.Appzia.addpanda.Fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.Appzia.addpanda.R;
import com.Appzia.addpanda.databinding.FragmentPartnerWithUsBinding;


public class partner_with_usFragment extends Fragment {

    FragmentPartnerWithUsBinding binding;


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentPartnerWithUsBinding.inflate(inflater, container, false);


        binding.backArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                assert getFragmentManager() != null;
                getFragmentManager().beginTransaction().replace(R.id.mainActivityFrame, new profileFragment()).commit();
            }
        });

        binding.contact1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String value="data1";
                contactCreatorFragment ldf = new contactCreatorFragment();
                Bundle args = new Bundle();
                args.putString( "contact",value);
                ldf.setArguments(args);

                assert getFragmentManager() != null;
                getFragmentManager().beginTransaction().replace(R.id.mainActivityFrame, ldf).commit();
            }
        });
        binding.contact2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String value2="data2";
                contactCreatorFragment ldf2 = new contactCreatorFragment();
                Bundle args = new Bundle();
                args.putString( "contact",value2);
                ldf2.setArguments(args);

                assert getFragmentManager() != null;
                getFragmentManager().beginTransaction().replace(R.id.mainActivityFrame, ldf2).commit();
            }
        });

        return binding.getRoot();
    }
}