package com.Appzia.addpanda.Adapter;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatButton;
import androidx.recyclerview.widget.RecyclerView;

import com.Appzia.addpanda.Screens.trendingActivity;
import com.Appzia.addpanda.Util.Constant.Constant;
import com.Appzia.addpanda.Model.trendingSubModel;
import com.Appzia.addpanda.R;
import com.squareup.picasso.Picasso;

public class trenddingImageAdapter extends RecyclerView.Adapter<trenddingImageAdapter.myViewHolder> {

    Context mContext;
    String result;
    AppCompatButton btn;


    public trenddingImageAdapter(Context mContext, AppCompatButton btn) {
        this.mContext = mContext;
        this.btn = btn;
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


        Picasso.get().load(model.getImage()).into(holder.img1);
        holder.sub_cat_id.setText(model.getSub_cat_id());
        holder.category_id.setText(model.getCategory_id());
        holder.template_id.setText(model.getTemplate_id());




        holder.img1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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
        ImageView img1;
        TextView category_id, sub_cat_id, template_id;

        public myViewHolder(@NonNull View itemView) {
            super(itemView);
            this.img1 = (ImageView) itemView.findViewById(R.id.img1);
            this.category_id = (TextView) itemView.findViewById(R.id.category_idtrendingfrag);
            this.sub_cat_id = (TextView) itemView.findViewById(R.id.sub_cat_idtrendingfrag);
            this.template_id = (TextView) itemView.findViewById(R.id.template_idtrendingfrag);


        }
    }
}



