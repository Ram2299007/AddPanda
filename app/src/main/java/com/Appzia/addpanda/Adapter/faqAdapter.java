package com.Appzia.addpanda.Adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.Appzia.addpanda.Model.faqModel;
import com.Appzia.addpanda.Util.Constant.Constant;
import com.Appzia.addpanda.Model.AnniversaryModel;
import com.Appzia.addpanda.R;
import com.Appzia.addpanda.Screens.trendingActivity;
import com.squareup.picasso.Picasso;

public class faqAdapter extends RecyclerView.Adapter<faqAdapter.myViewHolder> {

    Context mContext;

    public faqAdapter(Context mContext) {
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {


        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem = layoutInflater.inflate(R.layout.faq_row, parent, false);
        return new faqAdapter.myViewHolder(listItem);
    }

    @Override
    public void onBindViewHolder(@NonNull faqAdapter.myViewHolder holder, int position) {
        final faqModel model = Constant.faqList.get(position);

        holder.question.setText(model.getQuestion());
        holder.answer.setText(model.getAnswer());

        holder.faq1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(holder.faq2.getVisibility()==View.GONE){
                    holder.faq2.setVisibility(View.VISIBLE);
                    holder.firstarrow.setImageResource(R.drawable.uparrow);
                    holder.faq1.setBackgroundResource(R.drawable.faq_radius1);
                }else
                if(holder.faq2.getVisibility()==View.VISIBLE){
                    holder.faq2.setVisibility(View.GONE);
                    holder.firstarrow.setImageResource(R.drawable.downarrow);
                    holder.faq1.setBackgroundResource(R.drawable.low_radius_noti);
                }
            }
        });


    }


    @Override
    public int getItemCount() {
        return Constant.faqList.size();
    }

    public static class myViewHolder extends RecyclerView.ViewHolder {


        TextView question, answer;
        ImageView firstarrow;
        LinearLayout faq1,faq2;

        public myViewHolder(@NonNull View itemView) {
            super(itemView);


            question = itemView.findViewById(R.id.question);
            answer = itemView.findViewById(R.id.answer);
            firstarrow = itemView.findViewById(R.id.firstarrow);
            faq1 = itemView.findViewById(R.id.faq1);
            faq2 = itemView.findViewById(R.id.faq2);


        }
    }


}
