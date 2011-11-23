/*
 *  Copyright (c) 2011, StripBandunk and/or its affiliates. All rights reserved.
 * 
 *       http://stripbandunk.com/
 * 
 *  STRIPBANDUNK PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.stripbandunk.jglasspane.event;

import com.stripbandunk.jglasspane.component.DialogComponent;
import java.util.EventObject;

/**
 *
 * @author Eko Kurniawan Khannedy
 */
public class DialogEvent extends EventObject {

    private static final long serialVersionUID = 1L;

    public DialogEvent(DialogComponent source) {
        super(source);
    }

    public DialogComponent getComponent() {
        return (DialogComponent) getSource();
    }
}
