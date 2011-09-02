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
import java.awt.geom.Area;

/**
 *
 * @author Eko Kurniawan Khannedy
 */
public class BoxOutTransition extends AbstractTransition {

    private Point center;

    private Rectangle.Double rectangle;

    public BoxOutTransition() {
        rectangle = new Rectangle.Double();
        center = new Point();
    }

    public void paint(Graphics2D g2, int effect) {
        // Mengubah point tengah
        center.setLocation(getClip().width / 2, getClip().height / 2);

        // mendapatkan nila pertahap
        double stepW = getClip().width / 100.0;
        double stepH = getClip().height / 100.0;

        // mendapatkan nilai x, y, width, height
        double width = (stepW * effect);
        double height = (stepH * effect);
        double x = center.x - (width / 2) + getClip().x;
        double y = center.y - (height / 2) + getClip().y;

        // mengubah nilai rectangle double
        rectangle.setRect(x, y, width, height);

        // mengubah nilai area 
        Area area = new Area(getClip());
        Area area2 = new Area(rectangle);
        area.exclusiveOr(area2);

        // melakukan penggambaran
        g2.setClip(area);
        g2.setPaint(getPaint());
        g2.fill(getClip());
    }
}
