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
import com.stripbandunk.jglasspane.transition.NoTransition;
import com.stripbandunk.jglasspane.transition.Transition;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import org.jdesktop.animation.timing.interpolation.PropertySetter;

/**
 * 
 * @author Eko Kurniawan Khannedy
 */
public class TransitionComponent extends TimingTargetComponent implements JGlassPaneComponent {

    public static final float DEFAULT_ACCELERATION = 0.2F;

    public static final float DEFAULT_DECELERATION = 0.4F;

    public static final int DEFAULT_DURATION = 500;

    public static final String PROPERTY_TRANSITION = "transition";

    private static final long serialVersionUID = 1L;

    private int effect;

    private Transition transition;

    public TransitionComponent() {
        transition = new NoTransition();

        setOpaque(false);
        getAnimator().addTarget(new PropertySetter(this, "effect", 0, 100));
    }

    public Transition getTransition() {
        return transition;
    }

    public void setTransition(Transition transition) {

        AssertHelper.notNull(transition, "Transition is null");

        Transition oldValue = getTransition();
        this.transition = transition;
        if (oldValue != transition) {
            firePropertyChange(PROPERTY_TRANSITION, oldValue, transition);
        }
    }

    public void stop() {
        if (getAnimator().isRunning()) {
            getAnimator().stop();
            fireTransitionListenerOnFinish(new TransitionEvent(this));
        }
    }

    public boolean start() {
        return start(TransitionComponent.DEFAULT_DURATION);
    }

    public boolean start(int duration) {
        return start(duration, TransitionComponent.DEFAULT_ACCELERATION,
                TransitionComponent.DEFAULT_DECELERATION);
    }

    public boolean start(int duration, float acceleration, float deceleration) {
        if (transition == null || getAnimator().isRunning()) {
            return false;
        }

        getAnimator().setDuration(duration);
        getAnimator().setAcceleration(acceleration);
        getAnimator().setDeceleration(deceleration);
        getAnimator().start();

        return true;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (transition != null && getAnimator().isRunning()) {
            paintTransition(g);
        }
    }

    protected void paintTransition(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        try {
            transition.paint(g2, effect);
        } finally {
            g2.dispose();
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

    public int getEffect() {
        return effect;
    }

    /**
     * Don't use this method, this method used by Animator
     * @param effect
     * @deprecated 
     */
    @Deprecated
    public void setEffect(int effect) {
        this.effect = effect;
        repaint();
    }

    @Override
    protected void onAnimatorBegin() {
        transition.beforeStart(this);
        fireTransitionListenerOnStart(new TransitionEvent(this));
    }

    @Override
    protected void onAnimatorEnd() {
        transition.afterFinish();
        fireTransitionListenerOnFinish(new TransitionEvent(this));
    }

    @Override
    public Component getComponent() {
        return this;
    }
}
