package com.scott.xwidget.utils

import com.scott.xwidget.drawable.editor.IDrawableEditTransaction

object ParseUtils {
    fun getCornersByType(cornerType: Int, corner: Float): FloatArray {
        val corners = floatArrayOf(0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f)
        if (cornerType and IDrawableEditTransaction.CornerType.LEFT_TOP == IDrawableEditTransaction.CornerType.LEFT_TOP) { // top-left
            corners[0] = corner
            corners[1] = corner
        }
        if (cornerType and IDrawableEditTransaction.CornerType.RIGHT_TOP == IDrawableEditTransaction.CornerType.RIGHT_TOP) { // top-right
            corners[2] = corner
            corners[3] = corner
        }
        if (cornerType and IDrawableEditTransaction.CornerType.RIGHT_BOTTOM == IDrawableEditTransaction.CornerType.RIGHT_BOTTOM) { // bottom-right
            corners[6] = corner
            corners[7] = corner
        }
        if (cornerType and IDrawableEditTransaction.CornerType.LEFT_BOTTOM == IDrawableEditTransaction.CornerType.LEFT_BOTTOM) { // bottom-left
            corners[4] = corner
            corners[5] = corner
        }

        if (cornerType == IDrawableEditTransaction.CornerType.ALL) {
            for (i in IntRange(0, 7)) {
                corners[i] = corner
            }
        }
        return corners
    }
}