/*
 *  Copyright (c) 2011, StripBandunk and/or its affiliates. All rights reserved.
 * 
 *       http://stripbandunk.com/
 * 
 *  STRIPBANDUNK PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.stripbandunk.jglasspane.helper;

import com.stripbandunk.jglasspane.error.GlassPaneException;

/**
 *
 * @author Eko Kurniawan Khannedy
 */
@SuppressWarnings("FinalClass")
public final class AssertHelper {

    private AssertHelper() {
        // utilities class
    }

    public static void notNull(Object object) {
        notNull(object, "Object is null");
    }

    public static void notNull(Object object, String message) {
        if (object == null) {
            throw new GlassPaneException(message);
        }
    }
}
