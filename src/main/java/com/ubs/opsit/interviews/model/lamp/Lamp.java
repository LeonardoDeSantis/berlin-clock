package com.ubs.opsit.interviews.model.lamp;

/**
 * Basic representation of a Lamp. Concrete classes of Lamp should define a
 * color. This object represent that kind of lamp with colored glass, ones a
 * manufacturer creates it, its impossible to change the color.
 */
public abstract class Lamp {

    private boolean switchedOn = false;
    protected LampColor color;

    public boolean isSwitchedOn() {
        return switchedOn;
    }

    public LampColor getColor() {
        return color;
    }

    public void switchOff() {
        switchedOn = false;
    }

    public void switchOn() {
        switchedOn = true;
    }

    @Override
    public String toString() {
        return "Lamp{" + "switchedOn=" + switchedOn + ", color=" + color + '}';
    }

}
