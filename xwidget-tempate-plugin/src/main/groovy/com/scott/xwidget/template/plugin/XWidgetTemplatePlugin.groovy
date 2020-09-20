package com.scott.xwidget.template.plugin

import org.gradle.api.Plugin
import org.gradle.api.Project

class XWidgetTemplatePlugin implements Plugin<Project> {
    @Override
    void apply(Project project) {
        File output = project.file("src/main/java/com/scott/xwidget/processor/Template.java")
        File template = project.file("Template.txt")
        println("--- template -- " + template + ", " + template.exists())
        println("--- output -- " + output + ", " + output.exists())

        XWidgetTemplateGenerator.generate(output, template)
    }
}