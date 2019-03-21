package com.xol.widget;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Shader;
import android.graphics.SweepGradient;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.LinearInterpolator;

import com.xol.util.CommonUtil;

/**
 * Created by wwzhang on 2019/3/21
 */
public class RadarView extends View {

    private Paint mPaint;
    private int mCircleColor;
    private int mSweepColor[];
    private float mCenterY;
    private float mCenterX;
    private float mRadius;
    private int mPading;
    private Shader mSweepShader;
    private ValueAnimator mValueAnimator;
    private float mDegressProgress = 0.2f;

    public RadarView(Context context) {
        super(context);
    }

    public RadarView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    private static final String TAG = "RadarView";

    public RadarView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    {
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mCircleColor = Color.parseColor("#000000");
        mSweepColor = new int[]{Color.parseColor("#0000ff"),
                Color.parseColor("#22000000")};
        mPaint.setStrokeWidth(CommonUtil.dp2px(2, getContext()));
        mRadius = -1;
        mPading = (int) CommonUtil.dp2px(10, getContext());
        mValueAnimator = ValueAnimator.ofFloat(0, 1.0f);
        mValueAnimator.setInterpolator(new LinearInterpolator());
        mValueAnimator.setRepeatCount(ValueAnimator.INFINITE);
        mValueAnimator.setDuration(5000);
        mValueAnimator.setRepeatMode(ValueAnimator.RESTART);
        mValueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                mDegressProgress = (float) animation.getAnimatedValue();
                Log.d(TAG, "onAnimationUpdate: " + mDegressProgress);
                invalidate();
            }

        });

    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (mRadius < 0) {
            int minDistance = Math.min(getMeasuredHeight(), getMeasuredWidth());
            mCenterX = minDistance / 2;
            mCenterY = mCenterX;
            mRadius = mCenterX - mPading;
            mSweepShader = new SweepGradient(mCenterX, mCenterY, mSweepColor[0], mSweepColor[1]);
        }
        //绘制扫描线
        canvas.save();
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setShader(mSweepShader);
        canvas.rotate(mDegressProgress * 360, mCenterX, mCenterY);
        canvas.drawCircle(mCenterX, mCenterY, mRadius, mPaint);
        canvas.restore();
        //绘制圆形和直角坐标
        mPaint.setColor(mCircleColor);
        mPaint.setShader(null);
        canvas.save();
        mPaint.setStyle(Paint.Style.STROKE);
        canvas.drawCircle(mCenterX, mCenterY, mRadius, mPaint);
        canvas.drawCircle(mCenterX, mCenterY, mRadius / 2, mPaint);
        canvas.drawLine(mPading, mCenterY, mCenterX + mRadius, mCenterY, mPaint);
        canvas.drawLine(mCenterX, mPading, mCenterX, mCenterY + mRadius, mPaint);
        canvas.restore();
        if (!mValueAnimator.isStarted()) {
            mValueAnimator.start();
        }

    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
    }
}
