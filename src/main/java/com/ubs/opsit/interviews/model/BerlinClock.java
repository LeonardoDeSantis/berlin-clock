package com.ubs.opsit.interviews.model;

import com.ubs.opsit.interviews.model.engine.BerlinClockEngine;
import com.ubs.opsit.interviews.model.lamp.Lamp;
import com.ubs.opsit.interviews.model.lamp.RedLamp;
import com.ubs.opsit.interviews.model.lamp.YellowLamp;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Consumer;

public class BerlinClock {

    private static final int FIVE_HOURS_ROW_SIZE = 4;
    private static final int HOUR_ROW_SIZE = 4;
    private static final int FIVE_MINUTES_ROW_SIZE = 11;
    private static final int MINUTE_ROW_SIZE = 4;
    
    public static final int FIVE_HOURS_SOCKET_VALUE = 5;
    public static final int FIVE_MINUTES_SOCKET_VALUE = 5;
        
    private final Lamp twoSecondsLamp;
    private final List<Lamp> fiveHoursLamps = new ArrayList<>(FIVE_HOURS_ROW_SIZE);
    private final List<Lamp> hourLamps = new ArrayList<>(HOUR_ROW_SIZE);
    private final List<Lamp> fiveMinutesLamps = new ArrayList<>(FIVE_MINUTES_ROW_SIZE);
    private final List<Lamp> minuteLamps = new ArrayList<>(MINUTE_ROW_SIZE);

    private BerlinClockEngine engine;
    
    public BerlinClock(BerlinClockEngine berlinClockEngine) { 
        
        this.engine = berlinClockEngine;
        
        twoSecondsLamp = new YellowLamp();
        
        for (int i = 0; i < FIVE_HOURS_ROW_SIZE; i++) {
            fiveHoursLamps.add(i, new RedLamp());
        }

        for (int i = 0; i < HOUR_ROW_SIZE; i++) {
            hourLamps.add(i, new RedLamp());
        }

        for (int i = 0; i < FIVE_MINUTES_ROW_SIZE; i++) {
            if ((i + 1) % 3 == 0) {
                fiveMinutesLamps.add(i, new RedLamp());
            } else {
                fiveMinutesLamps.add(i, new YellowLamp());
            }
        }

        for (int i = 0; i < MINUTE_ROW_SIZE; i++) {
            minuteLamps.add(i, new YellowLamp());
        }        
    }

    public void init(BerlinClockInputTime berlinClockInputTime) {
        engine.init(this, berlinClockInputTime);
    }
    
    public Lamp getTwoSecondsLamp() {
        return twoSecondsLamp;
    }

    public List<Lamp> getFiveHoursLamps() {
        return Collections.unmodifiableList(fiveHoursLamps);
    }

    public List<Lamp> getHourLamps() {
        return Collections.unmodifiableList(hourLamps);
    }

    public List<Lamp> getFiveMinutesLamps() {
        return Collections.unmodifiableList(fiveMinutesLamps);
    }

    public List<Lamp> getMinuteLamps() {
        return Collections.unmodifiableList(minuteLamps);
    }
    
    public void turnAllTheLampsOn() {
        forAllLamp(lamp -> lamp.switchOn());
    }
    
    public void turnAllTheLampsOff() {
        forAllLamp(lamp -> lamp.switchOff());
    }

    public void setEngine(BerlinClockEngine engine) {
        this.engine = engine;
    }

    public BerlinClockEngine getEngine() {
        return engine;
    }
    
    private void forAllLamp(Consumer<? super Lamp> action) {
        action.accept(twoSecondsLamp);
        fiveHoursLamps.forEach(action);
        hourLamps.forEach(action);
        fiveMinutesLamps.forEach(action);
        minuteLamps.forEach(action);
    }
    
}
