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
public class SplitHorizontalInImageTransition extends AbstractRectangleImageTransition {

    public void paint(Graphics2D g2, BufferedImage image, int effect) {
        double step = image.getHeight() / 100.0;

        double center = getCoordinate().y + image.getHeight() / 2.0;

        double height = image.getHeight() - (step * effect);
        double y = center - (height / 2.0);

        getRectangle().setRect(getCoordinate().x, y,
                image.getWidth(), height);

        g2.setClip(getRectangle());
        g2.drawImage(image, getCoordinate().x, getCoordinate().x, null);
    }
}
