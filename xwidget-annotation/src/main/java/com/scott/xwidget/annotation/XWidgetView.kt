package com.scott.xwidget.annotation

/**
 * XWidgetView注解，用于标记自定义View
 * @param resourcePackageName 资源包名，如果为空则自动检测（从View所在包名推断）
 */
@Target(AnnotationTarget.CLASS)
@Retention(AnnotationRetention.BINARY)
annotation class XWidgetView(val resourcePackageName: String = "")