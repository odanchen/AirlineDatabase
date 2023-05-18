/*
Author: Oleksandr Danchenko
time spent: 15 minutes
version #1
 */

package database.interaction;

import java.io.File;

/**
 * The FilePathConstructor class provides methods to construct file paths for flight and seating data files.
 *
 * @author Oleksandr Danchenko
 */
public class FilePathConstructor {
    /**
     * The extension of a seating information file.
     */
    public static final String SEATING_FILE_EXT = ".seat";

    /**
     * A private constructor to prevent instantiation.
     *
     * @author Oleksandr Danchenko
     */
    private FilePathConstructor() {

    }

    /**
     * Constructs the file path for the flight list file.
     *
     * @return a File object representing the flight list file.
     * @author Oleksandr Danchenko
     */
    public static File getFlightListFile() {
        return new File(String.join(File.separator, new String[]{"src", "database", "data", "flights.flt"}));
    }

    /**
     * Constructs the file path for a seating file.
     *
     * @param filename the name of the seating file (without extension).
     * @return a File object representing the seating file.
     * @author Oleksandr Danchenko
     */
    public static File getSeatFile(String filename) {
        return new File(String.join(File.separator, new String[]{"src", "database", "data", "seating", filename + SEATING_FILE_EXT}));
    }
}

