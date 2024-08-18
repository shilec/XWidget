package com.example.viewdemo

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.PointF
import android.graphics.Rect
import android.graphics.RectF
import android.util.AttributeSet
import android.util.Log
import android.util.SparseArray
import android.view.MotionEvent
import android.view.View
import android.view.ViewConfiguration
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.toBitmap
import androidx.core.util.set
import kotlin.math.abs
import kotlin.math.max
import kotlin.math.min

/**
 * @Author:      shijiale
 * @Email:       shilec@126.com
 * @Date:        2024/7/31 8:59 PM
 * @Description:
 */
class MultiTouchGestureView(context: Context?, attrs: AttributeSet?) : View(context, attrs) {
    init {

    }

    private val gesturePathData = GesturePathData()

//    fun geGestureData(): GesturePathData {
//        val d = gesturePathData.clone()
//        val rect = gestureRect
//
//        // 减去窗口的偏移量，返回相对的位置
//        d.updatePathData {
//            it.x -= rect.left
//            it.y -= rect.top
//        }
//
//        d.left = rect.left.toFloat()
//        d.top = rect.top.toFloat()
//
////        LogUtils.d("GestureView", "--- gesture data = $d")
//        return d
//    }

    private val multiTouchPathList = SparseArray<SerializablePath>()

    companion object {
        const val STROKE_WIDTH = 15
    }

    private val paint = Paint().let {
        it.style = Paint.Style.STROKE
        it.strokeWidth = 45F
        it.isAntiAlias = true
        it.strokeCap = Paint.Cap.ROUND
        it.color = Color.RED
        it
    }

    private val textPaint = Paint().let {
        it.style = Paint.Style.FILL
        it.isAntiAlias = true
        it.color = Color.WHITE
        it.textSize = 25f
        it.strokeWidth = 3f
        it
    }

    override fun draw(canvas: Canvas?) {
        super.draw(canvas)

        for (path in gesturePathData.gestures) {
            canvas?.drawPath(path, paint)
        }

//        Log.d("Multi", "--- draw --- ${gesturePathData.gestures.size}")

        // 按下时，绘制手势起始点
//        if (gesturePathData.gestures.isNotEmpty()) {
//            drawEdgeAnchor(startAnchorBmp, canvas, downOriginX, downOriginY)
//        }
    }

//    private val startAnchorBmp: Bitmap? = null
//        get() {
//            field?.recycle()
//            val drawable =
//                ContextCompat.getDrawable(appContext, R.drawable.ic_anchor_ic_red) ?: return null
//            val bmp = drawable.toBitmap(anchorSize, anchorSize)
//            drawIndexText(bmp)
//            return bmp
//        }

//    private fun drawIndexText(bmp: Bitmap) {
//        val cv = Canvas(bmp)
//
//        val index = ActionExecutorEngine.getExecutorsCount() + 1
//        val x = (bmp.width - textPaint.measureText("${index}")) / 2f
//        val y = bmp.height / 2 + textPaint.textSize / 2 - textPaint.descent() / 2
//        cv.drawText("$index", x, y, textPaint)
//    }

//    private val anchorSize = CustomPathView.ANCHOR_SIZE

//    private fun drawEdgeAnchor(bmp: Bitmap?, canvas: Canvas?, x: Float, y: Float) {
//        val rect =
//            RectF(x - anchorSize / 2, y - anchorSize / 2, x + anchorSize / 2, y + anchorSize / 2)
//        if (bmp != null) {
//            canvas?.drawBitmap(bmp, null, rect, paint)
//        }
//    }

    fun reset() {
        gesturePathData.gestures.clear()
        isGestured = false
        postInvalidate()
    }

    private var minX: Float = 0F
    private var minY: Float = 0F
    private var maxX: Float = 0F
    private var maxY: Float = 0F

    var isGestured = false
        private set

//    val gestureRect: Rect
//        get() = Rect(
//            minX.toInt() - STROKE_WIDTH.dp / 2,
//            minY.toInt() - STROKE_WIDTH.dp / 2,
//            maxX.toInt() + STROKE_WIDTH.dp / 2,
//            maxY.toInt() + STROKE_WIDTH.dp / 2
//        )

    var onGestureTouch: ((MotionEvent) -> Unit)? = null

    @SuppressLint("ClickableViewAccessibility")
    override fun onTouchEvent(event: MotionEvent?): Boolean {
        event ?: return false

        val pointerIndex = event.actionIndex
        when (event.actionMasked) {
            MotionEvent.ACTION_POINTER_DOWN -> {
                val path = SerializablePath()
                path.moveTo(event.getX(pointerIndex), event.getY(pointerIndex))
                multiTouchPathList.put(pointerIndex, path)
                gesturePathData.addPath(path)
                invalidate()
                Log.d("Multi", "--- index = ${pointerIndex}, eventType = ${MotionEvent.actionToString(event.action)}")
            }
            MotionEvent.ACTION_DOWN -> {
                val path = SerializablePath()
                path.moveTo(event.getX(pointerIndex), event.getY(pointerIndex))
                multiTouchPathList.put(pointerIndex, path)
                gesturePathData.addPath(path)
                invalidate()
                Log.d("Multi", "--- index = ${pointerIndex}, eventType = ${MotionEvent.actionToString(event.action)}")
            }

            MotionEvent.ACTION_MOVE -> {
                for (i in 0 until event.pointerCount) {
                    val path = multiTouchPathList.get(i)
                    if (path == null) continue
                    path.lineTo(event.getX(i), event.getY(i))
                }
                Log.d("Multi", "--- index = ${pointerIndex}, eventType = ${MotionEvent.actionToString(event.action)}")
                invalidate()
            }

            MotionEvent.ACTION_CANCEL,
            MotionEvent.ACTION_UP,
            MotionEvent.ACTION_POINTER_UP -> {
                Log.d("Multi", "--- index = ${pointerIndex}, eventType = ${MotionEvent.actionToString(event.action)}")
                multiTouchPathList.remove(pointerIndex)
                invalidate()
            }
        }

//        when (event.action) {
//            MotionEvent.ACTION_POINTER_DOWN,
//            MotionEvent.ACTION_DOWN -> {
//                // 每次down事件都创建一个新的path对象
//                val path = SerializablePath()
//                gesturePathData.addPath(path)
//
//                path.moveTo(event.getX(pointerIndex), event.getY(pointerIndex))
//                currentPath.put(pointerId, path)
//                isGestured = false
//
//                downPoints.put(pointerId, PointF(event.getX(pointerIndex), event.getY(pointerIndex)))
//                Log.d("Gesture", "--- touch down --- $pointerId")
//            }
//
//            MotionEvent.ACTION_MOVE -> {
//                Log.d("Gesture", "--- touch move ---$pointerId")
//
//                for (i in 0 until event.pointerCount) {
//                    val pId = event.getPointerId(i)
//                    val p = currentPath[pId]
//                    val minMove = ViewConfiguration.get(context).scaledTouchSlop
//                    val downPoint = downPoints[pId]
//
//                    // 小于滑动距离，不计算入滑动
//                    if (abs(event.x - downPoint.x) <= minMove && abs(event.y - downPoint.y) <= minMove) continue
//
//                    // 绘制手势
//                    p.lineTo(event.x, event.y)
//                    postInvalidate()
//
//                    downPoints.put(pId, PointF(event.getX(pointerIndex), event.getY(pointerIndex)))
//
//                    isGestured = true
//
//                    minX = min(event.rawX, minX)
//                    minY = min(event.rawY, minY)
//                    maxX = max(event.rawX, maxX)
//                    maxY = max(event.rawY, maxY)
//
//                    Log.d("Gesture", "--- move path = $pId")
//                }
//            }
//
//            MotionEvent.ACTION_UP -> {
//                Log.d("Gesture", "--- touch up ---$pointerId")
//                if (!isGestured) {
//                    reset()
//                }
//                downPoints.remove(pointerId)
//                currentPath.remove(pointerId)
//            }
//        }
//
//        // 所有事件都向外通知，外部处理保存手势等逻辑
//        if (event != null) {
//            onGestureTouch?.invoke(event)
//        }
        return true
    }
}