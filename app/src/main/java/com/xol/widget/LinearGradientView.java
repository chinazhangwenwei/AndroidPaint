package com.xol.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.graphics.Shader;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.xol.util.CommonUtil;

/**
 * Created by wwzhang on 2019/3/11
 */
public class LinearGradientView extends View {

    private Paint mPaint;
    private LinearGradient mLinearGradient;
    private int ColorWidth = 40;

    private int widthNum = 3;
    private int heightNum = 3;
    private int[] colorPurpleArray;
    private int[] colorPinkArray;

    public LinearGradientView(Context context) {
        super(context);
    }

    public LinearGradientView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public LinearGradientView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    {
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setStrokeWidth(CommonUtil.dp2px(2, getContext()));
        mPaint.setTextSize(CommonUtil.sp2px(16, getContext()));
        colorPurpleArray = new int[]{Color.parseColor("#e1bee7"),
                Color.parseColor("#e91e63"),
                Color.parseColor("#ba68c8"),
                Color.parseColor("#f8bbd0")};
        colorPinkArray = new int[]{
                Color.parseColor("#f8bbd0"),
                Color.parseColor("#f48fd1"),
                Color.parseColor("#f06292"),
                Color.parseColor("#ec407a"),
                Color.parseColor("#e91e63"),
                Color.parseColor("#d81b60"),
                Color.parseColor("#c2185b"),
                Color.parseColor("#ff4081"),
                Color.parseColor("#c51162"),
        };
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        if (mLinearGradient == null) {
            mLinearGradient = new LinearGradient(0, 0,
                    getMeasuredWidth(), getMeasuredHeight(), Color.parseColor("#ff0000"),
                    Color.parseColor("#00ff00"), Shader.TileMode.REPEAT);
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawBack(canvas);
    }

    public void drawBack(Canvas canvas) {
        canvas.save();
        float shadowLayout = CommonUtil.dp2px(2, getContext());
        int space = (int) CommonUtil.dp2px(4, getContext());
        float width = (getMeasuredWidth() - (widthNum + 1) * space) / widthNum;
        heightNum = (int) (getMeasuredHeight() / width);
        RectF rectF = new RectF();
        int count = 0;
        for (int i = 0; i < heightNum; i++) {
            rectF.top = i * width + (i + 1) * space;
            rectF.bottom = rectF.top + width;
            for (int j = 0; j < widthNum; j++) {
                rectF.left = j * width + (j + 1) * space;
                rectF.right = rectF.left + width;
                count++;
                mPaint.setColor(Color.parseColor("#dddddd"));
                canvas.drawRoundRect(rectF, shadowLayout * 2, shadowLayout * 2, mPaint);
                drawGraph(canvas, rectF, count);
            }
        }
        canvas.restore();
    }

    public void drawGraph(Canvas canvas, RectF rectF, int count) {
        canvas.save();
        Shader shader = null;
        String content = "";
        switch (count) {
            case 1:
                shader = new LinearGradient(rectF.left, rectF.top, rectF.right,
                        rectF.bottom, colorPinkArray[0], colorPinkArray[3], Shader.TileMode.CLAMP);
                content = "LG-clamp";
                break;
            case 2:
                shader = new LinearGradient(rectF.left, rectF.top, rectF.right,
                        rectF.bottom, colorPinkArray[0], colorPinkArray[3], Shader.TileMode.MIRROR);
                content = "LG-mirror";
                break;
            case 3:
                shader = new LinearGradient(rectF.left, rectF.top, rectF.right,
                        rectF.bottom, colorPinkArray[0], colorPinkArray[3], Shader.TileMode.REPEAT);
                content = "LG-repeat";
                break;
            case 4:
                shader = new LinearGradient(rectF.left, rectF.top, rectF.right,
                        rectF.bottom, colorPurpleArray, new float[]{0, 0.33f, 0.66f, 1.0f}, Shader.TileMode.CLAMP);
                content = "LGMutil-clamp";
                break;
            case 5:
                shader = new LinearGradient(rectF.left, rectF.top, rectF.right,
                        rectF.bottom, colorPurpleArray, new float[]{0, 0.33f, 0.66f, 1.0f}, Shader.TileMode.MIRROR);
                content = "LGMutil-mirror";
                break;
            case 6:
                shader = new LinearGradient(rectF.left, rectF.top, rectF.right,
                        rectF.bottom, colorPurpleArray, new float[]{0, 0.33f, 0.66f, 1.0f}, Shader.TileMode.REPEAT);
                content = "LGMutil-repeat";
                break;
            case 7:
                break;
            case 9:
                break;
            default:
                shader = new LinearGradient(rectF.left, rectF.top, rectF.right,
                        rectF.bottom, colorPurpleArray[0], colorPurpleArray[2], Shader.TileMode.CLAMP);
        }
        mPaint.setShader(shader);
        mPaint.setStrokeJoin(Paint.Join.ROUND);
        canvas.drawLine(rectF.left, rectF.top, rectF.right, rectF.bottom, mPaint);
        float radius = CommonUtil.dp2px(2, getContext());
        canvas.drawRoundRect(rectF.left, rectF.top +
                ColorWidth, rectF.left + ColorWidth, rectF.top + 2 * ColorWidth, radius, radius, mPaint);
        canvas.drawText(content, rectF.left, rectF.bottom - ColorWidth, mPaint);
        mPaint.setShader(null);
        if (count < 4 && count >= 0) {
            for (int i = 0; i < 2; i++) {
                mPaint.setColor(colorPinkArray[i * 2]);
                canvas.drawRect(rectF.left + i * 2 * ColorWidth, rectF.bottom - ColorWidth,
                        rectF.left + (i * 2 + 1) * ColorWidth, rectF.bottom, mPaint);
            }
        } else if (count > 3 && count < 7) {
            for (int i = 0; i < 4; i++) {
                mPaint.setColor(colorPurpleArray[i]);
                canvas.drawRect(rectF.left + ColorWidth * i * 2, rectF.bottom - ColorWidth,
                        rectF.left + (i * 2 + 1) * ColorWidth, rectF.bottom, mPaint);
            }
        }
        canvas.restore();

    }

}
