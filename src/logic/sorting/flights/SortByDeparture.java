/*
Author: Oleksandr Danchenko
time spent: 10 minutes
date: 23 May 2023
version #1
*/

package logic.sorting.flights;

import logic.records.FlightInfo;

/**
 * The SortByDeparture class implements the FlightComparator interface and provides a way of comparing flights by departure.
 *
 * @author Oleksnadr Danchenko
 */
public class SortByDeparture implements FlightComparator {
    /**
     * Constructs a SortByDeparture object.
     *
     * @author Oleksandr Danchenko
     */
    public SortByDeparture() {

    }

    /**
     * Lexicographically compares two flight information objects based on their departure.
     *
     * @param flight1 the first flight information object.
     * @param flight2 the second flight information object.
     * @return an integer indicating the comparison result.
     * @author Oleksandr Danchenko
     */
    @Override
    public int compare(FlightInfo flight1, FlightInfo flight2) {
        if (flight1.getDeparture().equals(flight2.getDeparture())) return EQUAL;
        if (flight1.getDeparture().compareTo(flight2.getDeparture()) > 0) return GREATER;
        return LESSER;
    }
}

