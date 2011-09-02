/*
 *  Copyright (c) 2011, StripBandunk and/or its affiliates. All rights reserved.
 * 
 *       http://stripbandunk.com/
 * 
 *  STRIPBANDUNK PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.stripbandunk.jglasspane.error;

/**
 *
 * @author Eko Kurniawan Khannedy
 */
public class GlassPaneException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public GlassPaneException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public GlassPaneException(Throwable cause) {
        super(cause);
    }

    public GlassPaneException(String message, Throwable cause) {
        super(message, cause);
    }

    public GlassPaneException(String message) {
        super(message);
    }

    public GlassPaneException() {
    }
}
