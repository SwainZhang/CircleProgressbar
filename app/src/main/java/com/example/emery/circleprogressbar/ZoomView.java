package com.example.emery.circleprogressbar;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Shader;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by emery on 2017/8/19.
 */

public class ZoomView extends View {

    private Bitmap mBitmap;
    private Paint mPaint;
    private Matrix mMatrix;
    private ShapeDrawable mShapeDrawable;

    public ZoomView(Context context) {
        super(context);
    }

    public ZoomView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inSampleSize = 3;
        mBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.head, options);
        mPaint = new Paint();
        mPaint.setAntiAlias(true);

        Bitmap oldBitmap=mBitmap;

        mMatrix = new Matrix();
        //放大镜图
        Bitmap scaledBitmap = Bitmap.createScaledBitmap(oldBitmap, oldBitmap.getWidth()/10, oldBitmap
                .getHeight() /10, true);
        BitmapShader bitmapShader=new BitmapShader(scaledBitmap, Shader.TileMode.CLAMP, Shader
                .TileMode.CLAMP);
        mShapeDrawable = new ShapeDrawable(new OvalShape());
        mShapeDrawable.getPaint().setShader(bitmapShader);
        mShapeDrawable.setBounds(0,0,100,100);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
       //背景
        canvas.drawBitmap(mBitmap,0,0,mPaint);
        mShapeDrawable.draw(canvas);

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int x = (int) event.getX();
        int y = (int) event.getX();
        mMatrix.setTranslate(50/2-x/10,50/2-y/10);
        mShapeDrawable.getPaint().getShader().setLocalMatrix(mMatrix);
        mShapeDrawable.setBounds(x-50/2,y-50/2,x+50/2,y+50/2);
        invalidate();
        return true;

    }
}
