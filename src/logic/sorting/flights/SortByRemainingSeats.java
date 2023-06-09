/*
Author: Oleksandr Danchenko
time spent: 10 minutes
date: 23 May 2023
version #1
*/

package logic.sorting.flights;

import logic.records.FlightInfo;

/**
 * The SortByRemainingSeats class implements the FlightComparator interface and provides a way of comparing flights by the number of remaining seats.
 *
 * @author Oleksandr Danchenko
 */
public class SortByRemainingSeats implements FlightComparator {
    /**
     * Constructs a SortByRemainingSeats object.
     *
     * @author Oleksandr Danchenko
     */
    public SortByRemainingSeats() {

    }

    /**
     * Compares two FlightInfo objects based on the number of remaining seats.
     *
     * @param flight1 the first FlightInfo object to compare.
     * @param flight2 the second FlightInfo object to compare.
     * @return an integer value indicating the comparison result.
     * @author Oleksandr Danchenko
     */
    @Override
    public int compare(FlightInfo flight1, FlightInfo flight2) {
        if (flight1.getSeatsLeft() > flight2.getSeatsLeft()) return GREATER;
        if (flight1.getSeatsLeft() == flight2.getSeatsLeft()) return EQUAL;
        return LESSER;
    }
}

