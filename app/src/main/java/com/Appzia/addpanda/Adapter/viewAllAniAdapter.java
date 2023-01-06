package com.Appzia.addpanda.Adapter;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatButton;
import androidx.recyclerview.widget.RecyclerView;

import com.Appzia.addpanda.Screens.trendingActivity;
import com.Appzia.addpanda.Util.Constant.Constant;
import com.Appzia.addpanda.Model.trendingSubModel;
import com.Appzia.addpanda.R;
import com.squareup.picasso.Picasso;

public class viewAllAniAdapter extends RecyclerView.Adapter<viewAllAniAdapter.myViewHolder> {

    Context mContext;
    String result;


    public viewAllAniAdapter(Context mContext) {
        this.mContext = mContext;

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


        try {
            final trendingSubModel model2 = Constant.viewAllListAnnni.get(position);

            Picasso.get().load(model2.getImage()).into(holder.img1);
            holder.sub_cat_id.setText(model2.getSub_cat_id());
            holder.category_id.setText(model2.getCategory_id());
            holder.template_id.setText(model2.getTemplate_id());
            holder.subcatname.setText(model2.getSub_cat_name());





            holder.img1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {


                    //  Toast.makeText(mContext, "test", Toast.LENGTH_SHORT).show();


                    Intent intent = new Intent(holder.img1.getContext(), trendingActivity.class);

                    intent.putExtra("categoryidKey", String.valueOf(model2.getCategory_id()));
                    intent.putExtra("imageKey", String.valueOf(model2.getImage()));
                    intent.putExtra("sub_cat_idKey", String.valueOf(model2.getSub_cat_id()));
                    holder.img1.getContext().startActivity(intent);

                }
            });
        } catch (Exception ignored) {
        }


//        holder.img1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
////                Intent intent = new Intent(holder.img1.getContext(), editFameActivity.class);
////                holder.img1.getContext().startActivity(intent);
//                Constant.setSfFunction(holder.img1.getContext());
//                Constant.setSF.putString("originalImageKey",String.valueOf(model.getImage()));
//                Constant.setSF.apply();
//
//                Log.d("##test", String.valueOf(model.getImage()));
//
//                SharedPreferences sharedPreferences = holder.img1.getContext().getSharedPreferences("MySharedPref", Context.MODE_PRIVATE);
//
//                SharedPreferences.Editor myEdit = sharedPreferences.edit();
//
//                myEdit.putString("imagekey", String.valueOf(model.getImage()));
//                myEdit.putString("sub_cat_idKey", String.valueOf(model.getSub_cat_id()));
//                myEdit.putString("category_idKey", String.valueOf(model.getCategory_id()));
//                myEdit.putString("template_idKey", String.valueOf(model.getTemplate_id()));
//                myEdit.apply();
//
//
//
//            }
//        });


    }


    @Override
    public int getItemCount() {


        return Constant.viewAllListAnnni.size();

    }


    public static class myViewHolder extends RecyclerView.ViewHolder {
        ImageView img1;
        TextView category_id, sub_cat_id, template_id,subcatname;

        public myViewHolder(@NonNull View itemView) {
            super(itemView);
            this.img1 = (ImageView) itemView.findViewById(R.id.img1);
            this.category_id = (TextView) itemView.findViewById(R.id.category_idtrendingfrag);
            this.sub_cat_id = (TextView) itemView.findViewById(R.id.sub_cat_idtrendingfrag);
            this.template_id = (TextView) itemView.findViewById(R.id.template_idtrendingfrag);
            this.subcatname = (TextView) itemView.findViewById(R.id.subcatname);


        }
    }
}



