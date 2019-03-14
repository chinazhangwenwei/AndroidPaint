package com.xol.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.xol.util.CommonUtil;

/**
 * Created by wwzhang on 2019/3/14
 */
public class LinearGradientView1 extends View {
    private Paint mPaint;
    private LinearGradient mLinearGradient;
    private int mColorWidth = 40;

    private int mWidthNum = 3;
    private int mHeightNum = 3;
    private int[] colorThree;
    private int[] colorTwo;
    private int mSpace;
    private float mWidth = 0;


    public LinearGradientView1(Context context) {
        super(context);
    }

    public LinearGradientView1(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public LinearGradientView1(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    {
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setStyle(Paint.Style.FILL);
        colorThree = new int[]{
                Color.parseColor("#ff0000"),
                Color.parseColor("#00ff00"),
                Color.parseColor("#0000ff")};
        colorTwo = new int[]{Color.parseColor("#ff0000"),
                Color.parseColor("#000000")};
        mSpace = (int) CommonUtil.dp2px(2, getContext());
        mPaint.setColor(Color.parseColor("#AAAAAA"));
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawLinearRound(canvas);

    }

    public void drawLinearRound(Canvas canvas) {
//计算
        if (mWidth < 5) {
            mWidth = (getMeasuredWidth() - (mWidthNum + 1) * mSpace) / mWidthNum;
            mHeightNum = (int) (getMeasuredHeight() / mWidth);
        }
//绘制
        RectF rectF = new RectF();
        for (int i = 0; i < mHeightNum; i++) {
            rectF.top = i * mWidth + (i + 1) * mSpace;
            rectF.bottom = rectF.top + mWidth;
            for (int j = 0; j < mWidthNum; j++) {
                rectF.left = j * mWidth + (j + 1) * mSpace;
                rectF.right = rectF.left + mWidth;
                canvas.drawRoundRect(rectF, 10, 10, mPaint);
            }
        }

    }
}
