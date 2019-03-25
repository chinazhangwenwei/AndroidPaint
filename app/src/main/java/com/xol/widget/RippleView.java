package com.xol.widget;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RadialGradient;
import android.graphics.Shader;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.xol.util.CommonUtil;

/**
 * Created by wwzhang on 2019/3/23
 */
public class RippleView extends View {
    private Paint mPaint;
    private float[] mPositons;
    private int[] mColorThress;
    private float mRadius;
    private Shader mShader;
    private ObjectAnimator mObjectAnimator;
    private float mStartPosition;

    private static final String TAG = "SleepView";

    public void setMStartPosition(float mStartPosition) {
        this.mStartPosition = mStartPosition;
        //Log.d(TAG, "setmStartPosition: ");
        invalidate();
    }


    public RippleView(Context context) {
        super(context);
    }

    public RippleView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public RippleView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    {
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPositons = new float[]{0.0f, 0.5f, 1.0f};
        mColorThress = new int[]{Color.parseColor("#ff0000"),
                Color.parseColor("#00ff00"),
                Color.parseColor("#0000ff")};
        mRadius = CommonUtil.dp2px(40, getContext());
        mObjectAnimator = ObjectAnimator.ofFloat(this,
                "mStartPosition", 0, 1.0f)
                .setDuration(1000);
        mObjectAnimator.setRepeatCount(ValueAnimator.INFINITE);
        mObjectAnimator.setRepeatMode(ValueAnimator.RESTART);
        mObjectAnimator.start();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mPositons[0] = mStartPosition;
        mPositons[1] = mPositons[0] + 0.2f;
        mPositons[2] = mPositons[1] + 0.2f;
        mShader = new RadialGradient(getMeasuredWidth() / 2,
                getMeasuredHeight() / 2, mRadius, mColorThress, mPositons,
                Shader.TileMode.REPEAT);
        mPaint.setShader(mShader);
        canvas.drawCircle(getMeasuredWidth() / 2, getMeasuredHeight() / 2,
                getMeasuredWidth() / 2 - 20, mPaint);
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (!mObjectAnimator.isStarted()) {
            mObjectAnimator.start();
        }
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if (mObjectAnimator.isStarted()) {
            mObjectAnimator.cancel();
        }
    }
}
