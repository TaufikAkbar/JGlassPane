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

/**
 *
 * @author Eko Kurniatan Khannedy
 */
public class BlindVerticalTransition extends AbstractTransition {

    private int totalBlind;

    private Rectangle.Double rectable;

    public BlindVerticalTransition() {
    }

    public BlindVerticalTransition(Rectangle clip, Paint paint, int totalBlind) {
        super(clip, paint);
        this.totalBlind = totalBlind;
    }

    @Override
    public void beforeStart() {
        this.rectable = new java.awt.geom.Rectangle2D.Double();
    }

    public void paint(Graphics2D g2, int effect) {
        // mendaptkan nilai temp
        double temp = (getClip().width * 1.0) / (getTotalBlind() * 1.0);

        // mendapatkan nilai pertahap
        double step = temp / 100.0;

        // mengubah nilai Graphics
        g2.setClip(getClip());
        g2.setPaint(getPaint());

        // mendapatkan nilai x, y, width, height lalu menggambarnya
        double y = getClip().y;
        double width = temp - (step * effect);
        double height = getClip().height;
        for (int i = 0; i < getTotalBlind(); i++) {
            double x = getClip().x + (temp * i);
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
