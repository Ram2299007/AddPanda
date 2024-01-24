package com.Appzia.addpanda.Adapter;

import android.content.Context;
import android.content.Intent;
import android.os.CountDownTimer;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.Appzia.addpanda.Model.get_category_listChild2Model;
import com.Appzia.addpanda.R;
import com.Appzia.addpanda.Screens.subscriptionActivity;
import com.Appzia.addpanda.Screens.trendingActivity;
import com.Appzia.addpanda.Util.Constant.Constant;
import com.google.android.material.snackbar.Snackbar;
import com.squareup.picasso.Picasso;

import java.util.List;

public class categoryChildAdapter extends RecyclerView.Adapter<categoryChildAdapter.myViewHolder> {

    Context mContext;
    String BasicKey;
    List<get_category_listChild2Model> get_category_listChild2ModelList;
    String categoryId;
    RelativeLayout relativelayout;

    public categoryChildAdapter(Context mContext, List<get_category_listChild2Model> get_category_listChild2ModelList, String BasicKey, String categoryId, RelativeLayout relativelayout) {
        this.mContext = mContext;
        this.get_category_listChild2ModelList = get_category_listChild2ModelList;
        this.BasicKey = BasicKey;
        this.categoryId = categoryId;

        this.relativelayout = relativelayout;

    }

    @NonNull
    @Override
    public categoryChildAdapter.myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.category_row_child, parent, false);
        return new myViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull categoryChildAdapter.myViewHolder holder, int position) {
        final get_category_listChild2Model model = get_category_listChild2ModelList.get(position);

        if (model.getIs_active() == 0) {
            int widthInDp = 15;
            int heightInDp = 15;

            int widthInPixels = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, widthInDp, mContext.getResources().getDisplayMetrics());
            int heightInPixels = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, heightInDp, mContext.getResources().getDisplayMetrics());

            ViewGroup.LayoutParams layoutParams = holder.premium.getLayoutParams();
            layoutParams.width = widthInPixels;
            layoutParams.height = heightInPixels;
            holder.premium.setLayoutParams(layoutParams);
            holder.premium.setVisibility(View.VISIBLE);
            holder.premium.setImageResource(R.drawable.diamond);

        }else{
            int widthInDp = 27;
            int heightInDp = 10;

            int widthInPixels = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, widthInDp, mContext.getResources().getDisplayMetrics());
            int heightInPixels = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, heightInDp, mContext.getResources().getDisplayMetrics());

            ViewGroup.LayoutParams layoutParams = holder.premium.getLayoutParams();
            layoutParams.width = widthInPixels;
            layoutParams.height = heightInPixels;
            holder.premium.setLayoutParams(layoutParams);
            holder.premium.setVisibility(View.VISIBLE);
            holder.premium.setImageResource(R.drawable.free_svg);
        }

        //  Toast.makeText(mContext, "child", Toast.LENGTH_SHORT).show();
        Picasso.get().load(model.getImage()).into(holder.dispImg);
        holder.dispText.setText(model.getSub_cat_name());

        final Snackbar[] snackbar = new Snackbar[1];
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//

                Intent intent = new Intent(holder.itemView.getContext(), trendingActivity.class);
                intent.putExtra("categoryidKey", categoryId);
                intent.putExtra("categoryNameKey", String.valueOf(model.getSub_cat_name()));
                intent.putExtra("imageKey", String.valueOf(model.getImage()));
                intent.putExtra("clickedKey", "clickedKey");
                intent.putExtra("is_activeKey", model.getIs_active());
                intent.putExtra(Constant.BasicKeyMain, BasicKey);
                intent.putExtra("sub_cat_idKey", String.valueOf(model.getSub_cat_id()));

                holder.itemView.getContext().startActivity(intent);


            }
        });


    }

    @Override
    public int getItemCount() {
        return get_category_listChild2ModelList.size();
    }

    public class myViewHolder extends RecyclerView.ViewHolder {


        ImageView dispImg, premium;
        TextView dispText, category_image, category_id;

        public myViewHolder(@NonNull View itemView) {
            super(itemView);

            dispImg = itemView.findViewById(R.id.dispImg);
            dispText = itemView.findViewById(R.id.dispText);

            category_image = itemView.findViewById(R.id.category_image);
            category_id = itemView.findViewById(R.id.category_id);
            premium = itemView.findViewById(R.id.premium);


        }
    }
}

