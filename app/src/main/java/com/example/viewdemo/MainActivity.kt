package com.example.viewdemo

import android.graphics.Color
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.LinearLayout.LayoutParams
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    companion object {
        var isNewTheme = false
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (isNewTheme) {
            setTheme(R.style.AppTheme2)
        } else {
            setTheme(R.style.AppTheme)
        }
        setContentView(R.layout.activity_main)


//        val vTest = findViewById<View>(R.id.tv_test2)
//        vTest.background = vTest.background
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

//        val shapePathModel = ShapePathModel()
//        shapePathModel.topRightCorner = CutCornerTreatment(400f)
//        shapePathModel.bottomLeftCorner = RoundedCornerTreatment(50f)
////        shapePathModel.leftEdge = TriangleEdgeTreatment(40f, false)
////        shapePathModel.setRightEdge(TriangleEdgeTreatment(40f, false))
//
//        val materialShapeDrawable = MaterialShapeDrawable(shapePathModel)
//        materialShapeDrawable.tintList = ColorStateList.valueOf(Color.parseColor("#FF552E"))
//        findViewById<View>(R.id.v_test).background = materialShapeDrawable
//
//        val handlerThread = HandlerThread("new_thread")
//        val handler = Handler(handlerThread.looper)
//        handlerThread.start()
//
//        handler.post {
//            while (true) {
//                Thread.sleep(1000L)
//                System.out.println("----- 输出 ------")
//            }
//        }

        val linearLayout = LinearLayout(this)
        linearLayout.layoutParams = LayoutParams(LayoutParams.MATCH_PARENT, 600)
        linearLayout.orientation = LinearLayout.HORIZONTAL
        linearLayout.gravity = Gravity.BOTTOM
        linearLayout.setBackgroundColor(Color.parseColor("#334455"))
        (linearLayout.layoutParams as LinearLayout.LayoutParams).gravity = Gravity.BOTTOM
        linearLayout.requestLayout()

        var itemView = TextView(this)
        itemView.gravity = Gravity.BOTTOM
        itemView.setTextColor(Color.WHITE)
        itemView.text = "1"
        itemView.setBackgroundColor(Color.parseColor("#FF00FF"))
        linearLayout.addView(itemView, ViewGroup.LayoutParams(300, 300))


        itemView = TextView(this)
        itemView.gravity = Gravity.BOTTOM
        itemView.setTextColor(Color.WHITE)
        itemView.text = "2"
        itemView.setBackgroundColor(Color.parseColor("#FFFF00"))
        linearLayout.addView(itemView, ViewGroup.LayoutParams(300, 100))

        itemView = TextView(this)
        itemView.gravity = Gravity.BOTTOM
        itemView.setTextColor(Color.WHITE)
        itemView.text = "3"
        itemView.setBackgroundColor(Color.parseColor("#ff0033"))
        linearLayout.addView(itemView, ViewGroup.LayoutParams(300, 300))

        findViewById<View>(R.id.ll_container).setOnClickListener {
            (it as ViewGroup).removeAllViews()
            (findViewById<View>(R.id.ll_container) as ViewGroup).addView(linearLayout)
        }

        findViewById<View>(R.id.ll_container2).setOnClickListener {
            isNewTheme = true
            recreate()
        }
    }
}