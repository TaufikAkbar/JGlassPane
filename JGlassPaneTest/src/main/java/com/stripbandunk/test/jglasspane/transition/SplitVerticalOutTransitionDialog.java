/*
 *  Copyright (c) 2011, StripBandunk and/or its affiliates. All rights reserved.
 * 
 *       http://stripbandunk.com/
 * 
 *  STRIPBANDUNK PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.stripbandunk.test.jglasspane.transition;

import com.stripbandunk.jglasspane.transition.SplitVerticalOutTransition;
import java.awt.Frame;
import java.awt.Paint;
import java.awt.Rectangle;

/**
 *
 * @author echo
 */
public class SplitVerticalOutTransitionDialog extends TransitionDialog {

    private static final long serialVersionUID = 1L;

    private SplitVerticalOutTransition transition;

    public SplitVerticalOutTransitionDialog(Frame parent, boolean modal) {
        super(parent, modal);

        transition = new SplitVerticalOutTransition();
        getTransitionComponent().setTransition(transition);
    }

    @Override
    public void startTransition(Rectangle rectangle, Paint paint) {
        transition.setClip(rectangle);
        transition.setPaint(paint);
        getTransitionComponent().start();
    }
}
