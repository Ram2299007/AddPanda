package com.Appzia.addpanda.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.Appzia.addpanda.Model.get_subscription_listChild2Model;
import com.Appzia.addpanda.Model.get_subscription_listchild3Model;
import com.Appzia.addpanda.R;

import java.util.ArrayList;

public class recyclerviewcatAdapter extends RecyclerView.Adapter<recyclerviewcatAdapter.myviewholder> {
    Context mContext;
    ArrayList<get_subscription_listchild3Model> typeData = new ArrayList<>();

    public recyclerviewcatAdapter(Context mContext, ArrayList<get_subscription_listchild3Model> typeData) {
        this.mContext = mContext;
        this.typeData = typeData;


    }

    @NonNull
    @Override
    public recyclerviewcatAdapter.myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.subscriptionsubcat_rownew, parent, false);
        return new myviewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull recyclerviewcatAdapter.myviewholder holder, int position) {
        get_subscription_listchild3Model model = typeData.get(position);
        holder.catname.setText(model.getCategory_name());


    }

    @Override
    public int getItemCount() {
        return typeData.size();
    }

    public static class myviewholder extends RecyclerView.ViewHolder {
        TextView catname;

        public myviewholder(@NonNull View itemView) {
            super(itemView);
            catname = itemView.findViewById(R.id.catname);

        }
    }
}
