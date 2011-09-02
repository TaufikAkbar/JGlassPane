/*
 *  Copyright (c) 2011, StripBandunk and/or its affiliates. All rights reserved.
 * 
 *       http://stripbandunk.com/
 * 
 *  STRIPBANDUNK PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.stripbandunk.jglasspane.transition.image;

import com.stripbandunk.jglasspane.helper.GraphicHelper;
import java.awt.Graphics2D;
import java.awt.geom.Area;
import java.awt.image.BufferedImage;

/**
 *
 * @author echo
 */
public class BoxOutImageTransition extends AbstractCenterImageTransition {

    public void paint(Graphics2D g2, BufferedImage image, int effect) {
        getCenter().setLocation(image.getWidth() / 2.0, image.getHeight() / 2.0);

        double stepW = image.getWidth() / 100.0;
        double stepH = image.getHeight() / 100.0;

        double width = (stepW * effect);
        double height = (stepH * effect);
        double x = getCenter().x - (width / 2.0) + getCoordinate().x;
        double y = getCenter().y - (height / 2.0) + getCoordinate().y;

        getRectangle().setRect(x, y, width, height);

        Area area = new Area(GraphicHelper.getRectangleImage(image, getCoordinate()));
        Area area2 = new Area(getRectangle());
        area.exclusiveOr(area2);

        g2.setClip(area);
        g2.drawImage(image, getCoordinate().x, getCoordinate().y, null);
    }
}
