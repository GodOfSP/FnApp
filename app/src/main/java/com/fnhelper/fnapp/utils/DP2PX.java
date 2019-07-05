package com.fnhelper.fnapp.utils;

import android.content.Context;

/**
 * Created by ls on 2016/3/25.
 */

public class DP2PX {

    /**
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     */
    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        System.out.println("scale>>>"+scale);
        return (int) (dpValue * scale + 0.5f);
    }

    /**
     * 根据手机的分辨率从 px(像素) 的单位 转成为 dp
     */
    public static int px2dip(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

    /**
     * 得到屏幕的宽度
     * @param context
     * @return
     */
    public static float getWidth(Context context) {
       float scale = context.getResources().getDisplayMetrics().widthPixels;
        return scale;
    }

}