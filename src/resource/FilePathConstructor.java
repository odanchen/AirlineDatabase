/*
Author: Oleksandr Danchenko
time spent: 15 minutes
Date: 18 May 2023
version #3
Changes: Added a method that constructs a file path to the imported images in the resources folder
       Date: 25 May 2023
       time spent: 7 minutes
Changes: Changed the location of the database and image files, updated the class to construct the correct paths.
       Date: 26 May 2023
       time spent: 15 minutes
 */

package resource;

import java.io.File;

/**
 * The resource.FilePathConstructor class provides methods to construct file paths for flight and seating data files.
 *
 * @author Oleksandr Danchenko
 */
public class FilePathConstructor {
    /**
     * The extension of a seating information file.
     */
    public static final String SEATING_FILE_EXT = ".seat";
    /**
     * The name of the file that stores the basic information about all flights.
     */
    public static final String FLIGHT_LIST_FILENAME = "flights.flt";

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
        return new File(String.join(File.separator, new String[]{getDefaultPath(), "database", FLIGHT_LIST_FILENAME}));
    }

    /**
     * Constructs the file path for a seating file.
     *
     * @param filename the name of the seating file (without extension).
     * @return a File object representing the seating file.
     * @author Oleksandr Danchenko
     */
    public static File getSeatFile(String filename) {
        return new File(String.join(File.separator, new String[]{getDefaultPath(), "database", "seating", filename + SEATING_FILE_EXT}));
    }

    /**
     * Creates the unchangeable part of the relative path to a file in the database
     *
     * @return the unchangeable part of the relative path to a file in the database
     * @author Oleksandr Danchenko
     */
    private static String getDefaultPath() {
        if (System.getProperty("user.dir").endsWith("src")) {
            return String.join(File.separator, new String[]{"..", "resources"});
        }
        return "resources";
    }

    /**
     * Constructs a file path to an image with the specified name.
     *
     * @param filename the file name of the required image.
     * @return a file that represents the image file.
     */
    public static File getImageFile(String filename) {
        return new File(String.join(File.separator, new String[]{getDefaultPath(), "images", filename + ".png"}));
    }
}
