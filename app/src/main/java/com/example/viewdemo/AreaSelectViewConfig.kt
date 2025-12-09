package com.example.viewdemo

import android.graphics.Color
import android.graphics.Paint
import android.graphics.Rect

/**
 * AreaSelectView配置类，提供Builder模式配置
 */
data class AreaSelectViewConfig(
    val strokeColor: Int = Color.parseColor("#FF0000"),
    val strokeWidth: Float = 6f,
    val maskColor: Int = Color.parseColor("#66000000"),
    val touchBoxSize: Float = 30f
) {
    class Builder {
        private var strokeColor: Int = Color.parseColor("#FF0000")
        private var strokeWidth: Float = 6f
        private var maskColor: Int = Color.parseColor("#66000000")
        private var touchBoxSize: Float = 30f

        fun strokeColor(color: Int) = apply { this.strokeColor = color }
        fun strokeWidth(width: Float) = apply { this.strokeWidth = width }
        fun maskColor(color: Int) = apply { this.maskColor = color }
        fun touchBoxSize(size: Float) = apply { this.touchBoxSize = size }

        fun build() = AreaSelectViewConfig(strokeColor, strokeWidth, maskColor, touchBoxSize)
    }
}
