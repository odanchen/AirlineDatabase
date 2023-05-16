/*
Author: Oleksandr Danchenko
time spent: 25 minutes
version #1
*/

package data_checking;

/**
 * The PhoneNumberChecker class provides methods to validate and check the correctness of a phone number.
 *
 * @author Oleksandr Danchenko
 */
public class PhoneNumberChecker {
    /**
     * The message when the entered phone number is correct.
     */
    private static final String CORRECT = "Correct";
    /**
     * The message when the phone number contains illegal symbols.
     */
    private static final String ILLEGAL_SYMBOLS = "Illegal symbols found";
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
     * Private constructor to prevent instantiation of the PhoneNumberChecker class.
     *
     * @author Oleksandr Danchenko
     */
    private PhoneNumberChecker() {

    }

    /**
     * Checks if a phone number is correct.
     *
     * @param number The phone number to check.
     * @return true if the phone number is correct, false otherwise.
     * @author Oleksandr Danchenko
     */
    public boolean isPhoneNumberCorrect(String number) {
        return getErrorMessage(number).equals(CORRECT);
    }

    /**
     * Gets the error message for a phone number.
     *
     * @param number The phone number to check.
     * @return The error message indicating the issue with the phone number.
     * @author Oleksandr Danchenko
     */
    public String getErrorMessage(String number) {
        for (int i = 0; i < number.length(); i++) {
            if (!isPhoneChar(number.charAt(i))) return ILLEGAL_SYMBOLS;
        }
        if (countDigits(number) < 10) return NOT_ENOUGH_DIGITS;
        if (countDigits(number) > 10) return TOO_MUCH_DIGITS;
        if (!(checkFormatOne(number) || checkFormatTwo(number) || checkFormatThree(number))) return WRONG_FORMAT;
        return CORRECT;
    }

    /**
     * Counts the number of digits in a phone number.
     *
     * @param number The phone number to count digits in.
     * @return The number of digits in the phone number.
     * @author Oleksandr Danchenko
     */
    private int countDigits(String number) {
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
    private boolean isPhoneChar(char c) {
        return (c >= '0' && c <= '9') || c == ' ' || c == '-';
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

