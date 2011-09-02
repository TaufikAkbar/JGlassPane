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
public class WipeUpTransition extends AbstractTransition {

    private Rectangle.Double rectangle;

    public WipeUpTransition() {
        rectangle = new Rectangle.Double();
    }

    public void paint(Graphics2D g2, int effect) {
        // mendapatkan nilai pertahap
        double step = getClip().height / 100.0;

        // mendapatkan nilai x, y, width, height
        double x = getClip().x;
        double y = getClip().y;
        double width = getClip().width;
        double height = getClip().height - (step * effect);

        // mengubah nilai rectangle double
        rectangle.setRect(x, y, width, height);

        // melakukan penggambaran
        g2.setClip(getClip());
        g2.setPaint(getPaint());
        g2.fill(rectangle);
    }
}
