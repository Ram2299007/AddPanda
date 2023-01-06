package com.Appzia.addpanda.Adapter;

import static android.content.Context.MODE_PRIVATE;

import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.Appzia.addpanda.Model.textColorModel;
import com.Appzia.addpanda.R;

public class textColorAdapter extends RecyclerView.Adapter<textColorAdapter.myViewHolder> {

    final textColorModel[] listData;
    SharedPreferences sharedPreferences;
    public  LinearLayout cancelColor;

    public textColorAdapter(textColorModel[] listData,LinearLayout cancelColor) {
        this.listData = listData;
        this.cancelColor=cancelColor;
    }


    @NonNull
    @Override
    public textColorAdapter.myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {


        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem = layoutInflater.inflate(R.layout.text_color_row, parent, false);
        return new textColorAdapter.myViewHolder(listItem);

    }

    @Override
    public void onBindViewHolder(@NonNull textColorAdapter.myViewHolder holder, int position) {

        final textColorModel model = listData[position];
        holder.l1.setBackgroundColor(model.getL1());
        holder.l2.setBackgroundColor(model.getL2());
        holder.l3.setBackgroundColor(model.getL3());
        holder.l4.setBackgroundColor(model.getL4());
        holder.l5.setBackgroundColor(model.getL5());
        holder.l6.setBackgroundColor(model.getL6());
        holder.l7.setBackgroundColor(model.getL7());
        holder.l8.setBackgroundColor(model.getL8());
        holder.l9.setBackgroundColor(model.getL9());


        holder.t1.setText(model.getT1());
        holder.t2.setText(model.getT2());
        holder.t3.setText(model.getT3());
        holder.t4.setText(model.getT4());
        holder.t5.setText(model.getT5());
        holder.t6.setText(model.getT6());
        holder.t7.setText(model.getT7());
        holder.t8.setText(model.getT8());
        holder.t9.setText(model.getT9());


        holder.Lout1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                sharedPreferences = holder.l1.getContext().getSharedPreferences("colorPref", MODE_PRIVATE);
                SharedPreferences.Editor myEdit = sharedPreferences.edit();
                myEdit.putString("color", String.valueOf(model.getT1()));
                myEdit.apply();
                cancelColor.performClick();


            }
        });
        holder.Lout2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                sharedPreferences = holder.l1.getContext().getSharedPreferences("colorPref", MODE_PRIVATE);
                SharedPreferences.Editor myEdit = sharedPreferences.edit();
                myEdit.putString("color", String.valueOf(model.getT2()));
                myEdit.apply();
                cancelColor.performClick();
            }
        });
        holder.Lout3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                sharedPreferences = holder.l1.getContext().getSharedPreferences("colorPref", MODE_PRIVATE);
                SharedPreferences.Editor myEdit = sharedPreferences.edit();
                myEdit.putString("color", String.valueOf(model.getT3()));
                myEdit.apply();
                cancelColor.performClick();
            }
        });
        holder.Lout4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                sharedPreferences = holder.l1.getContext().getSharedPreferences("colorPref", MODE_PRIVATE);
                SharedPreferences.Editor myEdit = sharedPreferences.edit();
                myEdit.putString("color", String.valueOf(model.getT4()));
                myEdit.apply();
                cancelColor.performClick();
            }
        });
        holder.Lout5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                sharedPreferences = holder.l1.getContext().getSharedPreferences("colorPref", MODE_PRIVATE);
                SharedPreferences.Editor myEdit = sharedPreferences.edit();
                myEdit.putString("color", String.valueOf(model.getT5()));
                myEdit.apply();
                cancelColor.performClick();
            }
        });
        holder.Lout6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                sharedPreferences = holder.l1.getContext().getSharedPreferences("colorPref", MODE_PRIVATE);
                SharedPreferences.Editor myEdit = sharedPreferences.edit();
                myEdit.putString("color", String.valueOf(model.getT6()));
                myEdit.apply();
                cancelColor.performClick();
            }
        });
        holder.Lout7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                sharedPreferences = holder.l1.getContext().getSharedPreferences("colorPref", MODE_PRIVATE);
                SharedPreferences.Editor myEdit = sharedPreferences.edit();
                myEdit.putString("color", String.valueOf(model.getT7()));
                myEdit.apply();
                cancelColor.performClick();
            }
        });
        holder.Lout8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                sharedPreferences = holder.l1.getContext().getSharedPreferences("colorPref", MODE_PRIVATE);
                SharedPreferences.Editor myEdit = sharedPreferences.edit();
                myEdit.putString("color", String.valueOf(model.getT8()));
                myEdit.apply();
                cancelColor.performClick();
            }
        });
        holder.Lout9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                sharedPreferences = holder.l1.getContext().getSharedPreferences("colorPref", MODE_PRIVATE);
                SharedPreferences.Editor myEdit = sharedPreferences.edit();
                myEdit.putString("color", String.valueOf(model.getT9()));
                myEdit.apply();
                cancelColor.performClick();
            }
        });

    }


    @Override
    public int getItemCount() {
        return listData.length;
    }

    public static class myViewHolder extends RecyclerView.ViewHolder {
        LinearLayout l1, l2, l3, l4, l5, l6, l7, l8, l9;
        TextView t1, t2, t3, t4, t5, t6, t7, t8, t9, edittExt;
        LinearLayout Lout1, Lout2, Lout3, Lout4, Lout5, Lout6, Lout7, Lout8, Lout9;
        LinearLayout bgl1, bgl2, bgl3, bgl4, bgl5, bgl6, bgl7, bgl8, bgl9;

        public myViewHolder(@NonNull View itemView) {
            super(itemView);
            l1 = (LinearLayout) itemView.findViewById(R.id.l1);
            l2 = (LinearLayout) itemView.findViewById(R.id.l2);
            l3 = (LinearLayout) itemView.findViewById(R.id.l3);
            l4 = (LinearLayout) itemView.findViewById(R.id.l4);
            l5 = (LinearLayout) itemView.findViewById(R.id.l5);
            l6 = (LinearLayout) itemView.findViewById(R.id.l6);
            l7 = (LinearLayout) itemView.findViewById(R.id.l7);
            l8 = (LinearLayout) itemView.findViewById(R.id.l8);
            l9 = (LinearLayout) itemView.findViewById(R.id.l9);

            t1 = (TextView) itemView.findViewById(R.id.t1);
            t2 = (TextView) itemView.findViewById(R.id.t2);
            t3 = (TextView) itemView.findViewById(R.id.t3);
            t4 = (TextView) itemView.findViewById(R.id.t4);
            t5 = (TextView) itemView.findViewById(R.id.t5);
            t6 = (TextView) itemView.findViewById(R.id.t6);
            t7 = (TextView) itemView.findViewById(R.id.t7);
            t8 = (TextView) itemView.findViewById(R.id.t8);
            t9 = (TextView) itemView.findViewById(R.id.t9);


            Lout1 = (LinearLayout) itemView.findViewById(R.id.Lout1);
            Lout2 = (LinearLayout) itemView.findViewById(R.id.Lout2);
            Lout3 = (LinearLayout) itemView.findViewById(R.id.Lout3);
            Lout4 = (LinearLayout) itemView.findViewById(R.id.Lout4);
            Lout5 = (LinearLayout) itemView.findViewById(R.id.Lout5);
            Lout6 = (LinearLayout) itemView.findViewById(R.id.Lout6);
            Lout7 = (LinearLayout) itemView.findViewById(R.id.Lout7);
            Lout8 = (LinearLayout) itemView.findViewById(R.id.Lout8);
            Lout9 = (LinearLayout) itemView.findViewById(R.id.Lout9);


            bgl1 = (LinearLayout) itemView.findViewById(R.id.bgl1);
            bgl2 = (LinearLayout) itemView.findViewById(R.id.bgl2);
            bgl3 = (LinearLayout) itemView.findViewById(R.id.bgl3);
            bgl4 = (LinearLayout) itemView.findViewById(R.id.bgl4);
            bgl5 = (LinearLayout) itemView.findViewById(R.id.bgl5);
            bgl6 = (LinearLayout) itemView.findViewById(R.id.bgl6);
            bgl7 = (LinearLayout) itemView.findViewById(R.id.bgl7);
            bgl8 = (LinearLayout) itemView.findViewById(R.id.bgl8);
            bgl9 = (LinearLayout) itemView.findViewById(R.id.bgl9);


        }
    }
}
