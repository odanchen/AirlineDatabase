/*
Author: Oleksandr Danchenko
time spent: 25 minutes
version #1
*/

package logic.data_checking;

public abstract class DataChecker {
    /**
     * The message when the entered piece of data is correct.
     */
    protected static final String CORRECT = "Correct";
    /**
     * The message when the input is empty.
     */
    protected static final String EMPTY = "The field is empty";
    /**
     * The message when the input contains illegal symbols.
     */
    protected static final String ILLEGAL_SYMBOLS = "Illegal symbols found";
    /**
     * The piece of data to be checked.
     */
    protected final String data;

    /**
     * A constructor, initializes the object.
     *
     * @param data the piece of data to be checked.
     * @author Oleksandr Danchenko
     */
    public DataChecker(String data) {
        this.data = data;
    }

    /**
     * Checks that the piece of data is correct.
     *
     * @return true if the piece of data is correct, false - otherwise.
     * @author Oleksandr Danchenko
     */
    public boolean isCorrect() {
        return getErrorMessage().equals(CORRECT);
    }

    /**
     * Checks the piece of data for correctness and returns the error associated with it.
     *
     * @return the error message for the piece of data.
     * @author Oleksandr Danchenko
     */
    public abstract String getErrorMessage();

    /**
     * A method that checks if all symbols in the piece of data are present.
     *
     * @return true if an illegal symbol is present, false - otherwise.
     * @author Oleksandr Danchenko
     */
    protected boolean areIllegalSymbolsPresent() {
        for (int i = 0; i < data.length(); i++) {
            if (isIllegalSymbol(data.charAt(i))) return true;
        }
        return false;
    }

    /**
     * Checks if the character is acceptable in the piece of data.
     *
     * @param c the character to be checked.
     * @return true if the symbol is available, false - otherwise.
     * @author Oleksandr Danchenko
     */
    protected abstract boolean isIllegalSymbol(char c);
}
