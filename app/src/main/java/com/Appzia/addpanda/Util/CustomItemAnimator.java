package com.Appzia.addpanda.Util;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.view.View;

import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.RecyclerView;

public class CustomItemAnimator extends DefaultItemAnimator {

    @Override
    public boolean animateAdd(RecyclerView.ViewHolder holder) {
        // Customize the animation for adding views (scrolling to the next or previous page)
        // You can use various animations like slide, fade, scale, etc.
        // Example:
        ObjectAnimator scaleX = ObjectAnimator.ofFloat(holder.itemView, View.SCALE_X, 0.5f, 1f);
        ObjectAnimator scaleY = ObjectAnimator.ofFloat(holder.itemView, View.SCALE_Y, 0.5f, 1f);
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(scaleX, scaleY);
        animatorSet.setDuration(300);
        animatorSet.start();
        return super.animateAdd(holder);
    }
    
    @Override
    public boolean animateRemove(RecyclerView.ViewHolder holder) {
        // Customize the animation for removing views (scrolling to the next or previous page)
        // Example:
        ObjectAnimator scaleX = ObjectAnimator.ofFloat(holder.itemView, View.SCALE_X, 1f, 0.5f);
        ObjectAnimator scaleY = ObjectAnimator.ofFloat(holder.itemView, View.SCALE_Y, 1f, 0.5f);
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(scaleX, scaleY);
        animatorSet.setDuration(300);
        animatorSet.start();
        return super.animateRemove(holder);
    }
}
