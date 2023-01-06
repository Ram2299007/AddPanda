package com.Appzia.addpanda.Fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.Appzia.addpanda.R;
import com.Appzia.addpanda.databinding.FragmentOpenNotiBinding;


public class faksFragment extends Fragment {

    FragmentOpenNotiBinding binding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentOpenNotiBinding.inflate(inflater,container,false);


        binding.backArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                assert getFragmentManager() != null;
                getFragmentManager().beginTransaction().replace(R.id.mainActivityFrame,new settingFragment()).commit();
            }
        });

        binding.faq1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(binding.faq2.getVisibility()==View.GONE){
                    binding.faq2.setVisibility(View.VISIBLE);
                    binding.firstarrow.setImageResource(R.drawable.uparrow);
                    binding.faq1.setBackgroundResource(R.drawable.faq_radius1);
                }else
                if(binding.faq2.getVisibility()==View.VISIBLE){
                    binding.faq2.setVisibility(View.GONE);
                    binding.firstarrow.setImageResource(R.drawable.downarrow);
                    binding.faq1.setBackgroundResource(R.drawable.low_radius_noti);
                }
            }
        });
        // Inflate the layout for this fragment


        return binding.getRoot();
    }
}