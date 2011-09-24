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
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 *
 * @author Eko Kurniawan Khannedy
 */
public class FileImageCreator implements ImageCreator {

    private File file;

    public FileImageCreator(File file) {
        this.file = file;
    }

    @Override
    public BufferedImage create() {
        try {
            return ImageIO.read(file);
        } catch (IOException ex) {
            throw new GlassPaneException(ex);
        }
    }
}
