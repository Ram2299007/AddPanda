package com.Appzia.addpanda.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.Appzia.addpanda.Util.Constant.Constant;
import com.Appzia.addpanda.Model.trendingModel;
import com.Appzia.addpanda.R;
import com.Appzia.addpanda.Screens.trendingActivity;
import com.squareup.picasso.Picasso;

public class trendingAdapter extends RecyclerView.Adapter<trendingAdapter.myViewholder>{
    Context mContext;

    public trendingAdapter(Context mContext) {
        this.mContext = mContext;
    }
    @NonNull
    @Override
    public trendingAdapter.myViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem = layoutInflater.inflate(R.layout.category_row_child, parent, false);
        return new trendingAdapter.myViewholder(listItem);
    }

    @Override
    public void onBindViewHolder(@NonNull trendingAdapter.myViewholder holder, int position) {
        final trendingModel model = Constant.trendingList.get(position);


        try {
            Picasso.get().load(model.getImage()).into(holder.dispImg);
            holder.dispText.setText(model.getName());


            holder.dispImg.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {




                    Intent intent =  new Intent(holder.dispText.getContext(), trendingActivity.class);
                    intent.putExtra("nameKey", String.valueOf(model.getName()));
                    intent.putExtra("categoryidKey", String.valueOf(model.getCategory_id()));
                    intent.putExtra("imageKey", String.valueOf(model.getImage()));
                    intent.putExtra("sub_cat_idKey", String.valueOf(model.getSubcatid()));
                    holder.dispImg.getContext().startActivity(intent);
                }
            });

        } catch (Exception ignored) {
        }
    }

    @Override
    public int getItemCount() {
        return Constant.trendingList.size();
    }

    public static class myViewholder extends RecyclerView.ViewHolder {

        ImageView dispImg;
        TextView dispText,category_image,category_id,subcatid;


        public myViewholder(@NonNull View itemView) {
            super(itemView);
            dispImg = itemView.findViewById(R.id.dispImg);
            dispText = itemView.findViewById(R.id.dispText);

            category_image = itemView.findViewById(R.id.category_image);
            category_id = itemView.findViewById(R.id.category_id);
            subcatid = itemView.findViewById(R.id.subcatid);

        }
    }

}


