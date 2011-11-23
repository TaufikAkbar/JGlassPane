/*
 *  Copyright (c) 2011, StripBandunk and/or its affiliates. All rights reserved.
 * 
 *       http://stripbandunk.com/
 * 
 *  STRIPBANDUNK PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.stripbandunk.jglasspane.component;

import com.stripbandunk.jglasspane.event.DialogListener;
import com.stripbandunk.jglasspane.helper.AssertHelper;
import java.awt.Component;
import java.awt.FlowLayout;
import javax.swing.JPanel;

/**
 *
 * @author Eko Kurniawan Khannedy
 */
public class DialogComponent extends JPanel implements JGlassPaneComponent {

    private static final long serialVersionUID = 1L;

    private DialogBlockListener blockListener;

    public DialogComponent() {
        blockListener = new DialogBlockListener();

        setOpaque(false);
        setLayout(new FlowLayout(FlowLayout.CENTER, 30, 30));
    }

    public void addDialogListener(DialogListener listener) {
        listenerList.add(DialogListener.class, listener);
    }

    public void removeDialogListener(DialogListener listener) {
        listenerList.remove(DialogListener.class, listener);
    }

    @Override
    public Component getComponent() {
        return this;
    }

    public void showDialog(Component component, boolean block) {

        AssertHelper.notNull(component, "Component is null");

        if (block) {
            // add block listener
            addMouseListener(blockListener);
            addMouseMotionListener(blockListener);
            addMouseWheelListener(blockListener);
            addKeyListener(blockListener);
        }

        // add component
        add(component);

        // validate and repaint component and subcomponent
        validate();
        repaint();

        // request focus
        component.requestFocus();
    }

    public void hideDialog() {
        // remove block listener
        removeMouseListener(blockListener);
        removeMouseMotionListener(blockListener);
        removeMouseWheelListener(blockListener);
        removeKeyListener(blockListener);

        // remove all component
        removeAll();

        // validate and repaint component and subcomponent
        validate();
        repaint();
    }
}
