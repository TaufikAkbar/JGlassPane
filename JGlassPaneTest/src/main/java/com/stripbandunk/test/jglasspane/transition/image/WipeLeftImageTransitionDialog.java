/*
 *  Copyright (c) 2011, StripBandunk and/or its affiliates. All rights reserved.
 * 
 *       http://stripbandunk.com/
 * 
 *  STRIPBANDUNK PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.stripbandunk.test.jglasspane.transition.image;

import com.stripbandunk.jglasspane.transition.image.WipeLeftImageTransition;
import java.awt.Frame;
import java.awt.Point;

/**
 *
 * @author Eko Kuriawan Khannedy
 */
public class WipeLeftImageTransitionDialog extends ImageTransitionDialog {

    private static final long serialVersionUID = 1L;

    private WipeLeftImageTransition transition;

    public WipeLeftImageTransitionDialog(Frame parent, boolean modal) {
        super(parent, modal);

        transition = new WipeLeftImageTransition();
        getImageTransitionComponent().setTransition(transition);
    }

    @Override
    public void startTransition(Point location) {
        transition.setCoordinate(location);
        getImageTransitionComponent().start();
    }
}
