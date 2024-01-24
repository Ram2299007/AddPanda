package com.Appzia.addpanda.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.Appzia.addpanda.Model.downloadModel;
import com.Appzia.addpanda.Screens.MainContentDownloadScreen;
import com.Appzia.addpanda.Util.Constant.Constant;
import com.Appzia.addpanda.R;
import com.squareup.picasso.Picasso;

import java.util.Collection;
import java.util.Collections;

public class downloadAdapter extends RecyclerView.Adapter<downloadAdapter.myViewholder> {
    Context mContext;

    public downloadAdapter(Context mContext) {
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public downloadAdapter.myViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem = layoutInflater.inflate(R.layout.download_row, parent, false);
        return new downloadAdapter.myViewholder(listItem);
    }

    @Override
    public void onBindViewHolder(@NonNull downloadAdapter.myViewholder holder, int position) {

        Collections.reverse(Constant.downloadList);
        final downloadModel model = Constant.downloadList.get(position);


        Picasso.get().load(model.getImage()).into(holder.downloadImg);


        holder.downloadImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent intent = new Intent(mContext, MainContentDownloadScreen.class);
                intent.putExtra("img_url_key", model.getImage());
                mContext.startActivity(intent);


            }
        });

    }

    @Override
    public int getItemCount() {
        return Constant.downloadList.size();
    }

    public static class myViewholder extends RecyclerView.ViewHolder {

        ImageView downloadImg;


        public myViewholder(@NonNull View itemView) {
            super(itemView);
            downloadImg = itemView.findViewById(R.id.downloadImg);


        }
    }

    // Clean all elements of the recycler
    public void clear() {
        Constant.downloadList.clear();
        notifyDataSetChanged();
    }


}


