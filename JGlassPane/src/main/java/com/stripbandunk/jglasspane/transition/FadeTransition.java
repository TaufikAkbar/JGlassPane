/*
 *  Copyright (c) 2011, StripBandunk and/or its affiliates. All rights reserved.
 * 
 *       http://stripbandunk.com/
 * 
 *  STRIPBANDUNK PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.stripbandunk.jglasspane.transition;

import java.awt.AlphaComposite;
import java.awt.Graphics2D;

/**
 *
 * @author Eko Kurniawan Khannedy
 */
public class FadeTransition extends AbstractGeneralTransition {

    private AlphaComposite alphaComposite;

    public FadeTransition() {
        alphaComposite = AlphaComposite.SrcOver;
    }

    @Override
    protected void doPaint(Graphics2D g2, int effect) {
        // Mengubah alpha composite
        alphaComposite = alphaComposite.derive(1F - (effect / 100F));

        // Mengubah alpha composite milik Graphics
        g2.setComposite(alphaComposite);
        // Menggambar 
        g2.fill(getClip());
    }
}
