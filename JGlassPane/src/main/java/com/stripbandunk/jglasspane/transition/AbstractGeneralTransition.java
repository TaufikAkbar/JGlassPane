/*
 *  Copyright (c) 2011, StripBandunk and/or its affiliates. All rights reserved.
 * 
 *       http://stripbandunk.com/
 * 
 *  STRIPBANDUNK PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.stripbandunk.jglasspane.transition;

import com.stripbandunk.jglasspane.component.TransitionComponent;
import java.awt.Graphics2D;
import java.awt.Paint;
import java.awt.Rectangle;

/**
 *
 * @author Eko Kurniawan Khannedy
 */
public abstract class AbstractGeneralTransition extends AbstractTransition {

    private Paint paint;

    private Rectangle clip;

    @Override
    public void beforeStart(TransitionComponent component) {
        super.beforeStart(component);
        if (getClip() == null) {
            setClip(new Rectangle(0, 0, component.getWidth(), component.getHeight()));
        }
        if (getPaint() == null) {
            setPaint(component.getBackground());
        }
    }

    public Rectangle getClip() {
        return clip;
    }

    public void setClip(Rectangle clip) {
        this.clip = clip;
    }

    public Paint getPaint() {
        return paint;
    }

    public void setPaint(Paint paint) {
        this.paint = paint;
    }

    @Override
    @SuppressWarnings("FinalMethod")
    public final void paint(Graphics2D g2, int effect) {
        g2.setClip(getClip());
        g2.setPaint(getPaint());

        doPaint(g2, effect);

        g2.dispose();
    }

    protected abstract void doPaint(Graphics2D g2, int effect);
}
