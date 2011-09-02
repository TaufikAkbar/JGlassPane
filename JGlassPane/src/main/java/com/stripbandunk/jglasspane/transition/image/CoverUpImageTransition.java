/*
 *  Copyright (c) 2011, StripBandunk and/or its affiliates. All rights reserved.
 * 
 *       http://stripbandunk.com/
 * 
 *  STRIPBANDUNK PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.stripbandunk.jglasspane.transition.image;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

/**
 *
 * @author echo
 */
public class CoverUpImageTransition extends AbstractRectangleImageTransition {

    public void paint(Graphics2D g2, BufferedImage image, int effect) {
        double step = image.getHeight() / 100.0;

        double y = getCoordinate().y - step * effect;

        getRectangle().setRect(getCoordinate().x, getCoordinate().y,
                image.getWidth(), image.getHeight());

        g2.setClip(getRectangle());
        g2.drawImage(image, getCoordinate().x, (int) y, null);
    }
}
