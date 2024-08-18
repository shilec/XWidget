package com.example.viewdemo;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;

public class CustomGestureView extends View {
    private static final int LONG_PRESS_TIMEOUT = ViewConfiguration.getLongPressTimeout();
    private static final int DOUBLE_TAP_TIMEOUT = ViewConfiguration.getDoubleTapTimeout();

    private Handler handler;
    private Runnable longPressRunnable;
    private boolean isLongPress;
    private long lastTapTime;
    private float startX, startY;
    private float lastX, lastY;
    private boolean isDragging;
    private int activePointerId;

    public CustomGestureView(Context context) {
        super(context);
        init();
    }

    public CustomGestureView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public CustomGestureView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        handler = new Handler(Looper.getMainLooper());
        longPressRunnable = new Runnable() {
            @Override
            public void run() {
                isLongPress = true;
                handleLongPress();
                isDragging = true; // 开始拖拽
            }
        };
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int action = event.getActionMasked();
        int pointerCount = event.getPointerCount();

        switch (action) {
            case MotionEvent.ACTION_DOWN:
                isLongPress = false;
                isDragging = false;
                startX = event.getX();
                startY = event.getY();
                lastX = startX;
                lastY = startY;
                activePointerId = event.getPointerId(0);
                handler.postDelayed(longPressRunnable, LONG_PRESS_TIMEOUT);

                if (System.currentTimeMillis() - lastTapTime < DOUBLE_TAP_TIMEOUT) {
                    handleDoubleTap();
                }

                lastTapTime = System.currentTimeMillis();
                break;

            case MotionEvent.ACTION_POINTER_DOWN:
                handleMultiTouch(event);
                break;

            case MotionEvent.ACTION_MOVE:
                if (isDragging) {
                    int pointerIndex = event.findPointerIndex(activePointerId);
                    float currentX = event.getX(pointerIndex);
                    float currentY = event.getY(pointerIndex);
                    float dx = currentX - lastX;
                    float dy = currentY - lastY;
                    handleDrag(dx, dy);

                    lastX = currentX;
                    lastY = currentY;
                } else {
                    handleMove(event);
                }
                break;

            case MotionEvent.ACTION_UP:
                handler.removeCallbacks(longPressRunnable);
                if (!isLongPress) {
                    if (pointerCount > 1) {
                        handleMultiTouchRelease(event);
                    } else {
                        handleSingleTap();
                    }
                }
                isDragging = false;
                break;

            case MotionEvent.ACTION_CANCEL:
                handler.removeCallbacks(longPressRunnable);
                isDragging = false;
                break;

            case MotionEvent.ACTION_POINTER_UP:
                handlePointerUp(event);
                break;
        }

        return true;
    }

    private void handleSingleTap() {
        // 处理单击事件
        Log.d("CustomGestureView", "Single Tap");
    }

    private void handleDoubleTap() {
        // 处理双击事件
        Log.d("CustomGestureView", "Double Tap");
    }

    private void handleLongPress() {
        // 处理长按事件
        Log.d("CustomGestureView", "Long Press");
    }

    private void handleMove(MotionEvent event) {
        // 处理滑动事件
        Log.d("CustomGestureView", "Move");
    }

    private void handleDrag(float dx, float dy) {
        // 处理拖拽事件，dx 和 dy 是手指移动的距离
        Log.d("CustomGestureView", "Drag dx: " + dx + ", dy: " + dy);
    }

    private void handleMultiTouch(MotionEvent event) {
        // 处理多指点击事件
        Log.d("CustomGestureView", "Multi Touch Down");
    }

    private void handleMultiTouchMove(MotionEvent event) {
        // 处理多指滑动事件
        Log.d("CustomGestureView", "Multi Touch Move");
    }

    private void handleMultiTouchRelease(MotionEvent event) {
        // 处理多指点击释放事件
        int pointerIndex = event.getActionIndex();
        int pointerId = event.getPointerId(pointerIndex);

        if (pointerId == activePointerId) {
            // 如果抬起的是活动的手指，选择新的活动手指
            int newPointerIndex = pointerIndex == 0 ? 1 : 0;
            activePointerId = event.getPointerId(newPointerIndex);
            lastX = event.getX(newPointerIndex);
            lastY = event.getY(newPointerIndex);
        }

        Log.d("CustomGestureView", "Multi Touch Up");
    }

    private void handlePointerUp(MotionEvent event) {
        // 处理多指抬起事件
        int pointerIndex = event.getActionIndex();
        int pointerId = event.getPointerId(pointerIndex);

        if (pointerId == activePointerId) {
            // 如果抬起的是活动的手指，选择新的活动手指
            int newPointerIndex = pointerIndex == 0 ? 1 : 0;
            activePointerId = event.getPointerId(newPointerIndex);
            lastX = event.getX(newPointerIndex);
            lastY = event.getY(newPointerIndex);
        }

        Log.d("CustomGestureView", "Pointer Up");
    }
}
