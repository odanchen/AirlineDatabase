/*
Author: Oleksandr Danchenko
time spent: 15 minutes
version #1
*/

package logic.data_record;

/**
 * The Flight class represents a flight, which consists of flight information and seating data.
 *
 * @author Oleksandr Danchenko
 */
public class Flight {
    /**
     * The flight information about the flight.
     */
    private final FlightInfo flightInfo;
    /**
     * The seating information of the flight.
     */
    private final Seat[] seating;

    /**
     * Constructs a Flight object with the specified flight information and seating data.
     *
     * @param flightInfo the flight information.
     * @param seating    the seating data.
     * @author Oleksandr Danchenko
     */
    public Flight(FlightInfo flightInfo, Seat[] seating) {
        this.flightInfo = flightInfo;
        this.seating = seating;
    }

    /**
     * Returns the flight information.
     *
     * @return the flight information.
     * @author Oleksandr Danchenko
     */
    public FlightInfo getFlightInfo() {
        return flightInfo;
    }

    /**
     * Returns the seating data.
     *
     * @return the seating data.
     * @author Oleksandr Danchenko
     */
    public Seat[] getSeating() {
        return seating;
    }

    /**
     * Returns the filename associated with the flight.
     *
     * @return the filename.
     * @author Oleksandr Danchenko
     */
    public String getFilename() {
        return flightInfo.getFileName();
    }

    /**
     * Returns the departure airport code.
     *
     * @return the departure airport code.
     * @author Oleksandr Danchenko
     */
    public String getDeparture() {
        return flightInfo.getDeparture();
    }

    /**
     * Returns the destination airport code.
     *
     * @return the destination airport code.
     * @author Oleksandr Danchenko
     */
    public String getDestination() {
        return flightInfo.getDestination();
    }

    /**
     * Returns the date of the flight.
     *
     * @return the date of the flight.
     * @author Oleksandr Danchenko
     */
    public Date getDate() {
        return flightInfo.getDate();
    }

    /**
     * Returns the user-friendly departure time.
     *
     * @return the user-friendly departure time.
     * @author Oleksandr Danchenko
     */
    public String getUserDepartureTime() {
        return flightInfo.getUserDepartureTime();
    }

    public void bookSeat(Seat seat, Person passenger) {
        if (seat.isEmpty()) flightInfo.bookSeat();
        seat.setPassenger(passenger);
    }

    public void cancelSeat(Seat seat) {
        flightInfo.cancelSeat();
        seat.setPassenger(null);
    }
}

