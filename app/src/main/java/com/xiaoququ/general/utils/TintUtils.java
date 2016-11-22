package com.xiaoququ.general.utils;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.StateListDrawable;
import android.support.v4.content.ContextCompat;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.widget.ImageView;

/**
 * 利用Tint更优雅的实现selector
 * 该类是为了兼容6.0以下版本的Tint selector辅助类
 * Created by lizhihhui on 2016/11/21 11:41.
 */

public class TintUtils {

    private TintUtils() {
		/* cannot be instantiated */
        throw new UnsupportedOperationException("cannot be instantiated");
    }

    /**
     * 该方法是为了兼容6.0以下版本的Tint selector
     */
    public static void TintSelectorCompat(Context context, ImageView imageView, int resId, int color1, int color2) {
        Drawable drawable = ContextCompat.getDrawable(context, resId);
        int[] colors = new int[] {ContextCompat.getColor(context, color1), ContextCompat.getColor(context, color2)};
        int[][] states = new int[2][];
        states[0] = new int[] {android.R.attr.state_pressed};
        states[0] = new int[] {};

        ColorStateList colorStateList = new ColorStateList(states, colors);

        StateListDrawable stateListDrawable = new StateListDrawable();
        stateListDrawable.addState(states[0], drawable); // 顺序不要乱
        stateListDrawable.addState(states[1], drawable);

        Drawable.ConstantState state = stateListDrawable.getConstantState();
        drawable = DrawableCompat.wrap(state == null ? stateListDrawable : state.newDrawable().mutate());
        DrawableCompat.setTintList(drawable, colorStateList);

        imageView.setImageDrawable(drawable);
    }
}
