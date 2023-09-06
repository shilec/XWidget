package com.scott.xwidget.drawable.render

import android.graphics.Canvas
import android.graphics.Rect
import com.google.android.material.shape.CutCornerTreatment
import com.google.android.material.shape.MaterialShapeDrawable
import com.google.android.material.shape.RoundedCornerTreatment
import com.google.android.material.shape.ShapePathModel
import com.google.android.material.shape.TriangleEdgeTreatment
import com.scott.xwidget.Cons
import com.scott.xwidget.drawable.DrawableInfo

/**
 * @Author      :   shijiale
 * @Date        :   2023/9/6 10:42 AM
 * @Email       :   shilec@126.com
 * @Description :
 */
class ShapedDrawableRender(drawableInfo: DrawableInfo) : IRender {
    override var next: IRender? = null

    private val shapeDrawableImpl: MaterialShapeDrawable

    init {
        val shapePathModel = ShapePathModel()
        shapePathModel.topRightCorner =
            if (drawableInfo.shapedTopRightType == Cons.CutCornerType.CUT_CORNER) {
                CutCornerTreatment(drawableInfo.shapedTopRightSize)
            } else {
                RoundedCornerTreatment(drawableInfo.shapedTopRightSize)
            }

        shapePathModel.topLeftCorner =
            if (drawableInfo.shapedTopLeftType == Cons.CutCornerType.CUT_CORNER) {
                CutCornerTreatment(drawableInfo.shapedTopLeftSize)
            } else {
                RoundedCornerTreatment(drawableInfo.shapedTopLeftSize)
            }

        shapePathModel.bottomRightCorner =
            if (drawableInfo.shapedBottomRightType == Cons.CutCornerType.CUT_CORNER) {
                CutCornerTreatment(drawableInfo.shapedBottomRightSize)
            } else {
                RoundedCornerTreatment(drawableInfo.shapedBottomRightSize)
            }

        shapePathModel.bottomLeftCorner =
            if (drawableInfo.shapedBottomLeftType == Cons.CutCornerType.CUT_CORNER) {
                CutCornerTreatment(drawableInfo.shapedBottomLeftSize)
            } else {
                RoundedCornerTreatment(drawableInfo.shapedBottomLeftSize)
            }

        shapePathModel.leftEdge = TriangleEdgeTreatment(drawableInfo.shapedLeftEdgeSize, true)
        shapePathModel.rightEdge = TriangleEdgeTreatment(drawableInfo.shapedRightEdgeSize, true)
        shapePathModel.topEdge = TriangleEdgeTreatment(drawableInfo.shapedTopEdgeSize, true)
        shapePathModel.bottomEdge = TriangleEdgeTreatment(drawableInfo.shapedBottomEdgeSize, true)
        shapeDrawableImpl = GradientMaterialShapeDrawable(drawableInfo, shapePathModel)
    }

    override fun draw(canvas: Canvas, bounds: Rect) {
        shapeDrawableImpl.draw(canvas)
    }

    override fun onBoundsChanged(left: Int, top: Int, right: Int, bottom: Int): Rect {
        shapeDrawableImpl.setBounds(left, top, right, bottom)
        return Rect(left, top, right, bottom)
    }
}