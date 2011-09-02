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
public interface ImageTransition {
    
    public void beforeStart();
    
    public void afterFinish();

    public void paint(Graphics2D g2, BufferedImage image, int effect);

    public Point getCoordinate();
}
