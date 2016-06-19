package com.ubs.opsit.interviews.model.light;

public abstract class Light {
    
    private boolean switchedOn = false;
    protected LightColor color;

    public boolean isSwitchedOn() {
        return switchedOn;
    }

    public LightColor getColor() {
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
        return "Light{" + "switchedOn=" + switchedOn + ", color=" + color + '}';
    }

}
