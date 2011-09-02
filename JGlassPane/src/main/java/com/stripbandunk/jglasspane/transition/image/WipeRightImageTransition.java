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
public class WipeRightImageTransition extends AbstractRectangleImageTransition {

    public void paint(Graphics2D g2, BufferedImage image, int effect) {
        double step = image.getWidth() / 100.0;

        double x = getCoordinate().x + (step * effect);
        double width = image.getWidth() - (step * effect);

        getRectangle().setRect(x, getCoordinate().y, width, image.getHeight());

        g2.setClip(getRectangle());
        g2.drawImage(image, getCoordinate().x, getCoordinate().y, null);
    }
}
