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
import com.stripbandunk.jglasspane.transition.Transition;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JComponent;
import org.jdesktop.animation.timing.Animator;
import org.jdesktop.animation.timing.interpolation.PropertySetter;

/**
 * 
 * @author Eko Kurniawan Khannedy
 */
public class TransitionComponent extends JComponent implements JGlassPaneComponent {

    public static final float DEFAULT_ACCELERATION = 0.2F;

    public static final float DEFAULT_DECELERATION = 0.4F;

    public static final int DEFAULT_DURATION = 500;

    public static final String PROPERTY_TRANSITION = "transition";

    private static final long serialVersionUID = 1L;

    private Animator animator;

    private int effect;

    private Transition transition;

    public TransitionComponent() {
        setOpaque(false);
        animator = new Animator(0, new PropertySetter(this, "effect", 0, 101));
    }

    public Transition getTransition() {
        return transition;
    }

    public void setTransition(Transition transition) {
        Transition oldValue = getTransition();
        this.transition = transition;
        if (oldValue != transition) {
            firePropertyChange(PROPERTY_TRANSITION, oldValue, transition);
        }
    }

    public void stop() {
        if (animator.isRunning()) {
            animator.stop();
            transition.afterFinish();
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
        if (transition == null || animator.isRunning()) {
            return false;
        }

        transition.beforeStart();

        animator.setDuration(duration);
        animator.setAcceleration(acceleration);
        animator.setDeceleration(deceleration);
        animator.start();

        fireTransitionListenerOnStart(new TransitionEvent(this));

        return true;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (transition != null && animator.isRunning()) {
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

    public void setEffect(int effect) {
        this.effect = effect;
        repaint();
        if (effect >= 100) {
            animator.stop();
            transition.afterFinish();
            fireTransitionListenerOnFinish(new TransitionEvent(this));
        }
    }
}
