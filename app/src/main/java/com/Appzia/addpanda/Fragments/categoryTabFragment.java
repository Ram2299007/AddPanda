package com.Appzia.addpanda.Fragments;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatButton;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.Appzia.addpanda.Adapter.categoryAdapter;
import com.Appzia.addpanda.MainActivity;
import com.Appzia.addpanda.R;
import com.Appzia.addpanda.Util.Constant.Constant;
import com.Appzia.addpanda.Webservice.Webservice;
import com.Appzia.addpanda.databinding.FragmentCategoryTabBinding;
import com.facebook.shimmer.ShimmerFrameLayout;


public class categoryTabFragment extends Fragment {

    FragmentCategoryTabBinding binding;
    public static CoordinatorLayout CoLayout;
    public static ShimmerFrameLayout shimmerFrameLayout;

    ImageView backArrow;

    Context mContext;
    String token;
    categoryAdapter adapter;

    public static ProgressBar progressBar;


    @Override
    public void onResume() {
        super.onResume();


        CoLayout = (CoordinatorLayout) requireActivity().findViewById(R.id.CoLayout);

        shimmerFrameLayout = (ShimmerFrameLayout) requireActivity().findViewById(R.id.shimmerFrameLayout);
        progressBar = (ProgressBar) requireActivity().findViewById(R.id.progressbar);
        SharedPreferences sh = requireActivity().getSharedPreferences("MySharedPref", Context.MODE_PRIVATE);

        token = sh.getString("TOKEN_SF", "");

        binding.general.performClick();


    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentCategoryTabBinding.inflate(inflater, container, false);

        mContext = binding.getRoot().getContext();

        binding.general.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.general.setBackgroundResource(R.drawable.button_home_bg_orange);
                binding.business.setBackgroundResource(R.drawable.button_home_bg);

                Constant.NetworkCheck(mContext);
                if ((Constant.wifiInfo != null && Constant.wifiInfo.isConnected()) || (Constant.mobileInfo != null && Constant.mobileInfo.isConnected())) {


                    Webservice.get_category_list_display(mContext, token, categoryTabFragment.this, "personal");
                } else {
                    Constant.NetworkCheckDialogue(mContext);
                    Constant.dialogForNetwork.show();

                    AppCompatButton btn = Constant.dialogForNetwork.findViewById(R.id.retry);

                    btn.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Constant.dialogForNetwork.dismiss();

                        }
                    });

                }
            }
        });
        binding.business.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext, "Coming soon...", Toast.LENGTH_SHORT).show();
//                binding.general.setBackgroundResource(R.drawable.button_home_bg);
//                binding.business.setBackgroundResource(R.drawable.button_home_bg_orange);
//
//                Constant.NetworkCheck(mContext);
//                if ((Constant.wifiInfo != null && Constant.wifiInfo.isConnected()) || (Constant.mobileInfo != null && Constant.mobileInfo.isConnected())) {
//
//
//                    Webservice.get_category_list_display(mContext, token, categoryTabFragment.this, "business");
//                } else {
//                    Constant.NetworkCheckDialogue(mContext);
//                    Constant.dialogForNetwork.show();
//
//                    AppCompatButton btn = Constant.dialogForNetwork.findViewById(R.id.retry);
//
//                    btn.setOnClickListener(new View.OnClickListener() {
//                        @Override
//                        public void onClick(View v) {
//                            Constant.dialogForNetwork.dismiss();
//
//                        }
//                    });
//
//                }
            }
        });

        binding.gtranslate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext, "Coming soon....", Toast.LENGTH_SHORT).show();
            }
        });   binding.backArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               startActivity(new Intent(getActivity(),MainActivity.class));
            }
        });




        binding.swipeContainer.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                RefreshAndFetchData();
            }
        });
        return binding.getRoot();
    }

    public void setAdapter() {
        adapter = new categoryAdapter(mContext);
        binding.catRecyclerView.setAdapter(adapter);
        binding.catRecyclerView.setHasFixedSize(true);
        binding.catRecyclerView.setLayoutManager(new GridLayoutManager(mContext, 3));
        adapter.notifyDataSetChanged();

    }

    public void RefreshAndFetchData() {
        //   adapter.clear();
        //onStart();
        Constant.vibrator(mContext);

        adapter.notifyDataSetChanged();
        binding.swipeContainer.setRefreshing(false);


    }
}