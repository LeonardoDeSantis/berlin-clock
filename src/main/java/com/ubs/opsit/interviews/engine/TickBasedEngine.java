package com.ubs.opsit.interviews.engine;

import com.ubs.opsit.interviews.model.BerlinClock;
import com.ubs.opsit.interviews.model.BerlinClockInputTime;
import com.ubs.opsit.interviews.model.light.Light;
import java.util.Date;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TickBasedEngine implements BerlinClockEngine {

    private final Logger logger = LoggerFactory.getLogger(TickBasedEngine.class);
    
    @Override
    public void init(BerlinClock berlinClock, BerlinClockInputTime time) {

        Date before = new Date();
        
        int totalAmountOfSeconds = time.getHours() * 3600 + time.getMinutes() * 60 + time.getSeconds();
        logger.debug("totalAmountOfSeconds:" + totalAmountOfSeconds);
        
        for (int i = 0; i <= totalAmountOfSeconds; i++) {

            if (i % 2 == 0) {
                berlinClock.getTwoSecondsLight().switchOn();
            } else {
                berlinClock.getTwoSecondsLight().switchOff();
            }

            if (i != 0 && i % 60 == 0) {
                
                //Minute handling
                if (!areAllLightsOn(berlinClock.getMinuteLights())) {
                    switchOnNextLight(berlinClock.getMinuteLights());
                } else {                    
                    switchOffAllLights(berlinClock.getMinuteLights());

                    //5 minutes handling
                    if (!areAllLightsOn(berlinClock.getFiveMinutesLights())) {
                        switchOnNextLight(berlinClock.getFiveMinutesLights());
                    } else {
                        switchOffAllLights(berlinClock.getFiveMinutesLights());

                        //hour handling
                        if (!areAllLightsOn(berlinClock.getHourLights())) {
                            switchOnNextLight(berlinClock.getHourLights());
                        } else {
                            switchOffAllLights(berlinClock.getHourLights());
                            
                            //5 hours handling
                            if (!areAllLightsOn(berlinClock.getFiveHoursLights())) {
                                switchOnNextLight(berlinClock.getFiveHoursLights());
                            } else {
                                switchOffAllLights(berlinClock.getFiveHoursLights());
                                switchOffAllLights(berlinClock.getHourLights());
                                switchOnNextLight(berlinClock.getHourLights());
                            }
                        }
                    }
                }
            }
        }
        Date after = new Date();
        logger.debug("Initialization done in:" + (after.getTime() - before.getTime()) + " ms");
    }

    protected void switchOnNextLight(List<Light> lights) {
        for (Light light : lights) {
            if (!light.isSwitchedOn()) {
                light.switchOn();
                break;
            }
        }
    }

    protected void switchOffAllLights(List<Light> lights) {
        lights.forEach(light -> light.switchOff());
    }

    protected boolean areAllLightsOn(List<Light> lights) {
        return lights.stream().noneMatch(light -> !light.isSwitchedOn());
    }

}
