package com.example.viewdemo

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import kotlin.math.abs
import kotlin.math.max
import kotlin.math.min
import kotlin.math.roundToInt

/**
 * @Author:      shijiale
 * @Email:       shilec@126.com
 * @Date:        2021/4/28 3:00 PM
 * @Description: 区域选择View
 */
class AreaSelectView(context: Context?, attrs: AttributeSet?) :
    View(context, attrs) {
    init {
        isClickable = true
    }

    constructor(context: Context?): this(context, null)

    private val xferMode = PorterDuffXfermode(PorterDuff.Mode.CLEAR)

    private var downX: Float = 0f
    private var downY: Float = 0f

    var onSelected: ((Rect) -> Unit)? = null

    var onTouchDown: ((Point) -> Unit)? = null

    companion object {

        const val TOUCH_BOX_SIZE = 30f
        const val STATE_NEW_PATH = 0
        const val STATE_MOVE = 1
        const val STATE_RESIZE_LEFT_TOP = 2
        const val STATE_RESIZE_RIGHT_TOP = 3
        const val STATE_RESIZE_LEFT_BOTTOM = 4
        const val STATE_RESIZE_RIGHT_BOTTOM = 5
    }

    private var state = STATE_NEW_PATH

    private var selectRect = Rect()

    private fun getStateByPoint(x: Int, y: Int): Int {
        val leftTopRect = Rect(
            (selectRect.left - TOUCH_BOX_SIZE).roundToInt(),
            (selectRect.top - TOUCH_BOX_SIZE).roundToInt(),
            (selectRect.left + TOUCH_BOX_SIZE).toInt(), (selectRect.top + TOUCH_BOX_SIZE).toInt()
        )

        val rightTopRect = Rect(
            (selectRect.right - TOUCH_BOX_SIZE).toInt(),
            (selectRect.top - TOUCH_BOX_SIZE).roundToInt(),
            ((selectRect.right + TOUCH_BOX_SIZE).roundToInt()), (selectRect.top + TOUCH_BOX_SIZE).toInt()
        )

        val leftBottomRect = Rect(
            (selectRect.left - TOUCH_BOX_SIZE).roundToInt(), (selectRect.bottom - TOUCH_BOX_SIZE).toInt(),
            (selectRect.left + TOUCH_BOX_SIZE).toInt(), ((selectRect.bottom + TOUCH_BOX_SIZE).roundToInt())
        )

        val rightBottomRect = Rect(
            (selectRect.right - TOUCH_BOX_SIZE).toInt(),
            (selectRect.bottom - TOUCH_BOX_SIZE).toInt(),
            ((selectRect.right + TOUCH_BOX_SIZE).roundToInt()), ((selectRect.bottom + TOUCH_BOX_SIZE).roundToInt())
        )

        if (leftTopRect.contains(x, y)) {
            return STATE_RESIZE_LEFT_TOP
        }

        if (rightTopRect.contains(x, y)) {
            return STATE_RESIZE_RIGHT_TOP
        }

        if (leftBottomRect.contains(x, y)) {
            return STATE_RESIZE_LEFT_BOTTOM
        }

        if (rightBottomRect.contains(x, y)) {
            return STATE_RESIZE_RIGHT_BOTTOM
        }

        if (selectRect.contains(x, y)) {
            return STATE_MOVE
        }

        return STATE_NEW_PATH
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        when (event?.action) {
            MotionEvent.ACTION_DOWN -> {

                state = getStateByPoint(event.x.toInt(), event.y.toInt())

                downX = event.x
                downY = event.y
                onTouchDown?.invoke(Point(event.x.toInt(), event.y.toInt()))
            }

            MotionEvent.ACTION_MOVE -> {
                when (state) {
                    STATE_NEW_PATH -> {
                        val currentY = event.y
                        val currentX = event.x
                        val left = min(downX, currentX)
                        val right = max(downX, currentX)
                        val top = min(downY, currentY)
                        val bottom = max(downY, currentY)
                        selectRect = Rect(left.toInt(), top.toInt(), right.toInt(), bottom.toInt())
                        postInvalidate()
                    }

                    STATE_MOVE -> {
                        val offsetX = if (event.x - downX < 0) {
                            max(-selectRect.left, (event.x - downX).roundToInt())
                        } else {
                            min((event.x - downX).roundToInt(), right - selectRect.right)
                        }

                        val offsetY = if (event.y - downY < 0) {
                            max(-selectRect.top, (event.y - downY).roundToInt())
                        } else {
                            min((event.y - downY).roundToInt(), bottom - selectRect.bottom)
                        }
                        selectRect.offset(offsetX, offsetY)
                        downX = event.x
                        downY = event.y
                        postInvalidate()
                    }

                    STATE_RESIZE_LEFT_TOP -> {
                        val offsetX = if (event.x - downX < 0) {
                            max(-selectRect.left, (event.x - downX).roundToInt())
                        } else {
                            min(abs(selectRect.left - (selectRect.right - TOUCH_BOX_SIZE)), abs(event.x - downX)).roundToInt()
                        }

                        val offsetY = if (event.y - downY < 0) {
                            max(-selectRect.top, (event.y - downY).roundToInt())
                        } else {
                            min(abs(selectRect.top - (selectRect.bottom - TOUCH_BOX_SIZE)), abs(event.y - downY)).roundToInt()
                        }
                        selectRect = Rect(selectRect.left + offsetX, selectRect.top + offsetY, selectRect.right, selectRect.bottom)
                        downX = event.x
                        downY = event.y
                        postInvalidate()
                    }

                    STATE_RESIZE_RIGHT_TOP -> {
                        val offsetX = if (event.x - downX > 0) {
                            min(abs(right - selectRect.right), (event.x - downX).roundToInt())
                        } else {
                            max(-abs(selectRect.right - (selectRect.left + TOUCH_BOX_SIZE)), event.x - downX).roundToInt()
                        }

                        val offsetY = if (event.y - downY < 0) {
                            max(-selectRect.top, (event.y - downY).roundToInt())
                        } else {
                            min(abs(selectRect.top - (selectRect.bottom - TOUCH_BOX_SIZE)), abs(event.y - downY)).roundToInt()
                        }
                        selectRect = Rect(selectRect.left, selectRect.top + offsetY, selectRect.right + offsetX, selectRect.bottom)
                        downX = event.x
                        downY = event.y
                        postInvalidate()
                    }

                    STATE_RESIZE_RIGHT_BOTTOM -> {
                        val offsetX = if (event.x - downX > 0) {
                            min(abs(right - selectRect.right), (event.x - downX).roundToInt())
                        } else {
                            max(-abs(selectRect.right - (selectRect.left + TOUCH_BOX_SIZE)), event.x - downX).roundToInt()
                        }

                        val offsetY = if (event.y - downY > 0) {
                            min(abs(bottom - selectRect.bottom), (event.y - downY).roundToInt())
                        } else {
                            max(-abs(selectRect.bottom - (selectRect.top + TOUCH_BOX_SIZE)), event.y - downY).roundToInt()
                        }
                        selectRect = Rect(selectRect.left, selectRect.top, selectRect.right + offsetX, selectRect.bottom + offsetY)
                        downX = event.x
                        downY = event.y
                        postInvalidate()
                    }

                    STATE_RESIZE_LEFT_BOTTOM -> {
                        val offsetX = if (event.x - downX < 0) {
                            max(-selectRect.left, (event.x - downX).roundToInt())
                        } else {
                            min(abs(selectRect.left - (selectRect.right - TOUCH_BOX_SIZE)), abs(event.x - downX)).roundToInt()
                        }

                        val offsetY = if (event.y - downY > 0) {
                            min(abs(bottom - selectRect.bottom), (event.y - downY).roundToInt())
                        } else {
                            max(-abs(selectRect.bottom - (selectRect.top + TOUCH_BOX_SIZE)), event.y - downY).roundToInt()
                        }
                        selectRect = Rect(selectRect.left + offsetX, selectRect.top, selectRect.right, selectRect.bottom + offsetY)
                        downX = event.x
                        downY = event.y
                        postInvalidate()
                    }
                }
            }

            MotionEvent.ACTION_UP -> {
               onSelected?.invoke(selectRect)
            }
        }
        return super.onTouchEvent(event)
    }

    private fun reset() {
        downX = 0f
        downY = 0f
        selectRect = Rect()
        postInvalidate()
    }

    private val paint = Paint().run {
        isAntiAlias = true
        color = Color.parseColor("#FF0000")
        strokeWidth = 6F
        style = Paint.Style.STROKE
        this
    }

    private val strokePaint = Paint().run {
        isAntiAlias = true
        color = Color.parseColor("#FF0000")
        strokeWidth = 6F
        style = Paint.Style.STROKE
        this
    }

    override fun draw(canvas: Canvas?) {
        super.draw(canvas)

        val rect = selectRect
        if (!rect.isEmpty) {
            val shape = Path()
            shape.addRect(RectF(rect), Path.Direction.CCW)
            canvas?.drawMaskShape(measuredWidth.toFloat(), measuredHeight.toFloat(), Color.parseColor("#66000000"), shape, paint)

            var hBoxSize = TOUCH_BOX_SIZE
            var wBoxSize = TOUCH_BOX_SIZE
            if (rect.width() < 2 * TOUCH_BOX_SIZE) {
                wBoxSize = rect.width() / 2f
            }

            if (rect.height() < 2 * TOUCH_BOX_SIZE) {
                hBoxSize = rect.height() / 2f
            }

            val leftTop = Path()
            leftTop.moveTo(rect.left.toFloat(), rect.top + hBoxSize)
            leftTop.lineTo(rect.left.toFloat(), rect.top.toFloat())
            leftTop.lineTo(rect.left + wBoxSize, rect.top.toFloat())

            canvas?.drawPath(leftTop, strokePaint)

            val rightTop = Path()
            rightTop.moveTo(rect.right.toFloat() - wBoxSize, rect.top.toFloat())
            rightTop.lineTo(rect.right.toFloat(), rect.top.toFloat())
            rightTop.lineTo(rect.right.toFloat(), rect.top.toFloat() + hBoxSize)

            canvas?.drawPath(rightTop, strokePaint)

            val leftBottom = Path()
            leftBottom.moveTo(rect.left.toFloat(), rect.bottom.toFloat() - hBoxSize)
            leftBottom.lineTo(rect.left.toFloat(), rect.bottom.toFloat())
            leftBottom.lineTo(rect.left.toFloat() + wBoxSize, rect.bottom.toFloat())

            canvas?.drawPath(leftBottom, strokePaint)

            val rightBottom = Path()
            rightBottom.moveTo(rect.right.toFloat(), rect.bottom.toFloat() - hBoxSize)
            rightBottom.lineTo(rect.right.toFloat(), rect.bottom.toFloat())
            rightBottom.lineTo(rect.right.toFloat() - wBoxSize, rect.bottom.toFloat())

            canvas?.drawPath(rightBottom, strokePaint)
        } else {
            canvas?.drawColor(Color.parseColor("#66000000"))
        }
    }

    fun Canvas.drawMaskShape(w: Float, h: Float, color: Int, shape: Path, paint: Paint?) {
        drawMaskShape(this, paint, w, h, color, shape)
    }

    fun drawMaskShape(canvas: Canvas?, paint: Paint?, maskW: Float, maskH: Float, maskOutsideColor: Int, shape: Path) {
        paint?.apply {
            isDither = true
            isFilterBitmap = true

            val sc = canvas?.saveLayer(
                0f,
                0f,
                maskW,
                maskH,
                paint
            )

            color = maskOutsideColor
            canvas?.drawRect(0f, 0f, maskW, maskH, this)

            xfermode = xferMode
            color = Color.BLUE
            style = Paint.Style.FILL

            canvas?.drawPath(shape, this)

            xfermode = null
            sc?.let { canvas.restoreToCount(it) }
        }
    }
}