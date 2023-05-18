package data_checking;

import data_record.*;

/**
 * The DataInterpreter class provides methods for interpreting data strings and converting them into corresponding objects.
 *
 * @author Oleksandr Danchenko
 */
public class DataInterpreter {
    /**
     * Private constructor to prevent instantiation of the class.
     *
     * @author Oleksandr Danchenko
     */
    private DataInterpreter() {
    }

    /**
     * Retrieves flight information from the given data string and constructs a FlightInfo object.
     *
     * @param info the data string containing flight information
     * @return a FlightInfo object representing the flight information
     * @author Oleksandr Danchenko
     */
    public static FlightInfo getFlightInfo(String info) {
        String[] data = info.split("=");
        return new FlightInfo(new Route(data[0], data[1], Integer.parseInt(data[2])), data[3],
                Boolean.parseBoolean(data[4]), Integer.parseInt(data[5]), Integer.parseInt(data[6]), new Date(data[7]));
    }

    /**
     * Retrieves seat information from the given data string and constructs a Seat object.
     *
     * @param info the data string containing seat information
     * @return a Seat object representing the seat information
     * @author Oleksandr Danchenko
     */
    public static Seat getSeat(String info) {
        String[] data = info.split("=");
        if (data.length == 2)
            return new Seat(Integer.parseInt(data[0]), Integer.parseInt(data[1]));
        return new Seat(Integer.parseInt(data[0]), Integer.parseInt(data[1]),
                new Person(data[2], data[3], new Date(data[4]), data[5], data[6]));
    }
}

