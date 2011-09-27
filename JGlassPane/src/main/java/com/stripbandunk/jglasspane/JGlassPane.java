/*
 *  Copyright (c) 2011, StripBandunk and/or its affiliates. All rights reserved.
 * 
 *       http://stripbandunk.com/
 * 
 *  STRIPBANDUNK PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.stripbandunk.jglasspane;

import com.stripbandunk.jglasspane.component.JGlassPaneComponent;
import com.stripbandunk.jglasspane.helper.GraphicHelper;
import java.awt.Component;
import java.awt.Rectangle;
import javax.swing.JLayeredPane;

/**
 * 
 * @author Eko Kurniawan Khannedy
 */
public class JGlassPane extends JLayeredPane {

    private static final long serialVersionUID = 1L;

    public JGlassPane() {
        addComponentListener(new ResizeListener());
    }

    public void removeGlassPaneComponent(JGlassPaneComponent comp) {
        Component component = comp.getComponent();
        remove(component);
    }

    public void addGlassPaneComponent(JGlassPaneComponent comp) {
        Component component = comp.getComponent();
        add(component);
    }

    @Override
    protected void addImpl(Component comp, Object constraints, int index) {
        super.addImpl(comp, constraints, index);
        comp.setBounds(0, 0, getWidth(), getHeight());
    }

    public Rectangle getClip(Component component) {
        return GraphicHelper.getClip(component, this);
    }
}
