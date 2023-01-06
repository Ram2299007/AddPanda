package com.Appzia.addpanda.Adapter;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.Appzia.addpanda.Model.trendingImageModel;
import com.Appzia.addpanda.R;
import com.Appzia.addpanda.Screens.editFameActivity;

public class trenddingImageAdapter extends RecyclerView.Adapter<trenddingImageAdapter.myViewHolder> {

    private final trendingImageModel[] listdata;

    public trenddingImageAdapter(trendingImageModel[] listdata) {
        this.listdata = listdata;
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

        final trendingImageModel model = listdata[position];

        holder.img1.setImageResource(model.getImg1());
        holder.img2.setImageResource(model.getImg2());
        holder.img3.setImageResource(model.getimg3());


        holder.img1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(holder.img1.getContext(), editFameActivity.class);
                holder.img1.getContext().startActivity(intent);

                SharedPreferences sharedPreferences = holder.img1.getContext().getSharedPreferences("MySharedPref", Context.MODE_PRIVATE);

                SharedPreferences.Editor myEdit = sharedPreferences.edit();

                myEdit.putString("imagekey", String.valueOf(model.getImg1()));
                myEdit.apply();


            }
        });

        holder.img2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(holder.img1.getContext(), editFameActivity.class);
                holder.img1.getContext().startActivity(intent);


                SharedPreferences sharedPreferences = holder.img1.getContext().getSharedPreferences("MySharedPref", Context.MODE_PRIVATE);

                SharedPreferences.Editor myEdit = sharedPreferences.edit();

                myEdit.putString("imagekey", String.valueOf(model.getImg2()));
                myEdit.apply();
            }
        });

        holder.img3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(holder.img1.getContext(), editFameActivity.class);
                holder.img1.getContext().startActivity(intent);


                SharedPreferences sharedPreferences = holder.img1.getContext().getSharedPreferences("MySharedPref", Context.MODE_PRIVATE);

                SharedPreferences.Editor myEdit = sharedPreferences.edit();

                myEdit.putString("imagekey", String.valueOf(model.getimg3()));
                myEdit.apply();

            }
        });


    }


    @Override
    public int getItemCount() {
        return listdata.length;
    }


    public static class myViewHolder extends RecyclerView.ViewHolder {
        ImageView img1, img2, img3;

        public myViewHolder(@NonNull View itemView) {
            super(itemView);
            this.img1 = (ImageView) itemView.findViewById(R.id.img1);
            this.img2 = (ImageView) itemView.findViewById(R.id.img2);
            this.img3 = (ImageView) itemView.findViewById(R.id.img3);


        }
    }
}



