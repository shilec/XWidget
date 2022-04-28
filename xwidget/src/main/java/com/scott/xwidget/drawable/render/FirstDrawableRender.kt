package com.scott.xwidget.drawable.render

import android.graphics.Canvas
import android.graphics.Rect

class FirstDrawableRender: IRender {
    override var next: IRender? = null

    override fun draw(canvas: Canvas, bounds: Rect) {}

    override fun onBoundsChanged(left: Int, top: Int, right: Int, bottom: Int): Rect {
        return Rect(left, top, right, bottom)
    }
}