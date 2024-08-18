package com.example.viewdemo

import android.graphics.Path
import android.os.SystemClock
import androidx.annotation.Keep
import java.io.File

/**
 * @Author:      shijiale
 * @Email:       shilec@126.com
 * @Date:        2021/4/30 9:52 AM
 * @Description:
 */

@Keep
class GesturePathData {
    var id: Long = 0L

    var left: Float = 0F
    var top: Float = 0F

    var gestureType: Int = 0

    val gestures = mutableListOf<SerializablePath>()

    fun rollback(): SerializablePath? {
        if (gestures.isEmpty()) return null
        return gestures.removeAt(gestures.size - 1)
    }

    fun addPath(path: SerializablePath) {
        gestures.add(path)
    }

    @Synchronized
    fun updatePathData(updater: (PathAction) -> Unit) {
        for (path in gestures) {
            for (pa in path.pathActions) {
                updater(pa)
            }
            path.updatePath()
        }
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is GesturePathData) return false

        if (gestures != other.gestures) return false

        return true
    }

    fun reset() {
        gestures.forEach {
            it.reset()
        }
    }

    fun clone(): GesturePathData {
        val gesturePathData = GesturePathData()
        for (sp in gestures) {
            gesturePathData.gestures.add(sp.clone())
        }
        gesturePathData.top = top
        gesturePathData.left = left
        return gesturePathData
    }

    override fun hashCode(): Int {
        return gestures.hashCode()
    }

    override fun toString(): String {
        return "GesturePathData(gestures=$gestures)"
    }
}

@Keep
class SerializablePath : Path() {
    val pathActions: MutableList<PathAction> = mutableListOf()

    var startTimestamp: Long = 0L

    fun updatePath() {
        super.reset()
        for (action in pathActions) {
            doAction(action)
        }
    }

    fun clone(): SerializablePath {
        val sp = SerializablePath()
        for (a in pathActions) {
            sp.pathActions.add(a.clone())
        }
        sp.updatePath()
        return sp
    }

    private fun doAction(action: PathAction) {
        when (action.actionType) {
            PathAction.ACTION_TYPE_MOVE_TO -> super.moveTo(action.x, action.y)
            PathAction.ACTION_TYPE_LINE_TO -> super.lineTo(action.x, action.y)
        }
    }

    override fun moveTo(x: Float, y: Float) {
        super.moveTo(x, y)
        // 记录首次开始的绘制时间
        pathActions.add(PathAction(x, y, PathAction.ACTION_TYPE_MOVE_TO).let {
            // 当前手势的时间戳是相对于起始时间戳的偏移
            if (startTimestamp == 0L) {
                startTimestamp = SystemClock.uptimeMillis()
                it.timeStamp = 0L
            } else {
                it.timeStamp = SystemClock.uptimeMillis() - startTimestamp
            }
            it
        })
    }

    override fun lineTo(x: Float, y: Float) {
        super.lineTo(x, y)
        if (startTimestamp == 0L) {
            startTimestamp = SystemClock.uptimeMillis()
        }
        pathActions.add(PathAction(x, y, PathAction.ACTION_TYPE_LINE_TO).let {
            if (startTimestamp == 0L) {
                startTimestamp = SystemClock.uptimeMillis()
                it.timeStamp = 0L
            } else {
                it.timeStamp = SystemClock.uptimeMillis() - startTimestamp
            }
            it
        })
    }

    override fun reset() {
        try {
            super.reset()
            pathActions.clear()
            startTimestamp = 0L
        } catch (e: Throwable) {
            e.printStackTrace()
        }
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is SerializablePath) return false

        if (pathActions != other.pathActions) return false

        return true
    }

    override fun hashCode(): Int {
        return pathActions.hashCode()
    }

    override fun toString(): String {
        return "SerializablePath(pathActions=$pathActions)"
    }
}


@Keep
data class PathAction(var x: Float, var y: Float, val actionType: Int) {
    companion object {
        const val ACTION_TYPE_MOVE_TO = 1
        const val ACTION_TYPE_LINE_TO = 2
    }

    var timeStamp: Long = 0L

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is PathAction) return false

        if (x != other.x) return false
        if (y != other.y) return false
        if (actionType != other.actionType) return false

        return true
    }

    override fun hashCode(): Int {
        var result = x.hashCode()
        result = 31 * result + y.hashCode()
        result = 31 * result + actionType
        return result
    }

    fun clone(): PathAction {
        return PathAction(x, y, actionType).let {
            it.timeStamp = timeStamp
            it
        }
    }

    override fun toString(): String {
        return "PathAction(x=$x, y=$y, actionType=$actionType, timeStamp=$timeStamp)"
    }
}