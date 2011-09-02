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
public class WipeRightTransition extends AbstractTransition {

    private Rectangle.Double rectangle;

    public WipeRightTransition() {
        rectangle = new Rectangle.Double();
    }

    public void paint(Graphics2D g2, int effect) {
        // Mendapatkan nilai pertahap
        double step = getClip().width / 100.0;

        // mendapatkan posisi x, y, width, height
        double x = getClip().x + (step * effect);
        double y = getClip().y;
        double width = getClip().width - (step * effect);
        double height = getClip().height;

        // mengubah nilai double rectangle
        rectangle.setRect(x, y, width, height);

        // melakukan penggambaran
        g2.setClip(getClip());
        g2.setPaint(getPaint());
        g2.fill(rectangle);
    }
}
