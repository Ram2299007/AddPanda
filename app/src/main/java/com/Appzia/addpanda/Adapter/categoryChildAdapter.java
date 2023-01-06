package com.Appzia.addpanda.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.Appzia.addpanda.Model.basiceditTrendingModel;
import com.Appzia.addpanda.Model.categoryChildModel;
import com.Appzia.addpanda.R;
import com.Appzia.addpanda.Screens.trendingActivity;
import com.Appzia.addpanda.Util.Constant.Constant;
import com.squareup.picasso.Picasso;

import java.util.List;

public class categoryChildAdapter extends RecyclerView.Adapter<categoryChildAdapter.myViewHolder> {

    Context mContext;
    String BasicKey;
    List<categoryChildModel> childModelList;

    public categoryChildAdapter(Context mContext, List<categoryChildModel> childModelList, String BasicKey) {
        this.mContext = mContext;
        this.childModelList = childModelList;
        this.BasicKey = BasicKey;

    }

    @NonNull
    @Override
    public categoryChildAdapter.myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.category_row_child, parent, false);
        return new myViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull categoryChildAdapter.myViewHolder holder, int position) {
        final categoryChildModel model = childModelList.get(position);

        Picasso.get().load(model.getImage()).into(holder.dispImg);
        holder.dispText.setText(model.getName());


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    Intent intent = new Intent(holder.itemView.getContext(), trendingActivity.class);
                    intent.putExtra("categoryidKey", String.valueOf(model.getCategory_id()));
                    intent.putExtra("categoryNameKey", String.valueOf(model.getCat_name()));
                    intent.putExtra("imageKey", String.valueOf(model.getImage()));
                    intent.putExtra("clickedKey", "clickedKey");
                    intent.putExtra(Constant.BasicKeyMain, BasicKey);
                    intent.putExtra("sub_cat_idKey", String.valueOf(model.getSub_cat_id()));

                    holder.itemView.getContext().startActivity(intent);


            }
        });


    }

    @Override
    public int getItemCount() {
        return childModelList.size();
    }

    public class myViewHolder extends RecyclerView.ViewHolder {


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

