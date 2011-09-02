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
public class SplitHorizontalOutImageTransition extends AbstractRectangleImageTransition {

    public void paint(Graphics2D g2, BufferedImage image, int effect) {
        double step = image.getHeight() / 100.0;

        double height = (step * effect);
        double center = getCoordinate().y + image.getHeight() / 2.0;
        double y = center - (height / 2.0);

        getRectangle().setRect(getCoordinate().x, y, image.getWidth(), height);

        Area area = new Area(GraphicHelper.getRectangleImage(image, getCoordinate()));
        Area area2 = new Area(getRectangle());
        area.exclusiveOr(area2);

        g2.setClip(area);
        g2.drawImage(image, getCoordinate().x, getCoordinate().x, null);
    }
}
