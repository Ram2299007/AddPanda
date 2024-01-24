package com.Appzia.addpanda.Adapter;

import android.content.Context;
import android.graphics.Paint;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.Appzia.addpanda.Model.get_subscription_listChildModel;
import com.Appzia.addpanda.R;
import com.Appzia.addpanda.Screens.subscriptionActivity;
import com.Appzia.addpanda.Util.Constant.Constant;
import com.Appzia.addpanda.Webservice.WebserviceRetrofits.WebserviceRetrofit;

import java.util.ArrayList;

import pl.droidsonroids.gif.GifImageView;

public class subscriptionAapter extends RecyclerView.Adapter<subscriptionAapter.myViewHoolder> {

    Context mContext;
    WebView webview;
    ScrollView lyt;
    CoordinatorLayout btlyt;
    GifImageView progressbar;
    RecyclerView recyclerview;
    AppCompatActivity activity;
    ArrayList<get_subscription_listChildModel> get_subscription_list = new ArrayList<>();
    catSubscriptionAdapter adapter;
    subscriptionActivity subscriptionActivity;
    int subscriptionSrPos;

    public subscriptionAapter(Context mContext, WebView webview, ScrollView lyt, CoordinatorLayout btlyt, GifImageView progressbar, RecyclerView recyclerview, AppCompatActivity activity, ArrayList<get_subscription_listChildModel> get_subscription_list, subscriptionActivity subscriptionActivity, int postion) {
        this.mContext = mContext;
        this.webview = webview;
        this.lyt = lyt;
        this.btlyt = btlyt;
        this.progressbar = progressbar;
        this.recyclerview = recyclerview;
        this.activity = activity;
        this.get_subscription_list = get_subscription_list;
        this.subscriptionActivity = subscriptionActivity;
        this.subscriptionSrPos = postion;
    }

    @NonNull
    @Override
    public subscriptionAapter.myViewHoolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(mContext).inflate(R.layout.subscription_row, parent, false);
        return new myViewHoolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull subscriptionAapter.myViewHoolder holder, int position) {
        final get_subscription_listChildModel model = get_subscription_list.get(position);

        holder.name.setText(model.getPlan_name());
        holder.recView.smoothScrollToPosition(subscriptionSrPos);
        Log.d("TAG", "srno: " + subscriptionSrPos);

        if (model.getIs_active() == 1) {
            holder.expiry.setVisibility(View.VISIBLE);
            holder.expiry.setText("Existing Plan : Expires on " + model.getExpiry());
            holder.buyNow.setText("ACTIVATED");

        } else {
            holder.expiry.setVisibility(View.INVISIBLE);
            holder.buyNow.setText("BUY NOW");
        }

        holder.strike.setPaintFlags(Paint.STRIKE_THRU_TEXT_FLAG);
        holder.strike.setText(model.getMrp());
        holder.originalPrice.setText(model.getPrice());
        holder.validity.setText(model.getPlan_valiity());

        adapter = new catSubscriptionAdapter(mContext, model.getList());
        holder.recView.setLayoutManager(new LinearLayoutManager(mContext));
        holder.recView.setAdapter(adapter);
        adapter.notifyDataSetChanged();


        // for navigate that particular item position
        //     recyclerview.smoothScrollToPosition(3);

        holder.buyNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (holder.buyNow.getText().toString().equals("ACTIVATED")) {

                } else {
                    Constant.getSfFuncion(mContext.getApplicationContext());
                    WebserviceRetrofit.purchase_subscription(mContext, model.getSubscription_id(), model.getPrice(), webview, lyt, btlyt, progressbar, activity, Constant.getSF.getString(Constant.TOKEN_SF, ""), subscriptionActivity);

                }

            }
        });


    }

    @Override
    public int getItemCount() {
        return get_subscription_list.size();
    }

    public static class myViewHoolder extends RecyclerView.ViewHolder {
        TextView name, strike, originalPrice, validity, expiry;
        AppCompatButton buyNow;
        RecyclerView recView;

        public myViewHoolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name);
            expiry = itemView.findViewById(R.id.expiry);
            validity = itemView.findViewById(R.id.validity);
            buyNow = itemView.findViewById(R.id.buyNow);

            strike = itemView.findViewById(R.id.strike);
            originalPrice = itemView.findViewById(R.id.originalPrice);
            recView = itemView.findViewById(R.id.recView);
        }
    }
}
