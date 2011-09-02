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
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.geom.Rectangle2D.Double;

/**
 *
 * @author Eko Kurniawan Khannedy
 */
public class BoxInTransition extends AbstractTransition {

    private Rectangle.Double rectangle;

    private Point center;

    public BoxInTransition() {
    }

    public BoxInTransition(Rectangle clip, Paint paint) {
        super(clip, paint);
    }

    @Override
    public void beforeStart() {
        rectangle = new Double();
        center = new Point();
    }

    public Point getCenter() {
        return center;
    }

    public Double getRectangle() {
        return rectangle;
    }

    public void paint(Graphics2D g2, int effect) {
        // Mengubah point tengah
        getCenter().setLocation(getClip().width / 2, getClip().height / 2);

        // mendapatkan nilai pertahap
        double stepW = getClip().width / 100.0;
        double stepH = getClip().height / 100.0;

        // mendapatkan nilai x, y, width, height
        double width = getClip().width - (stepW * effect);
        double height = getClip().height - (stepH * effect);
        double x = getCenter().x - (width / 2) + getClip().x;
        double y = getCenter().y - (height / 2) + getClip().y;

        // mengubah nilai rectangle double
        getRectangle().setRect(x, y, width, height);

        // melakukan penggambaran
        g2.setClip(getClip());
        g2.setPaint(getPaint());
        g2.fill(getRectangle());
    }
}
