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
 * @author echo
 */
public interface MessageListener extends EventListener {

    void onShow(MessageEvent event);

    void onHide(MessageEvent event);
}
