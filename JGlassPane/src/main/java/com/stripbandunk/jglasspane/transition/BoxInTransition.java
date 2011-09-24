/*
 *  Copyright (c) 2011, StripBandunk and/or its affiliates. All rights reserved.
 * 
 *       http://stripbandunk.com/
 * 
 *  STRIPBANDUNK PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.stripbandunk.jglasspane.transition;

import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;

/**
 *
 * @author Eko Kurniawan Khannedy
 */
public class BoxInTransition extends AbstractGeneralTransition {

    private Rectangle.Double rectangle;

    private Point center;

    public BoxInTransition() {
        rectangle = new Rectangle.Double();
        center = new Point();
    }

    @Override
    protected void doPaint(Graphics2D g2, int effect) {
        // Mengubah point tengah
        center.setLocation(getClip().width / 2, getClip().height / 2);

        // mendapatkan nilai pertahap
        double stepW = getClip().width / 100.0;
        double stepH = getClip().height / 100.0;

        // mendapatkan nilai x, y, width, height
        double width = getClip().width - (stepW * effect);
        double height = getClip().height - (stepH * effect);
        double x = center.x - (width / 2) + getClip().x;
        double y = center.y - (height / 2) + getClip().y;

        // mengubah nilai rectangle double
        rectangle.setRect(x, y, width, height);

        // melakukan penggambaran
        g2.fill(rectangle);
    }
}
