package com.example.emery.circleprogressbar;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.RadialGradient;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.SweepGradient;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by emery on 2017/8/19.
 */

public class ShaderView extends View {

    public ShaderView(Context context) {
        super(context);
    }

    public ShaderView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawColor(Color.WHITE);

        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inSampleSize = 5;
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.head, options);
        BitmapShader shader = new BitmapShader(bitmap, Shader.TileMode.CLAMP, Shader.TileMode
                .REPEAT);

        Matrix matrix = new Matrix();
        matrix.setScale(1.2f, 1.2f);

        shader.setLocalMatrix(matrix);
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();

        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setShader(shader);

        RectF rectF = new RectF(0, 0, height, width);
        // canvas.drawOval(rectF,paint);


        ShapeDrawable shapeDrawable = new ShapeDrawable(new OvalShape());
        Paint paint1 = shapeDrawable.getPaint();
        paint1.setShader(shader);
        shapeDrawable.setBounds(0, 0, width, height);
        //  shapeDrawable.draw(canvas);
        int[] colors = new int[]{
                Color.BLACK,
                Color.DKGRAY,
                Color.GRAY,
                Color.LTGRAY,
                Color.WHITE,
                Color.RED,
                Color.GREEN,
                Color.BLUE,
                Color.YELLOW,
                Color.CYAN,
                Color.MAGENTA,};

        LinearGradient linearGradient = new LinearGradient(0, 0, 400, 400, colors, null, Shader
                .TileMode.REPEAT);
        paint.reset();
        paint.setShader(linearGradient);
        //  canvas.drawRect(0,0,400,400,paint);

        RadialGradient radialGradient = new RadialGradient(300, 300, 300, colors, null, Shader
                .TileMode.CLAMP);
        paint.reset();
        paint.setAntiAlias(true);
        paint.setShader(radialGradient);
        //canvas.drawCircle(300, 300, 300, paint);

        SweepGradient sweepGradient=new SweepGradient(300,300,Color.RED,Color.WHITE);
        paint.reset();
        paint.setAntiAlias(true);
        paint.setShader(sweepGradient);
        canvas.drawCircle(300, 300, 300, paint);

    }
}
