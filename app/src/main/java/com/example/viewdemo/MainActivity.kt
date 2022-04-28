package com.example.viewdemo

import android.graphics.Color
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.scott.xwidget.XWidget

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

//        val vTest = findViewById<View>(R.id.v_test2)
//
//        findViewById<View>(R.id.btn_test).setOnClickListener {
//            val stateEditor = XWidget.getNormalRippleDrawableEditor(vTest)
//
//            stateEditor?.edit()
//            ?.setStrokeBorderColor(Color.BLUE)
//            ?.setSolidColor(Color.GREEN)
//                ?.setStrokeBorder(10f)
//                ?.setRippleEnable(true)
//            ?.setRippleColor(Color.YELLOW)
//            ?.setCorner(10f)
//            ?.commit()

//            stateEditor?.stateEditor?.edit()
//            ?.setStrokeBorderColor(Color.RED)
//                ?.setStrokeBorder(30f)
//            ?.setSolidColor(Color.WHITE)
//            ?.setRippleColor(Color.RED)
//            ?.setCorner(40F)
//            ?.commit()
//        }
    }
}