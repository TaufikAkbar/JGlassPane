/*
 *  Copyright (c) 2011, StripBandunk and/or its affiliates. All rights reserved.
 * 
 *       http://stripbandunk.com/
 * 
 *  STRIPBANDUNK PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.stripbandunk.jglasspane.helper;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Component;
import java.awt.GradientPaint;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import javax.swing.SwingUtilities;

/**
 * 
 * @author Eko Kurniawan Khannedy
 */
public final class GraphicHelper {

    /**
     * mendapatkan rectangle gambar dengan x dan y sesuai posisi image;
     * @param image gambar sumber
     * @param locationTarget target lokasi
     * @return Rectangle
     */
    public static Rectangle getRectangleImage(BufferedImage image, Point locationTarget) {
        Rectangle rectangle = new Rectangle();

        // mengubah nilai rectangle
        rectangle.setLocation(locationTarget);
        rectangle.setSize(image.getWidth(), image.getHeight());

        return rectangle;
    }

    /**
     * Mendapatkan lokasi point dari komponen source terhadap komponen destination
     * @param source sumber komponen
     * @param destination target komponen
     * @return Point lokasi sumber di destination
     */
    public static Point getLocation(Component source, Component destination) {
        Point point = new Point();

        // mendapatkan lokasi source dari layar
        point.setLocation(source.getLocationOnScreen());

        // mengubah lokasi dari layar ke destination
        SwingUtilities.convertPointFromScreen(point, destination);

        return point;
    }

    /**
     * Membuat gambar efek glass dari sebuah komponen tanpa menggambar komponen
     * @param source komponen sumber gambar
     * @return gambar target
     */
    public static BufferedImage getImageReflection(Component source) {
        // membuat gambar baru
        BufferedImage image = new BufferedImage(source.getWidth(), source.getHeight() * 2, BufferedImage.TYPE_INT_ARGB);

        // menggambar gambar reflection
        getImageReflection(source, image);

        // mengembalikan gambar
        return image;
    }

    /**
     * Membuat gambar efek glass dari sebuah komponen tanpa menggambar komponen
     * @param source komponen sumber gambar
     * @param destination target gambar
     */
    public static void getImageReflection(Component source, BufferedImage destination) {
        // menggambar
        getImageReflection(source, destination, false);
    }

    /**
     * Membuat gambar efek glass dari sebuah komponen
     * @param source komponen sumber gambar
     * @param destination target tambar
     * @param withComponent jika true, maka komponen ikut digambar. 
     *      dan jika false komponen tidak ikut digambar
     */
    public static void getImageReflection(Component source, BufferedImage destination,
            boolean withComponent) {
        // Mendapatkan Graphics 2D
        Graphics2D g2 = destination.createGraphics();
        // mendapatkan gambar komponen
        BufferedImage tempImage = new BufferedImage(source.getWidth(), source.getHeight(), BufferedImage.TYPE_INT_ARGB);
        getBufferedImage(source, tempImage);

        // pakah komponen harug digambar
        if (withComponent) {
            // menggambar komponen
            g2.drawImage(tempImage, 0, 0, null);
        }

        // menggambar efect glass
        g2.scale(1, -1);
        g2.drawImage(tempImage, 0, -source.getHeight() * 2, null);
        g2.scale(1, -1);
        g2.translate(0, source.getHeight());
        g2.setPaint(new GradientPaint(0, 0, new Color(1f, 1f, 1f, 0.3f), 0, source.getHeight(),
                new Color(1f, 1f, 1f, 0f)));
        g2.setComposite(AlphaComposite.DstIn);
        g2.fillRect(0, 0, source.getWidth(), source.getHeight());

        // menghilangkan Graphics2D
        g2.dispose();
    }

    /**
     * Mendapatkan Rectangle dari sebuah komponen ? untuk komponen ?
     * @param source komponen sumber
     * @param destination komponen target
     * @return Rectangle
     */
    public static Rectangle getClip(Component source, Component destination) {
        // Mendapatkan lokasi komponen source pada layar
        Point temp = source.getLocationOnScreen();
        // Mengubah lokasi dari layar ke komponen destination
        SwingUtilities.convertPointFromScreen(temp, destination);

        Rectangle rectangle = new Rectangle();

        // Mengubah lokasi dan ukuran rectangle
        rectangle.setLocation(temp);
        rectangle.setSize(source.getSize());

        // mengembalikan nilai rectangle
        return rectangle;
    }

    /**
     * Mendapatkan BufferedImage dari sebuah komponen
     * @param comp komponen sumber
     * @param image gambar target
     */
    public static void getBufferedImage(Component comp, BufferedImage image) {
        // Mendapatkan Graphics2D dari image
        Graphics2D g2 = image.createGraphics();

        // menggambar seluruh komponen 
        comp.paint(g2);

        // menghilangkan Graphics2D
        g2.dispose();
    }

    /**
     * Mendapatkan gambar dari sebuah komponen
     * @param comp komponen sumber
     * @return gambar BufferedImage
     */
    public static BufferedImage getBufferedImage(Component comp) {
        // Membuat gambar BufferedImage
        BufferedImage image = new BufferedImage(comp.getWidth(), comp.getHeight(), BufferedImage.TYPE_INT_ARGB);

        // menggambar dengan memanggil metode getBufferedImageFromComponent();
        getBufferedImage(comp, image);

        // menggembalikan gambar
        return image;
    }

    private GraphicHelper() {
        // Unitility class without constructor
    }
}
