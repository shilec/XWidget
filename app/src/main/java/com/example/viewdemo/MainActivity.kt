package com.example.viewdemo

import android.graphics.Color
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.scott.xwidget.XWidgetParser
import com.scott.xwidget.widget.XTextView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        var b = false
        val v = findViewById<XTextView>(R.id.tv_test);
        v.setOnClickListener {
            val editor = XWidgetParser.getDrawableEditTransition(v)
            val ret = editor?.beginNormalTransition()
            ret
                ?.setShadowRadius(2F)
                ?.setShadowDy(3)
                ?.setShadowDy(3)
                ?.setShadowColor(Color.GREEN)
                ?.commit()

//            editor?.beginStateTransition()
//            ret?.setCorner(if (b) 20f else 40f)
//                ?.setCornerType(if (b) IDrawableEditTransition.CornerType.ALL else IDrawableEditTransition.CornerType.RIGHT_BOTTOM)
//                ?.commit()
            b = !b;
        }
    }
}