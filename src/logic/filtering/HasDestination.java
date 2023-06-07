/*
Author: Oleksandr Danchenko
time spent: 5 minutes
Date: 19 May 2023
version #1
*/

package logic.filtering;

import logic.data_record.FlightInfo;

/**
 * A class, implementation of the FlightFilter interface, used to check a flight for a specific place of destination.
 *
 * @author Oleksandr Danchenko
 */
public class HasDestination implements FlightFilter {
    /**
     * The place of destination to which the flight destination is compared.
     */
    private final String destination;

    /**
     * A constructor that initializes the destination for comparison.
     *
     * @param destination the place of destination for comparison.
     * @author Oleksandr Danchenko
     */
    public HasDestination(String destination) {
        this.destination = destination;
    }

    /**
     * Checks if the given flight has the place of destination specified in the constructor.
     *
     * @param flight the flight to be evaluated.
     * @return true if the place of destination matches, false - otherwise.
     * @author Oleksandr Danchenko
     */
    @Override
    public boolean predicate(FlightInfo flight) {
        return flight.getDestination().equals(destination);
    }
}