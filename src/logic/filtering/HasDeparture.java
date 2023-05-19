/*
Author: Oleksandr Danchenko
time spent: 7 minutes
version #1
*/

package logic.filtering;

import logic.data_record.FlightInfo;

/**
 * A class, implementation of the FlightFilter class, used to check a flight for a specific place of departure.
 *
 * @author Oleksandr Danchenko
 */
public class HasDeparture implements FlightFilter {
    /**
     * The place of departure to which the flight departure is compared.
     */
    private final String departure;

    /**
     * A constructor that initializes the departure for comparison.
     *
     * @param departure the place of departure for comparison.
     * @author Oleksandr Danchenko
     */
    public HasDeparture(String departure) {
        this.departure = departure;
    }

    /**
     * Checks if the given flight has the place of departure specified in the constructor.
     *
     * @param flight the flight to be evaluated.
     * @return true if the place of departure matches, false - otherwise.
     * @author Oleksandr Danchenko
     */
    @Override
    public boolean predicate(FlightInfo flight) {
        return flight.getDeparture().equals(departure);
    }
}
