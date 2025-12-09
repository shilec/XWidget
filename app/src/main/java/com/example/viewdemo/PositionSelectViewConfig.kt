package com.example.viewdemo

import android.graphics.Color

/**
 * PositionSelectView配置类，提供Builder模式配置
 */
data class PositionSelectViewConfig(
    val rectBorderColor: Int = Color.parseColor("#FF0000"),
    val rectBorderWidth: Float = 5f,
    val circleRadius: Float = 20f,
    val centerCircleColor: Int = Color.parseColor("#00FF00"),
    val offsetCircleColor: Int = Color.parseColor("#0000FF"),
    val lineColor: Int = Color.parseColor("#CCCCCC"),
    val lineWidth: Float = 13f,
    val touchSize: Float = 60f
) {
    class Builder {
        private var rectBorderColor: Int = Color.parseColor("#FF0000")
        private var rectBorderWidth: Float = 5f
        private var circleRadius: Float = 20f
        private var centerCircleColor: Int = Color.parseColor("#00FF00")
        private var offsetCircleColor: Int = Color.parseColor("#0000FF")
        private var lineColor: Int = Color.parseColor("#CCCCCC")
        private var lineWidth: Float = 13f
        private var touchSize: Float = 60f

        fun rectBorderColor(color: Int) = apply { this.rectBorderColor = color }
        fun rectBorderWidth(width: Float) = apply { this.rectBorderWidth = width }
        fun circleRadius(radius: Float) = apply { this.circleRadius = radius }
        fun centerCircleColor(color: Int) = apply { this.centerCircleColor = color }
        fun offsetCircleColor(color: Int) = apply { this.offsetCircleColor = color }
        fun lineColor(color: Int) = apply { this.lineColor = color }
        fun lineWidth(width: Float) = apply { this.lineWidth = width }
        fun touchSize(size: Float) = apply { this.touchSize = size }

        fun build() = PositionSelectViewConfig(
            rectBorderColor,
            rectBorderWidth,
            circleRadius,
            centerCircleColor,
            offsetCircleColor,
            lineColor,
            lineWidth,
            touchSize
        )
    }
}
