package com.scott.xwidget.drawable;

import androidx.annotation.NonNull;

public class XWidgetEditor {
    private final IDrawableEditBuilder normaEditorTransition;
    private final IDrawableEditBuilder stateEditorTransition;

    public XWidgetEditor(IDrawableEditBuilder normaEditorTransition, IDrawableEditBuilder stateEditorTransition) {
        this.normaEditorTransition = normaEditorTransition;
        this.stateEditorTransition = stateEditorTransition;
    }

    public @NonNull
    IDrawableEditBuilder editNormal() {
        return this.normaEditorTransition;
    }

    public @NonNull
    IDrawableEditBuilder editState() {
        return this.stateEditorTransition;
    }
}
