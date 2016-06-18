package com.ubs.opsit.interviews.engine;

import com.ubs.opsit.interviews.model.BerlinClock;
import java.util.Date;

public interface BerlinClockEngine {
    
    public void init(BerlinClock berlinClock, Date date);
    
}
