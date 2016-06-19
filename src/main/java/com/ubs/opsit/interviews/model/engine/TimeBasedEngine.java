package com.ubs.opsit.interviews.model.engine;

import com.ubs.opsit.interviews.model.BerlinClock;
import com.ubs.opsit.interviews.model.BerlinClockInputTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TimeBasedEngine implements BerlinClockEngine {

    private final Logger logger = LoggerFactory.getLogger(TimeBasedEngine.class);

    @Override
    public void init(BerlinClock berlinClock, BerlinClockInputTime time) {

        logger.debug("Initialing berlin clock with time:" + time);
        
        berlinClock.turnAllTheLampsOff();
        int fiveHoursLamps, hourLamps, fiveMinutesLamps, minuteLamps;

        fiveHoursLamps = time.getHours() / BerlinClock.FIVE_HOURS_SOCKET_VALUE;
        hourLamps = time.getHours() % BerlinClock.FIVE_HOURS_SOCKET_VALUE;
        fiveMinutesLamps = time.getMinutes() / BerlinClock.FIVE_MINUTES_SOCKET_VALUE;
        minuteLamps = time.getMinutes() % BerlinClock.FIVE_MINUTES_SOCKET_VALUE;

        logger.debug("Lamp numbers are fiveHoursLamps:" + fiveHoursLamps
                + " hourLamps:" + hourLamps
                + " fiveMinutesLamps:" + fiveMinutesLamps
                + " minuteLamps:" + minuteLamps);

        if (time.getSeconds() % 2 == 0) {
            berlinClock.getTwoSecondsLamp().switchOn();
        } else {
            berlinClock.getTwoSecondsLamp().switchOff();
        }

        for (int i = 0; i < fiveHoursLamps; i++) {
            berlinClock.getFiveHoursLamps().get(i).switchOn();
        }

        for (int i = 0; i < hourLamps; i++) {
            berlinClock.getHourLamps().get(i).switchOn();
        }

        for (int i = 0; i < fiveMinutesLamps; i++) {
            berlinClock.getFiveMinutesLamps().get(i).switchOn();
        }

        for (int i = 0; i < minuteLamps; i++) {
            berlinClock.getMinuteLamps().get(i).switchOn();
        }
    }
}
