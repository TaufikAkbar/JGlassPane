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
 * @author Eko Kurniatan Khannedy
 */
public class BlindHorizontalTransition extends AbstractGeneralTransition {

    private int totalBlind;

    private Rectangle.Double rectable;

    public BlindHorizontalTransition() {
        this.rectable = new Rectangle.Double();
    }

    public void setTotalBlind(int totalBlind) {
        this.totalBlind = totalBlind;
    }

    public int getTotalBlind() {
        return totalBlind;
    }

    @Override
    protected void doPaint(Graphics2D g2, int effect) {
        double temp = ((getClip().height * 1.0) / (totalBlind * 1.0));

        // mendapatkan nilai pertahap
        double step = temp / 100.0;

        // mendapatkan nilai x, y, width, height lalu menggambarnya
        double x = getClip().x;
        double height = temp - (step * effect);
        double width = getClip().width;
        for (int i = 0; i < totalBlind; i++) {
            double y = getClip().y + (temp * i);
            rectable.setRect(x, y, width, height);
            g2.fill(rectable);
        }
    }
}
