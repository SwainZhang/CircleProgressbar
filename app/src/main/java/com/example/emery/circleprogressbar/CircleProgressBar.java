package com.example.emery.circleprogressbar;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.os.Build;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by emery on 2017/8/17.
 */

public class CircleProgressBar extends View {
    public int getProgress() {
        return progress;
    }

    public void setProgress(int progress) {
        this.progress = progress;
        postInvalidate();
    }

    public Paint getPaint() {
        return mPaint;
    }

    public void setPaint(Paint paint) {
        mPaint = paint;
    }

    private  int progress;
    private Paint mPaint;

    public CircleProgressBar(Context context) {
        super(context);
    }

    public CircleProgressBar(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onDraw(final Canvas canvas) {
        super.onDraw(canvas);
        final int width = getWidth() / 2;
        final int height = getHeight() / 2;
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setStrokeWidth(50);
        mPaint.setStyle(Paint.Style.STROKE);
        canvas.drawCircle(width, height, 300, mPaint);
        drawProgress(canvas, width, height, progress,100);

    }


    private void drawProgress(Canvas canvas, int width, int height,int  progress,int max) {
        mPaint.reset();
        mPaint.setColor(Color.RED);
        mPaint.setAntiAlias(true);
        mPaint.setTextSize(60);
        canvas.drawText(String.valueOf(progress),width-30,height+30,mPaint);

        mPaint.reset();
        mPaint.setAntiAlias(true);
        mPaint.setStrokeWidth(50);
        mPaint.setColor(Color.RED);
        mPaint.setStyle(Paint.Style.STROKE);

        float left=width-300;
        float top=height-300;
        float right=width+300;
        float bottom=height+300;
        RectF rectF=new RectF(left,top,right,bottom);
        float angle=progress*360/max;
        canvas.drawArc(rectF,-90,angle,false,mPaint);
    }
}
