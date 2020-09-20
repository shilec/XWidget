package com.scott.xwidget_gradle_plugin


import org.gradle.api.Plugin
import org.gradle.api.Project

class XWidgetBuildPlugin implements Plugin<Project> {
    private static final ATTR_PATH = "src/main/res/values/xwidget_attrs.xml"
    private static final KAPT_DIR = "build/generated/source/kapt"

    @Override
    void apply(Project project) {
        println("---- build - " +   project.getBuildDir())
        project.gradle.buildFinished {
            def attrFile = project.file(ATTR_PATH)
            def kaptFile = project.file(KAPT_DIR)
            println("---- build finished - " + attrFile)
            XWidgetBuildHelper.generateAttrsRes(attrFile, kaptFile)
        }
    }
}