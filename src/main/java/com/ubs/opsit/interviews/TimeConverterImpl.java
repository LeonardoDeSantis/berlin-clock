package com.ubs.opsit.interviews;

import com.ubs.opsit.interviews.engine.BerlinClockEngine;
import com.ubs.opsit.interviews.engine.TimeBasedEngine;
import com.ubs.opsit.interviews.model.BerlinClock;
import com.ubs.opsit.interviews.rendering.BerlinClockRenderer;
import com.ubs.opsit.interviews.rendering.InterviewStoryRenderer;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TimeConverterImpl implements TimeConverter {

    private final Logger logger = LoggerFactory.getLogger(TimeConverterImpl.class);
    
    @Override
    public String convertTime(String aTime) {
        logger.debug("Received:" + aTime);
        String convertedTime = null;
        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm:ss");
            Date theTime = simpleDateFormat.parse(aTime);
            
            BerlinClock berlinClock = new BerlinClock();
            BerlinClockEngine berlinClockEngine = new TimeBasedEngine();
            BerlinClockRenderer berlinClockRenderer = new InterviewStoryRenderer();
            
            berlinClockEngine.init(berlinClock, theTime);
            
            convertedTime = berlinClockRenderer.render(berlinClock);
            
        } catch (ParseException e) {
            logger.error("Exception caught while parsing date" ,e);
        }
        
        logger.debug("Returning:" + convertedTime);
        
        return convertedTime;
    }

}
