package com.Appzia.addpanda.Adapter;

import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.recyclerview.widget.RecyclerView;

import com.Appzia.addpanda.Model.categoryModel;
import com.Appzia.addpanda.Screens.viewAll;
import com.Appzia.addpanda.Util.Constant.Constant;
import com.Appzia.addpanda.Model.AnniversaryModel;
import com.Appzia.addpanda.R;
import com.Appzia.addpanda.Screens.trendingActivity;
import com.squareup.picasso.Picasso;

public class categoryAdapter extends RecyclerView.Adapter<categoryAdapter.myViewHolder> {

    Context mContext;

    public categoryAdapter(Context mContext) {
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public categoryAdapter.myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(mContext).inflate(R.layout.category_row_tab2_row, parent, false);
        return new myViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull categoryAdapter.myViewHolder holder, int position) {

        final categoryModel model = Constant.categoryModelList.get(position);

        Picasso.get().load(model.getImage()).into(holder.dispImg);
        holder.dispText.setText(model.getText());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, viewAll.class);
                intent.putExtra("catIdKey",model.getCategory_id());
                intent.putExtra(Constant.BasicKeyMain, Constant.mainKey);
                mContext.startActivity(intent);
            }
        });



    }

    @Override
    public int getItemCount() {
        return Constant.categoryModelList.size();
    }

    public static class myViewHolder extends RecyclerView.ViewHolder {

        AppCompatImageView dispImg;
        TextView dispText;

        public myViewHolder(@NonNull View itemView) {
            super(itemView);
            dispImg = itemView.findViewById(R.id.dispImg);
            dispText = itemView.findViewById(R.id.dispText);

        }
    }

    public void clear() {
        Constant.categoryModelList.clear();
        notifyDataSetChanged();
    }
}
