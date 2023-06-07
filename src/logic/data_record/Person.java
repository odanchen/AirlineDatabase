/*
Author: Oleksandr Danchenko
time spent: 30 minutes
Date: 16 May 2023
version #1
*/


package logic.data_record;

/**
 * Represents a person with basic information such as name, date of birth, phone number, and email.
 *
 * @author Oleksandr Danchenko
 */
public class Person implements DatabaseItem {
    /**
     * A String field, represents the first name of the person.
     */
    private final String firstName;
    /**
     * A String field, represents the last name of the person.
     */
    private final String lastName;
    /**
     * A String field, represents the first date of birth of the person in the "DD-MM-YYYY" format.
     */
    private final Date dateOfBirth;
    /**
     * A String field, represents the phone number of the person.
     */
    private final String phoneNumber;
    /**
     * A String field, represents the email of the person.
     */
    private final String email;

    /**
     * Constructs a person object with the specified information.
     *
     * @param firstName   The first name of the person.
     * @param lastName    The last name of the person.
     * @param dateOfBirth The date of birth of the person in the format "DD-MM-YYYY".
     * @param phoneNumber The phone number of the person.
     * @param email       The email address of the person.
     * @author Oleksandr Danchenko
     */
    public Person(String firstName, String lastName, Date dateOfBirth, String phoneNumber, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }

    /**
     * Returns the first name of the person.
     *
     * @return The first name of the person.
     * @author Oleksandr Danchenko
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Returns the last name of the person.
     *
     * @return The last name of the person.
     * @author Oleksandr Danchenko
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Returns the date of birth of the person.
     *
     * @return The date of birth of the person in the format "DD-MM-YYYY".
     * @author Oleksandr Danchenko
     */
    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    /**
     * Returns the phone number of the person.
     *
     * @return The phone number of the person.
     * @author Oleksandr Danchenko
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * Returns the email address of the person.
     *
     * @return The email address of the person.
     * @author Oleksandr Danchenko
     */
    public String getEmail() {
        return email;
    }

    /**
     * Converts the person into a String in the way it is supposed to be represented in a database.
     *
     * @return a database String representation of the person.
     * @author Oleksandr Danchenko
     */
    @Override
    public String data() {
        return firstName + "=" + lastName + "=" + dateOfBirth.data() + "=" + phoneNumber + "=" + email;
    }

    /**
     * Checks if the current instance of the Person equals to the provided one.
     *
     * @param person the instance for comparison.
     * @return true if both people are the same person, false - otherwise.
     * @author Oleksandr Danchenko
     */
    public boolean equals(Person person) {
        if (person == null) return false;
        return (firstName.equals(person.firstName) && lastName.equals(person.lastName) && dateOfBirth.equals(person.dateOfBirth));
    }
}
