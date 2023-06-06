/*
Author: Oleksandr Danchenko
time spent: 20 minutes
Date: 18 May 2023
version #2
Changes: Added a method to read image files.
    time spent: 8 minutes.
    Date: 25 May 2023
 */

package resource;

import logic.data_record.Calendar;
import logic.data_record.FlightInfo;
import logic.data_record.Seat;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

/**
 * The resource.DataReader class provides methods to read and retrieve data from files.
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
        for (int i = 0; i < calendar.length; i++) calendar[i] = new ArrayList<FlightInfo>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(FilePathConstructor.getFlightListFile()));
            String line = reader.readLine();
            while (line != null) {
                FlightInfo info = DataInterpreter.getFlightInfo(line);
                calendar[info.getDate().getDay() - 1].add(info);
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

    /**
     * Reads an image with the specified file name.
     *
     * Citation: https://docs.oracle.com/javase/8/docs/api/javax/imageio/ImageIO.html#read-java.io.File-
     *      The method read() is used from the ImageIO class to read an image file.
     * @param filename the name of the image file.
     * @return the image stored at that file.
     */
    public static BufferedImage readImage(String filename) {
        try {
            return ImageIO.read(FilePathConstructor.getImageFile(filename));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
