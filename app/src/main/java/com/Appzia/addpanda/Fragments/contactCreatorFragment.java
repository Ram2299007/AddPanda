package com.Appzia.addpanda.Fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.Appzia.addpanda.R;
import com.Appzia.addpanda.databinding.FragmentContactCreatorBinding;


public class contactCreatorFragment extends Fragment {

    FragmentContactCreatorBinding binding;
    String contactValue;
    @Override
    public void onStart() {
        super.onStart();


        try{

            assert getArguments() != null;
            contactValue = getArguments().getString("contact");

            if (contactValue.equals("data1")) {


                binding.personal.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        assert getFragmentManager() != null;
                        getFragmentManager().beginTransaction().replace(R.id.mainActivityFrame, new contentCreatorPersonalJoinFragment()).commit();
                    }
                });
            } else if (contactValue.equals("data2")) {

                assert getFragmentManager() != null;
                binding.personal.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        assert getFragmentManager() != null;
                        getFragmentManager().beginTransaction().replace(R.id.mainActivityFrame, new contentCreatorBusinessJoinFragment()).commit();
                    }
                });

            }
            //Toast.makeText(getContext(), contactValue, Toast.LENGTH_SHORT).show();

        }catch(Exception ex){

        }

    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentContactCreatorBinding.inflate(inflater, container, false);

        binding.checkbox.setBackground(null);
        binding.checkbox.setBackgroundResource(R.drawable.check_box_bg);


        binding.backArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                assert getFragmentManager() != null;
                getFragmentManager().beginTransaction().replace(R.id.mainActivityFrame, new partner_with_usFragment()).commit();
            }
        });
        return binding.getRoot();
    }
}