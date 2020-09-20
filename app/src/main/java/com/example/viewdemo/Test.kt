package com.example.viewdemo

import androidx.annotation.GuardedBy
import com.scott.xwidget.annotation.XWidget

class Test {
    class A<T, D> {

    }

    class Client {
        private val objLock = Object()

        @GuardedBy("objLock")
        fun func() {

        }
    }
}