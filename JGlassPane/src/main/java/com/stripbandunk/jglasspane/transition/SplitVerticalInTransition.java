/*
 *  Copyright (c) 2011, StripBandunk and/or its affiliates. All rights reserved.
 * 
 *       http://stripbandunk.com/
 * 
 *  STRIPBANDUNK PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.stripbandunk.jglasspane.transition;

import java.awt.Graphics2D;
import java.awt.Rectangle;

/**
 *
 * @author Eko Kurniawan Khannedy
 */
public class SplitVerticalInTransition extends AbstractGeneralTransition {

    private Rectangle.Double rectangle;

    public SplitVerticalInTransition() {
        rectangle = new Rectangle.Double();
    }

    @Override
    protected void doPaint(Graphics2D g2, int effect) {
        // Mendapatkan nilai pertahap
        double step = getClip().width / 100.0;

        // Mendapatkan nilai tengah
        double center = getClip().x + getClip().width / 2;

        // mendapatkan nilai x, y, width, height
        double width = getClip().width - (step * effect);
        double height = getClip().height;
        double x = center - (width / 2);
        double y = getClip().y;

        // mengubah nilai rectangle double
        rectangle.setRect(x, y, width, height);

        // melakukan penggambaran
        g2.fill(rectangle);
    }
}
