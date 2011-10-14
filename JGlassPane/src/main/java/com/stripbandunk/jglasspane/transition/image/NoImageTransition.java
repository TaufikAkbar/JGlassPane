/*
 *  Copyright (c) 2011, StripBandunk and/or its affiliates. All rights reserved.
 * 
 *       http://stripbandunk.com/
 * 
 *  STRIPBANDUNK PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.stripbandunk.jglasspane.transition.image;

import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.image.BufferedImage;

/**
 *
 * @author Eko Kurniawan Khannedy
 */
public class NoImageTransition implements ImageTransition {

    @Override
    public void beforeStart() {
        // do nothing
    }

    @Override
    public void afterFinish() {
        // do nothing
    }

    @Override
    public void paint(Graphics2D g2, BufferedImage image, int effect) {
        // do nothing
    }

    @Override
    public Point getCoordinate() {
        return null;
    }
}
