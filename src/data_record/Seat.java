/*
Author: Oleksandr Danchenko
time spent: 20 minutes
version #1
*/


package data_record;

/**
 * A Seat data class, represents a seat on a flight.
 *
 * @author Oleksandr Dacnhenko
 */
public class Seat {
    /**
     * A field representing a passenger occupying the seat.
     */
    private Person passenger;
    /**
     * A field representing the price of the seat.
     */
    private final int price;
    /**
     * A filed representing the number of the seat in a plane.
     */
    private final int number;

    /**
     * A constructor of the class, initializes the object.
     *
     * @param passenger the passenger on the seat.
     * @param price     the price of the seat.
     * @param number    the number of the seat in a plane.
     * @author Oleksandr Danchenko
     */
    public Seat(Person passenger, int price, int number) {
        this.passenger = passenger;
        this.price = price;
        this.number = number;
    }

    /**
     * A constructor of the class, initializes the object when the seat is empty.
     *
     * @param price  the price of the seat.
     * @param number the number of the seat in a plane.
     * @author Oleksandr Danchenko
     */
    public Seat(int price, int number) {
        this.passenger = null;
        this.price = price;
        this.number = number;
    }

    /**
     * A getter method for the passenger.
     *
     * @return the passenger occupying the seat.
     * @author Oleksandr Danchenko
     */
    public Person getPassenger() {
        return passenger;
    }

    /**
     * A getter method for the price of the seat.
     *
     * @return the price of the seat.
     * @author Oleksandr Danchenko
     */
    public int getPrice() {
        return price;
    }

    /**
     * A getter method for the number of the seat.
     *
     * @return the number of the seat.
     * @author Oleksandr Danchenko
     */
    public int getNumber() {
        return number;
    }

    /**
     * A setter method for the price of the seat.
     *
     * @author Oleksandr Danchenko
     */
    public void setPassenger(Person passenger) {
        this.passenger = passenger;
    }

    /**
     * Checks if the seat is empty.
     *
     * @return true if the seat is empty, false - otherwise.
     * @author Oleksandr Danchenko
     */
    public boolean isEmpty() {
        return passenger == null;
    }

    /**
     * Cancels the seat. Frees the seat and removes the passenger form it.
     *
     * @author Oleksandr Danchenko
     */
    public void cancel() {
        this.passenger = null;
    }
}
