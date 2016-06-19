package com.ubs.opsit.interviews.model.engine;

import com.ubs.opsit.interviews.model.BerlinClock;
import com.ubs.opsit.interviews.model.BerlinClockInputTime;

/**
 * A BerlinClockEngine has the responsibility of switching a BerlinClock's lights on or off
 * based on given time input.
 */
public interface BerlinClockEngine {
      
    public void init(BerlinClock berlinClock, BerlinClockInputTime time);
    
}
