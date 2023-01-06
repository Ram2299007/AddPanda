package com.Appzia.addpanda.Adapter;

import android.content.Context;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatButton;
import androidx.recyclerview.widget.RecyclerView;

import com.Appzia.addpanda.Model.mainCatBtnChildModel;
import com.Appzia.addpanda.Screens.viewAll;
import com.Appzia.addpanda.Util.Constant.Constant;

import com.Appzia.addpanda.R;

public class mainCatBtnChildAdapter extends RecyclerView.Adapter<mainCatBtnChildAdapter.myViewHolder> {

    Context mContext;

    TextView trendingViewall, anniversaryViewall;
    String basickey;


    public mainCatBtnChildAdapter(Context mContext, String basickey) {
        this.mContext = mContext;
        this.basickey = basickey;

    }

    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {


        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem = layoutInflater.inflate(R.layout.btncategory_row, parent, false);
        return new mainCatBtnChildAdapter.myViewHolder(listItem);
    }

    @Override
    public void onBindViewHolder(@NonNull mainCatBtnChildAdapter.myViewHolder holder, int position) {
        final mainCatBtnChildModel model = Constant.mainCatBtnModelList.get(position);


        holder.textName.setText(model.getTextName());

        holder.textName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent intent = new Intent(holder.itemView.getContext(), viewAll.class);
                intent.putExtra("catIdKey", model.getCatId());
                holder.textName.getContext().startActivity(intent);

            }
        });


    }


    @Override
    public int getItemCount() {
        return Constant.mainCatBtnModelList.size();
    }

    public static class myViewHolder extends RecyclerView.ViewHolder {

        AppCompatButton textName;

        public myViewHolder(@NonNull View itemView) {
            super(itemView);

            textName = itemView.findViewById(R.id.btnCat);


        }
    }


}
