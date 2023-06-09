/*
Author: Oleksandr Danchenko
time spent: 3 minutes
Date: 18 May 2023
version #1
*/

package logic.records;

/**
 * The DatabaseItem interface represents an item that can be stored in a database.
 *
 * @author Oleksandr Danchenko
 */
public interface DatabaseItem {
    /**
     * Retrieves the data of the database item.
     *
     * @return a String representing the item's data.
     * @author Oleksandr Danchenko
     */
    public String data();
}

