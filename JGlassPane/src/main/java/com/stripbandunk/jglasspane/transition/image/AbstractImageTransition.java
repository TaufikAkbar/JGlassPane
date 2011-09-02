/*
 *  Copyright (c) 2011, StripBandunk and/or its affiliates. All rights reserved.
 * 
 *       http://stripbandunk.com/
 * 
 *  STRIPBANDUNK PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.stripbandunk.jglasspane.transition.image;

import com.stripbandunk.jglasspane.error.GlassPaneException;
import java.awt.Point;

/**
 *
 * @author echo
 */
public abstract class AbstractImageTransition implements ImageTransition {

    private Point coordinate;

    public AbstractImageTransition() {
    }

    public AbstractImageTransition(Point coordinate) {
        this.coordinate = coordinate;
    }

    public void beforeStart() {
        if (coordinate == null) {
            throw new GlassPaneException("Coordinate is null");
        }
    }

    @SuppressWarnings("NoopMethodInAbstractClass")
    public void afterFinish() {
        // do nothing
    }

    @Override
    public Point getCoordinate() {
        return coordinate;
    }

    public void setCoordinate(Point coordinate) {
        this.coordinate = coordinate;
    }
}
