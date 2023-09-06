package com.example.viewdemo

import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Bundle
import android.os.Handler
import android.os.HandlerThread
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.shape.CornerTreatment
import com.google.android.material.shape.CutCornerTreatment
import com.google.android.material.shape.MaterialShapeDrawable
import com.google.android.material.shape.RoundedCornerTreatment
import com.google.android.material.shape.ShapePathModel
import com.google.android.material.shape.TriangleEdgeTreatment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

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
    }
}