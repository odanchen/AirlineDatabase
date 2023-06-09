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
 * The EmailChecker class provides methods to validate and check the correctness of an email address.
 *
 * @author Oleksandr Danchenko
 */
public class EmailChecker extends DataChecker {
    /**
     * The message, when the email does not contain enough @ symbols.
     */
    public static final String NO_AT_SYMBOLS = "No @ symbols found";
    /**
     * The message, when the email contains multiple @ symbols.
     */
    public static final String MULTIPLE_AT_SYMBOLS = "Multiple @ symbols found";
    /**
     * The message, when the email does not have a domain.
     */
    public static final String DOMAIN_ABSENT = "Domain is absent from the email";
    /**
     * The message, when the email does not have a username
     */
    public static final String USERNAME_ABSENT = "Username is absent from the email";

    /**
     * A constructor, initializes the object.
     *
     * @param email the email to be checked.
     * @author Oleksandr Danchenko
     */
    public EmailChecker(String email) {
        super(email);
    }

    /**
     * Gets the error message for an email address.
     *
     * @return The error message indicating the issue with the email address.
     * @author Oleksandr Danchenko
     */
    @Override
    public String getErrorMessage() {
        if (data.equals("")) return EMPTY;
        if (areIllegalSymbolsPresent()) return ILLEGAL_SYMBOLS;
        if (countAtSymbols() == 0) return NO_AT_SYMBOLS;
        if (countAtSymbols() > 1) return MULTIPLE_AT_SYMBOLS;
        if (data.charAt(0) == '@') return USERNAME_ABSENT;
        if (data.charAt(data.length() - 1) == '@') return DOMAIN_ABSENT;
        return CORRECT;
    }

    /**
     * Counts the number of '@' symbols in an email address.
     *
     * @return The number of '@' symbols in the email address.
     * @author Oleksandr Danchenko
     */
    private int countAtSymbols() {
        int atCount = 0;
        for (int i = 0; i < data.length(); i++) {
            if (data.charAt(i) == '@') atCount++;
        }
        return atCount;
    }

    /**
     * Checks if a character in an email address is a valid email character.
     *
     * @param c the character to be checked.
     * @return true if the character is a valid email character, false otherwise.
     * @author Oleksandr Danchenko
     */
    @Override
    protected boolean isIllegalSymbol(char c) {
        return (c < 'a' || c > 'z') && (c < 'A' || c > 'Z') && (c < '0' || c > '9') && c != '@' && c != '-' &&
                c != '_' && c != '.' && c != '~' && c != '+';
    }
}

