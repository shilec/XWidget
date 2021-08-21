package com.scott.xwidget.widget.decorator

import android.graphics.Color
import com.scott.xwidget.Cons
import com.scott.xwidget.drawable.DrawableInfo
import com.scott.xwidget.widget.XImageView

/**
 * @Author:      shijiale
 * @Email:       shilec@126.com
 * @Date:        2021/8/21 7:53 上午
 * @Description:
 */
class ImageViewWidgetDecorator<T: XImageView> : IWidgetDecorator<T> {
    override fun decorate(v: T, normalInfo: DrawableInfo, statedInfo: DrawableInfo) {
        v.onDrawableStateChanged = {
            decorateImageColorFilter(v, normalInfo, statedInfo)
        }

        decorateImageColorFilter(v, normalInfo, statedInfo)
    }

    private fun decorateImageColorFilter(v: T, normalInfo: DrawableInfo, statedInfo: DrawableInfo) {
        if (normalInfo.state == Cons.DrawableState.SELECTED) {
            var color = if (v.isSelected) statedInfo.colorFilter else normalInfo.colorFilter
            if (color == -1) {
                color = Color.TRANSPARENT
            }
            v.setColorFilter(color)
        } else if (normalInfo.state == Cons.DrawableState.PRESSED) {
            var color = if (v.isPressed) statedInfo.colorFilter else normalInfo.colorFilter
            if (color == -1) {
                color = Color.TRANSPARENT
            }
            v.setColorFilter(color)
        }
    }
}