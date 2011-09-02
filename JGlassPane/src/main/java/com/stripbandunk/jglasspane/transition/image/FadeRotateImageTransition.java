/*
 *  Copyright (c) 2011, StripBandunk and/or its affiliates. All rights reserved.
 * 
 *       http://stripbandunk.com/
 * 
 *  STRIPBANDUNK PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.stripbandunk.jglasspane.transition.image;

import com.stripbandunk.jglasspane.helper.GraphicHelper;
import java.awt.AlphaComposite;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

/**
 *
 * @author echo
 */
public class FadeRotateImageTransition extends AbstractImageTransition {

    private AlphaComposite alphaComposite;

    public FadeRotateImageTransition() {
        alphaComposite = AlphaComposite.SrcOver;
    }

    public AlphaComposite getAlphaComposite() {
        return alphaComposite;
    }

    public void paint(Graphics2D g2, BufferedImage image, int effect) {
        alphaComposite = getAlphaComposite().derive(1.0F - (effect / 100.0F));
        double step = 360 / 100.0;

        g2.setClip(GraphicHelper.getRectangleImage(image, getCoordinate()));
        g2.rotate(Math.toRadians(step * effect), getCoordinate().x + image.getWidth() / 2,
                getCoordinate().y + image.getHeight() / 2);
        g2.setComposite(getAlphaComposite());
        g2.drawImage(image, getCoordinate().x, getCoordinate().y, null);
    }
}
