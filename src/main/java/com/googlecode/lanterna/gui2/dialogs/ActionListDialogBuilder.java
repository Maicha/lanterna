package com.googlecode.lanterna.gui2.dialogs;

import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.gui2.ActionListBox;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by martin on 23/06/15.
 */
public class ActionListDialogBuilder extends AbstractDialogBuilder<ActionListDialogBuilder, ActionListDialog> {
    private TerminalSize listBoxSize;
    private boolean canCancel;
    private List<Runnable> actions;

    public ActionListDialogBuilder() {
        super("ActionListDialogBuilder");
        this.listBoxSize = null;
        this.canCancel = true;
        this.actions = new ArrayList<Runnable>();
    }

    @Override
    protected ActionListDialogBuilder self() {
        return this;
    }

    @Override
    public ActionListDialog build() {
        return new ActionListDialog(
                title,
                description,
                listBoxSize,
                canCancel,
                actions);
    }

    public ActionListDialogBuilder setListBoxSize(TerminalSize listBoxSize) {
        this.listBoxSize = listBoxSize;
        return this;
    }

    public ActionListDialogBuilder setCanCancel(boolean canCancel) {
        this.canCancel = canCancel;
        return this;
    }

    public ActionListDialogBuilder addAction(final String label, final Runnable action) {
        return addAction(new Runnable() {
            @Override
            public String toString() {
                return label;
            }

            @Override
            public void run() {
                action.run();
            }
        });
    }

    public ActionListDialogBuilder addAction(Runnable action) {
        this.actions.add(action);
        return this;
    }

    public ActionListDialogBuilder addActions(Runnable... actions) {
        this.actions.addAll(Arrays.asList(actions));
        return this;
    }
}
