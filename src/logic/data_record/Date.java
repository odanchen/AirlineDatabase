/*
Author: Oleksandr Danchenko
time spent: 30 minutes
Date: 18 May 2023
version #2
change: transferred the data checking part into the DateChecker class.
    time spent: 15 minutes
    Date: 22 May 2023
*/

package logic.data_record;

public class Date implements DatabaseItem {
    /**
     * The day in the current date.
     */
    private final int day;
    /**
     * The month in the current date.
     */
    private final int month;
    /**
     * The year in the current date.
     */
    private final int year;

    /**
     * A constructor of the class, initializes the object
     *
     * @param date a string representation of the date
     * @author Oleksandr Danchenko
     */
    public Date(String date) {
        day = Integer.parseInt(date.split("/")[0]);
        month = Integer.parseInt(date.split("/")[1]);
        year = Integer.parseInt(date.split("/")[2]);
    }

    /**
     * A getter method for the day of the date.
     *
     * @return the day of the month of the current date.
     * @author Oleksandr Danchenko
     */
    public int getDay() {
        return day;
    }

    /**
     * A getter method for the month of the date.
     *
     * @return the month of the year of the current date.
     * @author Oleksandr Danchenko
     */
    public int getMonth() {
        return month;
    }

    /**
     * A getter method for the year of the date.
     *
     * @return the year of the current date.
     * @author Oleksandr Danchenko
     */
    public int getYear() {
        return year;
    }


    /**
     * Converts a numerical representation of the month into a String representation of the month.
     *
     * @param month the month to be converted.
     * @return a String representation of the specified month.
     * @author Oleksandr Danchenko
     */
    private static String monthToString(int month) {
        switch (month) {
            case 1: return "Jan";
            case 2: return "Feb";
            case 3: return "Mar";
            case 4: return "Apr";
            case 5: return "May";
            case 6: return "Jun";
            case 7: return "Jul";
            case 8: return "Aug";
            case 9: return "Sep";
            case 10: return "Oct";
            case 11: return "Nov";
            default: return "Dec";
        }
    }

    /**
     * Converts the date into a String.
     *
     * @return a String representation of the date.
     * @author Oleksandr Danchenko
     */
    @Override
    public String toString() {
        return day + "/" + monthToString(month) + "/" + year;
    }


    /**
     * Converts the date into a String in the way it is supposed to be represented in a database.
     *
     * @return a database String representation of the date.
     * @author Oleksandr Danchenko
     */
    @Override
    public String data() {
        return fixDate(day) + "/" + fixDate(month) + "/" + year;
    }

    /**
     * Fixes the date to be in correct format.
     *
     * @param date the day or the month of a date.
     * @return the day or month in the correct format.
     * @author Oleksandr Danchenko
     */
    private String fixDate(int date) {
        if (date < 10) return "0" + date;
        return String.valueOf(date);
    }
}
