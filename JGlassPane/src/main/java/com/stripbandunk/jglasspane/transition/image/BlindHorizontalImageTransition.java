/*
 *  Copyright (c) 2011, StripBandunk and/or its affiliates. All rights reserved.
 * 
 *       http://stripbandunk.com/
 * 
 *  STRIPBANDUNK PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.stripbandunk.jglasspane.transition.image;

import java.awt.Graphics2D;
import java.awt.geom.Area;
import java.awt.image.BufferedImage;

/**
 *
 * @author echo
 */
public class BlindHorizontalImageTransition extends AbstractRectangleImageTransition {

    private int totalBlind;

    public void setTotalBlind(int totalBlind) {
        this.totalBlind = totalBlind;
    }

    public int getTotalBlind() {
        return totalBlind;
    }

    public void paint(Graphics2D g2, BufferedImage image, int effect) {
        double temp = (image.getHeight() * 1.0) / (getTotalBlind() * 1.0);
        double step = temp / 100.0;

        double height = temp - (step * effect);
        Area area = new Area();

        for (int i = 0; i < getTotalBlind(); i++) {
            double y = getCoordinate().y + (temp * i);
            getRectangle().setRect(getCoordinate().x, y, image.getWidth(), height);
            Area area2 = new Area(getRectangle());
            area.add(area2);
        }
        g2.setClip(area);
        g2.drawImage(image, getCoordinate().x, getCoordinate().x, null);
    }
}
