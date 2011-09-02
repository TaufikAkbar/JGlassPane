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
public class SplitVerticalOutImageTransition extends AbstractRectangleImageTransition {

    public void paint(Graphics2D g2, BufferedImage image, int effect) {
        double step = image.getWidth() / 100.0;

        double width = (step * effect);
        double center = getCoordinate().x + image.getWidth() / 2.0;
        double x = center - (width / 2.0);

        getRectangle().setRect(x, getCoordinate().y, width, image.getHeight());

        Area area = new Area(GraphicHelper.getRectangleImage(image, getCoordinate()));
        Area area2 = new Area(getRectangle());
        area.exclusiveOr(area2);

        g2.setClip(area);
        g2.drawImage(image, getCoordinate().x, getCoordinate().x, null);
    }
}
