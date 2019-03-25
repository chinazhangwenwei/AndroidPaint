package com.xol.widget;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Shader;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.xol.android_paint.R;

/**
 * Created by wwzhang on 2019/3/24
 */
public class BitmapShaderView extends View {
    private Paint mPaint;
    private Shader mShader;
    private Bitmap mBitmap;
    private float mRadius;

    public BitmapShaderView(Context context) {
        super(context);
    }

    public BitmapShaderView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public BitmapShaderView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    {
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mRadius = 200;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        if (mBitmap == null) {
            mBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.rong);
            mRadius = Math.min(getMeasuredWidth(), getMeasuredHeight());
            mRadius = mRadius / 2;
            mShader = new BitmapShader(mBitmap, Shader.TileMode.CLAMP,
                    Shader.TileMode.CLAMP);
            Matrix matrix = new Matrix();
            float scale;
            float trans;
            //宽大于高
            if (mBitmap.getWidth() > mBitmap.getHeight()) {
                //缩放设置
                scale = 2 * mRadius / mBitmap.getHeight();
                matrix.setScale(scale, scale);
                //水平方向平移到中间
                trans = (2 * mRadius - mBitmap.getWidth() * scale) / 2;
                matrix.postTranslate(trans, 0);

            } else {
                //缩放设置
                scale = 2 * mRadius / mBitmap.getWidth();
                matrix.setScale(scale, scale);
                //移动到中间位置
                // trans = (2 * mRadius - mBitmap.getHeight() * scale) / 2;
                // matrix.postTranslate(0, trans);
            }
            mShader.setLocalMatrix(matrix);
        }
        mPaint.setShader(mShader);
        //绘制圆形
        canvas.drawCircle(mRadius, mRadius, mRadius, mPaint);


    }
}
