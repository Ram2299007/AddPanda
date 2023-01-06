package com.Appzia.addpanda.Adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.Appzia.addpanda.Model.frameModel;
import com.Appzia.addpanda.Screens.Basic_editFrameActivity;
import com.Appzia.addpanda.Screens.editFameActivity;
import com.Appzia.addpanda.Util.Constant.Constant;
import com.Appzia.addpanda.Model.AnniversaryModel;
import com.Appzia.addpanda.R;
import com.Appzia.addpanda.Screens.trendingActivity;
import com.squareup.picasso.Picasso;

import java.lang.annotation.Annotation;
import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

public class frameAdapter extends RecyclerView.Adapter<frameAdapter.myViewHolder> {

    Context mContext;

    public frameAdapter(Context mContext) {
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {


        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem = layoutInflater.inflate(R.layout.editingframe_row_design, parent, false);
        return new frameAdapter.myViewHolder(listItem);
    }

    @Override
    public void onBindViewHolder(@NonNull frameAdapter.myViewHolder holder, int position) {
        final frameModel model = Constant.frameListTwo.get(position);
        final ImageView img = new ImageView(holder.itemView.getContext());

        Picasso.get().load(model.getFrame()).into(img, new com.squareup.picasso.Callback() {
            @Override
            public void onSuccess() {
                holder.frame.setBackgroundDrawable(img.getDrawable());
            }

            @Override
            public void onError(Exception e) {

            }

        });

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(mContext, String.valueOf(model), Toast.LENGTH_SHORT).show();

                Constant.getSfFuncion(holder.itemView.getContext());
                String originalImageKey = Constant.getSF.getString("originalImageKey","");
         //      Toast.makeText(mContext, originalImageKey, Toast.LENGTH_SHORT).show();

                Picasso.get().load(originalImageKey).into(img, new com.squareup.picasso.Callback() {
                    @Override
                    public void onSuccess() {
                        try {
                            editFameActivity.mainLayout.setBackgroundDrawable(img.getDrawable());
                        }catch (Exception ignored){}

                        try {
                            Basic_editFrameActivity.mainLayout.setBackgroundDrawable(img.getDrawable());
                        }catch (Exception ignored){}
                    }

                    @Override
                    public void onError(Exception e) {

                    }

                });






                Picasso.get().load(model.getFrame()).into(img, new com.squareup.picasso.Callback() {
                    @Override
                    public void onSuccess() {

                        try {
                            editFameActivity.mainRelativeLayout.setBackgroundDrawable(img.getDrawable());
                        }catch (Exception ignored){}

                        try {
                            Basic_editFrameActivity.mainRelativeLayout.setBackgroundDrawable(img.getDrawable());
                        }catch (Exception ignored){}
                    }

                    @Override
                    public void onError(Exception e) {

                    }

                });
            }
        });


    }


    @Override
    public int getItemCount() {
        return Constant.frameListTwo.size();
    }

    public static class myViewHolder extends RecyclerView.ViewHolder {

     LinearLayout frame;

        public myViewHolder(@NonNull View itemView) {
            super(itemView);

            frame = itemView.findViewById(R.id.frameTemp);



        }
    }


}
