/*
Author: Oleksandr Danchenko
time spent: 25 minutes
Date: 16 May 2023
version #1
*/


package logic.data_record;

/**
 * A Seat data class, represents a seat on a flight.
 *
 * @author Oleksandr Dacnhenko
 */
public class Seat implements DatabaseItem {
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
     * @param number    the number of the seat in a plane.
     * @param price     the price of the seat.
     * @param passenger the passenger on the seat.
     * @author Oleksandr Danchenko
     */
    public Seat(int number, int price, Person passenger) {
        this.passenger = passenger;
        this.price = price;
        this.number = number;
    }

    /**
     * A constructor of the class, initializes the object when the seat is empty.
     *
     * @param number the number of the seat in a plane.
     * @param price  the price of the seat.
     * @author Oleksandr Danchenko
     */
    public Seat(int number, int price) {
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

    /**
     * Converts the seat into a String in the way it is supposed to be represented in a database.
     *
     * @return a database String representation of the seat.
     * @author Oleksandr Danchenko
     */
    @Override
    public String data() {
        if (passenger != null) return number + "=" + price + "=" + passenger.data();
        return number + "=" + price;
    }
}
