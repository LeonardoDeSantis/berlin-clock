package com.ubs.opsit.interviews.model.lamp;

public abstract class Lamp {
    
    private boolean switchedOn = false;
    protected LampColor color;

    public boolean isSwitchedOn() {
        return switchedOn;
    }

    public LampColor getColor() {
        return color;
    }
    
    public void switchOff(){
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
