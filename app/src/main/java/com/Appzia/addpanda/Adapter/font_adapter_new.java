package com.Appzia.addpanda.Adapter;

import static android.content.Context.MODE_PRIVATE;

import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.Appzia.addpanda.Model.fontModelNew;
import com.Appzia.addpanda.R;
import com.Appzia.addpanda.Screens.editFameActivity;

public class font_adapter_new extends RecyclerView.Adapter<font_adapter_new.myViewHolder> {

    SharedPreferences sharedPreferences;

    final fontModelNew[] listData;
    Typeface typeface;
    public RelativeLayout cancelIMg;



    public font_adapter_new(fontModelNew[] listData, RelativeLayout cancelIMg) {
        this.listData = listData;
        this.cancelIMg = cancelIMg;


    }


    @NonNull
    @Override
    public font_adapter_new.myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem = layoutInflater.inflate(R.layout.add_font_row, parent, false);
        return new myViewHolder(listItem);
    }

    @Override
    public void onBindViewHolder(@NonNull font_adapter_new.myViewHolder holder, int position) {

        final fontModelNew model = listData[position];

        holder.name.setText(model.getName());


     //  Toast.makeText(holder.font.getContext(), currentText, Toast.LENGTH_SHORT).show();

        try {
            typeface = Typeface.createFromAsset(holder.font.getContext().getAssets(), model.getFont());
            holder.name.setTypeface(typeface);
            holder.font.setText(model.getFont());
        }catch (Exception ignored){}

        holder.fontLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {
                    sharedPreferences = holder.itemView.getContext().getSharedPreferences("fontPref", MODE_PRIVATE);
                    SharedPreferences.Editor myEdit = sharedPreferences.edit();
                    myEdit.putString("font", String.valueOf(model.getFont()));
                    myEdit.apply();

                    // Toast.makeText(holder.font.getContext(), currentText, Toast.LENGTH_SHORT).show();
                    cancelIMg.performClick();
                }catch (Exception ignored){}
            }
        });


    }

    @Override
    public int getItemCount() {
        return listData.length;
    }

    public static class myViewHolder extends RecyclerView.ViewHolder {

        TextView name, font;
        LinearLayout fontLayout;

        public myViewHolder(@NonNull View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.f1);
            font = itemView.findViewById(R.id.f1Font);

            fontLayout = (LinearLayout) itemView.findViewById(R.id.fontLayout);


        }
    }
}
