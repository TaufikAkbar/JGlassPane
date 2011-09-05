/*
 *  Copyright (c) 2011, StripBandunk and/or its affiliates. All rights reserved.
 * 
 *       http://stripbandunk.com/
 * 
 *  STRIPBANDUNK PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.stripbandunk.jglasspane;

import java.awt.Component;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;

/**
 *
 * @author Eko Kurniawan Khannedy
 */
class ResizeListener implements ComponentListener {

    @Override
    public void componentResized(ComponentEvent e) {
        if (e.getComponent() instanceof JGlassPane) {
            JGlassPane glasspane = (JGlassPane) e.getComponent();
            for (Component comp : glasspane.getComponents()) {
                comp.setBounds(0, 0, glasspane.getWidth(), glasspane.getHeight());
                comp.repaint();
            }
        }
    }

    @Override
    public void componentMoved(ComponentEvent e) {
        componentResized(e);
    }

    @Override
    public void componentShown(ComponentEvent e) {
        componentResized(e);
    }

    @Override
    public void componentHidden(ComponentEvent e) {
        componentResized(e);
    }
}
