package com.Appzia.addpanda.Fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.Appzia.addpanda.Classes.MainImageViewPager;
import com.Appzia.addpanda.R;
import com.Appzia.addpanda.databinding.FragmentMainBinding;


public class mainFragment extends Fragment {

    FragmentMainBinding binding;


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentMainBinding.inflate(inflater, container, false);

        //code writing starts from here

        MainImageViewPager adapter = new MainImageViewPager(getContext());
        binding.viewPagerId.setAdapter(adapter);


        binding.tredingId.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                assert getFragmentManager() != null;
                getFragmentManager().beginTransaction().replace(R.id.mainActivityFrame,new trendingFragment()).commit();
            }
        });


        return binding.getRoot();
    }
}