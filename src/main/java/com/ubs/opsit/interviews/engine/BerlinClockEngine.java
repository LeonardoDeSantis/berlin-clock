package com.ubs.opsit.interviews.engine;

import com.ubs.opsit.interviews.model.BerlinClock;
import com.ubs.opsit.interviews.model.BerlinClockInputTime;

public interface BerlinClockEngine {
      
    public void init(BerlinClock berlinClock, BerlinClockInputTime time);
    
}
