package com.scott.xwidget_gradle_plugin;


import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class XWidgetBuildHelper {
    private static final String OUTPUT_PACKAGE_NAME = "com/scott/xwidget/parser";
    private static final String WIDGET_SUFFIX = "$$WidgetParser";
    private static final String DEBUG = "debug/" + OUTPUT_PACKAGE_NAME;
    private static final String RELEASE = "release/" + OUTPUT_PACKAGE_NAME;

    private static final String NEW_LINE = "\n";

    private static void println(String msg) {
        throw new IllegalStateException(msg);
    }

    public static void generateAttrsRes(File attrFile, File kaptFile) {
        if (kaptFile == null || !kaptFile.exists()) {
            return;
        }

        File[] listFiles = null;
        File debugFile = new File(kaptFile, DEBUG);
        if (debugFile.exists() && debugFile.listFiles() != null && Objects.requireNonNull(debugFile.listFiles()).length > 0) {
            listFiles = debugFile.listFiles();
        }

        if (listFiles == null || listFiles.length == 0) {
            File releaseFile = new File(kaptFile, RELEASE);
            if (releaseFile.exists() && releaseFile.listFiles() != null
                    && Objects.requireNonNull(releaseFile.listFiles()).length > 0) {
                listFiles = releaseFile.listFiles();
            }
        }
        //println("--- find parser files = " + Arrays.toString(listFiles));

        if (listFiles == null) return;

        List<String> names = new ArrayList<>();
        for (File f : listFiles) {
            if (f.getName().contains(WIDGET_SUFFIX)) {
                String name = f.getName().replace(WIDGET_SUFFIX, "");
                name = name.replace(".java", "");
                names.add(name);
            }
        }

        //println("--- find parser = " + names);
        if (names.isEmpty()) return;

        try {
            buildAllAttrsFields(names, attrFile);
        } catch (IOException e) {
            println(e.toString());
        }
    }

    private static void buildAllAttrsFields(List<String> widgetNames, File attrFile) throws IOException {
        StringBuilder builder = new StringBuilder();
        builder.append("<?xml version=\"1.0\" encoding=\"utf-8\"?>" + NEW_LINE);
        builder.append("<!-- Auto generated, do not modify!!! -->").append(NEW_LINE);
        builder.append("<resources>" + NEW_LINE);

        for (String name : widgetNames) {
            builder.append(NEW_LINE);
            builder.append("<!-- ").append(name).append(" -->").append(NEW_LINE);
            builder.append("<declare-styleable name=\"").append(name).append("\" >").append(NEW_LINE);
            builder.append("    <attr name=\"").append(name).append("_corner\" format=\"dimension\" />").append(NEW_LINE);
            builder.append("    <attr name=\"").append(name).append("_corner_type\">").append(NEW_LINE);
            builder.append("        <flag name=\"left_top\" value=\"0x01\"/>").append(NEW_LINE);
            builder.append("        <flag name=\"right_top\" value=\"0x08\"/>").append(NEW_LINE);
            builder.append("        <flag name=\"left_bottom\" value=\"0x16\"/>").append(NEW_LINE);
            builder.append("        <flag name=\"right_bottom\" value=\"0x32\"/>").append(NEW_LINE);
            builder.append("        <flag name=\"all\" value=\"0\"/>").append(NEW_LINE);
            builder.append("    </attr>").append(NEW_LINE);
            
            builder.append("    <attr name=\"").append(name).append("_shadow_radius\" format=\"dimension\"/>").append(NEW_LINE);
            builder.append("    <attr name=\"").append(name).append("_shadow_dx\" format=\"dimension\"/>").append(NEW_LINE);
            builder.append("    <attr name=\"").append(name).append("_shadow_dy\" format=\"dimension\"/>").append(NEW_LINE);
            builder.append("    <attr name=\"").append(name).append("_shadow_color\" format=\"color\"/>").append(NEW_LINE);

            builder.append("    <attr name=\"").append(name).append("_blur_type\" format=\"enum\">").append(NEW_LINE);
            builder.append("        <enum name=\"normal\" value=\"0\"/>").append(NEW_LINE);
            builder.append("        <enum name=\"outer\" value=\"1\"/>").append(NEW_LINE);
            builder.append("        <enum name=\"solid\" value=\"2\"/>").append(NEW_LINE);
            builder.append("        <enum name=\"inner\" value=\"3\"/>").append(NEW_LINE);
            builder.append("    </attr>").append(NEW_LINE);
            builder.append("    <attr name=\"").append(name).append("_blur_radius\" format=\"dimension\"/>").append(NEW_LINE);
            builder.append("    <attr name=\"").append(name).append("_blur_color\" format=\"color\"/>").append(NEW_LINE);

            builder.append("    <attr name=\"").append(name).append("_solid_color\" format=\"color\" />").append(NEW_LINE);
            builder.append("    <attr name=\"").append(name).append("_solid_gradient\" format=\"enum\">").append(NEW_LINE);
            builder.append("        <enum name=\"linear\" value=\"0\"/>").append(NEW_LINE);
            builder.append("        <enum name=\"radial\" value=\"1\"/>").append(NEW_LINE);
            builder.append("        <enum name=\"sweep\" value=\"2\"/>").append(NEW_LINE);
            builder.append("    </attr>").append(NEW_LINE);
            builder.append("    <attr name=\"").append(name).append("_gradient_center_color\" format=\"color\"/>").append(NEW_LINE);
            builder.append("    <attr name=\"").append(name).append("_gradient_start_color\" format=\"color\"/>").append(NEW_LINE);
            builder.append("    <attr name=\"").append(name).append("_gradient_end_color\" format=\"color\"/>").append(NEW_LINE);
            builder.append("    <attr name=\"").append(name).append("_gradient_orientation\" format=\"enum\">").append(NEW_LINE);
            builder.append("        <enum name=\"top_bottom\" value=\"2\"/>").append(NEW_LINE);
            builder.append("        <enum name=\"left_right\" value=\"1\"/>").append(NEW_LINE);
            builder.append("        <enum name=\"top_left_bottom_right\" value=\"3\"/>").append(NEW_LINE);
            builder.append("        <enum name=\"top_right_bottom_left\" value=\"4\"/>").append(NEW_LINE);
            builder.append("    </attr>").append(NEW_LINE);
            builder.append("    <attr name=\"").append(name).append("_gradient_radius\" format=\"dimension\"/>").append(NEW_LINE);
            builder.append("    <attr name=\"").append(name).append("_stroke_color\" format=\"color\" />").append(NEW_LINE);
            builder.append("    <attr name=\"").append(name).append("_stroke_border\" format=\"dimension\"/>").append(NEW_LINE);

            builder.append("    <attr name=\"").append(name).append("_stroke_gradient_start_color\" format=\"color\"/>").append(NEW_LINE);
            builder.append("    <attr name=\"").append(name).append("_stroke_gradient_middle_color\" format=\"color\"/>").append(NEW_LINE);
            builder.append("    <attr name=\"").append(name).append("_stroke_gradient_end_color\" format=\"color\"/>").append(NEW_LINE);
            builder.append("    <attr name=\"").append(name).append("_stroke_gradient_offset_x\" format=\"dimension\"/>").append(NEW_LINE);
            builder.append("    <attr name=\"").append(name).append("_stroke_gradient_offset_y\" format=\"dimension\"/>").append(NEW_LINE);
            builder.append("    <attr name=\"").append(name).append("_stroke_gradient_offset_x1\" format=\"dimension\"/>").append(NEW_LINE);
            builder.append("    <attr name=\"").append(name).append("_stroke_gradient_offset_y1\" format=\"dimension\"/>").append(NEW_LINE);

            builder.append("    <attr name=\"").append(name).append("_stroke_gradient\" format=\"enum\">").append(NEW_LINE);
            builder.append("        <enum name=\"linear\" value=\"0\"/>").append(NEW_LINE);
            builder.append("        <enum name=\"radial\" value=\"1\"/>").append(NEW_LINE);
            builder.append("        <enum name=\"sweep\" value=\"2\"/>").append(NEW_LINE);
            builder.append("    </attr>").append(NEW_LINE);

            builder.append("    <attr name=\"").append(name).append("_state\" format=\"enum\">").append(NEW_LINE);
            builder.append("        <enum name=\"none\" value=\"0\"/>").append(NEW_LINE);
            builder.append("        <enum name=\"pressed\" value=\"1\"/>").append(NEW_LINE);
            builder.append("        <enum name=\"selected\" value=\"2\"/>").append(NEW_LINE);
            builder.append("        <enum name=\"checked\" value=\"3\"/>").append(NEW_LINE);
            builder.append("    </attr>").append(NEW_LINE);

            builder.append("    <attr name=\"").append(name).append("_stated_corner\" format=\"dimension\" />").append(NEW_LINE);
            builder.append("    <attr name=\"").append(name).append("_stated_corner_type\">").append(NEW_LINE);
            builder.append("        <flag name=\"left_top\" value=\"0x01\"/>").append(NEW_LINE);
            builder.append("        <flag name=\"right_top\" value=\"0x08\"/>").append(NEW_LINE);
            builder.append("        <flag name=\"left_bottom\" value=\"0x16\"/>").append(NEW_LINE);
            builder.append("        <flag name=\"right_bottom\" value=\"0x32\"/>").append(NEW_LINE);
            builder.append("        <flag name=\"all\" value=\"0\"/>").append(NEW_LINE);
            builder.append("    </attr>").append(NEW_LINE);

            builder.append("    <attr name=\"").append(name).append("_stated_shadow_radius\" format=\"dimension\"/>").append(NEW_LINE);
            builder.append("    <attr name=\"").append(name).append("_stated_shadow_dx\" format=\"dimension\"/>").append(NEW_LINE);
            builder.append("    <attr name=\"").append(name).append("_stated_shadow_dy\" format=\"dimension\"/>").append(NEW_LINE);
            builder.append("    <attr name=\"").append(name).append("_stated_shadow_color\" format=\"color\"/>").append(NEW_LINE);

            builder.append("    <attr name=\"").append(name).append("_stated_blur_type\" format=\"enum\">").append(NEW_LINE);
            builder.append("        <enum name=\"normal\" value=\"0\"/>").append(NEW_LINE);
            builder.append("        <enum name=\"outer\" value=\"1\"/>").append(NEW_LINE);
            builder.append("        <enum name=\"solid\" value=\"2\"/>").append(NEW_LINE);
            builder.append("        <enum name=\"inner\" value=\"3\"/>").append(NEW_LINE);
            builder.append("    </attr>").append(NEW_LINE);
            builder.append("    <attr name=\"").append(name).append("_stated_blur_radius\" format=\"dimension\"/>").append(NEW_LINE);
            builder.append("    <attr name=\"").append(name).append("_stated_blur_color\" format=\"color\"/>").append(NEW_LINE);

            builder.append("    <attr name=\"").append(name).append("_stated_solid_color\" format=\"color\"/>").append(NEW_LINE);
            builder.append("    <attr name=\"").append(name).append("_stated_solid_gradient\" format=\"enum\">").append(NEW_LINE);
            builder.append("        <enum name=\"linear\" value=\"0\"/>").append(NEW_LINE);
            builder.append("        <enum name=\"radial\" value=\"1\"/>").append(NEW_LINE);
            builder.append("        <enum name=\"sweep\" value=\"2\"/>").append(NEW_LINE);
            builder.append("    </attr>").append(NEW_LINE);

            builder.append("    <attr name=\"").append(name).append("_stated_gradient_center_color\" format=\"color\"/>").append(NEW_LINE);
            builder.append("    <attr name=\"").append(name).append("_stated_gradient_start_color\" format=\"color\"/>").append(NEW_LINE);
            builder.append("    <attr name=\"").append(name).append("_stated_gradient_end_color\" format=\"color\"/>").append(NEW_LINE);
            builder.append("    <attr name=\"").append(name).append("_stated_gradient_orientation\" format=\"enum\">").append(NEW_LINE);
            builder.append("        <enum name=\"top_bottom\" value=\"2\"/>").append(NEW_LINE);
            builder.append("        <enum name=\"left_right\" value=\"1\"/>").append(NEW_LINE);
            builder.append("        <enum name=\"top_left_bottom_right\" value=\"3\"/>").append(NEW_LINE);
            builder.append("        <enum name=\"top_right_bottom_left\" value=\"4\"/>").append(NEW_LINE);
            builder.append("    </attr>").append(NEW_LINE);
            builder.append("    <attr name=\"").append(name).append("_stated_gradient_radius\" format=\"dimension\"/>").append(NEW_LINE);
            builder.append("    <attr name=\"").append(name).append("_stated_stroke_color\" format=\"color\" />").append(NEW_LINE);
            builder.append("    <attr name=\"").append(name).append("_stated_stroke_border\" format=\"dimension\"/>").append(NEW_LINE);

            builder.append("    <attr name=\"").append(name).append("_stated_stroke_gradient_start_color\" format=\"color\"/>").append(NEW_LINE);
            builder.append("    <attr name=\"").append(name).append("_stated_stroke_gradient_middle_color\" format=\"color\"/>").append(NEW_LINE);
            builder.append("    <attr name=\"").append(name).append("_stated_stroke_gradient_end_color\" format=\"color\"/>").append(NEW_LINE);
            builder.append("    <attr name=\"").append(name).append("_stated_stroke_gradient_offset_x\" format=\"dimension\"/>").append(NEW_LINE);
            builder.append("    <attr name=\"").append(name).append("_stated_stroke_gradient_offset_y\" format=\"dimension\"/>").append(NEW_LINE);
            builder.append("    <attr name=\"").append(name).append("_stated_stroke_gradient_offset_x1\" format=\"dimension\"/>").append(NEW_LINE);
            builder.append("    <attr name=\"").append(name).append("_stated_stroke_gradient_offset_y1\" format=\"dimension\"/>").append(NEW_LINE);

            builder.append("    <attr name=\"").append(name).append("_stated_stroke_gradient\" format=\"enum\">").append(NEW_LINE);
            builder.append("        <enum name=\"linear\" value=\"0\"/>").append(NEW_LINE);
            builder.append("        <enum name=\"radial\" value=\"1\"/>").append(NEW_LINE);
            builder.append("        <enum name=\"sweep\" value=\"2\"/>").append(NEW_LINE);
            builder.append("    </attr>").append(NEW_LINE);

            builder.append("    <attr name=\"").append(name).append("_drawable\" format=\"reference\"/>").append(NEW_LINE);
            builder.append("    <attr name=\"").append(name).append("_stated_drawable\" format=\"reference\"/>").append(NEW_LINE);

            builder.append("</declare-styleable>").append(NEW_LINE);
            builder.append("<!-- ").append(name).append(" -->").append(NEW_LINE);
        }
        
        builder.append("</resources>");
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(attrFile)));
        bufferedWriter.write(builder.toString());
        bufferedWriter.flush();
        bufferedWriter.close();
    }
}
