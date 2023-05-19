/*
Author: Oleksandr Danchenko
time spent: 5 minutes
version #1
*/

package logic.filtering;

import logic.data_record.FlightInfo;

/**
 * A class, implementation of the FlightFilter class, used to check a flight for a specific route.
 *
 * @author Oleksandr Danchenko
 */
public class HasRoute implements FlightFilter {
    /**
     * The place of departure to which the flight departure is compared.
     */
    private final String departure;
    /**
     * The place of destination to which the flight destination is compared.
     */
    private final String destination;

    /**
     * A constructor that initializes the route for comparison.
     *
     * @param destination the place of destination for comparison.
     * @param departure   the place of departure for comparison.
     * @author Oleksandr Danchenko
     */
    public HasRoute(String departure, String destination) {
        this.departure = departure;
        this.destination = destination;
    }

    /**
     * Checks if the departure and destination of the specified flight match the ones specified in the constructor.
     *
     * @param flight the flight to be evaluated.
     * @return true if the departure and the destination match with the flight, false - otherwise.
     * @author Oleksandr Danchenko
     */
    @Override
    public boolean predicate(FlightInfo flight) {
        return flight.getDeparture().equals(departure) && flight.getDestination().equals(destination);
    }
}
