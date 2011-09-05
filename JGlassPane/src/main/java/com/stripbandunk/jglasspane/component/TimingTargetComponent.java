/*
 *  Copyright (c) 2011, StripBandunk and/or its affiliates. All rights reserved.
 * 
 *       http://stripbandunk.com/
 * 
 *  STRIPBANDUNK PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.stripbandunk.jglasspane.component;

import javax.swing.JPanel;
import org.jdesktop.animation.timing.Animator;
import org.jdesktop.animation.timing.TimingTarget;

/**
 *
 * @author Eko Kurniawan Khannedy
 */
public abstract class TimingTargetComponent extends JPanel implements TimingTarget {

    private Animator animator;

    @SuppressWarnings("LeakingThisInConstructor")
    TimingTargetComponent() {
        animator = new Animator(0);
        animator.addTarget(this);
    }

    protected Animator getAnimator() {
        return animator;
    }

    protected abstract void onAnimatorBegin();

    protected abstract void onAnimatorEnd();

    @Override
    @SuppressWarnings("NoopMethodInAbstractClass")
    public void timingEvent(float fraction) {
    }

    @Override
    public void begin() {
        onAnimatorBegin();
    }

    @Override
    public void end() {
        onAnimatorEnd();
    }

    @Override
    @SuppressWarnings("NoopMethodInAbstractClass")
    public void repeat() {
    }
}
