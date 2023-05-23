/*
Author: Oleksandr Danchenko
time spent: 15 minutes
Date: 18 May 2023
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
        return new File(String.join(File.separator, new String[]{getDefaultPath(), "flights.flt"}));
    }

    /**
     * Constructs the file path for a seating file.
     *
     * @param filename the name of the seating file (without extension).
     * @return a File object representing the seating file.
     * @author Oleksandr Danchenko
     */
    public static File getSeatFile(String filename) {
        return new File(String.join(File.separator, new String[]{getDefaultPath(), "seating", filename + SEATING_FILE_EXT}));
    }

    /**
     * Creates the unchangeable part of the relative path to a file in the database
     *
     * @return the unchangeable part of the relative path to a file in the database
     * @author Oleksandr Danchenko
     */
    private static String getDefaultPath() {
        if (System.getProperty("user.dir").endsWith("src")) {
            return String.join(File.separator, new String[]{"database", "data"});
        }
        return String.join(File.separator, new String[]{"src", "database", "data"});
    }
}

