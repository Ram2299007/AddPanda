package com.Appzia.addpanda.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.Appzia.addpanda.Fragments.mainFragment;
import com.Appzia.addpanda.Model.categoryParentModel;
import com.Appzia.addpanda.R;
import com.Appzia.addpanda.Screens.viewAll;
import com.Appzia.addpanda.Screens.viewAllBasic;
import com.Appzia.addpanda.Util.Constant.Constant;

import java.util.ArrayList;
import java.util.List;

public class categoryParentAdapter extends RecyclerView.Adapter<categoryParentAdapter.myViewHolder> {

    Context mContext;
    List<categoryParentModel> categoryParentModelsList;
    categoryChildAdapter adapter;
    mainCatBtnChildAdapter mainCatBtnChildAdapter;
    String BasicKey;

    public categoryParentAdapter(Context mContext, List<categoryParentModel> categoryParentModels, String BasicKey) {
        this.mContext = mContext;
        this.categoryParentModelsList = categoryParentModels;
        this.BasicKey = BasicKey;
    }

    public void filterList(ArrayList<categoryParentModel> filterlist) {
        this.categoryParentModelsList = filterlist;
        notifyDataSetChanged();
    }


    @NonNull
    @Override
    public categoryParentAdapter.myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.category_row_layout_parent, parent, false);

        return new myViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull categoryParentAdapter.myViewHolder holder, int position) {
        final categoryParentModel model = categoryParentModelsList.get(position);
        holder.catNameId.setText(model.getCatname());

        adapter = new categoryChildAdapter(mContext, model.getCategoryChildModel(), BasicKey);
        holder.recyclerViewFinal.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false));
        holder.recyclerViewFinal.setAdapter(adapter);
        adapter.notifyDataSetChanged();


        holder.viewAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (BasicKey.equals(Constant.mainKey)) {
                    Intent intent = new Intent(holder.itemView.getContext(), viewAll.class);
                    intent.putExtra("catIdKey", model.getCatIdKey());
                    intent.putExtra(Constant.BasicKeyMain, BasicKey);
                    holder.viewAll.getContext().startActivity(intent);

                } else if (BasicKey.equals(Constant.basicKey)) {
                    Intent intent = new Intent(holder.itemView.getContext(), viewAll.class);
                    intent.putExtra("catIdKey", model.getCatIdKey());
                    intent.putExtra(Constant.BasicKeyMain, BasicKey);
                    holder.viewAll.getContext().startActivity(intent);

                } else if (BasicKey.equals(Constant.scratchKey)) {
                    Intent intent = new Intent(holder.itemView.getContext(), viewAll.class);
                    intent.putExtra("catIdKey", model.getCatIdKey());
                    intent.putExtra(Constant.BasicKeyMain, BasicKey);
                    holder.viewAll.getContext().startActivity(intent);
                }


            }
        });

    }

    @Override
    public int getItemCount() {
        return categoryParentModelsList.size();
    }


    public class myViewHolder extends RecyclerView.ViewHolder {

        TextView catNameId, viewAll;
        RecyclerView recyclerViewFinal;

        public myViewHolder(@NonNull View itemView) {
            super(itemView);

            catNameId = itemView.findViewById(R.id.catNameId);
            viewAll = itemView.findViewById(R.id.viewAll);
            recyclerViewFinal = itemView.findViewById(R.id.recyclerViewFinal);
        }
    }

    public void clear() {
        categoryParentModelsList.clear();
        adapter.childModelList.clear();
        notifyDataSetChanged();
    }
}
