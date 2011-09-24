/*
 *  Copyright (c) 2011, StripBandunk and/or its affiliates. All rights reserved.
 * 
 *       http://stripbandunk.com/
 * 
 *  STRIPBANDUNK PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.stripbandunk.jglasspane.transition.image.creator;

import com.stripbandunk.jglasspane.error.GlassPaneException;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import javax.imageio.ImageIO;

/**
 *
 * @author Eko Kurniawan Khannedy
 */
public class UrlImageCreator implements ImageCreator {

    private URL url;

    public UrlImageCreator(URL url) {
        this.url = url;
    }

    @Override
    public BufferedImage create() {
        try {
            return ImageIO.read(url);
        } catch (IOException ex) {
            throw new GlassPaneException(ex);
        }
    }
}
