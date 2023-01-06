package com.Appzia.addpanda.Fragments;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatButton;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import androidx.viewpager.widget.ViewPager;

import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.Appzia.addpanda.Adapter.ViewPagerAdapter;
import com.Appzia.addpanda.Adapter.categoryParentAdapter;
import com.Appzia.addpanda.Adapter.mainCatBtnChildAdapter;
import com.Appzia.addpanda.Classes.MainImageViewPager;
import com.Appzia.addpanda.MainActivity;
import com.Appzia.addpanda.Model.categoryChildModel;
import com.Appzia.addpanda.Model.categoryParentModel;
import com.Appzia.addpanda.R;
import com.Appzia.addpanda.Screens.NotificationActivity;
import com.Appzia.addpanda.Screens.createVisingCardHorizontalScreens;
import com.Appzia.addpanda.Screens.partner_with_usActivity;
import com.Appzia.addpanda.Screens.referandearnscreen;
import com.Appzia.addpanda.Screens.showProfileImageScreen;
import com.Appzia.addpanda.Util.Constant.Constant;
import com.Appzia.addpanda.Webservice.Webservice;
import com.Appzia.addpanda.databinding.FragmentMainBinding;
import com.facebook.shimmer.ShimmerFrameLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Timer;


public class mainFragment extends Fragment {

    FragmentMainBinding binding;

    public static ImageView mainImageview;

    public static Context mContext;
    String token;
    public static ShimmerFrameLayout shimmerFrameLayout;


    public static categoryParentAdapter categoryParentAdapter;
    //  public static trendingAdapter adapter2;

    public static TextView username;

    public static CoordinatorLayout CoLayout;
    public static FloatingActionButton whatsapp;

    public static MainImageViewPager mainImageViewPager;


    //declare array lists for categories

    public static ArrayList<categoryParentModel> parentModelArrayList;
    public static ArrayList<categoryChildModel> list1;
    public static ArrayList<categoryChildModel> list2;
    public static ArrayList<categoryChildModel> list3;
    public static ArrayList<categoryChildModel> list4;
    public static ArrayList<categoryChildModel> list5;
    public static ArrayList<categoryChildModel> list6;
    public static ArrayList<categoryChildModel> list7;
    public static ArrayList<categoryChildModel> list8;
    public static ArrayList<categoryChildModel> list9;
    mainCatBtnChildAdapter mainCatBtnChildAdapter;
    public static ArrayList<categoryChildModel> list10;
    public static ArrayList<categoryChildModel> list11;
    public static ArrayList<categoryChildModel> list12;
    public static ArrayList<categoryChildModel> list13;
    public static ArrayList<categoryChildModel> list14;
    public static ArrayList<categoryChildModel> list15;
    public static ArrayList<categoryChildModel> list16;
    public static ArrayList<categoryChildModel> list17;
    public static ArrayList<categoryChildModel> list18;
    public static ArrayList<categoryChildModel> list19;
    public static ArrayList<categoryChildModel> list20;
    public static ArrayList<categoryChildModel> list21;
    public static ArrayList<categoryChildModel> list22;
    public static ArrayList<categoryChildModel> list23;
    public static ArrayList<categoryChildModel> list24;
    public static ArrayList<categoryChildModel> list25;
    public static ArrayList<categoryChildModel> list26;
    public static ArrayList<categoryChildModel> list27;
    public static ArrayList<categoryChildModel> list28;
    public static ArrayList<categoryChildModel> list29;
    public static ArrayList<categoryChildModel> list30;
    public static ArrayList<categoryChildModel> list31;
    public static ArrayList<categoryChildModel> list32;
    public static ArrayList<categoryChildModel> list33;
    public static ArrayList<categoryChildModel> list34;
    public static ArrayList<categoryChildModel> list35;
    public static ArrayList<categoryChildModel> list36;
    public static ArrayList<categoryChildModel> list37;
    public static ArrayList<categoryChildModel> list38;
    public static ArrayList<categoryChildModel> list39;
    public static ArrayList<categoryChildModel> list40;
    public static ArrayList<categoryChildModel> list41;
    public static ArrayList<categoryChildModel> list42;
    public static ArrayList<categoryChildModel> list43;
    public static ArrayList<categoryChildModel> list44;
    public static ArrayList<categoryChildModel> list45;
    public static ArrayList<categoryChildModel> list46;
    public static ArrayList<categoryChildModel> list47;
    public static ArrayList<categoryChildModel> list48;
    public static ArrayList<categoryChildModel> list49;
    public static ArrayList<categoryChildModel> list50;

    public static String loadImgUrlProf;

    Timer timer;
    int page = 0;
    private Handler handler;
    private int delay = 4000; //milliseconds

    Runnable runnable = new Runnable() {
        public void run() {
            if (mainImageViewPager.getCount() == page) {
                page = 0;
            } else {
                page++;
            }
            binding.viewPagerId.setCurrentItem(page, true);
            handler.postDelayed(this, delay);
        }
    };


    @Override
    public void onResume() {
        super.onResume();
        parentModelArrayList.clear();
        list1.clear();
        handler.postDelayed(runnable, delay);


        //  Toast.makeText(mContext,   Constant.getSF.getString(Constant.ACC_TYPE, ""), Toast.LENGTH_SHORT).show();


        username = (TextView) requireActivity().findViewById(R.id.username);

        CoLayout = (CoordinatorLayout) requireActivity().findViewById(R.id.CoLayout);
        whatsapp = (FloatingActionButton) requireActivity().findViewById(R.id.whatsapp);
        shimmerFrameLayout = (ShimmerFrameLayout) requireActivity().findViewById(R.id.shimmerFrameLayout);


        SharedPreferences sh = requireActivity().getSharedPreferences("MySharedPref", Context.MODE_PRIVATE);

        token = sh.getString("TOKEN_SF", "");

        Constant.NetworkCheck(mContext);
        if ((Constant.wifiInfo != null && Constant.wifiInfo.isConnected()) || (Constant.mobileInfo != null && Constant.mobileInfo.isConnected())) {

            Webservice.fetchDataProfile(mContext, token);
            Webservice.get_banner(mContext, token, mainFragment.this);
            Webservice.get_category_list_All(mContext, token, mainFragment.this);




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
    public void onPause() {
        super.onPause();
        handler.removeCallbacks(runnable);

    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        binding = FragmentMainBinding.inflate(inflater, container, false);

        mainImageview = binding.getRoot().findViewById(R.id.mainImageview);


        username = binding.getRoot().findViewById(R.id.username);


        mContext = binding.getRoot().getContext();

        handler = new Handler();


        parentModelArrayList = new ArrayList<>();
        list1 = new ArrayList<>();
        list2 = new ArrayList<>();
        list3 = new ArrayList<>();
        list4 = new ArrayList<>();
        list5 = new ArrayList<>();
        list6 = new ArrayList<>();
        list7 = new ArrayList<>();
        list8 = new ArrayList<>();
        list9 = new ArrayList<>();
        list10 = new ArrayList<>();
        list11 = new ArrayList<>();
        list12 = new ArrayList<>();
        list13 = new ArrayList<>();
        list14 = new ArrayList<>();
        list15 = new ArrayList<>();
        list16 = new ArrayList<>();
        list17 = new ArrayList<>();
        list18 = new ArrayList<>();
        list19 = new ArrayList<>();
        list20 = new ArrayList<>();
        list21 = new ArrayList<>();
        list22 = new ArrayList<>();
        list23 = new ArrayList<>();
        list24 = new ArrayList<>();
        list25 = new ArrayList<>();
        list26 = new ArrayList<>();
        list27 = new ArrayList<>();
        list28 = new ArrayList<>();
        list29 = new ArrayList<>();
        list30 = new ArrayList<>();
        list31 = new ArrayList<>();
        list32 = new ArrayList<>();
        list33 = new ArrayList<>();
        list34 = new ArrayList<>();
        list35 = new ArrayList<>();
        list36 = new ArrayList<>();
        list37 = new ArrayList<>();
        list38 = new ArrayList<>();
        list39 = new ArrayList<>();
        list40 = new ArrayList<>();
        list41 = new ArrayList<>();
        list42 = new ArrayList<>();
        list43 = new ArrayList<>();
        list44 = new ArrayList<>();
        list45 = new ArrayList<>();
        list46 = new ArrayList<>();
        list47 = new ArrayList<>();
        list48 = new ArrayList<>();
        list49 = new ArrayList<>();
        list50 = new ArrayList<>();


        binding.mainImageview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), showProfileImageScreen.class);
                intent.putExtra("ImgKey", loadImgUrlProf);
                startActivity(intent);
            }
        });


        binding.partnerwithus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), partner_with_usActivity.class));
            }
        });

        binding.notification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), NotificationActivity.class));
            }
        });

        binding.whatsapp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String url = "https://wa.me/919145700012";

                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);
            }
        });

        binding.visiitngcard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), createVisingCardHorizontalScreens.class));
            }
        });

        binding.swipeContainer.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                RefreshAndFetchData();
            }
        });

        binding.referearn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), referandearnscreen.class));
            }
        });

        binding.search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (binding.searchEdts.getVisibility() == View.GONE) {

                    binding.username.setVisibility(View.GONE);
                    binding.searchEdts.setVisibility(View.VISIBLE);
                    binding.searchEdts.requestFocus();
                } else if (binding.searchEdts.getVisibility() == View.VISIBLE) {
                    binding.username.setVisibility(View.VISIBLE);
                    binding.searchEdts.setVisibility(View.GONE);
                }
            }
        });

        binding.searchEdts.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {


                filter(String.valueOf(s));
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        return binding.getRoot();
    }

    private void filter(String text) {
        // creating a new array list to filter our data.
        ArrayList<categoryParentModel> filteredlist = new ArrayList<categoryParentModel>();

        // running a for loop to compare elements.
        for (categoryParentModel item : parentModelArrayList) {
            // checking if the entered string matched with any item of our recycler view.
            if (item.getCatname().toLowerCase().contains(text.toLowerCase())) {
                // if the item is matched we are
                // adding it to our filtered list.
                filteredlist.add(item);
            }
        }
        if (filteredlist.isEmpty()) {
            // if no item is added in filtered list we are
            // displaying a toast message as no data found.
            Toast.makeText(mContext, "No Data Found..", Toast.LENGTH_SHORT).show();
        } else {

            categoryParentAdapter.filterList(filteredlist);
        }
    }

    public void setAdapter() {
        categoryParentAdapter = new categoryParentAdapter(mContext, parentModelArrayList, Constant.mainKey);
        binding.trendingRecyclerview.setAdapter(categoryParentAdapter);
        binding.trendingRecyclerview.setHasFixedSize(true);
        binding.trendingRecyclerview.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false));
        categoryParentAdapter.notifyDataSetChanged();


        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_dropdown_item_1line,Constant.categoryNames);
        binding.searchEdts.setAdapter(adapter);


        mainCatBtnChildAdapter = new mainCatBtnChildAdapter(mContext, Constant.mainKey);
        binding.truebuttonRecyclerview.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false));
        binding.truebuttonRecyclerview.setAdapter(mainCatBtnChildAdapter);
        mainCatBtnChildAdapter.notifyDataSetChanged();


    }

    public void RefreshAndFetchData() {

        Intent intent = new Intent(mContext, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        mContext.startActivity(intent);
        Constant.vibrator(mContext);
        binding.swipeContainer.setRefreshing(false);
    }

    public static void getImgUrl(String string) {

        loadImgUrlProf = string;
    }

    public void getBannerAdapter() {

        try {
            mainImageViewPager = new MainImageViewPager(mContext);
            binding.viewPagerId.setAdapter(mainImageViewPager);

            binding.viewPagerId.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                @Override
                public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

                }

                @Override
                public void onPageSelected(int position) {
                    page = position;

                }

                @Override
                public void onPageScrollStateChanged(int state) {

                }
            });
            mainImageViewPager.notifyDataSetChanged();

            binding.dotsIndicator.setViewPager(binding.viewPagerId);


        } catch (Exception ignored) {
        }


    }


}
