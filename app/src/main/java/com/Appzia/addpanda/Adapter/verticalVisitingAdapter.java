package com.Appzia.addpanda.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.Appzia.addpanda.Model.horiVisitingModel;
import com.Appzia.addpanda.Screens.editVisitingCardDetailsActivity;
import com.Appzia.addpanda.Util.Constant.Constant;
import com.Appzia.addpanda.R;
import com.squareup.picasso.Picasso;

public class verticalVisitingAdapter extends RecyclerView.Adapter<verticalVisitingAdapter.myViewHolder> {

    Context mContext;

    public verticalVisitingAdapter(Context mContext) {
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {


        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem = layoutInflater.inflate(R.layout.vertical_visiting_card, parent, false);
        return new verticalVisitingAdapter.myViewHolder(listItem);
    }

    @Override
    public void onBindViewHolder(@NonNull verticalVisitingAdapter.myViewHolder holder, int position) {
        final horiVisitingModel model = Constant.horiVisitingList.get(position);


        Picasso.get().load(model.getImage()).into(holder.horirow);
        holder.visiting_card_template_id.setText(model.getFile());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Constant.setSfFunction(holder.horirow.getContext());
                Constant.setSF.putString("template_idKey", model.getFile());
                Constant.setSF.putString("ProfileKey", model.getImage());
                Constant.setSF.putString("veriHoriKey", "VERI");
                Constant.setSF.apply();


                holder.itemView.getContext().startActivity(new Intent(holder.horirow.getContext(), editVisitingCardDetailsActivity.class));
            }
        });

    }


    @Override
    public int getItemCount() {
        return Constant.horiVisitingList.size();
    }

    public static class myViewHolder extends RecyclerView.ViewHolder {

        ImageView horirow;
        TextView visiting_card_template_id;


        public myViewHolder(@NonNull View itemView) {
            super(itemView);

            horirow = itemView.findViewById(R.id.horirow);
            visiting_card_template_id = itemView.findViewById(R.id.visiting_card_template_id);


        }
    }


}
