package com.Appzia.addpanda.Adapter;

import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.os.CountDownTimer;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.Appzia.addpanda.Screens.subscriptionActivity;
import com.Appzia.addpanda.Screens.trendingActivity;
import com.Appzia.addpanda.Util.Constant.Constant;
import com.Appzia.addpanda.Model.trendingSubModel;
import com.Appzia.addpanda.R;
import com.google.android.material.snackbar.Snackbar;
import com.squareup.picasso.Picasso;

public class viewAllAdapter extends RecyclerView.Adapter<viewAllAdapter.myViewHolder> {

    Context mContext;
    String BasicKey;
    RelativeLayout relativelayout;

    public viewAllAdapter(Context mContext, String basicKey, RelativeLayout relativelayout) {
        this.mContext = mContext;
        this.BasicKey = basicKey;
        this.relativelayout = relativelayout;


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


            final trendingSubModel model = Constant.viewAllList.get(position);
            try {
                Picasso.get().load(model.getImage()).into(holder.img1);
            }catch (Exception ingored){}
            holder.sub_cat_id.setText(model.getSub_cat_id());
            holder.category_id.setText(model.getCategory_id());
            holder.template_id.setText(model.getTemplate_id());
         //  Toast.makeText(mContext, model.getSub_cat_name(), Toast.LENGTH_SHORT).show();

            holder.subcatname.setText(model.getSub_cat_name());

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



            final Snackbar[] snackbar = new Snackbar[1];
            holder.img1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
//                    if (model.getIs_active() == 0) {
//
//
//                        snackbar[0] = Snackbar
//                                .make(relativelayout, "You need to purchase subscription", snackbar[0].LENGTH_LONG);
//
//                        snackbar[0].show();
//                        new CountDownTimer(2000, 1000) {
//                            @Override
//                            public void onTick(long millisUntilFinished) {
//
//                            }
//
//                            @Override
//                            public void onFinish() {
//                                mContext.startActivity(new Intent(mContext, subscriptionActivity.class));
//                            }
//                        }.start();
//                        //
//                    } else {
//
//
//                    }
//

                    Intent intent = new Intent(holder.img1.getContext(), trendingActivity.class);
                    intent.putExtra("categoryidKey", String.valueOf(model.getCategory_id()));
                    intent.putExtra("imageKey", String.valueOf(model.getImage()));
                    intent.putExtra("sub_cat_idKey", String.valueOf(model.getSub_cat_id()));
                    intent.putExtra("is_activeKey", model.getIs_active());
                    intent.putExtra(Constant.BasicKeyMain, BasicKey);
                    holder.img1.getContext().startActivity(intent);

                }
            });



    }


    @Override
    public int getItemCount() {

        return Constant.viewAllList.size();


    }


    public static class myViewHolder extends RecyclerView.ViewHolder {
        ImageView img1, premium;
        TextView category_id, sub_cat_id, template_id, subcatname;

        public myViewHolder(@NonNull View itemView) {
            super(itemView);
            this.img1 = (ImageView) itemView.findViewById(R.id.img1);
            this.category_id = (TextView) itemView.findViewById(R.id.category_idtrendingfrag);
            this.sub_cat_id = (TextView) itemView.findViewById(R.id.sub_cat_idtrendingfrag);
            this.template_id = (TextView) itemView.findViewById(R.id.template_idtrendingfrag);
            this.subcatname = (TextView) itemView.findViewById(R.id.subcatname);
            this.premium = (ImageView) itemView.findViewById(R.id.premium);


        }
    }
}



