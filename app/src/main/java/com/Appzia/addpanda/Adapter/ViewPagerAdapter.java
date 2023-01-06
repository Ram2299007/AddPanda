package com.Appzia.addpanda.Adapter;

import android.content.Context;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import androidx.annotation.CallSuper;
import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.Appzia.addpanda.Util.Constant.Constant;
import com.Appzia.addpanda.Model.frameModel;
import com.Appzia.addpanda.R;
import com.squareup.picasso.Picasso;

public class ViewPagerAdapter extends PagerAdapter {
    Context context;


    public ViewPagerAdapter(Context context) {

        this.context = context;

    }

    @Override
    public int getCount() {
        return Constant.frameList.size();
    }

    @Override
    public void destroyItem(View arg0, int arg1, Object arg2) {
        ((ViewPager) arg0).removeView((View) arg2);

    }

    @Override
    public boolean isViewFromObject(View view, Object object) {

        return view == ((RelativeLayout) object);

    }


    @Override
    public Parcelable saveState() {
        return null;
    }
    @NonNull
    @Override
    public Object instantiateItem(View collection, int position) {


        final frameModel model = Constant.frameList.get(position);
        LayoutInflater inflater = (LayoutInflater) collection.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.imagepager_layout, null);
        ((ViewPager) collection).addView(view);
        final ImageView img = (ImageView) view.findViewById(R.id.img);
        Picasso.get().load(model.getFrame()).fit().centerCrop()
                .into(img);
        return view;
    }
}