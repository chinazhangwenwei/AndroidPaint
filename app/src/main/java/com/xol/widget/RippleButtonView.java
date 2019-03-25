package com.xol.widget;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RadialGradient;
import android.graphics.Shader;
import android.support.v7.widget.AppCompatButton;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * Created by wwzhang on 2019/3/24
 */
public class RippleButtonView extends AppCompatButton {
    private Paint mPaint;
    private static final int DEFAULT_RADIUS = 50;
    private int mDuration = 500;
    private int radius;
    private int mColorTwo[];
    private Shader mShader;
    private int mCenterX, mCenterY;
    private ObjectAnimator mObjectAnimator;


    public RippleButtonView(Context context) {
        super(context);
    }

    public RippleButtonView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public RippleButtonView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    {
        mCenterX = -1;
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mColorTwo = new int[]{Color.parseColor("#0000ff00")
                , Color.parseColor("#ff00ff00")};
    }

    public void setRadius(int radius) {
        this.radius = radius;
        mShader = new RadialGradient(mCenterX, mCenterY, this.radius + 1, mColorTwo[0],
                mColorTwo[1], Shader.TileMode.CLAMP);
        mPaint.setShader(mShader);
        invalidate();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        if (mObjectAnimator == null) {
            mObjectAnimator = ObjectAnimator.ofInt(this, "radius",
                    DEFAULT_RADIUS, getMeasuredWidth());
            mObjectAnimator.addListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    setRadius(0);
                }
            });
            mObjectAnimator.setDuration(mDuration);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (mCenterX != (int) event.getX()) {
            mCenterX = (int) event.getX();
            mCenterY = (int) event.getY();
            setRadius(DEFAULT_RADIUS);
        }
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                return true;
            case MotionEvent.ACTION_UP:
                if (mObjectAnimator.isStarted()) {
                    mObjectAnimator.cancel();
                } else {
                    mObjectAnimator.start();
                }
                break;
        }
        return super.onTouchEvent(event);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (mShader != null) {
            canvas.drawCircle(mCenterX, mCenterY,
                    radius, mPaint);
        }
    }
}
