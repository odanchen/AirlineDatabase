package logic.data_record;

public class Flight {
    private final FlightInfo flightInfo;
    private final Seat[] seating;

    public Flight(FlightInfo flightInfo, Seat[] seating) {
        this.flightInfo = flightInfo;
        this.seating = seating;
    }

    public FlightInfo getFlightInfo() {
        return flightInfo;
    }

    public Seat[] getSeating() {
        return seating;
    }

    public String getFilename() {
        return flightInfo.getFileName();
    }

    public String getDeparture() {
        return flightInfo.getDeparture();
    }

    public String getDestination() {
        return flightInfo.getDestination();
    }

    public Date getDate() {
        return flightInfo.getDate();
    }

    public String getUserDepartureTime() {
        return flightInfo.getUserDepartureTime();
    }
}
