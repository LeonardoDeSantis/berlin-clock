package com.ubs.opsit.interviews.engine;

import com.ubs.opsit.interviews.TimeConverterImpl;
import com.ubs.opsit.interviews.model.BerlinClock;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TimeBasedEngine implements BerlinClockEngine {

    private Logger logger = LoggerFactory.getLogger(TimeConverterImpl.class);
    
    @Override
    public void init(BerlinClock berlinClock, Date date) {

        int hours, minutes, seconds;

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm:ss");
        String[] dateStr = simpleDateFormat.format(date).split(":");
        hours = Integer.valueOf(dateStr[0]);
        minutes = Integer.valueOf(dateStr[1]);
        seconds = Integer.valueOf(dateStr[2]);

        logger.debug("Initialing berlin clock with hours:" + hours + " minutes:" + minutes + " seconds:" + seconds);
        
        int fiveHoursLights, hourLights, fiveMinutesLights, minuteLights;

        fiveHoursLights = hours / BerlinClock.FIVE_HOURS_SOCKET_VALUE;
        hourLights = hours % BerlinClock.FIVE_HOURS_SOCKET_VALUE;
        fiveMinutesLights = minutes / BerlinClock.FIVE_MINUTES_SOCKET_VALUE;
        minuteLights = minutes % BerlinClock.FIVE_MINUTES_SOCKET_VALUE;

        logger.debug("Light numbers are fiveHoursLights:" + fiveHoursLights 
                + " hourLights:" + hourLights 
                + " fiveMinutesLights:" + fiveMinutesLights
                + " minuteLights:" + minuteLights);
        
        if (seconds % 2 == 0) {
            berlinClock.getTwoSecondsLight().switchOn();
        } else {
            berlinClock.getTwoSecondsLight().switchOff();
        }

        for (int i = 0; i < fiveHoursLights; i++) {
            berlinClock.getFiveHoursLights().get(i).switchOn();
        }

        for (int i = 0; i < hourLights; i++) {
            berlinClock.getHourLights().get(i).switchOn();
        }

        for (int i = 0; i < fiveMinutesLights; i++) {
            berlinClock.getFiveMinutesLights().get(i).switchOn();
        }

        for (int i = 0; i < minuteLights; i++) {
            berlinClock.getMinuteLights().get(i).switchOn();
        }
    }
}
