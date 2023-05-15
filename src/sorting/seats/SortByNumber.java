/*
Author: Oleksandr Danchenko
time spent: 7 minutes
version #1
*/

package sorting.seats;

import data_record.Seat;

/**
 * An implementation of the SeatComparator interface, which compares the two seats by their number.
 */
public class SortByNumber implements SeatComparator {
    /**
     * A method, which is supposed to return a number which represents the relationship between the two seats provided
     *
     * @param seat1 the first seat.
     * @param seat2 the second seat.
     * @return GREATER, EQUAL, or LESSER, if the number of the first seat is greater, equal, or lesser than the second one respectively.
     * @author Oleksandr Danchenko
     */
    @Override
    public int compare(Seat seat1, Seat seat2) {
        if (seat1.getNumber() == seat2.getNumber()) return EQUAL;
        if (seat1.getNumber() > seat2.getNumber()) return GREATER;
        return LESSER;
    }
}
