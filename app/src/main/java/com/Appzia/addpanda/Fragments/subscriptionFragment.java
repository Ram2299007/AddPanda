package com.Appzia.addpanda.Fragments;

import android.graphics.Paint;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.Appzia.addpanda.R;
import com.Appzia.addpanda.databinding.FragmentSubscriptionBinding;


public class subscriptionFragment extends Fragment {

    FragmentSubscriptionBinding binding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding=FragmentSubscriptionBinding.inflate(inflater,container,false);

        binding.strike.setPaintFlags(Paint.STRIKE_THRU_TEXT_FLAG);

        binding.backArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                assert getFragmentManager() != null;
                getFragmentManager().beginTransaction().replace(R.id.mainActivityFrame,new profileFragment()).commit();
            }
        });
        // Inflate the layout for this fragment
        return binding.getRoot();


    }
}