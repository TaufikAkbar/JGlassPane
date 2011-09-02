/*
 *  Copyright (c) 2011, StripBandunk and/or its affiliates. All rights reserved.
 * 
 *       http://stripbandunk.com/
 * 
 *  STRIPBANDUNK PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.stripbandunk.jglasspane.transition;

import com.stripbandunk.jglasspane.error.GlassPaneException;
import java.awt.Paint;
import java.awt.Rectangle;

/**
 *
 * @author Eko Kurniawan Khannedy
 */
public abstract class AbstractTransition implements Transition {

    private Rectangle clip;

    private Paint paint;

    public AbstractTransition() {
        this(null, null);
    }

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

    public void setClip(Rectangle clip) {
        this.clip = clip;
    }

    public void setPaint(Paint paint) {
        this.paint = paint;
    }

    @SuppressWarnings("NoopMethodInAbstractClass")
    public void beforeStart() {
        if (clip == null) {
            throw new GlassPaneException("Clip is null");
        } else if (paint == null) {
            throw new GlassPaneException("Paint is null");
        }
    }

    @SuppressWarnings("NoopMethodInAbstractClass")
    public void afterFinish() {
        // do nothing
    }
}
