/*
Author: Oleksandr Danchenko
time spent: 15 minutes
version #1
*/


package data_checking;

/**
 * The DayMonthCalculator class provides methods to calculate the number of days in a given month and year.
 * @author Oleksandr Danchenko
 */
public class DayMonthCalculator {
    /**
     * Private constructor to prevent instantiation of the PhoneNumberChecker class.
     *
     * @author Oleksandr Danchenko
     */
    private DayMonthCalculator() {

    }

    /**
     * Gets the number of days in a month for a given year.
     *
     * @param month The month (1-12).
     * @param year  The year.
     * @return The number of days in the specified month and year.
     * @author Oleksandr Danchenko
     */
    public static int getDayMonth(int month, int year) {
        switch (month) {
            case 1:
            case 3:
            case 5:
            case 7:
            case 8:
            case 10:
            case 12:
                return 31;
            case 4:
            case 6:
            case 9:
            case 11:
                return 30;
            default:
                return february(year);
        }
    }

    /**
     * Checks if a given year is a leap year.
     *
     * @param year The year.
     * @return true if the year is a leap year, false otherwise.
     * @author Oleksandr Danchenko
     */
    private static boolean isLeap(int year) {
        return (year % 4 == 0 && year % 100 != 0) || year % 400 == 0;
    }

    /**
     * Gets the number of days in February for a given year.
     *
     * @param year The year.
     * @return The number of days in February for the specified year.
     * @author Oleksandr Danchenko
     */
    private static int february(int year) {
        if (isLeap(year)) return 29;
        return 28;
    }
}
