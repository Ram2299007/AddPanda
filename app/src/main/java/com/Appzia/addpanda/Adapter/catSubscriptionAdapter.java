package com.Appzia.addpanda.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.Appzia.addpanda.Model.get_subscription_listChild2Model;
import com.Appzia.addpanda.Model.get_subscription_listChildModel;
import com.Appzia.addpanda.R;

import java.util.ArrayList;

public class catSubscriptionAdapter extends RecyclerView.Adapter<catSubscriptionAdapter.myviewholder> {
    Context mContext;


    recyclerviewcatAdapter recyclerviewcatAdapter;
    ArrayList<get_subscription_listChild2Model> list = new ArrayList<>();

    public catSubscriptionAdapter(Context mContext, ArrayList<get_subscription_listChild2Model> list) {
        this.mContext = mContext;
        this.list = list;


    }

    @NonNull
    @Override
    public catSubscriptionAdapter.myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.subscriptioncatrow, parent, false);
        return new myviewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull catSubscriptionAdapter.myviewholder holder, int position) {
        final get_subscription_listChild2Model model = list.get(position);

        holder.nameType.setText(model.getName());
        recyclerviewcatAdapter = new recyclerviewcatAdapter(mContext, model.getType_data());
        holder.recyclerviewcat.setLayoutManager(new LinearLayoutManager(mContext));
        holder.recyclerviewcat.setAdapter(recyclerviewcatAdapter);
        recyclerviewcatAdapter.notifyDataSetChanged();


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class myviewholder extends RecyclerView.ViewHolder {
        RecyclerView recyclerviewcat;
        TextView nameType;

        public myviewholder(@NonNull View itemView) {
            super(itemView);
            recyclerviewcat = itemView.findViewById(R.id.recyclerviewcat);
            nameType = itemView.findViewById(R.id.nameType);

        }
    }
}
