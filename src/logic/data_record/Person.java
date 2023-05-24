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
    private String firstName;
    /**
     * A String field, represents the last name of the person.
     */
    private String lastName;
    /**
     * A String field, represents the first date of birth of the person in the "DD-MM-YYYY" format.
     */
    private Date dateOfBirth;
    /**
     * A String field, represents the phone number of the person.
     */
    private String phoneNumber;
    /**
     * A String field, represents the email of the person.
     */
    private String email;

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
     * Sets the first name of the person.
     *
     * @param firstName The new first name of the person.
     * @author Oleksandr Danchenko
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Sets the last name of the person.
     *
     * @param lastName The new last name of the person.
     * @author Oleksandr Danchenko
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Sets the date of birth of the person.
     *
     * @param dateOfBirth The new date of birth of the person in the format "DD-MM-YYYY".
     * @author Oleksandr Danchenko
     */
    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    /**
     * Sets the phone number of the person.
     *
     * @param phoneNumber The new phone number of the person.
     * @author Oleksandr Danchenko
     */
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    /**
     * Sets the email address of the person.
     *
     * @param email The new email address of the person.
     * @author Oleksandr Danchenko
     */
    public void setEmail(String email) {
        this.email = email;
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
}
