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
public class WipeDownTransition extends AbstractGeneralTransition {

    private Rectangle.Double rectangle;

    public WipeDownTransition() {
        rectangle = new Rectangle.Double();
    }

    @Override
    protected void doPaint(Graphics2D g2, int effect) {
        // mandapatkan nilai per tahap
        double step = getClip().height / 100.0;

        // Mendapatkan nilai x, y, width, height
        double x = getClip().x;
        double y = getClip().y + (step * effect);
        double width = getClip().width;
        double height = getClip().height - (step * effect);

        // Mengubah Double Rectangle
        rectangle.setRect(x, y, width, height);

        // Melakukan penggambaran
        g2.fill(rectangle);
    }
}
