/*
 *  Copyright (c) 2011, StripBandunk and/or its affiliates. All rights reserved.
 * 
 *       http://stripbandunk.com/
 * 
 *  STRIPBANDUNK PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.stripbandunk.test.jglasspane.transition;

import com.stripbandunk.jglasspane.transition.WipeLeftTransition;
import java.awt.Frame;
import java.awt.Paint;
import java.awt.Rectangle;

/**
 *
 * @author echo
 */
public class WipeLeftTransitionDialog extends TransitionDialog {

    private static final long serialVersionUID = 1L;

    private WipeLeftTransition transition;

    public WipeLeftTransitionDialog(Frame parent, boolean modal) {
        super(parent, modal);

        transition = new WipeLeftTransition();
        getTransitionComponent().setTransition(transition);
    }

    @Override
    public void startTransition(Rectangle rectangle, Paint paint) {
        transition.setClip(rectangle);
        transition.setPaint(paint);
        getTransitionComponent().start();
    }
}
