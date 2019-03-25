package com.xol.widget;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Shader;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
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
        mRadius=100;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        if (mBitmap == null) {
            Drawable drawable = ContextCompat.getDrawable(getContext(),
                    R.drawable.rong);
            mBitmap = Bitmap.createBitmap(drawable.getIntrinsicWidth(),
                    drawable.getIntrinsicHeight(), Bitmap.Config.RGB_565);
            Canvas canvas = new Canvas(mBitmap);
            drawable.setBounds(0,0,canvas.getWidth(),canvas.getHeight());
            drawable.draw(canvas);
            mShader = new BitmapShader(mBitmap, Shader.TileMode.CLAMP,
                    Shader.TileMode.CLAMP);
            Matrix matrix = new Matrix();
            float fScale = Math.max(mRadius*2/mBitmap.getWidth(),
                    mRadius*2/mBitmap.getHeight());
            matrix.setScale(fScale,fScale);
            mShader.setLocalMatrix(matrix);

        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
       // canvas.drawBitmap(mBitmap, 0, 0, mPaint);
        mPaint.setShader(mShader);
        canvas.drawCircle(200, 200, mRadius, mPaint);

    }
}
