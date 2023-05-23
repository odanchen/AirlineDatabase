/*
Author: Oleksandr Danchenko
time spent: 5 minutes
Date: 19 May 2023
version #1
*/

package logic.filtering;

import logic.data_record.FlightInfo;

/**
 * An interface to filter flights depending on different characteristics.
 *
 * @author Oleksandr Danchenko
 */
public interface FlightFilter {
    /**
     * A method used to determine whether a given flight is acceptable on the criteria specific to the interface implementation.
     *
     * @param flight the flight to be evaluated.
     * @return true if the flight is considered valid, false - otherwise.
     * @author Oleksandr Dancenko
     */
    public boolean predicate(FlightInfo flight);
}
