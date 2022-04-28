package com.scott.xwidget.annotation

@Target(AnnotationTarget.CLASS)
@Retention(AnnotationRetention.BINARY)
annotation class XWidgetView(val resourcePackageName: String)