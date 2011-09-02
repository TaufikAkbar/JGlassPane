/*
 *  Copyright (c) 2011, StripBandunk and/or its affiliates. All rights reserved.
 * 
 *       http://stripbandunk.com/
 * 
 *  STRIPBANDUNK PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.stripbandunk.jglasspane.helper;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GradientPaint;
import java.awt.LinearGradientPaint;
import java.awt.Paint;

/**
 * 
 * @author Eko Kurniawan Khannedy
 */
public class GradientHelper {

    /**
     * Untuk membuat gradient DIAGONAL_DOWN yaitu gradient dari kiri atas ke kanan bawah
     */
    public final static int DIAGONAL_DOWN = 33333;

    /**
     * Untuk membuat gradient DIAGONAL_UP yaitu gradient dari kiri bawah ke kanan atas
     */
    public final static int DIAGONAL_UP = 44444;

    /**
     * Untuk membuat gradient HORIZONTAL yaitu gradient dari kiri ke kanan
     */
    public final static int HORIZONTAL = 11111;

    /**
     * Untuk membuat gradient VERTICAL yaitu gradient dari atas ke bawah
     */
    public final static int VERTICAL = 22222;

    private static Paint paint;

    /**
     * Membuat GradientPaint dari dua warna berdasarkan sebuah komponen
     * 
     * @param c1 warna satu
     * @param c2 warna dua
     * @param dimention dimensi
     * @param type tipe gradient, misal HORIZONTAL, VERTICAL, DIAGONAL_DOWN, DIAGONAL UP
     * @return warna gradient berdasarkan parameter
     */
    public static Paint getLinear(final Color c1, final Color c2, final Dimension dimention,
            final int type) {
        paint = null;
        if (type == GradientHelper.VERTICAL) {
            paint = new GradientPaint(0, 0, c1, 0, dimention.height, c2);
        } else if (type == GradientHelper.DIAGONAL_DOWN) {
            paint = new GradientPaint(0, 0, c1, dimention.width, dimention.height, c2);
        } else if (type == GradientHelper.DIAGONAL_UP) {
            paint = new GradientPaint(0, dimention.height, c1, dimention.width, 0, c2);
        } else {
            paint = new GradientPaint(0, 0, c1, dimention.width, 0, c2);
        }
        return paint;
    }

    /**
     * Membuat GradientPaint dari beberapa warna berdasarkan sebauh komponen dan berbentuk sesuai
     * tipe yang ditentukan
     * @param colors array warna
     * @param rect dimensi
     * @param type tipe gradient, seperti HORIZONTAL, VERTICAL, DIAGONAL_DOWN, DIAGONAL_UP
     * @return warna gradient berdasarkan parameter
     */
    public static Paint getLinear(final Color[] colors, final Dimension rect, final int type) {
        paint = null;

        final float step = 1.0F / (colors.length - 1);
        final float[] fractions = new float[colors.length];

        float now = 0;
        for (int i = 0; i < colors.length; i++) {
            fractions[i] = now;
            now += step;
        }

        if (type == GradientHelper.VERTICAL) {
            paint = new LinearGradientPaint(0, 0, 0, rect.width, fractions, colors);
        } else if (type == GradientHelper.DIAGONAL_DOWN) {
            paint = new LinearGradientPaint(0, 0, rect.width, rect.height, fractions,
                    colors);
        } else if (type == GradientHelper.DIAGONAL_UP) {
            paint = new LinearGradientPaint(0, rect.height, rect.width, 0, fractions,
                    colors);
        } else {
            paint = new LinearGradientPaint(0, 0, rect.width, 0, fractions, colors);
        }
        return paint;
    }

    private GradientHelper() {
        // utility class
    }
}
