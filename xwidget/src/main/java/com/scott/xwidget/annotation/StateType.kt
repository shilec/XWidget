package com.scott.xwidget.annotation

import androidx.annotation.IntDef

/**
 * @Author      :   shijiale
 * @Date        :   2022/4/26 6:27 下午
 * @Email       :   shilec@126.com
 * @Description :  
 */
@Target(AnnotationTarget.CLASS, AnnotationTarget.PROPERTY, AnnotationTarget.VALUE_PARAMETER)
@IntDef(
    StateType.TYPE_NONE,
    StateType.TYPE_CHECKED,
    StateType.TYPE_PRESSED,
    StateType.TYPE_SELECTED
)
@kotlin.annotation.Retention(AnnotationRetention.SOURCE)
annotation class StateType {
    companion object {
        const val TYPE_NONE = 0
        const val TYPE_PRESSED = 1
        const val TYPE_SELECTED = 2
        const val TYPE_CHECKED = 3
    }
}
