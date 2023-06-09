/*
Author: Aidan Baker
time spent: 8 minutes
Date: 26 May 2023
version #2
Changes: fixed the comparator to be null safe.
    Author: Oleksandr Danchenko
    Time spent: 4 minutes
    Date: 29 May 2023
*/

package logic.sorting.seats;

import logic.records.Seat;

/**
 * An implementation of the SeatComparator interface, which compares the two seats by their passenger's names lexicographically.
 *
 * @author Aidan Baker, Oleksandr Danchenko
 */
public class SortByName implements SeatComparator {
    /**
     * A constructor of the class, initializes the object.
     */
    public SortByName() {

    }

    /**
     * compares the two seats by their passenger's names lexicographically.
     *
     * @param seat1 the first seat.
     * @param seat2 the second seat.
     * @return an int value representing the relationship of the first seat to the second one.
     * @author Aidan Baker, Oleksandr Danchenko
     */
    @Override
    public int compare(Seat seat1, Seat seat2) {
        if (seat1.isEmpty() && seat2.isEmpty()) return EQUAL;
        else if (seat1.isEmpty()) return GREATER;
        else if (seat2.isEmpty()) return LESSER;

        String name1 = seat1.getPassenger().getFirstName() + seat1.getPassenger().getFirstName();
        String name2 = seat2.getPassenger().getFirstName() + seat2.getPassenger().getFirstName();
        if (name1.equals(name2)) return EQUAL;
        if (name1.compareTo(name2) > 0) return GREATER;
        return LESSER;
    }
}
