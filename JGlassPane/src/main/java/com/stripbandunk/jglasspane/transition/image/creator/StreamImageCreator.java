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
import java.io.InputStream;
import javax.imageio.ImageIO;

/**
 *
 * @author Eko Kurniawan Khannedy
 */
public class StreamImageCreator implements ImageCreator {

    private InputStream inputStream;

    public StreamImageCreator(InputStream inputStream) {
        this.inputStream = inputStream;
    }

    public BufferedImage create() {
        try {
            return ImageIO.read(inputStream);
        } catch (IOException ex) {
            throw new GlassPaneException(ex);
        }
    }
}
