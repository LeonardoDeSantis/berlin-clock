package com.ubs.opsit.interviews.rendering;

import com.ubs.opsit.interviews.model.BerlinClock;

/**
 * This functional interface, given a BerlinClock, returns a particular string
 * representation of it
 */
public interface BerlinClockRenderer {

    public String render(BerlinClock berlinClock) throws IllegalArgumentException;

}
