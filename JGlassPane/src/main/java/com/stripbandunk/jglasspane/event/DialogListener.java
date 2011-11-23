/*
 *  Copyright (c) 2011, StripBandunk and/or its affiliates. All rights reserved.
 * 
 *       http://stripbandunk.com/
 * 
 *  STRIPBANDUNK PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.stripbandunk.jglasspane.event;

import java.util.EventListener;

/**
 *
 * @author Eko Kurniawan Khannedy
 */
public interface DialogListener extends EventListener {

    /**
     * fire when dialog component show
     * @param event
     */
    void onShow(DialogEvent event);

    /**
     * fire when dialog component hide
     * @param event
     */
    void onHide(DialogEvent event);
}
