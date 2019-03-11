package com.xol.util;

import android.content.Context;
import android.util.TypedValue;

/**
 * Created by wwzhang on 2019/3/11
 */
public class CommonUtil {

    public static float dp2px(float dp, Context context) {

        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                dp, context.getResources().getDisplayMetrics());

    }

    public static float sp2px(float sp, Context context) {
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP,
                sp, context.getResources().getDisplayMetrics());
    }

}
