/*
 *  Copyright (c) 2011, StripBandunk and/or its affiliates. All rights reserved.
 * 
 *       http://stripbandunk.com/
 * 
 *  STRIPBANDUNK PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.stripbandunk.jglasspane.transition;

import java.awt.Graphics2D;
import java.awt.Paint;
import java.awt.Rectangle;

/**
 *
 * @author Eko Kurniawan Khannedy
 */
public interface Transition {

    public void beforeStart();

    public void afterFinish();

    public void paint(Graphics2D g2, int effect);

    public Paint getPaint();

    public Rectangle getClip();
}
