/*
Author: Oleksandr Danchenko
time spent: 20 minutes
version #1
*/

package data_record;

public class FlightInfo {
    /**
     * A field representing the route of the flight.
     */
    private Route route;
    /**
     * The name of the file the seating information is going to be stored in.
     */
    private String fileName;
    /**
     * A flag indicating whether the flight is cancelled.
     */
    private boolean isCancelled;
    /**
     * The number of seats left on the flight.
     */
    private int seatsLeft;
    /**
     * The time of departure of the flight.
     */
    private int departureTime;
    /**
     * The date of the flight.
     */
    private String date;

    /**
     * Constructs a FlightInfo object with the specified information.
     *
     * @param route         The Route object representing the flight route.
     * @param fileName      The name of the file associated with the flight.
     * @param isCancelled   A flag indicating whether the flight is cancelled.
     * @param seatsLeft     The number of seats left on the flight.
     * @param departureTime The departure time of the flight.
     * @param date          The date of the flight.
     * @author Oleksandr Danchenko
     */
    public FlightInfo(Route route, String fileName, boolean isCancelled, int seatsLeft, int departureTime, String date) {
        this.route = route;
        this.fileName = fileName;
        this.isCancelled = isCancelled;
        this.seatsLeft = seatsLeft;
        this.departureTime = departureTime;
        this.date = date;
    }

    /**
     * Returns the Route object representing the flight route.
     *
     * @return The Route object representing the flight route.
     * @author Oleksandr Danchenko
     */
    public Route getRoute() {
        return route;
    }

    /**
     * Returns the departure airport of the flight.
     *
     * @return The departure airport of the flight.
     * @author Oleksandr Danchenko
     */
    public String getDeparture() {
        return route.getDeparture();
    }

    /**
     * Returns the destination airport of the flight.
     *
     * @return The destination airport of the flight.
     * @author Oleksandr Danchenko
     */
    public String getDestination() {
        return route.getDestination();
    }

    /**
     * Returns the flight time in minutes.
     *
     * @return The flight time in minutes.
     * @author Oleksandr Danchenko
     */
    public int getFlightTime() {
        return route.getFlightTime();
    }

    /**
     * Returns the name of the file associated with the flight.
     *
     * @return The name of the file associated with the flight.
     * @author Oleksandr Danchenko
     */
    public String getFileName() {
        return fileName;
    }

    /**
     * Checks if the flight is cancelled.
     *
     * @return true if the flight is cancelled, false otherwise.
     * @author Oleksandr Danchenko
     */
    public boolean isCancelled() {
        return isCancelled;
    }

    /**
     * Returns the number of seats left on the flight.
     *
     * @return The number of seats left on the flight.
     * @author Oleksandr Danchenko
     */
    public int getSeatsLeft() {
        return seatsLeft;
    }

    /**
     * Returns the departure time of the flight.
     *
     * @return The departure time of the flight.
     * @author Oleksandr Danchenko
     */
    public int getDepartureTime() {
        return departureTime;
    }

    /**
     * Returns the date of the flight.
     *
     * @return The date of the flight.
     * @author Oleksandr Danchenko
     */
    public String getDate() {
        return date;
    }
}

