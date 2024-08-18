package com.example.viewdemo

import android.content.Context
import android.graphics.Point
import android.graphics.Rect
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import kotlin.math.min

/**
 * @Author:      shijiale
 * @Email:       shilec@126.com
 * @Date:        2024/3/30 5:53 PM
 * @Description:
 */
class AreaSelectContainerView(context: Context, attrs: AttributeSet?) :
    FrameLayout(context, attrs) {

    var onSelected: ((Rect) -> Unit)? = null

    var onTouchDown: ((Point) -> Unit)? = null

    private var menuView: View? = null

    init {
        val areaSelectView = AreaSelectView(context)
        areaSelectView.onSelected = this::onSelected
        areaSelectView.onTouchDown = this::onTouchDown
        addView(areaSelectView, LayoutParams(LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT))

        val menuView = LayoutInflater.from(context).inflate(R.layout.layout_select_menu_layout, this, false)
        menuView.findViewById<View>(R.id.tv_cancel).setOnClickListener(this::onClickCancel)
        menuView.findViewById<View>(R.id.tv_submit).setOnClickListener(this::onClickSubmit)
        addView(menuView, LayoutParams(LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT))
        menuView.visibility = View.INVISIBLE
        this.menuView = menuView
    }

    private fun onClickSubmit(v: View) {

    }

    private fun onClickCancel(v: View) {

    }

    private fun onTouchDown(point: Point) {
        menuView?.visibility = View.GONE
    }

    private fun onSelected(rect: Rect) {
        val lp = menuView?.layoutParams as? MarginLayoutParams?

        val menuWidth = menuView?.measuredWidth ?: 0
        val menuHeight = menuView?.measuredHeight ?: 0

        if (menuWidth > rect.width()) {
            var leftMargin = rect.left - (menuWidth - rect.width()) / 2
            if (leftMargin < 0) {
                leftMargin = 0
            } else if (leftMargin > measuredWidth - menuWidth) {
                leftMargin = measuredWidth - menuWidth
            }
            lp?.leftMargin = leftMargin
        } else {
            lp?.leftMargin = rect.left + (rect.width() - menuWidth) / 2
        }

        if (rect.bottom + menuHeight + 10 > bottom) {
            lp?.topMargin = rect.top - menuHeight - 10
        } else {
            lp?.topMargin = rect.bottom + 10
        }

        menuView?.layoutParams = lp
        menuView?.visibility = View.VISIBLE
    }
}