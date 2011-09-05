/*
 *  Copyright (c) 2011, StripBandunk and/or its affiliates. All rights reserved.
 * 
 *       http://stripbandunk.com/
 * 
 *  STRIPBANDUNK PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.stripbandunk.jglasspane.component;

import javax.swing.JPanel;
import org.jdesktop.animation.timing.TimingTarget;

/**
 *
 * @author Eko Kurniawan Khannedy
 */
abstract class TimingTargetComponent extends JPanel implements TimingTarget {

    @Override
    @SuppressWarnings("NoopMethodInAbstractClass")
    public void timingEvent(float fraction) {
    }

    @Override
    @SuppressWarnings("NoopMethodInAbstractClass")
    public void begin() {
    }

    @Override
    @SuppressWarnings("NoopMethodInAbstractClass")
    public void end() {
    }

    @Override
    @SuppressWarnings("NoopMethodInAbstractClass")
    public void repeat() {
    }
}
