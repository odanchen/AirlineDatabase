/*
Author: Oleksandr Danchenko
time spent: 10 minutes
Date: 15 May 2023
version #1
*/

package logic.sorting.seats;

import logic.records.Seat;

/**
 * A SeatComparator interface, used to provide a way to compare two seats.
 *
 * @author Oleksandr Danchenko
 */
public interface SeatComparator {
    /**
     * A constant GREATER, the number to be returned by the method compare.
     * Used when the first argument is considered to be greater than the second one.
     */
    public static final int GREATER = 1;
    /**
     * A constant EQUAL, the number to be returned by the method compare.
     * Used when the first argument is considered to be equal than the second one.
     */
    public static final int EQUAL = 0;
    /**
     * A constant GREATER, the number to be returned by the method compare.
     * Used when the first argument is considered to be LESSER than the second one.
     */
    public static final int LESSER = -1;

    /**
     * A method, which is supposed to return a number which represents the relationship between the two seats provided
     *
     * @param seat1 the first seat.
     * @param seat2 the second seat.
     * @return GREATER, EQUAL, or LESSER, if the first seat is greater, equal, or lesser than the second one respectively.
     * @author Oleksandr Danchenko
     */
    public int compare(Seat seat1, Seat seat2);
}
