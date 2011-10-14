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
import com.stripbandunk.jglasspane.helper.AssertHelper;
import com.stripbandunk.jglasspane.transition.image.ImageTransition;
import com.stripbandunk.jglasspane.transition.image.NoImageTransition;
import com.stripbandunk.jglasspane.transition.image.creator.ImageCreator;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import org.jdesktop.animation.timing.interpolation.PropertySetter;

/**
 * 
 * @author Eko Kurniawan Khannedy
 */
public class ImageTransitionComponent extends TimingTargetComponent implements JGlassPaneComponent {

    public static final float DEFAULT_ACCELERATION = 0.2F;

    public static final float DEFAULT_DECELERATION = 0.4F;

    public static final int DEFAULT_DURATION = 500;

    private static final long serialVersionUID = 1L;

    public static final String PROPERTY_IMAGECREATOR = "imageCreator";

    public static final String PROPERTY_TRANSITION = "transition";

    private ImageTransition transition;

    private ImageCreator imageCreator;

    private int effect;

    private BufferedImage image;

    public ImageTransitionComponent() {

        transition = new NoImageTransition();

        setOpaque(false);
        getAnimator().addTarget(new PropertySetter(this, "effect", 0, 100));
    }

    public void stop() {
        if (getAnimator().isRunning()) {
            getAnimator().stop();
        }
    }

    public boolean start() {
        return start(DEFAULT_DURATION);
    }

    public boolean start(int duration) {
        return start(duration, DEFAULT_ACCELERATION, DEFAULT_DECELERATION);
    }

    public boolean start(int duration, float acceleration, float deceleration) {
        if (imageCreator == null || getAnimator().isRunning()) {
            return false;
        }

        image = imageCreator.create();

        getAnimator().setDuration(duration);
        getAnimator().setAcceleration(acceleration);
        getAnimator().setDeceleration(deceleration);
        getAnimator().start();

        return true;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (imageCreator != null && getAnimator().isRunning()) {
            paintTransition(g);
        }
    }

    protected void paintTransition(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        try {
            transition.paint(g2, image, effect);
        } finally {
            g2.dispose();
        }
    }

    public int getEffect() {
        return effect;
    }

    public void setEffect(int effect) {
        this.effect = effect;
        repaint();
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

    public ImageTransition getTransition() {
        return transition;
    }

    public void setTransition(ImageTransition transition) {
        
        AssertHelper.notNull(transition, "ImageTransition is null");
        
        ImageTransition oldTransition = this.transition;
        this.transition = transition;
        firePropertyChange(PROPERTY_TRANSITION, oldTransition, transition);
    }

    public ImageCreator getImageCreator() {
        return imageCreator;
    }

    public void setImageCreator(ImageCreator imageCreator) {
        ImageCreator oldImageCreator = this.imageCreator;
        this.imageCreator = imageCreator;
        firePropertyChange(PROPERTY_IMAGECREATOR, oldImageCreator, imageCreator);
    }

    @Override
    protected void onAnimatorBegin() {
        transition.beforeStart();
        fireTransitionListenerOnStart(new TransitionEvent(this));
    }

    @Override
    protected void onAnimatorEnd() {
        transition.afterFinish();
        fireTransitionListenerOnFinish(new TransitionEvent(this));
        // clear image
        image = null;
    }

    @Override
    public Component getComponent() {
        return this;
    }
}
