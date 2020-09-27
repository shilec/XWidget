package com.scott.xwidget.utils

object ParseUtils {
    fun getCornersByType(cornerType: Int, corner: Float): FloatArray {
        val corners = floatArrayOf(0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f)
        if (cornerType and 0x01 == 0x01) { // top-left
            corners[0] = corner
            corners[1] = corner
        }
        if (cornerType and 0x08 == 0x8) { // top-right
            corners[2] = corner
            corners[3] = corner
        }
        if (cornerType and 0x16 == 0x16) { // bottom-right
            corners[6] = corner
            corners[7] = corner
        }
        if (cornerType and 0x32 == 0x32) { // bottom-left
            corners[4] = corner
            corners[5] = corner
        }

        if (cornerType == 0) {
            for (i in IntRange(0, 7)) {
                corners[i] = corner
            }
        }
        return corners
    }
}