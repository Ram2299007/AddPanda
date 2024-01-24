package com.Appzia.addpanda.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.Appzia.addpanda.Model.frameModel;
import com.Appzia.addpanda.Screens.EditFrameOwner;
import com.Appzia.addpanda.Util.Constant.Constant;
import com.Appzia.addpanda.R;
import com.squareup.picasso.Picasso;

public class frameAdapter2 extends RecyclerView.Adapter<frameAdapter2.myViewHolder> {

    Context mContext;

    public frameAdapter2(Context mContext) {
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem = layoutInflater.inflate(R.layout.editingframe_row_design, parent, false);
        return new frameAdapter2.myViewHolder(listItem);
    }

    @Override
    public void onBindViewHolder(@NonNull frameAdapter2.myViewHolder holder, int position) {
        final frameModel model = Constant.frameListTwo.get(position);
        final ImageView img = new ImageView(holder.itemView.getContext());


        Picasso.get().load(model.getFrame()).into(img, new com.squareup.picasso.Callback() {
            @Override
            public void onSuccess() {


                holder.frame.setBackgroundDrawable(img.getDrawable());
            }

            @Override
            public void onError(Exception e) {

            }

        });

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Picasso.get().load(model.getFrame()).into(img, new com.squareup.picasso.Callback() {
                    @Override
                    public void onSuccess() {

                        try {
                            EditFrameOwner.mainRelativeLayout.setBackgroundDrawable(img.getDrawable());
                        } catch (Exception ignored) {
                        }

                    }

                    @Override
                    public void onError(Exception e) {

                    }

                });
            }
        });


    }


    @Override
    public int getItemCount() {
        return Constant.frameListTwo.size();
    }

    public static class myViewHolder extends RecyclerView.ViewHolder {

        LinearLayout frame;

        public myViewHolder(@NonNull View itemView) {
            super(itemView);

            frame = itemView.findViewById(R.id.frameTemp);


        }
    }


}
