package com.ubs.opsit.interviews.rendering;

import com.ubs.opsit.interviews.model.BerlinClock;
import com.ubs.opsit.interviews.model.lamp.Lamp;
import com.ubs.opsit.interviews.model.lamp.LampColor;

/**
 * Implementation of a BerlinClockRenderer to satisfy the acceptance test
 * layout
 */
public class InterviewStoryRenderer implements BerlinClockRenderer {

    private final static String NL = System.getProperty("line.separator");

    private static final String OFF = "O";
    private static final String RED_STR = "R";
    private static final String YELLOW_STR = "Y";

    @Override
    public String render(BerlinClock berlinClock) throws IllegalArgumentException {

        StringBuilder output = new StringBuilder();

        //Rendering two seconds lamp
        output.append(getStringFromLamp(berlinClock.getTwoSecondsLamp()));
        output.append(NL);
        
        //Rendering 5 hours lamps row
        for (Lamp lamp : berlinClock.getFiveHoursLamps()) {
            output.append(getStringFromLamp(lamp));
        }
        output.append(NL);
        
        //Rendering 1 hour lamps row
        for (Lamp lamp : berlinClock.getHourLamps()) {
            output.append(getStringFromLamp(lamp));
        }
        output.append(NL);
        
        //Rendering 5 minutes lamps row
        for (Lamp lamp : berlinClock.getFiveMinutesLamps()) {
            output.append(getStringFromLamp(lamp));
        }
        output.append(NL);
        
        //Rendering 1 minute lamps row
        for (Lamp lamp : berlinClock.getMinuteLamps()) {
            output.append(getStringFromLamp(lamp));
        }
        
        return output.toString();
        
    }

    private String getStringFromLamp(Lamp lamp) throws IllegalArgumentException {
        if (lamp.isSwitchedOn()) {
            return getStringFromColor(lamp.getColor());
        } else {
            return OFF;
        }
    }
    
    private String getStringFromColor(LampColor color) throws IllegalArgumentException {
        switch (color) {
            case RED:
                return RED_STR;
            case YELLOW:
                return YELLOW_STR;
            default: {
                throw new IllegalArgumentException("Unknown color");
            }
        }
    }

}
