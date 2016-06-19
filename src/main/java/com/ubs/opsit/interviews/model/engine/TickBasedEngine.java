package com.ubs.opsit.interviews.model.engine;

import com.ubs.opsit.interviews.model.BerlinClock;
import com.ubs.opsit.interviews.model.BerlinClockInputTime;
import com.ubs.opsit.interviews.model.lamp.Lamp;
import java.util.Date;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * This BerlinClockEngine knows the lights status based on a step by step
 * calculation. It starts from the 0 seconds point and reaches the given time
 * performing steps of 1 second. This kind of algorithm is slow but emulates a
 * real clock engine, every status depends on the previous one.
 */
public class TickBasedEngine implements BerlinClockEngine {

    private final Logger logger = LoggerFactory.getLogger(TickBasedEngine.class);

    @Override
    public void init(BerlinClock berlinClock, BerlinClockInputTime time) {

        logger.debug("Initialing berlin clock with time:" + time);

        berlinClock.turnAllTheLampsOff();
        Date before = new Date();

        int totalAmountOfSeconds = time.getHours() * 3600 + time.getMinutes() * 60 + time.getSeconds();
        logger.debug("totalAmountOfSeconds:" + totalAmountOfSeconds);

        for (int i = 0; i <= totalAmountOfSeconds; i++) {

            if (berlinClock.getTwoSecondsLamp().isSwitchedOn()) {
                berlinClock.getTwoSecondsLamp().switchOff();
            } else {
                berlinClock.getTwoSecondsLamp().switchOn();
            }

            if (i != 0 && i % 60 == 0) {

                //Minute handling
                if (!areAllLampsOn(berlinClock.getMinuteLamps())) {
                    switchOnNextLamp(berlinClock.getMinuteLamps());
                } else {
                    switchOffAllLamps(berlinClock.getMinuteLamps());

                    //5 minutes handling
                    if (!areAllLampsOn(berlinClock.getFiveMinutesLamps())) {
                        switchOnNextLamp(berlinClock.getFiveMinutesLamps());
                    } else {
                        switchOffAllLamps(berlinClock.getFiveMinutesLamps());

                        //hour handling
                        if (!areAllLampsOn(berlinClock.getHourLamps())) {
                            switchOnNextLamp(berlinClock.getHourLamps());
                        } else {
                            switchOffAllLamps(berlinClock.getHourLamps());

                            //5 hours handling
                            if (!areAllLampsOn(berlinClock.getFiveHoursLamps())) {
                                switchOnNextLamp(berlinClock.getFiveHoursLamps());
                            } else {
                                switchOffAllLamps(berlinClock.getFiveHoursLamps());
                                switchOffAllLamps(berlinClock.getHourLamps());
                                switchOnNextLamp(berlinClock.getHourLamps());
                            }
                        }
                    }
                }
            }
        }
        Date after = new Date();
        logger.debug("Initialization done in:" + (after.getTime() - before.getTime()) + " ms");
    }

    protected void switchOnNextLamp(List<Lamp> lamps) {
        for (Lamp lamp : lamps) {
            if (!lamp.isSwitchedOn()) {
                lamp.switchOn();
                break;
            }
        }
    }

    protected void switchOffAllLamps(List<Lamp> lamps) {
        lamps.forEach(lamp -> lamp.switchOff());
    }

    protected boolean areAllLampsOn(List<Lamp> lamps) {
        return lamps.stream().noneMatch(lamp -> !lamp.isSwitchedOn());
    }

}
