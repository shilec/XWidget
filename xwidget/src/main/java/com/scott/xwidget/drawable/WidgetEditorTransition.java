package com.scott.xwidget.drawable;

import androidx.annotation.NonNull;

public class WidgetEditorTransition {
    private final IDrawableEditTransition normaEditorTransition;
    private final IDrawableEditTransition stateEditorTransition;

    public WidgetEditorTransition(IDrawableEditTransition normaEditorTransition, IDrawableEditTransition stateEditorTransition) {
        this.normaEditorTransition = normaEditorTransition;
        this.stateEditorTransition = stateEditorTransition;
    }

    public @NonNull IDrawableEditTransition beginNormalTransition() {
        return this.normaEditorTransition;
    }

    public @NonNull IDrawableEditTransition beginStateTransition() {
        return this.stateEditorTransition;
    }
}
