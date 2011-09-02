/*
 *  Copyright (c) 2011, StripBandunk and/or its affiliates. All rights reserved.
 * 
 *       http://stripbandunk.com/
 * 
 *  STRIPBANDUNK PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.stripbandunk.jglasspane.transition;

import java.awt.Paint;
import java.awt.Rectangle;

/**
 *
 * @author Eko Kurniawan Khannedy
 */
public abstract class AbstractTransition implements Transition {

    private Rectangle clip;

    private Paint paint;

    public AbstractTransition(Rectangle clip, Paint paint) {
        this.clip = clip;
        this.paint = paint;
    }

    public Paint getPaint() {
        return this.paint;
    }

    public Rectangle getClip() {
        return this.clip;
    }
}
