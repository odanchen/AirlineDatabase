/*
Author: Oleksandr Danchenko
time spent: 25 minutes
version #1
*/

package logic.data_checking;

/**
 * The NameChecker class extends the DataChecker class and provides validation and error checking functionality for names.
 *
 * @author Oleksandr Danchenko
 */
public class NameChecker extends DataChecker {
    /**
     * The array of delimiters used for name validation.
     */
    private static final char[] delimiters = {' ', '\'', '-', '_'};

    /**
     * Error message constant for multiple delimiters found in a row.
     */
    private static final String MULTIPLE_DELIMITERS = "Multiple delimiters found in a row";

    /**
     * Error message constant for a name ending with a delimiter.
     */
    private static final String DELIMITER_ENDING = "The name ends with a delimiter";

    /**
     * Error message constant for an uncapitalized name (first letter is not capitalized).
     */
    private static final String UNCAPITALIZED_NAME = "The first letter of the name is not capitalized";

    /**
     * Error message constant for a name starting with a delimiter.
     */
    private static final String DELIMITER_BEGINNING = "The name starts with a delimiter";

    /**
     * Error message constant for a name starting with a digit.
     */
    private static final String DIGIT_BEGINNING = "The first symbol of the name is a digit";

    /**
     * Constructs a NameChecker object with the specified name.
     *
     * @param name the name to be checked
     * @author Oleksandr Danchenko
     */
    public NameChecker(String name) {
        super(trim(name));
    }

    /**
     * Gets the error message indicating the validity of the name.
     *
     * @return the error message, or "CORRECT" if the name is valid
     * @author Oleksandr Danchenko
     */
    @Override
    public String getErrorMessage() {
        if (data.equals("")) return EMPTY;
        if (areIllegalSymbolsPresent()) return ILLEGAL_SYMBOLS;
        for (int i = 1; i < data.length(); i++) {
            if (isDelimiter(data.charAt(i)) && isDelimiter(data.charAt(i - 1))) return MULTIPLE_DELIMITERS;
        }
        if (isDelimiter(data.charAt(data.length() - 1))) return DELIMITER_ENDING;
        if (isDelimiter(data.charAt(0))) return DELIMITER_BEGINNING;
        for (int i = 0; i < data.length(); i++) {
            if (i == 0 || isDelimiter(data.charAt(i - 1))) {
                if (data.charAt(i) >= 'a' && data.charAt(i) <= 'z') return UNCAPITALIZED_NAME;
                else if (data.charAt(i) >= '0' && data.charAt(i) <= '9') return DIGIT_BEGINNING;
            }
        }
        return CORRECT;
    }

    /**
     * Checks if a character is an illegal symbol for a name.
     *
     * @param c the character to be checked
     * @return true if the character is an illegal symbol, false otherwise
     * @author Oleksandr Danchenko
     */
    @Override
    protected boolean isIllegalSymbol(char c) {
        return (c < 'a' || c > 'z') && (c < 'A' || c > 'Z') && (c < '0' || c > '9') && c != ' ' && c != '\'' &&
                c != '-' && c != '_';
    }

    /**
     * Trims the name by removing leading and trailing spaces.
     *
     * @param name the name to be trimmed
     * @return the trimmed name
     * @author Oleksandr Danchenko
     */
    private static String trim(String name) {
        if (name.equals("")) return name;
        int left = 0, right = name.length();
        while (left < name.length() && name.charAt(left) == ' ') left++;
        while (right > 0 && name.charAt(right - 1) == ' ') right--;
        if (right <= left) return "";
        return name.substring(left, right);
    }

    /**
     * Checks if a character is a delimiter.
     *
     * @param c the character to be checked
     * @return true if the character is a delimiter, false otherwise
     * @author Oleksandr Danchenko
     */
    private boolean isDelimiter(char c) {
        for (char del : delimiters) {
            if (c == del) return true;
        }
        return false;
    }
}
