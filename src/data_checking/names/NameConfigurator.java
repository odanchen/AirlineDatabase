/*
Author: Aidan Baker
time spent: 1 minute
version #1
 */
package data_checking.names;

/**
 * A name configurator class, used to configure names in a specific way.
 * @author Aidan Baker
 */
public class NameConfigurator {
    /**
     * A private constructor, prevents the class from being instantiated, because all of its methods are static.
     */
    private NameConfigurator() {}

    /**
     * A static method, splits a name with spaces in it.
     * @param name the name to be split
     * @return the split name
     */
    public static String[] splitName(String name) {
        return name.split(" ");
    }
}
