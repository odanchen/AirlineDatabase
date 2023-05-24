/*
Author: Oleksandr Danchenko
time spent: 15 minutes
date: 23 May 2023
version #1
*/

package logic.sorting.flights;

import logic.data_record.Date;
import logic.data_record.FlightInfo;

/**
 * The SortByDateAndTime class implements the FlightComparator interface and provides a way to compare flights by date and time.
 *
 * @author Oleksandr Danchenko
 */
public class SortByDateAndTime implements FlightComparator {
    /**
     * Constructs a SortByDateAndTime object.
     *
     * @author Oleksandr Danchenko
     */
    public SortByDateAndTime() {

    }

    /**
     * Compares two dates and returns the result of the comparison.
     *
     * @param date1 the first date.
     * @param date2 the second date.
     * @return an integer indicating the comparison result.
     * @author Oleksandr Danchenko
     */
    private int compareDates(Date date1, Date date2) {
        if (date1.getYear() == date2.getYear() && date1.getMonth() == date2.getMonth() && date1.getDay() == date2.getDay())
            return EQUAL;
        if (date1.getYear() > date2.getYear()) return GREATER;
        if (date1.getYear() < date2.getYear()) return LESSER;
        if (date1.getMonth() > date2.getMonth()) return GREATER;
        if (date1.getMonth() < date2.getMonth()) return LESSER;
        if (date1.getDay() > date2.getDay()) return GREATER;
        return LESSER;
    }

    /**
     * Compares two flight information objects based on their date and time.
     *
     * @param flight1 the first flight information object.
     * @param flight2 the second flight information object.
     * @return an integer indicating the comparison result.
     * @author Oleksandr Danchenko
     */
    @Override
    public int compare(FlightInfo flight1, FlightInfo flight2) {
        if (compareDates(flight1.getDate(), flight2.getDate()) != EQUAL)
            return compareDates(flight1.getDate(), flight2.getDate());
        if (flight1.getDepartureTime() > flight2.getDepartureTime()) return GREATER;
        if (flight1.getDepartureTime() == flight2.getDepartureTime()) return EQUAL;
        return LESSER;
    }
}

