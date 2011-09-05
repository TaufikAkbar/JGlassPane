/*
 *  Copyright (c) 2011, StripBandunk and/or its affiliates. All rights reserved.
 * 
 *       http://stripbandunk.com/
 * 
 *  STRIPBANDUNK PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.stripbandunk.jglasspane.component;

import java.awt.AlphaComposite;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.io.Serializable;
import javax.swing.JPanel;

/**
 *
 * @author Eko Kurniawan Khannedy
 */
class MessagePanel extends JPanel implements Serializable {

    private static final long serialVersionUID = 1L;

    private float alpha = 1.0f;

    MessagePanel() {
        setOpaque(false);
        setLayout(new FlowLayout(FlowLayout.CENTER, 15, 15));
    }

    public float getAlpha() {
        return alpha;
    }

    public void setAlpha(float alpha) {
        this.alpha = alpha;
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D gd = (Graphics2D) g.create();
        gd.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        gd.setComposite(AlphaComposite.SrcOver.derive(alpha));
        gd.setPaint(getBackground());
        gd.fillRoundRect(0, 0, getWidth(), getHeight(), 10, 10);
        gd.dispose();
        super.paintComponent(g);
    }
}
