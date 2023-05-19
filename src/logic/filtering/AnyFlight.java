package logic.filtering;

import logic.data_record.FlightInfo;

public class AnyFlight implements FlightFilter{
    public AnyFlight() {

    }

    @Override
    public boolean predicate(FlightInfo flight) {
        return true;
    }
}
