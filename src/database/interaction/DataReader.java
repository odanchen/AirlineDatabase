/*
Author: Oleksandr Danchenko
time spent: 20 minutes
version #1
 */

package database.interaction;

import data_checking.DataInterpreter;
import data_checking.FilePathConstructor;
import data_record.Calendar;
import data_record.FlightInfo;
import data_record.Seat;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

/**
 * The DataReader class provides methods to read and retrieve data from files.
 *
 * @author Oleksandr Danchenko
 */
public class DataReader {
    /**
     * A private constructor to prevent instantiation.
     *
     * @author Oleksandr Danchenko
     */
    private DataReader() {

    }

    /**
     * Retrieves a Calendar object populated with flight information from the database.
     *
     * @return a Calendar object containing flight information.
     * @throws RuntimeException if an error occurs while reading the file.
     * @author Oleksandr Danchenko
     */
    public static Calendar getCalendar() {
        List<FlightInfo>[] calendar = new List[Calendar.NUMBER_OF_DAYS];
        try {
            BufferedReader reader = new BufferedReader(new FileReader(FilePathConstructor.getFlightListFile()));
            String line = reader.readLine();
            while (line != null) {
                FlightInfo info = DataInterpreter.getFlightInfo(line);
                int day = info.getDate().getDay() - 1;
                if (calendar[day] == null) {
                    calendar[day] = new ArrayList<>();
                }
                calendar[day].add(info);
                line = reader.readLine();
            }
            reader.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return new Calendar(calendar);
    }

    /**
     * Retrieves an array of Seat objects populated with seating information from the database.
     *
     * @param filename the name of the file containing seating information.
     * @return an array of Seat objects representing the seating information.
     * @throws RuntimeException if an error occurs while reading the file.
     * @author Oleksnadr Danchenko
     */
    public static Seat[] getSeating(String filename) {
        Seat[] seating = new Seat[10];
        try {
            BufferedReader reader = new BufferedReader(new FileReader(FilePathConstructor.getSeatFile(filename)));
            String line = reader.readLine();
            while (line != null) {
                Seat info = DataInterpreter.getSeat(line);
                int seatNumber = info.getNumber() - 1;
                seating[seatNumber] = info;
                line = reader.readLine();
            }
            reader.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return seating;
    }
}
