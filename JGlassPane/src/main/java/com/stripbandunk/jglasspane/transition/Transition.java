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

/**
 *
 * @author Eko Kurniawan Khannedy
 */
public interface Transition {

    public void beforeStart(TransitionComponent component);

    public void afterFinish();

    public void paint(Graphics2D g2, int effect);
}
