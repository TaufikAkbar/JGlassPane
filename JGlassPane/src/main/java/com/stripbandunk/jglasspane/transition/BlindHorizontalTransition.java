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
 * @author Eko Kurniatan Khannedy
 */
public class BlindHorizontalTransition extends AbstractTransition {

    private int totalBlind;

    private Rectangle.Double rectable;

    public BlindHorizontalTransition(Rectangle clip, Paint paint, int totalBlind) {
        super(clip, paint);
        this.totalBlind = totalBlind;
        this.rectable = new java.awt.geom.Rectangle2D.Double();
    }

    public void paint(Graphics2D g2, int effect) {
        double temp = ((getClip().height * 1.0) / (getTotalBlind() * 1.0));

        // mendapatkan nilai pertahap
        double step = temp / 100.0;

        // mengubah nilai Graphics
        g2.setClip(getClip());
        g2.setPaint(getPaint());

        // mendapatkan nilai x, y, width, height lalu menggambarnya
        double x = getClip().x;
        double height = temp - (step * effect);
        double width = getClip().width;
        for (int i = 0; i < getTotalBlind(); i++) {
            double y = getClip().y + (temp * i);
            rectable.setRect(x, y, width, height);
            g2.fill(rectable);
        }
    }

    public Rectangle.Double getRectable() {
        return rectable;
    }

    public int getTotalBlind() {
        return totalBlind;
    }
}
