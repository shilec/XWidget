package com.scott.xwidget.drawable

import android.graphics.drawable.Drawable

/**
 * @Author      :   shijiale
 * @Date        :   2022/4/27 10:44 上午
 * @Email       :   shilec@126.com
 * @Description :
 */
interface IDrawableDecorator {
    fun asDrawable(): Drawable
}