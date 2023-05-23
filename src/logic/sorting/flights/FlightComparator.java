/*
Author: Oleksandr Danchenko
time spent: 10 minutes
Date: 15 May 2023
version #1
*/

package logic.sorting.flights;

import logic.data_record.FlightInfo;

/**
 * A SeatComparator interface, used to provide a way to compare two flights.
 *
 * @author Oleksandr Danchenko
 */
public interface FlightComparator {
    /**
     * A constant GREATER, the number to be returned by the method compare.
     * Used when the first argument is considered to be greater than the second one.
     */
    public static final int GREATER = 1;
    /**
     * A constant EQUAL, the number to be returned by the method compare.
     * Used when the first argument is considered to be equal than the second one.
     */
    public static final int EQUAL = 0;
    /**
     * A constant GREATER, the number to be returned by the method compare.
     * Used when the first argument is considered to be LESSER than the second one.
     */
    public static final int LESSER = -1;

    /**
     * A method, which is supposed to return a number which represents the relationship between the two flight provided
     *
     * @param flight1 the first flight.
     * @param flight2 the second flight.
     * @return GREATER, EQUAL, or LESSER, if the first flight is greater, equal, or lesser than the second one respectively.
     * @author Oleksandr Danchenko
     */
    public int compare(FlightInfo flight1, FlightInfo flight2);
}
