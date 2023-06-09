/*
Author: Oleksandr Danchenko
time spent: 15 minutes
Date: 18 May 2023
version #4
Changes: Added a method that constructs a file path to the imported images in the resources folder
       Date: 25 May 2023
       time spent: 7 minutes
Changes: Changed the location of the database and image files, updated the class to construct the correct paths.
       Date: 26 May 2023
       time spent: 15 minutes
Changes: Added the getManualFile() method/
        Date: 8 June 2023
        time spent: 3 minutes
        Author: Oleksandr Danchenko
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
     * Citation: https://docs.oracle.com/javase/8/docs/api/java/lang/System.html#getProperty-java.lang.String-
     *      The method getProperty() of the System class is used with the key "user.dir"
     *      to get the absolute path to the root of the project. This is needed because the path is slightly different
     *      if the program is launched using jGRASP or IntelliJ IDEA and should be corrected in order for a correct path
     *      to be constructed regardless of the IDE used to launch the program.
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

    /**
     * Constructs the file path to the user manual file.
     *
     * @return the file of the user manual.
     * @author Oleksandr Danchenko
     */
    public static File getManualFile() {
        return new File(String.join(File.separator, new String[]{getDefaultPath(), "User Manual.pdf"}));
    }
}

