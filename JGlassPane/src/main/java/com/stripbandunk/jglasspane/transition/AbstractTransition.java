/*
 *  Copyright (c) 2011, StripBandunk and/or its affiliates. All rights reserved.
 * 
 *       http://stripbandunk.com/
 * 
 *  STRIPBANDUNK PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.stripbandunk.jglasspane.transition;

import com.stripbandunk.jglasspane.component.TransitionComponent;

/**
 *
 * @author Eko Kurniawan Khannedy
 */
public abstract class AbstractTransition implements Transition {

    private TransitionComponent component;

    public TransitionComponent getComponent() {
        return component;
    }

    @Override
    public void beforeStart(TransitionComponent component) {
        this.component = component;
    }

    @SuppressWarnings("NoopMethodInAbstractClass")
    @Override
    public void afterFinish() {
        // do nothing
    }
}
