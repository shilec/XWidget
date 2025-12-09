package com.example.viewdemo

import android.graphics.Color

/**
 * 统一的View配置管理类
 * 提供默认配置和主题配置
 */
object ViewConfigManager {
    
    /**
     * 默认区域选择View配置
     */
    val defaultAreaSelectConfig = AreaSelectViewConfig(
        strokeColor = Color.parseColor("#FF0000"),
        strokeWidth = 6f,
        maskColor = Color.parseColor("#66000000"),
        touchBoxSize = 30f
    )

    /**
     * 默认位置选择View配置
     */
    val defaultPositionSelectConfig = PositionSelectViewConfig(
        rectBorderColor = Color.parseColor("#FF0000"),
        rectBorderWidth = 5f,
        circleRadius = 20f,
        centerCircleColor = Color.parseColor("#00FF00"),
        offsetCircleColor = Color.parseColor("#0000FF"),
        lineColor = Color.parseColor("#CCCCCC"),
        lineWidth = 13f,
        touchSize = 60f
    )

    /**
     * 创建区域选择View配置的Builder
     */
    fun areaSelectConfig() = AreaSelectViewConfig.Builder()

    /**
     * 创建位置选择View配置的Builder
     */
    fun positionSelectConfig() = PositionSelectViewConfig.Builder()

    /**
     * 应用默认配置到AreaSelectView
     */
    fun applyDefaultConfig(view: AreaSelectView) {
        view.setConfig(defaultAreaSelectConfig)
    }

    /**
     * 应用默认配置到PositionSelectView
     */
    fun applyDefaultConfig(view: PositionSelectView) {
        view.setConfig(defaultPositionSelectConfig)
    }
}
