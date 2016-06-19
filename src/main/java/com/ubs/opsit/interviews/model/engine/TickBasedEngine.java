package com.ubs.opsit.interviews.model.engine;

import com.ubs.opsit.interviews.model.BerlinClock;
import com.ubs.opsit.interviews.model.BerlinClockInputTime;
import com.ubs.opsit.interviews.model.lamp.Lamp;
import java.util.Date;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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

            if (i % 2 == 0) {
                berlinClock.getTwoSecondsLamp().switchOn();
            } else {
                berlinClock.getTwoSecondsLamp().switchOff();
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
