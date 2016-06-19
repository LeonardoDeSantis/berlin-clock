package com.ubs.opsit.interviews.model;

/**
 * Since it is acceptable to receive 24:00:00 for a BerlinClock time conversion,
 * I think it is convenient to generalize the input and think of it as "Give me
 * the BerlinClock representation after X hours, Y minutes and Z seconds it was
 * activated"
 */
public class BerlinClockInputTime {

    private int hours;
    private int minutes;
    private int seconds;

    /**
     * @param time A string representing a time in H:mm:ss format, where H can
     * be any value greater than zero, mm and ss must be between 0 and 59.
     */
    public BerlinClockInputTime(String time) {

        if (time == null || time.isEmpty()) {
            throw new IllegalArgumentException("Input time is empty");
        }
        String[] dateStr = time.split(":");
        if (dateStr == null || dateStr.length < 3) {
            throw new IllegalArgumentException("Input time wrong format");
        }

        hours = Integer.valueOf(dateStr[0]);
        minutes = Integer.valueOf(dateStr[1]);
        seconds = Integer.valueOf(dateStr[2]);

        if (minutes > 59 || minutes < 0) {
            throw new IllegalArgumentException("Minutes must be between 0 and 59");
        }
        if (seconds > 59 || seconds < 0) {
            throw new IllegalArgumentException("Seconds must be between 0 and 59");
        }
        if (hours < 0) {
            throw new IllegalArgumentException("Hours must be greater than 0");
        }
        if (hours > 24) {
            hours %= 24;
            if (hours == 0) {
                hours = 24;
            }
        }
    }

    public int getHours() {
        return hours;
    }

    public int getMinutes() {
        return minutes;
    }

    public int getSeconds() {
        return seconds;
    }

    @Override
    public String toString() {
        return "BerlinClockInputTime{" + "hours=" + hours + ", minutes=" + minutes + ", seconds=" + seconds + '}';
    }

}
