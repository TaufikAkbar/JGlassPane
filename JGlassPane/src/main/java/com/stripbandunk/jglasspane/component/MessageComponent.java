/*
 *  Copyright (c) 2011, StripBandunk and/or its affiliates. All rights reserved.
 * 
 *       http://stripbandunk.com/
 * 
 *  STRIPBANDUNK PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.stripbandunk.jglasspane.component;

import com.stripbandunk.jglasspane.event.MessageEvent;
import com.stripbandunk.jglasspane.event.MessageListener;
import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import org.jdesktop.animation.timing.interpolation.PropertySetter;

/**
 *
 * @author Eko Kurniawan Khannedy
 */
public class MessageComponent extends TimingTargetComponent implements JGlassPaneComponent {

    public static final float DEFAULT_ACCELERATION = 0.8F;

    public static final float DEFAULT_DECELERATION = 0.1F;

    public static final int DEFAULT_DURATION = 3000;

    private static final long serialVersionUID = 1L;

    private int alpha;

    private MessagePanel panel;

    private MessageLabel label;

    @SuppressWarnings("LeakingThisInConstructor")
    public MessageComponent() {
        setOpaque(false);

        panel = new MessagePanel();

        label = new MessageLabel();
        panel.add(label);

        getAnimator().addTarget(new PropertySetter(this, "alpha", 100, 0));

        setLayout(new FlowLayout(FlowLayout.CENTER));
    }

    public int getAlpha() {
        return alpha;
    }

    public void setAlpha(int alpha) {
        this.alpha = alpha;

        panel.setAlpha(this.alpha / 100.0f);
        label.setAlpha(this.alpha / 100.0f);

        repaint();
    }

    public boolean showWarning(String message) {
        return show(message, DEFAULT_DURATION, Color.ORANGE, Color.WHITE);
    }

    public boolean showWarning(String message, int duration) {
        return show(message, duration, DEFAULT_ACCELERATION, DEFAULT_DECELERATION, Color.ORANGE, Color.WHITE);
    }

    public boolean showError(String message) {
        return show(message, DEFAULT_DURATION, Color.RED, Color.WHITE);
    }

    public boolean showError(String message, int duration) {
        return show(message, duration, DEFAULT_ACCELERATION, DEFAULT_DECELERATION, Color.RED, Color.WHITE);
    }

    public boolean show(String message, Color background, Color foreground) {
        return show(message, DEFAULT_DURATION, background, foreground);
    }

    public boolean show(String message, int duration, Color background, Color foreground) {
        return show(message, duration, DEFAULT_ACCELERATION, DEFAULT_DECELERATION, background, foreground);
    }

    public boolean show(String message) {
        return show(message, DEFAULT_DURATION);
    }

    public boolean show(String message, int duration) {
        return show(message, duration, DEFAULT_ACCELERATION, DEFAULT_DECELERATION, Color.BLACK, Color.WHITE);
    }

    public boolean show(String message, int duration, float acceleration, float deceleration, Color background, Color foreground) {
        if (getAnimator().isRunning()) {
            return false;
        }

        panel.setBackground(background);
        label.setForeground(foreground);
        label.setText(message);

        getAnimator().setAcceleration(acceleration);
        getAnimator().setDeceleration(deceleration);
        getAnimator().setDuration(duration);
        getAnimator().start();

        return true;
    }

    @Override
    protected void onAnimatorBegin() {
        add(panel);
        doLayout();
        revalidate();

        fireOnMessageShow(new MessageEvent(this));
    }

    @Override
    protected void onAnimatorEnd() {
        remove(panel);
        doLayout();
        revalidate();

        fireOnMessageHide(new MessageEvent(this));
    }

    public void addMessageListener(MessageListener listener) {
        listenerList.add(MessageListener.class, listener);
    }

    public void removeMessageListener(MessageListener listener) {
        listenerList.remove(MessageListener.class, listener);
    }

    protected void fireOnMessageShow(MessageEvent event) {
        for (MessageListener listener : listenerList.getListeners(MessageListener.class)) {
            listener.onShow(event);
        }
    }

    protected void fireOnMessageHide(MessageEvent event) {
        for (MessageListener listener : listenerList.getListeners(MessageListener.class)) {
            listener.onHide(event);
        }
    }

    @Override
    public Component getComponent() {
        return this;
    }
}
