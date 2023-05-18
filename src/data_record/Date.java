/*
Author: Oleksandr Danchenko
time spent: 30 minutes
version #1
*/

package data_record;

public class Date implements DatabaseItem {
    /**
     * The message when the entered date of birth is correct.
     */
    private static final String CORRECT = "Correct";
    /**
     * The message when the entered date of birth has a wrong length.
     */
    private static final String WRONG_LENGTH = "The entered data is of incorrect length";
    /**
     * The message when the entered date of birth contains illegal symbols.
     */
    private static final String ILLEGAL_SYMBOLS = "Illegal symbols found in the input";
    /**
     * The message when the entered date of birth is in a wrong format.
     */
    private static final String WRONG_FORMAT = "Wrong format";
    /**
     * The message when the entered date does not exist.
     */
    private static final String NONEXISTENT_DATE = "The date entered does not exist";
    /**
     * The day in the current date.
     */
    private final int day;
    /**
     * The month in the current date.
     */
    private final int month;
    /**
     * The year in the current date.
     */
    private final int year;

    /**
     * A constructor of the class, initializes the object
     *
     * @param date a string representation of the date
     * @author Oleksandr Danchenko
     */
    public Date(String date) {
        day = Integer.parseInt(date.split("/")[0]);
        month = Integer.parseInt(date.split("/")[1]);
        year = Integer.parseInt(date.split("/")[2]);
    }

    /**
     * Checks if a date of birth is correct.
     *
     * @param dateOfBirth The date of birth to check.
     * @return true if the date of birth is correct, false otherwise.
     * @author Oleksandr Danchenko
     */
    public static boolean isDateOfBirthCorrect(String dateOfBirth) {
        return getErrorMessage(dateOfBirth).equals(CORRECT);
    }

    /**
     * Gets the error message for a date of birth.
     *
     * @param dateOfBirth The date of birth to check.
     * @return The error message indicating the issue with the date of birth.
     * @author Oleksandr Danchenko
     */
    public static String getErrorMessage(String dateOfBirth) {
        if (dateOfBirth.length() != 10) return WRONG_LENGTH;
        for (int i = 0; i < dateOfBirth.length(); i++) {
            if (!isDateOfBirthSymbol(dateOfBirth.charAt(i))) return ILLEGAL_SYMBOLS;
        }
        if (countSlash(dateOfBirth) != 2 || dateOfBirth.charAt(2) != '/' || dateOfBirth.charAt(5) != '/') {
            return WRONG_FORMAT;
        }
        if (!exists(dateOfBirth)) return NONEXISTENT_DATE;
        return CORRECT;
    }

    /**
     * Checks if the date exists.
     *
     * @return true if the date exists, false - otherwise.
     * @author Oleksandr Danchenko
     */
    private static boolean exists(String date) {
        int day = Integer.parseInt(date.split("/")[0]);
        int month = Integer.parseInt(date.split("/")[1]);
        int year = Integer.parseInt(date.split("/")[2]);
        return month == 0 || month > 12 || day == 0 || day > getDayMonth(month, year);
    }

    /**
     * Counts the number of '/' symbols in a date of birth.
     *
     * @param dateOfBirth The date of birth to count '/' symbols in.
     * @return The number of '/' symbols in the date of birth.
     * @author Oleksandr Danchenko
     */
    private static int countSlash(String dateOfBirth) {
        int cnt = 0;
        for (int i = 0; i < dateOfBirth.length(); i++) {
            if (dateOfBirth.charAt(i) == '/') cnt++;
        }
        return cnt;
    }

    /**
     * Checks if a character in a date of birth is a valid symbol.
     *
     * @param c The character to check.
     * @return true if the character is a valid date of birth symbol, false otherwise.
     * @author Oleksandr Danchenko
     */
    private static boolean isDateOfBirthSymbol(char c) {
        return c == '/' || (c >= '0' && c <= '9');
    }

    /**
     * Gets the number of days in a month for a given year.
     *
     * @param month The month (1-12).
     * @param year  The year.
     * @return The number of days in the specified month and year.
     * @author Oleksandr Danchenko
     */
    private static int getDayMonth(int month, int year) {
        switch (month) {
            case 1:
            case 3:
            case 5:
            case 7:
            case 8:
            case 10:
            case 12: return 31;
            case 4:
            case 6:
            case 9:
            case 11: return 30;
            default: return february(year);
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

    /**
     * Converts a numerical representation of the month into a String representation of the month.
     *
     * @param month the month to be converted.
     * @return a String representation of the specified month.
     * @author Oleksandr Danchenko
     */
    private static String monthToString(int month) {
        switch (month) {
            case 1: return "Jan";
            case 2: return "Feb";
            case 3: return "Mar";
            case 4: return "Apr";
            case 5: return "May";
            case 6: return "Jun";
            case 7: return "Jul";
            case 8: return "Aug";
            case 9: return "Sep";
            case 10: return "Oct";
            case 11: return "Nov";
            default: return "Dec";
        }
    }

    /**
     * Converts the date into a String.
     *
     * @return a String representation of the date.
     * @author Oleksandr Danchenko
     */
    @Override
    public String toString() {
        return day + "/" + monthToString(month) + "/" + year;
    }


    /**
     * Converts the date into a String in the way it is supposed to be represented in a database.
     *
     * @return a database String representation of the date.
     * @author Oleksandr Danchenko
     */
    @Override
    public String data() {
        return day + "/" + month + "/" + year;
    }
}
