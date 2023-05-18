package data_checking;

import java.io.File;

public class FilePathConstructor {
    public static final String SEATING_FILE_EXT = ".seat";

    public static File getFlightListFile() {
        return new File(String.join(File.separator, new String[]{"src", "database", "flights.flt"}));
    }

    public static File getSeatFile(String filename) {
        return new File(String.join(File.separator, new String[]{"src", "database", "seating", filename + SEATING_FILE_EXT}));
    }
}
