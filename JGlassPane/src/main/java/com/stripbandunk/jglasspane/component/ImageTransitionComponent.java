/*
 *  Copyright (c) 2011, StripBandunk and/or its affiliates. All rights reserved.
 * 
 *       http://stripbandunk.com/
 * 
 *  STRIPBANDUNK PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.stripbandunk.jglasspane.component;

import com.stripbandunk.jglasspane.event.TransitionEvent;
import com.stripbandunk.jglasspane.event.TransitionListener;
import com.stripbandunk.jglasspane.helper.GraphicHelper;
import com.stripbandunk.jglasspane.type.ImageTransition;
import java.awt.AlphaComposite;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.Area;
import java.awt.geom.Rectangle2D.Double;
import java.awt.image.BufferedImage;
import javax.swing.JComponent;
import org.jdesktop.animation.timing.Animator;
import org.jdesktop.animation.timing.interpolation.PropertySetter;

/**
 * 
 * @author Eko Kurniawan Khannedy
 */
public class ImageTransitionComponent extends JComponent {

    private static final long serialVersionUID = 1L;

    private ImageTransition transition;

    private Point coordinate;

    private BufferedImage image;

    private Animator animator;

    private int effect, totalBlind;

    private AlphaComposite alphaComposite;

    private Double rectangle;

    private Point centerPoint;

    private TransitionEvent transitionEvent;

    public ImageTransitionComponent() {
        super();
        setOpaque(false);

        animator = new Animator(0, new PropertySetter(this, "effect", 0, 101));
    }

    public int getTotalBlind() {
        return totalBlind;
    }

    public Point getCoordinate() {
        return coordinate;
    }

    public void startTransition(int milisecond) {
        startTransition(milisecond, 0.2F, 0.4F);
    }

    public void startTransition(int miliSecond, float acelerator, float deceleator) {
        if (animator.isRunning()) {
            return;
        }

        animator.setDuration(miliSecond);
        animator.setAcceleration(acelerator);
        animator.setDeceleration(deceleator);
        animator.start();

        fireTransitionListenerOnStart(getTransitionEvent());
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (image != null && transition != null && coordinate != null
                && animator.isRunning()) {
            paintTransition(g);
        }
    }

    /**
     * Melakukan penggambaran efek transisi
     * @param g
     */
    protected void paintTransition(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();

        try {
            if (transition == ImageTransition.BOX_OUT) {
                paintTransitionBoxOut(g2);
            } else if (transition == ImageTransition.FADE) {
                paintTransitionFade(g2);
            } else if (transition == ImageTransition.SPLIT_HORIZONTAL_IN) {
                paintTransitionSplitHorizontalIn(g2);
            } else if (transition == ImageTransition.SPLIT_HORIZONTAL_OUT) {
                paintTransitionSplitHorizontalOut(g2);
            } else if (transition == ImageTransition.SPLIT_VERTICAL_IN) {
                paintTransitionSplitVerticalIn(g2);
            } else if (transition == ImageTransition.SPLIT_VERTICAL_OUT) {
                paintTransitionSplitVerticalOut(g2);
            } else if (transition == ImageTransition.WIPE_DOWN) {
                paintTransitionWipeDown(g2);
            } else if (transition == ImageTransition.WIPE_LEFT) {
                paintTransitionWipeLeft(g2);
            } else if (transition == ImageTransition.WIPE_RIGHT) {
                paintTransitionWipeRight(g2);
            } else if (transition == ImageTransition.WIPE_UP) {
                paintTransitionWipeUp(g2);
            } else if (transition == ImageTransition.COVER_DOWN) {
                paintTransitionCoverDown(g2);
            } else if (transition == ImageTransition.COVER_LEFT) {
                paintTransitionCoverLeft(g2);
            } else if (transition == ImageTransition.COVER_RIGHT) {
                paintTransitionCoverRight(g2);
            } else if (transition == ImageTransition.COVER_UP) {
                paintTransitionCoverUp(g2);
            } else if (transition == ImageTransition.MINIMIZE_HORIZONTAL) {
                paintTransitionMinimizeHorizontal(g2);
            } else if (transition == ImageTransition.MINIMIZE_VERTICAL) {
                paintTransitionMinimizeVertical(g2);
            } else if (transition == ImageTransition.FADE_AND_ROTATE) {
                paintTransitionFadeAndRotate(g2);
            }
        } finally {
            g2.dispose();
        }
    }

    protected AlphaComposite getAlphaComposite() {
        return alphaComposite;
    }

    protected Point getCenter() {
        return centerPoint;
    }

    protected Double getRectangle() {
        return rectangle;
    }

    public int getEffect() {
        return effect;
    }

    public void setEffect(int effect) {
        this.effect = effect;
        repaint();

        if (effect >= 100) {
            // menjalankan aksi onFinish()
            fireTransitionListenerOnFinish(getTransitionEvent());
        }
    }

    public void addTransitionListener(TransitionListener listener) {
        listenerList.add(TransitionListener.class, listener);
    }

    public void removeTransitionListener(TransitionListener listener) {
        listenerList.remove(TransitionListener.class, listener);
    }

    protected void fireTransitionListenerOnStart(TransitionEvent event) {
        for (TransitionListener listenr : listenerList.getListeners(TransitionListener.class)) {
            listenr.onStart(event);
        }
    }

    protected void fireTransitionListenerOnFinish(TransitionEvent event) {
        for (TransitionListener listenr : listenerList.getListeners(TransitionListener.class)) {
            listenr.onFinish(event);
        }
    }

    private TransitionEvent getTransitionEvent() {
        if (transitionEvent == null) {
            transitionEvent = new TransitionEvent(this);
        }
        return transitionEvent;
    }

    /**
     * Mengambar efek Box Out
     * @param g2
     */
    protected void paintTransitionBoxOut(Graphics2D g2) {
        getCenter().setLocation(image.getWidth() / 2.0, image.getHeight() / 2.0);

        double stepW = image.getWidth() / 100.0;
        double stepH = image.getHeight() / 100.0;

        double width = (stepW * effect);
        double height = (stepH * effect);
        double x = getCenter().x - (width / 2.0) + getCoordinate().x;
        double y = getCenter().y - (height / 2.0) + getCoordinate().y;

        getRectangle().setRect(x, y, width, height);

        Area area = new Area(GraphicHelper.getRectangleImage(image, getCoordinate()));
        Area area2 = new Area(getRectangle());
        area.exclusiveOr(area2);

        g2.setClip(area);
        g2.drawImage(image, getCoordinate().x, getCoordinate().y, null);
    }

    /**
     * Menggambar efek Fade
     * @param g2
     */
    protected void paintTransitionFade(Graphics2D g2) {
        alphaComposite = getAlphaComposite().derive(1.0F - (this.effect / 100.0F));

        g2.setClip(GraphicHelper.getRectangleImage(image, getCoordinate()));
        g2.setComposite(getAlphaComposite());
        g2.drawImage(image, getCoordinate().x, getCoordinate().y, null);
    }

    /**
     * Menggambar efek Split Horizontal In
     * @param g2
     */
    protected void paintTransitionSplitHorizontalIn(Graphics2D g2) {
        double step = image.getHeight() / 100.0;

        double center = getCoordinate().y + image.getHeight() / 2.0;

        double height = image.getHeight() - (step * this.effect);
        double y = center - (height / 2.0);

        getRectangle().setRect(getCoordinate().x, y,
                image.getWidth(), height);

        g2.setClip(getRectangle());
        g2.drawImage(image, getCoordinate().x, getCoordinate().x, null);
    }

    /**
     * Menggambar efek SPlit Horizontal Out
     * @param g2
     */
    protected void paintTransitionSplitHorizontalOut(Graphics2D g2) {
        double step = image.getHeight() / 100.0;

        double height = (step * this.effect);
        double center = getCoordinate().y + image.getHeight() / 2.0;
        double y = center - (height / 2.0);

        getRectangle().setRect(getCoordinate().x, y, image.getWidth(), height);

        Area area = new Area(GraphicHelper.getRectangleImage(image, getCoordinate()));
        Area area2 = new Area(getRectangle());
        area.exclusiveOr(area2);

        g2.setClip(area);
        g2.drawImage(image, getCoordinate().x, getCoordinate().x, null);
    }

    /**
     * Mengambar efek Split Vertical In
     * @param g2
     */
    protected void paintTransitionSplitVerticalIn(Graphics2D g2) {
        double step = image.getWidth() / 100.0;

        double width = image.getWidth() - (step * this.effect);
        double center = getCoordinate().x + image.getWidth() / 2.0;
        double x = center - (width / 2.0);

        getRectangle().setRect(x, getCoordinate().y, width, image.getHeight());

        g2.setClip(getRectangle());
        g2.drawImage(image, getCoordinate().x, getCoordinate().x, null);
    }

    /**
     * Menggambar efek Split Vertical Out
     * @param g2
     */
    protected void paintTransitionSplitVerticalOut(Graphics2D g2) {
        double step = image.getWidth() / 100.0;

        double width = (step * this.effect);
        double center = getCoordinate().x + image.getWidth() / 2.0;
        double x = center - (width / 2.0);

        getRectangle().setRect(x, getCoordinate().y, width, image.getHeight());

        Area area = new Area(GraphicHelper.getRectangleImage(image, getCoordinate()));
        Area area2 = new Area(getRectangle());
        area.exclusiveOr(area2);

        g2.setClip(area);
        g2.drawImage(image, getCoordinate().x, getCoordinate().x, null);
    }

    /**
     * menggambar efek Wipe Down
     * @param g2
     */
    protected void paintTransitionWipeDown(Graphics2D g2) {
        double step = image.getHeight() / 100.0;

        double y = (getCoordinate().y + (step * this.effect));
        double height = (image.getHeight() - (step * this.effect));

        getRectangle().setRect(getCoordinate().x, y,
                image.getWidth(), height);

        g2.setClip(getRectangle());
        g2.drawImage(image, getCoordinate().x, getCoordinate().y, null);
    }

    /**
     * menggambar efek Wipe Left
     * @param g2
     */
    protected void paintTransitionWipeLeft(Graphics2D g2) {
        double step = image.getWidth() / 100.0;

        double width = image.getWidth() - (step * this.effect);

        getRectangle().setRect(getCoordinate().x, getCoordinate().y, width,
                image.getHeight());

        g2.setClip(getRectangle());
        g2.drawImage(image, getCoordinate().x, getCoordinate().y, null);
    }

    /**
     * menggambar efek Wipe Right
     * @param g2
     */
    protected void paintTransitionWipeRight(Graphics2D g2) {
        double step = image.getWidth() / 100.0;

        double x = getCoordinate().x + (step * this.effect);
        double width = image.getWidth() - (step * this.effect);

        getRectangle().setRect(x, getCoordinate().y, width, image.getHeight());

        g2.setClip(getRectangle());
        g2.drawImage(image, getCoordinate().x, getCoordinate().y, null);
    }

    /**
     * menggambar efek Wipe Up
     * @param g2
     */
    protected void paintTransitionWipeUp(Graphics2D g2) {
        double step = image.getHeight() / 100.0;

        double height = image.getHeight() - (step * this.effect);

        getRectangle().setRect(getCoordinate().x, getCoordinate().y,
                image.getWidth(), height);

        g2.setClip(getRectangle());
        g2.drawImage(image, getCoordinate().x, getCoordinate().y, null);
    }

    /**
     * menggambar efek Cover Down
     * @param g2
     */
    protected void paintTransitionCoverDown(Graphics2D g2) {
        double step = image.getHeight() / 100.0;

        double y = getCoordinate().y + step * effect;

        getRectangle().setRect(getCoordinate().x, getCoordinate().y,
                image.getWidth(), image.getHeight());

        g2.setClip(getRectangle());
        g2.drawImage(image, getCoordinate().x, (int) y, null);
    }

    /**
     * menggambar efek Cover Left
     * @param g2
     */
    protected void paintTransitionCoverLeft(Graphics2D g2) {
        double step = image.getWidth() / 100.0;

        double x = getCoordinate().x - step * effect;

        getRectangle().setRect(getCoordinate().x, getCoordinate().y,
                image.getWidth(), image.getHeight());

        g2.setClip(getRectangle());
        g2.drawImage(image, (int) x, getCoordinate().y, null);
    }

    /**
     * menggambar efek Cover Right
     * @param g2
     */
    protected void paintTransitionCoverRight(Graphics2D g2) {
        double step = image.getWidth() / 100.0;

        double x = getCoordinate().x + step * effect;

        getRectangle().setRect(getCoordinate().x, getCoordinate().y,
                image.getWidth(), image.getHeight());

        g2.setClip(getRectangle());
        g2.drawImage(image, (int) x, getCoordinate().y, null);
    }

    /**
     * menggambar efek Cover Up
     * @param g2
     */
    protected void paintTransitionCoverUp(Graphics2D g2) {
        double step = image.getHeight() / 100.0;

        double y = getCoordinate().y - step * effect;

        getRectangle().setRect(getCoordinate().x, getCoordinate().y,
                image.getWidth(), image.getHeight());

        g2.setClip(getRectangle());
        g2.drawImage(image, getCoordinate().x, (int) y, null);
    }

    /**
     * menggambar efek Minimize Horizontal
     * @param g2
     */
    protected void paintTransitionMinimizeHorizontal(Graphics2D g2) {
        double step = image.getHeight() / 100.0;

        double height = image.getHeight() - step * effect;
        double y = getCoordinate().y + (image.getHeight() - height) / 2.0;

        getRectangle().setRect(getCoordinate().x, getCoordinate().y,
                image.getWidth(), image.getHeight());
        g2.setClip(getRectangle());

        g2.drawImage(image, getCoordinate().x, (int) y,
                image.getWidth(), (int) height, null);
    }

    /**
     * menggambar efek fade and rotate
     * @param g2
     */
    protected void paintTransitionFadeAndRotate(Graphics2D g2) {
        alphaComposite = getAlphaComposite().derive(1.0F - (this.effect / 100.0F));
        double step = 360 / 100.0;

        g2.setClip(GraphicHelper.getRectangleImage(image, getCoordinate()));
        g2.rotate(Math.toRadians(step * effect), getCoordinate().x + image.getWidth() / 2,
                getCoordinate().y + image.getHeight() / 2);
        g2.setComposite(getAlphaComposite());
        g2.drawImage(image, getCoordinate().x, getCoordinate().y, null);
    }

    /**
     * menggambar efek Minimize Vertical
     * @param g2
     */
    protected void paintTransitionMinimizeVertical(Graphics2D g2) {
        double step = image.getWidth() / 100.0;

        double width = image.getWidth() - step * effect;
        double x = getCoordinate().y + (image.getWidth() - width) / 2.0;

        getRectangle().setRect(getCoordinate().x, getCoordinate().y,
                image.getWidth(), image.getHeight());
        g2.setClip(getRectangle());

        g2.drawImage(image, (int) x, getCoordinate().y, (int) width, image.getHeight(), null);
    }
}
