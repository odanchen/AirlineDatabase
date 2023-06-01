/*
Author: Oleksandr Danchenko
time spent: 40 minutes
Date 16 May 2023
version #1
*/

package logic.data_record;

public class FlightInfo implements DatabaseItem {
    /**
     * A field representing the route of the flight.
     */
    private final Route route;
    /**
     * The name of the file the seating information is going to be stored in.
     */
    private final String fileName;
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
    private final int departureTime;
    /**
     * The date of the flight.
     */
    private final Date date;

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
    public FlightInfo(Route route, String fileName, boolean isCancelled, int seatsLeft, int departureTime, Date date) {
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
     * Interprets the time and returns a formatted string representation.
     *
     * @return the interpreted departure time string.
     * @author Oleksandr Danchenko
     */
    public String getUserDepartureTime() {
        if (departureTime <= 60 * 13) return departureTime / 60 + ":" + fixTime(departureTime % 60) + " a.m.";
        return (departureTime % (60 * 12)) / 60 + ":" + fixTime(departureTime % 60) + " p.m.";
    }

    /**
     * Fixes the time format by adding a leading zero if the time value is less than 10.
     *
     * @param time the time value.
     * @return the fixed time string.
     * @author Oleksandr Danchenko
     */
    private String fixTime(int time) {
        if (time < 10) return "0" + time;
        return String.valueOf(time);
    }

    /**
     * A method that is called when an empty seat is booked.
     * Decrements the number of seats left.
     *
     * @author Oleksandr Danchenko
     */
    public void bookSeat() {
        seatsLeft--;
    }

    /**
     * A method that is called when a seat is freed.
     * Increments the number of seats left.
     *
     * @author Oleksandr Danchenko
     */
    public void freeSeat() {
        seatsLeft++;
    }

    /**
     * Returns the date of the flight.
     *
     * @return The date of the flight.
     * @author Oleksandr Danchenko
     */
    public Date getDate() {
        return date;
    }

    /**
     * Cancels the flight.
     *
     * @author OleksandrDanchenko
     */
    public void cancel() {
        isCancelled = true;
    }

    /**
     * Renews the flight.
     *
     * @author Oleksandr Danchenko
     */
    public void renew() {
        isCancelled = false;
    }

    /**
     * Converts the flight info into a String in the way it is supposed to be represented in a database.
     *
     * @return a database String representation of the flight inf object.
     * @author Oleksandr Danchenko
     */
    @Override
    public String data() {
        return route.data() + "=" + fileName + "=" + isCancelled + "=" + seatsLeft + "=" + departureTime + "=" + date.data();
    }
}
