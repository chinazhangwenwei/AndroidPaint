package com.xol.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RadialGradient;
import android.graphics.Shader;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by wwzhang on 2019/3/22
 */
public class RadialGradientView extends View {
    private Paint mPaint;
    private Shader mShader;
    private int[] mColorTwo;
    private int[] mColorThree;


    public RadialGradientView(Context context) {
        super(context);
    }

    public RadialGradientView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public RadialGradientView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    {
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mColorThree = new int[]{
                Color.parseColor("#ff0000"),
                Color.parseColor("#00ff00"),
                Color.parseColor("#0000ff")};
        mColorTwo = new int[]{Color.parseColor("#ff0000"),
                Color.parseColor("#0000ff")};

    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mShader = new RadialGradient(200, 200, 40, mColorTwo[0], mColorTwo[1], Shader.TileMode.REPEAT);
        mPaint.setShader(mShader);
        canvas.drawCircle(200, 200, 150, mPaint);

        mShader = new RadialGradient(500, 200, 112.5f, mColorTwo[0], mColorTwo[1], Shader.TileMode.MIRROR);
        mPaint.setShader(mShader);
        canvas.drawCircle(500, 200, 150, mPaint);

        mShader = new RadialGradient(200, 500, 40, mColorTwo[0], mColorTwo[1], Shader.TileMode.MIRROR);
        mPaint.setShader(mShader);
        canvas.drawCircle(200, 500, 150, mPaint);


    }


}
