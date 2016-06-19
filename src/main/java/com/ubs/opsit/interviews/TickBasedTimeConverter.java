package com.ubs.opsit.interviews;

import com.ubs.opsit.interviews.model.engine.TickBasedEngine;

public class TickBasedTimeConverter extends AbstractTimeConverter {

    public TickBasedTimeConverter() {
        super();
        berlinClock.setEngine(new TickBasedEngine());
    }
   
}
