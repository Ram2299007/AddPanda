package com.Appzia.addpanda.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.Appzia.addpanda.R;

public class catnameadapter extends RecyclerView.Adapter<catnameadapter.myViewHolder> {
    Context mContext;

    public catnameadapter(Context mContext) {
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public catnameadapter.myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.subscriptionsubcat_rownew, parent, false);
        return new myViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull catnameadapter.myViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public static class myViewHolder extends RecyclerView.ViewHolder {
        TextView catname;

        public myViewHolder(@NonNull View itemView) {
            super(itemView);
            catname = itemView.findViewById(R.id.catname);
        }
    }
}
