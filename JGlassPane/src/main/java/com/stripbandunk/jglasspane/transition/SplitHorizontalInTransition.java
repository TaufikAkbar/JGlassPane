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
public class SplitHorizontalInTransition extends AbstractTransition {

    private Rectangle.Double rectangle;

    public SplitHorizontalInTransition() {
        rectangle = new Rectangle.Double();
    }

    public void paint(Graphics2D g2, int effect) {
        // Mendapatkan nilai pertahap
        double step = getClip().height / 100.0;

        // Mendapatkan nilai tengah
        double center = getClip().y + getClip().height / 2;

        // Mendapatkan nilai x, y, width, height
        double height = getClip().height - (step * effect);
        double width = getClip().width;
        double x = getClip().x;
        double y = center - (height / 2);

        // mengubah nilai rectangle double
        rectangle.setRect(x, y, width, height);

        // melakukan penggambaran
        g2.setClip(getClip());
        g2.setPaint(getPaint());
        g2.fill(rectangle);
    }
}
