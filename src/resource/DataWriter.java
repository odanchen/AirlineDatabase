/*
Author: Oleksandr Danchenko
time spent: 20 minutes
Date: 18 May 2023
version #1
*/

package resource;

import logic.records.Calendar;
import logic.records.FlightInfo;
import logic.records.Seat;

import java.io.BufferedWriter;
import java.io.FileWriter;

/**
 * The resource.DataWriter class provides static methods to update flight list and seating information in the database.
 *
 * @author Oleksandr Danchenko
 */
public class DataWriter {
    /**
     * An empty constructor to prevent instantiation of the class.
     */
    private DataWriter() {
    }

    /**
     * Updates the flight list file with the data from the provided calendar.
     *
     * @param calendar the calendar containing the flight information.
     * @author Oleksandr Dacnehnko
     */
    public static void updateFlightList(Calendar calendar) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(FilePathConstructor.getFlightListFile()));
            for (int i = 1; i <= Calendar.NUMBER_OF_DAYS; i++) {
                for (FlightInfo info : calendar.getDay(i)) {
                    writer.write(info.data() + '\n');
                }
            }
            writer.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Updates the seating file with the data from the provided seating information.
     *
     * @param seating  the seating information.
     * @param filename the name of the seating file to update.
     * @author Oleksandr Danchenko
     */
    public static void updateSeatingInformation(Seat[] seating, String filename) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(FilePathConstructor.getSeatFile(filename)));
            for (Seat seat : seating) {
                writer.write(seat.data() + '\n');
            }
            writer.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}

