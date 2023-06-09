/*
Author: Oleksandr Danchenko
time spent: 25 minutes
Date: 23 May 2023
version #2
Changes: implemented logic to check that the date entered is not in the future and not too far in the past.
    Time spent: 15 minutes.
    Date: 31 May 2023
    Author: Oleksandr Danchenko
*/

package logic.data_checking;

/**
 * A class that inherits from the DataChecker class. Used to verify the correctness of the entered date.
 *
 * @author Oleksandr Danchenko
 */
public class DateChecker extends DataChecker {
    /**
     * The message when the entered date of birth has a wrong length.
     */
    private static final String WRONG_LENGTH = "The entered data is of incorrect length";
    /**
     * The message when the entered date of birth is in a wrong format.
     */
    private static final String WRONG_FORMAT = "Wrong format";
    /**
     * The message when the entered date does not exist.
     */
    private static final String NONEXISTENT_DATE = "The date entered does not exist";
    /**
     * The message when the entered date is yet to come.
     */
    private static final String DATE_IN_FUTURE = "The date entered hasn't yet occurred";

    /**
     * The message when the entered date is too far in the past.
     */
    private static final String DATE_IN_PAST = "The date entered is too far in the past";

    /**
     * A constructor of the class, initializes the object.
     *
     * @param date the date to be checked.
     * @author Oleksandr Danchenko
     */
    public DateChecker(String date) {
        super(date);
    }

    /**
     * Checks the date for correctness and returns the error associated with it.
     *
     * @return the error message for the date.
     * @author Oleksandr Danchenko
     */
    @Override
    public String getErrorMessage() {
        if (data.equals("")) return EMPTY;
        if (data.length() != 10) return WRONG_LENGTH;
        if (areIllegalSymbolsPresent()) return ILLEGAL_SYMBOLS;
        if (countSlash() != 2 || data.charAt(2) != '/' || data.charAt(5) != '/') return WRONG_FORMAT;
        if (!getLegality(data).equals(CORRECT)) return getLegality(data);
        return CORRECT;
    }

    /**
     * Checks if the given symbol is illegal.
     *
     * @param c the character to be checked.
     * @return false if the symbol is not allowed to be used, true - otherwise.
     */
    @Override
    protected boolean isIllegalSymbol(char c) {
        return c != '/' && (c < '0' || c > '9');
    }

    /**
     * Counts the number of '/' symbols in a date of birth.
     *
     * @return The number of '/' symbols in the date of birth.
     * @author Oleksandr Danchenko
     */
    private int countSlash() {
        int cnt = 0;
        for (int i = 0; i < data.length(); i++) {
            if (data.charAt(i) == '/') cnt++;
        }
        return cnt;
    }

    /**
     * Checks if the date exists.
     * Verifies that the date could be someone's date of birth - not in the future and not too far in the past.
     *
     * @param date the date to be checked.
     * @return the error message for the existence of the date.
     * @author Oleksandr Danchenko
     */
    private String getLegality(String date) {
        int day = Integer.parseInt(date.split("/")[0]);
        int month = Integer.parseInt(date.split("/")[1]);
        int year = Integer.parseInt(date.split("/")[2]);
        if (month <= 0 || month > 12 || day <= 0 || day > getDayMonth(month, year)) return NONEXISTENT_DATE;

        if (year > 2023) return DATE_IN_FUTURE;
        if (year == 2023) {
            if (month > 6) return DATE_IN_FUTURE;
            else if (day > 9) return DATE_IN_FUTURE;
        }

        if (year < 1907) return DATE_IN_PAST;
        if (year == 1907) {
            if (month < 3) return DATE_IN_PAST;
            else if (day < 4) return DATE_IN_PAST;
        }
        return CORRECT;
    }

    /**
     * Gets the number of days in a month for a given year.
     *
     * @param month The month (1-12).
     * @param year  The year.
     * @return The number of days in the specified month and year.
     * @author Oleksandr Danchenko
     */
    private int getDayMonth(int month, int year) {
        if (month == 1 || month == 3 || month == 5 || month == 7 || month == 8 || month == 10 || month == 12) return 31;
        if (month == 4 || month == 6 || month == 9 || month == 11) return 30;
        return february(year);
    }

    /**
     * Checks if a given year is a leap year.
     *
     * @param year The year.
     * @return true if the year is a leap year, false otherwise.
     * @author Oleksandr Danchenko
     */
    private boolean isLeap(int year) {
        return (year % 4 == 0 && year % 100 != 0) || year % 400 == 0;
    }

    /**
     * Gets the number of days in February for a given year.
     *
     * @param year The year.
     * @return The number of days in February for the specified year.
     * @author Oleksandr Danchenko
     */
    private int february(int year) {
        if (isLeap(year)) return 29;
        return 28;
    }
}
