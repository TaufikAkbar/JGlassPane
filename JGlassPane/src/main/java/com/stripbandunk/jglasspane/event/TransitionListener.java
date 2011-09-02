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
public interface TransitionListener extends EventListener {

    /**
     * Dipanggil ketika transisi mulai berjalan
     * @param event
     */
    public void onStart(TransitionEvent event);

    /**
     * Dipanggil ketika transisi selesai berjalan
     * @param event
     */
    public void onFinish(TransitionEvent event);
}
