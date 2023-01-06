package com.Appzia.addpanda.Fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.text.SpannableString;
import android.text.style.UnderlineSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.Appzia.addpanda.R;
import com.Appzia.addpanda.databinding.FragmentManageAcount3Binding;


public class manageAcount3Fragment extends Fragment {

    FragmentManageAcount3Binding binding;


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentManageAcount3Binding.inflate(inflater,container,false);
        // Inflate the layout for this fragment

        SpannableString content = new SpannableString("View all Transactions >");
        content.setSpan(new UnderlineSpan(), 0, content.length(), 0);
        binding.view.setText(content);

        binding.backArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                assert getFragmentManager() != null;
                getFragmentManager().beginTransaction().replace(R.id.mainActivityFrame,new addBankFragment()).commit();
            }
        });
        return binding.getRoot();
    }
}