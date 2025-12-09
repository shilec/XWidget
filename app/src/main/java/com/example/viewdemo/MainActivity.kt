package com.example.viewdemo

import android.graphics.Color
import android.graphics.Rect
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity

/**
 * MainActivity - 演示XWidget的使用
 */
class MainActivity : AppCompatActivity() {
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        
        // 示例：使用配置Builder自定义AreaSelectView
        setupAreaSelectViewExample()
        
        // 示例：使用配置Builder自定义PositionSelectView
        setupPositionSelectViewExample()
    }
    
    /**
     * 示例：配置AreaSelectView
     */
    private fun setupAreaSelectViewExample() {
        // 方式1：使用默认配置
        // ViewConfigManager.applyDefaultConfig(areaSelectView)
        
        // 方式2：使用Builder自定义配置
        val customConfig = ViewConfigManager.areaSelectConfig()
            .strokeColor(Color.BLUE)
            .strokeWidth(8f)
            .maskColor(Color.parseColor("#88000000"))
            .touchBoxSize(40f)
            .build()
        
        // areaSelectView.setConfig(customConfig)
    }
    
    /**
     * 示例：配置PositionSelectView
     */
    private fun setupPositionSelectViewExample() {
        // 方式1：使用默认配置
        // ViewConfigManager.applyDefaultConfig(positionSelectView)
        
        // 方式2：使用Builder自定义配置
        val customConfig = ViewConfigManager.positionSelectConfig()
            .rectBorderColor(Color.RED)
            .rectBorderWidth(6f)
            .circleRadius(25f)
            .centerCircleColor(Color.GREEN)
            .offsetCircleColor(Color.BLUE)
            .build()
        
        // positionSelectView.setConfig(customConfig)
    }
    
    /**
     * 示例：AreaSelectView的回调处理
     */
    private fun onAreaSelected(rect: Rect) {
        Log.d("MainActivity", "选中区域: $rect")
        // 处理选中区域
    }
}