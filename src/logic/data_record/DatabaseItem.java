/*
Author: Oleksandr Danchenko
time spent: 3 minutes
version #1
*/

package logic.data_record;

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

