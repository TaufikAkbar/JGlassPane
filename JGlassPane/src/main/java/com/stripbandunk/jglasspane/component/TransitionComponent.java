/*
 * Copyright 2008 echo.khannedy@gmail.com
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * 
 * ["Let's ROCK with JAVA"]
 * 
 * ["echo.khannedy@gmail.com"]
 * ["http://eecchhoo.wordpress.com/"]
 */
package com.stripbandunk.jglasspane.component;

import com.stripbandunk.jglasspane.JGlassPane;
import com.stripbandunk.jglasspane.event.TransitionEvent;
import com.stripbandunk.jglasspane.event.TransitionListener;
import com.stripbandunk.jglasspane.transition.Transition;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JComponent;
import org.jdesktop.animation.timing.Animator;
import org.jdesktop.animation.timing.interpolation.PropertySetter;

/**
 * TransitionComponent merupakan Komponent yang digunakan untuk menampilkan efek transisi.
 * Komponent ini merupakan komponen yang disimpan dalam {@link JGlassPane} sehingga 
 * dengan mudah {@link JGlassPane} dapat menampilkan efek transisi
 * 
 * @author Eko Kurniawan Khannedy
 */
public class TransitionComponent extends JComponent {

    public static final String PROPERTY_TRANSITION = "PROPERTI_TRANSITION";

    private static final long serialVersionUID = 1L;

    private Animator animator;

    private int effect;

    private TransitionEvent transitionEvent;

    private Transition transition;

    public TransitionComponent() {
        super.setOpaque(false);
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

    public void startTransition(int milisecond) {
        startTransition(milisecond, 0.2F, 0.4F);
    }

    public void startTransition(int miliSecond, float acelerator, float deceleator) {
        if (getAnimator().isRunning()) {
            getAnimator().stop();
        }

        getAnimator().setDuration(miliSecond);
        getAnimator().setAcceleration(acelerator);
        getAnimator().setDeceleration(deceleator);
        getAnimator().start();

        fireTransitionListenerOnStart(getTransitionEvent());
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (getTransition() != null && getAnimator().isRunning()) {
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

    protected Animator getAnimator() {
        if (animator == null) {
            animator = new Animator(0, new PropertySetter(this, "effect", 0, 101));
        }
        return animator;
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

    public int getEffect() {
        return effect;
    }

    public void setEffect(int effect) {
        this.effect = effect;
        repaint();

        if (effect >= 100) {
            getAnimator().stop();
            fireTransitionListenerOnFinish(getTransitionEvent());
        }
    }
}
