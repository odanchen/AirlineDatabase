/*
Author: Oleksandr Danchenko
time spent: 10 minutes
Date: 16 May 2023
version #1
*/

package logic.data_record;

import java.util.List;

/**
 * A Calendar data class which stores accurate current data about each flight in each day of the month.
 *
 * @author Oleksandr Danchenko
 */
public class Calendar {
    /**
     * A constant, representing the total number of days in the month.
     */
    public static final int NUMBER_OF_DAYS = 31;

    /**
     * An instance field of an array of lists of flights.
     * Each list of flights stores accurate information about every flight on that day.
     */
    private final List<FlightInfo>[] days;

    /**
     * A constructor of the class, initializes the object.
     *
     * @param days the record of information received from the database as the program launched.
     * @author Oleksandr Danchenko
     */
    public Calendar(List<FlightInfo>[] days) {
        this.days = days;
    }

    /**
     * A getter method which gets the list of flights on the given day.
     *
     * @param number the number of the day.
     * @return a list of flights planned for a specified day.
     * @author Oleksandr Danchenko
     */
    public List<FlightInfo> getDay(int number) {
        return days[number - 1];
    }
}
