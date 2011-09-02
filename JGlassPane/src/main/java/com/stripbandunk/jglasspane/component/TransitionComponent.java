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

import com.stripbandunk.jglasspane.event.TransitionEvent;
import com.stripbandunk.jglasspane.event.TransitionListener;
import com.stripbandunk.jglasspane.type.Transition;
import java.awt.geom.Rectangle2D.Double;
import java.awt.AlphaComposite;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Paint;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.geom.Area;
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

    private static final long serialVersionUID = -4333919084195748765L;

    /**
     * Properti untuk rectangleTransition
     */
    public static final String PROPERTY_RECTANGLE_TRANSITION = "PROPERTY_RECTANGLE_TRANSITION";

    /**
     * Properti untu colorRectangle
     */
    public static final String PROPERTY_COLOR_TRANSITION = "PROPERTY_COLOR_TRANSITION";

    /**
     * Properti untuk transition
     */
    public static final String PROPERTY_TRANSITION = "PROPERTI_TRANSITION";

    /**
     * Properti untuk blindsStep
     */
    public static final String PROPERTY_BLINDS_STEP = "PROPERTY_BLINDS_STEP";

    private Rectangle rectangleTransition;

    private Paint colorTransition;

    private Transition transition;

    private Animator animator;

    private int effect, i, blindsStep;

    private AlphaComposite alphaComposite;

    private double step, center, x, y, width, height, stepW, stepH, temp;

    private Double rectangleDouble;

    private Area area, area2;

    private Point centerPoint;

    private TransitionEvent transitionEvent;

    /**
     * Membuat TransitionComponent baru
     */
    public TransitionComponent() {
        // Mengubah opaque menjadi false agar tranparan
        super.setOpaque(false);
    }

    /**
     * Mendapatkan warna transisi
     * @return warna transisi
     */
    public Paint getColorTransition() {
        return colorTransition;
    }

    /**
     * mengubah warna transisi
     * @param colorTransition warna transisi baru
     */
    public void setColorTransition(Paint colorTransition) {
        // Mendapatkan warna yang lama
        Paint oldColor = getColorTransition();

        // mengubah warna menjadi yang baru
        this.colorTransition = colorTransition;

        // apakah warna lama tidak sama dengan warna baru
        if (oldColor != colorTransition) {
            // memberi tahu propertiChangeListener bahwa warna telah berubah
            firePropertyChange(PROPERTY_COLOR_TRANSITION, oldColor, colorTransition);
        }
    }

    /**
     * mendapatkan Rectangle transisi
     * @return Rectangle transisi
     */
    public Rectangle getRectangleTransition() {
        return rectangleTransition;
    }

    /**
     * Mengubah Retangle transisi
     * @param rectangleTransition Rectangle transisi baru
     */
    public void setRectangleTransition(Rectangle rectangleTransition) {
        // Mendapatkan Rectangle yang lama
        Rectangle oldValue = getRectangleTransition();

        // Mengubah Rectangle menjadi yang baru
        this.rectangleTransition = rectangleTransition;

        // apakah rectangle lama tidak sama dengan rectangle baru
        if (oldValue != rectangleTransition) {
            // Memberi tahu pada propertyChangeListener bahwa rectangle berubah
            firePropertyChange(PROPERTY_RECTANGLE_TRANSITION, oldValue, rectangleTransition);
        }
    }

    /**
     * Mendapatkan {@link Transition} milik TransitionComponent
     * @return Transition
     */
    public Transition getTransition() {
        return transition;
    }

    /**
     * Mengubah {@link Transition} saat ini
     * @param transition Transition baru
     */
    public void setTransition(Transition transition) {
        // Mendapatkan transisi lama
        Transition oldValue = getTransition();

        // Mengubah transisi menjadi yang baru
        this.transition = transition;

        // apakah transisi lama tidak sama dengan transisi baru
        if (oldValue != transition) {
            // Memberi tahu pada propertyChangeListener bahwa transisi berubah
            firePropertyChange(PROPERTY_TRANSITION, oldValue, transition);
        }
    }

    /**
     * Mendapatkan nilai banyaknya step untuk transisi Blinds
     * @return int banyak step
     */
    public int getBlindsStep() {
        if (blindsStep <= 1) {
            blindsStep = 5;
        }
        return blindsStep;
    }

    /**
     * Mengubah nilai banyaknya step untuk transisi Blinds, jika nilai baru 
     * kurang dari atau sama dengan 1, maka nilai step akan dirubah menjadi 5
     * @param blindsStep banyak blinds step
     */
    public void setBlindsStep(int blindsStep) {
        // jika blinds step kurang dari sama dengan 1
        if (blindsStep <= 1) {
            // dirubah jadi 5
            blindsStep = 5;
        }

        // mendapatkan nilai lama
        int oldValue = getBlindsStep();

        // mengubah menjadi yang baru
        this.blindsStep = blindsStep;

        // apakah blinds lama tidak sama dengan blinds baru
        if (oldValue != blindsStep) {
            // memberitahu propertyChangeListener bahwa blindsStep telah berubah
            firePropertyChange(PROPERTY_BLINDS_STEP, oldValue, blindsStep);
        }
    }

    /**
     * Menyuruh TransitionComponent untuk memulai efek transisi dengan akselerator 
     * sebesar 0.2F dan dekselerator sebesar 0.4F
     * @param milisecond lama waktu transisi
     */
    public void startTransition(int milisecond) {
        startTransition(milisecond, 0.2F, 0.4F);
    }

    /**
     * Menyuruh TransitionComponent untuk memulai efek transisi
     * @param miliSecond lama waktu transisi
     * @param acelerator nilai akselerator
     * @param deceleator nilai dekselerator
     */
    public void startTransition(int miliSecond, float acelerator, float deceleator) {
        // Mengecek apakah animator sedang berjalan
        if (getAnimator().isRunning()) {
            // Jika animator sedang berjalan maka animator akan diberhentikan
            getAnimator().stop();
        }

        // mengubah durasi animator
        getAnimator().setDuration(miliSecond);
        // mengubah akselerator animator
        getAnimator().setAcceleration(acelerator);
        // mengubah dekselerator animator
        getAnimator().setDeceleration(deceleator);
        // menjalankan animator
        getAnimator().start();

        // memberitahukan TransitionListener
        fireTransitionListenerOnStart(getTransitionEvent());
    }

    @Override
    protected void paintComponent(Graphics g) {
        // Memanggil paintComponent() milik superclass
        super.paintComponent(g);

        // Mengecek apakah colorTransition bernilai null dan 
        // rectangleTransition bernilai null dan Transition bernilai null
        if (getColorTransition() != null && getRectangleTransition() != null
                && getTransition() != null && getAnimator().isRunning()) {
            // jika tidak null maka pewarnaan afek akan dijalankan
            paintTransition(g);
        }
    }

    /**
     * Melakukan penggmbaran efek transisi
     * @param g Graphics yang digunakan
     */
    protected void paintTransition(Graphics g) {

        // Mengkonrsikan Graphics ke Graphics2D
        Graphics2D g2 = (Graphics2D) g.create();

        try {
            // Mengecek jenis transisi lalu menggambarnya
            if (getTransition() == Transition.BLINDS_HORIZONTAL) {
                paintTransitionBlindsHorizontal(g2);
            } else if (getTransition() == Transition.BLINDS_VERTICAL) {
                paintTransitionBlindsVertical(g2);
            } else if (getTransition() == Transition.BOX_IN) {
                paintTransitionBoxIn(g2);
            } else if (getTransition() == Transition.BOX_OUT) {
                paintTransitionBoxOut(g2);
            } else if (getTransition() == Transition.FADE) {
                paintTransitionFade(g2);
            } else if (getTransition() == Transition.SPLIT_HORIZONTAL_IN) {
                paintTransitionSplitHorizontalIn(g2);
            } else if (getTransition() == Transition.SPLIT_HORIZONTAL_OUT) {
                paintTransitionSplitHorizontalOut(g2);
            } else if (getTransition() == Transition.SPLIT_VERTICAL_IN) {
                paintTransitionSplitVerticalIn(g2);
            } else if (getTransition() == Transition.SPLIT_VERTICAL_OUT) {
                paintTransitionSplitVerticalOut(g2);
            } else if (getTransition() == Transition.WIPE_DOWN) {
                paintTransitionWipeDown(g2);
            } else if (getTransition() == Transition.WIPE_LEFT) {
                paintTransitionWipeLeft(g2);
            } else if (getTransition() == Transition.WIPE_RIGHT) {
                paintTransitionWipeRight(g2);
            } else if (getTransition() == Transition.WIPE_UP) {
                paintTransitionWipeUp(g2);
            }
        } finally {
            // Menghilangkan Graphics2D
            g2.dispose();
        }
    }

    /**
     * Melakukan pewarnaan untuk efek Fade
     * @param g2 
     */
    protected void paintTransitionFade(Graphics2D g2) {

        // Mengubah Clip
        g2.setClip(getRectangleTransition());
        // Mengubah warna Graphics
        g2.setPaint(getColorTransition());

        // Mengubah alpha composite
        alphaComposite = getAlphaComposite().derive(1F - (this.effect / 100F));

        // Mengubah alpha composite milik Graphics
        g2.setComposite(getAlphaComposite());
        // Menggambar 
        g2.fill(getRectangleTransition());
    }

    /**
     * Melakukan pewarnaan efek Wipe Down
     * @param g2
     */
    protected void paintTransitionWipeDown(Graphics2D g2) {
        // mandapatkan nilai per tahap
        step = getRectangleTransition().height / 100.0;

        // Mendapatkan nilai x, y, width, height
        x = getRectangleTransition().x;
        y = getRectangleTransition().y + (step * this.effect);
        width = getRectangleTransition().width;
        height = getRectangleTransition().height - (step * this.effect);

        // Mengubah Double Rectangle
        getRectangleDouble().setRect(x, y, width, height);

        // Melakukan penggambaran
        g2.setClip(getRectangleTransition());
        g2.setPaint(getColorTransition());
        g2.fill(getRectangleDouble());
    }

    /**
     * Melakukan pewarnaan efek WipeRight
     * @param g2
     */
    protected void paintTransitionWipeRight(Graphics2D g2) {
        // Mendapatkan nilai pertahap
        step = getRectangleTransition().width / 100.0;

        // mendapatkan posisi x, y, width, height
        x = getRectangleTransition().x + (step * this.effect);
        y = getRectangleTransition().y;
        width = getRectangleTransition().width - (step * this.effect);
        height = getRectangleTransition().height;

        // mengubah nilai double rectangle
        getRectangleDouble().setRect(x, y, width, height);

        // melakukan penggambaran
        g2.setClip(getRectangleTransition());
        g2.setPaint(getColorTransition());
        g2.fill(getRectangleDouble());
    }

    /**
     * Melakukan penggambaran efek Wipe Left
     * @param g2
     */
    protected void paintTransitionWipeLeft(Graphics2D g2) {
        // Mendapatkan nilai pertahap
        step = getRectangleTransition().width / 100.0;

        // mendapatkan nilai x, y, width, height
        x = getRectangleTransition().x;
        y = getRectangleTransition().y;
        width = getRectangleTransition().width - (step * this.effect);
        height = getRectangleTransition().height;

        // mengubah nilai double rectangle
        getRectangleDouble().setRect(x, y, width, height);

        // melakukan pengambaran
        g2.setClip(getRectangleTransition());
        g2.setPaint(getColorTransition());
        g2.fill(getRectangleDouble());
    }

    /**
     * Melakukan penggambaran efek Wipe Up
     * @param g2
     */
    protected void paintTransitionWipeUp(Graphics2D g2) {
        // mendapatkan nilai pertahap
        step = getRectangleTransition().height / 100.0;

        // mendapatkan nilai x, y, width, height
        x = getRectangleTransition().x;
        y = getRectangleTransition().y;
        width = getRectangleTransition().width;
        height = getRectangleTransition().height - (step * this.effect);

        // mengubah nilai rectangle double
        getRectangleDouble().setRect(x, y, width, height);

        // melakukan penggambaran
        g2.setClip(getRectangleTransition());
        g2.setPaint(getColorTransition());
        g2.fill(getRectangleDouble());
    }

    /**
     * Melakukan penggambaran efek SPlit Horizontal In
     * @param g2
     */
    protected void paintTransitionSplitHorizontalIn(Graphics2D g2) {
        // Mendapatkan nilai pertahap
        step = getRectangleTransition().height / 100.0;

        // Mendapatkan nilai tengah
        center = getRectangleTransition().y + getRectangleTransition().height / 2;

        // Mendapatkan nilai x, y, width, height
        height = getRectangleTransition().height - (step * this.effect);
        width = getRectangleTransition().width;
        x = getRectangleTransition().x;
        y = center - (height / 2);

        // mengubah nilai rectangle double
        getRectangleDouble().setRect(x, y, width, height);

        // melakukan penggambaran
        g2.setClip(getRectangleTransition());
        g2.setPaint(getColorTransition());
        g2.fill(getRectangleDouble());
    }

    /**
     * Melakukan penggambaran efek SPlit Horizontal Out
     * @param g2
     */
    protected void paintTransitionSplitHorizontalOut(Graphics2D g2) {
        // Mendapatkan nilai pertahap
        step = getRectangleTransition().height / 100.0;

        // Mendapatkan nilai tengah
        center = getRectangleTransition().y + getRectangleTransition().height / 2;

        // mendapatkan nilai x, y, width, height
        height = step * this.effect;
        width = getRectangleTransition().width;
        x = getRectangleTransition().x;
        y = center - (height / 2);

        // Mengubah nilai rectangle double
        getRectangleDouble().setRect(x, y, width, height);

        // Mengubah nilai area
        area = new Area(getRectangleTransition());
        area2 = new Area(getRectangleDouble());
        area.exclusiveOr(area2);

        // melakukan pengambaran
        g2.setClip(area);
        g2.setPaint(getColorTransition());
        g2.fill(getRectangleTransition());
    }

    /**
     * Melakukan penggambaran efek SPlit Vertical In
     * @param g2
     */
    protected void paintTransitionSplitVerticalIn(Graphics2D g2) {
        // Mendapatkan nilai pertahap
        step = getRectangleTransition().width / 100.0;

        // Mendapatkan nilai tengah
        center = getRectangleTransition().x + getRectangleTransition().width / 2;

        // mendapatkan nilai x, y, width, height
        width = getRectangleTransition().width - (step * this.effect);
        height = getRectangleTransition().height;
        x = center - (width / 2);
        y = getRectangleTransition().y;

        // mengubah nilai rectangle double
        getRectangleDouble().setRect(x, y, width, height);

        // melakukan penggambaran
        g2.setClip(getRectangleTransition());
        g2.setPaint(getColorTransition());
        g2.fill(getRectangleDouble());
    }

    /**
     * Melakukan penggambaran efek SPlit Vertical Out
     * @param g2
     */
    protected void paintTransitionSplitVerticalOut(Graphics2D g2) {
        // Mendapatkan nilai pertahap
        step = getRectangleTransition().width / 100.0;

        // Mendapatkan nilai tengah
        center = getRectangleTransition().x + getRectangleTransition().width / 2;

        // mendapatkan nilai x, y, width, height
        width = step * this.effect;
        height = getRectangleTransition().height;
        x = center - (width / 2);
        y = getRectangleTransition().y;

        // mengubah nilai rectangle double
        getRectangleDouble().setRect(x, y, width, height);

        // Mengubah nilai area dan area2
        area = new Area(getRectangleTransition());
        area2 = new Area(getRectangleDouble());
        area.exclusiveOr(area2);

        // Melakukan penggambaran
        g2.setClip(area);
        g2.setPaint(getColorTransition());
        g2.fill(getRectangleTransition());
    }

    /**
     * Melakukan penggambaran efek Box In
     * @param g2
     */
    protected void paintTransitionBoxIn(Graphics2D g2) {
        // Mengubah point tengah
        getCenterPoint().setLocation(getRectangleTransition().width / 2, getRectangleTransition().height / 2);

        // mendapatkan nilai pertahap
        stepW = getRectangleTransition().width / 100.0;
        stepH = getRectangleTransition().height / 100.0;

        // mendapatkan nilai x, y, width, height
        width = getRectangleTransition().width - (stepW * effect);
        height = getRectangleTransition().height - (stepH * effect);
        x = getCenterPoint().x - (width / 2) + getRectangleTransition().x;
        y = getCenterPoint().y - (height / 2) + getRectangleTransition().y;

        // mengubah nilai rectangle double
        getRectangleDouble().setRect(x, y, width, height);

        // melakukan penggambaran
        g2.setClip(getRectangleTransition());
        g2.setPaint(getColorTransition());
        g2.fill(getRectangleDouble());
    }

    /**
     * Melakukan penggambaran efek Box Out
     * @param g2
     */
    protected void paintTransitionBoxOut(Graphics2D g2) {
        // Mengubah point tengah
        getCenterPoint().setLocation(getRectangleTransition().width / 2, getRectangleTransition().height / 2);

        // mendapatkan nila pertahap
        stepW = getRectangleTransition().width / 100.0;
        stepH = getRectangleTransition().height / 100.0;

        // mendapatkan nilai x, y, width, height
        width = (stepW * effect);
        height = (stepH * effect);
        x = getCenterPoint().x - (width / 2) + getRectangleTransition().x;
        y = getCenterPoint().y - (height / 2) + getRectangleTransition().y;

        // mengubah nilai rectangle double
        getRectangleDouble().setRect(x, y, width, height);

        // mengubah nilai area 
        area = new Area(getRectangleTransition());
        area2 = new Area(getRectangleDouble());
        area.exclusiveOr(area2);

        // melakukan penggambaran
        g2.setClip(area);
        g2.setPaint(getColorTransition());
        g2.fill(getRectangleTransition());
    }

    /**
     * Melakukan Penggambaran efek Blinds Horizontal
     * @param g2
     */
    protected void paintTransitionBlindsHorizontal(Graphics2D g2) {
        // mendapatkan nilai temp
        temp = ((getRectangleTransition().height * 1.0) / (getBlindsStep() * 1.0));

        // mendapatkan nilai pertahap
        step = temp / 100.0;

        // mengubah nilai Graphics
        g2.setClip(getRectangleTransition());
        g2.setPaint(getColorTransition());

        // mendapatkan nilai x, y, width, height lalu menggambarnya
        x = getRectangleTransition().x;
        height = temp - (step * effect);
        width = getRectangleTransition().width;
        for (i = 0; i < getBlindsStep(); i++) {
            y = getRectangleTransition().y + (temp * i);
            getRectangleDouble().setRect(x, y, width, height);
            g2.fill(getRectangleDouble());
        }
    }

    /**
     * Melakukan penggambaran efek Blinds Vertical
     * @param g2
     */
    protected void paintTransitionBlindsVertical(Graphics2D g2) {
        // mendaptkan nilai temp
        temp = (getRectangleTransition().width * 1.0) / (getBlindsStep() * 1.0);

        // mendapatkan nilai pertahap
        step = temp / 100.0;

        // mengubah nilai Graphics
        g2.setClip(getRectangleTransition());
        g2.setPaint(getColorTransition());

        // mendapatkan nilai x, y, width, height lalu menggambarnya
        y = getRectangleTransition().y;
        width = temp - (step * effect);
        height = getRectangleTransition().height;
        for (i = 0; i < getBlindsStep(); i++) {
            x = getRectangleTransition().x + (temp * i);
            getRectangleDouble().setRect(x, y, width, height);
            g2.fill(getRectangleDouble());
        }
    }
    // </editor-fold>    
    // <editor-fold defaultstate="collapsed" desc="Temp Code">

    /**
     * Mendapatkan animator milik TransitionComponent
     * @return Animator
     */
    protected Animator getAnimator() {
        if (animator == null) {
            animator = new Animator(0, new PropertySetter(this, "effect", 0, 101));
        }
        return animator;
    }

    /**
     * Mendapatkan AlphaComposite
     * @return AlphaCOmposite
     */
    protected AlphaComposite getAlphaComposite() {
        if (alphaComposite == null) {
            alphaComposite = AlphaComposite.SrcOver;
        }
        return alphaComposite;
    }

    /**
     * Mendapatkan DOuble Rectangle 
     * @return Rectangle2D.Double
     */
    protected Double getRectangleDouble() {
        if (rectangleDouble == null) {
            rectangleDouble = new Double();
        }
        return rectangleDouble;
    }

    /**
     * Mendapatkan center Point
     * @return Point
     */
    protected Point getCenterPoint() {
        if (centerPoint == null) {
            centerPoint = new Point();
        }
        return centerPoint;
    }

    /**
     * Menambahkan aksi Transition Listener
     * @param listener 
     */
    public void addTransitionListener(TransitionListener listener) {
        listenerList.add(TransitionListener.class, listener);
    }

    /**
     * Menghapus sebuah listener
     * @param listener
     */
    public void removeTransitionListener(TransitionListener listener) {
        listenerList.remove(TransitionListener.class, listener);
    }

    /**
     * Menjalankan aksi {@link TransitionListener#onStart(open.usu.swing.event.TransitionEvent) }
     * @param event
     */
    protected void fireTransitionListenerOnStart(TransitionEvent event) {
        for (TransitionListener listenr : listenerList.getListeners(TransitionListener.class)) {
            listenr.onStart(event);
        }
    }

    /**
     * Menjalankan aksi {@link TransitionListener#onFinish(open.usu.swing.event.TransitionEvent) }
     * @param event
     */
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
     * Mendapatkan nilai effect
     * @return int nilai effect
     * @deprecated TIDAK DIPERBOLEHKAN UNTUK MEMANGGIL METODE INI
     */
    @Deprecated
    public int getEffect() {
        return effect;
    }

    /**
     * Mengubah nilai effect
     * @param effect nilai baru
     * @deprecated TIDAK DIPERBOLEHKAN UNTUK MENGUBAH NILAI INI
     */
    @Deprecated
    void setEffect(int effect) {
        this.effect = effect;
        repaint();

        // pakah effect lebih dari atau sama dengan 100
        if (effect >= 100) {
            // jika benar, maka transisi selesai
            getAnimator().stop();
            fireTransitionListenerOnFinish(getTransitionEvent());
        }
    }

    @Override
    @Deprecated
    public void setOpaque(boolean isOpaque) {
        super.setOpaque(false);
    }
}
