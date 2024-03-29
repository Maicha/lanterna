/*
 * This file is part of lanterna (http://code.google.com/p/lanterna/).
 *
 * lanterna is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 *
 * Copyright (C) 2010-2015 Martin
 */
package com.googlecode.lanterna.gui2.dialogs;

import com.googlecode.lanterna.gui2.*;

/**
 * Dialog that displays a text message, an optional spinning indicator and an optional progress bar
 * @author martin
 */
public class WaitingDialog extends DialogWindow {
    private WaitingDialog(String title, String text) {
        super(title);

        Panel mainPanel = Panels.horizontal(
                new Label(text),
                AnimatedLabel.createClassicSpinningLine());
        setComponent(mainPanel);
    }

    public void showDialog(WindowBasedTextGUI textGUI, boolean blockUntilClosed) {
        textGUI.addWindow(this);

        if(blockUntilClosed) {
            //Wait for the window to close, in case the window manager doesn't honor the MODAL hint
            waitUntilClosed();
        }
    }

    public static WaitingDialog createDialog(String title, String text) {
        WaitingDialog waitingDialog = new WaitingDialog(title, text);
        return waitingDialog;
    }

    public static WaitingDialog showDialog(WindowBasedTextGUI textGUI, String title, String text) {
        WaitingDialog waitingDialog = createDialog(title, text);
        waitingDialog.showDialog(textGUI, false);
        return waitingDialog;
    }
}
