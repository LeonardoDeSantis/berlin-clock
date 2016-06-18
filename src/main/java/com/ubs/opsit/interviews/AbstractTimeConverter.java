package com.ubs.opsit.interviews;

import com.ubs.opsit.interviews.model.BerlinClock;
import com.ubs.opsit.interviews.model.BerlinClockInputTime;
import com.ubs.opsit.interviews.rendering.BerlinClockRenderer;
import com.ubs.opsit.interviews.rendering.InterviewStoryRenderer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class AbstractTimeConverter implements TimeConverter {
    
    protected final Logger logger = LoggerFactory.getLogger(AbstractTimeConverter.class);
    
    protected BerlinClock berlinClock;
    
    public AbstractTimeConverter() {    
        berlinClock = new BerlinClock(null);
    }
  
    @Override
    public String convertTime(String aTime) {
        logger.debug("Received:" + aTime); 
        BerlinClockRenderer berlinClockRenderer = new InterviewStoryRenderer();
        berlinClock.init(new BerlinClockInputTime(aTime));
        String convertedTime = berlinClockRenderer.render(berlinClock);
        logger.debug("Returning:" + convertedTime);
        return convertedTime;
    }
    
}
