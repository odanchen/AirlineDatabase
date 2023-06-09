/*
Author: Oleksandr Danchenko
time spent: 10 minutes
date: 23 May 2023
version #1
*/

package logic.sorting.flights;

import logic.records.FlightInfo;

/**
 * The SortByStatus class implements the FlightComparator interface and provides a way of comparing flights by their cancellation status.
 *
 * @author Oleksandr Danchenko
 */
public class SortByStatus implements FlightComparator {
    /**
     * Constructs a SortByStatus object.
     *
     * @author Oleksandr Danchenko
     */
    public SortByStatus() {

    }

    /**
     * Compares two FlightInfo objects based on their status (Available, Full, or Cancelled).
     *
     * @param flight1 the first FlightInfo object to compare.
     * @param flight2 the second FlightInfo object to compare.
     * @return an integer value indicating the comparison result.
     * @author Oleksandr Danchenko
     */
    @Override
    public int compare(FlightInfo flight1, FlightInfo flight2) {
        if (flight1.getStatus().equals(flight2.getStatus())) return EQUAL;
        if (flight1.getStatus().equals("Cancelled")) return GREATER;
        if (flight2.getStatus().equals("Cancelled")) return LESSER;
        if (flight1.getStatus().equals("Full")) return GREATER;
        if (flight2.getStatus().equals("Full")) return LESSER;
        return GREATER;
    }
}

