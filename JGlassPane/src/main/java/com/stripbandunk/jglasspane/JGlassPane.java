/*
 *  Copyright (c) 2011, StripBandunk and/or its affiliates. All rights reserved.
 * 
 *       http://stripbandunk.com/
 * 
 *  STRIPBANDUNK PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.stripbandunk.jglasspane;

import java.awt.Component;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import javax.swing.JLayeredPane;

/**
 * JGlassPane merupakan komponen untuk glasspane yang berguna sebagai container
 * untuk komponen - komponen, karena merupakan subclass dari {@link JLayeredPane}
 * 
 * @author Eko Kurniawan Khannedy
 */
public class JGlassPane extends JLayeredPane {

    private static final long serialVersionUID = 1L;

    /**
     * Membuat JGlassPane baru
     */
    public JGlassPane() {
        // Panggil superclass
        super();

        // Mengubah Opaque menjadi false agar transparan
        super.setOpaque(false);

        // Menambah aksi komponen ketika struktur komponen berubah
        addComponentListener(new ResizeListener(this));
    }

    /**
     * Menambah komponen ke dalam JGlassPane
     * @param comp komponen yang akan ditambahkan
     */
    public void addGlassPaneComponent(Component comp) {
        // Menambah komponen
        add(comp);
    }

    @Override
    @Deprecated
    public Component add(Component comp) {
        Component c = super.add(comp);

        // Mengubah lokasi dan besar komponen agar sama dengan JGlassPane
        comp.setBounds(0, 0, getWidth(), getHeight());
        return c;
    }

    @Override
    @Deprecated
    public void setOpaque(boolean isOpaque) {
        super.setOpaque(false);
    }

    /**
     * Listener untuk aksi komponen resize
     * 
     * @author Eko Kurniawan Khannedy
     */
    private class ResizeListener implements ComponentListener {

        private JGlassPane glasspane;

        /**
         * Membuat COmponent Resize Listener baru
         * @param glasspane
         */
        ResizeListener(JGlassPane glasspane) {
            this.glasspane = glasspane;
        }

        @Override
        public void componentResized(ComponentEvent e) {
            for (Component comp : glasspane.getComponents()) {
                // Mengubah ukuran komponen agar sama dengan glasspane
                comp.setBounds(0, 0, glasspane.getWidth(), glasspane.getHeight());
                comp.repaint();
            }
        }

        @Override
        public void componentMoved(ComponentEvent e) {
            componentResized(e);
        }

        @Override
        public void componentShown(ComponentEvent e) {
            componentResized(e);
        }

        @Override
        public void componentHidden(ComponentEvent e) {
            componentResized(e);
        }
    }
}
