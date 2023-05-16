/*
Author: Oleksandr Danchenko
time spent: 20 minutes
version #1
*/

package data_checking;

/**
 * The EmailChecker class provides methods to validate and check the correctness of an email address.
 *
 * @author Oleksandr Danchenko
 */
public class EmailChecker {
    /**
     * The message when the entered email is correct.
     */
    private static final String CORRECT = "Correct";
    /**
     * The message when the email contains illegal symbols.
     */
    private static final String ILLEGAL_SYMBOLS = "Illegal symbols found";
    /**
     * The message, when the email does not contain enough @ symbols.
     */
    private static final String NO_AT_SYMBOLS = "No @ symbols found";
    /**
     * The message, when the email contains multiple @ symbols.
     */
    private static final String MULTIPLE_AT_SYMBOLS = "Multiple @ symbols found";
    /**
     * The message, when the email does not have a domain.
     */
    private static final String DOMAIN_ABSENT = "Domain is absent from the email";
    /**
     * The message, when the email does not have a username
     */
    private static final String USERNAME_ABSENT = "Username is absent from the email";

    /**
     * Private constructor to prevent instantiation of the EmailChecker class.
     *
     * @author Oleksandr Danchenko
     */
    private EmailChecker() {

    }

    /**
     * Checks if an email address is correct.
     *
     * @param email The email address to check.
     * @return true if the email address is correct, false otherwise.
     * @author Oleksandr Danchenko
     */
    public static boolean isEmailCorrect(String email) {
        return getErrorMessage(email).equals(CORRECT);
    }

    /**
     * Gets the error message for an email address.
     *
     * @param email The email address to check.
     * @return The error message indicating the issue with the email address.
     * @author Oleksandr Danchenko
     */
    public static String getErrorMessage(String email) {
        for (int i = 0; i < email.length(); i++) {
            if (!isEmailChar(email.charAt(i))) return ILLEGAL_SYMBOLS;
        }
        if (countAtSymbols(email) == 0) return NO_AT_SYMBOLS;
        if (countAtSymbols(email) > 1) return MULTIPLE_AT_SYMBOLS;
        if (email.charAt(0) == '@') return USERNAME_ABSENT;
        if (email.charAt(email.length() - 1) == '@') return DOMAIN_ABSENT;
        return CORRECT;
    }

    /**
     * Counts the number of '@' symbols in an email address.
     *
     * @param email The email address to count '@' symbols in.
     * @return The number of '@' symbols in the email address.
     * @author Oleksandr Danchenko
     */
    private static int countAtSymbols(String email) {
        int atCount = 0;
        for (int i = 0; i < email.length(); i++) {
            if (email.charAt(i) == '@') atCount++;
        }
        return atCount;
    }

    /**
     * Checks if a character in an email address is a valid email character.
     *
     * @param c The character to check.
     * @return true if the character is a valid email character, false otherwise.
     * @author Oleksandr Danchenko
     */
    private static boolean isEmailChar(char c) {
        return (c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z') || (c >= '0' && c <= '9') || c == '@' || c == '-' ||
                c == '_' || c == '.' || c == '~' || c == '+';
    }
}

