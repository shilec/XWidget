package com.example.viewdemo

import android.graphics.Color
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.scott.xwidget.XWidgetParser

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val vTest = findViewById<View>(R.id.v_test)

        findViewById<View>(R.id.btn_test).setOnClickListener {
            XWidgetParser.getWidgetEditor(vTest)
                ?.editNormal()
                ?.setStrokeBorderColor(Color.BLUE)
                ?.setSolidColor(Color.GREEN)
                ?.setRippleColor(Color.YELLOW)
                ?.setCorner(10f)
                ?.commit()
        }
    }
}