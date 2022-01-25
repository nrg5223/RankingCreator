package com.nrg5223.Data.Objects.Utilities;

public class TimeConverter {

    /**
     * Convert a string representing an amount of time into that amount of time
     * in seconds
     *
     * @param time a string in the format m:ss where m is the number of minutes
     *             and ss is a number of seconds
     * @return an integer that equals the total number of seconds
     */
    public static int toSeconds(String time) {
        String[] times = time.split("[:]");
        int mins = Integer.parseInt(times[0]);
        int secs = Integer.parseInt(times[1]);
        return 60 * mins + secs;
    }

    /**
     * Create a time representation in the format m:ss from a given number of
     * total seconds
     *
     * @param totalSeconds the total number of seconds in an amount of time
     * @return the string representation of amount of time in the format m:ss
     */
    public static String toFormatted(int totalSeconds) {
        int seconds = totalSeconds % 60;
        int mins = (totalSeconds - seconds) / 60;
        return mins + ":" + seconds;
    }
}
