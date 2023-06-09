/*
Author: Oleksandr Danchenko
time spent: 20 minutes
Date: 16 May 2023
version #2
changes: changed the class to be a descendant of the DataChecker abstract class.
        Date : 22 May 2023.
        time spent : 10 minutes.
*/

package logic.checking;

/**
 * The PhoneNumberChecker class provides methods to validate and check the correctness of a phone number.
 *
 * @author Oleksandr Danchenko
 */
public class PhoneNumberChecker extends DataChecker {
    /**
     * The message, when the phone number does not contain enough digits.
     */
    private static final String NOT_ENOUGH_DIGITS = "Less than 10 digits entered";
    /**
     * The message, when the phone number contains an excessive number of digits.
     */
    private static final String TOO_MUCH_DIGITS = "More than 10 digits entered";
    /**
     * The message, when the phone number is not in a correct format.
     */
    private static final String WRONG_FORMAT = "Wrong format";

    /**
     * A constructor, initializes the object.
     *
     * @param number the number to be checked.
     * @author Oleksandr Danchenko
     */
    public PhoneNumberChecker(String number) {
        super(number);
    }

    /**
     * Gets the error message for a phone number.
     *
     * @return The error message indicating the issue with the phone number.
     * @author Oleksandr Danchenko
     */
    public String getErrorMessage() {
        if (data.equals("")) return EMPTY;
        if (areIllegalSymbolsPresent()) return ILLEGAL_SYMBOLS;
        if (countDigits(data) < 10) return NOT_ENOUGH_DIGITS;
        if (countDigits(data) > 10) return TOO_MUCH_DIGITS;
        if (!(checkFormatOne(data) || checkFormatTwo(data) || checkFormatThree(data))) return WRONG_FORMAT;
        return CORRECT;
    }

    /**
     * Counts the number of digits in a phone number.
     *
     * @param number The phone number to count digits in.
     * @return The number of digits in the phone number.
     * @author Oleksandr Danchenko
     */
    private static int countDigits(String number) {
        int cnt = 0;
        for (int i = 0; i < number.length(); i++) {
            if (number.charAt(i) >= '0' && number.charAt(i) <= '9') cnt++;
        }
        return cnt;
    }

    /**
     * Checks if a character in a phone number is a valid phone number character.
     *
     * @param c The character to check.
     * @return true if the character is a valid phone number character, false otherwise.
     * @author Oleksandr Danchenko
     */
    protected boolean isIllegalSymbol(char c) {
        return (c < '0' || c > '9') && c != ' ' && c != '-';
    }

    /**
     * Checks if a phone number matches the format "XXXXXXXXXX" (10 digits).
     *
     * @param number The phone number to check.
     * @return true if the phone number matches the format, false otherwise.
     * @author Oleksandr Danchenko
     */
    private boolean checkFormatOne(String number) {
        return number.length() == 10;
    }

    /**
     * Checks if a phone number matches the format "XXX XXX XXXX" (12 digits with spaces at positions 3 and 7).
     *
     * @param number The phone number to check.
     * @return true if the phone number matches the format, false otherwise.
     * @author Oleksandr Danchenko
     */
    private boolean checkFormatTwo(String number) {
        return number.length() == 12 && number.charAt(3) == ' ' && number.charAt(7) == ' ';
    }

    /**
     * Checks if a phone number matches the format "XXX-XXX-XXXX" (12 digits with dashes at positions 3 and 7).
     *
     * @param number The phone number to check.
     * @return true if the phone number matches the format, false otherwise.
     * @author Oleksandr Danchenko
     */
    private boolean checkFormatThree(String number) {
        return number.length() == 12 && number.charAt(3) == '-' && number.charAt(7) == '-';
    }
}

