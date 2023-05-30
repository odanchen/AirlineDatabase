/*
Author: Oleksandr Danchenko
time spent: 30 minutes
Date: 15 May 2023
version #2
Changes: changed the algorithm to be quicksort and improved the comparators for null safety.
    time spent: 20 minutes.
    Date 29 May 2023.
*/

package logic.sorting.seats;

import logic.data_record.Seat;

/**
 * A SeatSorter class, used to sort seats in a specific order, determined by the comparator used with it.
 * The class is not meant to be instantiated because all of its methods are static.
 *
 * @author Oleksandr Danchenko
 */
public class SeatSorter {
    /**
     * A private constructor, prevents the class from being instantiated, because all of its methods are static.
     *
     * @author Oleksandr Danchenko
     */
    private SeatSorter() {

    }

    /**
     * A static method, copy of the given array.
     *
     * @param array  the array to be copied.
     * @return a copy of the given array.
     * @author Oleksandr Danchenko
     */
    private static Seat[] copyArr(Seat[] array) {
        Seat[] ans = new Seat[array.length];
        for (int i = 0; i  < array.length; i++) ans[i] = array[i];
        return ans;
    }

    /**
     * Swaps the elements at the two given indexes in the array.
     *
     * @param arr the array where the swap would occur.
     * @param i the index of the first element.
     * @param j the index of the second element.
     * @author Oleksandr Danchenko
     */
    private static void swap(Seat[] arr, int i, int j) {
        Seat temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    /**
     * Chooses the pivot and places it in the correct position.
     *
     * @param arr the array to be sorted.
     * @param begin the first index of the range.
     * @param end the last index of the range.
     * @param comp the comparator to compare the elements.
     * @return the index at which the pivot was placed.
     * @author Oleksadnr Danchenko
     */
    private static int placePivot(Seat[] arr, int begin, int end, SeatComparator comp) {
        int i = begin;
        for (int j = begin; j < end; j++) {
            if (comp.compare(arr[j], arr[end]) == SeatComparator.LESSER) swap(arr, j, i++);
        }
        swap(arr, end, i);
        return i;
    }

    /**
     * Runs the quicksort algorithm on the specified range of the array.
     *
     * @param array the array to be sorted.
     * @param begin the first index of the range.
     * @param end the last index of the range.
     * @param comp the comparator to compare the elements.
     * @author Oleksandr Danchenko
     */
    private static void qSort(Seat[] array, int begin, int end, SeatComparator comp) {
        if (begin >= end) return;

        int pivot = placePivot(array, begin, end, comp);
        qSort(array, begin, pivot - 1, comp);
        qSort(array, pivot + 1, end, comp);
    }

    /**
     * Sorts the array by the given criteria.
     *
     * @param array       the array to be sorted.
     * @param comparator an object which acts as a way to compare elements in the array, the criteria for logic.sorting.
     * @return an array sorted using the given criteria.
     * @author Oleksandr Danchenko.
     */
    public static Seat[] sort(Seat[] array, SeatComparator comparator) {
        Seat[] ans = copyArr(array);
        qSort(ans, 0, ans.length - 1, comparator);
        return ans;
    }
}
