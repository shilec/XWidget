package com.scott.xwidget.annotation

@Target(AnnotationTarget.CLASS)
@Retention(AnnotationRetention.BINARY)
annotation class XWidget(val resourcePackageName: String)