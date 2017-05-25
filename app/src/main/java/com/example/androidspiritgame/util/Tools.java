package com.example.androidspiritgame.util;

import android.animation.ObjectAnimator;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.example.androidspiritgame.R;
import com.example.androidspiritgame.application.ContextHolder;

/**
 * Created by 区枫华 on 2017/5/25.
 */

public class Tools {

    private static View btnView;

    /**
     * 放大动画
     */
    private static Animation enlargeAnim = null;

    /**
     * 缩小动画
     */
    private static Animation narrowAnim = null;

    /**
     * 按下动画
     *
     * @param view
     */
    public static void btnPress(View view) {
        btnView = view;
        if (narrowAnim == null) {
            narrowAnim = AnimationUtils.loadAnimation(ContextHolder.getContext(), R.anim.btn_anim_narrow);
            narrowAnim.setFillAfter(true);
        }
        btnView.clearAnimation();
        btnView.startAnimation(narrowAnim);
    }

    /**
     * 释放动画
     */
    public static void btnRelease() {
        if (enlargeAnim == null) {
            enlargeAnim = AnimationUtils.loadAnimation(ContextHolder.getContext(), R.anim.btn_anim_enlarge);
            enlargeAnim.setFillAfter(true);
        }
//        btnView.clearAnimation();
//        btnView.startAnimation(enlargeAnim);
        btnView.clearAnimation();

    }

    /**
     * 竖向动画
     *
     * @param view
     * @param deltaY
     * @param duration
     */
    public static void moveYTo(View view, int deltaY, int duration) {
        float from = view.getY();
        float to = from + deltaY;
        ObjectAnimator.ofFloat(view, "translationY", from, to).setDuration(duration).start();
    }

}
