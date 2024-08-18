package com.example.viewdemo;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import java.util.jar.Attributes;

public class MultiTouchView extends View {
 
    private static final int MAX_TOUCH_POINTS = 10; // 最多跟踪10个触点
    private float[] x = new float[MAX_TOUCH_POINTS];
    private float[] y = new float[MAX_TOUCH_POINTS];
    private Paint paint = new Paint();
 
    public MultiTouchView(Context context, AttributeSet attributes) {
        super(context, attributes);
        paint.setColor(Color.BLUE);
        paint.setStrokeWidth(2f);
    }
 
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int action = event.getActionMasked();
        int pointerIndex = event.getActionIndex();
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                // 第一个触点按下
                x[pointerIndex] = event.getX(pointerIndex);
                y[pointerIndex] = event.getY(pointerIndex);
                invalidate(); // 重绘视图
                Log.d("Multi2", "---- " + MotionEvent.actionToString(action) + " -----");
                break;
            case MotionEvent.ACTION_POINTER_DOWN:
                // 第二个或更多触点按下
                x[pointerIndex] = event.getX(pointerIndex);
                y[pointerIndex] = event.getY(pointerIndex);
                invalidate();
                Log.d("Multi2", "---- " + MotionEvent.actionToString(action) + " -----");
                break;
            case MotionEvent.ACTION_MOVE:
                // 触点移动
                final int pointerCount = event.getPointerCount();
                for (int i = 0; i < pointerCount; i++) {
                    x[i] = event.getX(i);
                    y[i] = event.getY(i);
                }
                invalidate();
                break;
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_POINTER_UP:
            case MotionEvent.ACTION_CANCEL:
                // 触点抬起或取消
                final int upPointerIndex = event.getActionIndex();
                x[upPointerIndex] = -1;
                y[upPointerIndex] = -1;
                invalidate();
                break;
        }
        return true;
    }
 
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawColor(Color.WHITE); // 绘制背景
        for (int i = 0; i < MAX_TOUCH_POINTS; i++) {
            if (x[i] != -1) {
                canvas.drawCircle(x[i], y[i], 50, paint); // 绘制圆形
            }
        }
    }
}