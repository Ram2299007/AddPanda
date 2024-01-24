package com.Appzia.addpanda.Adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.Appzia.addpanda.Model.get_category_listChild1Model;
import com.Appzia.addpanda.R;
import com.Appzia.addpanda.Screens.viewAll;
import com.Appzia.addpanda.Util.Constant.Constant;

import java.util.ArrayList;
import java.util.List;

public class categoryParentAdapter extends RecyclerView.Adapter<categoryParentAdapter.myViewHolder> {

    Context mContext;
    List<get_category_listChild1Model> get_category_listChild1ModelList;
    categoryChildAdapter adapter;
    mainCatBtnChildAdapter mainCatBtnChildAdapter;
    String BasicKey;
    RelativeLayout relativelayout;

    public categoryParentAdapter(Context mContext, List<get_category_listChild1Model> get_category_listChild1ModelList, String BasicKey, RelativeLayout relativelayout) {
        this.mContext = mContext;
        this.get_category_listChild1ModelList = get_category_listChild1ModelList;
        this.BasicKey = BasicKey;
        this.relativelayout = relativelayout;
    }

    public void filterList(ArrayList<get_category_listChild1Model> filterlist) {
        this.get_category_listChild1ModelList = filterlist;
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
        final get_category_listChild1Model model = get_category_listChild1ModelList.get(position);
        holder.catNameId.setText(model.getCategory_name());



      //  Toast.makeText(mContext, "", Toast.LENGTH_SHORT).show();
        Log.d("TAG", "onBindViewHolder: "+model.getSub_category_list());

        adapter = new categoryChildAdapter(mContext, model.getSub_category_list(), BasicKey,model.getCategory_id(),relativelayout);
        holder.recyclerViewFinal.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false));
        holder.recyclerViewFinal.setAdapter(adapter);
        adapter.notifyDataSetChanged();


        holder.viewAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (BasicKey.equals(Constant.mainKey)) {
                    Intent intent = new Intent(holder.itemView.getContext(), viewAll.class);
                    intent.putExtra("catIdKey", model.getCategory_id());
                    intent.putExtra(Constant.BasicKeyMain, BasicKey);
                    holder.viewAll.getContext().startActivity(intent);

                } else if (BasicKey.equals(Constant.basicKey)) {
                    Intent intent = new Intent(holder.itemView.getContext(), viewAll.class);
                    intent.putExtra("catIdKey", model.getCategory_id());
                    intent.putExtra(Constant.BasicKeyMain, BasicKey);
                    holder.viewAll.getContext().startActivity(intent);

                } else if (BasicKey.equals(Constant.scratchKey)) {
                    Intent intent = new Intent(holder.itemView.getContext(), viewAll.class);
                    intent.putExtra("catIdKey", model.getCategory_id());
                    intent.putExtra(Constant.BasicKeyMain, BasicKey);
                    holder.viewAll.getContext().startActivity(intent);
                }


            }
        });

    }

    @Override
    public int getItemCount() {
        return get_category_listChild1ModelList.size();
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
        get_category_listChild1ModelList.clear();
        adapter.get_category_listChild2ModelList.clear();
        notifyDataSetChanged();
    }
}
