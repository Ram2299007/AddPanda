package com.Appzia.addpanda.Util.Constant;

import static android.content.Context.MODE_PRIVATE;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Vibrator;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.Transformation;
import android.widget.LinearLayout;

import androidx.appcompat.widget.AppCompatButton;
import androidx.coordinatorlayout.widget.CoordinatorLayout;

import com.Appzia.addpanda.Model.AnniversaryModel;
import com.Appzia.addpanda.Model.basiceditTrendingModel;
import com.Appzia.addpanda.Model.categoryChildModel;
import com.Appzia.addpanda.Model.categoryModel;
import com.Appzia.addpanda.Model.categoryParentModel;
import com.Appzia.addpanda.Model.downloadModel;
import com.Appzia.addpanda.Model.faqModel;
import com.Appzia.addpanda.Model.frameModel;
import com.Appzia.addpanda.Model.get_category_listChild1Model;
import com.Appzia.addpanda.Model.get_subscription_listChildModel;
import com.Appzia.addpanda.Model.horiVisitingModel;
import com.Appzia.addpanda.Model.mainCatBtnChildModel;
import com.Appzia.addpanda.Model.notiModel;
import com.Appzia.addpanda.Model.scratcheditAniversaryModel;
import com.Appzia.addpanda.Model.scratcheditTrendingModel;
import com.Appzia.addpanda.Model.trendingModel;
import com.Appzia.addpanda.Model.trendingSubModel;
import com.Appzia.addpanda.Model.viewPagerModel;
import com.Appzia.addpanda.R;

import java.util.ArrayList;

public class Constant {


    public static SharedPreferences.Editor setSF;
    public static SharedPreferences getSF;
    public static NetworkInfo wifiInfo, mobileInfo;

    public static AppCompatButton btn;
    public static Dialog dialogForNetwork;
    public static Dialog DialogueGlobal;
    public static ConnectivityManager connectivityManager;

    public static ArrayList<String> categoryNames = new ArrayList<>();
    public static ArrayList<AnniversaryModel> anniversaryList = new ArrayList<>();
    public static ArrayList<faqModel> faqList = new ArrayList<>();
    public static ArrayList<trendingModel> trendingList = new ArrayList<>();
    public static ArrayList<trendingSubModel> trendingSubList = new ArrayList<>();
    public static ArrayList<trendingSubModel> viewAllList = new ArrayList<>();
    public static ArrayList<trendingSubModel> trendingListForExtra = new ArrayList<>();
    public static ArrayList<trendingSubModel> viewAllListAnnni = new ArrayList<>();
    public static ArrayList<basiceditTrendingModel> basiceditTrendingModelSubList = new ArrayList<>();
    public static ArrayList<scratcheditTrendingModel> scratcheditTrendingModelSubList = new ArrayList<>();
    public static ArrayList<scratcheditAniversaryModel> scratcheditAniversaryModelSubList = new ArrayList<>();
    public static ArrayList<frameModel> frameList = new ArrayList<>();
    public static ArrayList<frameModel> frameListTwo = new ArrayList<>();
    public static ArrayList<get_subscription_listChildModel> get_subscription_list = new ArrayList<>();
    public static ArrayList<get_category_listChild1Model> get_category_listChild1ModelList = new ArrayList<>();

    public static ArrayList<downloadModel> downloadList = new ArrayList<>();
    public static ArrayList<downloadModel> visitingCardAdapterList = new ArrayList<>();
    public static ArrayList<downloadModel> VisitingdownloadList = new ArrayList<>();
    public static ArrayList<downloadModel> visitingcardList = new ArrayList<>();
    public static ArrayList<mainCatBtnChildModel> mainCatBtnModelList = new ArrayList<>();
    public static ArrayList<notiModel> notiList = new ArrayList<>();
    public static ArrayList<horiVisitingModel> horiVisitingList = new ArrayList<>();
    public static ArrayList<categoryChildModel> categoryChildModelList = new ArrayList<>();
    public static ArrayList<categoryChildModel> categoryChildModelList2 = new ArrayList<>();
    public static ArrayList<categoryParentModel> ccategoryParentModelList = new ArrayList<>();
    public static ArrayList<viewPagerModel> viewpager = new ArrayList<>();
    public static ObjectAnimator alphaAnimator;
    public static ObjectAnimator translationYAnimator;
    public static AnimatorSet animatorSet;

    public static String mainKey = "mainKey";
    public static String basicKey = "basicKey";
    public static String scratchKey = "scratchKey";
    public static String BasicKeyMain = "BasicKey";
    public static String ACC_TYPE = "ACC_TYPE_KEY";
    public static String TOKEN_SF = "TOKEN_SF";
    public static ArrayList<categoryModel> categoryModelList = new ArrayList<>();


    public static void setSfFunction(Context context) {

        SharedPreferences sharedPreferences = context.getSharedPreferences("MySharedPref", MODE_PRIVATE);
        setSF = sharedPreferences.edit();
    }

    public static void getSfFuncion(Context context) {
        getSF = context.getSharedPreferences("MySharedPref", MODE_PRIVATE);
    }

    public static void vibrator(Context mContext) {
        Vibrator vibrator = (Vibrator) mContext.getSystemService(Context.VIBRATOR_SERVICE);
        vibrator.vibrate(100);
    }


    public static void NetworkCheck(Context mContext) {
        connectivityManager = (ConnectivityManager) mContext.getSystemService(Context.CONNECTIVITY_SERVICE);
        wifiInfo = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        mobileInfo = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);

    }


    public static void NetworkCheckDialogue(Context mContext) {

        dialogForNetwork = new Dialog(mContext);
        dialogForNetwork.setContentView(R.layout.nwtwork_dialogue);
        dialogForNetwork.setCanceledOnTouchOutside(false);
        dialogForNetwork.setCancelable(false);
        dialogForNetwork.getWindow().getAttributes().windowAnimations = R.style.PauseDialogAnimation;

        dialogForNetwork.getWindow().setGravity(Gravity.CENTER);
        dialogForNetwork.getWindow().setBackgroundDrawable(null);

        Window window = dialogForNetwork.getWindow();
        window.setLayout(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);

    }


    public static void DialogueGlobal(Context mContext, int layout) {

        DialogueGlobal = new Dialog(mContext);
        DialogueGlobal.setContentView(layout);
        DialogueGlobal.setCanceledOnTouchOutside(true);
        DialogueGlobal.setCancelable(true);
        DialogueGlobal.getWindow().getAttributes().windowAnimations = R.style.PauseDialogAnimation;

        DialogueGlobal.getWindow().setGravity(Gravity.CENTER);
        DialogueGlobal.getWindow().setBackgroundDrawable(null);

        Window window = DialogueGlobal.getWindow();
        window.setLayout(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);

    }


    public static void viewGoneAnimator(final View view) {

        view.animate()
                .alpha(0f)
                .setDuration(1500)
                .setListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        view.setVisibility(View.GONE);
                    }
                });

    }

    public static void viewVisibleAnimator(final View view) {

        view.animate()
                .alpha(1f)
                .setDuration(0)
                .setListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        view.setVisibility(View.VISIBLE);
                    }
                });

    }


}
