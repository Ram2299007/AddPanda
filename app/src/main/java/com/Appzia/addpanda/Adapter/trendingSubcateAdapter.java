package com.Appzia.addpanda.Adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatButton;
import androidx.recyclerview.widget.RecyclerView;

import com.Appzia.addpanda.Model.basiceditTrendingModel;
import com.Appzia.addpanda.Model.categoryChildModel;
import com.Appzia.addpanda.Model.trendingSubModel;
import com.Appzia.addpanda.R;
import com.Appzia.addpanda.Screens.trendingActivity;
import com.Appzia.addpanda.Util.Constant.Constant;
import com.Appzia.addpanda.Webservice.Webservice;
import com.squareup.picasso.Picasso;

import java.util.List;

public class trendingSubcateAdapter extends RecyclerView.Adapter<trendingSubcateAdapter.myViewHolder> {

    Context mContext;
    String basickey;
    List<categoryChildModel> childModelList;
    String token,categoryidKey;
    trendingActivity trendingActivity2;

    public trendingSubcateAdapter(Context mContext, String token,String categoryidKey, trendingActivity trendingActivity2) {
        this.mContext = mContext;
        this.trendingActivity2 = trendingActivity2;
        this.categoryidKey = categoryidKey;
        this.token = token;

    }

    @NonNull
    @Override
    public trendingSubcateAdapter.myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.btnsubcategory_row, parent, false);
        return new myViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull trendingSubcateAdapter.myViewHolder holder, int position) {
        final trendingSubModel model = Constant.trendingListForExtra.get(position);
        Log.d("TAG", "onBindViewHolder: "+model.getSub_cat_name());
        holder.btnCat.setText(model.getSub_cat_name());


        holder.btnCat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clear();
                trendingActivity.sub_cat_id = model.getSub_cat_id();
                Webservice.get_Template_list(mContext, token, categoryidKey, model.getSub_cat_id(), trendingActivity2,"English");

              //  WebserviceRetrofit.get_frame_list(mContext, token, trendingActivity2, categoryidKey, model.getSub_cat_id(), model.getTemplate_id());

            }
        });

    }

    @Override
    public int getItemCount() {
        return Constant.trendingListForExtra.size();
    }

    public static class myViewHolder extends RecyclerView.ViewHolder {

        AppCompatButton btnCat;

        public myViewHolder(@NonNull View itemView) {
            super(itemView);

            btnCat = itemView.findViewById(R.id.btnCat);


        }
    }

    public void clear() {
        Constant.trendingSubList.clear();
        notifyDataSetChanged();
    }
}

