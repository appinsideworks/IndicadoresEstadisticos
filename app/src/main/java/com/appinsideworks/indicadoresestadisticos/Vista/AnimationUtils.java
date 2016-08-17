package com.appinsideworks.indicadoresestadisticos.Vista;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.support.v7.widget.RecyclerView;
import android.view.animation.Animation;

/**
 * Created by ramon_a on 8/17/16.
 */
public class AnimationUtils {

    public static void animate(RecyclerView.ViewHolder holder, boolean goesDown) {
        AnimatorSet animatorSet = new AnimatorSet();

        ObjectAnimator animateY = ObjectAnimator.ofFloat(holder.itemView, "translationY", goesDown == true ? 100 : 0 , 0);
        animateY.setDuration(700);
        animatorSet.playTogether(animateY);
        animatorSet.start();

    }
}
