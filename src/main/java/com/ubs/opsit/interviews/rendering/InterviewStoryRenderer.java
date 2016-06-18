package com.ubs.opsit.interviews.rendering;

import com.ubs.opsit.interviews.model.BerlinClock;
import com.ubs.opsit.interviews.model.light.Light;
import com.ubs.opsit.interviews.model.light.LightColor;

public class InterviewStoryRenderer implements BerlinClockRenderer {

    private final static String NL = System.getProperty("line.separator");

    private static final String OFF = "O";
    private static final String RED_STR = "R";
    private static final String YELLOW_STR = "Y";

    @Override
    public String render(BerlinClock berlinClock) throws Exception {

        StringBuilder output = new StringBuilder();

        //Rendering two seconds light
        output.append(getStringFromLight(berlinClock.getTwoSecondsLight()));
        output.append(NL);
        
        //Rendering 5 hours lights row
        for (Light light : berlinClock.getFiveHoursLights()) {
            output.append(getStringFromLight(light));
        }
        output.append(NL);
        
        //Rendering 1 hour lights row
        for (Light light : berlinClock.getHourLights()) {
            output.append(getStringFromLight(light));
        }
        output.append(NL);
        
        //Rendering 5 minutes lights row
        for (Light light : berlinClock.getFiveMinutesLights()) {
            output.append(getStringFromLight(light));
        }
        output.append(NL);
        
        //Rendering 1 minute lights row
        for (Light light : berlinClock.getMinuteLights()) {
            output.append(getStringFromLight(light));
        }
        
        return output.toString();
        
    }

    private String getStringFromLight(Light light) throws Exception {
        if (light.isSwitchedOn()) {
            return getStringFromColor(light.getColor());
        } else {
            return OFF;
        }
    }
    
    private String getStringFromColor(LightColor color) throws Exception {
        switch (color) {
            case RED:
                return RED_STR;
            case YELLOW:
                return YELLOW_STR;
            default: {
                throw new Exception("Unknown color");
            }
        }
    }

}
