package com.scott.xwidget.drawable

import android.graphics.Canvas
import android.graphics.Rect

interface IRender {
    var next: IRender?

    fun draw(canvas: Canvas, bounds: Rect)

    fun onBoundsChanged(left: Int, top: Int, right: Int, bottom: Int): Rect
}