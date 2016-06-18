package com.ubs.opsit.interviews.model;

import com.ubs.opsit.interviews.model.light.Light;
import com.ubs.opsit.interviews.model.light.RedLight;
import com.ubs.opsit.interviews.model.light.YellowLight;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Consumer;

public class BerlinClock {

    private static final int FIVE_HOURS_ROW_SIZE = 4;
    private static final int HOUR_ROW_SIZE = 4;
    private static final int FIVE_MINUTES_ROW_SIZE = 11;
    private static final int MINUTE_ROW_SIZE = 4;
    
    private final Light twoSecondsLight;
    private final List<Light> fiveHoursLights = new ArrayList<>(FIVE_HOURS_ROW_SIZE);
    private final List<Light> hourLights = new ArrayList<>(HOUR_ROW_SIZE);
    private final List<Light> fiveMinutesLights = new ArrayList<>(FIVE_MINUTES_ROW_SIZE);
    private final List<Light> minuteLights = new ArrayList<>(MINUTE_ROW_SIZE);

    public BerlinClock() { 
        
        twoSecondsLight = new YellowLight();
        
        for (int i = 0; i < FIVE_HOURS_ROW_SIZE; i++) {
            fiveHoursLights.add(i, new RedLight());
        }

        for (int i = 0; i < HOUR_ROW_SIZE; i++) {
            hourLights.add(i, new RedLight());
        }

        for (int i = 0; i < FIVE_MINUTES_ROW_SIZE; i++) {
            if (i + 1 % 3 == 0) {
                fiveMinutesLights.add(i, new RedLight());
            } else {
                fiveMinutesLights.add(i, new YellowLight());
            }
        }

        for (int i = 0; i < MINUTE_ROW_SIZE; i++) {
            minuteLights.add(i, new YellowLight());
        }        
    }

    public Light getTwoSecondsLight() {
        return twoSecondsLight;
    }

    public List<Light> getFiveHoursLights() {
        return Collections.unmodifiableList(fiveHoursLights);
    }

    public List<Light> getHourLights() {
        return Collections.unmodifiableList(hourLights);
    }

    public List<Light> getFiveMinutesLights() {
        return Collections.unmodifiableList(fiveMinutesLights);
    }

    public List<Light> getMinuteLights() {
        return Collections.unmodifiableList(minuteLights);
    }
    
    public void turnAllTheLightsOn() {
        forAllLight(light -> light.switchOn());
    }
    
    public void turnAllTheLightsOff() {
        forAllLight(light -> light.switchOff());
    }
    
    private void forAllLight(Consumer<? super Light> action) {
        action.accept(twoSecondsLight);
        fiveHoursLights.forEach(action);
        hourLights.forEach(action);
        fiveMinutesLights.forEach(action);
        minuteLights.forEach(action);
    }
    
}
