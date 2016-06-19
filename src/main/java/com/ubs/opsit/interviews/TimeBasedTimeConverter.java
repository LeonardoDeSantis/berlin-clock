package com.ubs.opsit.interviews;

import com.ubs.opsit.interviews.model.engine.TimeBasedEngine;

public class TimeBasedTimeConverter extends AbstractTimeConverter {
    
    public TimeBasedTimeConverter() {
        super();
        berlinClock.setEngine(new TimeBasedEngine());
    }

}
