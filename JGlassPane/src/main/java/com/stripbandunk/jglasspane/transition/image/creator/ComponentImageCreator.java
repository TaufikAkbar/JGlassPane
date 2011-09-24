/*
 *  Copyright (c) 2011, StripBandunk and/or its affiliates. All rights reserved.
 * 
 *       http://stripbandunk.com/
 * 
 *  STRIPBANDUNK PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.stripbandunk.jglasspane.transition.image.creator;

import com.stripbandunk.jglasspane.helper.GraphicHelper;
import java.awt.Component;
import java.awt.image.BufferedImage;

/**
 *
 * @author Eko Kurniawan Khannedy
 */
public class ComponentImageCreator implements ImageCreator {

    private Component component;

    public ComponentImageCreator(Component component) {
        this.component = component;
    }

    @Override
    public BufferedImage create() {
        return GraphicHelper.getBufferedImage(component);
    }
}
