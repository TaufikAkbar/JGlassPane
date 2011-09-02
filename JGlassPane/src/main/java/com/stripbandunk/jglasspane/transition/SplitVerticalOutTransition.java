/*
 *  Copyright (c) 2011, StripBandunk and/or its affiliates. All rights reserved.
 * 
 *       http://stripbandunk.com/
 * 
 *  STRIPBANDUNK PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.stripbandunk.jglasspane.transition;

import java.awt.Graphics2D;
import java.awt.Paint;
import java.awt.Rectangle;
import java.awt.geom.Area;
import java.awt.geom.Rectangle2D.Double;

/**
 *
 * @author Eko Kurniawan Khannedy
 */
public class SplitVerticalOutTransition extends AbstractTransition {

    private Rectangle.Double rectangle;

    public SplitVerticalOutTransition(Rectangle clip, Paint paint) {
        super(clip, paint);
    }

    @Override
    public void beforeStart() {
        rectangle = new Double();
    }

    public Double getRectangle() {
        return rectangle;
    }

    public void paint(Graphics2D g2, int effect) {
        // Mendapatkan nilai pertahap
        double step = getClip().width / 100.0;

        // Mendapatkan nilai tengah
        double center = getClip().x + getClip().width / 2;

        // mendapatkan nilai x, y, width, height
        double width = step * effect;
        double height = getClip().height;
        double x = center - (width / 2);
        double y = getClip().y;

        // mengubah nilai rectangle double
        getRectangle().setRect(x, y, width, height);

        // Mengubah nilai area dan area2
        Area area = new Area(getClip());
        Area area2 = new Area(getRectangle());
        area.exclusiveOr(area2);

        // Melakukan penggambaran
        g2.setClip(area);
        g2.setPaint(getPaint());
        g2.fill(getClip());
    }
}
