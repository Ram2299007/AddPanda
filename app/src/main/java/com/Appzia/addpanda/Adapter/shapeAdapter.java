package com.Appzia.addpanda.Adapter;

import static android.content.Context.MODE_PRIVATE;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.Appzia.addpanda.Model.shapeModel;
import com.Appzia.addpanda.R;

public class shapeAdapter extends RecyclerView.Adapter<shapeAdapter.myViewHolder> {

    private final shapeModel[] listdata;
    SharedPreferences sharedPreferences;

   public LinearLayout click ;


    public shapeAdapter(shapeModel[] listdata,LinearLayout click) {
        this.listdata = listdata;
        this.click =click;

    }

    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {


        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem = layoutInflater.inflate(R.layout.shape_row, parent, false);
        return new shapeAdapter.myViewHolder(listItem);
    }

    @Override
    public void onBindViewHolder(@NonNull shapeAdapter.myViewHolder holder, int position) {
        final shapeModel model = listdata[position];

        try {

            holder.img1.setImageResource(model.getImg1());
            holder.img2.setImageResource(model.getImg2());
            holder.img3.setImageResource(model.getImg3());
            holder.img4.setImageResource(model.getImg4());

            holder.img1Text.setText(model.getImg1T());
            holder.img2Text.setText(model.getImg2T());
            holder.img3Text.setText(model.getImg3T());
            holder.img4Text.setText(model.getImg4T());
        }catch (Exception ignored){}

        holder.img1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sharedPreferences = holder.img1.getContext().getSharedPreferences("shapePref", MODE_PRIVATE);
                SharedPreferences.Editor myEdit = sharedPreferences.edit();
                myEdit.putString("shape", String.valueOf(model.getImg1T()));
                myEdit.apply();

                click.performClick();

            }
        });


        holder.img2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sharedPreferences = holder.img1.getContext().getSharedPreferences("shapePref", MODE_PRIVATE);
                SharedPreferences.Editor myEdit = sharedPreferences.edit();
                myEdit.putString("shape", String.valueOf(model.getImg2T()));
                myEdit.apply();
                click.performClick();

            }
        });


        holder.img3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sharedPreferences = holder.img1.getContext().getSharedPreferences("shapePref", MODE_PRIVATE);
                SharedPreferences.Editor myEdit = sharedPreferences.edit();
                myEdit.putString("shape", String.valueOf(model.getImg3T()));
                myEdit.apply();
                click.performClick();

            }
        });


        holder.img4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sharedPreferences = holder.img1.getContext().getSharedPreferences("shapePref", MODE_PRIVATE);
                SharedPreferences.Editor myEdit = sharedPreferences.edit();
                myEdit.putString("shape", String.valueOf(model.getImg4T()));
                myEdit.apply();
                click.performClick();

            }
        });


    }



    @Override
    public int getItemCount() {
        return listdata.length;
    }

    public static class myViewHolder extends RecyclerView.ViewHolder {

        ImageView img1, img2, img3, img4;
        TextView img1Text, img2Text, img3Text, img4Text;

        public myViewHolder(@NonNull View itemView) {
            super(itemView);
            img1 = (ImageView) itemView.findViewById(R.id.img1shape);
            img2 = (ImageView) itemView.findViewById(R.id.img2shape);
            img3 = (ImageView) itemView.findViewById(R.id.img3shape);
            img4 = (ImageView) itemView.findViewById(R.id.img4shape);

            img1Text = (TextView) itemView.findViewById(R.id.img1Text);
            img2Text = (TextView) itemView.findViewById(R.id.img2Text);
            img3Text = (TextView) itemView.findViewById(R.id.img3Text);
            img4Text = (TextView) itemView.findViewById(R.id.img4Text);
        }
    }


}
