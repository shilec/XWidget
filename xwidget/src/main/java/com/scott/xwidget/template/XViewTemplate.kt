package com.scott.xwidget.template

import android.content.Context
import android.util.AttributeSet
import android.view.View
import com.scott.xwidget.XWidget

/**
 * @Author:      shijiale
 * @Email:       shilec@126.com
 * @Date:        2022/3/20 11:19 上午
 * @Description: 模板类
 */
class XViewTemplate(context: Context?, attrs: AttributeSet?) : View(context, attrs) {
   init {
       XWidget.inject(this, attrs)
   }
}