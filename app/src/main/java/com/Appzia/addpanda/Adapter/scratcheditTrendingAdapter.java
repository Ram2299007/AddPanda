package com.Appzia.addpanda.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.Appzia.addpanda.Util.Constant.Constant;
import com.Appzia.addpanda.Model.scratcheditTrendingModel;
import com.Appzia.addpanda.R;
import com.squareup.picasso.Picasso;

public class scratcheditTrendingAdapter extends RecyclerView.Adapter<scratcheditTrendingAdapter.myViewHolder> {

    Context mContext;

    public scratcheditTrendingAdapter(Context mContext) {
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {


        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem = layoutInflater.inflate(R.layout.category_row_child, parent, false);
        return new scratcheditTrendingAdapter.myViewHolder(listItem);
    }

    @Override
    public void onBindViewHolder(@NonNull scratcheditTrendingAdapter.myViewHolder holder, int position) {
        final scratcheditTrendingModel model = Constant.scratcheditTrendingModelSubList.get(position);


        Picasso.get().load(model.getImage()).into(holder.dispImg);
        holder.dispText.setText(model.getName());


    }


    @Override
    public int getItemCount() {
        return Constant.scratcheditTrendingModelSubList.size();
    }

    public static class myViewHolder extends RecyclerView.ViewHolder {

        ImageView dispImg;
        TextView dispText,category_image,category_id;

        public myViewHolder(@NonNull View itemView) {
            super(itemView);

            dispImg = itemView.findViewById(R.id.dispImg);
            dispText = itemView.findViewById(R.id.dispText);

            category_image = itemView.findViewById(R.id.category_image);
            category_id = itemView.findViewById(R.id.category_id);


        }
    }


}
