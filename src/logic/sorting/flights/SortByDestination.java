/*
Author: Oleksandr Danchenko
time spent: 10 minutes
date: 23 May 2023
version #1
*/

package logic.sorting.flights;

import logic.data_record.FlightInfo;

/**
 * The SortByDestination class implements the FlightComparator interface and provides a way for comparing flights by destination.
 *
 * @author Oleksandr Danchenko
 */
public class SortByDestination implements FlightComparator {
    /**
     * Constructs a SortByDestination object.
     *
     * @author Oleksandr Danchenko
     */
    public SortByDestination() {

    }

    /**
     * Lexicographically compares two flight information objects based on their destination.
     *
     * @param flight1 the first flight information object.
     * @param flight2 the second flight information object.
     * @return an integer indicating the comparison result.
     * @author Oleksandr Danchenko
     */
    @Override
    public int compare(FlightInfo flight1, FlightInfo flight2) {
        if (flight1.getDestination().equals(flight2.getDestination())) return EQUAL;
        if (flight1.getDestination().compareTo(flight2.getDestination()) > 0) return GREATER;
        return LESSER;
    }
}

