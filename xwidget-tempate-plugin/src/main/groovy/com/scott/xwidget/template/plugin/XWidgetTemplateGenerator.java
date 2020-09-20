package com.scott.xwidget.template.plugin;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class XWidgetTemplateGenerator {
    public static void generate(File output, File template) throws Exception {
        if (!template.exists())
            return;
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(template)));
        String line = "";

        if (!output.exists()) {
            output.createNewFile();
        }

        StringBuilder builder = new StringBuilder();
        builder.append("package com.scott.xwidget.processor;");
        builder.append("\n\n");
        builder.append("class Template {\n");

        builder.append("    public static StringBuilder template = new StringBuilder();\n");
        builder.append("    static {\n");

        while ((line = bufferedReader.readLine()) != null) {
            builder.append("        template.append(\"").append(line).append("\\n\");\n");
        }

        builder.append("    }\n");
        builder.append("}\n");

        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(output)));
        bufferedWriter.write(builder.toString());
        bufferedWriter.flush();
        bufferedWriter.close();
    }
}
