package com.Appzia.addpanda.Fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.Appzia.addpanda.Screens.editFameActivity;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.Appzia.addpanda.Adapter.trenddingImageAdapter;
import com.Appzia.addpanda.Classes.TreendingImageViewPager;
import com.Appzia.addpanda.Model.trendingImageModel;
import com.Appzia.addpanda.R;
import com.Appzia.addpanda.databinding.FragmentTrendingBinding;

import me.zhanghai.android.fastscroll.FastScrollerBuilder;


public class trendingFragment extends Fragment {

    FragmentTrendingBinding binding;
    trenddingImageAdapter adapter;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding =FragmentTrendingBinding.inflate(inflater,container,false);

        TreendingImageViewPager adapterView = new TreendingImageViewPager(getContext());
        binding.viewPagerId.setAdapter(adapterView);


        binding.backArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                assert getFragmentManager() != null;
                getFragmentManager().beginTransaction().replace(R.id.mainActivityFrame,new mainFragment()).commit();
            }
        });

        trendingImageModel[] model = new trendingImageModel[]{

                new trendingImageModel(R.drawable.img1,R.drawable.img2,R.drawable.flag3),
                new trendingImageModel(R.drawable.india,R.drawable.tirangachkara,R.drawable.default_frame),
                new trendingImageModel(R.drawable.flag1,R.drawable.flag2,R.drawable.img1),



        };



        adapter = new trenddingImageAdapter(model);
        binding.recyclerview.setHasFixedSize(true);
        binding.recyclerview.setLayoutManager(new LinearLayoutManager(getContext()));

        binding.recyclerview.setAdapter(adapter);



//        binding.edit.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                assert getFragmentManager() != null;
////                getFragmentManager().beginTransaction().replace(R.id.mainActivityFrame,new editFragment()).commit();
//                startActivity(new Intent(getActivity(),editFameActivity.class));
//
//            }
//        });

        binding.gtranslate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //  startActivity(new Intent(getApplicationContext(),EditActivity.class));
            }
        });

        binding.download.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // startActivity(new Intent(getApplicationContext(),EditActivity.class));
            }
        });



        return binding.getRoot();
    }
}