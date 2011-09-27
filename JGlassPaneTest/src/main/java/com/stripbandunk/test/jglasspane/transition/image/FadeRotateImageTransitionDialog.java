/*
 *  Copyright (c) 2011, StripBandunk and/or its affiliates. All rights reserved.
 * 
 *       http://stripbandunk.com/
 * 
 *  STRIPBANDUNK PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.stripbandunk.test.jglasspane.transition.image;

import com.stripbandunk.jglasspane.transition.image.FadeRotateImageTransition;
import java.awt.Frame;
import java.awt.Point;

/**
 *
 * @author echo
 */
public class FadeRotateImageTransitionDialog extends ImageTransitionDialog {

    private static final long serialVersionUID = 1L;

    private FadeRotateImageTransition transition;

    public FadeRotateImageTransitionDialog(Frame parent, boolean modal) {
        super(parent, modal);

        transition = new FadeRotateImageTransition();
        getImageTransitionComponent().setTransition(transition);
    }

    @Override
    public void startTransition(Point location) {
        transition.setCoordinate(location);
        getImageTransitionComponent().start();
    }
}
