package logic.sorting.seats;

import logic.data_record.Seat;

public class SortByName implements SeatComparator {

    @Override
    public int compare(Seat seat1, Seat seat2) {
        if (seat1.getPassenger().getFirstName().equals(seat2.getPassenger().getFirstName())) {
            if (seat1.getPassenger().getLastName().equals(seat2.getPassenger().getLastName())) return EQUAL;
            else if (seat1.getPassenger().getLastName().compareTo(seat2.getPassenger().getLastName()) > 0) return GREATER;
            else return LESSER;
        }

        if (seat1.getPassenger().getFirstName().compareTo(seat2.getPassenger().getFirstName()) > 0) return GREATER;
        return LESSER;
    }
}
