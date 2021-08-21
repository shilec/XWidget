package com.example.viewdemo

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.widget.ImageView
import androidx.appcompat.widget.AppCompatImageView

/**
 * @Author:      shijiale
 * @Email:       shilec@126.com
 * @Date:        2021/8/21 8:40 上午
 * @Description:
 */
class TestImageView(context: Context?, attrs: AttributeSet?) : AppCompatImageView(context!!, attrs) {
    override fun drawableStateChanged() {
        super.drawableStateChanged()
        Log.e("ImageView", "--- ${android.R.attr.state_pressed}")
        Log.e("ImageView", "--- ${android.R.attr.state_selected}")
        Log.e("ImageView", "--- state = ${drawableState.toList()}")
    }
}