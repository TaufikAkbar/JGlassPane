/*
 *  Copyright (c) 2011, StripBandunk and/or its affiliates. All rights reserved.
 * 
 *       http://stripbandunk.com/
 * 
 *  STRIPBANDUNK PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.stripbandunk.jglasspane.transition.image;

import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.geom.Area;
import java.awt.geom.Rectangle2D.Double;
import java.awt.image.BufferedImage;

/**
 *
 * @author echo
 */
public class BoxInImageTransition extends AbstractImageTransition {

    private Rectangle.Double rectangle;

    private Point center;

    public BoxInImageTransition() {
    }

    public BoxInImageTransition(Point coordinate) {
        super(coordinate);
    }

    @Override
    public void beforeStart() {
        super.beforeStart();
        center = new Point();
        rectangle = new Double();
    }

    public Point getCenter() {
        return center;
    }

    public Double getRectangle() {
        return rectangle;
    }

    public void paint(Graphics2D g2, BufferedImage image, int effect) {
        getCenter().setLocation(image.getWidth() / 2.0, image.getHeight() / 2.0);

        double stepW = image.getWidth() / 100.0;
        double stepH = image.getHeight() / 100.0;

        double width = image.getWidth() - (stepW * effect);
        double height = image.getHeight() - (stepH * effect);
        double x = getCenter().x - (width / 2.0) + getCoordinate().x;
        double y = getCenter().y - (height / 2.0) + getCoordinate().y;

        getRectangle().setRect(x, y, width, height);
        Area area2 = new Area(getRectangle());
        Area area = new Area();
        area.add(area2);

        g2.setClip(area);
        g2.drawImage(image, getCoordinate().x, getCoordinate().y, null);
    }
}
