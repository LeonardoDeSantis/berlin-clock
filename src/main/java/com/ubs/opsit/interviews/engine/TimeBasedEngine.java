package com.ubs.opsit.interviews.engine;

import com.ubs.opsit.interviews.TimeConverterImpl;
import com.ubs.opsit.interviews.model.BerlinClock;
import com.ubs.opsit.interviews.model.BerlinClockInputTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TimeBasedEngine implements BerlinClockEngine {

    private final Logger logger = LoggerFactory.getLogger(TimeConverterImpl.class);

    @Override
    public void init(BerlinClock berlinClock, BerlinClockInputTime time) {

        logger.debug("Initialing berlin clock with time:" + time);

        int fiveHoursLights, hourLights, fiveMinutesLights, minuteLights;

        fiveHoursLights = time.getHours() / BerlinClock.FIVE_HOURS_SOCKET_VALUE;
        hourLights = time.getHours() % BerlinClock.FIVE_HOURS_SOCKET_VALUE;
        fiveMinutesLights = time.getMinutes() / BerlinClock.FIVE_MINUTES_SOCKET_VALUE;
        minuteLights = time.getMinutes() % BerlinClock.FIVE_MINUTES_SOCKET_VALUE;

        logger.debug("Light numbers are fiveHoursLights:" + fiveHoursLights
                + " hourLights:" + hourLights
                + " fiveMinutesLights:" + fiveMinutesLights
                + " minuteLights:" + minuteLights);

        if (time.getSeconds() % 2 == 0) {
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
