package com.Appzia.addpanda.Fragments;

import static android.content.Context.MODE_PRIVATE;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.Appzia.addpanda.R;
import com.Appzia.addpanda.Screens.NotificationActivity;
import com.Appzia.addpanda.Screens.SignInActivity;
import com.Appzia.addpanda.Screens.createVisingCardHorizontalScreens;
import com.Appzia.addpanda.Screens.manageAccountScreens;
import com.Appzia.addpanda.Screens.partner_with_usActivity;
import com.Appzia.addpanda.Screens.referandearnscreen;
import com.Appzia.addpanda.Screens.settingScreen;
import com.Appzia.addpanda.Screens.subscriptionActivity;
import com.Appzia.addpanda.Screens.EditFrameOwner;
import com.Appzia.addpanda.Util.Constant.Constant;
import com.Appzia.addpanda.Webservice.Webservice;
import com.Appzia.addpanda.databinding.FragmentProfileBinding;
import com.facebook.shimmer.ShimmerFrameLayout;
import com.google.firebase.auth.FirebaseAuth;


public class profileFragment extends Fragment {

    FragmentProfileBinding binding;
    private FirebaseAuth mfirebaseAuth;
    public static ImageView imageViewProfile;

    Context mContext;
    String token;

    public static LinearLayout profileLinearId;
    public static ShimmerFrameLayout shimmerFrameLayout;

    public static TextView userNameId, subId;


    @Override
    public void onResume() {
        super.onResume();

        profileLinearId = (LinearLayout) requireActivity().findViewById(R.id.profileLinearId);
        shimmerFrameLayout = (ShimmerFrameLayout) requireActivity().findViewById(R.id.shimmerFrameLayout);
        SharedPreferences sh = requireActivity().getSharedPreferences("MySharedPref", Context.MODE_PRIVATE);
        token = sh.getString("TOKEN_SF", "");

        try {
            userNameId = requireActivity().findViewById(R.id.userNameId);
            subId = requireActivity().findViewById(R.id.subId);
            Webservice.fetch_user_profile_SETDATA(mContext, token);


        } catch (Exception ignored) {
        }

    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentProfileBinding.inflate(inflater, container, false);
        mfirebaseAuth = FirebaseAuth.getInstance();
        mContext = binding.getRoot().getContext();
        try {
            userNameId = requireActivity().findViewById(R.id.userNameId);
            subId = requireActivity().findViewById(R.id.subId);

            Constant.NetworkCheck(mContext);
            if ((Constant.wifiInfo != null && Constant.wifiInfo.isConnected()) || (Constant.mobileInfo != null && Constant.mobileInfo.isConnected())) {
                Webservice.fetch_user_profile_SETDATA(mContext, token);
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


        } catch (Exception ignored) {
        }
        imageViewProfile = binding.getRoot().findViewById(R.id.imageViewProfile);

        binding.notification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), NotificationActivity.class));
            }
        });

        binding.backArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                assert getFragmentManager() != null;
                getFragmentManager().beginTransaction().replace(R.id.mainActivityFrame, new mainFragment()).commit();
            }
        });
        binding.userguide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext, "Coming soon....", Toast.LENGTH_SHORT).show();
            }
        });
        binding.editFrame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //  Toast.makeText(mContext, "Coming soon....", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(getActivity(), EditFrameOwner.class));

            }
        });
        binding.personalsocia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext, "Coming soon....", Toast.LENGTH_SHORT).show();
            }
        });

        binding.setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(getActivity(), settingScreen.class));

            }
        });
        binding.partnerwithus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(getActivity(), partner_with_usActivity.class));

            }
        });

        binding.manageBankAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), manageAccountScreens.class));
            }
        });

        binding.subscribe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), subscriptionActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);

            }
        });
        binding.referearn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                assert getFragmentManager() != null;
                startActivity(new Intent(getActivity(), referandearnscreen.class));
            }
        });
//        binding.visitingCard.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                assert getFragmentManager() != null;
//
//            }
//        });

        binding.createdigitalcard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), createVisingCardHorizontalScreens.class));
            }
        });


        binding.logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                new AlertDialog.Builder(mContext).setTitle("Warning").setMessage("Are you sure want to exit?")


                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {

                                SharedPreferences sharedPreferences = requireActivity().getSharedPreferences("MySharedPref", MODE_PRIVATE);
                                SharedPreferences.Editor myEdit = sharedPreferences.edit();
                                myEdit.putString("user_id", "");
                                myEdit.putString("TOKEN_SF", "");
                                myEdit.putString("social_media_type", "");
                                myEdit.putString("VERIFIED_KEY", "");
                                myEdit.putString("VERIFIED_SOCIAL_MEDIA_KEY", "");
                                myEdit.apply();
                                mfirebaseAuth.signOut();
                                startActivity(new Intent(getActivity(), SignInActivity.class));

                            }

                        })

                        .setNegativeButton(android.R.string.no, null).setIcon(null).show();


            }
        });

        binding.profileLinearId.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                binding.profileLinearId.setBackgroundResource(R.drawable.linear_button_color_for_all);

                Webservice.fetch_user_profile(mContext, token);

            }
        });

        return binding.getRoot();
    }
}