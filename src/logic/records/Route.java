/*
Author: Oleksandr Danchenko
time spent: 15 minutes
Date: 16 May 2023
version #1
*/

package logic.records;

/**
 * A Route class. Represents the departure and destination location as well as the length of the flight.
 */
public class Route implements DatabaseItem {
    /**
     * A String constant, the destination of Toronto.
     */
    public static final String TORONTO = "Toronto";
    /**
     * A String constant, the destination of Ottawa.
     */
    public static final String OTTAWA = "Ottawa";
    /**
     * A String constant, the destination of Vancouver.
     */
    public static final String VANCOUVER = "Vancouver";
    /**
     * An instance String field, represents the place of departure for the flight.
     */
    private final String departure;
    /**
     * An instance String field, represents the destination of the flight.
     */
    private final String destination;
    /**
     * An instance integer field, represents the time a flight takes.
     */
    private final int flightTime;

    /**
     * A constructor of the class, initializes the object.
     *
     * @param departure   the place of departure of the flight.
     * @param destination the destination of the flight.
     * @param flightTime  the flight time of the flight.
     * @author Oleksandr Danchenko
     */
    public Route(String departure, String destination, int flightTime) {
        this.departure = departure;
        this.destination = destination;
        this.flightTime = flightTime;
    }

    /**
     * A getter method for the departure.
     *
     * @return the place of departure of the flight.
     * @author Oleksandr Danchenko
     */
    public String getDeparture() {
        return departure;
    }

    /**
     * A getter method for the destination.
     *
     * @return the destination of the flight.
     * @author Oleksandr Danchenko
     */
    public String getDestination() {
        return destination;
    }

    /**
     * A getter method for the flight time.
     *
     * @return the flight time of the flight.
     * @author Oleksandr Danchenko
     */
    public int getFlightTime() {
        return flightTime;
    }

    /**
     * A method which generates a database String representation of the Route object.
     *
     * @return a database String representation of the object.
     * @author Oleksandr Danchenko
     */
    @Override
    public String data() {
        return departure + "=" + destination + "=" + flightTime;
    }
}
