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
public class FadeTransition extends AbstractTransition {

    private AlphaComposite alphaComposite;

    public FadeTransition() {
        alphaComposite = AlphaComposite.SrcOver;
    }

    public void paint(Graphics2D g2, int effect) {
        // Mengubah Clip
        g2.setClip(getClip());
        // Mengubah warna Graphics
        g2.setPaint(getPaint());

        // Mengubah alpha composite
        alphaComposite = alphaComposite.derive(1F - (effect / 100F));

        // Mengubah alpha composite milik Graphics
        g2.setComposite(alphaComposite);
        // Menggambar 
        g2.fill(getClip());
    }
}
