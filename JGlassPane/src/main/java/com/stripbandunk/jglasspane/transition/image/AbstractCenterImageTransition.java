/*
 *  Copyright (c) 2011, StripBandunk and/or its affiliates. All rights reserved.
 * 
 *       http://stripbandunk.com/
 * 
 *  STRIPBANDUNK PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.stripbandunk.jglasspane.transition.image;

import java.awt.Point;

/**
 *
 * @author echo
 */
abstract class AbstractCenterImageTransition extends AbstractRectangleImageTransition {

    private Point center;

    AbstractCenterImageTransition() {
        center = new Point();
    }

    public Point getCenter() {
        return center;
    }
}
