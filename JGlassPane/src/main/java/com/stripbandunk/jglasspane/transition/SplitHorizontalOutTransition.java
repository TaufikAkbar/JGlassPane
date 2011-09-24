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
import java.awt.geom.Area;

/**
 *
 * @author Eko Kurniawan Khannedy
 */
public class SplitHorizontalOutTransition extends AbstractGeneralTransition {

    private Rectangle.Double rectangle;

    public SplitHorizontalOutTransition() {
        rectangle = new Rectangle.Double();
    }

    @Override
    protected void doPaint(Graphics2D g2, int effect) {
        // Mendapatkan nilai pertahap
        double step = getClip().height / 100.0;

        // Mendapatkan nilai tengah
        double center = getClip().y + getClip().height / 2;

        // mendapatkan nilai x, y, width, height
        double height = step * effect;
        double width = getClip().width;
        double x = getClip().x;
        double y = center - (height / 2);

        // Mengubah nilai rectangle double
        rectangle.setRect(x, y, width, height);

        // Mengubah nilai area
        Area area = new Area(getClip());
        Area area2 = new Area(rectangle);
        area.exclusiveOr(area2);

        // melakukan pengambaran
        g2.setClip(area);
        g2.fill(getClip());
    }
}
