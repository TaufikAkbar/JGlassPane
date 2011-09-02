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
import java.awt.geom.Rectangle2D.Double;

/**
 *
 * @author Eko Kurniawan Khannedy
 */
public class WipeLeftTransition extends AbstractTransition {

    private Rectangle.Double rectangle;

    public WipeLeftTransition(Rectangle clip, Paint paint) {
        super(clip, paint);
    }

    @Override
    public void beforeStart() {
        rectangle = new Double();
    }

    public void paint(Graphics2D g2, int effect) {
        // Mendapatkan nilai pertahap
        double step = getClip().width / 100.0;

        // mendapatkan nilai x, y, width, height
        double x = getClip().x;
        double y = getClip().y;
        double width = getClip().width - (step * effect);
        double height = getClip().height;

        // mengubah nilai double rectangle
        getRectangle().setRect(x, y, width, height);

        // melakukan pengambaran
        g2.setClip(getClip());
        g2.setPaint(getPaint());
        g2.fill(getRectangle());
    }

    public Double getRectangle() {
        return rectangle;
    }
}
