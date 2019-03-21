package com.xol.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.SweepGradient;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.xol.util.CommonUtil;

/**
 * Created by wwzhang on 2019/3/20
 */
public class SweepGradientView extends View {
    private Paint mPaint;
    private int[] mColorArrayTwo;
    private int[] mColorArrayThree;
    private SweepGradient mSweepGradient;

    public SweepGradientView(Context context) {
        super(context);
    }

    public SweepGradientView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public SweepGradientView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    {
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mColorArrayTwo = new int[]{Color.parseColor("#ff0000"),
                Color.parseColor("#ffffff")};
        mColorArrayThree = new int[]{Color.parseColor("#ff0000")
                , Color.parseColor("#00ff00")
                , Color.parseColor("#0000ff")};

    }
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //绘制圆形
        mSweepGradient = new SweepGradient(200, 200, mColorArrayTwo[0], mColorArrayTwo[1]);
        mPaint.setShader(mSweepGradient);
        canvas.drawCircle(  200, 200, 100, mPaint);
        //绘制圆形
        mSweepGradient = new SweepGradient(300, 400, mColorArrayThree, null);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(CommonUtil.dp2px(4, getContext()));
        mPaint.setStrokeJoin(Paint.Join.ROUND);
        mPaint.setStrokeCap(Paint.Cap.ROUND);
        mPaint.setShader(mSweepGradient);
        canvas.drawCircle(300, 400, 100, mPaint);
        //绘制弧度
        canvas.drawArc(200, 500, 300, 600, 0, 120, false, mPaint);

    }
}
