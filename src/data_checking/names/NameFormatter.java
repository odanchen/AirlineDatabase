/*
Author: Aidan Baker
time spent: 5 minutes
version #1
*/
package data_checking.names;

/**
 * A name formatter class, used to format names in a specific way.
 * The class is not meant to be instantiated because all of its methods are static.
 *
 * @author Aidan Baker
 */
public class NameFormatter {
    /**
     * A private constructor, prevents the class from being instantiated, because all of its methods are static.
     *
     * @author Aidan Baker
     */
    private NameFormatter() {

    }

    /**
     * A static method, formats a name in a specific way.
     *
     * @param name the name to be formatted
     * @return the formatted name
     * @author Aidan Baker
     */
    public static String formatName(String name) {
        char[] chars = name.toCharArray();
        boolean capitalize = true;

        for (int i = 0; i < chars.length; i++) {
            char c = chars[i];

            if (Character.isWhitespace(c) || c == '-') {
                capitalize = true;
            } else if (capitalize) {
                chars[i] = Character.toUpperCase(c);
                capitalize = false;
            } else {
                chars[i] = Character.toLowerCase(c);
            }
        }

        return new String(chars);
    }
}
