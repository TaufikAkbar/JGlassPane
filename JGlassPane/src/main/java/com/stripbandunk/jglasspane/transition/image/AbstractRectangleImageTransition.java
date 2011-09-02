/*
 *  Copyright (c) 2011, StripBandunk and/or its affiliates. All rights reserved.
 * 
 *       http://stripbandunk.com/
 * 
 *  STRIPBANDUNK PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.stripbandunk.jglasspane.transition.image;

import java.awt.Rectangle;
import java.awt.geom.Rectangle2D.Double;

/**
 *
 * @author echo
 */
abstract class AbstractRectangleImageTransition extends AbstractImageTransition {

    private Rectangle.Double rectangle;

    AbstractRectangleImageTransition() {
        rectangle = new Rectangle.Double();
    }

    public Double getRectangle() {
        return rectangle;
    }

    public void setRectangle(Double rectangle) {
        this.rectangle = rectangle;
    }
}
