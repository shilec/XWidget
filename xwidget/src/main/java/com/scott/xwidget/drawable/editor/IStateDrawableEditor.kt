package com.scott.xwidget.drawable.editor

import com.scott.xwidget.annotation.StateType
import com.scott.xwidget.drawable.IDrawableDecorator

/**
 * @Author      :   shijiale
 * @Date        :   2022/4/27 10:46 上午
 * @Email       :   shilec@126.com
 * @Description :
 */
interface IStateDrawableEditor : IDrawableDecorator {
    val normalEditor: IDrawableEditor

    val stateEditor: IDrawableEditor

    @StateType val stateType: Int
}