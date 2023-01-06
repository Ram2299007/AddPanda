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
import androidx.recyclerview.widget.RecyclerView;

import com.Appzia.addpanda.Model.downloadModel;
import com.Appzia.addpanda.Model.notiModel;
import com.Appzia.addpanda.Util.Constant.Constant;
import com.Appzia.addpanda.Model.trendingModel;
import com.Appzia.addpanda.R;
import com.Appzia.addpanda.Screens.trendingActivity;
import com.squareup.picasso.Picasso;

public class notiAdapter extends RecyclerView.Adapter<notiAdapter.myViewholder> {
    Context mContext;

    public notiAdapter(Context mContext) {
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public notiAdapter.myViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem = layoutInflater.inflate(R.layout.notification_row_layout, parent, false);
        return new notiAdapter.myViewholder(listItem);
    }

    @Override
    public void onBindViewHolder(@NonNull notiAdapter.myViewholder holder, int position) {
        final notiModel model = Constant.notiList.get(position);


        try {
            Picasso.get().load(model.getNotiImg()).placeholder(R.drawable.notiicon).into(holder.notiImg);
        }catch (Exception ignored){}
        holder.notiText.setText(model.getNotiText());
        holder.notiTime.setText(model.getNotiTime());

    }

    @Override
    public int getItemCount() {
        return Constant.notiList.size();
    }

    public static class myViewholder extends RecyclerView.ViewHolder {

        ImageView notiImg;
        TextView notiText, notiTime;


        public myViewholder(@NonNull View itemView) {
            super(itemView);
            notiImg = itemView.findViewById(R.id.notiImg);
            notiText = itemView.findViewById(R.id.notiText);
            notiTime = itemView.findViewById(R.id.notiTime);


        }
    }

}


