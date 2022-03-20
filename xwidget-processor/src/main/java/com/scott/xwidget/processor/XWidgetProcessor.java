package com.scott.xwidget.processor;

import com.google.auto.service.AutoService;
import com.scott.xwidget.annotation.XWidget;

import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.io.Writer;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Filer;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import javax.tools.JavaFileObject;

@AutoService(Processor.class)
public class XWidgetProcessor extends AbstractProcessor {

    private static final String NEW_LINE = "\n";
    private static final String DOUBLE_NEW_LINE = "\n\n";

    private Filer mFiler;

    @Override
    public synchronized void init(ProcessingEnvironment processingEnvironment) {
        super.init(processingEnvironment);
        mFiler = processingEnv.getFiler();
    }

    @Override
    public Set<String> getSupportedAnnotationTypes() {
        Set<String> types = new LinkedHashSet<>(1);
        types.add(XWidget.class.getCanonicalName());
        return types;
    }


    @Override
    public SourceVersion getSupportedSourceVersion() {
        return SourceVersion.latestSupported();
    }

    @Override
    public boolean process(Set<? extends TypeElement> set, RoundEnvironment roundEnvironment) {
        if (roundEnvironment == null) {
            return false;
        }

        return handleProcess(roundEnvironment);
    }

    private boolean handleProcess(RoundEnvironment roundEnv) {
        Set<? extends Element> elements = roundEnv.getElementsAnnotatedWith(XWidget.class);

        //List<String> names = new ArrayList<>();
        //List<String> widgetCls = new ArrayList<>();

        for (Element element : elements) {
            String name = element.getSimpleName().toString();
            String fullViewClsName = element.toString();
            String packageName = element.getAnnotation(XWidget.class).resourcePackageName();
            generateParser(fullViewClsName, name, packageName);

            //names.add(name);
            //widgetCls.add(element.toString());
        }

        //generateRegistry(names, widgetCls);
        return true;
    }

    private void generateParser(String fullClsName, String name, String packageName) {
        StringBuilder builder = Template.template;
        String s = builder.toString().replace("XViewTemplate", name);
        s = s.replace("xwidget.template;", "xwidget.parser;");
        s = s.replace("XViewParserTemplate", name + "$$WidgetParser");
        s = s.replace("com.scott.xwidget.R", packageName + ".R");

        Writer writer = null;
        try {
            JavaFileObject fileObject = mFiler.createSourceFile(
                     "com.scott.xwidget.parser." + name + "$$WidgetParser");
            writer = fileObject.openWriter();
            writer.write(s);
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (writer != null) {
                    writer.close();
                }
            } catch (IOException ignore) {}
        }
    }
}