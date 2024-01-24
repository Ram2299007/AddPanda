package com.Appzia.addpanda.Adapter;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatButton;
import androidx.recyclerview.widget.RecyclerView;

import com.Appzia.addpanda.Util.Constant.Constant;
import com.Appzia.addpanda.Model.trendingSubModel;
import com.Appzia.addpanda.R;
import com.google.android.material.snackbar.Snackbar;
import com.squareup.picasso.Picasso;

public class trenddingImageAdapter extends RecyclerView.Adapter<trenddingImageAdapter.myViewHolder> {

    Context mContext;
    String result;
    AppCompatButton btn;
    RelativeLayout relativelayout;
    TextView isactivedummy;


    public trenddingImageAdapter(Context mContext, AppCompatButton btn, RelativeLayout relativelayout, TextView isactivedummy) {
        this.mContext = mContext;
        this.btn = btn;
        this.relativelayout = relativelayout;
        this.isactivedummy = isactivedummy;
    }

    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {


        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem = layoutInflater.inflate(R.layout.trending_row, parent, false);
        return new myViewHolder(listItem);
    }

    @Override
    public void onBindViewHolder(@NonNull myViewHolder holder, int position) {

        final trendingSubModel model = Constant.trendingSubList.get(position);

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

        } else {
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


        Picasso.get().load(model.getImage()).into(holder.img1);
        holder.sub_cat_id.setText(model.getSub_cat_id());
        holder.category_id.setText(model.getCategory_id());
        holder.template_id.setText(model.getTemplate_id());

        final Snackbar[] snackbar = new Snackbar[1];
        holder.img1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                if (model.getIs_active() == 0) {
//
//
//                    snackbar[0] = Snackbar
//                            .make(relativelayout, "You need to purchase subscription", snackbar[0].LENGTH_LONG);
//
//                    snackbar[0].show();
//                    new CountDownTimer(2000, 1000) {
//                        @Override
//                        public void onTick(long millisUntilFinished) {
//
//                        }
//
//                        @Override
//                        public void onFinish() {
//                            mContext.startActivity(new Intent(mContext, subscriptionActivity.class));
//                        }
//                    }.start();
//                    //
//                } else {
//
//
//                }
//

                isactivedummy.setText(String.valueOf(model.getIs_active()));
                Constant.setSfFunction(holder.img1.getContext());
                Constant.setSF.putString("originalImageKey", String.valueOf(model.getImage()));
                Constant.setSF.apply();
                Log.d("##test", String.valueOf(model.getImage()));
                SharedPreferences sharedPreferences = holder.img1.getContext().getSharedPreferences("MySharedPref", Context.MODE_PRIVATE);
                SharedPreferences.Editor myEdit = sharedPreferences.edit();
                myEdit.putString("imagekey", String.valueOf(model.getImage()));
                myEdit.putString("sub_cat_idKey", String.valueOf(model.getSub_cat_id()));
                myEdit.putString("category_idKey", String.valueOf(model.getCategory_id()));
                myEdit.putString("template_idKey", String.valueOf(model.getTemplate_id()));
                myEdit.apply();
                btn.performClick();

            }
        });


    }


    @Override
    public int getItemCount() {
        return Constant.trendingSubList.size();
    }


    public static class myViewHolder extends RecyclerView.ViewHolder {
        ImageView img1, premium;
        TextView category_id, sub_cat_id, template_id;

        public myViewHolder(@NonNull View itemView) {
            super(itemView);
            this.img1 = (ImageView) itemView.findViewById(R.id.img1);
            this.category_id = (TextView) itemView.findViewById(R.id.category_idtrendingfrag);
            this.sub_cat_id = (TextView) itemView.findViewById(R.id.sub_cat_idtrendingfrag);
            this.template_id = (TextView) itemView.findViewById(R.id.template_idtrendingfrag);
            this.premium = (ImageView) itemView.findViewById(R.id.premium);


        }
    }
}



