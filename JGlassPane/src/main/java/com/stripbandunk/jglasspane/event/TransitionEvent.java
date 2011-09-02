/*
 *  Copyright (c) 2011, StripBandunk and/or its affiliates. All rights reserved.
 * 
 *       http://stripbandunk.com/
 * 
 *  STRIPBANDUNK PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.stripbandunk.jglasspane.event;

import java.util.EventObject;

/**
 * 
 * @author Eko Kurniawan Khannedy
 */
public class TransitionEvent extends EventObject {

    private static final long serialVersionUID = 1L;

    /**
     * Membuat TransitionEvent baru dengan sumber object
     * @param source objek sumber
     */
    public TransitionEvent(Object source) {
        super(source);
    }
}
