package com.xol.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
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
    private int[] colorTextColor;

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
//        colorPurpleArray = new int[]{Color.parseColor("#e1bee7"),
//                Color.parseColor("#e91e63"),
//                Color.parseColor("#ba68c8"),
//                Color.parseColor("#f8bbd0")};
        colorPurpleArray = new int[]{Color.parseColor("#ff0000"),
                Color.parseColor("#00ff00"),
                Color.parseColor("#0000ff"),
                Color.parseColor("#000000")};
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
        colorTextColor = new int[]{
                Color.parseColor("#ff0000"),
                Color.parseColor("#0000ff")
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
        //绘制
        canvas.save();
        float shadowLayout = CommonUtil.dp2px(2, getContext());
        int space = (int) CommonUtil.dp2px(4, getContext());
        float width = (getMeasuredWidth() - (widthNum + 1) * space) / widthNum;
        heightNum = (int) (getMeasuredHeight() / width);
        RectF rectF = new RectF();
        Shader shader = null;
        int count = 0;
        for (int i = 0; i < heightNum; i++) {
            rectF.top = i * width + (i + 1) * space;
            rectF.bottom = rectF.top + width;
            for (int j = 0; j < widthNum; j++) {
                rectF.left = j * width + (j + 1) * space;
                rectF.right = rectF.left + width;
                count++;
                mPaint.setColor(Color.parseColor("#dddddd"));
                //绘制背景
                shader = null;
                switch (count) {
                    case 7:
                        shader = new LinearGradient(rectF.left, rectF.top, rectF.right,
                                rectF.bottom, colorPurpleArray, new float[]{1.0f, 0.5f, 0.5f, 0.88f}, Shader.TileMode.CLAMP);
                        break;
                    case 8:
                        shader = new LinearGradient(rectF.left, rectF.top, rectF.right,
                                rectF.bottom, colorPurpleArray, new float[]{0.2f, 0.55f, 0.66f, 0.66f}, Shader.TileMode.REPEAT);
                        break;
                    case 9:
                        shader = new LinearGradient(rectF.left, rectF.top, rectF.right,
                                rectF.bottom, colorPurpleArray, new float[]{0, 0.55f, 0.66f, 0.66f}, Shader.TileMode.REPEAT);
                        break;
                }
                mPaint.setShader(shader);
                canvas.drawRoundRect(rectF, shadowLayout * 2, shadowLayout * 2, mPaint);
                //绘制各种图形
                drawGraph(canvas, rectF, count);
            }
        }
        canvas.restore();
        //绘制闪动文字
        String content = "非淡泊无以明智，非宁静无以致远";
        float contentWidth = mPaint.measureText(content);
        mPaint.setColor(Color.parseColor("#00ff00"));
        shader = new LinearGradient(0, rectF.bottom, contentWidth,
                rectF.bottom, colorTextColor, new float[]{0.0f, 0.5f}, Shader.TileMode.REPEAT);
        shader = new LinearGradient(0, rectF.bottom, contentWidth,
                rectF.bottom, colorPurpleArray, new float[]{0.5f, 1.0f, 0.0f, 0.0f}, Shader.TileMode.CLAMP);
        mPaint.setShader(shader);
        canvas.drawText("非淡泊无以明智，非宁静无以致远", 0, getMeasuredHeight()-2*CommonUtil.dp2px(20,getContext()), mPaint);
        shader = new LinearGradient(0, rectF.bottom, contentWidth,
                rectF.bottom, colorTextColor, new float[]{0.9f, 0.9f}, Shader.TileMode.CLAMP);
        mPaint.setShader(shader);
        canvas.drawText("非淡泊无以明智，非宁静无以致远", 0, getMeasuredHeight() - CommonUtil.dp2px(20, getContext()), mPaint);
        shader = new LinearGradient(0, rectF.bottom, contentWidth,
                rectF.bottom, colorTextColor, new float[]{0.4f, 0.6f}, Shader.TileMode.MIRROR);
        mPaint.setShader(shader);
        canvas.drawText("非淡泊无以明智，非宁静无以致远", 0, getMeasuredHeight(), mPaint);

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
            case 8:
                break;
            case 9:
                break;
            default:
                shader = new LinearGradient(rectF.left, rectF.top, rectF.right,
                        rectF.bottom, colorPurpleArray[0], colorPurpleArray[2], Shader.TileMode.CLAMP);
        }

        //超过绘制类型范围return;
        if (count > 6) {
            mPaint.setShader(null);
            return;
        }
        mPaint.setShader(shader);
        //绘制当前色块下的画线效果
        mPaint.setStrokeJoin(Paint.Join.ROUND);
        canvas.drawLine(rectF.left, rectF.top, rectF.right, rectF.bottom, mPaint);
        //绘制当前变换下的圆角矩形效果
        float radius = CommonUtil.dp2px(2, getContext());
        mPaint.setStyle(Paint.Style.FILL_AND_STROKE);
        canvas.drawRoundRect(rectF.left, rectF.top +
                ColorWidth, rectF.left + ColorWidth, rectF.top + 2 * ColorWidth, radius, radius, mPaint);
        //绘制当前线性变换类型文本
        mPaint.setStyle(Paint.Style.FILL);
        canvas.drawText(content, rectF.left, rectF.bottom - ColorWidth, mPaint);
        //计算绘圆形的坐标和半径
        float cenX = rectF.left + (rectF.right - rectF.left) / 2;
        float cenY = rectF.top + (rectF.bottom - rectF.top) / 2;
        float circleRadius = CommonUtil.dp2px(10, getContext());
        float offset = (float) (circleRadius / Math.sqrt(2));
        //绘制当前变换下圆形和扇形
        mPaint.setStyle(Paint.Style.FILL);
        canvas.drawCircle(cenX + offset, cenY - offset, circleRadius, mPaint);
        float arcOffset = 3 * circleRadius;
        if (count == 3) {
            canvas.drawArc(rectF.right - arcOffset, rectF.top - arcOffset, rectF.right + arcOffset, rectF.top +
                    arcOffset, 90, 90, false, mPaint);
        }
        if (count < 3) {
            canvas.drawArc(rectF.right - arcOffset, rectF.top - arcOffset, rectF.right + arcOffset, rectF.top +
                    arcOffset, 90, 90, true, mPaint);
        }
        //绘制当前变换下圆形和扇形
        mPaint.setStyle(Paint.Style.STROKE);
        canvas.drawCircle(cenX - offset, cenY + offset, circleRadius, mPaint);
        if (count == 4) {
            canvas.drawArc(rectF.right - arcOffset, rectF.top - arcOffset, rectF.right + arcOffset, rectF.top +
                    arcOffset, 90, 90, false, mPaint);
        }
        if (count > 4 && count < 7) {
            canvas.drawArc(rectF.right - arcOffset, rectF.top - arcOffset, rectF.right + arcOffset, rectF.top +
                    arcOffset, 90, 90, true, mPaint);
        }
        //绘制目前所用色块
        mPaint.setShader(null);
        mPaint.setStyle(Paint.Style.FILL);
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
