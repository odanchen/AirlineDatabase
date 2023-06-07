/*
Author: Oleksandr Danchenko
time spent: 5 minutes
Date: 19 May 2023
version #1
*/

package logic.filtering;

import logic.data_record.FlightInfo;

/**
 * A class, implementation of the FlightFilter interface, used to pass a flight with any characteristics.
 *
 * @author Oleksandr Danchenko
 */
public class AnyFlight implements FlightFilter {
    /**
     * A constructor of the class.
     *
     * @author Oleksandr Danchenko
     */
    public AnyFlight() {

    }

    /**
     * Passes a flight with any characteristics.
     *
     * @param flight the flight to be evaluated.
     * @return true.
     * @author Oleksandr Danchenko
     */
    @Override
    public boolean predicate(FlightInfo flight) {
        return true;
    }
}
