package com.Appzia.addpanda.Fragments;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatButton;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.Appzia.addpanda.Adapter.downloadAdapter;
import com.Appzia.addpanda.Adapter.visitingAdapter;
import com.Appzia.addpanda.R;
import com.Appzia.addpanda.Screens.NotificationActivity;
import com.Appzia.addpanda.Util.Constant.Constant;
import com.Appzia.addpanda.Webservice.Webservice;
import com.Appzia.addpanda.databinding.FragmentDownloadsBinding;
import com.facebook.shimmer.ShimmerFrameLayout;


public class DownloadsFragment extends Fragment {

    FragmentDownloadsBinding binding;

    public static Context mContext;
    public static RecyclerView downloadRecyclerview;

    //for recyclerview
    public static downloadAdapter adapter;
    public static visitingAdapter visitingAdapter;
    public static GridLayoutManager layoutManager;
    public static CoordinatorLayout CoLayout;
    public static ShimmerFrameLayout shimmerFrameLayout;
    String token;


    @Override
    public void onResume() {
        super.onResume();

        CoLayout = (CoordinatorLayout) requireActivity().findViewById(R.id.CoLayout2);

        shimmerFrameLayout = (ShimmerFrameLayout) requireActivity().findViewById(R.id.shimmerFrameLayout2);

        SharedPreferences sh = requireActivity().getSharedPreferences("MySharedPref", Context.MODE_PRIVATE);

        token = sh.getString("TOKEN_SF", "");
        downloadRecyclerview = (RecyclerView) requireActivity().findViewById(R.id.downloadRecyclerview);
        //progress bar declaration
        Constant.NetworkCheck(mContext);
        if ((Constant.wifiInfo != null && Constant.wifiInfo.isConnected()) || (Constant.mobileInfo != null && Constant.mobileInfo.isConnected())) {

            Webservice.get_my_content_creator_list(mContext, token);
            //   WebserviceRetrofit.get_my_creating_visiting_card_list(mContext, token);
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

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentDownloadsBinding.inflate(inflater, container, false);
        mContext = binding.getRoot().getContext();


        binding.backArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                assert getFragmentManager() != null;
                getFragmentManager().beginTransaction().replace(R.id.mainActivityFrame, new mainFragment()).commit();
            }
        });
        binding.video.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext, "Coming soon....", Toast.LENGTH_SHORT).show();
            }
        });

        binding.download.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), NotificationActivity.class));
            }
        });

        binding.swipeContainer.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                RefreshAndFetchData();

            }
        });

        binding.visitingId.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.tmpId.setBackgroundResource(R.drawable.button_home_bg);
                binding.visitingId.setBackgroundResource(R.drawable.button_bg_hover_2);
                if ((Constant.wifiInfo != null && Constant.wifiInfo.isConnected()) || (Constant.mobileInfo != null && Constant.mobileInfo.isConnected())) {

                    Webservice.get_my_creating_visiting_card_list(mContext, token);
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

        binding.tmpId.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.tmpId.setBackgroundResource(R.drawable.button_bg_hover_2);
                binding.visitingId.setBackgroundResource(R.drawable.button_home_bg);
                if ((Constant.wifiInfo != null && Constant.wifiInfo.isConnected()) || (Constant.mobileInfo != null && Constant.mobileInfo.isConnected())) {

                    Webservice.get_my_content_creator_list(mContext, token);
                    //  WebserviceRetrofit.get_my_creating_visiting_card_list(mContext, token);
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

        return binding.getRoot();
    }

    public static void setAdapter() {

        adapter = new downloadAdapter(mContext);
        layoutManager = new GridLayoutManager(mContext, 2);
        downloadRecyclerview.setLayoutManager(layoutManager);
        downloadRecyclerview.setHasFixedSize(true);
        downloadRecyclerview.setAdapter(adapter);
        adapter.notifyDataSetChanged();

    }

    public static void visitingCardAdapter() {

        visitingAdapter = new visitingAdapter(mContext);
        layoutManager = new GridLayoutManager(mContext, 2);
        downloadRecyclerview.setLayoutManager(layoutManager);
        downloadRecyclerview.setHasFixedSize(true);
        downloadRecyclerview.setAdapter(visitingAdapter);
        visitingAdapter.notifyDataSetChanged();

    }

    public void RefreshAndFetchData() {
        binding.tmpId.setBackgroundResource(R.drawable.button_bg_hover_2);
        binding.visitingId.setBackgroundResource(R.drawable.button_home_bg);

        Constant.vibrator(mContext);

        try {
            onResume();
        } catch (Exception ignored) {
        }
        binding.swipeContainer.setRefreshing(false);
    }


}