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
import com.Appzia.addpanda.Model.AnniversaryModel;
import com.Appzia.addpanda.R;
import com.Appzia.addpanda.Screens.trendingActivity;
import com.squareup.picasso.Picasso;

public class anniversaryAdapter extends RecyclerView.Adapter<anniversaryAdapter.myViewHolder> {

    Context mContext;

    public anniversaryAdapter(Context mContext) {
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {


        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem = layoutInflater.inflate(R.layout.category_row_child, parent, false);
        return new anniversaryAdapter.myViewHolder(listItem);
    }

    @Override
    public void onBindViewHolder(@NonNull anniversaryAdapter.myViewHolder holder, int position) {
        final AnniversaryModel model = Constant.anniversaryList.get(position);

        try {
            Picasso.get().load(model.getImage()).into(holder.dispImg);
            holder.dispText.setText(model.getName());


            holder.dispImg.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {


                    //  Toast.makeText(mContext, "test", Toast.LENGTH_SHORT).show();


                    Intent intent = new Intent(holder.dispText.getContext(), trendingActivity.class);
                    intent.putExtra("nameKey", String.valueOf(model.getName()));
                    intent.putExtra("categoryidKey", String.valueOf(model.getCategory_id()));
                    intent.putExtra("imageKey", String.valueOf(model.getImage()));
                    intent.putExtra("sub_cat_idKey", String.valueOf(model.getSub_cat_id()));
                    holder.dispImg.getContext().startActivity(intent);

                }
            });

        } catch (Exception ignored) {
        }

    }


    @Override
    public int getItemCount() {
        return Constant.anniversaryList.size();
    }

    public static class myViewHolder extends RecyclerView.ViewHolder {

        ImageView dispImg;
        TextView dispText, category_image, category_id;

        public myViewHolder(@NonNull View itemView) {
            super(itemView);

            dispImg = itemView.findViewById(R.id.dispImg);
            dispText = itemView.findViewById(R.id.dispText);

            category_image = itemView.findViewById(R.id.category_image);
            category_id = itemView.findViewById(R.id.category_id);


        }
    }


}
