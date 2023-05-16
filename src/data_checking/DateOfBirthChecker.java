/*
Author: Oleksandr Danchenko
time spent: 20 minutes
version #1
*/

package data_checking;

/**
 * The DateOfBirthChecker class provides methods to validate and check the correctness of a date of birth.
 *
 * @author Oleksandr Danchenko
 */
public class DateOfBirthChecker {
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
     * Private constructor to prevent instantiation of the DateOfBirthChecker class.
     *
     * @author Oleksandr Danchenko
     */
    private DateOfBirthChecker() {

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
        if (isNonexistentDate(dateOfBirth)) return NONEXISTENT_DATE;
        return CORRECT;
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
     * Checks if a date of birth and exists.
     *
     * @param dateOfBirth The date of birth to check.
     * @return true if the date of birth exists, false otherwise.
     * @author Oleksandr Danchenko
     */
    private static boolean isNonexistentDate(String dateOfBirth) {
        int day = Integer.parseInt(dateOfBirth.split("/")[0]);
        int month = Integer.parseInt(dateOfBirth.split("/")[1]);
        int year = Integer.parseInt(dateOfBirth.split("/")[2]);
        return month == 0 || month > 12 || day == 0 || day > DayMonthCalculator.getDayMonth(month, year);
    }
}

